/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.ebmwebsourcing.petals.services.cdk.cdk5;

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
 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Factory
 * @model kind="package"
 * @generated
 */
public interface Cdk5Package extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "cdk5";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://petals.ow2.org/components/extensions/version-5";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "cdk5";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Cdk5Package eINSTANCE = com.ebmwebsourcing.petals.services.cdk.cdk5.impl.Cdk5PackageImpl.init();

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.impl.CDK5ProvidesImpl <em>CDK5 Provides</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.impl.CDK5ProvidesImpl
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.impl.Cdk5PackageImpl#getCDK5Provides()
	 * @generated
	 */
	int CDK5_PROVIDES = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__GROUP = JbiPackage.PROVIDES__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__OTHER = JbiPackage.PROVIDES__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__LOCAL = JbiPackage.PROVIDES__LOCAL;

	/**
	 * The feature id for the '<em><b>Endpoint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__ENDPOINT_NAME = JbiPackage.PROVIDES__ENDPOINT_NAME;

	/**
	 * The feature id for the '<em><b>Interface Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__INTERFACE_NAME = JbiPackage.PROVIDES__INTERFACE_NAME;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__SERVICE_NAME = JbiPackage.PROVIDES__SERVICE_NAME;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__TIMEOUT = JbiPackage.PROVIDES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Validate Wsdl</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__VALIDATE_WSDL = JbiPackage.PROVIDES_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Forward Security Subject</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__FORWARD_SECURITY_SUBJECT = JbiPackage.PROVIDES_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Forward Message Properties</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__FORWARD_MESSAGE_PROPERTIES = JbiPackage.PROVIDES_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Forward Attachments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__FORWARD_ATTACHMENTS = JbiPackage.PROVIDES_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Wsdl</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__WSDL = JbiPackage.PROVIDES_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>CDK5 Provides</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES_FEATURE_COUNT = JbiPackage.PROVIDES_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.impl.CDK5ConsumesImpl <em>CDK5 Consumes</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.impl.CDK5ConsumesImpl
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.impl.Cdk5PackageImpl#getCDK5Consumes()
	 * @generated
	 */
	int CDK5_CONSUMES = 1;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_CONSUMES__GROUP = JbiPackage.CONSUMES__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_CONSUMES__OTHER = JbiPackage.CONSUMES__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_CONSUMES__LOCAL = JbiPackage.CONSUMES__LOCAL;

	/**
	 * The feature id for the '<em><b>Endpoint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_CONSUMES__ENDPOINT_NAME = JbiPackage.CONSUMES__ENDPOINT_NAME;

	/**
	 * The feature id for the '<em><b>Interface Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_CONSUMES__INTERFACE_NAME = JbiPackage.CONSUMES__INTERFACE_NAME;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_CONSUMES__SERVICE_NAME = JbiPackage.CONSUMES__SERVICE_NAME;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_CONSUMES__TIMEOUT = JbiPackage.CONSUMES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_CONSUMES__OPERATION = JbiPackage.CONSUMES_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Mep</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_CONSUMES__MEP = JbiPackage.CONSUMES_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>CDK5 Consumes</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_CONSUMES_FEATURE_COUNT = JbiPackage.CONSUMES_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.impl.RetryPolicyImpl <em>Retry Policy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.impl.RetryPolicyImpl
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.impl.Cdk5PackageImpl#getRetryPolicy()
	 * @generated
	 */
	int RETRY_POLICY = 2;

	/**
	 * The feature id for the '<em><b>Attempts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_POLICY__ATTEMPTS = 0;

	/**
	 * The feature id for the '<em><b>Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_POLICY__DELAY = 1;

	/**
	 * The number of structural features of the '<em>Retry Policy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_POLICY_FEATURE_COUNT = 2;


	/**
	 * Returns the meta object for class '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Provides <em>CDK5 Provides</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CDK5 Provides</em>'.
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Provides
	 * @generated
	 */
	EClass getCDK5Provides();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Provides#getTimeout <em>Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timeout</em>'.
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Provides#getTimeout()
	 * @see #getCDK5Provides()
	 * @generated
	 */
	EAttribute getCDK5Provides_Timeout();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Provides#isValidateWsdl <em>Validate Wsdl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Validate Wsdl</em>'.
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Provides#isValidateWsdl()
	 * @see #getCDK5Provides()
	 * @generated
	 */
	EAttribute getCDK5Provides_ValidateWsdl();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Provides#isForwardSecuritySubject <em>Forward Security Subject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Forward Security Subject</em>'.
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Provides#isForwardSecuritySubject()
	 * @see #getCDK5Provides()
	 * @generated
	 */
	EAttribute getCDK5Provides_ForwardSecuritySubject();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Provides#isForwardMessageProperties <em>Forward Message Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Forward Message Properties</em>'.
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Provides#isForwardMessageProperties()
	 * @see #getCDK5Provides()
	 * @generated
	 */
	EAttribute getCDK5Provides_ForwardMessageProperties();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Provides#isForwardAttachments <em>Forward Attachments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Forward Attachments</em>'.
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Provides#isForwardAttachments()
	 * @see #getCDK5Provides()
	 * @generated
	 */
	EAttribute getCDK5Provides_ForwardAttachments();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Provides#getWsdl <em>Wsdl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wsdl</em>'.
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Provides#getWsdl()
	 * @see #getCDK5Provides()
	 * @generated
	 */
	EAttribute getCDK5Provides_Wsdl();

	/**
	 * Returns the meta object for class '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Consumes <em>CDK5 Consumes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CDK5 Consumes</em>'.
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Consumes
	 * @generated
	 */
	EClass getCDK5Consumes();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Consumes#getTimeout <em>Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timeout</em>'.
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Consumes#getTimeout()
	 * @see #getCDK5Consumes()
	 * @generated
	 */
	EAttribute getCDK5Consumes_Timeout();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Consumes#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operation</em>'.
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Consumes#getOperation()
	 * @see #getCDK5Consumes()
	 * @generated
	 */
	EAttribute getCDK5Consumes_Operation();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Consumes#getMep <em>Mep</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mep</em>'.
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Consumes#getMep()
	 * @see #getCDK5Consumes()
	 * @generated
	 */
	EAttribute getCDK5Consumes_Mep();

	/**
	 * Returns the meta object for class '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.RetryPolicy <em>Retry Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Retry Policy</em>'.
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.RetryPolicy
	 * @generated
	 */
	EClass getRetryPolicy();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.RetryPolicy#getAttempts <em>Attempts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attempts</em>'.
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.RetryPolicy#getAttempts()
	 * @see #getRetryPolicy()
	 * @generated
	 */
	EAttribute getRetryPolicy_Attempts();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.RetryPolicy#getDelay <em>Delay</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Delay</em>'.
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.RetryPolicy#getDelay()
	 * @see #getRetryPolicy()
	 * @generated
	 */
	EAttribute getRetryPolicy_Delay();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Cdk5Factory getCdk5Factory();

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
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.impl.CDK5ProvidesImpl <em>CDK5 Provides</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.impl.CDK5ProvidesImpl
		 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.impl.Cdk5PackageImpl#getCDK5Provides()
		 * @generated
		 */
		EClass CDK5_PROVIDES = eINSTANCE.getCDK5Provides();

		/**
		 * The meta object literal for the '<em><b>Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CDK5_PROVIDES__TIMEOUT = eINSTANCE.getCDK5Provides_Timeout();

		/**
		 * The meta object literal for the '<em><b>Validate Wsdl</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CDK5_PROVIDES__VALIDATE_WSDL = eINSTANCE.getCDK5Provides_ValidateWsdl();

		/**
		 * The meta object literal for the '<em><b>Forward Security Subject</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CDK5_PROVIDES__FORWARD_SECURITY_SUBJECT = eINSTANCE.getCDK5Provides_ForwardSecuritySubject();

		/**
		 * The meta object literal for the '<em><b>Forward Message Properties</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CDK5_PROVIDES__FORWARD_MESSAGE_PROPERTIES = eINSTANCE.getCDK5Provides_ForwardMessageProperties();

		/**
		 * The meta object literal for the '<em><b>Forward Attachments</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CDK5_PROVIDES__FORWARD_ATTACHMENTS = eINSTANCE.getCDK5Provides_ForwardAttachments();

		/**
		 * The meta object literal for the '<em><b>Wsdl</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CDK5_PROVIDES__WSDL = eINSTANCE.getCDK5Provides_Wsdl();

		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.impl.CDK5ConsumesImpl <em>CDK5 Consumes</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.impl.CDK5ConsumesImpl
		 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.impl.Cdk5PackageImpl#getCDK5Consumes()
		 * @generated
		 */
		EClass CDK5_CONSUMES = eINSTANCE.getCDK5Consumes();

		/**
		 * The meta object literal for the '<em><b>Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CDK5_CONSUMES__TIMEOUT = eINSTANCE.getCDK5Consumes_Timeout();

		/**
		 * The meta object literal for the '<em><b>Operation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CDK5_CONSUMES__OPERATION = eINSTANCE.getCDK5Consumes_Operation();

		/**
		 * The meta object literal for the '<em><b>Mep</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CDK5_CONSUMES__MEP = eINSTANCE.getCDK5Consumes_Mep();

		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.impl.RetryPolicyImpl <em>Retry Policy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.impl.RetryPolicyImpl
		 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.impl.Cdk5PackageImpl#getRetryPolicy()
		 * @generated
		 */
		EClass RETRY_POLICY = eINSTANCE.getRetryPolicy();

		/**
		 * The meta object literal for the '<em><b>Attempts</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RETRY_POLICY__ATTEMPTS = eINSTANCE.getRetryPolicy_Attempts();

		/**
		 * The meta object literal for the '<em><b>Delay</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RETRY_POLICY__DELAY = eINSTANCE.getRetryPolicy_Delay();

	}

} //Cdk5Package
