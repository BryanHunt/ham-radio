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

package net.springfieldusa.ham.units;

public enum FrequencyUnit implements UnitOfMeasure
{
  Hz(1, "Hz"), KHz(1000, "kHz"), MHz(1000000, "MHz"), GHz(1000000000, "GHz");

  private double multiplier;
  private String displayName;

  @Override
  public double getMultiplier()
  {
    return multiplier;
  }

  @Override
  public String getDisplayName()
  {
    return displayName;
  }

  private FrequencyUnit(double multiplier, String displayName)
  {
    this.multiplier = multiplier;
    this.displayName = displayName;
  }
}
