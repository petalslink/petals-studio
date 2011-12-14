/**
 * Copyright (c) 2011, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 */
package com.ebmwebsourcing.petals.services.mail.mail;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Send Mode</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#getSendMode()
 * @model
 * @generated
 */
public enum SendMode implements Enumerator {
	/**
	 * The '<em><b>CONTENT ONLY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONTENT_ONLY_VALUE
	 * @generated
	 * @ordered
	 */
	CONTENT_ONLY(0, "CONTENT_ONLY", "content-only"),

	/**
	 * The '<em><b>ATTACHMENTS ONLY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ATTACHMENTS_ONLY_VALUE
	 * @generated
	 * @ordered
	 */
	ATTACHMENTS_ONLY(1, "ATTACHMENTS_ONLY", "attachments-only"),

	/**
	 * The '<em><b>CONTENT AND ATTACHMENTS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONTENT_AND_ATTACHMENTS_VALUE
	 * @generated
	 * @ordered
	 */
	CONTENT_AND_ATTACHMENTS(2, "CONTENT_AND_ATTACHMENTS", "content-and-attachments");

	/**
	 * The '<em><b>CONTENT ONLY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CONTENT ONLY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONTENT_ONLY
	 * @model literal="content-only"
	 * @generated
	 * @ordered
	 */
	public static final int CONTENT_ONLY_VALUE = 0;

	/**
	 * The '<em><b>ATTACHMENTS ONLY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ATTACHMENTS ONLY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ATTACHMENTS_ONLY
	 * @model literal="attachments-only"
	 * @generated
	 * @ordered
	 */
	public static final int ATTACHMENTS_ONLY_VALUE = 1;

	/**
	 * The '<em><b>CONTENT AND ATTACHMENTS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CONTENT AND ATTACHMENTS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONTENT_AND_ATTACHMENTS
	 * @model literal="content-and-attachments"
	 * @generated
	 * @ordered
	 */
	public static final int CONTENT_AND_ATTACHMENTS_VALUE = 2;

	/**
	 * An array of all the '<em><b>Send Mode</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final SendMode[] VALUES_ARRAY =
		new SendMode[] {
			CONTENT_ONLY,
			ATTACHMENTS_ONLY,
			CONTENT_AND_ATTACHMENTS,
		};

	/**
	 * A public read-only list of all the '<em><b>Send Mode</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<SendMode> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Send Mode</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SendMode get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SendMode result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Send Mode</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SendMode getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SendMode result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Send Mode</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SendMode get(int value) {
		switch (value) {
			case CONTENT_ONLY_VALUE: return CONTENT_ONLY;
			case ATTACHMENTS_ONLY_VALUE: return ATTACHMENTS_ONLY;
			case CONTENT_AND_ATTACHMENTS_VALUE: return CONTENT_AND_ATTACHMENTS;
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
	private SendMode(int value, String name, String literal) {
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
	
} //SendMode
