/**
 * Copyright (c) 2011-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.ejb.ejb;

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
 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbFactory
 * @model kind="package"
 * @generated
 */
public interface EjbPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ejb";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://petals.ow2.org/components/ejb/version-1";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ejb";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EjbPackage eINSTANCE = com.ebmwebsourcing.petals.services.ejb.ejb.impl.EjbPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.impl.EjbProvidesImpl <em>Provides</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.impl.EjbProvidesImpl
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.impl.EjbPackageImpl#getEjbProvides()
	 * @generated
	 */
	int EJB_PROVIDES = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_PROVIDES__GROUP = JbiPackage.PROVIDES__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_PROVIDES__OTHER = JbiPackage.PROVIDES__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_PROVIDES__LOCAL = JbiPackage.PROVIDES__LOCAL;

	/**
	 * The feature id for the '<em><b>Endpoint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_PROVIDES__ENDPOINT_NAME = JbiPackage.PROVIDES__ENDPOINT_NAME;

	/**
	 * The feature id for the '<em><b>Interface Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_PROVIDES__INTERFACE_NAME = JbiPackage.PROVIDES__INTERFACE_NAME;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_PROVIDES__SERVICE_NAME = JbiPackage.PROVIDES__SERVICE_NAME;

	/**
	 * The feature id for the '<em><b>Ejb Jndi Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_PROVIDES__EJB_JNDI_NAME = JbiPackage.PROVIDES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Java Naming Factory Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_PROVIDES__JAVA_NAMING_FACTORY_INITIAL = JbiPackage.PROVIDES_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Java Naming Factory Url Pkgs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_PROVIDES__JAVA_NAMING_FACTORY_URL_PKGS = JbiPackage.PROVIDES_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Java Naming Provider Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_PROVIDES__JAVA_NAMING_PROVIDER_URL = JbiPackage.PROVIDES_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Ejb Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_PROVIDES__EJB_VERSION = JbiPackage.PROVIDES_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Ejb Home Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_PROVIDES__EJB_HOME_INTERFACE = JbiPackage.PROVIDES_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Security Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_PROVIDES__SECURITY_NAME = JbiPackage.PROVIDES_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Security Principal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_PROVIDES__SECURITY_PRINCIPAL = JbiPackage.PROVIDES_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Security Credencials</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_PROVIDES__SECURITY_CREDENCIALS = JbiPackage.PROVIDES_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Marshalling Engine</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_PROVIDES__MARSHALLING_ENGINE = JbiPackage.PROVIDES_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Provides</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_PROVIDES_FEATURE_COUNT = JbiPackage.PROVIDES_FEATURE_COUNT + 10;

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.XmlEngine <em>Xml Engine</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.XmlEngine
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.impl.EjbPackageImpl#getXmlEngine()
	 * @generated
	 */
	int XML_ENGINE = 1;

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbVersion <em>Version</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbVersion
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.impl.EjbPackageImpl#getEjbVersion()
	 * @generated
	 */
	int EJB_VERSION = 2;


	/**
	 * Returns the meta object for class '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides <em>Provides</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Provides</em>'.
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides
	 * @generated
	 */
	EClass getEjbProvides();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getEjbJndiName <em>Ejb Jndi Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ejb Jndi Name</em>'.
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getEjbJndiName()
	 * @see #getEjbProvides()
	 * @generated
	 */
	EAttribute getEjbProvides_EjbJndiName();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getJavaNamingFactoryInitial <em>Java Naming Factory Initial</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Java Naming Factory Initial</em>'.
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getJavaNamingFactoryInitial()
	 * @see #getEjbProvides()
	 * @generated
	 */
	EAttribute getEjbProvides_JavaNamingFactoryInitial();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getJavaNamingFactoryUrlPkgs <em>Java Naming Factory Url Pkgs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Java Naming Factory Url Pkgs</em>'.
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getJavaNamingFactoryUrlPkgs()
	 * @see #getEjbProvides()
	 * @generated
	 */
	EAttribute getEjbProvides_JavaNamingFactoryUrlPkgs();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getJavaNamingProviderUrl <em>Java Naming Provider Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Java Naming Provider Url</em>'.
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getJavaNamingProviderUrl()
	 * @see #getEjbProvides()
	 * @generated
	 */
	EAttribute getEjbProvides_JavaNamingProviderUrl();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getEjbVersion <em>Ejb Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ejb Version</em>'.
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getEjbVersion()
	 * @see #getEjbProvides()
	 * @generated
	 */
	EAttribute getEjbProvides_EjbVersion();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getEjbHomeInterface <em>Ejb Home Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ejb Home Interface</em>'.
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getEjbHomeInterface()
	 * @see #getEjbProvides()
	 * @generated
	 */
	EAttribute getEjbProvides_EjbHomeInterface();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getSecurityName <em>Security Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Security Name</em>'.
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getSecurityName()
	 * @see #getEjbProvides()
	 * @generated
	 */
	EAttribute getEjbProvides_SecurityName();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getSecurityPrincipal <em>Security Principal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Security Principal</em>'.
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getSecurityPrincipal()
	 * @see #getEjbProvides()
	 * @generated
	 */
	EAttribute getEjbProvides_SecurityPrincipal();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getSecurityCredencials <em>Security Credencials</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Security Credencials</em>'.
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getSecurityCredencials()
	 * @see #getEjbProvides()
	 * @generated
	 */
	EAttribute getEjbProvides_SecurityCredencials();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getMarshallingEngine <em>Marshalling Engine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Marshalling Engine</em>'.
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getMarshallingEngine()
	 * @see #getEjbProvides()
	 * @generated
	 */
	EAttribute getEjbProvides_MarshallingEngine();

	/**
	 * Returns the meta object for enum '{@link com.ebmwebsourcing.petals.services.ejb.ejb.XmlEngine <em>Xml Engine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Xml Engine</em>'.
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.XmlEngine
	 * @generated
	 */
	EEnum getXmlEngine();

	/**
	 * Returns the meta object for enum '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Version</em>'.
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbVersion
	 * @generated
	 */
	EEnum getEjbVersion();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EjbFactory getEjbFactory();

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
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.impl.EjbProvidesImpl <em>Provides</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.ejb.ejb.impl.EjbProvidesImpl
		 * @see com.ebmwebsourcing.petals.services.ejb.ejb.impl.EjbPackageImpl#getEjbProvides()
		 * @generated
		 */
		EClass EJB_PROVIDES = eINSTANCE.getEjbProvides();

		/**
		 * The meta object literal for the '<em><b>Ejb Jndi Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EJB_PROVIDES__EJB_JNDI_NAME = eINSTANCE.getEjbProvides_EjbJndiName();

		/**
		 * The meta object literal for the '<em><b>Java Naming Factory Initial</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EJB_PROVIDES__JAVA_NAMING_FACTORY_INITIAL = eINSTANCE.getEjbProvides_JavaNamingFactoryInitial();

		/**
		 * The meta object literal for the '<em><b>Java Naming Factory Url Pkgs</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EJB_PROVIDES__JAVA_NAMING_FACTORY_URL_PKGS = eINSTANCE.getEjbProvides_JavaNamingFactoryUrlPkgs();

		/**
		 * The meta object literal for the '<em><b>Java Naming Provider Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EJB_PROVIDES__JAVA_NAMING_PROVIDER_URL = eINSTANCE.getEjbProvides_JavaNamingProviderUrl();

		/**
		 * The meta object literal for the '<em><b>Ejb Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EJB_PROVIDES__EJB_VERSION = eINSTANCE.getEjbProvides_EjbVersion();

		/**
		 * The meta object literal for the '<em><b>Ejb Home Interface</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EJB_PROVIDES__EJB_HOME_INTERFACE = eINSTANCE.getEjbProvides_EjbHomeInterface();

		/**
		 * The meta object literal for the '<em><b>Security Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EJB_PROVIDES__SECURITY_NAME = eINSTANCE.getEjbProvides_SecurityName();

		/**
		 * The meta object literal for the '<em><b>Security Principal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EJB_PROVIDES__SECURITY_PRINCIPAL = eINSTANCE.getEjbProvides_SecurityPrincipal();

		/**
		 * The meta object literal for the '<em><b>Security Credencials</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EJB_PROVIDES__SECURITY_CREDENCIALS = eINSTANCE.getEjbProvides_SecurityCredencials();

		/**
		 * The meta object literal for the '<em><b>Marshalling Engine</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EJB_PROVIDES__MARSHALLING_ENGINE = eINSTANCE.getEjbProvides_MarshallingEngine();

		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.XmlEngine <em>Xml Engine</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.ejb.ejb.XmlEngine
		 * @see com.ebmwebsourcing.petals.services.ejb.ejb.impl.EjbPackageImpl#getXmlEngine()
		 * @generated
		 */
		EEnum XML_ENGINE = eINSTANCE.getXmlEngine();

		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbVersion <em>Version</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbVersion
		 * @see com.ebmwebsourcing.petals.services.ejb.ejb.impl.EjbPackageImpl#getEjbVersion()
		 * @generated
		 */
		EEnum EJB_VERSION = eINSTANCE.getEjbVersion();

	}

} //EjbPackage
