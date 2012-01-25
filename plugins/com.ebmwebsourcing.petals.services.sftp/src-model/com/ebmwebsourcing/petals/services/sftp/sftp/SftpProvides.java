/**
 * Copyright (c) 2011, EBM WebSourcing
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
 *   <li>{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getHost <em>Host</em>}</li>
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
	 * Returns the value of the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Host</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Host</em>' attribute.
	 * @see #setHost(String)
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpPackage#getSftpProvides_Host()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getHost();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getHost <em>Host</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Host</em>' attribute.
	 * @see #getHost()
	 * @generated
	 */
	void setHost(String value);

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
	 * @see #setPassword(String)
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpPackage#getSftpProvides_Password()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getPassword();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getPassword <em>Password</em>}' attribute.
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
	 * @see #setUser(String)
	 * @see com.ebmwebsourcing.petals.services.sftp.sftp.SftpPackage#getSftpProvides_User()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getUser();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides#getUser <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User</em>' attribute.
	 * @see #getUser()
	 * @generated
	 */
	void setUser(String value);

} // SftpProvides
