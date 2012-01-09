/**
 * Copyright (c) 2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.jms.jms;

import com.sun.java.xml.ns.jbi.JbiPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see com.ebmwebsourcing.petals.services.jms.jms.JmsFactory
 * @model kind="package"
 * @generated
 */
public interface JmsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "jms";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://petals.ow2.org/components/jms/version-3";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "jms";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	JmsPackage eINSTANCE = com.ebmwebsourcing.petals.services.jms.jms.impl.JmsPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.jms.jms.impl.JmsExtensionImpl <em>Extension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.jms.jms.impl.JmsExtensionImpl
	 * @see com.ebmwebsourcing.petals.services.jms.jms.impl.JmsPackageImpl#getJmsExtension()
	 * @generated
	 */
	int JMS_EXTENSION = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_EXTENSION__GROUP = JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_EXTENSION__OTHER = JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_EXTENSION__LOCAL = JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT__LOCAL;

	/**
	 * The feature id for the '<em><b>Jndi Provider URL</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_EXTENSION__JNDI_PROVIDER_URL = JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Jndi Initial Context Factory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_EXTENSION__JNDI_INITIAL_CONTEXT_FACTORY = JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Jndi Destination Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_EXTENSION__JNDI_DESTINATION_NAME = JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Jndi Connection Factory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_EXTENSION__JNDI_CONNECTION_FACTORY = JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_EXTENSION__USER = JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_EXTENSION__PASSWORD = JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Transacted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_EXTENSION__TRANSACTED = JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Extension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_EXTENSION_FEATURE_COUNT = JbiPackage.ABSTRACT_EXTENSIBLE_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.jms.jms.impl.JmsProvidesImpl <em>Provides</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.jms.jms.impl.JmsProvidesImpl
	 * @see com.ebmwebsourcing.petals.services.jms.jms.impl.JmsPackageImpl#getJmsProvides()
	 * @generated
	 */
	int JMS_PROVIDES = 1;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_PROVIDES__GROUP = JMS_EXTENSION__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_PROVIDES__OTHER = JMS_EXTENSION__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_PROVIDES__LOCAL = JMS_EXTENSION__LOCAL;

	/**
	 * The feature id for the '<em><b>Jndi Provider URL</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_PROVIDES__JNDI_PROVIDER_URL = JMS_EXTENSION__JNDI_PROVIDER_URL;

	/**
	 * The feature id for the '<em><b>Jndi Initial Context Factory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_PROVIDES__JNDI_INITIAL_CONTEXT_FACTORY = JMS_EXTENSION__JNDI_INITIAL_CONTEXT_FACTORY;

	/**
	 * The feature id for the '<em><b>Jndi Destination Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_PROVIDES__JNDI_DESTINATION_NAME = JMS_EXTENSION__JNDI_DESTINATION_NAME;

	/**
	 * The feature id for the '<em><b>Jndi Connection Factory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_PROVIDES__JNDI_CONNECTION_FACTORY = JMS_EXTENSION__JNDI_CONNECTION_FACTORY;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_PROVIDES__USER = JMS_EXTENSION__USER;

	/**
	 * The feature id for the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_PROVIDES__PASSWORD = JMS_EXTENSION__PASSWORD;

	/**
	 * The feature id for the '<em><b>Transacted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_PROVIDES__TRANSACTED = JMS_EXTENSION__TRANSACTED;

	/**
	 * The feature id for the '<em><b>Max Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_PROVIDES__MAX_ACTIVE = JMS_EXTENSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Max Wait</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_PROVIDES__MAX_WAIT = JMS_EXTENSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Max Idle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_PROVIDES__MAX_IDLE = JMS_EXTENSION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Time Between Eviction Runs Milles</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_PROVIDES__TIME_BETWEEN_EVICTION_RUNS_MILLES = JMS_EXTENSION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Min Evictable Idle Time Millis</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_PROVIDES__MIN_EVICTABLE_IDLE_TIME_MILLIS = JMS_EXTENSION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Test While Idle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_PROVIDES__TEST_WHILE_IDLE = JMS_EXTENSION_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Provides</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_PROVIDES_FEATURE_COUNT = JMS_EXTENSION_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.jms.jms.impl.JmsConsumesImpl <em>Consumes</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.jms.jms.impl.JmsConsumesImpl
	 * @see com.ebmwebsourcing.petals.services.jms.jms.impl.JmsPackageImpl#getJmsConsumes()
	 * @generated
	 */
	int JMS_CONSUMES = 2;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_CONSUMES__GROUP = JMS_EXTENSION__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_CONSUMES__OTHER = JMS_EXTENSION__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_CONSUMES__LOCAL = JMS_EXTENSION__LOCAL;

	/**
	 * The feature id for the '<em><b>Jndi Provider URL</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_CONSUMES__JNDI_PROVIDER_URL = JMS_EXTENSION__JNDI_PROVIDER_URL;

	/**
	 * The feature id for the '<em><b>Jndi Initial Context Factory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_CONSUMES__JNDI_INITIAL_CONTEXT_FACTORY = JMS_EXTENSION__JNDI_INITIAL_CONTEXT_FACTORY;

	/**
	 * The feature id for the '<em><b>Jndi Destination Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_CONSUMES__JNDI_DESTINATION_NAME = JMS_EXTENSION__JNDI_DESTINATION_NAME;

	/**
	 * The feature id for the '<em><b>Jndi Connection Factory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_CONSUMES__JNDI_CONNECTION_FACTORY = JMS_EXTENSION__JNDI_CONNECTION_FACTORY;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_CONSUMES__USER = JMS_EXTENSION__USER;

	/**
	 * The feature id for the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_CONSUMES__PASSWORD = JMS_EXTENSION__PASSWORD;

	/**
	 * The feature id for the '<em><b>Transacted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_CONSUMES__TRANSACTED = JMS_EXTENSION__TRANSACTED;

	/**
	 * The number of structural features of the '<em>Consumes</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JMS_CONSUMES_FEATURE_COUNT = JMS_EXTENSION_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link com.ebmwebsourcing.petals.services.jms.jms.JmsExtension <em>Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extension</em>'.
	 * @see com.ebmwebsourcing.petals.services.jms.jms.JmsExtension
	 * @generated
	 */
	EClass getJmsExtension();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.jms.jms.JmsExtension#getJndiProviderURL <em>Jndi Provider URL</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Jndi Provider URL</em>'.
	 * @see com.ebmwebsourcing.petals.services.jms.jms.JmsExtension#getJndiProviderURL()
	 * @see #getJmsExtension()
	 * @generated
	 */
	EAttribute getJmsExtension_JndiProviderURL();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.jms.jms.JmsExtension#getJndiInitialContextFactory <em>Jndi Initial Context Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Jndi Initial Context Factory</em>'.
	 * @see com.ebmwebsourcing.petals.services.jms.jms.JmsExtension#getJndiInitialContextFactory()
	 * @see #getJmsExtension()
	 * @generated
	 */
	EAttribute getJmsExtension_JndiInitialContextFactory();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.jms.jms.JmsExtension#getJndiDestinationName <em>Jndi Destination Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Jndi Destination Name</em>'.
	 * @see com.ebmwebsourcing.petals.services.jms.jms.JmsExtension#getJndiDestinationName()
	 * @see #getJmsExtension()
	 * @generated
	 */
	EAttribute getJmsExtension_JndiDestinationName();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.jms.jms.JmsExtension#getJndiConnectionFactory <em>Jndi Connection Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Jndi Connection Factory</em>'.
	 * @see com.ebmwebsourcing.petals.services.jms.jms.JmsExtension#getJndiConnectionFactory()
	 * @see #getJmsExtension()
	 * @generated
	 */
	EAttribute getJmsExtension_JndiConnectionFactory();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.jms.jms.JmsExtension#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User</em>'.
	 * @see com.ebmwebsourcing.petals.services.jms.jms.JmsExtension#getUser()
	 * @see #getJmsExtension()
	 * @generated
	 */
	EAttribute getJmsExtension_User();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.jms.jms.JmsExtension#getPassword <em>Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Password</em>'.
	 * @see com.ebmwebsourcing.petals.services.jms.jms.JmsExtension#getPassword()
	 * @see #getJmsExtension()
	 * @generated
	 */
	EAttribute getJmsExtension_Password();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.jms.jms.JmsExtension#isTransacted <em>Transacted</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transacted</em>'.
	 * @see com.ebmwebsourcing.petals.services.jms.jms.JmsExtension#isTransacted()
	 * @see #getJmsExtension()
	 * @generated
	 */
	EAttribute getJmsExtension_Transacted();

	/**
	 * Returns the meta object for class '{@link com.ebmwebsourcing.petals.services.jms.jms.JmsProvides <em>Provides</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Provides</em>'.
	 * @see com.ebmwebsourcing.petals.services.jms.jms.JmsProvides
	 * @generated
	 */
	EClass getJmsProvides();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.jms.jms.JmsProvides#getMaxActive <em>Max Active</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Active</em>'.
	 * @see com.ebmwebsourcing.petals.services.jms.jms.JmsProvides#getMaxActive()
	 * @see #getJmsProvides()
	 * @generated
	 */
	EAttribute getJmsProvides_MaxActive();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.jms.jms.JmsProvides#getMaxWait <em>Max Wait</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Wait</em>'.
	 * @see com.ebmwebsourcing.petals.services.jms.jms.JmsProvides#getMaxWait()
	 * @see #getJmsProvides()
	 * @generated
	 */
	EAttribute getJmsProvides_MaxWait();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.jms.jms.JmsProvides#getMaxIdle <em>Max Idle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Idle</em>'.
	 * @see com.ebmwebsourcing.petals.services.jms.jms.JmsProvides#getMaxIdle()
	 * @see #getJmsProvides()
	 * @generated
	 */
	EAttribute getJmsProvides_MaxIdle();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.jms.jms.JmsProvides#getTimeBetweenEvictionRunsMilles <em>Time Between Eviction Runs Milles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Between Eviction Runs Milles</em>'.
	 * @see com.ebmwebsourcing.petals.services.jms.jms.JmsProvides#getTimeBetweenEvictionRunsMilles()
	 * @see #getJmsProvides()
	 * @generated
	 */
	EAttribute getJmsProvides_TimeBetweenEvictionRunsMilles();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.jms.jms.JmsProvides#getMinEvictableIdleTimeMillis <em>Min Evictable Idle Time Millis</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min Evictable Idle Time Millis</em>'.
	 * @see com.ebmwebsourcing.petals.services.jms.jms.JmsProvides#getMinEvictableIdleTimeMillis()
	 * @see #getJmsProvides()
	 * @generated
	 */
	EAttribute getJmsProvides_MinEvictableIdleTimeMillis();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.jms.jms.JmsProvides#isTestWhileIdle <em>Test While Idle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Test While Idle</em>'.
	 * @see com.ebmwebsourcing.petals.services.jms.jms.JmsProvides#isTestWhileIdle()
	 * @see #getJmsProvides()
	 * @generated
	 */
	EAttribute getJmsProvides_TestWhileIdle();

	/**
	 * Returns the meta object for class '{@link com.ebmwebsourcing.petals.services.jms.jms.JmsConsumes <em>Consumes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Consumes</em>'.
	 * @see com.ebmwebsourcing.petals.services.jms.jms.JmsConsumes
	 * @generated
	 */
	EClass getJmsConsumes();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	JmsFactory getJmsFactory();

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
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.jms.jms.impl.JmsExtensionImpl <em>Extension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.jms.jms.impl.JmsExtensionImpl
		 * @see com.ebmwebsourcing.petals.services.jms.jms.impl.JmsPackageImpl#getJmsExtension()
		 * @generated
		 */
		EClass JMS_EXTENSION = eINSTANCE.getJmsExtension();

		/**
		 * The meta object literal for the '<em><b>Jndi Provider URL</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JMS_EXTENSION__JNDI_PROVIDER_URL = eINSTANCE.getJmsExtension_JndiProviderURL();

		/**
		 * The meta object literal for the '<em><b>Jndi Initial Context Factory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JMS_EXTENSION__JNDI_INITIAL_CONTEXT_FACTORY = eINSTANCE.getJmsExtension_JndiInitialContextFactory();

		/**
		 * The meta object literal for the '<em><b>Jndi Destination Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JMS_EXTENSION__JNDI_DESTINATION_NAME = eINSTANCE.getJmsExtension_JndiDestinationName();

		/**
		 * The meta object literal for the '<em><b>Jndi Connection Factory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JMS_EXTENSION__JNDI_CONNECTION_FACTORY = eINSTANCE.getJmsExtension_JndiConnectionFactory();

		/**
		 * The meta object literal for the '<em><b>User</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JMS_EXTENSION__USER = eINSTANCE.getJmsExtension_User();

		/**
		 * The meta object literal for the '<em><b>Password</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JMS_EXTENSION__PASSWORD = eINSTANCE.getJmsExtension_Password();

		/**
		 * The meta object literal for the '<em><b>Transacted</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JMS_EXTENSION__TRANSACTED = eINSTANCE.getJmsExtension_Transacted();

		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.jms.jms.impl.JmsProvidesImpl <em>Provides</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.jms.jms.impl.JmsProvidesImpl
		 * @see com.ebmwebsourcing.petals.services.jms.jms.impl.JmsPackageImpl#getJmsProvides()
		 * @generated
		 */
		EClass JMS_PROVIDES = eINSTANCE.getJmsProvides();

		/**
		 * The meta object literal for the '<em><b>Max Active</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JMS_PROVIDES__MAX_ACTIVE = eINSTANCE.getJmsProvides_MaxActive();

		/**
		 * The meta object literal for the '<em><b>Max Wait</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JMS_PROVIDES__MAX_WAIT = eINSTANCE.getJmsProvides_MaxWait();

		/**
		 * The meta object literal for the '<em><b>Max Idle</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JMS_PROVIDES__MAX_IDLE = eINSTANCE.getJmsProvides_MaxIdle();

		/**
		 * The meta object literal for the '<em><b>Time Between Eviction Runs Milles</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JMS_PROVIDES__TIME_BETWEEN_EVICTION_RUNS_MILLES = eINSTANCE.getJmsProvides_TimeBetweenEvictionRunsMilles();

		/**
		 * The meta object literal for the '<em><b>Min Evictable Idle Time Millis</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JMS_PROVIDES__MIN_EVICTABLE_IDLE_TIME_MILLIS = eINSTANCE.getJmsProvides_MinEvictableIdleTimeMillis();

		/**
		 * The meta object literal for the '<em><b>Test While Idle</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JMS_PROVIDES__TEST_WHILE_IDLE = eINSTANCE.getJmsProvides_TestWhileIdle();

		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.jms.jms.impl.JmsConsumesImpl <em>Consumes</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.jms.jms.impl.JmsConsumesImpl
		 * @see com.ebmwebsourcing.petals.services.jms.jms.impl.JmsPackageImpl#getJmsConsumes()
		 * @generated
		 */
		EClass JMS_CONSUMES = eINSTANCE.getJmsConsumes();

	}

} //JmsPackage
