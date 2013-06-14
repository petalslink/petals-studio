/**
 * Copyright (c) 2012-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 */
package com.ebmwebsourcing.petals.services.eip.eip.impl;

import com.ebmwebsourcing.petals.services.eip.eip.EipFactory;
import com.ebmwebsourcing.petals.services.eip.eip.EipPackage;
import com.ebmwebsourcing.petals.services.eip.eip.EipProvides;
import com.ebmwebsourcing.petals.services.eip.eip.EipType;
import com.ebmwebsourcing.petals.services.eip.eip.WiretapWay;

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
public class EipPackageImpl extends EPackageImpl implements EipPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eipProvidesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum eipTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum wiretapWayEEnum = null;

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
	 * @see com.ebmwebsourcing.petals.services.eip.eip.EipPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private EipPackageImpl() {
		super(eNS_URI, EipFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link EipPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static EipPackage init() {
		if (isInited) return (EipPackage)EPackage.Registry.INSTANCE.getEPackage(EipPackage.eNS_URI);

		// Obtain or create and register package
		EipPackageImpl theEipPackage = (EipPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof EipPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new EipPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		JbiPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theEipPackage.createPackageContents();

		// Initialize created meta-data
		theEipPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theEipPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(EipPackage.eNS_URI, theEipPackage);
		return theEipPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEipProvides() {
		return eipProvidesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEipProvides_Eip() {
		return (EAttribute)eipProvidesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEipProvides_Test() {
		return (EAttribute)eipProvidesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEipProvides_TestOperation() {
		return (EAttribute)eipProvidesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEipProvides_WiretapWay() {
		return (EAttribute)eipProvidesEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEipProvides_AggregatorCorrelation() {
		return (EAttribute)eipProvidesEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEipProvides_FaultRobust() {
		return (EAttribute)eipProvidesEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEipProvides_ExceptionRobust() {
		return (EAttribute)eipProvidesEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEipProvides_FaultToException() {
		return (EAttribute)eipProvidesEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEipProvides_AttachmentMode() {
		return (EAttribute)eipProvidesEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getEipType() {
		return eipTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getWiretapWay() {
		return wiretapWayEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EipFactory getEipFactory() {
		return (EipFactory)getEFactoryInstance();
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
		eipProvidesEClass = createEClass(EIP_PROVIDES);
		createEAttribute(eipProvidesEClass, EIP_PROVIDES__EIP);
		createEAttribute(eipProvidesEClass, EIP_PROVIDES__TEST);
		createEAttribute(eipProvidesEClass, EIP_PROVIDES__TEST_OPERATION);
		createEAttribute(eipProvidesEClass, EIP_PROVIDES__WIRETAP_WAY);
		createEAttribute(eipProvidesEClass, EIP_PROVIDES__AGGREGATOR_CORRELATION);
		createEAttribute(eipProvidesEClass, EIP_PROVIDES__FAULT_ROBUST);
		createEAttribute(eipProvidesEClass, EIP_PROVIDES__EXCEPTION_ROBUST);
		createEAttribute(eipProvidesEClass, EIP_PROVIDES__FAULT_TO_EXCEPTION);
		createEAttribute(eipProvidesEClass, EIP_PROVIDES__ATTACHMENT_MODE);

		// Create enums
		eipTypeEEnum = createEEnum(EIP_TYPE);
		wiretapWayEEnum = createEEnum(WIRETAP_WAY);
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
		eipProvidesEClass.getESuperTypes().add(theJbiPackage.getProvides());

		// Initialize classes and features; add operations and parameters
		initEClass(eipProvidesEClass, EipProvides.class, "EipProvides", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEipProvides_Eip(), this.getEipType(), "eip", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEipProvides_Test(), theXMLTypePackage.getString(), "test", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEipProvides_TestOperation(), theXMLTypePackage.getString(), "testOperation", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEipProvides_WiretapWay(), this.getWiretapWay(), "wiretapWay", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEipProvides_AggregatorCorrelation(), theXMLTypePackage.getString(), "aggregatorCorrelation", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEipProvides_FaultRobust(), theXMLTypePackage.getBoolean(), "faultRobust", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEipProvides_ExceptionRobust(), theXMLTypePackage.getBoolean(), "exceptionRobust", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEipProvides_FaultToException(), theXMLTypePackage.getBoolean(), "faultToException", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEipProvides_AttachmentMode(), theXMLTypePackage.getBoolean(), "attachmentMode", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(eipTypeEEnum, EipType.class, "EipType");
		addEEnumLiteral(eipTypeEEnum, EipType.ROUTING_SLIP);
		addEEnumLiteral(eipTypeEEnum, EipType.ROUTER);
		addEEnumLiteral(eipTypeEEnum, EipType.DYNAMIC_ROUTER);
		addEEnumLiteral(eipTypeEEnum, EipType.BRIDGE);
		addEEnumLiteral(eipTypeEEnum, EipType.SPLITTER);
		addEEnumLiteral(eipTypeEEnum, EipType.AGGREGATOR);
		addEEnumLiteral(eipTypeEEnum, EipType.DISPATCHER);
		addEEnumLiteral(eipTypeEEnum, EipType.SCATTER_GATHER);
		addEEnumLiteral(eipTypeEEnum, EipType.WIRE_TAP);

		initEEnum(wiretapWayEEnum, WiretapWay.class, "WiretapWay");
		addEEnumLiteral(wiretapWayEEnum, WiretapWay.REQUEST_ON_RESPONSE);
		addEEnumLiteral(wiretapWayEEnum, WiretapWay.REQUEST_RESPONSE);
		addEEnumLiteral(wiretapWayEEnum, WiretapWay.RESPONSE);
		addEEnumLiteral(wiretapWayEEnum, WiretapWay.REQUEST);

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
		  (eipProvidesEClass, 
		   source, 
		   new String[] {
			 "name", ""
		   });		
		addAnnotation
		  (getEipProvides_Eip(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getEipProvides_Test(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getEipProvides_TestOperation(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0",
			 "name", "test-operation"
		   });		
		addAnnotation
		  (getEipProvides_WiretapWay(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0",
			 "name", "wiretap-way"
		   });		
		addAnnotation
		  (getEipProvides_AggregatorCorrelation(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0",
			 "name", "aggregator-correlation"
		   });		
		addAnnotation
		  (getEipProvides_FaultRobust(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0",
			 "name", "fault-robust"
		   });		
		addAnnotation
		  (getEipProvides_ExceptionRobust(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0",
			 "name", "exception-robust"
		   });		
		addAnnotation
		  (getEipProvides_FaultToException(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0",
			 "name", "fault-to-exception"
		   });		
		addAnnotation
		  (getEipProvides_AttachmentMode(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0",
			 "name", "attachment-mode"
		   });
	}

} //EipPackageImpl
