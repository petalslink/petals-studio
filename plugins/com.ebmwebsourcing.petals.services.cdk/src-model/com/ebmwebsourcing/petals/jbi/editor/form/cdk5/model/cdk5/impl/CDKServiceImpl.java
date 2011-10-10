/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl;

import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDKService;
import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;

import com.sun.java.xml.ns.jbi.impl.AbstractEndpointImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CDK Service</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDKServiceImpl#getCdkExtContainer <em>Cdk Ext Container</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDKServiceImpl#getTimeout <em>Timeout</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDKServiceImpl#getSuInterceptors <em>Su Interceptors</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class CDKServiceImpl extends AbstractEndpointImpl implements CDKService {
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
	 * The default value of the '{@link #getSuInterceptors() <em>Su Interceptors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuInterceptors()
	 * @generated
	 * @ordered
	 */
	protected static final String SU_INTERCEPTORS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSuInterceptors() <em>Su Interceptors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuInterceptors()
	 * @generated
	 * @ordered
	 */
	protected String suInterceptors = SU_INTERCEPTORS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CDKServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Cdk5Package.Literals.CDK_SERVICE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getCdkExtContainer() {
		return (FeatureMap)getGroup().<FeatureMap.Entry>list(Cdk5Package.Literals.CDK_SERVICE__CDK_EXT_CONTAINER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeout(int newTimeout) {
		int oldTimeout = timeout;
		timeout = newTimeout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Cdk5Package.CDK_SERVICE__TIMEOUT, oldTimeout, timeout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSuInterceptors() {
		return suInterceptors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSuInterceptors(String newSuInterceptors) {
		String oldSuInterceptors = suInterceptors;
		suInterceptors = newSuInterceptors;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Cdk5Package.CDK_SERVICE__SU_INTERCEPTORS, oldSuInterceptors, suInterceptors));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Cdk5Package.CDK_SERVICE__CDK_EXT_CONTAINER:
				return ((InternalEList<?>)getCdkExtContainer()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Cdk5Package.CDK_SERVICE__CDK_EXT_CONTAINER:
				if (coreType) return getCdkExtContainer();
				return ((FeatureMap.Internal)getCdkExtContainer()).getWrapper();
			case Cdk5Package.CDK_SERVICE__TIMEOUT:
				return getTimeout();
			case Cdk5Package.CDK_SERVICE__SU_INTERCEPTORS:
				return getSuInterceptors();
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
			case Cdk5Package.CDK_SERVICE__CDK_EXT_CONTAINER:
				((FeatureMap.Internal)getCdkExtContainer()).set(newValue);
				return;
			case Cdk5Package.CDK_SERVICE__TIMEOUT:
				setTimeout((Integer)newValue);
				return;
			case Cdk5Package.CDK_SERVICE__SU_INTERCEPTORS:
				setSuInterceptors((String)newValue);
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
			case Cdk5Package.CDK_SERVICE__CDK_EXT_CONTAINER:
				getCdkExtContainer().clear();
				return;
			case Cdk5Package.CDK_SERVICE__TIMEOUT:
				setTimeout(TIMEOUT_EDEFAULT);
				return;
			case Cdk5Package.CDK_SERVICE__SU_INTERCEPTORS:
				setSuInterceptors(SU_INTERCEPTORS_EDEFAULT);
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
			case Cdk5Package.CDK_SERVICE__CDK_EXT_CONTAINER:
				return !getCdkExtContainer().isEmpty();
			case Cdk5Package.CDK_SERVICE__TIMEOUT:
				return timeout != TIMEOUT_EDEFAULT;
			case Cdk5Package.CDK_SERVICE__SU_INTERCEPTORS:
				return SU_INTERCEPTORS_EDEFAULT == null ? suInterceptors != null : !SU_INTERCEPTORS_EDEFAULT.equals(suInterceptors);
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
		result.append(timeout);
		result.append(", suInterceptors: ");
		result.append(suInterceptors);
		result.append(')');
		return result.toString();
	}

} //CDKServiceImpl
