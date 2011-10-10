/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5;

import com.sun.java.xml.ns.jbi.Provides;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CDK5 Provides</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides#getValidateWsdl <em>Validate Wsdl</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides#getForwardSecuritySubject <em>Forward Security Subject</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides#getForwardMessageProperties <em>Forward Message Properties</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides#getForwardAttachments <em>Forward Attachments</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides#getWsdl <em>Wsdl</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides#getRetryPolicy <em>Retry Policy</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package#getCDK5Provides()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface CDK5Provides extends CDKService, Provides {
	/**
	 * Returns the value of the '<em><b>Validate Wsdl</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Validate Wsdl</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Validate Wsdl</em>' attribute.
	 * @see #setValidateWsdl(String)
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package#getCDK5Provides_ValidateWsdl()
	 * @model derived="true"
	 *        extendedMetaData="kind='element' namespace='http://petals.ow2.org/components/extensions/version-5' name='validate-wsdl' group='#cdkExtContainer'"
	 * @generated
	 */
	String getValidateWsdl();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides#getValidateWsdl <em>Validate Wsdl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Validate Wsdl</em>' attribute.
	 * @see #getValidateWsdl()
	 * @generated
	 */
	void setValidateWsdl(String value);

	/**
	 * Returns the value of the '<em><b>Forward Security Subject</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Forward Security Subject</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Forward Security Subject</em>' attribute.
	 * @see #setForwardSecuritySubject(String)
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package#getCDK5Provides_ForwardSecuritySubject()
	 * @model derived="true"
	 *        extendedMetaData="kind='element' namespace='http://petals.ow2.org/components/extensions/version-5' name='forward-security-subject' group='#cdkExtContainer'"
	 * @generated
	 */
	String getForwardSecuritySubject();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides#getForwardSecuritySubject <em>Forward Security Subject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Forward Security Subject</em>' attribute.
	 * @see #getForwardSecuritySubject()
	 * @generated
	 */
	void setForwardSecuritySubject(String value);

	/**
	 * Returns the value of the '<em><b>Forward Message Properties</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Forward Message Properties</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Forward Message Properties</em>' attribute.
	 * @see #setForwardMessageProperties(String)
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package#getCDK5Provides_ForwardMessageProperties()
	 * @model derived="true"
	 *        extendedMetaData="kind='element' namespace='http://petals.ow2.org/components/extensions/version-5' name='forward-message-properties' group='#cdkExtContainer'"
	 * @generated
	 */
	String getForwardMessageProperties();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides#getForwardMessageProperties <em>Forward Message Properties</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Forward Message Properties</em>' attribute.
	 * @see #getForwardMessageProperties()
	 * @generated
	 */
	void setForwardMessageProperties(String value);

	/**
	 * Returns the value of the '<em><b>Forward Attachments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Forward Attachments</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Forward Attachments</em>' attribute.
	 * @see #setForwardAttachments(String)
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package#getCDK5Provides_ForwardAttachments()
	 * @model derived="true"
	 *        extendedMetaData="kind='element' namespace='http://petals.ow2.org/components/extensions/version-5' name='forward-attachments' group='#cdkExtContainer'"
	 * @generated
	 */
	String getForwardAttachments();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides#getForwardAttachments <em>Forward Attachments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Forward Attachments</em>' attribute.
	 * @see #getForwardAttachments()
	 * @generated
	 */
	void setForwardAttachments(String value);

	/**
	 * Returns the value of the '<em><b>Wsdl</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wsdl</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wsdl</em>' attribute.
	 * @see #setWsdl(String)
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package#getCDK5Provides_Wsdl()
	 * @model derived="true"
	 *        extendedMetaData="kind='element' namespace='http://petals.ow2.org/components/extensions/version-5' name='wsdl' group='#cdkExtContainer'"
	 * @generated
	 */
	String getWsdl();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides#getWsdl <em>Wsdl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wsdl</em>' attribute.
	 * @see #getWsdl()
	 * @generated
	 */
	void setWsdl(String value);

	/**
	 * Returns the value of the '<em><b>Retry Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Retry Policy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Retry Policy</em>' attribute.
	 * @see #setRetryPolicy(String)
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package#getCDK5Provides_RetryPolicy()
	 * @model derived="true"
	 *        extendedMetaData="kind='element' namespace='http://petals.ow2.org/components/extensions/version-5' name='validate-wsdl' group='#cdkExtContainer'"
	 * @generated
	 */
	String getRetryPolicy();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides#getRetryPolicy <em>Retry Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Retry Policy</em>' attribute.
	 * @see #getRetryPolicy()
	 * @generated
	 */
	void setRetryPolicy(String value);

} // CDK5Provides
