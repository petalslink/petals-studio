/**
 * Copyright (c) 2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.quartz.quartz.impl;

import com.ebmwebsourcing.petals.services.quartz.quartz.QuartzConsumes;
import com.ebmwebsourcing.petals.services.quartz.quartz.QuartzPackage;

import com.sun.java.xml.ns.jbi.impl.ConsumerImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Consumes</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.quartz.quartz.impl.QuartzConsumesImpl#getCronExpression <em>Cron Expression</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.quartz.quartz.impl.QuartzConsumesImpl#getContent <em>Content</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QuartzConsumesImpl extends ConsumerImpl implements QuartzConsumes {
	/**
	 * The default value of the '{@link #getCronExpression() <em>Cron Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCronExpression()
	 * @generated
	 * @ordered
	 */
	protected static final String CRON_EXPRESSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCronExpression() <em>Cron Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCronExpression()
	 * @generated
	 * @ordered
	 */
	protected String cronExpression = CRON_EXPRESSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getContent() <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContent()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContent() <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContent()
	 * @generated
	 * @ordered
	 */
	protected String content = CONTENT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QuartzConsumesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QuartzPackage.Literals.QUARTZ_CONSUMES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCronExpression() {
		return cronExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCronExpression(String newCronExpression) {
		String oldCronExpression = cronExpression;
		cronExpression = newCronExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QuartzPackage.QUARTZ_CONSUMES__CRON_EXPRESSION, oldCronExpression, cronExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getContent() {
		return content;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContent(String newContent) {
		String oldContent = content;
		content = newContent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QuartzPackage.QUARTZ_CONSUMES__CONTENT, oldContent, content));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case QuartzPackage.QUARTZ_CONSUMES__CRON_EXPRESSION:
				return getCronExpression();
			case QuartzPackage.QUARTZ_CONSUMES__CONTENT:
				return getContent();
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
			case QuartzPackage.QUARTZ_CONSUMES__CRON_EXPRESSION:
				setCronExpression((String)newValue);
				return;
			case QuartzPackage.QUARTZ_CONSUMES__CONTENT:
				setContent((String)newValue);
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
			case QuartzPackage.QUARTZ_CONSUMES__CRON_EXPRESSION:
				setCronExpression(CRON_EXPRESSION_EDEFAULT);
				return;
			case QuartzPackage.QUARTZ_CONSUMES__CONTENT:
				setContent(CONTENT_EDEFAULT);
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
			case QuartzPackage.QUARTZ_CONSUMES__CRON_EXPRESSION:
				return CRON_EXPRESSION_EDEFAULT == null ? cronExpression != null : !CRON_EXPRESSION_EDEFAULT.equals(cronExpression);
			case QuartzPackage.QUARTZ_CONSUMES__CONTENT:
				return CONTENT_EDEFAULT == null ? content != null : !CONTENT_EDEFAULT.equals(content);
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
		result.append(" (cronExpression: ");
		result.append(cronExpression);
		result.append(", content: ");
		result.append(content);
		result.append(')');
		return result.toString();
	}

} //QuartzConsumesImpl
