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
 * A representation of the literals of the enumeration '<em><b>Component Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.sun.java.xml.ns.jbi.JbiPackage#getComponentType()
 * @model extendedMetaData="name='ComponentType'"
 * @generated
 */
public enum ComponentType implements Enumerator {
	/**
	 * The '<em><b>Service Engine</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SERVICE_ENGINE_VALUE
	 * @generated
	 * @ordered
	 */
	SERVICE_ENGINE(0, "serviceEngine", "service-engine"),

	/**
	 * The '<em><b>Binding Component</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BINDING_COMPONENT_VALUE
	 * @generated
	 * @ordered
	 */
	BINDING_COMPONENT(1, "bindingComponent", "binding-component");

	/**
	 * The '<em><b>Service Engine</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Service Engine</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SERVICE_ENGINE
	 * @model name="serviceEngine" literal="service-engine"
	 * @generated
	 * @ordered
	 */
	public static final int SERVICE_ENGINE_VALUE = 0;

	/**
	 * The '<em><b>Binding Component</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Binding Component</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BINDING_COMPONENT
	 * @model name="bindingComponent" literal="binding-component"
	 * @generated
	 * @ordered
	 */
	public static final int BINDING_COMPONENT_VALUE = 1;

	/**
	 * An array of all the '<em><b>Component Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ComponentType[] VALUES_ARRAY =
		new ComponentType[] {
			SERVICE_ENGINE,
			BINDING_COMPONENT,
		};

	/**
	 * A public read-only list of all the '<em><b>Component Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ComponentType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Component Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ComponentType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ComponentType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Component Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ComponentType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ComponentType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Component Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ComponentType get(int value) {
		switch (value) {
			case SERVICE_ENGINE_VALUE: return SERVICE_ENGINE;
			case BINDING_COMPONENT_VALUE: return BINDING_COMPONENT;
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
	private ComponentType(int value, String name, String literal) {
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
	
} //ComponentType
