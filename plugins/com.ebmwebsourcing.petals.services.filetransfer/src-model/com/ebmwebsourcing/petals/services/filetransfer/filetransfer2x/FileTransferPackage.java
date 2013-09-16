/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

import com.sun.java.xml.ns.jbi.JbiPackage;

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
 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferFactory
 * @model kind="package"
 * @generated
 */
public interface FileTransferPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "filetransfer2x";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://petals.ow2.org/components/filetransfer/version-2";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "filetransfer2x";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	FileTransferPackage eINSTANCE = com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferProvidesImpl <em>Provides</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferProvidesImpl
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferPackageImpl#getFileTransferProvides()
	 * @generated
	 */
	int FILE_TRANSFER_PROVIDES = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER_PROVIDES__GROUP = JbiPackage.PROVIDES__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER_PROVIDES__OTHER = JbiPackage.PROVIDES__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER_PROVIDES__LOCAL = JbiPackage.PROVIDES__LOCAL;

	/**
	 * The feature id for the '<em><b>Endpoint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER_PROVIDES__ENDPOINT_NAME = JbiPackage.PROVIDES__ENDPOINT_NAME;

	/**
	 * The feature id for the '<em><b>Interface Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER_PROVIDES__INTERFACE_NAME = JbiPackage.PROVIDES__INTERFACE_NAME;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER_PROVIDES__SERVICE_NAME = JbiPackage.PROVIDES__SERVICE_NAME;

	/**
	 * The feature id for the '<em><b>Write Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER_PROVIDES__WRITE_DIRECTORY = JbiPackage.PROVIDES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Copy Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER_PROVIDES__COPY_MODE = JbiPackage.PROVIDES_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>File Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER_PROVIDES__FILE_PATTERN = JbiPackage.PROVIDES_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Read Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER_PROVIDES__READ_DIRECTORY = JbiPackage.PROVIDES_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Backup Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER_PROVIDES__BACKUP_DIRECTORY = JbiPackage.PROVIDES_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Provides</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER_PROVIDES_FEATURE_COUNT = JbiPackage.PROVIDES_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferConsumesImpl <em>Consumes</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferConsumesImpl
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferPackageImpl#getFileTransferConsumes()
	 * @generated
	 */
	int FILE_TRANSFER_CONSUMES = 1;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER_CONSUMES__GROUP = JbiPackage.CONSUMES__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER_CONSUMES__OTHER = JbiPackage.CONSUMES__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER_CONSUMES__LOCAL = JbiPackage.CONSUMES__LOCAL;

	/**
	 * The feature id for the '<em><b>Endpoint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER_CONSUMES__ENDPOINT_NAME = JbiPackage.CONSUMES__ENDPOINT_NAME;

	/**
	 * The feature id for the '<em><b>Interface Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER_CONSUMES__INTERFACE_NAME = JbiPackage.CONSUMES__INTERFACE_NAME;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER_CONSUMES__SERVICE_NAME = JbiPackage.CONSUMES__SERVICE_NAME;

	/**
	 * The feature id for the '<em><b>Read Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER_CONSUMES__READ_DIRECTORY = JbiPackage.CONSUMES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Backup Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER_CONSUMES__BACKUP_DIRECTORY = JbiPackage.CONSUMES_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Transfer Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER_CONSUMES__TRANSFER_MODE = JbiPackage.CONSUMES_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>File Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER_CONSUMES__FILE_PATTERN = JbiPackage.CONSUMES_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Polling Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER_CONSUMES__POLLING_PERIOD = JbiPackage.CONSUMES_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Consumes</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER_CONSUMES_FEATURE_COUNT = JbiPackage.CONSUMES_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.CopyMode <em>Copy Mode</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.CopyMode
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferPackageImpl#getCopyMode()
	 * @generated
	 */
	int COPY_MODE = 2;

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.TransferMode <em>Transfer Mode</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.TransferMode
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferPackageImpl#getTransferMode()
	 * @generated
	 */
	int TRANSFER_MODE = 3;


	/**
	 * Returns the meta object for class '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferProvides <em>Provides</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Provides</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferProvides
	 * @generated
	 */
	EClass getFileTransferProvides();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferProvides#getWriteDirectory <em>Write Directory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Write Directory</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferProvides#getWriteDirectory()
	 * @see #getFileTransferProvides()
	 * @generated
	 */
	EAttribute getFileTransferProvides_WriteDirectory();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferProvides#getCopyMode <em>Copy Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Copy Mode</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferProvides#getCopyMode()
	 * @see #getFileTransferProvides()
	 * @generated
	 */
	EAttribute getFileTransferProvides_CopyMode();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferProvides#getFilePattern <em>File Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Pattern</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferProvides#getFilePattern()
	 * @see #getFileTransferProvides()
	 * @generated
	 */
	EAttribute getFileTransferProvides_FilePattern();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferProvides#getReadDirectory <em>Read Directory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Read Directory</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferProvides#getReadDirectory()
	 * @see #getFileTransferProvides()
	 * @generated
	 */
	EAttribute getFileTransferProvides_ReadDirectory();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferProvides#getBackupDirectory <em>Backup Directory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Backup Directory</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferProvides#getBackupDirectory()
	 * @see #getFileTransferProvides()
	 * @generated
	 */
	EAttribute getFileTransferProvides_BackupDirectory();

	/**
	 * Returns the meta object for class '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferConsumes <em>Consumes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Consumes</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferConsumes
	 * @generated
	 */
	EClass getFileTransferConsumes();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferConsumes#getReadDirectory <em>Read Directory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Read Directory</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferConsumes#getReadDirectory()
	 * @see #getFileTransferConsumes()
	 * @generated
	 */
	EAttribute getFileTransferConsumes_ReadDirectory();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferConsumes#getBackupDirectory <em>Backup Directory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Backup Directory</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferConsumes#getBackupDirectory()
	 * @see #getFileTransferConsumes()
	 * @generated
	 */
	EAttribute getFileTransferConsumes_BackupDirectory();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferConsumes#getTransferMode <em>Transfer Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transfer Mode</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferConsumes#getTransferMode()
	 * @see #getFileTransferConsumes()
	 * @generated
	 */
	EAttribute getFileTransferConsumes_TransferMode();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferConsumes#getFilePattern <em>File Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Pattern</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferConsumes#getFilePattern()
	 * @see #getFileTransferConsumes()
	 * @generated
	 */
	EAttribute getFileTransferConsumes_FilePattern();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferConsumes#getPollingPeriod <em>Polling Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Polling Period</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferConsumes#getPollingPeriod()
	 * @see #getFileTransferConsumes()
	 * @generated
	 */
	EAttribute getFileTransferConsumes_PollingPeriod();

	/**
	 * Returns the meta object for enum '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.CopyMode <em>Copy Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Copy Mode</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.CopyMode
	 * @generated
	 */
	EEnum getCopyMode();

	/**
	 * Returns the meta object for enum '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.TransferMode <em>Transfer Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Transfer Mode</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.TransferMode
	 * @generated
	 */
	EEnum getTransferMode();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	FileTransferFactory getFileTransferFactory();

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
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferProvidesImpl <em>Provides</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferProvidesImpl
		 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferPackageImpl#getFileTransferProvides()
		 * @generated
		 */
		EClass FILE_TRANSFER_PROVIDES = eINSTANCE.getFileTransferProvides();

		/**
		 * The meta object literal for the '<em><b>Write Directory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_TRANSFER_PROVIDES__WRITE_DIRECTORY = eINSTANCE.getFileTransferProvides_WriteDirectory();

		/**
		 * The meta object literal for the '<em><b>Copy Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_TRANSFER_PROVIDES__COPY_MODE = eINSTANCE.getFileTransferProvides_CopyMode();

		/**
		 * The meta object literal for the '<em><b>File Pattern</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_TRANSFER_PROVIDES__FILE_PATTERN = eINSTANCE.getFileTransferProvides_FilePattern();

		/**
		 * The meta object literal for the '<em><b>Read Directory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_TRANSFER_PROVIDES__READ_DIRECTORY = eINSTANCE.getFileTransferProvides_ReadDirectory();

		/**
		 * The meta object literal for the '<em><b>Backup Directory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_TRANSFER_PROVIDES__BACKUP_DIRECTORY = eINSTANCE.getFileTransferProvides_BackupDirectory();

		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferConsumesImpl <em>Consumes</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferConsumesImpl
		 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferPackageImpl#getFileTransferConsumes()
		 * @generated
		 */
		EClass FILE_TRANSFER_CONSUMES = eINSTANCE.getFileTransferConsumes();

		/**
		 * The meta object literal for the '<em><b>Read Directory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_TRANSFER_CONSUMES__READ_DIRECTORY = eINSTANCE.getFileTransferConsumes_ReadDirectory();

		/**
		 * The meta object literal for the '<em><b>Backup Directory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_TRANSFER_CONSUMES__BACKUP_DIRECTORY = eINSTANCE.getFileTransferConsumes_BackupDirectory();

		/**
		 * The meta object literal for the '<em><b>Transfer Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_TRANSFER_CONSUMES__TRANSFER_MODE = eINSTANCE.getFileTransferConsumes_TransferMode();

		/**
		 * The meta object literal for the '<em><b>File Pattern</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_TRANSFER_CONSUMES__FILE_PATTERN = eINSTANCE.getFileTransferConsumes_FilePattern();

		/**
		 * The meta object literal for the '<em><b>Polling Period</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_TRANSFER_CONSUMES__POLLING_PERIOD = eINSTANCE.getFileTransferConsumes_PollingPeriod();

		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.CopyMode <em>Copy Mode</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.CopyMode
		 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferPackageImpl#getCopyMode()
		 * @generated
		 */
		EEnum COPY_MODE = eINSTANCE.getCopyMode();

		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.TransferMode <em>Transfer Mode</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.TransferMode
		 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferPackageImpl#getTransferMode()
		 * @generated
		 */
		EEnum TRANSFER_MODE = eINSTANCE.getTransferMode();

	}

} //FileTransferPackage
