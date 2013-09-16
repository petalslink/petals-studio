/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.cdk.cdk5.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Provides;
import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;
import com.sun.java.xml.ns.jbi.impl.ProvidesImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CDK5 Provides</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.cdk.cdk5.impl.CDK5ProvidesImpl#getTimeout <em>Timeout</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.cdk.cdk5.impl.CDK5ProvidesImpl#isValidateWsdl <em>Validate Wsdl</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.cdk.cdk5.impl.CDK5ProvidesImpl#isForwardSecuritySubject <em>Forward Security Subject</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.cdk.cdk5.impl.CDK5ProvidesImpl#isForwardMessageProperties <em>Forward Message Properties</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.cdk.cdk5.impl.CDK5ProvidesImpl#isForwardAttachments <em>Forward Attachments</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.cdk.cdk5.impl.CDK5ProvidesImpl#getWsdl <em>Wsdl</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CDK5ProvidesImpl extends ProvidesImpl implements CDK5Provides {
	/**
	 * The default value of the '{@link #getTimeout() <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeout()
	 * @generated
	 * @ordered
	 */
	protected static final int TIMEOUT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTimeout() <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeout()
	 * @generated
	 * @ordered
	 */
	protected int timeout = TIMEOUT_EDEFAULT;

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
	 * This is true if the Validate Wsdl attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean validateWsdlESet;

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
	@Override
	public int getTimeout() {
		return this.timeout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTimeout(int newTimeout) {
		int oldTimeout = this.timeout;
		this.timeout = newTimeout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Cdk5Package.CDK5_PROVIDES__TIMEOUT, oldTimeout, this.timeout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isValidateWsdl() {
		return this.validateWsdl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setValidateWsdl(boolean newValidateWsdl) {
		boolean oldValidateWsdl = this.validateWsdl;
		this.validateWsdl = newValidateWsdl;
		boolean oldValidateWsdlESet = this.validateWsdlESet;
		this.validateWsdlESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Cdk5Package.CDK5_PROVIDES__VALIDATE_WSDL, oldValidateWsdl, this.validateWsdl, !oldValidateWsdlESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetValidateWsdl() {
		boolean oldValidateWsdl = this.validateWsdl;
		boolean oldValidateWsdlESet = this.validateWsdlESet;
		this.validateWsdl = VALIDATE_WSDL_EDEFAULT;
		this.validateWsdlESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, Cdk5Package.CDK5_PROVIDES__VALIDATE_WSDL, oldValidateWsdl, VALIDATE_WSDL_EDEFAULT, oldValidateWsdlESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetValidateWsdl() {
		return this.validateWsdlESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isForwardSecuritySubject() {
		return this.forwardSecuritySubject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setForwardSecuritySubject(boolean newForwardSecuritySubject) {
		boolean oldForwardSecuritySubject = this.forwardSecuritySubject;
		this.forwardSecuritySubject = newForwardSecuritySubject;
		boolean oldForwardSecuritySubjectESet = this.forwardSecuritySubjectESet;
		this.forwardSecuritySubjectESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Cdk5Package.CDK5_PROVIDES__FORWARD_SECURITY_SUBJECT, oldForwardSecuritySubject, this.forwardSecuritySubject, !oldForwardSecuritySubjectESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetForwardSecuritySubject() {
		boolean oldForwardSecuritySubject = this.forwardSecuritySubject;
		boolean oldForwardSecuritySubjectESet = this.forwardSecuritySubjectESet;
		this.forwardSecuritySubject = FORWARD_SECURITY_SUBJECT_EDEFAULT;
		this.forwardSecuritySubjectESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, Cdk5Package.CDK5_PROVIDES__FORWARD_SECURITY_SUBJECT, oldForwardSecuritySubject, FORWARD_SECURITY_SUBJECT_EDEFAULT, oldForwardSecuritySubjectESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetForwardSecuritySubject() {
		return this.forwardSecuritySubjectESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isForwardMessageProperties() {
		return this.forwardMessageProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setForwardMessageProperties(boolean newForwardMessageProperties) {
		boolean oldForwardMessageProperties = this.forwardMessageProperties;
		this.forwardMessageProperties = newForwardMessageProperties;
		boolean oldForwardMessagePropertiesESet = this.forwardMessagePropertiesESet;
		this.forwardMessagePropertiesESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Cdk5Package.CDK5_PROVIDES__FORWARD_MESSAGE_PROPERTIES, oldForwardMessageProperties, this.forwardMessageProperties, !oldForwardMessagePropertiesESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetForwardMessageProperties() {
		boolean oldForwardMessageProperties = this.forwardMessageProperties;
		boolean oldForwardMessagePropertiesESet = this.forwardMessagePropertiesESet;
		this.forwardMessageProperties = FORWARD_MESSAGE_PROPERTIES_EDEFAULT;
		this.forwardMessagePropertiesESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, Cdk5Package.CDK5_PROVIDES__FORWARD_MESSAGE_PROPERTIES, oldForwardMessageProperties, FORWARD_MESSAGE_PROPERTIES_EDEFAULT, oldForwardMessagePropertiesESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetForwardMessageProperties() {
		return this.forwardMessagePropertiesESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isForwardAttachments() {
		return this.forwardAttachments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setForwardAttachments(boolean newForwardAttachments) {
		boolean oldForwardAttachments = this.forwardAttachments;
		this.forwardAttachments = newForwardAttachments;
		boolean oldForwardAttachmentsESet = this.forwardAttachmentsESet;
		this.forwardAttachmentsESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Cdk5Package.CDK5_PROVIDES__FORWARD_ATTACHMENTS, oldForwardAttachments, this.forwardAttachments, !oldForwardAttachmentsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetForwardAttachments() {
		boolean oldForwardAttachments = this.forwardAttachments;
		boolean oldForwardAttachmentsESet = this.forwardAttachmentsESet;
		this.forwardAttachments = FORWARD_ATTACHMENTS_EDEFAULT;
		this.forwardAttachmentsESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, Cdk5Package.CDK5_PROVIDES__FORWARD_ATTACHMENTS, oldForwardAttachments, FORWARD_ATTACHMENTS_EDEFAULT, oldForwardAttachmentsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetForwardAttachments() {
		return this.forwardAttachmentsESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getWsdl() {
		return this.wsdl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWsdl(String newWsdl) {
		String oldWsdl = this.wsdl;
		this.wsdl = newWsdl;
		boolean oldWsdlESet = this.wsdlESet;
		this.wsdlESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Cdk5Package.CDK5_PROVIDES__WSDL, oldWsdl, this.wsdl, !oldWsdlESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetWsdl() {
		String oldWsdl = this.wsdl;
		boolean oldWsdlESet = this.wsdlESet;
		this.wsdl = WSDL_EDEFAULT;
		this.wsdlESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, Cdk5Package.CDK5_PROVIDES__WSDL, oldWsdl, WSDL_EDEFAULT, oldWsdlESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetWsdl() {
		return this.wsdlESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Cdk5Package.CDK5_PROVIDES__TIMEOUT:
				return getTimeout();
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
			case Cdk5Package.CDK5_PROVIDES__TIMEOUT:
				setTimeout((Integer)newValue);
				return;
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
			case Cdk5Package.CDK5_PROVIDES__TIMEOUT:
				setTimeout(TIMEOUT_EDEFAULT);
				return;
			case Cdk5Package.CDK5_PROVIDES__VALIDATE_WSDL:
				unsetValidateWsdl();
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
			case Cdk5Package.CDK5_PROVIDES__TIMEOUT:
				return this.timeout != TIMEOUT_EDEFAULT;
			case Cdk5Package.CDK5_PROVIDES__VALIDATE_WSDL:
				return isSetValidateWsdl();
			case Cdk5Package.CDK5_PROVIDES__FORWARD_SECURITY_SUBJECT:
				return isSetForwardSecuritySubject();
			case Cdk5Package.CDK5_PROVIDES__FORWARD_MESSAGE_PROPERTIES:
				return isSetForwardMessageProperties();
			case Cdk5Package.CDK5_PROVIDES__FORWARD_ATTACHMENTS:
				return isSetForwardAttachments();
			case Cdk5Package.CDK5_PROVIDES__WSDL:
				return isSetWsdl();
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
		result.append(" (timeout: ");
		result.append(this.timeout);
		result.append(", validateWsdl: ");
		if (this.validateWsdlESet) result.append(this.validateWsdl); else result.append("<unset>");
		result.append(", forwardSecuritySubject: ");
		if (this.forwardSecuritySubjectESet) result.append(this.forwardSecuritySubject); else result.append("<unset>");
		result.append(", forwardMessageProperties: ");
		if (this.forwardMessagePropertiesESet) result.append(this.forwardMessageProperties); else result.append("<unset>");
		result.append(", forwardAttachments: ");
		if (this.forwardAttachmentsESet) result.append(this.forwardAttachments); else result.append("<unset>");
		result.append(", wsdl: ");
		if (this.wsdlESet) result.append(this.wsdl); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //CDK5ProvidesImpl
