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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.springfieldusa.ham.radio.program.MemorySegment;
import net.springfieldusa.ham.radio.program.RadioMemory;

public class BaofengMemory implements RadioMemory
{
  public static final int MEMORY_SEGMENT_SIZE = 0x40;
  public static final int MEMORY_SIZE = (0x2000 - 0x1EC0 + 0x1800) * MEMORY_SEGMENT_SIZE;
  private List<MemorySegment> memorySegments = new ArrayList<>();

  public void addSegment(MemorySegment segment)
  {
    memorySegments.add(segment);
  }

  @Override
  public boolean isEmpty()
  {
    return memorySegments.isEmpty();
  }

  @Override
  public void clear()
  {
    memorySegments.clear();
  }

  @Override
  public MemorySegment getSegment(int address)
  {
    int segmentIndex = address / MEMORY_SEGMENT_SIZE;
    return memorySegments.get(segmentIndex);
  }

  @Override
  public List<MemorySegment> getSegments()
  {
    return Collections.unmodifiableList(memorySegments);
  }

  @Override
  public byte[] getContents()
  {
    if (memorySegments.isEmpty())
      return null;

    byte[] buffer = new byte[MEMORY_SIZE];

    for (int i = 0; i < MEMORY_SIZE; i += MEMORY_SEGMENT_SIZE)
      System.arraycopy(getSegment(i / MEMORY_SEGMENT_SIZE), 0, buffer, i, MEMORY_SEGMENT_SIZE);

    return buffer;
  }

  @Override
  public void setContents(byte[] contents)
  {
    memorySegments.clear();

    for (int address = 0; address < 0x1800; address += MEMORY_SEGMENT_SIZE)
    {
      MemorySegment segment = new MemorySegment(address, MEMORY_SEGMENT_SIZE);
      segment.setContents(contents, address);
      memorySegments.add(segment);
    }

    for (int address = 0x1EC0; address < 0x2000; address += MEMORY_SEGMENT_SIZE)
    {
      MemorySegment segment = new MemorySegment(address, MEMORY_SEGMENT_SIZE);
      segment.setContents(contents, 0x1800 + address - 0x1EC0);
      memorySegments.add(segment);
    }
  }
}
