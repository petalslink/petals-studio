/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.ftp.ftp3;

import com.sun.java.xml.ns.jbi.JbiPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Factory
 * @model kind="package"
 * @generated
 */
public interface Ftp3Package extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ftp3";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://petals.ow2.org/components/ftp/version-3";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ftp3";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Ftp3Package eINSTANCE = com.ebmwebsourcing.petals.services.ftp.ftp3.impl.Ftp3PackageImpl.init();

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.impl.FtpProvidesImpl <em>Ftp Provides</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.impl.FtpProvidesImpl
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.impl.Ftp3PackageImpl#getFtpProvides()
	 * @generated
	 */
	int FTP_PROVIDES = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FTP_PROVIDES__GROUP = JbiPackage.PROVIDES__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FTP_PROVIDES__OTHER = JbiPackage.PROVIDES__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FTP_PROVIDES__LOCAL = JbiPackage.PROVIDES__LOCAL;

	/**
	 * The feature id for the '<em><b>Endpoint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FTP_PROVIDES__ENDPOINT_NAME = JbiPackage.PROVIDES__ENDPOINT_NAME;

	/**
	 * The feature id for the '<em><b>Interface Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FTP_PROVIDES__INTERFACE_NAME = JbiPackage.PROVIDES__INTERFACE_NAME;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FTP_PROVIDES__SERVICE_NAME = JbiPackage.PROVIDES__SERVICE_NAME;

	/**
	 * The feature id for the '<em><b>Connection Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FTP_PROVIDES__CONNECTION_MODE = JbiPackage.PROVIDES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Delete Processed Files</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FTP_PROVIDES__DELETE_PROCESSED_FILES = JbiPackage.PROVIDES_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Filename</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FTP_PROVIDES__FILENAME = JbiPackage.PROVIDES_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Folder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FTP_PROVIDES__FOLDER = JbiPackage.PROVIDES_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Overwrite</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FTP_PROVIDES__OVERWRITE = JbiPackage.PROVIDES_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FTP_PROVIDES__PASSWORD = JbiPackage.PROVIDES_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FTP_PROVIDES__PORT = JbiPackage.PROVIDES_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Server</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FTP_PROVIDES__SERVER = JbiPackage.PROVIDES_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FTP_PROVIDES__USER = JbiPackage.PROVIDES_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Transfer Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FTP_PROVIDES__TRANSFER_TYPE = JbiPackage.PROVIDES_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Encoding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FTP_PROVIDES__ENCODING = JbiPackage.PROVIDES_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Max Idle Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FTP_PROVIDES__MAX_IDLE_TIME = JbiPackage.PROVIDES_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Max Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FTP_PROVIDES__MAX_CONNECTION = JbiPackage.PROVIDES_FEATURE_COUNT + 12;

	/**
	 * The number of structural features of the '<em>Ftp Provides</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FTP_PROVIDES_FEATURE_COUNT = JbiPackage.PROVIDES_FEATURE_COUNT + 13;

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.ConnectionType <em>Connection Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.ConnectionType
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.impl.Ftp3PackageImpl#getConnectionType()
	 * @generated
	 */
	int CONNECTION_TYPE = 1;

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.TransferType <em>Transfer Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.TransferType
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.impl.Ftp3PackageImpl#getTransferType()
	 * @generated
	 */
	int TRANSFER_TYPE = 2;


	/**
	 * Returns the meta object for class '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides <em>Ftp Provides</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ftp Provides</em>'.
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides
	 * @generated
	 */
	EClass getFtpProvides();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getConnectionMode <em>Connection Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connection Mode</em>'.
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getConnectionMode()
	 * @see #getFtpProvides()
	 * @generated
	 */
	EAttribute getFtpProvides_ConnectionMode();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#isDeleteProcessedFiles <em>Delete Processed Files</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Delete Processed Files</em>'.
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#isDeleteProcessedFiles()
	 * @see #getFtpProvides()
	 * @generated
	 */
	EAttribute getFtpProvides_DeleteProcessedFiles();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getFilename <em>Filename</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Filename</em>'.
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getFilename()
	 * @see #getFtpProvides()
	 * @generated
	 */
	EAttribute getFtpProvides_Filename();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Folder</em>'.
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getFolder()
	 * @see #getFtpProvides()
	 * @generated
	 */
	EAttribute getFtpProvides_Folder();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#isOverwrite <em>Overwrite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Overwrite</em>'.
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#isOverwrite()
	 * @see #getFtpProvides()
	 * @generated
	 */
	EAttribute getFtpProvides_Overwrite();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getPassword <em>Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Password</em>'.
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getPassword()
	 * @see #getFtpProvides()
	 * @generated
	 */
	EAttribute getFtpProvides_Password();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Port</em>'.
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getPort()
	 * @see #getFtpProvides()
	 * @generated
	 */
	EAttribute getFtpProvides_Port();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getServer <em>Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Server</em>'.
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getServer()
	 * @see #getFtpProvides()
	 * @generated
	 */
	EAttribute getFtpProvides_Server();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User</em>'.
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getUser()
	 * @see #getFtpProvides()
	 * @generated
	 */
	EAttribute getFtpProvides_User();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getTransferType <em>Transfer Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transfer Type</em>'.
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getTransferType()
	 * @see #getFtpProvides()
	 * @generated
	 */
	EAttribute getFtpProvides_TransferType();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getEncoding <em>Encoding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Encoding</em>'.
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getEncoding()
	 * @see #getFtpProvides()
	 * @generated
	 */
	EAttribute getFtpProvides_Encoding();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getMaxIdleTime <em>Max Idle Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Idle Time</em>'.
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getMaxIdleTime()
	 * @see #getFtpProvides()
	 * @generated
	 */
	EAttribute getFtpProvides_MaxIdleTime();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getMaxConnection <em>Max Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Connection</em>'.
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getMaxConnection()
	 * @see #getFtpProvides()
	 * @generated
	 */
	EAttribute getFtpProvides_MaxConnection();

	/**
	 * Returns the meta object for enum '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.ConnectionType <em>Connection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Connection Type</em>'.
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.ConnectionType
	 * @generated
	 */
	EEnum getConnectionType();

	/**
	 * Returns the meta object for enum '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.TransferType <em>Transfer Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Transfer Type</em>'.
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.TransferType
	 * @generated
	 */
	EEnum getTransferType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Ftp3Factory getFtp3Factory();

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
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.impl.FtpProvidesImpl <em>Ftp Provides</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.impl.FtpProvidesImpl
		 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.impl.Ftp3PackageImpl#getFtpProvides()
		 * @generated
		 */
		EClass FTP_PROVIDES = eINSTANCE.getFtpProvides();

		/**
		 * The meta object literal for the '<em><b>Connection Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FTP_PROVIDES__CONNECTION_MODE = eINSTANCE.getFtpProvides_ConnectionMode();

		/**
		 * The meta object literal for the '<em><b>Delete Processed Files</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FTP_PROVIDES__DELETE_PROCESSED_FILES = eINSTANCE.getFtpProvides_DeleteProcessedFiles();

		/**
		 * The meta object literal for the '<em><b>Filename</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FTP_PROVIDES__FILENAME = eINSTANCE.getFtpProvides_Filename();

		/**
		 * The meta object literal for the '<em><b>Folder</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FTP_PROVIDES__FOLDER = eINSTANCE.getFtpProvides_Folder();

		/**
		 * The meta object literal for the '<em><b>Overwrite</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FTP_PROVIDES__OVERWRITE = eINSTANCE.getFtpProvides_Overwrite();

		/**
		 * The meta object literal for the '<em><b>Password</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FTP_PROVIDES__PASSWORD = eINSTANCE.getFtpProvides_Password();

		/**
		 * The meta object literal for the '<em><b>Port</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FTP_PROVIDES__PORT = eINSTANCE.getFtpProvides_Port();

		/**
		 * The meta object literal for the '<em><b>Server</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FTP_PROVIDES__SERVER = eINSTANCE.getFtpProvides_Server();

		/**
		 * The meta object literal for the '<em><b>User</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FTP_PROVIDES__USER = eINSTANCE.getFtpProvides_User();

		/**
		 * The meta object literal for the '<em><b>Transfer Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FTP_PROVIDES__TRANSFER_TYPE = eINSTANCE.getFtpProvides_TransferType();

		/**
		 * The meta object literal for the '<em><b>Encoding</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FTP_PROVIDES__ENCODING = eINSTANCE.getFtpProvides_Encoding();

		/**
		 * The meta object literal for the '<em><b>Max Idle Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FTP_PROVIDES__MAX_IDLE_TIME = eINSTANCE.getFtpProvides_MaxIdleTime();

		/**
		 * The meta object literal for the '<em><b>Max Connection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FTP_PROVIDES__MAX_CONNECTION = eINSTANCE.getFtpProvides_MaxConnection();

		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.ConnectionType <em>Connection Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.ConnectionType
		 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.impl.Ftp3PackageImpl#getConnectionType()
		 * @generated
		 */
		EEnum CONNECTION_TYPE = eINSTANCE.getConnectionType();

		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.TransferType <em>Transfer Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.TransferType
		 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.impl.Ftp3PackageImpl#getTransferType()
		 * @generated
		 */
		EEnum TRANSFER_TYPE = eINSTANCE.getTransferType();

	}

} //Ftp3Package
