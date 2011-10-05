/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package
 * @generated
 */
public interface Cdk5Factory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Cdk5Factory eINSTANCE = com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.Cdk5FactoryImpl.init();

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
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	Cdk5Package getCdk5Package();

} //Cdk5Factory
