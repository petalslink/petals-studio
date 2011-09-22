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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
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
	 * @return
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
	public EClass getClassPath() {
		return this.classPathEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClassPath_PathElement() {
		return (EAttribute)this.classPathEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComponent() {
		return this.componentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_Identification() {
		return (EReference)this.componentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_ComponentClassName() {
		return (EReference)this.componentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_ComponentClassPath() {
		return (EReference)this.componentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_BootstrapClassName() {
		return (EAttribute)this.componentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_BootstrapClassPath() {
		return (EReference)this.componentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_SharedLibraryList() {
		return (EAttribute)this.componentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_SharedLibrary() {
		return (EReference)this.componentEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_Group() {
		return (EAttribute)this.componentEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_Any() {
		return (EAttribute)this.componentEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_Any1() {
		return (EAttribute)this.componentEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_BootstrapClassLoaderDelegation() {
		return (EAttribute)this.componentEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_ComponentClassLoaderDelegation() {
		return (EAttribute)this.componentEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_Type() {
		return (EAttribute)this.componentEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComponentClassName() {
		return this.componentClassNameEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponentClassName_Mixed() {
		return (EAttribute)this.componentClassNameEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponentClassName_Description() {
		return (EAttribute)this.componentClassNameEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConnection() {
		return this.connectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnection_Consumer() {
		return (EReference)this.connectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnection_Provider() {
		return (EReference)this.connectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConnections() {
		return this.connectionsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnections_Connection() {
		return (EReference)this.connectionsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnections_Group() {
		return (EAttribute)this.connectionsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnections_Any() {
		return (EAttribute)this.connectionsEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnections_Any1() {
		return (EAttribute)this.connectionsEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConsumer() {
		return this.consumerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConsumer_EndpointName() {
		return (EAttribute)this.consumerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConsumer_InterfaceName() {
		return (EAttribute)this.consumerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConsumer_ServiceName() {
		return (EAttribute)this.consumerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConsumes() {
		return this.consumesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConsumes_Group() {
		return (EAttribute)this.consumesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConsumes_Any() {
		return (EAttribute)this.consumesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConsumes_Any1() {
		return (EAttribute)this.consumesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConsumes_EndpointName() {
		return (EAttribute)this.consumesEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConsumes_InterfaceName() {
		return (EAttribute)this.consumesEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConsumes_LinkType() {
		return (EAttribute)this.consumesEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConsumes_ServiceName() {
		return (EAttribute)this.consumesEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDocumentRoot() {
		return this.documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Mixed() {
		return (EAttribute)this.documentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XMLNSPrefixMap() {
		return (EReference)this.documentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XSISchemaLocation() {
		return (EReference)this.documentRootEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_ArtifactsZip() {
		return (EAttribute)this.documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_BootstrapClassName() {
		return (EAttribute)this.documentRootEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_BootstrapClassPath() {
		return (EReference)this.documentRootEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Component() {
		return (EReference)this.documentRootEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ComponentClassName() {
		return (EReference)this.documentRootEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ComponentClassPath() {
		return (EReference)this.documentRootEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_ComponentName() {
		return (EAttribute)this.documentRootEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Connection() {
		return (EReference)this.documentRootEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Connections() {
		return (EReference)this.documentRootEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Consumer() {
		return (EReference)this.documentRootEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Consumes() {
		return (EReference)this.documentRootEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Description() {
		return (EAttribute)this.documentRootEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Identification() {
		return (EReference)this.documentRootEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Jbi() {
		return (EReference)this.documentRootEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Name() {
		return (EAttribute)this.documentRootEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_PathElement() {
		return (EAttribute)this.documentRootEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Provider() {
		return (EReference)this.documentRootEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Provides() {
		return (EReference)this.documentRootEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ServiceAssembly() {
		return (EReference)this.documentRootEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Services() {
		return (EReference)this.documentRootEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ServiceUnit() {
		return (EReference)this.documentRootEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_SharedLibraryClassPath() {
		return (EReference)this.documentRootEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Target() {
		return (EReference)this.documentRootEClass.getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIdentification() {
		return this.identificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIdentification_Name() {
		return (EAttribute)this.identificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIdentification_Description() {
		return (EAttribute)this.identificationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIdentification_Group() {
		return (EAttribute)this.identificationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIdentification_Any() {
		return (EAttribute)this.identificationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIdentification_Any1() {
		return (EAttribute)this.identificationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJbi() {
		return this.jbiEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJbi_Component() {
		return (EReference)this.jbiEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJbi_SharedLibrary() {
		return (EReference)this.jbiEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJbi_ServiceAssembly() {
		return (EReference)this.jbiEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJbi_Services() {
		return (EReference)this.jbiEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJbi_Version() {
		return (EAttribute)this.jbiEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProvider() {
		return this.providerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProvider_EndpointName() {
		return (EAttribute)this.providerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProvider_ServiceName() {
		return (EAttribute)this.providerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProvides() {
		return this.providesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProvides_Group() {
		return (EAttribute)this.providesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProvides_Any() {
		return (EAttribute)this.providesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProvides_Any1() {
		return (EAttribute)this.providesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProvides_EndpointName() {
		return (EAttribute)this.providesEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProvides_InterfaceName() {
		return (EAttribute)this.providesEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProvides_ServiceName() {
		return (EAttribute)this.providesEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getServiceAssembly() {
		return this.serviceAssemblyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceAssembly_Identification() {
		return (EReference)this.serviceAssemblyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceAssembly_ServiceUnit() {
		return (EReference)this.serviceAssemblyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceAssembly_Connections() {
		return (EReference)this.serviceAssemblyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceAssembly_Group() {
		return (EAttribute)this.serviceAssemblyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceAssembly_Any() {
		return (EAttribute)this.serviceAssemblyEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceAssembly_Any1() {
		return (EAttribute)this.serviceAssemblyEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getServices() {
		return this.servicesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServices_Provides() {
		return (EReference)this.servicesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServices_Consumes() {
		return (EReference)this.servicesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServices_Group() {
		return (EAttribute)this.servicesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServices_Any() {
		return (EAttribute)this.servicesEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServices_Any1() {
		return (EAttribute)this.servicesEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServices_BindingComponent() {
		return (EAttribute)this.servicesEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getServiceUnit() {
		return this.serviceUnitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceUnit_Identification() {
		return (EReference)this.serviceUnitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceUnit_Target() {
		return (EReference)this.serviceUnitEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceUnit_Group() {
		return (EAttribute)this.serviceUnitEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceUnit_Any() {
		return (EAttribute)this.serviceUnitEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceUnit_Any1() {
		return (EAttribute)this.serviceUnitEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSharedLibraryType() {
		return this.sharedLibraryTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSharedLibraryType_Identification() {
		return (EReference)this.sharedLibraryTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSharedLibraryType_SharedLibraryClassPath() {
		return (EReference)this.sharedLibraryTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSharedLibraryType_ClassLoaderDelegation() {
		return (EAttribute)this.sharedLibraryTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSharedLibraryType_Version() {
		return (EAttribute)this.sharedLibraryTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSharedLibraryType1() {
		return this.sharedLibraryType1EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSharedLibraryType1_Mixed() {
		return (EAttribute)this.sharedLibraryType1EClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSharedLibraryType1_Version() {
		return (EAttribute)this.sharedLibraryType1EClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTarget() {
		return this.targetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTarget_ArtifactsZip() {
		return (EAttribute)this.targetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTarget_ComponentName() {
		return (EAttribute)this.targetEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getClassLoaderDelegationType() {
		return this.classLoaderDelegationTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getComponentType() {
		return this.componentTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getLinkType() {
		return this.linkTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getClassLoaderDelegationTypeObject() {
		return this.classLoaderDelegationTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getComponentTypeObject() {
		return this.componentTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getLinkTypeObject() {
		return this.linkTypeObjectEDataType;
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
		if (this.isCreated) return;
		this.isCreated = true;

		// Create classes and their features
		this.classPathEClass = createEClass(CLASS_PATH);
		createEAttribute(this.classPathEClass, CLASS_PATH__PATH_ELEMENT);

		this.componentEClass = createEClass(COMPONENT);
		createEReference(this.componentEClass, COMPONENT__IDENTIFICATION);
		createEReference(this.componentEClass, COMPONENT__COMPONENT_CLASS_NAME);
		createEReference(this.componentEClass, COMPONENT__COMPONENT_CLASS_PATH);
		createEAttribute(this.componentEClass, COMPONENT__BOOTSTRAP_CLASS_NAME);
		createEReference(this.componentEClass, COMPONENT__BOOTSTRAP_CLASS_PATH);
		createEAttribute(this.componentEClass, COMPONENT__SHARED_LIBRARY_LIST);
		createEReference(this.componentEClass, COMPONENT__SHARED_LIBRARY);
		createEAttribute(this.componentEClass, COMPONENT__GROUP);
		createEAttribute(this.componentEClass, COMPONENT__ANY);
		createEAttribute(this.componentEClass, COMPONENT__ANY1);
		createEAttribute(this.componentEClass, COMPONENT__BOOTSTRAP_CLASS_LOADER_DELEGATION);
		createEAttribute(this.componentEClass, COMPONENT__COMPONENT_CLASS_LOADER_DELEGATION);
		createEAttribute(this.componentEClass, COMPONENT__TYPE);

		this.componentClassNameEClass = createEClass(COMPONENT_CLASS_NAME);
		createEAttribute(this.componentClassNameEClass, COMPONENT_CLASS_NAME__MIXED);
		createEAttribute(this.componentClassNameEClass, COMPONENT_CLASS_NAME__DESCRIPTION);

		this.connectionEClass = createEClass(CONNECTION);
		createEReference(this.connectionEClass, CONNECTION__CONSUMER);
		createEReference(this.connectionEClass, CONNECTION__PROVIDER);

		this.connectionsEClass = createEClass(CONNECTIONS);
		createEReference(this.connectionsEClass, CONNECTIONS__CONNECTION);
		createEAttribute(this.connectionsEClass, CONNECTIONS__GROUP);
		createEAttribute(this.connectionsEClass, CONNECTIONS__ANY);
		createEAttribute(this.connectionsEClass, CONNECTIONS__ANY1);

		this.consumerEClass = createEClass(CONSUMER);
		createEAttribute(this.consumerEClass, CONSUMER__ENDPOINT_NAME);
		createEAttribute(this.consumerEClass, CONSUMER__INTERFACE_NAME);
		createEAttribute(this.consumerEClass, CONSUMER__SERVICE_NAME);

		this.consumesEClass = createEClass(CONSUMES);
		createEAttribute(this.consumesEClass, CONSUMES__GROUP);
		createEAttribute(this.consumesEClass, CONSUMES__ANY);
		createEAttribute(this.consumesEClass, CONSUMES__ANY1);
		createEAttribute(this.consumesEClass, CONSUMES__ENDPOINT_NAME);
		createEAttribute(this.consumesEClass, CONSUMES__INTERFACE_NAME);
		createEAttribute(this.consumesEClass, CONSUMES__LINK_TYPE);
		createEAttribute(this.consumesEClass, CONSUMES__SERVICE_NAME);

		this.documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(this.documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(this.documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(this.documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEAttribute(this.documentRootEClass, DOCUMENT_ROOT__ARTIFACTS_ZIP);
		createEAttribute(this.documentRootEClass, DOCUMENT_ROOT__BOOTSTRAP_CLASS_NAME);
		createEReference(this.documentRootEClass, DOCUMENT_ROOT__BOOTSTRAP_CLASS_PATH);
		createEReference(this.documentRootEClass, DOCUMENT_ROOT__COMPONENT);
		createEReference(this.documentRootEClass, DOCUMENT_ROOT__COMPONENT_CLASS_NAME);
		createEReference(this.documentRootEClass, DOCUMENT_ROOT__COMPONENT_CLASS_PATH);
		createEAttribute(this.documentRootEClass, DOCUMENT_ROOT__COMPONENT_NAME);
		createEReference(this.documentRootEClass, DOCUMENT_ROOT__CONNECTION);
		createEReference(this.documentRootEClass, DOCUMENT_ROOT__CONNECTIONS);
		createEReference(this.documentRootEClass, DOCUMENT_ROOT__CONSUMER);
		createEReference(this.documentRootEClass, DOCUMENT_ROOT__CONSUMES);
		createEAttribute(this.documentRootEClass, DOCUMENT_ROOT__DESCRIPTION);
		createEReference(this.documentRootEClass, DOCUMENT_ROOT__IDENTIFICATION);
		createEReference(this.documentRootEClass, DOCUMENT_ROOT__JBI);
		createEAttribute(this.documentRootEClass, DOCUMENT_ROOT__NAME);
		createEAttribute(this.documentRootEClass, DOCUMENT_ROOT__PATH_ELEMENT);
		createEReference(this.documentRootEClass, DOCUMENT_ROOT__PROVIDER);
		createEReference(this.documentRootEClass, DOCUMENT_ROOT__PROVIDES);
		createEReference(this.documentRootEClass, DOCUMENT_ROOT__SERVICE_ASSEMBLY);
		createEReference(this.documentRootEClass, DOCUMENT_ROOT__SERVICES);
		createEReference(this.documentRootEClass, DOCUMENT_ROOT__SERVICE_UNIT);
		createEReference(this.documentRootEClass, DOCUMENT_ROOT__SHARED_LIBRARY_CLASS_PATH);
		createEReference(this.documentRootEClass, DOCUMENT_ROOT__TARGET);

		this.identificationEClass = createEClass(IDENTIFICATION);
		createEAttribute(this.identificationEClass, IDENTIFICATION__NAME);
		createEAttribute(this.identificationEClass, IDENTIFICATION__DESCRIPTION);
		createEAttribute(this.identificationEClass, IDENTIFICATION__GROUP);
		createEAttribute(this.identificationEClass, IDENTIFICATION__ANY);
		createEAttribute(this.identificationEClass, IDENTIFICATION__ANY1);

		this.jbiEClass = createEClass(JBI);
		createEReference(this.jbiEClass, JBI__COMPONENT);
		createEReference(this.jbiEClass, JBI__SHARED_LIBRARY);
		createEReference(this.jbiEClass, JBI__SERVICE_ASSEMBLY);
		createEReference(this.jbiEClass, JBI__SERVICES);
		createEAttribute(this.jbiEClass, JBI__VERSION);

		this.providerEClass = createEClass(PROVIDER);
		createEAttribute(this.providerEClass, PROVIDER__ENDPOINT_NAME);
		createEAttribute(this.providerEClass, PROVIDER__SERVICE_NAME);

		this.providesEClass = createEClass(PROVIDES);
		createEAttribute(this.providesEClass, PROVIDES__GROUP);
		createEAttribute(this.providesEClass, PROVIDES__ANY);
		createEAttribute(this.providesEClass, PROVIDES__ANY1);
		createEAttribute(this.providesEClass, PROVIDES__ENDPOINT_NAME);
		createEAttribute(this.providesEClass, PROVIDES__INTERFACE_NAME);
		createEAttribute(this.providesEClass, PROVIDES__SERVICE_NAME);

		this.serviceAssemblyEClass = createEClass(SERVICE_ASSEMBLY);
		createEReference(this.serviceAssemblyEClass, SERVICE_ASSEMBLY__IDENTIFICATION);
		createEReference(this.serviceAssemblyEClass, SERVICE_ASSEMBLY__SERVICE_UNIT);
		createEReference(this.serviceAssemblyEClass, SERVICE_ASSEMBLY__CONNECTIONS);
		createEAttribute(this.serviceAssemblyEClass, SERVICE_ASSEMBLY__GROUP);
		createEAttribute(this.serviceAssemblyEClass, SERVICE_ASSEMBLY__ANY);
		createEAttribute(this.serviceAssemblyEClass, SERVICE_ASSEMBLY__ANY1);

		this.servicesEClass = createEClass(SERVICES);
		createEReference(this.servicesEClass, SERVICES__PROVIDES);
		createEReference(this.servicesEClass, SERVICES__CONSUMES);
		createEAttribute(this.servicesEClass, SERVICES__GROUP);
		createEAttribute(this.servicesEClass, SERVICES__ANY);
		createEAttribute(this.servicesEClass, SERVICES__ANY1);
		createEAttribute(this.servicesEClass, SERVICES__BINDING_COMPONENT);

		this.serviceUnitEClass = createEClass(SERVICE_UNIT);
		createEReference(this.serviceUnitEClass, SERVICE_UNIT__IDENTIFICATION);
		createEReference(this.serviceUnitEClass, SERVICE_UNIT__TARGET);
		createEAttribute(this.serviceUnitEClass, SERVICE_UNIT__GROUP);
		createEAttribute(this.serviceUnitEClass, SERVICE_UNIT__ANY);
		createEAttribute(this.serviceUnitEClass, SERVICE_UNIT__ANY1);

		this.sharedLibraryTypeEClass = createEClass(SHARED_LIBRARY_TYPE);
		createEReference(this.sharedLibraryTypeEClass, SHARED_LIBRARY_TYPE__IDENTIFICATION);
		createEReference(this.sharedLibraryTypeEClass, SHARED_LIBRARY_TYPE__SHARED_LIBRARY_CLASS_PATH);
		createEAttribute(this.sharedLibraryTypeEClass, SHARED_LIBRARY_TYPE__CLASS_LOADER_DELEGATION);
		createEAttribute(this.sharedLibraryTypeEClass, SHARED_LIBRARY_TYPE__VERSION);

		this.sharedLibraryType1EClass = createEClass(SHARED_LIBRARY_TYPE1);
		createEAttribute(this.sharedLibraryType1EClass, SHARED_LIBRARY_TYPE1__MIXED);
		createEAttribute(this.sharedLibraryType1EClass, SHARED_LIBRARY_TYPE1__VERSION);

		this.targetEClass = createEClass(TARGET);
		createEAttribute(this.targetEClass, TARGET__ARTIFACTS_ZIP);
		createEAttribute(this.targetEClass, TARGET__COMPONENT_NAME);

		// Create enums
		this.classLoaderDelegationTypeEEnum = createEEnum(CLASS_LOADER_DELEGATION_TYPE);
		this.componentTypeEEnum = createEEnum(COMPONENT_TYPE);
		this.linkTypeEEnum = createEEnum(LINK_TYPE);

		// Create data types
		this.classLoaderDelegationTypeObjectEDataType = createEDataType(CLASS_LOADER_DELEGATION_TYPE_OBJECT);
		this.componentTypeObjectEDataType = createEDataType(COMPONENT_TYPE_OBJECT);
		this.linkTypeObjectEDataType = createEDataType(LINK_TYPE_OBJECT);
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
		if (this.isInitialized) return;
		this.isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(this.classPathEClass, ClassPath.class, "ClassPath", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getClassPath_PathElement(), theXMLTypePackage.getString(), "pathElement", null, 1, -1, ClassPath.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(this.componentEClass, Component.class, "Component", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComponent_Identification(), this.getIdentification(), null, "identification", null, 1, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_ComponentClassName(), this.getComponentClassName(), null, "componentClassName", null, 1, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_ComponentClassPath(), this.getClassPath(), null, "componentClassPath", null, 1, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_BootstrapClassName(), theXMLTypePackage.getString(), "bootstrapClassName", null, 1, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_BootstrapClassPath(), this.getClassPath(), null, "bootstrapClassPath", null, 1, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_SharedLibraryList(), this.ecorePackage.getEFeatureMapEntry(), "sharedLibraryList", null, 0, -1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_SharedLibrary(), this.getSharedLibraryType1(), null, "sharedLibrary", null, 0, -1, Component.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_Group(), this.ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_Any(), this.ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, Component.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_Any1(), this.ecorePackage.getEFeatureMapEntry(), "any1", null, 0, -1, Component.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_BootstrapClassLoaderDelegation(), this.getClassLoaderDelegationType(), "bootstrapClassLoaderDelegation", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_ComponentClassLoaderDelegation(), this.getClassLoaderDelegationType(), "componentClassLoaderDelegation", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_Type(), this.getComponentType(), "type", null, 1, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(this.componentClassNameEClass, ComponentClassName.class, "ComponentClassName", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getComponentClassName_Mixed(), this.ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, ComponentClassName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentClassName_Description(), theXMLTypePackage.getAnySimpleType(), "description", null, 0, 1, ComponentClassName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(this.connectionEClass, Connection.class, "Connection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConnection_Consumer(), this.getConsumer(), null, "consumer", null, 1, 1, Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConnection_Provider(), this.getProvider(), null, "provider", null, 1, 1, Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(this.connectionsEClass, Connections.class, "Connections", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConnections_Connection(), this.getConnection(), null, "connection", null, 0, -1, Connections.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnections_Group(), this.ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, Connections.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnections_Any(), this.ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, Connections.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnections_Any1(), this.ecorePackage.getEFeatureMapEntry(), "any1", null, 0, -1, Connections.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(this.consumerEClass, Consumer.class, "Consumer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConsumer_EndpointName(), theXMLTypePackage.getString(), "endpointName", null, 0, 1, Consumer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConsumer_InterfaceName(), theXMLTypePackage.getQName(), "interfaceName", null, 0, 1, Consumer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConsumer_ServiceName(), theXMLTypePackage.getQName(), "serviceName", null, 0, 1, Consumer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(this.consumesEClass, Consumes.class, "Consumes", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConsumes_Group(), this.ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, Consumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConsumes_Any(), this.ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, Consumes.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getConsumes_Any1(), this.ecorePackage.getEFeatureMapEntry(), "any1", null, 0, -1, Consumes.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getConsumes_EndpointName(), theXMLTypePackage.getString(), "endpointName", null, 0, 1, Consumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConsumes_InterfaceName(), theXMLTypePackage.getQName(), "interfaceName", null, 1, 1, Consumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConsumes_LinkType(), this.getLinkType(), "linkType", null, 0, 1, Consumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConsumes_ServiceName(), theXMLTypePackage.getQName(), "serviceName", null, 0, 1, Consumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(this.documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), this.ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), this.ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), this.ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_ArtifactsZip(), theXMLTypePackage.getString(), "artifactsZip", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_BootstrapClassName(), theXMLTypePackage.getString(), "bootstrapClassName", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_BootstrapClassPath(), this.getClassPath(), null, "bootstrapClassPath", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Component(), this.getComponent(), null, "component", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ComponentClassName(), this.getComponentClassName(), null, "componentClassName", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ComponentClassPath(), this.getClassPath(), null, "componentClassPath", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_ComponentName(), theXMLTypePackage.getNCName(), "componentName", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Connection(), this.getConnection(), null, "connection", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Connections(), this.getConnections(), null, "connections", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Consumer(), this.getConsumer(), null, "consumer", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Consumes(), this.getConsumes(), null, "consumes", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Description(), theXMLTypePackage.getString(), "description", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Identification(), this.getIdentification(), null, "identification", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Jbi(), this.getJbi(), null, "jbi", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Name(), theXMLTypePackage.getNCName(), "name", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_PathElement(), theXMLTypePackage.getString(), "pathElement", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Provider(), this.getProvider(), null, "provider", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Provides(), this.getProvides(), null, "provides", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ServiceAssembly(), this.getServiceAssembly(), null, "serviceAssembly", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Services(), this.getServices(), null, "services", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ServiceUnit(), this.getServiceUnit(), null, "serviceUnit", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_SharedLibraryClassPath(), this.getClassPath(), null, "sharedLibraryClassPath", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Target(), this.getTarget(), null, "target", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(this.identificationEClass, Identification.class, "Identification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIdentification_Name(), theXMLTypePackage.getNCName(), "name", null, 1, 1, Identification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIdentification_Description(), theXMLTypePackage.getString(), "description", null, 1, 1, Identification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIdentification_Group(), this.ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, Identification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIdentification_Any(), this.ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, Identification.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getIdentification_Any1(), this.ecorePackage.getEFeatureMapEntry(), "any1", null, 0, -1, Identification.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(this.jbiEClass, Jbi.class, "Jbi", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getJbi_Component(), this.getComponent(), null, "component", null, 0, 1, Jbi.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJbi_SharedLibrary(), this.getSharedLibraryType(), null, "sharedLibrary", null, 0, 1, Jbi.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJbi_ServiceAssembly(), this.getServiceAssembly(), null, "serviceAssembly", null, 0, 1, Jbi.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJbi_Services(), this.getServices(), null, "services", null, 0, 1, Jbi.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJbi_Version(), theXMLTypePackage.getDecimal(), "version", null, 1, 1, Jbi.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(this.providerEClass, Provider.class, "Provider", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProvider_EndpointName(), theXMLTypePackage.getString(), "endpointName", null, 1, 1, Provider.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvider_ServiceName(), theXMLTypePackage.getQName(), "serviceName", null, 1, 1, Provider.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(this.providesEClass, Provides.class, "Provides", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProvides_Group(), this.ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, Provides.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvides_Any(), this.ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, Provides.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvides_Any1(), this.ecorePackage.getEFeatureMapEntry(), "any1", null, 0, -1, Provides.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvides_EndpointName(), theXMLTypePackage.getString(), "endpointName", null, 1, 1, Provides.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvides_InterfaceName(), theXMLTypePackage.getQName(), "interfaceName", null, 1, 1, Provides.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvides_ServiceName(), theXMLTypePackage.getQName(), "serviceName", null, 1, 1, Provides.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(this.serviceAssemblyEClass, ServiceAssembly.class, "ServiceAssembly", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getServiceAssembly_Identification(), this.getIdentification(), null, "identification", null, 1, 1, ServiceAssembly.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getServiceAssembly_ServiceUnit(), this.getServiceUnit(), null, "serviceUnit", null, 0, -1, ServiceAssembly.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getServiceAssembly_Connections(), this.getConnections(), null, "connections", null, 0, 1, ServiceAssembly.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getServiceAssembly_Group(), this.ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, ServiceAssembly.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getServiceAssembly_Any(), this.ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ServiceAssembly.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getServiceAssembly_Any1(), this.ecorePackage.getEFeatureMapEntry(), "any1", null, 0, -1, ServiceAssembly.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(this.servicesEClass, Services.class, "Services", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getServices_Provides(), this.getProvides(), null, "provides", null, 0, -1, Services.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getServices_Consumes(), this.getConsumes(), null, "consumes", null, 0, -1, Services.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getServices_Group(), this.ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, Services.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getServices_Any(), this.ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, Services.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getServices_Any1(), this.ecorePackage.getEFeatureMapEntry(), "any1", null, 0, -1, Services.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getServices_BindingComponent(), theXMLTypePackage.getBoolean(), "bindingComponent", null, 1, 1, Services.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(this.serviceUnitEClass, ServiceUnit.class, "ServiceUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getServiceUnit_Identification(), this.getIdentification(), null, "identification", null, 1, 1, ServiceUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getServiceUnit_Target(), this.getTarget(), null, "target", null, 1, 1, ServiceUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getServiceUnit_Group(), this.ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, ServiceUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getServiceUnit_Any(), this.ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ServiceUnit.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getServiceUnit_Any1(), this.ecorePackage.getEFeatureMapEntry(), "any1", null, 0, -1, ServiceUnit.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(this.sharedLibraryTypeEClass, SharedLibraryType.class, "SharedLibraryType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSharedLibraryType_Identification(), this.getIdentification(), null, "identification", null, 1, 1, SharedLibraryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSharedLibraryType_SharedLibraryClassPath(), this.getClassPath(), null, "sharedLibraryClassPath", null, 1, 1, SharedLibraryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSharedLibraryType_ClassLoaderDelegation(), this.getClassLoaderDelegationType(), "classLoaderDelegation", null, 0, 1, SharedLibraryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSharedLibraryType_Version(), theXMLTypePackage.getAnySimpleType(), "version", null, 0, 1, SharedLibraryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(this.sharedLibraryType1EClass, SharedLibraryType1.class, "SharedLibraryType1", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSharedLibraryType1_Mixed(), this.ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, SharedLibraryType1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSharedLibraryType1_Version(), theXMLTypePackage.getAnySimpleType(), "version", null, 0, 1, SharedLibraryType1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(this.targetEClass, Target.class, "Target", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTarget_ArtifactsZip(), theXMLTypePackage.getString(), "artifactsZip", null, 1, 1, Target.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTarget_ComponentName(), theXMLTypePackage.getNCName(), "componentName", null, 1, 1, Target.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(this.classLoaderDelegationTypeEEnum, ClassLoaderDelegationType.class, "ClassLoaderDelegationType");
		addEEnumLiteral(this.classLoaderDelegationTypeEEnum, ClassLoaderDelegationType.PARENT_FIRST);
		addEEnumLiteral(this.classLoaderDelegationTypeEEnum, ClassLoaderDelegationType.SELF_FIRST);

		initEEnum(this.componentTypeEEnum, ComponentType.class, "ComponentType");
		addEEnumLiteral(this.componentTypeEEnum, ComponentType.SERVICE_ENGINE);
		addEEnumLiteral(this.componentTypeEEnum, ComponentType.BINDING_COMPONENT);

		initEEnum(this.linkTypeEEnum, LinkType.class, "LinkType");
		addEEnumLiteral(this.linkTypeEEnum, LinkType.STANDARD);
		addEEnumLiteral(this.linkTypeEEnum, LinkType.HARD);
		addEEnumLiteral(this.linkTypeEEnum, LinkType.SOFT);

		// Initialize data types
		initEDataType(this.classLoaderDelegationTypeObjectEDataType, ClassLoaderDelegationType.class, "ClassLoaderDelegationTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(this.componentTypeObjectEDataType, ComponentType.class, "ComponentTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(this.linkTypeObjectEDataType, LinkType.class, "LinkTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);

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
		(this.classLoaderDelegationTypeEEnum,
					source,
					new String[] {
					"name", "ClassLoaderDelegationType"
		});
		addAnnotation
		(this.classLoaderDelegationTypeObjectEDataType,
					source,
					new String[] {
					"name", "ClassLoaderDelegationType:Object",
					"baseType", "ClassLoaderDelegationType"
		});
		addAnnotation
		(this.classPathEClass,
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
		(this.componentEClass,
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
		(getComponent_Group(),
					source,
					new String[] {
			"kind", "group",
			"name", "group:7"
		});
		addAnnotation
		(getComponent_Any(),
					source,
					new String[] {
			"kind", "elementWildcard",
			"wildcards", "##other",
			"name", ":8",
			"processing", "skip",
			"group", "#group:7"
		});
		addAnnotation
		(getComponent_Any1(),
					source,
					new String[] {
			"kind", "elementWildcard",
			"wildcards", "##local",
			"name", ":9",
			"processing", "skip",
			"group", "#group:7"
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
		(this.componentClassNameEClass,
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
		(this.componentTypeEEnum,
					source,
					new String[] {
					"name", "ComponentType"
		});
		addAnnotation
		(this.componentTypeObjectEDataType,
					source,
					new String[] {
					"name", "ComponentType:Object",
					"baseType", "ComponentType"
		});
		addAnnotation
		(this.connectionEClass,
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
		(this.connectionsEClass,
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
		(getConnections_Group(),
					source,
					new String[] {
			"kind", "group",
			"name", "group:1"
		});
		addAnnotation
		(getConnections_Any(),
					source,
					new String[] {
			"kind", "elementWildcard",
			"wildcards", "##other",
			"name", ":2",
			"processing", "skip",
			"group", "#group:1"
		});
		addAnnotation
		(getConnections_Any1(),
					source,
					new String[] {
			"kind", "elementWildcard",
			"wildcards", "##local",
			"name", ":3",
			"processing", "skip",
			"group", "#group:1"
		});
		addAnnotation
		(this.consumerEClass,
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
		(this.consumesEClass,
					source,
					new String[] {
					"name", "Consumes",
					"kind", "elementOnly"
		});
		addAnnotation
		(getConsumes_Group(),
					source,
					new String[] {
			"kind", "group",
			"name", "group:0"
		});
		addAnnotation
		(getConsumes_Any(),
					source,
					new String[] {
			"kind", "elementWildcard",
			"wildcards", "##other",
			"name", ":1",
			"processing", "skip",
			"group", "#group:0"
		});
		addAnnotation
		(getConsumes_Any1(),
					source,
					new String[] {
			"kind", "elementWildcard",
			"wildcards", "##local",
			"name", ":2",
			"processing", "skip",
			"group", "#group:0"
		});
		addAnnotation
		(getConsumes_EndpointName(),
					source,
					new String[] {
			"kind", "attribute",
			"name", "endpoint-name"
		});
		addAnnotation
		(getConsumes_InterfaceName(),
					source,
					new String[] {
			"kind", "attribute",
			"name", "interface-name"
		});
		addAnnotation
		(getConsumes_LinkType(),
					source,
					new String[] {
			"kind", "attribute",
			"name", "link-type"
		});
		addAnnotation
		(getConsumes_ServiceName(),
					source,
					new String[] {
			"kind", "attribute",
			"name", "service-name"
		});
		addAnnotation
		(this.documentRootEClass,
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
		(getDocumentRoot_ArtifactsZip(),
					source,
					new String[] {
			"kind", "element",
			"name", "artifacts-zip",
			"namespace", "##targetNamespace"
		});
		addAnnotation
		(getDocumentRoot_BootstrapClassName(),
					source,
					new String[] {
			"kind", "element",
			"name", "bootstrap-class-name",
			"namespace", "##targetNamespace"
		});
		addAnnotation
		(getDocumentRoot_BootstrapClassPath(),
					source,
					new String[] {
			"kind", "element",
			"name", "bootstrap-class-path",
			"namespace", "##targetNamespace"
		});
		addAnnotation
		(getDocumentRoot_Component(),
					source,
					new String[] {
			"kind", "element",
			"name", "component",
			"namespace", "##targetNamespace"
		});
		addAnnotation
		(getDocumentRoot_ComponentClassName(),
					source,
					new String[] {
			"kind", "element",
			"name", "component-class-name",
			"namespace", "##targetNamespace"
		});
		addAnnotation
		(getDocumentRoot_ComponentClassPath(),
					source,
					new String[] {
			"kind", "element",
			"name", "component-class-path",
			"namespace", "##targetNamespace"
		});
		addAnnotation
		(getDocumentRoot_ComponentName(),
					source,
					new String[] {
			"kind", "element",
			"name", "component-name",
			"namespace", "##targetNamespace"
		});
		addAnnotation
		(getDocumentRoot_Connection(),
					source,
					new String[] {
			"kind", "element",
			"name", "connection",
			"namespace", "##targetNamespace"
		});
		addAnnotation
		(getDocumentRoot_Connections(),
					source,
					new String[] {
			"kind", "element",
			"name", "connections",
			"namespace", "##targetNamespace"
		});
		addAnnotation
		(getDocumentRoot_Consumer(),
					source,
					new String[] {
			"kind", "element",
			"name", "consumer",
			"namespace", "##targetNamespace"
		});
		addAnnotation
		(getDocumentRoot_Consumes(),
					source,
					new String[] {
			"kind", "element",
			"name", "consumes",
			"namespace", "##targetNamespace"
		});
		addAnnotation
		(getDocumentRoot_Description(),
					source,
					new String[] {
			"kind", "element",
			"name", "description",
			"namespace", "##targetNamespace"
		});
		addAnnotation
		(getDocumentRoot_Identification(),
					source,
					new String[] {
			"kind", "element",
			"name", "identification",
			"namespace", "##targetNamespace"
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
		(getDocumentRoot_Name(),
					source,
					new String[] {
			"kind", "element",
			"name", "name",
			"namespace", "##targetNamespace"
		});
		addAnnotation
		(getDocumentRoot_PathElement(),
					source,
					new String[] {
			"kind", "element",
			"name", "path-element",
			"namespace", "##targetNamespace"
		});
		addAnnotation
		(getDocumentRoot_Provider(),
					source,
					new String[] {
			"kind", "element",
			"name", "provider",
			"namespace", "##targetNamespace"
		});
		addAnnotation
		(getDocumentRoot_Provides(),
					source,
					new String[] {
			"kind", "element",
			"name", "provides",
			"namespace", "##targetNamespace"
		});
		addAnnotation
		(getDocumentRoot_ServiceAssembly(),
					source,
					new String[] {
			"kind", "element",
			"name", "service-assembly",
			"namespace", "##targetNamespace"
		});
		addAnnotation
		(getDocumentRoot_Services(),
					source,
					new String[] {
			"kind", "element",
			"name", "services",
			"namespace", "##targetNamespace"
		});
		addAnnotation
		(getDocumentRoot_ServiceUnit(),
					source,
					new String[] {
			"kind", "element",
			"name", "service-unit",
			"namespace", "##targetNamespace"
		});
		addAnnotation
		(getDocumentRoot_SharedLibraryClassPath(),
					source,
					new String[] {
			"kind", "element",
			"name", "shared-library-class-path",
			"namespace", "##targetNamespace"
		});
		addAnnotation
		(getDocumentRoot_Target(),
					source,
					new String[] {
			"kind", "element",
			"name", "target",
			"namespace", "##targetNamespace"
		});
		addAnnotation
		(this.identificationEClass,
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
		(getIdentification_Group(),
					source,
					new String[] {
			"kind", "group",
			"name", "group:2"
		});
		addAnnotation
		(getIdentification_Any(),
					source,
					new String[] {
			"kind", "elementWildcard",
			"wildcards", "##other",
			"name", ":3",
			"processing", "skip",
			"group", "#group:2"
		});
		addAnnotation
		(getIdentification_Any1(),
					source,
					new String[] {
			"kind", "elementWildcard",
			"wildcards", "##local",
			"name", ":4",
			"processing", "skip",
			"group", "#group:2"
		});
		addAnnotation
		(this.jbiEClass,
					source,
					new String[] {
					"name", "Jbi",
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
		(this.linkTypeEEnum,
					source,
					new String[] {
					"name", "LinkType"
		});
		addAnnotation
		(this.linkTypeObjectEDataType,
					source,
					new String[] {
					"name", "LinkType:Object",
					"baseType", "LinkType"
		});
		addAnnotation
		(this.providerEClass,
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
		(this.providesEClass,
					source,
					new String[] {
					"name", "Provides",
					"kind", "elementOnly"
		});
		addAnnotation
		(getProvides_Group(),
					source,
					new String[] {
			"kind", "group",
			"name", "group:0"
		});
		addAnnotation
		(getProvides_Any(),
					source,
					new String[] {
			"kind", "elementWildcard",
			"wildcards", "##other",
			"name", ":1",
			"processing", "skip",
			"group", "#group:0"
		});
		addAnnotation
		(getProvides_Any1(),
					source,
					new String[] {
			"kind", "elementWildcard",
			"wildcards", "##local",
			"name", ":2",
			"processing", "skip",
			"group", "#group:0"
		});
		addAnnotation
		(getProvides_EndpointName(),
					source,
					new String[] {
			"kind", "attribute",
			"name", "endpoint-name"
		});
		addAnnotation
		(getProvides_InterfaceName(),
					source,
					new String[] {
			"kind", "attribute",
			"name", "interface-name"
		});
		addAnnotation
		(getProvides_ServiceName(),
					source,
					new String[] {
			"kind", "attribute",
			"name", "service-name"
		});
		addAnnotation
		(this.serviceAssemblyEClass,
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
		(getServiceAssembly_Group(),
					source,
					new String[] {
			"kind", "group",
			"name", "group:3"
		});
		addAnnotation
		(getServiceAssembly_Any(),
					source,
					new String[] {
			"kind", "elementWildcard",
			"wildcards", "##other",
			"name", ":4",
			"processing", "skip",
			"group", "#group:3"
		});
		addAnnotation
		(getServiceAssembly_Any1(),
					source,
					new String[] {
			"kind", "elementWildcard",
			"wildcards", "##local",
			"name", ":5",
			"processing", "skip",
			"group", "#group:3"
		});
		addAnnotation
		(this.servicesEClass,
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
		(getServices_Group(),
					source,
					new String[] {
			"kind", "group",
			"name", "group:2"
		});
		addAnnotation
		(getServices_Any(),
					source,
					new String[] {
			"kind", "elementWildcard",
			"wildcards", "##other",
			"name", ":3",
			"processing", "skip",
			"group", "#group:2"
		});
		addAnnotation
		(getServices_Any1(),
					source,
					new String[] {
			"kind", "elementWildcard",
			"wildcards", "##local",
			"name", ":4",
			"processing", "skip",
			"group", "#group:2"
		});
		addAnnotation
		(getServices_BindingComponent(),
					source,
					new String[] {
			"kind", "attribute",
			"name", "binding-component"
		});
		addAnnotation
		(this.serviceUnitEClass,
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
		(getServiceUnit_Group(),
					source,
					new String[] {
			"kind", "group",
			"name", "group:2"
		});
		addAnnotation
		(getServiceUnit_Any(),
					source,
					new String[] {
			"kind", "elementWildcard",
			"wildcards", "##other",
			"name", ":3",
			"processing", "skip",
			"group", "#group:2"
		});
		addAnnotation
		(getServiceUnit_Any1(),
					source,
					new String[] {
			"kind", "elementWildcard",
			"wildcards", "##local",
			"name", ":4",
			"processing", "skip",
			"group", "#group:2"
		});
		addAnnotation
		(this.sharedLibraryTypeEClass,
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
		(this.sharedLibraryType1EClass,
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
		(this.targetEClass,
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
	}

} //JbiPackageImpl
