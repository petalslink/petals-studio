/**
 * Copyright (c) 2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.filetransfer.filetransfer3;

import com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Provides;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>File Transfer3 Provides</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Provides#getFilename <em>Filename</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Provides#getBackupDirectory <em>Backup Directory</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Provides#getFolder <em>Folder</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.Filetransfer3Package#getFileTransfer3Provides()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface FileTransfer3Provides extends CDK5Provides {
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
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.Filetransfer3Package#getFileTransfer3Provides_Filename()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0' name='filename'"
	 * @generated
	 */
	String getFilename();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Provides#getFilename <em>Filename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filename</em>' attribute.
	 * @see #getFilename()
	 * @generated
	 */
	void setFilename(String value);

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
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.Filetransfer3Package#getFileTransfer3Provides_BackupDirectory()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0' name='backup-directory'"
	 * @generated
	 */
	String getBackupDirectory();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Provides#getBackupDirectory <em>Backup Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Backup Directory</em>' attribute.
	 * @see #getBackupDirectory()
	 * @generated
	 */
	void setBackupDirectory(String value);

	/**
	 * Returns the value of the '<em><b>Folder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Folder</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Folder</em>' attribute.
	 * @see #setFolder(String)
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.Filetransfer3Package#getFileTransfer3Provides_Folder()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0' name='folder'"
	 * @generated
	 */
	String getFolder();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Provides#getFolder <em>Folder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Folder</em>' attribute.
	 * @see #getFolder()
	 * @generated
	 */
	void setFolder(String value);

} // FileTransfer3Provides
