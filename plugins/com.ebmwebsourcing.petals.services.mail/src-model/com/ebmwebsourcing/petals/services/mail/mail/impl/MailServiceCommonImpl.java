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
import com.ebmwebsourcing.petals.services.mail.mail.MailServiceCommon;
import com.ebmwebsourcing.petals.services.mail.mail.Scheme;
import com.sun.java.xml.ns.jbi.impl.AbstractEndpointImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service Common</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.impl.MailServiceCommonImpl#getScheme <em>Scheme</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.impl.MailServiceCommonImpl#getHost <em>Host</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.impl.MailServiceCommonImpl#getPort <em>Port</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.impl.MailServiceCommonImpl#getUser <em>User</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.impl.MailServiceCommonImpl#getPassword <em>Password</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MailServiceCommonImpl extends AbstractEndpointImpl implements MailServiceCommon {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MailServiceCommonImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MailPackage.Literals.MAIL_SERVICE_COMMON;
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
			eNotify(new ENotificationImpl(this, Notification.SET, MailPackage.MAIL_SERVICE_COMMON__SCHEME, oldScheme, scheme, !oldSchemeESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, MailPackage.MAIL_SERVICE_COMMON__SCHEME, oldScheme, SCHEME_EDEFAULT, oldSchemeESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MailPackage.MAIL_SERVICE_COMMON__HOST, oldHost, host));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MailPackage.MAIL_SERVICE_COMMON__PORT, oldPort, port));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MailPackage.MAIL_SERVICE_COMMON__USER, oldUser, user));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MailPackage.MAIL_SERVICE_COMMON__PASSWORD, oldPassword, password));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MailPackage.MAIL_SERVICE_COMMON__SCHEME:
				return getScheme();
			case MailPackage.MAIL_SERVICE_COMMON__HOST:
				return getHost();
			case MailPackage.MAIL_SERVICE_COMMON__PORT:
				return getPort();
			case MailPackage.MAIL_SERVICE_COMMON__USER:
				return getUser();
			case MailPackage.MAIL_SERVICE_COMMON__PASSWORD:
				return getPassword();
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
			case MailPackage.MAIL_SERVICE_COMMON__SCHEME:
				setScheme((Scheme)newValue);
				return;
			case MailPackage.MAIL_SERVICE_COMMON__HOST:
				setHost((String)newValue);
				return;
			case MailPackage.MAIL_SERVICE_COMMON__PORT:
				setPort((Integer)newValue);
				return;
			case MailPackage.MAIL_SERVICE_COMMON__USER:
				setUser((String)newValue);
				return;
			case MailPackage.MAIL_SERVICE_COMMON__PASSWORD:
				setPassword((String)newValue);
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
			case MailPackage.MAIL_SERVICE_COMMON__SCHEME:
				unsetScheme();
				return;
			case MailPackage.MAIL_SERVICE_COMMON__HOST:
				setHost(HOST_EDEFAULT);
				return;
			case MailPackage.MAIL_SERVICE_COMMON__PORT:
				setPort(PORT_EDEFAULT);
				return;
			case MailPackage.MAIL_SERVICE_COMMON__USER:
				setUser(USER_EDEFAULT);
				return;
			case MailPackage.MAIL_SERVICE_COMMON__PASSWORD:
				setPassword(PASSWORD_EDEFAULT);
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
			case MailPackage.MAIL_SERVICE_COMMON__SCHEME:
				return isSetScheme();
			case MailPackage.MAIL_SERVICE_COMMON__HOST:
				return HOST_EDEFAULT == null ? host != null : !HOST_EDEFAULT.equals(host);
			case MailPackage.MAIL_SERVICE_COMMON__PORT:
				return port != PORT_EDEFAULT;
			case MailPackage.MAIL_SERVICE_COMMON__USER:
				return USER_EDEFAULT == null ? user != null : !USER_EDEFAULT.equals(user);
			case MailPackage.MAIL_SERVICE_COMMON__PASSWORD:
				return PASSWORD_EDEFAULT == null ? password != null : !PASSWORD_EDEFAULT.equals(password);
		}
		return super.eIsSet(featureID);
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
		result.append(')');
		return result.toString();
	}

} //MailServiceCommonImpl
