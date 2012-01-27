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

import com.ebmwebsourcing.petals.services.soap.soap.Compatibility;
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
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getWsaReplyTo <em>Wsa Reply To</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getWsaFrom <em>Wsa From</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getWsaFaultTo <em>Wsa Fault To</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getSoapVersion <em>Soap Version</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#isChunkedMode <em>Chunked Mode</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getSynchonousTimeout <em>Synchonous Timeout</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#isCleanupTransport <em>Cleanup Transport</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getMode <em>Mode</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getProxyHost <em>Proxy Host</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getProxyPort <em>Proxy Port</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getProxyUser <em>Proxy User</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getProxyPassword <em>Proxy Password</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getProxyDomain <em>Proxy Domain</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getHeadersFilter <em>Headers Filter</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getInjectHeaders <em>Inject Headers</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getHeadersToInject <em>Headers To Inject</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getHttpBasicAuthUsername <em>Http Basic Auth Username</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getHttpBasicAuthPassword <em>Http Basic Auth Password</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getEnableCompatibilityFor <em>Enable Compatibility For</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#isEnableWsa <em>Enable Wsa</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getHttpsTruststoreFile <em>Https Truststore File</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getHttpsTruststorePassword <em>Https Truststore Password</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getHttpsKeystoreFile <em>Https Keystore File</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl#getHttpsKeystorePassword <em>Https Keystore Password</em>}</li>
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
	 * The default value of the '{@link #getWsaReplyTo() <em>Wsa Reply To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWsaReplyTo()
	 * @generated
	 * @ordered
	 */
	protected static final String WSA_REPLY_TO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWsaReplyTo() <em>Wsa Reply To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWsaReplyTo()
	 * @generated
	 * @ordered
	 */
	protected String wsaReplyTo = WSA_REPLY_TO_EDEFAULT;

	/**
	 * The default value of the '{@link #getWsaFrom() <em>Wsa From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWsaFrom()
	 * @generated
	 * @ordered
	 */
	protected static final String WSA_FROM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWsaFrom() <em>Wsa From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWsaFrom()
	 * @generated
	 * @ordered
	 */
	protected String wsaFrom = WSA_FROM_EDEFAULT;

	/**
	 * The default value of the '{@link #getWsaFaultTo() <em>Wsa Fault To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWsaFaultTo()
	 * @generated
	 * @ordered
	 */
	protected static final String WSA_FAULT_TO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWsaFaultTo() <em>Wsa Fault To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWsaFaultTo()
	 * @generated
	 * @ordered
	 */
	protected String wsaFaultTo = WSA_FAULT_TO_EDEFAULT;

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
	 * This is true if the Chunked Mode attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean chunkedModeESet;

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
	 * This is true if the Cleanup Transport attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean cleanupTransportESet;

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
	 * The default value of the '{@link #getHeadersFilter() <em>Headers Filter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeadersFilter()
	 * @generated
	 * @ordered
	 */
	protected static final String HEADERS_FILTER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHeadersFilter() <em>Headers Filter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeadersFilter()
	 * @generated
	 * @ordered
	 */
	protected String headersFilter = HEADERS_FILTER_EDEFAULT;

	/**
	 * The default value of the '{@link #getInjectHeaders() <em>Inject Headers</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInjectHeaders()
	 * @generated
	 * @ordered
	 */
	protected static final String INJECT_HEADERS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInjectHeaders() <em>Inject Headers</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInjectHeaders()
	 * @generated
	 * @ordered
	 */
	protected String injectHeaders = INJECT_HEADERS_EDEFAULT;

	/**
	 * The default value of the '{@link #getHeadersToInject() <em>Headers To Inject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeadersToInject()
	 * @generated
	 * @ordered
	 */
	protected static final String HEADERS_TO_INJECT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHeadersToInject() <em>Headers To Inject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeadersToInject()
	 * @generated
	 * @ordered
	 */
	protected String headersToInject = HEADERS_TO_INJECT_EDEFAULT;

	/**
	 * The default value of the '{@link #getHttpBasicAuthUsername() <em>Http Basic Auth Username</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpBasicAuthUsername()
	 * @generated
	 * @ordered
	 */
	protected static final String HTTP_BASIC_AUTH_USERNAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHttpBasicAuthUsername() <em>Http Basic Auth Username</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpBasicAuthUsername()
	 * @generated
	 * @ordered
	 */
	protected String httpBasicAuthUsername = HTTP_BASIC_AUTH_USERNAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getHttpBasicAuthPassword() <em>Http Basic Auth Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpBasicAuthPassword()
	 * @generated
	 * @ordered
	 */
	protected static final String HTTP_BASIC_AUTH_PASSWORD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHttpBasicAuthPassword() <em>Http Basic Auth Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpBasicAuthPassword()
	 * @generated
	 * @ordered
	 */
	protected String httpBasicAuthPassword = HTTP_BASIC_AUTH_PASSWORD_EDEFAULT;

	/**
	 * The default value of the '{@link #getEnableCompatibilityFor() <em>Enable Compatibility For</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnableCompatibilityFor()
	 * @generated
	 * @ordered
	 */
	protected static final Compatibility ENABLE_COMPATIBILITY_FOR_EDEFAULT = Compatibility.AXIS1;

	/**
	 * The cached value of the '{@link #getEnableCompatibilityFor() <em>Enable Compatibility For</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnableCompatibilityFor()
	 * @generated
	 * @ordered
	 */
	protected Compatibility enableCompatibilityFor = ENABLE_COMPATIBILITY_FOR_EDEFAULT;

	/**
	 * The default value of the '{@link #isEnableWsa() <em>Enable Wsa</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnableWsa()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLE_WSA_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEnableWsa() <em>Enable Wsa</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnableWsa()
	 * @generated
	 * @ordered
	 */
	protected boolean enableWsa = ENABLE_WSA_EDEFAULT;

	/**
	 * The default value of the '{@link #getHttpsTruststoreFile() <em>Https Truststore File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpsTruststoreFile()
	 * @generated
	 * @ordered
	 */
	protected static final String HTTPS_TRUSTSTORE_FILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHttpsTruststoreFile() <em>Https Truststore File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpsTruststoreFile()
	 * @generated
	 * @ordered
	 */
	protected String httpsTruststoreFile = HTTPS_TRUSTSTORE_FILE_EDEFAULT;

	/**
	 * The default value of the '{@link #getHttpsTruststorePassword() <em>Https Truststore Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpsTruststorePassword()
	 * @generated
	 * @ordered
	 */
	protected static final String HTTPS_TRUSTSTORE_PASSWORD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHttpsTruststorePassword() <em>Https Truststore Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpsTruststorePassword()
	 * @generated
	 * @ordered
	 */
	protected String httpsTruststorePassword = HTTPS_TRUSTSTORE_PASSWORD_EDEFAULT;

	/**
	 * The default value of the '{@link #getHttpsKeystoreFile() <em>Https Keystore File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpsKeystoreFile()
	 * @generated
	 * @ordered
	 */
	protected static final String HTTPS_KEYSTORE_FILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHttpsKeystoreFile() <em>Https Keystore File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpsKeystoreFile()
	 * @generated
	 * @ordered
	 */
	protected String httpsKeystoreFile = HTTPS_KEYSTORE_FILE_EDEFAULT;

	/**
	 * The default value of the '{@link #getHttpsKeystorePassword() <em>Https Keystore Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpsKeystorePassword()
	 * @generated
	 * @ordered
	 */
	protected static final String HTTPS_KEYSTORE_PASSWORD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHttpsKeystorePassword() <em>Https Keystore Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpsKeystorePassword()
	 * @generated
	 * @ordered
	 */
	protected String httpsKeystorePassword = HTTPS_KEYSTORE_PASSWORD_EDEFAULT;

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
	public String getWsaReplyTo() {
		return wsaReplyTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWsaReplyTo(String newWsaReplyTo) {
		String oldWsaReplyTo = wsaReplyTo;
		wsaReplyTo = newWsaReplyTo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__WSA_REPLY_TO, oldWsaReplyTo, wsaReplyTo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWsaFrom() {
		return wsaFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWsaFrom(String newWsaFrom) {
		String oldWsaFrom = wsaFrom;
		wsaFrom = newWsaFrom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__WSA_FROM, oldWsaFrom, wsaFrom));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWsaFaultTo() {
		return wsaFaultTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWsaFaultTo(String newWsaFaultTo) {
		String oldWsaFaultTo = wsaFaultTo;
		wsaFaultTo = newWsaFaultTo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__WSA_FAULT_TO, oldWsaFaultTo, wsaFaultTo));
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
		boolean oldChunkedModeESet = chunkedModeESet;
		chunkedModeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__CHUNKED_MODE, oldChunkedMode, chunkedMode, !oldChunkedModeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetChunkedMode() {
		boolean oldChunkedMode = chunkedMode;
		boolean oldChunkedModeESet = chunkedModeESet;
		chunkedMode = CHUNKED_MODE_EDEFAULT;
		chunkedModeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, SoapPackage.SOAP_PROVIDES__CHUNKED_MODE, oldChunkedMode, CHUNKED_MODE_EDEFAULT, oldChunkedModeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetChunkedMode() {
		return chunkedModeESet;
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
		boolean oldCleanupTransportESet = cleanupTransportESet;
		cleanupTransportESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__CLEANUP_TRANSPORT, oldCleanupTransport, cleanupTransport, !oldCleanupTransportESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetCleanupTransport() {
		boolean oldCleanupTransport = cleanupTransport;
		boolean oldCleanupTransportESet = cleanupTransportESet;
		cleanupTransport = CLEANUP_TRANSPORT_EDEFAULT;
		cleanupTransportESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, SoapPackage.SOAP_PROVIDES__CLEANUP_TRANSPORT, oldCleanupTransport, CLEANUP_TRANSPORT_EDEFAULT, oldCleanupTransportESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetCleanupTransport() {
		return cleanupTransportESet;
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
	public String getHeadersFilter() {
		return headersFilter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHeadersFilter(String newHeadersFilter) {
		String oldHeadersFilter = headersFilter;
		headersFilter = newHeadersFilter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__HEADERS_FILTER, oldHeadersFilter, headersFilter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInjectHeaders() {
		return injectHeaders;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInjectHeaders(String newInjectHeaders) {
		String oldInjectHeaders = injectHeaders;
		injectHeaders = newInjectHeaders;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__INJECT_HEADERS, oldInjectHeaders, injectHeaders));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHeadersToInject() {
		return headersToInject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHeadersToInject(String newHeadersToInject) {
		String oldHeadersToInject = headersToInject;
		headersToInject = newHeadersToInject;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__HEADERS_TO_INJECT, oldHeadersToInject, headersToInject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHttpBasicAuthUsername() {
		return httpBasicAuthUsername;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHttpBasicAuthUsername(String newHttpBasicAuthUsername) {
		String oldHttpBasicAuthUsername = httpBasicAuthUsername;
		httpBasicAuthUsername = newHttpBasicAuthUsername;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__HTTP_BASIC_AUTH_USERNAME, oldHttpBasicAuthUsername, httpBasicAuthUsername));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHttpBasicAuthPassword() {
		return httpBasicAuthPassword;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHttpBasicAuthPassword(String newHttpBasicAuthPassword) {
		String oldHttpBasicAuthPassword = httpBasicAuthPassword;
		httpBasicAuthPassword = newHttpBasicAuthPassword;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__HTTP_BASIC_AUTH_PASSWORD, oldHttpBasicAuthPassword, httpBasicAuthPassword));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Compatibility getEnableCompatibilityFor() {
		return enableCompatibilityFor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnableCompatibilityFor(Compatibility newEnableCompatibilityFor) {
		Compatibility oldEnableCompatibilityFor = enableCompatibilityFor;
		enableCompatibilityFor = newEnableCompatibilityFor == null ? ENABLE_COMPATIBILITY_FOR_EDEFAULT : newEnableCompatibilityFor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__ENABLE_COMPATIBILITY_FOR, oldEnableCompatibilityFor, enableCompatibilityFor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEnableWsa() {
		return enableWsa;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnableWsa(boolean newEnableWsa) {
		boolean oldEnableWsa = enableWsa;
		enableWsa = newEnableWsa;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__ENABLE_WSA, oldEnableWsa, enableWsa));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHttpsTruststoreFile() {
		return httpsTruststoreFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHttpsTruststoreFile(String newHttpsTruststoreFile) {
		String oldHttpsTruststoreFile = httpsTruststoreFile;
		httpsTruststoreFile = newHttpsTruststoreFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__HTTPS_TRUSTSTORE_FILE, oldHttpsTruststoreFile, httpsTruststoreFile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHttpsTruststorePassword() {
		return httpsTruststorePassword;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHttpsTruststorePassword(String newHttpsTruststorePassword) {
		String oldHttpsTruststorePassword = httpsTruststorePassword;
		httpsTruststorePassword = newHttpsTruststorePassword;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__HTTPS_TRUSTSTORE_PASSWORD, oldHttpsTruststorePassword, httpsTruststorePassword));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHttpsKeystoreFile() {
		return httpsKeystoreFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHttpsKeystoreFile(String newHttpsKeystoreFile) {
		String oldHttpsKeystoreFile = httpsKeystoreFile;
		httpsKeystoreFile = newHttpsKeystoreFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__HTTPS_KEYSTORE_FILE, oldHttpsKeystoreFile, httpsKeystoreFile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHttpsKeystorePassword() {
		return httpsKeystorePassword;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHttpsKeystorePassword(String newHttpsKeystorePassword) {
		String oldHttpsKeystorePassword = httpsKeystorePassword;
		httpsKeystorePassword = newHttpsKeystorePassword;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_PROVIDES__HTTPS_KEYSTORE_PASSWORD, oldHttpsKeystorePassword, httpsKeystorePassword));
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
			case SoapPackage.SOAP_PROVIDES__WSA_REPLY_TO:
				return getWsaReplyTo();
			case SoapPackage.SOAP_PROVIDES__WSA_FROM:
				return getWsaFrom();
			case SoapPackage.SOAP_PROVIDES__WSA_FAULT_TO:
				return getWsaFaultTo();
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
			case SoapPackage.SOAP_PROVIDES__PROXY_PORT:
				return getProxyPort();
			case SoapPackage.SOAP_PROVIDES__PROXY_USER:
				return getProxyUser();
			case SoapPackage.SOAP_PROVIDES__PROXY_PASSWORD:
				return getProxyPassword();
			case SoapPackage.SOAP_PROVIDES__PROXY_DOMAIN:
				return getProxyDomain();
			case SoapPackage.SOAP_PROVIDES__HEADERS_FILTER:
				return getHeadersFilter();
			case SoapPackage.SOAP_PROVIDES__INJECT_HEADERS:
				return getInjectHeaders();
			case SoapPackage.SOAP_PROVIDES__HEADERS_TO_INJECT:
				return getHeadersToInject();
			case SoapPackage.SOAP_PROVIDES__HTTP_BASIC_AUTH_USERNAME:
				return getHttpBasicAuthUsername();
			case SoapPackage.SOAP_PROVIDES__HTTP_BASIC_AUTH_PASSWORD:
				return getHttpBasicAuthPassword();
			case SoapPackage.SOAP_PROVIDES__ENABLE_COMPATIBILITY_FOR:
				return getEnableCompatibilityFor();
			case SoapPackage.SOAP_PROVIDES__ENABLE_WSA:
				return isEnableWsa();
			case SoapPackage.SOAP_PROVIDES__HTTPS_TRUSTSTORE_FILE:
				return getHttpsTruststoreFile();
			case SoapPackage.SOAP_PROVIDES__HTTPS_TRUSTSTORE_PASSWORD:
				return getHttpsTruststorePassword();
			case SoapPackage.SOAP_PROVIDES__HTTPS_KEYSTORE_FILE:
				return getHttpsKeystoreFile();
			case SoapPackage.SOAP_PROVIDES__HTTPS_KEYSTORE_PASSWORD:
				return getHttpsKeystorePassword();
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
			case SoapPackage.SOAP_PROVIDES__WSA_REPLY_TO:
				setWsaReplyTo((String)newValue);
				return;
			case SoapPackage.SOAP_PROVIDES__WSA_FROM:
				setWsaFrom((String)newValue);
				return;
			case SoapPackage.SOAP_PROVIDES__WSA_FAULT_TO:
				setWsaFaultTo((String)newValue);
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
			case SoapPackage.SOAP_PROVIDES__PROXY_PORT:
				setProxyPort((Integer)newValue);
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
			case SoapPackage.SOAP_PROVIDES__HEADERS_FILTER:
				setHeadersFilter((String)newValue);
				return;
			case SoapPackage.SOAP_PROVIDES__INJECT_HEADERS:
				setInjectHeaders((String)newValue);
				return;
			case SoapPackage.SOAP_PROVIDES__HEADERS_TO_INJECT:
				setHeadersToInject((String)newValue);
				return;
			case SoapPackage.SOAP_PROVIDES__HTTP_BASIC_AUTH_USERNAME:
				setHttpBasicAuthUsername((String)newValue);
				return;
			case SoapPackage.SOAP_PROVIDES__HTTP_BASIC_AUTH_PASSWORD:
				setHttpBasicAuthPassword((String)newValue);
				return;
			case SoapPackage.SOAP_PROVIDES__ENABLE_COMPATIBILITY_FOR:
				setEnableCompatibilityFor((Compatibility)newValue);
				return;
			case SoapPackage.SOAP_PROVIDES__ENABLE_WSA:
				setEnableWsa((Boolean)newValue);
				return;
			case SoapPackage.SOAP_PROVIDES__HTTPS_TRUSTSTORE_FILE:
				setHttpsTruststoreFile((String)newValue);
				return;
			case SoapPackage.SOAP_PROVIDES__HTTPS_TRUSTSTORE_PASSWORD:
				setHttpsTruststorePassword((String)newValue);
				return;
			case SoapPackage.SOAP_PROVIDES__HTTPS_KEYSTORE_FILE:
				setHttpsKeystoreFile((String)newValue);
				return;
			case SoapPackage.SOAP_PROVIDES__HTTPS_KEYSTORE_PASSWORD:
				setHttpsKeystorePassword((String)newValue);
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
			case SoapPackage.SOAP_PROVIDES__WSA_REPLY_TO:
				setWsaReplyTo(WSA_REPLY_TO_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__WSA_FROM:
				setWsaFrom(WSA_FROM_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__WSA_FAULT_TO:
				setWsaFaultTo(WSA_FAULT_TO_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__SOAP_VERSION:
				setSoapVersion(SOAP_VERSION_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__CHUNKED_MODE:
				unsetChunkedMode();
				return;
			case SoapPackage.SOAP_PROVIDES__SYNCHONOUS_TIMEOUT:
				setSynchonousTimeout(SYNCHONOUS_TIMEOUT_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__CLEANUP_TRANSPORT:
				unsetCleanupTransport();
				return;
			case SoapPackage.SOAP_PROVIDES__MODE:
				setMode(MODE_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__PROXY_HOST:
				setProxyHost(PROXY_HOST_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__PROXY_PORT:
				setProxyPort(PROXY_PORT_EDEFAULT);
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
			case SoapPackage.SOAP_PROVIDES__HEADERS_FILTER:
				setHeadersFilter(HEADERS_FILTER_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__INJECT_HEADERS:
				setInjectHeaders(INJECT_HEADERS_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__HEADERS_TO_INJECT:
				setHeadersToInject(HEADERS_TO_INJECT_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__HTTP_BASIC_AUTH_USERNAME:
				setHttpBasicAuthUsername(HTTP_BASIC_AUTH_USERNAME_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__HTTP_BASIC_AUTH_PASSWORD:
				setHttpBasicAuthPassword(HTTP_BASIC_AUTH_PASSWORD_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__ENABLE_COMPATIBILITY_FOR:
				setEnableCompatibilityFor(ENABLE_COMPATIBILITY_FOR_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__ENABLE_WSA:
				setEnableWsa(ENABLE_WSA_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__HTTPS_TRUSTSTORE_FILE:
				setHttpsTruststoreFile(HTTPS_TRUSTSTORE_FILE_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__HTTPS_TRUSTSTORE_PASSWORD:
				setHttpsTruststorePassword(HTTPS_TRUSTSTORE_PASSWORD_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__HTTPS_KEYSTORE_FILE:
				setHttpsKeystoreFile(HTTPS_KEYSTORE_FILE_EDEFAULT);
				return;
			case SoapPackage.SOAP_PROVIDES__HTTPS_KEYSTORE_PASSWORD:
				setHttpsKeystorePassword(HTTPS_KEYSTORE_PASSWORD_EDEFAULT);
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
			case SoapPackage.SOAP_PROVIDES__WSA_REPLY_TO:
				return WSA_REPLY_TO_EDEFAULT == null ? wsaReplyTo != null : !WSA_REPLY_TO_EDEFAULT.equals(wsaReplyTo);
			case SoapPackage.SOAP_PROVIDES__WSA_FROM:
				return WSA_FROM_EDEFAULT == null ? wsaFrom != null : !WSA_FROM_EDEFAULT.equals(wsaFrom);
			case SoapPackage.SOAP_PROVIDES__WSA_FAULT_TO:
				return WSA_FAULT_TO_EDEFAULT == null ? wsaFaultTo != null : !WSA_FAULT_TO_EDEFAULT.equals(wsaFaultTo);
			case SoapPackage.SOAP_PROVIDES__SOAP_VERSION:
				return soapVersion != SOAP_VERSION_EDEFAULT;
			case SoapPackage.SOAP_PROVIDES__CHUNKED_MODE:
				return isSetChunkedMode();
			case SoapPackage.SOAP_PROVIDES__SYNCHONOUS_TIMEOUT:
				return synchonousTimeout != SYNCHONOUS_TIMEOUT_EDEFAULT;
			case SoapPackage.SOAP_PROVIDES__CLEANUP_TRANSPORT:
				return isSetCleanupTransport();
			case SoapPackage.SOAP_PROVIDES__MODE:
				return mode != MODE_EDEFAULT;
			case SoapPackage.SOAP_PROVIDES__PROXY_HOST:
				return PROXY_HOST_EDEFAULT == null ? proxyHost != null : !PROXY_HOST_EDEFAULT.equals(proxyHost);
			case SoapPackage.SOAP_PROVIDES__PROXY_PORT:
				return proxyPort != PROXY_PORT_EDEFAULT;
			case SoapPackage.SOAP_PROVIDES__PROXY_USER:
				return PROXY_USER_EDEFAULT == null ? proxyUser != null : !PROXY_USER_EDEFAULT.equals(proxyUser);
			case SoapPackage.SOAP_PROVIDES__PROXY_PASSWORD:
				return PROXY_PASSWORD_EDEFAULT == null ? proxyPassword != null : !PROXY_PASSWORD_EDEFAULT.equals(proxyPassword);
			case SoapPackage.SOAP_PROVIDES__PROXY_DOMAIN:
				return PROXY_DOMAIN_EDEFAULT == null ? proxyDomain != null : !PROXY_DOMAIN_EDEFAULT.equals(proxyDomain);
			case SoapPackage.SOAP_PROVIDES__HEADERS_FILTER:
				return HEADERS_FILTER_EDEFAULT == null ? headersFilter != null : !HEADERS_FILTER_EDEFAULT.equals(headersFilter);
			case SoapPackage.SOAP_PROVIDES__INJECT_HEADERS:
				return INJECT_HEADERS_EDEFAULT == null ? injectHeaders != null : !INJECT_HEADERS_EDEFAULT.equals(injectHeaders);
			case SoapPackage.SOAP_PROVIDES__HEADERS_TO_INJECT:
				return HEADERS_TO_INJECT_EDEFAULT == null ? headersToInject != null : !HEADERS_TO_INJECT_EDEFAULT.equals(headersToInject);
			case SoapPackage.SOAP_PROVIDES__HTTP_BASIC_AUTH_USERNAME:
				return HTTP_BASIC_AUTH_USERNAME_EDEFAULT == null ? httpBasicAuthUsername != null : !HTTP_BASIC_AUTH_USERNAME_EDEFAULT.equals(httpBasicAuthUsername);
			case SoapPackage.SOAP_PROVIDES__HTTP_BASIC_AUTH_PASSWORD:
				return HTTP_BASIC_AUTH_PASSWORD_EDEFAULT == null ? httpBasicAuthPassword != null : !HTTP_BASIC_AUTH_PASSWORD_EDEFAULT.equals(httpBasicAuthPassword);
			case SoapPackage.SOAP_PROVIDES__ENABLE_COMPATIBILITY_FOR:
				return enableCompatibilityFor != ENABLE_COMPATIBILITY_FOR_EDEFAULT;
			case SoapPackage.SOAP_PROVIDES__ENABLE_WSA:
				return enableWsa != ENABLE_WSA_EDEFAULT;
			case SoapPackage.SOAP_PROVIDES__HTTPS_TRUSTSTORE_FILE:
				return HTTPS_TRUSTSTORE_FILE_EDEFAULT == null ? httpsTruststoreFile != null : !HTTPS_TRUSTSTORE_FILE_EDEFAULT.equals(httpsTruststoreFile);
			case SoapPackage.SOAP_PROVIDES__HTTPS_TRUSTSTORE_PASSWORD:
				return HTTPS_TRUSTSTORE_PASSWORD_EDEFAULT == null ? httpsTruststorePassword != null : !HTTPS_TRUSTSTORE_PASSWORD_EDEFAULT.equals(httpsTruststorePassword);
			case SoapPackage.SOAP_PROVIDES__HTTPS_KEYSTORE_FILE:
				return HTTPS_KEYSTORE_FILE_EDEFAULT == null ? httpsKeystoreFile != null : !HTTPS_KEYSTORE_FILE_EDEFAULT.equals(httpsKeystoreFile);
			case SoapPackage.SOAP_PROVIDES__HTTPS_KEYSTORE_PASSWORD:
				return HTTPS_KEYSTORE_PASSWORD_EDEFAULT == null ? httpsKeystorePassword != null : !HTTPS_KEYSTORE_PASSWORD_EDEFAULT.equals(httpsKeystorePassword);
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
		result.append(", wsaReplyTo: ");
		result.append(wsaReplyTo);
		result.append(", wsaFrom: ");
		result.append(wsaFrom);
		result.append(", wsaFaultTo: ");
		result.append(wsaFaultTo);
		result.append(", soapVersion: ");
		result.append(soapVersion);
		result.append(", chunkedMode: ");
		if (chunkedModeESet) result.append(chunkedMode); else result.append("<unset>");
		result.append(", synchonousTimeout: ");
		result.append(synchonousTimeout);
		result.append(", cleanupTransport: ");
		if (cleanupTransportESet) result.append(cleanupTransport); else result.append("<unset>");
		result.append(", mode: ");
		result.append(mode);
		result.append(", proxyHost: ");
		result.append(proxyHost);
		result.append(", proxyPort: ");
		result.append(proxyPort);
		result.append(", proxyUser: ");
		result.append(proxyUser);
		result.append(", proxyPassword: ");
		result.append(proxyPassword);
		result.append(", proxyDomain: ");
		result.append(proxyDomain);
		result.append(", headersFilter: ");
		result.append(headersFilter);
		result.append(", injectHeaders: ");
		result.append(injectHeaders);
		result.append(", headersToInject: ");
		result.append(headersToInject);
		result.append(", httpBasicAuthUsername: ");
		result.append(httpBasicAuthUsername);
		result.append(", httpBasicAuthPassword: ");
		result.append(httpBasicAuthPassword);
		result.append(", enableCompatibilityFor: ");
		result.append(enableCompatibilityFor);
		result.append(", enableWsa: ");
		result.append(enableWsa);
		result.append(", httpsTruststoreFile: ");
		result.append(httpsTruststoreFile);
		result.append(", httpsTruststorePassword: ");
		result.append(httpsTruststorePassword);
		result.append(", httpsKeystoreFile: ");
		result.append(httpsKeystoreFile);
		result.append(", httpsKeystorePassword: ");
		result.append(httpsKeystorePassword);
		result.append(')');
		return result.toString();
	}

} //SoapProvidesImpl
