/**
 * Copyright (c) 2011-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 */
package com.ebmwebsourcing.petals.services.quartz.quartz;

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
 * @see com.ebmwebsourcing.petals.services.quartz.quartz.QuartzFactory
 * @model kind="package"
 * @generated
 */
public interface QuartzPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "quartz";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://petals.ow2.org/components/quartz/version-1";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "quartz";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	QuartzPackage eINSTANCE = com.ebmwebsourcing.petals.services.quartz.quartz.impl.QuartzPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.quartz.quartz.impl.QuartzConsumesImpl <em>Consumes</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.quartz.quartz.impl.QuartzConsumesImpl
	 * @see com.ebmwebsourcing.petals.services.quartz.quartz.impl.QuartzPackageImpl#getQuartzConsumes()
	 * @generated
	 */
	int QUARTZ_CONSUMES = 0;

	/**
	 * The feature id for the '<em><b>Endpoint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUARTZ_CONSUMES__ENDPOINT_NAME = JbiPackage.CONSUMER__ENDPOINT_NAME;

	/**
	 * The feature id for the '<em><b>Interface Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUARTZ_CONSUMES__INTERFACE_NAME = JbiPackage.CONSUMER__INTERFACE_NAME;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUARTZ_CONSUMES__SERVICE_NAME = JbiPackage.CONSUMER__SERVICE_NAME;

	/**
	 * The feature id for the '<em><b>Cron Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUARTZ_CONSUMES__CRON_EXPRESSION = JbiPackage.CONSUMER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUARTZ_CONSUMES__CONTENT = JbiPackage.CONSUMER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Consumes</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUARTZ_CONSUMES_FEATURE_COUNT = JbiPackage.CONSUMER_FEATURE_COUNT + 2;


	/**
	 * Returns the meta object for class '{@link com.ebmwebsourcing.petals.services.quartz.quartz.QuartzConsumes <em>Consumes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Consumes</em>'.
	 * @see com.ebmwebsourcing.petals.services.quartz.quartz.QuartzConsumes
	 * @generated
	 */
	EClass getQuartzConsumes();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.quartz.quartz.QuartzConsumes#getCronExpression <em>Cron Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cron Expression</em>'.
	 * @see com.ebmwebsourcing.petals.services.quartz.quartz.QuartzConsumes#getCronExpression()
	 * @see #getQuartzConsumes()
	 * @generated
	 */
	EAttribute getQuartzConsumes_CronExpression();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.quartz.quartz.QuartzConsumes#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Content</em>'.
	 * @see com.ebmwebsourcing.petals.services.quartz.quartz.QuartzConsumes#getContent()
	 * @see #getQuartzConsumes()
	 * @generated
	 */
	EAttribute getQuartzConsumes_Content();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	QuartzFactory getQuartzFactory();

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
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.quartz.quartz.impl.QuartzConsumesImpl <em>Consumes</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.quartz.quartz.impl.QuartzConsumesImpl
		 * @see com.ebmwebsourcing.petals.services.quartz.quartz.impl.QuartzPackageImpl#getQuartzConsumes()
		 * @generated
		 */
		EClass QUARTZ_CONSUMES = eINSTANCE.getQuartzConsumes();

		/**
		 * The meta object literal for the '<em><b>Cron Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUARTZ_CONSUMES__CRON_EXPRESSION = eINSTANCE.getQuartzConsumes_CronExpression();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUARTZ_CONSUMES__CONTENT = eINSTANCE.getQuartzConsumes_Content();

	}

} //QuartzPackage
