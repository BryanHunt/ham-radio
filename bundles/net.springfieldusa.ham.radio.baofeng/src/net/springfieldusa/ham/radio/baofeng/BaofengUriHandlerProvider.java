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

import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipselabs.emodeling.UriHandlerProvider;

public class BaofengUriHandlerProvider implements UriHandlerProvider
{
  private BaofengUriHandler uriHandler;

  @Override
  public synchronized URIHandler getURIHandler()
  {
    if (uriHandler == null)
      uriHandler = new BaofengUriHandler();

    return uriHandler;
  }
}
