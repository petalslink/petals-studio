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
package com.ebmwebsourcing.petals.services.sftp.sftp;

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
 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpFactory
 * @model kind="package"
 * @generated
 */
public interface SftpPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "sftp";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://petals.ow2.org/components/sftp/version-1";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "sftp";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SftpPackage eINSTANCE = com.ebmwebsourcing.petals.services.sftp.sftp.impl.SftpPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.sftp.sftp.impl.SftpProvidesImpl <em>Provides</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.impl.SftpProvidesImpl
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.impl.SftpPackageImpl#getSftpProvides()
	 * @generated
	 */
	int SFTP_PROVIDES = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SFTP_PROVIDES__GROUP = JbiPackage.PROVIDES__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SFTP_PROVIDES__OTHER = JbiPackage.PROVIDES__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SFTP_PROVIDES__LOCAL = JbiPackage.PROVIDES__LOCAL;

	/**
	 * The feature id for the '<em><b>Endpoint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SFTP_PROVIDES__ENDPOINT_NAME = JbiPackage.PROVIDES__ENDPOINT_NAME;

	/**
	 * The feature id for the '<em><b>Interface Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SFTP_PROVIDES__INTERFACE_NAME = JbiPackage.PROVIDES__INTERFACE_NAME;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SFTP_PROVIDES__SERVICE_NAME = JbiPackage.PROVIDES__SERVICE_NAME;

	/**
	 * The feature id for the '<em><b>Folder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SFTP_PROVIDES__FOLDER = JbiPackage.PROVIDES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Server</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SFTP_PROVIDES__SERVER = JbiPackage.PROVIDES_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Overwrite</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SFTP_PROVIDES__OVERWRITE = JbiPackage.PROVIDES_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Passphrase</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SFTP_PROVIDES__PASSPHRASE = JbiPackage.PROVIDES_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SFTP_PROVIDES__PASSWORD = JbiPackage.PROVIDES_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SFTP_PROVIDES__PORT = JbiPackage.PROVIDES_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Privatekey</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SFTP_PROVIDES__PRIVATEKEY = JbiPackage.PROVIDES_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SFTP_PROVIDES__USER = JbiPackage.PROVIDES_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Max Idle Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SFTP_PROVIDES__MAX_IDLE_TIME = JbiPackage.PROVIDES_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Max Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SFTP_PROVIDES__MAX_CONNECTION = JbiPackage.PROVIDES_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Provides</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SFTP_PROVIDES_FEATURE_COUNT = JbiPackage.PROVIDES_FEATURE_COUNT + 10;


	/**
	 * Returns the meta object for class '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides <em>Provides</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Provides</em>'.
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides
	 * @generated
	 */
	EClass getSftpProvides();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Folder</em>'.
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getFolder()
	 * @see #getSftpProvides()
	 * @generated
	 */
	EAttribute getSftpProvides_Folder();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getServer <em>Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Server</em>'.
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getServer()
	 * @see #getSftpProvides()
	 * @generated
	 */
	EAttribute getSftpProvides_Server();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getMaxIdleTime <em>Max Idle Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Idle Time</em>'.
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getMaxIdleTime()
	 * @see #getSftpProvides()
	 * @generated
	 */
	EAttribute getSftpProvides_MaxIdleTime();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getMaxConnection <em>Max Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Connection</em>'.
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getMaxConnection()
	 * @see #getSftpProvides()
	 * @generated
	 */
	EAttribute getSftpProvides_MaxConnection();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#isOverwrite <em>Overwrite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Overwrite</em>'.
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#isOverwrite()
	 * @see #getSftpProvides()
	 * @generated
	 */
	EAttribute getSftpProvides_Overwrite();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getPassphrase <em>Passphrase</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Passphrase</em>'.
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getPassphrase()
	 * @see #getSftpProvides()
	 * @generated
	 */
	EAttribute getSftpProvides_Passphrase();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getPassword <em>Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Password</em>'.
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getPassword()
	 * @see #getSftpProvides()
	 * @generated
	 */
	EAttribute getSftpProvides_Password();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Port</em>'.
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getPort()
	 * @see #getSftpProvides()
	 * @generated
	 */
	EAttribute getSftpProvides_Port();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getPrivatekey <em>Privatekey</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Privatekey</em>'.
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getPrivatekey()
	 * @see #getSftpProvides()
	 * @generated
	 */
	EAttribute getSftpProvides_Privatekey();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User</em>'.
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getUser()
	 * @see #getSftpProvides()
	 * @generated
	 */
	EAttribute getSftpProvides_User();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SftpFactory getSftpFactory();

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
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.sftp.sftp.impl.SftpProvidesImpl <em>Provides</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.sftp.sftp.impl.SftpProvidesImpl
		 * @see com.ebmwebsourcing.petals.services.sftp.sftp.impl.SftpPackageImpl#getSftpProvides()
		 * @generated
		 */
		EClass SFTP_PROVIDES = eINSTANCE.getSftpProvides();

		/**
		 * The meta object literal for the '<em><b>Folder</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SFTP_PROVIDES__FOLDER = eINSTANCE.getSftpProvides_Folder();

		/**
		 * The meta object literal for the '<em><b>Server</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SFTP_PROVIDES__SERVER = eINSTANCE.getSftpProvides_Server();

		/**
		 * The meta object literal for the '<em><b>Max Idle Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SFTP_PROVIDES__MAX_IDLE_TIME = eINSTANCE.getSftpProvides_MaxIdleTime();

		/**
		 * The meta object literal for the '<em><b>Max Connection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SFTP_PROVIDES__MAX_CONNECTION = eINSTANCE.getSftpProvides_MaxConnection();

		/**
		 * The meta object literal for the '<em><b>Overwrite</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SFTP_PROVIDES__OVERWRITE = eINSTANCE.getSftpProvides_Overwrite();

		/**
		 * The meta object literal for the '<em><b>Passphrase</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SFTP_PROVIDES__PASSPHRASE = eINSTANCE.getSftpProvides_Passphrase();

		/**
		 * The meta object literal for the '<em><b>Password</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SFTP_PROVIDES__PASSWORD = eINSTANCE.getSftpProvides_Password();

		/**
		 * The meta object literal for the '<em><b>Port</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SFTP_PROVIDES__PORT = eINSTANCE.getSftpProvides_Port();

		/**
		 * The meta object literal for the '<em><b>Privatekey</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SFTP_PROVIDES__PRIVATEKEY = eINSTANCE.getSftpProvides_Privatekey();

		/**
		 * The meta object literal for the '<em><b>User</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SFTP_PROVIDES__USER = eINSTANCE.getSftpProvides_User();

	}

} //SftpPackage
