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

import java.math.BigDecimal;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Jbi</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.sun.java.xml.ns.jbi.Jbi#getComponent <em>Component</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.Jbi#getSharedLibrary <em>Shared Library</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.Jbi#getServiceAssembly <em>Service Assembly</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.Jbi#getServices <em>Services</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.Jbi#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.sun.java.xml.ns.jbi.JbiPackage#getJbi()
 * @model extendedMetaData="name='jbi' kind='elementOnly'"
 * @generated
 */
public interface Jbi extends EObject {
	/**
	 * Returns the value of the '<em><b>Component</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component</em>' containment reference.
	 * @see #setComponent(Component)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getJbi_Component()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='component' namespace='##targetNamespace'"
	 * @generated
	 */
	Component getComponent();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.Jbi#getComponent <em>Component</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component</em>' containment reference.
	 * @see #getComponent()
	 * @generated
	 */
	void setComponent(Component value);

	/**
	 * Returns the value of the '<em><b>Shared Library</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Shared Library</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Shared Library</em>' containment reference.
	 * @see #setSharedLibrary(SharedLibraryType)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getJbi_SharedLibrary()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='shared-library' namespace='##targetNamespace'"
	 * @generated
	 */
	SharedLibraryType getSharedLibrary();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.Jbi#getSharedLibrary <em>Shared Library</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shared Library</em>' containment reference.
	 * @see #getSharedLibrary()
	 * @generated
	 */
	void setSharedLibrary(SharedLibraryType value);

	/**
	 * Returns the value of the '<em><b>Service Assembly</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service Assembly</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Service Assembly</em>' containment reference.
	 * @see #setServiceAssembly(ServiceAssembly)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getJbi_ServiceAssembly()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='service-assembly' namespace='##targetNamespace'"
	 * @generated
	 */
	ServiceAssembly getServiceAssembly();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.Jbi#getServiceAssembly <em>Service Assembly</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Assembly</em>' containment reference.
	 * @see #getServiceAssembly()
	 * @generated
	 */
	void setServiceAssembly(ServiceAssembly value);

	/**
	 * Returns the value of the '<em><b>Services</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Services</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Services</em>' containment reference.
	 * @see #setServices(Services)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getJbi_Services()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='services' namespace='##targetNamespace'"
	 * @generated
	 */
	Services getServices();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.Jbi#getServices <em>Services</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Services</em>' containment reference.
	 * @see #getServices()
	 * @generated
	 */
	void setServices(Services value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(BigDecimal)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getJbi_Version()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Decimal" required="true"
	 *        extendedMetaData="kind='attribute' name='version'"
	 * @generated
	 */
	BigDecimal getVersion();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.Jbi#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(BigDecimal value);

} // Jbi
