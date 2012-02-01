/**
 * Copyright (c) 2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.ejb.ejb;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Xml Engine</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbPackage#getXmlEngine()
 * @model
 * @generated
 */
public enum XmlEngine implements Enumerator {
	/**
	 * The '<em><b>JAXB</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAXB_VALUE
	 * @generated
	 * @ordered
	 */
	JAXB(0, "JAXB", "jaxb");

	/**
	 * The '<em><b>JAXB</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>JAXB</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAXB
	 * @model literal="jaxb"
	 * @generated
	 * @ordered
	 */
	public static final int JAXB_VALUE = 0;

	/**
	 * An array of all the '<em><b>Xml Engine</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final XmlEngine[] VALUES_ARRAY =
		new XmlEngine[] {
			JAXB,
		};

	/**
	 * A public read-only list of all the '<em><b>Xml Engine</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<XmlEngine> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Xml Engine</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static XmlEngine get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			XmlEngine result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Xml Engine</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static XmlEngine getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			XmlEngine result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Xml Engine</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static XmlEngine get(int value) {
		switch (value) {
			case JAXB_VALUE: return JAXB;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private XmlEngine(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //XmlEngine
