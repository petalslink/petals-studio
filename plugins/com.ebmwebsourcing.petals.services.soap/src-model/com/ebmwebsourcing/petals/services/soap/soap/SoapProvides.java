/**
 * Copyright (c) 2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.soap.soap;

import com.sun.java.xml.ns.jbi.Provides;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Provides</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getAddress <em>Address</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getSoapVersion <em>Soap Version</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#isChunkedMode <em>Chunked Mode</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getSynchonousTimeout <em>Synchonous Timeout</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#isCleanupTransport <em>Cleanup Transport</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getMode <em>Mode</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getProxyHost <em>Proxy Host</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getProxyUser <em>Proxy User</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getProxyPassword <em>Proxy Password</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getProxyDomain <em>Proxy Domain</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getProxyPort <em>Proxy Port</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface SoapProvides extends Provides {
	/**
	 * Returns the value of the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Address</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Address</em>' attribute.
	 * @see #setAddress(String)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_Address()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	String getAddress();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getAddress <em>Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Address</em>' attribute.
	 * @see #getAddress()
	 * @generated
	 */
	void setAddress(String value);

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
	 * @see #setSoapVersion(SoapVersion)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_SoapVersion()
	 * @model required="true" derived="true"
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
	 * @see #getSoapVersion()
	 * @generated
	 */
	void setSoapVersion(SoapVersion value);

	/**
	 * Returns the value of the '<em><b>Chunked Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Chunked Mode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Chunked Mode</em>' attribute.
	 * @see #setChunkedMode(boolean)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_ChunkedMode()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean" required="true" derived="true"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='chunked-mode'"
	 * @generated
	 */
	boolean isChunkedMode();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#isChunkedMode <em>Chunked Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Chunked Mode</em>' attribute.
	 * @see #isChunkedMode()
	 * @generated
	 */
	void setChunkedMode(boolean value);

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
	 * @see #setCleanupTransport(boolean)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_CleanupTransport()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean" required="true" derived="true"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='cleanup-transport'"
	 * @generated
	 */
	boolean isCleanupTransport();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#isCleanupTransport <em>Cleanup Transport</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cleanup Transport</em>' attribute.
	 * @see #isCleanupTransport()
	 * @generated
	 */
	void setCleanupTransport(boolean value);

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
	 * @see #setMode(SoapMode)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapProvides_Mode()
	 * @model required="true" derived="true"
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
	 * @see #getMode()
	 * @generated
	 */
	void setMode(SoapMode value);

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

} // SoapProvides
