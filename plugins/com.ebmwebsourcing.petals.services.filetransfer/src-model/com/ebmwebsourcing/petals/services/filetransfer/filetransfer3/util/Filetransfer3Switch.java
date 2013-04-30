/**
 * Copyright (c) 2012-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.util;

import com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Consumes;
import com.ebmwebsourcing.petals.services.cdk.cdk5.CDK5Provides;

import com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.*;

import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.AbstractExtensibleElement;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.Provides;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.Filetransfer3Package
 * @generated
 */
public class Filetransfer3Switch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static Filetransfer3Package modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Filetransfer3Switch() {
		if (modelPackage == null) {
			modelPackage = Filetransfer3Package.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case Filetransfer3Package.FILE_TRANSFER3_PROVIDES: {
				FileTransfer3Provides fileTransfer3Provides = (FileTransfer3Provides)theEObject;
				T result = caseFileTransfer3Provides(fileTransfer3Provides);
				if (result == null) result = caseCDK5Provides(fileTransfer3Provides);
				if (result == null) result = caseProvides(fileTransfer3Provides);
				if (result == null) result = caseAbstractEndpoint(fileTransfer3Provides);
				if (result == null) result = caseAbstractExtensibleElement(fileTransfer3Provides);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Filetransfer3Package.FILE_TRANSFER3_CONSUMES: {
				FileTransfer3Consumes fileTransfer3Consumes = (FileTransfer3Consumes)theEObject;
				T result = caseFileTransfer3Consumes(fileTransfer3Consumes);
				if (result == null) result = caseCDK5Consumes(fileTransfer3Consumes);
				if (result == null) result = caseConsumes(fileTransfer3Consumes);
				if (result == null) result = caseAbstractEndpoint(fileTransfer3Consumes);
				if (result == null) result = caseAbstractExtensibleElement(fileTransfer3Consumes);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>File Transfer3 Provides</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>File Transfer3 Provides</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFileTransfer3Provides(FileTransfer3Provides object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>File Transfer3 Consumes</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>File Transfer3 Consumes</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFileTransfer3Consumes(FileTransfer3Consumes object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Extensible Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Extensible Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractExtensibleElement(AbstractExtensibleElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Endpoint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Endpoint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractEndpoint(AbstractEndpoint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Provides</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Provides</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProvides(Provides object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CDK5 Provides</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CDK5 Provides</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCDK5Provides(CDK5Provides object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Consumes</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Consumes</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConsumes(Consumes object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CDK5 Consumes</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CDK5 Consumes</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCDK5Consumes(CDK5Consumes object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //Filetransfer3Switch
