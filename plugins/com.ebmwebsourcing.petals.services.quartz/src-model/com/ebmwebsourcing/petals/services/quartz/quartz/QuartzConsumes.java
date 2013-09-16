/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.quartz.quartz;

import com.sun.java.xml.ns.jbi.Consumer;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Consumes</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.quartz.quartz.QuartzConsumes#getCronExpression <em>Cron Expression</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.quartz.quartz.QuartzConsumes#getContent <em>Content</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.quartz.quartz.QuartzPackage#getQuartzConsumes()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface QuartzConsumes extends Consumer {
	/**
	 * Returns the value of the '<em><b>Cron Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cron Expression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cron Expression</em>' attribute.
	 * @see #setCronExpression(String)
	 * @see com.ebmwebsourcing.petals.services.quartz.quartz.QuartzPackage#getQuartzConsumes_CronExpression()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="name='cron-expression' namespace='##targetNamespace' group='#group:0' kind='element'"
	 * @generated
	 */
	String getCronExpression();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.quartz.quartz.QuartzConsumes#getCronExpression <em>Cron Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cron Expression</em>' attribute.
	 * @see #getCronExpression()
	 * @generated
	 */
	void setCronExpression(String value);

	/**
	 * Returns the value of the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Content</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Content</em>' attribute.
	 * @see #setContent(String)
	 * @see com.ebmwebsourcing.petals.services.quartz.quartz.QuartzPackage#getQuartzConsumes_Content()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="name='content' namespace='##targetNamespace' group='#group:0' kind='element'"
	 * @generated
	 */
	String getContent();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.quartz.quartz.QuartzConsumes#getContent <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Content</em>' attribute.
	 * @see #getContent()
	 * @generated
	 */
	void setContent(String value);

} // QuartzConsumes
