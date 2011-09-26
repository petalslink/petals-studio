/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.impl;

import com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.CopyMode;
import com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.FileTransferPackage;
import com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.FileTransferProvides;

import com.sun.java.xml.ns.jbi.impl.ProvidesImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Provides</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.impl.FileTransferProvidesImpl#getWriteDirectory <em>Write Directory</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.impl.FileTransferProvidesImpl#getCopyMode <em>Copy Mode</em>}</li>
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
		return FileTransferPackage.Literals.FILE_TRANSFER_PROVIDES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWriteDirectory() {
		return writeDirectory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWriteDirectory(String newWriteDirectory) {
		String oldWriteDirectory = writeDirectory;
		writeDirectory = newWriteDirectory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FileTransferPackage.FILE_TRANSFER_PROVIDES__WRITE_DIRECTORY, oldWriteDirectory, writeDirectory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CopyMode getCopyMode() {
		return copyMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCopyMode(CopyMode newCopyMode) {
		CopyMode oldCopyMode = copyMode;
		copyMode = newCopyMode == null ? COPY_MODE_EDEFAULT : newCopyMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FileTransferPackage.FILE_TRANSFER_PROVIDES__COPY_MODE, oldCopyMode, copyMode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FileTransferPackage.FILE_TRANSFER_PROVIDES__WRITE_DIRECTORY:
				return getWriteDirectory();
			case FileTransferPackage.FILE_TRANSFER_PROVIDES__COPY_MODE:
				return getCopyMode();
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
			case FileTransferPackage.FILE_TRANSFER_PROVIDES__WRITE_DIRECTORY:
				setWriteDirectory((String)newValue);
				return;
			case FileTransferPackage.FILE_TRANSFER_PROVIDES__COPY_MODE:
				setCopyMode((CopyMode)newValue);
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
			case FileTransferPackage.FILE_TRANSFER_PROVIDES__WRITE_DIRECTORY:
				setWriteDirectory(WRITE_DIRECTORY_EDEFAULT);
				return;
			case FileTransferPackage.FILE_TRANSFER_PROVIDES__COPY_MODE:
				setCopyMode(COPY_MODE_EDEFAULT);
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
			case FileTransferPackage.FILE_TRANSFER_PROVIDES__WRITE_DIRECTORY:
				return WRITE_DIRECTORY_EDEFAULT == null ? writeDirectory != null : !WRITE_DIRECTORY_EDEFAULT.equals(writeDirectory);
			case FileTransferPackage.FILE_TRANSFER_PROVIDES__COPY_MODE:
				return copyMode != COPY_MODE_EDEFAULT;
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
		result.append(writeDirectory);
		result.append(", copyMode: ");
		result.append(copyMode);
		result.append(')');
		return result.toString();
	}

} //FileTransferProvidesImpl
