/**
 * Copyright (c) 2011-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.soap.soap.impl;

import com.ebmwebsourcing.petals.services.soap.soap.Compatibility;
import com.ebmwebsourcing.petals.services.soap.soap.SoapComponent;
import com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes;
import com.ebmwebsourcing.petals.services.soap.soap.SoapFactory;
import com.ebmwebsourcing.petals.services.soap.soap.SoapMode;
import com.ebmwebsourcing.petals.services.soap.soap.SoapPackage;
import com.ebmwebsourcing.petals.services.soap.soap.SoapProvides;
import com.ebmwebsourcing.petals.services.soap.soap.SoapVersion;

import com.sun.java.xml.ns.jbi.JbiPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SoapPackageImpl extends EPackageImpl implements SoapPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass soapProvidesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass soapConsumesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass soapComponentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum soapVersionEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum soapModeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum compatibilityEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SoapPackageImpl() {
		super(eNS_URI, SoapFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link SoapPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SoapPackage init() {
		if (isInited) return (SoapPackage)EPackage.Registry.INSTANCE.getEPackage(SoapPackage.eNS_URI);

		// Obtain or create and register package
		SoapPackageImpl theSoapPackage = (SoapPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SoapPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SoapPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		JbiPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theSoapPackage.createPackageContents();

		// Initialize created meta-data
		theSoapPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSoapPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SoapPackage.eNS_URI, theSoapPackage);
		return theSoapPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSoapProvides() {
		return soapProvidesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapProvides_Address() {
		return (EAttribute)soapProvidesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapProvides_WsaReplyTo() {
		return (EAttribute)soapProvidesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapProvides_WsaFrom() {
		return (EAttribute)soapProvidesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapProvides_WsaFaultTo() {
		return (EAttribute)soapProvidesEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapProvides_SoapVersion() {
		return (EAttribute)soapProvidesEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapProvides_ChunkedMode() {
		return (EAttribute)soapProvidesEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapProvides_SynchonousTimeout() {
		return (EAttribute)soapProvidesEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapProvides_CleanupTransport() {
		return (EAttribute)soapProvidesEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapProvides_Mode() {
		return (EAttribute)soapProvidesEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapProvides_ProxyHost() {
		return (EAttribute)soapProvidesEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapProvides_ProxyUser() {
		return (EAttribute)soapProvidesEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapProvides_ProxyPassword() {
		return (EAttribute)soapProvidesEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapProvides_ProxyDomain() {
		return (EAttribute)soapProvidesEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapProvides_HeadersFilter() {
		return (EAttribute)soapProvidesEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapProvides_InjectHeaders() {
		return (EAttribute)soapProvidesEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapProvides_HeadersToInject() {
		return (EAttribute)soapProvidesEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapProvides_HttpBasicAuthUsername() {
		return (EAttribute)soapProvidesEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapProvides_HttpBasicAuthPassword() {
		return (EAttribute)soapProvidesEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapProvides_EnableCompatibilityFor() {
		return (EAttribute)soapProvidesEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapProvides_EnableWsa() {
		return (EAttribute)soapProvidesEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapProvides_HttpsTruststoreFile() {
		return (EAttribute)soapProvidesEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapProvides_HttpsTruststorePassword() {
		return (EAttribute)soapProvidesEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapProvides_HttpsKeystoreFile() {
		return (EAttribute)soapProvidesEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapProvides_HttpsKeystorePassword() {
		return (EAttribute)soapProvidesEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapProvides_ProxyPort() {
		return (EAttribute)soapProvidesEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSoapConsumes() {
		return soapConsumesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapConsumes_SoapServiceName() {
		return (EAttribute)soapConsumesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapConsumes_SoapAction() {
		return (EAttribute)soapConsumesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapConsumes_SynchonousTimeout() {
		return (EAttribute)soapConsumesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapConsumes_Mode() {
		return (EAttribute)soapConsumesEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapConsumes_EnableHttpTransport() {
		return (EAttribute)soapConsumesEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapConsumes_EnableHttpsTransport() {
		return (EAttribute)soapConsumesEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapConsumes_EnableJmsTransport() {
		return (EAttribute)soapConsumesEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapConsumes_EnableCompatibilityFor() {
		return (EAttribute)soapConsumesEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapConsumes_EnableWsa() {
		return (EAttribute)soapConsumesEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapConsumes_HttpServicesRedirection() {
		return (EAttribute)soapConsumesEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSoapComponent() {
		return soapComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapComponent_HttpPort() {
		return (EAttribute)soapComponentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapComponent_HttpHost() {
		return (EAttribute)soapComponentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapComponent_HttpServiceList() {
		return (EAttribute)soapComponentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapComponent_HttpServiceContext() {
		return (EAttribute)soapComponentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapComponent_HttpServiceMapping() {
		return (EAttribute)soapComponentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapComponent_HttpThreadPoolSizeMin() {
		return (EAttribute)soapComponentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapComponent_HttpThreadPoolSizeMax() {
		return (EAttribute)soapComponentEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapComponent_HttpAcceptors() {
		return (EAttribute)soapComponentEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapComponent_JavaNamingFactoryInitial() {
		return (EAttribute)soapComponentEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapComponent_JavaNamingProviderUrl() {
		return (EAttribute)soapComponentEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoapComponent_JmsConnectionFactoryJndiname() {
		return (EAttribute)soapComponentEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSoapVersion() {
		return soapVersionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSoapMode() {
		return soapModeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getCompatibility() {
		return compatibilityEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SoapFactory getSoapFactory() {
		return (SoapFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		soapProvidesEClass = createEClass(SOAP_PROVIDES);
		createEAttribute(soapProvidesEClass, SOAP_PROVIDES__ADDRESS);
		createEAttribute(soapProvidesEClass, SOAP_PROVIDES__WSA_REPLY_TO);
		createEAttribute(soapProvidesEClass, SOAP_PROVIDES__WSA_FROM);
		createEAttribute(soapProvidesEClass, SOAP_PROVIDES__WSA_FAULT_TO);
		createEAttribute(soapProvidesEClass, SOAP_PROVIDES__SOAP_VERSION);
		createEAttribute(soapProvidesEClass, SOAP_PROVIDES__CHUNKED_MODE);
		createEAttribute(soapProvidesEClass, SOAP_PROVIDES__SYNCHONOUS_TIMEOUT);
		createEAttribute(soapProvidesEClass, SOAP_PROVIDES__CLEANUP_TRANSPORT);
		createEAttribute(soapProvidesEClass, SOAP_PROVIDES__MODE);
		createEAttribute(soapProvidesEClass, SOAP_PROVIDES__PROXY_HOST);
		createEAttribute(soapProvidesEClass, SOAP_PROVIDES__PROXY_PORT);
		createEAttribute(soapProvidesEClass, SOAP_PROVIDES__PROXY_USER);
		createEAttribute(soapProvidesEClass, SOAP_PROVIDES__PROXY_PASSWORD);
		createEAttribute(soapProvidesEClass, SOAP_PROVIDES__PROXY_DOMAIN);
		createEAttribute(soapProvidesEClass, SOAP_PROVIDES__HEADERS_FILTER);
		createEAttribute(soapProvidesEClass, SOAP_PROVIDES__INJECT_HEADERS);
		createEAttribute(soapProvidesEClass, SOAP_PROVIDES__HEADERS_TO_INJECT);
		createEAttribute(soapProvidesEClass, SOAP_PROVIDES__HTTP_BASIC_AUTH_USERNAME);
		createEAttribute(soapProvidesEClass, SOAP_PROVIDES__HTTP_BASIC_AUTH_PASSWORD);
		createEAttribute(soapProvidesEClass, SOAP_PROVIDES__ENABLE_COMPATIBILITY_FOR);
		createEAttribute(soapProvidesEClass, SOAP_PROVIDES__ENABLE_WSA);
		createEAttribute(soapProvidesEClass, SOAP_PROVIDES__HTTPS_TRUSTSTORE_FILE);
		createEAttribute(soapProvidesEClass, SOAP_PROVIDES__HTTPS_TRUSTSTORE_PASSWORD);
		createEAttribute(soapProvidesEClass, SOAP_PROVIDES__HTTPS_KEYSTORE_FILE);
		createEAttribute(soapProvidesEClass, SOAP_PROVIDES__HTTPS_KEYSTORE_PASSWORD);

		soapConsumesEClass = createEClass(SOAP_CONSUMES);
		createEAttribute(soapConsumesEClass, SOAP_CONSUMES__SOAP_SERVICE_NAME);
		createEAttribute(soapConsumesEClass, SOAP_CONSUMES__SOAP_ACTION);
		createEAttribute(soapConsumesEClass, SOAP_CONSUMES__SYNCHONOUS_TIMEOUT);
		createEAttribute(soapConsumesEClass, SOAP_CONSUMES__MODE);
		createEAttribute(soapConsumesEClass, SOAP_CONSUMES__ENABLE_HTTP_TRANSPORT);
		createEAttribute(soapConsumesEClass, SOAP_CONSUMES__ENABLE_HTTPS_TRANSPORT);
		createEAttribute(soapConsumesEClass, SOAP_CONSUMES__ENABLE_JMS_TRANSPORT);
		createEAttribute(soapConsumesEClass, SOAP_CONSUMES__ENABLE_COMPATIBILITY_FOR);
		createEAttribute(soapConsumesEClass, SOAP_CONSUMES__ENABLE_WSA);
		createEAttribute(soapConsumesEClass, SOAP_CONSUMES__HTTP_SERVICES_REDIRECTION);

		soapComponentEClass = createEClass(SOAP_COMPONENT);
		createEAttribute(soapComponentEClass, SOAP_COMPONENT__HTTP_PORT);
		createEAttribute(soapComponentEClass, SOAP_COMPONENT__HTTP_HOST);
		createEAttribute(soapComponentEClass, SOAP_COMPONENT__HTTP_SERVICE_LIST);
		createEAttribute(soapComponentEClass, SOAP_COMPONENT__HTTP_SERVICE_CONTEXT);
		createEAttribute(soapComponentEClass, SOAP_COMPONENT__HTTP_SERVICE_MAPPING);
		createEAttribute(soapComponentEClass, SOAP_COMPONENT__HTTP_THREAD_POOL_SIZE_MIN);
		createEAttribute(soapComponentEClass, SOAP_COMPONENT__HTTP_THREAD_POOL_SIZE_MAX);
		createEAttribute(soapComponentEClass, SOAP_COMPONENT__HTTP_ACCEPTORS);
		createEAttribute(soapComponentEClass, SOAP_COMPONENT__JAVA_NAMING_FACTORY_INITIAL);
		createEAttribute(soapComponentEClass, SOAP_COMPONENT__JAVA_NAMING_PROVIDER_URL);
		createEAttribute(soapComponentEClass, SOAP_COMPONENT__JMS_CONNECTION_FACTORY_JNDINAME);

		// Create enums
		soapVersionEEnum = createEEnum(SOAP_VERSION);
		soapModeEEnum = createEEnum(SOAP_MODE);
		compatibilityEEnum = createEEnum(COMPATIBILITY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		JbiPackage theJbiPackage = (JbiPackage)EPackage.Registry.INSTANCE.getEPackage(JbiPackage.eNS_URI);
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		soapProvidesEClass.getESuperTypes().add(theJbiPackage.getProvides());
		soapConsumesEClass.getESuperTypes().add(theJbiPackage.getConsumes());
		soapComponentEClass.getESuperTypes().add(theJbiPackage.getComponent());

		// Initialize classes and features; add operations and parameters
		initEClass(soapProvidesEClass, SoapProvides.class, "SoapProvides", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSoapProvides_Address(), theXMLTypePackage.getString(), "address", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapProvides_WsaReplyTo(), theXMLTypePackage.getString(), "wsaReplyTo", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapProvides_WsaFrom(), theXMLTypePackage.getString(), "wsaFrom", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapProvides_WsaFaultTo(), theXMLTypePackage.getString(), "wsaFaultTo", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapProvides_SoapVersion(), this.getSoapVersion(), "soapVersion", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapProvides_ChunkedMode(), theXMLTypePackage.getBoolean(), "chunkedMode", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapProvides_SynchonousTimeout(), theXMLTypePackage.getInt(), "synchonousTimeout", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapProvides_CleanupTransport(), theXMLTypePackage.getBoolean(), "cleanupTransport", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapProvides_Mode(), this.getSoapMode(), "mode", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapProvides_ProxyHost(), theXMLTypePackage.getString(), "proxyHost", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapProvides_ProxyPort(), theXMLTypePackage.getInt(), "proxyPort", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapProvides_ProxyUser(), theXMLTypePackage.getString(), "proxyUser", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapProvides_ProxyPassword(), theXMLTypePackage.getString(), "proxyPassword", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapProvides_ProxyDomain(), theXMLTypePackage.getString(), "proxyDomain", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapProvides_HeadersFilter(), theXMLTypePackage.getString(), "headersFilter", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapProvides_InjectHeaders(), theXMLTypePackage.getString(), "injectHeaders", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapProvides_HeadersToInject(), theXMLTypePackage.getString(), "headersToInject", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapProvides_HttpBasicAuthUsername(), theXMLTypePackage.getString(), "httpBasicAuthUsername", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapProvides_HttpBasicAuthPassword(), theXMLTypePackage.getString(), "httpBasicAuthPassword", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapProvides_EnableCompatibilityFor(), this.getCompatibility(), "enableCompatibilityFor", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapProvides_EnableWsa(), theXMLTypePackage.getBoolean(), "enableWsa", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapProvides_HttpsTruststoreFile(), theXMLTypePackage.getString(), "httpsTruststoreFile", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapProvides_HttpsTruststorePassword(), theXMLTypePackage.getString(), "httpsTruststorePassword", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapProvides_HttpsKeystoreFile(), theXMLTypePackage.getString(), "httpsKeystoreFile", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapProvides_HttpsKeystorePassword(), theXMLTypePackage.getString(), "httpsKeystorePassword", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(soapConsumesEClass, SoapConsumes.class, "SoapConsumes", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSoapConsumes_SoapServiceName(), theXMLTypePackage.getString(), "soapServiceName", null, 1, 1, SoapConsumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapConsumes_SoapAction(), theXMLTypePackage.getString(), "soapAction", null, 0, 1, SoapConsumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapConsumes_SynchonousTimeout(), theXMLTypePackage.getInt(), "synchonousTimeout", null, 0, 1, SoapConsumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapConsumes_Mode(), this.getSoapMode(), "mode", null, 1, 1, SoapConsumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapConsumes_EnableHttpTransport(), theXMLTypePackage.getBoolean(), "enableHttpTransport", null, 0, 1, SoapConsumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapConsumes_EnableHttpsTransport(), theXMLTypePackage.getBoolean(), "enableHttpsTransport", null, 0, 1, SoapConsumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapConsumes_EnableJmsTransport(), theXMLTypePackage.getBoolean(), "enableJmsTransport", null, 0, 1, SoapConsumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapConsumes_EnableCompatibilityFor(), this.getCompatibility(), "enableCompatibilityFor", null, 0, 1, SoapConsumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapConsumes_EnableWsa(), theXMLTypePackage.getBoolean(), "enableWsa", null, 0, 1, SoapConsumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapConsumes_HttpServicesRedirection(), theXMLTypePackage.getString(), "httpServicesRedirection", null, 0, 1, SoapConsumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(soapComponentEClass, SoapComponent.class, "SoapComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSoapComponent_HttpPort(), theXMLTypePackage.getInt(), "httpPort", null, 0, 1, SoapComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapComponent_HttpHost(), theXMLTypePackage.getString(), "httpHost", null, 0, 1, SoapComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapComponent_HttpServiceList(), theXMLTypePackage.getBoolean(), "httpServiceList", null, 0, 1, SoapComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapComponent_HttpServiceContext(), theXMLTypePackage.getString(), "httpServiceContext", null, 0, 1, SoapComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapComponent_HttpServiceMapping(), theXMLTypePackage.getString(), "httpServiceMapping", null, 0, 1, SoapComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapComponent_HttpThreadPoolSizeMin(), theXMLTypePackage.getInt(), "httpThreadPoolSizeMin", null, 0, 1, SoapComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapComponent_HttpThreadPoolSizeMax(), theXMLTypePackage.getInt(), "httpThreadPoolSizeMax", null, 0, 1, SoapComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapComponent_HttpAcceptors(), theXMLTypePackage.getInt(), "httpAcceptors", null, 0, 1, SoapComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapComponent_JavaNamingFactoryInitial(), theXMLTypePackage.getString(), "javaNamingFactoryInitial", null, 0, 1, SoapComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapComponent_JavaNamingProviderUrl(), theXMLTypePackage.getString(), "javaNamingProviderUrl", null, 0, 1, SoapComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSoapComponent_JmsConnectionFactoryJndiname(), theXMLTypePackage.getString(), "jmsConnectionFactoryJndiname", null, 0, 1, SoapComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(soapVersionEEnum, SoapVersion.class, "SoapVersion");
		addEEnumLiteral(soapVersionEEnum, SoapVersion.SOAP_11);
		addEEnumLiteral(soapVersionEEnum, SoapVersion.SOAP_12);

		initEEnum(soapModeEEnum, SoapMode.class, "SoapMode");
		addEEnumLiteral(soapModeEEnum, SoapMode.SOAP);
		addEEnumLiteral(soapModeEEnum, SoapMode.REST);
		addEEnumLiteral(soapModeEEnum, SoapMode.TOPIC);

		initEEnum(compatibilityEEnum, Compatibility.class, "Compatibility");
		addEEnumLiteral(compatibilityEEnum, Compatibility.AXIS1);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
		addAnnotation
		  (soapProvidesEClass, 
		   source, 
		   new String[] {
			 "name", ""
		   });		
		addAnnotation
		  (getSoapProvides_Address(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element"
		   });		
		addAnnotation
		  (getSoapProvides_WsaReplyTo(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "wsa-replyto"
		   });		
		addAnnotation
		  (getSoapProvides_WsaFrom(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "wsa-from"
		   });		
		addAnnotation
		  (getSoapProvides_WsaFaultTo(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "wsa-faultto"
		   });		
		addAnnotation
		  (getSoapProvides_SoapVersion(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "soap-version"
		   });		
		addAnnotation
		  (getSoapProvides_ChunkedMode(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "chunked-mode"
		   });		
		addAnnotation
		  (getSoapProvides_SynchonousTimeout(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "synchronous-timeout"
		   });		
		addAnnotation
		  (getSoapProvides_CleanupTransport(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "cleanup-transport"
		   });		
		addAnnotation
		  (getSoapProvides_Mode(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element"
		   });		
		addAnnotation
		  (getSoapProvides_ProxyHost(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "proxy-host"
		   });		
		addAnnotation
		  (getSoapProvides_ProxyPort(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "proxy-port"
		   });		
		addAnnotation
		  (getSoapProvides_ProxyUser(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "proxy-user"
		   });		
		addAnnotation
		  (getSoapProvides_ProxyPassword(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "proxy-password"
		   });		
		addAnnotation
		  (getSoapProvides_ProxyDomain(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "proxy-domain"
		   });		
		addAnnotation
		  (getSoapProvides_HeadersFilter(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "headers-filter"
		   });		
		addAnnotation
		  (getSoapProvides_InjectHeaders(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "inject-headers"
		   });		
		addAnnotation
		  (getSoapProvides_HeadersToInject(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "headers-to-inject"
		   });		
		addAnnotation
		  (getSoapProvides_HttpBasicAuthUsername(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "http-basic-auth-username"
		   });		
		addAnnotation
		  (getSoapProvides_HttpBasicAuthPassword(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "http-basic-auth-password"
		   });		
		addAnnotation
		  (getSoapProvides_EnableCompatibilityFor(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "enable-compatibility-for"
		   });		
		addAnnotation
		  (getSoapProvides_EnableWsa(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "enable-wsa"
		   });		
		addAnnotation
		  (getSoapProvides_HttpsTruststoreFile(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "https-truststore-file"
		   });		
		addAnnotation
		  (getSoapProvides_HttpsTruststorePassword(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "https-truststore-password"
		   });		
		addAnnotation
		  (getSoapProvides_HttpsKeystoreFile(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "https-keystore-file"
		   });		
		addAnnotation
		  (getSoapProvides_HttpsKeystorePassword(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "https-keystore-password"
		   });		
		addAnnotation
		  (soapConsumesEClass, 
		   source, 
		   new String[] {
			 "name", ""
		   });		
		addAnnotation
		  (getSoapConsumes_SoapServiceName(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "service-name"
		   });		
		addAnnotation
		  (getSoapConsumes_SoapAction(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "soap-action"
		   });		
		addAnnotation
		  (getSoapConsumes_SynchonousTimeout(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "synchronous-timeout"
		   });		
		addAnnotation
		  (getSoapConsumes_Mode(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element"
		   });		
		addAnnotation
		  (getSoapConsumes_EnableHttpTransport(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "enable-http-transport"
		   });		
		addAnnotation
		  (getSoapConsumes_EnableHttpsTransport(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "enable-https-transport"
		   });		
		addAnnotation
		  (getSoapConsumes_EnableJmsTransport(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "enable-jms-transport"
		   });		
		addAnnotation
		  (getSoapConsumes_EnableCompatibilityFor(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "enable-compatibility-for"
		   });		
		addAnnotation
		  (getSoapConsumes_EnableWsa(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "enable-wsa"
		   });		
		addAnnotation
		  (getSoapConsumes_HttpServicesRedirection(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "http-service-redirection"
		   });		
		addAnnotation
		  (soapComponentEClass, 
		   source, 
		   new String[] {
			 "name", ""
		   });		
		addAnnotation
		  (getSoapComponent_HttpPort(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "http-port"
		   });		
		addAnnotation
		  (getSoapComponent_HttpHost(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "http-host"
		   });		
		addAnnotation
		  (getSoapComponent_HttpServiceList(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "http-service-list"
		   });		
		addAnnotation
		  (getSoapComponent_HttpServiceContext(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "http-service-context"
		   });		
		addAnnotation
		  (getSoapComponent_HttpServiceMapping(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "http-service-mapping"
		   });		
		addAnnotation
		  (getSoapComponent_HttpThreadPoolSizeMin(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "http-thread-pool-size-min"
		   });		
		addAnnotation
		  (getSoapComponent_HttpThreadPoolSizeMax(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "http-thread-pool-size-max"
		   });		
		addAnnotation
		  (getSoapComponent_HttpAcceptors(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "http-acceptors"
		   });		
		addAnnotation
		  (getSoapComponent_JavaNamingFactoryInitial(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "java-naming-factory-initial"
		   });		
		addAnnotation
		  (getSoapComponent_JavaNamingProviderUrl(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "java-naming-provider-url"
		   });		
		addAnnotation
		  (getSoapComponent_JmsConnectionFactoryJndiname(), 
		   source, 
		   new String[] {
			 "group", "#group:0",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "jms-connection-factory-jndiname"
		   });
	}

} //SoapPackageImpl
