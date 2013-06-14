/**
 * Copyright (c) 2011-2013, Linagora
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 */
package com.ebmwebsourcing.petals.studio.services.xslt.xslt;

import com.sun.java.xml.ns.jbi.JbiPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see com.ebmwebsourcing.petals.studio.services.xslt.xslt.XsltFactory
 * @model kind="package"
 * @generated
 */
public interface XsltPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "xslt";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://petals.ow2.org/components/xslt/version-2";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "xslt";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	XsltPackage eINSTANCE = com.ebmwebsourcing.petals.studio.services.xslt.xslt.impl.XsltPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.ebmwebsourcing.petals.studio.services.xslt.xslt.impl.XSLTProvidesImpl <em>XSLT Provides</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ebmwebsourcing.petals.studio.services.xslt.xslt.impl.XSLTProvidesImpl
	 * @see com.ebmwebsourcing.petals.studio.services.xslt.xslt.impl.XsltPackageImpl#getXSLTProvides()
	 * @generated
	 */
	int XSLT_PROVIDES = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSLT_PROVIDES__GROUP = JbiPackage.PROVIDES__GROUP;

	/**
	 * The feature id for the '<em><b>Other</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSLT_PROVIDES__OTHER = JbiPackage.PROVIDES__OTHER;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSLT_PROVIDES__LOCAL = JbiPackage.PROVIDES__LOCAL;

	/**
	 * The feature id for the '<em><b>Endpoint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSLT_PROVIDES__ENDPOINT_NAME = JbiPackage.PROVIDES__ENDPOINT_NAME;

	/**
	 * The feature id for the '<em><b>Interface Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSLT_PROVIDES__INTERFACE_NAME = JbiPackage.PROVIDES__INTERFACE_NAME;

	/**
	 * The feature id for the '<em><b>Service Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSLT_PROVIDES__SERVICE_NAME = JbiPackage.PROVIDES__SERVICE_NAME;

	/**
	 * The feature id for the '<em><b>Stylesheet</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSLT_PROVIDES__STYLESHEET = JbiPackage.PROVIDES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Transformer Factory Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSLT_PROVIDES__TRANSFORMER_FACTORY_MIN = JbiPackage.PROVIDES_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Transformer Factory Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSLT_PROVIDES__TRANSFORMER_FACTORY_MAX = JbiPackage.PROVIDES_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Output Attachment Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSLT_PROVIDES__OUTPUT_ATTACHMENT_NAME = JbiPackage.PROVIDES_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Xslt Engine Factory Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSLT_PROVIDES__XSLT_ENGINE_FACTORY_CLASS_NAME = JbiPackage.PROVIDES_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>XSLT Provides</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSLT_PROVIDES_FEATURE_COUNT = JbiPackage.PROVIDES_FEATURE_COUNT + 5;


	/**
	 * Returns the meta object for class '{@link com.ebmwebsourcing.petals.studio.services.xslt.xslt.XSLTProvides <em>XSLT Provides</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>XSLT Provides</em>'.
	 * @see com.ebmwebsourcing.petals.studio.services.xslt.xslt.XSLTProvides
	 * @generated
	 */
	EClass getXSLTProvides();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.studio.services.xslt.xslt.XSLTProvides#getStylesheet <em>Stylesheet</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stylesheet</em>'.
	 * @see com.ebmwebsourcing.petals.studio.services.xslt.xslt.XSLTProvides#getStylesheet()
	 * @see #getXSLTProvides()
	 * @generated
	 */
	EAttribute getXSLTProvides_Stylesheet();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.studio.services.xslt.xslt.XSLTProvides#getTransformerFactoryMin <em>Transformer Factory Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transformer Factory Min</em>'.
	 * @see com.ebmwebsourcing.petals.studio.services.xslt.xslt.XSLTProvides#getTransformerFactoryMin()
	 * @see #getXSLTProvides()
	 * @generated
	 */
	EAttribute getXSLTProvides_TransformerFactoryMin();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.studio.services.xslt.xslt.XSLTProvides#getTransformerFactoryMax <em>Transformer Factory Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transformer Factory Max</em>'.
	 * @see com.ebmwebsourcing.petals.studio.services.xslt.xslt.XSLTProvides#getTransformerFactoryMax()
	 * @see #getXSLTProvides()
	 * @generated
	 */
	EAttribute getXSLTProvides_TransformerFactoryMax();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.studio.services.xslt.xslt.XSLTProvides#getOutputAttachmentName <em>Output Attachment Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Output Attachment Name</em>'.
	 * @see com.ebmwebsourcing.petals.studio.services.xslt.xslt.XSLTProvides#getOutputAttachmentName()
	 * @see #getXSLTProvides()
	 * @generated
	 */
	EAttribute getXSLTProvides_OutputAttachmentName();

	/**
	 * Returns the meta object for the attribute '{@link com.ebmwebsourcing.petals.studio.services.xslt.xslt.XSLTProvides#getXsltEngineFactoryClassName <em>Xslt Engine Factory Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xslt Engine Factory Class Name</em>'.
	 * @see com.ebmwebsourcing.petals.studio.services.xslt.xslt.XSLTProvides#getXsltEngineFactoryClassName()
	 * @see #getXSLTProvides()
	 * @generated
	 */
	EAttribute getXSLTProvides_XsltEngineFactoryClassName();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	XsltFactory getXsltFactory();

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
		 * The meta object literal for the '{@link com.ebmwebsourcing.petals.studio.services.xslt.xslt.impl.XSLTProvidesImpl <em>XSLT Provides</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ebmwebsourcing.petals.studio.services.xslt.xslt.impl.XSLTProvidesImpl
		 * @see com.ebmwebsourcing.petals.studio.services.xslt.xslt.impl.XsltPackageImpl#getXSLTProvides()
		 * @generated
		 */
		EClass XSLT_PROVIDES = eINSTANCE.getXSLTProvides();

		/**
		 * The meta object literal for the '<em><b>Stylesheet</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute XSLT_PROVIDES__STYLESHEET = eINSTANCE.getXSLTProvides_Stylesheet();

		/**
		 * The meta object literal for the '<em><b>Transformer Factory Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute XSLT_PROVIDES__TRANSFORMER_FACTORY_MIN = eINSTANCE.getXSLTProvides_TransformerFactoryMin();

		/**
		 * The meta object literal for the '<em><b>Transformer Factory Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute XSLT_PROVIDES__TRANSFORMER_FACTORY_MAX = eINSTANCE.getXSLTProvides_TransformerFactoryMax();

		/**
		 * The meta object literal for the '<em><b>Output Attachment Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute XSLT_PROVIDES__OUTPUT_ATTACHMENT_NAME = eINSTANCE.getXSLTProvides_OutputAttachmentName();

		/**
		 * The meta object literal for the '<em><b>Xslt Engine Factory Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute XSLT_PROVIDES__XSLT_ENGINE_FACTORY_CLASS_NAME = eINSTANCE.getXSLTProvides_XsltEngineFactoryClassName();

	}

} //XsltPackage
