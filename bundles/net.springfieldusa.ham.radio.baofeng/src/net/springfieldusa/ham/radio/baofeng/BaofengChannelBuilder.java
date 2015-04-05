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

import net.springfieldusa.ham.radio.OperatingFrequency;
import net.springfieldusa.ham.radio.ProgrammedRadioChannel;
import net.springfieldusa.ham.radio.RadioChannel;
import net.springfieldusa.ham.radio.RadioFactory;
import net.springfieldusa.ham.radio.RadioMemory;
import net.springfieldusa.ham.radio.RadioMemorySegment;
import net.springfieldusa.ham.radio.Tone;
import net.springfieldusa.ham.radio.TransmitPower;
import net.springfieldusa.ham.units.FrequencyUnit;
import net.springfieldusa.ham.units.PowerUnit;

public class BaofengChannelBuilder
{
  private static byte CHANNEL_SIZE = 0x10;
  private static int RX_FREQUENCY_OFFSET = 0x0;
  private static int TX_FREQUENCY_OFFSET = 0x4;
  private static int RX_TONE_OFFSET = 0x08;
  private static int TX_TONE_OFFSET = 0x0A;
  private static int POWER_OFFSET = 0x0E;

  public static int getChannelAddress(int channelNumber)
  {
    return channelNumber * CHANNEL_SIZE;
  }

  public static ProgrammedRadioChannel buildChannel(int channelNumber, RadioMemory memory) throws IOException
  {
    if (channelNumber < 0 || channelNumber > 127)
      throw new IOException("Invalid channel number");

    int channelAddress = getChannelAddress(channelNumber);
    RadioMemorySegment channelSegment = memory.findSegment(channelAddress);
    int channelOffset = channelAddress - channelSegment.getStartAddress();

    ProgrammedRadioChannel programmedChannel = RadioFactory.eINSTANCE.createProgrammedRadioChannel();
    programmedChannel.setChannelNumber(channelNumber);
    programmedChannel.setPower(decodePower(channelSegment.getContents().get(channelOffset + POWER_OFFSET)));

    RadioChannel channel = RadioFactory.eINSTANCE.createRadioChannel();
    programmedChannel.setBaseChannel(channel);

    channel.setChannelName(decodeChannelName(channelAddress, memory));
    channel.setReceiveTone(decodeTone(channelSegment, channelOffset, RX_TONE_OFFSET));
    channel.setReceiveFrequency(decodeFrequency(channelSegment, channelOffset, RX_FREQUENCY_OFFSET));
    channel.setTransmitTone(decodeTone(channelSegment, channelOffset, TX_TONE_OFFSET));
    channel.setTransmitFrequency(decodeFrequency(channelSegment, channelOffset, TX_FREQUENCY_OFFSET));

    return programmedChannel;
  }

  private static String decodeChannelName(int channelAddress, RadioMemory memory)
  {
    int channelNameAddress = 0x1000 + channelAddress;
    RadioMemorySegment segment = memory.findSegment(channelNameAddress);
    int offset = channelNameAddress - segment.getStartAddress();

    StringBuilder channelName = new StringBuilder();

    for (int i = 0; i < 7; i++)
    {
      byte value = segment.getContents().get(offset + i);

      if (value != (byte) 0xff)
        channelName.append((char) value);
    }

    return channelName.toString();
  }

  private static OperatingFrequency decodeFrequency(RadioMemorySegment channelSegment, int channelOffset, int valueOffset)
  {
    long frequency = 0;
    byte value;

    value = channelSegment.getContents().get(channelOffset + valueOffset);
    frequency += ((value >> 4) & 0xf) * 100;
    frequency += (value & 0xf) * 10;

    value = channelSegment.getContents().get(channelOffset + valueOffset + 1);
    frequency += ((value >> 4) & 0xf) * 10000;
    frequency += (value & 0xf) * 1000;

    value = channelSegment.getContents().get(channelOffset + valueOffset + 2);
    frequency += ((value >> 4) & 0xf) * 1000000;
    frequency += (value & 0xf) * 100000;

    value = channelSegment.getContents().get(channelOffset + valueOffset + 3);
    frequency += ((value >> 4) & 0xf) * 100000000;
    frequency += (value & 0xf) * 10000000;

    OperatingFrequency operatingFrequency = RadioFactory.eINSTANCE.createOperatingFrequency();
    operatingFrequency.setValue(frequency);
    operatingFrequency.setPreferredDisplayUnits(FrequencyUnit.MHz);
    operatingFrequency.setPreferredDisplayResolution(6);
    return operatingFrequency;
  }

  private static Tone decodeTone(RadioMemorySegment channelSegment, int channelOffset, int valueOffset)
  {
    int value = (byte) channelSegment.getContents().get(channelOffset + valueOffset) & 0xff | (channelSegment.getContents().get(channelOffset + valueOffset + 1) << 8);
    return Tone.get(value);
  }

  private static TransmitPower decodePower(byte power)
  {
    // 0x0 is 5 watts, 0x1 is 1 watt

    TransmitPower transmitPower = RadioFactory.eINSTANCE.createTransmitPower();
    transmitPower.setValue(power == 0x0 ? 5L : 1L);
    transmitPower.setPreferredDisplayUnits(PowerUnit.W);
    transmitPower.setPreferredDisplayResolution(0);
    return transmitPower;
  }
}
