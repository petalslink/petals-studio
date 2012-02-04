/**
 * Copyright (c) 2011-2012, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 */
package com.ebmwebsourcing.petals.services.sftp.sftp.impl;

import com.ebmwebsourcing.petals.services.sftp.sftp.SftpFactory;
import com.ebmwebsourcing.petals.services.sftp.sftp.SftpPackage;
import com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides;

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
public class SftpPackageImpl extends EPackageImpl implements SftpPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sftpProvidesEClass = null;

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
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SftpPackageImpl() {
		super(eNS_URI, SftpFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link SftpPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SftpPackage init() {
		if (isInited) return (SftpPackage)EPackage.Registry.INSTANCE.getEPackage(SftpPackage.eNS_URI);

		// Obtain or create and register package
		SftpPackageImpl theSftpPackage = (SftpPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SftpPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SftpPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		JbiPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theSftpPackage.createPackageContents();

		// Initialize created meta-data
		theSftpPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSftpPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SftpPackage.eNS_URI, theSftpPackage);
		return theSftpPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSftpProvides() {
		return sftpProvidesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSftpProvides_Folder() {
		return (EAttribute)sftpProvidesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSftpProvides_Server() {
		return (EAttribute)sftpProvidesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSftpProvides_MaxIdleTime() {
		return (EAttribute)sftpProvidesEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSftpProvides_MaxConnection() {
		return (EAttribute)sftpProvidesEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSftpProvides_Overwrite() {
		return (EAttribute)sftpProvidesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSftpProvides_Passphrase() {
		return (EAttribute)sftpProvidesEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSftpProvides_Password() {
		return (EAttribute)sftpProvidesEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSftpProvides_Port() {
		return (EAttribute)sftpProvidesEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSftpProvides_Privatekey() {
		return (EAttribute)sftpProvidesEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSftpProvides_User() {
		return (EAttribute)sftpProvidesEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SftpFactory getSftpFactory() {
		return (SftpFactory)getEFactoryInstance();
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
		sftpProvidesEClass = createEClass(SFTP_PROVIDES);
		createEAttribute(sftpProvidesEClass, SFTP_PROVIDES__FOLDER);
		createEAttribute(sftpProvidesEClass, SFTP_PROVIDES__SERVER);
		createEAttribute(sftpProvidesEClass, SFTP_PROVIDES__OVERWRITE);
		createEAttribute(sftpProvidesEClass, SFTP_PROVIDES__PASSPHRASE);
		createEAttribute(sftpProvidesEClass, SFTP_PROVIDES__PASSWORD);
		createEAttribute(sftpProvidesEClass, SFTP_PROVIDES__PORT);
		createEAttribute(sftpProvidesEClass, SFTP_PROVIDES__PRIVATEKEY);
		createEAttribute(sftpProvidesEClass, SFTP_PROVIDES__USER);
		createEAttribute(sftpProvidesEClass, SFTP_PROVIDES__MAX_IDLE_TIME);
		createEAttribute(sftpProvidesEClass, SFTP_PROVIDES__MAX_CONNECTION);
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
		sftpProvidesEClass.getESuperTypes().add(theJbiPackage.getProvides());

		// Initialize classes and features; add operations and parameters
		initEClass(sftpProvidesEClass, SftpProvides.class, "SftpProvides", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSftpProvides_Folder(), theXMLTypePackage.getString(), "folder", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSftpProvides_Server(), theXMLTypePackage.getString(), "server", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSftpProvides_Overwrite(), theXMLTypePackage.getBoolean(), "overwrite", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSftpProvides_Passphrase(), theXMLTypePackage.getString(), "passphrase", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSftpProvides_Password(), theXMLTypePackage.getString(), "password", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSftpProvides_Port(), theXMLTypePackage.getInt(), "port", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSftpProvides_Privatekey(), theXMLTypePackage.getString(), "privatekey", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSftpProvides_User(), theXMLTypePackage.getString(), "user", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSftpProvides_MaxIdleTime(), theXMLTypePackage.getInt(), "maxIdleTime", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSftpProvides_MaxConnection(), theXMLTypePackage.getInt(), "maxConnection", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

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
		  (sftpProvidesEClass, 
		   source, 
		   new String[] {
			 "name", ""
		   });		
		addAnnotation
		  (getSftpProvides_Folder(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSftpProvides_Server(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSftpProvides_Overwrite(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSftpProvides_Passphrase(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSftpProvides_Password(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSftpProvides_Port(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSftpProvides_Privatekey(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSftpProvides_User(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSftpProvides_MaxIdleTime(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0",
			 "name", "max-idle-time"
		   });		
		addAnnotation
		  (getSftpProvides_MaxConnection(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0",
			 "name", "max-connection"
		   });
	}

} //SftpPackageImpl
