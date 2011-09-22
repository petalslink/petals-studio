/**
 *  Copyright (c) 2009-2011, EBM WebSourcing
 *  
 *  This source code is available under agreement available at
 *  http://www.petalslink.com/legal/licenses/petals-studio
 *  
 *  You should have received a copy of the agreement along with this program.
 *  If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 * $Id$
 */
package com.sun.java.xml.ns.jbi;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.sun.java.xml.ns.jbi.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.DocumentRoot#getArtifactsZip <em>Artifacts Zip</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.DocumentRoot#getBootstrapClassName <em>Bootstrap Class Name</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.DocumentRoot#getBootstrapClassPath <em>Bootstrap Class Path</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.DocumentRoot#getComponent <em>Component</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.DocumentRoot#getComponentClassName <em>Component Class Name</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.DocumentRoot#getComponentClassPath <em>Component Class Path</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.DocumentRoot#getComponentName <em>Component Name</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.DocumentRoot#getConnection <em>Connection</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.DocumentRoot#getConnections <em>Connections</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.DocumentRoot#getConsumer <em>Consumer</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.DocumentRoot#getConsumes <em>Consumes</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.DocumentRoot#getDescription <em>Description</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.DocumentRoot#getIdentification <em>Identification</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.DocumentRoot#getJbi <em>Jbi</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.DocumentRoot#getName <em>Name</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.DocumentRoot#getPathElement <em>Path Element</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.DocumentRoot#getProvider <em>Provider</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.DocumentRoot#getProvides <em>Provides</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.DocumentRoot#getServiceAssembly <em>Service Assembly</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.DocumentRoot#getServices <em>Services</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.DocumentRoot#getServiceUnit <em>Service Unit</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.DocumentRoot#getSharedLibraryClassPath <em>Shared Library Class Path</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.DocumentRoot#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface DocumentRoot extends EObject {
	/**
	 * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mixed</em>' attribute list.
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot_Mixed()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='elementWildcard' name=':mixed'"
	 * @generated
	 */
	FeatureMap getMixed();

	/**
	 * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XMLNS Prefix Map</em>' map.
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot_XMLNSPrefixMap()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
	 *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
	 * @generated
	 */
	EMap<String, String> getXMLNSPrefixMap();

	/**
	 * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XSI Schema Location</em>' map.
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot_XSISchemaLocation()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
	 *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
	 * @generated
	 */
	EMap<String, String> getXSISchemaLocation();

	/**
	 * Returns the value of the '<em><b>Artifacts Zip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Artifacts Zip</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Artifacts Zip</em>' attribute.
	 * @see #setArtifactsZip(String)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot_ArtifactsZip()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='artifacts-zip' namespace='##targetNamespace'"
	 * @generated
	 */
	String getArtifactsZip();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getArtifactsZip <em>Artifacts Zip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Artifacts Zip</em>' attribute.
	 * @see #getArtifactsZip()
	 * @generated
	 */
	void setArtifactsZip(String value);

	/**
	 * Returns the value of the '<em><b>Bootstrap Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bootstrap Class Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bootstrap Class Name</em>' attribute.
	 * @see #setBootstrapClassName(String)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot_BootstrapClassName()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='bootstrap-class-name' namespace='##targetNamespace'"
	 * @generated
	 */
	String getBootstrapClassName();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getBootstrapClassName <em>Bootstrap Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bootstrap Class Name</em>' attribute.
	 * @see #getBootstrapClassName()
	 * @generated
	 */
	void setBootstrapClassName(String value);

	/**
	 * Returns the value of the '<em><b>Bootstrap Class Path</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bootstrap Class Path</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bootstrap Class Path</em>' containment reference.
	 * @see #setBootstrapClassPath(ClassPath)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot_BootstrapClassPath()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='bootstrap-class-path' namespace='##targetNamespace'"
	 * @generated
	 */
	ClassPath getBootstrapClassPath();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getBootstrapClassPath <em>Bootstrap Class Path</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bootstrap Class Path</em>' containment reference.
	 * @see #getBootstrapClassPath()
	 * @generated
	 */
	void setBootstrapClassPath(ClassPath value);

	/**
	 * Returns the value of the '<em><b>Component</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component</em>' containment reference.
	 * @see #setComponent(Component)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot_Component()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='component' namespace='##targetNamespace'"
	 * @generated
	 */
	Component getComponent();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getComponent <em>Component</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component</em>' containment reference.
	 * @see #getComponent()
	 * @generated
	 */
	void setComponent(Component value);

	/**
	 * Returns the value of the '<em><b>Component Class Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Class Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Class Name</em>' containment reference.
	 * @see #setComponentClassName(ComponentClassName)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot_ComponentClassName()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='component-class-name' namespace='##targetNamespace'"
	 * @generated
	 */
	ComponentClassName getComponentClassName();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getComponentClassName <em>Component Class Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Class Name</em>' containment reference.
	 * @see #getComponentClassName()
	 * @generated
	 */
	void setComponentClassName(ComponentClassName value);

	/**
	 * Returns the value of the '<em><b>Component Class Path</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Class Path</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Class Path</em>' containment reference.
	 * @see #setComponentClassPath(ClassPath)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot_ComponentClassPath()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='component-class-path' namespace='##targetNamespace'"
	 * @generated
	 */
	ClassPath getComponentClassPath();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getComponentClassPath <em>Component Class Path</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Class Path</em>' containment reference.
	 * @see #getComponentClassPath()
	 * @generated
	 */
	void setComponentClassPath(ClassPath value);

	/**
	 * Returns the value of the '<em><b>Component Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Name</em>' attribute.
	 * @see #setComponentName(String)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot_ComponentName()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.NCName" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='component-name' namespace='##targetNamespace'"
	 * @generated
	 */
	String getComponentName();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getComponentName <em>Component Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Name</em>' attribute.
	 * @see #getComponentName()
	 * @generated
	 */
	void setComponentName(String value);

	/**
	 * Returns the value of the '<em><b>Connection</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connection</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connection</em>' containment reference.
	 * @see #setConnection(Connection)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot_Connection()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='connection' namespace='##targetNamespace'"
	 * @generated
	 */
	Connection getConnection();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getConnection <em>Connection</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connection</em>' containment reference.
	 * @see #getConnection()
	 * @generated
	 */
	void setConnection(Connection value);

	/**
	 * Returns the value of the '<em><b>Connections</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connections</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connections</em>' containment reference.
	 * @see #setConnections(Connections)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot_Connections()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='connections' namespace='##targetNamespace'"
	 * @generated
	 */
	Connections getConnections();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getConnections <em>Connections</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connections</em>' containment reference.
	 * @see #getConnections()
	 * @generated
	 */
	void setConnections(Connections value);

	/**
	 * Returns the value of the '<em><b>Consumer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Consumer</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Consumer</em>' containment reference.
	 * @see #setConsumer(Consumer)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot_Consumer()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='consumer' namespace='##targetNamespace'"
	 * @generated
	 */
	Consumer getConsumer();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getConsumer <em>Consumer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Consumer</em>' containment reference.
	 * @see #getConsumer()
	 * @generated
	 */
	void setConsumer(Consumer value);

	/**
	 * Returns the value of the '<em><b>Consumes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Consumes</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Consumes</em>' containment reference.
	 * @see #setConsumes(Consumes)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot_Consumes()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='consumes' namespace='##targetNamespace'"
	 * @generated
	 */
	Consumes getConsumes();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getConsumes <em>Consumes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Consumes</em>' containment reference.
	 * @see #getConsumes()
	 * @generated
	 */
	void setConsumes(Consumes value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot_Description()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='description' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Identification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identification</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identification</em>' containment reference.
	 * @see #setIdentification(Identification)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot_Identification()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='identification' namespace='##targetNamespace'"
	 * @generated
	 */
	Identification getIdentification();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getIdentification <em>Identification</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identification</em>' containment reference.
	 * @see #getIdentification()
	 * @generated
	 */
	void setIdentification(Identification value);

	/**
	 * Returns the value of the '<em><b>Jbi</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Jbi</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Jbi</em>' containment reference.
	 * @see #setJbi(Jbi)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot_Jbi()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='jbi' namespace='##targetNamespace'"
	 * @generated
	 */
	Jbi getJbi();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getJbi <em>Jbi</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Jbi</em>' containment reference.
	 * @see #getJbi()
	 * @generated
	 */
	void setJbi(Jbi value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot_Name()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.NCName" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='name' namespace='##targetNamespace'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Path Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path Element</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path Element</em>' attribute.
	 * @see #setPathElement(String)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot_PathElement()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='path-element' namespace='##targetNamespace'"
	 * @generated
	 */
	String getPathElement();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getPathElement <em>Path Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path Element</em>' attribute.
	 * @see #getPathElement()
	 * @generated
	 */
	void setPathElement(String value);

	/**
	 * Returns the value of the '<em><b>Provider</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Provider</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Provider</em>' containment reference.
	 * @see #setProvider(Provider)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot_Provider()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='provider' namespace='##targetNamespace'"
	 * @generated
	 */
	Provider getProvider();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getProvider <em>Provider</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Provider</em>' containment reference.
	 * @see #getProvider()
	 * @generated
	 */
	void setProvider(Provider value);

	/**
	 * Returns the value of the '<em><b>Provides</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Provides</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Provides</em>' containment reference.
	 * @see #setProvides(Provides)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot_Provides()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='provides' namespace='##targetNamespace'"
	 * @generated
	 */
	Provides getProvides();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getProvides <em>Provides</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Provides</em>' containment reference.
	 * @see #getProvides()
	 * @generated
	 */
	void setProvides(Provides value);

	/**
	 * Returns the value of the '<em><b>Service Assembly</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service Assembly</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Service Assembly</em>' containment reference.
	 * @see #setServiceAssembly(ServiceAssembly)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot_ServiceAssembly()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='service-assembly' namespace='##targetNamespace'"
	 * @generated
	 */
	ServiceAssembly getServiceAssembly();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getServiceAssembly <em>Service Assembly</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Assembly</em>' containment reference.
	 * @see #getServiceAssembly()
	 * @generated
	 */
	void setServiceAssembly(ServiceAssembly value);

	/**
	 * Returns the value of the '<em><b>Services</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Services</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Services</em>' containment reference.
	 * @see #setServices(Services)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot_Services()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='services' namespace='##targetNamespace'"
	 * @generated
	 */
	Services getServices();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getServices <em>Services</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Services</em>' containment reference.
	 * @see #getServices()
	 * @generated
	 */
	void setServices(Services value);

	/**
	 * Returns the value of the '<em><b>Service Unit</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service Unit</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Service Unit</em>' containment reference.
	 * @see #setServiceUnit(ServiceUnit)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot_ServiceUnit()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='service-unit' namespace='##targetNamespace'"
	 * @generated
	 */
	ServiceUnit getServiceUnit();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getServiceUnit <em>Service Unit</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Unit</em>' containment reference.
	 * @see #getServiceUnit()
	 * @generated
	 */
	void setServiceUnit(ServiceUnit value);

	/**
	 * Returns the value of the '<em><b>Shared Library Class Path</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Shared Library Class Path</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Shared Library Class Path</em>' containment reference.
	 * @see #setSharedLibraryClassPath(ClassPath)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot_SharedLibraryClassPath()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='shared-library-class-path' namespace='##targetNamespace'"
	 * @generated
	 */
	ClassPath getSharedLibraryClassPath();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getSharedLibraryClassPath <em>Shared Library Class Path</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shared Library Class Path</em>' containment reference.
	 * @see #getSharedLibraryClassPath()
	 * @generated
	 */
	void setSharedLibraryClassPath(ClassPath value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' containment reference.
	 * @see #setTarget(Target)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getDocumentRoot_Target()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='target' namespace='##targetNamespace'"
	 * @generated
	 */
	Target getTarget();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.DocumentRoot#getTarget <em>Target</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' containment reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Target value);

} // DocumentRoot
