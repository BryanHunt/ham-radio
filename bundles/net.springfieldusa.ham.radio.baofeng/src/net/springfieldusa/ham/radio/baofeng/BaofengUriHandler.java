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
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;

import net.springfieldusa.ham.radio.program.TransferProgressMonitor;

public class BaofengUriHandler extends URIHandlerImpl
{
  @Override
  public boolean canHandle(URI uri)
  {
    return "baofeng".equalsIgnoreCase(uri.scheme());
  }

  @Override
  public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException
  {
    // TODO Auto-generated method stub
    return super.createOutputStream(uri, options);
  }

  @Override
  public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException
  {
    return new BaofengInputStream((TransferProgressMonitor) options.get(TransferProgressMonitor.OPTION_PROGRESS_MONITOR));
  }
}
