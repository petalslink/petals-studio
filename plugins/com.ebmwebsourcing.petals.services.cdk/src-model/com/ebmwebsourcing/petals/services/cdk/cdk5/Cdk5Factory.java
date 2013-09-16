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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package
 * @generated
 */
public interface Cdk5Factory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Cdk5Factory eINSTANCE = com.ebmwebsourcing.petals.services.cdk.cdk5.impl.Cdk5FactoryImpl.init();

	/**
	 * Returns a new object of class '<em>CDK5 Provides</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>CDK5 Provides</em>'.
	 * @generated
	 */
	CDK5Provides createCDK5Provides();

	/**
	 * Returns a new object of class '<em>CDK5 Consumes</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>CDK5 Consumes</em>'.
	 * @generated
	 */
	CDK5Consumes createCDK5Consumes();

	/**
	 * Returns a new object of class '<em>Retry Policy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Retry Policy</em>'.
	 * @generated
	 */
	RetryPolicy createRetryPolicy();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	Cdk5Package getCdk5Package();

} //Cdk5Factory
