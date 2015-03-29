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

package net.springfieldusa.ham.radio.registry;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.springfieldusa.ham.radio.RadioFactory;
import net.springfieldusa.ham.radio.RadioManufacturer;
import net.springfieldusa.ham.radio.RadioModel;
import net.springfieldusa.ham.radio.RadioRegistry;
import net.springfieldusa.ham.radio.RadioService;
import net.springfieldusa.ham.radio.RadioType;

public class RadioRegistryComponent implements RadioRegistry
{
  Map<String, RadioManufacturer> registeredRadios = new HashMap<>();

  @Override
  public Collection<RadioManufacturer> getRegisteredRadios()
  {
    return Collections.unmodifiableCollection(registeredRadios.values());
  }

  public synchronized void bindRadioService(RadioService radioService)
  {
    Collection<RadioType> supportedRadios = radioService.getSupportedRadios();

    for (RadioType radioType : supportedRadios)
    {
      RadioManufacturer manufacturer = registeredRadios.get(radioType.getManufacturer());

      if (manufacturer == null)
      {
        manufacturer = RadioFactory.eINSTANCE.createRadioManufacturer();
        manufacturer.setName(radioType.getManufacturer());
        registeredRadios.put(radioType.getManufacturer(), manufacturer);
      }

      RadioModel model = RadioFactory.eINSTANCE.createRadioModel();
      model.setName(radioType.getModel());
      model.setType(radioType);

      manufacturer.getModels().add(model);
    }
  }

  public synchronized void unbindRadioService(RadioService radioService)
  {
    Collection<RadioType> supportedRadios = radioService.getSupportedRadios();

    for (RadioType radioType : supportedRadios)
    {
      RadioManufacturer manufacturer = registeredRadios.get(radioType.getManufacturer());

      if (manufacturer == null)
        continue;

      Iterator<RadioModel> iterator = manufacturer.getModels().iterator();

      while (iterator.hasNext())
      {
        if (iterator.next().getName().equals(radioType.getModel()))
        {
          iterator.remove();
        }
      }

      if (manufacturer.getModels().isEmpty())
        registeredRadios.remove(manufacturer.getName());
    }
  }
}
