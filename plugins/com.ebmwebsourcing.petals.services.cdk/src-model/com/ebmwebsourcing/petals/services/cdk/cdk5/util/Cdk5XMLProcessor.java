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

package com.ebmwebsourcing.petals.services.cdk.cdk5.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class Cdk5XMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Cdk5XMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		Cdk5Package.eINSTANCE.eClass();
	}

	/**
	 * Register for "*" and "xml" file extensions the Cdk5ResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (this.registrations == null) {
			super.getRegistrations();
			this.registrations.put(XML_EXTENSION, new Cdk5ResourceFactoryImpl());
			this.registrations.put(STAR_EXTENSION, new Cdk5ResourceFactoryImpl());
		}
		return this.registrations;
	}

} //Cdk5XMLProcessor
