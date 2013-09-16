/******************************************************************************
 * Copyright (c) 2009-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.sun.java.xml.ns.jbi;

import javax.xml.namespace.QName;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Endpoint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.sun.java.xml.ns.jbi.AbstractEndpoint#getEndpointName <em>Endpoint Name</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.AbstractEndpoint#getInterfaceName <em>Interface Name</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.AbstractEndpoint#getServiceName <em>Service Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.sun.java.xml.ns.jbi.JbiPackage#getAbstractEndpoint()
 * @model abstract="true"
 * @generated
 */
public interface AbstractEndpoint extends AbstractExtensibleElement {
	/**
	 * Returns the value of the '<em><b>Endpoint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Endpoint Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Endpoint Name</em>' attribute.
	 * @see #setEndpointName(String)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getAbstractEndpoint_EndpointName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='endpoint-name'"
	 * @generated
	 */
	String getEndpointName();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.AbstractEndpoint#getEndpointName <em>Endpoint Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Endpoint Name</em>' attribute.
	 * @see #getEndpointName()
	 * @generated
	 */
	void setEndpointName(String value);

	/**
	 * Returns the value of the '<em><b>Interface Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interface Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interface Name</em>' attribute.
	 * @see #setInterfaceName(QName)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getAbstractEndpoint_InterfaceName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
	 *        extendedMetaData="kind='attribute' name='interface-name'"
	 * @generated
	 */
	QName getInterfaceName();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.AbstractEndpoint#getInterfaceName <em>Interface Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interface Name</em>' attribute.
	 * @see #getInterfaceName()
	 * @generated
	 */
	void setInterfaceName(QName value);

	/**
	 * Returns the value of the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Service Name</em>' attribute.
	 * @see #setServiceName(QName)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getAbstractEndpoint_ServiceName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='attribute' name='service-name'"
	 * @generated
	 */
	QName getServiceName();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.AbstractEndpoint#getServiceName <em>Service Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Name</em>' attribute.
	 * @see #getServiceName()
	 * @generated
	 */
	void setServiceName(QName value);

} // AbstractEndpoint
