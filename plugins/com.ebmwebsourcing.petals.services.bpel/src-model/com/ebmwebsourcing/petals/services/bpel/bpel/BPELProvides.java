/**
 * Copyright (c) 2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.bpel.bpel;

import com.sun.java.xml.ns.jbi.Provides;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>BPEL Provides</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.bpel.bpel.BPELProvides#getBpel <em>Bpel</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.bpel.bpel.BPELProvides#getPoolsize <em>Poolsize</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.bpel.bpel.BpelPackage#getBPELProvides()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface BPELProvides extends Provides {
	/**
	 * Returns the value of the '<em><b>Bpel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bpel</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bpel</em>' attribute.
	 * @see #setBpel(String)
	 * @see com.ebmwebsourcing.petals.services.bpel.bpel.BpelPackage#getBPELProvides_Bpel()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' group='#group:0' kind='element'"
	 * @generated
	 */
	String getBpel();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.bpel.bpel.BPELProvides#getBpel <em>Bpel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bpel</em>' attribute.
	 * @see #getBpel()
	 * @generated
	 */
	void setBpel(String value);

	/**
	 * Returns the value of the '<em><b>Poolsize</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Poolsize</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Poolsize</em>' attribute.
	 * @see #setPoolsize(int)
	 * @see com.ebmwebsourcing.petals.services.bpel.bpel.BpelPackage#getBPELProvides_Poolsize()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' group='#group:0' kind='element'"
	 * @generated
	 */
	int getPoolsize();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.bpel.bpel.BPELProvides#getPoolsize <em>Poolsize</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Poolsize</em>' attribute.
	 * @see #getPoolsize()
	 * @generated
	 */
	void setPoolsize(int value);

} // BPELProvides
