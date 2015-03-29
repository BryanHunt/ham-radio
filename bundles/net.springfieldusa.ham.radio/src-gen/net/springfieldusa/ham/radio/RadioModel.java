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

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Model</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link net.springfieldusa.ham.radio.RadioModel#getName <em>Name</em>}
 * </li>
 * <li>{@link net.springfieldusa.ham.radio.RadioModel#getType <em>Type</em>}
 * </li>
 * </ul>
 *
 * @see net.springfieldusa.ham.radio.RadioPackage#getRadioModel()
 * @model kind="class"
 * @generated
 */
public class RadioModel extends MinimalEObjectImpl.Container implements EObject
{
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getType() <em>Type</em>}' reference. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getType()
   * @generated
   * @ordered
   */
  protected RadioType type;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected RadioModel()
  {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return RadioPackage.Literals.RADIO_MODEL;
  }

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute. <!--
   * begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear, there really
   * should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see net.springfieldusa.ham.radio.RadioPackage#getRadioModel_Name()
   * @model unique="false"
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * Sets the value of the '
   * {@link net.springfieldusa.ham.radio.RadioModel#getName <em>Name</em>}'
   * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value
   *          the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RadioPackage.RADIO_MODEL__NAME, oldName, name));
  }

  /**
   * Returns the value of the '<em><b>Type</b></em>' reference. <!--
   * begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' reference isn't clear, there really
   * should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Type</em>' reference.
   * @see #setType(RadioType)
   * @see net.springfieldusa.ham.radio.RadioPackage#getRadioModel_Type()
   * @model
   * @generated
   */
  public RadioType getType()
  {
    if (type != null && type.eIsProxy())
    {
      InternalEObject oldType = (InternalEObject) type;
      type = (RadioType) eResolveProxy(oldType);
      if (type != oldType)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, RadioPackage.RADIO_MODEL__TYPE, oldType, type));
      }
    }
    return type;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public RadioType basicGetType()
  {
    return type;
  }

  /**
   * Sets the value of the '
   * {@link net.springfieldusa.ham.radio.RadioModel#getType <em>Type</em>}'
   * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value
   *          the new value of the '<em>Type</em>' reference.
   * @see #getType()
   * @generated
   */
  public void setType(RadioType newType)
  {
    RadioType oldType = type;
    type = newType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RadioPackage.RADIO_MODEL__TYPE, oldType, type));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case RadioPackage.RADIO_MODEL__NAME:
        return getName();
      case RadioPackage.RADIO_MODEL__TYPE:
        if (resolve)
          return getType();
        return basicGetType();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case RadioPackage.RADIO_MODEL__NAME:
        setName((String) newValue);
        return;
      case RadioPackage.RADIO_MODEL__TYPE:
        setType((RadioType) newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case RadioPackage.RADIO_MODEL__NAME:
        setName(NAME_EDEFAULT);
        return;
      case RadioPackage.RADIO_MODEL__TYPE:
        setType((RadioType) null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case RadioPackage.RADIO_MODEL__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case RadioPackage.RADIO_MODEL__TYPE:
        return type != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy())
      return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} // RadioModel
