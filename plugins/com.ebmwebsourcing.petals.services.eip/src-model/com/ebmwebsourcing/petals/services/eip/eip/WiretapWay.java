/**
 * Copyright (c) 2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.eip.eip;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Wiretap Way</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.ebmwebsourcing.petals.services.eip.eip.EipPackage#getWiretapWay()
 * @model
 * @generated
 */
public enum WiretapWay implements Enumerator {
	/**
	 * The '<em><b>REQUEST ON RESPONSE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REQUEST_ON_RESPONSE_VALUE
	 * @generated
	 * @ordered
	 */
	REQUEST_ON_RESPONSE(0, "REQUEST_ON_RESPONSE", "request-on-response"),

	/**
	 * The '<em><b>REQUEST RESPONSE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REQUEST_RESPONSE_VALUE
	 * @generated
	 * @ordered
	 */
	REQUEST_RESPONSE(1, "REQUEST_RESPONSE", "request-response"),

	/**
	 * The '<em><b>RESPONSE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RESPONSE_VALUE
	 * @generated
	 * @ordered
	 */
	RESPONSE(2, "RESPONSE", "response"),

	/**
	 * The '<em><b>REQUEST</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REQUEST_VALUE
	 * @generated
	 * @ordered
	 */
	REQUEST(3, "REQUEST", "request");

	/**
	 * The '<em><b>REQUEST ON RESPONSE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>REQUEST ON RESPONSE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REQUEST_ON_RESPONSE
	 * @model literal="request-on-response"
	 * @generated
	 * @ordered
	 */
	public static final int REQUEST_ON_RESPONSE_VALUE = 0;

	/**
	 * The '<em><b>REQUEST RESPONSE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>REQUEST RESPONSE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REQUEST_RESPONSE
	 * @model literal="request-response"
	 * @generated
	 * @ordered
	 */
	public static final int REQUEST_RESPONSE_VALUE = 1;

	/**
	 * The '<em><b>RESPONSE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RESPONSE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RESPONSE
	 * @model literal="response"
	 * @generated
	 * @ordered
	 */
	public static final int RESPONSE_VALUE = 2;

	/**
	 * The '<em><b>REQUEST</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>REQUEST</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REQUEST
	 * @model literal="request"
	 * @generated
	 * @ordered
	 */
	public static final int REQUEST_VALUE = 3;

	/**
	 * An array of all the '<em><b>Wiretap Way</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final WiretapWay[] VALUES_ARRAY =
		new WiretapWay[] {
			REQUEST_ON_RESPONSE,
			REQUEST_RESPONSE,
			RESPONSE,
			REQUEST,
		};

	/**
	 * A public read-only list of all the '<em><b>Wiretap Way</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<WiretapWay> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Wiretap Way</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WiretapWay get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			WiretapWay result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Wiretap Way</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WiretapWay getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			WiretapWay result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Wiretap Way</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WiretapWay get(int value) {
		switch (value) {
			case REQUEST_ON_RESPONSE_VALUE: return REQUEST_ON_RESPONSE;
			case REQUEST_RESPONSE_VALUE: return REQUEST_RESPONSE;
			case RESPONSE_VALUE: return RESPONSE;
			case REQUEST_VALUE: return REQUEST;
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
	private WiretapWay(int value, String name, String literal) {
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
	
} //WiretapWay
