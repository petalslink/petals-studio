/**
 * Copyright (c) 2011-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 */
package com.ebmwebsourcing.petals.services.jms.jms.impl;

import com.ebmwebsourcing.petals.services.jms.jms.JmsPackage;
import com.ebmwebsourcing.petals.services.jms.jms.JmsProvides;

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
 *   <li>{@link com.ebmwebsourcing.petals.services.jms.jms.impl.JmsProvidesImpl#getMaxActive <em>Max Active</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.jms.jms.impl.JmsProvidesImpl#getMaxWait <em>Max Wait</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.jms.jms.impl.JmsProvidesImpl#getMaxIdle <em>Max Idle</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.jms.jms.impl.JmsProvidesImpl#getTimeBetweenEvictionRunsMilles <em>Time Between Eviction Runs Milles</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.jms.jms.impl.JmsProvidesImpl#getMinEvictableIdleTimeMillis <em>Min Evictable Idle Time Millis</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.jms.jms.impl.JmsProvidesImpl#isTestWhileIdle <em>Test While Idle</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JmsProvidesImpl extends JmsExtensionImpl implements JmsProvides {
	/**
	 * The default value of the '{@link #getMaxActive() <em>Max Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxActive()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_ACTIVE_EDEFAULT = 0; // TODO The default value literal "" is not valid.

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
	 * The default value of the '{@link #getMaxWait() <em>Max Wait</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxWait()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_WAIT_EDEFAULT = 0; // TODO The default value literal "" is not valid.

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
	 * The default value of the '{@link #getMaxIdle() <em>Max Idle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxIdle()
	 * @generated
	 * @ordered
	 */
	protected static final int MAX_IDLE_EDEFAULT = 0; // TODO The default value literal "" is not valid.

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
	 * The default value of the '{@link #getTimeBetweenEvictionRunsMilles() <em>Time Between Eviction Runs Milles</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeBetweenEvictionRunsMilles()
	 * @generated
	 * @ordered
	 */
	protected static final int TIME_BETWEEN_EVICTION_RUNS_MILLES_EDEFAULT = 0; // TODO The default value literal "" is not valid.

	/**
	 * The cached value of the '{@link #getTimeBetweenEvictionRunsMilles() <em>Time Between Eviction Runs Milles</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeBetweenEvictionRunsMilles()
	 * @generated
	 * @ordered
	 */
	protected int timeBetweenEvictionRunsMilles = TIME_BETWEEN_EVICTION_RUNS_MILLES_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinEvictableIdleTimeMillis() <em>Min Evictable Idle Time Millis</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinEvictableIdleTimeMillis()
	 * @generated
	 * @ordered
	 */
	protected static final int MIN_EVICTABLE_IDLE_TIME_MILLIS_EDEFAULT = 0; // TODO The default value literal "" is not valid.

	/**
	 * The cached value of the '{@link #getMinEvictableIdleTimeMillis() <em>Min Evictable Idle Time Millis</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinEvictableIdleTimeMillis()
	 * @generated
	 * @ordered
	 */
	protected int minEvictableIdleTimeMillis = MIN_EVICTABLE_IDLE_TIME_MILLIS_EDEFAULT;

	/**
	 * The default value of the '{@link #isTestWhileIdle() <em>Test While Idle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTestWhileIdle()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TEST_WHILE_IDLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isTestWhileIdle() <em>Test While Idle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTestWhileIdle()
	 * @generated
	 * @ordered
	 */
	protected boolean testWhileIdle = TEST_WHILE_IDLE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JmsProvidesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JmsPackage.Literals.JMS_PROVIDES;
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
			eNotify(new ENotificationImpl(this, Notification.SET, JmsPackage.JMS_PROVIDES__MAX_ACTIVE, oldMaxActive, maxActive));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JmsPackage.JMS_PROVIDES__MAX_WAIT, oldMaxWait, maxWait));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JmsPackage.JMS_PROVIDES__MAX_IDLE, oldMaxIdle, maxIdle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTimeBetweenEvictionRunsMilles() {
		return timeBetweenEvictionRunsMilles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeBetweenEvictionRunsMilles(int newTimeBetweenEvictionRunsMilles) {
		int oldTimeBetweenEvictionRunsMilles = timeBetweenEvictionRunsMilles;
		timeBetweenEvictionRunsMilles = newTimeBetweenEvictionRunsMilles;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JmsPackage.JMS_PROVIDES__TIME_BETWEEN_EVICTION_RUNS_MILLES, oldTimeBetweenEvictionRunsMilles, timeBetweenEvictionRunsMilles));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinEvictableIdleTimeMillis(int newMinEvictableIdleTimeMillis) {
		int oldMinEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
		minEvictableIdleTimeMillis = newMinEvictableIdleTimeMillis;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JmsPackage.JMS_PROVIDES__MIN_EVICTABLE_IDLE_TIME_MILLIS, oldMinEvictableIdleTimeMillis, minEvictableIdleTimeMillis));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTestWhileIdle() {
		return testWhileIdle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTestWhileIdle(boolean newTestWhileIdle) {
		boolean oldTestWhileIdle = testWhileIdle;
		testWhileIdle = newTestWhileIdle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JmsPackage.JMS_PROVIDES__TEST_WHILE_IDLE, oldTestWhileIdle, testWhileIdle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JmsPackage.JMS_PROVIDES__MAX_ACTIVE:
				return getMaxActive();
			case JmsPackage.JMS_PROVIDES__MAX_WAIT:
				return getMaxWait();
			case JmsPackage.JMS_PROVIDES__MAX_IDLE:
				return getMaxIdle();
			case JmsPackage.JMS_PROVIDES__TIME_BETWEEN_EVICTION_RUNS_MILLES:
				return getTimeBetweenEvictionRunsMilles();
			case JmsPackage.JMS_PROVIDES__MIN_EVICTABLE_IDLE_TIME_MILLIS:
				return getMinEvictableIdleTimeMillis();
			case JmsPackage.JMS_PROVIDES__TEST_WHILE_IDLE:
				return isTestWhileIdle();
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
			case JmsPackage.JMS_PROVIDES__MAX_ACTIVE:
				setMaxActive((Integer)newValue);
				return;
			case JmsPackage.JMS_PROVIDES__MAX_WAIT:
				setMaxWait((Integer)newValue);
				return;
			case JmsPackage.JMS_PROVIDES__MAX_IDLE:
				setMaxIdle((Integer)newValue);
				return;
			case JmsPackage.JMS_PROVIDES__TIME_BETWEEN_EVICTION_RUNS_MILLES:
				setTimeBetweenEvictionRunsMilles((Integer)newValue);
				return;
			case JmsPackage.JMS_PROVIDES__MIN_EVICTABLE_IDLE_TIME_MILLIS:
				setMinEvictableIdleTimeMillis((Integer)newValue);
				return;
			case JmsPackage.JMS_PROVIDES__TEST_WHILE_IDLE:
				setTestWhileIdle((Boolean)newValue);
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
			case JmsPackage.JMS_PROVIDES__MAX_ACTIVE:
				setMaxActive(MAX_ACTIVE_EDEFAULT);
				return;
			case JmsPackage.JMS_PROVIDES__MAX_WAIT:
				setMaxWait(MAX_WAIT_EDEFAULT);
				return;
			case JmsPackage.JMS_PROVIDES__MAX_IDLE:
				setMaxIdle(MAX_IDLE_EDEFAULT);
				return;
			case JmsPackage.JMS_PROVIDES__TIME_BETWEEN_EVICTION_RUNS_MILLES:
				setTimeBetweenEvictionRunsMilles(TIME_BETWEEN_EVICTION_RUNS_MILLES_EDEFAULT);
				return;
			case JmsPackage.JMS_PROVIDES__MIN_EVICTABLE_IDLE_TIME_MILLIS:
				setMinEvictableIdleTimeMillis(MIN_EVICTABLE_IDLE_TIME_MILLIS_EDEFAULT);
				return;
			case JmsPackage.JMS_PROVIDES__TEST_WHILE_IDLE:
				setTestWhileIdle(TEST_WHILE_IDLE_EDEFAULT);
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
			case JmsPackage.JMS_PROVIDES__MAX_ACTIVE:
				return maxActive != MAX_ACTIVE_EDEFAULT;
			case JmsPackage.JMS_PROVIDES__MAX_WAIT:
				return maxWait != MAX_WAIT_EDEFAULT;
			case JmsPackage.JMS_PROVIDES__MAX_IDLE:
				return maxIdle != MAX_IDLE_EDEFAULT;
			case JmsPackage.JMS_PROVIDES__TIME_BETWEEN_EVICTION_RUNS_MILLES:
				return timeBetweenEvictionRunsMilles != TIME_BETWEEN_EVICTION_RUNS_MILLES_EDEFAULT;
			case JmsPackage.JMS_PROVIDES__MIN_EVICTABLE_IDLE_TIME_MILLIS:
				return minEvictableIdleTimeMillis != MIN_EVICTABLE_IDLE_TIME_MILLIS_EDEFAULT;
			case JmsPackage.JMS_PROVIDES__TEST_WHILE_IDLE:
				return testWhileIdle != TEST_WHILE_IDLE_EDEFAULT;
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
		result.append(" (maxActive: ");
		result.append(maxActive);
		result.append(", maxWait: ");
		result.append(maxWait);
		result.append(", maxIdle: ");
		result.append(maxIdle);
		result.append(", timeBetweenEvictionRunsMilles: ");
		result.append(timeBetweenEvictionRunsMilles);
		result.append(", minEvictableIdleTimeMillis: ");
		result.append(minEvictableIdleTimeMillis);
		result.append(", testWhileIdle: ");
		result.append(testWhileIdle);
		result.append(')');
		return result.toString();
	}

} //JmsProvidesImpl
