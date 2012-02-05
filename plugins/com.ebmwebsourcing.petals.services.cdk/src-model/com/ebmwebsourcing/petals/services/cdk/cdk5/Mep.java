/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.ebmwebsourcing.petals.services.cdk.cdk5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Mep</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package#getMep()
 * @model
 * @generated
 */
public enum Mep implements Enumerator {
	/**
	 * The '<em><b>In Only</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IN_ONLY_VALUE
	 * @generated
	 * @ordered
	 */
	IN_ONLY(0, "InOnly", "InOnly"),

	/**
	 * The '<em><b>In Optional Out</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IN_OPTIONAL_OUT_VALUE
	 * @generated
	 * @ordered
	 */
	IN_OPTIONAL_OUT(1, "InOptionalOut", "InOptionalOut"),

	/**
	 * The '<em><b>In Out</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IN_OUT_VALUE
	 * @generated
	 * @ordered
	 */
	IN_OUT(2, "InOut", "InOut"),

	/**
	 * The '<em><b>Robust In Only</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ROBUST_IN_ONLY_VALUE
	 * @generated
	 * @ordered
	 */
	ROBUST_IN_ONLY(3, "RobustInOnly", "RobustInOnly");

	/**
	 * The '<em><b>In Only</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>In Only</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #IN_ONLY
	 * @model name="InOnly"
	 * @generated
	 * @ordered
	 */
	public static final int IN_ONLY_VALUE = 0;

	/**
	 * The '<em><b>In Optional Out</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>In Optional Out</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #IN_OPTIONAL_OUT
	 * @model name="InOptionalOut"
	 * @generated
	 * @ordered
	 */
	public static final int IN_OPTIONAL_OUT_VALUE = 1;

	/**
	 * The '<em><b>In Out</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>In Out</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #IN_OUT
	 * @model name="InOut"
	 * @generated
	 * @ordered
	 */
	public static final int IN_OUT_VALUE = 2;

	/**
	 * The '<em><b>Robust In Only</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Robust In Only</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ROBUST_IN_ONLY
	 * @model name="RobustInOnly"
	 * @generated
	 * @ordered
	 */
	public static final int ROBUST_IN_ONLY_VALUE = 3;

	/**
	 * An array of all the '<em><b>Mep</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Mep[] VALUES_ARRAY =
		new Mep[] {
			IN_ONLY,
			IN_OPTIONAL_OUT,
			IN_OUT,
			ROBUST_IN_ONLY,
		};

	/**
	 * A public read-only list of all the '<em><b>Mep</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<Mep> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Mep</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Mep get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Mep result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Mep</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Mep getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Mep result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Mep</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Mep get(int value) {
		switch (value) {
			case IN_ONLY_VALUE: return IN_ONLY;
			case IN_OPTIONAL_OUT_VALUE: return IN_OPTIONAL_OUT;
			case IN_OUT_VALUE: return IN_OUT;
			case ROBUST_IN_ONLY_VALUE: return ROBUST_IN_ONLY;
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
	private Mep(int value, String name, String literal) {
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
	
} //Mep
