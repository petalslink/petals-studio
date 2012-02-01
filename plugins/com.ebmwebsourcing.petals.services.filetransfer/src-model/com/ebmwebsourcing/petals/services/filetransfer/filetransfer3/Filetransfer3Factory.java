/**
 * Copyright (c) 2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.filetransfer.filetransfer3;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.Filetransfer3Package
 * @generated
 */
public interface Filetransfer3Factory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Filetransfer3Factory eINSTANCE = com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.impl.Filetransfer3FactoryImpl.init();

	/**
	 * Returns a new object of class '<em>File Transfer3 Provides</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>File Transfer3 Provides</em>'.
	 * @generated
	 */
	FileTransfer3Provides createFileTransfer3Provides();

	/**
	 * Returns a new object of class '<em>File Transfer3 Consumes</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>File Transfer3 Consumes</em>'.
	 * @generated
	 */
	FileTransfer3Consumes createFileTransfer3Consumes();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	Filetransfer3Package getFiletransfer3Package();

} //Filetransfer3Factory
