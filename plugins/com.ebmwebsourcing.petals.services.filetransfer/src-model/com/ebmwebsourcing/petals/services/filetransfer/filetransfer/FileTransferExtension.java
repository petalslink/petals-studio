/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.ebmwebsourcing.petals.services.filetransfer.filetransfer;

import com.sun.java.xml.ns.jbi.AbstractExtensibleElement;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extension</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferExtension#getFilePattern <em>File Pattern</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferExtension#getReadDirectory <em>Read Directory</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferExtension#getBackupDirectory <em>Backup Directory</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferPackage#getFileTransferExtension()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface FileTransferExtension extends AbstractExtensibleElement {
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
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferPackage#getFileTransferExtension_FilePattern()
	 * @model derived="true"
	 *        extendedMetaData="name='file-pattern' kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	String getFilePattern();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferExtension#getFilePattern <em>File Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File Pattern</em>' attribute.
	 * @see #getFilePattern()
	 * @generated
	 */
	void setFilePattern(String value);

	/**
	 * Returns the value of the '<em><b>Read Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Read Directory</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Read Directory</em>' attribute.
	 * @see #setReadDirectory(String)
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferPackage#getFileTransferExtension_ReadDirectory()
	 * @model derived="true"
	 *        extendedMetaData="name='read-directory' kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	String getReadDirectory();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferExtension#getReadDirectory <em>Read Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Read Directory</em>' attribute.
	 * @see #getReadDirectory()
	 * @generated
	 */
	void setReadDirectory(String value);

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
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferPackage#getFileTransferExtension_BackupDirectory()
	 * @model derived="true"
	 *        extendedMetaData="name='backup-directory' kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	String getBackupDirectory();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferExtension#getBackupDirectory <em>Backup Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Backup Directory</em>' attribute.
	 * @see #getBackupDirectory()
	 * @generated
	 */
	void setBackupDirectory(String value);

} // FileTransferExtension
