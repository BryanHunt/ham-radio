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

package net.springfieldusa.ham.radio.program;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MemorySegment
{
  private int startAddress;
  private byte[] contents;

  public MemorySegment(int startAddress, int size)
  {
    this.startAddress = startAddress;
    this.contents = new byte[size];
  }

  public int getStartAddress()
  {
    return startAddress;
  }

  public int getSize()
  {
    return contents.length;
  }

  public void loadFrom(InputStream in) throws IOException
  {
    int numberBytesRead = in.read(contents);

    if (numberBytesRead != contents.length)
      throw new IOException("Expected to read " + contents.length + " bytes but only got " + numberBytesRead + " bytes");
  }

  public void writeTo(OutputStream out) throws IOException
  {
    out.write(contents);
  }

  public byte[] getContents()
  {
    return contents;
  }

  public void setContents(byte[] contents, int startPos)
  {
    System.arraycopy(contents, startPos, this.contents, 0, this.contents.length);
  }
}
