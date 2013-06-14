/**
 * Copyright (c) 2011-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 */
package com.ebmwebsourcing.petals.services.soap.soap.impl;

import com.ebmwebsourcing.petals.services.soap.soap.SoapComponent;
import com.ebmwebsourcing.petals.services.soap.soap.SoapPackage;

import com.sun.java.xml.ns.jbi.impl.ComponentImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapComponentImpl#getHttpPort <em>Http Port</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapComponentImpl#getHttpHost <em>Http Host</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapComponentImpl#isHttpServiceList <em>Http Service List</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapComponentImpl#getHttpServiceContext <em>Http Service Context</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapComponentImpl#getHttpServiceMapping <em>Http Service Mapping</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapComponentImpl#getHttpThreadPoolSizeMin <em>Http Thread Pool Size Min</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapComponentImpl#getHttpThreadPoolSizeMax <em>Http Thread Pool Size Max</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapComponentImpl#getHttpAcceptors <em>Http Acceptors</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapComponentImpl#getJavaNamingFactoryInitial <em>Java Naming Factory Initial</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapComponentImpl#getJavaNamingProviderUrl <em>Java Naming Provider Url</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapComponentImpl#getJmsConnectionFactoryJndiname <em>Jms Connection Factory Jndiname</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SoapComponentImpl extends ComponentImpl implements SoapComponent {
	/**
	 * The default value of the '{@link #getHttpPort() <em>Http Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpPort()
	 * @generated
	 * @ordered
	 */
	protected static final int HTTP_PORT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getHttpPort() <em>Http Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpPort()
	 * @generated
	 * @ordered
	 */
	protected int httpPort = HTTP_PORT_EDEFAULT;

	/**
	 * The default value of the '{@link #getHttpHost() <em>Http Host</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpHost()
	 * @generated
	 * @ordered
	 */
	protected static final String HTTP_HOST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHttpHost() <em>Http Host</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpHost()
	 * @generated
	 * @ordered
	 */
	protected String httpHost = HTTP_HOST_EDEFAULT;

	/**
	 * The default value of the '{@link #isHttpServiceList() <em>Http Service List</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHttpServiceList()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HTTP_SERVICE_LIST_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHttpServiceList() <em>Http Service List</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHttpServiceList()
	 * @generated
	 * @ordered
	 */
	protected boolean httpServiceList = HTTP_SERVICE_LIST_EDEFAULT;

	/**
	 * The default value of the '{@link #getHttpServiceContext() <em>Http Service Context</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpServiceContext()
	 * @generated
	 * @ordered
	 */
	protected static final String HTTP_SERVICE_CONTEXT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHttpServiceContext() <em>Http Service Context</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpServiceContext()
	 * @generated
	 * @ordered
	 */
	protected String httpServiceContext = HTTP_SERVICE_CONTEXT_EDEFAULT;

	/**
	 * The default value of the '{@link #getHttpServiceMapping() <em>Http Service Mapping</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpServiceMapping()
	 * @generated
	 * @ordered
	 */
	protected static final String HTTP_SERVICE_MAPPING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHttpServiceMapping() <em>Http Service Mapping</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpServiceMapping()
	 * @generated
	 * @ordered
	 */
	protected String httpServiceMapping = HTTP_SERVICE_MAPPING_EDEFAULT;

	/**
	 * The default value of the '{@link #getHttpThreadPoolSizeMin() <em>Http Thread Pool Size Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpThreadPoolSizeMin()
	 * @generated
	 * @ordered
	 */
	protected static final int HTTP_THREAD_POOL_SIZE_MIN_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getHttpThreadPoolSizeMin() <em>Http Thread Pool Size Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpThreadPoolSizeMin()
	 * @generated
	 * @ordered
	 */
	protected int httpThreadPoolSizeMin = HTTP_THREAD_POOL_SIZE_MIN_EDEFAULT;

	/**
	 * The default value of the '{@link #getHttpThreadPoolSizeMax() <em>Http Thread Pool Size Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpThreadPoolSizeMax()
	 * @generated
	 * @ordered
	 */
	protected static final int HTTP_THREAD_POOL_SIZE_MAX_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getHttpThreadPoolSizeMax() <em>Http Thread Pool Size Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpThreadPoolSizeMax()
	 * @generated
	 * @ordered
	 */
	protected int httpThreadPoolSizeMax = HTTP_THREAD_POOL_SIZE_MAX_EDEFAULT;

	/**
	 * The default value of the '{@link #getHttpAcceptors() <em>Http Acceptors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpAcceptors()
	 * @generated
	 * @ordered
	 */
	protected static final int HTTP_ACCEPTORS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getHttpAcceptors() <em>Http Acceptors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpAcceptors()
	 * @generated
	 * @ordered
	 */
	protected int httpAcceptors = HTTP_ACCEPTORS_EDEFAULT;

	/**
	 * The default value of the '{@link #getJavaNamingFactoryInitial() <em>Java Naming Factory Initial</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavaNamingFactoryInitial()
	 * @generated
	 * @ordered
	 */
	protected static final String JAVA_NAMING_FACTORY_INITIAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getJavaNamingFactoryInitial() <em>Java Naming Factory Initial</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavaNamingFactoryInitial()
	 * @generated
	 * @ordered
	 */
	protected String javaNamingFactoryInitial = JAVA_NAMING_FACTORY_INITIAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getJavaNamingProviderUrl() <em>Java Naming Provider Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavaNamingProviderUrl()
	 * @generated
	 * @ordered
	 */
	protected static final String JAVA_NAMING_PROVIDER_URL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getJavaNamingProviderUrl() <em>Java Naming Provider Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavaNamingProviderUrl()
	 * @generated
	 * @ordered
	 */
	protected String javaNamingProviderUrl = JAVA_NAMING_PROVIDER_URL_EDEFAULT;

	/**
	 * The default value of the '{@link #getJmsConnectionFactoryJndiname() <em>Jms Connection Factory Jndiname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJmsConnectionFactoryJndiname()
	 * @generated
	 * @ordered
	 */
	protected static final String JMS_CONNECTION_FACTORY_JNDINAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getJmsConnectionFactoryJndiname() <em>Jms Connection Factory Jndiname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJmsConnectionFactoryJndiname()
	 * @generated
	 * @ordered
	 */
	protected String jmsConnectionFactoryJndiname = JMS_CONNECTION_FACTORY_JNDINAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SoapComponentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SoapPackage.Literals.SOAP_COMPONENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getHttpPort() {
		return httpPort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHttpPort(int newHttpPort) {
		int oldHttpPort = httpPort;
		httpPort = newHttpPort;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_COMPONENT__HTTP_PORT, oldHttpPort, httpPort));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHttpHost() {
		return httpHost;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHttpHost(String newHttpHost) {
		String oldHttpHost = httpHost;
		httpHost = newHttpHost;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_COMPONENT__HTTP_HOST, oldHttpHost, httpHost));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHttpServiceList() {
		return httpServiceList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHttpServiceList(boolean newHttpServiceList) {
		boolean oldHttpServiceList = httpServiceList;
		httpServiceList = newHttpServiceList;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_COMPONENT__HTTP_SERVICE_LIST, oldHttpServiceList, httpServiceList));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHttpServiceContext() {
		return httpServiceContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHttpServiceContext(String newHttpServiceContext) {
		String oldHttpServiceContext = httpServiceContext;
		httpServiceContext = newHttpServiceContext;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_COMPONENT__HTTP_SERVICE_CONTEXT, oldHttpServiceContext, httpServiceContext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHttpServiceMapping() {
		return httpServiceMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHttpServiceMapping(String newHttpServiceMapping) {
		String oldHttpServiceMapping = httpServiceMapping;
		httpServiceMapping = newHttpServiceMapping;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_COMPONENT__HTTP_SERVICE_MAPPING, oldHttpServiceMapping, httpServiceMapping));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getHttpThreadPoolSizeMin() {
		return httpThreadPoolSizeMin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHttpThreadPoolSizeMin(int newHttpThreadPoolSizeMin) {
		int oldHttpThreadPoolSizeMin = httpThreadPoolSizeMin;
		httpThreadPoolSizeMin = newHttpThreadPoolSizeMin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_COMPONENT__HTTP_THREAD_POOL_SIZE_MIN, oldHttpThreadPoolSizeMin, httpThreadPoolSizeMin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getHttpThreadPoolSizeMax() {
		return httpThreadPoolSizeMax;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHttpThreadPoolSizeMax(int newHttpThreadPoolSizeMax) {
		int oldHttpThreadPoolSizeMax = httpThreadPoolSizeMax;
		httpThreadPoolSizeMax = newHttpThreadPoolSizeMax;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_COMPONENT__HTTP_THREAD_POOL_SIZE_MAX, oldHttpThreadPoolSizeMax, httpThreadPoolSizeMax));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getHttpAcceptors() {
		return httpAcceptors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHttpAcceptors(int newHttpAcceptors) {
		int oldHttpAcceptors = httpAcceptors;
		httpAcceptors = newHttpAcceptors;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_COMPONENT__HTTP_ACCEPTORS, oldHttpAcceptors, httpAcceptors));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getJavaNamingFactoryInitial() {
		return javaNamingFactoryInitial;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJavaNamingFactoryInitial(String newJavaNamingFactoryInitial) {
		String oldJavaNamingFactoryInitial = javaNamingFactoryInitial;
		javaNamingFactoryInitial = newJavaNamingFactoryInitial;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_COMPONENT__JAVA_NAMING_FACTORY_INITIAL, oldJavaNamingFactoryInitial, javaNamingFactoryInitial));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getJavaNamingProviderUrl() {
		return javaNamingProviderUrl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJavaNamingProviderUrl(String newJavaNamingProviderUrl) {
		String oldJavaNamingProviderUrl = javaNamingProviderUrl;
		javaNamingProviderUrl = newJavaNamingProviderUrl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_COMPONENT__JAVA_NAMING_PROVIDER_URL, oldJavaNamingProviderUrl, javaNamingProviderUrl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getJmsConnectionFactoryJndiname() {
		return jmsConnectionFactoryJndiname;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJmsConnectionFactoryJndiname(String newJmsConnectionFactoryJndiname) {
		String oldJmsConnectionFactoryJndiname = jmsConnectionFactoryJndiname;
		jmsConnectionFactoryJndiname = newJmsConnectionFactoryJndiname;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_COMPONENT__JMS_CONNECTION_FACTORY_JNDINAME, oldJmsConnectionFactoryJndiname, jmsConnectionFactoryJndiname));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SoapPackage.SOAP_COMPONENT__HTTP_PORT:
				return getHttpPort();
			case SoapPackage.SOAP_COMPONENT__HTTP_HOST:
				return getHttpHost();
			case SoapPackage.SOAP_COMPONENT__HTTP_SERVICE_LIST:
				return isHttpServiceList();
			case SoapPackage.SOAP_COMPONENT__HTTP_SERVICE_CONTEXT:
				return getHttpServiceContext();
			case SoapPackage.SOAP_COMPONENT__HTTP_SERVICE_MAPPING:
				return getHttpServiceMapping();
			case SoapPackage.SOAP_COMPONENT__HTTP_THREAD_POOL_SIZE_MIN:
				return getHttpThreadPoolSizeMin();
			case SoapPackage.SOAP_COMPONENT__HTTP_THREAD_POOL_SIZE_MAX:
				return getHttpThreadPoolSizeMax();
			case SoapPackage.SOAP_COMPONENT__HTTP_ACCEPTORS:
				return getHttpAcceptors();
			case SoapPackage.SOAP_COMPONENT__JAVA_NAMING_FACTORY_INITIAL:
				return getJavaNamingFactoryInitial();
			case SoapPackage.SOAP_COMPONENT__JAVA_NAMING_PROVIDER_URL:
				return getJavaNamingProviderUrl();
			case SoapPackage.SOAP_COMPONENT__JMS_CONNECTION_FACTORY_JNDINAME:
				return getJmsConnectionFactoryJndiname();
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
			case SoapPackage.SOAP_COMPONENT__HTTP_PORT:
				setHttpPort((Integer)newValue);
				return;
			case SoapPackage.SOAP_COMPONENT__HTTP_HOST:
				setHttpHost((String)newValue);
				return;
			case SoapPackage.SOAP_COMPONENT__HTTP_SERVICE_LIST:
				setHttpServiceList((Boolean)newValue);
				return;
			case SoapPackage.SOAP_COMPONENT__HTTP_SERVICE_CONTEXT:
				setHttpServiceContext((String)newValue);
				return;
			case SoapPackage.SOAP_COMPONENT__HTTP_SERVICE_MAPPING:
				setHttpServiceMapping((String)newValue);
				return;
			case SoapPackage.SOAP_COMPONENT__HTTP_THREAD_POOL_SIZE_MIN:
				setHttpThreadPoolSizeMin((Integer)newValue);
				return;
			case SoapPackage.SOAP_COMPONENT__HTTP_THREAD_POOL_SIZE_MAX:
				setHttpThreadPoolSizeMax((Integer)newValue);
				return;
			case SoapPackage.SOAP_COMPONENT__HTTP_ACCEPTORS:
				setHttpAcceptors((Integer)newValue);
				return;
			case SoapPackage.SOAP_COMPONENT__JAVA_NAMING_FACTORY_INITIAL:
				setJavaNamingFactoryInitial((String)newValue);
				return;
			case SoapPackage.SOAP_COMPONENT__JAVA_NAMING_PROVIDER_URL:
				setJavaNamingProviderUrl((String)newValue);
				return;
			case SoapPackage.SOAP_COMPONENT__JMS_CONNECTION_FACTORY_JNDINAME:
				setJmsConnectionFactoryJndiname((String)newValue);
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
			case SoapPackage.SOAP_COMPONENT__HTTP_PORT:
				setHttpPort(HTTP_PORT_EDEFAULT);
				return;
			case SoapPackage.SOAP_COMPONENT__HTTP_HOST:
				setHttpHost(HTTP_HOST_EDEFAULT);
				return;
			case SoapPackage.SOAP_COMPONENT__HTTP_SERVICE_LIST:
				setHttpServiceList(HTTP_SERVICE_LIST_EDEFAULT);
				return;
			case SoapPackage.SOAP_COMPONENT__HTTP_SERVICE_CONTEXT:
				setHttpServiceContext(HTTP_SERVICE_CONTEXT_EDEFAULT);
				return;
			case SoapPackage.SOAP_COMPONENT__HTTP_SERVICE_MAPPING:
				setHttpServiceMapping(HTTP_SERVICE_MAPPING_EDEFAULT);
				return;
			case SoapPackage.SOAP_COMPONENT__HTTP_THREAD_POOL_SIZE_MIN:
				setHttpThreadPoolSizeMin(HTTP_THREAD_POOL_SIZE_MIN_EDEFAULT);
				return;
			case SoapPackage.SOAP_COMPONENT__HTTP_THREAD_POOL_SIZE_MAX:
				setHttpThreadPoolSizeMax(HTTP_THREAD_POOL_SIZE_MAX_EDEFAULT);
				return;
			case SoapPackage.SOAP_COMPONENT__HTTP_ACCEPTORS:
				setHttpAcceptors(HTTP_ACCEPTORS_EDEFAULT);
				return;
			case SoapPackage.SOAP_COMPONENT__JAVA_NAMING_FACTORY_INITIAL:
				setJavaNamingFactoryInitial(JAVA_NAMING_FACTORY_INITIAL_EDEFAULT);
				return;
			case SoapPackage.SOAP_COMPONENT__JAVA_NAMING_PROVIDER_URL:
				setJavaNamingProviderUrl(JAVA_NAMING_PROVIDER_URL_EDEFAULT);
				return;
			case SoapPackage.SOAP_COMPONENT__JMS_CONNECTION_FACTORY_JNDINAME:
				setJmsConnectionFactoryJndiname(JMS_CONNECTION_FACTORY_JNDINAME_EDEFAULT);
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
			case SoapPackage.SOAP_COMPONENT__HTTP_PORT:
				return httpPort != HTTP_PORT_EDEFAULT;
			case SoapPackage.SOAP_COMPONENT__HTTP_HOST:
				return HTTP_HOST_EDEFAULT == null ? httpHost != null : !HTTP_HOST_EDEFAULT.equals(httpHost);
			case SoapPackage.SOAP_COMPONENT__HTTP_SERVICE_LIST:
				return httpServiceList != HTTP_SERVICE_LIST_EDEFAULT;
			case SoapPackage.SOAP_COMPONENT__HTTP_SERVICE_CONTEXT:
				return HTTP_SERVICE_CONTEXT_EDEFAULT == null ? httpServiceContext != null : !HTTP_SERVICE_CONTEXT_EDEFAULT.equals(httpServiceContext);
			case SoapPackage.SOAP_COMPONENT__HTTP_SERVICE_MAPPING:
				return HTTP_SERVICE_MAPPING_EDEFAULT == null ? httpServiceMapping != null : !HTTP_SERVICE_MAPPING_EDEFAULT.equals(httpServiceMapping);
			case SoapPackage.SOAP_COMPONENT__HTTP_THREAD_POOL_SIZE_MIN:
				return httpThreadPoolSizeMin != HTTP_THREAD_POOL_SIZE_MIN_EDEFAULT;
			case SoapPackage.SOAP_COMPONENT__HTTP_THREAD_POOL_SIZE_MAX:
				return httpThreadPoolSizeMax != HTTP_THREAD_POOL_SIZE_MAX_EDEFAULT;
			case SoapPackage.SOAP_COMPONENT__HTTP_ACCEPTORS:
				return httpAcceptors != HTTP_ACCEPTORS_EDEFAULT;
			case SoapPackage.SOAP_COMPONENT__JAVA_NAMING_FACTORY_INITIAL:
				return JAVA_NAMING_FACTORY_INITIAL_EDEFAULT == null ? javaNamingFactoryInitial != null : !JAVA_NAMING_FACTORY_INITIAL_EDEFAULT.equals(javaNamingFactoryInitial);
			case SoapPackage.SOAP_COMPONENT__JAVA_NAMING_PROVIDER_URL:
				return JAVA_NAMING_PROVIDER_URL_EDEFAULT == null ? javaNamingProviderUrl != null : !JAVA_NAMING_PROVIDER_URL_EDEFAULT.equals(javaNamingProviderUrl);
			case SoapPackage.SOAP_COMPONENT__JMS_CONNECTION_FACTORY_JNDINAME:
				return JMS_CONNECTION_FACTORY_JNDINAME_EDEFAULT == null ? jmsConnectionFactoryJndiname != null : !JMS_CONNECTION_FACTORY_JNDINAME_EDEFAULT.equals(jmsConnectionFactoryJndiname);
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
		result.append(" (httpPort: ");
		result.append(httpPort);
		result.append(", httpHost: ");
		result.append(httpHost);
		result.append(", httpServiceList: ");
		result.append(httpServiceList);
		result.append(", httpServiceContext: ");
		result.append(httpServiceContext);
		result.append(", httpServiceMapping: ");
		result.append(httpServiceMapping);
		result.append(", httpThreadPoolSizeMin: ");
		result.append(httpThreadPoolSizeMin);
		result.append(", httpThreadPoolSizeMax: ");
		result.append(httpThreadPoolSizeMax);
		result.append(", httpAcceptors: ");
		result.append(httpAcceptors);
		result.append(", javaNamingFactoryInitial: ");
		result.append(javaNamingFactoryInitial);
		result.append(", javaNamingProviderUrl: ");
		result.append(javaNamingProviderUrl);
		result.append(", jmsConnectionFactoryJndiname: ");
		result.append(jmsConnectionFactoryJndiname);
		result.append(')');
		return result.toString();
	}

} //SoapComponentImpl
