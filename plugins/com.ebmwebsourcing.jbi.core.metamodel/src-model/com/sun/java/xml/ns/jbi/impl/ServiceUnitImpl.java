/**
 *  Copyright (c) 2009-2011, EBM WebSourcing
 * 
 *  This source code is available under agreement available at
 *  http://www.petalslink.com/legal/licenses/petals-studio
 * 
 *  You should have received a copy of the agreement along with this program.
 *  If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 * $Id$
 */
package com.sun.java.xml.ns.jbi.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import com.sun.java.xml.ns.jbi.Identification;
import com.sun.java.xml.ns.jbi.JbiPackage;
import com.sun.java.xml.ns.jbi.ServiceUnit;
import com.sun.java.xml.ns.jbi.Target;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service Unit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.ServiceUnitImpl#getIdentification <em>Identification</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.ServiceUnitImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.ServiceUnitImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.ServiceUnitImpl#getAny <em>Any</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.ServiceUnitImpl#getAny1 <em>Any1</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServiceUnitImpl extends EObjectImpl implements ServiceUnit {
	/**
	 * The cached value of the '{@link #getIdentification() <em>Identification</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdentification()
	 * @generated
	 * @ordered
	 */
	protected Identification identification;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected Target target;

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
	protected ServiceUnitImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JbiPackage.Literals.SERVICE_UNIT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Identification getIdentification() {
		return this.identification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newIdentification
	 * @param msgs
	 * @return
	 * @generated
	 */
	public NotificationChain basicSetIdentification(Identification newIdentification, NotificationChain msgs) {
		Identification oldIdentification = this.identification;
		this.identification = newIdentification;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JbiPackage.SERVICE_UNIT__IDENTIFICATION, oldIdentification, newIdentification);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdentification(Identification newIdentification) {
		if (newIdentification != this.identification) {
			NotificationChain msgs = null;
			if (this.identification != null)
				msgs = ((InternalEObject)this.identification).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JbiPackage.SERVICE_UNIT__IDENTIFICATION, null, msgs);
			if (newIdentification != null)
				msgs = ((InternalEObject)newIdentification).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JbiPackage.SERVICE_UNIT__IDENTIFICATION, null, msgs);
			msgs = basicSetIdentification(newIdentification, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JbiPackage.SERVICE_UNIT__IDENTIFICATION, newIdentification, newIdentification));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Target getTarget() {
		return this.target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newTarget
	 * @param msgs
	 * @return
	 * @generated
	 */
	public NotificationChain basicSetTarget(Target newTarget, NotificationChain msgs) {
		Target oldTarget = this.target;
		this.target = newTarget;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JbiPackage.SERVICE_UNIT__TARGET, oldTarget, newTarget);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(Target newTarget) {
		if (newTarget != this.target) {
			NotificationChain msgs = null;
			if (this.target != null)
				msgs = ((InternalEObject)this.target).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JbiPackage.SERVICE_UNIT__TARGET, null, msgs);
			if (newTarget != null)
				msgs = ((InternalEObject)newTarget).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JbiPackage.SERVICE_UNIT__TARGET, null, msgs);
			msgs = basicSetTarget(newTarget, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JbiPackage.SERVICE_UNIT__TARGET, newTarget, newTarget));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (this.group == null) {
			this.group = new BasicFeatureMap(this, JbiPackage.SERVICE_UNIT__GROUP);
		}
		return this.group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getAny() {
		return (FeatureMap)getGroup().<FeatureMap.Entry>list(JbiPackage.Literals.SERVICE_UNIT__ANY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getAny1() {
		return (FeatureMap)getGroup().<FeatureMap.Entry>list(JbiPackage.Literals.SERVICE_UNIT__ANY1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case JbiPackage.SERVICE_UNIT__IDENTIFICATION:
			return basicSetIdentification(null, msgs);
		case JbiPackage.SERVICE_UNIT__TARGET:
			return basicSetTarget(null, msgs);
		case JbiPackage.SERVICE_UNIT__GROUP:
			return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
		case JbiPackage.SERVICE_UNIT__ANY:
			return ((InternalEList<?>)getAny()).basicRemove(otherEnd, msgs);
		case JbiPackage.SERVICE_UNIT__ANY1:
			return ((InternalEList<?>)getAny1()).basicRemove(otherEnd, msgs);
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
		case JbiPackage.SERVICE_UNIT__IDENTIFICATION:
			return getIdentification();
		case JbiPackage.SERVICE_UNIT__TARGET:
			return getTarget();
		case JbiPackage.SERVICE_UNIT__GROUP:
			if (coreType) return getGroup();
			return ((FeatureMap.Internal)getGroup()).getWrapper();
		case JbiPackage.SERVICE_UNIT__ANY:
			if (coreType) return getAny();
			return ((FeatureMap.Internal)getAny()).getWrapper();
		case JbiPackage.SERVICE_UNIT__ANY1:
			if (coreType) return getAny1();
			return ((FeatureMap.Internal)getAny1()).getWrapper();
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
		case JbiPackage.SERVICE_UNIT__IDENTIFICATION:
			setIdentification((Identification)newValue);
			return;
		case JbiPackage.SERVICE_UNIT__TARGET:
			setTarget((Target)newValue);
			return;
		case JbiPackage.SERVICE_UNIT__GROUP:
			((FeatureMap.Internal)getGroup()).set(newValue);
			return;
		case JbiPackage.SERVICE_UNIT__ANY:
			((FeatureMap.Internal)getAny()).set(newValue);
			return;
		case JbiPackage.SERVICE_UNIT__ANY1:
			((FeatureMap.Internal)getAny1()).set(newValue);
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
		case JbiPackage.SERVICE_UNIT__IDENTIFICATION:
			setIdentification((Identification)null);
			return;
		case JbiPackage.SERVICE_UNIT__TARGET:
			setTarget((Target)null);
			return;
		case JbiPackage.SERVICE_UNIT__GROUP:
			getGroup().clear();
			return;
		case JbiPackage.SERVICE_UNIT__ANY:
			getAny().clear();
			return;
		case JbiPackage.SERVICE_UNIT__ANY1:
			getAny1().clear();
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
		case JbiPackage.SERVICE_UNIT__IDENTIFICATION:
			return this.identification != null;
		case JbiPackage.SERVICE_UNIT__TARGET:
			return this.target != null;
		case JbiPackage.SERVICE_UNIT__GROUP:
			return this.group != null && !this.group.isEmpty();
		case JbiPackage.SERVICE_UNIT__ANY:
			return !getAny().isEmpty();
		case JbiPackage.SERVICE_UNIT__ANY1:
			return !getAny1().isEmpty();
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
		result.append(this.group);
		result.append(')');
		return result.toString();
	}

} //ServiceUnitImpl
