/**
 * Copyright (c) 2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.soap.soap.impl;

import com.ebmwebsourcing.petals.services.soap.soap.SoapMode;
import com.ebmwebsourcing.petals.services.soap.soap.SoapPackage;
import com.ebmwebsourcing.petals.services.soap.soap.SoapProvides;

import com.ebmwebsourcing.petals.services.soap.soap.SoapVersion;
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
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getAddress <em>Address</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getSoapVersion <em>Soap Version</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#isChunkedMode <em>Chunked Mode</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getSynchonousTimeout <em>Synchonous Timeout</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#isCleanupTransport <em>Cleanup Transport</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getMode <em>Mode</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getProxyHost <em>Proxy Host</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getProxyUser <em>Proxy User</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getProxyPassword <em>Proxy Password</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getProxyDomain <em>Proxy Domain</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getProxyPort <em>Proxy Port</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SoapProvidesImpl extends ProvidesImpl implements SoapProvides {
	/**
	 * The default value of the '{@link #getAddress() <em>Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAddress()
	 * @generated
	 * @ordered
	 */
	protected static final String ADDRESS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAddress() <em>Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAddress()
	 * @generated
	 * @ordered
	 */
	protected String address = ADDRESS_EDEFAULT;

	/**
	 * The default value of the '{@link #getSoapVersion() <em>Soap Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSoapVersion()
	 * @generated
	 * @ordered
	 */
	protected static final SoapVersion SOAP_VERSION_EDEFAULT = SoapVersion.SOAP_11;

	/**
	 * The cached value of the '{@link #getSoapVersion() <em>Soap Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSoapVersion()
	 * @generated
	 * @ordered
	 */
	protected SoapVersion soapVersion = SOAP_VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #isChunkedMode() <em>Chunked Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isChunkedMode()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CHUNKED_MODE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isChunkedMode() <em>Chunked Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isChunkedMode()
	 * @generated
	 * @ordered
	 */
	protected boolean chunkedMode = CHUNKED_MODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSynchonousTimeout() <em>Synchonous Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSynchonousTimeout()
	 * @generated
	 * @ordered
	 */
	protected static final int SYNCHONOUS_TIMEOUT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSynchonousTimeout() <em>Synchonous Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSynchonousTimeout()
	 * @generated
	 * @ordered
	 */
	protected int synchonousTimeout = SYNCHONOUS_TIMEOUT_EDEFAULT;

	/**
	 * The default value of the '{@link #isCleanupTransport() <em>Cleanup Transport</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCleanupTransport()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CLEANUP_TRANSPORT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCleanupTransport() <em>Cleanup Transport</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCleanupTransport()
	 * @generated
	 * @ordered
	 */
	protected boolean cleanupTransport = CLEANUP_TRANSPORT_EDEFAULT;

	/**
	 * The default value of the '{@link #getMode() <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMode()
	 * @generated
	 * @ordered
	 */
	protected static final SoapMode MODE_EDEFAULT = SoapMode.SOAP;

	/**
	 * The cached value of the '{@link #getMode() <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMode()
	 * @generated
	 * @ordered
	 */
	protected SoapMode mode = MODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getProxyHost() <em>Proxy Host</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProxyHost()
	 * @generated
	 * @ordered
	 */
	protected static final String PROXY_HOST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProxyHost() <em>Proxy Host</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProxyHost()
	 * @generated
	 * @ordered
	 */
	protected String proxyHost = PROXY_HOST_EDEFAULT;

	/**
	 * The default value of the '{@link #getProxyUser() <em>Proxy User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProxyUser()
	 * @generated
	 * @ordered
	 */
	protected static final String PROXY_USER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProxyUser() <em>Proxy User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProxyUser()
	 * @generated
	 * @ordered
	 */
	protected String proxyUser = PROXY_USER_EDEFAULT;

	/**
	 * The default value of the '{@link #getProxyPassword() <em>Proxy Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProxyPassword()
	 * @generated
	 * @ordered
	 */
	protected static final String PROXY_PASSWORD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProxyPassword() <em>Proxy Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProxyPassword()
	 * @generated
	 * @ordered
	 */
	protected String proxyPassword = PROXY_PASSWORD_EDEFAULT;

	/**
	 * The default value of the '{@link #getProxyDomain() <em>Proxy Domain</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProxyDomain()
	 * @generated
	 * @ordered
	 */
	protected static final String PROXY_DOMAIN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProxyDomain() <em>Proxy Domain</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProxyDomain()
	 * @generated
	 * @ordered
	 */
	protected String proxyDomain = PROXY_DOMAIN_EDEFAULT;

	/**
	 * The default value of the '{@link #getProxyPort() <em>Proxy Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProxyPort()
	 * @generated
	 * @ordered
	 */
	protected static final int PROXY_PORT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getProxyPort() <em>Proxy Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProxyPort()
	 * @generated
	 * @ordered
	 */
	protected int proxyPort = PROXY_PORT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SoapProvidesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SoapPackage.Literals.SOAP_PROVIDES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAddress(String newAddress) {
		String oldAddress = address;
		address = newAddress;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__ADDRESS, oldAddress, address));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SoapVersion getSoapVersion() {
		return soapVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSoapVersion(SoapVersion newSoapVersion) {
		SoapVersion oldSoapVersion = soapVersion;
		soapVersion = newSoapVersion == null ? SOAP_VERSION_EDEFAULT : newSoapVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__SOAP_VERSION, oldSoapVersion, soapVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isChunkedMode() {
		return chunkedMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChunkedMode(boolean newChunkedMode) {
		boolean oldChunkedMode = chunkedMode;
		chunkedMode = newChunkedMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__CHUNKED_MODE, oldChunkedMode, chunkedMode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSynchonousTimeout() {
		return synchonousTimeout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSynchonousTimeout(int newSynchonousTimeout) {
		int oldSynchonousTimeout = synchonousTimeout;
		synchonousTimeout = newSynchonousTimeout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__SYNCHONOUS_TIMEOUT, oldSynchonousTimeout, synchonousTimeout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCleanupTransport() {
		return cleanupTransport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCleanupTransport(boolean newCleanupTransport) {
		boolean oldCleanupTransport = cleanupTransport;
		cleanupTransport = newCleanupTransport;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__CLEANUP_TRANSPORT, oldCleanupTransport, cleanupTransport));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SoapMode getMode() {
		return mode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMode(SoapMode newMode) {
		SoapMode oldMode = mode;
		mode = newMode == null ? MODE_EDEFAULT : newMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__MODE, oldMode, mode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getProxyHost() {
		return proxyHost;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProxyHost(String newProxyHost) {
		String oldProxyHost = proxyHost;
		proxyHost = newProxyHost;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__PROXY_HOST, oldProxyHost, proxyHost));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getProxyUser() {
		return proxyUser;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProxyUser(String newProxyUser) {
		String oldProxyUser = proxyUser;
		proxyUser = newProxyUser;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__PROXY_USER, oldProxyUser, proxyUser));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getProxyPassword() {
		return proxyPassword;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProxyPassword(String newProxyPassword) {
		String oldProxyPassword = proxyPassword;
		proxyPassword = newProxyPassword;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__PROXY_PASSWORD, oldProxyPassword, proxyPassword));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getProxyDomain() {
		return proxyDomain;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProxyDomain(String newProxyDomain) {
		String oldProxyDomain = proxyDomain;
		proxyDomain = newProxyDomain;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__PROXY_DOMAIN, oldProxyDomain, proxyDomain));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getProxyPort() {
		return proxyPort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProxyPort(int newProxyPort) {
		int oldProxyPort = proxyPort;
		proxyPort = newProxyPort;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__PROXY_PORT, oldProxyPort, proxyPort));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SoapPackage.SOAP_PROVIDES__ADDRESS:
				return getAddress();
			case SoapPackage.SOAP_PROVIDES__SOAP_VERSION:
				return getSoapVersion();
			case SoapPackage.SOAP_PROVIDES__CHUNKED_MODE:
				return isChunkedMode();
			case SoapPackage.SOAP_PROVIDES__SYNCHONOUS_TIMEOUT:
				return getSynchonousTimeout();
			case SoapPackage.SOAP_PROVIDES__CLEANUP_TRANSPORT:
				return isCleanupTransport();
			case SoapPackage.SOAP_PROVIDES__MODE:
				return getMode();
			case SoapPackage.SOAP_PROVIDES__PROXY_HOST:
				return getProxyHost();
			case SoapPackage.SOAP_PROVIDES__PROXY_USER:
				return getProxyUser();
			case SoapPackage.SOAP_PROVIDES__PROXY_PASSWORD:
				return getProxyPassword();
			case SoapPackage.SOAP_PROVIDES__PROXY_DOMAIN:
				return getProxyDomain();
			case SoapPackage.SOAP_PROVIDES__PROXY_PORT:
				return getProxyPort();
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
			case SoapPackage.SOAP_PROVIDES__ADDRESS:
				setAddress((String)newValue);
				return;
			case SoapPackage.SOAP_PROVIDES__SOAP_VERSION:
				setSoapVersion((SoapVersion)newValue);
				return;
			case SoapPackage.SOAP_PROVIDES__CHUNKED_MODE:
				setChunkedMode((Boolean)newValue);
				return;
			case SoapPackage.SOAP_PROVIDES__SYNCHONOUS_TIMEOUT:
				setSynchonousTimeout((Integer)newValue);
				return;
			case SoapPackage.SOAP_PROVIDES__CLEANUP_TRANSPORT:
				setCleanupTransport((Boolean)newValue);
				return;
			case SoapPackage.SOAP_PROVIDES__MODE:
				setMode((SoapMode)newValue);
				return;
			case SoapPackage.SOAP_PROVIDES__PROXY_HOST:
				setProxyHost((String)newValue);
				return;
			case SoapPackage.SOAP_PROVIDES__PROXY_USER:
				setProxyUser((String)newValue);
				return;
			case SoapPackage.SOAP_PROVIDES__PROXY_PASSWORD:
				setProxyPassword((String)newValue);
				return;
			case SoapPackage.SOAP_PROVIDES__PROXY_DOMAIN:
				setProxyDomain((String)newValue);
				return;
			case SoapPackage.SOAP_PROVIDES__PROXY_PORT:
				setProxyPort((Integer)newValue);
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
			case SoapPackage.SOAP_PROVIDES__ADDRESS:
				setAddress(ADDRESS_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__SOAP_VERSION:
				setSoapVersion(SOAP_VERSION_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__CHUNKED_MODE:
				setChunkedMode(CHUNKED_MODE_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__SYNCHONOUS_TIMEOUT:
				setSynchonousTimeout(SYNCHONOUS_TIMEOUT_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__CLEANUP_TRANSPORT:
				setCleanupTransport(CLEANUP_TRANSPORT_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__MODE:
				setMode(MODE_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__PROXY_HOST:
				setProxyHost(PROXY_HOST_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__PROXY_USER:
				setProxyUser(PROXY_USER_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__PROXY_PASSWORD:
				setProxyPassword(PROXY_PASSWORD_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__PROXY_DOMAIN:
				setProxyDomain(PROXY_DOMAIN_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__PROXY_PORT:
				setProxyPort(PROXY_PORT_EDEFAULT);
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
			case SoapPackage.SOAP_PROVIDES__ADDRESS:
				return ADDRESS_EDEFAULT == null ? address != null : !ADDRESS_EDEFAULT.equals(address);
			case SoapPackage.SOAP_PROVIDES__SOAP_VERSION:
				return soapVersion != SOAP_VERSION_EDEFAULT;
			case SoapPackage.SOAP_PROVIDES__CHUNKED_MODE:
				return chunkedMode != CHUNKED_MODE_EDEFAULT;
			case SoapPackage.SOAP_PROVIDES__SYNCHONOUS_TIMEOUT:
				return synchonousTimeout != SYNCHONOUS_TIMEOUT_EDEFAULT;
			case SoapPackage.SOAP_PROVIDES__CLEANUP_TRANSPORT:
				return cleanupTransport != CLEANUP_TRANSPORT_EDEFAULT;
			case SoapPackage.SOAP_PROVIDES__MODE:
				return mode != MODE_EDEFAULT;
			case SoapPackage.SOAP_PROVIDES__PROXY_HOST:
				return PROXY_HOST_EDEFAULT == null ? proxyHost != null : !PROXY_HOST_EDEFAULT.equals(proxyHost);
			case SoapPackage.SOAP_PROVIDES__PROXY_USER:
				return PROXY_USER_EDEFAULT == null ? proxyUser != null : !PROXY_USER_EDEFAULT.equals(proxyUser);
			case SoapPackage.SOAP_PROVIDES__PROXY_PASSWORD:
				return PROXY_PASSWORD_EDEFAULT == null ? proxyPassword != null : !PROXY_PASSWORD_EDEFAULT.equals(proxyPassword);
			case SoapPackage.SOAP_PROVIDES__PROXY_DOMAIN:
				return PROXY_DOMAIN_EDEFAULT == null ? proxyDomain != null : !PROXY_DOMAIN_EDEFAULT.equals(proxyDomain);
			case SoapPackage.SOAP_PROVIDES__PROXY_PORT:
				return proxyPort != PROXY_PORT_EDEFAULT;
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
		result.append(" (address: ");
		result.append(address);
		result.append(", soapVersion: ");
		result.append(soapVersion);
		result.append(", chunkedMode: ");
		result.append(chunkedMode);
		result.append(", synchonousTimeout: ");
		result.append(synchonousTimeout);
		result.append(", cleanupTransport: ");
		result.append(cleanupTransport);
		result.append(", mode: ");
		result.append(mode);
		result.append(", proxyHost: ");
		result.append(proxyHost);
		result.append(", proxyUser: ");
		result.append(proxyUser);
		result.append(", proxyPassword: ");
		result.append(proxyPassword);
		result.append(", proxyDomain: ");
		result.append(proxyDomain);
		result.append(", proxyPort: ");
		result.append(proxyPort);
		result.append(')');
		return result.toString();
	}

} //SoapProvidesImpl
