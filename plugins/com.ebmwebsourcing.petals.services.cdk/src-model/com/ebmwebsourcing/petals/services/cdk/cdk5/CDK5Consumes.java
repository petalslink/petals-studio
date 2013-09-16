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

package com.ebmwebsourcing.petals.services.cdk.cdk5;

import javax.xml.namespace.QName;

import com.sun.java.xml.ns.jbi.Consumes;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CDK5 Consumes</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Consumes#getTimeout <em>Timeout</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Consumes#getOperation <em>Operation</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Consumes#getMep <em>Mep</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package#getCDK5Consumes()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface CDK5Consumes extends Consumes {
	/**
	 * Returns the value of the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timeout</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timeout</em>' attribute.
	 * @see #setTimeout(int)
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package#getCDK5Consumes_Timeout()
	 * @model derived="true"
	 *        extendedMetaData="kind='element' namespace='http://petals.ow2.org/components/extensions/version-5' name='timeout' group='#cdkExtContainer'"
	 * @generated
	 */
	int getTimeout();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Consumes#getTimeout <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timeout</em>' attribute.
	 * @see #getTimeout()
	 * @generated
	 */
	void setTimeout(int value);

	/**
	 * Returns the value of the '<em><b>Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation</em>' attribute.
	 * @see #setOperation(QName)
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package#getCDK5Consumes_Operation()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName" derived="true"
	 *        extendedMetaData="kind='element' namespace='http://petals.ow2.org/components/extensions/version-5' name='operation' group='#cdkExtContainer'"
	 * @generated
	 */
	QName getOperation();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Consumes#getOperation <em>Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation</em>' attribute.
	 * @see #getOperation()
	 * @generated
	 */
	void setOperation(QName value);

	/**
	 * Returns the value of the '<em><b>Mep</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mep</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mep</em>' attribute.
	 * @see #isSetMep()
	 * @see #unsetMep()
	 * @see #setMep(String)
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package#getCDK5Consumes_Mep()
	 * @model unsettable="true" required="true" derived="true"
	 *        extendedMetaData="kind='element' namespace='http://petals.ow2.org/components/extensions/version-5' group='#cdkExtContainer'"
	 * @generated
	 */
	String getMep();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Consumes#getMep <em>Mep</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mep</em>' attribute.
	 * @see #isSetMep()
	 * @see #unsetMep()
	 * @see #getMep()
	 * @generated
	 */
	void setMep(String value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Consumes#getMep <em>Mep</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMep()
	 * @see #getMep()
	 * @see #setMep(String)
	 * @generated
	 */
	void unsetMep();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Consumes#getMep <em>Mep</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Mep</em>' attribute is set.
	 * @see #unsetMep()
	 * @see #getMep()
	 * @see #setMep(String)
	 * @generated
	 */
	boolean isSetMep();

} // CDK5Consumes
