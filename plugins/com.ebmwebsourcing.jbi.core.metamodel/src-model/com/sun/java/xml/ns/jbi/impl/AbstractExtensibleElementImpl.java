/**
 *  Copyright (c) 2009-2011, EBM WebSourcing
 *  
 *  This source code is available under agreement available at
 *  http://www.petalslink.com/legal/licenses/petals-studio
 *  
 *  You should have received a copy of the agreement along with this program.
 *  If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.sun.java.xml.ns.jbi.impl;

import com.sun.java.xml.ns.jbi.AbstractExtensibleElement;
import com.sun.java.xml.ns.jbi.JbiPackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Extensible Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.AbstractExtensibleElementImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.AbstractExtensibleElementImpl#getOther <em>Other</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.AbstractExtensibleElementImpl#getLocal <em>Local</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractExtensibleElementImpl extends EObjectImpl implements AbstractExtensibleElement {
	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractExtensibleElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JbiPackage.Literals.ABSTRACT_EXTENSIBLE_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getOther() {
		return (FeatureMap)getGroup().<FeatureMap.Entry>list(JbiPackage.Literals.ABSTRACT_EXTENSIBLE_ELEMENT__OTHER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getLocal() {
		return (FeatureMap)getGroup().<FeatureMap.Entry>list(JbiPackage.Literals.ABSTRACT_EXTENSIBLE_ELEMENT__LOCAL);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT__OTHER:
				return ((InternalEList<?>)getOther()).basicRemove(otherEnd, msgs);
			case JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT__LOCAL:
				return ((InternalEList<?>)getLocal()).basicRemove(otherEnd, msgs);
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
			case JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT__OTHER:
				if (coreType) return getOther();
				return ((FeatureMap.Internal)getOther()).getWrapper();
			case JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT__LOCAL:
				if (coreType) return getLocal();
				return ((FeatureMap.Internal)getLocal()).getWrapper();
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
			case JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT__OTHER:
				((FeatureMap.Internal)getOther()).set(newValue);
				return;
			case JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT__LOCAL:
				((FeatureMap.Internal)getLocal()).set(newValue);
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
			case JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT__GROUP:
				getGroup().clear();
				return;
			case JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT__OTHER:
				getOther().clear();
				return;
			case JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT__LOCAL:
				getLocal().clear();
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
			case JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT__GROUP:
				return group != null && !group.isEmpty();
			case JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT__OTHER:
				return !getOther().isEmpty();
			case JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT__LOCAL:
				return !getLocal().isEmpty();
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
		result.append(" (group: ");
		result.append(group);
		result.append(')');
		return result.toString();
	}

} //AbstractExtensibleElementImpl
