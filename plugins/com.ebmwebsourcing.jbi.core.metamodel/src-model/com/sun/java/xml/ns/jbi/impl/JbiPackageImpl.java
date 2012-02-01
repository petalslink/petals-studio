/**
 *  Copyright (c) 2009-2012, EBM WebSourcing
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

import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.AbstractExtensibleElement;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import com.sun.java.xml.ns.jbi.ClassLoaderDelegationType;
import com.sun.java.xml.ns.jbi.ClassPath;
import com.sun.java.xml.ns.jbi.Component;
import com.sun.java.xml.ns.jbi.ComponentClassName;
import com.sun.java.xml.ns.jbi.ComponentType;
import com.sun.java.xml.ns.jbi.Connection;
import com.sun.java.xml.ns.jbi.Connections;
import com.sun.java.xml.ns.jbi.Consumer;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.DocumentRoot;
import com.sun.java.xml.ns.jbi.Identification;
import com.sun.java.xml.ns.jbi.Jbi;
import com.sun.java.xml.ns.jbi.JbiFactory;
import com.sun.java.xml.ns.jbi.JbiPackage;
import com.sun.java.xml.ns.jbi.LinkType;
import com.sun.java.xml.ns.jbi.Provider;
import com.sun.java.xml.ns.jbi.Provides;
import com.sun.java.xml.ns.jbi.ServiceAssembly;
import com.sun.java.xml.ns.jbi.ServiceUnit;
import com.sun.java.xml.ns.jbi.Services;
import com.sun.java.xml.ns.jbi.SharedLibraryType;
import com.sun.java.xml.ns.jbi.SharedLibraryType1;
import com.sun.java.xml.ns.jbi.Target;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class JbiPackageImpl extends EPackageImpl implements JbiPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractExtensibleElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classPathEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentClassNameEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectionsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass consumerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass consumesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentRootEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass identificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass jbiEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass providerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass providesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass serviceAssemblyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass servicesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractEndpointEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass serviceUnitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sharedLibraryTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sharedLibraryType1EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass targetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum classLoaderDelegationTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum componentTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum linkTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType classLoaderDelegationTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType componentTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType linkTypeObjectEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private JbiPackageImpl() {
		super(eNS_URI, JbiFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link JbiPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static JbiPackage init() {
		if (isInited) return (JbiPackage)EPackage.Registry.INSTANCE.getEPackage(JbiPackage.eNS_URI);

		// Obtain or create and register package
		JbiPackageImpl theJbiPackage = (JbiPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof JbiPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new JbiPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theJbiPackage.createPackageContents();

		// Initialize created meta-data
		theJbiPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theJbiPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(JbiPackage.eNS_URI, theJbiPackage);
		return theJbiPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractExtensibleElement() {
		return abstractExtensibleElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractExtensibleElement_Group() {
		return (EAttribute)abstractExtensibleElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractExtensibleElement_Other() {
		return (EAttribute)abstractExtensibleElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractExtensibleElement_Local() {
		return (EAttribute)abstractExtensibleElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassPath() {
		return classPathEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClassPath_PathElement() {
		return (EAttribute)classPathEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComponent() {
		return componentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_Identification() {
		return (EReference)componentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_ComponentClassName() {
		return (EReference)componentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_ComponentClassPath() {
		return (EReference)componentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_BootstrapClassName() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_BootstrapClassPath() {
		return (EReference)componentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_SharedLibraryList() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_SharedLibrary() {
		return (EReference)componentEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_BootstrapClassLoaderDelegation() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_ComponentClassLoaderDelegation() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_Type() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComponentClassName() {
		return componentClassNameEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponentClassName_Mixed() {
		return (EAttribute)componentClassNameEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponentClassName_Description() {
		return (EAttribute)componentClassNameEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConnection() {
		return connectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnection_Consumer() {
		return (EReference)connectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnection_Provider() {
		return (EReference)connectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConnections() {
		return connectionsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnections_Connection() {
		return (EReference)connectionsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConsumer() {
		return consumerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConsumer_EndpointName() {
		return (EAttribute)consumerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConsumer_InterfaceName() {
		return (EAttribute)consumerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConsumer_ServiceName() {
		return (EAttribute)consumerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConsumes() {
		return consumesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDocumentRoot() {
		return documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Mixed() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XMLNSPrefixMap() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XSISchemaLocation() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Jbi() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIdentification() {
		return identificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIdentification_Name() {
		return (EAttribute)identificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIdentification_Description() {
		return (EAttribute)identificationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJbi() {
		return jbiEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJbi_Component() {
		return (EReference)jbiEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJbi_SharedLibrary() {
		return (EReference)jbiEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJbi_ServiceAssembly() {
		return (EReference)jbiEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJbi_Services() {
		return (EReference)jbiEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJbi_Version() {
		return (EAttribute)jbiEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProvider() {
		return providerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProvider_EndpointName() {
		return (EAttribute)providerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProvider_ServiceName() {
		return (EAttribute)providerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProvides() {
		return providesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getServiceAssembly() {
		return serviceAssemblyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceAssembly_Identification() {
		return (EReference)serviceAssemblyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceAssembly_ServiceUnit() {
		return (EReference)serviceAssemblyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceAssembly_Connections() {
		return (EReference)serviceAssemblyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getServices() {
		return servicesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServices_Provides() {
		return (EReference)servicesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServices_Consumes() {
		return (EReference)servicesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServices_BindingComponent() {
		return (EAttribute)servicesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractEndpoint() {
		return abstractEndpointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractEndpoint_EndpointName() {
		return (EAttribute)abstractEndpointEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractEndpoint_InterfaceName() {
		return (EAttribute)abstractEndpointEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractEndpoint_ServiceName() {
		return (EAttribute)abstractEndpointEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getServiceUnit() {
		return serviceUnitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceUnit_Identification() {
		return (EReference)serviceUnitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceUnit_Target() {
		return (EReference)serviceUnitEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSharedLibraryType() {
		return sharedLibraryTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSharedLibraryType_Identification() {
		return (EReference)sharedLibraryTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSharedLibraryType_SharedLibraryClassPath() {
		return (EReference)sharedLibraryTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSharedLibraryType_ClassLoaderDelegation() {
		return (EAttribute)sharedLibraryTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSharedLibraryType_Version() {
		return (EAttribute)sharedLibraryTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSharedLibraryType1() {
		return sharedLibraryType1EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSharedLibraryType1_Mixed() {
		return (EAttribute)sharedLibraryType1EClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSharedLibraryType1_Version() {
		return (EAttribute)sharedLibraryType1EClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTarget() {
		return targetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTarget_ArtifactsZip() {
		return (EAttribute)targetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTarget_ComponentName() {
		return (EAttribute)targetEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getClassLoaderDelegationType() {
		return classLoaderDelegationTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getComponentType() {
		return componentTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getLinkType() {
		return linkTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getClassLoaderDelegationTypeObject() {
		return classLoaderDelegationTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getComponentTypeObject() {
		return componentTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getLinkTypeObject() {
		return linkTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JbiFactory getJbiFactory() {
		return (JbiFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		abstractExtensibleElementEClass = createEClass(ABSTRACT_EXTENSIBLE_ELEMENT);
		createEAttribute(abstractExtensibleElementEClass, ABSTRACT_EXTENSIBLE_ELEMENT__GROUP);
		createEAttribute(abstractExtensibleElementEClass, ABSTRACT_EXTENSIBLE_ELEMENT__OTHER);
		createEAttribute(abstractExtensibleElementEClass, ABSTRACT_EXTENSIBLE_ELEMENT__LOCAL);

		classPathEClass = createEClass(CLASS_PATH);
		createEAttribute(classPathEClass, CLASS_PATH__PATH_ELEMENT);

		componentEClass = createEClass(COMPONENT);
		createEReference(componentEClass, COMPONENT__IDENTIFICATION);
		createEReference(componentEClass, COMPONENT__COMPONENT_CLASS_NAME);
		createEReference(componentEClass, COMPONENT__COMPONENT_CLASS_PATH);
		createEAttribute(componentEClass, COMPONENT__BOOTSTRAP_CLASS_NAME);
		createEReference(componentEClass, COMPONENT__BOOTSTRAP_CLASS_PATH);
		createEAttribute(componentEClass, COMPONENT__SHARED_LIBRARY_LIST);
		createEReference(componentEClass, COMPONENT__SHARED_LIBRARY);
		createEAttribute(componentEClass, COMPONENT__BOOTSTRAP_CLASS_LOADER_DELEGATION);
		createEAttribute(componentEClass, COMPONENT__COMPONENT_CLASS_LOADER_DELEGATION);
		createEAttribute(componentEClass, COMPONENT__TYPE);

		componentClassNameEClass = createEClass(COMPONENT_CLASS_NAME);
		createEAttribute(componentClassNameEClass, COMPONENT_CLASS_NAME__MIXED);
		createEAttribute(componentClassNameEClass, COMPONENT_CLASS_NAME__DESCRIPTION);

		connectionEClass = createEClass(CONNECTION);
		createEReference(connectionEClass, CONNECTION__CONSUMER);
		createEReference(connectionEClass, CONNECTION__PROVIDER);

		connectionsEClass = createEClass(CONNECTIONS);
		createEReference(connectionsEClass, CONNECTIONS__CONNECTION);

		consumerEClass = createEClass(CONSUMER);
		createEAttribute(consumerEClass, CONSUMER__ENDPOINT_NAME);
		createEAttribute(consumerEClass, CONSUMER__INTERFACE_NAME);
		createEAttribute(consumerEClass, CONSUMER__SERVICE_NAME);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__JBI);

		identificationEClass = createEClass(IDENTIFICATION);
		createEAttribute(identificationEClass, IDENTIFICATION__NAME);
		createEAttribute(identificationEClass, IDENTIFICATION__DESCRIPTION);

		jbiEClass = createEClass(JBI);
		createEReference(jbiEClass, JBI__COMPONENT);
		createEReference(jbiEClass, JBI__SHARED_LIBRARY);
		createEReference(jbiEClass, JBI__SERVICE_ASSEMBLY);
		createEReference(jbiEClass, JBI__SERVICES);
		createEAttribute(jbiEClass, JBI__VERSION);

		servicesEClass = createEClass(SERVICES);
		createEReference(servicesEClass, SERVICES__PROVIDES);
		createEReference(servicesEClass, SERVICES__CONSUMES);
		createEAttribute(servicesEClass, SERVICES__BINDING_COMPONENT);

		abstractEndpointEClass = createEClass(ABSTRACT_ENDPOINT);
		createEAttribute(abstractEndpointEClass, ABSTRACT_ENDPOINT__ENDPOINT_NAME);
		createEAttribute(abstractEndpointEClass, ABSTRACT_ENDPOINT__INTERFACE_NAME);
		createEAttribute(abstractEndpointEClass, ABSTRACT_ENDPOINT__SERVICE_NAME);

		consumesEClass = createEClass(CONSUMES);

		providesEClass = createEClass(PROVIDES);

		providerEClass = createEClass(PROVIDER);
		createEAttribute(providerEClass, PROVIDER__ENDPOINT_NAME);
		createEAttribute(providerEClass, PROVIDER__SERVICE_NAME);

		serviceAssemblyEClass = createEClass(SERVICE_ASSEMBLY);
		createEReference(serviceAssemblyEClass, SERVICE_ASSEMBLY__IDENTIFICATION);
		createEReference(serviceAssemblyEClass, SERVICE_ASSEMBLY__SERVICE_UNIT);
		createEReference(serviceAssemblyEClass, SERVICE_ASSEMBLY__CONNECTIONS);

		serviceUnitEClass = createEClass(SERVICE_UNIT);
		createEReference(serviceUnitEClass, SERVICE_UNIT__IDENTIFICATION);
		createEReference(serviceUnitEClass, SERVICE_UNIT__TARGET);

		sharedLibraryTypeEClass = createEClass(SHARED_LIBRARY_TYPE);
		createEReference(sharedLibraryTypeEClass, SHARED_LIBRARY_TYPE__IDENTIFICATION);
		createEReference(sharedLibraryTypeEClass, SHARED_LIBRARY_TYPE__SHARED_LIBRARY_CLASS_PATH);
		createEAttribute(sharedLibraryTypeEClass, SHARED_LIBRARY_TYPE__CLASS_LOADER_DELEGATION);
		createEAttribute(sharedLibraryTypeEClass, SHARED_LIBRARY_TYPE__VERSION);

		sharedLibraryType1EClass = createEClass(SHARED_LIBRARY_TYPE1);
		createEAttribute(sharedLibraryType1EClass, SHARED_LIBRARY_TYPE1__MIXED);
		createEAttribute(sharedLibraryType1EClass, SHARED_LIBRARY_TYPE1__VERSION);

		targetEClass = createEClass(TARGET);
		createEAttribute(targetEClass, TARGET__ARTIFACTS_ZIP);
		createEAttribute(targetEClass, TARGET__COMPONENT_NAME);

		// Create enums
		classLoaderDelegationTypeEEnum = createEEnum(CLASS_LOADER_DELEGATION_TYPE);
		componentTypeEEnum = createEEnum(COMPONENT_TYPE);
		linkTypeEEnum = createEEnum(LINK_TYPE);

		// Create data types
		classLoaderDelegationTypeObjectEDataType = createEDataType(CLASS_LOADER_DELEGATION_TYPE_OBJECT);
		componentTypeObjectEDataType = createEDataType(COMPONENT_TYPE_OBJECT);
		linkTypeObjectEDataType = createEDataType(LINK_TYPE_OBJECT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		componentEClass.getESuperTypes().add(this.getAbstractExtensibleElement());
		connectionsEClass.getESuperTypes().add(this.getAbstractExtensibleElement());
		identificationEClass.getESuperTypes().add(this.getAbstractExtensibleElement());
		servicesEClass.getESuperTypes().add(this.getAbstractExtensibleElement());
		abstractEndpointEClass.getESuperTypes().add(this.getAbstractExtensibleElement());
		consumesEClass.getESuperTypes().add(this.getAbstractEndpoint());
		providesEClass.getESuperTypes().add(this.getAbstractEndpoint());
		serviceAssemblyEClass.getESuperTypes().add(this.getAbstractExtensibleElement());
		serviceUnitEClass.getESuperTypes().add(this.getAbstractExtensibleElement());

		// Initialize classes and features; add operations and parameters
		initEClass(abstractExtensibleElementEClass, AbstractExtensibleElement.class, "AbstractExtensibleElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAbstractExtensibleElement_Group(), theEcorePackage.getEFeatureMapEntry(), "group", null, 0, -1, AbstractExtensibleElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractExtensibleElement_Other(), theEcorePackage.getEFeatureMapEntry(), "other", null, 0, -1, AbstractExtensibleElement.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractExtensibleElement_Local(), theEcorePackage.getEFeatureMapEntry(), "local", null, 0, -1, AbstractExtensibleElement.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(classPathEClass, ClassPath.class, "ClassPath", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getClassPath_PathElement(), theXMLTypePackage.getString(), "pathElement", null, 1, 1, ClassPath.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(componentEClass, Component.class, "Component", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComponent_Identification(), this.getIdentification(), null, "identification", null, 1, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_ComponentClassName(), this.getComponentClassName(), null, "componentClassName", null, 1, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_ComponentClassPath(), this.getClassPath(), null, "componentClassPath", null, 1, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_BootstrapClassName(), theXMLTypePackage.getString(), "bootstrapClassName", null, 1, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_BootstrapClassPath(), this.getClassPath(), null, "bootstrapClassPath", null, 1, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_SharedLibraryList(), theEcorePackage.getEFeatureMapEntry(), "sharedLibraryList", null, 0, -1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_SharedLibrary(), this.getSharedLibraryType1(), null, "sharedLibrary", null, 0, -1, Component.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_BootstrapClassLoaderDelegation(), this.getClassLoaderDelegationType(), "bootstrapClassLoaderDelegation", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_ComponentClassLoaderDelegation(), this.getClassLoaderDelegationType(), "componentClassLoaderDelegation", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_Type(), this.getComponentType(), "type", null, 1, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(componentClassNameEClass, ComponentClassName.class, "ComponentClassName", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getComponentClassName_Mixed(), theEcorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, ComponentClassName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentClassName_Description(), theXMLTypePackage.getAnySimpleType(), "description", null, 0, 1, ComponentClassName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(connectionEClass, Connection.class, "Connection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConnection_Consumer(), this.getConsumer(), null, "consumer", null, 1, 1, Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConnection_Provider(), this.getProvider(), null, "provider", null, 1, 1, Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(connectionsEClass, Connections.class, "Connections", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConnections_Connection(), this.getConnection(), null, "connection", null, 0, -1, Connections.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(consumerEClass, Consumer.class, "Consumer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConsumer_EndpointName(), theXMLTypePackage.getString(), "endpointName", null, 0, 1, Consumer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConsumer_InterfaceName(), theXMLTypePackage.getQName(), "interfaceName", null, 0, 1, Consumer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConsumer_ServiceName(), theXMLTypePackage.getQName(), "serviceName", null, 0, 1, Consumer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), theEcorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), theEcorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), theEcorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Jbi(), this.getJbi(), null, "jbi", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(identificationEClass, Identification.class, "Identification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIdentification_Name(), theXMLTypePackage.getNCName(), "name", null, 1, 1, Identification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIdentification_Description(), theXMLTypePackage.getString(), "description", null, 1, 1, Identification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(jbiEClass, Jbi.class, "Jbi", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getJbi_Component(), this.getComponent(), null, "component", null, 0, 1, Jbi.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJbi_SharedLibrary(), this.getSharedLibraryType(), null, "sharedLibrary", null, 0, 1, Jbi.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJbi_ServiceAssembly(), this.getServiceAssembly(), null, "serviceAssembly", null, 0, 1, Jbi.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJbi_Services(), this.getServices(), null, "services", null, 0, 1, Jbi.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJbi_Version(), theXMLTypePackage.getDecimal(), "version", null, 1, 1, Jbi.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(servicesEClass, Services.class, "Services", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getServices_Provides(), this.getProvides(), null, "provides", null, 0, -1, Services.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getServices_Consumes(), this.getConsumes(), null, "consumes", null, 0, -1, Services.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getServices_BindingComponent(), theXMLTypePackage.getBoolean(), "bindingComponent", null, 1, 1, Services.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractEndpointEClass, AbstractEndpoint.class, "AbstractEndpoint", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAbstractEndpoint_EndpointName(), theXMLTypePackage.getString(), "endpointName", null, 1, 1, AbstractEndpoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractEndpoint_InterfaceName(), theXMLTypePackage.getQName(), "interfaceName", null, 1, 1, AbstractEndpoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractEndpoint_ServiceName(), theXMLTypePackage.getQName(), "serviceName", null, 1, 1, AbstractEndpoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(consumesEClass, Consumes.class, "Consumes", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(providesEClass, Provides.class, "Provides", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(providerEClass, Provider.class, "Provider", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProvider_EndpointName(), theXMLTypePackage.getString(), "endpointName", null, 1, 1, Provider.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvider_ServiceName(), theXMLTypePackage.getQName(), "serviceName", null, 1, 1, Provider.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(serviceAssemblyEClass, ServiceAssembly.class, "ServiceAssembly", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getServiceAssembly_Identification(), this.getIdentification(), null, "identification", null, 1, 1, ServiceAssembly.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getServiceAssembly_ServiceUnit(), this.getServiceUnit(), null, "serviceUnit", null, 0, -1, ServiceAssembly.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getServiceAssembly_Connections(), this.getConnections(), null, "connections", null, 0, 1, ServiceAssembly.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(serviceUnitEClass, ServiceUnit.class, "ServiceUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getServiceUnit_Identification(), this.getIdentification(), null, "identification", null, 1, 1, ServiceUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getServiceUnit_Target(), this.getTarget(), null, "target", null, 1, 1, ServiceUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sharedLibraryTypeEClass, SharedLibraryType.class, "SharedLibraryType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSharedLibraryType_Identification(), this.getIdentification(), null, "identification", null, 1, 1, SharedLibraryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSharedLibraryType_SharedLibraryClassPath(), this.getClassPath(), null, "sharedLibraryClassPath", null, 1, 1, SharedLibraryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSharedLibraryType_ClassLoaderDelegation(), this.getClassLoaderDelegationType(), "classLoaderDelegation", null, 0, 1, SharedLibraryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSharedLibraryType_Version(), theXMLTypePackage.getAnySimpleType(), "version", null, 0, 1, SharedLibraryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sharedLibraryType1EClass, SharedLibraryType1.class, "SharedLibraryType1", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSharedLibraryType1_Mixed(), theEcorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, SharedLibraryType1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSharedLibraryType1_Version(), theXMLTypePackage.getAnySimpleType(), "version", null, 0, 1, SharedLibraryType1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(targetEClass, Target.class, "Target", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTarget_ArtifactsZip(), theXMLTypePackage.getString(), "artifactsZip", null, 1, 1, Target.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTarget_ComponentName(), theXMLTypePackage.getNCName(), "componentName", null, 1, 1, Target.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(classLoaderDelegationTypeEEnum, ClassLoaderDelegationType.class, "ClassLoaderDelegationType");
		addEEnumLiteral(classLoaderDelegationTypeEEnum, ClassLoaderDelegationType.PARENT_FIRST);
		addEEnumLiteral(classLoaderDelegationTypeEEnum, ClassLoaderDelegationType.SELF_FIRST);

		initEEnum(componentTypeEEnum, ComponentType.class, "ComponentType");
		addEEnumLiteral(componentTypeEEnum, ComponentType.SERVICE_ENGINE);
		addEEnumLiteral(componentTypeEEnum, ComponentType.BINDING_COMPONENT);

		initEEnum(linkTypeEEnum, LinkType.class, "LinkType");
		addEEnumLiteral(linkTypeEEnum, LinkType.STANDARD);
		addEEnumLiteral(linkTypeEEnum, LinkType.HARD);
		addEEnumLiteral(linkTypeEEnum, LinkType.SOFT);

		// Initialize data types
		initEDataType(classLoaderDelegationTypeObjectEDataType, ClassLoaderDelegationType.class, "ClassLoaderDelegationTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(componentTypeObjectEDataType, ComponentType.class, "ComponentTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(linkTypeObjectEDataType, LinkType.class, "LinkTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
		addAnnotation
		  (getAbstractExtensibleElement_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getAbstractExtensibleElement_Other(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "wildcards", "##other",
			 "name", ":2",
			 "processing", "skip",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getAbstractExtensibleElement_Local(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "wildcards", "##local",
			 "name", ":3",
			 "processing", "skip",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (classPathEClass, 
		   source, 
		   new String[] {
			 "name", "ClassPath",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getClassPath_PathElement(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "path-element",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (componentEClass, 
		   source, 
		   new String[] {
			 "name", "Component",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getComponent_Identification(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "identification",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getComponent_ComponentClassName(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "component-class-name",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getComponent_ComponentClassPath(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "component-class-path",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getComponent_BootstrapClassName(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "bootstrap-class-name",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getComponent_BootstrapClassPath(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "bootstrap-class-path",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getComponent_SharedLibraryList(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "SharedLibraryList:5"
		   });		
		addAnnotation
		  (getComponent_SharedLibrary(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "shared-library",
			 "namespace", "##targetNamespace",
			 "group", "#SharedLibraryList:5"
		   });		
		addAnnotation
		  (getComponent_BootstrapClassLoaderDelegation(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "bootstrap-class-loader-delegation"
		   });		
		addAnnotation
		  (getComponent_ComponentClassLoaderDelegation(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "component-class-loader-delegation"
		   });		
		addAnnotation
		  (getComponent_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });		
		addAnnotation
		  (componentClassNameEClass, 
		   source, 
		   new String[] {
			 "name", "ComponentClassName",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getComponentClassName_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getComponentClassName_Description(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "description"
		   });		
		addAnnotation
		  (connectionEClass, 
		   source, 
		   new String[] {
			 "name", "Connection",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getConnection_Consumer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "consumer",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getConnection_Provider(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "provider",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (connectionsEClass, 
		   source, 
		   new String[] {
			 "name", "Connections",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getConnections_Connection(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "connection",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (consumerEClass, 
		   source, 
		   new String[] {
			 "name", "Consumer",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getConsumer_EndpointName(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "endpoint-name"
		   });		
		addAnnotation
		  (getConsumer_InterfaceName(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "interface-name"
		   });		
		addAnnotation
		  (getConsumer_ServiceName(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "service-name"
		   });		
		addAnnotation
		  (documentRootEClass, 
		   source, 
		   new String[] {
			 "name", "",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_XMLNSPrefixMap(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xmlns:prefix"
		   });		
		addAnnotation
		  (getDocumentRoot_XSISchemaLocation(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xsi:schemaLocation"
		   });		
		addAnnotation
		  (getDocumentRoot_Jbi(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "jbi",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (identificationEClass, 
		   source, 
		   new String[] {
			 "name", "Identification",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getIdentification_Name(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "name",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getIdentification_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "description",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (jbiEClass, 
		   source, 
		   new String[] {
			 "name", "jbi",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getJbi_Component(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "component",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getJbi_SharedLibrary(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "shared-library",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getJbi_ServiceAssembly(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "service-assembly",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getJbi_Services(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "services",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getJbi_Version(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "version"
		   });		
		addAnnotation
		  (servicesEClass, 
		   source, 
		   new String[] {
			 "name", "Services",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getServices_Provides(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "provides",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getServices_Consumes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "consumes",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getServices_BindingComponent(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "binding-component"
		   });		
		addAnnotation
		  (getAbstractEndpoint_EndpointName(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "endpoint-name"
		   });		
		addAnnotation
		  (getAbstractEndpoint_InterfaceName(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "interface-name"
		   });		
		addAnnotation
		  (getAbstractEndpoint_ServiceName(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "service-name"
		   });		
		addAnnotation
		  (consumesEClass, 
		   source, 
		   new String[] {
			 "name", "Consumes",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (providesEClass, 
		   source, 
		   new String[] {
			 "name", "Provides",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (providerEClass, 
		   source, 
		   new String[] {
			 "name", "Provider",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getProvider_EndpointName(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "endpoint-name"
		   });		
		addAnnotation
		  (getProvider_ServiceName(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "service-name"
		   });		
		addAnnotation
		  (serviceAssemblyEClass, 
		   source, 
		   new String[] {
			 "name", "ServiceAssembly",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getServiceAssembly_Identification(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "identification",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getServiceAssembly_ServiceUnit(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "service-unit",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getServiceAssembly_Connections(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "connections",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (serviceUnitEClass, 
		   source, 
		   new String[] {
			 "name", "ServiceUnit",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getServiceUnit_Identification(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "identification",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getServiceUnit_Target(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "target",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (sharedLibraryTypeEClass, 
		   source, 
		   new String[] {
			 "name", "shared-library_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getSharedLibraryType_Identification(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "identification",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getSharedLibraryType_SharedLibraryClassPath(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "shared-library-class-path",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getSharedLibraryType_ClassLoaderDelegation(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "class-loader-delegation"
		   });		
		addAnnotation
		  (getSharedLibraryType_Version(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "version"
		   });		
		addAnnotation
		  (sharedLibraryType1EClass, 
		   source, 
		   new String[] {
			 "name", "shared-library_._1_._type",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getSharedLibraryType1_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getSharedLibraryType1_Version(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "version"
		   });		
		addAnnotation
		  (targetEClass, 
		   source, 
		   new String[] {
			 "name", "Target",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTarget_ArtifactsZip(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "artifacts-zip",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTarget_ComponentName(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "component-name",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (classLoaderDelegationTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "ClassLoaderDelegationType"
		   });		
		addAnnotation
		  (componentTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "ComponentType"
		   });		
		addAnnotation
		  (linkTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "LinkType"
		   });		
		addAnnotation
		  (classLoaderDelegationTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "ClassLoaderDelegationType:Object",
			 "baseType", "ClassLoaderDelegationType"
		   });		
		addAnnotation
		  (componentTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "ComponentType:Object",
			 "baseType", "ComponentType"
		   });		
		addAnnotation
		  (linkTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "LinkType:Object",
			 "baseType", "LinkType"
		   });
	}

} //JbiPackageImpl
