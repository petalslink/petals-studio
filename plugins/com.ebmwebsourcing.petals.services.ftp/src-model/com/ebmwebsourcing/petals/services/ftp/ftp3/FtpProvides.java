/**
 * Copyright (c) 2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.ftp.ftp3;

import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ftp Provides</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getConnectionMode <em>Connection Mode</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#isDeleteProcessedFiles <em>Delete Processed Files</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getFilename <em>Filename</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getFolder <em>Folder</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#isOverwrite <em>Overwrite</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getPassword <em>Password</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getPort <em>Port</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getServer <em>Server</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getUser <em>User</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getTransferType <em>Transfer Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package#getFtpProvides()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface FtpProvides extends CDK5Provides {
	/**
	 * Returns the value of the '<em><b>Connection Mode</b></em>' attribute.
	 * The literals are from the enumeration {@link com.ebmwebsourcing.petals.services.ftp.ftp3.ConnectionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connection Mode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connection Mode</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.ConnectionType
	 * @see #setConnectionMode(ConnectionType)
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package#getFtpProvides_ConnectionMode()
	 * @model extendedMetaData="name='connection-mode' namespace='##targetNamespace' type='element' group='#group:0'"
	 * @generated
	 */
	ConnectionType getConnectionMode();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getConnectionMode <em>Connection Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connection Mode</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.ConnectionType
	 * @see #getConnectionMode()
	 * @generated
	 */
	void setConnectionMode(ConnectionType value);

	/**
	 * Returns the value of the '<em><b>Delete Processed Files</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Delete Processed Files</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Delete Processed Files</em>' attribute.
	 * @see #setDeleteProcessedFiles(boolean)
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package#getFtpProvides_DeleteProcessedFiles()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="name='delete-processed-files' namespace='##targetNamespace' type='element' group='#group:0'"
	 * @generated
	 */
	boolean isDeleteProcessedFiles();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#isDeleteProcessedFiles <em>Delete Processed Files</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Delete Processed Files</em>' attribute.
	 * @see #isDeleteProcessedFiles()
	 * @generated
	 */
	void setDeleteProcessedFiles(boolean value);

	/**
	 * Returns the value of the '<em><b>Filename</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filename</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filename</em>' attribute.
	 * @see #setFilename(String)
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package#getFtpProvides_Filename()
	 * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' type='element' group='#group:0'"
	 * @generated
	 */
	String getFilename();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getFilename <em>Filename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filename</em>' attribute.
	 * @see #getFilename()
	 * @generated
	 */
	void setFilename(String value);

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
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package#getFtpProvides_Folder()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' type='element' group='#group:0'"
	 * @generated
	 */
	String getFolder();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getFolder <em>Folder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Folder</em>' attribute.
	 * @see #getFolder()
	 * @generated
	 */
	void setFolder(String value);

	/**
	 * Returns the value of the '<em><b>Overwrite</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Overwrite</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Overwrite</em>' attribute.
	 * @see #setOverwrite(boolean)
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package#getFtpProvides_Overwrite()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="namespace='##targetNamespace' type='element' group='#group:0'"
	 * @generated
	 */
	boolean isOverwrite();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#isOverwrite <em>Overwrite</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Overwrite</em>' attribute.
	 * @see #isOverwrite()
	 * @generated
	 */
	void setOverwrite(boolean value);

	/**
	 * Returns the value of the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Password</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Password</em>' attribute.
	 * @see #setPassword(String)
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package#getFtpProvides_Password()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' type='element' group='#group:0'"
	 * @generated
	 */
	String getPassword();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getPassword <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Password</em>' attribute.
	 * @see #getPassword()
	 * @generated
	 */
	void setPassword(String value);

	/**
	 * Returns the value of the '<em><b>Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port</em>' attribute.
	 * @see #setPort(int)
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package#getFtpProvides_Port()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int"
	 *        extendedMetaData="namespace='##targetNamespace' type='element' group='#group:0'"
	 * @generated
	 */
	int getPort();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getPort <em>Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port</em>' attribute.
	 * @see #getPort()
	 * @generated
	 */
	void setPort(int value);

	/**
	 * Returns the value of the '<em><b>Server</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Server</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Server</em>' attribute.
	 * @see #setServer(String)
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package#getFtpProvides_Server()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' type='element' group='#group:0'"
	 * @generated
	 */
	String getServer();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getServer <em>Server</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Server</em>' attribute.
	 * @see #getServer()
	 * @generated
	 */
	void setServer(String value);

	/**
	 * Returns the value of the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User</em>' attribute.
	 * @see #setUser(String)
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package#getFtpProvides_User()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="namespace='##targetNamespace' type='element' group='#group:0'"
	 * @generated
	 */
	String getUser();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getUser <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User</em>' attribute.
	 * @see #getUser()
	 * @generated
	 */
	void setUser(String value);

	/**
	 * Returns the value of the '<em><b>Transfer Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.ebmwebsourcing.petals.services.ftp.ftp3.TransferType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transfer Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transfer Type</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.TransferType
	 * @see #setTransferType(TransferType)
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package#getFtpProvides_TransferType()
	 * @model extendedMetaData="namespace='##targetNamespace' type='element' name='transfer-type' group='#group:0'"
	 * @generated
	 */
	TransferType getTransferType();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getTransferType <em>Transfer Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transfer Type</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.TransferType
	 * @see #getTransferType()
	 * @generated
	 */
	void setTransferType(TransferType value);

} // FtpProvides
