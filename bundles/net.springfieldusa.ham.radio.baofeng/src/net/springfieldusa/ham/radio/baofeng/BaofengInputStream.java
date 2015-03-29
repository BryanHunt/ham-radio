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
import net.springfieldusa.ham.radio.program.MemorySegment;
import net.springfieldusa.ham.radio.program.TransferProgressMonitor;
import net.springfieldusa.io.serial.BaudRate;
import net.springfieldusa.io.serial.DataBits;
import net.springfieldusa.io.serial.FlowControl;
import net.springfieldusa.io.serial.IOStream;
import net.springfieldusa.io.serial.Parity;
import net.springfieldusa.io.serial.StopBits;

public class BaofengInputStream extends InputStream implements URIConverter.Loadable
{
  private TransferProgressMonitor progressMonitor;
  private IOStream stream;

  public BaofengInputStream(TransferProgressMonitor progressMonitor) throws IOException
  {
    this.progressMonitor = progressMonitor;
  }

  @Override
  public void loadResource(Resource resource) throws IOException
  {
    int totalWork = 0x1800 / BaofengMemory.MEMORY_SEGMENT_SIZE + (0x2000 - 0x1EC0) / BaofengMemory.MEMORY_SEGMENT_SIZE + 2;
    progressMonitor.beginTask("Import from radio", totalWork);
    BaofengMemory memory = new BaofengMemory();

    progressMonitor.subTask("Connecting to radio");

    try (IOStream stream = connect(resource.getURI().devicePath()))
    {
      if (stream == null)
        throw new IOException("Failed to connect to radio");

      OutputStream out = stream.createOuputStream();
      InputStream in = stream.createInputStream();

      out.write(0x02);
      Thread.sleep(100);
      byte[] identifier = new byte[8];
      in.read(identifier);

      out.write(0x06);
      Thread.sleep(100);
      byte response = (byte) in.read();

      if (response != 0x06)
        throw new IOException("Refused to clone");

      if (progressMonitor.isCanceled())
        return;

      progressMonitor.worked(1);
      progressMonitor.subTask("Downloading memory");

      for (int address = 0; address < 0x1800; address += BaofengMemory.MEMORY_SEGMENT_SIZE)
      {
        memory.addSegment(createSegment(stream, address));
        progressMonitor.worked(1);

        if (progressMonitor.isCanceled())
          return;
      }

      for (int address = 0x1EC0; address < 0x2000; address += BaofengMemory.MEMORY_SEGMENT_SIZE)
      {
        memory.addSegment(createSegment(stream, address));
        progressMonitor.worked(1);

        if (progressMonitor.isCanceled())
          return;
      }

      progressMonitor.subTask("Processing memory");
      Radio radio = RadioFactory.eINSTANCE.createRadio();

      for (int i = 0; i < 128; i++)
      {
        int channelAddress = BaofengChannelBuilder.getChannelAddress(i);
        MemorySegment channelSegment = memory.getSegment(channelAddress);
        int channelOffset = channelAddress - channelSegment.getStartAddress();

        if (channelSegment.getContents()[channelOffset] != (byte) 0xff)
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

  private IOStream connect(String device) throws IOException
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

    try
    {
      Thread.sleep(100);
    }
    catch (InterruptedException e)
    {
      throw new IOException("Interrupted while attempting to connect");
    }

    OutputStream out = stream.createOuputStream();
    InputStream in = stream.createInputStream();

    // System.out.println("Sending magic number");

    out.write(magicNumber);

    try
    {
      Thread.sleep(200);
    }
    catch (InterruptedException e)
    {
      throw new IOException("Interrupted while attempting to connect");
    }

    // System.out.println("Reading response");

    if (in.read() != 0x06)
    {
      stream.close();
      return null;
    }

    // System.out.println("Connected to radio");
    return stream;
  }

  private MemorySegment createSegment(IOStream stream, int address) throws IOException, InterruptedException
  {
    MemorySegment segment = new MemorySegment(address, BaofengMemory.MEMORY_SEGMENT_SIZE);

    InputStream in = stream.createInputStream();
    OutputStream out = stream.createOuputStream();

    byte[] command = new byte[] { 'S', (byte) (segment.getStartAddress() >> 8), (byte) segment.getStartAddress(), (byte) segment.getSize() };
    out.write(command);
    Thread.sleep(50);

    byte[] reply = new byte[4];
    in.read(reply);

    if (reply[0] != 'X')
      throw new IOException("Radio did not respond with memory segment");

//    Thread.sleep(60);
    Thread.sleep(100);
    segment.loadFrom(in);

    out.write(0x06);
    byte ack = (byte) in.read();

    if (ack != 0x06)
      throw new IOException("Unexpected ack Reply " + ack);

    return segment;
  }
}