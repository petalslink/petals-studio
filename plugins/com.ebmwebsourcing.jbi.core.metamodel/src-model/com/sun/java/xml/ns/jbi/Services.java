/**
 *  Copyright (c) 2009-2012, EBM WebSourcing
 *  
 *  This source code is available under agreement available at
 *  http://www.petalslink.com/legal/licenses/petals-studio
 *  
 *  You should have received a copy of the agreement along with this program.
 *  If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.sun.java.xml.ns.jbi;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Services</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.sun.java.xml.ns.jbi.Services#getProvides <em>Provides</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.Services#getConsumes <em>Consumes</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.Services#isBindingComponent <em>Binding Component</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.sun.java.xml.ns.jbi.JbiPackage#getServices()
 * @model extendedMetaData="name='Services' kind='elementOnly'"
 * @generated
 */
public interface Services extends AbstractExtensibleElement {
	/**
	 * Returns the value of the '<em><b>Provides</b></em>' containment reference list.
	 * The list contents are of type {@link com.sun.java.xml.ns.jbi.Provides}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Provides</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Provides</em>' containment reference list.
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getServices_Provides()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='provides' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Provides> getProvides();

	/**
	 * Returns the value of the '<em><b>Consumes</b></em>' containment reference list.
	 * The list contents are of type {@link com.sun.java.xml.ns.jbi.Consumes}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Consumes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Consumes</em>' containment reference list.
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getServices_Consumes()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='consumes' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Consumes> getConsumes();

	/**
	 * Returns the value of the '<em><b>Binding Component</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binding Component</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding Component</em>' attribute.
	 * @see #isSetBindingComponent()
	 * @see #unsetBindingComponent()
	 * @see #setBindingComponent(boolean)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getServices_BindingComponent()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean" required="true"
	 *        extendedMetaData="kind='attribute' name='binding-component'"
	 * @generated
	 */
	boolean isBindingComponent();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.Services#isBindingComponent <em>Binding Component</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding Component</em>' attribute.
	 * @see #isSetBindingComponent()
	 * @see #unsetBindingComponent()
	 * @see #isBindingComponent()
	 * @generated
	 */
	void setBindingComponent(boolean value);

	/**
	 * Unsets the value of the '{@link com.sun.java.xml.ns.jbi.Services#isBindingComponent <em>Binding Component</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetBindingComponent()
	 * @see #isBindingComponent()
	 * @see #setBindingComponent(boolean)
	 * @generated
	 */
	void unsetBindingComponent();

	/**
	 * Returns whether the value of the '{@link com.sun.java.xml.ns.jbi.Services#isBindingComponent <em>Binding Component</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Binding Component</em>' attribute is set.
	 * @see #unsetBindingComponent()
	 * @see #isBindingComponent()
	 * @see #setBindingComponent(boolean)
	 * @generated
	 */
	boolean isSetBindingComponent();

} // Services
