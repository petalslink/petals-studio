/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl;

import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Provides;
import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CDK5 Provides</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDK5ProvidesImpl#getValidateWsdl <em>Validate Wsdl</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDK5ProvidesImpl#getForwardSecuritySubject <em>Forward Security Subject</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDK5ProvidesImpl#getForwardMessageProperties <em>Forward Message Properties</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDK5ProvidesImpl#getForwardAttachments <em>Forward Attachments</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDK5ProvidesImpl#getWsdl <em>Wsdl</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDK5ProvidesImpl#getRetryPolicy <em>Retry Policy</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CDK5ProvidesImpl extends CDKServiceImpl implements CDK5Provides {
	/**
	 * The default value of the '{@link #getValidateWsdl() <em>Validate Wsdl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidateWsdl()
	 * @generated
	 * @ordered
	 */
	protected static final String VALIDATE_WSDL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValidateWsdl() <em>Validate Wsdl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidateWsdl()
	 * @generated
	 * @ordered
	 */
	protected String validateWsdl = VALIDATE_WSDL_EDEFAULT;

	/**
	 * The default value of the '{@link #getForwardSecuritySubject() <em>Forward Security Subject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForwardSecuritySubject()
	 * @generated
	 * @ordered
	 */
	protected static final String FORWARD_SECURITY_SUBJECT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getForwardSecuritySubject() <em>Forward Security Subject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForwardSecuritySubject()
	 * @generated
	 * @ordered
	 */
	protected String forwardSecuritySubject = FORWARD_SECURITY_SUBJECT_EDEFAULT;

	/**
	 * The default value of the '{@link #getForwardMessageProperties() <em>Forward Message Properties</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForwardMessageProperties()
	 * @generated
	 * @ordered
	 */
	protected static final String FORWARD_MESSAGE_PROPERTIES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getForwardMessageProperties() <em>Forward Message Properties</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForwardMessageProperties()
	 * @generated
	 * @ordered
	 */
	protected String forwardMessageProperties = FORWARD_MESSAGE_PROPERTIES_EDEFAULT;

	/**
	 * The default value of the '{@link #getForwardAttachments() <em>Forward Attachments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForwardAttachments()
	 * @generated
	 * @ordered
	 */
	protected static final String FORWARD_ATTACHMENTS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getForwardAttachments() <em>Forward Attachments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForwardAttachments()
	 * @generated
	 * @ordered
	 */
	protected String forwardAttachments = FORWARD_ATTACHMENTS_EDEFAULT;

	/**
	 * The default value of the '{@link #getWsdl() <em>Wsdl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWsdl()
	 * @generated
	 * @ordered
	 */
	protected static final String WSDL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWsdl() <em>Wsdl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWsdl()
	 * @generated
	 * @ordered
	 */
	protected String wsdl = WSDL_EDEFAULT;

	/**
	 * The default value of the '{@link #getRetryPolicy() <em>Retry Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRetryPolicy()
	 * @generated
	 * @ordered
	 */
	protected static final String RETRY_POLICY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRetryPolicy() <em>Retry Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRetryPolicy()
	 * @generated
	 * @ordered
	 */
	protected String retryPolicy = RETRY_POLICY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CDK5ProvidesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Cdk5Package.Literals.CDK5_PROVIDES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValidateWsdl() {
		return validateWsdl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValidateWsdl(String newValidateWsdl) {
		String oldValidateWsdl = validateWsdl;
		validateWsdl = newValidateWsdl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Cdk5Package.CDK5_PROVIDES__VALIDATE_WSDL, oldValidateWsdl, validateWsdl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getForwardSecuritySubject() {
		return forwardSecuritySubject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setForwardSecuritySubject(String newForwardSecuritySubject) {
		String oldForwardSecuritySubject = forwardSecuritySubject;
		forwardSecuritySubject = newForwardSecuritySubject;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Cdk5Package.CDK5_PROVIDES__FORWARD_SECURITY_SUBJECT, oldForwardSecuritySubject, forwardSecuritySubject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getForwardMessageProperties() {
		return forwardMessageProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setForwardMessageProperties(String newForwardMessageProperties) {
		String oldForwardMessageProperties = forwardMessageProperties;
		forwardMessageProperties = newForwardMessageProperties;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Cdk5Package.CDK5_PROVIDES__FORWARD_MESSAGE_PROPERTIES, oldForwardMessageProperties, forwardMessageProperties));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getForwardAttachments() {
		return forwardAttachments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setForwardAttachments(String newForwardAttachments) {
		String oldForwardAttachments = forwardAttachments;
		forwardAttachments = newForwardAttachments;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Cdk5Package.CDK5_PROVIDES__FORWARD_ATTACHMENTS, oldForwardAttachments, forwardAttachments));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWsdl() {
		return wsdl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWsdl(String newWsdl) {
		String oldWsdl = wsdl;
		wsdl = newWsdl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Cdk5Package.CDK5_PROVIDES__WSDL, oldWsdl, wsdl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRetryPolicy() {
		return retryPolicy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRetryPolicy(String newRetryPolicy) {
		String oldRetryPolicy = retryPolicy;
		retryPolicy = newRetryPolicy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Cdk5Package.CDK5_PROVIDES__RETRY_POLICY, oldRetryPolicy, retryPolicy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Cdk5Package.CDK5_PROVIDES__VALIDATE_WSDL:
				return getValidateWsdl();
			case Cdk5Package.CDK5_PROVIDES__FORWARD_SECURITY_SUBJECT:
				return getForwardSecuritySubject();
			case Cdk5Package.CDK5_PROVIDES__FORWARD_MESSAGE_PROPERTIES:
				return getForwardMessageProperties();
			case Cdk5Package.CDK5_PROVIDES__FORWARD_ATTACHMENTS:
				return getForwardAttachments();
			case Cdk5Package.CDK5_PROVIDES__WSDL:
				return getWsdl();
			case Cdk5Package.CDK5_PROVIDES__RETRY_POLICY:
				return getRetryPolicy();
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
			case Cdk5Package.CDK5_PROVIDES__VALIDATE_WSDL:
				setValidateWsdl((String)newValue);
				return;
			case Cdk5Package.CDK5_PROVIDES__FORWARD_SECURITY_SUBJECT:
				setForwardSecuritySubject((String)newValue);
				return;
			case Cdk5Package.CDK5_PROVIDES__FORWARD_MESSAGE_PROPERTIES:
				setForwardMessageProperties((String)newValue);
				return;
			case Cdk5Package.CDK5_PROVIDES__FORWARD_ATTACHMENTS:
				setForwardAttachments((String)newValue);
				return;
			case Cdk5Package.CDK5_PROVIDES__WSDL:
				setWsdl((String)newValue);
				return;
			case Cdk5Package.CDK5_PROVIDES__RETRY_POLICY:
				setRetryPolicy((String)newValue);
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
			case Cdk5Package.CDK5_PROVIDES__VALIDATE_WSDL:
				setValidateWsdl(VALIDATE_WSDL_EDEFAULT);
				return;
			case Cdk5Package.CDK5_PROVIDES__FORWARD_SECURITY_SUBJECT:
				setForwardSecuritySubject(FORWARD_SECURITY_SUBJECT_EDEFAULT);
				return;
			case Cdk5Package.CDK5_PROVIDES__FORWARD_MESSAGE_PROPERTIES:
				setForwardMessageProperties(FORWARD_MESSAGE_PROPERTIES_EDEFAULT);
				return;
			case Cdk5Package.CDK5_PROVIDES__FORWARD_ATTACHMENTS:
				setForwardAttachments(FORWARD_ATTACHMENTS_EDEFAULT);
				return;
			case Cdk5Package.CDK5_PROVIDES__WSDL:
				setWsdl(WSDL_EDEFAULT);
				return;
			case Cdk5Package.CDK5_PROVIDES__RETRY_POLICY:
				setRetryPolicy(RETRY_POLICY_EDEFAULT);
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
			case Cdk5Package.CDK5_PROVIDES__VALIDATE_WSDL:
				return VALIDATE_WSDL_EDEFAULT == null ? validateWsdl != null : !VALIDATE_WSDL_EDEFAULT.equals(validateWsdl);
			case Cdk5Package.CDK5_PROVIDES__FORWARD_SECURITY_SUBJECT:
				return FORWARD_SECURITY_SUBJECT_EDEFAULT == null ? forwardSecuritySubject != null : !FORWARD_SECURITY_SUBJECT_EDEFAULT.equals(forwardSecuritySubject);
			case Cdk5Package.CDK5_PROVIDES__FORWARD_MESSAGE_PROPERTIES:
				return FORWARD_MESSAGE_PROPERTIES_EDEFAULT == null ? forwardMessageProperties != null : !FORWARD_MESSAGE_PROPERTIES_EDEFAULT.equals(forwardMessageProperties);
			case Cdk5Package.CDK5_PROVIDES__FORWARD_ATTACHMENTS:
				return FORWARD_ATTACHMENTS_EDEFAULT == null ? forwardAttachments != null : !FORWARD_ATTACHMENTS_EDEFAULT.equals(forwardAttachments);
			case Cdk5Package.CDK5_PROVIDES__WSDL:
				return WSDL_EDEFAULT == null ? wsdl != null : !WSDL_EDEFAULT.equals(wsdl);
			case Cdk5Package.CDK5_PROVIDES__RETRY_POLICY:
				return RETRY_POLICY_EDEFAULT == null ? retryPolicy != null : !RETRY_POLICY_EDEFAULT.equals(retryPolicy);
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
		result.append(" (validateWsdl: ");
		result.append(validateWsdl);
		result.append(", forwardSecuritySubject: ");
		result.append(forwardSecuritySubject);
		result.append(", forwardMessageProperties: ");
		result.append(forwardMessageProperties);
		result.append(", forwardAttachments: ");
		result.append(forwardAttachments);
		result.append(", wsdl: ");
		result.append(wsdl);
		result.append(", retryPolicy: ");
		result.append(retryPolicy);
		result.append(')');
		return result.toString();
	}

} //CDK5ProvidesImpl
