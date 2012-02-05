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

import com.sun.java.xml.ns.jbi.Provides;

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
 *   <li>{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getEncoding <em>Encoding</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getMaxIdleTime <em>Max Idle Time</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getMaxConnection <em>Max Connection</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package#getFtpProvides()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface FtpProvides extends Provides {
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
	 * @model derived="true"
	 *        extendedMetaData="name='connection-mode' namespace='##targetNamespace' kind='element' group='#group:0'"
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
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean" derived="true"
	 *        extendedMetaData="name='delete-processed-files' namespace='##targetNamespace' kind='element' group='#group:0'"
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
	 * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0'"
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
	 * @see #isSetFolder()
	 * @see #unsetFolder()
	 * @see #setFolder(String)
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package#getFtpProvides_Folder()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getFolder();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getFolder <em>Folder</em>}' attribute.
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
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getFolder <em>Folder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetFolder()
	 * @see #getFolder()
	 * @see #setFolder(String)
	 * @generated
	 */
	void unsetFolder();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getFolder <em>Folder</em>}' attribute is set.
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
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0'"
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
	 * @see #isSetPassword()
	 * @see #unsetPassword()
	 * @see #setPassword(String)
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package#getFtpProvides_Password()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getPassword();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getPassword <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Password</em>' attribute.
	 * @see #isSetPassword()
	 * @see #unsetPassword()
	 * @see #getPassword()
	 * @generated
	 */
	void setPassword(String value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getPassword <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPassword()
	 * @see #getPassword()
	 * @see #setPassword(String)
	 * @generated
	 */
	void unsetPassword();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getPassword <em>Password</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Password</em>' attribute is set.
	 * @see #unsetPassword()
	 * @see #getPassword()
	 * @see #setPassword(String)
	 * @generated
	 */
	boolean isSetPassword();

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
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0'"
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
	 * @see #isSetServer()
	 * @see #unsetServer()
	 * @see #setServer(String)
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package#getFtpProvides_Server()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getServer();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getServer <em>Server</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Server</em>' attribute.
	 * @see #isSetServer()
	 * @see #unsetServer()
	 * @see #getServer()
	 * @generated
	 */
	void setServer(String value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getServer <em>Server</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetServer()
	 * @see #getServer()
	 * @see #setServer(String)
	 * @generated
	 */
	void unsetServer();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getServer <em>Server</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Server</em>' attribute is set.
	 * @see #unsetServer()
	 * @see #getServer()
	 * @see #setServer(String)
	 * @generated
	 */
	boolean isSetServer();

	/**
	 * Returns the value of the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User</em>' attribute.
	 * @see #isSetUser()
	 * @see #unsetUser()
	 * @see #setUser(String)
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package#getFtpProvides_User()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getUser();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getUser <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User</em>' attribute.
	 * @see #isSetUser()
	 * @see #unsetUser()
	 * @see #getUser()
	 * @generated
	 */
	void setUser(String value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getUser <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetUser()
	 * @see #getUser()
	 * @see #setUser(String)
	 * @generated
	 */
	void unsetUser();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getUser <em>User</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>User</em>' attribute is set.
	 * @see #unsetUser()
	 * @see #getUser()
	 * @see #setUser(String)
	 * @generated
	 */
	boolean isSetUser();

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
	 * @model derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' name='transfer-type' group='#group:0'"
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

	/**
	 * Returns the value of the '<em><b>Encoding</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Encoding</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Encoding</em>' attribute.
	 * @see #setEncoding(String)
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package#getFtpProvides_Encoding()
	 * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getEncoding();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getEncoding <em>Encoding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Encoding</em>' attribute.
	 * @see #getEncoding()
	 * @generated
	 */
	void setEncoding(String value);

	/**
	 * Returns the value of the '<em><b>Max Idle Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Idle Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Idle Time</em>' attribute.
	 * @see #setMaxIdleTime(int)
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package#getFtpProvides_MaxIdleTime()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0' name='max-idle-time'"
	 * @generated
	 */
	int getMaxIdleTime();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getMaxIdleTime <em>Max Idle Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Idle Time</em>' attribute.
	 * @see #getMaxIdleTime()
	 * @generated
	 */
	void setMaxIdleTime(int value);

	/**
	 * Returns the value of the '<em><b>Max Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Connection</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Connection</em>' attribute.
	 * @see #setMaxConnection(int)
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package#getFtpProvides_MaxConnection()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0' name='max-connection'"
	 * @generated
	 */
	int getMaxConnection();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides#getMaxConnection <em>Max Connection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Connection</em>' attribute.
	 * @see #getMaxConnection()
	 * @generated
	 */
	void setMaxConnection(int value);

} // FtpProvides
