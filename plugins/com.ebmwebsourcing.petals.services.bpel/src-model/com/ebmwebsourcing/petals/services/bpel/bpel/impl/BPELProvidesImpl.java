/**
 * Copyright (c) 2011-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 */
package com.ebmwebsourcing.petals.services.bpel.bpel.impl;

import com.ebmwebsourcing.petals.services.bpel.bpel.BPELProvides;
import com.ebmwebsourcing.petals.services.bpel.bpel.BpelPackage;

import com.sun.java.xml.ns.jbi.impl.ProvidesImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>BPEL Provides</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.bpel.bpel.impl.BPELProvidesImpl#getBpel <em>Bpel</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.bpel.bpel.impl.BPELProvidesImpl#getPoolsize <em>Poolsize</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BPELProvidesImpl extends ProvidesImpl implements BPELProvides {
	/**
	 * The default value of the '{@link #getBpel() <em>Bpel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBpel()
	 * @generated
	 * @ordered
	 */
	protected static final String BPEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBpel() <em>Bpel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBpel()
	 * @generated
	 * @ordered
	 */
	protected String bpel = BPEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getPoolsize() <em>Poolsize</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPoolsize()
	 * @generated
	 * @ordered
	 */
	protected static final int POOLSIZE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPoolsize() <em>Poolsize</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPoolsize()
	 * @generated
	 * @ordered
	 */
	protected int poolsize = POOLSIZE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BPELProvidesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BpelPackage.Literals.BPEL_PROVIDES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBpel() {
		return bpel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBpel(String newBpel) {
		String oldBpel = bpel;
		bpel = newBpel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BpelPackage.BPEL_PROVIDES__BPEL, oldBpel, bpel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPoolsize() {
		return poolsize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPoolsize(int newPoolsize) {
		int oldPoolsize = poolsize;
		poolsize = newPoolsize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BpelPackage.BPEL_PROVIDES__POOLSIZE, oldPoolsize, poolsize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BpelPackage.BPEL_PROVIDES__BPEL:
				return getBpel();
			case BpelPackage.BPEL_PROVIDES__POOLSIZE:
				return getPoolsize();
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
			case BpelPackage.BPEL_PROVIDES__BPEL:
				setBpel((String)newValue);
				return;
			case BpelPackage.BPEL_PROVIDES__POOLSIZE:
				setPoolsize((Integer)newValue);
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
			case BpelPackage.BPEL_PROVIDES__BPEL:
				setBpel(BPEL_EDEFAULT);
				return;
			case BpelPackage.BPEL_PROVIDES__POOLSIZE:
				setPoolsize(POOLSIZE_EDEFAULT);
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
			case BpelPackage.BPEL_PROVIDES__BPEL:
				return BPEL_EDEFAULT == null ? bpel != null : !BPEL_EDEFAULT.equals(bpel);
			case BpelPackage.BPEL_PROVIDES__POOLSIZE:
				return poolsize != POOLSIZE_EDEFAULT;
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
		result.append(" (bpel: ");
		result.append(bpel);
		result.append(", poolsize: ");
		result.append(poolsize);
		result.append(')');
		return result.toString();
	}

} //BPELProvidesImpl
