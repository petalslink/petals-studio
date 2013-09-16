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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.cdk.cdk5.RetryPolicy;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Retry Policy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.cdk.cdk5.impl.RetryPolicyImpl#getAttempts <em>Attempts</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.cdk.cdk5.impl.RetryPolicyImpl#getDelay <em>Delay</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RetryPolicyImpl extends EObjectImpl implements RetryPolicy {
	/**
	 * The default value of the '{@link #getAttempts() <em>Attempts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttempts()
	 * @generated
	 * @ordered
	 */
	protected static final int ATTEMPTS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getAttempts() <em>Attempts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttempts()
	 * @generated
	 * @ordered
	 */
	protected int attempts = ATTEMPTS_EDEFAULT;

	/**
	 * This is true if the Attempts attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean attemptsESet;

	/**
	 * The default value of the '{@link #getDelay() <em>Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelay()
	 * @generated
	 * @ordered
	 */
	protected static final long DELAY_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getDelay() <em>Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelay()
	 * @generated
	 * @ordered
	 */
	protected long delay = DELAY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RetryPolicyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Cdk5Package.Literals.RETRY_POLICY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getAttempts() {
		return this.attempts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAttempts(int newAttempts) {
		int oldAttempts = this.attempts;
		this.attempts = newAttempts;
		boolean oldAttemptsESet = this.attemptsESet;
		this.attemptsESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Cdk5Package.RETRY_POLICY__ATTEMPTS, oldAttempts, this.attempts, !oldAttemptsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetAttempts() {
		int oldAttempts = this.attempts;
		boolean oldAttemptsESet = this.attemptsESet;
		this.attempts = ATTEMPTS_EDEFAULT;
		this.attemptsESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, Cdk5Package.RETRY_POLICY__ATTEMPTS, oldAttempts, ATTEMPTS_EDEFAULT, oldAttemptsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetAttempts() {
		return this.attemptsESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public long getDelay() {
		return this.delay;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDelay(long newDelay) {
		long oldDelay = this.delay;
		this.delay = newDelay;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Cdk5Package.RETRY_POLICY__DELAY, oldDelay, this.delay));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Cdk5Package.RETRY_POLICY__ATTEMPTS:
				return getAttempts();
			case Cdk5Package.RETRY_POLICY__DELAY:
				return getDelay();
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
			case Cdk5Package.RETRY_POLICY__ATTEMPTS:
				setAttempts((Integer)newValue);
				return;
			case Cdk5Package.RETRY_POLICY__DELAY:
				setDelay((Long)newValue);
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
			case Cdk5Package.RETRY_POLICY__ATTEMPTS:
				unsetAttempts();
				return;
			case Cdk5Package.RETRY_POLICY__DELAY:
				setDelay(DELAY_EDEFAULT);
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
			case Cdk5Package.RETRY_POLICY__ATTEMPTS:
				return isSetAttempts();
			case Cdk5Package.RETRY_POLICY__DELAY:
				return this.delay != DELAY_EDEFAULT;
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
		result.append(" (attempts: ");
		if (this.attemptsESet) result.append(this.attempts); else result.append("<unset>");
		result.append(", delay: ");
		result.append(this.delay);
		result.append(')');
		return result.toString();
	}

} //RetryPolicyImpl
