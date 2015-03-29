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

public enum WavelengthUnit implements UnitOfMeasure
{
  M(1, "m"), CM(.01, "cm");

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

  private WavelengthUnit(double multiplier, String displayName)
  {
    this.multiplier = multiplier;
    this.displayName = displayName;
  }
}
