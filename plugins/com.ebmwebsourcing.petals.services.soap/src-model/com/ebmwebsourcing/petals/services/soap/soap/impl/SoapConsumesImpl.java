/**
 * Copyright (c) 2011-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.soap.soap.impl;

import com.ebmwebsourcing.petals.services.soap.soap.Compatibility;
import com.ebmwebsourcing.petals.services.soap.soap.SoapConsumes;
import com.ebmwebsourcing.petals.services.soap.soap.SoapMode;
import com.ebmwebsourcing.petals.services.soap.soap.SoapPackage;

import com.sun.java.xml.ns.jbi.impl.ConsumesImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Consumes</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapConsumesImpl#getSoapServiceName <em>Soap Service Name</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapConsumesImpl#getSoapAction <em>Soap Action</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapConsumesImpl#getSynchonousTimeout <em>Synchonous Timeout</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapConsumesImpl#getMode <em>Mode</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapConsumesImpl#isEnableHttpTransport <em>Enable Http Transport</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapConsumesImpl#isEnableHttpsTransport <em>Enable Https Transport</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapConsumesImpl#isEnableJmsTransport <em>Enable Jms Transport</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapConsumesImpl#getEnableCompatibilityFor <em>Enable Compatibility For</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapConsumesImpl#isEnableWsa <em>Enable Wsa</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.soap.soap.impl.SoapConsumesImpl#getHttpServicesRedirection <em>Http Services Redirection</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SoapConsumesImpl extends ConsumesImpl implements SoapConsumes {
	/**
	 * The default value of the '{@link #getSoapServiceName() <em>Soap Service Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSoapServiceName()
	 * @generated
	 * @ordered
	 */
	protected static final String SOAP_SERVICE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSoapServiceName() <em>Soap Service Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSoapServiceName()
	 * @generated
	 * @ordered
	 */
	protected String soapServiceName = SOAP_SERVICE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getSoapAction() <em>Soap Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSoapAction()
	 * @generated
	 * @ordered
	 */
	protected static final String SOAP_ACTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSoapAction() <em>Soap Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSoapAction()
	 * @generated
	 * @ordered
	 */
	protected String soapAction = SOAP_ACTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getSynchonousTimeout() <em>Synchonous Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSynchonousTimeout()
	 * @generated
	 * @ordered
	 */
	protected static final int SYNCHONOUS_TIMEOUT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSynchonousTimeout() <em>Synchonous Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSynchonousTimeout()
	 * @generated
	 * @ordered
	 */
	protected int synchonousTimeout = SYNCHONOUS_TIMEOUT_EDEFAULT;

	/**
	 * The default value of the '{@link #getMode() <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMode()
	 * @generated
	 * @ordered
	 */
	protected static final SoapMode MODE_EDEFAULT = SoapMode.SOAP;

	/**
	 * The cached value of the '{@link #getMode() <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMode()
	 * @generated
	 * @ordered
	 */
	protected SoapMode mode = MODE_EDEFAULT;

	/**
	 * The default value of the '{@link #isEnableHttpTransport() <em>Enable Http Transport</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnableHttpTransport()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLE_HTTP_TRANSPORT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEnableHttpTransport() <em>Enable Http Transport</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnableHttpTransport()
	 * @generated
	 * @ordered
	 */
	protected boolean enableHttpTransport = ENABLE_HTTP_TRANSPORT_EDEFAULT;

	/**
	 * This is true if the Enable Http Transport attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean enableHttpTransportESet;

	/**
	 * The default value of the '{@link #isEnableHttpsTransport() <em>Enable Https Transport</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnableHttpsTransport()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLE_HTTPS_TRANSPORT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEnableHttpsTransport() <em>Enable Https Transport</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnableHttpsTransport()
	 * @generated
	 * @ordered
	 */
	protected boolean enableHttpsTransport = ENABLE_HTTPS_TRANSPORT_EDEFAULT;

	/**
	 * This is true if the Enable Https Transport attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean enableHttpsTransportESet;

	/**
	 * The default value of the '{@link #isEnableJmsTransport() <em>Enable Jms Transport</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnableJmsTransport()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLE_JMS_TRANSPORT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEnableJmsTransport() <em>Enable Jms Transport</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnableJmsTransport()
	 * @generated
	 * @ordered
	 */
	protected boolean enableJmsTransport = ENABLE_JMS_TRANSPORT_EDEFAULT;

	/**
	 * This is true if the Enable Jms Transport attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean enableJmsTransportESet;

	/**
	 * The default value of the '{@link #getEnableCompatibilityFor() <em>Enable Compatibility For</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnableCompatibilityFor()
	 * @generated
	 * @ordered
	 */
	protected static final Compatibility ENABLE_COMPATIBILITY_FOR_EDEFAULT = Compatibility.AXIS1;

	/**
	 * The cached value of the '{@link #getEnableCompatibilityFor() <em>Enable Compatibility For</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnableCompatibilityFor()
	 * @generated
	 * @ordered
	 */
	protected Compatibility enableCompatibilityFor = ENABLE_COMPATIBILITY_FOR_EDEFAULT;

	/**
	 * The default value of the '{@link #isEnableWsa() <em>Enable Wsa</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnableWsa()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLE_WSA_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEnableWsa() <em>Enable Wsa</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnableWsa()
	 * @generated
	 * @ordered
	 */
	protected boolean enableWsa = ENABLE_WSA_EDEFAULT;

	/**
	 * The default value of the '{@link #getHttpServicesRedirection() <em>Http Services Redirection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpServicesRedirection()
	 * @generated
	 * @ordered
	 */
	protected static final String HTTP_SERVICES_REDIRECTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHttpServicesRedirection() <em>Http Services Redirection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpServicesRedirection()
	 * @generated
	 * @ordered
	 */
	protected String httpServicesRedirection = HTTP_SERVICES_REDIRECTION_EDEFAULT;

	/**
	 * This is true if the Http Services Redirection attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean httpServicesRedirectionESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SoapConsumesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SoapPackage.Literals.SOAP_CONSUMES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSoapServiceName() {
		return soapServiceName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSoapServiceName(String newSoapServiceName) {
		String oldSoapServiceName = soapServiceName;
		soapServiceName = newSoapServiceName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_CONSUMES__SOAP_SERVICE_NAME, oldSoapServiceName, soapServiceName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSoapAction() {
		return soapAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSoapAction(String newSoapAction) {
		String oldSoapAction = soapAction;
		soapAction = newSoapAction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_CONSUMES__SOAP_ACTION, oldSoapAction, soapAction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSynchonousTimeout() {
		return synchonousTimeout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSynchonousTimeout(int newSynchonousTimeout) {
		int oldSynchonousTimeout = synchonousTimeout;
		synchonousTimeout = newSynchonousTimeout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_CONSUMES__SYNCHONOUS_TIMEOUT, oldSynchonousTimeout, synchonousTimeout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SoapMode getMode() {
		return mode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMode(SoapMode newMode) {
		SoapMode oldMode = mode;
		mode = newMode == null ? MODE_EDEFAULT : newMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_CONSUMES__MODE, oldMode, mode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEnableHttpTransport() {
		return enableHttpTransport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnableHttpTransport(boolean newEnableHttpTransport) {
		boolean oldEnableHttpTransport = enableHttpTransport;
		enableHttpTransport = newEnableHttpTransport;
		boolean oldEnableHttpTransportESet = enableHttpTransportESet;
		enableHttpTransportESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_CONSUMES__ENABLE_HTTP_TRANSPORT, oldEnableHttpTransport, enableHttpTransport, !oldEnableHttpTransportESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetEnableHttpTransport() {
		boolean oldEnableHttpTransport = enableHttpTransport;
		boolean oldEnableHttpTransportESet = enableHttpTransportESet;
		enableHttpTransport = ENABLE_HTTP_TRANSPORT_EDEFAULT;
		enableHttpTransportESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, SoapPackage.SOAP_CONSUMES__ENABLE_HTTP_TRANSPORT, oldEnableHttpTransport, ENABLE_HTTP_TRANSPORT_EDEFAULT, oldEnableHttpTransportESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetEnableHttpTransport() {
		return enableHttpTransportESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEnableHttpsTransport() {
		return enableHttpsTransport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnableHttpsTransport(boolean newEnableHttpsTransport) {
		boolean oldEnableHttpsTransport = enableHttpsTransport;
		enableHttpsTransport = newEnableHttpsTransport;
		boolean oldEnableHttpsTransportESet = enableHttpsTransportESet;
		enableHttpsTransportESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_CONSUMES__ENABLE_HTTPS_TRANSPORT, oldEnableHttpsTransport, enableHttpsTransport, !oldEnableHttpsTransportESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetEnableHttpsTransport() {
		boolean oldEnableHttpsTransport = enableHttpsTransport;
		boolean oldEnableHttpsTransportESet = enableHttpsTransportESet;
		enableHttpsTransport = ENABLE_HTTPS_TRANSPORT_EDEFAULT;
		enableHttpsTransportESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, SoapPackage.SOAP_CONSUMES__ENABLE_HTTPS_TRANSPORT, oldEnableHttpsTransport, ENABLE_HTTPS_TRANSPORT_EDEFAULT, oldEnableHttpsTransportESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetEnableHttpsTransport() {
		return enableHttpsTransportESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEnableJmsTransport() {
		return enableJmsTransport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnableJmsTransport(boolean newEnableJmsTransport) {
		boolean oldEnableJmsTransport = enableJmsTransport;
		enableJmsTransport = newEnableJmsTransport;
		boolean oldEnableJmsTransportESet = enableJmsTransportESet;
		enableJmsTransportESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_CONSUMES__ENABLE_JMS_TRANSPORT, oldEnableJmsTransport, enableJmsTransport, !oldEnableJmsTransportESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetEnableJmsTransport() {
		boolean oldEnableJmsTransport = enableJmsTransport;
		boolean oldEnableJmsTransportESet = enableJmsTransportESet;
		enableJmsTransport = ENABLE_JMS_TRANSPORT_EDEFAULT;
		enableJmsTransportESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, SoapPackage.SOAP_CONSUMES__ENABLE_JMS_TRANSPORT, oldEnableJmsTransport, ENABLE_JMS_TRANSPORT_EDEFAULT, oldEnableJmsTransportESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetEnableJmsTransport() {
		return enableJmsTransportESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Compatibility getEnableCompatibilityFor() {
		return enableCompatibilityFor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnableCompatibilityFor(Compatibility newEnableCompatibilityFor) {
		Compatibility oldEnableCompatibilityFor = enableCompatibilityFor;
		enableCompatibilityFor = newEnableCompatibilityFor == null ? ENABLE_COMPATIBILITY_FOR_EDEFAULT : newEnableCompatibilityFor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_CONSUMES__ENABLE_COMPATIBILITY_FOR, oldEnableCompatibilityFor, enableCompatibilityFor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEnableWsa() {
		return enableWsa;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnableWsa(boolean newEnableWsa) {
		boolean oldEnableWsa = enableWsa;
		enableWsa = newEnableWsa;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_CONSUMES__ENABLE_WSA, oldEnableWsa, enableWsa));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHttpServicesRedirection() {
		return httpServicesRedirection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHttpServicesRedirection(String newHttpServicesRedirection) {
		String oldHttpServicesRedirection = httpServicesRedirection;
		httpServicesRedirection = newHttpServicesRedirection;
		boolean oldHttpServicesRedirectionESet = httpServicesRedirectionESet;
		httpServicesRedirectionESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoapPackage.SOAP_CONSUMES__HTTP_SERVICES_REDIRECTION, oldHttpServicesRedirection, httpServicesRedirection, !oldHttpServicesRedirectionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetHttpServicesRedirection() {
		String oldHttpServicesRedirection = httpServicesRedirection;
		boolean oldHttpServicesRedirectionESet = httpServicesRedirectionESet;
		httpServicesRedirection = HTTP_SERVICES_REDIRECTION_EDEFAULT;
		httpServicesRedirectionESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, SoapPackage.SOAP_CONSUMES__HTTP_SERVICES_REDIRECTION, oldHttpServicesRedirection, HTTP_SERVICES_REDIRECTION_EDEFAULT, oldHttpServicesRedirectionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetHttpServicesRedirection() {
		return httpServicesRedirectionESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SoapPackage.SOAP_CONSUMES__SOAP_SERVICE_NAME:
				return getSoapServiceName();
			case SoapPackage.SOAP_CONSUMES__SOAP_ACTION:
				return getSoapAction();
			case SoapPackage.SOAP_CONSUMES__SYNCHONOUS_TIMEOUT:
				return getSynchonousTimeout();
			case SoapPackage.SOAP_CONSUMES__MODE:
				return getMode();
			case SoapPackage.SOAP_CONSUMES__ENABLE_HTTP_TRANSPORT:
				return isEnableHttpTransport();
			case SoapPackage.SOAP_CONSUMES__ENABLE_HTTPS_TRANSPORT:
				return isEnableHttpsTransport();
			case SoapPackage.SOAP_CONSUMES__ENABLE_JMS_TRANSPORT:
				return isEnableJmsTransport();
			case SoapPackage.SOAP_CONSUMES__ENABLE_COMPATIBILITY_FOR:
				return getEnableCompatibilityFor();
			case SoapPackage.SOAP_CONSUMES__ENABLE_WSA:
				return isEnableWsa();
			case SoapPackage.SOAP_CONSUMES__HTTP_SERVICES_REDIRECTION:
				return getHttpServicesRedirection();
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
			case SoapPackage.SOAP_CONSUMES__SOAP_SERVICE_NAME:
				setSoapServiceName((String)newValue);
				return;
			case SoapPackage.SOAP_CONSUMES__SOAP_ACTION:
				setSoapAction((String)newValue);
				return;
			case SoapPackage.SOAP_CONSUMES__SYNCHONOUS_TIMEOUT:
				setSynchonousTimeout((Integer)newValue);
				return;
			case SoapPackage.SOAP_CONSUMES__MODE:
				setMode((SoapMode)newValue);
				return;
			case SoapPackage.SOAP_CONSUMES__ENABLE_HTTP_TRANSPORT:
				setEnableHttpTransport((Boolean)newValue);
				return;
			case SoapPackage.SOAP_CONSUMES__ENABLE_HTTPS_TRANSPORT:
				setEnableHttpsTransport((Boolean)newValue);
				return;
			case SoapPackage.SOAP_CONSUMES__ENABLE_JMS_TRANSPORT:
				setEnableJmsTransport((Boolean)newValue);
				return;
			case SoapPackage.SOAP_CONSUMES__ENABLE_COMPATIBILITY_FOR:
				setEnableCompatibilityFor((Compatibility)newValue);
				return;
			case SoapPackage.SOAP_CONSUMES__ENABLE_WSA:
				setEnableWsa((Boolean)newValue);
				return;
			case SoapPackage.SOAP_CONSUMES__HTTP_SERVICES_REDIRECTION:
				setHttpServicesRedirection((String)newValue);
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
			case SoapPackage.SOAP_CONSUMES__SOAP_SERVICE_NAME:
				setSoapServiceName(SOAP_SERVICE_NAME_EDEFAULT);
				return;
			case SoapPackage.SOAP_CONSUMES__SOAP_ACTION:
				setSoapAction(SOAP_ACTION_EDEFAULT);
				return;
			case SoapPackage.SOAP_CONSUMES__SYNCHONOUS_TIMEOUT:
				setSynchonousTimeout(SYNCHONOUS_TIMEOUT_EDEFAULT);
				return;
			case SoapPackage.SOAP_CONSUMES__MODE:
				setMode(MODE_EDEFAULT);
				return;
			case SoapPackage.SOAP_CONSUMES__ENABLE_HTTP_TRANSPORT:
				unsetEnableHttpTransport();
				return;
			case SoapPackage.SOAP_CONSUMES__ENABLE_HTTPS_TRANSPORT:
				unsetEnableHttpsTransport();
				return;
			case SoapPackage.SOAP_CONSUMES__ENABLE_JMS_TRANSPORT:
				unsetEnableJmsTransport();
				return;
			case SoapPackage.SOAP_CONSUMES__ENABLE_COMPATIBILITY_FOR:
				setEnableCompatibilityFor(ENABLE_COMPATIBILITY_FOR_EDEFAULT);
				return;
			case SoapPackage.SOAP_CONSUMES__ENABLE_WSA:
				setEnableWsa(ENABLE_WSA_EDEFAULT);
				return;
			case SoapPackage.SOAP_CONSUMES__HTTP_SERVICES_REDIRECTION:
				unsetHttpServicesRedirection();
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
			case SoapPackage.SOAP_CONSUMES__SOAP_SERVICE_NAME:
				return SOAP_SERVICE_NAME_EDEFAULT == null ? soapServiceName != null : !SOAP_SERVICE_NAME_EDEFAULT.equals(soapServiceName);
			case SoapPackage.SOAP_CONSUMES__SOAP_ACTION:
				return SOAP_ACTION_EDEFAULT == null ? soapAction != null : !SOAP_ACTION_EDEFAULT.equals(soapAction);
			case SoapPackage.SOAP_CONSUMES__SYNCHONOUS_TIMEOUT:
				return synchonousTimeout != SYNCHONOUS_TIMEOUT_EDEFAULT;
			case SoapPackage.SOAP_CONSUMES__MODE:
				return mode != MODE_EDEFAULT;
			case SoapPackage.SOAP_CONSUMES__ENABLE_HTTP_TRANSPORT:
				return isSetEnableHttpTransport();
			case SoapPackage.SOAP_CONSUMES__ENABLE_HTTPS_TRANSPORT:
				return isSetEnableHttpsTransport();
			case SoapPackage.SOAP_CONSUMES__ENABLE_JMS_TRANSPORT:
				return isSetEnableJmsTransport();
			case SoapPackage.SOAP_CONSUMES__ENABLE_COMPATIBILITY_FOR:
				return enableCompatibilityFor != ENABLE_COMPATIBILITY_FOR_EDEFAULT;
			case SoapPackage.SOAP_CONSUMES__ENABLE_WSA:
				return enableWsa != ENABLE_WSA_EDEFAULT;
			case SoapPackage.SOAP_CONSUMES__HTTP_SERVICES_REDIRECTION:
				return isSetHttpServicesRedirection();
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
		result.append(" (soapServiceName: ");
		result.append(soapServiceName);
		result.append(", soapAction: ");
		result.append(soapAction);
		result.append(", synchonousTimeout: ");
		result.append(synchonousTimeout);
		result.append(", mode: ");
		result.append(mode);
		result.append(", enableHttpTransport: ");
		if (enableHttpTransportESet) result.append(enableHttpTransport); else result.append("<unset>");
		result.append(", enableHttpsTransport: ");
		if (enableHttpsTransportESet) result.append(enableHttpsTransport); else result.append("<unset>");
		result.append(", enableJmsTransport: ");
		if (enableJmsTransportESet) result.append(enableJmsTransport); else result.append("<unset>");
		result.append(", enableCompatibilityFor: ");
		result.append(enableCompatibilityFor);
		result.append(", enableWsa: ");
		result.append(enableWsa);
		result.append(", httpServicesRedirection: ");
		if (httpServicesRedirectionESet) result.append(httpServicesRedirection); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //SoapConsumesImpl
