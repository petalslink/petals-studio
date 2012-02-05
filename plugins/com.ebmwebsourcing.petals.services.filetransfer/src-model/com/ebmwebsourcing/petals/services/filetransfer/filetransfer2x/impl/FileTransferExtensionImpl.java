/**
 * Copyright (c) 2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl;

import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferExtension;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.Filetransfer2xPackage;

import com.sun.java.xml.ns.jbi.impl.AbstractExtensibleElementImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>File Transfer Extension</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferExtensionImpl#getFilePattern <em>File Pattern</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.FileTransferExtensionImpl#getBackupDirectory <em>Backup Directory</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FileTransferExtensionImpl extends AbstractExtensibleElementImpl implements FileTransferExtension {
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
	protected FileTransferExtensionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Filetransfer2xPackage.Literals.FILE_TRANSFER_EXTENSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFilePattern() {
		return filePattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFilePattern(String newFilePattern) {
		String oldFilePattern = filePattern;
		filePattern = newFilePattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Filetransfer2xPackage.FILE_TRANSFER_EXTENSION__FILE_PATTERN, oldFilePattern, filePattern));
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
			eNotify(new ENotificationImpl(this, Notification.SET, Filetransfer2xPackage.FILE_TRANSFER_EXTENSION__BACKUP_DIRECTORY, oldBackupDirectory, backupDirectory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Filetransfer2xPackage.FILE_TRANSFER_EXTENSION__FILE_PATTERN:
				return getFilePattern();
			case Filetransfer2xPackage.FILE_TRANSFER_EXTENSION__BACKUP_DIRECTORY:
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
			case Filetransfer2xPackage.FILE_TRANSFER_EXTENSION__FILE_PATTERN:
				setFilePattern((String)newValue);
				return;
			case Filetransfer2xPackage.FILE_TRANSFER_EXTENSION__BACKUP_DIRECTORY:
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
			case Filetransfer2xPackage.FILE_TRANSFER_EXTENSION__FILE_PATTERN:
				setFilePattern(FILE_PATTERN_EDEFAULT);
				return;
			case Filetransfer2xPackage.FILE_TRANSFER_EXTENSION__BACKUP_DIRECTORY:
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
			case Filetransfer2xPackage.FILE_TRANSFER_EXTENSION__FILE_PATTERN:
				return FILE_PATTERN_EDEFAULT == null ? filePattern != null : !FILE_PATTERN_EDEFAULT.equals(filePattern);
			case Filetransfer2xPackage.FILE_TRANSFER_EXTENSION__BACKUP_DIRECTORY:
				return BACKUP_DIRECTORY_EDEFAULT == null ? backupDirectory != null : !BACKUP_DIRECTORY_EDEFAULT.equals(backupDirectory);
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
		result.append(" (filePattern: ");
		result.append(filePattern);
		result.append(", backupDirectory: ");
		result.append(backupDirectory);
		result.append(')');
		return result.toString();
	}

} //FileTransferExtensionImpl
