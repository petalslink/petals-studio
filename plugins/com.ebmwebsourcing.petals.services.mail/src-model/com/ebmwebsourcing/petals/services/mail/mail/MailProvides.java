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
public interface MailProvides extends Provides, MailServiceCommon {
	/**
	 * Returns the value of the '<em><b>From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' attribute.
	 * @see #setFrom(String)
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailProvides_From()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	String getFrom();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getFrom <em>From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' attribute.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(String value);

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
	 * @see #setTo(String)
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailProvides_To()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	String getTo();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getTo <em>To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' attribute.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(String value);

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
