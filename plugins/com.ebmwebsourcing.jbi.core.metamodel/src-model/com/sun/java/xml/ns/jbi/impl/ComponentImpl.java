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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import com.sun.java.xml.ns.jbi.ClassLoaderDelegationType;
import com.sun.java.xml.ns.jbi.ClassPath;
import com.sun.java.xml.ns.jbi.Component;
import com.sun.java.xml.ns.jbi.ComponentClassName;
import com.sun.java.xml.ns.jbi.ComponentType;
import com.sun.java.xml.ns.jbi.Identification;
import com.sun.java.xml.ns.jbi.JbiPackage;
import com.sun.java.xml.ns.jbi.SharedLibraryType1;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.ComponentImpl#getIdentification <em>Identification</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.ComponentImpl#getComponentClassName <em>Component Class Name</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.ComponentImpl#getComponentClassPath <em>Component Class Path</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.ComponentImpl#getBootstrapClassName <em>Bootstrap Class Name</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.ComponentImpl#getBootstrapClassPath <em>Bootstrap Class Path</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.ComponentImpl#getSharedLibraryList <em>Shared Library List</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.ComponentImpl#getSharedLibrary <em>Shared Library</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.ComponentImpl#getBootstrapClassLoaderDelegation <em>Bootstrap Class Loader Delegation</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.ComponentImpl#getComponentClassLoaderDelegation <em>Component Class Loader Delegation</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.ComponentImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentImpl extends AbstractExtensibleElementImpl implements Component {
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
	 * The cached value of the '{@link #getComponentClassName() <em>Component Class Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentClassName()
	 * @generated
	 * @ordered
	 */
	protected ComponentClassName componentClassName;

	/**
	 * The cached value of the '{@link #getComponentClassPath() <em>Component Class Path</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentClassPath()
	 * @generated
	 * @ordered
	 */
	protected ClassPath componentClassPath;

	/**
	 * The default value of the '{@link #getBootstrapClassName() <em>Bootstrap Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBootstrapClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String BOOTSTRAP_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBootstrapClassName() <em>Bootstrap Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBootstrapClassName()
	 * @generated
	 * @ordered
	 */
	protected String bootstrapClassName = BOOTSTRAP_CLASS_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBootstrapClassPath() <em>Bootstrap Class Path</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBootstrapClassPath()
	 * @generated
	 * @ordered
	 */
	protected ClassPath bootstrapClassPath;

	/**
	 * The cached value of the '{@link #getSharedLibraryList() <em>Shared Library List</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSharedLibraryList()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap sharedLibraryList;

	/**
	 * The default value of the '{@link #getBootstrapClassLoaderDelegation() <em>Bootstrap Class Loader Delegation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBootstrapClassLoaderDelegation()
	 * @generated
	 * @ordered
	 */
	protected static final ClassLoaderDelegationType BOOTSTRAP_CLASS_LOADER_DELEGATION_EDEFAULT = ClassLoaderDelegationType.PARENT_FIRST;

	/**
	 * The cached value of the '{@link #getBootstrapClassLoaderDelegation() <em>Bootstrap Class Loader Delegation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBootstrapClassLoaderDelegation()
	 * @generated
	 * @ordered
	 */
	protected ClassLoaderDelegationType bootstrapClassLoaderDelegation = BOOTSTRAP_CLASS_LOADER_DELEGATION_EDEFAULT;

	/**
	 * This is true if the Bootstrap Class Loader Delegation attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean bootstrapClassLoaderDelegationESet;

	/**
	 * The default value of the '{@link #getComponentClassLoaderDelegation() <em>Component Class Loader Delegation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentClassLoaderDelegation()
	 * @generated
	 * @ordered
	 */
	protected static final ClassLoaderDelegationType COMPONENT_CLASS_LOADER_DELEGATION_EDEFAULT = ClassLoaderDelegationType.PARENT_FIRST;

	/**
	 * The cached value of the '{@link #getComponentClassLoaderDelegation() <em>Component Class Loader Delegation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentClassLoaderDelegation()
	 * @generated
	 * @ordered
	 */
	protected ClassLoaderDelegationType componentClassLoaderDelegation = COMPONENT_CLASS_LOADER_DELEGATION_EDEFAULT;

	/**
	 * This is true if the Component Class Loader Delegation attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean componentClassLoaderDelegationESet;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final ComponentType TYPE_EDEFAULT = ComponentType.SERVICE_ENGINE;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected ComponentType type = TYPE_EDEFAULT;

	/**
	 * This is true if the Type attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean typeESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JbiPackage.Literals.COMPONENT;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JbiPackage.COMPONENT__IDENTIFICATION, oldIdentification, newIdentification);
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
				msgs = ((InternalEObject)identification).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JbiPackage.COMPONENT__IDENTIFICATION, null, msgs);
			if (newIdentification != null)
				msgs = ((InternalEObject)newIdentification).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JbiPackage.COMPONENT__IDENTIFICATION, null, msgs);
			msgs = basicSetIdentification(newIdentification, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JbiPackage.COMPONENT__IDENTIFICATION, newIdentification, newIdentification));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentClassName getComponentClassName() {
		return componentClassName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComponentClassName(ComponentClassName newComponentClassName, NotificationChain msgs) {
		ComponentClassName oldComponentClassName = componentClassName;
		componentClassName = newComponentClassName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JbiPackage.COMPONENT__COMPONENT_CLASS_NAME, oldComponentClassName, newComponentClassName);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentClassName(ComponentClassName newComponentClassName) {
		if (newComponentClassName != componentClassName) {
			NotificationChain msgs = null;
			if (componentClassName != null)
				msgs = ((InternalEObject)componentClassName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JbiPackage.COMPONENT__COMPONENT_CLASS_NAME, null, msgs);
			if (newComponentClassName != null)
				msgs = ((InternalEObject)newComponentClassName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JbiPackage.COMPONENT__COMPONENT_CLASS_NAME, null, msgs);
			msgs = basicSetComponentClassName(newComponentClassName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JbiPackage.COMPONENT__COMPONENT_CLASS_NAME, newComponentClassName, newComponentClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassPath getComponentClassPath() {
		return componentClassPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComponentClassPath(ClassPath newComponentClassPath, NotificationChain msgs) {
		ClassPath oldComponentClassPath = componentClassPath;
		componentClassPath = newComponentClassPath;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JbiPackage.COMPONENT__COMPONENT_CLASS_PATH, oldComponentClassPath, newComponentClassPath);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentClassPath(ClassPath newComponentClassPath) {
		if (newComponentClassPath != componentClassPath) {
			NotificationChain msgs = null;
			if (componentClassPath != null)
				msgs = ((InternalEObject)componentClassPath).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JbiPackage.COMPONENT__COMPONENT_CLASS_PATH, null, msgs);
			if (newComponentClassPath != null)
				msgs = ((InternalEObject)newComponentClassPath).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JbiPackage.COMPONENT__COMPONENT_CLASS_PATH, null, msgs);
			msgs = basicSetComponentClassPath(newComponentClassPath, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JbiPackage.COMPONENT__COMPONENT_CLASS_PATH, newComponentClassPath, newComponentClassPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBootstrapClassName() {
		return bootstrapClassName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBootstrapClassName(String newBootstrapClassName) {
		String oldBootstrapClassName = bootstrapClassName;
		bootstrapClassName = newBootstrapClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JbiPackage.COMPONENT__BOOTSTRAP_CLASS_NAME, oldBootstrapClassName, bootstrapClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassPath getBootstrapClassPath() {
		return bootstrapClassPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBootstrapClassPath(ClassPath newBootstrapClassPath, NotificationChain msgs) {
		ClassPath oldBootstrapClassPath = bootstrapClassPath;
		bootstrapClassPath = newBootstrapClassPath;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JbiPackage.COMPONENT__BOOTSTRAP_CLASS_PATH, oldBootstrapClassPath, newBootstrapClassPath);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBootstrapClassPath(ClassPath newBootstrapClassPath) {
		if (newBootstrapClassPath != bootstrapClassPath) {
			NotificationChain msgs = null;
			if (bootstrapClassPath != null)
				msgs = ((InternalEObject)bootstrapClassPath).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JbiPackage.COMPONENT__BOOTSTRAP_CLASS_PATH, null, msgs);
			if (newBootstrapClassPath != null)
				msgs = ((InternalEObject)newBootstrapClassPath).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JbiPackage.COMPONENT__BOOTSTRAP_CLASS_PATH, null, msgs);
			msgs = basicSetBootstrapClassPath(newBootstrapClassPath, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JbiPackage.COMPONENT__BOOTSTRAP_CLASS_PATH, newBootstrapClassPath, newBootstrapClassPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getSharedLibraryList() {
		if (sharedLibraryList == null) {
			sharedLibraryList = new BasicFeatureMap(this, JbiPackage.COMPONENT__SHARED_LIBRARY_LIST);
		}
		return sharedLibraryList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SharedLibraryType1> getSharedLibrary() {
		return getSharedLibraryList().list(JbiPackage.Literals.COMPONENT__SHARED_LIBRARY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassLoaderDelegationType getBootstrapClassLoaderDelegation() {
		return bootstrapClassLoaderDelegation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBootstrapClassLoaderDelegation(ClassLoaderDelegationType newBootstrapClassLoaderDelegation) {
		ClassLoaderDelegationType oldBootstrapClassLoaderDelegation = bootstrapClassLoaderDelegation;
		bootstrapClassLoaderDelegation = newBootstrapClassLoaderDelegation == null ? BOOTSTRAP_CLASS_LOADER_DELEGATION_EDEFAULT : newBootstrapClassLoaderDelegation;
		boolean oldBootstrapClassLoaderDelegationESet = bootstrapClassLoaderDelegationESet;
		bootstrapClassLoaderDelegationESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JbiPackage.COMPONENT__BOOTSTRAP_CLASS_LOADER_DELEGATION, oldBootstrapClassLoaderDelegation, bootstrapClassLoaderDelegation, !oldBootstrapClassLoaderDelegationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetBootstrapClassLoaderDelegation() {
		ClassLoaderDelegationType oldBootstrapClassLoaderDelegation = bootstrapClassLoaderDelegation;
		boolean oldBootstrapClassLoaderDelegationESet = bootstrapClassLoaderDelegationESet;
		bootstrapClassLoaderDelegation = BOOTSTRAP_CLASS_LOADER_DELEGATION_EDEFAULT;
		bootstrapClassLoaderDelegationESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JbiPackage.COMPONENT__BOOTSTRAP_CLASS_LOADER_DELEGATION, oldBootstrapClassLoaderDelegation, BOOTSTRAP_CLASS_LOADER_DELEGATION_EDEFAULT, oldBootstrapClassLoaderDelegationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetBootstrapClassLoaderDelegation() {
		return bootstrapClassLoaderDelegationESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassLoaderDelegationType getComponentClassLoaderDelegation() {
		return componentClassLoaderDelegation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentClassLoaderDelegation(ClassLoaderDelegationType newComponentClassLoaderDelegation) {
		ClassLoaderDelegationType oldComponentClassLoaderDelegation = componentClassLoaderDelegation;
		componentClassLoaderDelegation = newComponentClassLoaderDelegation == null ? COMPONENT_CLASS_LOADER_DELEGATION_EDEFAULT : newComponentClassLoaderDelegation;
		boolean oldComponentClassLoaderDelegationESet = componentClassLoaderDelegationESet;
		componentClassLoaderDelegationESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JbiPackage.COMPONENT__COMPONENT_CLASS_LOADER_DELEGATION, oldComponentClassLoaderDelegation, componentClassLoaderDelegation, !oldComponentClassLoaderDelegationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetComponentClassLoaderDelegation() {
		ClassLoaderDelegationType oldComponentClassLoaderDelegation = componentClassLoaderDelegation;
		boolean oldComponentClassLoaderDelegationESet = componentClassLoaderDelegationESet;
		componentClassLoaderDelegation = COMPONENT_CLASS_LOADER_DELEGATION_EDEFAULT;
		componentClassLoaderDelegationESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JbiPackage.COMPONENT__COMPONENT_CLASS_LOADER_DELEGATION, oldComponentClassLoaderDelegation, COMPONENT_CLASS_LOADER_DELEGATION_EDEFAULT, oldComponentClassLoaderDelegationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetComponentClassLoaderDelegation() {
		return componentClassLoaderDelegationESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(ComponentType newType) {
		ComponentType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		boolean oldTypeESet = typeESet;
		typeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JbiPackage.COMPONENT__TYPE, oldType, type, !oldTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetType() {
		ComponentType oldType = type;
		boolean oldTypeESet = typeESet;
		type = TYPE_EDEFAULT;
		typeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JbiPackage.COMPONENT__TYPE, oldType, TYPE_EDEFAULT, oldTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetType() {
		return typeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JbiPackage.COMPONENT__IDENTIFICATION:
				return basicSetIdentification(null, msgs);
			case JbiPackage.COMPONENT__COMPONENT_CLASS_NAME:
				return basicSetComponentClassName(null, msgs);
			case JbiPackage.COMPONENT__COMPONENT_CLASS_PATH:
				return basicSetComponentClassPath(null, msgs);
			case JbiPackage.COMPONENT__BOOTSTRAP_CLASS_PATH:
				return basicSetBootstrapClassPath(null, msgs);
			case JbiPackage.COMPONENT__SHARED_LIBRARY_LIST:
				return ((InternalEList<?>)getSharedLibraryList()).basicRemove(otherEnd, msgs);
			case JbiPackage.COMPONENT__SHARED_LIBRARY:
				return ((InternalEList<?>)getSharedLibrary()).basicRemove(otherEnd, msgs);
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
			case JbiPackage.COMPONENT__IDENTIFICATION:
				return getIdentification();
			case JbiPackage.COMPONENT__COMPONENT_CLASS_NAME:
				return getComponentClassName();
			case JbiPackage.COMPONENT__COMPONENT_CLASS_PATH:
				return getComponentClassPath();
			case JbiPackage.COMPONENT__BOOTSTRAP_CLASS_NAME:
				return getBootstrapClassName();
			case JbiPackage.COMPONENT__BOOTSTRAP_CLASS_PATH:
				return getBootstrapClassPath();
			case JbiPackage.COMPONENT__SHARED_LIBRARY_LIST:
				if (coreType) return getSharedLibraryList();
				return ((FeatureMap.Internal)getSharedLibraryList()).getWrapper();
			case JbiPackage.COMPONENT__SHARED_LIBRARY:
				return getSharedLibrary();
			case JbiPackage.COMPONENT__BOOTSTRAP_CLASS_LOADER_DELEGATION:
				return getBootstrapClassLoaderDelegation();
			case JbiPackage.COMPONENT__COMPONENT_CLASS_LOADER_DELEGATION:
				return getComponentClassLoaderDelegation();
			case JbiPackage.COMPONENT__TYPE:
				return getType();
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
			case JbiPackage.COMPONENT__IDENTIFICATION:
				setIdentification((Identification)newValue);
				return;
			case JbiPackage.COMPONENT__COMPONENT_CLASS_NAME:
				setComponentClassName((ComponentClassName)newValue);
				return;
			case JbiPackage.COMPONENT__COMPONENT_CLASS_PATH:
				setComponentClassPath((ClassPath)newValue);
				return;
			case JbiPackage.COMPONENT__BOOTSTRAP_CLASS_NAME:
				setBootstrapClassName((String)newValue);
				return;
			case JbiPackage.COMPONENT__BOOTSTRAP_CLASS_PATH:
				setBootstrapClassPath((ClassPath)newValue);
				return;
			case JbiPackage.COMPONENT__SHARED_LIBRARY_LIST:
				((FeatureMap.Internal)getSharedLibraryList()).set(newValue);
				return;
			case JbiPackage.COMPONENT__SHARED_LIBRARY:
				getSharedLibrary().clear();
				getSharedLibrary().addAll((Collection<? extends SharedLibraryType1>)newValue);
				return;
			case JbiPackage.COMPONENT__BOOTSTRAP_CLASS_LOADER_DELEGATION:
				setBootstrapClassLoaderDelegation((ClassLoaderDelegationType)newValue);
				return;
			case JbiPackage.COMPONENT__COMPONENT_CLASS_LOADER_DELEGATION:
				setComponentClassLoaderDelegation((ClassLoaderDelegationType)newValue);
				return;
			case JbiPackage.COMPONENT__TYPE:
				setType((ComponentType)newValue);
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
			case JbiPackage.COMPONENT__IDENTIFICATION:
				setIdentification((Identification)null);
				return;
			case JbiPackage.COMPONENT__COMPONENT_CLASS_NAME:
				setComponentClassName((ComponentClassName)null);
				return;
			case JbiPackage.COMPONENT__COMPONENT_CLASS_PATH:
				setComponentClassPath((ClassPath)null);
				return;
			case JbiPackage.COMPONENT__BOOTSTRAP_CLASS_NAME:
				setBootstrapClassName(BOOTSTRAP_CLASS_NAME_EDEFAULT);
				return;
			case JbiPackage.COMPONENT__BOOTSTRAP_CLASS_PATH:
				setBootstrapClassPath((ClassPath)null);
				return;
			case JbiPackage.COMPONENT__SHARED_LIBRARY_LIST:
				getSharedLibraryList().clear();
				return;
			case JbiPackage.COMPONENT__SHARED_LIBRARY:
				getSharedLibrary().clear();
				return;
			case JbiPackage.COMPONENT__BOOTSTRAP_CLASS_LOADER_DELEGATION:
				unsetBootstrapClassLoaderDelegation();
				return;
			case JbiPackage.COMPONENT__COMPONENT_CLASS_LOADER_DELEGATION:
				unsetComponentClassLoaderDelegation();
				return;
			case JbiPackage.COMPONENT__TYPE:
				unsetType();
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
			case JbiPackage.COMPONENT__IDENTIFICATION:
				return identification != null;
			case JbiPackage.COMPONENT__COMPONENT_CLASS_NAME:
				return componentClassName != null;
			case JbiPackage.COMPONENT__COMPONENT_CLASS_PATH:
				return componentClassPath != null;
			case JbiPackage.COMPONENT__BOOTSTRAP_CLASS_NAME:
				return BOOTSTRAP_CLASS_NAME_EDEFAULT == null ? bootstrapClassName != null : !BOOTSTRAP_CLASS_NAME_EDEFAULT.equals(bootstrapClassName);
			case JbiPackage.COMPONENT__BOOTSTRAP_CLASS_PATH:
				return bootstrapClassPath != null;
			case JbiPackage.COMPONENT__SHARED_LIBRARY_LIST:
				return sharedLibraryList != null && !sharedLibraryList.isEmpty();
			case JbiPackage.COMPONENT__SHARED_LIBRARY:
				return !getSharedLibrary().isEmpty();
			case JbiPackage.COMPONENT__BOOTSTRAP_CLASS_LOADER_DELEGATION:
				return isSetBootstrapClassLoaderDelegation();
			case JbiPackage.COMPONENT__COMPONENT_CLASS_LOADER_DELEGATION:
				return isSetComponentClassLoaderDelegation();
			case JbiPackage.COMPONENT__TYPE:
				return isSetType();
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
		result.append(" (bootstrapClassName: ");
		result.append(bootstrapClassName);
		result.append(", sharedLibraryList: ");
		result.append(sharedLibraryList);
		result.append(", bootstrapClassLoaderDelegation: ");
		if (bootstrapClassLoaderDelegationESet) result.append(bootstrapClassLoaderDelegation); else result.append("<unset>");
		result.append(", componentClassLoaderDelegation: ");
		if (componentClassLoaderDelegationESet) result.append(componentClassLoaderDelegation); else result.append("<unset>");
		result.append(", type: ");
		if (typeESet) result.append(type); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //ComponentImpl
