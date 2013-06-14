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

import com.sun.java.xml.ns.jbi.Component;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpPort <em>Http Port</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpHost <em>Http Host</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#isHttpServiceList <em>Http Service List</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpServiceContext <em>Http Service Context</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpServiceMapping <em>Http Service Mapping</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpThreadPoolSizeMin <em>Http Thread Pool Size Min</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpThreadPoolSizeMax <em>Http Thread Pool Size Max</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpAcceptors <em>Http Acceptors</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getJavaNamingFactoryInitial <em>Java Naming Factory Initial</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getJavaNamingProviderUrl <em>Java Naming Provider Url</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getJmsConnectionFactoryJndiname <em>Jms Connection Factory Jndiname</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapComponent()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface SoapComponent extends Component {
	/**
	 * Returns the value of the '<em><b>Http Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Http Port</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Http Port</em>' attribute.
	 * @see #setHttpPort(int)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapComponent_HttpPort()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" derived="true"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='http-port'"
	 * @generated
	 */
	int getHttpPort();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpPort <em>Http Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Http Port</em>' attribute.
	 * @see #getHttpPort()
	 * @generated
	 */
	void setHttpPort(int value);

	/**
	 * Returns the value of the '<em><b>Http Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Http Host</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Http Host</em>' attribute.
	 * @see #setHttpHost(String)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapComponent_HttpHost()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='http-host'"
	 * @generated
	 */
	String getHttpHost();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpHost <em>Http Host</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Http Host</em>' attribute.
	 * @see #getHttpHost()
	 * @generated
	 */
	void setHttpHost(String value);

	/**
	 * Returns the value of the '<em><b>Http Service List</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Http Service List</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Http Service List</em>' attribute.
	 * @see #setHttpServiceList(boolean)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapComponent_HttpServiceList()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean" derived="true"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='http-service-list'"
	 * @generated
	 */
	boolean isHttpServiceList();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#isHttpServiceList <em>Http Service List</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Http Service List</em>' attribute.
	 * @see #isHttpServiceList()
	 * @generated
	 */
	void setHttpServiceList(boolean value);

	/**
	 * Returns the value of the '<em><b>Http Service Context</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Http Service Context</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Http Service Context</em>' attribute.
	 * @see #setHttpServiceContext(String)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapComponent_HttpServiceContext()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='http-service-context'"
	 * @generated
	 */
	String getHttpServiceContext();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpServiceContext <em>Http Service Context</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Http Service Context</em>' attribute.
	 * @see #getHttpServiceContext()
	 * @generated
	 */
	void setHttpServiceContext(String value);

	/**
	 * Returns the value of the '<em><b>Http Service Mapping</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Http Service Mapping</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Http Service Mapping</em>' attribute.
	 * @see #setHttpServiceMapping(String)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapComponent_HttpServiceMapping()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='http-service-mapping'"
	 * @generated
	 */
	String getHttpServiceMapping();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpServiceMapping <em>Http Service Mapping</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Http Service Mapping</em>' attribute.
	 * @see #getHttpServiceMapping()
	 * @generated
	 */
	void setHttpServiceMapping(String value);

	/**
	 * Returns the value of the '<em><b>Http Thread Pool Size Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Http Thread Pool Size Min</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Http Thread Pool Size Min</em>' attribute.
	 * @see #setHttpThreadPoolSizeMin(int)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapComponent_HttpThreadPoolSizeMin()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" derived="true"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='http-thread-pool-size-min'"
	 * @generated
	 */
	int getHttpThreadPoolSizeMin();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpThreadPoolSizeMin <em>Http Thread Pool Size Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Http Thread Pool Size Min</em>' attribute.
	 * @see #getHttpThreadPoolSizeMin()
	 * @generated
	 */
	void setHttpThreadPoolSizeMin(int value);

	/**
	 * Returns the value of the '<em><b>Http Thread Pool Size Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Http Thread Pool Size Max</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Http Thread Pool Size Max</em>' attribute.
	 * @see #setHttpThreadPoolSizeMax(int)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapComponent_HttpThreadPoolSizeMax()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" derived="true"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='http-thread-pool-size-max'"
	 * @generated
	 */
	int getHttpThreadPoolSizeMax();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpThreadPoolSizeMax <em>Http Thread Pool Size Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Http Thread Pool Size Max</em>' attribute.
	 * @see #getHttpThreadPoolSizeMax()
	 * @generated
	 */
	void setHttpThreadPoolSizeMax(int value);

	/**
	 * Returns the value of the '<em><b>Http Acceptors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Http Acceptors</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Http Acceptors</em>' attribute.
	 * @see #setHttpAcceptors(int)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapComponent_HttpAcceptors()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" derived="true"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='http-acceptors'"
	 * @generated
	 */
	int getHttpAcceptors();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpAcceptors <em>Http Acceptors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Http Acceptors</em>' attribute.
	 * @see #getHttpAcceptors()
	 * @generated
	 */
	void setHttpAcceptors(int value);

	/**
	 * Returns the value of the '<em><b>Java Naming Factory Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Java Naming Factory Initial</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Java Naming Factory Initial</em>' attribute.
	 * @see #setJavaNamingFactoryInitial(String)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapComponent_JavaNamingFactoryInitial()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='java-naming-factory-initial'"
	 * @generated
	 */
	String getJavaNamingFactoryInitial();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getJavaNamingFactoryInitial <em>Java Naming Factory Initial</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Java Naming Factory Initial</em>' attribute.
	 * @see #getJavaNamingFactoryInitial()
	 * @generated
	 */
	void setJavaNamingFactoryInitial(String value);

	/**
	 * Returns the value of the '<em><b>Java Naming Provider Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Java Naming Provider Url</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Java Naming Provider Url</em>' attribute.
	 * @see #setJavaNamingProviderUrl(String)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapComponent_JavaNamingProviderUrl()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='java-naming-provider-url'"
	 * @generated
	 */
	String getJavaNamingProviderUrl();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getJavaNamingProviderUrl <em>Java Naming Provider Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Java Naming Provider Url</em>' attribute.
	 * @see #getJavaNamingProviderUrl()
	 * @generated
	 */
	void setJavaNamingProviderUrl(String value);

	/**
	 * Returns the value of the '<em><b>Jms Connection Factory Jndiname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Jms Connection Factory Jndiname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Jms Connection Factory Jndiname</em>' attribute.
	 * @see #setJmsConnectionFactoryJndiname(String)
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapPackage#getSoapComponent_JmsConnectionFactoryJndiname()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="group='#group:0' namespace='##targetNamespace' kind='element' name='jms-connection-factory-jndiname'"
	 * @generated
	 */
	String getJmsConnectionFactoryJndiname();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getJmsConnectionFactoryJndiname <em>Jms Connection Factory Jndiname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Jms Connection Factory Jndiname</em>' attribute.
	 * @see #getJmsConnectionFactoryJndiname()
	 * @generated
	 */
	void setJmsConnectionFactoryJndiname(String value);

} // SoapComponent
