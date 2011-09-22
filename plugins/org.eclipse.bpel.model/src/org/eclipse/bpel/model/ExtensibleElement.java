/**
 * <copyright>
 * </copyright>
 *
 * $Id: ExtensibleElement.java,v 1.3 2007/08/01 21:02:30 mchmielewski Exp $
 */
package org.eclipse.bpel.model;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extensible Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpel.model.ExtensibleElement#getDocumentation <em>Documentation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpel.model.BPELPackage#getExtensibleElement()
 * @model
 * @generated
 */
public interface ExtensibleElement extends
		org.eclipse.wst.wsdl.ExtensibleElement {
	/**
	 * Returns the value of the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documentation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Documentation</em>' containment reference.
	 * @see #isSetDocumentation()
	 * @see #unsetDocumentation()
	 * @see #setDocumentation(Documentation)
	 * @see org.eclipse.bpel.model.BPELPackage#getExtensibleElement_Documentation()
	 * @model containment="true" unsettable="true"
	 * @generated
	 */
	Documentation getDocumentation();

	/**
	 * Sets the value of the '{@link org.eclipse.bpel.model.ExtensibleElement#getDocumentation <em>Documentation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Documentation</em>' containment reference.
	 * @see #isSetDocumentation()
	 * @see #unsetDocumentation()
	 * @see #getDocumentation()
	 * @generated
	 */
	void setDocumentation(Documentation value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpel.model.ExtensibleElement#getDocumentation <em>Documentation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDocumentation()
	 * @see #getDocumentation()
	 * @see #setDocumentation(Documentation)
	 * @generated
	 */
	void unsetDocumentation();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpel.model.ExtensibleElement#getDocumentation <em>Documentation</em>}' containment reference is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Documentation</em>' containment reference is set.
	 * @see #unsetDocumentation()
	 * @see #getDocumentation()
	 * @see #setDocumentation(Documentation)
	 * @generated
	 */
	boolean isSetDocumentation();

	/**
	 * Get transient property of the model object. 
	 * These properties are never saved as part of the model. 
	 * 
	 * @param <T> the type of the
	 * @param key the key of the property.
	 * @return the value of the property
	 * @generated NOT
	 */

	<T extends Object> T getTransientProperty(String key);

	/** 
	 * Set transient property of this model object. 
	 * These properties are never saved as part of the model object.
	 * @param <T>  
	 * @param key the key
	 * @param value the value to set
	 * @return  the previous value or null
	 * @generated NOT
	 */

	<T extends Object> T setTransientProperty(String key, T value);

} // ExtensibleElement
