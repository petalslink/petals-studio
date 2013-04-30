/**
 * Copyright (c) 2011-2013, Linagora
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
	 * This is true if the Jndi Provider URL attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean jndiProviderURLESet;

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
	 * This is true if the Jndi Initial Context Factory attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean jndiInitialContextFactoryESet;

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
	 * This is true if the Jndi Destination Name attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean jndiDestinationNameESet;

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
	 * This is true if the Jndi Connection Factory attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean jndiConnectionFactoryESet;

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
		boolean oldJndiProviderURLESet = jndiProviderURLESet;
		jndiProviderURLESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JmsPackage.JMS_EXTENSION__JNDI_PROVIDER_URL, oldJndiProviderURL, jndiProviderURL, !oldJndiProviderURLESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetJndiProviderURL() {
		String oldJndiProviderURL = jndiProviderURL;
		boolean oldJndiProviderURLESet = jndiProviderURLESet;
		jndiProviderURL = JNDI_PROVIDER_URL_EDEFAULT;
		jndiProviderURLESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JmsPackage.JMS_EXTENSION__JNDI_PROVIDER_URL, oldJndiProviderURL, JNDI_PROVIDER_URL_EDEFAULT, oldJndiProviderURLESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetJndiProviderURL() {
		return jndiProviderURLESet;
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
		boolean oldJndiInitialContextFactoryESet = jndiInitialContextFactoryESet;
		jndiInitialContextFactoryESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JmsPackage.JMS_EXTENSION__JNDI_INITIAL_CONTEXT_FACTORY, oldJndiInitialContextFactory, jndiInitialContextFactory, !oldJndiInitialContextFactoryESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetJndiInitialContextFactory() {
		String oldJndiInitialContextFactory = jndiInitialContextFactory;
		boolean oldJndiInitialContextFactoryESet = jndiInitialContextFactoryESet;
		jndiInitialContextFactory = JNDI_INITIAL_CONTEXT_FACTORY_EDEFAULT;
		jndiInitialContextFactoryESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JmsPackage.JMS_EXTENSION__JNDI_INITIAL_CONTEXT_FACTORY, oldJndiInitialContextFactory, JNDI_INITIAL_CONTEXT_FACTORY_EDEFAULT, oldJndiInitialContextFactoryESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetJndiInitialContextFactory() {
		return jndiInitialContextFactoryESet;
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
		boolean oldJndiDestinationNameESet = jndiDestinationNameESet;
		jndiDestinationNameESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JmsPackage.JMS_EXTENSION__JNDI_DESTINATION_NAME, oldJndiDestinationName, jndiDestinationName, !oldJndiDestinationNameESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetJndiDestinationName() {
		String oldJndiDestinationName = jndiDestinationName;
		boolean oldJndiDestinationNameESet = jndiDestinationNameESet;
		jndiDestinationName = JNDI_DESTINATION_NAME_EDEFAULT;
		jndiDestinationNameESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JmsPackage.JMS_EXTENSION__JNDI_DESTINATION_NAME, oldJndiDestinationName, JNDI_DESTINATION_NAME_EDEFAULT, oldJndiDestinationNameESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetJndiDestinationName() {
		return jndiDestinationNameESet;
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
		boolean oldJndiConnectionFactoryESet = jndiConnectionFactoryESet;
		jndiConnectionFactoryESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JmsPackage.JMS_EXTENSION__JNDI_CONNECTION_FACTORY, oldJndiConnectionFactory, jndiConnectionFactory, !oldJndiConnectionFactoryESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetJndiConnectionFactory() {
		String oldJndiConnectionFactory = jndiConnectionFactory;
		boolean oldJndiConnectionFactoryESet = jndiConnectionFactoryESet;
		jndiConnectionFactory = JNDI_CONNECTION_FACTORY_EDEFAULT;
		jndiConnectionFactoryESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JmsPackage.JMS_EXTENSION__JNDI_CONNECTION_FACTORY, oldJndiConnectionFactory, JNDI_CONNECTION_FACTORY_EDEFAULT, oldJndiConnectionFactoryESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetJndiConnectionFactory() {
		return jndiConnectionFactoryESet;
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
				unsetJndiProviderURL();
				return;
			case JmsPackage.JMS_EXTENSION__JNDI_INITIAL_CONTEXT_FACTORY:
				unsetJndiInitialContextFactory();
				return;
			case JmsPackage.JMS_EXTENSION__JNDI_DESTINATION_NAME:
				unsetJndiDestinationName();
				return;
			case JmsPackage.JMS_EXTENSION__JNDI_CONNECTION_FACTORY:
				unsetJndiConnectionFactory();
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
				return isSetJndiProviderURL();
			case JmsPackage.JMS_EXTENSION__JNDI_INITIAL_CONTEXT_FACTORY:
				return isSetJndiInitialContextFactory();
			case JmsPackage.JMS_EXTENSION__JNDI_DESTINATION_NAME:
				return isSetJndiDestinationName();
			case JmsPackage.JMS_EXTENSION__JNDI_CONNECTION_FACTORY:
				return isSetJndiConnectionFactory();
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
		if (jndiProviderURLESet) result.append(jndiProviderURL); else result.append("<unset>");
		result.append(", jndiInitialContextFactory: ");
		if (jndiInitialContextFactoryESet) result.append(jndiInitialContextFactory); else result.append("<unset>");
		result.append(", jndiDestinationName: ");
		if (jndiDestinationNameESet) result.append(jndiDestinationName); else result.append("<unset>");
		result.append(", jndiConnectionFactory: ");
		if (jndiConnectionFactoryESet) result.append(jndiConnectionFactory); else result.append("<unset>");
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
