/**
 * Copyright (c) 2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.impl;

import com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.*;

import org.eclipse.emf.ecore.EClass;
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
public class Filetransfer3FactoryImpl extends EFactoryImpl implements Filetransfer3Factory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Filetransfer3Factory init() {
		try {
			Filetransfer3Factory theFiletransfer3Factory = (Filetransfer3Factory)EPackage.Registry.INSTANCE.getEFactory("http://petals.ow2.org/components/filetransfer/version-3"); 
			if (theFiletransfer3Factory != null) {
				return theFiletransfer3Factory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Filetransfer3FactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Filetransfer3FactoryImpl() {
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
			case Filetransfer3Package.FILE_TRANSFER3_PROVIDES: return createFileTransfer3Provides();
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES: return createFileTransfer3Consumes();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FileTransfer3Provides createFileTransfer3Provides() {
		FileTransfer3ProvidesImpl fileTransfer3Provides = new FileTransfer3ProvidesImpl();
		return fileTransfer3Provides;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FileTransfer3Consumes createFileTransfer3Consumes() {
		FileTransfer3ConsumesImpl fileTransfer3Consumes = new FileTransfer3ConsumesImpl();
		return fileTransfer3Consumes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Filetransfer3Package getFiletransfer3Package() {
		return (Filetransfer3Package)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Filetransfer3Package getPackage() {
		return Filetransfer3Package.eINSTANCE;
	}

} //Filetransfer3FactoryImpl
