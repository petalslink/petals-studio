/**
 * Copyright (c) 2012-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 */
package com.ebmwebsourcing.petals.services.eip.eip;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.ebmwebsourcing.petals.services.eip.eip.EipPackage
 * @generated
 */
public interface EipFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EipFactory eINSTANCE = com.ebmwebsourcing.petals.services.eip.eip.impl.EipFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Provides</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Provides</em>'.
	 * @generated
	 */
	EipProvides createEipProvides();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	EipPackage getEipPackage();

} //EipFactory
