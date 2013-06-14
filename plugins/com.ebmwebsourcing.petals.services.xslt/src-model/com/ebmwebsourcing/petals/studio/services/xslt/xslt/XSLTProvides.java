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

import com.sun.java.xml.ns.jbi.Provides;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XSLT Provides</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ebmwebsourcing.petals.studio.services.xslt.xslt.XSLTProvides#getStylesheet <em>Stylesheet</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.studio.services.xslt.xslt.XSLTProvides#getTransformerFactoryMin <em>Transformer Factory Min</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.studio.services.xslt.xslt.XSLTProvides#getTransformerFactoryMax <em>Transformer Factory Max</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.studio.services.xslt.xslt.XSLTProvides#getOutputAttachmentName <em>Output Attachment Name</em>}</li>
 *   <li>{@link com.ebmwebsourcing.petals.studio.services.xslt.xslt.XSLTProvides#getXsltEngineFactoryClassName <em>Xslt Engine Factory Class Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ebmwebsourcing.petals.studio.services.xslt.xslt.XsltPackage#getXSLTProvides()
 * @model extendedMetaData="name=''"
 * @generated
 */
public interface XSLTProvides extends Provides {
	/**
	 * Returns the value of the '<em><b>Stylesheet</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stylesheet</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stylesheet</em>' attribute.
	 * @see #setStylesheet(String)
	 * @see com.ebmwebsourcing.petals.studio.services.xslt.xslt.XsltPackage#getXSLTProvides_Stylesheet()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getStylesheet();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.studio.services.xslt.xslt.XSLTProvides#getStylesheet <em>Stylesheet</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stylesheet</em>' attribute.
	 * @see #getStylesheet()
	 * @generated
	 */
	void setStylesheet(String value);

	/**
	 * Returns the value of the '<em><b>Transformer Factory Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transformer Factory Min</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transformer Factory Min</em>' attribute.
	 * @see #setTransformerFactoryMin(int)
	 * @see com.ebmwebsourcing.petals.studio.services.xslt.xslt.XsltPackage#getXSLTProvides_TransformerFactoryMin()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0' name='transformer-factory-min'"
	 * @generated
	 */
	int getTransformerFactoryMin();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.studio.services.xslt.xslt.XSLTProvides#getTransformerFactoryMin <em>Transformer Factory Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transformer Factory Min</em>' attribute.
	 * @see #getTransformerFactoryMin()
	 * @generated
	 */
	void setTransformerFactoryMin(int value);

	/**
	 * Returns the value of the '<em><b>Transformer Factory Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transformer Factory Max</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transformer Factory Max</em>' attribute.
	 * @see #setTransformerFactoryMax(int)
	 * @see com.ebmwebsourcing.petals.studio.services.xslt.xslt.XsltPackage#getXSLTProvides_TransformerFactoryMax()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0' name='transformer-factory-max'"
	 * @generated
	 */
	int getTransformerFactoryMax();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.studio.services.xslt.xslt.XSLTProvides#getTransformerFactoryMax <em>Transformer Factory Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transformer Factory Max</em>' attribute.
	 * @see #getTransformerFactoryMax()
	 * @generated
	 */
	void setTransformerFactoryMax(int value);

	/**
	 * Returns the value of the '<em><b>Output Attachment Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output Attachment Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output Attachment Name</em>' attribute.
	 * @see #setOutputAttachmentName(String)
	 * @see com.ebmwebsourcing.petals.studio.services.xslt.xslt.XsltPackage#getXSLTProvides_OutputAttachmentName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" derived="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getOutputAttachmentName();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.studio.services.xslt.xslt.XSLTProvides#getOutputAttachmentName <em>Output Attachment Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Output Attachment Name</em>' attribute.
	 * @see #getOutputAttachmentName()
	 * @generated
	 */
	void setOutputAttachmentName(String value);

	/**
	 * Returns the value of the '<em><b>Xslt Engine Factory Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Xslt Engine Factory Class Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Xslt Engine Factory Class Name</em>' attribute.
	 * @see #setXsltEngineFactoryClassName(String)
	 * @see com.ebmwebsourcing.petals.studio.services.xslt.xslt.XsltPackage#getXSLTProvides_XsltEngineFactoryClassName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="name='xslt-engine-factory-class-name' namespace='##targetNamespace' kind='element' group='#group:0'"
	 * @generated
	 */
	String getXsltEngineFactoryClassName();

	/**
	 * Sets the value of the '{@link com.ebmwebsourcing.petals.studio.services.xslt.xslt.XSLTProvides#getXsltEngineFactoryClassName <em>Xslt Engine Factory Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xslt Engine Factory Class Name</em>' attribute.
	 * @see #getXsltEngineFactoryClassName()
	 * @generated
	 */
	void setXsltEngineFactoryClassName(String value);

} // XSLTProvides
