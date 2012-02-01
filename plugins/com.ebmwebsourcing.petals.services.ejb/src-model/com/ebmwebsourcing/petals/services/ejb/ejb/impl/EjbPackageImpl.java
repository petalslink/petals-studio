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

import com.ebmwebsourcing.petals.services.ejb.ejb.EjbFactory;
import com.ebmwebsourcing.petals.services.ejb.ejb.EjbPackage;
import com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides;
import com.ebmwebsourcing.petals.services.ejb.ejb.EjbVersion;
import com.ebmwebsourcing.petals.services.ejb.ejb.XmlEngine;

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
public class EjbPackageImpl extends EPackageImpl implements EjbPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ejbProvidesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum xmlEngineEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum ejbVersionEEnum = null;

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
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private EjbPackageImpl() {
		super(eNS_URI, EjbFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link EjbPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static EjbPackage init() {
		if (isInited) return (EjbPackage)EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI);

		// Obtain or create and register package
		EjbPackageImpl theEjbPackage = (EjbPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof EjbPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new EjbPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		JbiPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theEjbPackage.createPackageContents();

		// Initialize created meta-data
		theEjbPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theEjbPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(EjbPackage.eNS_URI, theEjbPackage);
		return theEjbPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEjbProvides() {
		return ejbProvidesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEjbProvides_EjbJndiName() {
		return (EAttribute)ejbProvidesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEjbProvides_JavaNamingFactoryInitial() {
		return (EAttribute)ejbProvidesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEjbProvides_JavaNamingFactoryUrlPkgs() {
		return (EAttribute)ejbProvidesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEjbProvides_JavaNamingProviderUrl() {
		return (EAttribute)ejbProvidesEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEjbProvides_EjbVersion() {
		return (EAttribute)ejbProvidesEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEjbProvides_EjbHomeInterface() {
		return (EAttribute)ejbProvidesEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEjbProvides_SecurityName() {
		return (EAttribute)ejbProvidesEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEjbProvides_SecurityPrincipal() {
		return (EAttribute)ejbProvidesEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEjbProvides_SecurityCredencials() {
		return (EAttribute)ejbProvidesEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEjbProvides_MarshallingEngine() {
		return (EAttribute)ejbProvidesEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getXmlEngine() {
		return xmlEngineEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getEjbVersion() {
		return ejbVersionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EjbFactory getEjbFactory() {
		return (EjbFactory)getEFactoryInstance();
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
		ejbProvidesEClass = createEClass(EJB_PROVIDES);
		createEAttribute(ejbProvidesEClass, EJB_PROVIDES__EJB_JNDI_NAME);
		createEAttribute(ejbProvidesEClass, EJB_PROVIDES__JAVA_NAMING_FACTORY_INITIAL);
		createEAttribute(ejbProvidesEClass, EJB_PROVIDES__JAVA_NAMING_FACTORY_URL_PKGS);
		createEAttribute(ejbProvidesEClass, EJB_PROVIDES__JAVA_NAMING_PROVIDER_URL);
		createEAttribute(ejbProvidesEClass, EJB_PROVIDES__EJB_VERSION);
		createEAttribute(ejbProvidesEClass, EJB_PROVIDES__EJB_HOME_INTERFACE);
		createEAttribute(ejbProvidesEClass, EJB_PROVIDES__SECURITY_NAME);
		createEAttribute(ejbProvidesEClass, EJB_PROVIDES__SECURITY_PRINCIPAL);
		createEAttribute(ejbProvidesEClass, EJB_PROVIDES__SECURITY_CREDENCIALS);
		createEAttribute(ejbProvidesEClass, EJB_PROVIDES__MARSHALLING_ENGINE);

		// Create enums
		xmlEngineEEnum = createEEnum(XML_ENGINE);
		ejbVersionEEnum = createEEnum(EJB_VERSION);
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
		ejbProvidesEClass.getESuperTypes().add(theJbiPackage.getProvides());

		// Initialize classes and features; add operations and parameters
		initEClass(ejbProvidesEClass, EjbProvides.class, "EjbProvides", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEjbProvides_EjbJndiName(), theXMLTypePackage.getString(), "ejbJndiName", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getEjbProvides_JavaNamingFactoryInitial(), theXMLTypePackage.getString(), "javaNamingFactoryInitial", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getEjbProvides_JavaNamingFactoryUrlPkgs(), theXMLTypePackage.getString(), "javaNamingFactoryUrlPkgs", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getEjbProvides_JavaNamingProviderUrl(), theXMLTypePackage.getString(), "javaNamingProviderUrl", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getEjbProvides_EjbVersion(), this.getEjbVersion(), "ejbVersion", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getEjbProvides_EjbHomeInterface(), theXMLTypePackage.getString(), "ejbHomeInterface", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getEjbProvides_SecurityName(), theXMLTypePackage.getString(), "securityName", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getEjbProvides_SecurityPrincipal(), theXMLTypePackage.getString(), "securityPrincipal", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getEjbProvides_SecurityCredencials(), theXMLTypePackage.getString(), "securityCredencials", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getEjbProvides_MarshallingEngine(), this.getXmlEngine(), "marshallingEngine", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(xmlEngineEEnum, XmlEngine.class, "XmlEngine");
		addEEnumLiteral(xmlEngineEEnum, XmlEngine.JAXB);

		initEEnum(ejbVersionEEnum, EjbVersion.class, "EjbVersion");
		addEEnumLiteral(ejbVersionEEnum, EjbVersion.V20);
		addEEnumLiteral(ejbVersionEEnum, EjbVersion.V21);
		addEEnumLiteral(ejbVersionEEnum, EjbVersion.V30);
		addEEnumLiteral(ejbVersionEEnum, EjbVersion.V31);

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
		  (ejbProvidesEClass, 
		   source, 
		   new String[] {
			 "name", ""
		   });		
		addAnnotation
		  (getEjbProvides_EjbJndiName(), 
		   source, 
		   new String[] {
			 "name", "ejb.jndi.name",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getEjbProvides_JavaNamingFactoryInitial(), 
		   source, 
		   new String[] {
			 "name", "java.naming.factory.initial",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getEjbProvides_JavaNamingFactoryUrlPkgs(), 
		   source, 
		   new String[] {
			 "name", "java.naming.factory.url.pkgs",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getEjbProvides_JavaNamingProviderUrl(), 
		   source, 
		   new String[] {
			 "name", "java.naming.provider.url",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getEjbProvides_EjbVersion(), 
		   source, 
		   new String[] {
			 "name", "ejb.version",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getEjbProvides_EjbHomeInterface(), 
		   source, 
		   new String[] {
			 "name", "ejb.home.interface",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getEjbProvides_SecurityName(), 
		   source, 
		   new String[] {
			 "name", "security.name",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getEjbProvides_SecurityPrincipal(), 
		   source, 
		   new String[] {
			 "name", "security.principal",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getEjbProvides_SecurityCredencials(), 
		   source, 
		   new String[] {
			 "name", "security.credencials",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getEjbProvides_MarshallingEngine(), 
		   source, 
		   new String[] {
			 "name", "marshalling.engine",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });
	}

} //EjbPackageImpl
