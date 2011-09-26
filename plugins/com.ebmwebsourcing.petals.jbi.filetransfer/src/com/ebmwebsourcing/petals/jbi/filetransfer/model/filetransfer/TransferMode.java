/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Transfer Mode</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.FileTransferPackage#getTransferMode()
 * @model
 * @generated
 */
public enum TransferMode implements Enumerator {
	/**
	 * The '<em><b>CONTENT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONTENT_VALUE
	 * @generated
	 * @ordered
	 */
	CONTENT(0, "CONTENT", "content"),

	/**
	 * The '<em><b>ATTACHMENTS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ATTACHMENTS_VALUE
	 * @generated
	 * @ordered
	 */
	ATTACHMENTS(1, "ATTACHMENTS", "attachments");

	/**
	 * The '<em><b>CONTENT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CONTENT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONTENT
	 * @model literal="content"
	 * @generated
	 * @ordered
	 */
	public static final int CONTENT_VALUE = 0;

	/**
	 * The '<em><b>ATTACHMENTS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ATTACHMENTS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ATTACHMENTS
	 * @model literal="attachments"
	 * @generated
	 * @ordered
	 */
	public static final int ATTACHMENTS_VALUE = 1;

	/**
	 * An array of all the '<em><b>Transfer Mode</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final TransferMode[] VALUES_ARRAY =
		new TransferMode[] {
			CONTENT,
			ATTACHMENTS,
		};

	/**
	 * A public read-only list of all the '<em><b>Transfer Mode</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<TransferMode> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Transfer Mode</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TransferMode get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TransferMode result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Transfer Mode</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TransferMode getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TransferMode result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Transfer Mode</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TransferMode get(int value) {
		switch (value) {
			case CONTENT_VALUE: return CONTENT;
			case ATTACHMENTS_VALUE: return ATTACHMENTS;
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
	private TransferMode(int value, String name, String literal) {
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
	
} //TransferMode
