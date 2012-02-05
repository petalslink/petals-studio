/**
 * Copyright (c) 2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x;

import com.sun.java.xml.ns.jbi.Provides;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>File Transfer Provides</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferProvides#getWriteDirectory <em>Write Directory</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferProvides#getCopyMode <em>Copy Mode</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferProvides#getReadDirectory <em>Read Directory</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.Filetransfer2xPackage#getFileTransferProvides()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface FileTransferProvides extends Provides, FileTransferExtension {
	/**
	 * Returns the value of the '<em><b>Write Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Write Directory</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Write Directory</em>' attribute.
	 * @see #setWriteDirectory(String)
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.Filetransfer2xPackage#getFileTransferProvides_WriteDirectory()
	 * @model derived="true"
	 *        extendedMetaData="name='write-directory' kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	String getWriteDirectory();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferProvides#getWriteDirectory <em>Write Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Write Directory</em>' attribute.
	 * @see #getWriteDirectory()
	 * @generated
	 */
	void setWriteDirectory(String value);

	/**
	 * Returns the value of the '<em><b>Copy Mode</b></em>' attribute.
	 * The default value is <code>"CopyMode.CONTENT_ONLY"</code>.
	 * The literals are from the enumeration {@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.CopyMode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Copy Mode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Copy Mode</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.CopyMode
	 * @see #setCopyMode(CopyMode)
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.Filetransfer2xPackage#getFileTransferProvides_CopyMode()
	 * @model default="CopyMode.CONTENT_ONLY" derived="true"
	 *        extendedMetaData="name='copy-mode' kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	CopyMode getCopyMode();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferProvides#getCopyMode <em>Copy Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Copy Mode</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.CopyMode
	 * @see #getCopyMode()
	 * @generated
	 */
	void setCopyMode(CopyMode value);

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
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.Filetransfer2xPackage#getFileTransferProvides_ReadDirectory()
	 * @model derived="true"
	 *        extendedMetaData="name='read-directory' kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	String getReadDirectory();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferProvides#getReadDirectory <em>Read Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Read Directory</em>' attribute.
	 * @see #getReadDirectory()
	 * @generated
	 */
	void setReadDirectory(String value);

} // FileTransferProvides
