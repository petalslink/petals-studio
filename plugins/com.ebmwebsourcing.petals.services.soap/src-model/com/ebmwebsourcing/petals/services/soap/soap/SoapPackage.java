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

import com.sun.java.xml.ns.jbi.JbiPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapFactory
 * @model kind="package"
 * @generated
 */
public interface SoapPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "soap";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://petals.ow2.org/components/soap/version-4";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "soap";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SoapPackage eINSTANCE = com.ebmwebsourcing.petals.services.soap.soap.impl.SoapPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl <em>Provides</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl
	 * @see com.ebmwebsourcing.petals.services.soap.soap.impl.SoapPackageImpl#getSoapProvides()
	 * @generated
	 */
	int SOAP_PROVIDES = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_PROVIDES__GROUP = JbiPackage.PROVIDES__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_PROVIDES__OTHER = JbiPackage.PROVIDES__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_PROVIDES__LOCAL = JbiPackage.PROVIDES__LOCAL;

	/**
	 * The feature id for the '<em><b>Endpoint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_PROVIDES__ENDPOINT_NAME = JbiPackage.PROVIDES__ENDPOINT_NAME;

	/**
	 * The feature id for the '<em><b>Interface Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_PROVIDES__INTERFACE_NAME = JbiPackage.PROVIDES__INTERFACE_NAME;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_PROVIDES__SERVICE_NAME = JbiPackage.PROVIDES__SERVICE_NAME;

	/**
	 * The feature id for the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_PROVIDES__ADDRESS = JbiPackage.PROVIDES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Soap Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_PROVIDES__SOAP_VERSION = JbiPackage.PROVIDES_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Chunked Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_PROVIDES__CHUNKED_MODE = JbiPackage.PROVIDES_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Synchonous Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_PROVIDES__SYNCHONOUS_TIMEOUT = JbiPackage.PROVIDES_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Cleanup Transport</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_PROVIDES__CLEANUP_TRANSPORT = JbiPackage.PROVIDES_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_PROVIDES__MODE = JbiPackage.PROVIDES_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Proxy Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_PROVIDES__PROXY_HOST = JbiPackage.PROVIDES_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Proxy User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_PROVIDES__PROXY_USER = JbiPackage.PROVIDES_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Proxy Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_PROVIDES__PROXY_PASSWORD = JbiPackage.PROVIDES_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Proxy Domain</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_PROVIDES__PROXY_DOMAIN = JbiPackage.PROVIDES_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Proxy Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_PROVIDES__PROXY_PORT = JbiPackage.PROVIDES_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Provides</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_PROVIDES_FEATURE_COUNT = JbiPackage.PROVIDES_FEATURE_COUNT + 11;

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapConsumesImpl <em>Consumes</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.soap.soap.impl.SoapConsumesImpl
	 * @see com.ebmwebsourcing.petals.services.soap.soap.impl.SoapPackageImpl#getSoapConsumes()
	 * @generated
	 */
	int SOAP_CONSUMES = 1;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_CONSUMES__GROUP = JbiPackage.CONSUMES__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_CONSUMES__OTHER = JbiPackage.CONSUMES__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_CONSUMES__LOCAL = JbiPackage.CONSUMES__LOCAL;

	/**
	 * The feature id for the '<em><b>Endpoint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_CONSUMES__ENDPOINT_NAME = JbiPackage.CONSUMES__ENDPOINT_NAME;

	/**
	 * The feature id for the '<em><b>Interface Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_CONSUMES__INTERFACE_NAME = JbiPackage.CONSUMES__INTERFACE_NAME;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_CONSUMES__SERVICE_NAME = JbiPackage.CONSUMES__SERVICE_NAME;

	/**
	 * The feature id for the '<em><b>Soap Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_CONSUMES__SOAP_SERVICE_NAME = JbiPackage.CONSUMES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Soap Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_CONSUMES__SOAP_ACTION = JbiPackage.CONSUMES_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Synchonous Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_CONSUMES__SYNCHONOUS_TIMEOUT = JbiPackage.CONSUMES_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_CONSUMES__MODE = JbiPackage.CONSUMES_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Enable Http Transport</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_CONSUMES__ENABLE_HTTP_TRANSPORT = JbiPackage.CONSUMES_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Enable Jms Transport</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_CONSUMES__ENABLE_JMS_TRANSPORT = JbiPackage.CONSUMES_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Consumes</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_CONSUMES_FEATURE_COUNT = JbiPackage.CONSUMES_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapComponentImpl <em>Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.soap.soap.impl.SoapComponentImpl
	 * @see com.ebmwebsourcing.petals.services.soap.soap.impl.SoapPackageImpl#getSoapComponent()
	 * @generated
	 */
	int SOAP_COMPONENT = 2;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_COMPONENT__GROUP = JbiPackage.COMPONENT__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_COMPONENT__OTHER = JbiPackage.COMPONENT__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_COMPONENT__LOCAL = JbiPackage.COMPONENT__LOCAL;

	/**
	 * The feature id for the '<em><b>Identification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_COMPONENT__IDENTIFICATION = JbiPackage.COMPONENT__IDENTIFICATION;

	/**
	 * The feature id for the '<em><b>Component Class Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_COMPONENT__COMPONENT_CLASS_NAME = JbiPackage.COMPONENT__COMPONENT_CLASS_NAME;

	/**
	 * The feature id for the '<em><b>Component Class Path</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_COMPONENT__COMPONENT_CLASS_PATH = JbiPackage.COMPONENT__COMPONENT_CLASS_PATH;

	/**
	 * The feature id for the '<em><b>Bootstrap Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_COMPONENT__BOOTSTRAP_CLASS_NAME = JbiPackage.COMPONENT__BOOTSTRAP_CLASS_NAME;

	/**
	 * The feature id for the '<em><b>Bootstrap Class Path</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_COMPONENT__BOOTSTRAP_CLASS_PATH = JbiPackage.COMPONENT__BOOTSTRAP_CLASS_PATH;

	/**
	 * The feature id for the '<em><b>Shared Library List</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_COMPONENT__SHARED_LIBRARY_LIST = JbiPackage.COMPONENT__SHARED_LIBRARY_LIST;

	/**
	 * The feature id for the '<em><b>Shared Library</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_COMPONENT__SHARED_LIBRARY = JbiPackage.COMPONENT__SHARED_LIBRARY;

	/**
	 * The feature id for the '<em><b>Bootstrap Class Loader Delegation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_COMPONENT__BOOTSTRAP_CLASS_LOADER_DELEGATION = JbiPackage.COMPONENT__BOOTSTRAP_CLASS_LOADER_DELEGATION;

	/**
	 * The feature id for the '<em><b>Component Class Loader Delegation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_COMPONENT__COMPONENT_CLASS_LOADER_DELEGATION = JbiPackage.COMPONENT__COMPONENT_CLASS_LOADER_DELEGATION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_COMPONENT__TYPE = JbiPackage.COMPONENT__TYPE;

	/**
	 * The feature id for the '<em><b>Http Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_COMPONENT__HTTP_PORT = JbiPackage.COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Http Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_COMPONENT__HTTP_HOST = JbiPackage.COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Http Service List</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_COMPONENT__HTTP_SERVICE_LIST = JbiPackage.COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Http Service Context</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_COMPONENT__HTTP_SERVICE_CONTEXT = JbiPackage.COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Http Service Mapping</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_COMPONENT__HTTP_SERVICE_MAPPING = JbiPackage.COMPONENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Http Thread Pool Size Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_COMPONENT__HTTP_THREAD_POOL_SIZE_MIN = JbiPackage.COMPONENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Http Thread Pool Size Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_COMPONENT__HTTP_THREAD_POOL_SIZE_MAX = JbiPackage.COMPONENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Http Acceptors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_COMPONENT__HTTP_ACCEPTORS = JbiPackage.COMPONENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Java Naming Factory Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_COMPONENT__JAVA_NAMING_FACTORY_INITIAL = JbiPackage.COMPONENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Java Naming Provider Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_COMPONENT__JAVA_NAMING_PROVIDER_URL = JbiPackage.COMPONENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Jms Connection Factory Jndiname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_COMPONENT__JMS_CONNECTION_FACTORY_JNDINAME = JbiPackage.COMPONENT_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOAP_COMPONENT_FEATURE_COUNT = JbiPackage.COMPONENT_FEATURE_COUNT + 11;

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapVersion <em>Version</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapVersion
	 * @see com.ebmwebsourcing.petals.services.soap.soap.impl.SoapPackageImpl#getSoapVersion()
	 * @generated
	 */
	int SOAP_VERSION = 3;

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapMode <em>Mode</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapMode
	 * @see com.ebmwebsourcing.petals.services.soap.soap.impl.SoapPackageImpl#getSoapMode()
	 * @generated
	 */
	int SOAP_MODE = 4;


	/**
	 * Returns the meta object for class '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides <em>Provides</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Provides</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapProvides
	 * @generated
	 */
	EClass getSoapProvides();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getAddress <em>Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Address</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getAddress()
	 * @see #getSoapProvides()
	 * @generated
	 */
	EAttribute getSoapProvides_Address();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getSoapVersion <em>Soap Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Soap Version</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getSoapVersion()
	 * @see #getSoapProvides()
	 * @generated
	 */
	EAttribute getSoapProvides_SoapVersion();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#isChunkedMode <em>Chunked Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Chunked Mode</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#isChunkedMode()
	 * @see #getSoapProvides()
	 * @generated
	 */
	EAttribute getSoapProvides_ChunkedMode();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getSynchonousTimeout <em>Synchonous Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Synchonous Timeout</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getSynchonousTimeout()
	 * @see #getSoapProvides()
	 * @generated
	 */
	EAttribute getSoapProvides_SynchonousTimeout();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#isCleanupTransport <em>Cleanup Transport</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cleanup Transport</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#isCleanupTransport()
	 * @see #getSoapProvides()
	 * @generated
	 */
	EAttribute getSoapProvides_CleanupTransport();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getMode <em>Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mode</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getMode()
	 * @see #getSoapProvides()
	 * @generated
	 */
	EAttribute getSoapProvides_Mode();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getProxyHost <em>Proxy Host</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Proxy Host</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getProxyHost()
	 * @see #getSoapProvides()
	 * @generated
	 */
	EAttribute getSoapProvides_ProxyHost();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getProxyUser <em>Proxy User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Proxy User</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getProxyUser()
	 * @see #getSoapProvides()
	 * @generated
	 */
	EAttribute getSoapProvides_ProxyUser();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getProxyPassword <em>Proxy Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Proxy Password</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getProxyPassword()
	 * @see #getSoapProvides()
	 * @generated
	 */
	EAttribute getSoapProvides_ProxyPassword();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getProxyDomain <em>Proxy Domain</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Proxy Domain</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getProxyDomain()
	 * @see #getSoapProvides()
	 * @generated
	 */
	EAttribute getSoapProvides_ProxyDomain();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getProxyPort <em>Proxy Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Proxy Port</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapProvides#getProxyPort()
	 * @see #getSoapProvides()
	 * @generated
	 */
	EAttribute getSoapProvides_ProxyPort();

	/**
	 * Returns the meta object for class '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes <em>Consumes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Consumes</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes
	 * @generated
	 */
	EClass getSoapConsumes();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#getSoapServiceName <em>Soap Service Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Soap Service Name</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#getSoapServiceName()
	 * @see #getSoapConsumes()
	 * @generated
	 */
	EAttribute getSoapConsumes_SoapServiceName();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#getSoapAction <em>Soap Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Soap Action</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#getSoapAction()
	 * @see #getSoapConsumes()
	 * @generated
	 */
	EAttribute getSoapConsumes_SoapAction();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#getSynchonousTimeout <em>Synchonous Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Synchonous Timeout</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#getSynchonousTimeout()
	 * @see #getSoapConsumes()
	 * @generated
	 */
	EAttribute getSoapConsumes_SynchonousTimeout();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#getMode <em>Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mode</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#getMode()
	 * @see #getSoapConsumes()
	 * @generated
	 */
	EAttribute getSoapConsumes_Mode();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#isEnableHttpTransport <em>Enable Http Transport</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enable Http Transport</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#isEnableHttpTransport()
	 * @see #getSoapConsumes()
	 * @generated
	 */
	EAttribute getSoapConsumes_EnableHttpTransport();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#isEnableJmsTransport <em>Enable Jms Transport</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enable Jms Transport</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes#isEnableJmsTransport()
	 * @see #getSoapConsumes()
	 * @generated
	 */
	EAttribute getSoapConsumes_EnableJmsTransport();

	/**
	 * Returns the meta object for class '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapComponent
	 * @generated
	 */
	EClass getSoapComponent();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpPort <em>Http Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Http Port</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpPort()
	 * @see #getSoapComponent()
	 * @generated
	 */
	EAttribute getSoapComponent_HttpPort();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpHost <em>Http Host</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Http Host</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpHost()
	 * @see #getSoapComponent()
	 * @generated
	 */
	EAttribute getSoapComponent_HttpHost();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#isHttpServiceList <em>Http Service List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Http Service List</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#isHttpServiceList()
	 * @see #getSoapComponent()
	 * @generated
	 */
	EAttribute getSoapComponent_HttpServiceList();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpServiceContext <em>Http Service Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Http Service Context</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpServiceContext()
	 * @see #getSoapComponent()
	 * @generated
	 */
	EAttribute getSoapComponent_HttpServiceContext();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpServiceMapping <em>Http Service Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Http Service Mapping</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpServiceMapping()
	 * @see #getSoapComponent()
	 * @generated
	 */
	EAttribute getSoapComponent_HttpServiceMapping();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpThreadPoolSizeMin <em>Http Thread Pool Size Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Http Thread Pool Size Min</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpThreadPoolSizeMin()
	 * @see #getSoapComponent()
	 * @generated
	 */
	EAttribute getSoapComponent_HttpThreadPoolSizeMin();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpThreadPoolSizeMax <em>Http Thread Pool Size Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Http Thread Pool Size Max</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpThreadPoolSizeMax()
	 * @see #getSoapComponent()
	 * @generated
	 */
	EAttribute getSoapComponent_HttpThreadPoolSizeMax();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpAcceptors <em>Http Acceptors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Http Acceptors</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getHttpAcceptors()
	 * @see #getSoapComponent()
	 * @generated
	 */
	EAttribute getSoapComponent_HttpAcceptors();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getJavaNamingFactoryInitial <em>Java Naming Factory Initial</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Java Naming Factory Initial</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getJavaNamingFactoryInitial()
	 * @see #getSoapComponent()
	 * @generated
	 */
	EAttribute getSoapComponent_JavaNamingFactoryInitial();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getJavaNamingProviderUrl <em>Java Naming Provider Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Java Naming Provider Url</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getJavaNamingProviderUrl()
	 * @see #getSoapComponent()
	 * @generated
	 */
	EAttribute getSoapComponent_JavaNamingProviderUrl();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getJmsConnectionFactoryJndiname <em>Jms Connection Factory Jndiname</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Jms Connection Factory Jndiname</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapComponent#getJmsConnectionFactoryJndiname()
	 * @see #getSoapComponent()
	 * @generated
	 */
	EAttribute getSoapComponent_JmsConnectionFactoryJndiname();

	/**
	 * Returns the meta object for enum '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Version</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapVersion
	 * @generated
	 */
	EEnum getSoapVersion();

	/**
	 * Returns the meta object for enum '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapMode <em>Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Mode</em>'.
	 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapMode
	 * @generated
	 */
	EEnum getSoapMode();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SoapFactory getSoapFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl <em>Provides</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.soap.soap.impl.SoapProvidesImpl
		 * @see com.ebmwebsourcing.petals.services.soap.soap.impl.SoapPackageImpl#getSoapProvides()
		 * @generated
		 */
		EClass SOAP_PROVIDES = eINSTANCE.getSoapProvides();

		/**
		 * The meta object literal for the '<em><b>Address</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_PROVIDES__ADDRESS = eINSTANCE.getSoapProvides_Address();

		/**
		 * The meta object literal for the '<em><b>Soap Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_PROVIDES__SOAP_VERSION = eINSTANCE.getSoapProvides_SoapVersion();

		/**
		 * The meta object literal for the '<em><b>Chunked Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_PROVIDES__CHUNKED_MODE = eINSTANCE.getSoapProvides_ChunkedMode();

		/**
		 * The meta object literal for the '<em><b>Synchonous Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_PROVIDES__SYNCHONOUS_TIMEOUT = eINSTANCE.getSoapProvides_SynchonousTimeout();

		/**
		 * The meta object literal for the '<em><b>Cleanup Transport</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_PROVIDES__CLEANUP_TRANSPORT = eINSTANCE.getSoapProvides_CleanupTransport();

		/**
		 * The meta object literal for the '<em><b>Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_PROVIDES__MODE = eINSTANCE.getSoapProvides_Mode();

		/**
		 * The meta object literal for the '<em><b>Proxy Host</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_PROVIDES__PROXY_HOST = eINSTANCE.getSoapProvides_ProxyHost();

		/**
		 * The meta object literal for the '<em><b>Proxy User</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_PROVIDES__PROXY_USER = eINSTANCE.getSoapProvides_ProxyUser();

		/**
		 * The meta object literal for the '<em><b>Proxy Password</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_PROVIDES__PROXY_PASSWORD = eINSTANCE.getSoapProvides_ProxyPassword();

		/**
		 * The meta object literal for the '<em><b>Proxy Domain</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_PROVIDES__PROXY_DOMAIN = eINSTANCE.getSoapProvides_ProxyDomain();

		/**
		 * The meta object literal for the '<em><b>Proxy Port</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_PROVIDES__PROXY_PORT = eINSTANCE.getSoapProvides_ProxyPort();

		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapConsumesImpl <em>Consumes</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.soap.soap.impl.SoapConsumesImpl
		 * @see com.ebmwebsourcing.petals.services.soap.soap.impl.SoapPackageImpl#getSoapConsumes()
		 * @generated
		 */
		EClass SOAP_CONSUMES = eINSTANCE.getSoapConsumes();

		/**
		 * The meta object literal for the '<em><b>Soap Service Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_CONSUMES__SOAP_SERVICE_NAME = eINSTANCE.getSoapConsumes_SoapServiceName();

		/**
		 * The meta object literal for the '<em><b>Soap Action</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_CONSUMES__SOAP_ACTION = eINSTANCE.getSoapConsumes_SoapAction();

		/**
		 * The meta object literal for the '<em><b>Synchonous Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_CONSUMES__SYNCHONOUS_TIMEOUT = eINSTANCE.getSoapConsumes_SynchonousTimeout();

		/**
		 * The meta object literal for the '<em><b>Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_CONSUMES__MODE = eINSTANCE.getSoapConsumes_Mode();

		/**
		 * The meta object literal for the '<em><b>Enable Http Transport</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_CONSUMES__ENABLE_HTTP_TRANSPORT = eINSTANCE.getSoapConsumes_EnableHttpTransport();

		/**
		 * The meta object literal for the '<em><b>Enable Jms Transport</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_CONSUMES__ENABLE_JMS_TRANSPORT = eINSTANCE.getSoapConsumes_EnableJmsTransport();

		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapComponentImpl <em>Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.soap.soap.impl.SoapComponentImpl
		 * @see com.ebmwebsourcing.petals.services.soap.soap.impl.SoapPackageImpl#getSoapComponent()
		 * @generated
		 */
		EClass SOAP_COMPONENT = eINSTANCE.getSoapComponent();

		/**
		 * The meta object literal for the '<em><b>Http Port</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_COMPONENT__HTTP_PORT = eINSTANCE.getSoapComponent_HttpPort();

		/**
		 * The meta object literal for the '<em><b>Http Host</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_COMPONENT__HTTP_HOST = eINSTANCE.getSoapComponent_HttpHost();

		/**
		 * The meta object literal for the '<em><b>Http Service List</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_COMPONENT__HTTP_SERVICE_LIST = eINSTANCE.getSoapComponent_HttpServiceList();

		/**
		 * The meta object literal for the '<em><b>Http Service Context</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_COMPONENT__HTTP_SERVICE_CONTEXT = eINSTANCE.getSoapComponent_HttpServiceContext();

		/**
		 * The meta object literal for the '<em><b>Http Service Mapping</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_COMPONENT__HTTP_SERVICE_MAPPING = eINSTANCE.getSoapComponent_HttpServiceMapping();

		/**
		 * The meta object literal for the '<em><b>Http Thread Pool Size Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_COMPONENT__HTTP_THREAD_POOL_SIZE_MIN = eINSTANCE.getSoapComponent_HttpThreadPoolSizeMin();

		/**
		 * The meta object literal for the '<em><b>Http Thread Pool Size Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_COMPONENT__HTTP_THREAD_POOL_SIZE_MAX = eINSTANCE.getSoapComponent_HttpThreadPoolSizeMax();

		/**
		 * The meta object literal for the '<em><b>Http Acceptors</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_COMPONENT__HTTP_ACCEPTORS = eINSTANCE.getSoapComponent_HttpAcceptors();

		/**
		 * The meta object literal for the '<em><b>Java Naming Factory Initial</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_COMPONENT__JAVA_NAMING_FACTORY_INITIAL = eINSTANCE.getSoapComponent_JavaNamingFactoryInitial();

		/**
		 * The meta object literal for the '<em><b>Java Naming Provider Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_COMPONENT__JAVA_NAMING_PROVIDER_URL = eINSTANCE.getSoapComponent_JavaNamingProviderUrl();

		/**
		 * The meta object literal for the '<em><b>Jms Connection Factory Jndiname</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOAP_COMPONENT__JMS_CONNECTION_FACTORY_JNDINAME = eINSTANCE.getSoapComponent_JmsConnectionFactoryJndiname();

		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapVersion <em>Version</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapVersion
		 * @see com.ebmwebsourcing.petals.services.soap.soap.impl.SoapPackageImpl#getSoapVersion()
		 * @generated
		 */
		EEnum SOAP_VERSION = eINSTANCE.getSoapVersion();

		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.soap.soap.SoapMode <em>Mode</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.soap.soap.SoapMode
		 * @see com.ebmwebsourcing.petals.services.soap.soap.impl.SoapPackageImpl#getSoapMode()
		 * @generated
		 */
		EEnum SOAP_MODE = eINSTANCE.getSoapMode();

	}

} //SoapPackage
