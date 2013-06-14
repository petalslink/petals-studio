/**
 * Copyright (c) 2012-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 */
package com.ebmwebsourcing.petals.services.eip.eip.impl;

import com.ebmwebsourcing.petals.services.eip.eip.EipPackage;
import com.ebmwebsourcing.petals.services.eip.eip.EipProvides;
import com.ebmwebsourcing.petals.services.eip.eip.EipType;
import com.ebmwebsourcing.petals.services.eip.eip.WiretapWay;

import com.sun.java.xml.ns.jbi.impl.ProvidesImpl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Provides</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.eip.eip.impl.EipProvidesImpl#getEip <em>Eip</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.eip.eip.impl.EipProvidesImpl#getTest <em>Test</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.eip.eip.impl.EipProvidesImpl#getTestOperation <em>Test Operation</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.eip.eip.impl.EipProvidesImpl#getWiretapWay <em>Wiretap Way</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.eip.eip.impl.EipProvidesImpl#getAggregatorCorrelation <em>Aggregator Correlation</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.eip.eip.impl.EipProvidesImpl#isFaultRobust <em>Fault Robust</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.eip.eip.impl.EipProvidesImpl#isExceptionRobust <em>Exception Robust</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.eip.eip.impl.EipProvidesImpl#isFaultToException <em>Fault To Exception</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.eip.eip.impl.EipProvidesImpl#isAttachmentMode <em>Attachment Mode</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EipProvidesImpl extends ProvidesImpl implements EipProvides {
	/**
	 * The default value of the '{@link #getEip() <em>Eip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEip()
	 * @generated
	 * @ordered
	 */
	protected static final EipType EIP_EDEFAULT = EipType.ROUTING_SLIP;

	/**
	 * The cached value of the '{@link #getEip() <em>Eip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEip()
	 * @generated
	 * @ordered
	 */
	protected EipType eip = EIP_EDEFAULT;

	/**
	 * This is true if the Eip attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean eipESet;

	/**
	 * The cached value of the '{@link #getTest() <em>Test</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTest()
	 * @generated
	 * @ordered
	 */
	protected EList<String> test;

	/**
	 * The cached value of the '{@link #getTestOperation() <em>Test Operation</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestOperation()
	 * @generated
	 * @ordered
	 */
	protected EList<String> testOperation;

	/**
	 * The default value of the '{@link #getWiretapWay() <em>Wiretap Way</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWiretapWay()
	 * @generated
	 * @ordered
	 */
	protected static final WiretapWay WIRETAP_WAY_EDEFAULT = WiretapWay.REQUEST_ON_RESPONSE;

	/**
	 * The cached value of the '{@link #getWiretapWay() <em>Wiretap Way</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWiretapWay()
	 * @generated
	 * @ordered
	 */
	protected WiretapWay wiretapWay = WIRETAP_WAY_EDEFAULT;

	/**
	 * The default value of the '{@link #getAggregatorCorrelation() <em>Aggregator Correlation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAggregatorCorrelation()
	 * @generated
	 * @ordered
	 */
	protected static final String AGGREGATOR_CORRELATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAggregatorCorrelation() <em>Aggregator Correlation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAggregatorCorrelation()
	 * @generated
	 * @ordered
	 */
	protected String aggregatorCorrelation = AGGREGATOR_CORRELATION_EDEFAULT;

	/**
	 * The default value of the '{@link #isFaultRobust() <em>Fault Robust</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFaultRobust()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FAULT_ROBUST_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFaultRobust() <em>Fault Robust</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFaultRobust()
	 * @generated
	 * @ordered
	 */
	protected boolean faultRobust = FAULT_ROBUST_EDEFAULT;

	/**
	 * The default value of the '{@link #isExceptionRobust() <em>Exception Robust</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExceptionRobust()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EXCEPTION_ROBUST_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isExceptionRobust() <em>Exception Robust</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExceptionRobust()
	 * @generated
	 * @ordered
	 */
	protected boolean exceptionRobust = EXCEPTION_ROBUST_EDEFAULT;

	/**
	 * The default value of the '{@link #isFaultToException() <em>Fault To Exception</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFaultToException()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FAULT_TO_EXCEPTION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFaultToException() <em>Fault To Exception</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFaultToException()
	 * @generated
	 * @ordered
	 */
	protected boolean faultToException = FAULT_TO_EXCEPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #isAttachmentMode() <em>Attachment Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAttachmentMode()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ATTACHMENT_MODE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAttachmentMode() <em>Attachment Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAttachmentMode()
	 * @generated
	 * @ordered
	 */
	protected boolean attachmentMode = ATTACHMENT_MODE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EipProvidesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EipPackage.Literals.EIP_PROVIDES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EipType getEip() {
		return eip;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEip(EipType newEip) {
		EipType oldEip = eip;
		eip = newEip == null ? EIP_EDEFAULT : newEip;
		boolean oldEipESet = eipESet;
		eipESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EipPackage.EIP_PROVIDES__EIP, oldEip, eip, !oldEipESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetEip() {
		EipType oldEip = eip;
		boolean oldEipESet = eipESet;
		eip = EIP_EDEFAULT;
		eipESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EipPackage.EIP_PROVIDES__EIP, oldEip, EIP_EDEFAULT, oldEipESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetEip() {
		return eipESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getTest() {
		if (test == null) {
			test = new EDataTypeUniqueEList<String>(String.class, this, EipPackage.EIP_PROVIDES__TEST);
		}
		return test;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getTestOperation() {
		if (testOperation == null) {
			testOperation = new EDataTypeUniqueEList<String>(String.class, this, EipPackage.EIP_PROVIDES__TEST_OPERATION);
		}
		return testOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WiretapWay getWiretapWay() {
		return wiretapWay;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWiretapWay(WiretapWay newWiretapWay) {
		WiretapWay oldWiretapWay = wiretapWay;
		wiretapWay = newWiretapWay == null ? WIRETAP_WAY_EDEFAULT : newWiretapWay;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EipPackage.EIP_PROVIDES__WIRETAP_WAY, oldWiretapWay, wiretapWay));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAggregatorCorrelation() {
		return aggregatorCorrelation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAggregatorCorrelation(String newAggregatorCorrelation) {
		String oldAggregatorCorrelation = aggregatorCorrelation;
		aggregatorCorrelation = newAggregatorCorrelation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EipPackage.EIP_PROVIDES__AGGREGATOR_CORRELATION, oldAggregatorCorrelation, aggregatorCorrelation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFaultRobust() {
		return faultRobust;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFaultRobust(boolean newFaultRobust) {
		boolean oldFaultRobust = faultRobust;
		faultRobust = newFaultRobust;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EipPackage.EIP_PROVIDES__FAULT_ROBUST, oldFaultRobust, faultRobust));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isExceptionRobust() {
		return exceptionRobust;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExceptionRobust(boolean newExceptionRobust) {
		boolean oldExceptionRobust = exceptionRobust;
		exceptionRobust = newExceptionRobust;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EipPackage.EIP_PROVIDES__EXCEPTION_ROBUST, oldExceptionRobust, exceptionRobust));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFaultToException() {
		return faultToException;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFaultToException(boolean newFaultToException) {
		boolean oldFaultToException = faultToException;
		faultToException = newFaultToException;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EipPackage.EIP_PROVIDES__FAULT_TO_EXCEPTION, oldFaultToException, faultToException));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAttachmentMode() {
		return attachmentMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttachmentMode(boolean newAttachmentMode) {
		boolean oldAttachmentMode = attachmentMode;
		attachmentMode = newAttachmentMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EipPackage.EIP_PROVIDES__ATTACHMENT_MODE, oldAttachmentMode, attachmentMode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EipPackage.EIP_PROVIDES__EIP:
				return getEip();
			case EipPackage.EIP_PROVIDES__TEST:
				return getTest();
			case EipPackage.EIP_PROVIDES__TEST_OPERATION:
				return getTestOperation();
			case EipPackage.EIP_PROVIDES__WIRETAP_WAY:
				return getWiretapWay();
			case EipPackage.EIP_PROVIDES__AGGREGATOR_CORRELATION:
				return getAggregatorCorrelation();
			case EipPackage.EIP_PROVIDES__FAULT_ROBUST:
				return isFaultRobust();
			case EipPackage.EIP_PROVIDES__EXCEPTION_ROBUST:
				return isExceptionRobust();
			case EipPackage.EIP_PROVIDES__FAULT_TO_EXCEPTION:
				return isFaultToException();
			case EipPackage.EIP_PROVIDES__ATTACHMENT_MODE:
				return isAttachmentMode();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EipPackage.EIP_PROVIDES__EIP:
				setEip((EipType)newValue);
				return;
			case EipPackage.EIP_PROVIDES__TEST:
				getTest().clear();
				getTest().addAll((Collection<? extends String>)newValue);
				return;
			case EipPackage.EIP_PROVIDES__TEST_OPERATION:
				getTestOperation().clear();
				getTestOperation().addAll((Collection<? extends String>)newValue);
				return;
			case EipPackage.EIP_PROVIDES__WIRETAP_WAY:
				setWiretapWay((WiretapWay)newValue);
				return;
			case EipPackage.EIP_PROVIDES__AGGREGATOR_CORRELATION:
				setAggregatorCorrelation((String)newValue);
				return;
			case EipPackage.EIP_PROVIDES__FAULT_ROBUST:
				setFaultRobust((Boolean)newValue);
				return;
			case EipPackage.EIP_PROVIDES__EXCEPTION_ROBUST:
				setExceptionRobust((Boolean)newValue);
				return;
			case EipPackage.EIP_PROVIDES__FAULT_TO_EXCEPTION:
				setFaultToException((Boolean)newValue);
				return;
			case EipPackage.EIP_PROVIDES__ATTACHMENT_MODE:
				setAttachmentMode((Boolean)newValue);
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
			case EipPackage.EIP_PROVIDES__EIP:
				unsetEip();
				return;
			case EipPackage.EIP_PROVIDES__TEST:
				getTest().clear();
				return;
			case EipPackage.EIP_PROVIDES__TEST_OPERATION:
				getTestOperation().clear();
				return;
			case EipPackage.EIP_PROVIDES__WIRETAP_WAY:
				setWiretapWay(WIRETAP_WAY_EDEFAULT);
				return;
			case EipPackage.EIP_PROVIDES__AGGREGATOR_CORRELATION:
				setAggregatorCorrelation(AGGREGATOR_CORRELATION_EDEFAULT);
				return;
			case EipPackage.EIP_PROVIDES__FAULT_ROBUST:
				setFaultRobust(FAULT_ROBUST_EDEFAULT);
				return;
			case EipPackage.EIP_PROVIDES__EXCEPTION_ROBUST:
				setExceptionRobust(EXCEPTION_ROBUST_EDEFAULT);
				return;
			case EipPackage.EIP_PROVIDES__FAULT_TO_EXCEPTION:
				setFaultToException(FAULT_TO_EXCEPTION_EDEFAULT);
				return;
			case EipPackage.EIP_PROVIDES__ATTACHMENT_MODE:
				setAttachmentMode(ATTACHMENT_MODE_EDEFAULT);
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
			case EipPackage.EIP_PROVIDES__EIP:
				return isSetEip();
			case EipPackage.EIP_PROVIDES__TEST:
				return test != null && !test.isEmpty();
			case EipPackage.EIP_PROVIDES__TEST_OPERATION:
				return testOperation != null && !testOperation.isEmpty();
			case EipPackage.EIP_PROVIDES__WIRETAP_WAY:
				return wiretapWay != WIRETAP_WAY_EDEFAULT;
			case EipPackage.EIP_PROVIDES__AGGREGATOR_CORRELATION:
				return AGGREGATOR_CORRELATION_EDEFAULT == null ? aggregatorCorrelation != null : !AGGREGATOR_CORRELATION_EDEFAULT.equals(aggregatorCorrelation);
			case EipPackage.EIP_PROVIDES__FAULT_ROBUST:
				return faultRobust != FAULT_ROBUST_EDEFAULT;
			case EipPackage.EIP_PROVIDES__EXCEPTION_ROBUST:
				return exceptionRobust != EXCEPTION_ROBUST_EDEFAULT;
			case EipPackage.EIP_PROVIDES__FAULT_TO_EXCEPTION:
				return faultToException != FAULT_TO_EXCEPTION_EDEFAULT;
			case EipPackage.EIP_PROVIDES__ATTACHMENT_MODE:
				return attachmentMode != ATTACHMENT_MODE_EDEFAULT;
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
		result.append(" (eip: ");
		if (eipESet) result.append(eip); else result.append("<unset>");
		result.append(", test: ");
		result.append(test);
		result.append(", testOperation: ");
		result.append(testOperation);
		result.append(", wiretapWay: ");
		result.append(wiretapWay);
		result.append(", aggregatorCorrelation: ");
		result.append(aggregatorCorrelation);
		result.append(", faultRobust: ");
		result.append(faultRobust);
		result.append(", exceptionRobust: ");
		result.append(exceptionRobust);
		result.append(", faultToException: ");
		result.append(faultToException);
		result.append(", attachmentMode: ");
		result.append(attachmentMode);
		result.append(')');
		return result.toString();
	}

} //EipProvidesImpl
