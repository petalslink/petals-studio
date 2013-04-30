/**
 * Copyright (c) 2012-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.eip.eip;

import com.sun.java.xml.ns.jbi.JbiPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.ebmwebsourcing.petals.services.eip.eip.EipFactory
 * @model kind="package"
 * @generated
 */
public interface EipPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "eip";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://petals.ow2.org/components/eip/version-2";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "eip";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EipPackage eINSTANCE = com.ebmwebsourcing.petals.services.eip.eip.impl.EipPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.eip.eip.impl.EipProvidesImpl <em>Provides</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.eip.eip.impl.EipProvidesImpl
	 * @see com.ebmwebsourcing.petals.services.eip.eip.impl.EipPackageImpl#getEipProvides()
	 * @generated
	 */
	int EIP_PROVIDES = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EIP_PROVIDES__GROUP = JbiPackage.PROVIDES__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EIP_PROVIDES__OTHER = JbiPackage.PROVIDES__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EIP_PROVIDES__LOCAL = JbiPackage.PROVIDES__LOCAL;

	/**
	 * The feature id for the '<em><b>Endpoint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EIP_PROVIDES__ENDPOINT_NAME = JbiPackage.PROVIDES__ENDPOINT_NAME;

	/**
	 * The feature id for the '<em><b>Interface Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EIP_PROVIDES__INTERFACE_NAME = JbiPackage.PROVIDES__INTERFACE_NAME;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EIP_PROVIDES__SERVICE_NAME = JbiPackage.PROVIDES__SERVICE_NAME;

	/**
	 * The feature id for the '<em><b>Eip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EIP_PROVIDES__EIP = JbiPackage.PROVIDES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Test</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EIP_PROVIDES__TEST = JbiPackage.PROVIDES_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Test Operation</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EIP_PROVIDES__TEST_OPERATION = JbiPackage.PROVIDES_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Wiretap Way</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EIP_PROVIDES__WIRETAP_WAY = JbiPackage.PROVIDES_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Aggregator Correlation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EIP_PROVIDES__AGGREGATOR_CORRELATION = JbiPackage.PROVIDES_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Fault Robust</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EIP_PROVIDES__FAULT_ROBUST = JbiPackage.PROVIDES_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Exception Robust</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EIP_PROVIDES__EXCEPTION_ROBUST = JbiPackage.PROVIDES_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Fault To Exception</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EIP_PROVIDES__FAULT_TO_EXCEPTION = JbiPackage.PROVIDES_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Attachment Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EIP_PROVIDES__ATTACHMENT_MODE = JbiPackage.PROVIDES_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Provides</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EIP_PROVIDES_FEATURE_COUNT = JbiPackage.PROVIDES_FEATURE_COUNT + 9;

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.eip.eip.EipType <em>Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.eip.eip.EipType
	 * @see com.ebmwebsourcing.petals.services.eip.eip.impl.EipPackageImpl#getEipType()
	 * @generated
	 */
	int EIP_TYPE = 1;

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.services.eip.eip.WiretapWay <em>Wiretap Way</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.services.eip.eip.WiretapWay
	 * @see com.ebmwebsourcing.petals.services.eip.eip.impl.EipPackageImpl#getWiretapWay()
	 * @generated
	 */
	int WIRETAP_WAY = 2;


	/**
	 * Returns the meta object for class '{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides <em>Provides</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Provides</em>'.
	 * @see com.ebmwebsourcing.petals.services.eip.eip.EipProvides
	 * @generated
	 */
	EClass getEipProvides();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#getEip <em>Eip</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Eip</em>'.
	 * @see com.ebmwebsourcing.petals.services.eip.eip.EipProvides#getEip()
	 * @see #getEipProvides()
	 * @generated
	 */
	EAttribute getEipProvides_Eip();

	/**
	 * Returns the meta object for the attribute list '{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#getTest <em>Test</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Test</em>'.
	 * @see com.ebmwebsourcing.petals.services.eip.eip.EipProvides#getTest()
	 * @see #getEipProvides()
	 * @generated
	 */
	EAttribute getEipProvides_Test();

	/**
	 * Returns the meta object for the attribute list '{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#getTestOperation <em>Test Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Test Operation</em>'.
	 * @see com.ebmwebsourcing.petals.services.eip.eip.EipProvides#getTestOperation()
	 * @see #getEipProvides()
	 * @generated
	 */
	EAttribute getEipProvides_TestOperation();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#getWiretapWay <em>Wiretap Way</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wiretap Way</em>'.
	 * @see com.ebmwebsourcing.petals.services.eip.eip.EipProvides#getWiretapWay()
	 * @see #getEipProvides()
	 * @generated
	 */
	EAttribute getEipProvides_WiretapWay();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#getAggregatorCorrelation <em>Aggregator Correlation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Aggregator Correlation</em>'.
	 * @see com.ebmwebsourcing.petals.services.eip.eip.EipProvides#getAggregatorCorrelation()
	 * @see #getEipProvides()
	 * @generated
	 */
	EAttribute getEipProvides_AggregatorCorrelation();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#isFaultRobust <em>Fault Robust</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fault Robust</em>'.
	 * @see com.ebmwebsourcing.petals.services.eip.eip.EipProvides#isFaultRobust()
	 * @see #getEipProvides()
	 * @generated
	 */
	EAttribute getEipProvides_FaultRobust();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#isExceptionRobust <em>Exception Robust</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Exception Robust</em>'.
	 * @see com.ebmwebsourcing.petals.services.eip.eip.EipProvides#isExceptionRobust()
	 * @see #getEipProvides()
	 * @generated
	 */
	EAttribute getEipProvides_ExceptionRobust();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#isFaultToException <em>Fault To Exception</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fault To Exception</em>'.
	 * @see com.ebmwebsourcing.petals.services.eip.eip.EipProvides#isFaultToException()
	 * @see #getEipProvides()
	 * @generated
	 */
	EAttribute getEipProvides_FaultToException();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.services.eip.eip.EipProvides#isAttachmentMode <em>Attachment Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attachment Mode</em>'.
	 * @see com.ebmwebsourcing.petals.services.eip.eip.EipProvides#isAttachmentMode()
	 * @see #getEipProvides()
	 * @generated
	 */
	EAttribute getEipProvides_AttachmentMode();

	/**
	 * Returns the meta object for enum '{@link com.ebmwebsourcing.petals.services.eip.eip.EipType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Type</em>'.
	 * @see com.ebmwebsourcing.petals.services.eip.eip.EipType
	 * @generated
	 */
	EEnum getEipType();

	/**
	 * Returns the meta object for enum '{@link com.ebmwebsourcing.petals.services.eip.eip.WiretapWay <em>Wiretap Way</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Wiretap Way</em>'.
	 * @see com.ebmwebsourcing.petals.services.eip.eip.WiretapWay
	 * @generated
	 */
	EEnum getWiretapWay();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EipFactory getEipFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.eip.eip.impl.EipProvidesImpl <em>Provides</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.eip.eip.impl.EipProvidesImpl
		 * @see com.ebmwebsourcing.petals.services.eip.eip.impl.EipPackageImpl#getEipProvides()
		 * @generated
		 */
		EClass EIP_PROVIDES = eINSTANCE.getEipProvides();

		/**
		 * The meta object literal for the '<em><b>Eip</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EIP_PROVIDES__EIP = eINSTANCE.getEipProvides_Eip();

		/**
		 * The meta object literal for the '<em><b>Test</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EIP_PROVIDES__TEST = eINSTANCE.getEipProvides_Test();

		/**
		 * The meta object literal for the '<em><b>Test Operation</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EIP_PROVIDES__TEST_OPERATION = eINSTANCE.getEipProvides_TestOperation();

		/**
		 * The meta object literal for the '<em><b>Wiretap Way</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EIP_PROVIDES__WIRETAP_WAY = eINSTANCE.getEipProvides_WiretapWay();

		/**
		 * The meta object literal for the '<em><b>Aggregator Correlation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EIP_PROVIDES__AGGREGATOR_CORRELATION = eINSTANCE.getEipProvides_AggregatorCorrelation();

		/**
		 * The meta object literal for the '<em><b>Fault Robust</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EIP_PROVIDES__FAULT_ROBUST = eINSTANCE.getEipProvides_FaultRobust();

		/**
		 * The meta object literal for the '<em><b>Exception Robust</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EIP_PROVIDES__EXCEPTION_ROBUST = eINSTANCE.getEipProvides_ExceptionRobust();

		/**
		 * The meta object literal for the '<em><b>Fault To Exception</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EIP_PROVIDES__FAULT_TO_EXCEPTION = eINSTANCE.getEipProvides_FaultToException();

		/**
		 * The meta object literal for the '<em><b>Attachment Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EIP_PROVIDES__ATTACHMENT_MODE = eINSTANCE.getEipProvides_AttachmentMode();

		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.eip.eip.EipType <em>Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.eip.eip.EipType
		 * @see com.ebmwebsourcing.petals.services.eip.eip.impl.EipPackageImpl#getEipType()
		 * @generated
		 */
		EEnum EIP_TYPE = eINSTANCE.getEipType();

		/**
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.services.eip.eip.WiretapWay <em>Wiretap Way</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.services.eip.eip.WiretapWay
		 * @see com.ebmwebsourcing.petals.services.eip.eip.impl.EipPackageImpl#getWiretapWay()
		 * @generated
		 */
		EEnum WIRETAP_WAY = eINSTANCE.getWiretapWay();

	}

} //EipPackage
