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

import java.util.List;

public interface RadioMemory
{
  boolean isEmpty();

  void clear();

  MemorySegment getSegment(int address);

  List<MemorySegment> getSegments();

  byte[] getContents();

  void setContents(byte[] contents);
}
