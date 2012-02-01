/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl;

import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Consumes;
import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides;
import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDKService;
import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Factory;
import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;

import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Mep;
import com.sun.java.xml.ns.jbi.JbiPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Cdk5PackageImpl extends EPackageImpl implements Cdk5Package {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cdkServiceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cdk5ProvidesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cdk5ConsumesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum mepEEnum = null;

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
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Cdk5PackageImpl() {
		super(eNS_URI, Cdk5Factory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link Cdk5Package#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Cdk5Package init() {
		if (isInited) return (Cdk5Package)EPackage.Registry.INSTANCE.getEPackage(Cdk5Package.eNS_URI);

		// Obtain or create and register package
		Cdk5PackageImpl theCdk5Package = (Cdk5PackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Cdk5PackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Cdk5PackageImpl());

		isInited = true;

		// Initialize simple dependencies
		JbiPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theCdk5Package.createPackageContents();

		// Initialize created meta-data
		theCdk5Package.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCdk5Package.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Cdk5Package.eNS_URI, theCdk5Package);
		return theCdk5Package;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCDKService() {
		return cdkServiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCDKService_CdkExtContainer() {
		return (EAttribute)cdkServiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCDKService_Timeout() {
		return (EAttribute)cdkServiceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCDKService_SuInterceptors() {
		return (EAttribute)cdkServiceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCDK5Provides() {
		return cdk5ProvidesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCDK5Provides_ValidateWsdl() {
		return (EAttribute)cdk5ProvidesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCDK5Provides_ForwardSecuritySubject() {
		return (EAttribute)cdk5ProvidesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCDK5Provides_ForwardMessageProperties() {
		return (EAttribute)cdk5ProvidesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCDK5Provides_ForwardAttachments() {
		return (EAttribute)cdk5ProvidesEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCDK5Provides_Wsdl() {
		return (EAttribute)cdk5ProvidesEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCDK5Provides_RetryPolicy() {
		return (EAttribute)cdk5ProvidesEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCDK5Consumes() {
		return cdk5ConsumesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCDK5Consumes_Operation() {
		return (EAttribute)cdk5ConsumesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCDK5Consumes_Mep() {
		return (EAttribute)cdk5ConsumesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getMep() {
		return mepEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Cdk5Factory getCdk5Factory() {
		return (Cdk5Factory)getEFactoryInstance();
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
		cdkServiceEClass = createEClass(CDK_SERVICE);
		createEAttribute(cdkServiceEClass, CDK_SERVICE__CDK_EXT_CONTAINER);
		createEAttribute(cdkServiceEClass, CDK_SERVICE__TIMEOUT);
		createEAttribute(cdkServiceEClass, CDK_SERVICE__SU_INTERCEPTORS);

		cdk5ProvidesEClass = createEClass(CDK5_PROVIDES);
		createEAttribute(cdk5ProvidesEClass, CDK5_PROVIDES__VALIDATE_WSDL);
		createEAttribute(cdk5ProvidesEClass, CDK5_PROVIDES__FORWARD_SECURITY_SUBJECT);
		createEAttribute(cdk5ProvidesEClass, CDK5_PROVIDES__FORWARD_MESSAGE_PROPERTIES);
		createEAttribute(cdk5ProvidesEClass, CDK5_PROVIDES__FORWARD_ATTACHMENTS);
		createEAttribute(cdk5ProvidesEClass, CDK5_PROVIDES__WSDL);
		createEAttribute(cdk5ProvidesEClass, CDK5_PROVIDES__RETRY_POLICY);

		cdk5ConsumesEClass = createEClass(CDK5_CONSUMES);
		createEAttribute(cdk5ConsumesEClass, CDK5_CONSUMES__OPERATION);
		createEAttribute(cdk5ConsumesEClass, CDK5_CONSUMES__MEP);

		// Create enums
		mepEEnum = createEEnum(MEP);
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
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		cdkServiceEClass.getESuperTypes().add(theJbiPackage.getAbstractEndpoint());
		cdk5ProvidesEClass.getESuperTypes().add(this.getCDKService());
		cdk5ProvidesEClass.getESuperTypes().add(theJbiPackage.getProvides());
		cdk5ConsumesEClass.getESuperTypes().add(this.getCDKService());
		cdk5ConsumesEClass.getESuperTypes().add(theJbiPackage.getConsumes());

		// Initialize classes and features; add operations and parameters
		initEClass(cdkServiceEClass, CDKService.class, "CDKService", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCDKService_CdkExtContainer(), theEcorePackage.getEFeatureMapEntry(), "cdkExtContainer", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getCDKService_Timeout(), ecorePackage.getEInt(), "timeout", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getCDKService_SuInterceptors(), ecorePackage.getEString(), "suInterceptors", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cdk5ProvidesEClass, CDK5Provides.class, "CDK5Provides", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCDK5Provides_ValidateWsdl(), theXMLTypePackage.getBoolean(), "validateWsdl", null, 0, 1, CDK5Provides.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getCDK5Provides_ForwardSecuritySubject(), theXMLTypePackage.getBoolean(), "forwardSecuritySubject", null, 0, 1, CDK5Provides.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getCDK5Provides_ForwardMessageProperties(), theXMLTypePackage.getBoolean(), "forwardMessageProperties", null, 0, 1, CDK5Provides.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getCDK5Provides_ForwardAttachments(), theXMLTypePackage.getBoolean(), "forwardAttachments", null, 0, 1, CDK5Provides.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getCDK5Provides_Wsdl(), ecorePackage.getEString(), "wsdl", null, 0, 1, CDK5Provides.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getCDK5Provides_RetryPolicy(), ecorePackage.getEString(), "retryPolicy", null, 0, 1, CDK5Provides.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(cdk5ConsumesEClass, CDK5Consumes.class, "CDK5Consumes", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCDK5Consumes_Operation(), theXMLTypePackage.getString(), "operation", null, 0, 1, CDK5Consumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getCDK5Consumes_Mep(), this.getMep(), "mep", null, 0, 1, CDK5Consumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(mepEEnum, Mep.class, "Mep");
		addEEnumLiteral(mepEEnum, Mep.IN_ONLY);
		addEEnumLiteral(mepEEnum, Mep.IN_OPTIONAL_OUT);
		addEEnumLiteral(mepEEnum, Mep.IN_OUT);
		addEEnumLiteral(mepEEnum, Mep.ROBUST_IN_ONLY);

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
		  (cdkServiceEClass, 
		   source, 
		   new String[] {
			 "name", ""
		   });		
		addAnnotation
		  (getCDKService_CdkExtContainer(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "wildcards", "http://petals.ow2.org/components/extensions/version-5",
			 "processing", "skip",
			 "name", "cdkExtContainer",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getCDKService_Timeout(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "http://petals.ow2.org/components/extensions/version-5",
			 "name", "timeout",
			 "group", "#cdkExtContainer"
		   });		
		addAnnotation
		  (getCDKService_SuInterceptors(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "http://petals.ow2.org/components/extensions/version-5",
			 "name", "suInterceptors",
			 "group", "#cdkExtContainer"
		   });		
		addAnnotation
		  (cdk5ProvidesEClass, 
		   source, 
		   new String[] {
			 "name", ""
		   });		
		addAnnotation
		  (getCDK5Provides_ValidateWsdl(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "http://petals.ow2.org/components/extensions/version-5",
			 "name", "validate-wsdl",
			 "group", "#cdkExtContainer"
		   });		
		addAnnotation
		  (getCDK5Provides_ForwardSecuritySubject(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "http://petals.ow2.org/components/extensions/version-5",
			 "name", "forward-security-subject",
			 "group", "#cdkExtContainer"
		   });		
		addAnnotation
		  (getCDK5Provides_ForwardMessageProperties(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "http://petals.ow2.org/components/extensions/version-5",
			 "name", "forward-message-properties",
			 "group", "#cdkExtContainer"
		   });		
		addAnnotation
		  (getCDK5Provides_ForwardAttachments(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "http://petals.ow2.org/components/extensions/version-5",
			 "name", "forward-attachments",
			 "group", "#cdkExtContainer"
		   });		
		addAnnotation
		  (getCDK5Provides_Wsdl(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "http://petals.ow2.org/components/extensions/version-5",
			 "name", "wsdl",
			 "group", "#cdkExtContainer"
		   });		
		addAnnotation
		  (getCDK5Provides_RetryPolicy(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "http://petals.ow2.org/components/extensions/version-5",
			 "name", "validate-wsdl",
			 "group", "#cdkExtContainer"
		   });		
		addAnnotation
		  (cdk5ConsumesEClass, 
		   source, 
		   new String[] {
			 "name", ""
		   });		
		addAnnotation
		  (getCDK5Consumes_Operation(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "http://petals.ow2.org/components/extensions/version-5",
			 "name", "operation",
			 "group", "#cdkExtContainer"
		   });		
		addAnnotation
		  (getCDK5Consumes_Mep(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "http://petals.ow2.org/components/extensions/version-5",
			 "group", "#cdkExtContainer"
		   });
	}

} //Cdk5PackageImpl
