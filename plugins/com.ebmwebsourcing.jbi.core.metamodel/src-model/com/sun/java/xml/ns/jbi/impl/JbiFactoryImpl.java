/**
 *  Copyright (c) 2009-2013, Linagora
 *  
 *  This source code is available under agreement available at
 *  http://www.petalslink.com/legal/licenses/petals-studio
 *  
 *  You should have received a copy of the agreement along with this program.
 *  If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 */
package com.sun.java.xml.ns.jbi.impl;

import com.sun.java.xml.ns.jbi.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class JbiFactoryImpl extends EFactoryImpl implements JbiFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static JbiFactory init() {
		try {
			JbiFactory theJbiFactory = (JbiFactory)EPackage.Registry.INSTANCE.getEFactory("http://java.sun.com/xml/ns/jbi"); 
			if (theJbiFactory != null) {
				return theJbiFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new JbiFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JbiFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case JbiPackage.CLASS_PATH: return createClassPath();
			case JbiPackage.COMPONENT: return createComponent();
			case JbiPackage.COMPONENT_CLASS_NAME: return createComponentClassName();
			case JbiPackage.CONNECTION: return createConnection();
			case JbiPackage.CONNECTIONS: return createConnections();
			case JbiPackage.CONSUMER: return createConsumer();
			case JbiPackage.DOCUMENT_ROOT: return createDocumentRoot();
			case JbiPackage.IDENTIFICATION: return createIdentification();
			case JbiPackage.JBI: return createJbi();
			case JbiPackage.SERVICES: return createServices();
			case JbiPackage.CONSUMES: return createConsumes();
			case JbiPackage.PROVIDES: return createProvides();
			case JbiPackage.PROVIDER: return createProvider();
			case JbiPackage.SERVICE_ASSEMBLY: return createServiceAssembly();
			case JbiPackage.SERVICE_UNIT: return createServiceUnit();
			case JbiPackage.SHARED_LIBRARY_TYPE: return createSharedLibraryType();
			case JbiPackage.SHARED_LIBRARY_TYPE1: return createSharedLibraryType1();
			case JbiPackage.TARGET: return createTarget();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case JbiPackage.CLASS_LOADER_DELEGATION_TYPE:
				return createClassLoaderDelegationTypeFromString(eDataType, initialValue);
			case JbiPackage.COMPONENT_TYPE:
				return createComponentTypeFromString(eDataType, initialValue);
			case JbiPackage.LINK_TYPE:
				return createLinkTypeFromString(eDataType, initialValue);
			case JbiPackage.CLASS_LOADER_DELEGATION_TYPE_OBJECT:
				return createClassLoaderDelegationTypeObjectFromString(eDataType, initialValue);
			case JbiPackage.COMPONENT_TYPE_OBJECT:
				return createComponentTypeObjectFromString(eDataType, initialValue);
			case JbiPackage.LINK_TYPE_OBJECT:
				return createLinkTypeObjectFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case JbiPackage.CLASS_LOADER_DELEGATION_TYPE:
				return convertClassLoaderDelegationTypeToString(eDataType, instanceValue);
			case JbiPackage.COMPONENT_TYPE:
				return convertComponentTypeToString(eDataType, instanceValue);
			case JbiPackage.LINK_TYPE:
				return convertLinkTypeToString(eDataType, instanceValue);
			case JbiPackage.CLASS_LOADER_DELEGATION_TYPE_OBJECT:
				return convertClassLoaderDelegationTypeObjectToString(eDataType, instanceValue);
			case JbiPackage.COMPONENT_TYPE_OBJECT:
				return convertComponentTypeObjectToString(eDataType, instanceValue);
			case JbiPackage.LINK_TYPE_OBJECT:
				return convertLinkTypeObjectToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassPath createClassPath() {
		ClassPathImpl classPath = new ClassPathImpl();
		return classPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Component createComponent() {
		ComponentImpl component = new ComponentImpl();
		return component;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentClassName createComponentClassName() {
		ComponentClassNameImpl componentClassName = new ComponentClassNameImpl();
		return componentClassName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Connection createConnection() {
		ConnectionImpl connection = new ConnectionImpl();
		return connection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Connections createConnections() {
		ConnectionsImpl connections = new ConnectionsImpl();
		return connections;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Consumer createConsumer() {
		ConsumerImpl consumer = new ConsumerImpl();
		return consumer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentRoot createDocumentRoot() {
		DocumentRootImpl documentRoot = new DocumentRootImpl();
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Identification createIdentification() {
		IdentificationImpl identification = new IdentificationImpl();
		return identification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Jbi createJbi() {
		JbiImpl jbi = new JbiImpl();
		return jbi;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Services createServices() {
		ServicesImpl services = new ServicesImpl();
		return services;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Consumes createConsumes() {
		ConsumesImpl consumes = new ConsumesImpl();
		return consumes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Provides createProvides() {
		ProvidesImpl provides = new ProvidesImpl();
		return provides;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Provider createProvider() {
		ProviderImpl provider = new ProviderImpl();
		return provider;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceAssembly createServiceAssembly() {
		ServiceAssemblyImpl serviceAssembly = new ServiceAssemblyImpl();
		return serviceAssembly;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceUnit createServiceUnit() {
		ServiceUnitImpl serviceUnit = new ServiceUnitImpl();
		return serviceUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SharedLibraryType createSharedLibraryType() {
		SharedLibraryTypeImpl sharedLibraryType = new SharedLibraryTypeImpl();
		return sharedLibraryType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SharedLibraryType1 createSharedLibraryType1() {
		SharedLibraryType1Impl sharedLibraryType1 = new SharedLibraryType1Impl();
		return sharedLibraryType1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Target createTarget() {
		TargetImpl target = new TargetImpl();
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassLoaderDelegationType createClassLoaderDelegationTypeFromString(EDataType eDataType, String initialValue) {
		ClassLoaderDelegationType result = ClassLoaderDelegationType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertClassLoaderDelegationTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentType createComponentTypeFromString(EDataType eDataType, String initialValue) {
		ComponentType result = ComponentType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertComponentTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinkType createLinkTypeFromString(EDataType eDataType, String initialValue) {
		LinkType result = LinkType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLinkTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassLoaderDelegationType createClassLoaderDelegationTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createClassLoaderDelegationTypeFromString(JbiPackage.Literals.CLASS_LOADER_DELEGATION_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertClassLoaderDelegationTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertClassLoaderDelegationTypeToString(JbiPackage.Literals.CLASS_LOADER_DELEGATION_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentType createComponentTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createComponentTypeFromString(JbiPackage.Literals.COMPONENT_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertComponentTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertComponentTypeToString(JbiPackage.Literals.COMPONENT_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinkType createLinkTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createLinkTypeFromString(JbiPackage.Literals.LINK_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLinkTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertLinkTypeToString(JbiPackage.Literals.LINK_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JbiPackage getJbiPackage() {
		return (JbiPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static JbiPackage getPackage() {
		return JbiPackage.eINSTANCE;
	}

} //JbiFactoryImpl
