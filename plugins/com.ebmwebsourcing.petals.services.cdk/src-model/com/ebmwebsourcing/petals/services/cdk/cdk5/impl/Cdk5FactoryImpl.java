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

package com.ebmwebsourcing.petals.services.cdk.cdk5.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Consumes;
import com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Provides;
import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Factory;
import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.cdk.cdk5.RetryPolicy;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Cdk5FactoryImpl extends EFactoryImpl implements Cdk5Factory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Cdk5Factory init() {
		try {
			Cdk5Factory theCdk5Factory = (Cdk5Factory)EPackage.Registry.INSTANCE.getEFactory("http://petals.ow2.org/components/extensions/version-5");
			if (theCdk5Factory != null) {
				return theCdk5Factory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Cdk5FactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Cdk5FactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case Cdk5Package.CDK5_PROVIDES: return createCDK5Provides();
			case Cdk5Package.CDK5_CONSUMES: return createCDK5Consumes();
			case Cdk5Package.RETRY_POLICY: return createRetryPolicy();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CDK5Provides createCDK5Provides() {
		CDK5ProvidesImpl cdk5Provides = new CDK5ProvidesImpl();
		return cdk5Provides;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CDK5Consumes createCDK5Consumes() {
		CDK5ConsumesImpl cdk5Consumes = new CDK5ConsumesImpl();
		return cdk5Consumes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RetryPolicy createRetryPolicy() {
		RetryPolicyImpl retryPolicy = new RetryPolicyImpl();
		return retryPolicy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Cdk5Package getCdk5Package() {
		return (Cdk5Package)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Cdk5Package getPackage() {
		return Cdk5Package.eINSTANCE;
	}

} //Cdk5FactoryImpl
