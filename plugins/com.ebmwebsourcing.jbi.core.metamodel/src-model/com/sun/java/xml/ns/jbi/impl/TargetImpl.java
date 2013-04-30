/**
 *  Copyright (c) 2009-2013, Linagora
 *  
 *  This source code is available under agreement available at
 *  http://www.petalslink.com/legal/licenses/petals-studio
 *  
 *  You should have received a copy of the agreement along with this program.
 *  If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.sun.java.xml.ns.jbi.impl;

import com.sun.java.xml.ns.jbi.JbiPackage;
import com.sun.java.xml.ns.jbi.Target;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Target</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.TargetImpl#getArtifactsZip <em>Artifacts Zip</em>}</li>
 *   <li>{@link com.sun.java.xml.ns.jbi.impl.TargetImpl#getComponentName <em>Component Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TargetImpl extends EObjectImpl implements Target {
	/**
	 * The default value of the '{@link #getArtifactsZip() <em>Artifacts Zip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifactsZip()
	 * @generated
	 * @ordered
	 */
	protected static final String ARTIFACTS_ZIP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getArtifactsZip() <em>Artifacts Zip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifactsZip()
	 * @generated
	 * @ordered
	 */
	protected String artifactsZip = ARTIFACTS_ZIP_EDEFAULT;

	/**
	 * The default value of the '{@link #getComponentName() <em>Component Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentName()
	 * @generated
	 * @ordered
	 */
	protected static final String COMPONENT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getComponentName() <em>Component Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentName()
	 * @generated
	 * @ordered
	 */
	protected String componentName = COMPONENT_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TargetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JbiPackage.Literals.TARGET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getArtifactsZip() {
		return artifactsZip;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArtifactsZip(String newArtifactsZip) {
		String oldArtifactsZip = artifactsZip;
		artifactsZip = newArtifactsZip;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JbiPackage.TARGET__ARTIFACTS_ZIP, oldArtifactsZip, artifactsZip));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getComponentName() {
		return componentName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentName(String newComponentName) {
		String oldComponentName = componentName;
		componentName = newComponentName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JbiPackage.TARGET__COMPONENT_NAME, oldComponentName, componentName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JbiPackage.TARGET__ARTIFACTS_ZIP:
				return getArtifactsZip();
			case JbiPackage.TARGET__COMPONENT_NAME:
				return getComponentName();
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
			case JbiPackage.TARGET__ARTIFACTS_ZIP:
				setArtifactsZip((String)newValue);
				return;
			case JbiPackage.TARGET__COMPONENT_NAME:
				setComponentName((String)newValue);
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
			case JbiPackage.TARGET__ARTIFACTS_ZIP:
				setArtifactsZip(ARTIFACTS_ZIP_EDEFAULT);
				return;
			case JbiPackage.TARGET__COMPONENT_NAME:
				setComponentName(COMPONENT_NAME_EDEFAULT);
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
			case JbiPackage.TARGET__ARTIFACTS_ZIP:
				return ARTIFACTS_ZIP_EDEFAULT == null ? artifactsZip != null : !ARTIFACTS_ZIP_EDEFAULT.equals(artifactsZip);
			case JbiPackage.TARGET__COMPONENT_NAME:
				return COMPONENT_NAME_EDEFAULT == null ? componentName != null : !COMPONENT_NAME_EDEFAULT.equals(componentName);
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
		result.append(" (artifactsZip: ");
		result.append(artifactsZip);
		result.append(", componentName: ");
		result.append(componentName);
		result.append(')');
		return result.toString();
	}

} //TargetImpl
