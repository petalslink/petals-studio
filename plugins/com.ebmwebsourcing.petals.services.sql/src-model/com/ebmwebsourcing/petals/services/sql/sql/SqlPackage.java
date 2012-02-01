/**
 * Copyright (c) 2011, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 */
package com.ebmwebsourcing.petals.services.sql.sql;

import com.sun.java.xml.ns.jbi.JbiPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.ebmwebsourcing.petals.services.sql.sql.SqlFactory
 * @model kind="package"
 * @generated
 */
public interface SqlPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "sql";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://petals.ow2.org/components/sql/version-1";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "sql";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SqlPackage eINSTANCE = com.ebmwebsourcing.petals.services.sql.sql.impl.SqlPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.sql.sql.impl.SqlProvidesImpl <em>Provides</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.sql.sql.impl.SqlProvidesImpl
	 * @see com.ebmwebsourcing.petals.services.sql.sql.impl.SqlPackageImpl#getSqlProvides()
	 * @generated
	 */
	int SQL_PROVIDES = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_PROVIDES__GROUP = JbiPackage.PROVIDES__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_PROVIDES__OTHER = JbiPackage.PROVIDES__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_PROVIDES__LOCAL = JbiPackage.PROVIDES__LOCAL;

	/**
	 * The feature id for the '<em><b>Endpoint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_PROVIDES__ENDPOINT_NAME = JbiPackage.PROVIDES__ENDPOINT_NAME;

	/**
	 * The feature id for the '<em><b>Interface Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_PROVIDES__INTERFACE_NAME = JbiPackage.PROVIDES__INTERFACE_NAME;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_PROVIDES__SERVICE_NAME = JbiPackage.PROVIDES__SERVICE_NAME;

	/**
	 * The feature id for the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_PROVIDES__URL = JbiPackage.PROVIDES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_PROVIDES__USER = JbiPackage.PROVIDES_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_PROVIDES__PASSWORD = JbiPackage.PROVIDES_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Driver</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_PROVIDES__DRIVER = JbiPackage.PROVIDES_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Max Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_PROVIDES__MAX_ACTIVE = JbiPackage.PROVIDES_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Max Idle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_PROVIDES__MAX_IDLE = JbiPackage.PROVIDES_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Min Idle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_PROVIDES__MIN_IDLE = JbiPackage.PROVIDES_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Max Wait</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_PROVIDES__MAX_WAIT = JbiPackage.PROVIDES_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Time Between Eviction Runs Millis</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_PROVIDES__TIME_BETWEEN_EVICTION_RUNS_MILLIS = JbiPackage.PROVIDES_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_PROVIDES__METADATA = JbiPackage.PROVIDES_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Stored Procedure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_PROVIDES__STORED_PROCEDURE = JbiPackage.PROVIDES_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Provides</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_PROVIDES_FEATURE_COUNT = JbiPackage.PROVIDES_FEATURE_COUNT + 11;


	/**
	 * Returns the meta object for class '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides <em>Provides</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Provides</em>'.
	 * @see com.ebmwebsourcing.petals.services.sql.sql.SqlProvides
	 * @generated
	 */
	EClass getSqlProvides();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getUrl <em>Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Url</em>'.
	 * @see com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getUrl()
	 * @see #getSqlProvides()
	 * @generated
	 */
	EAttribute getSqlProvides_Url();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User</em>'.
	 * @see com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getUser()
	 * @see #getSqlProvides()
	 * @generated
	 */
	EAttribute getSqlProvides_User();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getPassword <em>Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Password</em>'.
	 * @see com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getPassword()
	 * @see #getSqlProvides()
	 * @generated
	 */
	EAttribute getSqlProvides_Password();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getDriver <em>Driver</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Driver</em>'.
	 * @see com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getDriver()
	 * @see #getSqlProvides()
	 * @generated
	 */
	EAttribute getSqlProvides_Driver();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getMaxActive <em>Max Active</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Active</em>'.
	 * @see com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getMaxActive()
	 * @see #getSqlProvides()
	 * @generated
	 */
	EAttribute getSqlProvides_MaxActive();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getMaxIdle <em>Max Idle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Idle</em>'.
	 * @see com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getMaxIdle()
	 * @see #getSqlProvides()
	 * @generated
	 */
	EAttribute getSqlProvides_MaxIdle();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getMinIdle <em>Min Idle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min Idle</em>'.
	 * @see com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getMinIdle()
	 * @see #getSqlProvides()
	 * @generated
	 */
	EAttribute getSqlProvides_MinIdle();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getMaxWait <em>Max Wait</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Wait</em>'.
	 * @see com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getMaxWait()
	 * @see #getSqlProvides()
	 * @generated
	 */
	EAttribute getSqlProvides_MaxWait();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getTimeBetweenEvictionRunsMillis <em>Time Between Eviction Runs Millis</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Between Eviction Runs Millis</em>'.
	 * @see com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getTimeBetweenEvictionRunsMillis()
	 * @see #getSqlProvides()
	 * @generated
	 */
	EAttribute getSqlProvides_TimeBetweenEvictionRunsMillis();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#isMetadata <em>Metadata</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Metadata</em>'.
	 * @see com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#isMetadata()
	 * @see #getSqlProvides()
	 * @generated
	 */
	EAttribute getSqlProvides_Metadata();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getStoredProcedure <em>Stored Procedure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stored Procedure</em>'.
	 * @see com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getStoredProcedure()
	 * @see #getSqlProvides()
	 * @generated
	 */
	EAttribute getSqlProvides_StoredProcedure();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SqlFactory getSqlFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.sql.sql.impl.SqlProvidesImpl <em>Provides</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.sql.sql.impl.SqlProvidesImpl
		 * @see com.ebmwebsourcing.petals.services.sql.sql.impl.SqlPackageImpl#getSqlProvides()
		 * @generated
		 */
		EClass SQL_PROVIDES = eINSTANCE.getSqlProvides();

		/**
		 * The meta object literal for the '<em><b>Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SQL_PROVIDES__URL = eINSTANCE.getSqlProvides_Url();

		/**
		 * The meta object literal for the '<em><b>User</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SQL_PROVIDES__USER = eINSTANCE.getSqlProvides_User();

		/**
		 * The meta object literal for the '<em><b>Password</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SQL_PROVIDES__PASSWORD = eINSTANCE.getSqlProvides_Password();

		/**
		 * The meta object literal for the '<em><b>Driver</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SQL_PROVIDES__DRIVER = eINSTANCE.getSqlProvides_Driver();

		/**
		 * The meta object literal for the '<em><b>Max Active</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SQL_PROVIDES__MAX_ACTIVE = eINSTANCE.getSqlProvides_MaxActive();

		/**
		 * The meta object literal for the '<em><b>Max Idle</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SQL_PROVIDES__MAX_IDLE = eINSTANCE.getSqlProvides_MaxIdle();

		/**
		 * The meta object literal for the '<em><b>Min Idle</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SQL_PROVIDES__MIN_IDLE = eINSTANCE.getSqlProvides_MinIdle();

		/**
		 * The meta object literal for the '<em><b>Max Wait</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SQL_PROVIDES__MAX_WAIT = eINSTANCE.getSqlProvides_MaxWait();

		/**
		 * The meta object literal for the '<em><b>Time Between Eviction Runs Millis</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SQL_PROVIDES__TIME_BETWEEN_EVICTION_RUNS_MILLIS = eINSTANCE.getSqlProvides_TimeBetweenEvictionRunsMillis();

		/**
		 * The meta object literal for the '<em><b>Metadata</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SQL_PROVIDES__METADATA = eINSTANCE.getSqlProvides_Metadata();

		/**
		 * The meta object literal for the '<em><b>Stored Procedure</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SQL_PROVIDES__STORED_PROCEDURE = eINSTANCE.getSqlProvides_StoredProcedure();

	}

} //SqlPackage
