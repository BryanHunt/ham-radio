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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Memory Block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link net.springfieldusa.ham.radio.RadioMemoryBlock#getStartAddress <em>Start Address</em>}</li>
 *   <li>{@link net.springfieldusa.ham.radio.RadioMemoryBlock#getEndAddress <em>End Address</em>}</li>
 *   <li>{@link net.springfieldusa.ham.radio.RadioMemoryBlock#getSegmentSize <em>Segment Size</em>}</li>
 *   <li>{@link net.springfieldusa.ham.radio.RadioMemoryBlock#getSegments <em>Segments</em>}</li>
 * </ul>
 *
 * @see net.springfieldusa.ham.radio.RadioPackage#getRadioMemoryBlock()
 * @model kind="class"
 * @generated
 */
public class RadioMemoryBlock extends MinimalEObjectImpl.Container implements EObject
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
   * The default value of the '{@link #getEndAddress() <em>End Address</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEndAddress()
   * @generated
   * @ordered
   */
  protected static final int END_ADDRESS_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getEndAddress() <em>End Address</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEndAddress()
   * @generated
   * @ordered
   */
  protected int endAddress = END_ADDRESS_EDEFAULT;

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
   * The cached value of the '{@link #getSegments() <em>Segments</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSegments()
   * @generated
   * @ordered
   */
  protected EList<RadioMemorySegment> segments;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected RadioMemoryBlock()
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
    return RadioPackage.Literals.RADIO_MEMORY_BLOCK;
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
   * @see net.springfieldusa.ham.radio.RadioPackage#getRadioMemoryBlock_StartAddress()
   * @model unique="false"
   * @generated
   */
  public int getStartAddress()
  {
    return startAddress;
  }

  /**
   * Sets the value of the '{@link net.springfieldusa.ham.radio.RadioMemoryBlock#getStartAddress <em>Start Address</em>}' attribute.
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
      eNotify(new ENotificationImpl(this, Notification.SET, RadioPackage.RADIO_MEMORY_BLOCK__START_ADDRESS, oldStartAddress, startAddress));
  }

  /**
   * Returns the value of the '<em><b>End Address</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>End Address</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>End Address</em>' attribute.
   * @see #setEndAddress(int)
   * @see net.springfieldusa.ham.radio.RadioPackage#getRadioMemoryBlock_EndAddress()
   * @model unique="false"
   * @generated
   */
  public int getEndAddress()
  {
    return endAddress;
  }

  /**
   * Sets the value of the '{@link net.springfieldusa.ham.radio.RadioMemoryBlock#getEndAddress <em>End Address</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>End Address</em>' attribute.
   * @see #getEndAddress()
   * @generated
   */
  public void setEndAddress(int newEndAddress)
  {
    int oldEndAddress = endAddress;
    endAddress = newEndAddress;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RadioPackage.RADIO_MEMORY_BLOCK__END_ADDRESS, oldEndAddress, endAddress));
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
   * @see net.springfieldusa.ham.radio.RadioPackage#getRadioMemoryBlock_SegmentSize()
   * @model unique="false"
   * @generated
   */
  public int getSegmentSize()
  {
    return segmentSize;
  }

  /**
   * Sets the value of the '{@link net.springfieldusa.ham.radio.RadioMemoryBlock#getSegmentSize <em>Segment Size</em>}' attribute.
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
      eNotify(new ENotificationImpl(this, Notification.SET, RadioPackage.RADIO_MEMORY_BLOCK__SEGMENT_SIZE, oldSegmentSize, segmentSize));
  }

  /**
   * Returns the value of the '<em><b>Segments</b></em>' containment reference list.
   * The list contents are of type {@link net.springfieldusa.ham.radio.RadioMemorySegment}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Segments</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Segments</em>' containment reference list.
   * @see net.springfieldusa.ham.radio.RadioPackage#getRadioMemoryBlock_Segments()
   * @model containment="true"
   * @generated
   */
  public EList<RadioMemorySegment> getSegments()
  {
    if (segments == null)
    {
      segments = new EObjectContainmentEList<RadioMemorySegment>(RadioMemorySegment.class, this, RadioPackage.RADIO_MEMORY_BLOCK__SEGMENTS);
    }
    return segments;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model unique="false" addressUnique="false"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='int segmentIndex = ((address - this.startAddress) / this.segmentSize);\nreturn this.segments.get(segmentIndex);'"
   * @generated
   */
  public RadioMemorySegment findSegment(final int address)
  {
    int segmentIndex = ((address - this.startAddress) / this.segmentSize);
    return this.segments.get(segmentIndex);
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
      case RadioPackage.RADIO_MEMORY_BLOCK__SEGMENTS:
        return ((InternalEList<?>)getSegments()).basicRemove(otherEnd, msgs);
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
      case RadioPackage.RADIO_MEMORY_BLOCK__START_ADDRESS:
        return getStartAddress();
      case RadioPackage.RADIO_MEMORY_BLOCK__END_ADDRESS:
        return getEndAddress();
      case RadioPackage.RADIO_MEMORY_BLOCK__SEGMENT_SIZE:
        return getSegmentSize();
      case RadioPackage.RADIO_MEMORY_BLOCK__SEGMENTS:
        return getSegments();
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
      case RadioPackage.RADIO_MEMORY_BLOCK__START_ADDRESS:
        setStartAddress((Integer)newValue);
        return;
      case RadioPackage.RADIO_MEMORY_BLOCK__END_ADDRESS:
        setEndAddress((Integer)newValue);
        return;
      case RadioPackage.RADIO_MEMORY_BLOCK__SEGMENT_SIZE:
        setSegmentSize((Integer)newValue);
        return;
      case RadioPackage.RADIO_MEMORY_BLOCK__SEGMENTS:
        getSegments().clear();
        getSegments().addAll((Collection<? extends RadioMemorySegment>)newValue);
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
      case RadioPackage.RADIO_MEMORY_BLOCK__START_ADDRESS:
        setStartAddress(START_ADDRESS_EDEFAULT);
        return;
      case RadioPackage.RADIO_MEMORY_BLOCK__END_ADDRESS:
        setEndAddress(END_ADDRESS_EDEFAULT);
        return;
      case RadioPackage.RADIO_MEMORY_BLOCK__SEGMENT_SIZE:
        setSegmentSize(SEGMENT_SIZE_EDEFAULT);
        return;
      case RadioPackage.RADIO_MEMORY_BLOCK__SEGMENTS:
        getSegments().clear();
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
      case RadioPackage.RADIO_MEMORY_BLOCK__START_ADDRESS:
        return startAddress != START_ADDRESS_EDEFAULT;
      case RadioPackage.RADIO_MEMORY_BLOCK__END_ADDRESS:
        return endAddress != END_ADDRESS_EDEFAULT;
      case RadioPackage.RADIO_MEMORY_BLOCK__SEGMENT_SIZE:
        return segmentSize != SEGMENT_SIZE_EDEFAULT;
      case RadioPackage.RADIO_MEMORY_BLOCK__SEGMENTS:
        return segments != null && !segments.isEmpty();
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
      case RadioPackage.RADIO_MEMORY_BLOCK___FIND_SEGMENT__INT:
        return findSegment((Integer)arguments.get(0));
    }
    return super.eInvoke(operationID, arguments);
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
    result.append(", endAddress: ");
    result.append(endAddress);
    result.append(", segmentSize: ");
    result.append(segmentSize);
    result.append(')');
    return result.toString();
  }

} // RadioMemoryBlock
