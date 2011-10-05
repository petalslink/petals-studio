/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.ebmwebsourcing.petals.services.filetransfer.filetransfer.impl;

import com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferConsumes;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferExtension;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferPackage;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer.TransferMode;

import com.sun.java.xml.ns.jbi.impl.ConsumesImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Consumes</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer.impl.FileTransferConsumesImpl#getFileTransferExtContainer <em>File Transfer Ext Container</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer.impl.FileTransferConsumesImpl#getFilePattern <em>File Pattern</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer.impl.FileTransferConsumesImpl#getReadDirectory <em>Read Directory</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer.impl.FileTransferConsumesImpl#getBackupDirectory <em>Backup Directory</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer.impl.FileTransferConsumesImpl#getTransferMode <em>Transfer Mode</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer.impl.FileTransferConsumesImpl#getPollingPeriod <em>Polling Period</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FileTransferConsumesImpl extends ConsumesImpl implements FileTransferConsumes {
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
		return FileTransferPackage.Literals.FILE_TRANSFER_CONSUMES;
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
			eNotify(new ENotificationImpl(this, Notification.SET, FileTransferPackage.FILE_TRANSFER_CONSUMES__FILE_PATTERN, oldFilePattern, filePattern));
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
			eNotify(new ENotificationImpl(this, Notification.SET, FileTransferPackage.FILE_TRANSFER_CONSUMES__READ_DIRECTORY, oldReadDirectory, readDirectory));
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
			eNotify(new ENotificationImpl(this, Notification.SET, FileTransferPackage.FILE_TRANSFER_CONSUMES__BACKUP_DIRECTORY, oldBackupDirectory, backupDirectory));
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
			eNotify(new ENotificationImpl(this, Notification.SET, FileTransferPackage.FILE_TRANSFER_CONSUMES__TRANSFER_MODE, oldTransferMode, transferMode));
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
			eNotify(new ENotificationImpl(this, Notification.SET, FileTransferPackage.FILE_TRANSFER_CONSUMES__POLLING_PERIOD, oldPollingPeriod, pollingPeriod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FileTransferPackage.FILE_TRANSFER_CONSUMES__FILE_TRANSFER_EXT_CONTAINER:
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
			case FileTransferPackage.FILE_TRANSFER_CONSUMES__FILE_TRANSFER_EXT_CONTAINER:
				if (coreType) return getFileTransferExtContainer();
				return ((FeatureMap.Internal)getFileTransferExtContainer()).getWrapper();
			case FileTransferPackage.FILE_TRANSFER_CONSUMES__FILE_PATTERN:
				return getFilePattern();
			case FileTransferPackage.FILE_TRANSFER_CONSUMES__READ_DIRECTORY:
				return getReadDirectory();
			case FileTransferPackage.FILE_TRANSFER_CONSUMES__BACKUP_DIRECTORY:
				return getBackupDirectory();
			case FileTransferPackage.FILE_TRANSFER_CONSUMES__TRANSFER_MODE:
				return getTransferMode();
			case FileTransferPackage.FILE_TRANSFER_CONSUMES__POLLING_PERIOD:
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
			case FileTransferPackage.FILE_TRANSFER_CONSUMES__FILE_TRANSFER_EXT_CONTAINER:
				((FeatureMap.Internal)getFileTransferExtContainer()).set(newValue);
				return;
			case FileTransferPackage.FILE_TRANSFER_CONSUMES__FILE_PATTERN:
				setFilePattern((String)newValue);
				return;
			case FileTransferPackage.FILE_TRANSFER_CONSUMES__READ_DIRECTORY:
				setReadDirectory((String)newValue);
				return;
			case FileTransferPackage.FILE_TRANSFER_CONSUMES__BACKUP_DIRECTORY:
				setBackupDirectory((String)newValue);
				return;
			case FileTransferPackage.FILE_TRANSFER_CONSUMES__TRANSFER_MODE:
				setTransferMode((TransferMode)newValue);
				return;
			case FileTransferPackage.FILE_TRANSFER_CONSUMES__POLLING_PERIOD:
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
			case FileTransferPackage.FILE_TRANSFER_CONSUMES__FILE_TRANSFER_EXT_CONTAINER:
				getFileTransferExtContainer().clear();
				return;
			case FileTransferPackage.FILE_TRANSFER_CONSUMES__FILE_PATTERN:
				setFilePattern(FILE_PATTERN_EDEFAULT);
				return;
			case FileTransferPackage.FILE_TRANSFER_CONSUMES__READ_DIRECTORY:
				setReadDirectory(READ_DIRECTORY_EDEFAULT);
				return;
			case FileTransferPackage.FILE_TRANSFER_CONSUMES__BACKUP_DIRECTORY:
				setBackupDirectory(BACKUP_DIRECTORY_EDEFAULT);
				return;
			case FileTransferPackage.FILE_TRANSFER_CONSUMES__TRANSFER_MODE:
				setTransferMode(TRANSFER_MODE_EDEFAULT);
				return;
			case FileTransferPackage.FILE_TRANSFER_CONSUMES__POLLING_PERIOD:
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
			case FileTransferPackage.FILE_TRANSFER_CONSUMES__FILE_TRANSFER_EXT_CONTAINER:
				return !getFileTransferExtContainer().isEmpty();
			case FileTransferPackage.FILE_TRANSFER_CONSUMES__FILE_PATTERN:
				return FILE_PATTERN_EDEFAULT == null ? filePattern != null : !FILE_PATTERN_EDEFAULT.equals(filePattern);
			case FileTransferPackage.FILE_TRANSFER_CONSUMES__READ_DIRECTORY:
				return READ_DIRECTORY_EDEFAULT == null ? readDirectory != null : !READ_DIRECTORY_EDEFAULT.equals(readDirectory);
			case FileTransferPackage.FILE_TRANSFER_CONSUMES__BACKUP_DIRECTORY:
				return BACKUP_DIRECTORY_EDEFAULT == null ? backupDirectory != null : !BACKUP_DIRECTORY_EDEFAULT.equals(backupDirectory);
			case FileTransferPackage.FILE_TRANSFER_CONSUMES__TRANSFER_MODE:
				return transferMode != TRANSFER_MODE_EDEFAULT;
			case FileTransferPackage.FILE_TRANSFER_CONSUMES__POLLING_PERIOD:
				return pollingPeriod != POLLING_PERIOD_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == FileTransferExtension.class) {
			switch (derivedFeatureID) {
				case FileTransferPackage.FILE_TRANSFER_CONSUMES__FILE_TRANSFER_EXT_CONTAINER: return FileTransferPackage.FILE_TRANSFER_EXTENSION__FILE_TRANSFER_EXT_CONTAINER;
				case FileTransferPackage.FILE_TRANSFER_CONSUMES__FILE_PATTERN: return FileTransferPackage.FILE_TRANSFER_EXTENSION__FILE_PATTERN;
				case FileTransferPackage.FILE_TRANSFER_CONSUMES__READ_DIRECTORY: return FileTransferPackage.FILE_TRANSFER_EXTENSION__READ_DIRECTORY;
				case FileTransferPackage.FILE_TRANSFER_CONSUMES__BACKUP_DIRECTORY: return FileTransferPackage.FILE_TRANSFER_EXTENSION__BACKUP_DIRECTORY;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == FileTransferExtension.class) {
			switch (baseFeatureID) {
				case FileTransferPackage.FILE_TRANSFER_EXTENSION__FILE_TRANSFER_EXT_CONTAINER: return FileTransferPackage.FILE_TRANSFER_CONSUMES__FILE_TRANSFER_EXT_CONTAINER;
				case FileTransferPackage.FILE_TRANSFER_EXTENSION__FILE_PATTERN: return FileTransferPackage.FILE_TRANSFER_CONSUMES__FILE_PATTERN;
				case FileTransferPackage.FILE_TRANSFER_EXTENSION__READ_DIRECTORY: return FileTransferPackage.FILE_TRANSFER_CONSUMES__READ_DIRECTORY;
				case FileTransferPackage.FILE_TRANSFER_EXTENSION__BACKUP_DIRECTORY: return FileTransferPackage.FILE_TRANSFER_CONSUMES__BACKUP_DIRECTORY;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(", transferMode: ");
		result.append(transferMode);
		result.append(", pollingPeriod: ");
		result.append(pollingPeriod);
		result.append(')');
		return result.toString();
	}

} //FileTransferConsumesImpl
