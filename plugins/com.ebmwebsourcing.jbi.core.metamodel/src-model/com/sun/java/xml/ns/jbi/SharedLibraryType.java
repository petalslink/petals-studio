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
 * A representation of the model object '<em><b>Shared Library Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.sun.java.xml.ns.jbi.SharedLibraryType#getIdentification <em>Identification</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.SharedLibraryType#getSharedLibraryClassPath <em>Shared Library Class Path</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.SharedLibraryType#getClassLoaderDelegation <em>Class Loader Delegation</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.SharedLibraryType#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.sun.java.xml.ns.jbi.JbiPackage#getSharedLibraryType()
 * @model extendedMetaData="name='shared-library_._type' kind='elementOnly'"
 * @generated
 */
public interface SharedLibraryType extends EObject {
	/**
	 * Returns the value of the '<em><b>Identification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identification</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identification</em>' containment reference.
	 * @see #setIdentification(Identification)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getSharedLibraryType_Identification()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='identification' namespace='##targetNamespace'"
	 * @generated
	 */
	Identification getIdentification();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.SharedLibraryType#getIdentification <em>Identification</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identification</em>' containment reference.
	 * @see #getIdentification()
	 * @generated
	 */
	void setIdentification(Identification value);

	/**
	 * Returns the value of the '<em><b>Shared Library Class Path</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Shared Library Class Path</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Shared Library Class Path</em>' containment reference.
	 * @see #setSharedLibraryClassPath(ClassPath)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getSharedLibraryType_SharedLibraryClassPath()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='shared-library-class-path' namespace='##targetNamespace'"
	 * @generated
	 */
	ClassPath getSharedLibraryClassPath();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.SharedLibraryType#getSharedLibraryClassPath <em>Shared Library Class Path</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shared Library Class Path</em>' containment reference.
	 * @see #getSharedLibraryClassPath()
	 * @generated
	 */
	void setSharedLibraryClassPath(ClassPath value);

	/**
	 * Returns the value of the '<em><b>Class Loader Delegation</b></em>' attribute.
	 * The literals are from the enumeration {@link com.sun.java.xml.ns.jbi.ClassLoaderDelegationType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class Loader Delegation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class Loader Delegation</em>' attribute.
	 * @see com.sun.java.xml.ns.jbi.ClassLoaderDelegationType
	 * @see #isSetClassLoaderDelegation()
	 * @see #unsetClassLoaderDelegation()
	 * @see #setClassLoaderDelegation(ClassLoaderDelegationType)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getSharedLibraryType_ClassLoaderDelegation()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='class-loader-delegation'"
	 * @generated
	 */
	ClassLoaderDelegationType getClassLoaderDelegation();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.SharedLibraryType#getClassLoaderDelegation <em>Class Loader Delegation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Loader Delegation</em>' attribute.
	 * @see com.sun.java.xml.ns.jbi.ClassLoaderDelegationType
	 * @see #isSetClassLoaderDelegation()
	 * @see #unsetClassLoaderDelegation()
	 * @see #getClassLoaderDelegation()
	 * @generated
	 */
	void setClassLoaderDelegation(ClassLoaderDelegationType value);

	/**
	 * Unsets the value of the '{@link com.sun.java.xml.ns.jbi.SharedLibraryType#getClassLoaderDelegation <em>Class Loader Delegation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetClassLoaderDelegation()
	 * @see #getClassLoaderDelegation()
	 * @see #setClassLoaderDelegation(ClassLoaderDelegationType)
	 * @generated
	 */
	void unsetClassLoaderDelegation();

	/**
	 * Returns whether the value of the '{@link com.sun.java.xml.ns.jbi.SharedLibraryType#getClassLoaderDelegation <em>Class Loader Delegation</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Class Loader Delegation</em>' attribute is set.
	 * @see #unsetClassLoaderDelegation()
	 * @see #getClassLoaderDelegation()
	 * @see #setClassLoaderDelegation(ClassLoaderDelegationType)
	 * @generated
	 */
	boolean isSetClassLoaderDelegation();

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(Object)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getSharedLibraryType_Version()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.AnySimpleType"
	 *        extendedMetaData="kind='attribute' name='version'"
	 * @generated
	 */
	Object getVersion();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.SharedLibraryType#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(Object value);

} // SharedLibraryType
