/**
 *  Copyright (c) 2009-2013, Linagora
 *  
 *  This source code is available under agreement available at
 *  http://www.petalslink.com/legal/licenses/petals-studio
 *  
 *  You should have received a copy of the agreement along with this program.
 *  If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 */
package com.sun.java.xml.ns.jbi;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.sun.java.xml.ns.jbi.Component#getIdentification <em>Identification</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.Component#getComponentClassName <em>Component Class Name</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.Component#getComponentClassPath <em>Component Class Path</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.Component#getBootstrapClassName <em>Bootstrap Class Name</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.Component#getBootstrapClassPath <em>Bootstrap Class Path</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.Component#getSharedLibraryList <em>Shared Library List</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.Component#getSharedLibrary <em>Shared Library</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.Component#getGroup <em>Group</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.Component#getAny <em>Any</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.Component#getAny1 <em>Any1</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.Component#getBootstrapClassLoaderDelegation <em>Bootstrap Class Loader Delegation</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.Component#getComponentClassLoaderDelegation <em>Component Class Loader Delegation</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.Component#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.sun.java.xml.ns.jbi.JbiPackage#getComponent()
 * @model extendedMetaData="name='Component' kind='elementOnly'"
 * @generated
 */
public interface Component extends EObject {
	/**
	 * Returns the value of the '<em><b>Identification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identification</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identification</em>' containment reference.
	 * @see #setIdentification(Identification)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getComponent_Identification()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='identification' namespace='##targetNamespace'"
	 * @generated
	 */
	Identification getIdentification();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.Component#getIdentification <em>Identification</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identification</em>' containment reference.
	 * @see #getIdentification()
	 * @generated
	 */
	void setIdentification(Identification value);

	/**
	 * Returns the value of the '<em><b>Component Class Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Class Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Class Name</em>' containment reference.
	 * @see #setComponentClassName(ComponentClassName)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getComponent_ComponentClassName()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='component-class-name' namespace='##targetNamespace'"
	 * @generated
	 */
	ComponentClassName getComponentClassName();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.Component#getComponentClassName <em>Component Class Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Class Name</em>' containment reference.
	 * @see #getComponentClassName()
	 * @generated
	 */
	void setComponentClassName(ComponentClassName value);

	/**
	 * Returns the value of the '<em><b>Component Class Path</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Class Path</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Class Path</em>' containment reference.
	 * @see #setComponentClassPath(ClassPath)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getComponent_ComponentClassPath()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='component-class-path' namespace='##targetNamespace'"
	 * @generated
	 */
	ClassPath getComponentClassPath();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.Component#getComponentClassPath <em>Component Class Path</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Class Path</em>' containment reference.
	 * @see #getComponentClassPath()
	 * @generated
	 */
	void setComponentClassPath(ClassPath value);

	/**
	 * Returns the value of the '<em><b>Bootstrap Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bootstrap Class Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bootstrap Class Name</em>' attribute.
	 * @see #setBootstrapClassName(String)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getComponent_BootstrapClassName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='bootstrap-class-name' namespace='##targetNamespace'"
	 * @generated
	 */
	String getBootstrapClassName();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.Component#getBootstrapClassName <em>Bootstrap Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bootstrap Class Name</em>' attribute.
	 * @see #getBootstrapClassName()
	 * @generated
	 */
	void setBootstrapClassName(String value);

	/**
	 * Returns the value of the '<em><b>Bootstrap Class Path</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bootstrap Class Path</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bootstrap Class Path</em>' containment reference.
	 * @see #setBootstrapClassPath(ClassPath)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getComponent_BootstrapClassPath()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='bootstrap-class-path' namespace='##targetNamespace'"
	 * @generated
	 */
	ClassPath getBootstrapClassPath();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.Component#getBootstrapClassPath <em>Bootstrap Class Path</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bootstrap Class Path</em>' containment reference.
	 * @see #getBootstrapClassPath()
	 * @generated
	 */
	void setBootstrapClassPath(ClassPath value);

	/**
	 * Returns the value of the '<em><b>Shared Library List</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Shared Library List</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Shared Library List</em>' attribute list.
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getComponent_SharedLibraryList()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='SharedLibraryList:5'"
	 * @generated
	 */
	FeatureMap getSharedLibraryList();

	/**
	 * Returns the value of the '<em><b>Shared Library</b></em>' containment reference list.
	 * The list contents are of type {@link com.sun.java.xml.ns.jbi.SharedLibraryType1}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Shared Library</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Shared Library</em>' containment reference list.
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getComponent_SharedLibrary()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='shared-library' namespace='##targetNamespace' group='#SharedLibraryList:5'"
	 * @generated
	 */
	EList<SharedLibraryType1> getSharedLibrary();

	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getComponent_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:7'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Any</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Any</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Any</em>' attribute list.
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getComponent_Any()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='elementWildcard' wildcards='##other' name=':8' processing='skip' group='#group:7'"
	 * @generated
	 */
	FeatureMap getAny();

	/**
	 * Returns the value of the '<em><b>Any1</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Any1</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Any1</em>' attribute list.
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getComponent_Any1()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='elementWildcard' wildcards='##local' name=':9' processing='skip' group='#group:7'"
	 * @generated
	 */
	FeatureMap getAny1();

	/**
	 * Returns the value of the '<em><b>Bootstrap Class Loader Delegation</b></em>' attribute.
	 * The literals are from the enumeration {@link com.sun.java.xml.ns.jbi.ClassLoaderDelegationType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bootstrap Class Loader Delegation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bootstrap Class Loader Delegation</em>' attribute.
	 * @see com.sun.java.xml.ns.jbi.ClassLoaderDelegationType
	 * @see #isSetBootstrapClassLoaderDelegation()
	 * @see #unsetBootstrapClassLoaderDelegation()
	 * @see #setBootstrapClassLoaderDelegation(ClassLoaderDelegationType)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getComponent_BootstrapClassLoaderDelegation()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='bootstrap-class-loader-delegation'"
	 * @generated
	 */
	ClassLoaderDelegationType getBootstrapClassLoaderDelegation();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.Component#getBootstrapClassLoaderDelegation <em>Bootstrap Class Loader Delegation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bootstrap Class Loader Delegation</em>' attribute.
	 * @see com.sun.java.xml.ns.jbi.ClassLoaderDelegationType
	 * @see #isSetBootstrapClassLoaderDelegation()
	 * @see #unsetBootstrapClassLoaderDelegation()
	 * @see #getBootstrapClassLoaderDelegation()
	 * @generated
	 */
	void setBootstrapClassLoaderDelegation(ClassLoaderDelegationType value);

	/**
	 * Unsets the value of the '{@link com.sun.java.xml.ns.jbi.Component#getBootstrapClassLoaderDelegation <em>Bootstrap Class Loader Delegation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetBootstrapClassLoaderDelegation()
	 * @see #getBootstrapClassLoaderDelegation()
	 * @see #setBootstrapClassLoaderDelegation(ClassLoaderDelegationType)
	 * @generated
	 */
	void unsetBootstrapClassLoaderDelegation();

	/**
	 * Returns whether the value of the '{@link com.sun.java.xml.ns.jbi.Component#getBootstrapClassLoaderDelegation <em>Bootstrap Class Loader Delegation</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Bootstrap Class Loader Delegation</em>' attribute is set.
	 * @see #unsetBootstrapClassLoaderDelegation()
	 * @see #getBootstrapClassLoaderDelegation()
	 * @see #setBootstrapClassLoaderDelegation(ClassLoaderDelegationType)
	 * @generated
	 */
	boolean isSetBootstrapClassLoaderDelegation();

	/**
	 * Returns the value of the '<em><b>Component Class Loader Delegation</b></em>' attribute.
	 * The literals are from the enumeration {@link com.sun.java.xml.ns.jbi.ClassLoaderDelegationType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Class Loader Delegation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Class Loader Delegation</em>' attribute.
	 * @see com.sun.java.xml.ns.jbi.ClassLoaderDelegationType
	 * @see #isSetComponentClassLoaderDelegation()
	 * @see #unsetComponentClassLoaderDelegation()
	 * @see #setComponentClassLoaderDelegation(ClassLoaderDelegationType)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getComponent_ComponentClassLoaderDelegation()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='component-class-loader-delegation'"
	 * @generated
	 */
	ClassLoaderDelegationType getComponentClassLoaderDelegation();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.Component#getComponentClassLoaderDelegation <em>Component Class Loader Delegation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Class Loader Delegation</em>' attribute.
	 * @see com.sun.java.xml.ns.jbi.ClassLoaderDelegationType
	 * @see #isSetComponentClassLoaderDelegation()
	 * @see #unsetComponentClassLoaderDelegation()
	 * @see #getComponentClassLoaderDelegation()
	 * @generated
	 */
	void setComponentClassLoaderDelegation(ClassLoaderDelegationType value);

	/**
	 * Unsets the value of the '{@link com.sun.java.xml.ns.jbi.Component#getComponentClassLoaderDelegation <em>Component Class Loader Delegation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetComponentClassLoaderDelegation()
	 * @see #getComponentClassLoaderDelegation()
	 * @see #setComponentClassLoaderDelegation(ClassLoaderDelegationType)
	 * @generated
	 */
	void unsetComponentClassLoaderDelegation();

	/**
	 * Returns whether the value of the '{@link com.sun.java.xml.ns.jbi.Component#getComponentClassLoaderDelegation <em>Component Class Loader Delegation</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Component Class Loader Delegation</em>' attribute is set.
	 * @see #unsetComponentClassLoaderDelegation()
	 * @see #getComponentClassLoaderDelegation()
	 * @see #setComponentClassLoaderDelegation(ClassLoaderDelegationType)
	 * @generated
	 */
	boolean isSetComponentClassLoaderDelegation();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.sun.java.xml.ns.jbi.ComponentType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see com.sun.java.xml.ns.jbi.ComponentType
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #setType(ComponentType)
	 * @see com.sun.java.xml.ns.jbi.JbiPackage#getComponent_Type()
	 * @model unsettable="true" required="true"
	 *        extendedMetaData="kind='attribute' name='type'"
	 * @generated
	 */
	ComponentType getType();

	/**
	 * Sets the value of the '{@link com.sun.java.xml.ns.jbi.Component#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see com.sun.java.xml.ns.jbi.ComponentType
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #getType()
	 * @generated
	 */
	void setType(ComponentType value);

	/**
	 * Unsets the value of the '{@link com.sun.java.xml.ns.jbi.Component#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetType()
	 * @see #getType()
	 * @see #setType(ComponentType)
	 * @generated
	 */
	void unsetType();

	/**
	 * Returns whether the value of the '{@link com.sun.java.xml.ns.jbi.Component#getType <em>Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Type</em>' attribute is set.
	 * @see #unsetType()
	 * @see #getType()
	 * @see #setType(ComponentType)
	 * @generated
	 */
	boolean isSetType();

} // Component
