/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5;

import com.sun.java.xml.ns.jbi.Consumes;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CDK5 Consumes</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Consumes#getOperation <em>Operation</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Consumes#getMep <em>Mep</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package#getCDK5Consumes()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface CDK5Consumes extends CDKService, Consumes {
	/**
	 * Returns the value of the '<em><b>Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation</em>' attribute.
	 * @see #setOperation(String)
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package#getCDK5Consumes_Operation()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="kind='element' namespace='http://petals.ow2.org/components/extensions/version-5' name='operation' group='#cdkExtContainer'"
	 * @generated
	 */
	String getOperation();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Consumes#getOperation <em>Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation</em>' attribute.
	 * @see #getOperation()
	 * @generated
	 */
	void setOperation(String value);

	/**
	 * Returns the value of the '<em><b>Mep</b></em>' attribute.
	 * The literals are from the enumeration {@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Mep}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mep</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mep</em>' attribute.
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Mep
	 * @see #isSetMep()
	 * @see #unsetMep()
	 * @see #setMep(Mep)
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package#getCDK5Consumes_Mep()
	 * @model unsettable="true" derived="true"
	 *        extendedMetaData="kind='element' namespace='http://petals.ow2.org/components/extensions/version-5' group='#cdkExtContainer'"
	 * @generated
	 */
	Mep getMep();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Consumes#getMep <em>Mep</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mep</em>' attribute.
	 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Mep
	 * @see #isSetMep()
	 * @see #unsetMep()
	 * @see #getMep()
	 * @generated
	 */
	void setMep(Mep value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Consumes#getMep <em>Mep</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMep()
	 * @see #getMep()
	 * @see #setMep(Mep)
	 * @generated
	 */
	void unsetMep();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Consumes#getMep <em>Mep</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Mep</em>' attribute is set.
	 * @see #unsetMep()
	 * @see #getMep()
	 * @see #setMep(Mep)
	 * @generated
	 */
	boolean isSetMep();

} // CDK5Consumes
