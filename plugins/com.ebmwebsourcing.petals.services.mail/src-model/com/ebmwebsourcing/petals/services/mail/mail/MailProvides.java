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

import com.sun.java.xml.ns.jbi.Provides;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Provides</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getScheme <em>Scheme</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getHost <em>Host</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getPort <em>Port</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getUser <em>User</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getPassword <em>Password</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getFrom <em>From</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getReply <em>Reply</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getTo <em>To</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getSubject <em>Subject</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getHelohost <em>Helohost</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getSendMode <em>Send Mode</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getContentType <em>Content Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailProvides()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface MailProvides extends Provides {
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
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailProvides_Scheme()
	 * @model unsettable="true" required="true" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	Scheme getScheme();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getScheme <em>Scheme</em>}' attribute.
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
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getScheme <em>Scheme</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetScheme()
	 * @see #getScheme()
	 * @see #setScheme(Scheme)
	 * @generated
	 */
	void unsetScheme();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getScheme <em>Scheme</em>}' attribute is set.
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
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailProvides_Host()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	String getHost();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getHost <em>Host</em>}' attribute.
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
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getHost <em>Host</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetHost()
	 * @see #getHost()
	 * @see #setHost(String)
	 * @generated
	 */
	void unsetHost();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getHost <em>Host</em>}' attribute is set.
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
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailProvides_Port()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	int getPort();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getPort <em>Port</em>}' attribute.
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
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getPort <em>Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPort()
	 * @see #getPort()
	 * @see #setPort(int)
	 * @generated
	 */
	void unsetPort();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getPort <em>Port</em>}' attribute is set.
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
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailProvides_User()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	String getUser();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getUser <em>User</em>}' attribute.
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
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailProvides_Password()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	String getPassword();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getPassword <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Password</em>' attribute.
	 * @see #getPassword()
	 * @generated
	 */
	void setPassword(String value);

	/**
	 * Returns the value of the '<em><b>From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' attribute.
	 * @see #isSetFrom()
	 * @see #unsetFrom()
	 * @see #setFrom(String)
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailProvides_From()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	String getFrom();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getFrom <em>From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' attribute.
	 * @see #isSetFrom()
	 * @see #unsetFrom()
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(String value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getFrom <em>From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetFrom()
	 * @see #getFrom()
	 * @see #setFrom(String)
	 * @generated
	 */
	void unsetFrom();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getFrom <em>From</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>From</em>' attribute is set.
	 * @see #unsetFrom()
	 * @see #getFrom()
	 * @see #setFrom(String)
	 * @generated
	 */
	boolean isSetFrom();

	/**
	 * Returns the value of the '<em><b>Reply</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reply</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reply</em>' attribute.
	 * @see #setReply(String)
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailProvides_Reply()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	String getReply();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getReply <em>Reply</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reply</em>' attribute.
	 * @see #getReply()
	 * @generated
	 */
	void setReply(String value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' attribute.
	 * @see #isSetTo()
	 * @see #unsetTo()
	 * @see #setTo(String)
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailProvides_To()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	String getTo();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getTo <em>To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' attribute.
	 * @see #isSetTo()
	 * @see #unsetTo()
	 * @see #getTo()
	 * @generated
	 */
	void setTo(String value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getTo <em>To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTo()
	 * @see #getTo()
	 * @see #setTo(String)
	 * @generated
	 */
	void unsetTo();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getTo <em>To</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>To</em>' attribute is set.
	 * @see #unsetTo()
	 * @see #getTo()
	 * @see #setTo(String)
	 * @generated
	 */
	boolean isSetTo();

	/**
	 * Returns the value of the '<em><b>Subject</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subject</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subject</em>' attribute.
	 * @see #setSubject(String)
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailProvides_Subject()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	String getSubject();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getSubject <em>Subject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subject</em>' attribute.
	 * @see #getSubject()
	 * @generated
	 */
	void setSubject(String value);

	/**
	 * Returns the value of the '<em><b>Helohost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Helohost</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Helohost</em>' attribute.
	 * @see #setHelohost(String)
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailProvides_Helohost()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	String getHelohost();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getHelohost <em>Helohost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Helohost</em>' attribute.
	 * @see #getHelohost()
	 * @generated
	 */
	void setHelohost(String value);

	/**
	 * Returns the value of the '<em><b>Send Mode</b></em>' attribute.
	 * The literals are from the enumeration {@link com.ebmwebsourcing.petals.services.mail.mail.SendMode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Send Mode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Send Mode</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.SendMode
	 * @see #setSendMode(SendMode)
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailProvides_SendMode()
	 * @model derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0' name='send-mode'"
	 * @generated
	 */
	SendMode getSendMode();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getSendMode <em>Send Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Send Mode</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.SendMode
	 * @see #getSendMode()
	 * @generated
	 */
	void setSendMode(SendMode value);

	/**
	 * Returns the value of the '<em><b>Content Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Content Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Content Type</em>' attribute.
	 * @see #setContentType(String)
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailProvides_ContentType()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0' name='content-type'"
	 * @generated
	 */
	String getContentType();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getContentType <em>Content Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Content Type</em>' attribute.
	 * @see #getContentType()
	 * @generated
	 */
	void setContentType(String value);

} // MailProvides
