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
 *   <li>{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDK5ProvidesImpl#isValidateWsdl <em>Validate Wsdl</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDK5ProvidesImpl#isForwardSecuritySubject <em>Forward Security Subject</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDK5ProvidesImpl#isForwardMessageProperties <em>Forward Message Properties</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDK5ProvidesImpl#isForwardAttachments <em>Forward Attachments</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDK5ProvidesImpl#getWsdl <em>Wsdl</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDK5ProvidesImpl#getRetryPolicy <em>Retry Policy</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CDK5ProvidesImpl extends CDKServiceImpl implements CDK5Provides {
	/**
	 * The default value of the '{@link #isValidateWsdl() <em>Validate Wsdl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isValidateWsdl()
	 * @generated
	 * @ordered
	 */
	protected static final boolean VALIDATE_WSDL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isValidateWsdl() <em>Validate Wsdl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isValidateWsdl()
	 * @generated
	 * @ordered
	 */
	protected boolean validateWsdl = VALIDATE_WSDL_EDEFAULT;

	/**
	 * The default value of the '{@link #isForwardSecuritySubject() <em>Forward Security Subject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isForwardSecuritySubject()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FORWARD_SECURITY_SUBJECT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isForwardSecuritySubject() <em>Forward Security Subject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isForwardSecuritySubject()
	 * @generated
	 * @ordered
	 */
	protected boolean forwardSecuritySubject = FORWARD_SECURITY_SUBJECT_EDEFAULT;

	/**
	 * This is true if the Forward Security Subject attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean forwardSecuritySubjectESet;

	/**
	 * The default value of the '{@link #isForwardMessageProperties() <em>Forward Message Properties</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isForwardMessageProperties()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FORWARD_MESSAGE_PROPERTIES_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isForwardMessageProperties() <em>Forward Message Properties</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isForwardMessageProperties()
	 * @generated
	 * @ordered
	 */
	protected boolean forwardMessageProperties = FORWARD_MESSAGE_PROPERTIES_EDEFAULT;

	/**
	 * This is true if the Forward Message Properties attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean forwardMessagePropertiesESet;

	/**
	 * The default value of the '{@link #isForwardAttachments() <em>Forward Attachments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isForwardAttachments()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FORWARD_ATTACHMENTS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isForwardAttachments() <em>Forward Attachments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isForwardAttachments()
	 * @generated
	 * @ordered
	 */
	protected boolean forwardAttachments = FORWARD_ATTACHMENTS_EDEFAULT;

	/**
	 * This is true if the Forward Attachments attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean forwardAttachmentsESet;

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
	 * This is true if the Wsdl attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean wsdlESet;

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
	public boolean isValidateWsdl() {
		return validateWsdl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValidateWsdl(boolean newValidateWsdl) {
		boolean oldValidateWsdl = validateWsdl;
		validateWsdl = newValidateWsdl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Cdk5Package.CDK5_PROVIDES__VALIDATE_WSDL, oldValidateWsdl, validateWsdl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isForwardSecuritySubject() {
		return forwardSecuritySubject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setForwardSecuritySubject(boolean newForwardSecuritySubject) {
		boolean oldForwardSecuritySubject = forwardSecuritySubject;
		forwardSecuritySubject = newForwardSecuritySubject;
		boolean oldForwardSecuritySubjectESet = forwardSecuritySubjectESet;
		forwardSecuritySubjectESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Cdk5Package.CDK5_PROVIDES__FORWARD_SECURITY_SUBJECT, oldForwardSecuritySubject, forwardSecuritySubject, !oldForwardSecuritySubjectESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetForwardSecuritySubject() {
		boolean oldForwardSecuritySubject = forwardSecuritySubject;
		boolean oldForwardSecuritySubjectESet = forwardSecuritySubjectESet;
		forwardSecuritySubject = FORWARD_SECURITY_SUBJECT_EDEFAULT;
		forwardSecuritySubjectESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, Cdk5Package.CDK5_PROVIDES__FORWARD_SECURITY_SUBJECT, oldForwardSecuritySubject, FORWARD_SECURITY_SUBJECT_EDEFAULT, oldForwardSecuritySubjectESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetForwardSecuritySubject() {
		return forwardSecuritySubjectESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isForwardMessageProperties() {
		return forwardMessageProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setForwardMessageProperties(boolean newForwardMessageProperties) {
		boolean oldForwardMessageProperties = forwardMessageProperties;
		forwardMessageProperties = newForwardMessageProperties;
		boolean oldForwardMessagePropertiesESet = forwardMessagePropertiesESet;
		forwardMessagePropertiesESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Cdk5Package.CDK5_PROVIDES__FORWARD_MESSAGE_PROPERTIES, oldForwardMessageProperties, forwardMessageProperties, !oldForwardMessagePropertiesESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetForwardMessageProperties() {
		boolean oldForwardMessageProperties = forwardMessageProperties;
		boolean oldForwardMessagePropertiesESet = forwardMessagePropertiesESet;
		forwardMessageProperties = FORWARD_MESSAGE_PROPERTIES_EDEFAULT;
		forwardMessagePropertiesESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, Cdk5Package.CDK5_PROVIDES__FORWARD_MESSAGE_PROPERTIES, oldForwardMessageProperties, FORWARD_MESSAGE_PROPERTIES_EDEFAULT, oldForwardMessagePropertiesESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetForwardMessageProperties() {
		return forwardMessagePropertiesESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isForwardAttachments() {
		return forwardAttachments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setForwardAttachments(boolean newForwardAttachments) {
		boolean oldForwardAttachments = forwardAttachments;
		forwardAttachments = newForwardAttachments;
		boolean oldForwardAttachmentsESet = forwardAttachmentsESet;
		forwardAttachmentsESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Cdk5Package.CDK5_PROVIDES__FORWARD_ATTACHMENTS, oldForwardAttachments, forwardAttachments, !oldForwardAttachmentsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetForwardAttachments() {
		boolean oldForwardAttachments = forwardAttachments;
		boolean oldForwardAttachmentsESet = forwardAttachmentsESet;
		forwardAttachments = FORWARD_ATTACHMENTS_EDEFAULT;
		forwardAttachmentsESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, Cdk5Package.CDK5_PROVIDES__FORWARD_ATTACHMENTS, oldForwardAttachments, FORWARD_ATTACHMENTS_EDEFAULT, oldForwardAttachmentsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetForwardAttachments() {
		return forwardAttachmentsESet;
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
		boolean oldWsdlESet = wsdlESet;
		wsdlESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Cdk5Package.CDK5_PROVIDES__WSDL, oldWsdl, wsdl, !oldWsdlESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetWsdl() {
		String oldWsdl = wsdl;
		boolean oldWsdlESet = wsdlESet;
		wsdl = WSDL_EDEFAULT;
		wsdlESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, Cdk5Package.CDK5_PROVIDES__WSDL, oldWsdl, WSDL_EDEFAULT, oldWsdlESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetWsdl() {
		return wsdlESet;
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
				return isValidateWsdl();
			case Cdk5Package.CDK5_PROVIDES__FORWARD_SECURITY_SUBJECT:
				return isForwardSecuritySubject();
			case Cdk5Package.CDK5_PROVIDES__FORWARD_MESSAGE_PROPERTIES:
				return isForwardMessageProperties();
			case Cdk5Package.CDK5_PROVIDES__FORWARD_ATTACHMENTS:
				return isForwardAttachments();
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
				setValidateWsdl((Boolean)newValue);
				return;
			case Cdk5Package.CDK5_PROVIDES__FORWARD_SECURITY_SUBJECT:
				setForwardSecuritySubject((Boolean)newValue);
				return;
			case Cdk5Package.CDK5_PROVIDES__FORWARD_MESSAGE_PROPERTIES:
				setForwardMessageProperties((Boolean)newValue);
				return;
			case Cdk5Package.CDK5_PROVIDES__FORWARD_ATTACHMENTS:
				setForwardAttachments((Boolean)newValue);
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
				unsetForwardSecuritySubject();
				return;
			case Cdk5Package.CDK5_PROVIDES__FORWARD_MESSAGE_PROPERTIES:
				unsetForwardMessageProperties();
				return;
			case Cdk5Package.CDK5_PROVIDES__FORWARD_ATTACHMENTS:
				unsetForwardAttachments();
				return;
			case Cdk5Package.CDK5_PROVIDES__WSDL:
				unsetWsdl();
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
				return validateWsdl != VALIDATE_WSDL_EDEFAULT;
			case Cdk5Package.CDK5_PROVIDES__FORWARD_SECURITY_SUBJECT:
				return isSetForwardSecuritySubject();
			case Cdk5Package.CDK5_PROVIDES__FORWARD_MESSAGE_PROPERTIES:
				return isSetForwardMessageProperties();
			case Cdk5Package.CDK5_PROVIDES__FORWARD_ATTACHMENTS:
				return isSetForwardAttachments();
			case Cdk5Package.CDK5_PROVIDES__WSDL:
				return isSetWsdl();
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
		if (forwardSecuritySubjectESet) result.append(forwardSecuritySubject); else result.append("<unset>");
		result.append(", forwardMessageProperties: ");
		if (forwardMessagePropertiesESet) result.append(forwardMessageProperties); else result.append("<unset>");
		result.append(", forwardAttachments: ");
		if (forwardAttachmentsESet) result.append(forwardAttachments); else result.append("<unset>");
		result.append(", wsdl: ");
		if (wsdlESet) result.append(wsdl); else result.append("<unset>");
		result.append(", retryPolicy: ");
		result.append(retryPolicy);
		result.append(')');
		return result.toString();
	}

} //CDK5ProvidesImpl
