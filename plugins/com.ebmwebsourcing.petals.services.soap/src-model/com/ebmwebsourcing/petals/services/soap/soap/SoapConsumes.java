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

import com.sun.java.xml.ns.jbi.Consumes;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Consumes</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#getSoapServiceName <em>Soap Service Name</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#getSoapAction <em>Soap Action</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#getSynchonousTimeout <em>Synchonous Timeout</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#getMode <em>Mode</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#isEnableHttpTransport <em>Enable Http Transport</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#isEnableJmsTransport <em>Enable Jms Transport</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapConsumes()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface SoapConsumes extends Consumes {
	/**
	 * Returns the value of the '<em><b>Soap Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Soap Service Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Soap Service Name</em>' attribute.
	 * @see #setSoapServiceName(String)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapConsumes_SoapServiceName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='service-name'"
	 * @generated
	 */
	String getSoapServiceName();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#getSoapServiceName <em>Soap Service Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Soap Service Name</em>' attribute.
	 * @see #getSoapServiceName()
	 * @generated
	 */
	void setSoapServiceName(String value);

	/**
	 * Returns the value of the '<em><b>Soap Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Soap Action</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Soap Action</em>' attribute.
	 * @see #setSoapAction(String)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapConsumes_SoapAction()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='soap-action'"
	 * @generated
	 */
	String getSoapAction();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#getSoapAction <em>Soap Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Soap Action</em>' attribute.
	 * @see #getSoapAction()
	 * @generated
	 */
	void setSoapAction(String value);

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
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapConsumes_SynchonousTimeout()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" derived="true"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='synchronous-timeout'"
	 * @generated
	 */
	int getSynchonousTimeout();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#getSynchonousTimeout <em>Synchonous Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Synchonous Timeout</em>' attribute.
	 * @see #getSynchonousTimeout()
	 * @generated
	 */
	void setSynchonousTimeout(int value);

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
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapConsumes_Mode()
	 * @model required="true" derived="true"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	SoapMode getMode();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#getMode <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mode</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapMode
	 * @see #getMode()
	 * @generated
	 */
	void setMode(SoapMode value);

	/**
	 * Returns the value of the '<em><b>Enable Http Transport</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enable Http Transport</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enable Http Transport</em>' attribute.
	 * @see #isSetEnableHttpTransport()
	 * @see #unsetEnableHttpTransport()
	 * @see #setEnableHttpTransport(boolean)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapConsumes_EnableHttpTransport()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean" derived="true"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='enable-http-transport'"
	 * @generated
	 */
	boolean isEnableHttpTransport();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#isEnableHttpTransport <em>Enable Http Transport</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enable Http Transport</em>' attribute.
	 * @see #isSetEnableHttpTransport()
	 * @see #unsetEnableHttpTransport()
	 * @see #isEnableHttpTransport()
	 * @generated
	 */
	void setEnableHttpTransport(boolean value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#isEnableHttpTransport <em>Enable Http Transport</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEnableHttpTransport()
	 * @see #isEnableHttpTransport()
	 * @see #setEnableHttpTransport(boolean)
	 * @generated
	 */
	void unsetEnableHttpTransport();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#isEnableHttpTransport <em>Enable Http Transport</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Enable Http Transport</em>' attribute is set.
	 * @see #unsetEnableHttpTransport()
	 * @see #isEnableHttpTransport()
	 * @see #setEnableHttpTransport(boolean)
	 * @generated
	 */
	boolean isSetEnableHttpTransport();

	/**
	 * Returns the value of the '<em><b>Enable Jms Transport</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enable Jms Transport</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enable Jms Transport</em>' attribute.
	 * @see #isSetEnableJmsTransport()
	 * @see #unsetEnableJmsTransport()
	 * @see #setEnableJmsTransport(boolean)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapConsumes_EnableJmsTransport()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean" derived="true"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='enable-jms-transport'"
	 * @generated
	 */
	boolean isEnableJmsTransport();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#isEnableJmsTransport <em>Enable Jms Transport</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enable Jms Transport</em>' attribute.
	 * @see #isSetEnableJmsTransport()
	 * @see #unsetEnableJmsTransport()
	 * @see #isEnableJmsTransport()
	 * @generated
	 */
	void setEnableJmsTransport(boolean value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#isEnableJmsTransport <em>Enable Jms Transport</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEnableJmsTransport()
	 * @see #isEnableJmsTransport()
	 * @see #setEnableJmsTransport(boolean)
	 * @generated
	 */
	void unsetEnableJmsTransport();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#isEnableJmsTransport <em>Enable Jms Transport</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Enable Jms Transport</em>' attribute is set.
	 * @see #unsetEnableJmsTransport()
	 * @see #isEnableJmsTransport()
	 * @see #setEnableJmsTransport(boolean)
	 * @generated
	 */
	boolean isSetEnableJmsTransport();

} // SoapConsumes
