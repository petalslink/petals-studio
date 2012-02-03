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
package com.ebmwebsourcing.petals.services.sql.sql.impl;

import com.ebmwebsourcing.petals.services.sql.sql.SqlFactory;
import com.ebmwebsourcing.petals.services.sql.sql.SqlPackage;
import com.ebmwebsourcing.petals.services.sql.sql.SqlProvides;

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
public class SqlPackageImpl extends EPackageImpl implements SqlPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sqlProvidesEClass = null;

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
	 * @see com.ebmwebsourcing.petals.services.sql.sql.SqlPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SqlPackageImpl() {
		super(eNS_URI, SqlFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link SqlPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SqlPackage init() {
		if (isInited) return (SqlPackage)EPackage.Registry.INSTANCE.getEPackage(SqlPackage.eNS_URI);

		// Obtain or create and register package
		SqlPackageImpl theSqlPackage = (SqlPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SqlPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SqlPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		JbiPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theSqlPackage.createPackageContents();

		// Initialize created meta-data
		theSqlPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSqlPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SqlPackage.eNS_URI, theSqlPackage);
		return theSqlPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSqlProvides() {
		return sqlProvidesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSqlProvides_Url() {
		return (EAttribute)sqlProvidesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSqlProvides_User() {
		return (EAttribute)sqlProvidesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSqlProvides_Password() {
		return (EAttribute)sqlProvidesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSqlProvides_Driver() {
		return (EAttribute)sqlProvidesEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSqlProvides_MaxActive() {
		return (EAttribute)sqlProvidesEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSqlProvides_MaxIdle() {
		return (EAttribute)sqlProvidesEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSqlProvides_MinIdle() {
		return (EAttribute)sqlProvidesEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSqlProvides_MaxWait() {
		return (EAttribute)sqlProvidesEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSqlProvides_TimeBetweenEvictionRunsMillis() {
		return (EAttribute)sqlProvidesEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSqlProvides_Metadata() {
		return (EAttribute)sqlProvidesEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSqlProvides_StoredProcedure() {
		return (EAttribute)sqlProvidesEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SqlFactory getSqlFactory() {
		return (SqlFactory)getEFactoryInstance();
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
		sqlProvidesEClass = createEClass(SQL_PROVIDES);
		createEAttribute(sqlProvidesEClass, SQL_PROVIDES__URL);
		createEAttribute(sqlProvidesEClass, SQL_PROVIDES__USER);
		createEAttribute(sqlProvidesEClass, SQL_PROVIDES__PASSWORD);
		createEAttribute(sqlProvidesEClass, SQL_PROVIDES__DRIVER);
		createEAttribute(sqlProvidesEClass, SQL_PROVIDES__MAX_ACTIVE);
		createEAttribute(sqlProvidesEClass, SQL_PROVIDES__MAX_IDLE);
		createEAttribute(sqlProvidesEClass, SQL_PROVIDES__MIN_IDLE);
		createEAttribute(sqlProvidesEClass, SQL_PROVIDES__MAX_WAIT);
		createEAttribute(sqlProvidesEClass, SQL_PROVIDES__TIME_BETWEEN_EVICTION_RUNS_MILLIS);
		createEAttribute(sqlProvidesEClass, SQL_PROVIDES__METADATA);
		createEAttribute(sqlProvidesEClass, SQL_PROVIDES__STORED_PROCEDURE);
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
		sqlProvidesEClass.getESuperTypes().add(theJbiPackage.getProvides());

		// Initialize classes and features; add operations and parameters
		initEClass(sqlProvidesEClass, SqlProvides.class, "SqlProvides", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSqlProvides_Url(), theXMLTypePackage.getString(), "url", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSqlProvides_User(), theXMLTypePackage.getString(), "user", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSqlProvides_Password(), theXMLTypePackage.getString(), "password", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSqlProvides_Driver(), theXMLTypePackage.getString(), "driver", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSqlProvides_MaxActive(), theXMLTypePackage.getInt(), "maxActive", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSqlProvides_MaxIdle(), theXMLTypePackage.getInt(), "maxIdle", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSqlProvides_MinIdle(), theXMLTypePackage.getInt(), "minIdle", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSqlProvides_MaxWait(), theXMLTypePackage.getInt(), "maxWait", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSqlProvides_TimeBetweenEvictionRunsMillis(), theXMLTypePackage.getInt(), "timeBetweenEvictionRunsMillis", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSqlProvides_Metadata(), theXMLTypePackage.getBoolean(), "metadata", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSqlProvides_StoredProcedure(), theXMLTypePackage.getString(), "storedProcedure", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

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
		  (sqlProvidesEClass, 
		   source, 
		   new String[] {
			 "name", ""
		   });		
		addAnnotation
		  (getSqlProvides_Url(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "group", "#group:0",
			 "kind", "element"
		   });		
		addAnnotation
		  (getSqlProvides_User(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "group", "#group:0",
			 "kind", "element"
		   });		
		addAnnotation
		  (getSqlProvides_Password(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "group", "#group:0",
			 "kind", "element"
		   });		
		addAnnotation
		  (getSqlProvides_Driver(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "group", "#group:0",
			 "kind", "element"
		   });		
		addAnnotation
		  (getSqlProvides_MaxActive(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "group", "#group:0",
			 "kind", "element"
		   });		
		addAnnotation
		  (getSqlProvides_MaxIdle(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "group", "#group:0",
			 "kind", "element"
		   });		
		addAnnotation
		  (getSqlProvides_MinIdle(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "group", "#group:0",
			 "kind", "element"
		   });		
		addAnnotation
		  (getSqlProvides_MaxWait(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "group", "#group:0",
			 "kind", "element"
		   });		
		addAnnotation
		  (getSqlProvides_TimeBetweenEvictionRunsMillis(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "group", "#group:0",
			 "kind", "element"
		   });		
		addAnnotation
		  (getSqlProvides_Metadata(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "group", "#group:0",
			 "kind", "element"
		   });		
		addAnnotation
		  (getSqlProvides_StoredProcedure(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "group", "#group:0",
			 "kind", "element"
		   });
	}

} //SqlPackageImpl
