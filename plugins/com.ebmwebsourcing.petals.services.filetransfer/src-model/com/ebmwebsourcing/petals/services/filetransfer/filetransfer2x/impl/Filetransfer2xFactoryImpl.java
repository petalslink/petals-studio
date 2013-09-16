/******************************************************************************
 * Copyright (c) 2012-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl;

import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Filetransfer2xFactoryImpl extends EFactoryImpl implements Filetransfer2xFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Filetransfer2xFactory init() {
		try {
			Filetransfer2xFactory theFiletransfer2xFactory = (Filetransfer2xFactory)EPackage.Registry.INSTANCE.getEFactory("http://petals.ow2.org/components/filetransfer/version-2"); 
			if (theFiletransfer2xFactory != null) {
				return theFiletransfer2xFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Filetransfer2xFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Filetransfer2xFactoryImpl() {
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
			case Filetransfer2xPackage.FILE_TRANSFER_PROVIDES: return createFileTransferProvides();
			case Filetransfer2xPackage.FILE_TRANSFER_CONSUMES: return createFileTransferConsumes();
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
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case Filetransfer2xPackage.COPY_MODE:
				return createCopyModeFromString(eDataType, initialValue);
			case Filetransfer2xPackage.TRANSFER_MODE:
				return createTransferModeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case Filetransfer2xPackage.COPY_MODE:
				return convertCopyModeToString(eDataType, instanceValue);
			case Filetransfer2xPackage.TRANSFER_MODE:
				return convertTransferModeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FileTransferProvides createFileTransferProvides() {
		FileTransferProvidesImpl fileTransferProvides = new FileTransferProvidesImpl();
		return fileTransferProvides;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FileTransferConsumes createFileTransferConsumes() {
		FileTransferConsumesImpl fileTransferConsumes = new FileTransferConsumesImpl();
		return fileTransferConsumes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CopyMode createCopyModeFromString(EDataType eDataType, String initialValue) {
		CopyMode result = CopyMode.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCopyModeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransferMode createTransferModeFromString(EDataType eDataType, String initialValue) {
		TransferMode result = TransferMode.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTransferModeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Filetransfer2xPackage getFiletransfer2xPackage() {
		return (Filetransfer2xPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Filetransfer2xPackage getPackage() {
		return Filetransfer2xPackage.eINSTANCE;
	}

} //Filetransfer2xFactoryImpl
