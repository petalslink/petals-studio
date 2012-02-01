/**
 * Copyright (c) 2011-2012, EBM WebSourcing
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
	 * @see #setEjbJndiName(String)
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbPackage#getEjbProvides_EjbJndiName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="name='ejb.jndi.name' namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getEjbJndiName();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getEjbJndiName <em>Ejb Jndi Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ejb Jndi Name</em>' attribute.
	 * @see #getEjbJndiName()
	 * @generated
	 */
	void setEjbJndiName(String value);

	/**
	 * Returns the value of the '<em><b>Java Naming Factory Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Java Naming Factory Initial</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Java Naming Factory Initial</em>' attribute.
	 * @see #setJavaNamingFactoryInitial(String)
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbPackage#getEjbProvides_JavaNamingFactoryInitial()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="name='java.naming.factory.initial' namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getJavaNamingFactoryInitial();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getJavaNamingFactoryInitial <em>Java Naming Factory Initial</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Java Naming Factory Initial</em>' attribute.
	 * @see #getJavaNamingFactoryInitial()
	 * @generated
	 */
	void setJavaNamingFactoryInitial(String value);

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
	 * @see #setJavaNamingProviderUrl(String)
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbPackage#getEjbProvides_JavaNamingProviderUrl()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="name='java.naming.provider.url' namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getJavaNamingProviderUrl();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.ejb.ejb.EjbProvides#getJavaNamingProviderUrl <em>Java Naming Provider Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Java Naming Provider Url</em>' attribute.
	 * @see #getJavaNamingProviderUrl()
	 * @generated
	 */
	void setJavaNamingProviderUrl(String value);

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
	 * @see #setEjbVersion(EjbVersion)
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbPackage#getEjbProvides_EjbVersion()
	 * @model required="true" derived="true"
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
	 * @see #getEjbVersion()
	 * @generated
	 */
	void setEjbVersion(EjbVersion value);

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
	 * @see #setMarshallingEngine(XmlEngine)
	 * @see com.ebmwebsourcing.petals.services.ejb.ejb.EjbPackage#getEjbProvides_MarshallingEngine()
	 * @model derived="true"
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
	 * @see #getMarshallingEngine()
	 * @generated
	 */
	void setMarshallingEngine(XmlEngine value);

} // EjbProvides
