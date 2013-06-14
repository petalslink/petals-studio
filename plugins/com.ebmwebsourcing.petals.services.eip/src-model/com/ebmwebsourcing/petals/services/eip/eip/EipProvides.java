/**
 * Copyright (c) 2012-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 */
package com.ebmwebsourcing.petals.services.eip.eip;

import com.sun.java.xml.ns.jbi.Provides;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Provides</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#getEip <em>Eip</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#getTest <em>Test</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#getTestOperation <em>Test Operation</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#getWiretapWay <em>Wiretap Way</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#getAggregatorCorrelation <em>Aggregator Correlation</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#isFaultRobust <em>Fault Robust</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#isExceptionRobust <em>Exception Robust</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#isFaultToException <em>Fault To Exception</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#isAttachmentMode <em>Attachment Mode</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.services.eip.eip.EipPackage#getEipProvides()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface EipProvides extends Provides {
	/**
	 * Returns the value of the '<em><b>Eip</b></em>' attribute.
	 * The literals are from the enumeration {@link com.ebmwebsourcing.petals.services.eip.eip.EipType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Eip</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Eip</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.eip.eip.EipType
	 * @see #isSetEip()
	 * @see #unsetEip()
	 * @see #setEip(EipType)
	 * @see com.ebmwebsourcing.petals.services.eip.eip.EipPackage#getEipProvides_Eip()
	 * @model unsettable="true" required="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EipType getEip();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#getEip <em>Eip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Eip</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.eip.eip.EipType
	 * @see #isSetEip()
	 * @see #unsetEip()
	 * @see #getEip()
	 * @generated
	 */
	void setEip(EipType value);

	/**
	 * Unsets the value of the '{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#getEip <em>Eip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEip()
	 * @see #getEip()
	 * @see #setEip(EipType)
	 * @generated
	 */
	void unsetEip();

	/**
	 * Returns whether the value of the '{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#getEip <em>Eip</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Eip</em>' attribute is set.
	 * @see #unsetEip()
	 * @see #getEip()
	 * @see #setEip(EipType)
	 * @generated
	 */
	boolean isSetEip();

	/**
	 * Returns the value of the '<em><b>Test</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test</em>' attribute list.
	 * @see com.ebmwebsourcing.petals.services.eip.eip.EipPackage#getEipProvides_Test()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<String> getTest();

	/**
	 * Returns the value of the '<em><b>Test Operation</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test Operation</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Operation</em>' attribute list.
	 * @see com.ebmwebsourcing.petals.services.eip.eip.EipPackage#getEipProvides_TestOperation()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0' name='test-operation'"
	 * @generated
	 */
	EList<String> getTestOperation();

	/**
	 * Returns the value of the '<em><b>Wiretap Way</b></em>' attribute.
	 * The literals are from the enumeration {@link com.ebmwebsourcing.petals.services.eip.eip.WiretapWay}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wiretap Way</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wiretap Way</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.eip.eip.WiretapWay
	 * @see #setWiretapWay(WiretapWay)
	 * @see com.ebmwebsourcing.petals.services.eip.eip.EipPackage#getEipProvides_WiretapWay()
	 * @model extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0' name='wiretap-way'"
	 * @generated
	 */
	WiretapWay getWiretapWay();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#getWiretapWay <em>Wiretap Way</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wiretap Way</em>' attribute.
	 * @see com.ebmwebsourcing.petals.services.eip.eip.WiretapWay
	 * @see #getWiretapWay()
	 * @generated
	 */
	void setWiretapWay(WiretapWay value);

	/**
	 * Returns the value of the '<em><b>Aggregator Correlation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aggregator Correlation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aggregator Correlation</em>' attribute.
	 * @see #setAggregatorCorrelation(String)
	 * @see com.ebmwebsourcing.petals.services.eip.eip.EipPackage#getEipProvides_AggregatorCorrelation()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0' name='aggregator-correlation'"
	 * @generated
	 */
	String getAggregatorCorrelation();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#getAggregatorCorrelation <em>Aggregator Correlation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Aggregator Correlation</em>' attribute.
	 * @see #getAggregatorCorrelation()
	 * @generated
	 */
	void setAggregatorCorrelation(String value);

	/**
	 * Returns the value of the '<em><b>Fault Robust</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fault Robust</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fault Robust</em>' attribute.
	 * @see #setFaultRobust(boolean)
	 * @see com.ebmwebsourcing.petals.services.eip.eip.EipPackage#getEipProvides_FaultRobust()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0' name='fault-robust'"
	 * @generated
	 */
	boolean isFaultRobust();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#isFaultRobust <em>Fault Robust</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fault Robust</em>' attribute.
	 * @see #isFaultRobust()
	 * @generated
	 */
	void setFaultRobust(boolean value);

	/**
	 * Returns the value of the '<em><b>Exception Robust</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exception Robust</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exception Robust</em>' attribute.
	 * @see #setExceptionRobust(boolean)
	 * @see com.ebmwebsourcing.petals.services.eip.eip.EipPackage#getEipProvides_ExceptionRobust()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0' name='exception-robust'"
	 * @generated
	 */
	boolean isExceptionRobust();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#isExceptionRobust <em>Exception Robust</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exception Robust</em>' attribute.
	 * @see #isExceptionRobust()
	 * @generated
	 */
	void setExceptionRobust(boolean value);

	/**
	 * Returns the value of the '<em><b>Fault To Exception</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fault To Exception</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fault To Exception</em>' attribute.
	 * @see #setFaultToException(boolean)
	 * @see com.ebmwebsourcing.petals.services.eip.eip.EipPackage#getEipProvides_FaultToException()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0' name='fault-to-exception'"
	 * @generated
	 */
	boolean isFaultToException();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#isFaultToException <em>Fault To Exception</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fault To Exception</em>' attribute.
	 * @see #isFaultToException()
	 * @generated
	 */
	void setFaultToException(boolean value);

	/**
	 * Returns the value of the '<em><b>Attachment Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attachment Mode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attachment Mode</em>' attribute.
	 * @see #setAttachmentMode(boolean)
	 * @see com.ebmwebsourcing.petals.services.eip.eip.EipPackage#getEipProvides_AttachmentMode()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace' group='#group:0' name='attachment-mode'"
	 * @generated
	 */
	boolean isAttachmentMode();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#isAttachmentMode <em>Attachment Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attachment Mode</em>' attribute.
	 * @see #isAttachmentMode()
	 * @generated
	 */
	void setAttachmentMode(boolean value);

} // EipProvides
