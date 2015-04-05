/**
 * Copyright (c) 2015 Bryan Hunt
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Bryan Hunt - initial API and implementation
 * 
 */
package net.springfieldusa.ham.radio;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Memory</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link net.springfieldusa.ham.radio.RadioMemory#getBlocks <em>Blocks</em>}</li>
 * </ul>
 *
 * @see net.springfieldusa.ham.radio.RadioPackage#getRadioMemory()
 * @model kind="class"
 * @generated
 */
public class RadioMemory extends MinimalEObjectImpl.Container implements EObject
{
  /**
   * The cached value of the '{@link #getBlocks() <em>Blocks</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBlocks()
   * @generated
   * @ordered
   */
  protected EList<RadioMemoryBlock> blocks;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected RadioMemory()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return RadioPackage.Literals.RADIO_MEMORY;
  }

  /**
   * Returns the value of the '<em><b>Blocks</b></em>' containment reference list.
   * The list contents are of type {@link net.springfieldusa.ham.radio.RadioMemoryBlock}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Blocks</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Blocks</em>' containment reference list.
   * @see net.springfieldusa.ham.radio.RadioPackage#getRadioMemory_Blocks()
   * @model containment="true"
   * @generated
   */
  public EList<RadioMemoryBlock> getBlocks()
  {
    if (blocks == null)
    {
      blocks = new EObjectContainmentEList<RadioMemoryBlock>(RadioMemoryBlock.class, this, RadioPackage.RADIO_MEMORY__BLOCKS);
    }
    return blocks;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model unique="false" addressUnique="false"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='for (final <%net.springfieldusa.ham.radio.RadioMemoryBlock%> block : this.blocks)\n{\n\tif (((block.startAddress <= address) && (block.endAddress > address)))\n\t{\n\t\treturn block.findSegment(address);\n\t}\n}\nreturn null;'"
   * @generated
   */
  public RadioMemorySegment findSegment(final int address)
  {
    for (final RadioMemoryBlock block : this.blocks)
    {
      if (((block.startAddress <= address) && (block.endAddress > address)))
      {
        return block.findSegment(address);
      }
    }
    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case RadioPackage.RADIO_MEMORY__BLOCKS:
        return ((InternalEList<?>)getBlocks()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case RadioPackage.RADIO_MEMORY__BLOCKS:
        return getBlocks();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case RadioPackage.RADIO_MEMORY__BLOCKS:
        getBlocks().clear();
        getBlocks().addAll((Collection<? extends RadioMemoryBlock>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case RadioPackage.RADIO_MEMORY__BLOCKS:
        getBlocks().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case RadioPackage.RADIO_MEMORY__BLOCKS:
        return blocks != null && !blocks.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException
  {
    switch (operationID)
    {
      case RadioPackage.RADIO_MEMORY___FIND_SEGMENT__INT:
        return findSegment((Integer)arguments.get(0));
    }
    return super.eInvoke(operationID, arguments);
  }

} // RadioMemory
