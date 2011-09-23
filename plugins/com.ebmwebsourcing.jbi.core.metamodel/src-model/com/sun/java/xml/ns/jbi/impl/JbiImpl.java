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

import java.math.BigDecimal;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.sun.java.xml.ns.jbi.Component;
import com.sun.java.xml.ns.jbi.Jbi;
import com.sun.java.xml.ns.jbi.JbiPackage;
import com.sun.java.xml.ns.jbi.ServiceAssembly;
import com.sun.java.xml.ns.jbi.Services;
import com.sun.java.xml.ns.jbi.SharedLibraryType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Jbi</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.JbiImpl#getComponent <em>Component</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.JbiImpl#getSharedLibrary <em>Shared Library</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.JbiImpl#getServiceAssembly <em>Service Assembly</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.JbiImpl#getServices <em>Services</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.JbiImpl#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JbiImpl extends EObjectImpl implements Jbi {
	/**
	 * The cached value of the '{@link #getComponent() <em>Component</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponent()
	 * @generated
	 * @ordered
	 */
	protected Component component;

	/**
	 * The cached value of the '{@link #getSharedLibrary() <em>Shared Library</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSharedLibrary()
	 * @generated
	 * @ordered
	 */
	protected SharedLibraryType sharedLibrary;

	/**
	 * The cached value of the '{@link #getServiceAssembly() <em>Service Assembly</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceAssembly()
	 * @generated
	 * @ordered
	 */
	protected ServiceAssembly serviceAssembly;

	/**
	 * The cached value of the '{@link #getServices() <em>Services</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServices()
	 * @generated
	 * @ordered
	 */
	protected Services services;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final BigDecimal VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected BigDecimal version = VERSION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JbiImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JbiPackage.Literals.JBI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Component getComponent() {
		return component;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComponent(Component newComponent, NotificationChain msgs) {
		Component oldComponent = component;
		component = newComponent;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JbiPackage.JBI__COMPONENT, oldComponent, newComponent);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponent(Component newComponent) {
		if (newComponent != component) {
			NotificationChain msgs = null;
			if (component != null)
				msgs = ((InternalEObject)component).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JbiPackage.JBI__COMPONENT, null, msgs);
			if (newComponent != null)
				msgs = ((InternalEObject)newComponent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JbiPackage.JBI__COMPONENT, null, msgs);
			msgs = basicSetComponent(newComponent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JbiPackage.JBI__COMPONENT, newComponent, newComponent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SharedLibraryType getSharedLibrary() {
		return sharedLibrary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSharedLibrary(SharedLibraryType newSharedLibrary, NotificationChain msgs) {
		SharedLibraryType oldSharedLibrary = sharedLibrary;
		sharedLibrary = newSharedLibrary;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JbiPackage.JBI__SHARED_LIBRARY, oldSharedLibrary, newSharedLibrary);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSharedLibrary(SharedLibraryType newSharedLibrary) {
		if (newSharedLibrary != sharedLibrary) {
			NotificationChain msgs = null;
			if (sharedLibrary != null)
				msgs = ((InternalEObject)sharedLibrary).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JbiPackage.JBI__SHARED_LIBRARY, null, msgs);
			if (newSharedLibrary != null)
				msgs = ((InternalEObject)newSharedLibrary).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JbiPackage.JBI__SHARED_LIBRARY, null, msgs);
			msgs = basicSetSharedLibrary(newSharedLibrary, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JbiPackage.JBI__SHARED_LIBRARY, newSharedLibrary, newSharedLibrary));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceAssembly getServiceAssembly() {
		return serviceAssembly;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetServiceAssembly(ServiceAssembly newServiceAssembly, NotificationChain msgs) {
		ServiceAssembly oldServiceAssembly = serviceAssembly;
		serviceAssembly = newServiceAssembly;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JbiPackage.JBI__SERVICE_ASSEMBLY, oldServiceAssembly, newServiceAssembly);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServiceAssembly(ServiceAssembly newServiceAssembly) {
		if (newServiceAssembly != serviceAssembly) {
			NotificationChain msgs = null;
			if (serviceAssembly != null)
				msgs = ((InternalEObject)serviceAssembly).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JbiPackage.JBI__SERVICE_ASSEMBLY, null, msgs);
			if (newServiceAssembly != null)
				msgs = ((InternalEObject)newServiceAssembly).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JbiPackage.JBI__SERVICE_ASSEMBLY, null, msgs);
			msgs = basicSetServiceAssembly(newServiceAssembly, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JbiPackage.JBI__SERVICE_ASSEMBLY, newServiceAssembly, newServiceAssembly));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Services getServices() {
		return services;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetServices(Services newServices, NotificationChain msgs) {
		Services oldServices = services;
		services = newServices;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JbiPackage.JBI__SERVICES, oldServices, newServices);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServices(Services newServices) {
		if (newServices != services) {
			NotificationChain msgs = null;
			if (services != null)
				msgs = ((InternalEObject)services).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JbiPackage.JBI__SERVICES, null, msgs);
			if (newServices != null)
				msgs = ((InternalEObject)newServices).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JbiPackage.JBI__SERVICES, null, msgs);
			msgs = basicSetServices(newServices, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JbiPackage.JBI__SERVICES, newServices, newServices));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(BigDecimal newVersion) {
		BigDecimal oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JbiPackage.JBI__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JbiPackage.JBI__COMPONENT:
				return basicSetComponent(null, msgs);
			case JbiPackage.JBI__SHARED_LIBRARY:
				return basicSetSharedLibrary(null, msgs);
			case JbiPackage.JBI__SERVICE_ASSEMBLY:
				return basicSetServiceAssembly(null, msgs);
			case JbiPackage.JBI__SERVICES:
				return basicSetServices(null, msgs);
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
			case JbiPackage.JBI__COMPONENT:
				return getComponent();
			case JbiPackage.JBI__SHARED_LIBRARY:
				return getSharedLibrary();
			case JbiPackage.JBI__SERVICE_ASSEMBLY:
				return getServiceAssembly();
			case JbiPackage.JBI__SERVICES:
				return getServices();
			case JbiPackage.JBI__VERSION:
				return getVersion();
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
			case JbiPackage.JBI__COMPONENT:
				setComponent((Component)newValue);
				return;
			case JbiPackage.JBI__SHARED_LIBRARY:
				setSharedLibrary((SharedLibraryType)newValue);
				return;
			case JbiPackage.JBI__SERVICE_ASSEMBLY:
				setServiceAssembly((ServiceAssembly)newValue);
				return;
			case JbiPackage.JBI__SERVICES:
				setServices((Services)newValue);
				return;
			case JbiPackage.JBI__VERSION:
				setVersion((BigDecimal)newValue);
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
			case JbiPackage.JBI__COMPONENT:
				setComponent((Component)null);
				return;
			case JbiPackage.JBI__SHARED_LIBRARY:
				setSharedLibrary((SharedLibraryType)null);
				return;
			case JbiPackage.JBI__SERVICE_ASSEMBLY:
				setServiceAssembly((ServiceAssembly)null);
				return;
			case JbiPackage.JBI__SERVICES:
				setServices((Services)null);
				return;
			case JbiPackage.JBI__VERSION:
				setVersion(VERSION_EDEFAULT);
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
			case JbiPackage.JBI__COMPONENT:
				return component != null;
			case JbiPackage.JBI__SHARED_LIBRARY:
				return sharedLibrary != null;
			case JbiPackage.JBI__SERVICE_ASSEMBLY:
				return serviceAssembly != null;
			case JbiPackage.JBI__SERVICES:
				return services != null;
			case JbiPackage.JBI__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
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
		result.append(" (version: ");
		result.append(version);
		result.append(')');
		return result.toString();
	}

} //JbiImpl
