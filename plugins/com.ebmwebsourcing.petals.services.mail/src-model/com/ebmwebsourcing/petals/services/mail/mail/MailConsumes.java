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
package com.ebmwebsourcing.petals.services.mail.mail;

import com.sun.java.xml.ns.jbi.Consumes;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Consumes</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getFolder <em>Folder</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#isDelete <em>Delete</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getPeriod <em>Period</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#isIsxmlcontent <em>Isxmlcontent</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailConsumes()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface MailConsumes extends Consumes, MailServiceCommon {
	/**
	 * Returns the value of the '<em><b>Folder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Folder</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Folder</em>' attribute.
	 * @see #setFolder(String)
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailConsumes_Folder()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	String getFolder();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getFolder <em>Folder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Folder</em>' attribute.
	 * @see #getFolder()
	 * @generated
	 */
	void setFolder(String value);

	/**
	 * Returns the value of the '<em><b>Delete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Delete</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Delete</em>' attribute.
	 * @see #setDelete(boolean)
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailConsumes_Delete()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	boolean isDelete();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#isDelete <em>Delete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Delete</em>' attribute.
	 * @see #isDelete()
	 * @generated
	 */
	void setDelete(boolean value);

	/**
	 * Returns the value of the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Period</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Period</em>' attribute.
	 * @see #setPeriod(int)
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailConsumes_Period()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" required="true" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	int getPeriod();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#getPeriod <em>Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Period</em>' attribute.
	 * @see #getPeriod()
	 * @generated
	 */
	void setPeriod(int value);

	/**
	 * Returns the value of the '<em><b>Isxmlcontent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Isxmlcontent</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Isxmlcontent</em>' attribute.
	 * @see #setIsxmlcontent(boolean)
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getMailConsumes_Isxmlcontent()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean" derived="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	boolean isIsxmlcontent();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.mail.mail.MailConsumes#isIsxmlcontent <em>Isxmlcontent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Isxmlcontent</em>' attribute.
	 * @see #isIsxmlcontent()
	 * @generated
	 */
	void setIsxmlcontent(boolean value);

} // MailConsumes
