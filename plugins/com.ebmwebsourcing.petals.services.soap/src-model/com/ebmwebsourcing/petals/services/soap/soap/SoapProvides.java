/**
 * Copyright (c) 2011-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 */
package com.ebmwebsourcing.petals.services.soap.soap;

import com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Provides;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Provides</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getAddress <em>Address</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getWsaReplyTo <em>Wsa Reply To</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getWsaFrom <em>Wsa From</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getWsaFaultTo <em>Wsa Fault To</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getSoapVersion <em>Soap Version</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#isChunkedMode <em>Chunked Mode</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getSynchonousTimeout <em>Synchonous Timeout</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#isCleanupTransport <em>Cleanup Transport</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getMode <em>Mode</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getProxyHost <em>Proxy Host</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getProxyPort <em>Proxy Port</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getProxyUser <em>Proxy User</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getProxyPassword <em>Proxy Password</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getProxyDomain <em>Proxy Domain</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getHeadersFilter <em>Headers Filter</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getInjectHeaders <em>Inject Headers</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getHeadersToInject <em>Headers To Inject</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getHttpBasicAuthUsername <em>Http Basic Auth Username</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getHttpBasicAuthPassword <em>Http Basic Auth Password</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getEnableCompatibilityFor <em>Enable Compatibility For</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#isEnableWsa <em>Enable Wsa</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getHttpsTruststoreFile <em>Https Truststore File</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getHttpsTruststorePassword <em>Https Truststore Password</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getHttpsKeystoreFile <em>Https Keystore File</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getHttpsKeystorePassword <em>Https Keystore Password</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface SoapProvides extends CDK5Provides {
	/**
	 * Returns the value of the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Address</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Address</em>' attribute.
	 * @see #isSetAddress()
	 * @see #unsetAddress()
	 * @see #setAddress(String)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_Address()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	String getAddress();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getAddress <em>Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Address</em>' attribute.
	 * @see #isSetAddress()
	 * @see #unsetAddress()
	 * @see #getAddress()
	 * @generated
	 */
	void setAddress(String value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getAddress <em>Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAddress()
	 * @see #getAddress()
	 * @see #setAddress(String)
	 * @generated
	 */
	void unsetAddress();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getAddress <em>Address</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Address</em>' attribute is set.
	 * @see #unsetAddress()
	 * @see #getAddress()
	 * @see #setAddress(String)
	 * @generated
	 */
	boolean isSetAddress();

	/**
	 * Returns the value of the '<em><b>Wsa Reply To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wsa Reply To</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wsa Reply To</em>' attribute.
	 * @see #setWsaReplyTo(String)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_WsaReplyTo()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='wsa-replyto'"
	 * @generated
	 */
	String getWsaReplyTo();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getWsaReplyTo <em>Wsa Reply To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wsa Reply To</em>' attribute.
	 * @see #getWsaReplyTo()
	 * @generated
	 */
	void setWsaReplyTo(String value);

	/**
	 * Returns the value of the '<em><b>Wsa From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wsa From</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wsa From</em>' attribute.
	 * @see #setWsaFrom(String)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_WsaFrom()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='wsa-from'"
	 * @generated
	 */
	String getWsaFrom();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getWsaFrom <em>Wsa From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wsa From</em>' attribute.
	 * @see #getWsaFrom()
	 * @generated
	 */
	void setWsaFrom(String value);

	/**
	 * Returns the value of the '<em><b>Wsa Fault To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wsa Fault To</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wsa Fault To</em>' attribute.
	 * @see #setWsaFaultTo(String)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_WsaFaultTo()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='wsa-faultto'"
	 * @generated
	 */
	String getWsaFaultTo();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getWsaFaultTo <em>Wsa Fault To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wsa Fault To</em>' attribute.
	 * @see #getWsaFaultTo()
	 * @generated
	 */
	void setWsaFaultTo(String value);

	/**
	 * Returns the value of the '<em><b>Soap Version</b></em>' attribute.
	 * The literals are from the enumeration {@link com.ebmwebsourcing.petals.services.soap.soap.SoapVersion}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Soap Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Soap Version</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapVersion
	 * @see #isSetSoapVersion()
	 * @see #unsetSoapVersion()
	 * @see #setSoapVersion(SoapVersion)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_SoapVersion()
	 * @model unsettable="true" required="true" derived="true"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='soap-version'"
	 * @generated
	 */
	SoapVersion getSoapVersion();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getSoapVersion <em>Soap Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Soap Version</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapVersion
	 * @see #isSetSoapVersion()
	 * @see #unsetSoapVersion()
	 * @see #getSoapVersion()
	 * @generated
	 */
	void setSoapVersion(SoapVersion value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getSoapVersion <em>Soap Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSoapVersion()
	 * @see #getSoapVersion()
	 * @see #setSoapVersion(SoapVersion)
	 * @generated
	 */
	void unsetSoapVersion();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getSoapVersion <em>Soap Version</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Soap Version</em>' attribute is set.
	 * @see #unsetSoapVersion()
	 * @see #getSoapVersion()
	 * @see #setSoapVersion(SoapVersion)
	 * @generated
	 */
	boolean isSetSoapVersion();

	/**
	 * Returns the value of the '<em><b>Chunked Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Chunked Mode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Chunked Mode</em>' attribute.
	 * @see #isSetChunkedMode()
	 * @see #unsetChunkedMode()
	 * @see #setChunkedMode(boolean)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_ChunkedMode()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean" required="true" derived="true"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='chunked-mode'"
	 * @generated
	 */
	boolean isChunkedMode();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#isChunkedMode <em>Chunked Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Chunked Mode</em>' attribute.
	 * @see #isSetChunkedMode()
	 * @see #unsetChunkedMode()
	 * @see #isChunkedMode()
	 * @generated
	 */
	void setChunkedMode(boolean value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#isChunkedMode <em>Chunked Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetChunkedMode()
	 * @see #isChunkedMode()
	 * @see #setChunkedMode(boolean)
	 * @generated
	 */
	void unsetChunkedMode();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#isChunkedMode <em>Chunked Mode</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Chunked Mode</em>' attribute is set.
	 * @see #unsetChunkedMode()
	 * @see #isChunkedMode()
	 * @see #setChunkedMode(boolean)
	 * @generated
	 */
	boolean isSetChunkedMode();

	/**
	 * Returns the value of the '<em><b>Synchonous Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Synchonous Timeout</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Synchonous Timeout</em>' attribute.
	 * @see #setSynchonousTimeout(int)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_SynchonousTimeout()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" derived="true"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='synchronous-timeout'"
	 * @generated
	 */
	int getSynchonousTimeout();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getSynchonousTimeout <em>Synchonous Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Synchonous Timeout</em>' attribute.
	 * @see #getSynchonousTimeout()
	 * @generated
	 */
	void setSynchonousTimeout(int value);

	/**
	 * Returns the value of the '<em><b>Cleanup Transport</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cleanup Transport</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cleanup Transport</em>' attribute.
	 * @see #isSetCleanupTransport()
	 * @see #unsetCleanupTransport()
	 * @see #setCleanupTransport(boolean)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_CleanupTransport()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean" required="true" derived="true"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='cleanup-transport'"
	 * @generated
	 */
	boolean isCleanupTransport();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#isCleanupTransport <em>Cleanup Transport</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cleanup Transport</em>' attribute.
	 * @see #isSetCleanupTransport()
	 * @see #unsetCleanupTransport()
	 * @see #isCleanupTransport()
	 * @generated
	 */
	void setCleanupTransport(boolean value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#isCleanupTransport <em>Cleanup Transport</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetCleanupTransport()
	 * @see #isCleanupTransport()
	 * @see #setCleanupTransport(boolean)
	 * @generated
	 */
	void unsetCleanupTransport();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#isCleanupTransport <em>Cleanup Transport</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Cleanup Transport</em>' attribute is set.
	 * @see #unsetCleanupTransport()
	 * @see #isCleanupTransport()
	 * @see #setCleanupTransport(boolean)
	 * @generated
	 */
	boolean isSetCleanupTransport();

	/**
	 * Returns the value of the '<em><b>Mode</b></em>' attribute.
	 * The literals are from the enumeration {@link com.ebmwebsourcing.petals.services.soap.soap.SoapMode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mode</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapMode
	 * @see #isSetMode()
	 * @see #unsetMode()
	 * @see #setMode(SoapMode)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_Mode()
	 * @model unsettable="true" required="true" derived="true"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	SoapMode getMode();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getMode <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mode</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapMode
	 * @see #isSetMode()
	 * @see #unsetMode()
	 * @see #getMode()
	 * @generated
	 */
	void setMode(SoapMode value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getMode <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMode()
	 * @see #getMode()
	 * @see #setMode(SoapMode)
	 * @generated
	 */
	void unsetMode();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getMode <em>Mode</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Mode</em>' attribute is set.
	 * @see #unsetMode()
	 * @see #getMode()
	 * @see #setMode(SoapMode)
	 * @generated
	 */
	boolean isSetMode();

	/**
	 * Returns the value of the '<em><b>Proxy Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Proxy Host</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Proxy Host</em>' attribute.
	 * @see #setProxyHost(String)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_ProxyHost()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='proxy-host'"
	 * @generated
	 */
	String getProxyHost();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getProxyHost <em>Proxy Host</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Proxy Host</em>' attribute.
	 * @see #getProxyHost()
	 * @generated
	 */
	void setProxyHost(String value);

	/**
	 * Returns the value of the '<em><b>Proxy Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Proxy Port</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Proxy Port</em>' attribute.
	 * @see #setProxyPort(int)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_ProxyPort()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='proxy-port'"
	 * @generated
	 */
	int getProxyPort();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getProxyPort <em>Proxy Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Proxy Port</em>' attribute.
	 * @see #getProxyPort()
	 * @generated
	 */
	void setProxyPort(int value);

	/**
	 * Returns the value of the '<em><b>Proxy User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Proxy User</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Proxy User</em>' attribute.
	 * @see #setProxyUser(String)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_ProxyUser()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='proxy-user'"
	 * @generated
	 */
	String getProxyUser();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getProxyUser <em>Proxy User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Proxy User</em>' attribute.
	 * @see #getProxyUser()
	 * @generated
	 */
	void setProxyUser(String value);

	/**
	 * Returns the value of the '<em><b>Proxy Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Proxy Password</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Proxy Password</em>' attribute.
	 * @see #setProxyPassword(String)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_ProxyPassword()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='proxy-password'"
	 * @generated
	 */
	String getProxyPassword();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getProxyPassword <em>Proxy Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Proxy Password</em>' attribute.
	 * @see #getProxyPassword()
	 * @generated
	 */
	void setProxyPassword(String value);

	/**
	 * Returns the value of the '<em><b>Proxy Domain</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Proxy Domain</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Proxy Domain</em>' attribute.
	 * @see #setProxyDomain(String)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_ProxyDomain()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='proxy-domain'"
	 * @generated
	 */
	String getProxyDomain();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getProxyDomain <em>Proxy Domain</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Proxy Domain</em>' attribute.
	 * @see #getProxyDomain()
	 * @generated
	 */
	void setProxyDomain(String value);

	/**
	 * Returns the value of the '<em><b>Headers Filter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Headers Filter</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Headers Filter</em>' attribute.
	 * @see #setHeadersFilter(String)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_HeadersFilter()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='headers-filter'"
	 * @generated
	 */
	String getHeadersFilter();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getHeadersFilter <em>Headers Filter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Headers Filter</em>' attribute.
	 * @see #getHeadersFilter()
	 * @generated
	 */
	void setHeadersFilter(String value);

	/**
	 * Returns the value of the '<em><b>Inject Headers</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inject Headers</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inject Headers</em>' attribute.
	 * @see #setInjectHeaders(String)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_InjectHeaders()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='inject-headers'"
	 * @generated
	 */
	String getInjectHeaders();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getInjectHeaders <em>Inject Headers</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inject Headers</em>' attribute.
	 * @see #getInjectHeaders()
	 * @generated
	 */
	void setInjectHeaders(String value);

	/**
	 * Returns the value of the '<em><b>Headers To Inject</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Headers To Inject</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Headers To Inject</em>' attribute.
	 * @see #setHeadersToInject(String)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_HeadersToInject()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='headers-to-inject'"
	 * @generated
	 */
	String getHeadersToInject();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getHeadersToInject <em>Headers To Inject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Headers To Inject</em>' attribute.
	 * @see #getHeadersToInject()
	 * @generated
	 */
	void setHeadersToInject(String value);

	/**
	 * Returns the value of the '<em><b>Http Basic Auth Username</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Http Basic Auth Username</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Http Basic Auth Username</em>' attribute.
	 * @see #setHttpBasicAuthUsername(String)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_HttpBasicAuthUsername()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='http-basic-auth-username'"
	 * @generated
	 */
	String getHttpBasicAuthUsername();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getHttpBasicAuthUsername <em>Http Basic Auth Username</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Http Basic Auth Username</em>' attribute.
	 * @see #getHttpBasicAuthUsername()
	 * @generated
	 */
	void setHttpBasicAuthUsername(String value);

	/**
	 * Returns the value of the '<em><b>Http Basic Auth Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Http Basic Auth Password</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Http Basic Auth Password</em>' attribute.
	 * @see #setHttpBasicAuthPassword(String)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_HttpBasicAuthPassword()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='http-basic-auth-password'"
	 * @generated
	 */
	String getHttpBasicAuthPassword();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getHttpBasicAuthPassword <em>Http Basic Auth Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Http Basic Auth Password</em>' attribute.
	 * @see #getHttpBasicAuthPassword()
	 * @generated
	 */
	void setHttpBasicAuthPassword(String value);

	/**
	 * Returns the value of the '<em><b>Enable Compatibility For</b></em>' attribute.
	 * The literals are from the enumeration {@link com.ebmwebsourcing.petals.services.soap.soap.Compatibility}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enable Compatibility For</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enable Compatibility For</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.Compatibility
	 * @see #setEnableCompatibilityFor(Compatibility)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_EnableCompatibilityFor()
	 * @model extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='enable-compatibility-for'"
	 * @generated
	 */
	Compatibility getEnableCompatibilityFor();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getEnableCompatibilityFor <em>Enable Compatibility For</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enable Compatibility For</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.Compatibility
	 * @see #getEnableCompatibilityFor()
	 * @generated
	 */
	void setEnableCompatibilityFor(Compatibility value);

	/**
	 * Returns the value of the '<em><b>Enable Wsa</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enable Wsa</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enable Wsa</em>' attribute.
	 * @see #setEnableWsa(boolean)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_EnableWsa()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='enable-wsa'"
	 * @generated
	 */
	boolean isEnableWsa();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#isEnableWsa <em>Enable Wsa</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enable Wsa</em>' attribute.
	 * @see #isEnableWsa()
	 * @generated
	 */
	void setEnableWsa(boolean value);

	/**
	 * Returns the value of the '<em><b>Https Truststore File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Https Truststore File</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Https Truststore File</em>' attribute.
	 * @see #setHttpsTruststoreFile(String)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_HttpsTruststoreFile()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='https-truststore-file'"
	 * @generated
	 */
	String getHttpsTruststoreFile();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getHttpsTruststoreFile <em>Https Truststore File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Https Truststore File</em>' attribute.
	 * @see #getHttpsTruststoreFile()
	 * @generated
	 */
	void setHttpsTruststoreFile(String value);

	/**
	 * Returns the value of the '<em><b>Https Truststore Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Https Truststore Password</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Https Truststore Password</em>' attribute.
	 * @see #setHttpsTruststorePassword(String)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_HttpsTruststorePassword()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='https-truststore-password'"
	 * @generated
	 */
	String getHttpsTruststorePassword();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getHttpsTruststorePassword <em>Https Truststore Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Https Truststore Password</em>' attribute.
	 * @see #getHttpsTruststorePassword()
	 * @generated
	 */
	void setHttpsTruststorePassword(String value);

	/**
	 * Returns the value of the '<em><b>Https Keystore File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Https Keystore File</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Https Keystore File</em>' attribute.
	 * @see #setHttpsKeystoreFile(String)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_HttpsKeystoreFile()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='https-keystore-file'"
	 * @generated
	 */
	String getHttpsKeystoreFile();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getHttpsKeystoreFile <em>Https Keystore File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Https Keystore File</em>' attribute.
	 * @see #getHttpsKeystoreFile()
	 * @generated
	 */
	void setHttpsKeystoreFile(String value);

	/**
	 * Returns the value of the '<em><b>Https Keystore Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Https Keystore Password</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Https Keystore Password</em>' attribute.
	 * @see #setHttpsKeystorePassword(String)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_HttpsKeystorePassword()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='https-keystore-password'"
	 * @generated
	 */
	String getHttpsKeystorePassword();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getHttpsKeystorePassword <em>Https Keystore Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Https Keystore Password</em>' attribute.
	 * @see #getHttpsKeystorePassword()
	 * @generated
	 */
	void setHttpsKeystorePassword(String value);

} // SoapProvides
