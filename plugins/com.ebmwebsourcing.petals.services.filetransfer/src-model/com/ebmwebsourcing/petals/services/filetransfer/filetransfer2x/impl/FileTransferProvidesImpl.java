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

import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.CopyMode;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferProvides;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.Filetransfer2xPackage;
import com.sun.java.xml.ns.jbi.impl.ProvidesImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Provides</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferProvidesImpl#getWriteDirectory <em>Write Directory</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferProvidesImpl#getCopyMode <em>Copy Mode</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferProvidesImpl#getFilePattern <em>File Pattern</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferProvidesImpl#getReadDirectory <em>Read Directory</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferProvidesImpl#getBackupDirectory <em>Backup Directory</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FileTransferProvidesImpl extends ProvidesImpl implements FileTransferProvides {
	/**
	 * The default value of the '{@link #getWriteDirectory() <em>Write Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWriteDirectory()
	 * @generated
	 * @ordered
	 */
	protected static final String WRITE_DIRECTORY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWriteDirectory() <em>Write Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWriteDirectory()
	 * @generated
	 * @ordered
	 */
	protected String writeDirectory = WRITE_DIRECTORY_EDEFAULT;

	/**
	 * The default value of the '{@link #getCopyMode() <em>Copy Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCopyMode()
	 * @generated
	 * @ordered
	 */
	protected static final CopyMode COPY_MODE_EDEFAULT = CopyMode.CONTENT_ONLY;

	/**
	 * The cached value of the '{@link #getCopyMode() <em>Copy Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCopyMode()
	 * @generated
	 * @ordered
	 */
	protected CopyMode copyMode = COPY_MODE_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FileTransferProvidesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Filetransfer2xPackage.Literals.FILE_TRANSFER_PROVIDES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getWriteDirectory() {
		return this.writeDirectory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWriteDirectory(String newWriteDirectory) {
		String oldWriteDirectory = this.writeDirectory;
		this.writeDirectory = newWriteDirectory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Filetransfer2xPackage.FILE_TRANSFER_PROVIDES__WRITE_DIRECTORY, oldWriteDirectory, this.writeDirectory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CopyMode getCopyMode() {
		return this.copyMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCopyMode(CopyMode newCopyMode) {
		CopyMode oldCopyMode = this.copyMode;
		this.copyMode = newCopyMode == null ? COPY_MODE_EDEFAULT : newCopyMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Filetransfer2xPackage.FILE_TRANSFER_PROVIDES__COPY_MODE, oldCopyMode, this.copyMode));
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
			eNotify(new ENotificationImpl(this, Notification.SET, Filetransfer2xPackage.FILE_TRANSFER_PROVIDES__FILE_PATTERN, oldFilePattern, this.filePattern));
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
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Filetransfer2xPackage.FILE_TRANSFER_PROVIDES__READ_DIRECTORY, oldReadDirectory, this.readDirectory));
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
			eNotify(new ENotificationImpl(this, Notification.SET, Filetransfer2xPackage.FILE_TRANSFER_PROVIDES__BACKUP_DIRECTORY, oldBackupDirectory, this.backupDirectory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Filetransfer2xPackage.FILE_TRANSFER_PROVIDES__WRITE_DIRECTORY:
				return getWriteDirectory();
			case Filetransfer2xPackage.FILE_TRANSFER_PROVIDES__COPY_MODE:
				return getCopyMode();
			case Filetransfer2xPackage.FILE_TRANSFER_PROVIDES__FILE_PATTERN:
				return getFilePattern();
			case Filetransfer2xPackage.FILE_TRANSFER_PROVIDES__READ_DIRECTORY:
				return getReadDirectory();
			case Filetransfer2xPackage.FILE_TRANSFER_PROVIDES__BACKUP_DIRECTORY:
				return getBackupDirectory();
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
			case Filetransfer2xPackage.FILE_TRANSFER_PROVIDES__WRITE_DIRECTORY:
				setWriteDirectory((String)newValue);
				return;
			case Filetransfer2xPackage.FILE_TRANSFER_PROVIDES__COPY_MODE:
				setCopyMode((CopyMode)newValue);
				return;
			case Filetransfer2xPackage.FILE_TRANSFER_PROVIDES__FILE_PATTERN:
				setFilePattern((String)newValue);
				return;
			case Filetransfer2xPackage.FILE_TRANSFER_PROVIDES__READ_DIRECTORY:
				setReadDirectory((String)newValue);
				return;
			case Filetransfer2xPackage.FILE_TRANSFER_PROVIDES__BACKUP_DIRECTORY:
				setBackupDirectory((String)newValue);
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
			case Filetransfer2xPackage.FILE_TRANSFER_PROVIDES__WRITE_DIRECTORY:
				setWriteDirectory(WRITE_DIRECTORY_EDEFAULT);
				return;
			case Filetransfer2xPackage.FILE_TRANSFER_PROVIDES__COPY_MODE:
				setCopyMode(COPY_MODE_EDEFAULT);
				return;
			case Filetransfer2xPackage.FILE_TRANSFER_PROVIDES__FILE_PATTERN:
				setFilePattern(FILE_PATTERN_EDEFAULT);
				return;
			case Filetransfer2xPackage.FILE_TRANSFER_PROVIDES__READ_DIRECTORY:
				setReadDirectory(READ_DIRECTORY_EDEFAULT);
				return;
			case Filetransfer2xPackage.FILE_TRANSFER_PROVIDES__BACKUP_DIRECTORY:
				setBackupDirectory(BACKUP_DIRECTORY_EDEFAULT);
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
			case Filetransfer2xPackage.FILE_TRANSFER_PROVIDES__WRITE_DIRECTORY:
				return WRITE_DIRECTORY_EDEFAULT == null ? this.writeDirectory != null : !WRITE_DIRECTORY_EDEFAULT.equals(this.writeDirectory);
			case Filetransfer2xPackage.FILE_TRANSFER_PROVIDES__COPY_MODE:
				return this.copyMode != COPY_MODE_EDEFAULT;
			case Filetransfer2xPackage.FILE_TRANSFER_PROVIDES__FILE_PATTERN:
				return FILE_PATTERN_EDEFAULT == null ? this.filePattern != null : !FILE_PATTERN_EDEFAULT.equals(this.filePattern);
			case Filetransfer2xPackage.FILE_TRANSFER_PROVIDES__READ_DIRECTORY:
				return READ_DIRECTORY_EDEFAULT == null ? this.readDirectory != null : !READ_DIRECTORY_EDEFAULT.equals(this.readDirectory);
			case Filetransfer2xPackage.FILE_TRANSFER_PROVIDES__BACKUP_DIRECTORY:
				return BACKUP_DIRECTORY_EDEFAULT == null ? this.backupDirectory != null : !BACKUP_DIRECTORY_EDEFAULT.equals(this.backupDirectory);
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
		result.append(" (writeDirectory: ");
		result.append(this.writeDirectory);
		result.append(", copyMode: ");
		result.append(this.copyMode);
		result.append(", filePattern: ");
		result.append(this.filePattern);
		result.append(", readDirectory: ");
		result.append(this.readDirectory);
		result.append(", backupDirectory: ");
		result.append(this.backupDirectory);
		result.append(')');
		return result.toString();
	}

} //FileTransferProvidesImpl
