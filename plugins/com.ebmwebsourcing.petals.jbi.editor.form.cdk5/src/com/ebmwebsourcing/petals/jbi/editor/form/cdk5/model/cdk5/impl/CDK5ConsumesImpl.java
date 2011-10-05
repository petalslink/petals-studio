/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.CDK5Consumes;
import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CDK5 Consumes</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDK5ConsumesImpl#getOperation <em>Operation</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.impl.CDK5ConsumesImpl#getMep <em>Mep</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CDK5ConsumesImpl extends CDKServiceImpl implements CDK5Consumes {
	/**
	 * The default value of the '{@link #getOperation() <em>Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperation()
	 * @generated
	 * @ordered
	 */
	protected static final String OPERATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOperation() <em>Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperation()
	 * @generated
	 * @ordered
	 */
	protected String operation = OPERATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getMep() <em>Mep</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMep()
	 * @generated
	 * @ordered
	 */
	protected static final String MEP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMep() <em>Mep</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMep()
	 * @generated
	 * @ordered
	 */
	protected String mep = MEP_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CDK5ConsumesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Cdk5Package.Literals.CDK5_CONSUMES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperation(String newOperation) {
		String oldOperation = operation;
		operation = newOperation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Cdk5Package.CDK5_CONSUMES__OPERATION, oldOperation, operation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMep() {
		return mep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMep(String newMep) {
		String oldMep = mep;
		mep = newMep;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Cdk5Package.CDK5_CONSUMES__MEP, oldMep, mep));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Cdk5Package.CDK5_CONSUMES__OPERATION:
				return getOperation();
			case Cdk5Package.CDK5_CONSUMES__MEP:
				return getMep();
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
			case Cdk5Package.CDK5_CONSUMES__OPERATION:
				setOperation((String)newValue);
				return;
			case Cdk5Package.CDK5_CONSUMES__MEP:
				setMep((String)newValue);
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
			case Cdk5Package.CDK5_CONSUMES__OPERATION:
				setOperation(OPERATION_EDEFAULT);
				return;
			case Cdk5Package.CDK5_CONSUMES__MEP:
				setMep(MEP_EDEFAULT);
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
			case Cdk5Package.CDK5_CONSUMES__OPERATION:
				return OPERATION_EDEFAULT == null ? operation != null : !OPERATION_EDEFAULT.equals(operation);
			case Cdk5Package.CDK5_CONSUMES__MEP:
				return MEP_EDEFAULT == null ? mep != null : !MEP_EDEFAULT.equals(mep);
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
		result.append(" (operation: ");
		result.append(operation);
		result.append(", mep: ");
		result.append(mep);
		result.append(')');
		return result.toString();
	}

} //CDK5ConsumesImpl
