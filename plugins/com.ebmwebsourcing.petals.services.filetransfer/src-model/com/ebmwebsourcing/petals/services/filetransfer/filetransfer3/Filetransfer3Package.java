/******************************************************************************
 * Copyright (c) 2012-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.filetransfer.filetransfer3;

import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;

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
 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.Filetransfer3Factory
 * @model kind="package"
 * @generated
 */
public interface Filetransfer3Package extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "filetransfer3";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://petals.ow2.org/components/filetransfer/version-3";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "filetransfer3";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Filetransfer3Package eINSTANCE = com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.impl.Filetransfer3PackageImpl.init();

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.impl.FileTransfer3ProvidesImpl <em>File Transfer3 Provides</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.impl.FileTransfer3ProvidesImpl
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.impl.Filetransfer3PackageImpl#getFileTransfer3Provides()
	 * @generated
	 */
	int FILE_TRANSFER3_PROVIDES = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_PROVIDES__GROUP = Cdk5Package.CDK5_PROVIDES__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_PROVIDES__OTHER = Cdk5Package.CDK5_PROVIDES__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_PROVIDES__LOCAL = Cdk5Package.CDK5_PROVIDES__LOCAL;

	/**
	 * The feature id for the '<em><b>Endpoint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_PROVIDES__ENDPOINT_NAME = Cdk5Package.CDK5_PROVIDES__ENDPOINT_NAME;

	/**
	 * The feature id for the '<em><b>Interface Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_PROVIDES__INTERFACE_NAME = Cdk5Package.CDK5_PROVIDES__INTERFACE_NAME;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_PROVIDES__SERVICE_NAME = Cdk5Package.CDK5_PROVIDES__SERVICE_NAME;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_PROVIDES__TIMEOUT = Cdk5Package.CDK5_PROVIDES__TIMEOUT;

	/**
	 * The feature id for the '<em><b>Validate Wsdl</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_PROVIDES__VALIDATE_WSDL = Cdk5Package.CDK5_PROVIDES__VALIDATE_WSDL;

	/**
	 * The feature id for the '<em><b>Forward Security Subject</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_PROVIDES__FORWARD_SECURITY_SUBJECT = Cdk5Package.CDK5_PROVIDES__FORWARD_SECURITY_SUBJECT;

	/**
	 * The feature id for the '<em><b>Forward Message Properties</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_PROVIDES__FORWARD_MESSAGE_PROPERTIES = Cdk5Package.CDK5_PROVIDES__FORWARD_MESSAGE_PROPERTIES;

	/**
	 * The feature id for the '<em><b>Forward Attachments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_PROVIDES__FORWARD_ATTACHMENTS = Cdk5Package.CDK5_PROVIDES__FORWARD_ATTACHMENTS;

	/**
	 * The feature id for the '<em><b>Wsdl</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_PROVIDES__WSDL = Cdk5Package.CDK5_PROVIDES__WSDL;

	/**
	 * The feature id for the '<em><b>Filename</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_PROVIDES__FILENAME = Cdk5Package.CDK5_PROVIDES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Backup Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_PROVIDES__BACKUP_DIRECTORY = Cdk5Package.CDK5_PROVIDES_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Folder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_PROVIDES__FOLDER = Cdk5Package.CDK5_PROVIDES_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>File Transfer3 Provides</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_PROVIDES_FEATURE_COUNT = Cdk5Package.CDK5_PROVIDES_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.impl.FileTransfer3ConsumesImpl <em>File Transfer3 Consumes</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.impl.FileTransfer3ConsumesImpl
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.impl.Filetransfer3PackageImpl#getFileTransfer3Consumes()
	 * @generated
	 */
	int FILE_TRANSFER3_CONSUMES = 1;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_CONSUMES__GROUP = Cdk5Package.CDK5_CONSUMES__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_CONSUMES__OTHER = Cdk5Package.CDK5_CONSUMES__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_CONSUMES__LOCAL = Cdk5Package.CDK5_CONSUMES__LOCAL;

	/**
	 * The feature id for the '<em><b>Endpoint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_CONSUMES__ENDPOINT_NAME = Cdk5Package.CDK5_CONSUMES__ENDPOINT_NAME;

	/**
	 * The feature id for the '<em><b>Interface Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_CONSUMES__INTERFACE_NAME = Cdk5Package.CDK5_CONSUMES__INTERFACE_NAME;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_CONSUMES__SERVICE_NAME = Cdk5Package.CDK5_CONSUMES__SERVICE_NAME;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_CONSUMES__TIMEOUT = Cdk5Package.CDK5_CONSUMES__TIMEOUT;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_CONSUMES__OPERATION = Cdk5Package.CDK5_CONSUMES__OPERATION;

	/**
	 * The feature id for the '<em><b>Mep</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_CONSUMES__MEP = Cdk5Package.CDK5_CONSUMES__MEP;

	/**
	 * The feature id for the '<em><b>Folder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_CONSUMES__FOLDER = Cdk5Package.CDK5_CONSUMES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Backup Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_CONSUMES__BACKUP_DIRECTORY = Cdk5Package.CDK5_CONSUMES_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Transfer Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_CONSUMES__TRANSFER_MODE = Cdk5Package.CDK5_CONSUMES_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Filename</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_CONSUMES__FILENAME = Cdk5Package.CDK5_CONSUMES_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Polling Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_CONSUMES__POLLING_PERIOD = Cdk5Package.CDK5_CONSUMES_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Base Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_CONSUMES__BASE_MESSAGE = Cdk5Package.CDK5_CONSUMES_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Processor Pool Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_CONSUMES__PROCESSOR_POOL_SIZE = Cdk5Package.CDK5_CONSUMES_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Processor Pool Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_CONSUMES__PROCESSOR_POOL_TIMEOUT = Cdk5Package.CDK5_CONSUMES_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>File Transfer3 Consumes</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_TRANSFER3_CONSUMES_FEATURE_COUNT = Cdk5Package.CDK5_CONSUMES_FEATURE_COUNT + 8;


	/**
	 * Returns the meta object for class '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Provides <em>File Transfer3 Provides</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>File Transfer3 Provides</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Provides
	 * @generated
	 */
	EClass getFileTransfer3Provides();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Provides#getFilename <em>Filename</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Filename</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Provides#getFilename()
	 * @see #getFileTransfer3Provides()
	 * @generated
	 */
	EAttribute getFileTransfer3Provides_Filename();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Provides#getBackupDirectory <em>Backup Directory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Backup Directory</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Provides#getBackupDirectory()
	 * @see #getFileTransfer3Provides()
	 * @generated
	 */
	EAttribute getFileTransfer3Provides_BackupDirectory();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Provides#getFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Folder</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Provides#getFolder()
	 * @see #getFileTransfer3Provides()
	 * @generated
	 */
	EAttribute getFileTransfer3Provides_Folder();

	/**
	 * Returns the meta object for class '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes <em>File Transfer3 Consumes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>File Transfer3 Consumes</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes
	 * @generated
	 */
	EClass getFileTransfer3Consumes();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Folder</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getFolder()
	 * @see #getFileTransfer3Consumes()
	 * @generated
	 */
	EAttribute getFileTransfer3Consumes_Folder();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getBackupDirectory <em>Backup Directory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Backup Directory</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getBackupDirectory()
	 * @see #getFileTransfer3Consumes()
	 * @generated
	 */
	EAttribute getFileTransfer3Consumes_BackupDirectory();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getTransferMode <em>Transfer Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transfer Mode</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getTransferMode()
	 * @see #getFileTransfer3Consumes()
	 * @generated
	 */
	EAttribute getFileTransfer3Consumes_TransferMode();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getFilename <em>Filename</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Filename</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getFilename()
	 * @see #getFileTransfer3Consumes()
	 * @generated
	 */
	EAttribute getFileTransfer3Consumes_Filename();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getPollingPeriod <em>Polling Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Polling Period</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getPollingPeriod()
	 * @see #getFileTransfer3Consumes()
	 * @generated
	 */
	EAttribute getFileTransfer3Consumes_PollingPeriod();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getBaseMessage <em>Base Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Base Message</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getBaseMessage()
	 * @see #getFileTransfer3Consumes()
	 * @generated
	 */
	EAttribute getFileTransfer3Consumes_BaseMessage();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getProcessorPoolSize <em>Processor Pool Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Processor Pool Size</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getProcessorPoolSize()
	 * @see #getFileTransfer3Consumes()
	 * @generated
	 */
	EAttribute getFileTransfer3Consumes_ProcessorPoolSize();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getProcessorPoolTimeout <em>Processor Pool Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Processor Pool Timeout</em>'.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getProcessorPoolTimeout()
	 * @see #getFileTransfer3Consumes()
	 * @generated
	 */
	EAttribute getFileTransfer3Consumes_ProcessorPoolTimeout();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Filetransfer3Factory getFiletransfer3Factory();

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
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.impl.FileTransfer3ProvidesImpl <em>File Transfer3 Provides</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.impl.FileTransfer3ProvidesImpl
		 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.impl.Filetransfer3PackageImpl#getFileTransfer3Provides()
		 * @generated
		 */
		EClass FILE_TRANSFER3_PROVIDES = eINSTANCE.getFileTransfer3Provides();

		/**
		 * The meta object literal for the '<em><b>Filename</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_TRANSFER3_PROVIDES__FILENAME = eINSTANCE.getFileTransfer3Provides_Filename();

		/**
		 * The meta object literal for the '<em><b>Backup Directory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_TRANSFER3_PROVIDES__BACKUP_DIRECTORY = eINSTANCE.getFileTransfer3Provides_BackupDirectory();

		/**
		 * The meta object literal for the '<em><b>Folder</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_TRANSFER3_PROVIDES__FOLDER = eINSTANCE.getFileTransfer3Provides_Folder();

		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.impl.FileTransfer3ConsumesImpl <em>File Transfer3 Consumes</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.impl.FileTransfer3ConsumesImpl
		 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.impl.Filetransfer3PackageImpl#getFileTransfer3Consumes()
		 * @generated
		 */
		EClass FILE_TRANSFER3_CONSUMES = eINSTANCE.getFileTransfer3Consumes();

		/**
		 * The meta object literal for the '<em><b>Folder</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_TRANSFER3_CONSUMES__FOLDER = eINSTANCE.getFileTransfer3Consumes_Folder();

		/**
		 * The meta object literal for the '<em><b>Backup Directory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_TRANSFER3_CONSUMES__BACKUP_DIRECTORY = eINSTANCE.getFileTransfer3Consumes_BackupDirectory();

		/**
		 * The meta object literal for the '<em><b>Transfer Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_TRANSFER3_CONSUMES__TRANSFER_MODE = eINSTANCE.getFileTransfer3Consumes_TransferMode();

		/**
		 * The meta object literal for the '<em><b>Filename</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_TRANSFER3_CONSUMES__FILENAME = eINSTANCE.getFileTransfer3Consumes_Filename();

		/**
		 * The meta object literal for the '<em><b>Polling Period</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_TRANSFER3_CONSUMES__POLLING_PERIOD = eINSTANCE.getFileTransfer3Consumes_PollingPeriod();

		/**
		 * The meta object literal for the '<em><b>Base Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_TRANSFER3_CONSUMES__BASE_MESSAGE = eINSTANCE.getFileTransfer3Consumes_BaseMessage();

		/**
		 * The meta object literal for the '<em><b>Processor Pool Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_TRANSFER3_CONSUMES__PROCESSOR_POOL_SIZE = eINSTANCE.getFileTransfer3Consumes_ProcessorPoolSize();

		/**
		 * The meta object literal for the '<em><b>Processor Pool Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_TRANSFER3_CONSUMES__PROCESSOR_POOL_TIMEOUT = eINSTANCE.getFileTransfer3Consumes_ProcessorPoolTimeout();

	}

} //Filetransfer3Package
