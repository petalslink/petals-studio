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

package com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FileTransferXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FileTransferXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		FileTransferPackage.eINSTANCE.eClass();
	}

	/**
	 * Register for "*" and "xml" file extensions the FileTransferResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (this.registrations == null) {
			super.getRegistrations();
			this.registrations.put(XML_EXTENSION, new FileTransferResourceFactoryImpl());
			this.registrations.put(STAR_EXTENSION, new FileTransferResourceFactoryImpl());
		}
		return this.registrations;
	}

} //FileTransferXMLProcessor
