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

public interface TransferProgressMonitor
{
  String OPTION_PROGRESS_MONITOR = "PROGRESS_MONITOR";

  void beginTask(String name, int totalWork);

  void done();

  boolean isCanceled();

  void setTaskName(String name);

  void subTask(String name);

  void worked(int work);
}
