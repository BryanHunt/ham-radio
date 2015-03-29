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
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Manufacturer</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link net.springfieldusa.ham.radio.RadioManufacturer#getName
 * <em>Name</em>}</li>
 * <li>{@link net.springfieldusa.ham.radio.RadioManufacturer#getModels
 * <em>Models</em>}</li>
 * </ul>
 *
 * @see net.springfieldusa.ham.radio.RadioPackage#getRadioManufacturer()
 * @model kind="class"
 * @generated
 */
public class RadioManufacturer extends MinimalEObjectImpl.Container implements EObject
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
   * The cached value of the '{@link #getModels() <em>Models</em>}' containment
   * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getModels()
   * @generated
   * @ordered
   */
  protected EList<RadioModel> models;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected RadioManufacturer()
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
    return RadioPackage.Literals.RADIO_MANUFACTURER;
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
   * @see net.springfieldusa.ham.radio.RadioPackage#getRadioManufacturer_Name()
   * @model unique="false"
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * Sets the value of the '
   * {@link net.springfieldusa.ham.radio.RadioManufacturer#getName <em>Name</em>
   * }' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
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
      eNotify(new ENotificationImpl(this, Notification.SET, RadioPackage.RADIO_MANUFACTURER__NAME, oldName, name));
  }

  /**
   * Returns the value of the '<em><b>Models</b></em>' containment reference
   * list. The list contents are of type
   * {@link net.springfieldusa.ham.radio.RadioModel}. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Models</em>' containment reference list isn't
   * clear, there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Models</em>' containment reference list.
   * @see net.springfieldusa.ham.radio.RadioPackage#getRadioManufacturer_Models()
   * @model containment="true"
   * @generated
   */
  public EList<RadioModel> getModels()
  {
    if (models == null)
    {
      models = new EObjectContainmentEList<RadioModel>(RadioModel.class, this, RadioPackage.RADIO_MANUFACTURER__MODELS);
    }
    return models;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case RadioPackage.RADIO_MANUFACTURER__MODELS:
        return ((InternalEList<?>) getModels()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case RadioPackage.RADIO_MANUFACTURER__NAME:
        return getName();
      case RadioPackage.RADIO_MANUFACTURER__MODELS:
        return getModels();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case RadioPackage.RADIO_MANUFACTURER__NAME:
        setName((String) newValue);
        return;
      case RadioPackage.RADIO_MANUFACTURER__MODELS:
        getModels().clear();
        getModels().addAll((Collection<? extends RadioModel>) newValue);
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
      case RadioPackage.RADIO_MANUFACTURER__NAME:
        setName(NAME_EDEFAULT);
        return;
      case RadioPackage.RADIO_MANUFACTURER__MODELS:
        getModels().clear();
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
      case RadioPackage.RADIO_MANUFACTURER__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case RadioPackage.RADIO_MANUFACTURER__MODELS:
        return models != null && !models.isEmpty();
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

} // RadioManufacturer
