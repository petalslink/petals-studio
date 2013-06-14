/**
 *  Copyright (c) 2009-2013, Linagora
 *  
 *  This source code is available under agreement available at
 *  http://www.petalslink.com/legal/licenses/petals-studio
 *  
 *  You should have received a copy of the agreement along with this program.
 *  If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 */
package com.sun.java.xml.ns.jbi.impl;

import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.JbiPackage;
import com.sun.java.xml.ns.jbi.Provides;
import com.sun.java.xml.ns.jbi.Services;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Services</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.ServicesImpl#getProvides <em>Provides</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.ServicesImpl#getConsumes <em>Consumes</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.ServicesImpl#isBindingComponent <em>Binding Component</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServicesImpl extends AbstractExtensibleElementImpl implements Services {
	/**
	 * The cached value of the '{@link #getProvides() <em>Provides</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProvides()
	 * @generated
	 * @ordered
	 */
	protected EList<Provides> provides;

	/**
	 * The cached value of the '{@link #getConsumes() <em>Consumes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConsumes()
	 * @generated
	 * @ordered
	 */
	protected EList<Consumes> consumes;

	/**
	 * The default value of the '{@link #isBindingComponent() <em>Binding Component</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBindingComponent()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BINDING_COMPONENT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isBindingComponent() <em>Binding Component</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBindingComponent()
	 * @generated
	 * @ordered
	 */
	protected boolean bindingComponent = BINDING_COMPONENT_EDEFAULT;

	/**
	 * This is true if the Binding Component attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean bindingComponentESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ServicesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JbiPackage.Literals.SERVICES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Provides> getProvides() {
		if (provides == null) {
			provides = new EObjectContainmentEList<Provides>(Provides.class, this, JbiPackage.SERVICES__PROVIDES);
		}
		return provides;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Consumes> getConsumes() {
		if (consumes == null) {
			consumes = new EObjectContainmentEList<Consumes>(Consumes.class, this, JbiPackage.SERVICES__CONSUMES);
		}
		return consumes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isBindingComponent() {
		return bindingComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBindingComponent(boolean newBindingComponent) {
		boolean oldBindingComponent = bindingComponent;
		bindingComponent = newBindingComponent;
		boolean oldBindingComponentESet = bindingComponentESet;
		bindingComponentESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JbiPackage.SERVICES__BINDING_COMPONENT, oldBindingComponent, bindingComponent, !oldBindingComponentESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetBindingComponent() {
		boolean oldBindingComponent = bindingComponent;
		boolean oldBindingComponentESet = bindingComponentESet;
		bindingComponent = BINDING_COMPONENT_EDEFAULT;
		bindingComponentESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JbiPackage.SERVICES__BINDING_COMPONENT, oldBindingComponent, BINDING_COMPONENT_EDEFAULT, oldBindingComponentESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetBindingComponent() {
		return bindingComponentESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JbiPackage.SERVICES__PROVIDES:
				return ((InternalEList<?>)getProvides()).basicRemove(otherEnd, msgs);
			case JbiPackage.SERVICES__CONSUMES:
				return ((InternalEList<?>)getConsumes()).basicRemove(otherEnd, msgs);
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
			case JbiPackage.SERVICES__PROVIDES:
				return getProvides();
			case JbiPackage.SERVICES__CONSUMES:
				return getConsumes();
			case JbiPackage.SERVICES__BINDING_COMPONENT:
				return isBindingComponent();
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
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case JbiPackage.SERVICES__PROVIDES:
				getProvides().clear();
				getProvides().addAll((Collection<? extends Provides>)newValue);
				return;
			case JbiPackage.SERVICES__CONSUMES:
				getConsumes().clear();
				getConsumes().addAll((Collection<? extends Consumes>)newValue);
				return;
			case JbiPackage.SERVICES__BINDING_COMPONENT:
				setBindingComponent((Boolean)newValue);
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
			case JbiPackage.SERVICES__PROVIDES:
				getProvides().clear();
				return;
			case JbiPackage.SERVICES__CONSUMES:
				getConsumes().clear();
				return;
			case JbiPackage.SERVICES__BINDING_COMPONENT:
				unsetBindingComponent();
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
			case JbiPackage.SERVICES__PROVIDES:
				return provides != null && !provides.isEmpty();
			case JbiPackage.SERVICES__CONSUMES:
				return consumes != null && !consumes.isEmpty();
			case JbiPackage.SERVICES__BINDING_COMPONENT:
				return isSetBindingComponent();
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
		result.append(" (bindingComponent: ");
		if (bindingComponentESet) result.append(bindingComponent); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //ServicesImpl
