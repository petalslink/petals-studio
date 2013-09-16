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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Target</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.sun.java.xml.ns.jbi.Target#getArtifactsZip <em>Artifacts Zip</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.Target#getComponentName <em>Component Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.sun.java.xml.ns.jbi.JbiPackage#getTarget()
 * @model extendedMetaData="name='Target' kind='elementOnly'"
 * @generated
 */
public interface Target extends EObject {
	/**
	 * Returns the value of the '<em><b>Artifacts Zip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Artifacts Zip</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Artifacts Zip</em>' attribute.
	 * @see #setArtifactsZip(String)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getTarget_ArtifactsZip()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='artifacts-zip' namespace='##targetNamespace'"
	 * @generated
	 */
	String getArtifactsZip();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.Target#getArtifactsZip <em>Artifacts Zip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Artifacts Zip</em>' attribute.
	 * @see #getArtifactsZip()
	 * @generated
	 */
	void setArtifactsZip(String value);

	/**
	 * Returns the value of the '<em><b>Component Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Name</em>' attribute.
	 * @see #setComponentName(String)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getTarget_ComponentName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.NCName" required="true"
	 *        extendedMetaData="kind='element' name='component-name' namespace='##targetNamespace'"
	 * @generated
	 */
	String getComponentName();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.Target#getComponentName <em>Component Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Name</em>' attribute.
	 * @see #getComponentName()
	 * @generated
	 */
	void setComponentName(String value);

} // Target
