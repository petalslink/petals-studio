/**
 * Copyright (c) 2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.Filetransfer2xPackage
 * @generated
 */
public interface Filetransfer2xFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Filetransfer2xFactory eINSTANCE = com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.Filetransfer2xFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>File Transfer Extension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>File Transfer Extension</em>'.
	 * @generated
	 */
	FileTransferExtension createFileTransferExtension();

	/**
	 * Returns a new object of class '<em>File Transfer Provides</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>File Transfer Provides</em>'.
	 * @generated
	 */
	FileTransferProvides createFileTransferProvides();

	/**
	 * Returns a new object of class '<em>File Transfer Consumes</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>File Transfer Consumes</em>'.
	 * @generated
	 */
	FileTransferConsumes createFileTransferConsumes();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	Filetransfer2xPackage getFiletransfer2xPackage();

} //Filetransfer2xFactory
