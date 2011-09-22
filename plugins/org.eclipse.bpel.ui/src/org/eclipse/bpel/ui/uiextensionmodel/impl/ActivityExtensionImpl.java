/**
 * <copyright>
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 * </copyright>
 *
 * $Id: ActivityExtensionImpl.java,v 1.3 2008/05/04 11:05:28 odanilov Exp $
 */
package org.eclipse.bpel.ui.uiextensionmodel.impl;

import org.eclipse.bpel.ui.uiextensionmodel.ActivityExtension;
import org.eclipse.bpel.ui.uiextensionmodel.UiextensionmodelPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity Extension</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpel.ui.uiextensionmodel.impl.ActivityExtensionImpl#getX <em>X</em>}</li>
 *   <li>{@link org.eclipse.bpel.ui.uiextensionmodel.impl.ActivityExtensionImpl#getY <em>Y</em>}</li>
 *   <li>{@link org.eclipse.bpel.ui.uiextensionmodel.impl.ActivityExtensionImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link org.eclipse.bpel.ui.uiextensionmodel.impl.ActivityExtensionImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link org.eclipse.bpel.ui.uiextensionmodel.impl.ActivityExtensionImpl#isImplicit <em>Implicit</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActivityExtensionImpl extends EObjectImpl implements ActivityExtension {
	/**
	 * The default value of the '{@link #getX() <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getX()
	 * @generated
	 * @ordered
	 */
	protected static final int X_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getX() <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getX()
	 * @generated
	 * @ordered
	 */
	protected int x = X_EDEFAULT;

	/**
	 * The default value of the '{@link #getY() <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getY()
	 * @generated
	 * @ordered
	 */
	protected static final int Y_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getY() <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getY()
	 * @generated
	 * @ordered
	 */
	protected int y = Y_EDEFAULT;

	/**
	 * The default value of the '{@link #getWidth() <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidth()
	 * @generated
	 * @ordered
	 */
	protected static final int WIDTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidth()
	 * @generated
	 * @ordered
	 */
	protected int width = WIDTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getHeight() <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeight()
	 * @generated
	 * @ordered
	 */
	protected static final int HEIGHT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getHeight() <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeight()
	 * @generated
	 * @ordered
	 */
	protected int height = HEIGHT_EDEFAULT;

	/**
	 * The default value of the '{@link #isImplicit() <em>Implicit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isImplicit()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IMPLICIT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isImplicit() <em>Implicit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isImplicit()
	 * @generated
	 * @ordered
	 */
	protected boolean implicit = IMPLICIT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActivityExtensionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UiextensionmodelPackage.Literals.ACTIVITY_EXTENSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setX(int newX) {
		int oldX = this.x;
		this.x = newX;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiextensionmodelPackage.ACTIVITY_EXTENSION__X, oldX, this.x));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setY(int newY) {
		int oldY = this.y;
		this.y = newY;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiextensionmodelPackage.ACTIVITY_EXTENSION__Y, oldY, this.y));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWidth(int newWidth) {
		int oldWidth = this.width;
		this.width = newWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiextensionmodelPackage.ACTIVITY_EXTENSION__WIDTH, oldWidth, this.width));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHeight(int newHeight) {
		int oldHeight = this.height;
		this.height = newHeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiextensionmodelPackage.ACTIVITY_EXTENSION__HEIGHT, oldHeight, this.height));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isImplicit() {
		return this.implicit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImplicit(boolean newImplicit) {
		boolean oldImplicit = this.implicit;
		this.implicit = newImplicit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiextensionmodelPackage.ACTIVITY_EXTENSION__IMPLICIT, oldImplicit, this.implicit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case UiextensionmodelPackage.ACTIVITY_EXTENSION__X:
			return getX();
		case UiextensionmodelPackage.ACTIVITY_EXTENSION__Y:
			return getY();
		case UiextensionmodelPackage.ACTIVITY_EXTENSION__WIDTH:
			return getWidth();
		case UiextensionmodelPackage.ACTIVITY_EXTENSION__HEIGHT:
			return getHeight();
		case UiextensionmodelPackage.ACTIVITY_EXTENSION__IMPLICIT:
			return isImplicit() ? Boolean.TRUE : Boolean.FALSE;
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case UiextensionmodelPackage.ACTIVITY_EXTENSION__X:
			setX(((Integer)newValue).intValue());
			return;
		case UiextensionmodelPackage.ACTIVITY_EXTENSION__Y:
			setY(((Integer)newValue).intValue());
			return;
		case UiextensionmodelPackage.ACTIVITY_EXTENSION__WIDTH:
			setWidth(((Integer)newValue).intValue());
			return;
		case UiextensionmodelPackage.ACTIVITY_EXTENSION__HEIGHT:
			setHeight(((Integer)newValue).intValue());
			return;
		case UiextensionmodelPackage.ACTIVITY_EXTENSION__IMPLICIT:
			setImplicit(((Boolean)newValue).booleanValue());
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
	public void eUnset(int featureID) {
		switch (featureID) {
		case UiextensionmodelPackage.ACTIVITY_EXTENSION__X:
			setX(X_EDEFAULT);
			return;
		case UiextensionmodelPackage.ACTIVITY_EXTENSION__Y:
			setY(Y_EDEFAULT);
			return;
		case UiextensionmodelPackage.ACTIVITY_EXTENSION__WIDTH:
			setWidth(WIDTH_EDEFAULT);
			return;
		case UiextensionmodelPackage.ACTIVITY_EXTENSION__HEIGHT:
			setHeight(HEIGHT_EDEFAULT);
			return;
		case UiextensionmodelPackage.ACTIVITY_EXTENSION__IMPLICIT:
			setImplicit(IMPLICIT_EDEFAULT);
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
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case UiextensionmodelPackage.ACTIVITY_EXTENSION__X:
			return this.x != X_EDEFAULT;
		case UiextensionmodelPackage.ACTIVITY_EXTENSION__Y:
			return this.y != Y_EDEFAULT;
		case UiextensionmodelPackage.ACTIVITY_EXTENSION__WIDTH:
			return this.width != WIDTH_EDEFAULT;
		case UiextensionmodelPackage.ACTIVITY_EXTENSION__HEIGHT:
			return this.height != HEIGHT_EDEFAULT;
		case UiextensionmodelPackage.ACTIVITY_EXTENSION__IMPLICIT:
			return this.implicit != IMPLICIT_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (x: ");
		result.append(this.x);
		result.append(", y: ");
		result.append(this.y);
		result.append(", width: ");
		result.append(this.width);
		result.append(", height: ");
		result.append(this.height);
		result.append(", implicit: ");
		result.append(this.implicit);
		result.append(')');
		return result.toString();
	}

} //ActivityExtensionImpl