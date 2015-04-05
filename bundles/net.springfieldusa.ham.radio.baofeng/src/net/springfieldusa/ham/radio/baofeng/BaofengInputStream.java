/*******************************************************************************
 * Copyright (c) 2015 Bryan Hunt
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Bryan Hunt - initial API and implementation
 *******************************************************************************/

package net.springfieldusa.ham.radio.baofeng;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;

import net.springfieldusa.ham.radio.Radio;
import net.springfieldusa.ham.radio.RadioFactory;
import net.springfieldusa.ham.radio.RadioMemory;
import net.springfieldusa.ham.radio.RadioMemoryBlock;
import net.springfieldusa.ham.radio.RadioMemorySegment;
import net.springfieldusa.ham.radio.RadioType;
import net.springfieldusa.ham.radio.TransferProgressMonitor;
import net.springfieldusa.io.serial.BaudRate;
import net.springfieldusa.io.serial.DataBits;
import net.springfieldusa.io.serial.FlowControl;
import net.springfieldusa.io.serial.IOStream;
import net.springfieldusa.io.serial.Parity;
import net.springfieldusa.io.serial.StopBits;

public class BaofengInputStream extends InputStream implements URIConverter.Loadable
{
  public static final int MEMORY_SEGMENT_SIZE = 0x40;

  private TransferProgressMonitor progressMonitor;
  private RadioType radioType;
  private IOStream stream;

  public BaofengInputStream(TransferProgressMonitor progressMonitor, RadioType radioType) throws IOException
  {
    this.progressMonitor = progressMonitor;
    this.radioType = radioType;
  }

  @Override
  public void loadResource(Resource resource) throws IOException
  {
    int totalWork = 0x1800 / MEMORY_SEGMENT_SIZE + (0x2000 - 0x1EC0) / MEMORY_SEGMENT_SIZE + 2;
    progressMonitor.beginTask("Import from radio", totalWork);
    RadioMemory memory = RadioFactory.eINSTANCE.createRadioMemory();

    progressMonitor.subTask("Connecting to radio");

    try (IOStream stream = connect(resource.getURI().devicePath()))
    {
      if (stream == null)
        throw new IOException("Failed to connect to radio");

      OutputStream out = stream.createOuputStream();
      InputStream in = stream.createInputStream();

      out.write(0x02);
      Thread.sleep(radioType.getImportDelays().get(BaofengRadioService.DELAY_IDENTIFIER));
      byte[] identifier = new byte[8];
      in.read(identifier);

      out.write(0x06);
      Thread.sleep(radioType.getImportDelays().get(BaofengRadioService.DELAY_CLONE));
      byte response = (byte) in.read();

      if (response != 0x06)
        throw new IOException("Refused to clone");

      if (progressMonitor.isCanceled())
        return;

      progressMonitor.worked(1);
      progressMonitor.subTask("Downloading memory");

      RadioMemoryBlock channelBlock = RadioFactory.eINSTANCE.createRadioMemoryBlock();
      channelBlock.setStartAddress(0x0);
      channelBlock.setEndAddress(0x1800 - 1);
      channelBlock.setSegmentSize(MEMORY_SEGMENT_SIZE);
      memory.getBlocks().add(channelBlock);
      
      for (int address = 0; address < 0x1800; address += MEMORY_SEGMENT_SIZE)
      {
        channelBlock.getSegments().add(createSegment(stream, address));
        progressMonitor.worked(1);

        if (progressMonitor.isCanceled())
          return;
      }

      RadioMemoryBlock settingsBlock = RadioFactory.eINSTANCE.createRadioMemoryBlock();
      settingsBlock.setStartAddress(0x1EC0);
      settingsBlock.setEndAddress(0x2000 - 1);
      settingsBlock.setSegmentSize(MEMORY_SEGMENT_SIZE);
      memory.getBlocks().add(settingsBlock);

      for (int address = 0x1EC0; address < 0x2000; address += MEMORY_SEGMENT_SIZE)
      {
        settingsBlock.getSegments().add(createSegment(stream, address));
        progressMonitor.worked(1);

        if (progressMonitor.isCanceled())
          return;
      }

      progressMonitor.subTask("Processing memory");
      Radio radio = RadioFactory.eINSTANCE.createRadio();
      radio.setMemoryImage(memory);

      for (int i = 0; i < 128; i++)
      {
        int channelAddress = BaofengChannelBuilder.getChannelAddress(i);
        RadioMemorySegment channelSegment = channelBlock.findSegment(channelAddress);
        int channelOffset = channelAddress - channelSegment.getStartAddress();

        if (channelSegment.getContents().get(channelOffset) != (byte) 0xff)
          radio.getChannels().add(BaofengChannelBuilder.buildChannel(i, memory));
      }

      resource.getContents().add(radio);
      progressMonitor.worked(1);

    }
    catch (InterruptedException e)
    {
      throw new IOException("Download was interrupted");
    }
    finally
    {
      progressMonitor.done();
    }
  }

  @Override
  public int read() throws IOException
  {
    // InputStream requires that we implement this function. It will never be
    // called
    // since this implementation implements URIConverter.Loadable. The
    // loadResource()
    // function will be called instead.

    return 0;
  }

  private IOStream connect(String device) throws IOException, InterruptedException
  {
    if (device == null || device.isEmpty())
      throw new IOException("Serial device not specified");

    byte[] magicNumber = new byte[] { 0x50, (byte) 0xBB, (byte) 0xFF, 0x20, 0x12, 0x07, 0x25 };

    stream = new IOStream(device);
    stream.setBaud(BaudRate.B9600);
    stream.setDataBits(DataBits.EIGHT);
    stream.setParity(Parity.NONE);
    stream.setStopBits(StopBits.ONE);
    stream.setUseFlowControl(FlowControl.NO);
    stream.setReadMode(1, 10);

    Thread.sleep(radioType.getImportDelays().get(BaofengRadioService.DELAY_CONNECT));

    OutputStream out = stream.createOuputStream();
    InputStream in = stream.createInputStream();

    // System.out.println("Sending magic number");

    out.write(magicNumber);

    Thread.sleep(radioType.getImportDelays().get(BaofengRadioService.DELAY_MAGIC_NUMBER));

    // System.out.println("Reading response");

    if (in.read() != 0x06)
    {
      stream.close();
      return null;
    }

    // System.out.println("Connected to radio");
    return stream;
  }

  private RadioMemorySegment createSegment(IOStream stream, int address) throws IOException, InterruptedException
  {
    RadioMemorySegment segment = RadioFactory.eINSTANCE.createRadioMemorySegment();
    segment.setStartAddress(address);
    segment.setSegmentSize(MEMORY_SEGMENT_SIZE);

    InputStream in = stream.createInputStream();
    OutputStream out = stream.createOuputStream();

    byte[] command = new byte[] { 'S', (byte) (segment.getStartAddress() >> 8), (byte) segment.getStartAddress(), (byte) MEMORY_SEGMENT_SIZE };
    out.write(command);
    Thread.sleep(50);

    byte[] reply = new byte[4];
    in.read(reply);

    if (reply[0] != 'X')
      throw new IOException("Radio did not respond with memory segment");

    Thread.sleep(radioType.getImportDelays().get(BaofengRadioService.DELAY_READ));
    byte[] data = new byte[MEMORY_SEGMENT_SIZE];
    in.read(data);
    
    for(byte element : data)
    segment.getContents().add(element);

    out.write(0x06);
    byte ack = (byte) in.read();

    if (ack != 0x06)
      throw new IOException("Unexpected ack Reply " + ack);

    return segment;
  }
}