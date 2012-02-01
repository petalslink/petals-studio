/**
 * Copyright (c) 2011-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.jms.jms.impl;

import com.ebmwebsourcing.petals.services.jms.jms.JmsExtension;
import com.ebmwebsourcing.petals.services.jms.jms.JmsPackage;

import com.sun.java.xml.ns.jbi.impl.AbstractExtensibleElementImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extension</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.jms.jms.impl.JmsExtensionImpl#getJndiProviderURL <em>Jndi Provider URL</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.jms.jms.impl.JmsExtensionImpl#getJndiInitialContextFactory <em>Jndi Initial Context Factory</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.jms.jms.impl.JmsExtensionImpl#getJndiDestinationName <em>Jndi Destination Name</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.jms.jms.impl.JmsExtensionImpl#getJndiConnectionFactory <em>Jndi Connection Factory</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.jms.jms.impl.JmsExtensionImpl#getUser <em>User</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.jms.jms.impl.JmsExtensionImpl#getPassword <em>Password</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.jms.jms.impl.JmsExtensionImpl#isTransacted <em>Transacted</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class JmsExtensionImpl extends AbstractExtensibleElementImpl implements JmsExtension {
	/**
	 * The default value of the '{@link #getJndiProviderURL() <em>Jndi Provider URL</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJndiProviderURL()
	 * @generated
	 * @ordered
	 */
	protected static final String JNDI_PROVIDER_URL_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getJndiProviderURL() <em>Jndi Provider URL</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJndiProviderURL()
	 * @generated
	 * @ordered
	 */
	protected String jndiProviderURL = JNDI_PROVIDER_URL_EDEFAULT;

	/**
	 * The default value of the '{@link #getJndiInitialContextFactory() <em>Jndi Initial Context Factory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJndiInitialContextFactory()
	 * @generated
	 * @ordered
	 */
	protected static final String JNDI_INITIAL_CONTEXT_FACTORY_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getJndiInitialContextFactory() <em>Jndi Initial Context Factory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJndiInitialContextFactory()
	 * @generated
	 * @ordered
	 */
	protected String jndiInitialContextFactory = JNDI_INITIAL_CONTEXT_FACTORY_EDEFAULT;

	/**
	 * The default value of the '{@link #getJndiDestinationName() <em>Jndi Destination Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJndiDestinationName()
	 * @generated
	 * @ordered
	 */
	protected static final String JNDI_DESTINATION_NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getJndiDestinationName() <em>Jndi Destination Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJndiDestinationName()
	 * @generated
	 * @ordered
	 */
	protected String jndiDestinationName = JNDI_DESTINATION_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getJndiConnectionFactory() <em>Jndi Connection Factory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJndiConnectionFactory()
	 * @generated
	 * @ordered
	 */
	protected static final String JNDI_CONNECTION_FACTORY_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getJndiConnectionFactory() <em>Jndi Connection Factory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJndiConnectionFactory()
	 * @generated
	 * @ordered
	 */
	protected String jndiConnectionFactory = JNDI_CONNECTION_FACTORY_EDEFAULT;

	/**
	 * The default value of the '{@link #getUser() <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUser()
	 * @generated
	 * @ordered
	 */
	protected static final String USER_EDEFAULT = "";

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
	protected static final String PASSWORD_EDEFAULT = "";

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
	 * The default value of the '{@link #isTransacted() <em>Transacted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTransacted()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TRANSACTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isTransacted() <em>Transacted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTransacted()
	 * @generated
	 * @ordered
	 */
	protected boolean transacted = TRANSACTED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JmsExtensionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JmsPackage.Literals.JMS_EXTENSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getJndiProviderURL() {
		return jndiProviderURL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJndiProviderURL(String newJndiProviderURL) {
		String oldJndiProviderURL = jndiProviderURL;
		jndiProviderURL = newJndiProviderURL;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JmsPackage.JMS_EXTENSION__JNDI_PROVIDER_URL, oldJndiProviderURL, jndiProviderURL));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getJndiInitialContextFactory() {
		return jndiInitialContextFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJndiInitialContextFactory(String newJndiInitialContextFactory) {
		String oldJndiInitialContextFactory = jndiInitialContextFactory;
		jndiInitialContextFactory = newJndiInitialContextFactory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JmsPackage.JMS_EXTENSION__JNDI_INITIAL_CONTEXT_FACTORY, oldJndiInitialContextFactory, jndiInitialContextFactory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getJndiDestinationName() {
		return jndiDestinationName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJndiDestinationName(String newJndiDestinationName) {
		String oldJndiDestinationName = jndiDestinationName;
		jndiDestinationName = newJndiDestinationName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JmsPackage.JMS_EXTENSION__JNDI_DESTINATION_NAME, oldJndiDestinationName, jndiDestinationName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getJndiConnectionFactory() {
		return jndiConnectionFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJndiConnectionFactory(String newJndiConnectionFactory) {
		String oldJndiConnectionFactory = jndiConnectionFactory;
		jndiConnectionFactory = newJndiConnectionFactory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JmsPackage.JMS_EXTENSION__JNDI_CONNECTION_FACTORY, oldJndiConnectionFactory, jndiConnectionFactory));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JmsPackage.JMS_EXTENSION__USER, oldUser, user));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JmsPackage.JMS_EXTENSION__PASSWORD, oldPassword, password));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTransacted() {
		return transacted;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransacted(boolean newTransacted) {
		boolean oldTransacted = transacted;
		transacted = newTransacted;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JmsPackage.JMS_EXTENSION__TRANSACTED, oldTransacted, transacted));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JmsPackage.JMS_EXTENSION__JNDI_PROVIDER_URL:
				return getJndiProviderURL();
			case JmsPackage.JMS_EXTENSION__JNDI_INITIAL_CONTEXT_FACTORY:
				return getJndiInitialContextFactory();
			case JmsPackage.JMS_EXTENSION__JNDI_DESTINATION_NAME:
				return getJndiDestinationName();
			case JmsPackage.JMS_EXTENSION__JNDI_CONNECTION_FACTORY:
				return getJndiConnectionFactory();
			case JmsPackage.JMS_EXTENSION__USER:
				return getUser();
			case JmsPackage.JMS_EXTENSION__PASSWORD:
				return getPassword();
			case JmsPackage.JMS_EXTENSION__TRANSACTED:
				return isTransacted();
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
			case JmsPackage.JMS_EXTENSION__JNDI_PROVIDER_URL:
				setJndiProviderURL((String)newValue);
				return;
			case JmsPackage.JMS_EXTENSION__JNDI_INITIAL_CONTEXT_FACTORY:
				setJndiInitialContextFactory((String)newValue);
				return;
			case JmsPackage.JMS_EXTENSION__JNDI_DESTINATION_NAME:
				setJndiDestinationName((String)newValue);
				return;
			case JmsPackage.JMS_EXTENSION__JNDI_CONNECTION_FACTORY:
				setJndiConnectionFactory((String)newValue);
				return;
			case JmsPackage.JMS_EXTENSION__USER:
				setUser((String)newValue);
				return;
			case JmsPackage.JMS_EXTENSION__PASSWORD:
				setPassword((String)newValue);
				return;
			case JmsPackage.JMS_EXTENSION__TRANSACTED:
				setTransacted((Boolean)newValue);
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
			case JmsPackage.JMS_EXTENSION__JNDI_PROVIDER_URL:
				setJndiProviderURL(JNDI_PROVIDER_URL_EDEFAULT);
				return;
			case JmsPackage.JMS_EXTENSION__JNDI_INITIAL_CONTEXT_FACTORY:
				setJndiInitialContextFactory(JNDI_INITIAL_CONTEXT_FACTORY_EDEFAULT);
				return;
			case JmsPackage.JMS_EXTENSION__JNDI_DESTINATION_NAME:
				setJndiDestinationName(JNDI_DESTINATION_NAME_EDEFAULT);
				return;
			case JmsPackage.JMS_EXTENSION__JNDI_CONNECTION_FACTORY:
				setJndiConnectionFactory(JNDI_CONNECTION_FACTORY_EDEFAULT);
				return;
			case JmsPackage.JMS_EXTENSION__USER:
				setUser(USER_EDEFAULT);
				return;
			case JmsPackage.JMS_EXTENSION__PASSWORD:
				setPassword(PASSWORD_EDEFAULT);
				return;
			case JmsPackage.JMS_EXTENSION__TRANSACTED:
				setTransacted(TRANSACTED_EDEFAULT);
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
			case JmsPackage.JMS_EXTENSION__JNDI_PROVIDER_URL:
				return JNDI_PROVIDER_URL_EDEFAULT == null ? jndiProviderURL != null : !JNDI_PROVIDER_URL_EDEFAULT.equals(jndiProviderURL);
			case JmsPackage.JMS_EXTENSION__JNDI_INITIAL_CONTEXT_FACTORY:
				return JNDI_INITIAL_CONTEXT_FACTORY_EDEFAULT == null ? jndiInitialContextFactory != null : !JNDI_INITIAL_CONTEXT_FACTORY_EDEFAULT.equals(jndiInitialContextFactory);
			case JmsPackage.JMS_EXTENSION__JNDI_DESTINATION_NAME:
				return JNDI_DESTINATION_NAME_EDEFAULT == null ? jndiDestinationName != null : !JNDI_DESTINATION_NAME_EDEFAULT.equals(jndiDestinationName);
			case JmsPackage.JMS_EXTENSION__JNDI_CONNECTION_FACTORY:
				return JNDI_CONNECTION_FACTORY_EDEFAULT == null ? jndiConnectionFactory != null : !JNDI_CONNECTION_FACTORY_EDEFAULT.equals(jndiConnectionFactory);
			case JmsPackage.JMS_EXTENSION__USER:
				return USER_EDEFAULT == null ? user != null : !USER_EDEFAULT.equals(user);
			case JmsPackage.JMS_EXTENSION__PASSWORD:
				return PASSWORD_EDEFAULT == null ? password != null : !PASSWORD_EDEFAULT.equals(password);
			case JmsPackage.JMS_EXTENSION__TRANSACTED:
				return transacted != TRANSACTED_EDEFAULT;
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
		result.append(" (jndiProviderURL: ");
		result.append(jndiProviderURL);
		result.append(", jndiInitialContextFactory: ");
		result.append(jndiInitialContextFactory);
		result.append(", jndiDestinationName: ");
		result.append(jndiDestinationName);
		result.append(", jndiConnectionFactory: ");
		result.append(jndiConnectionFactory);
		result.append(", user: ");
		result.append(user);
		result.append(", password: ");
		result.append(password);
		result.append(", transacted: ");
		result.append(transacted);
		result.append(')');
		return result.toString();
	}

} //JmsExtensionImpl
