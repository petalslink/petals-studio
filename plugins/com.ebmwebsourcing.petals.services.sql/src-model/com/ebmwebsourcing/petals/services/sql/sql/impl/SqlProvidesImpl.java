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
package com.ebmwebsourcing.petals.services.sql.sql.impl;

import com.ebmwebsourcing.petals.services.sql.sql.SqlPackage;
import com.ebmwebsourcing.petals.services.sql.sql.SqlProvides;

import com.sun.java.xml.ns.jbi.impl.ProvidesImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Provides</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.sql.sql.impl.SqlProvidesImpl#getUrl <em>Url</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sql.sql.impl.SqlProvidesImpl#getUser <em>User</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sql.sql.impl.SqlProvidesImpl#getPassword <em>Password</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sql.sql.impl.SqlProvidesImpl#getDriver <em>Driver</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sql.sql.impl.SqlProvidesImpl#getMaxActive <em>Max Active</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sql.sql.impl.SqlProvidesImpl#getMaxIdle <em>Max Idle</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sql.sql.impl.SqlProvidesImpl#getMinIdle <em>Min Idle</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sql.sql.impl.SqlProvidesImpl#getMaxWait <em>Max Wait</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sql.sql.impl.SqlProvidesImpl#getTimeBetweenEvictionRunsMillis <em>Time Between Eviction Runs Millis</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.sql.sql.impl.SqlProvidesImpl#isMetadata <em>Metadata</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SqlProvidesImpl extends ProvidesImpl implements SqlProvides {
	/**
	 * The default value of the '{@link #getUrl() <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrl()
	 * @generated
	 * @ordered
	 */
	protected static final String URL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUrl() <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrl()
	 * @generated
	 * @ordered
	 */
	protected String url = URL_EDEFAULT;

	/**
	 * The default value of the '{@link #getUser() <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUser()
	 * @generated
	 * @ordered
	 */
	protected static final String USER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUser() <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUser()
	 * @generated
	 * @ordered
	 */
	protected String user = USER_EDEFAULT;

	/**
	 * The default value of the '{@link #getPassword() <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPassword()
	 * @generated
	 * @ordered
	 */
	protected static final String PASSWORD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPassword() <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPassword()
	 * @generated
	 * @ordered
	 */
	protected String password = PASSWORD_EDEFAULT;

	/**
	 * The default value of the '{@link #getDriver() <em>Driver</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDriver()
	 * @generated
	 * @ordered
	 */
	protected static final String DRIVER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDriver() <em>Driver</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDriver()
	 * @generated
	 * @ordered
	 */
	protected String driver = DRIVER_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxActive() <em>Max Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxActive()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_ACTIVE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaxActive() <em>Max Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxActive()
	 * @generated
	 * @ordered
	 */
	protected int maxActive = MAX_ACTIVE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxIdle() <em>Max Idle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxIdle()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_IDLE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaxIdle() <em>Max Idle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxIdle()
	 * @generated
	 * @ordered
	 */
	protected int maxIdle = MAX_IDLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinIdle() <em>Min Idle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinIdle()
	 * @generated
	 * @ordered
	 */
	protected static final int MIN_IDLE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMinIdle() <em>Min Idle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinIdle()
	 * @generated
	 * @ordered
	 */
	protected int minIdle = MIN_IDLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxWait() <em>Max Wait</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxWait()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_WAIT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaxWait() <em>Max Wait</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxWait()
	 * @generated
	 * @ordered
	 */
	protected int maxWait = MAX_WAIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getTimeBetweenEvictionRunsMillis() <em>Time Between Eviction Runs Millis</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeBetweenEvictionRunsMillis()
	 * @generated
	 * @ordered
	 */
	protected static final int TIME_BETWEEN_EVICTION_RUNS_MILLIS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTimeBetweenEvictionRunsMillis() <em>Time Between Eviction Runs Millis</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeBetweenEvictionRunsMillis()
	 * @generated
	 * @ordered
	 */
	protected int timeBetweenEvictionRunsMillis = TIME_BETWEEN_EVICTION_RUNS_MILLIS_EDEFAULT;

	/**
	 * The default value of the '{@link #isMetadata() <em>Metadata</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMetadata()
	 * @generated
	 * @ordered
	 */
	protected static final boolean METADATA_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMetadata() <em>Metadata</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMetadata()
	 * @generated
	 * @ordered
	 */
	protected boolean metadata = METADATA_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SqlProvidesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SqlPackage.Literals.SQL_PROVIDES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUrl(String newUrl) {
		String oldUrl = url;
		url = newUrl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SQL_PROVIDES__URL, oldUrl, url));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUser() {
		return user;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUser(String newUser) {
		String oldUser = user;
		user = newUser;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SQL_PROVIDES__USER, oldUser, user));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPassword(String newPassword) {
		String oldPassword = password;
		password = newPassword;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SQL_PROVIDES__PASSWORD, oldPassword, password));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDriver(String newDriver) {
		String oldDriver = driver;
		driver = newDriver;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SQL_PROVIDES__DRIVER, oldDriver, driver));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaxActive() {
		return maxActive;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxActive(int newMaxActive) {
		int oldMaxActive = maxActive;
		maxActive = newMaxActive;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SQL_PROVIDES__MAX_ACTIVE, oldMaxActive, maxActive));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaxIdle() {
		return maxIdle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxIdle(int newMaxIdle) {
		int oldMaxIdle = maxIdle;
		maxIdle = newMaxIdle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SQL_PROVIDES__MAX_IDLE, oldMaxIdle, maxIdle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMinIdle() {
		return minIdle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinIdle(int newMinIdle) {
		int oldMinIdle = minIdle;
		minIdle = newMinIdle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SQL_PROVIDES__MIN_IDLE, oldMinIdle, minIdle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaxWait() {
		return maxWait;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxWait(int newMaxWait) {
		int oldMaxWait = maxWait;
		maxWait = newMaxWait;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SQL_PROVIDES__MAX_WAIT, oldMaxWait, maxWait));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeBetweenEvictionRunsMillis(int newTimeBetweenEvictionRunsMillis) {
		int oldTimeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
		timeBetweenEvictionRunsMillis = newTimeBetweenEvictionRunsMillis;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SQL_PROVIDES__TIME_BETWEEN_EVICTION_RUNS_MILLIS, oldTimeBetweenEvictionRunsMillis, timeBetweenEvictionRunsMillis));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMetadata() {
		return metadata;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMetadata(boolean newMetadata) {
		boolean oldMetadata = metadata;
		metadata = newMetadata;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SQL_PROVIDES__METADATA, oldMetadata, metadata));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SqlPackage.SQL_PROVIDES__URL:
				return getUrl();
			case SqlPackage.SQL_PROVIDES__USER:
				return getUser();
			case SqlPackage.SQL_PROVIDES__PASSWORD:
				return getPassword();
			case SqlPackage.SQL_PROVIDES__DRIVER:
				return getDriver();
			case SqlPackage.SQL_PROVIDES__MAX_ACTIVE:
				return getMaxActive();
			case SqlPackage.SQL_PROVIDES__MAX_IDLE:
				return getMaxIdle();
			case SqlPackage.SQL_PROVIDES__MIN_IDLE:
				return getMinIdle();
			case SqlPackage.SQL_PROVIDES__MAX_WAIT:
				return getMaxWait();
			case SqlPackage.SQL_PROVIDES__TIME_BETWEEN_EVICTION_RUNS_MILLIS:
				return getTimeBetweenEvictionRunsMillis();
			case SqlPackage.SQL_PROVIDES__METADATA:
				return isMetadata();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SqlPackage.SQL_PROVIDES__URL:
				setUrl((String)newValue);
				return;
			case SqlPackage.SQL_PROVIDES__USER:
				setUser((String)newValue);
				return;
			case SqlPackage.SQL_PROVIDES__PASSWORD:
				setPassword((String)newValue);
				return;
			case SqlPackage.SQL_PROVIDES__DRIVER:
				setDriver((String)newValue);
				return;
			case SqlPackage.SQL_PROVIDES__MAX_ACTIVE:
				setMaxActive((Integer)newValue);
				return;
			case SqlPackage.SQL_PROVIDES__MAX_IDLE:
				setMaxIdle((Integer)newValue);
				return;
			case SqlPackage.SQL_PROVIDES__MIN_IDLE:
				setMinIdle((Integer)newValue);
				return;
			case SqlPackage.SQL_PROVIDES__MAX_WAIT:
				setMaxWait((Integer)newValue);
				return;
			case SqlPackage.SQL_PROVIDES__TIME_BETWEEN_EVICTION_RUNS_MILLIS:
				setTimeBetweenEvictionRunsMillis((Integer)newValue);
				return;
			case SqlPackage.SQL_PROVIDES__METADATA:
				setMetadata((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case SqlPackage.SQL_PROVIDES__URL:
				setUrl(URL_EDEFAULT);
				return;
			case SqlPackage.SQL_PROVIDES__USER:
				setUser(USER_EDEFAULT);
				return;
			case SqlPackage.SQL_PROVIDES__PASSWORD:
				setPassword(PASSWORD_EDEFAULT);
				return;
			case SqlPackage.SQL_PROVIDES__DRIVER:
				setDriver(DRIVER_EDEFAULT);
				return;
			case SqlPackage.SQL_PROVIDES__MAX_ACTIVE:
				setMaxActive(MAX_ACTIVE_EDEFAULT);
				return;
			case SqlPackage.SQL_PROVIDES__MAX_IDLE:
				setMaxIdle(MAX_IDLE_EDEFAULT);
				return;
			case SqlPackage.SQL_PROVIDES__MIN_IDLE:
				setMinIdle(MIN_IDLE_EDEFAULT);
				return;
			case SqlPackage.SQL_PROVIDES__MAX_WAIT:
				setMaxWait(MAX_WAIT_EDEFAULT);
				return;
			case SqlPackage.SQL_PROVIDES__TIME_BETWEEN_EVICTION_RUNS_MILLIS:
				setTimeBetweenEvictionRunsMillis(TIME_BETWEEN_EVICTION_RUNS_MILLIS_EDEFAULT);
				return;
			case SqlPackage.SQL_PROVIDES__METADATA:
				setMetadata(METADATA_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SqlPackage.SQL_PROVIDES__URL:
				return URL_EDEFAULT == null ? url != null : !URL_EDEFAULT.equals(url);
			case SqlPackage.SQL_PROVIDES__USER:
				return USER_EDEFAULT == null ? user != null : !USER_EDEFAULT.equals(user);
			case SqlPackage.SQL_PROVIDES__PASSWORD:
				return PASSWORD_EDEFAULT == null ? password != null : !PASSWORD_EDEFAULT.equals(password);
			case SqlPackage.SQL_PROVIDES__DRIVER:
				return DRIVER_EDEFAULT == null ? driver != null : !DRIVER_EDEFAULT.equals(driver);
			case SqlPackage.SQL_PROVIDES__MAX_ACTIVE:
				return maxActive != MAX_ACTIVE_EDEFAULT;
			case SqlPackage.SQL_PROVIDES__MAX_IDLE:
				return maxIdle != MAX_IDLE_EDEFAULT;
			case SqlPackage.SQL_PROVIDES__MIN_IDLE:
				return minIdle != MIN_IDLE_EDEFAULT;
			case SqlPackage.SQL_PROVIDES__MAX_WAIT:
				return maxWait != MAX_WAIT_EDEFAULT;
			case SqlPackage.SQL_PROVIDES__TIME_BETWEEN_EVICTION_RUNS_MILLIS:
				return timeBetweenEvictionRunsMillis != TIME_BETWEEN_EVICTION_RUNS_MILLIS_EDEFAULT;
			case SqlPackage.SQL_PROVIDES__METADATA:
				return metadata != METADATA_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (url: ");
		result.append(url);
		result.append(", user: ");
		result.append(user);
		result.append(", password: ");
		result.append(password);
		result.append(", driver: ");
		result.append(driver);
		result.append(", maxActive: ");
		result.append(maxActive);
		result.append(", maxIdle: ");
		result.append(maxIdle);
		result.append(", minIdle: ");
		result.append(minIdle);
		result.append(", maxWait: ");
		result.append(maxWait);
		result.append(", timeBetweenEvictionRunsMillis: ");
		result.append(timeBetweenEvictionRunsMillis);
		result.append(", metadata: ");
		result.append(metadata);
		result.append(')');
		return result.toString();
	}

} //SqlProvidesImpl
