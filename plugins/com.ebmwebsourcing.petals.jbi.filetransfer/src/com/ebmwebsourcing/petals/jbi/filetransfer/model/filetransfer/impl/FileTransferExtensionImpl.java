/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.impl;

import com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.FileTransferExtension;
import com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.FileTransferPackage;

import com.sun.java.xml.ns.jbi.impl.AbstractExtensibleElementImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extension</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.impl.FileTransferExtensionImpl#getFileTransferExtContainer <em>File Transfer Ext Container</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.impl.FileTransferExtensionImpl#getFilePattern <em>File Pattern</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.impl.FileTransferExtensionImpl#getReadDirectory <em>Read Directory</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.impl.FileTransferExtensionImpl#getBackupDirectory <em>Backup Directory</em>}</li>
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
		return FileTransferPackage.Literals.FILE_TRANSFER_EXTENSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getFileTransferExtContainer() {
		return (FeatureMap)getGroup().<FeatureMap.Entry>list(FileTransferPackage.Literals.FILE_TRANSFER_EXTENSION__FILE_TRANSFER_EXT_CONTAINER);
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
			eNotify(new ENotificationImpl(this, Notification.SET, FileTransferPackage.FILE_TRANSFER_EXTENSION__FILE_PATTERN, oldFilePattern, filePattern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getReadDirectory() {
		return readDirectory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReadDirectory(String newReadDirectory) {
		String oldReadDirectory = readDirectory;
		readDirectory = newReadDirectory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FileTransferPackage.FILE_TRANSFER_EXTENSION__READ_DIRECTORY, oldReadDirectory, readDirectory));
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
			eNotify(new ENotificationImpl(this, Notification.SET, FileTransferPackage.FILE_TRANSFER_EXTENSION__BACKUP_DIRECTORY, oldBackupDirectory, backupDirectory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FileTransferPackage.FILE_TRANSFER_EXTENSION__FILE_TRANSFER_EXT_CONTAINER:
				return ((InternalEList<?>)getFileTransferExtContainer()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FileTransferPackage.FILE_TRANSFER_EXTENSION__FILE_TRANSFER_EXT_CONTAINER:
				if (coreType) return getFileTransferExtContainer();
				return ((FeatureMap.Internal)getFileTransferExtContainer()).getWrapper();
			case FileTransferPackage.FILE_TRANSFER_EXTENSION__FILE_PATTERN:
				return getFilePattern();
			case FileTransferPackage.FILE_TRANSFER_EXTENSION__READ_DIRECTORY:
				return getReadDirectory();
			case FileTransferPackage.FILE_TRANSFER_EXTENSION__BACKUP_DIRECTORY:
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
			case FileTransferPackage.FILE_TRANSFER_EXTENSION__FILE_TRANSFER_EXT_CONTAINER:
				((FeatureMap.Internal)getFileTransferExtContainer()).set(newValue);
				return;
			case FileTransferPackage.FILE_TRANSFER_EXTENSION__FILE_PATTERN:
				setFilePattern((String)newValue);
				return;
			case FileTransferPackage.FILE_TRANSFER_EXTENSION__READ_DIRECTORY:
				setReadDirectory((String)newValue);
				return;
			case FileTransferPackage.FILE_TRANSFER_EXTENSION__BACKUP_DIRECTORY:
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
			case FileTransferPackage.FILE_TRANSFER_EXTENSION__FILE_TRANSFER_EXT_CONTAINER:
				getFileTransferExtContainer().clear();
				return;
			case FileTransferPackage.FILE_TRANSFER_EXTENSION__FILE_PATTERN:
				setFilePattern(FILE_PATTERN_EDEFAULT);
				return;
			case FileTransferPackage.FILE_TRANSFER_EXTENSION__READ_DIRECTORY:
				setReadDirectory(READ_DIRECTORY_EDEFAULT);
				return;
			case FileTransferPackage.FILE_TRANSFER_EXTENSION__BACKUP_DIRECTORY:
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
			case FileTransferPackage.FILE_TRANSFER_EXTENSION__FILE_TRANSFER_EXT_CONTAINER:
				return !getFileTransferExtContainer().isEmpty();
			case FileTransferPackage.FILE_TRANSFER_EXTENSION__FILE_PATTERN:
				return FILE_PATTERN_EDEFAULT == null ? filePattern != null : !FILE_PATTERN_EDEFAULT.equals(filePattern);
			case FileTransferPackage.FILE_TRANSFER_EXTENSION__READ_DIRECTORY:
				return READ_DIRECTORY_EDEFAULT == null ? readDirectory != null : !READ_DIRECTORY_EDEFAULT.equals(readDirectory);
			case FileTransferPackage.FILE_TRANSFER_EXTENSION__BACKUP_DIRECTORY:
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
		result.append(", readDirectory: ");
		result.append(readDirectory);
		result.append(", backupDirectory: ");
		result.append(backupDirectory);
		result.append(')');
		return result.toString();
	}

} //FileTransferExtensionImpl
