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
 
package com.ebmwebsourcing.petals.services.jms.jms;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Provides</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.jms.jms.JmsProvides#getMaxActive <em>Max Active</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.jms.jms.JmsProvides#getMaxWait <em>Max Wait</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.jms.jms.JmsProvides#getMaxIdle <em>Max Idle</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.jms.jms.JmsProvides#getTimeBetweenEvictionRunsMilles <em>Time Between Eviction Runs Milles</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.jms.jms.JmsProvides#getMinEvictableIdleTimeMillis <em>Min Evictable Idle Time Millis</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.jms.jms.JmsProvides#isTestWhileIdle <em>Test While Idle</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.jms.jms.JmsPackage#getJmsProvides()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface JmsProvides extends JmsExtension {
	/**
	 * Returns the value of the '<em><b>Max Active</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Active</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Active</em>' attribute.
	 * @see #setMaxActive(int)
	 * @see com.ebmwebsourcing.petals.services.jms.jms.JmsPackage#getJmsProvides_MaxActive()
	 * @model default="0" dataType="org.eclipse.emf.ecore.xml.type.Int" derived="true"
	 *        extendedMetaData="name='max-active' namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	int getMaxActive();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.jms.jms.JmsProvides#getMaxActive <em>Max Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Active</em>' attribute.
	 * @see #getMaxActive()
	 * @generated
	 */
	void setMaxActive(int value);

	/**
	 * Returns the value of the '<em><b>Max Wait</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Wait</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Wait</em>' attribute.
	 * @see #setMaxWait(int)
	 * @see com.ebmwebsourcing.petals.services.jms.jms.JmsPackage#getJmsProvides_MaxWait()
	 * @model default="0" dataType="org.eclipse.emf.ecore.xml.type.Int" derived="true"
	 *        extendedMetaData="name='max-wait' namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	int getMaxWait();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.jms.jms.JmsProvides#getMaxWait <em>Max Wait</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Wait</em>' attribute.
	 * @see #getMaxWait()
	 * @generated
	 */
	void setMaxWait(int value);

	/**
	 * Returns the value of the '<em><b>Max Idle</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Idle</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Idle</em>' attribute.
	 * @see #setMaxIdle(int)
	 * @see com.ebmwebsourcing.petals.services.jms.jms.JmsPackage#getJmsProvides_MaxIdle()
	 * @model default="0" dataType="org.eclipse.emf.ecore.xml.type.Int" derived="true"
	 *        extendedMetaData="name='max-idle' namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	int getMaxIdle();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.jms.jms.JmsProvides#getMaxIdle <em>Max Idle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Idle</em>' attribute.
	 * @see #getMaxIdle()
	 * @generated
	 */
	void setMaxIdle(int value);

	/**
	 * Returns the value of the '<em><b>Time Between Eviction Runs Milles</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time Between Eviction Runs Milles</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Between Eviction Runs Milles</em>' attribute.
	 * @see #setTimeBetweenEvictionRunsMilles(int)
	 * @see com.ebmwebsourcing.petals.services.jms.jms.JmsPackage#getJmsProvides_TimeBetweenEvictionRunsMilles()
	 * @model default="0" dataType="org.eclipse.emf.ecore.xml.type.Int" derived="true"
	 *        extendedMetaData="name='time-between-eviction-runs-millis' namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	int getTimeBetweenEvictionRunsMilles();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.jms.jms.JmsProvides#getTimeBetweenEvictionRunsMilles <em>Time Between Eviction Runs Milles</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Between Eviction Runs Milles</em>' attribute.
	 * @see #getTimeBetweenEvictionRunsMilles()
	 * @generated
	 */
	void setTimeBetweenEvictionRunsMilles(int value);

	/**
	 * Returns the value of the '<em><b>Min Evictable Idle Time Millis</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min Evictable Idle Time Millis</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min Evictable Idle Time Millis</em>' attribute.
	 * @see #setMinEvictableIdleTimeMillis(int)
	 * @see com.ebmwebsourcing.petals.services.jms.jms.JmsPackage#getJmsProvides_MinEvictableIdleTimeMillis()
	 * @model default="0" dataType="org.eclipse.emf.ecore.xml.type.Int" derived="true"
	 *        extendedMetaData="name='min-evictable-idle-time-millis' namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	int getMinEvictableIdleTimeMillis();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.jms.jms.JmsProvides#getMinEvictableIdleTimeMillis <em>Min Evictable Idle Time Millis</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min Evictable Idle Time Millis</em>' attribute.
	 * @see #getMinEvictableIdleTimeMillis()
	 * @generated
	 */
	void setMinEvictableIdleTimeMillis(int value);

	/**
	 * Returns the value of the '<em><b>Test While Idle</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test While Idle</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test While Idle</em>' attribute.
	 * @see #setTestWhileIdle(boolean)
	 * @see com.ebmwebsourcing.petals.services.jms.jms.JmsPackage#getJmsProvides_TestWhileIdle()
	 * @model default="" dataType="org.eclipse.emf.ecore.xml.type.Boolean" derived="true"
	 *        extendedMetaData="name='test-while-idle' namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	boolean isTestWhileIdle();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.jms.jms.JmsProvides#isTestWhileIdle <em>Test While Idle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Test While Idle</em>' attribute.
	 * @see #isTestWhileIdle()
	 * @generated
	 */
	void setTestWhileIdle(boolean value);

} // JmsProvides
