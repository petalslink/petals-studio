/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.impl;

import com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.*;

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
public class FileTransferFactoryImpl extends EFactoryImpl implements FileTransferFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static FileTransferFactory init() {
		try {
			FileTransferFactory theFileTransferFactory = (FileTransferFactory)EPackage.Registry.INSTANCE.getEFactory("http://petals.ow2.org/components/filetransfer/version-2"); 
			if (theFileTransferFactory != null) {
				return theFileTransferFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new FileTransferFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FileTransferFactoryImpl() {
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
			case FileTransferPackage.FILE_TRANSFER_EXTENSION: return createFileTransferExtension();
			case FileTransferPackage.FILE_TRANSFER_PROVIDES: return createFileTransferProvides();
			case FileTransferPackage.FILE_TRANSFER_CONSUMES: return createFileTransferConsumes();
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
			case FileTransferPackage.COPY_MODE:
				return createCopyModeFromString(eDataType, initialValue);
			case FileTransferPackage.TRANSFER_MODE:
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
			case FileTransferPackage.COPY_MODE:
				return convertCopyModeToString(eDataType, instanceValue);
			case FileTransferPackage.TRANSFER_MODE:
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
	public FileTransferExtension createFileTransferExtension() {
		FileTransferExtensionImpl fileTransferExtension = new FileTransferExtensionImpl();
		return fileTransferExtension;
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
	public FileTransferPackage getFileTransferPackage() {
		return (FileTransferPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static FileTransferPackage getPackage() {
		return FileTransferPackage.eINSTANCE;
	}

} //FileTransferFactoryImpl
