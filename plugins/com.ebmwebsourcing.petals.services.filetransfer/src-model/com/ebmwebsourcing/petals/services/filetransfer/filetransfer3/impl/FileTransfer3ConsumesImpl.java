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
 
package com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.impl;

import com.ebmwebsourcing.petals.services.cdk.cdk5.impl.CDK5ConsumesImpl;

import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.TransferMode;

import com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.Filetransfer3Package;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>File Transfer3 Consumes</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.impl.FileTransfer3ConsumesImpl#getFolder <em>Folder</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.impl.FileTransfer3ConsumesImpl#getBackupDirectory <em>Backup Directory</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.impl.FileTransfer3ConsumesImpl#getTransferMode <em>Transfer Mode</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.impl.FileTransfer3ConsumesImpl#getFilename <em>Filename</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.impl.FileTransfer3ConsumesImpl#getPollingPeriod <em>Polling Period</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.impl.FileTransfer3ConsumesImpl#getBaseMessage <em>Base Message</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.impl.FileTransfer3ConsumesImpl#getProcessorPoolSize <em>Processor Pool Size</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.impl.FileTransfer3ConsumesImpl#getProcessorPoolTimeout <em>Processor Pool Timeout</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FileTransfer3ConsumesImpl extends CDK5ConsumesImpl implements FileTransfer3Consumes {
	/**
	 * The default value of the '{@link #getFolder() <em>Folder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFolder()
	 * @generated
	 * @ordered
	 */
	protected static final String FOLDER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFolder() <em>Folder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFolder()
	 * @generated
	 * @ordered
	 */
	protected String folder = FOLDER_EDEFAULT;

	/**
	 * This is true if the Folder attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean folderESet;

	/**
	 * The default value of the '{@link #getBackupDirectory() <em>Backup Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBackupDirectory()
	 * @generated
	 * @ordered
	 */
	protected static final String BACKUP_DIRECTORY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBackupDirectory() <em>Backup Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBackupDirectory()
	 * @generated
	 * @ordered
	 */
	protected String backupDirectory = BACKUP_DIRECTORY_EDEFAULT;

	/**
	 * The default value of the '{@link #getTransferMode() <em>Transfer Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransferMode()
	 * @generated
	 * @ordered
	 */
	protected static final TransferMode TRANSFER_MODE_EDEFAULT = TransferMode.CONTENT;

	/**
	 * The cached value of the '{@link #getTransferMode() <em>Transfer Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransferMode()
	 * @generated
	 * @ordered
	 */
	protected TransferMode transferMode = TRANSFER_MODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getFilename() <em>Filename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilename()
	 * @generated
	 * @ordered
	 */
	protected static final String FILENAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFilename() <em>Filename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilename()
	 * @generated
	 * @ordered
	 */
	protected String filename = FILENAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPollingPeriod() <em>Polling Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPollingPeriod()
	 * @generated
	 * @ordered
	 */
	protected static final int POLLING_PERIOD_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPollingPeriod() <em>Polling Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPollingPeriod()
	 * @generated
	 * @ordered
	 */
	protected int pollingPeriod = POLLING_PERIOD_EDEFAULT;

	/**
	 * The default value of the '{@link #getBaseMessage() <em>Base Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseMessage()
	 * @generated
	 * @ordered
	 */
	protected static final String BASE_MESSAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBaseMessage() <em>Base Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseMessage()
	 * @generated
	 * @ordered
	 */
	protected String baseMessage = BASE_MESSAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getProcessorPoolSize() <em>Processor Pool Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessorPoolSize()
	 * @generated
	 * @ordered
	 */
	protected static final int PROCESSOR_POOL_SIZE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getProcessorPoolSize() <em>Processor Pool Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessorPoolSize()
	 * @generated
	 * @ordered
	 */
	protected int processorPoolSize = PROCESSOR_POOL_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getProcessorPoolTimeout() <em>Processor Pool Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessorPoolTimeout()
	 * @generated
	 * @ordered
	 */
	protected static final int PROCESSOR_POOL_TIMEOUT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getProcessorPoolTimeout() <em>Processor Pool Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessorPoolTimeout()
	 * @generated
	 * @ordered
	 */
	protected int processorPoolTimeout = PROCESSOR_POOL_TIMEOUT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FileTransfer3ConsumesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Filetransfer3Package.Literals.FILE_TRANSFER3_CONSUMES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFolder() {
		return folder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFolder(String newFolder) {
		String oldFolder = folder;
		folder = newFolder;
		boolean oldFolderESet = folderESet;
		folderESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Filetransfer3Package.FILE_TRANSFER3_CONSUMES__FOLDER, oldFolder, folder, !oldFolderESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetFolder() {
		String oldFolder = folder;
		boolean oldFolderESet = folderESet;
		folder = FOLDER_EDEFAULT;
		folderESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, Filetransfer3Package.FILE_TRANSFER3_CONSUMES__FOLDER, oldFolder, FOLDER_EDEFAULT, oldFolderESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetFolder() {
		return folderESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBackupDirectory() {
		return backupDirectory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBackupDirectory(String newBackupDirectory) {
		String oldBackupDirectory = backupDirectory;
		backupDirectory = newBackupDirectory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Filetransfer3Package.FILE_TRANSFER3_CONSUMES__BACKUP_DIRECTORY, oldBackupDirectory, backupDirectory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransferMode getTransferMode() {
		return transferMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransferMode(TransferMode newTransferMode) {
		TransferMode oldTransferMode = transferMode;
		transferMode = newTransferMode == null ? TRANSFER_MODE_EDEFAULT : newTransferMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Filetransfer3Package.FILE_TRANSFER3_CONSUMES__TRANSFER_MODE, oldTransferMode, transferMode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFilename(String newFilename) {
		String oldFilename = filename;
		filename = newFilename;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Filetransfer3Package.FILE_TRANSFER3_CONSUMES__FILENAME, oldFilename, filename));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPollingPeriod() {
		return pollingPeriod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPollingPeriod(int newPollingPeriod) {
		int oldPollingPeriod = pollingPeriod;
		pollingPeriod = newPollingPeriod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Filetransfer3Package.FILE_TRANSFER3_CONSUMES__POLLING_PERIOD, oldPollingPeriod, pollingPeriod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBaseMessage() {
		return baseMessage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBaseMessage(String newBaseMessage) {
		String oldBaseMessage = baseMessage;
		baseMessage = newBaseMessage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Filetransfer3Package.FILE_TRANSFER3_CONSUMES__BASE_MESSAGE, oldBaseMessage, baseMessage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getProcessorPoolSize() {
		return processorPoolSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProcessorPoolSize(int newProcessorPoolSize) {
		int oldProcessorPoolSize = processorPoolSize;
		processorPoolSize = newProcessorPoolSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Filetransfer3Package.FILE_TRANSFER3_CONSUMES__PROCESSOR_POOL_SIZE, oldProcessorPoolSize, processorPoolSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getProcessorPoolTimeout() {
		return processorPoolTimeout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProcessorPoolTimeout(int newProcessorPoolTimeout) {
		int oldProcessorPoolTimeout = processorPoolTimeout;
		processorPoolTimeout = newProcessorPoolTimeout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Filetransfer3Package.FILE_TRANSFER3_CONSUMES__PROCESSOR_POOL_TIMEOUT, oldProcessorPoolTimeout, processorPoolTimeout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__FOLDER:
				return getFolder();
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__BACKUP_DIRECTORY:
				return getBackupDirectory();
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__TRANSFER_MODE:
				return getTransferMode();
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__FILENAME:
				return getFilename();
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__POLLING_PERIOD:
				return getPollingPeriod();
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__BASE_MESSAGE:
				return getBaseMessage();
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__PROCESSOR_POOL_SIZE:
				return getProcessorPoolSize();
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__PROCESSOR_POOL_TIMEOUT:
				return getProcessorPoolTimeout();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__FOLDER:
				setFolder((String)newValue);
				return;
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__BACKUP_DIRECTORY:
				setBackupDirectory((String)newValue);
				return;
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__TRANSFER_MODE:
				setTransferMode((TransferMode)newValue);
				return;
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__FILENAME:
				setFilename((String)newValue);
				return;
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__POLLING_PERIOD:
				setPollingPeriod((Integer)newValue);
				return;
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__BASE_MESSAGE:
				setBaseMessage((String)newValue);
				return;
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__PROCESSOR_POOL_SIZE:
				setProcessorPoolSize((Integer)newValue);
				return;
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__PROCESSOR_POOL_TIMEOUT:
				setProcessorPoolTimeout((Integer)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__FOLDER:
				unsetFolder();
				return;
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__BACKUP_DIRECTORY:
				setBackupDirectory(BACKUP_DIRECTORY_EDEFAULT);
				return;
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__TRANSFER_MODE:
				setTransferMode(TRANSFER_MODE_EDEFAULT);
				return;
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__FILENAME:
				setFilename(FILENAME_EDEFAULT);
				return;
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__POLLING_PERIOD:
				setPollingPeriod(POLLING_PERIOD_EDEFAULT);
				return;
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__BASE_MESSAGE:
				setBaseMessage(BASE_MESSAGE_EDEFAULT);
				return;
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__PROCESSOR_POOL_SIZE:
				setProcessorPoolSize(PROCESSOR_POOL_SIZE_EDEFAULT);
				return;
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__PROCESSOR_POOL_TIMEOUT:
				setProcessorPoolTimeout(PROCESSOR_POOL_TIMEOUT_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__FOLDER:
				return isSetFolder();
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__BACKUP_DIRECTORY:
				return BACKUP_DIRECTORY_EDEFAULT == null ? backupDirectory != null : !BACKUP_DIRECTORY_EDEFAULT.equals(backupDirectory);
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__TRANSFER_MODE:
				return transferMode != TRANSFER_MODE_EDEFAULT;
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__FILENAME:
				return FILENAME_EDEFAULT == null ? filename != null : !FILENAME_EDEFAULT.equals(filename);
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__POLLING_PERIOD:
				return pollingPeriod != POLLING_PERIOD_EDEFAULT;
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__BASE_MESSAGE:
				return BASE_MESSAGE_EDEFAULT == null ? baseMessage != null : !BASE_MESSAGE_EDEFAULT.equals(baseMessage);
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__PROCESSOR_POOL_SIZE:
				return processorPoolSize != PROCESSOR_POOL_SIZE_EDEFAULT;
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES__PROCESSOR_POOL_TIMEOUT:
				return processorPoolTimeout != PROCESSOR_POOL_TIMEOUT_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (folder: ");
		if (folderESet) result.append(folder); else result.append("<unset>");
		result.append(", backupDirectory: ");
		result.append(backupDirectory);
		result.append(", transferMode: ");
		result.append(transferMode);
		result.append(", filename: ");
		result.append(filename);
		result.append(", pollingPeriod: ");
		result.append(pollingPeriod);
		result.append(", baseMessage: ");
		result.append(baseMessage);
		result.append(", processorPoolSize: ");
		result.append(processorPoolSize);
		result.append(", processorPoolTimeout: ");
		result.append(processorPoolTimeout);
		result.append(')');
		return result.toString();
	}

} //FileTransfer3ConsumesImpl
