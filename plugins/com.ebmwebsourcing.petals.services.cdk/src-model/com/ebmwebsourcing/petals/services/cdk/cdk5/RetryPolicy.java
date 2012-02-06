/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.ebmwebsourcing.petals.services.cdk.cdk5;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Retry Policy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.cdk.cdk5.RetryPolicy#getAttempts <em>Attempts</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.cdk.cdk5.RetryPolicy#getDelay <em>Delay</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package#getRetryPolicy()
 * @model
 * @generated
 */
public interface RetryPolicy extends EObject {
	/**
	 * Returns the value of the '<em><b>Attempts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attempts</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attempts</em>' attribute.
	 * @see #isSetAttempts()
	 * @see #unsetAttempts()
	 * @see #setAttempts(int)
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package#getRetryPolicy_Attempts()
	 * @model unsettable="true" required="true" derived="true"
	 *        extendedMetaData="kind='element' namespace='http://petals.ow2.org/components/extensions/version-5' name='attempts' group='#cdkExtContainer'"
	 * @generated
	 */
	int getAttempts();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.RetryPolicy#getAttempts <em>Attempts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attempts</em>' attribute.
	 * @see #isSetAttempts()
	 * @see #unsetAttempts()
	 * @see #getAttempts()
	 * @generated
	 */
	void setAttempts(int value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.RetryPolicy#getAttempts <em>Attempts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAttempts()
	 * @see #getAttempts()
	 * @see #setAttempts(int)
	 * @generated
	 */
	void unsetAttempts();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.RetryPolicy#getAttempts <em>Attempts</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Attempts</em>' attribute is set.
	 * @see #unsetAttempts()
	 * @see #getAttempts()
	 * @see #setAttempts(int)
	 * @generated
	 */
	boolean isSetAttempts();

	/**
	 * Returns the value of the '<em><b>Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Delay</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Delay</em>' attribute.
	 * @see #setDelay(long)
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package#getRetryPolicy_Delay()
	 * @model derived="true"
	 *        extendedMetaData="kind='element' namespace='http://petals.ow2.org/components/extensions/version-5' name='delay' group='#cdkExtContainer'"
	 * @generated
	 */
	long getDelay();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.RetryPolicy#getDelay <em>Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Delay</em>' attribute.
	 * @see #getDelay()
	 * @generated
	 */
	void setDelay(long value);

} // RetryPolicy
