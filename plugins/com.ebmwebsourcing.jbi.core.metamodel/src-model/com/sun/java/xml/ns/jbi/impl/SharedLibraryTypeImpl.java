/**
 *  Copyright (c) 2009-2012, EBM WebSourcing
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

import com.sun.java.xml.ns.jbi.ClassLoaderDelegationType;
import com.sun.java.xml.ns.jbi.ClassPath;
import com.sun.java.xml.ns.jbi.Identification;
import com.sun.java.xml.ns.jbi.JbiPackage;
import com.sun.java.xml.ns.jbi.SharedLibraryType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Shared Library Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.SharedLibraryTypeImpl#getIdentification <em>Identification</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.SharedLibraryTypeImpl#getSharedLibraryClassPath <em>Shared Library Class Path</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.SharedLibraryTypeImpl#getClassLoaderDelegation <em>Class Loader Delegation</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.SharedLibraryTypeImpl#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SharedLibraryTypeImpl extends EObjectImpl implements SharedLibraryType {
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
	 * The cached value of the '{@link #getSharedLibraryClassPath() <em>Shared Library Class Path</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSharedLibraryClassPath()
	 * @generated
	 * @ordered
	 */
	protected ClassPath sharedLibraryClassPath;

	/**
	 * The default value of the '{@link #getClassLoaderDelegation() <em>Class Loader Delegation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassLoaderDelegation()
	 * @generated
	 * @ordered
	 */
	protected static final ClassLoaderDelegationType CLASS_LOADER_DELEGATION_EDEFAULT = ClassLoaderDelegationType.PARENT_FIRST;

	/**
	 * The cached value of the '{@link #getClassLoaderDelegation() <em>Class Loader Delegation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassLoaderDelegation()
	 * @generated
	 * @ordered
	 */
	protected ClassLoaderDelegationType classLoaderDelegation = CLASS_LOADER_DELEGATION_EDEFAULT;

	/**
	 * This is true if the Class Loader Delegation attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean classLoaderDelegationESet;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final Object VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected Object version = VERSION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SharedLibraryTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JbiPackage.Literals.SHARED_LIBRARY_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Identification getIdentification() {
		return identification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIdentification(Identification newIdentification, NotificationChain msgs) {
		Identification oldIdentification = identification;
		identification = newIdentification;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JbiPackage.SHARED_LIBRARY_TYPE__IDENTIFICATION, oldIdentification, newIdentification);
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
		if (newIdentification != identification) {
			NotificationChain msgs = null;
			if (identification != null)
				msgs = ((InternalEObject)identification).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JbiPackage.SHARED_LIBRARY_TYPE__IDENTIFICATION, null, msgs);
			if (newIdentification != null)
				msgs = ((InternalEObject)newIdentification).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JbiPackage.SHARED_LIBRARY_TYPE__IDENTIFICATION, null, msgs);
			msgs = basicSetIdentification(newIdentification, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JbiPackage.SHARED_LIBRARY_TYPE__IDENTIFICATION, newIdentification, newIdentification));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassPath getSharedLibraryClassPath() {
		return sharedLibraryClassPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSharedLibraryClassPath(ClassPath newSharedLibraryClassPath, NotificationChain msgs) {
		ClassPath oldSharedLibraryClassPath = sharedLibraryClassPath;
		sharedLibraryClassPath = newSharedLibraryClassPath;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JbiPackage.SHARED_LIBRARY_TYPE__SHARED_LIBRARY_CLASS_PATH, oldSharedLibraryClassPath, newSharedLibraryClassPath);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSharedLibraryClassPath(ClassPath newSharedLibraryClassPath) {
		if (newSharedLibraryClassPath != sharedLibraryClassPath) {
			NotificationChain msgs = null;
			if (sharedLibraryClassPath != null)
				msgs = ((InternalEObject)sharedLibraryClassPath).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JbiPackage.SHARED_LIBRARY_TYPE__SHARED_LIBRARY_CLASS_PATH, null, msgs);
			if (newSharedLibraryClassPath != null)
				msgs = ((InternalEObject)newSharedLibraryClassPath).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JbiPackage.SHARED_LIBRARY_TYPE__SHARED_LIBRARY_CLASS_PATH, null, msgs);
			msgs = basicSetSharedLibraryClassPath(newSharedLibraryClassPath, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JbiPackage.SHARED_LIBRARY_TYPE__SHARED_LIBRARY_CLASS_PATH, newSharedLibraryClassPath, newSharedLibraryClassPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassLoaderDelegationType getClassLoaderDelegation() {
		return classLoaderDelegation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassLoaderDelegation(ClassLoaderDelegationType newClassLoaderDelegation) {
		ClassLoaderDelegationType oldClassLoaderDelegation = classLoaderDelegation;
		classLoaderDelegation = newClassLoaderDelegation == null ? CLASS_LOADER_DELEGATION_EDEFAULT : newClassLoaderDelegation;
		boolean oldClassLoaderDelegationESet = classLoaderDelegationESet;
		classLoaderDelegationESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JbiPackage.SHARED_LIBRARY_TYPE__CLASS_LOADER_DELEGATION, oldClassLoaderDelegation, classLoaderDelegation, !oldClassLoaderDelegationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetClassLoaderDelegation() {
		ClassLoaderDelegationType oldClassLoaderDelegation = classLoaderDelegation;
		boolean oldClassLoaderDelegationESet = classLoaderDelegationESet;
		classLoaderDelegation = CLASS_LOADER_DELEGATION_EDEFAULT;
		classLoaderDelegationESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JbiPackage.SHARED_LIBRARY_TYPE__CLASS_LOADER_DELEGATION, oldClassLoaderDelegation, CLASS_LOADER_DELEGATION_EDEFAULT, oldClassLoaderDelegationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetClassLoaderDelegation() {
		return classLoaderDelegationESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(Object newVersion) {
		Object oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JbiPackage.SHARED_LIBRARY_TYPE__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JbiPackage.SHARED_LIBRARY_TYPE__IDENTIFICATION:
				return basicSetIdentification(null, msgs);
			case JbiPackage.SHARED_LIBRARY_TYPE__SHARED_LIBRARY_CLASS_PATH:
				return basicSetSharedLibraryClassPath(null, msgs);
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
			case JbiPackage.SHARED_LIBRARY_TYPE__IDENTIFICATION:
				return getIdentification();
			case JbiPackage.SHARED_LIBRARY_TYPE__SHARED_LIBRARY_CLASS_PATH:
				return getSharedLibraryClassPath();
			case JbiPackage.SHARED_LIBRARY_TYPE__CLASS_LOADER_DELEGATION:
				return getClassLoaderDelegation();
			case JbiPackage.SHARED_LIBRARY_TYPE__VERSION:
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
			case JbiPackage.SHARED_LIBRARY_TYPE__IDENTIFICATION:
				setIdentification((Identification)newValue);
				return;
			case JbiPackage.SHARED_LIBRARY_TYPE__SHARED_LIBRARY_CLASS_PATH:
				setSharedLibraryClassPath((ClassPath)newValue);
				return;
			case JbiPackage.SHARED_LIBRARY_TYPE__CLASS_LOADER_DELEGATION:
				setClassLoaderDelegation((ClassLoaderDelegationType)newValue);
				return;
			case JbiPackage.SHARED_LIBRARY_TYPE__VERSION:
				setVersion(newValue);
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
			case JbiPackage.SHARED_LIBRARY_TYPE__IDENTIFICATION:
				setIdentification((Identification)null);
				return;
			case JbiPackage.SHARED_LIBRARY_TYPE__SHARED_LIBRARY_CLASS_PATH:
				setSharedLibraryClassPath((ClassPath)null);
				return;
			case JbiPackage.SHARED_LIBRARY_TYPE__CLASS_LOADER_DELEGATION:
				unsetClassLoaderDelegation();
				return;
			case JbiPackage.SHARED_LIBRARY_TYPE__VERSION:
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
			case JbiPackage.SHARED_LIBRARY_TYPE__IDENTIFICATION:
				return identification != null;
			case JbiPackage.SHARED_LIBRARY_TYPE__SHARED_LIBRARY_CLASS_PATH:
				return sharedLibraryClassPath != null;
			case JbiPackage.SHARED_LIBRARY_TYPE__CLASS_LOADER_DELEGATION:
				return isSetClassLoaderDelegation();
			case JbiPackage.SHARED_LIBRARY_TYPE__VERSION:
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
		result.append(" (classLoaderDelegation: ");
		if (classLoaderDelegationESet) result.append(classLoaderDelegation); else result.append("<unset>");
		result.append(", version: ");
		result.append(version);
		result.append(')');
		return result.toString();
	}

} //SharedLibraryTypeImpl
