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

package com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x;

import com.sun.java.xml.ns.jbi.Consumes;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Consumes</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferConsumes#getReadDirectory <em>Read Directory</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferConsumes#getBackupDirectory <em>Backup Directory</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferConsumes#getTransferMode <em>Transfer Mode</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferConsumes#getFilePattern <em>File Pattern</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferConsumes#getPollingPeriod <em>Polling Period</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.Filetransfer2xPackage#getFileTransferConsumes()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface FileTransferConsumes extends Consumes {
	/**
	 * Returns the value of the '<em><b>Read Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Read Directory</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Read Directory</em>' attribute.
	 * @see #isSetReadDirectory()
	 * @see #unsetReadDirectory()
	 * @see #setReadDirectory(String)
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.Filetransfer2xPackage#getFileTransferConsumes_ReadDirectory()
	 * @model unsettable="true" required="true" derived="true"
	 *        extendedMetaData="name='read-directory' kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	String getReadDirectory();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferConsumes#getReadDirectory <em>Read Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Read Directory</em>' attribute.
	 * @see #isSetReadDirectory()
	 * @see #unsetReadDirectory()
	 * @see #getReadDirectory()
	 * @generated
	 */
	void setReadDirectory(String value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferConsumes#getReadDirectory <em>Read Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetReadDirectory()
	 * @see #getReadDirectory()
	 * @see #setReadDirectory(String)
	 * @generated
	 */
	void unsetReadDirectory();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferConsumes#getReadDirectory <em>Read Directory</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Read Directory</em>' attribute is set.
	 * @see #unsetReadDirectory()
	 * @see #getReadDirectory()
	 * @see #setReadDirectory(String)
	 * @generated
	 */
	boolean isSetReadDirectory();

	/**
	 * Returns the value of the '<em><b>Backup Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Backup Directory</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Backup Directory</em>' attribute.
	 * @see #setBackupDirectory(String)
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.Filetransfer2xPackage#getFileTransferConsumes_BackupDirectory()
	 * @model derived="true"
	 *        extendedMetaData="name='backup-directory' kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	String getBackupDirectory();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferConsumes#getBackupDirectory <em>Backup Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Backup Directory</em>' attribute.
	 * @see #getBackupDirectory()
	 * @generated
	 */
	void setBackupDirectory(String value);

	/**
	 * Returns the value of the '<em><b>Transfer Mode</b></em>' attribute.
	 * The default value is <code>"TransferMode.CONTENT"</code>.
	 * The literals are from the enumeration {@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.TransferMode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transfer Mode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transfer Mode</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.TransferMode
	 * @see #setTransferMode(TransferMode)
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.Filetransfer2xPackage#getFileTransferConsumes_TransferMode()
	 * @model default="TransferMode.CONTENT" derived="true"
	 *        extendedMetaData="name='transfer-mode' kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	TransferMode getTransferMode();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferConsumes#getTransferMode <em>Transfer Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transfer Mode</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.TransferMode
	 * @see #getTransferMode()
	 * @generated
	 */
	void setTransferMode(TransferMode value);

	/**
	 * Returns the value of the '<em><b>File Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File Pattern</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File Pattern</em>' attribute.
	 * @see #setFilePattern(String)
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.Filetransfer2xPackage#getFileTransferConsumes_FilePattern()
	 * @model derived="true"
	 *        extendedMetaData="name='file-pattern' kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	String getFilePattern();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferConsumes#getFilePattern <em>File Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File Pattern</em>' attribute.
	 * @see #getFilePattern()
	 * @generated
	 */
	void setFilePattern(String value);

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
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.Filetransfer2xPackage#getFileTransferConsumes_PollingPeriod()
	 * @model derived="true"
	 *        extendedMetaData="name='polling-period' kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	int getPollingPeriod();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferConsumes#getPollingPeriod <em>Polling Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Polling Period</em>' attribute.
	 * @see #getPollingPeriod()
	 * @generated
	 */
	void setPollingPeriod(int value);

} // FileTransferConsumes
