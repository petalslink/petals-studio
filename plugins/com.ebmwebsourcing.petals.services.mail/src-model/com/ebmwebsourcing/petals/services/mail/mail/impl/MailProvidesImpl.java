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
package com.ebmwebsourcing.petals.services.mail.mail.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import com.ebmwebsourcing.petals.services.mail.mail.MailPackage;
import com.ebmwebsourcing.petals.services.mail.mail.MailProvides;
import com.ebmwebsourcing.petals.services.mail.mail.MailServiceCommon;
import com.ebmwebsourcing.petals.services.mail.mail.Scheme;
import com.ebmwebsourcing.petals.services.mail.mail.SendMode;
import com.sun.java.xml.ns.jbi.impl.ProvidesImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Provides</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.impl.MailProvidesImpl#getScheme <em>Scheme</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.impl.MailProvidesImpl#getHost <em>Host</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.impl.MailProvidesImpl#getPort <em>Port</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.impl.MailProvidesImpl#getUser <em>User</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.impl.MailProvidesImpl#getPassword <em>Password</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.impl.MailProvidesImpl#getFrom <em>From</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.impl.MailProvidesImpl#getReply <em>Reply</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.impl.MailProvidesImpl#getTo <em>To</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.impl.MailProvidesImpl#getSubject <em>Subject</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.impl.MailProvidesImpl#getHelohost <em>Helohost</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.impl.MailProvidesImpl#getSendMode <em>Send Mode</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.impl.MailProvidesImpl#getContentType <em>Content Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MailProvidesImpl extends ProvidesImpl implements MailProvides {
	/**
	 * The default value of the '{@link #getScheme() <em>Scheme</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScheme()
	 * @generated
	 * @ordered
	 */
	protected static final Scheme SCHEME_EDEFAULT = Scheme.SMTP;

	/**
	 * The cached value of the '{@link #getScheme() <em>Scheme</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScheme()
	 * @generated
	 * @ordered
	 */
	protected Scheme scheme = SCHEME_EDEFAULT;

	/**
	 * This is true if the Scheme attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean schemeESet;

	/**
	 * The default value of the '{@link #getHost() <em>Host</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHost()
	 * @generated
	 * @ordered
	 */
	protected static final String HOST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHost() <em>Host</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHost()
	 * @generated
	 * @ordered
	 */
	protected String host = HOST_EDEFAULT;

	/**
	 * The default value of the '{@link #getPort() <em>Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPort()
	 * @generated
	 * @ordered
	 */
	protected static final int PORT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPort() <em>Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPort()
	 * @generated
	 * @ordered
	 */
	protected int port = PORT_EDEFAULT;

	/**
	 * The default value of the '{@link #getUser() <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUser()
	 * @generated
	 * @ordered
	 */
	protected static final String USER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUser() <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUser()
	 * @generated
	 * @ordered
	 */
	protected String user = USER_EDEFAULT;

	/**
	 * The default value of the '{@link #getPassword() <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPassword()
	 * @generated
	 * @ordered
	 */
	protected static final String PASSWORD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPassword() <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPassword()
	 * @generated
	 * @ordered
	 */
	protected String password = PASSWORD_EDEFAULT;

	/**
	 * The default value of the '{@link #getFrom() <em>From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrom()
	 * @generated
	 * @ordered
	 */
	protected static final String FROM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFrom() <em>From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrom()
	 * @generated
	 * @ordered
	 */
	protected String from = FROM_EDEFAULT;

	/**
	 * The default value of the '{@link #getReply() <em>Reply</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReply()
	 * @generated
	 * @ordered
	 */
	protected static final String REPLY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReply() <em>Reply</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReply()
	 * @generated
	 * @ordered
	 */
	protected String reply = REPLY_EDEFAULT;

	/**
	 * The default value of the '{@link #getTo() <em>To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTo()
	 * @generated
	 * @ordered
	 */
	protected static final String TO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTo() <em>To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTo()
	 * @generated
	 * @ordered
	 */
	protected String to = TO_EDEFAULT;

	/**
	 * The default value of the '{@link #getSubject() <em>Subject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubject()
	 * @generated
	 * @ordered
	 */
	protected static final String SUBJECT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSubject() <em>Subject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubject()
	 * @generated
	 * @ordered
	 */
	protected String subject = SUBJECT_EDEFAULT;

	/**
	 * The default value of the '{@link #getHelohost() <em>Helohost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHelohost()
	 * @generated
	 * @ordered
	 */
	protected static final String HELOHOST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHelohost() <em>Helohost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHelohost()
	 * @generated
	 * @ordered
	 */
	protected String helohost = HELOHOST_EDEFAULT;

	/**
	 * The default value of the '{@link #getSendMode() <em>Send Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSendMode()
	 * @generated
	 * @ordered
	 */
	protected static final SendMode SEND_MODE_EDEFAULT = SendMode.CONTENT_ONLY;

	/**
	 * The cached value of the '{@link #getSendMode() <em>Send Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSendMode()
	 * @generated
	 * @ordered
	 */
	protected SendMode sendMode = SEND_MODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getContentType() <em>Content Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContentType()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTENT_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContentType() <em>Content Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContentType()
	 * @generated
	 * @ordered
	 */
	protected String contentType = CONTENT_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MailProvidesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MailPackage.Literals.MAIL_PROVIDES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Scheme getScheme() {
		return scheme;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScheme(Scheme newScheme) {
		Scheme oldScheme = scheme;
		scheme = newScheme == null ? SCHEME_EDEFAULT : newScheme;
		boolean oldSchemeESet = schemeESet;
		schemeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MailPackage.MAIL_PROVIDES__SCHEME, oldScheme, scheme, !oldSchemeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetScheme() {
		Scheme oldScheme = scheme;
		boolean oldSchemeESet = schemeESet;
		scheme = SCHEME_EDEFAULT;
		schemeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MailPackage.MAIL_PROVIDES__SCHEME, oldScheme, SCHEME_EDEFAULT, oldSchemeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetScheme() {
		return schemeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHost() {
		return host;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHost(String newHost) {
		String oldHost = host;
		host = newHost;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MailPackage.MAIL_PROVIDES__HOST, oldHost, host));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPort() {
		return port;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPort(int newPort) {
		int oldPort = port;
		port = newPort;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MailPackage.MAIL_PROVIDES__PORT, oldPort, port));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUser() {
		return user;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUser(String newUser) {
		String oldUser = user;
		user = newUser;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MailPackage.MAIL_PROVIDES__USER, oldUser, user));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPassword(String newPassword) {
		String oldPassword = password;
		password = newPassword;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MailPackage.MAIL_PROVIDES__PASSWORD, oldPassword, password));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFrom(String newFrom) {
		String oldFrom = from;
		from = newFrom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MailPackage.MAIL_PROVIDES__FROM, oldFrom, from));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getReply() {
		return reply;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReply(String newReply) {
		String oldReply = reply;
		reply = newReply;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MailPackage.MAIL_PROVIDES__REPLY, oldReply, reply));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTo() {
		return to;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTo(String newTo) {
		String oldTo = to;
		to = newTo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MailPackage.MAIL_PROVIDES__TO, oldTo, to));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubject(String newSubject) {
		String oldSubject = subject;
		subject = newSubject;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MailPackage.MAIL_PROVIDES__SUBJECT, oldSubject, subject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHelohost() {
		return helohost;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHelohost(String newHelohost) {
		String oldHelohost = helohost;
		helohost = newHelohost;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MailPackage.MAIL_PROVIDES__HELOHOST, oldHelohost, helohost));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SendMode getSendMode() {
		return sendMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSendMode(SendMode newSendMode) {
		SendMode oldSendMode = sendMode;
		sendMode = newSendMode == null ? SEND_MODE_EDEFAULT : newSendMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MailPackage.MAIL_PROVIDES__SEND_MODE, oldSendMode, sendMode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContentType(String newContentType) {
		String oldContentType = contentType;
		contentType = newContentType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MailPackage.MAIL_PROVIDES__CONTENT_TYPE, oldContentType, contentType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MailPackage.MAIL_PROVIDES__SCHEME:
				return getScheme();
			case MailPackage.MAIL_PROVIDES__HOST:
				return getHost();
			case MailPackage.MAIL_PROVIDES__PORT:
				return getPort();
			case MailPackage.MAIL_PROVIDES__USER:
				return getUser();
			case MailPackage.MAIL_PROVIDES__PASSWORD:
				return getPassword();
			case MailPackage.MAIL_PROVIDES__FROM:
				return getFrom();
			case MailPackage.MAIL_PROVIDES__REPLY:
				return getReply();
			case MailPackage.MAIL_PROVIDES__TO:
				return getTo();
			case MailPackage.MAIL_PROVIDES__SUBJECT:
				return getSubject();
			case MailPackage.MAIL_PROVIDES__HELOHOST:
				return getHelohost();
			case MailPackage.MAIL_PROVIDES__SEND_MODE:
				return getSendMode();
			case MailPackage.MAIL_PROVIDES__CONTENT_TYPE:
				return getContentType();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MailPackage.MAIL_PROVIDES__SCHEME:
				setScheme((Scheme)newValue);
				return;
			case MailPackage.MAIL_PROVIDES__HOST:
				setHost((String)newValue);
				return;
			case MailPackage.MAIL_PROVIDES__PORT:
				setPort((Integer)newValue);
				return;
			case MailPackage.MAIL_PROVIDES__USER:
				setUser((String)newValue);
				return;
			case MailPackage.MAIL_PROVIDES__PASSWORD:
				setPassword((String)newValue);
				return;
			case MailPackage.MAIL_PROVIDES__FROM:
				setFrom((String)newValue);
				return;
			case MailPackage.MAIL_PROVIDES__REPLY:
				setReply((String)newValue);
				return;
			case MailPackage.MAIL_PROVIDES__TO:
				setTo((String)newValue);
				return;
			case MailPackage.MAIL_PROVIDES__SUBJECT:
				setSubject((String)newValue);
				return;
			case MailPackage.MAIL_PROVIDES__HELOHOST:
				setHelohost((String)newValue);
				return;
			case MailPackage.MAIL_PROVIDES__SEND_MODE:
				setSendMode((SendMode)newValue);
				return;
			case MailPackage.MAIL_PROVIDES__CONTENT_TYPE:
				setContentType((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case MailPackage.MAIL_PROVIDES__SCHEME:
				unsetScheme();
				return;
			case MailPackage.MAIL_PROVIDES__HOST:
				setHost(HOST_EDEFAULT);
				return;
			case MailPackage.MAIL_PROVIDES__PORT:
				setPort(PORT_EDEFAULT);
				return;
			case MailPackage.MAIL_PROVIDES__USER:
				setUser(USER_EDEFAULT);
				return;
			case MailPackage.MAIL_PROVIDES__PASSWORD:
				setPassword(PASSWORD_EDEFAULT);
				return;
			case MailPackage.MAIL_PROVIDES__FROM:
				setFrom(FROM_EDEFAULT);
				return;
			case MailPackage.MAIL_PROVIDES__REPLY:
				setReply(REPLY_EDEFAULT);
				return;
			case MailPackage.MAIL_PROVIDES__TO:
				setTo(TO_EDEFAULT);
				return;
			case MailPackage.MAIL_PROVIDES__SUBJECT:
				setSubject(SUBJECT_EDEFAULT);
				return;
			case MailPackage.MAIL_PROVIDES__HELOHOST:
				setHelohost(HELOHOST_EDEFAULT);
				return;
			case MailPackage.MAIL_PROVIDES__SEND_MODE:
				setSendMode(SEND_MODE_EDEFAULT);
				return;
			case MailPackage.MAIL_PROVIDES__CONTENT_TYPE:
				setContentType(CONTENT_TYPE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case MailPackage.MAIL_PROVIDES__SCHEME:
				return isSetScheme();
			case MailPackage.MAIL_PROVIDES__HOST:
				return HOST_EDEFAULT == null ? host != null : !HOST_EDEFAULT.equals(host);
			case MailPackage.MAIL_PROVIDES__PORT:
				return port != PORT_EDEFAULT;
			case MailPackage.MAIL_PROVIDES__USER:
				return USER_EDEFAULT == null ? user != null : !USER_EDEFAULT.equals(user);
			case MailPackage.MAIL_PROVIDES__PASSWORD:
				return PASSWORD_EDEFAULT == null ? password != null : !PASSWORD_EDEFAULT.equals(password);
			case MailPackage.MAIL_PROVIDES__FROM:
				return FROM_EDEFAULT == null ? from != null : !FROM_EDEFAULT.equals(from);
			case MailPackage.MAIL_PROVIDES__REPLY:
				return REPLY_EDEFAULT == null ? reply != null : !REPLY_EDEFAULT.equals(reply);
			case MailPackage.MAIL_PROVIDES__TO:
				return TO_EDEFAULT == null ? to != null : !TO_EDEFAULT.equals(to);
			case MailPackage.MAIL_PROVIDES__SUBJECT:
				return SUBJECT_EDEFAULT == null ? subject != null : !SUBJECT_EDEFAULT.equals(subject);
			case MailPackage.MAIL_PROVIDES__HELOHOST:
				return HELOHOST_EDEFAULT == null ? helohost != null : !HELOHOST_EDEFAULT.equals(helohost);
			case MailPackage.MAIL_PROVIDES__SEND_MODE:
				return sendMode != SEND_MODE_EDEFAULT;
			case MailPackage.MAIL_PROVIDES__CONTENT_TYPE:
				return CONTENT_TYPE_EDEFAULT == null ? contentType != null : !CONTENT_TYPE_EDEFAULT.equals(contentType);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == MailServiceCommon.class) {
			switch (derivedFeatureID) {
				case MailPackage.MAIL_PROVIDES__SCHEME: return MailPackage.MAIL_SERVICE_COMMON__SCHEME;
				case MailPackage.MAIL_PROVIDES__HOST: return MailPackage.MAIL_SERVICE_COMMON__HOST;
				case MailPackage.MAIL_PROVIDES__PORT: return MailPackage.MAIL_SERVICE_COMMON__PORT;
				case MailPackage.MAIL_PROVIDES__USER: return MailPackage.MAIL_SERVICE_COMMON__USER;
				case MailPackage.MAIL_PROVIDES__PASSWORD: return MailPackage.MAIL_SERVICE_COMMON__PASSWORD;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == MailServiceCommon.class) {
			switch (baseFeatureID) {
				case MailPackage.MAIL_SERVICE_COMMON__SCHEME: return MailPackage.MAIL_PROVIDES__SCHEME;
				case MailPackage.MAIL_SERVICE_COMMON__HOST: return MailPackage.MAIL_PROVIDES__HOST;
				case MailPackage.MAIL_SERVICE_COMMON__PORT: return MailPackage.MAIL_PROVIDES__PORT;
				case MailPackage.MAIL_SERVICE_COMMON__USER: return MailPackage.MAIL_PROVIDES__USER;
				case MailPackage.MAIL_SERVICE_COMMON__PASSWORD: return MailPackage.MAIL_PROVIDES__PASSWORD;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (scheme: ");
		if (schemeESet) result.append(scheme); else result.append("<unset>");
		result.append(", host: ");
		result.append(host);
		result.append(", port: ");
		result.append(port);
		result.append(", user: ");
		result.append(user);
		result.append(", password: ");
		result.append(password);
		result.append(", from: ");
		result.append(from);
		result.append(", reply: ");
		result.append(reply);
		result.append(", to: ");
		result.append(to);
		result.append(", subject: ");
		result.append(subject);
		result.append(", helohost: ");
		result.append(helohost);
		result.append(", sendMode: ");
		result.append(sendMode);
		result.append(", contentType: ");
		result.append(contentType);
		result.append(')');
		return result.toString();
	}

} //MailProvidesImpl
