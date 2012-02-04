/**
 * Copyright (c) 2011-2012, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 */
package com.ebmwebsourcing.petals.services.sftp.sftp;

import com.sun.java.xml.ns.jbi.Provides;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Provides</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getFolder <em>Folder</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getServer <em>Server</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#isOverwrite <em>Overwrite</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getPassphrase <em>Passphrase</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getPassword <em>Password</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getPort <em>Port</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getPrivatekey <em>Privatekey</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getUser <em>User</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getMaxIdleTime <em>Max Idle Time</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getMaxConnection <em>Max Connection</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpPackage#getSftpProvides()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface SftpProvides extends Provides {
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
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpPackage#getSftpProvides_Folder()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getFolder();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getFolder <em>Folder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Folder</em>' attribute.
	 * @see #getFolder()
	 * @generated
	 */
	void setFolder(String value);

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
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpPackage#getSftpProvides_Server()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getServer();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getServer <em>Server</em>}' attribute.
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
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getServer <em>Server</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetServer()
	 * @see #getServer()
	 * @see #setServer(String)
	 * @generated
	 */
	void unsetServer();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getServer <em>Server</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Max Idle Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Idle Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Idle Time</em>' attribute.
	 * @see #setMaxIdleTime(int)
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpPackage#getSftpProvides_MaxIdleTime()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0' name='max-idle-time'"
	 * @generated
	 */
	int getMaxIdleTime();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getMaxIdleTime <em>Max Idle Time</em>}' attribute.
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
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpPackage#getSftpProvides_MaxConnection()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0' name='max-connection'"
	 * @generated
	 */
	int getMaxConnection();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getMaxConnection <em>Max Connection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Connection</em>' attribute.
	 * @see #getMaxConnection()
	 * @generated
	 */
	void setMaxConnection(int value);

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
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpPackage#getSftpProvides_Overwrite()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	boolean isOverwrite();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#isOverwrite <em>Overwrite</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Overwrite</em>' attribute.
	 * @see #isOverwrite()
	 * @generated
	 */
	void setOverwrite(boolean value);

	/**
	 * Returns the value of the '<em><b>Passphrase</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Passphrase</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Passphrase</em>' attribute.
	 * @see #setPassphrase(String)
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpPackage#getSftpProvides_Passphrase()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getPassphrase();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getPassphrase <em>Passphrase</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Passphrase</em>' attribute.
	 * @see #getPassphrase()
	 * @generated
	 */
	void setPassphrase(String value);

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
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpPackage#getSftpProvides_Password()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getPassword();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getPassword <em>Password</em>}' attribute.
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
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getPassword <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPassword()
	 * @see #getPassword()
	 * @see #setPassword(String)
	 * @generated
	 */
	void unsetPassword();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getPassword <em>Password</em>}' attribute is set.
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
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpPackage#getSftpProvides_Port()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	int getPort();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getPort <em>Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port</em>' attribute.
	 * @see #getPort()
	 * @generated
	 */
	void setPort(int value);

	/**
	 * Returns the value of the '<em><b>Privatekey</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Privatekey</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Privatekey</em>' attribute.
	 * @see #setPrivatekey(String)
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpPackage#getSftpProvides_Privatekey()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getPrivatekey();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getPrivatekey <em>Privatekey</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Privatekey</em>' attribute.
	 * @see #getPrivatekey()
	 * @generated
	 */
	void setPrivatekey(String value);

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
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpPackage#getSftpProvides_User()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getUser();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getUser <em>User</em>}' attribute.
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
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getUser <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetUser()
	 * @see #getUser()
	 * @see #setUser(String)
	 * @generated
	 */
	void unsetUser();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getUser <em>User</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>User</em>' attribute is set.
	 * @see #unsetUser()
	 * @see #getUser()
	 * @see #setUser(String)
	 * @generated
	 */
	boolean isSetUser();

} // SftpProvides
