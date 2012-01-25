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
package com.ebmwebsourcing.petals.services.sftp.sftp.impl;

import com.ebmwebsourcing.petals.services.sftp.sftp.SftpPackage;
import com.ebmwebsourcing.petals.services.sftp.sftp.SftpProvides;

import com.sun.java.xml.ns.jbi.impl.ProvidesImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Provides</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.sftp.sftp.impl.SftpProvidesImpl#getFolder <em>Folder</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sftp.sftp.impl.SftpProvidesImpl#getHost <em>Host</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sftp.sftp.impl.SftpProvidesImpl#isOverwrite <em>Overwrite</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sftp.sftp.impl.SftpProvidesImpl#getPassphrase <em>Passphrase</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sftp.sftp.impl.SftpProvidesImpl#getPassword <em>Password</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sftp.sftp.impl.SftpProvidesImpl#getPort <em>Port</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sftp.sftp.impl.SftpProvidesImpl#getPrivatekey <em>Privatekey</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sftp.sftp.impl.SftpProvidesImpl#getUser <em>User</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sftp.sftp.impl.SftpProvidesImpl#getMaxIdleTime <em>Max Idle Time</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sftp.sftp.impl.SftpProvidesImpl#getMaxConnection <em>Max Connection</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SftpProvidesImpl extends ProvidesImpl implements SftpProvides {
	/**
	 * The default value of the '{@link #getFolder() <em>Folder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFolder()
	 * @generated
	 * @ordered
	 */
	protected static final String FOLDER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFolder() <em>Folder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFolder()
	 * @generated
	 * @ordered
	 */
	protected String folder = FOLDER_EDEFAULT;

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
	 * The default value of the '{@link #isOverwrite() <em>Overwrite</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverwrite()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OVERWRITE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOverwrite() <em>Overwrite</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverwrite()
	 * @generated
	 * @ordered
	 */
	protected boolean overwrite = OVERWRITE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPassphrase() <em>Passphrase</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPassphrase()
	 * @generated
	 * @ordered
	 */
	protected static final String PASSPHRASE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPassphrase() <em>Passphrase</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPassphrase()
	 * @generated
	 * @ordered
	 */
	protected String passphrase = PASSPHRASE_EDEFAULT;

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
	 * The default value of the '{@link #getPrivatekey() <em>Privatekey</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrivatekey()
	 * @generated
	 * @ordered
	 */
	protected static final String PRIVATEKEY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrivatekey() <em>Privatekey</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrivatekey()
	 * @generated
	 * @ordered
	 */
	protected String privatekey = PRIVATEKEY_EDEFAULT;

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
	 * The default value of the '{@link #getMaxIdleTime() <em>Max Idle Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxIdleTime()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_IDLE_TIME_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaxIdleTime() <em>Max Idle Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxIdleTime()
	 * @generated
	 * @ordered
	 */
	protected int maxIdleTime = MAX_IDLE_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxConnection() <em>Max Connection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxConnection()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_CONNECTION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaxConnection() <em>Max Connection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxConnection()
	 * @generated
	 * @ordered
	 */
	protected int maxConnection = MAX_CONNECTION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SftpProvidesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SftpPackage.Literals.SFTP_PROVIDES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFolder() {
		return folder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFolder(String newFolder) {
		String oldFolder = folder;
		folder = newFolder;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SftpPackage.SFTP_PROVIDES__FOLDER, oldFolder, folder));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SftpPackage.SFTP_PROVIDES__HOST, oldHost, host));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaxIdleTime() {
		return maxIdleTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxIdleTime(int newMaxIdleTime) {
		int oldMaxIdleTime = maxIdleTime;
		maxIdleTime = newMaxIdleTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SftpPackage.SFTP_PROVIDES__MAX_IDLE_TIME, oldMaxIdleTime, maxIdleTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaxConnection() {
		return maxConnection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxConnection(int newMaxConnection) {
		int oldMaxConnection = maxConnection;
		maxConnection = newMaxConnection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SftpPackage.SFTP_PROVIDES__MAX_CONNECTION, oldMaxConnection, maxConnection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOverwrite() {
		return overwrite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOverwrite(boolean newOverwrite) {
		boolean oldOverwrite = overwrite;
		overwrite = newOverwrite;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SftpPackage.SFTP_PROVIDES__OVERWRITE, oldOverwrite, overwrite));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPassphrase() {
		return passphrase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPassphrase(String newPassphrase) {
		String oldPassphrase = passphrase;
		passphrase = newPassphrase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SftpPackage.SFTP_PROVIDES__PASSPHRASE, oldPassphrase, passphrase));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SftpPackage.SFTP_PROVIDES__PASSWORD, oldPassword, password));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SftpPackage.SFTP_PROVIDES__PORT, oldPort, port));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPrivatekey() {
		return privatekey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrivatekey(String newPrivatekey) {
		String oldPrivatekey = privatekey;
		privatekey = newPrivatekey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SftpPackage.SFTP_PROVIDES__PRIVATEKEY, oldPrivatekey, privatekey));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SftpPackage.SFTP_PROVIDES__USER, oldUser, user));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SftpPackage.SFTP_PROVIDES__FOLDER:
				return getFolder();
			case SftpPackage.SFTP_PROVIDES__HOST:
				return getHost();
			case SftpPackage.SFTP_PROVIDES__OVERWRITE:
				return isOverwrite();
			case SftpPackage.SFTP_PROVIDES__PASSPHRASE:
				return getPassphrase();
			case SftpPackage.SFTP_PROVIDES__PASSWORD:
				return getPassword();
			case SftpPackage.SFTP_PROVIDES__PORT:
				return getPort();
			case SftpPackage.SFTP_PROVIDES__PRIVATEKEY:
				return getPrivatekey();
			case SftpPackage.SFTP_PROVIDES__USER:
				return getUser();
			case SftpPackage.SFTP_PROVIDES__MAX_IDLE_TIME:
				return getMaxIdleTime();
			case SftpPackage.SFTP_PROVIDES__MAX_CONNECTION:
				return getMaxConnection();
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
			case SftpPackage.SFTP_PROVIDES__FOLDER:
				setFolder((String)newValue);
				return;
			case SftpPackage.SFTP_PROVIDES__HOST:
				setHost((String)newValue);
				return;
			case SftpPackage.SFTP_PROVIDES__OVERWRITE:
				setOverwrite((Boolean)newValue);
				return;
			case SftpPackage.SFTP_PROVIDES__PASSPHRASE:
				setPassphrase((String)newValue);
				return;
			case SftpPackage.SFTP_PROVIDES__PASSWORD:
				setPassword((String)newValue);
				return;
			case SftpPackage.SFTP_PROVIDES__PORT:
				setPort((Integer)newValue);
				return;
			case SftpPackage.SFTP_PROVIDES__PRIVATEKEY:
				setPrivatekey((String)newValue);
				return;
			case SftpPackage.SFTP_PROVIDES__USER:
				setUser((String)newValue);
				return;
			case SftpPackage.SFTP_PROVIDES__MAX_IDLE_TIME:
				setMaxIdleTime((Integer)newValue);
				return;
			case SftpPackage.SFTP_PROVIDES__MAX_CONNECTION:
				setMaxConnection((Integer)newValue);
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
			case SftpPackage.SFTP_PROVIDES__FOLDER:
				setFolder(FOLDER_EDEFAULT);
				return;
			case SftpPackage.SFTP_PROVIDES__HOST:
				setHost(HOST_EDEFAULT);
				return;
			case SftpPackage.SFTP_PROVIDES__OVERWRITE:
				setOverwrite(OVERWRITE_EDEFAULT);
				return;
			case SftpPackage.SFTP_PROVIDES__PASSPHRASE:
				setPassphrase(PASSPHRASE_EDEFAULT);
				return;
			case SftpPackage.SFTP_PROVIDES__PASSWORD:
				setPassword(PASSWORD_EDEFAULT);
				return;
			case SftpPackage.SFTP_PROVIDES__PORT:
				setPort(PORT_EDEFAULT);
				return;
			case SftpPackage.SFTP_PROVIDES__PRIVATEKEY:
				setPrivatekey(PRIVATEKEY_EDEFAULT);
				return;
			case SftpPackage.SFTP_PROVIDES__USER:
				setUser(USER_EDEFAULT);
				return;
			case SftpPackage.SFTP_PROVIDES__MAX_IDLE_TIME:
				setMaxIdleTime(MAX_IDLE_TIME_EDEFAULT);
				return;
			case SftpPackage.SFTP_PROVIDES__MAX_CONNECTION:
				setMaxConnection(MAX_CONNECTION_EDEFAULT);
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
			case SftpPackage.SFTP_PROVIDES__FOLDER:
				return FOLDER_EDEFAULT == null ? folder != null : !FOLDER_EDEFAULT.equals(folder);
			case SftpPackage.SFTP_PROVIDES__HOST:
				return HOST_EDEFAULT == null ? host != null : !HOST_EDEFAULT.equals(host);
			case SftpPackage.SFTP_PROVIDES__OVERWRITE:
				return overwrite != OVERWRITE_EDEFAULT;
			case SftpPackage.SFTP_PROVIDES__PASSPHRASE:
				return PASSPHRASE_EDEFAULT == null ? passphrase != null : !PASSPHRASE_EDEFAULT.equals(passphrase);
			case SftpPackage.SFTP_PROVIDES__PASSWORD:
				return PASSWORD_EDEFAULT == null ? password != null : !PASSWORD_EDEFAULT.equals(password);
			case SftpPackage.SFTP_PROVIDES__PORT:
				return port != PORT_EDEFAULT;
			case SftpPackage.SFTP_PROVIDES__PRIVATEKEY:
				return PRIVATEKEY_EDEFAULT == null ? privatekey != null : !PRIVATEKEY_EDEFAULT.equals(privatekey);
			case SftpPackage.SFTP_PROVIDES__USER:
				return USER_EDEFAULT == null ? user != null : !USER_EDEFAULT.equals(user);
			case SftpPackage.SFTP_PROVIDES__MAX_IDLE_TIME:
				return maxIdleTime != MAX_IDLE_TIME_EDEFAULT;
			case SftpPackage.SFTP_PROVIDES__MAX_CONNECTION:
				return maxConnection != MAX_CONNECTION_EDEFAULT;
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
		result.append(" (folder: ");
		result.append(folder);
		result.append(", host: ");
		result.append(host);
		result.append(", overwrite: ");
		result.append(overwrite);
		result.append(", passphrase: ");
		result.append(passphrase);
		result.append(", password: ");
		result.append(password);
		result.append(", port: ");
		result.append(port);
		result.append(", privatekey: ");
		result.append(privatekey);
		result.append(", user: ");
		result.append(user);
		result.append(", maxIdleTime: ");
		result.append(maxIdleTime);
		result.append(", maxConnection: ");
		result.append(maxConnection);
		result.append(')');
		return result.toString();
	}

} //SftpProvidesImpl
