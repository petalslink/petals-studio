/**
 * Copyright (c) 2011-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 */
package com.ebmwebsourcing.petals.services.jms.jms.impl;

import com.ebmwebsourcing.petals.services.jms.jms.JmsConsumes;
import com.ebmwebsourcing.petals.services.jms.jms.JmsExtension;
import com.ebmwebsourcing.petals.services.jms.jms.JmsFactory;
import com.ebmwebsourcing.petals.services.jms.jms.JmsPackage;
import com.ebmwebsourcing.petals.services.jms.jms.JmsProvides;

import com.sun.java.xml.ns.jbi.JbiPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class JmsPackageImpl extends EPackageImpl implements JmsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass jmsExtensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass jmsProvidesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass jmsConsumesEClass = null;

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
	 * @see com.ebmwebsourcing.petals.services.jms.jms.JmsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private JmsPackageImpl() {
		super(eNS_URI, JmsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link JmsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static JmsPackage init() {
		if (isInited) return (JmsPackage)EPackage.Registry.INSTANCE.getEPackage(JmsPackage.eNS_URI);

		// Obtain or create and register package
		JmsPackageImpl theJmsPackage = (JmsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof JmsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new JmsPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		JbiPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theJmsPackage.createPackageContents();

		// Initialize created meta-data
		theJmsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theJmsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(JmsPackage.eNS_URI, theJmsPackage);
		return theJmsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJmsExtension() {
		return jmsExtensionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJmsExtension_JndiProviderURL() {
		return (EAttribute)jmsExtensionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJmsExtension_JndiInitialContextFactory() {
		return (EAttribute)jmsExtensionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJmsExtension_JndiDestinationName() {
		return (EAttribute)jmsExtensionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJmsExtension_JndiConnectionFactory() {
		return (EAttribute)jmsExtensionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJmsExtension_User() {
		return (EAttribute)jmsExtensionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJmsExtension_Password() {
		return (EAttribute)jmsExtensionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJmsExtension_Transacted() {
		return (EAttribute)jmsExtensionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJmsProvides() {
		return jmsProvidesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJmsProvides_MaxActive() {
		return (EAttribute)jmsProvidesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJmsProvides_MaxWait() {
		return (EAttribute)jmsProvidesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJmsProvides_MaxIdle() {
		return (EAttribute)jmsProvidesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJmsProvides_TimeBetweenEvictionRunsMilles() {
		return (EAttribute)jmsProvidesEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJmsProvides_MinEvictableIdleTimeMillis() {
		return (EAttribute)jmsProvidesEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJmsProvides_TestWhileIdle() {
		return (EAttribute)jmsProvidesEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJmsConsumes() {
		return jmsConsumesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JmsFactory getJmsFactory() {
		return (JmsFactory)getEFactoryInstance();
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
		jmsExtensionEClass = createEClass(JMS_EXTENSION);
		createEAttribute(jmsExtensionEClass, JMS_EXTENSION__JNDI_PROVIDER_URL);
		createEAttribute(jmsExtensionEClass, JMS_EXTENSION__JNDI_INITIAL_CONTEXT_FACTORY);
		createEAttribute(jmsExtensionEClass, JMS_EXTENSION__JNDI_DESTINATION_NAME);
		createEAttribute(jmsExtensionEClass, JMS_EXTENSION__JNDI_CONNECTION_FACTORY);
		createEAttribute(jmsExtensionEClass, JMS_EXTENSION__USER);
		createEAttribute(jmsExtensionEClass, JMS_EXTENSION__PASSWORD);
		createEAttribute(jmsExtensionEClass, JMS_EXTENSION__TRANSACTED);

		jmsProvidesEClass = createEClass(JMS_PROVIDES);
		createEAttribute(jmsProvidesEClass, JMS_PROVIDES__MAX_ACTIVE);
		createEAttribute(jmsProvidesEClass, JMS_PROVIDES__MAX_WAIT);
		createEAttribute(jmsProvidesEClass, JMS_PROVIDES__MAX_IDLE);
		createEAttribute(jmsProvidesEClass, JMS_PROVIDES__TIME_BETWEEN_EVICTION_RUNS_MILLES);
		createEAttribute(jmsProvidesEClass, JMS_PROVIDES__MIN_EVICTABLE_IDLE_TIME_MILLIS);
		createEAttribute(jmsProvidesEClass, JMS_PROVIDES__TEST_WHILE_IDLE);

		jmsConsumesEClass = createEClass(JMS_CONSUMES);
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
		jmsExtensionEClass.getESuperTypes().add(theJbiPackage.getAbstractExtensibleElement());
		jmsProvidesEClass.getESuperTypes().add(this.getJmsExtension());
		jmsConsumesEClass.getESuperTypes().add(this.getJmsExtension());

		// Initialize classes and features; add operations and parameters
		initEClass(jmsExtensionEClass, JmsExtension.class, "JmsExtension", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getJmsExtension_JndiProviderURL(), theXMLTypePackage.getString(), "jndiProviderURL", "", 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getJmsExtension_JndiInitialContextFactory(), theXMLTypePackage.getString(), "jndiInitialContextFactory", "", 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getJmsExtension_JndiDestinationName(), theXMLTypePackage.getString(), "jndiDestinationName", "", 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getJmsExtension_JndiConnectionFactory(), theXMLTypePackage.getString(), "jndiConnectionFactory", "", 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getJmsExtension_User(), theXMLTypePackage.getString(), "user", "", 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getJmsExtension_Password(), theXMLTypePackage.getString(), "password", "", 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getJmsExtension_Transacted(), theXMLTypePackage.getBoolean(), "transacted", "", 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(jmsProvidesEClass, JmsProvides.class, "JmsProvides", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getJmsProvides_MaxActive(), theXMLTypePackage.getInt(), "maxActive", "0", 0, 1, JmsProvides.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getJmsProvides_MaxWait(), theXMLTypePackage.getInt(), "maxWait", "0", 0, 1, JmsProvides.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getJmsProvides_MaxIdle(), theXMLTypePackage.getInt(), "maxIdle", "0", 0, 1, JmsProvides.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getJmsProvides_TimeBetweenEvictionRunsMilles(), theXMLTypePackage.getInt(), "timeBetweenEvictionRunsMilles", "0", 0, 1, JmsProvides.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getJmsProvides_MinEvictableIdleTimeMillis(), theXMLTypePackage.getInt(), "minEvictableIdleTimeMillis", "0", 0, 1, JmsProvides.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getJmsProvides_TestWhileIdle(), theXMLTypePackage.getBoolean(), "testWhileIdle", "", 0, 1, JmsProvides.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(jmsConsumesEClass, JmsConsumes.class, "JmsConsumes", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

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
		  (jmsExtensionEClass, 
		   source, 
		   new String[] {
			 "name", ""
		   });		
		addAnnotation
		  (getJmsExtension_JndiProviderURL(), 
		   source, 
		   new String[] {
			 "name", "jndi-provider-url",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getJmsExtension_JndiInitialContextFactory(), 
		   source, 
		   new String[] {
			 "name", "jndi-initial-context-factory",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getJmsExtension_JndiDestinationName(), 
		   source, 
		   new String[] {
			 "name", "jndi-destination-name",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getJmsExtension_JndiConnectionFactory(), 
		   source, 
		   new String[] {
			 "name", "jndi-connection-factory",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getJmsExtension_User(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getJmsExtension_Password(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getJmsExtension_Transacted(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (jmsProvidesEClass, 
		   source, 
		   new String[] {
			 "name", ""
		   });		
		addAnnotation
		  (getJmsProvides_MaxActive(), 
		   source, 
		   new String[] {
			 "name", "max-active",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getJmsProvides_MaxWait(), 
		   source, 
		   new String[] {
			 "name", "max-wait",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getJmsProvides_MaxIdle(), 
		   source, 
		   new String[] {
			 "name", "max-idle",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getJmsProvides_TimeBetweenEvictionRunsMilles(), 
		   source, 
		   new String[] {
			 "name", "time-between-eviction-runs-millis",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getJmsProvides_MinEvictableIdleTimeMillis(), 
		   source, 
		   new String[] {
			 "name", "min-evictable-idle-time-millis",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getJmsProvides_TestWhileIdle(), 
		   source, 
		   new String[] {
			 "name", "test-while-idle",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (jmsConsumesEClass, 
		   source, 
		   new String[] {
			 "name", ""
		   });
	}

} //JmsPackageImpl
