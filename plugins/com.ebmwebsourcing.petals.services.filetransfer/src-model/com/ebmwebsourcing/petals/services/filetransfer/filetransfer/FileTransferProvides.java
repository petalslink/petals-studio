/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.ebmwebsourcing.petals.services.filetransfer.filetransfer;

import com.sun.java.xml.ns.jbi.Provides;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Provides</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferProvides#getWriteDirectory <em>Write Directory</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferProvides#getCopyMode <em>Copy Mode</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferPackage#getFileTransferProvides()
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
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferPackage#getFileTransferProvides_WriteDirectory()
	 * @model derived="true"
	 *        extendedMetaData="name='write-directory' kind='element' namespace='http://petals.ow2.org/components/filetransfer/version-2' group='#fileTransferExtContainer'"
	 * @generated
	 */
	String getWriteDirectory();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferProvides#getWriteDirectory <em>Write Directory</em>}' attribute.
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
	 * The literals are from the enumeration {@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer.CopyMode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Copy Mode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Copy Mode</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer.CopyMode
	 * @see #setCopyMode(CopyMode)
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferPackage#getFileTransferProvides_CopyMode()
	 * @model default="CopyMode.CONTENT_ONLY" derived="true"
	 *        extendedMetaData="name='copy-mode' kind='element' namespace='http://petals.ow2.org/components/filetransfer/version-2' group='#fileTransferExtContainer'"
	 * @generated
	 */
	CopyMode getCopyMode();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.filetransfer.filetransfer.FileTransferProvides#getCopyMode <em>Copy Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Copy Mode</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer.CopyMode
	 * @see #getCopyMode()
	 * @generated
	 */
	void setCopyMode(CopyMode value);

} // FileTransferProvides
