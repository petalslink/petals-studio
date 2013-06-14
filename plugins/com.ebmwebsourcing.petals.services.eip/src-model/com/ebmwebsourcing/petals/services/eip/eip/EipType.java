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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.ebmwebsourcing.petals.services.eip.eip.EipPackage#getEipType()
 * @model
 * @generated
 */
public enum EipType implements Enumerator {
	/**
	 * The '<em><b>ROUTING SLIP</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ROUTING_SLIP_VALUE
	 * @generated
	 * @ordered
	 */
	ROUTING_SLIP(0, "ROUTING_SLIP", "routing-slip"),

	/**
	 * The '<em><b>ROUTER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ROUTER_VALUE
	 * @generated
	 * @ordered
	 */
	ROUTER(1, "ROUTER", "router"),

	/**
	 * The '<em><b>DYNAMIC ROUTER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DYNAMIC_ROUTER_VALUE
	 * @generated
	 * @ordered
	 */
	DYNAMIC_ROUTER(2, "DYNAMIC_ROUTER", "dynamic-router"),

	/**
	 * The '<em><b>BRIDGE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BRIDGE_VALUE
	 * @generated
	 * @ordered
	 */
	BRIDGE(3, "BRIDGE", "bridge"),

	/**
	 * The '<em><b>SPLITTER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SPLITTER_VALUE
	 * @generated
	 * @ordered
	 */
	SPLITTER(4, "SPLITTER", "splitter"),

	/**
	 * The '<em><b>AGGREGATOR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AGGREGATOR_VALUE
	 * @generated
	 * @ordered
	 */
	AGGREGATOR(5, "AGGREGATOR", "aggregator"),

	/**
	 * The '<em><b>DISPATCHER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DISPATCHER_VALUE
	 * @generated
	 * @ordered
	 */
	DISPATCHER(6, "DISPATCHER", "dispatcher"),

	/**
	 * The '<em><b>SCATTER GATHER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SCATTER_GATHER_VALUE
	 * @generated
	 * @ordered
	 */
	SCATTER_GATHER(7, "SCATTER_GATHER", "scatter-gather"),

	/**
	 * The '<em><b>WIRE TAP</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WIRE_TAP_VALUE
	 * @generated
	 * @ordered
	 */
	WIRE_TAP(8, "WIRE_TAP", "wire-tap");

	/**
	 * The '<em><b>ROUTING SLIP</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ROUTING SLIP</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ROUTING_SLIP
	 * @model literal="routing-slip"
	 * @generated
	 * @ordered
	 */
	public static final int ROUTING_SLIP_VALUE = 0;

	/**
	 * The '<em><b>ROUTER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ROUTER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ROUTER
	 * @model literal="router"
	 * @generated
	 * @ordered
	 */
	public static final int ROUTER_VALUE = 1;

	/**
	 * The '<em><b>DYNAMIC ROUTER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DYNAMIC ROUTER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DYNAMIC_ROUTER
	 * @model literal="dynamic-router"
	 * @generated
	 * @ordered
	 */
	public static final int DYNAMIC_ROUTER_VALUE = 2;

	/**
	 * The '<em><b>BRIDGE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BRIDGE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BRIDGE
	 * @model literal="bridge"
	 * @generated
	 * @ordered
	 */
	public static final int BRIDGE_VALUE = 3;

	/**
	 * The '<em><b>SPLITTER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SPLITTER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SPLITTER
	 * @model literal="splitter"
	 * @generated
	 * @ordered
	 */
	public static final int SPLITTER_VALUE = 4;

	/**
	 * The '<em><b>AGGREGATOR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>AGGREGATOR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #AGGREGATOR
	 * @model literal="aggregator"
	 * @generated
	 * @ordered
	 */
	public static final int AGGREGATOR_VALUE = 5;

	/**
	 * The '<em><b>DISPATCHER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DISPATCHER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DISPATCHER
	 * @model literal="dispatcher"
	 * @generated
	 * @ordered
	 */
	public static final int DISPATCHER_VALUE = 6;

	/**
	 * The '<em><b>SCATTER GATHER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SCATTER GATHER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SCATTER_GATHER
	 * @model literal="scatter-gather"
	 * @generated
	 * @ordered
	 */
	public static final int SCATTER_GATHER_VALUE = 7;

	/**
	 * The '<em><b>WIRE TAP</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>WIRE TAP</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WIRE_TAP
	 * @model literal="wire-tap"
	 * @generated
	 * @ordered
	 */
	public static final int WIRE_TAP_VALUE = 8;

	/**
	 * An array of all the '<em><b>Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final EipType[] VALUES_ARRAY =
		new EipType[] {
			ROUTING_SLIP,
			ROUTER,
			DYNAMIC_ROUTER,
			BRIDGE,
			SPLITTER,
			AGGREGATOR,
			DISPATCHER,
			SCATTER_GATHER,
			WIRE_TAP,
		};

	/**
	 * A public read-only list of all the '<em><b>Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<EipType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EipType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EipType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EipType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EipType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EipType get(int value) {
		switch (value) {
			case ROUTING_SLIP_VALUE: return ROUTING_SLIP;
			case ROUTER_VALUE: return ROUTER;
			case DYNAMIC_ROUTER_VALUE: return DYNAMIC_ROUTER;
			case BRIDGE_VALUE: return BRIDGE;
			case SPLITTER_VALUE: return SPLITTER;
			case AGGREGATOR_VALUE: return AGGREGATOR;
			case DISPATCHER_VALUE: return DISPATCHER;
			case SCATTER_GATHER_VALUE: return SCATTER_GATHER;
			case WIRE_TAP_VALUE: return WIRE_TAP;
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
	private EipType(int value, String name, String literal) {
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
	
} //EipType
