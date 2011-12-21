/**
 * Copyright (c) 2011, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 */
package com.ebmwebsourcing.petals.studio.services.xslt.xslt.impl;

import com.ebmwebsourcing.petals.studio.services.xslt.xslt.XSLTProvides;
import com.ebmwebsourcing.petals.studio.services.xslt.xslt.XsltPackage;

import com.sun.java.xml.ns.jbi.impl.ProvidesImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XSLT Provides</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.studio.services.xslt.xslt.impl.XSLTProvidesImpl#getStylesheet <em>Stylesheet</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.studio.services.xslt.xslt.impl.XSLTProvidesImpl#getOutputAttachmentName <em>Output Attachment Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSLTProvidesImpl extends ProvidesImpl implements XSLTProvides {
	/**
	 * The default value of the '{@link #getStylesheet() <em>Stylesheet</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStylesheet()
	 * @generated
	 * @ordered
	 */
	protected static final String STYLESHEET_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStylesheet() <em>Stylesheet</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStylesheet()
	 * @generated
	 * @ordered
	 */
	protected String stylesheet = STYLESHEET_EDEFAULT;

	/**
	 * The default value of the '{@link #getOutputAttachmentName() <em>Output Attachment Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputAttachmentName()
	 * @generated
	 * @ordered
	 */
	protected static final String OUTPUT_ATTACHMENT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOutputAttachmentName() <em>Output Attachment Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputAttachmentName()
	 * @generated
	 * @ordered
	 */
	protected String outputAttachmentName = OUTPUT_ATTACHMENT_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected XSLTProvidesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XsltPackage.Literals.XSLT_PROVIDES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStylesheet() {
		return stylesheet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStylesheet(String newStylesheet) {
		String oldStylesheet = stylesheet;
		stylesheet = newStylesheet;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XsltPackage.XSLT_PROVIDES__STYLESHEET, oldStylesheet, stylesheet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOutputAttachmentName() {
		return outputAttachmentName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutputAttachmentName(String newOutputAttachmentName) {
		String oldOutputAttachmentName = outputAttachmentName;
		outputAttachmentName = newOutputAttachmentName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XsltPackage.XSLT_PROVIDES__OUTPUT_ATTACHMENT_NAME, oldOutputAttachmentName, outputAttachmentName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case XsltPackage.XSLT_PROVIDES__STYLESHEET:
				return getStylesheet();
			case XsltPackage.XSLT_PROVIDES__OUTPUT_ATTACHMENT_NAME:
				return getOutputAttachmentName();
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
			case XsltPackage.XSLT_PROVIDES__STYLESHEET:
				setStylesheet((String)newValue);
				return;
			case XsltPackage.XSLT_PROVIDES__OUTPUT_ATTACHMENT_NAME:
				setOutputAttachmentName((String)newValue);
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
			case XsltPackage.XSLT_PROVIDES__STYLESHEET:
				setStylesheet(STYLESHEET_EDEFAULT);
				return;
			case XsltPackage.XSLT_PROVIDES__OUTPUT_ATTACHMENT_NAME:
				setOutputAttachmentName(OUTPUT_ATTACHMENT_NAME_EDEFAULT);
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
			case XsltPackage.XSLT_PROVIDES__STYLESHEET:
				return STYLESHEET_EDEFAULT == null ? stylesheet != null : !STYLESHEET_EDEFAULT.equals(stylesheet);
			case XsltPackage.XSLT_PROVIDES__OUTPUT_ATTACHMENT_NAME:
				return OUTPUT_ATTACHMENT_NAME_EDEFAULT == null ? outputAttachmentName != null : !OUTPUT_ATTACHMENT_NAME_EDEFAULT.equals(outputAttachmentName);
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
		result.append(" (stylesheet: ");
		result.append(stylesheet);
		result.append(", outputAttachmentName: ");
		result.append(outputAttachmentName);
		result.append(')');
		return result.toString();
	}

} //XSLTProvidesImpl
