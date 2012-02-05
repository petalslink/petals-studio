/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.ebmwebsourcing.petals.services.cdk.cdk5;

import com.sun.java.xml.ns.jbi.AbstractEndpoint;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CDK Service</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.cdk.cdk5.CDKService#getCdkExtContainer <em>Cdk Ext Container</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.cdk.cdk5.CDKService#getTimeout <em>Timeout</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.cdk.cdk5.CDKService#getSuInterceptors <em>Su Interceptors</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package#getCDKService()
 * @model abstract="true"
 *        extendedMetaData="name=''"
 * @generated
 */
public interface CDKService extends AbstractEndpoint {
	/**
	 * Returns the value of the '<em><b>Cdk Ext Container</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cdk Ext Container</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cdk Ext Container</em>' attribute list.
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package#getCDKService_CdkExtContainer()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='elementWildcard' wildcards='http://petals.ow2.org/components/extensions/version-5' processing='skip' name='cdkExtContainer' group='#group:0'"
	 * @generated
	 */
	FeatureMap getCdkExtContainer();

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
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package#getCDKService_Timeout()
	 * @model derived="true"
	 *        extendedMetaData="kind='element' namespace='http://petals.ow2.org/components/extensions/version-5' name='timeout' group='#cdkExtContainer'"
	 * @generated
	 */
	int getTimeout();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.CDKService#getTimeout <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timeout</em>' attribute.
	 * @see #getTimeout()
	 * @generated
	 */
	void setTimeout(int value);

	/**
	 * Returns the value of the '<em><b>Su Interceptors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Su Interceptors</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Su Interceptors</em>' attribute.
	 * @see #setSuInterceptors(String)
	 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package#getCDKService_SuInterceptors()
	 * @model extendedMetaData="kind='element' namespace='http://petals.ow2.org/components/extensions/version-5' name='suInterceptors' group='#cdkExtContainer'"
	 * @generated
	 */
	String getSuInterceptors();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.cdk.cdk5.CDKService#getSuInterceptors <em>Su Interceptors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Su Interceptors</em>' attribute.
	 * @see #getSuInterceptors()
	 * @generated
	 */
	void setSuInterceptors(String value);

} // CDKService
