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
package com.sun.java.xml.ns.jbi.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import com.sun.java.xml.ns.jbi.ClassPath;
import com.sun.java.xml.ns.jbi.Component;
import com.sun.java.xml.ns.jbi.ComponentClassName;
import com.sun.java.xml.ns.jbi.Connection;
import com.sun.java.xml.ns.jbi.Connections;
import com.sun.java.xml.ns.jbi.Consumer;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.DocumentRoot;
import com.sun.java.xml.ns.jbi.Identification;
import com.sun.java.xml.ns.jbi.Jbi;
import com.sun.java.xml.ns.jbi.JbiPackage;
import com.sun.java.xml.ns.jbi.Provider;
import com.sun.java.xml.ns.jbi.Provides;
import com.sun.java.xml.ns.jbi.ServiceAssembly;
import com.sun.java.xml.ns.jbi.ServiceUnit;
import com.sun.java.xml.ns.jbi.Services;
import com.sun.java.xml.ns.jbi.Target;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl#getArtifactsZip <em>Artifacts Zip</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl#getBootstrapClassName <em>Bootstrap Class Name</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl#getBootstrapClassPath <em>Bootstrap Class Path</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl#getComponent <em>Component</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl#getComponentClassName <em>Component Class Name</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl#getComponentClassPath <em>Component Class Path</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl#getComponentName <em>Component Name</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl#getConnection <em>Connection</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl#getConnections <em>Connections</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl#getConsumer <em>Consumer</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl#getConsumes <em>Consumes</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl#getIdentification <em>Identification</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl#getJbi <em>Jbi</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl#getPathElement <em>Path Element</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl#getProvider <em>Provider</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl#getProvides <em>Provides</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl#getServiceAssembly <em>Service Assembly</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl#getServices <em>Services</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl#getServiceUnit <em>Service Unit</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl#getSharedLibraryClassPath <em>Shared Library Class Path</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.DocumentRootImpl#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends EObjectImpl implements DocumentRoot {
	/**
	 * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMixed()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap mixed;

	/**
	 * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXMLNSPrefixMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xMLNSPrefixMap;

	/**
	 * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXSISchemaLocation()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xSISchemaLocation;

	/**
	 * The default value of the '{@link #getArtifactsZip() <em>Artifacts Zip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifactsZip()
	 * @generated
	 * @ordered
	 */
	protected static final String ARTIFACTS_ZIP_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getBootstrapClassName() <em>Bootstrap Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBootstrapClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String BOOTSTRAP_CLASS_NAME_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getComponentName() <em>Component Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentName()
	 * @generated
	 * @ordered
	 */
	protected static final String COMPONENT_NAME_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getPathElement() <em>Path Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPathElement()
	 * @generated
	 * @ordered
	 */
	protected static final String PATH_ELEMENT_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DocumentRootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JbiPackage.Literals.DOCUMENT_ROOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getMixed() {
		if (this.mixed == null) {
			this.mixed = new BasicFeatureMap(this, JbiPackage.DOCUMENT_ROOT__MIXED);
		}
		return this.mixed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXMLNSPrefixMap() {
		if (this.xMLNSPrefixMap == null) {
			this.xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, JbiPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		}
		return this.xMLNSPrefixMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXSISchemaLocation() {
		if (this.xSISchemaLocation == null) {
			this.xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, JbiPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		}
		return this.xSISchemaLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getArtifactsZip() {
		return (String)getMixed().get(JbiPackage.Literals.DOCUMENT_ROOT__ARTIFACTS_ZIP, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArtifactsZip(String newArtifactsZip) {
		((FeatureMap.Internal)getMixed()).set(JbiPackage.Literals.DOCUMENT_ROOT__ARTIFACTS_ZIP, newArtifactsZip);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBootstrapClassName() {
		return (String)getMixed().get(JbiPackage.Literals.DOCUMENT_ROOT__BOOTSTRAP_CLASS_NAME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBootstrapClassName(String newBootstrapClassName) {
		((FeatureMap.Internal)getMixed()).set(JbiPackage.Literals.DOCUMENT_ROOT__BOOTSTRAP_CLASS_NAME, newBootstrapClassName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassPath getBootstrapClassPath() {
		return (ClassPath)getMixed().get(JbiPackage.Literals.DOCUMENT_ROOT__BOOTSTRAP_CLASS_PATH, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newBootstrapClassPath
	 * @param msgs
	 * @return
	 * @generated
	 */
	public NotificationChain basicSetBootstrapClassPath(ClassPath newBootstrapClassPath, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(JbiPackage.Literals.DOCUMENT_ROOT__BOOTSTRAP_CLASS_PATH, newBootstrapClassPath, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBootstrapClassPath(ClassPath newBootstrapClassPath) {
		((FeatureMap.Internal)getMixed()).set(JbiPackage.Literals.DOCUMENT_ROOT__BOOTSTRAP_CLASS_PATH, newBootstrapClassPath);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Component getComponent() {
		return (Component)getMixed().get(JbiPackage.Literals.DOCUMENT_ROOT__COMPONENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newComponent
	 * @param msgs
	 * @return
	 * @generated
	 */
	public NotificationChain basicSetComponent(Component newComponent, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(JbiPackage.Literals.DOCUMENT_ROOT__COMPONENT, newComponent, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponent(Component newComponent) {
		((FeatureMap.Internal)getMixed()).set(JbiPackage.Literals.DOCUMENT_ROOT__COMPONENT, newComponent);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentClassName getComponentClassName() {
		return (ComponentClassName)getMixed().get(JbiPackage.Literals.DOCUMENT_ROOT__COMPONENT_CLASS_NAME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newComponentClassName
	 * @param msgs
	 * @return
	 * @generated
	 */
	public NotificationChain basicSetComponentClassName(ComponentClassName newComponentClassName, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(JbiPackage.Literals.DOCUMENT_ROOT__COMPONENT_CLASS_NAME, newComponentClassName, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentClassName(ComponentClassName newComponentClassName) {
		((FeatureMap.Internal)getMixed()).set(JbiPackage.Literals.DOCUMENT_ROOT__COMPONENT_CLASS_NAME, newComponentClassName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassPath getComponentClassPath() {
		return (ClassPath)getMixed().get(JbiPackage.Literals.DOCUMENT_ROOT__COMPONENT_CLASS_PATH, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newComponentClassPath
	 * @param msgs
	 * @return
	 * @generated
	 */
	public NotificationChain basicSetComponentClassPath(ClassPath newComponentClassPath, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(JbiPackage.Literals.DOCUMENT_ROOT__COMPONENT_CLASS_PATH, newComponentClassPath, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentClassPath(ClassPath newComponentClassPath) {
		((FeatureMap.Internal)getMixed()).set(JbiPackage.Literals.DOCUMENT_ROOT__COMPONENT_CLASS_PATH, newComponentClassPath);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getComponentName() {
		return (String)getMixed().get(JbiPackage.Literals.DOCUMENT_ROOT__COMPONENT_NAME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentName(String newComponentName) {
		((FeatureMap.Internal)getMixed()).set(JbiPackage.Literals.DOCUMENT_ROOT__COMPONENT_NAME, newComponentName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Connection getConnection() {
		return (Connection)getMixed().get(JbiPackage.Literals.DOCUMENT_ROOT__CONNECTION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newConnection
	 * @param msgs
	 * @return
	 * @generated
	 */
	public NotificationChain basicSetConnection(Connection newConnection, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(JbiPackage.Literals.DOCUMENT_ROOT__CONNECTION, newConnection, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnection(Connection newConnection) {
		((FeatureMap.Internal)getMixed()).set(JbiPackage.Literals.DOCUMENT_ROOT__CONNECTION, newConnection);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Connections getConnections() {
		return (Connections)getMixed().get(JbiPackage.Literals.DOCUMENT_ROOT__CONNECTIONS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newConnections
	 * @param msgs
	 * @return
	 * @generated
	 */
	public NotificationChain basicSetConnections(Connections newConnections, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(JbiPackage.Literals.DOCUMENT_ROOT__CONNECTIONS, newConnections, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnections(Connections newConnections) {
		((FeatureMap.Internal)getMixed()).set(JbiPackage.Literals.DOCUMENT_ROOT__CONNECTIONS, newConnections);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Consumer getConsumer() {
		return (Consumer)getMixed().get(JbiPackage.Literals.DOCUMENT_ROOT__CONSUMER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newConsumer
	 * @param msgs
	 * @return
	 * @generated
	 */
	public NotificationChain basicSetConsumer(Consumer newConsumer, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(JbiPackage.Literals.DOCUMENT_ROOT__CONSUMER, newConsumer, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConsumer(Consumer newConsumer) {
		((FeatureMap.Internal)getMixed()).set(JbiPackage.Literals.DOCUMENT_ROOT__CONSUMER, newConsumer);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Consumes getConsumes() {
		return (Consumes)getMixed().get(JbiPackage.Literals.DOCUMENT_ROOT__CONSUMES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newConsumes
	 * @param msgs
	 * @return
	 * @generated
	 */
	public NotificationChain basicSetConsumes(Consumes newConsumes, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(JbiPackage.Literals.DOCUMENT_ROOT__CONSUMES, newConsumes, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConsumes(Consumes newConsumes) {
		((FeatureMap.Internal)getMixed()).set(JbiPackage.Literals.DOCUMENT_ROOT__CONSUMES, newConsumes);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return (String)getMixed().get(JbiPackage.Literals.DOCUMENT_ROOT__DESCRIPTION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		((FeatureMap.Internal)getMixed()).set(JbiPackage.Literals.DOCUMENT_ROOT__DESCRIPTION, newDescription);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Identification getIdentification() {
		return (Identification)getMixed().get(JbiPackage.Literals.DOCUMENT_ROOT__IDENTIFICATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newIdentification
	 * @param msgs
	 * @return
	 * @generated
	 */
	public NotificationChain basicSetIdentification(Identification newIdentification, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(JbiPackage.Literals.DOCUMENT_ROOT__IDENTIFICATION, newIdentification, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdentification(Identification newIdentification) {
		((FeatureMap.Internal)getMixed()).set(JbiPackage.Literals.DOCUMENT_ROOT__IDENTIFICATION, newIdentification);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Jbi getJbi() {
		return (Jbi)getMixed().get(JbiPackage.Literals.DOCUMENT_ROOT__JBI, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newJbi
	 * @param msgs
	 * @return
	 * @generated
	 */
	public NotificationChain basicSetJbi(Jbi newJbi, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(JbiPackage.Literals.DOCUMENT_ROOT__JBI, newJbi, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJbi(Jbi newJbi) {
		((FeatureMap.Internal)getMixed()).set(JbiPackage.Literals.DOCUMENT_ROOT__JBI, newJbi);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return (String)getMixed().get(JbiPackage.Literals.DOCUMENT_ROOT__NAME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		((FeatureMap.Internal)getMixed()).set(JbiPackage.Literals.DOCUMENT_ROOT__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPathElement() {
		return (String)getMixed().get(JbiPackage.Literals.DOCUMENT_ROOT__PATH_ELEMENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPathElement(String newPathElement) {
		((FeatureMap.Internal)getMixed()).set(JbiPackage.Literals.DOCUMENT_ROOT__PATH_ELEMENT, newPathElement);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Provider getProvider() {
		return (Provider)getMixed().get(JbiPackage.Literals.DOCUMENT_ROOT__PROVIDER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newProvider
	 * @param msgs
	 * @return
	 * @generated
	 */
	public NotificationChain basicSetProvider(Provider newProvider, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(JbiPackage.Literals.DOCUMENT_ROOT__PROVIDER, newProvider, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProvider(Provider newProvider) {
		((FeatureMap.Internal)getMixed()).set(JbiPackage.Literals.DOCUMENT_ROOT__PROVIDER, newProvider);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Provides getProvides() {
		return (Provides)getMixed().get(JbiPackage.Literals.DOCUMENT_ROOT__PROVIDES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newProvides
	 * @param msgs
	 * @return
	 * @generated
	 */
	public NotificationChain basicSetProvides(Provides newProvides, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(JbiPackage.Literals.DOCUMENT_ROOT__PROVIDES, newProvides, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProvides(Provides newProvides) {
		((FeatureMap.Internal)getMixed()).set(JbiPackage.Literals.DOCUMENT_ROOT__PROVIDES, newProvides);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceAssembly getServiceAssembly() {
		return (ServiceAssembly)getMixed().get(JbiPackage.Literals.DOCUMENT_ROOT__SERVICE_ASSEMBLY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newServiceAssembly
	 * @param msgs
	 * @return
	 * @generated
	 */
	public NotificationChain basicSetServiceAssembly(ServiceAssembly newServiceAssembly, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(JbiPackage.Literals.DOCUMENT_ROOT__SERVICE_ASSEMBLY, newServiceAssembly, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServiceAssembly(ServiceAssembly newServiceAssembly) {
		((FeatureMap.Internal)getMixed()).set(JbiPackage.Literals.DOCUMENT_ROOT__SERVICE_ASSEMBLY, newServiceAssembly);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Services getServices() {
		return (Services)getMixed().get(JbiPackage.Literals.DOCUMENT_ROOT__SERVICES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newServices
	 * @param msgs
	 * @return
	 * @generated
	 */
	public NotificationChain basicSetServices(Services newServices, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(JbiPackage.Literals.DOCUMENT_ROOT__SERVICES, newServices, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServices(Services newServices) {
		((FeatureMap.Internal)getMixed()).set(JbiPackage.Literals.DOCUMENT_ROOT__SERVICES, newServices);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceUnit getServiceUnit() {
		return (ServiceUnit)getMixed().get(JbiPackage.Literals.DOCUMENT_ROOT__SERVICE_UNIT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newServiceUnit
	 * @param msgs
	 * @return
	 * @generated
	 */
	public NotificationChain basicSetServiceUnit(ServiceUnit newServiceUnit, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(JbiPackage.Literals.DOCUMENT_ROOT__SERVICE_UNIT, newServiceUnit, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServiceUnit(ServiceUnit newServiceUnit) {
		((FeatureMap.Internal)getMixed()).set(JbiPackage.Literals.DOCUMENT_ROOT__SERVICE_UNIT, newServiceUnit);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassPath getSharedLibraryClassPath() {
		return (ClassPath)getMixed().get(JbiPackage.Literals.DOCUMENT_ROOT__SHARED_LIBRARY_CLASS_PATH, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newSharedLibraryClassPath
	 * @param msgs
	 * @return
	 * @generated
	 */
	public NotificationChain basicSetSharedLibraryClassPath(ClassPath newSharedLibraryClassPath, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(JbiPackage.Literals.DOCUMENT_ROOT__SHARED_LIBRARY_CLASS_PATH, newSharedLibraryClassPath, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSharedLibraryClassPath(ClassPath newSharedLibraryClassPath) {
		((FeatureMap.Internal)getMixed()).set(JbiPackage.Literals.DOCUMENT_ROOT__SHARED_LIBRARY_CLASS_PATH, newSharedLibraryClassPath);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Target getTarget() {
		return (Target)getMixed().get(JbiPackage.Literals.DOCUMENT_ROOT__TARGET, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newTarget
	 * @param msgs
	 * @return
	 * @generated
	 */
	public NotificationChain basicSetTarget(Target newTarget, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(JbiPackage.Literals.DOCUMENT_ROOT__TARGET, newTarget, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(Target newTarget) {
		((FeatureMap.Internal)getMixed()).set(JbiPackage.Literals.DOCUMENT_ROOT__TARGET, newTarget);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case JbiPackage.DOCUMENT_ROOT__MIXED:
			return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
		case JbiPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
			return ((InternalEList<?>)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
		case JbiPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
			return ((InternalEList<?>)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
		case JbiPackage.DOCUMENT_ROOT__BOOTSTRAP_CLASS_PATH:
			return basicSetBootstrapClassPath(null, msgs);
		case JbiPackage.DOCUMENT_ROOT__COMPONENT:
			return basicSetComponent(null, msgs);
		case JbiPackage.DOCUMENT_ROOT__COMPONENT_CLASS_NAME:
			return basicSetComponentClassName(null, msgs);
		case JbiPackage.DOCUMENT_ROOT__COMPONENT_CLASS_PATH:
			return basicSetComponentClassPath(null, msgs);
		case JbiPackage.DOCUMENT_ROOT__CONNECTION:
			return basicSetConnection(null, msgs);
		case JbiPackage.DOCUMENT_ROOT__CONNECTIONS:
			return basicSetConnections(null, msgs);
		case JbiPackage.DOCUMENT_ROOT__CONSUMER:
			return basicSetConsumer(null, msgs);
		case JbiPackage.DOCUMENT_ROOT__CONSUMES:
			return basicSetConsumes(null, msgs);
		case JbiPackage.DOCUMENT_ROOT__IDENTIFICATION:
			return basicSetIdentification(null, msgs);
		case JbiPackage.DOCUMENT_ROOT__JBI:
			return basicSetJbi(null, msgs);
		case JbiPackage.DOCUMENT_ROOT__PROVIDER:
			return basicSetProvider(null, msgs);
		case JbiPackage.DOCUMENT_ROOT__PROVIDES:
			return basicSetProvides(null, msgs);
		case JbiPackage.DOCUMENT_ROOT__SERVICE_ASSEMBLY:
			return basicSetServiceAssembly(null, msgs);
		case JbiPackage.DOCUMENT_ROOT__SERVICES:
			return basicSetServices(null, msgs);
		case JbiPackage.DOCUMENT_ROOT__SERVICE_UNIT:
			return basicSetServiceUnit(null, msgs);
		case JbiPackage.DOCUMENT_ROOT__SHARED_LIBRARY_CLASS_PATH:
			return basicSetSharedLibraryClassPath(null, msgs);
		case JbiPackage.DOCUMENT_ROOT__TARGET:
			return basicSetTarget(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case JbiPackage.DOCUMENT_ROOT__MIXED:
			if (coreType) return getMixed();
			return ((FeatureMap.Internal)getMixed()).getWrapper();
		case JbiPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
			if (coreType) return getXMLNSPrefixMap();
			else return getXMLNSPrefixMap().map();
		case JbiPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
			if (coreType) return getXSISchemaLocation();
			else return getXSISchemaLocation().map();
		case JbiPackage.DOCUMENT_ROOT__ARTIFACTS_ZIP:
			return getArtifactsZip();
		case JbiPackage.DOCUMENT_ROOT__BOOTSTRAP_CLASS_NAME:
			return getBootstrapClassName();
		case JbiPackage.DOCUMENT_ROOT__BOOTSTRAP_CLASS_PATH:
			return getBootstrapClassPath();
		case JbiPackage.DOCUMENT_ROOT__COMPONENT:
			return getComponent();
		case JbiPackage.DOCUMENT_ROOT__COMPONENT_CLASS_NAME:
			return getComponentClassName();
		case JbiPackage.DOCUMENT_ROOT__COMPONENT_CLASS_PATH:
			return getComponentClassPath();
		case JbiPackage.DOCUMENT_ROOT__COMPONENT_NAME:
			return getComponentName();
		case JbiPackage.DOCUMENT_ROOT__CONNECTION:
			return getConnection();
		case JbiPackage.DOCUMENT_ROOT__CONNECTIONS:
			return getConnections();
		case JbiPackage.DOCUMENT_ROOT__CONSUMER:
			return getConsumer();
		case JbiPackage.DOCUMENT_ROOT__CONSUMES:
			return getConsumes();
		case JbiPackage.DOCUMENT_ROOT__DESCRIPTION:
			return getDescription();
		case JbiPackage.DOCUMENT_ROOT__IDENTIFICATION:
			return getIdentification();
		case JbiPackage.DOCUMENT_ROOT__JBI:
			return getJbi();
		case JbiPackage.DOCUMENT_ROOT__NAME:
			return getName();
		case JbiPackage.DOCUMENT_ROOT__PATH_ELEMENT:
			return getPathElement();
		case JbiPackage.DOCUMENT_ROOT__PROVIDER:
			return getProvider();
		case JbiPackage.DOCUMENT_ROOT__PROVIDES:
			return getProvides();
		case JbiPackage.DOCUMENT_ROOT__SERVICE_ASSEMBLY:
			return getServiceAssembly();
		case JbiPackage.DOCUMENT_ROOT__SERVICES:
			return getServices();
		case JbiPackage.DOCUMENT_ROOT__SERVICE_UNIT:
			return getServiceUnit();
		case JbiPackage.DOCUMENT_ROOT__SHARED_LIBRARY_CLASS_PATH:
			return getSharedLibraryClassPath();
		case JbiPackage.DOCUMENT_ROOT__TARGET:
			return getTarget();
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
		case JbiPackage.DOCUMENT_ROOT__MIXED:
			((FeatureMap.Internal)getMixed()).set(newValue);
			return;
		case JbiPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
			((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
			return;
		case JbiPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
			((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
			return;
		case JbiPackage.DOCUMENT_ROOT__ARTIFACTS_ZIP:
			setArtifactsZip((String)newValue);
			return;
		case JbiPackage.DOCUMENT_ROOT__BOOTSTRAP_CLASS_NAME:
			setBootstrapClassName((String)newValue);
			return;
		case JbiPackage.DOCUMENT_ROOT__BOOTSTRAP_CLASS_PATH:
			setBootstrapClassPath((ClassPath)newValue);
			return;
		case JbiPackage.DOCUMENT_ROOT__COMPONENT:
			setComponent((Component)newValue);
			return;
		case JbiPackage.DOCUMENT_ROOT__COMPONENT_CLASS_NAME:
			setComponentClassName((ComponentClassName)newValue);
			return;
		case JbiPackage.DOCUMENT_ROOT__COMPONENT_CLASS_PATH:
			setComponentClassPath((ClassPath)newValue);
			return;
		case JbiPackage.DOCUMENT_ROOT__COMPONENT_NAME:
			setComponentName((String)newValue);
			return;
		case JbiPackage.DOCUMENT_ROOT__CONNECTION:
			setConnection((Connection)newValue);
			return;
		case JbiPackage.DOCUMENT_ROOT__CONNECTIONS:
			setConnections((Connections)newValue);
			return;
		case JbiPackage.DOCUMENT_ROOT__CONSUMER:
			setConsumer((Consumer)newValue);
			return;
		case JbiPackage.DOCUMENT_ROOT__CONSUMES:
			setConsumes((Consumes)newValue);
			return;
		case JbiPackage.DOCUMENT_ROOT__DESCRIPTION:
			setDescription((String)newValue);
			return;
		case JbiPackage.DOCUMENT_ROOT__IDENTIFICATION:
			setIdentification((Identification)newValue);
			return;
		case JbiPackage.DOCUMENT_ROOT__JBI:
			setJbi((Jbi)newValue);
			return;
		case JbiPackage.DOCUMENT_ROOT__NAME:
			setName((String)newValue);
			return;
		case JbiPackage.DOCUMENT_ROOT__PATH_ELEMENT:
			setPathElement((String)newValue);
			return;
		case JbiPackage.DOCUMENT_ROOT__PROVIDER:
			setProvider((Provider)newValue);
			return;
		case JbiPackage.DOCUMENT_ROOT__PROVIDES:
			setProvides((Provides)newValue);
			return;
		case JbiPackage.DOCUMENT_ROOT__SERVICE_ASSEMBLY:
			setServiceAssembly((ServiceAssembly)newValue);
			return;
		case JbiPackage.DOCUMENT_ROOT__SERVICES:
			setServices((Services)newValue);
			return;
		case JbiPackage.DOCUMENT_ROOT__SERVICE_UNIT:
			setServiceUnit((ServiceUnit)newValue);
			return;
		case JbiPackage.DOCUMENT_ROOT__SHARED_LIBRARY_CLASS_PATH:
			setSharedLibraryClassPath((ClassPath)newValue);
			return;
		case JbiPackage.DOCUMENT_ROOT__TARGET:
			setTarget((Target)newValue);
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
		case JbiPackage.DOCUMENT_ROOT__MIXED:
			getMixed().clear();
			return;
		case JbiPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
			getXMLNSPrefixMap().clear();
			return;
		case JbiPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
			getXSISchemaLocation().clear();
			return;
		case JbiPackage.DOCUMENT_ROOT__ARTIFACTS_ZIP:
			setArtifactsZip(ARTIFACTS_ZIP_EDEFAULT);
			return;
		case JbiPackage.DOCUMENT_ROOT__BOOTSTRAP_CLASS_NAME:
			setBootstrapClassName(BOOTSTRAP_CLASS_NAME_EDEFAULT);
			return;
		case JbiPackage.DOCUMENT_ROOT__BOOTSTRAP_CLASS_PATH:
			setBootstrapClassPath((ClassPath)null);
			return;
		case JbiPackage.DOCUMENT_ROOT__COMPONENT:
			setComponent((Component)null);
			return;
		case JbiPackage.DOCUMENT_ROOT__COMPONENT_CLASS_NAME:
			setComponentClassName((ComponentClassName)null);
			return;
		case JbiPackage.DOCUMENT_ROOT__COMPONENT_CLASS_PATH:
			setComponentClassPath((ClassPath)null);
			return;
		case JbiPackage.DOCUMENT_ROOT__COMPONENT_NAME:
			setComponentName(COMPONENT_NAME_EDEFAULT);
			return;
		case JbiPackage.DOCUMENT_ROOT__CONNECTION:
			setConnection((Connection)null);
			return;
		case JbiPackage.DOCUMENT_ROOT__CONNECTIONS:
			setConnections((Connections)null);
			return;
		case JbiPackage.DOCUMENT_ROOT__CONSUMER:
			setConsumer((Consumer)null);
			return;
		case JbiPackage.DOCUMENT_ROOT__CONSUMES:
			setConsumes((Consumes)null);
			return;
		case JbiPackage.DOCUMENT_ROOT__DESCRIPTION:
			setDescription(DESCRIPTION_EDEFAULT);
			return;
		case JbiPackage.DOCUMENT_ROOT__IDENTIFICATION:
			setIdentification((Identification)null);
			return;
		case JbiPackage.DOCUMENT_ROOT__JBI:
			setJbi((Jbi)null);
			return;
		case JbiPackage.DOCUMENT_ROOT__NAME:
			setName(NAME_EDEFAULT);
			return;
		case JbiPackage.DOCUMENT_ROOT__PATH_ELEMENT:
			setPathElement(PATH_ELEMENT_EDEFAULT);
			return;
		case JbiPackage.DOCUMENT_ROOT__PROVIDER:
			setProvider((Provider)null);
			return;
		case JbiPackage.DOCUMENT_ROOT__PROVIDES:
			setProvides((Provides)null);
			return;
		case JbiPackage.DOCUMENT_ROOT__SERVICE_ASSEMBLY:
			setServiceAssembly((ServiceAssembly)null);
			return;
		case JbiPackage.DOCUMENT_ROOT__SERVICES:
			setServices((Services)null);
			return;
		case JbiPackage.DOCUMENT_ROOT__SERVICE_UNIT:
			setServiceUnit((ServiceUnit)null);
			return;
		case JbiPackage.DOCUMENT_ROOT__SHARED_LIBRARY_CLASS_PATH:
			setSharedLibraryClassPath((ClassPath)null);
			return;
		case JbiPackage.DOCUMENT_ROOT__TARGET:
			setTarget((Target)null);
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
		case JbiPackage.DOCUMENT_ROOT__MIXED:
			return this.mixed != null && !this.mixed.isEmpty();
		case JbiPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
			return this.xMLNSPrefixMap != null && !this.xMLNSPrefixMap.isEmpty();
		case JbiPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
			return this.xSISchemaLocation != null && !this.xSISchemaLocation.isEmpty();
		case JbiPackage.DOCUMENT_ROOT__ARTIFACTS_ZIP:
			return ARTIFACTS_ZIP_EDEFAULT == null ? getArtifactsZip() != null : !ARTIFACTS_ZIP_EDEFAULT.equals(getArtifactsZip());
		case JbiPackage.DOCUMENT_ROOT__BOOTSTRAP_CLASS_NAME:
			return BOOTSTRAP_CLASS_NAME_EDEFAULT == null ? getBootstrapClassName() != null : !BOOTSTRAP_CLASS_NAME_EDEFAULT.equals(getBootstrapClassName());
		case JbiPackage.DOCUMENT_ROOT__BOOTSTRAP_CLASS_PATH:
			return getBootstrapClassPath() != null;
		case JbiPackage.DOCUMENT_ROOT__COMPONENT:
			return getComponent() != null;
		case JbiPackage.DOCUMENT_ROOT__COMPONENT_CLASS_NAME:
			return getComponentClassName() != null;
		case JbiPackage.DOCUMENT_ROOT__COMPONENT_CLASS_PATH:
			return getComponentClassPath() != null;
		case JbiPackage.DOCUMENT_ROOT__COMPONENT_NAME:
			return COMPONENT_NAME_EDEFAULT == null ? getComponentName() != null : !COMPONENT_NAME_EDEFAULT.equals(getComponentName());
		case JbiPackage.DOCUMENT_ROOT__CONNECTION:
			return getConnection() != null;
		case JbiPackage.DOCUMENT_ROOT__CONNECTIONS:
			return getConnections() != null;
		case JbiPackage.DOCUMENT_ROOT__CONSUMER:
			return getConsumer() != null;
		case JbiPackage.DOCUMENT_ROOT__CONSUMES:
			return getConsumes() != null;
		case JbiPackage.DOCUMENT_ROOT__DESCRIPTION:
			return DESCRIPTION_EDEFAULT == null ? getDescription() != null : !DESCRIPTION_EDEFAULT.equals(getDescription());
		case JbiPackage.DOCUMENT_ROOT__IDENTIFICATION:
			return getIdentification() != null;
		case JbiPackage.DOCUMENT_ROOT__JBI:
			return getJbi() != null;
		case JbiPackage.DOCUMENT_ROOT__NAME:
			return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
		case JbiPackage.DOCUMENT_ROOT__PATH_ELEMENT:
			return PATH_ELEMENT_EDEFAULT == null ? getPathElement() != null : !PATH_ELEMENT_EDEFAULT.equals(getPathElement());
		case JbiPackage.DOCUMENT_ROOT__PROVIDER:
			return getProvider() != null;
		case JbiPackage.DOCUMENT_ROOT__PROVIDES:
			return getProvides() != null;
		case JbiPackage.DOCUMENT_ROOT__SERVICE_ASSEMBLY:
			return getServiceAssembly() != null;
		case JbiPackage.DOCUMENT_ROOT__SERVICES:
			return getServices() != null;
		case JbiPackage.DOCUMENT_ROOT__SERVICE_UNIT:
			return getServiceUnit() != null;
		case JbiPackage.DOCUMENT_ROOT__SHARED_LIBRARY_CLASS_PATH:
			return getSharedLibraryClassPath() != null;
		case JbiPackage.DOCUMENT_ROOT__TARGET:
			return getTarget() != null;
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
		result.append(" (mixed: ");
		result.append(this.mixed);
		result.append(')');
		return result.toString();
	}

} //DocumentRootImpl
