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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Memory Segment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link net.springfieldusa.ham.radio.RadioMemorySegment#getStartAddress <em>Start Address</em>}</li>
 *   <li>{@link net.springfieldusa.ham.radio.RadioMemorySegment#getSegmentSize <em>Segment Size</em>}</li>
 *   <li>{@link net.springfieldusa.ham.radio.RadioMemorySegment#getContents <em>Contents</em>}</li>
 * </ul>
 *
 * @see net.springfieldusa.ham.radio.RadioPackage#getRadioMemorySegment()
 * @model kind="class"
 * @generated
 */
public class RadioMemorySegment extends MinimalEObjectImpl.Container implements EObject
{
  /**
   * The default value of the '{@link #getStartAddress() <em>Start Address</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStartAddress()
   * @generated
   * @ordered
   */
  protected static final int START_ADDRESS_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getStartAddress() <em>Start Address</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStartAddress()
   * @generated
   * @ordered
   */
  protected int startAddress = START_ADDRESS_EDEFAULT;

  /**
   * The default value of the '{@link #getSegmentSize() <em>Segment Size</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSegmentSize()
   * @generated
   * @ordered
   */
  protected static final int SEGMENT_SIZE_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getSegmentSize() <em>Segment Size</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSegmentSize()
   * @generated
   * @ordered
   */
  protected int segmentSize = SEGMENT_SIZE_EDEFAULT;

  /**
   * The cached value of the '{@link #getContents() <em>Contents</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContents()
   * @generated
   * @ordered
   */
  protected EList<Byte> contents;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected RadioMemorySegment()
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
    return RadioPackage.Literals.RADIO_MEMORY_SEGMENT;
  }

  /**
   * Returns the value of the '<em><b>Start Address</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Start Address</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Start Address</em>' attribute.
   * @see #setStartAddress(int)
   * @see net.springfieldusa.ham.radio.RadioPackage#getRadioMemorySegment_StartAddress()
   * @model unique="false"
   * @generated
   */
  public int getStartAddress()
  {
    return startAddress;
  }

  /**
   * Sets the value of the '{@link net.springfieldusa.ham.radio.RadioMemorySegment#getStartAddress <em>Start Address</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Start Address</em>' attribute.
   * @see #getStartAddress()
   * @generated
   */
  public void setStartAddress(int newStartAddress)
  {
    int oldStartAddress = startAddress;
    startAddress = newStartAddress;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RadioPackage.RADIO_MEMORY_SEGMENT__START_ADDRESS, oldStartAddress, startAddress));
  }

  /**
   * Returns the value of the '<em><b>Segment Size</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Segment Size</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Segment Size</em>' attribute.
   * @see #setSegmentSize(int)
   * @see net.springfieldusa.ham.radio.RadioPackage#getRadioMemorySegment_SegmentSize()
   * @model unique="false"
   * @generated
   */
  public int getSegmentSize()
  {
    return segmentSize;
  }

  /**
   * Sets the value of the '{@link net.springfieldusa.ham.radio.RadioMemorySegment#getSegmentSize <em>Segment Size</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Segment Size</em>' attribute.
   * @see #getSegmentSize()
   * @generated
   */
  public void setSegmentSize(int newSegmentSize)
  {
    int oldSegmentSize = segmentSize;
    segmentSize = newSegmentSize;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RadioPackage.RADIO_MEMORY_SEGMENT__SEGMENT_SIZE, oldSegmentSize, segmentSize));
  }

  /**
   * Returns the value of the '<em><b>Contents</b></em>' attribute list.
   * The list contents are of type {@link java.lang.Byte}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Contents</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Contents</em>' attribute list.
   * @see net.springfieldusa.ham.radio.RadioPackage#getRadioMemorySegment_Contents()
   * @model unique="false"
   * @generated
   */
  public EList<Byte> getContents()
  {
    if (contents == null)
    {
      contents = new EDataTypeEList<Byte>(Byte.class, this, RadioPackage.RADIO_MEMORY_SEGMENT__CONTENTS);
    }
    return contents;
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
      case RadioPackage.RADIO_MEMORY_SEGMENT__START_ADDRESS:
        return getStartAddress();
      case RadioPackage.RADIO_MEMORY_SEGMENT__SEGMENT_SIZE:
        return getSegmentSize();
      case RadioPackage.RADIO_MEMORY_SEGMENT__CONTENTS:
        return getContents();
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
      case RadioPackage.RADIO_MEMORY_SEGMENT__START_ADDRESS:
        setStartAddress((Integer)newValue);
        return;
      case RadioPackage.RADIO_MEMORY_SEGMENT__SEGMENT_SIZE:
        setSegmentSize((Integer)newValue);
        return;
      case RadioPackage.RADIO_MEMORY_SEGMENT__CONTENTS:
        getContents().clear();
        getContents().addAll((Collection<? extends Byte>)newValue);
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
      case RadioPackage.RADIO_MEMORY_SEGMENT__START_ADDRESS:
        setStartAddress(START_ADDRESS_EDEFAULT);
        return;
      case RadioPackage.RADIO_MEMORY_SEGMENT__SEGMENT_SIZE:
        setSegmentSize(SEGMENT_SIZE_EDEFAULT);
        return;
      case RadioPackage.RADIO_MEMORY_SEGMENT__CONTENTS:
        getContents().clear();
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
      case RadioPackage.RADIO_MEMORY_SEGMENT__START_ADDRESS:
        return startAddress != START_ADDRESS_EDEFAULT;
      case RadioPackage.RADIO_MEMORY_SEGMENT__SEGMENT_SIZE:
        return segmentSize != SEGMENT_SIZE_EDEFAULT;
      case RadioPackage.RADIO_MEMORY_SEGMENT__CONTENTS:
        return contents != null && !contents.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (startAddress: ");
    result.append(startAddress);
    result.append(", segmentSize: ");
    result.append(segmentSize);
    result.append(", contents: ");
    result.append(contents);
    result.append(')');
    return result.toString();
  }

} // RadioMemorySegment
