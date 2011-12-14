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
package com.ebmwebsourcing.petals.services.mail.mail;

import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service Common</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailServiceCommon#getScheme <em>Scheme</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailServiceCommon#getHost <em>Host</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailServiceCommon#getPort <em>Port</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailServiceCommon#getUser <em>User</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailServiceCommon#getPassword <em>Password</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailServiceCommon()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface MailServiceCommon extends AbstractEndpoint {
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
	 * @see #setScheme(Scheme)
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailServiceCommon_Scheme()
	 * @model required="true" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	Scheme getScheme();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailServiceCommon#getScheme <em>Scheme</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scheme</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.Scheme
	 * @see #getScheme()
	 * @generated
	 */
	void setScheme(Scheme value);

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
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailServiceCommon_Host()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	String getHost();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailServiceCommon#getHost <em>Host</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Host</em>' attribute.
	 * @see #getHost()
	 * @generated
	 */
	void setHost(String value);

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
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailServiceCommon_Port()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	int getPort();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailServiceCommon#getPort <em>Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port</em>' attribute.
	 * @see #getPort()
	 * @generated
	 */
	void setPort(int value);

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
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailServiceCommon_User()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	String getUser();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailServiceCommon#getUser <em>User</em>}' attribute.
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
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailServiceCommon_Password()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	String getPassword();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailServiceCommon#getPassword <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Password</em>' attribute.
	 * @see #getPassword()
	 * @generated
	 */
	void setPassword(String value);

} // MailServiceCommon
