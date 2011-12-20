/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.ebmwebsourcing.petals.services.filetransfer.filetransfer;

import com.sun.java.xml.ns.jbi.Consumes;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Consumes</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferConsumes#getTransferMode <em>Transfer Mode</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferConsumes#getPollingPeriod <em>Polling Period</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferPackage#getFileTransferConsumes()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface FileTransferConsumes extends Consumes, FileTransferExtension {
	/**
	 * Returns the value of the '<em><b>Transfer Mode</b></em>' attribute.
	 * The default value is <code>"TransferMode.CONTENT"</code>.
	 * The literals are from the enumeration {@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer.TransferMode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transfer Mode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transfer Mode</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer.TransferMode
	 * @see #setTransferMode(TransferMode)
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferPackage#getFileTransferConsumes_TransferMode()
	 * @model default="TransferMode.CONTENT" derived="true"
	 *        extendedMetaData="name='transfer-mode' kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	TransferMode getTransferMode();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferConsumes#getTransferMode <em>Transfer Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transfer Mode</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer.TransferMode
	 * @see #getTransferMode()
	 * @generated
	 */
	void setTransferMode(TransferMode value);

	/**
	 * Returns the value of the '<em><b>Polling Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Polling Period</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Polling Period</em>' attribute.
	 * @see #setPollingPeriod(int)
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferPackage#getFileTransferConsumes_PollingPeriod()
	 * @model derived="true"
	 *        extendedMetaData="name='polling-period' kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	int getPollingPeriod();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferConsumes#getPollingPeriod <em>Polling Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Polling Period</em>' attribute.
	 * @see #getPollingPeriod()
	 * @generated
	 */
	void setPollingPeriod(int value);

} // FileTransferConsumes
