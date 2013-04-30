/**
 *  Copyright (c) 2009-2013, Linagora
 *  
 *  This source code is available under agreement available at
 *  http://www.petalslink.com/legal/licenses/petals-studio
 *  
 *  You should have received a copy of the agreement along with this program.
 *  If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.sun.java.xml.ns.jbi;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Class Loader Delegation Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.sun.java.xml.ns.jbi.JbiPackage#getClassLoaderDelegationType()
 * @model extendedMetaData="name='ClassLoaderDelegationType'"
 * @generated
 */
public enum ClassLoaderDelegationType implements Enumerator {
	/**
	 * The '<em><b>Parent First</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PARENT_FIRST_VALUE
	 * @generated
	 * @ordered
	 */
	PARENT_FIRST(0, "parentFirst", "parent-first"),

	/**
	 * The '<em><b>Self First</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SELF_FIRST_VALUE
	 * @generated
	 * @ordered
	 */
	SELF_FIRST(1, "selfFirst", "self-first");

	/**
	 * The '<em><b>Parent First</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Parent First</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PARENT_FIRST
	 * @model name="parentFirst" literal="parent-first"
	 * @generated
	 * @ordered
	 */
	public static final int PARENT_FIRST_VALUE = 0;

	/**
	 * The '<em><b>Self First</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Self First</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SELF_FIRST
	 * @model name="selfFirst" literal="self-first"
	 * @generated
	 * @ordered
	 */
	public static final int SELF_FIRST_VALUE = 1;

	/**
	 * An array of all the '<em><b>Class Loader Delegation Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ClassLoaderDelegationType[] VALUES_ARRAY =
		new ClassLoaderDelegationType[] {
			PARENT_FIRST,
			SELF_FIRST,
		};

	/**
	 * A public read-only list of all the '<em><b>Class Loader Delegation Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ClassLoaderDelegationType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Class Loader Delegation Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ClassLoaderDelegationType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ClassLoaderDelegationType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Class Loader Delegation Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ClassLoaderDelegationType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ClassLoaderDelegationType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Class Loader Delegation Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ClassLoaderDelegationType get(int value) {
		switch (value) {
			case PARENT_FIRST_VALUE: return PARENT_FIRST;
			case SELF_FIRST_VALUE: return SELF_FIRST;
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
	private ClassLoaderDelegationType(int value, String name, String literal) {
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
	
} //ClassLoaderDelegationType
