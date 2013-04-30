/**
 *  Copyright (c) 2009-2013, Linagora
 *  
 *  This source code is available under agreement available at
 *  http://www.petalslink.com/legal/licenses/petals-studio
 *  
 *  You should have received a copy of the agreement along with this program.
 *  If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.sun.java.xml.ns.jbi;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see com.sun.java.xml.ns.jbi.JbiFactory
 * @model kind="package"
 * @generated
 */
public interface JbiPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "jbi";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://java.sun.com/xml/ns/jbi";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "jbi";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	JbiPackage eINSTANCE = com.sun.java.xml.ns.jbi.impl.JbiPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.sun.java.xml.ns.jbi.impl.AbstractExtensibleElementImpl <em>Abstract Extensible Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.sun.java.xml.ns.jbi.impl.AbstractExtensibleElementImpl
	 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getAbstractExtensibleElement()
	 * @generated
	 */
	int ABSTRACT_EXTENSIBLE_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EXTENSIBLE_ELEMENT__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EXTENSIBLE_ELEMENT__OTHER = 1;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EXTENSIBLE_ELEMENT__LOCAL = 2;

	/**
	 * The number of structural features of the '<em>Abstract Extensible Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.sun.java.xml.ns.jbi.impl.ClassPathImpl <em>Class Path</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.sun.java.xml.ns.jbi.impl.ClassPathImpl
	 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getClassPath()
	 * @generated
	 */
	int CLASS_PATH = 1;

	/**
	 * The feature id for the '<em><b>Path Element</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_PATH__PATH_ELEMENT = 0;

	/**
	 * The number of structural features of the '<em>Class Path</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_PATH_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.sun.java.xml.ns.jbi.impl.ComponentImpl <em>Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.sun.java.xml.ns.jbi.impl.ComponentImpl
	 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getComponent()
	 * @generated
	 */
	int COMPONENT = 2;

	/**
	 * The feature id for the '<em><b>Identification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__IDENTIFICATION = 0;

	/**
	 * The feature id for the '<em><b>Component Class Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__COMPONENT_CLASS_NAME = 1;

	/**
	 * The feature id for the '<em><b>Component Class Path</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__COMPONENT_CLASS_PATH = 2;

	/**
	 * The feature id for the '<em><b>Bootstrap Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__BOOTSTRAP_CLASS_NAME = 3;

	/**
	 * The feature id for the '<em><b>Bootstrap Class Path</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__BOOTSTRAP_CLASS_PATH = 4;

	/**
	 * The feature id for the '<em><b>Shared Library List</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__SHARED_LIBRARY_LIST = 5;

	/**
	 * The feature id for the '<em><b>Shared Library</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__SHARED_LIBRARY = 6;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__GROUP = 7;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__ANY = 8;

	/**
	 * The feature id for the '<em><b>Any1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__ANY1 = 9;

	/**
	 * The feature id for the '<em><b>Bootstrap Class Loader Delegation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__BOOTSTRAP_CLASS_LOADER_DELEGATION = 10;

	/**
	 * The feature id for the '<em><b>Component Class Loader Delegation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__COMPONENT_CLASS_LOADER_DELEGATION = 11;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__TYPE = 12;

	/**
	 * The number of structural features of the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_FEATURE_COUNT = 13;

	/**
	 * The meta object id for the '{@link com.sun.java.xml.ns.jbi.impl.ComponentClassNameImpl <em>Component Class Name</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.sun.java.xml.ns.jbi.impl.ComponentClassNameImpl
	 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getComponentClassName()
	 * @generated
	 */
	int COMPONENT_CLASS_NAME = 3;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_CLASS_NAME__MIXED = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_CLASS_NAME__DESCRIPTION = 1;

	/**
	 * The number of structural features of the '<em>Component Class Name</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_CLASS_NAME_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.sun.java.xml.ns.jbi.impl.ConnectionImpl <em>Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.sun.java.xml.ns.jbi.impl.ConnectionImpl
	 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getConnection()
	 * @generated
	 */
	int CONNECTION = 4;

	/**
	 * The feature id for the '<em><b>Consumer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__CONSUMER = 0;

	/**
	 * The feature id for the '<em><b>Provider</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__PROVIDER = 1;

	/**
	 * The number of structural features of the '<em>Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.sun.java.xml.ns.jbi.impl.ConnectionsImpl <em>Connections</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.sun.java.xml.ns.jbi.impl.ConnectionsImpl
	 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getConnections()
	 * @generated
	 */
	int CONNECTIONS = 5;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTIONS__GROUP = ABSTRACT_EXTENSIBLE_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTIONS__OTHER = ABSTRACT_EXTENSIBLE_ELEMENT__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTIONS__LOCAL = ABSTRACT_EXTENSIBLE_ELEMENT__LOCAL;

	/**
	 * The feature id for the '<em><b>Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTIONS__CONNECTION = ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Connections</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTIONS_FEATURE_COUNT = ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.sun.java.xml.ns.jbi.impl.ConsumerImpl <em>Consumer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.sun.java.xml.ns.jbi.impl.ConsumerImpl
	 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getConsumer()
	 * @generated
	 */
	int CONSUMER = 6;

	/**
	 * The feature id for the '<em><b>Endpoint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSUMER__ENDPOINT_NAME = 0;

	/**
	 * The feature id for the '<em><b>Interface Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSUMER__INTERFACE_NAME = 1;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSUMER__SERVICE_NAME = 2;

	/**
	 * The number of structural features of the '<em>Consumer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSUMER_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.sun.java.xml.ns.jbi.impl.DocumentRootImpl
	 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 7;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Jbi</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__JBI = 3;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link com.sun.java.xml.ns.jbi.impl.IdentificationImpl <em>Identification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.sun.java.xml.ns.jbi.impl.IdentificationImpl
	 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getIdentification()
	 * @generated
	 */
	int IDENTIFICATION = 8;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFICATION__GROUP = ABSTRACT_EXTENSIBLE_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFICATION__OTHER = ABSTRACT_EXTENSIBLE_ELEMENT__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFICATION__LOCAL = ABSTRACT_EXTENSIBLE_ELEMENT__LOCAL;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFICATION__NAME = ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFICATION__DESCRIPTION = ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Identification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFICATION_FEATURE_COUNT = ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.sun.java.xml.ns.jbi.impl.JbiImpl <em>Jbi</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.sun.java.xml.ns.jbi.impl.JbiImpl
	 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getJbi()
	 * @generated
	 */
	int JBI = 9;

	/**
	 * The feature id for the '<em><b>Component</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JBI__COMPONENT = 0;

	/**
	 * The feature id for the '<em><b>Shared Library</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JBI__SHARED_LIBRARY = 1;

	/**
	 * The feature id for the '<em><b>Service Assembly</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JBI__SERVICE_ASSEMBLY = 2;

	/**
	 * The feature id for the '<em><b>Services</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JBI__SERVICES = 3;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JBI__VERSION = 4;

	/**
	 * The number of structural features of the '<em>Jbi</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JBI_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link com.sun.java.xml.ns.jbi.impl.ServicesImpl <em>Services</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.sun.java.xml.ns.jbi.impl.ServicesImpl
	 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getServices()
	 * @generated
	 */
	int SERVICES = 10;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICES__GROUP = ABSTRACT_EXTENSIBLE_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICES__OTHER = ABSTRACT_EXTENSIBLE_ELEMENT__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICES__LOCAL = ABSTRACT_EXTENSIBLE_ELEMENT__LOCAL;

	/**
	 * The feature id for the '<em><b>Provides</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICES__PROVIDES = ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Consumes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICES__CONSUMES = ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Binding Component</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICES__BINDING_COMPONENT = ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Services</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICES_FEATURE_COUNT = ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link com.sun.java.xml.ns.jbi.impl.AbstractEndpointImpl <em>Abstract Endpoint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.sun.java.xml.ns.jbi.impl.AbstractEndpointImpl
	 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getAbstractEndpoint()
	 * @generated
	 */
	int ABSTRACT_ENDPOINT = 11;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ENDPOINT__GROUP = ABSTRACT_EXTENSIBLE_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ENDPOINT__OTHER = ABSTRACT_EXTENSIBLE_ELEMENT__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ENDPOINT__LOCAL = ABSTRACT_EXTENSIBLE_ELEMENT__LOCAL;

	/**
	 * The feature id for the '<em><b>Endpoint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ENDPOINT__ENDPOINT_NAME = ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Interface Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ENDPOINT__INTERFACE_NAME = ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ENDPOINT__SERVICE_NAME = ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Abstract Endpoint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ENDPOINT_FEATURE_COUNT = ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link com.sun.java.xml.ns.jbi.impl.ConsumesImpl <em>Consumes</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.sun.java.xml.ns.jbi.impl.ConsumesImpl
	 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getConsumes()
	 * @generated
	 */
	int CONSUMES = 12;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSUMES__GROUP = ABSTRACT_ENDPOINT__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSUMES__OTHER = ABSTRACT_ENDPOINT__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSUMES__LOCAL = ABSTRACT_ENDPOINT__LOCAL;

	/**
	 * The feature id for the '<em><b>Endpoint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSUMES__ENDPOINT_NAME = ABSTRACT_ENDPOINT__ENDPOINT_NAME;

	/**
	 * The feature id for the '<em><b>Interface Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSUMES__INTERFACE_NAME = ABSTRACT_ENDPOINT__INTERFACE_NAME;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSUMES__SERVICE_NAME = ABSTRACT_ENDPOINT__SERVICE_NAME;

	/**
	 * The number of structural features of the '<em>Consumes</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSUMES_FEATURE_COUNT = ABSTRACT_ENDPOINT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.sun.java.xml.ns.jbi.impl.ProvidesImpl <em>Provides</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.sun.java.xml.ns.jbi.impl.ProvidesImpl
	 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getProvides()
	 * @generated
	 */
	int PROVIDES = 13;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDES__GROUP = ABSTRACT_ENDPOINT__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDES__OTHER = ABSTRACT_ENDPOINT__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDES__LOCAL = ABSTRACT_ENDPOINT__LOCAL;

	/**
	 * The feature id for the '<em><b>Endpoint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDES__ENDPOINT_NAME = ABSTRACT_ENDPOINT__ENDPOINT_NAME;

	/**
	 * The feature id for the '<em><b>Interface Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDES__INTERFACE_NAME = ABSTRACT_ENDPOINT__INTERFACE_NAME;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDES__SERVICE_NAME = ABSTRACT_ENDPOINT__SERVICE_NAME;

	/**
	 * The number of structural features of the '<em>Provides</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDES_FEATURE_COUNT = ABSTRACT_ENDPOINT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.sun.java.xml.ns.jbi.impl.ProviderImpl <em>Provider</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.sun.java.xml.ns.jbi.impl.ProviderImpl
	 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getProvider()
	 * @generated
	 */
	int PROVIDER = 14;

	/**
	 * The feature id for the '<em><b>Endpoint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDER__ENDPOINT_NAME = 0;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDER__SERVICE_NAME = 1;

	/**
	 * The number of structural features of the '<em>Provider</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDER_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.sun.java.xml.ns.jbi.impl.ServiceAssemblyImpl <em>Service Assembly</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.sun.java.xml.ns.jbi.impl.ServiceAssemblyImpl
	 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getServiceAssembly()
	 * @generated
	 */
	int SERVICE_ASSEMBLY = 15;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_ASSEMBLY__GROUP = ABSTRACT_EXTENSIBLE_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_ASSEMBLY__OTHER = ABSTRACT_EXTENSIBLE_ELEMENT__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_ASSEMBLY__LOCAL = ABSTRACT_EXTENSIBLE_ELEMENT__LOCAL;

	/**
	 * The feature id for the '<em><b>Identification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_ASSEMBLY__IDENTIFICATION = ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Service Unit</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_ASSEMBLY__SERVICE_UNIT = ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Connections</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_ASSEMBLY__CONNECTIONS = ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Service Assembly</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_ASSEMBLY_FEATURE_COUNT = ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link com.sun.java.xml.ns.jbi.impl.ServiceUnitImpl <em>Service Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.sun.java.xml.ns.jbi.impl.ServiceUnitImpl
	 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getServiceUnit()
	 * @generated
	 */
	int SERVICE_UNIT = 16;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_UNIT__GROUP = ABSTRACT_EXTENSIBLE_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_UNIT__OTHER = ABSTRACT_EXTENSIBLE_ELEMENT__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_UNIT__LOCAL = ABSTRACT_EXTENSIBLE_ELEMENT__LOCAL;

	/**
	 * The feature id for the '<em><b>Identification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_UNIT__IDENTIFICATION = ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_UNIT__TARGET = ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Service Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_UNIT_FEATURE_COUNT = ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.sun.java.xml.ns.jbi.impl.SharedLibraryTypeImpl <em>Shared Library Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.sun.java.xml.ns.jbi.impl.SharedLibraryTypeImpl
	 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getSharedLibraryType()
	 * @generated
	 */
	int SHARED_LIBRARY_TYPE = 17;

	/**
	 * The feature id for the '<em><b>Identification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHARED_LIBRARY_TYPE__IDENTIFICATION = 0;

	/**
	 * The feature id for the '<em><b>Shared Library Class Path</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHARED_LIBRARY_TYPE__SHARED_LIBRARY_CLASS_PATH = 1;

	/**
	 * The feature id for the '<em><b>Class Loader Delegation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHARED_LIBRARY_TYPE__CLASS_LOADER_DELEGATION = 2;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHARED_LIBRARY_TYPE__VERSION = 3;

	/**
	 * The number of structural features of the '<em>Shared Library Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHARED_LIBRARY_TYPE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link com.sun.java.xml.ns.jbi.impl.SharedLibraryType1Impl <em>Shared Library Type1</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.sun.java.xml.ns.jbi.impl.SharedLibraryType1Impl
	 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getSharedLibraryType1()
	 * @generated
	 */
	int SHARED_LIBRARY_TYPE1 = 18;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHARED_LIBRARY_TYPE1__MIXED = 0;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHARED_LIBRARY_TYPE1__VERSION = 1;

	/**
	 * The number of structural features of the '<em>Shared Library Type1</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHARED_LIBRARY_TYPE1_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.sun.java.xml.ns.jbi.impl.TargetImpl <em>Target</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.sun.java.xml.ns.jbi.impl.TargetImpl
	 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getTarget()
	 * @generated
	 */
	int TARGET = 19;

	/**
	 * The feature id for the '<em><b>Artifacts Zip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET__ARTIFACTS_ZIP = 0;

	/**
	 * The feature id for the '<em><b>Component Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET__COMPONENT_NAME = 1;

	/**
	 * The number of structural features of the '<em>Target</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.sun.java.xml.ns.jbi.ClassLoaderDelegationType <em>Class Loader Delegation Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.sun.java.xml.ns.jbi.ClassLoaderDelegationType
	 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getClassLoaderDelegationType()
	 * @generated
	 */
	int CLASS_LOADER_DELEGATION_TYPE = 20;

	/**
	 * The meta object id for the '{@link com.sun.java.xml.ns.jbi.ComponentType <em>Component Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.sun.java.xml.ns.jbi.ComponentType
	 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getComponentType()
	 * @generated
	 */
	int COMPONENT_TYPE = 21;

	/**
	 * The meta object id for the '{@link com.sun.java.xml.ns.jbi.LinkType <em>Link Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.sun.java.xml.ns.jbi.LinkType
	 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getLinkType()
	 * @generated
	 */
	int LINK_TYPE = 22;

	/**
	 * The meta object id for the '<em>Class Loader Delegation Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.sun.java.xml.ns.jbi.ClassLoaderDelegationType
	 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getClassLoaderDelegationTypeObject()
	 * @generated
	 */
	int CLASS_LOADER_DELEGATION_TYPE_OBJECT = 23;

	/**
	 * The meta object id for the '<em>Component Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.sun.java.xml.ns.jbi.ComponentType
	 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getComponentTypeObject()
	 * @generated
	 */
	int COMPONENT_TYPE_OBJECT = 24;

	/**
	 * The meta object id for the '<em>Link Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.sun.java.xml.ns.jbi.LinkType
	 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getLinkTypeObject()
	 * @generated
	 */
	int LINK_TYPE_OBJECT = 25;


	/**
	 * Returns the meta object for class '{@link com.sun.java.xml.ns.jbi.AbstractExtensibleElement <em>Abstract Extensible Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Extensible Element</em>'.
	 * @see com.sun.java.xml.ns.jbi.AbstractExtensibleElement
	 * @generated
	 */
	EClass getAbstractExtensibleElement();

	/**
	 * Returns the meta object for the attribute list '{@link com.sun.java.xml.ns.jbi.AbstractExtensibleElement#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see com.sun.java.xml.ns.jbi.AbstractExtensibleElement#getGroup()
	 * @see #getAbstractExtensibleElement()
	 * @generated
	 */
	EAttribute getAbstractExtensibleElement_Group();

	/**
	 * Returns the meta object for the attribute list '{@link com.sun.java.xml.ns.jbi.AbstractExtensibleElement#getOther <em>Other</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Other</em>'.
	 * @see com.sun.java.xml.ns.jbi.AbstractExtensibleElement#getOther()
	 * @see #getAbstractExtensibleElement()
	 * @generated
	 */
	EAttribute getAbstractExtensibleElement_Other();

	/**
	 * Returns the meta object for the attribute list '{@link com.sun.java.xml.ns.jbi.AbstractExtensibleElement#getLocal <em>Local</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Local</em>'.
	 * @see com.sun.java.xml.ns.jbi.AbstractExtensibleElement#getLocal()
	 * @see #getAbstractExtensibleElement()
	 * @generated
	 */
	EAttribute getAbstractExtensibleElement_Local();

	/**
	 * Returns the meta object for class '{@link com.sun.java.xml.ns.jbi.ClassPath <em>Class Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class Path</em>'.
	 * @see com.sun.java.xml.ns.jbi.ClassPath
	 * @generated
	 */
	EClass getClassPath();

	/**
	 * Returns the meta object for the attribute list '{@link com.sun.java.xml.ns.jbi.ClassPath#getPathElement <em>Path Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Path Element</em>'.
	 * @see com.sun.java.xml.ns.jbi.ClassPath#getPathElement()
	 * @see #getClassPath()
	 * @generated
	 */
	EAttribute getClassPath_PathElement();

	/**
	 * Returns the meta object for class '{@link com.sun.java.xml.ns.jbi.Component <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component</em>'.
	 * @see com.sun.java.xml.ns.jbi.Component
	 * @generated
	 */
	EClass getComponent();

	/**
	 * Returns the meta object for the containment reference '{@link com.sun.java.xml.ns.jbi.Component#getIdentification <em>Identification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Identification</em>'.
	 * @see com.sun.java.xml.ns.jbi.Component#getIdentification()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Identification();

	/**
	 * Returns the meta object for the containment reference '{@link com.sun.java.xml.ns.jbi.Component#getComponentClassName <em>Component Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Component Class Name</em>'.
	 * @see com.sun.java.xml.ns.jbi.Component#getComponentClassName()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_ComponentClassName();

	/**
	 * Returns the meta object for the containment reference '{@link com.sun.java.xml.ns.jbi.Component#getComponentClassPath <em>Component Class Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Component Class Path</em>'.
	 * @see com.sun.java.xml.ns.jbi.Component#getComponentClassPath()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_ComponentClassPath();

	/**
	 * Returns the meta object for the attribute '{@link com.sun.java.xml.ns.jbi.Component#getBootstrapClassName <em>Bootstrap Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bootstrap Class Name</em>'.
	 * @see com.sun.java.xml.ns.jbi.Component#getBootstrapClassName()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_BootstrapClassName();

	/**
	 * Returns the meta object for the containment reference '{@link com.sun.java.xml.ns.jbi.Component#getBootstrapClassPath <em>Bootstrap Class Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Bootstrap Class Path</em>'.
	 * @see com.sun.java.xml.ns.jbi.Component#getBootstrapClassPath()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_BootstrapClassPath();

	/**
	 * Returns the meta object for the attribute list '{@link com.sun.java.xml.ns.jbi.Component#getSharedLibraryList <em>Shared Library List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Shared Library List</em>'.
	 * @see com.sun.java.xml.ns.jbi.Component#getSharedLibraryList()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_SharedLibraryList();

	/**
	 * Returns the meta object for the containment reference list '{@link com.sun.java.xml.ns.jbi.Component#getSharedLibrary <em>Shared Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Shared Library</em>'.
	 * @see com.sun.java.xml.ns.jbi.Component#getSharedLibrary()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_SharedLibrary();

	/**
	 * Returns the meta object for the attribute list '{@link com.sun.java.xml.ns.jbi.Component#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see com.sun.java.xml.ns.jbi.Component#getGroup()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Group();

	/**
	 * Returns the meta object for the attribute list '{@link com.sun.java.xml.ns.jbi.Component#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see com.sun.java.xml.ns.jbi.Component#getAny()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Any();

	/**
	 * Returns the meta object for the attribute list '{@link com.sun.java.xml.ns.jbi.Component#getAny1 <em>Any1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any1</em>'.
	 * @see com.sun.java.xml.ns.jbi.Component#getAny1()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Any1();

	/**
	 * Returns the meta object for the attribute '{@link com.sun.java.xml.ns.jbi.Component#getBootstrapClassLoaderDelegation <em>Bootstrap Class Loader Delegation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bootstrap Class Loader Delegation</em>'.
	 * @see com.sun.java.xml.ns.jbi.Component#getBootstrapClassLoaderDelegation()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_BootstrapClassLoaderDelegation();

	/**
	 * Returns the meta object for the attribute '{@link com.sun.java.xml.ns.jbi.Component#getComponentClassLoaderDelegation <em>Component Class Loader Delegation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Component Class Loader Delegation</em>'.
	 * @see com.sun.java.xml.ns.jbi.Component#getComponentClassLoaderDelegation()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_ComponentClassLoaderDelegation();

	/**
	 * Returns the meta object for the attribute '{@link com.sun.java.xml.ns.jbi.Component#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.sun.java.xml.ns.jbi.Component#getType()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Type();

	/**
	 * Returns the meta object for class '{@link com.sun.java.xml.ns.jbi.ComponentClassName <em>Component Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Class Name</em>'.
	 * @see com.sun.java.xml.ns.jbi.ComponentClassName
	 * @generated
	 */
	EClass getComponentClassName();

	/**
	 * Returns the meta object for the attribute list '{@link com.sun.java.xml.ns.jbi.ComponentClassName#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see com.sun.java.xml.ns.jbi.ComponentClassName#getMixed()
	 * @see #getComponentClassName()
	 * @generated
	 */
	EAttribute getComponentClassName_Mixed();

	/**
	 * Returns the meta object for the attribute '{@link com.sun.java.xml.ns.jbi.ComponentClassName#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see com.sun.java.xml.ns.jbi.ComponentClassName#getDescription()
	 * @see #getComponentClassName()
	 * @generated
	 */
	EAttribute getComponentClassName_Description();

	/**
	 * Returns the meta object for class '{@link com.sun.java.xml.ns.jbi.Connection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection</em>'.
	 * @see com.sun.java.xml.ns.jbi.Connection
	 * @generated
	 */
	EClass getConnection();

	/**
	 * Returns the meta object for the containment reference '{@link com.sun.java.xml.ns.jbi.Connection#getConsumer <em>Consumer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Consumer</em>'.
	 * @see com.sun.java.xml.ns.jbi.Connection#getConsumer()
	 * @see #getConnection()
	 * @generated
	 */
	EReference getConnection_Consumer();

	/**
	 * Returns the meta object for the containment reference '{@link com.sun.java.xml.ns.jbi.Connection#getProvider <em>Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Provider</em>'.
	 * @see com.sun.java.xml.ns.jbi.Connection#getProvider()
	 * @see #getConnection()
	 * @generated
	 */
	EReference getConnection_Provider();

	/**
	 * Returns the meta object for class '{@link com.sun.java.xml.ns.jbi.Connections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connections</em>'.
	 * @see com.sun.java.xml.ns.jbi.Connections
	 * @generated
	 */
	EClass getConnections();

	/**
	 * Returns the meta object for the containment reference list '{@link com.sun.java.xml.ns.jbi.Connections#getConnection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connection</em>'.
	 * @see com.sun.java.xml.ns.jbi.Connections#getConnection()
	 * @see #getConnections()
	 * @generated
	 */
	EReference getConnections_Connection();

	/**
	 * Returns the meta object for class '{@link com.sun.java.xml.ns.jbi.Consumer <em>Consumer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Consumer</em>'.
	 * @see com.sun.java.xml.ns.jbi.Consumer
	 * @generated
	 */
	EClass getConsumer();

	/**
	 * Returns the meta object for the attribute '{@link com.sun.java.xml.ns.jbi.Consumer#getEndpointName <em>Endpoint Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Endpoint Name</em>'.
	 * @see com.sun.java.xml.ns.jbi.Consumer#getEndpointName()
	 * @see #getConsumer()
	 * @generated
	 */
	EAttribute getConsumer_EndpointName();

	/**
	 * Returns the meta object for the attribute '{@link com.sun.java.xml.ns.jbi.Consumer#getInterfaceName <em>Interface Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interface Name</em>'.
	 * @see com.sun.java.xml.ns.jbi.Consumer#getInterfaceName()
	 * @see #getConsumer()
	 * @generated
	 */
	EAttribute getConsumer_InterfaceName();

	/**
	 * Returns the meta object for the attribute '{@link com.sun.java.xml.ns.jbi.Consumer#getServiceName <em>Service Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service Name</em>'.
	 * @see com.sun.java.xml.ns.jbi.Consumer#getServiceName()
	 * @see #getConsumer()
	 * @generated
	 */
	EAttribute getConsumer_ServiceName();

	/**
	 * Returns the meta object for class '{@link com.sun.java.xml.ns.jbi.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see com.sun.java.xml.ns.jbi.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see com.sun.java.xml.ns.jbi.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see com.sun.java.xml.ns.jbi.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see com.sun.java.xml.ns.jbi.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getJbi <em>Jbi</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Jbi</em>'.
	 * @see com.sun.java.xml.ns.jbi.DocumentRoot#getJbi()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Jbi();

	/**
	 * Returns the meta object for class '{@link com.sun.java.xml.ns.jbi.Identification <em>Identification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Identification</em>'.
	 * @see com.sun.java.xml.ns.jbi.Identification
	 * @generated
	 */
	EClass getIdentification();

	/**
	 * Returns the meta object for the attribute '{@link com.sun.java.xml.ns.jbi.Identification#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.sun.java.xml.ns.jbi.Identification#getName()
	 * @see #getIdentification()
	 * @generated
	 */
	EAttribute getIdentification_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.sun.java.xml.ns.jbi.Identification#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see com.sun.java.xml.ns.jbi.Identification#getDescription()
	 * @see #getIdentification()
	 * @generated
	 */
	EAttribute getIdentification_Description();

	/**
	 * Returns the meta object for class '{@link com.sun.java.xml.ns.jbi.Jbi <em>Jbi</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Jbi</em>'.
	 * @see com.sun.java.xml.ns.jbi.Jbi
	 * @generated
	 */
	EClass getJbi();

	/**
	 * Returns the meta object for the containment reference '{@link com.sun.java.xml.ns.jbi.Jbi#getComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Component</em>'.
	 * @see com.sun.java.xml.ns.jbi.Jbi#getComponent()
	 * @see #getJbi()
	 * @generated
	 */
	EReference getJbi_Component();

	/**
	 * Returns the meta object for the containment reference '{@link com.sun.java.xml.ns.jbi.Jbi#getSharedLibrary <em>Shared Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Shared Library</em>'.
	 * @see com.sun.java.xml.ns.jbi.Jbi#getSharedLibrary()
	 * @see #getJbi()
	 * @generated
	 */
	EReference getJbi_SharedLibrary();

	/**
	 * Returns the meta object for the containment reference '{@link com.sun.java.xml.ns.jbi.Jbi#getServiceAssembly <em>Service Assembly</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Service Assembly</em>'.
	 * @see com.sun.java.xml.ns.jbi.Jbi#getServiceAssembly()
	 * @see #getJbi()
	 * @generated
	 */
	EReference getJbi_ServiceAssembly();

	/**
	 * Returns the meta object for the containment reference '{@link com.sun.java.xml.ns.jbi.Jbi#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Services</em>'.
	 * @see com.sun.java.xml.ns.jbi.Jbi#getServices()
	 * @see #getJbi()
	 * @generated
	 */
	EReference getJbi_Services();

	/**
	 * Returns the meta object for the attribute '{@link com.sun.java.xml.ns.jbi.Jbi#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see com.sun.java.xml.ns.jbi.Jbi#getVersion()
	 * @see #getJbi()
	 * @generated
	 */
	EAttribute getJbi_Version();

	/**
	 * Returns the meta object for class '{@link com.sun.java.xml.ns.jbi.Services <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Services</em>'.
	 * @see com.sun.java.xml.ns.jbi.Services
	 * @generated
	 */
	EClass getServices();

	/**
	 * Returns the meta object for the containment reference list '{@link com.sun.java.xml.ns.jbi.Services#getProvides <em>Provides</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Provides</em>'.
	 * @see com.sun.java.xml.ns.jbi.Services#getProvides()
	 * @see #getServices()
	 * @generated
	 */
	EReference getServices_Provides();

	/**
	 * Returns the meta object for the containment reference list '{@link com.sun.java.xml.ns.jbi.Services#getConsumes <em>Consumes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Consumes</em>'.
	 * @see com.sun.java.xml.ns.jbi.Services#getConsumes()
	 * @see #getServices()
	 * @generated
	 */
	EReference getServices_Consumes();

	/**
	 * Returns the meta object for the attribute '{@link com.sun.java.xml.ns.jbi.Services#isBindingComponent <em>Binding Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Binding Component</em>'.
	 * @see com.sun.java.xml.ns.jbi.Services#isBindingComponent()
	 * @see #getServices()
	 * @generated
	 */
	EAttribute getServices_BindingComponent();

	/**
	 * Returns the meta object for class '{@link com.sun.java.xml.ns.jbi.AbstractEndpoint <em>Abstract Endpoint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Endpoint</em>'.
	 * @see com.sun.java.xml.ns.jbi.AbstractEndpoint
	 * @generated
	 */
	EClass getAbstractEndpoint();

	/**
	 * Returns the meta object for the attribute '{@link com.sun.java.xml.ns.jbi.AbstractEndpoint#getEndpointName <em>Endpoint Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Endpoint Name</em>'.
	 * @see com.sun.java.xml.ns.jbi.AbstractEndpoint#getEndpointName()
	 * @see #getAbstractEndpoint()
	 * @generated
	 */
	EAttribute getAbstractEndpoint_EndpointName();

	/**
	 * Returns the meta object for the attribute '{@link com.sun.java.xml.ns.jbi.AbstractEndpoint#getInterfaceName <em>Interface Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interface Name</em>'.
	 * @see com.sun.java.xml.ns.jbi.AbstractEndpoint#getInterfaceName()
	 * @see #getAbstractEndpoint()
	 * @generated
	 */
	EAttribute getAbstractEndpoint_InterfaceName();

	/**
	 * Returns the meta object for the attribute '{@link com.sun.java.xml.ns.jbi.AbstractEndpoint#getServiceName <em>Service Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service Name</em>'.
	 * @see com.sun.java.xml.ns.jbi.AbstractEndpoint#getServiceName()
	 * @see #getAbstractEndpoint()
	 * @generated
	 */
	EAttribute getAbstractEndpoint_ServiceName();

	/**
	 * Returns the meta object for class '{@link com.sun.java.xml.ns.jbi.Consumes <em>Consumes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Consumes</em>'.
	 * @see com.sun.java.xml.ns.jbi.Consumes
	 * @generated
	 */
	EClass getConsumes();

	/**
	 * Returns the meta object for class '{@link com.sun.java.xml.ns.jbi.Provides <em>Provides</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Provides</em>'.
	 * @see com.sun.java.xml.ns.jbi.Provides
	 * @generated
	 */
	EClass getProvides();

	/**
	 * Returns the meta object for class '{@link com.sun.java.xml.ns.jbi.Provider <em>Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Provider</em>'.
	 * @see com.sun.java.xml.ns.jbi.Provider
	 * @generated
	 */
	EClass getProvider();

	/**
	 * Returns the meta object for the attribute '{@link com.sun.java.xml.ns.jbi.Provider#getEndpointName <em>Endpoint Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Endpoint Name</em>'.
	 * @see com.sun.java.xml.ns.jbi.Provider#getEndpointName()
	 * @see #getProvider()
	 * @generated
	 */
	EAttribute getProvider_EndpointName();

	/**
	 * Returns the meta object for the attribute '{@link com.sun.java.xml.ns.jbi.Provider#getServiceName <em>Service Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service Name</em>'.
	 * @see com.sun.java.xml.ns.jbi.Provider#getServiceName()
	 * @see #getProvider()
	 * @generated
	 */
	EAttribute getProvider_ServiceName();

	/**
	 * Returns the meta object for class '{@link com.sun.java.xml.ns.jbi.ServiceAssembly <em>Service Assembly</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service Assembly</em>'.
	 * @see com.sun.java.xml.ns.jbi.ServiceAssembly
	 * @generated
	 */
	EClass getServiceAssembly();

	/**
	 * Returns the meta object for the containment reference '{@link com.sun.java.xml.ns.jbi.ServiceAssembly#getIdentification <em>Identification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Identification</em>'.
	 * @see com.sun.java.xml.ns.jbi.ServiceAssembly#getIdentification()
	 * @see #getServiceAssembly()
	 * @generated
	 */
	EReference getServiceAssembly_Identification();

	/**
	 * Returns the meta object for the containment reference list '{@link com.sun.java.xml.ns.jbi.ServiceAssembly#getServiceUnit <em>Service Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Service Unit</em>'.
	 * @see com.sun.java.xml.ns.jbi.ServiceAssembly#getServiceUnit()
	 * @see #getServiceAssembly()
	 * @generated
	 */
	EReference getServiceAssembly_ServiceUnit();

	/**
	 * Returns the meta object for the containment reference '{@link com.sun.java.xml.ns.jbi.ServiceAssembly#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Connections</em>'.
	 * @see com.sun.java.xml.ns.jbi.ServiceAssembly#getConnections()
	 * @see #getServiceAssembly()
	 * @generated
	 */
	EReference getServiceAssembly_Connections();

	/**
	 * Returns the meta object for class '{@link com.sun.java.xml.ns.jbi.ServiceUnit <em>Service Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service Unit</em>'.
	 * @see com.sun.java.xml.ns.jbi.ServiceUnit
	 * @generated
	 */
	EClass getServiceUnit();

	/**
	 * Returns the meta object for the containment reference '{@link com.sun.java.xml.ns.jbi.ServiceUnit#getIdentification <em>Identification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Identification</em>'.
	 * @see com.sun.java.xml.ns.jbi.ServiceUnit#getIdentification()
	 * @see #getServiceUnit()
	 * @generated
	 */
	EReference getServiceUnit_Identification();

	/**
	 * Returns the meta object for the containment reference '{@link com.sun.java.xml.ns.jbi.ServiceUnit#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Target</em>'.
	 * @see com.sun.java.xml.ns.jbi.ServiceUnit#getTarget()
	 * @see #getServiceUnit()
	 * @generated
	 */
	EReference getServiceUnit_Target();

	/**
	 * Returns the meta object for class '{@link com.sun.java.xml.ns.jbi.SharedLibraryType <em>Shared Library Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Shared Library Type</em>'.
	 * @see com.sun.java.xml.ns.jbi.SharedLibraryType
	 * @generated
	 */
	EClass getSharedLibraryType();

	/**
	 * Returns the meta object for the containment reference '{@link com.sun.java.xml.ns.jbi.SharedLibraryType#getIdentification <em>Identification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Identification</em>'.
	 * @see com.sun.java.xml.ns.jbi.SharedLibraryType#getIdentification()
	 * @see #getSharedLibraryType()
	 * @generated
	 */
	EReference getSharedLibraryType_Identification();

	/**
	 * Returns the meta object for the containment reference '{@link com.sun.java.xml.ns.jbi.SharedLibraryType#getSharedLibraryClassPath <em>Shared Library Class Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Shared Library Class Path</em>'.
	 * @see com.sun.java.xml.ns.jbi.SharedLibraryType#getSharedLibraryClassPath()
	 * @see #getSharedLibraryType()
	 * @generated
	 */
	EReference getSharedLibraryType_SharedLibraryClassPath();

	/**
	 * Returns the meta object for the attribute '{@link com.sun.java.xml.ns.jbi.SharedLibraryType#getClassLoaderDelegation <em>Class Loader Delegation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class Loader Delegation</em>'.
	 * @see com.sun.java.xml.ns.jbi.SharedLibraryType#getClassLoaderDelegation()
	 * @see #getSharedLibraryType()
	 * @generated
	 */
	EAttribute getSharedLibraryType_ClassLoaderDelegation();

	/**
	 * Returns the meta object for the attribute '{@link com.sun.java.xml.ns.jbi.SharedLibraryType#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see com.sun.java.xml.ns.jbi.SharedLibraryType#getVersion()
	 * @see #getSharedLibraryType()
	 * @generated
	 */
	EAttribute getSharedLibraryType_Version();

	/**
	 * Returns the meta object for class '{@link com.sun.java.xml.ns.jbi.SharedLibraryType1 <em>Shared Library Type1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Shared Library Type1</em>'.
	 * @see com.sun.java.xml.ns.jbi.SharedLibraryType1
	 * @generated
	 */
	EClass getSharedLibraryType1();

	/**
	 * Returns the meta object for the attribute list '{@link com.sun.java.xml.ns.jbi.SharedLibraryType1#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see com.sun.java.xml.ns.jbi.SharedLibraryType1#getMixed()
	 * @see #getSharedLibraryType1()
	 * @generated
	 */
	EAttribute getSharedLibraryType1_Mixed();

	/**
	 * Returns the meta object for the attribute '{@link com.sun.java.xml.ns.jbi.SharedLibraryType1#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see com.sun.java.xml.ns.jbi.SharedLibraryType1#getVersion()
	 * @see #getSharedLibraryType1()
	 * @generated
	 */
	EAttribute getSharedLibraryType1_Version();

	/**
	 * Returns the meta object for class '{@link com.sun.java.xml.ns.jbi.Target <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Target</em>'.
	 * @see com.sun.java.xml.ns.jbi.Target
	 * @generated
	 */
	EClass getTarget();

	/**
	 * Returns the meta object for the attribute '{@link com.sun.java.xml.ns.jbi.Target#getArtifactsZip <em>Artifacts Zip</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Artifacts Zip</em>'.
	 * @see com.sun.java.xml.ns.jbi.Target#getArtifactsZip()
	 * @see #getTarget()
	 * @generated
	 */
	EAttribute getTarget_ArtifactsZip();

	/**
	 * Returns the meta object for the attribute '{@link com.sun.java.xml.ns.jbi.Target#getComponentName <em>Component Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Component Name</em>'.
	 * @see com.sun.java.xml.ns.jbi.Target#getComponentName()
	 * @see #getTarget()
	 * @generated
	 */
	EAttribute getTarget_ComponentName();

	/**
	 * Returns the meta object for enum '{@link com.sun.java.xml.ns.jbi.ClassLoaderDelegationType <em>Class Loader Delegation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Class Loader Delegation Type</em>'.
	 * @see com.sun.java.xml.ns.jbi.ClassLoaderDelegationType
	 * @generated
	 */
	EEnum getClassLoaderDelegationType();

	/**
	 * Returns the meta object for enum '{@link com.sun.java.xml.ns.jbi.ComponentType <em>Component Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Component Type</em>'.
	 * @see com.sun.java.xml.ns.jbi.ComponentType
	 * @generated
	 */
	EEnum getComponentType();

	/**
	 * Returns the meta object for enum '{@link com.sun.java.xml.ns.jbi.LinkType <em>Link Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Link Type</em>'.
	 * @see com.sun.java.xml.ns.jbi.LinkType
	 * @generated
	 */
	EEnum getLinkType();

	/**
	 * Returns the meta object for data type '{@link com.sun.java.xml.ns.jbi.ClassLoaderDelegationType <em>Class Loader Delegation Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Class Loader Delegation Type Object</em>'.
	 * @see com.sun.java.xml.ns.jbi.ClassLoaderDelegationType
	 * @model instanceClass="com.sun.java.xml.ns.jbi.ClassLoaderDelegationType"
	 *        extendedMetaData="name='ClassLoaderDelegationType:Object' baseType='ClassLoaderDelegationType'"
	 * @generated
	 */
	EDataType getClassLoaderDelegationTypeObject();

	/**
	 * Returns the meta object for data type '{@link com.sun.java.xml.ns.jbi.ComponentType <em>Component Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Component Type Object</em>'.
	 * @see com.sun.java.xml.ns.jbi.ComponentType
	 * @model instanceClass="com.sun.java.xml.ns.jbi.ComponentType"
	 *        extendedMetaData="name='ComponentType:Object' baseType='ComponentType'"
	 * @generated
	 */
	EDataType getComponentTypeObject();

	/**
	 * Returns the meta object for data type '{@link com.sun.java.xml.ns.jbi.LinkType <em>Link Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Link Type Object</em>'.
	 * @see com.sun.java.xml.ns.jbi.LinkType
	 * @model instanceClass="com.sun.java.xml.ns.jbi.LinkType"
	 *        extendedMetaData="name='LinkType:Object' baseType='LinkType'"
	 * @generated
	 */
	EDataType getLinkTypeObject();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	JbiFactory getJbiFactory();

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
		 * The meta object literal for the '{@link com.sun.java.xml.ns.jbi.impl.AbstractExtensibleElementImpl <em>Abstract Extensible Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.sun.java.xml.ns.jbi.impl.AbstractExtensibleElementImpl
		 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getAbstractExtensibleElement()
		 * @generated
		 */
		EClass ABSTRACT_EXTENSIBLE_ELEMENT = eINSTANCE.getAbstractExtensibleElement();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_EXTENSIBLE_ELEMENT__GROUP = eINSTANCE.getAbstractExtensibleElement_Group();

		/**
		 * The meta object literal for the '<em><b>Other</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_EXTENSIBLE_ELEMENT__OTHER = eINSTANCE.getAbstractExtensibleElement_Other();

		/**
		 * The meta object literal for the '<em><b>Local</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_EXTENSIBLE_ELEMENT__LOCAL = eINSTANCE.getAbstractExtensibleElement_Local();

		/**
		 * The meta object literal for the '{@link com.sun.java.xml.ns.jbi.impl.ClassPathImpl <em>Class Path</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.sun.java.xml.ns.jbi.impl.ClassPathImpl
		 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getClassPath()
		 * @generated
		 */
		EClass CLASS_PATH = eINSTANCE.getClassPath();

		/**
		 * The meta object literal for the '<em><b>Path Element</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS_PATH__PATH_ELEMENT = eINSTANCE.getClassPath_PathElement();

		/**
		 * The meta object literal for the '{@link com.sun.java.xml.ns.jbi.impl.ComponentImpl <em>Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.sun.java.xml.ns.jbi.impl.ComponentImpl
		 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getComponent()
		 * @generated
		 */
		EClass COMPONENT = eINSTANCE.getComponent();

		/**
		 * The meta object literal for the '<em><b>Identification</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__IDENTIFICATION = eINSTANCE.getComponent_Identification();

		/**
		 * The meta object literal for the '<em><b>Component Class Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__COMPONENT_CLASS_NAME = eINSTANCE.getComponent_ComponentClassName();

		/**
		 * The meta object literal for the '<em><b>Component Class Path</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__COMPONENT_CLASS_PATH = eINSTANCE.getComponent_ComponentClassPath();

		/**
		 * The meta object literal for the '<em><b>Bootstrap Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__BOOTSTRAP_CLASS_NAME = eINSTANCE.getComponent_BootstrapClassName();

		/**
		 * The meta object literal for the '<em><b>Bootstrap Class Path</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__BOOTSTRAP_CLASS_PATH = eINSTANCE.getComponent_BootstrapClassPath();

		/**
		 * The meta object literal for the '<em><b>Shared Library List</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__SHARED_LIBRARY_LIST = eINSTANCE.getComponent_SharedLibraryList();

		/**
		 * The meta object literal for the '<em><b>Shared Library</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__SHARED_LIBRARY = eINSTANCE.getComponent_SharedLibrary();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__GROUP = eINSTANCE.getComponent_Group();

		/**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__ANY = eINSTANCE.getComponent_Any();

		/**
		 * The meta object literal for the '<em><b>Any1</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__ANY1 = eINSTANCE.getComponent_Any1();

		/**
		 * The meta object literal for the '<em><b>Bootstrap Class Loader Delegation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__BOOTSTRAP_CLASS_LOADER_DELEGATION = eINSTANCE.getComponent_BootstrapClassLoaderDelegation();

		/**
		 * The meta object literal for the '<em><b>Component Class Loader Delegation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__COMPONENT_CLASS_LOADER_DELEGATION = eINSTANCE.getComponent_ComponentClassLoaderDelegation();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__TYPE = eINSTANCE.getComponent_Type();

		/**
		 * The meta object literal for the '{@link com.sun.java.xml.ns.jbi.impl.ComponentClassNameImpl <em>Component Class Name</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.sun.java.xml.ns.jbi.impl.ComponentClassNameImpl
		 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getComponentClassName()
		 * @generated
		 */
		EClass COMPONENT_CLASS_NAME = eINSTANCE.getComponentClassName();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_CLASS_NAME__MIXED = eINSTANCE.getComponentClassName_Mixed();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_CLASS_NAME__DESCRIPTION = eINSTANCE.getComponentClassName_Description();

		/**
		 * The meta object literal for the '{@link com.sun.java.xml.ns.jbi.impl.ConnectionImpl <em>Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.sun.java.xml.ns.jbi.impl.ConnectionImpl
		 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getConnection()
		 * @generated
		 */
		EClass CONNECTION = eINSTANCE.getConnection();

		/**
		 * The meta object literal for the '<em><b>Consumer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION__CONSUMER = eINSTANCE.getConnection_Consumer();

		/**
		 * The meta object literal for the '<em><b>Provider</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION__PROVIDER = eINSTANCE.getConnection_Provider();

		/**
		 * The meta object literal for the '{@link com.sun.java.xml.ns.jbi.impl.ConnectionsImpl <em>Connections</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.sun.java.xml.ns.jbi.impl.ConnectionsImpl
		 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getConnections()
		 * @generated
		 */
		EClass CONNECTIONS = eINSTANCE.getConnections();

		/**
		 * The meta object literal for the '<em><b>Connection</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTIONS__CONNECTION = eINSTANCE.getConnections_Connection();

		/**
		 * The meta object literal for the '{@link com.sun.java.xml.ns.jbi.impl.ConsumerImpl <em>Consumer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.sun.java.xml.ns.jbi.impl.ConsumerImpl
		 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getConsumer()
		 * @generated
		 */
		EClass CONSUMER = eINSTANCE.getConsumer();

		/**
		 * The meta object literal for the '<em><b>Endpoint Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSUMER__ENDPOINT_NAME = eINSTANCE.getConsumer_EndpointName();

		/**
		 * The meta object literal for the '<em><b>Interface Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSUMER__INTERFACE_NAME = eINSTANCE.getConsumer_InterfaceName();

		/**
		 * The meta object literal for the '<em><b>Service Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSUMER__SERVICE_NAME = eINSTANCE.getConsumer_ServiceName();

		/**
		 * The meta object literal for the '{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl <em>Document Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.sun.java.xml.ns.jbi.impl.DocumentRootImpl
		 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getDocumentRoot()
		 * @generated
		 */
		EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>Jbi</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__JBI = eINSTANCE.getDocumentRoot_Jbi();

		/**
		 * The meta object literal for the '{@link com.sun.java.xml.ns.jbi.impl.IdentificationImpl <em>Identification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.sun.java.xml.ns.jbi.impl.IdentificationImpl
		 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getIdentification()
		 * @generated
		 */
		EClass IDENTIFICATION = eINSTANCE.getIdentification();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDENTIFICATION__NAME = eINSTANCE.getIdentification_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDENTIFICATION__DESCRIPTION = eINSTANCE.getIdentification_Description();

		/**
		 * The meta object literal for the '{@link com.sun.java.xml.ns.jbi.impl.JbiImpl <em>Jbi</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.sun.java.xml.ns.jbi.impl.JbiImpl
		 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getJbi()
		 * @generated
		 */
		EClass JBI = eINSTANCE.getJbi();

		/**
		 * The meta object literal for the '<em><b>Component</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JBI__COMPONENT = eINSTANCE.getJbi_Component();

		/**
		 * The meta object literal for the '<em><b>Shared Library</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JBI__SHARED_LIBRARY = eINSTANCE.getJbi_SharedLibrary();

		/**
		 * The meta object literal for the '<em><b>Service Assembly</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JBI__SERVICE_ASSEMBLY = eINSTANCE.getJbi_ServiceAssembly();

		/**
		 * The meta object literal for the '<em><b>Services</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JBI__SERVICES = eINSTANCE.getJbi_Services();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JBI__VERSION = eINSTANCE.getJbi_Version();

		/**
		 * The meta object literal for the '{@link com.sun.java.xml.ns.jbi.impl.ServicesImpl <em>Services</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.sun.java.xml.ns.jbi.impl.ServicesImpl
		 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getServices()
		 * @generated
		 */
		EClass SERVICES = eINSTANCE.getServices();

		/**
		 * The meta object literal for the '<em><b>Provides</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICES__PROVIDES = eINSTANCE.getServices_Provides();

		/**
		 * The meta object literal for the '<em><b>Consumes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICES__CONSUMES = eINSTANCE.getServices_Consumes();

		/**
		 * The meta object literal for the '<em><b>Binding Component</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICES__BINDING_COMPONENT = eINSTANCE.getServices_BindingComponent();

		/**
		 * The meta object literal for the '{@link com.sun.java.xml.ns.jbi.impl.AbstractEndpointImpl <em>Abstract Endpoint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.sun.java.xml.ns.jbi.impl.AbstractEndpointImpl
		 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getAbstractEndpoint()
		 * @generated
		 */
		EClass ABSTRACT_ENDPOINT = eINSTANCE.getAbstractEndpoint();

		/**
		 * The meta object literal for the '<em><b>Endpoint Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_ENDPOINT__ENDPOINT_NAME = eINSTANCE.getAbstractEndpoint_EndpointName();

		/**
		 * The meta object literal for the '<em><b>Interface Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_ENDPOINT__INTERFACE_NAME = eINSTANCE.getAbstractEndpoint_InterfaceName();

		/**
		 * The meta object literal for the '<em><b>Service Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_ENDPOINT__SERVICE_NAME = eINSTANCE.getAbstractEndpoint_ServiceName();

		/**
		 * The meta object literal for the '{@link com.sun.java.xml.ns.jbi.impl.ConsumesImpl <em>Consumes</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.sun.java.xml.ns.jbi.impl.ConsumesImpl
		 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getConsumes()
		 * @generated
		 */
		EClass CONSUMES = eINSTANCE.getConsumes();

		/**
		 * The meta object literal for the '{@link com.sun.java.xml.ns.jbi.impl.ProvidesImpl <em>Provides</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.sun.java.xml.ns.jbi.impl.ProvidesImpl
		 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getProvides()
		 * @generated
		 */
		EClass PROVIDES = eINSTANCE.getProvides();

		/**
		 * The meta object literal for the '{@link com.sun.java.xml.ns.jbi.impl.ProviderImpl <em>Provider</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.sun.java.xml.ns.jbi.impl.ProviderImpl
		 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getProvider()
		 * @generated
		 */
		EClass PROVIDER = eINSTANCE.getProvider();

		/**
		 * The meta object literal for the '<em><b>Endpoint Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROVIDER__ENDPOINT_NAME = eINSTANCE.getProvider_EndpointName();

		/**
		 * The meta object literal for the '<em><b>Service Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROVIDER__SERVICE_NAME = eINSTANCE.getProvider_ServiceName();

		/**
		 * The meta object literal for the '{@link com.sun.java.xml.ns.jbi.impl.ServiceAssemblyImpl <em>Service Assembly</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.sun.java.xml.ns.jbi.impl.ServiceAssemblyImpl
		 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getServiceAssembly()
		 * @generated
		 */
		EClass SERVICE_ASSEMBLY = eINSTANCE.getServiceAssembly();

		/**
		 * The meta object literal for the '<em><b>Identification</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE_ASSEMBLY__IDENTIFICATION = eINSTANCE.getServiceAssembly_Identification();

		/**
		 * The meta object literal for the '<em><b>Service Unit</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE_ASSEMBLY__SERVICE_UNIT = eINSTANCE.getServiceAssembly_ServiceUnit();

		/**
		 * The meta object literal for the '<em><b>Connections</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE_ASSEMBLY__CONNECTIONS = eINSTANCE.getServiceAssembly_Connections();

		/**
		 * The meta object literal for the '{@link com.sun.java.xml.ns.jbi.impl.ServiceUnitImpl <em>Service Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.sun.java.xml.ns.jbi.impl.ServiceUnitImpl
		 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getServiceUnit()
		 * @generated
		 */
		EClass SERVICE_UNIT = eINSTANCE.getServiceUnit();

		/**
		 * The meta object literal for the '<em><b>Identification</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE_UNIT__IDENTIFICATION = eINSTANCE.getServiceUnit_Identification();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE_UNIT__TARGET = eINSTANCE.getServiceUnit_Target();

		/**
		 * The meta object literal for the '{@link com.sun.java.xml.ns.jbi.impl.SharedLibraryTypeImpl <em>Shared Library Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.sun.java.xml.ns.jbi.impl.SharedLibraryTypeImpl
		 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getSharedLibraryType()
		 * @generated
		 */
		EClass SHARED_LIBRARY_TYPE = eINSTANCE.getSharedLibraryType();

		/**
		 * The meta object literal for the '<em><b>Identification</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SHARED_LIBRARY_TYPE__IDENTIFICATION = eINSTANCE.getSharedLibraryType_Identification();

		/**
		 * The meta object literal for the '<em><b>Shared Library Class Path</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SHARED_LIBRARY_TYPE__SHARED_LIBRARY_CLASS_PATH = eINSTANCE.getSharedLibraryType_SharedLibraryClassPath();

		/**
		 * The meta object literal for the '<em><b>Class Loader Delegation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SHARED_LIBRARY_TYPE__CLASS_LOADER_DELEGATION = eINSTANCE.getSharedLibraryType_ClassLoaderDelegation();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SHARED_LIBRARY_TYPE__VERSION = eINSTANCE.getSharedLibraryType_Version();

		/**
		 * The meta object literal for the '{@link com.sun.java.xml.ns.jbi.impl.SharedLibraryType1Impl <em>Shared Library Type1</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.sun.java.xml.ns.jbi.impl.SharedLibraryType1Impl
		 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getSharedLibraryType1()
		 * @generated
		 */
		EClass SHARED_LIBRARY_TYPE1 = eINSTANCE.getSharedLibraryType1();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SHARED_LIBRARY_TYPE1__MIXED = eINSTANCE.getSharedLibraryType1_Mixed();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SHARED_LIBRARY_TYPE1__VERSION = eINSTANCE.getSharedLibraryType1_Version();

		/**
		 * The meta object literal for the '{@link com.sun.java.xml.ns.jbi.impl.TargetImpl <em>Target</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.sun.java.xml.ns.jbi.impl.TargetImpl
		 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getTarget()
		 * @generated
		 */
		EClass TARGET = eINSTANCE.getTarget();

		/**
		 * The meta object literal for the '<em><b>Artifacts Zip</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TARGET__ARTIFACTS_ZIP = eINSTANCE.getTarget_ArtifactsZip();

		/**
		 * The meta object literal for the '<em><b>Component Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TARGET__COMPONENT_NAME = eINSTANCE.getTarget_ComponentName();

		/**
		 * The meta object literal for the '{@link com.sun.java.xml.ns.jbi.ClassLoaderDelegationType <em>Class Loader Delegation Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.sun.java.xml.ns.jbi.ClassLoaderDelegationType
		 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getClassLoaderDelegationType()
		 * @generated
		 */
		EEnum CLASS_LOADER_DELEGATION_TYPE = eINSTANCE.getClassLoaderDelegationType();

		/**
		 * The meta object literal for the '{@link com.sun.java.xml.ns.jbi.ComponentType <em>Component Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.sun.java.xml.ns.jbi.ComponentType
		 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getComponentType()
		 * @generated
		 */
		EEnum COMPONENT_TYPE = eINSTANCE.getComponentType();

		/**
		 * The meta object literal for the '{@link com.sun.java.xml.ns.jbi.LinkType <em>Link Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.sun.java.xml.ns.jbi.LinkType
		 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getLinkType()
		 * @generated
		 */
		EEnum LINK_TYPE = eINSTANCE.getLinkType();

		/**
		 * The meta object literal for the '<em>Class Loader Delegation Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.sun.java.xml.ns.jbi.ClassLoaderDelegationType
		 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getClassLoaderDelegationTypeObject()
		 * @generated
		 */
		EDataType CLASS_LOADER_DELEGATION_TYPE_OBJECT = eINSTANCE.getClassLoaderDelegationTypeObject();

		/**
		 * The meta object literal for the '<em>Component Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.sun.java.xml.ns.jbi.ComponentType
		 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getComponentTypeObject()
		 * @generated
		 */
		EDataType COMPONENT_TYPE_OBJECT = eINSTANCE.getComponentTypeObject();

		/**
		 * The meta object literal for the '<em>Link Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.sun.java.xml.ns.jbi.LinkType
		 * @see com.sun.java.xml.ns.jbi.impl.JbiPackageImpl#getLinkTypeObject()
		 * @generated
		 */
		EDataType LINK_TYPE_OBJECT = eINSTANCE.getLinkTypeObject();

	}

} //JbiPackage
