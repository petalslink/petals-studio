/**
 * Copyright (c) 2011, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 */
package com.ebmwebsourcing.petals.services.mail.mail.impl;

import com.ebmwebsourcing.petals.services.mail.mail.MailConsumes;
import com.ebmwebsourcing.petals.services.mail.mail.MailFactory;
import com.ebmwebsourcing.petals.services.mail.mail.MailPackage;
import com.ebmwebsourcing.petals.services.mail.mail.MailProvides;
import com.ebmwebsourcing.petals.services.mail.mail.Scheme;
import com.ebmwebsourcing.petals.services.mail.mail.SendMode;

import com.sun.java.xml.ns.jbi.JbiPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MailPackageImpl extends EPackageImpl implements MailPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mailProvidesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mailConsumesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum schemeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum sendModeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.ebmwebsourcing.petals.services.mail.mail.MailPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private MailPackageImpl() {
		super(eNS_URI, MailFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link MailPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static MailPackage init() {
		if (isInited) return (MailPackage)EPackage.Registry.INSTANCE.getEPackage(MailPackage.eNS_URI);

		// Obtain or create and register package
		MailPackageImpl theMailPackage = (MailPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof MailPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new MailPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		JbiPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theMailPackage.createPackageContents();

		// Initialize created meta-data
		theMailPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theMailPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(MailPackage.eNS_URI, theMailPackage);
		return theMailPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMailProvides() {
		return mailProvidesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailProvides_Scheme() {
		return (EAttribute)mailProvidesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailProvides_Host() {
		return (EAttribute)mailProvidesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailProvides_Port() {
		return (EAttribute)mailProvidesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailProvides_User() {
		return (EAttribute)mailProvidesEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailProvides_Password() {
		return (EAttribute)mailProvidesEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailProvides_From() {
		return (EAttribute)mailProvidesEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailProvides_Reply() {
		return (EAttribute)mailProvidesEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailProvides_To() {
		return (EAttribute)mailProvidesEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailProvides_Subject() {
		return (EAttribute)mailProvidesEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailProvides_Helohost() {
		return (EAttribute)mailProvidesEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailProvides_SendMode() {
		return (EAttribute)mailProvidesEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailProvides_ContentType() {
		return (EAttribute)mailProvidesEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMailConsumes() {
		return mailConsumesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailConsumes_Scheme() {
		return (EAttribute)mailConsumesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailConsumes_Host() {
		return (EAttribute)mailConsumesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailConsumes_Port() {
		return (EAttribute)mailConsumesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailConsumes_User() {
		return (EAttribute)mailConsumesEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailConsumes_Password() {
		return (EAttribute)mailConsumesEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailConsumes_Folder() {
		return (EAttribute)mailConsumesEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailConsumes_Delete() {
		return (EAttribute)mailConsumesEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailConsumes_Period() {
		return (EAttribute)mailConsumesEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailConsumes_Isxmlcontent() {
		return (EAttribute)mailConsumesEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getScheme() {
		return schemeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSendMode() {
		return sendModeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MailFactory getMailFactory() {
		return (MailFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		mailProvidesEClass = createEClass(MAIL_PROVIDES);
		createEAttribute(mailProvidesEClass, MAIL_PROVIDES__SCHEME);
		createEAttribute(mailProvidesEClass, MAIL_PROVIDES__HOST);
		createEAttribute(mailProvidesEClass, MAIL_PROVIDES__PORT);
		createEAttribute(mailProvidesEClass, MAIL_PROVIDES__USER);
		createEAttribute(mailProvidesEClass, MAIL_PROVIDES__PASSWORD);
		createEAttribute(mailProvidesEClass, MAIL_PROVIDES__FROM);
		createEAttribute(mailProvidesEClass, MAIL_PROVIDES__REPLY);
		createEAttribute(mailProvidesEClass, MAIL_PROVIDES__TO);
		createEAttribute(mailProvidesEClass, MAIL_PROVIDES__SUBJECT);
		createEAttribute(mailProvidesEClass, MAIL_PROVIDES__HELOHOST);
		createEAttribute(mailProvidesEClass, MAIL_PROVIDES__SEND_MODE);
		createEAttribute(mailProvidesEClass, MAIL_PROVIDES__CONTENT_TYPE);

		mailConsumesEClass = createEClass(MAIL_CONSUMES);
		createEAttribute(mailConsumesEClass, MAIL_CONSUMES__SCHEME);
		createEAttribute(mailConsumesEClass, MAIL_CONSUMES__HOST);
		createEAttribute(mailConsumesEClass, MAIL_CONSUMES__PORT);
		createEAttribute(mailConsumesEClass, MAIL_CONSUMES__USER);
		createEAttribute(mailConsumesEClass, MAIL_CONSUMES__PASSWORD);
		createEAttribute(mailConsumesEClass, MAIL_CONSUMES__FOLDER);
		createEAttribute(mailConsumesEClass, MAIL_CONSUMES__DELETE);
		createEAttribute(mailConsumesEClass, MAIL_CONSUMES__PERIOD);
		createEAttribute(mailConsumesEClass, MAIL_CONSUMES__ISXMLCONTENT);

		// Create enums
		schemeEEnum = createEEnum(SCHEME);
		sendModeEEnum = createEEnum(SEND_MODE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		JbiPackage theJbiPackage = (JbiPackage)EPackage.Registry.INSTANCE.getEPackage(JbiPackage.eNS_URI);
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		mailProvidesEClass.getESuperTypes().add(theJbiPackage.getProvides());
		mailConsumesEClass.getESuperTypes().add(theJbiPackage.getConsumes());

		// Initialize classes and features; add operations and parameters
		initEClass(mailProvidesEClass, MailProvides.class, "MailProvides", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMailProvides_Scheme(), this.getScheme(), "scheme", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailProvides_Host(), theXMLTypePackage.getString(), "host", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailProvides_Port(), theXMLTypePackage.getInt(), "port", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailProvides_User(), theXMLTypePackage.getString(), "user", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailProvides_Password(), theXMLTypePackage.getString(), "password", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailProvides_From(), theXMLTypePackage.getString(), "from", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailProvides_Reply(), theXMLTypePackage.getString(), "reply", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailProvides_To(), theXMLTypePackage.getString(), "to", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailProvides_Subject(), theXMLTypePackage.getString(), "subject", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailProvides_Helohost(), theXMLTypePackage.getString(), "helohost", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailProvides_SendMode(), this.getSendMode(), "sendMode", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailProvides_ContentType(), theXMLTypePackage.getString(), "contentType", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mailConsumesEClass, MailConsumes.class, "MailConsumes", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMailConsumes_Scheme(), this.getScheme(), "scheme", null, 1, 1, MailConsumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailConsumes_Host(), theXMLTypePackage.getString(), "host", null, 1, 1, MailConsumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailConsumes_Port(), theXMLTypePackage.getInt(), "port", null, 1, 1, MailConsumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailConsumes_User(), theXMLTypePackage.getString(), "user", null, 0, 1, MailConsumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailConsumes_Password(), theXMLTypePackage.getString(), "password", null, 0, 1, MailConsumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailConsumes_Folder(), theXMLTypePackage.getString(), "folder", null, 0, 1, MailConsumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailConsumes_Delete(), theXMLTypePackage.getBoolean(), "delete", null, 0, 1, MailConsumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailConsumes_Period(), theXMLTypePackage.getInt(), "period", null, 1, 1, MailConsumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailConsumes_Isxmlcontent(), theXMLTypePackage.getBoolean(), "isxmlcontent", null, 0, 1, MailConsumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(schemeEEnum, Scheme.class, "Scheme");
		addEEnumLiteral(schemeEEnum, Scheme.SMTP);
		addEEnumLiteral(schemeEEnum, Scheme.POP3);
		addEEnumLiteral(schemeEEnum, Scheme.IMAP);

		initEEnum(sendModeEEnum, SendMode.class, "SendMode");
		addEEnumLiteral(sendModeEEnum, SendMode.CONTENT_ONLY);
		addEEnumLiteral(sendModeEEnum, SendMode.ATTACHMENTS_ONLY);
		addEEnumLiteral(sendModeEEnum, SendMode.CONTENT_AND_ATTACHMENTS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
		addAnnotation
		  (mailProvidesEClass, 
		   source, 
		   new String[] {
			 "name", ""
		   });		
		addAnnotation
		  (getMailProvides_Scheme(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailProvides_Host(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailProvides_Port(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailProvides_User(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailProvides_Password(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailProvides_From(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailProvides_Reply(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailProvides_To(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailProvides_Subject(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailProvides_Helohost(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailProvides_SendMode(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0",
			 "name", "send-mode"
		   });		
		addAnnotation
		  (getMailProvides_ContentType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0",
			 "name", "content-type"
		   });		
		addAnnotation
		  (mailConsumesEClass, 
		   source, 
		   new String[] {
			 "name", ""
		   });		
		addAnnotation
		  (getMailConsumes_Scheme(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailConsumes_Host(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailConsumes_Port(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailConsumes_User(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailConsumes_Password(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailConsumes_Folder(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailConsumes_Delete(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailConsumes_Period(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailConsumes_Isxmlcontent(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });
	}

} //MailPackageImpl
