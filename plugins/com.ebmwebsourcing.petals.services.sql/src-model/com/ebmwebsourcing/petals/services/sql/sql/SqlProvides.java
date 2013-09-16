/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.sql.sql;

import com.sun.java.xml.ns.jbi.Provides;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Provides</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getUrl <em>Url</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getUser <em>User</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getPassword <em>Password</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getDriver <em>Driver</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getMaxActive <em>Max Active</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getMaxIdle <em>Max Idle</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getMinIdle <em>Min Idle</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getMaxWait <em>Max Wait</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getTimeBetweenEvictionRunsMillis <em>Time Between Eviction Runs Millis</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#isMetadata <em>Metadata</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getStoredProcedure <em>Stored Procedure</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.sql.sql.SqlPackage#getSqlProvides()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface SqlProvides extends Provides {
	/**
	 * Returns the value of the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Url</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Url</em>' attribute.
	 * @see #isSetUrl()
	 * @see #unsetUrl()
	 * @see #setUrl(String)
	 * @see com.ebmwebsourcing.petals.services.sql.sql.SqlPackage#getSqlProvides_Url()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' group='#group:0' kind='element'"
	 * @generated
	 */
	String getUrl();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getUrl <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Url</em>' attribute.
	 * @see #isSetUrl()
	 * @see #unsetUrl()
	 * @see #getUrl()
	 * @generated
	 */
	void setUrl(String value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getUrl <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetUrl()
	 * @see #getUrl()
	 * @see #setUrl(String)
	 * @generated
	 */
	void unsetUrl();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getUrl <em>Url</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Url</em>' attribute is set.
	 * @see #unsetUrl()
	 * @see #getUrl()
	 * @see #setUrl(String)
	 * @generated
	 */
	boolean isSetUrl();

	/**
	 * Returns the value of the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User</em>' attribute.
	 * @see #isSetUser()
	 * @see #unsetUser()
	 * @see #setUser(String)
	 * @see com.ebmwebsourcing.petals.services.sql.sql.SqlPackage#getSqlProvides_User()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' group='#group:0' kind='element'"
	 * @generated
	 */
	String getUser();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getUser <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User</em>' attribute.
	 * @see #isSetUser()
	 * @see #unsetUser()
	 * @see #getUser()
	 * @generated
	 */
	void setUser(String value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getUser <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetUser()
	 * @see #getUser()
	 * @see #setUser(String)
	 * @generated
	 */
	void unsetUser();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getUser <em>User</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>User</em>' attribute is set.
	 * @see #unsetUser()
	 * @see #getUser()
	 * @see #setUser(String)
	 * @generated
	 */
	boolean isSetUser();

	/**
	 * Returns the value of the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Password</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Password</em>' attribute.
	 * @see #isSetPassword()
	 * @see #unsetPassword()
	 * @see #setPassword(String)
	 * @see com.ebmwebsourcing.petals.services.sql.sql.SqlPackage#getSqlProvides_Password()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' group='#group:0' kind='element'"
	 * @generated
	 */
	String getPassword();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getPassword <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Password</em>' attribute.
	 * @see #isSetPassword()
	 * @see #unsetPassword()
	 * @see #getPassword()
	 * @generated
	 */
	void setPassword(String value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getPassword <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPassword()
	 * @see #getPassword()
	 * @see #setPassword(String)
	 * @generated
	 */
	void unsetPassword();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getPassword <em>Password</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Password</em>' attribute is set.
	 * @see #unsetPassword()
	 * @see #getPassword()
	 * @see #setPassword(String)
	 * @generated
	 */
	boolean isSetPassword();

	/**
	 * Returns the value of the '<em><b>Driver</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Driver</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Driver</em>' attribute.
	 * @see #setDriver(String)
	 * @see com.ebmwebsourcing.petals.services.sql.sql.SqlPackage#getSqlProvides_Driver()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' group='#group:0' kind='element'"
	 * @generated
	 */
	String getDriver();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getDriver <em>Driver</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Driver</em>' attribute.
	 * @see #getDriver()
	 * @generated
	 */
	void setDriver(String value);

	/**
	 * Returns the value of the '<em><b>Max Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Active</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Active</em>' attribute.
	 * @see #setMaxActive(int)
	 * @see com.ebmwebsourcing.petals.services.sql.sql.SqlPackage#getSqlProvides_MaxActive()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' group='#group:0' kind='element'"
	 * @generated
	 */
	int getMaxActive();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getMaxActive <em>Max Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Active</em>' attribute.
	 * @see #getMaxActive()
	 * @generated
	 */
	void setMaxActive(int value);

	/**
	 * Returns the value of the '<em><b>Max Idle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Idle</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Idle</em>' attribute.
	 * @see #setMaxIdle(int)
	 * @see com.ebmwebsourcing.petals.services.sql.sql.SqlPackage#getSqlProvides_MaxIdle()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' group='#group:0' kind='element'"
	 * @generated
	 */
	int getMaxIdle();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getMaxIdle <em>Max Idle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Idle</em>' attribute.
	 * @see #getMaxIdle()
	 * @generated
	 */
	void setMaxIdle(int value);

	/**
	 * Returns the value of the '<em><b>Min Idle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min Idle</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min Idle</em>' attribute.
	 * @see #setMinIdle(int)
	 * @see com.ebmwebsourcing.petals.services.sql.sql.SqlPackage#getSqlProvides_MinIdle()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' group='#group:0' kind='element'"
	 * @generated
	 */
	int getMinIdle();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getMinIdle <em>Min Idle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min Idle</em>' attribute.
	 * @see #getMinIdle()
	 * @generated
	 */
	void setMinIdle(int value);

	/**
	 * Returns the value of the '<em><b>Max Wait</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Wait</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Wait</em>' attribute.
	 * @see #setMaxWait(int)
	 * @see com.ebmwebsourcing.petals.services.sql.sql.SqlPackage#getSqlProvides_MaxWait()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' group='#group:0' kind='element'"
	 * @generated
	 */
	int getMaxWait();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getMaxWait <em>Max Wait</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Wait</em>' attribute.
	 * @see #getMaxWait()
	 * @generated
	 */
	void setMaxWait(int value);

	/**
	 * Returns the value of the '<em><b>Time Between Eviction Runs Millis</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time Between Eviction Runs Millis</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Between Eviction Runs Millis</em>' attribute.
	 * @see #setTimeBetweenEvictionRunsMillis(int)
	 * @see com.ebmwebsourcing.petals.services.sql.sql.SqlPackage#getSqlProvides_TimeBetweenEvictionRunsMillis()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' group='#group:0' kind='element'"
	 * @generated
	 */
	int getTimeBetweenEvictionRunsMillis();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getTimeBetweenEvictionRunsMillis <em>Time Between Eviction Runs Millis</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Between Eviction Runs Millis</em>' attribute.
	 * @see #getTimeBetweenEvictionRunsMillis()
	 * @generated
	 */
	void setTimeBetweenEvictionRunsMillis(int value);

	/**
	 * Returns the value of the '<em><b>Metadata</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metadata</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metadata</em>' attribute.
	 * @see #setMetadata(boolean)
	 * @see com.ebmwebsourcing.petals.services.sql.sql.SqlPackage#getSqlProvides_Metadata()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' group='#group:0' kind='element'"
	 * @generated
	 */
	boolean isMetadata();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#isMetadata <em>Metadata</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Metadata</em>' attribute.
	 * @see #isMetadata()
	 * @generated
	 */
	void setMetadata(boolean value);

	/**
	 * Returns the value of the '<em><b>Stored Procedure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stored Procedure</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stored Procedure</em>' attribute.
	 * @see #setStoredProcedure(String)
	 * @see com.ebmwebsourcing.petals.services.sql.sql.SqlPackage#getSqlProvides_StoredProcedure()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' group='#group:0' kind='element'"
	 * @generated
	 */
	String getStoredProcedure();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.sql.sql.SqlProvides#getStoredProcedure <em>Stored Procedure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stored Procedure</em>' attribute.
	 * @see #getStoredProcedure()
	 * @generated
	 */
	void setStoredProcedure(String value);

} // SqlProvides
