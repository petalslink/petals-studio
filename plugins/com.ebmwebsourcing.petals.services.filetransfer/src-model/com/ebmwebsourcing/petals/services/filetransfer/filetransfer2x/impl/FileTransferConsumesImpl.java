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

package com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferConsumes;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.Filetransfer2xPackage;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.TransferMode;
import com.sun.java.xml.ns.jbi.impl.ConsumesImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Consumes</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferConsumesImpl#getReadDirectory <em>Read Directory</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferConsumesImpl#getBackupDirectory <em>Backup Directory</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferConsumesImpl#getTransferMode <em>Transfer Mode</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferConsumesImpl#getFilePattern <em>File Pattern</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferConsumesImpl#getPollingPeriod <em>Polling Period</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FileTransferConsumesImpl extends ConsumesImpl implements FileTransferConsumes {
	/**
	 * The default value of the '{@link #getReadDirectory() <em>Read Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReadDirectory()
	 * @generated
	 * @ordered
	 */
	protected static final String READ_DIRECTORY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReadDirectory() <em>Read Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReadDirectory()
	 * @generated
	 * @ordered
	 */
	protected String readDirectory = READ_DIRECTORY_EDEFAULT;

	/**
	 * This is true if the Read Directory attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean readDirectoryESet;

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
	 * The default value of the '{@link #getFilePattern() <em>File Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilePattern()
	 * @generated
	 * @ordered
	 */
	protected static final String FILE_PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFilePattern() <em>File Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilePattern()
	 * @generated
	 * @ordered
	 */
	protected String filePattern = FILE_PATTERN_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FileTransferConsumesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Filetransfer2xPackage.Literals.FILE_TRANSFER_CONSUMES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getReadDirectory() {
		return this.readDirectory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReadDirectory(String newReadDirectory) {
		String oldReadDirectory = this.readDirectory;
		this.readDirectory = newReadDirectory;
		boolean oldReadDirectoryESet = this.readDirectoryESet;
		this.readDirectoryESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Filetransfer2xPackage.FILE_TRANSFER_CONSUMES__READ_DIRECTORY, oldReadDirectory, this.readDirectory, !oldReadDirectoryESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetReadDirectory() {
		String oldReadDirectory = this.readDirectory;
		boolean oldReadDirectoryESet = this.readDirectoryESet;
		this.readDirectory = READ_DIRECTORY_EDEFAULT;
		this.readDirectoryESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, Filetransfer2xPackage.FILE_TRANSFER_CONSUMES__READ_DIRECTORY, oldReadDirectory, READ_DIRECTORY_EDEFAULT, oldReadDirectoryESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetReadDirectory() {
		return this.readDirectoryESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getBackupDirectory() {
		return this.backupDirectory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBackupDirectory(String newBackupDirectory) {
		String oldBackupDirectory = this.backupDirectory;
		this.backupDirectory = newBackupDirectory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Filetransfer2xPackage.FILE_TRANSFER_CONSUMES__BACKUP_DIRECTORY, oldBackupDirectory, this.backupDirectory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TransferMode getTransferMode() {
		return this.transferMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTransferMode(TransferMode newTransferMode) {
		TransferMode oldTransferMode = this.transferMode;
		this.transferMode = newTransferMode == null ? TRANSFER_MODE_EDEFAULT : newTransferMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Filetransfer2xPackage.FILE_TRANSFER_CONSUMES__TRANSFER_MODE, oldTransferMode, this.transferMode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFilePattern() {
		return this.filePattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFilePattern(String newFilePattern) {
		String oldFilePattern = this.filePattern;
		this.filePattern = newFilePattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Filetransfer2xPackage.FILE_TRANSFER_CONSUMES__FILE_PATTERN, oldFilePattern, this.filePattern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getPollingPeriod() {
		return this.pollingPeriod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPollingPeriod(int newPollingPeriod) {
		int oldPollingPeriod = this.pollingPeriod;
		this.pollingPeriod = newPollingPeriod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Filetransfer2xPackage.FILE_TRANSFER_CONSUMES__POLLING_PERIOD, oldPollingPeriod, this.pollingPeriod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Filetransfer2xPackage.FILE_TRANSFER_CONSUMES__READ_DIRECTORY:
				return getReadDirectory();
			case Filetransfer2xPackage.FILE_TRANSFER_CONSUMES__BACKUP_DIRECTORY:
				return getBackupDirectory();
			case Filetransfer2xPackage.FILE_TRANSFER_CONSUMES__TRANSFER_MODE:
				return getTransferMode();
			case Filetransfer2xPackage.FILE_TRANSFER_CONSUMES__FILE_PATTERN:
				return getFilePattern();
			case Filetransfer2xPackage.FILE_TRANSFER_CONSUMES__POLLING_PERIOD:
				return getPollingPeriod();
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
			case Filetransfer2xPackage.FILE_TRANSFER_CONSUMES__READ_DIRECTORY:
				setReadDirectory((String)newValue);
				return;
			case Filetransfer2xPackage.FILE_TRANSFER_CONSUMES__BACKUP_DIRECTORY:
				setBackupDirectory((String)newValue);
				return;
			case Filetransfer2xPackage.FILE_TRANSFER_CONSUMES__TRANSFER_MODE:
				setTransferMode((TransferMode)newValue);
				return;
			case Filetransfer2xPackage.FILE_TRANSFER_CONSUMES__FILE_PATTERN:
				setFilePattern((String)newValue);
				return;
			case Filetransfer2xPackage.FILE_TRANSFER_CONSUMES__POLLING_PERIOD:
				setPollingPeriod((Integer)newValue);
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
			case Filetransfer2xPackage.FILE_TRANSFER_CONSUMES__READ_DIRECTORY:
				unsetReadDirectory();
				return;
			case Filetransfer2xPackage.FILE_TRANSFER_CONSUMES__BACKUP_DIRECTORY:
				setBackupDirectory(BACKUP_DIRECTORY_EDEFAULT);
				return;
			case Filetransfer2xPackage.FILE_TRANSFER_CONSUMES__TRANSFER_MODE:
				setTransferMode(TRANSFER_MODE_EDEFAULT);
				return;
			case Filetransfer2xPackage.FILE_TRANSFER_CONSUMES__FILE_PATTERN:
				setFilePattern(FILE_PATTERN_EDEFAULT);
				return;
			case Filetransfer2xPackage.FILE_TRANSFER_CONSUMES__POLLING_PERIOD:
				setPollingPeriod(POLLING_PERIOD_EDEFAULT);
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
			case Filetransfer2xPackage.FILE_TRANSFER_CONSUMES__READ_DIRECTORY:
				return isSetReadDirectory();
			case Filetransfer2xPackage.FILE_TRANSFER_CONSUMES__BACKUP_DIRECTORY:
				return BACKUP_DIRECTORY_EDEFAULT == null ? this.backupDirectory != null : !BACKUP_DIRECTORY_EDEFAULT.equals(this.backupDirectory);
			case Filetransfer2xPackage.FILE_TRANSFER_CONSUMES__TRANSFER_MODE:
				return this.transferMode != TRANSFER_MODE_EDEFAULT;
			case Filetransfer2xPackage.FILE_TRANSFER_CONSUMES__FILE_PATTERN:
				return FILE_PATTERN_EDEFAULT == null ? this.filePattern != null : !FILE_PATTERN_EDEFAULT.equals(this.filePattern);
			case Filetransfer2xPackage.FILE_TRANSFER_CONSUMES__POLLING_PERIOD:
				return this.pollingPeriod != POLLING_PERIOD_EDEFAULT;
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
		result.append(" (readDirectory: ");
		if (this.readDirectoryESet) result.append(this.readDirectory); else result.append("<unset>");
		result.append(", backupDirectory: ");
		result.append(this.backupDirectory);
		result.append(", transferMode: ");
		result.append(this.transferMode);
		result.append(", filePattern: ");
		result.append(this.filePattern);
		result.append(", pollingPeriod: ");
		result.append(this.pollingPeriod);
		result.append(')');
		return result.toString();
	}

} //FileTransferConsumesImpl
