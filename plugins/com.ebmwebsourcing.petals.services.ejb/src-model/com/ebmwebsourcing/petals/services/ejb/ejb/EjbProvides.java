/**
 * Copyright (c) 2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.ejb.ejb;

import com.sun.java.xml.ns.jbi.Provides;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Provides</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getEjbJndiName <em>Ejb Jndi Name</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getJavaNamingFactoryInitial <em>Java Naming Factory Initial</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getJavaNamingFactoryUrlPkgs <em>Java Naming Factory Url Pkgs</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getJavaNamingProviderUrl <em>Java Naming Provider Url</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getEjbVersion <em>Ejb Version</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getEjbHomeInterface <em>Ejb Home Interface</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getSecurityName <em>Security Name</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getSecurityPrincipal <em>Security Principal</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getSecurityCredencials <em>Security Credencials</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getMarshallingEngine <em>Marshalling Engine</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbPackage#getEjbProvides()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface EjbProvides extends Provides {
	/**
	 * Returns the value of the '<em><b>Ejb Jndi Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ejb Jndi Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ejb Jndi Name</em>' attribute.
	 * @see #isSetEjbJndiName()
	 * @see #unsetEjbJndiName()
	 * @see #setEjbJndiName(String)
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbPackage#getEjbProvides_EjbJndiName()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="name='ejb.jndi.name' namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getEjbJndiName();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getEjbJndiName <em>Ejb Jndi Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ejb Jndi Name</em>' attribute.
	 * @see #isSetEjbJndiName()
	 * @see #unsetEjbJndiName()
	 * @see #getEjbJndiName()
	 * @generated
	 */
	void setEjbJndiName(String value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getEjbJndiName <em>Ejb Jndi Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEjbJndiName()
	 * @see #getEjbJndiName()
	 * @see #setEjbJndiName(String)
	 * @generated
	 */
	void unsetEjbJndiName();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getEjbJndiName <em>Ejb Jndi Name</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Ejb Jndi Name</em>' attribute is set.
	 * @see #unsetEjbJndiName()
	 * @see #getEjbJndiName()
	 * @see #setEjbJndiName(String)
	 * @generated
	 */
	boolean isSetEjbJndiName();

	/**
	 * Returns the value of the '<em><b>Java Naming Factory Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Java Naming Factory Initial</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Java Naming Factory Initial</em>' attribute.
	 * @see #isSetJavaNamingFactoryInitial()
	 * @see #unsetJavaNamingFactoryInitial()
	 * @see #setJavaNamingFactoryInitial(String)
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbPackage#getEjbProvides_JavaNamingFactoryInitial()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="name='java.naming.factory.initial' namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getJavaNamingFactoryInitial();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getJavaNamingFactoryInitial <em>Java Naming Factory Initial</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Java Naming Factory Initial</em>' attribute.
	 * @see #isSetJavaNamingFactoryInitial()
	 * @see #unsetJavaNamingFactoryInitial()
	 * @see #getJavaNamingFactoryInitial()
	 * @generated
	 */
	void setJavaNamingFactoryInitial(String value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getJavaNamingFactoryInitial <em>Java Naming Factory Initial</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetJavaNamingFactoryInitial()
	 * @see #getJavaNamingFactoryInitial()
	 * @see #setJavaNamingFactoryInitial(String)
	 * @generated
	 */
	void unsetJavaNamingFactoryInitial();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getJavaNamingFactoryInitial <em>Java Naming Factory Initial</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Java Naming Factory Initial</em>' attribute is set.
	 * @see #unsetJavaNamingFactoryInitial()
	 * @see #getJavaNamingFactoryInitial()
	 * @see #setJavaNamingFactoryInitial(String)
	 * @generated
	 */
	boolean isSetJavaNamingFactoryInitial();

	/**
	 * Returns the value of the '<em><b>Java Naming Factory Url Pkgs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Java Naming Factory Url Pkgs</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Java Naming Factory Url Pkgs</em>' attribute.
	 * @see #setJavaNamingFactoryUrlPkgs(String)
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbPackage#getEjbProvides_JavaNamingFactoryUrlPkgs()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="name='java.naming.factory.url.pkgs' namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getJavaNamingFactoryUrlPkgs();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getJavaNamingFactoryUrlPkgs <em>Java Naming Factory Url Pkgs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Java Naming Factory Url Pkgs</em>' attribute.
	 * @see #getJavaNamingFactoryUrlPkgs()
	 * @generated
	 */
	void setJavaNamingFactoryUrlPkgs(String value);

	/**
	 * Returns the value of the '<em><b>Java Naming Provider Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Java Naming Provider Url</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Java Naming Provider Url</em>' attribute.
	 * @see #isSetJavaNamingProviderUrl()
	 * @see #unsetJavaNamingProviderUrl()
	 * @see #setJavaNamingProviderUrl(String)
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbPackage#getEjbProvides_JavaNamingProviderUrl()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="name='java.naming.provider.url' namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getJavaNamingProviderUrl();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getJavaNamingProviderUrl <em>Java Naming Provider Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Java Naming Provider Url</em>' attribute.
	 * @see #isSetJavaNamingProviderUrl()
	 * @see #unsetJavaNamingProviderUrl()
	 * @see #getJavaNamingProviderUrl()
	 * @generated
	 */
	void setJavaNamingProviderUrl(String value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getJavaNamingProviderUrl <em>Java Naming Provider Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetJavaNamingProviderUrl()
	 * @see #getJavaNamingProviderUrl()
	 * @see #setJavaNamingProviderUrl(String)
	 * @generated
	 */
	void unsetJavaNamingProviderUrl();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getJavaNamingProviderUrl <em>Java Naming Provider Url</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Java Naming Provider Url</em>' attribute is set.
	 * @see #unsetJavaNamingProviderUrl()
	 * @see #getJavaNamingProviderUrl()
	 * @see #setJavaNamingProviderUrl(String)
	 * @generated
	 */
	boolean isSetJavaNamingProviderUrl();

	/**
	 * Returns the value of the '<em><b>Ejb Version</b></em>' attribute.
	 * The literals are from the enumeration {@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbVersion}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ejb Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ejb Version</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbVersion
	 * @see #isSetEjbVersion()
	 * @see #unsetEjbVersion()
	 * @see #setEjbVersion(EjbVersion)
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbPackage#getEjbProvides_EjbVersion()
	 * @model unsettable="true" required="true" derived="true"
	 *        extendedMetaData="name='ejb.version' namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	EjbVersion getEjbVersion();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getEjbVersion <em>Ejb Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ejb Version</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbVersion
	 * @see #isSetEjbVersion()
	 * @see #unsetEjbVersion()
	 * @see #getEjbVersion()
	 * @generated
	 */
	void setEjbVersion(EjbVersion value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getEjbVersion <em>Ejb Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEjbVersion()
	 * @see #getEjbVersion()
	 * @see #setEjbVersion(EjbVersion)
	 * @generated
	 */
	void unsetEjbVersion();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getEjbVersion <em>Ejb Version</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Ejb Version</em>' attribute is set.
	 * @see #unsetEjbVersion()
	 * @see #getEjbVersion()
	 * @see #setEjbVersion(EjbVersion)
	 * @generated
	 */
	boolean isSetEjbVersion();

	/**
	 * Returns the value of the '<em><b>Ejb Home Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ejb Home Interface</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ejb Home Interface</em>' attribute.
	 * @see #setEjbHomeInterface(String)
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbPackage#getEjbProvides_EjbHomeInterface()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="name='ejb.home.interface' namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getEjbHomeInterface();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getEjbHomeInterface <em>Ejb Home Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ejb Home Interface</em>' attribute.
	 * @see #getEjbHomeInterface()
	 * @generated
	 */
	void setEjbHomeInterface(String value);

	/**
	 * Returns the value of the '<em><b>Security Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Security Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Security Name</em>' attribute.
	 * @see #setSecurityName(String)
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbPackage#getEjbProvides_SecurityName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="name='security.name' namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getSecurityName();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getSecurityName <em>Security Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Security Name</em>' attribute.
	 * @see #getSecurityName()
	 * @generated
	 */
	void setSecurityName(String value);

	/**
	 * Returns the value of the '<em><b>Security Principal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Security Principal</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Security Principal</em>' attribute.
	 * @see #setSecurityPrincipal(String)
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbPackage#getEjbProvides_SecurityPrincipal()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="name='security.principal' namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getSecurityPrincipal();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getSecurityPrincipal <em>Security Principal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Security Principal</em>' attribute.
	 * @see #getSecurityPrincipal()
	 * @generated
	 */
	void setSecurityPrincipal(String value);

	/**
	 * Returns the value of the '<em><b>Security Credencials</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Security Credencials</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Security Credencials</em>' attribute.
	 * @see #setSecurityCredencials(String)
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbPackage#getEjbProvides_SecurityCredencials()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="name='security.credencials' namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getSecurityCredencials();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getSecurityCredencials <em>Security Credencials</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Security Credencials</em>' attribute.
	 * @see #getSecurityCredencials()
	 * @generated
	 */
	void setSecurityCredencials(String value);

	/**
	 * Returns the value of the '<em><b>Marshalling Engine</b></em>' attribute.
	 * The literals are from the enumeration {@link com.ebmwebsourcing.petals.services.ejb.ejb.XmlEngine}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Marshalling Engine</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Marshalling Engine</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.XmlEngine
	 * @see #isSetMarshallingEngine()
	 * @see #unsetMarshallingEngine()
	 * @see #setMarshallingEngine(XmlEngine)
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbPackage#getEjbProvides_MarshallingEngine()
	 * @model unsettable="true" derived="true"
	 *        extendedMetaData="name='marshalling.engine' namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	XmlEngine getMarshallingEngine();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getMarshallingEngine <em>Marshalling Engine</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Marshalling Engine</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.XmlEngine
	 * @see #isSetMarshallingEngine()
	 * @see #unsetMarshallingEngine()
	 * @see #getMarshallingEngine()
	 * @generated
	 */
	void setMarshallingEngine(XmlEngine value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getMarshallingEngine <em>Marshalling Engine</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMarshallingEngine()
	 * @see #getMarshallingEngine()
	 * @see #setMarshallingEngine(XmlEngine)
	 * @generated
	 */
	void unsetMarshallingEngine();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getMarshallingEngine <em>Marshalling Engine</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Marshalling Engine</em>' attribute is set.
	 * @see #unsetMarshallingEngine()
	 * @see #getMarshallingEngine()
	 * @see #setMarshallingEngine(XmlEngine)
	 * @generated
	 */
	boolean isSetMarshallingEngine();

} // EjbProvides
