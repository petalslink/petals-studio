/**
 * Copyright (c) 2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.ejb.ejb.impl;

import com.ebmwebsourcing.petals.services.ejb.ejb.EjbPackage;
import com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides;
import com.ebmwebsourcing.petals.services.ejb.ejb.EjbVersion;
import com.ebmwebsourcing.petals.services.ejb.ejb.XmlEngine;

import com.sun.java.xml.ns.jbi.impl.ProvidesImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Provides</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.ejb.ejb.impl.EjbProvidesImpl#getEjbJndiName <em>Ejb Jndi Name</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ejb.ejb.impl.EjbProvidesImpl#getJavaNamingFactoryInitial <em>Java Naming Factory Initial</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ejb.ejb.impl.EjbProvidesImpl#getJavaNamingFactoryUrlPkgs <em>Java Naming Factory Url Pkgs</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ejb.ejb.impl.EjbProvidesImpl#getJavaNamingProviderUrl <em>Java Naming Provider Url</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ejb.ejb.impl.EjbProvidesImpl#getEjbVersion <em>Ejb Version</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ejb.ejb.impl.EjbProvidesImpl#getEjbHomeInterface <em>Ejb Home Interface</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ejb.ejb.impl.EjbProvidesImpl#getSecurityName <em>Security Name</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ejb.ejb.impl.EjbProvidesImpl#getSecurityPrincipal <em>Security Principal</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ejb.ejb.impl.EjbProvidesImpl#getSecurityCredencials <em>Security Credencials</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ejb.ejb.impl.EjbProvidesImpl#getMarshallingEngine <em>Marshalling Engine</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EjbProvidesImpl extends ProvidesImpl implements EjbProvides {
	/**
	 * The default value of the '{@link #getEjbJndiName() <em>Ejb Jndi Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbJndiName()
	 * @generated
	 * @ordered
	 */
	protected static final String EJB_JNDI_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEjbJndiName() <em>Ejb Jndi Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbJndiName()
	 * @generated
	 * @ordered
	 */
	protected String ejbJndiName = EJB_JNDI_NAME_EDEFAULT;

	/**
	 * This is true if the Ejb Jndi Name attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean ejbJndiNameESet;

	/**
	 * The default value of the '{@link #getJavaNamingFactoryInitial() <em>Java Naming Factory Initial</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavaNamingFactoryInitial()
	 * @generated
	 * @ordered
	 */
	protected static final String JAVA_NAMING_FACTORY_INITIAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getJavaNamingFactoryInitial() <em>Java Naming Factory Initial</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavaNamingFactoryInitial()
	 * @generated
	 * @ordered
	 */
	protected String javaNamingFactoryInitial = JAVA_NAMING_FACTORY_INITIAL_EDEFAULT;

	/**
	 * This is true if the Java Naming Factory Initial attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean javaNamingFactoryInitialESet;

	/**
	 * The default value of the '{@link #getJavaNamingFactoryUrlPkgs() <em>Java Naming Factory Url Pkgs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavaNamingFactoryUrlPkgs()
	 * @generated
	 * @ordered
	 */
	protected static final String JAVA_NAMING_FACTORY_URL_PKGS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getJavaNamingFactoryUrlPkgs() <em>Java Naming Factory Url Pkgs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavaNamingFactoryUrlPkgs()
	 * @generated
	 * @ordered
	 */
	protected String javaNamingFactoryUrlPkgs = JAVA_NAMING_FACTORY_URL_PKGS_EDEFAULT;

	/**
	 * The default value of the '{@link #getJavaNamingProviderUrl() <em>Java Naming Provider Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavaNamingProviderUrl()
	 * @generated
	 * @ordered
	 */
	protected static final String JAVA_NAMING_PROVIDER_URL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getJavaNamingProviderUrl() <em>Java Naming Provider Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavaNamingProviderUrl()
	 * @generated
	 * @ordered
	 */
	protected String javaNamingProviderUrl = JAVA_NAMING_PROVIDER_URL_EDEFAULT;

	/**
	 * This is true if the Java Naming Provider Url attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean javaNamingProviderUrlESet;

	/**
	 * The default value of the '{@link #getEjbVersion() <em>Ejb Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbVersion()
	 * @generated
	 * @ordered
	 */
	protected static final EjbVersion EJB_VERSION_EDEFAULT = EjbVersion.V20;

	/**
	 * The cached value of the '{@link #getEjbVersion() <em>Ejb Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbVersion()
	 * @generated
	 * @ordered
	 */
	protected EjbVersion ejbVersion = EJB_VERSION_EDEFAULT;

	/**
	 * This is true if the Ejb Version attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean ejbVersionESet;

	/**
	 * The default value of the '{@link #getEjbHomeInterface() <em>Ejb Home Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbHomeInterface()
	 * @generated
	 * @ordered
	 */
	protected static final String EJB_HOME_INTERFACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEjbHomeInterface() <em>Ejb Home Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbHomeInterface()
	 * @generated
	 * @ordered
	 */
	protected String ejbHomeInterface = EJB_HOME_INTERFACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSecurityName() <em>Security Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurityName()
	 * @generated
	 * @ordered
	 */
	protected static final String SECURITY_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSecurityName() <em>Security Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurityName()
	 * @generated
	 * @ordered
	 */
	protected String securityName = SECURITY_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getSecurityPrincipal() <em>Security Principal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurityPrincipal()
	 * @generated
	 * @ordered
	 */
	protected static final String SECURITY_PRINCIPAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSecurityPrincipal() <em>Security Principal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurityPrincipal()
	 * @generated
	 * @ordered
	 */
	protected String securityPrincipal = SECURITY_PRINCIPAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getSecurityCredencials() <em>Security Credencials</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurityCredencials()
	 * @generated
	 * @ordered
	 */
	protected static final String SECURITY_CREDENCIALS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSecurityCredencials() <em>Security Credencials</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurityCredencials()
	 * @generated
	 * @ordered
	 */
	protected String securityCredencials = SECURITY_CREDENCIALS_EDEFAULT;

	/**
	 * The default value of the '{@link #getMarshallingEngine() <em>Marshalling Engine</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMarshallingEngine()
	 * @generated
	 * @ordered
	 */
	protected static final XmlEngine MARSHALLING_ENGINE_EDEFAULT = XmlEngine.JAXB;

	/**
	 * The cached value of the '{@link #getMarshallingEngine() <em>Marshalling Engine</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMarshallingEngine()
	 * @generated
	 * @ordered
	 */
	protected XmlEngine marshallingEngine = MARSHALLING_ENGINE_EDEFAULT;

	/**
	 * This is true if the Marshalling Engine attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean marshallingEngineESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EjbProvidesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.EJB_PROVIDES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEjbJndiName() {
		return ejbJndiName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEjbJndiName(String newEjbJndiName) {
		String oldEjbJndiName = ejbJndiName;
		ejbJndiName = newEjbJndiName;
		boolean oldEjbJndiNameESet = ejbJndiNameESet;
		ejbJndiNameESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_PROVIDES__EJB_JNDI_NAME, oldEjbJndiName, ejbJndiName, !oldEjbJndiNameESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetEjbJndiName() {
		String oldEjbJndiName = ejbJndiName;
		boolean oldEjbJndiNameESet = ejbJndiNameESet;
		ejbJndiName = EJB_JNDI_NAME_EDEFAULT;
		ejbJndiNameESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.EJB_PROVIDES__EJB_JNDI_NAME, oldEjbJndiName, EJB_JNDI_NAME_EDEFAULT, oldEjbJndiNameESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetEjbJndiName() {
		return ejbJndiNameESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getJavaNamingFactoryInitial() {
		return javaNamingFactoryInitial;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJavaNamingFactoryInitial(String newJavaNamingFactoryInitial) {
		String oldJavaNamingFactoryInitial = javaNamingFactoryInitial;
		javaNamingFactoryInitial = newJavaNamingFactoryInitial;
		boolean oldJavaNamingFactoryInitialESet = javaNamingFactoryInitialESet;
		javaNamingFactoryInitialESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_PROVIDES__JAVA_NAMING_FACTORY_INITIAL, oldJavaNamingFactoryInitial, javaNamingFactoryInitial, !oldJavaNamingFactoryInitialESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetJavaNamingFactoryInitial() {
		String oldJavaNamingFactoryInitial = javaNamingFactoryInitial;
		boolean oldJavaNamingFactoryInitialESet = javaNamingFactoryInitialESet;
		javaNamingFactoryInitial = JAVA_NAMING_FACTORY_INITIAL_EDEFAULT;
		javaNamingFactoryInitialESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.EJB_PROVIDES__JAVA_NAMING_FACTORY_INITIAL, oldJavaNamingFactoryInitial, JAVA_NAMING_FACTORY_INITIAL_EDEFAULT, oldJavaNamingFactoryInitialESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetJavaNamingFactoryInitial() {
		return javaNamingFactoryInitialESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getJavaNamingFactoryUrlPkgs() {
		return javaNamingFactoryUrlPkgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJavaNamingFactoryUrlPkgs(String newJavaNamingFactoryUrlPkgs) {
		String oldJavaNamingFactoryUrlPkgs = javaNamingFactoryUrlPkgs;
		javaNamingFactoryUrlPkgs = newJavaNamingFactoryUrlPkgs;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_PROVIDES__JAVA_NAMING_FACTORY_URL_PKGS, oldJavaNamingFactoryUrlPkgs, javaNamingFactoryUrlPkgs));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getJavaNamingProviderUrl() {
		return javaNamingProviderUrl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJavaNamingProviderUrl(String newJavaNamingProviderUrl) {
		String oldJavaNamingProviderUrl = javaNamingProviderUrl;
		javaNamingProviderUrl = newJavaNamingProviderUrl;
		boolean oldJavaNamingProviderUrlESet = javaNamingProviderUrlESet;
		javaNamingProviderUrlESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_PROVIDES__JAVA_NAMING_PROVIDER_URL, oldJavaNamingProviderUrl, javaNamingProviderUrl, !oldJavaNamingProviderUrlESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetJavaNamingProviderUrl() {
		String oldJavaNamingProviderUrl = javaNamingProviderUrl;
		boolean oldJavaNamingProviderUrlESet = javaNamingProviderUrlESet;
		javaNamingProviderUrl = JAVA_NAMING_PROVIDER_URL_EDEFAULT;
		javaNamingProviderUrlESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.EJB_PROVIDES__JAVA_NAMING_PROVIDER_URL, oldJavaNamingProviderUrl, JAVA_NAMING_PROVIDER_URL_EDEFAULT, oldJavaNamingProviderUrlESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetJavaNamingProviderUrl() {
		return javaNamingProviderUrlESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EjbVersion getEjbVersion() {
		return ejbVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEjbVersion(EjbVersion newEjbVersion) {
		EjbVersion oldEjbVersion = ejbVersion;
		ejbVersion = newEjbVersion == null ? EJB_VERSION_EDEFAULT : newEjbVersion;
		boolean oldEjbVersionESet = ejbVersionESet;
		ejbVersionESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_PROVIDES__EJB_VERSION, oldEjbVersion, ejbVersion, !oldEjbVersionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetEjbVersion() {
		EjbVersion oldEjbVersion = ejbVersion;
		boolean oldEjbVersionESet = ejbVersionESet;
		ejbVersion = EJB_VERSION_EDEFAULT;
		ejbVersionESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.EJB_PROVIDES__EJB_VERSION, oldEjbVersion, EJB_VERSION_EDEFAULT, oldEjbVersionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetEjbVersion() {
		return ejbVersionESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEjbHomeInterface() {
		return ejbHomeInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEjbHomeInterface(String newEjbHomeInterface) {
		String oldEjbHomeInterface = ejbHomeInterface;
		ejbHomeInterface = newEjbHomeInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_PROVIDES__EJB_HOME_INTERFACE, oldEjbHomeInterface, ejbHomeInterface));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSecurityName() {
		return securityName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSecurityName(String newSecurityName) {
		String oldSecurityName = securityName;
		securityName = newSecurityName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_PROVIDES__SECURITY_NAME, oldSecurityName, securityName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSecurityPrincipal() {
		return securityPrincipal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSecurityPrincipal(String newSecurityPrincipal) {
		String oldSecurityPrincipal = securityPrincipal;
		securityPrincipal = newSecurityPrincipal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_PROVIDES__SECURITY_PRINCIPAL, oldSecurityPrincipal, securityPrincipal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSecurityCredencials() {
		return securityCredencials;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSecurityCredencials(String newSecurityCredencials) {
		String oldSecurityCredencials = securityCredencials;
		securityCredencials = newSecurityCredencials;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_PROVIDES__SECURITY_CREDENCIALS, oldSecurityCredencials, securityCredencials));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XmlEngine getMarshallingEngine() {
		return marshallingEngine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMarshallingEngine(XmlEngine newMarshallingEngine) {
		XmlEngine oldMarshallingEngine = marshallingEngine;
		marshallingEngine = newMarshallingEngine == null ? MARSHALLING_ENGINE_EDEFAULT : newMarshallingEngine;
		boolean oldMarshallingEngineESet = marshallingEngineESet;
		marshallingEngineESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_PROVIDES__MARSHALLING_ENGINE, oldMarshallingEngine, marshallingEngine, !oldMarshallingEngineESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetMarshallingEngine() {
		XmlEngine oldMarshallingEngine = marshallingEngine;
		boolean oldMarshallingEngineESet = marshallingEngineESet;
		marshallingEngine = MARSHALLING_ENGINE_EDEFAULT;
		marshallingEngineESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.EJB_PROVIDES__MARSHALLING_ENGINE, oldMarshallingEngine, MARSHALLING_ENGINE_EDEFAULT, oldMarshallingEngineESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetMarshallingEngine() {
		return marshallingEngineESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EjbPackage.EJB_PROVIDES__EJB_JNDI_NAME:
				return getEjbJndiName();
			case EjbPackage.EJB_PROVIDES__JAVA_NAMING_FACTORY_INITIAL:
				return getJavaNamingFactoryInitial();
			case EjbPackage.EJB_PROVIDES__JAVA_NAMING_FACTORY_URL_PKGS:
				return getJavaNamingFactoryUrlPkgs();
			case EjbPackage.EJB_PROVIDES__JAVA_NAMING_PROVIDER_URL:
				return getJavaNamingProviderUrl();
			case EjbPackage.EJB_PROVIDES__EJB_VERSION:
				return getEjbVersion();
			case EjbPackage.EJB_PROVIDES__EJB_HOME_INTERFACE:
				return getEjbHomeInterface();
			case EjbPackage.EJB_PROVIDES__SECURITY_NAME:
				return getSecurityName();
			case EjbPackage.EJB_PROVIDES__SECURITY_PRINCIPAL:
				return getSecurityPrincipal();
			case EjbPackage.EJB_PROVIDES__SECURITY_CREDENCIALS:
				return getSecurityCredencials();
			case EjbPackage.EJB_PROVIDES__MARSHALLING_ENGINE:
				return getMarshallingEngine();
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
			case EjbPackage.EJB_PROVIDES__EJB_JNDI_NAME:
				setEjbJndiName((String)newValue);
				return;
			case EjbPackage.EJB_PROVIDES__JAVA_NAMING_FACTORY_INITIAL:
				setJavaNamingFactoryInitial((String)newValue);
				return;
			case EjbPackage.EJB_PROVIDES__JAVA_NAMING_FACTORY_URL_PKGS:
				setJavaNamingFactoryUrlPkgs((String)newValue);
				return;
			case EjbPackage.EJB_PROVIDES__JAVA_NAMING_PROVIDER_URL:
				setJavaNamingProviderUrl((String)newValue);
				return;
			case EjbPackage.EJB_PROVIDES__EJB_VERSION:
				setEjbVersion((EjbVersion)newValue);
				return;
			case EjbPackage.EJB_PROVIDES__EJB_HOME_INTERFACE:
				setEjbHomeInterface((String)newValue);
				return;
			case EjbPackage.EJB_PROVIDES__SECURITY_NAME:
				setSecurityName((String)newValue);
				return;
			case EjbPackage.EJB_PROVIDES__SECURITY_PRINCIPAL:
				setSecurityPrincipal((String)newValue);
				return;
			case EjbPackage.EJB_PROVIDES__SECURITY_CREDENCIALS:
				setSecurityCredencials((String)newValue);
				return;
			case EjbPackage.EJB_PROVIDES__MARSHALLING_ENGINE:
				setMarshallingEngine((XmlEngine)newValue);
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
			case EjbPackage.EJB_PROVIDES__EJB_JNDI_NAME:
				unsetEjbJndiName();
				return;
			case EjbPackage.EJB_PROVIDES__JAVA_NAMING_FACTORY_INITIAL:
				unsetJavaNamingFactoryInitial();
				return;
			case EjbPackage.EJB_PROVIDES__JAVA_NAMING_FACTORY_URL_PKGS:
				setJavaNamingFactoryUrlPkgs(JAVA_NAMING_FACTORY_URL_PKGS_EDEFAULT);
				return;
			case EjbPackage.EJB_PROVIDES__JAVA_NAMING_PROVIDER_URL:
				unsetJavaNamingProviderUrl();
				return;
			case EjbPackage.EJB_PROVIDES__EJB_VERSION:
				unsetEjbVersion();
				return;
			case EjbPackage.EJB_PROVIDES__EJB_HOME_INTERFACE:
				setEjbHomeInterface(EJB_HOME_INTERFACE_EDEFAULT);
				return;
			case EjbPackage.EJB_PROVIDES__SECURITY_NAME:
				setSecurityName(SECURITY_NAME_EDEFAULT);
				return;
			case EjbPackage.EJB_PROVIDES__SECURITY_PRINCIPAL:
				setSecurityPrincipal(SECURITY_PRINCIPAL_EDEFAULT);
				return;
			case EjbPackage.EJB_PROVIDES__SECURITY_CREDENCIALS:
				setSecurityCredencials(SECURITY_CREDENCIALS_EDEFAULT);
				return;
			case EjbPackage.EJB_PROVIDES__MARSHALLING_ENGINE:
				unsetMarshallingEngine();
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
			case EjbPackage.EJB_PROVIDES__EJB_JNDI_NAME:
				return isSetEjbJndiName();
			case EjbPackage.EJB_PROVIDES__JAVA_NAMING_FACTORY_INITIAL:
				return isSetJavaNamingFactoryInitial();
			case EjbPackage.EJB_PROVIDES__JAVA_NAMING_FACTORY_URL_PKGS:
				return JAVA_NAMING_FACTORY_URL_PKGS_EDEFAULT == null ? javaNamingFactoryUrlPkgs != null : !JAVA_NAMING_FACTORY_URL_PKGS_EDEFAULT.equals(javaNamingFactoryUrlPkgs);
			case EjbPackage.EJB_PROVIDES__JAVA_NAMING_PROVIDER_URL:
				return isSetJavaNamingProviderUrl();
			case EjbPackage.EJB_PROVIDES__EJB_VERSION:
				return isSetEjbVersion();
			case EjbPackage.EJB_PROVIDES__EJB_HOME_INTERFACE:
				return EJB_HOME_INTERFACE_EDEFAULT == null ? ejbHomeInterface != null : !EJB_HOME_INTERFACE_EDEFAULT.equals(ejbHomeInterface);
			case EjbPackage.EJB_PROVIDES__SECURITY_NAME:
				return SECURITY_NAME_EDEFAULT == null ? securityName != null : !SECURITY_NAME_EDEFAULT.equals(securityName);
			case EjbPackage.EJB_PROVIDES__SECURITY_PRINCIPAL:
				return SECURITY_PRINCIPAL_EDEFAULT == null ? securityPrincipal != null : !SECURITY_PRINCIPAL_EDEFAULT.equals(securityPrincipal);
			case EjbPackage.EJB_PROVIDES__SECURITY_CREDENCIALS:
				return SECURITY_CREDENCIALS_EDEFAULT == null ? securityCredencials != null : !SECURITY_CREDENCIALS_EDEFAULT.equals(securityCredencials);
			case EjbPackage.EJB_PROVIDES__MARSHALLING_ENGINE:
				return isSetMarshallingEngine();
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
		result.append(" (ejbJndiName: ");
		if (ejbJndiNameESet) result.append(ejbJndiName); else result.append("<unset>");
		result.append(", javaNamingFactoryInitial: ");
		if (javaNamingFactoryInitialESet) result.append(javaNamingFactoryInitial); else result.append("<unset>");
		result.append(", javaNamingFactoryUrlPkgs: ");
		result.append(javaNamingFactoryUrlPkgs);
		result.append(", javaNamingProviderUrl: ");
		if (javaNamingProviderUrlESet) result.append(javaNamingProviderUrl); else result.append("<unset>");
		result.append(", ejbVersion: ");
		if (ejbVersionESet) result.append(ejbVersion); else result.append("<unset>");
		result.append(", ejbHomeInterface: ");
		result.append(ejbHomeInterface);
		result.append(", securityName: ");
		result.append(securityName);
		result.append(", securityPrincipal: ");
		result.append(securityPrincipal);
		result.append(", securityCredencials: ");
		result.append(securityCredencials);
		result.append(", marshallingEngine: ");
		if (marshallingEngineESet) result.append(marshallingEngine); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //EjbProvidesImpl
