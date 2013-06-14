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

import com.sun.java.xml.ns.jbi.JbiPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.ebmwebsourcing.petals.services.mail.mail.MailFactory
 * @model kind="package"
 * @generated
 */
public interface MailPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "mail";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://petals.ow2.org/components/mail/version-3";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "mail";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MailPackage eINSTANCE = com.ebmwebsourcing.petals.services.mail.mail.impl.MailPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.mail.mail.impl.MailProvidesImpl <em>Provides</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.mail.mail.impl.MailProvidesImpl
	 * @see com.ebmwebsourcing.petals.services.mail.mail.impl.MailPackageImpl#getMailProvides()
	 * @generated
	 */
	int MAIL_PROVIDES = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_PROVIDES__GROUP = JbiPackage.PROVIDES__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_PROVIDES__OTHER = JbiPackage.PROVIDES__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_PROVIDES__LOCAL = JbiPackage.PROVIDES__LOCAL;

	/**
	 * The feature id for the '<em><b>Endpoint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_PROVIDES__ENDPOINT_NAME = JbiPackage.PROVIDES__ENDPOINT_NAME;

	/**
	 * The feature id for the '<em><b>Interface Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_PROVIDES__INTERFACE_NAME = JbiPackage.PROVIDES__INTERFACE_NAME;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_PROVIDES__SERVICE_NAME = JbiPackage.PROVIDES__SERVICE_NAME;

	/**
	 * The feature id for the '<em><b>Scheme</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_PROVIDES__SCHEME = JbiPackage.PROVIDES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_PROVIDES__HOST = JbiPackage.PROVIDES_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_PROVIDES__PORT = JbiPackage.PROVIDES_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_PROVIDES__USER = JbiPackage.PROVIDES_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_PROVIDES__PASSWORD = JbiPackage.PROVIDES_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_PROVIDES__FROM = JbiPackage.PROVIDES_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Reply</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_PROVIDES__REPLY = JbiPackage.PROVIDES_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_PROVIDES__TO = JbiPackage.PROVIDES_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Subject</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_PROVIDES__SUBJECT = JbiPackage.PROVIDES_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Helohost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_PROVIDES__HELOHOST = JbiPackage.PROVIDES_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Send Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_PROVIDES__SEND_MODE = JbiPackage.PROVIDES_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Content Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_PROVIDES__CONTENT_TYPE = JbiPackage.PROVIDES_FEATURE_COUNT + 11;

	/**
	 * The number of structural features of the '<em>Provides</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_PROVIDES_FEATURE_COUNT = JbiPackage.PROVIDES_FEATURE_COUNT + 12;

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.mail.mail.impl.MailConsumesImpl <em>Consumes</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.mail.mail.impl.MailConsumesImpl
	 * @see com.ebmwebsourcing.petals.services.mail.mail.impl.MailPackageImpl#getMailConsumes()
	 * @generated
	 */
	int MAIL_CONSUMES = 1;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_CONSUMES__GROUP = JbiPackage.CONSUMES__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_CONSUMES__OTHER = JbiPackage.CONSUMES__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_CONSUMES__LOCAL = JbiPackage.CONSUMES__LOCAL;

	/**
	 * The feature id for the '<em><b>Endpoint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_CONSUMES__ENDPOINT_NAME = JbiPackage.CONSUMES__ENDPOINT_NAME;

	/**
	 * The feature id for the '<em><b>Interface Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_CONSUMES__INTERFACE_NAME = JbiPackage.CONSUMES__INTERFACE_NAME;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_CONSUMES__SERVICE_NAME = JbiPackage.CONSUMES__SERVICE_NAME;

	/**
	 * The feature id for the '<em><b>Scheme</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_CONSUMES__SCHEME = JbiPackage.CONSUMES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_CONSUMES__HOST = JbiPackage.CONSUMES_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_CONSUMES__PORT = JbiPackage.CONSUMES_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_CONSUMES__USER = JbiPackage.CONSUMES_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_CONSUMES__PASSWORD = JbiPackage.CONSUMES_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Folder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_CONSUMES__FOLDER = JbiPackage.CONSUMES_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Delete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_CONSUMES__DELETE = JbiPackage.CONSUMES_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_CONSUMES__PERIOD = JbiPackage.CONSUMES_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Isxmlcontent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_CONSUMES__ISXMLCONTENT = JbiPackage.CONSUMES_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Consumes</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_CONSUMES_FEATURE_COUNT = JbiPackage.CONSUMES_FEATURE_COUNT + 9;

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.mail.mail.Scheme <em>Scheme</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.mail.mail.Scheme
	 * @see com.ebmwebsourcing.petals.services.mail.mail.impl.MailPackageImpl#getScheme()
	 * @generated
	 */
	int SCHEME = 2;

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.mail.mail.SendMode <em>Send Mode</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.mail.mail.SendMode
	 * @see com.ebmwebsourcing.petals.services.mail.mail.impl.MailPackageImpl#getSendMode()
	 * @generated
	 */
	int SEND_MODE = 3;


	/**
	 * Returns the meta object for class '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides <em>Provides</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Provides</em>'.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailProvides
	 * @generated
	 */
	EClass getMailProvides();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getScheme <em>Scheme</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scheme</em>'.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getScheme()
	 * @see #getMailProvides()
	 * @generated
	 */
	EAttribute getMailProvides_Scheme();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getHost <em>Host</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Host</em>'.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getHost()
	 * @see #getMailProvides()
	 * @generated
	 */
	EAttribute getMailProvides_Host();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Port</em>'.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getPort()
	 * @see #getMailProvides()
	 * @generated
	 */
	EAttribute getMailProvides_Port();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User</em>'.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getUser()
	 * @see #getMailProvides()
	 * @generated
	 */
	EAttribute getMailProvides_User();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getPassword <em>Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Password</em>'.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getPassword()
	 * @see #getMailProvides()
	 * @generated
	 */
	EAttribute getMailProvides_Password();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>From</em>'.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getFrom()
	 * @see #getMailProvides()
	 * @generated
	 */
	EAttribute getMailProvides_From();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getReply <em>Reply</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reply</em>'.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getReply()
	 * @see #getMailProvides()
	 * @generated
	 */
	EAttribute getMailProvides_Reply();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>To</em>'.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getTo()
	 * @see #getMailProvides()
	 * @generated
	 */
	EAttribute getMailProvides_To();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getSubject <em>Subject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Subject</em>'.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getSubject()
	 * @see #getMailProvides()
	 * @generated
	 */
	EAttribute getMailProvides_Subject();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getHelohost <em>Helohost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Helohost</em>'.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getHelohost()
	 * @see #getMailProvides()
	 * @generated
	 */
	EAttribute getMailProvides_Helohost();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getSendMode <em>Send Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Send Mode</em>'.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getSendMode()
	 * @see #getMailProvides()
	 * @generated
	 */
	EAttribute getMailProvides_SendMode();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getContentType <em>Content Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Content Type</em>'.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailProvides#getContentType()
	 * @see #getMailProvides()
	 * @generated
	 */
	EAttribute getMailProvides_ContentType();

	/**
	 * Returns the meta object for class '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes <em>Consumes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Consumes</em>'.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailConsumes
	 * @generated
	 */
	EClass getMailConsumes();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getScheme <em>Scheme</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scheme</em>'.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getScheme()
	 * @see #getMailConsumes()
	 * @generated
	 */
	EAttribute getMailConsumes_Scheme();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getHost <em>Host</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Host</em>'.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getHost()
	 * @see #getMailConsumes()
	 * @generated
	 */
	EAttribute getMailConsumes_Host();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Port</em>'.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getPort()
	 * @see #getMailConsumes()
	 * @generated
	 */
	EAttribute getMailConsumes_Port();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User</em>'.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getUser()
	 * @see #getMailConsumes()
	 * @generated
	 */
	EAttribute getMailConsumes_User();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getPassword <em>Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Password</em>'.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getPassword()
	 * @see #getMailConsumes()
	 * @generated
	 */
	EAttribute getMailConsumes_Password();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Folder</em>'.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getFolder()
	 * @see #getMailConsumes()
	 * @generated
	 */
	EAttribute getMailConsumes_Folder();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#isDelete <em>Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Delete</em>'.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#isDelete()
	 * @see #getMailConsumes()
	 * @generated
	 */
	EAttribute getMailConsumes_Delete();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getPeriod <em>Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Period</em>'.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getPeriod()
	 * @see #getMailConsumes()
	 * @generated
	 */
	EAttribute getMailConsumes_Period();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#isIsxmlcontent <em>Isxmlcontent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Isxmlcontent</em>'.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#isIsxmlcontent()
	 * @see #getMailConsumes()
	 * @generated
	 */
	EAttribute getMailConsumes_Isxmlcontent();

	/**
	 * Returns the meta object for enum '{@link com.ebmwebsourcing.petals.services.mail.mail.Scheme <em>Scheme</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Scheme</em>'.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.Scheme
	 * @generated
	 */
	EEnum getScheme();

	/**
	 * Returns the meta object for enum '{@link com.ebmwebsourcing.petals.services.mail.mail.SendMode <em>Send Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Send Mode</em>'.
	 * @see com.ebmwebsourcing.petals.services.mail.mail.SendMode
	 * @generated
	 */
	EEnum getSendMode();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MailFactory getMailFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.mail.mail.impl.MailProvidesImpl <em>Provides</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.mail.mail.impl.MailProvidesImpl
		 * @see com.ebmwebsourcing.petals.services.mail.mail.impl.MailPackageImpl#getMailProvides()
		 * @generated
		 */
		EClass MAIL_PROVIDES = eINSTANCE.getMailProvides();

		/**
		 * The meta object literal for the '<em><b>Scheme</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_PROVIDES__SCHEME = eINSTANCE.getMailProvides_Scheme();

		/**
		 * The meta object literal for the '<em><b>Host</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_PROVIDES__HOST = eINSTANCE.getMailProvides_Host();

		/**
		 * The meta object literal for the '<em><b>Port</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_PROVIDES__PORT = eINSTANCE.getMailProvides_Port();

		/**
		 * The meta object literal for the '<em><b>User</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_PROVIDES__USER = eINSTANCE.getMailProvides_User();

		/**
		 * The meta object literal for the '<em><b>Password</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_PROVIDES__PASSWORD = eINSTANCE.getMailProvides_Password();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_PROVIDES__FROM = eINSTANCE.getMailProvides_From();

		/**
		 * The meta object literal for the '<em><b>Reply</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_PROVIDES__REPLY = eINSTANCE.getMailProvides_Reply();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_PROVIDES__TO = eINSTANCE.getMailProvides_To();

		/**
		 * The meta object literal for the '<em><b>Subject</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_PROVIDES__SUBJECT = eINSTANCE.getMailProvides_Subject();

		/**
		 * The meta object literal for the '<em><b>Helohost</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_PROVIDES__HELOHOST = eINSTANCE.getMailProvides_Helohost();

		/**
		 * The meta object literal for the '<em><b>Send Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_PROVIDES__SEND_MODE = eINSTANCE.getMailProvides_SendMode();

		/**
		 * The meta object literal for the '<em><b>Content Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_PROVIDES__CONTENT_TYPE = eINSTANCE.getMailProvides_ContentType();

		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.mail.mail.impl.MailConsumesImpl <em>Consumes</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.mail.mail.impl.MailConsumesImpl
		 * @see com.ebmwebsourcing.petals.services.mail.mail.impl.MailPackageImpl#getMailConsumes()
		 * @generated
		 */
		EClass MAIL_CONSUMES = eINSTANCE.getMailConsumes();

		/**
		 * The meta object literal for the '<em><b>Scheme</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_CONSUMES__SCHEME = eINSTANCE.getMailConsumes_Scheme();

		/**
		 * The meta object literal for the '<em><b>Host</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_CONSUMES__HOST = eINSTANCE.getMailConsumes_Host();

		/**
		 * The meta object literal for the '<em><b>Port</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_CONSUMES__PORT = eINSTANCE.getMailConsumes_Port();

		/**
		 * The meta object literal for the '<em><b>User</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_CONSUMES__USER = eINSTANCE.getMailConsumes_User();

		/**
		 * The meta object literal for the '<em><b>Password</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_CONSUMES__PASSWORD = eINSTANCE.getMailConsumes_Password();

		/**
		 * The meta object literal for the '<em><b>Folder</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_CONSUMES__FOLDER = eINSTANCE.getMailConsumes_Folder();

		/**
		 * The meta object literal for the '<em><b>Delete</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_CONSUMES__DELETE = eINSTANCE.getMailConsumes_Delete();

		/**
		 * The meta object literal for the '<em><b>Period</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_CONSUMES__PERIOD = eINSTANCE.getMailConsumes_Period();

		/**
		 * The meta object literal for the '<em><b>Isxmlcontent</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_CONSUMES__ISXMLCONTENT = eINSTANCE.getMailConsumes_Isxmlcontent();

		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.mail.mail.Scheme <em>Scheme</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.mail.mail.Scheme
		 * @see com.ebmwebsourcing.petals.services.mail.mail.impl.MailPackageImpl#getScheme()
		 * @generated
		 */
		EEnum SCHEME = eINSTANCE.getScheme();

		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.mail.mail.SendMode <em>Send Mode</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.mail.mail.SendMode
		 * @see com.ebmwebsourcing.petals.services.mail.mail.impl.MailPackageImpl#getSendMode()
		 * @generated
		 */
		EEnum SEND_MODE = eINSTANCE.getSendMode();

	}

} //MailPackage
