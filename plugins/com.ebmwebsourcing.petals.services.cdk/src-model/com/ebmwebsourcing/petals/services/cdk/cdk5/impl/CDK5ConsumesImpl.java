/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.cdk.cdk5.impl;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Consumes;
import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;
import com.sun.java.xml.ns.jbi.impl.ConsumesImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CDK5 Consumes</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.cdk.cdk5.impl.CDK5ConsumesImpl#getTimeout <em>Timeout</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.cdk.cdk5.impl.CDK5ConsumesImpl#getOperation <em>Operation</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.cdk.cdk5.impl.CDK5ConsumesImpl#getMep <em>Mep</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CDK5ConsumesImpl extends ConsumesImpl implements CDK5Consumes {
	/**
	 * The default value of the '{@link #getTimeout() <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeout()
	 * @generated
	 * @ordered
	 */
	protected static final int TIMEOUT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTimeout() <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeout()
	 * @generated
	 * @ordered
	 */
	protected int timeout = TIMEOUT_EDEFAULT;

	/**
	 * The default value of the '{@link #getOperation() <em>Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperation()
	 * @generated
	 * @ordered
	 */
	protected static final QName OPERATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOperation() <em>Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperation()
	 * @generated
	 * @ordered
	 */
	protected QName operation = OPERATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getMep() <em>Mep</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMep()
	 * @generated
	 * @ordered
	 */
	protected static final String MEP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMep() <em>Mep</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMep()
	 * @generated
	 * @ordered
	 */
	protected String mep = MEP_EDEFAULT;

	/**
	 * This is true if the Mep attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean mepESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CDK5ConsumesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Cdk5Package.Literals.CDK5_CONSUMES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getTimeout() {
		return this.timeout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTimeout(int newTimeout) {
		int oldTimeout = this.timeout;
		this.timeout = newTimeout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Cdk5Package.CDK5_CONSUMES__TIMEOUT, oldTimeout, this.timeout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public QName getOperation() {
		return this.operation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOperation(QName newOperation) {
		QName oldOperation = this.operation;
		this.operation = newOperation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Cdk5Package.CDK5_CONSUMES__OPERATION, oldOperation, this.operation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMep() {
		return this.mep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMep(String newMep) {
		String oldMep = this.mep;
		this.mep = newMep;
		boolean oldMepESet = this.mepESet;
		this.mepESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Cdk5Package.CDK5_CONSUMES__MEP, oldMep, this.mep, !oldMepESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetMep() {
		String oldMep = this.mep;
		boolean oldMepESet = this.mepESet;
		this.mep = MEP_EDEFAULT;
		this.mepESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, Cdk5Package.CDK5_CONSUMES__MEP, oldMep, MEP_EDEFAULT, oldMepESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetMep() {
		return this.mepESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Cdk5Package.CDK5_CONSUMES__TIMEOUT:
				return getTimeout();
			case Cdk5Package.CDK5_CONSUMES__OPERATION:
				return getOperation();
			case Cdk5Package.CDK5_CONSUMES__MEP:
				return getMep();
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
			case Cdk5Package.CDK5_CONSUMES__TIMEOUT:
				setTimeout((Integer)newValue);
				return;
			case Cdk5Package.CDK5_CONSUMES__OPERATION:
				setOperation((QName)newValue);
				return;
			case Cdk5Package.CDK5_CONSUMES__MEP:
				setMep((String)newValue);
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
			case Cdk5Package.CDK5_CONSUMES__TIMEOUT:
				setTimeout(TIMEOUT_EDEFAULT);
				return;
			case Cdk5Package.CDK5_CONSUMES__OPERATION:
				setOperation(OPERATION_EDEFAULT);
				return;
			case Cdk5Package.CDK5_CONSUMES__MEP:
				unsetMep();
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
			case Cdk5Package.CDK5_CONSUMES__TIMEOUT:
				return this.timeout != TIMEOUT_EDEFAULT;
			case Cdk5Package.CDK5_CONSUMES__OPERATION:
				return OPERATION_EDEFAULT == null ? this.operation != null : !OPERATION_EDEFAULT.equals(this.operation);
			case Cdk5Package.CDK5_CONSUMES__MEP:
				return isSetMep();
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
		result.append(" (timeout: ");
		result.append(this.timeout);
		result.append(", operation: ");
		result.append(this.operation);
		result.append(", mep: ");
		if (this.mepESet) result.append(this.mep); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //CDK5ConsumesImpl
