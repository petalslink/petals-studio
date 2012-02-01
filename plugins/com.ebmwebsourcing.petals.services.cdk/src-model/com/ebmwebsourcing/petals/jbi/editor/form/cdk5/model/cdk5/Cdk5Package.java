/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5;

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
 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Factory
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
	Cdk5Package eINSTANCE = com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.Cdk5PackageImpl.init();

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDKServiceImpl <em>CDK Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDKServiceImpl
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.Cdk5PackageImpl#getCDKService()
	 * @generated
	 */
	int CDK_SERVICE = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK_SERVICE__GROUP = JbiPackage.ABSTRACT_ENDPOINT__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK_SERVICE__OTHER = JbiPackage.ABSTRACT_ENDPOINT__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK_SERVICE__LOCAL = JbiPackage.ABSTRACT_ENDPOINT__LOCAL;

	/**
	 * The feature id for the '<em><b>Endpoint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK_SERVICE__ENDPOINT_NAME = JbiPackage.ABSTRACT_ENDPOINT__ENDPOINT_NAME;

	/**
	 * The feature id for the '<em><b>Interface Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK_SERVICE__INTERFACE_NAME = JbiPackage.ABSTRACT_ENDPOINT__INTERFACE_NAME;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK_SERVICE__SERVICE_NAME = JbiPackage.ABSTRACT_ENDPOINT__SERVICE_NAME;

	/**
	 * The feature id for the '<em><b>Cdk Ext Container</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK_SERVICE__CDK_EXT_CONTAINER = JbiPackage.ABSTRACT_ENDPOINT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK_SERVICE__TIMEOUT = JbiPackage.ABSTRACT_ENDPOINT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Su Interceptors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK_SERVICE__SU_INTERCEPTORS = JbiPackage.ABSTRACT_ENDPOINT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>CDK Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK_SERVICE_FEATURE_COUNT = JbiPackage.ABSTRACT_ENDPOINT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDK5ProvidesImpl <em>CDK5 Provides</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDK5ProvidesImpl
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.Cdk5PackageImpl#getCDK5Provides()
	 * @generated
	 */
	int CDK5_PROVIDES = 1;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__GROUP = CDK_SERVICE__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__OTHER = CDK_SERVICE__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__LOCAL = CDK_SERVICE__LOCAL;

	/**
	 * The feature id for the '<em><b>Endpoint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__ENDPOINT_NAME = CDK_SERVICE__ENDPOINT_NAME;

	/**
	 * The feature id for the '<em><b>Interface Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__INTERFACE_NAME = CDK_SERVICE__INTERFACE_NAME;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__SERVICE_NAME = CDK_SERVICE__SERVICE_NAME;

	/**
	 * The feature id for the '<em><b>Cdk Ext Container</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__CDK_EXT_CONTAINER = CDK_SERVICE__CDK_EXT_CONTAINER;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__TIMEOUT = CDK_SERVICE__TIMEOUT;

	/**
	 * The feature id for the '<em><b>Su Interceptors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__SU_INTERCEPTORS = CDK_SERVICE__SU_INTERCEPTORS;

	/**
	 * The feature id for the '<em><b>Validate Wsdl</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__VALIDATE_WSDL = CDK_SERVICE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Forward Security Subject</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__FORWARD_SECURITY_SUBJECT = CDK_SERVICE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Forward Message Properties</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__FORWARD_MESSAGE_PROPERTIES = CDK_SERVICE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Forward Attachments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__FORWARD_ATTACHMENTS = CDK_SERVICE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Wsdl</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__WSDL = CDK_SERVICE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Retry Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES__RETRY_POLICY = CDK_SERVICE_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>CDK5 Provides</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_PROVIDES_FEATURE_COUNT = CDK_SERVICE_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDK5ConsumesImpl <em>CDK5 Consumes</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDK5ConsumesImpl
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.Cdk5PackageImpl#getCDK5Consumes()
	 * @generated
	 */
	int CDK5_CONSUMES = 2;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_CONSUMES__GROUP = CDK_SERVICE__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_CONSUMES__OTHER = CDK_SERVICE__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_CONSUMES__LOCAL = CDK_SERVICE__LOCAL;

	/**
	 * The feature id for the '<em><b>Endpoint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_CONSUMES__ENDPOINT_NAME = CDK_SERVICE__ENDPOINT_NAME;

	/**
	 * The feature id for the '<em><b>Interface Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_CONSUMES__INTERFACE_NAME = CDK_SERVICE__INTERFACE_NAME;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_CONSUMES__SERVICE_NAME = CDK_SERVICE__SERVICE_NAME;

	/**
	 * The feature id for the '<em><b>Cdk Ext Container</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_CONSUMES__CDK_EXT_CONTAINER = CDK_SERVICE__CDK_EXT_CONTAINER;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_CONSUMES__TIMEOUT = CDK_SERVICE__TIMEOUT;

	/**
	 * The feature id for the '<em><b>Su Interceptors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_CONSUMES__SU_INTERCEPTORS = CDK_SERVICE__SU_INTERCEPTORS;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_CONSUMES__OPERATION = CDK_SERVICE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Mep</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_CONSUMES__MEP = CDK_SERVICE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>CDK5 Consumes</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CDK5_CONSUMES_FEATURE_COUNT = CDK_SERVICE_FEATURE_COUNT + 2;


	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Mep <em>Mep</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Mep
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.Cdk5PackageImpl#getMep()
	 * @generated
	 */
	int MEP = 3;


	/**
	 * Returns the meta object for class '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDKService <em>CDK Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CDK Service</em>'.
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDKService
	 * @generated
	 */
	EClass getCDKService();

	/**
	 * Returns the meta object for the attribute list '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDKService#getCdkExtContainer <em>Cdk Ext Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Cdk Ext Container</em>'.
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDKService#getCdkExtContainer()
	 * @see #getCDKService()
	 * @generated
	 */
	EAttribute getCDKService_CdkExtContainer();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDKService#getTimeout <em>Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timeout</em>'.
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDKService#getTimeout()
	 * @see #getCDKService()
	 * @generated
	 */
	EAttribute getCDKService_Timeout();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDKService#getSuInterceptors <em>Su Interceptors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Su Interceptors</em>'.
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDKService#getSuInterceptors()
	 * @see #getCDKService()
	 * @generated
	 */
	EAttribute getCDKService_SuInterceptors();

	/**
	 * Returns the meta object for class '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides <em>CDK5 Provides</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CDK5 Provides</em>'.
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides
	 * @generated
	 */
	EClass getCDK5Provides();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides#isValidateWsdl <em>Validate Wsdl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Validate Wsdl</em>'.
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides#isValidateWsdl()
	 * @see #getCDK5Provides()
	 * @generated
	 */
	EAttribute getCDK5Provides_ValidateWsdl();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides#isForwardSecuritySubject <em>Forward Security Subject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Forward Security Subject</em>'.
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides#isForwardSecuritySubject()
	 * @see #getCDK5Provides()
	 * @generated
	 */
	EAttribute getCDK5Provides_ForwardSecuritySubject();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides#isForwardMessageProperties <em>Forward Message Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Forward Message Properties</em>'.
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides#isForwardMessageProperties()
	 * @see #getCDK5Provides()
	 * @generated
	 */
	EAttribute getCDK5Provides_ForwardMessageProperties();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides#isForwardAttachments <em>Forward Attachments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Forward Attachments</em>'.
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides#isForwardAttachments()
	 * @see #getCDK5Provides()
	 * @generated
	 */
	EAttribute getCDK5Provides_ForwardAttachments();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides#getWsdl <em>Wsdl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wsdl</em>'.
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides#getWsdl()
	 * @see #getCDK5Provides()
	 * @generated
	 */
	EAttribute getCDK5Provides_Wsdl();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides#getRetryPolicy <em>Retry Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Retry Policy</em>'.
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides#getRetryPolicy()
	 * @see #getCDK5Provides()
	 * @generated
	 */
	EAttribute getCDK5Provides_RetryPolicy();

	/**
	 * Returns the meta object for class '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Consumes <em>CDK5 Consumes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CDK5 Consumes</em>'.
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Consumes
	 * @generated
	 */
	EClass getCDK5Consumes();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Consumes#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operation</em>'.
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Consumes#getOperation()
	 * @see #getCDK5Consumes()
	 * @generated
	 */
	EAttribute getCDK5Consumes_Operation();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Consumes#getMep <em>Mep</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mep</em>'.
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Consumes#getMep()
	 * @see #getCDK5Consumes()
	 * @generated
	 */
	EAttribute getCDK5Consumes_Mep();

	/**
	 * Returns the meta object for enum '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Mep <em>Mep</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Mep</em>'.
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Mep
	 * @generated
	 */
	EEnum getMep();

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
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDKServiceImpl <em>CDK Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDKServiceImpl
		 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.Cdk5PackageImpl#getCDKService()
		 * @generated
		 */
		EClass CDK_SERVICE = eINSTANCE.getCDKService();

		/**
		 * The meta object literal for the '<em><b>Cdk Ext Container</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CDK_SERVICE__CDK_EXT_CONTAINER = eINSTANCE.getCDKService_CdkExtContainer();

		/**
		 * The meta object literal for the '<em><b>Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CDK_SERVICE__TIMEOUT = eINSTANCE.getCDKService_Timeout();

		/**
		 * The meta object literal for the '<em><b>Su Interceptors</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CDK_SERVICE__SU_INTERCEPTORS = eINSTANCE.getCDKService_SuInterceptors();

		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDK5ProvidesImpl <em>CDK5 Provides</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDK5ProvidesImpl
		 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.Cdk5PackageImpl#getCDK5Provides()
		 * @generated
		 */
		EClass CDK5_PROVIDES = eINSTANCE.getCDK5Provides();

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
		 * The meta object literal for the '<em><b>Retry Policy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CDK5_PROVIDES__RETRY_POLICY = eINSTANCE.getCDK5Provides_RetryPolicy();

		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDK5ConsumesImpl <em>CDK5 Consumes</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDK5ConsumesImpl
		 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.Cdk5PackageImpl#getCDK5Consumes()
		 * @generated
		 */
		EClass CDK5_CONSUMES = eINSTANCE.getCDK5Consumes();

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
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Mep <em>Mep</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Mep
		 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.Cdk5PackageImpl#getMep()
		 * @generated
		 */
		EEnum MEP = eINSTANCE.getMep();

	}

} //Cdk5Package
