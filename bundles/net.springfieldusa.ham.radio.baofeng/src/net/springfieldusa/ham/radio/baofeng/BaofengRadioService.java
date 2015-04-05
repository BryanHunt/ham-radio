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
import java.util.Collection;

import net.springfieldusa.ham.radio.RadioFactory;
import net.springfieldusa.ham.radio.RadioService;
import net.springfieldusa.ham.radio.RadioType;

public class BaofengRadioService implements RadioService
{
  public static String DELAY_CONNECT = "delay.connect";
  public static String DELAY_MAGIC_NUMBER = "delay.magic";
  public static String DELAY_IDENTIFIER = "delay.identifier";
  public static String DELAY_CLONE = "delay.clone";
  public static String DELAY_READ = "delay.read";
  
  private static Collection<RadioType> supportedRadios = new ArrayList<>();

  static
  {
    RadioType uv5r = RadioFactory.eINSTANCE.createRadioType();
    uv5r.setManufacturer("Baofeng");
    uv5r.setModel("UV-5RE Plus");
    uv5r.setProgrammingScheme("baofeng");
    uv5r.getImportDelays().put(DELAY_CONNECT, 100);
    uv5r.getImportDelays().put(DELAY_MAGIC_NUMBER, 200);
    uv5r.getImportDelays().put(DELAY_IDENTIFIER, 100);
    uv5r.getImportDelays().put(DELAY_CLONE, 100);
    uv5r.getImportDelays().put(DELAY_READ, 60);
    
    supportedRadios.add(uv5r);
  }

  @Override
  public Collection<RadioType> getSupportedRadios()
  {
    return supportedRadios;
  }
}
