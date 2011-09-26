/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.impl;

import com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.FileTransferConsumes;
import com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.FileTransferPackage;
import com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.TransferMode;

import com.sun.java.xml.ns.jbi.impl.ConsumesImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Consumes</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.impl.FileTransferConsumesImpl#getTransferMode <em>Transfer Mode</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.impl.FileTransferConsumesImpl#getPollingPeriod <em>Polling Period</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FileTransferConsumesImpl extends ConsumesImpl implements FileTransferConsumes {
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
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
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (transferMode: ");
		result.append(transferMode);
		result.append(", pollingPeriod: ");
		result.append(pollingPeriod);
		result.append(')');
		return result.toString();
	}

} //FileTransferConsumesImpl
