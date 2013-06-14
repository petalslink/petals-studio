/**
 * Copyright (c) 2012-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 */
package com.ebmwebsourcing.petals.services.filetransfer.filetransfer3;

import com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Consumes;

import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.TransferMode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>File Transfer3 Consumes</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getFolder <em>Folder</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getBackupDirectory <em>Backup Directory</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getTransferMode <em>Transfer Mode</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getFilename <em>Filename</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getPollingPeriod <em>Polling Period</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getBaseMessage <em>Base Message</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getProcessorPoolSize <em>Processor Pool Size</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getProcessorPoolTimeout <em>Processor Pool Timeout</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.Filetransfer3Package#getFileTransfer3Consumes()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface FileTransfer3Consumes extends CDK5Consumes {
	/**
	 * Returns the value of the '<em><b>Folder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Folder</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Folder</em>' attribute.
	 * @see #isSetFolder()
	 * @see #unsetFolder()
	 * @see #setFolder(String)
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.Filetransfer3Package#getFileTransfer3Consumes_Folder()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0' name='folder'"
	 * @generated
	 */
	String getFolder();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getFolder <em>Folder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Folder</em>' attribute.
	 * @see #isSetFolder()
	 * @see #unsetFolder()
	 * @see #getFolder()
	 * @generated
	 */
	void setFolder(String value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getFolder <em>Folder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetFolder()
	 * @see #getFolder()
	 * @see #setFolder(String)
	 * @generated
	 */
	void unsetFolder();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getFolder <em>Folder</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Folder</em>' attribute is set.
	 * @see #unsetFolder()
	 * @see #getFolder()
	 * @see #setFolder(String)
	 * @generated
	 */
	boolean isSetFolder();

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
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.Filetransfer3Package#getFileTransfer3Consumes_BackupDirectory()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0' name='backup-directory'"
	 * @generated
	 */
	String getBackupDirectory();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getBackupDirectory <em>Backup Directory</em>}' attribute.
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
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.Filetransfer3Package#getFileTransfer3Consumes_TransferMode()
	 * @model default="TransferMode.CONTENT"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0' name='transfer-mode'"
	 * @generated
	 */
	TransferMode getTransferMode();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getTransferMode <em>Transfer Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transfer Mode</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.TransferMode
	 * @see #getTransferMode()
	 * @generated
	 */
	void setTransferMode(TransferMode value);

	/**
	 * Returns the value of the '<em><b>Filename</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filename</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filename</em>' attribute.
	 * @see #setFilename(String)
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.Filetransfer3Package#getFileTransfer3Consumes_Filename()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0' name='filename'"
	 * @generated
	 */
	String getFilename();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getFilename <em>Filename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filename</em>' attribute.
	 * @see #getFilename()
	 * @generated
	 */
	void setFilename(String value);

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
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.Filetransfer3Package#getFileTransfer3Consumes_PollingPeriod()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0' name='polling-period'"
	 * @generated
	 */
	int getPollingPeriod();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getPollingPeriod <em>Polling Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Polling Period</em>' attribute.
	 * @see #getPollingPeriod()
	 * @generated
	 */
	void setPollingPeriod(int value);

	/**
	 * Returns the value of the '<em><b>Base Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Message</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Message</em>' attribute.
	 * @see #setBaseMessage(String)
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.Filetransfer3Package#getFileTransfer3Consumes_BaseMessage()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0' name='base-message'"
	 * @generated
	 */
	String getBaseMessage();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getBaseMessage <em>Base Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Message</em>' attribute.
	 * @see #getBaseMessage()
	 * @generated
	 */
	void setBaseMessage(String value);

	/**
	 * Returns the value of the '<em><b>Processor Pool Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Processor Pool Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Processor Pool Size</em>' attribute.
	 * @see #setProcessorPoolSize(int)
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.Filetransfer3Package#getFileTransfer3Consumes_ProcessorPoolSize()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0' name='processor-pool-size'"
	 * @generated
	 */
	int getProcessorPoolSize();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getProcessorPoolSize <em>Processor Pool Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Processor Pool Size</em>' attribute.
	 * @see #getProcessorPoolSize()
	 * @generated
	 */
	void setProcessorPoolSize(int value);

	/**
	 * Returns the value of the '<em><b>Processor Pool Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Processor Pool Timeout</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Processor Pool Timeout</em>' attribute.
	 * @see #setProcessorPoolTimeout(int)
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.Filetransfer3Package#getFileTransfer3Consumes_ProcessorPoolTimeout()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0' name='processor-pool-timeout'"
	 * @generated
	 */
	int getProcessorPoolTimeout();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes#getProcessorPoolTimeout <em>Processor Pool Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Processor Pool Timeout</em>' attribute.
	 * @see #getProcessorPoolTimeout()
	 * @generated
	 */
	void setProcessorPoolTimeout(int value);

} // FileTransfer3Consumes
