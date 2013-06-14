/**
 * Copyright (c) 2011-2013, Linagora
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 */
package com.ebmwebsourcing.petals.services.mail.mail;

import com.sun.java.xml.ns.jbi.Consumes;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Consumes</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getScheme <em>Scheme</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getHost <em>Host</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getPort <em>Port</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getUser <em>User</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getPassword <em>Password</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getFolder <em>Folder</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#isDelete <em>Delete</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getPeriod <em>Period</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#isIsxmlcontent <em>Isxmlcontent</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailConsumes()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface MailConsumes extends Consumes {
	/**
	 * Returns the value of the '<em><b>Scheme</b></em>' attribute.
	 * The literals are from the enumeration {@link com.ebmwebsourcing.petals.services.mail.mail.Scheme}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scheme</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scheme</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.Scheme
	 * @see #isSetScheme()
	 * @see #unsetScheme()
	 * @see #setScheme(Scheme)
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailConsumes_Scheme()
	 * @model unsettable="true" required="true" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	Scheme getScheme();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getScheme <em>Scheme</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scheme</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.Scheme
	 * @see #isSetScheme()
	 * @see #unsetScheme()
	 * @see #getScheme()
	 * @generated
	 */
	void setScheme(Scheme value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getScheme <em>Scheme</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetScheme()
	 * @see #getScheme()
	 * @see #setScheme(Scheme)
	 * @generated
	 */
	void unsetScheme();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getScheme <em>Scheme</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Scheme</em>' attribute is set.
	 * @see #unsetScheme()
	 * @see #getScheme()
	 * @see #setScheme(Scheme)
	 * @generated
	 */
	boolean isSetScheme();

	/**
	 * Returns the value of the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Host</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Host</em>' attribute.
	 * @see #isSetHost()
	 * @see #unsetHost()
	 * @see #setHost(String)
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailConsumes_Host()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	String getHost();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getHost <em>Host</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Host</em>' attribute.
	 * @see #isSetHost()
	 * @see #unsetHost()
	 * @see #getHost()
	 * @generated
	 */
	void setHost(String value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getHost <em>Host</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetHost()
	 * @see #getHost()
	 * @see #setHost(String)
	 * @generated
	 */
	void unsetHost();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getHost <em>Host</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Host</em>' attribute is set.
	 * @see #unsetHost()
	 * @see #getHost()
	 * @see #setHost(String)
	 * @generated
	 */
	boolean isSetHost();

	/**
	 * Returns the value of the '<em><b>Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port</em>' attribute.
	 * @see #isSetPort()
	 * @see #unsetPort()
	 * @see #setPort(int)
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailConsumes_Port()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	int getPort();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getPort <em>Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port</em>' attribute.
	 * @see #isSetPort()
	 * @see #unsetPort()
	 * @see #getPort()
	 * @generated
	 */
	void setPort(int value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getPort <em>Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPort()
	 * @see #getPort()
	 * @see #setPort(int)
	 * @generated
	 */
	void unsetPort();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getPort <em>Port</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Port</em>' attribute is set.
	 * @see #unsetPort()
	 * @see #getPort()
	 * @see #setPort(int)
	 * @generated
	 */
	boolean isSetPort();

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
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailConsumes_User()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	String getUser();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getUser <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User</em>' attribute.
	 * @see #getUser()
	 * @generated
	 */
	void setUser(String value);

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
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailConsumes_Password()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	String getPassword();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getPassword <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Password</em>' attribute.
	 * @see #getPassword()
	 * @generated
	 */
	void setPassword(String value);

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
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailConsumes_Folder()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	String getFolder();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getFolder <em>Folder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Folder</em>' attribute.
	 * @see #getFolder()
	 * @generated
	 */
	void setFolder(String value);

	/**
	 * Returns the value of the '<em><b>Delete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Delete</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Delete</em>' attribute.
	 * @see #setDelete(boolean)
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailConsumes_Delete()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	boolean isDelete();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#isDelete <em>Delete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Delete</em>' attribute.
	 * @see #isDelete()
	 * @generated
	 */
	void setDelete(boolean value);

	/**
	 * Returns the value of the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Period</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Period</em>' attribute.
	 * @see #setPeriod(int)
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailConsumes_Period()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" required="true" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	int getPeriod();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getPeriod <em>Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Period</em>' attribute.
	 * @see #getPeriod()
	 * @generated
	 */
	void setPeriod(int value);

	/**
	 * Returns the value of the '<em><b>Isxmlcontent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Isxmlcontent</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Isxmlcontent</em>' attribute.
	 * @see #setIsxmlcontent(boolean)
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailConsumes_Isxmlcontent()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	boolean isIsxmlcontent();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#isIsxmlcontent <em>Isxmlcontent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Isxmlcontent</em>' attribute.
	 * @see #isIsxmlcontent()
	 * @generated
	 */
	void setIsxmlcontent(boolean value);

} // MailConsumes
