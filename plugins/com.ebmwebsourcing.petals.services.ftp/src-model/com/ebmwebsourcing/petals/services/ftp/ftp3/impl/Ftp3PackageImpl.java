/**
 * Copyright (c) 2011-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 */
package com.ebmwebsourcing.petals.services.ftp.ftp3.impl;

import com.ebmwebsourcing.petals.services.ftp.ftp3.ConnectionType;
import com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Factory;
import com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package;
import com.ebmwebsourcing.petals.services.ftp.ftp3.FtpProvides;
import com.ebmwebsourcing.petals.services.ftp.ftp3.TransferType;

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
public class Ftp3PackageImpl extends EPackageImpl implements Ftp3Package {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ftpProvidesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum connectionTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum transferTypeEEnum = null;

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
	 * @see com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Ftp3PackageImpl() {
		super(eNS_URI, Ftp3Factory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link Ftp3Package#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Ftp3Package init() {
		if (isInited) return (Ftp3Package)EPackage.Registry.INSTANCE.getEPackage(Ftp3Package.eNS_URI);

		// Obtain or create and register package
		Ftp3PackageImpl theFtp3Package = (Ftp3PackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Ftp3PackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Ftp3PackageImpl());

		isInited = true;

		// Initialize simple dependencies
		JbiPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theFtp3Package.createPackageContents();

		// Initialize created meta-data
		theFtp3Package.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theFtp3Package.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Ftp3Package.eNS_URI, theFtp3Package);
		return theFtp3Package;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFtpProvides() {
		return ftpProvidesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFtpProvides_ConnectionMode() {
		return (EAttribute)ftpProvidesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFtpProvides_DeleteProcessedFiles() {
		return (EAttribute)ftpProvidesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFtpProvides_Filename() {
		return (EAttribute)ftpProvidesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFtpProvides_Folder() {
		return (EAttribute)ftpProvidesEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFtpProvides_Overwrite() {
		return (EAttribute)ftpProvidesEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFtpProvides_Password() {
		return (EAttribute)ftpProvidesEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFtpProvides_Port() {
		return (EAttribute)ftpProvidesEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFtpProvides_Server() {
		return (EAttribute)ftpProvidesEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFtpProvides_User() {
		return (EAttribute)ftpProvidesEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFtpProvides_TransferType() {
		return (EAttribute)ftpProvidesEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFtpProvides_Encoding() {
		return (EAttribute)ftpProvidesEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFtpProvides_MaxIdleTime() {
		return (EAttribute)ftpProvidesEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFtpProvides_MaxConnection() {
		return (EAttribute)ftpProvidesEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getConnectionType() {
		return connectionTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTransferType() {
		return transferTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ftp3Factory getFtp3Factory() {
		return (Ftp3Factory)getEFactoryInstance();
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
		ftpProvidesEClass = createEClass(FTP_PROVIDES);
		createEAttribute(ftpProvidesEClass, FTP_PROVIDES__CONNECTION_MODE);
		createEAttribute(ftpProvidesEClass, FTP_PROVIDES__DELETE_PROCESSED_FILES);
		createEAttribute(ftpProvidesEClass, FTP_PROVIDES__FILENAME);
		createEAttribute(ftpProvidesEClass, FTP_PROVIDES__FOLDER);
		createEAttribute(ftpProvidesEClass, FTP_PROVIDES__OVERWRITE);
		createEAttribute(ftpProvidesEClass, FTP_PROVIDES__PASSWORD);
		createEAttribute(ftpProvidesEClass, FTP_PROVIDES__PORT);
		createEAttribute(ftpProvidesEClass, FTP_PROVIDES__SERVER);
		createEAttribute(ftpProvidesEClass, FTP_PROVIDES__USER);
		createEAttribute(ftpProvidesEClass, FTP_PROVIDES__TRANSFER_TYPE);
		createEAttribute(ftpProvidesEClass, FTP_PROVIDES__ENCODING);
		createEAttribute(ftpProvidesEClass, FTP_PROVIDES__MAX_IDLE_TIME);
		createEAttribute(ftpProvidesEClass, FTP_PROVIDES__MAX_CONNECTION);

		// Create enums
		connectionTypeEEnum = createEEnum(CONNECTION_TYPE);
		transferTypeEEnum = createEEnum(TRANSFER_TYPE);
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
		ftpProvidesEClass.getESuperTypes().add(theJbiPackage.getProvides());

		// Initialize classes and features; add operations and parameters
		initEClass(ftpProvidesEClass, FtpProvides.class, "FtpProvides", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFtpProvides_ConnectionMode(), this.getConnectionType(), "connectionMode", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFtpProvides_DeleteProcessedFiles(), theXMLTypePackage.getBoolean(), "deleteProcessedFiles", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFtpProvides_Filename(), theXMLTypePackage.getString(), "filename", "", 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFtpProvides_Folder(), theXMLTypePackage.getString(), "folder", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFtpProvides_Overwrite(), theXMLTypePackage.getBoolean(), "overwrite", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFtpProvides_Password(), theXMLTypePackage.getString(), "password", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFtpProvides_Port(), theXMLTypePackage.getInt(), "port", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFtpProvides_Server(), theXMLTypePackage.getString(), "server", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFtpProvides_User(), theXMLTypePackage.getString(), "user", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFtpProvides_TransferType(), this.getTransferType(), "transferType", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFtpProvides_Encoding(), theXMLTypePackage.getString(), "encoding", "", 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFtpProvides_MaxIdleTime(), theXMLTypePackage.getInt(), "maxIdleTime", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFtpProvides_MaxConnection(), theXMLTypePackage.getInt(), "maxConnection", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(connectionTypeEEnum, ConnectionType.class, "ConnectionType");
		addEEnumLiteral(connectionTypeEEnum, ConnectionType.ACTIVE);
		addEEnumLiteral(connectionTypeEEnum, ConnectionType.PASSIVE);

		initEEnum(transferTypeEEnum, TransferType.class, "TransferType");
		addEEnumLiteral(transferTypeEEnum, TransferType.AUTO);
		addEEnumLiteral(transferTypeEEnum, TransferType.ASCII);
		addEEnumLiteral(transferTypeEEnum, TransferType.BINARY);

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
		  (ftpProvidesEClass, 
		   source, 
		   new String[] {
			 "name", ""
		   });		
		addAnnotation
		  (getFtpProvides_ConnectionMode(), 
		   source, 
		   new String[] {
			 "name", "connection-mode",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getFtpProvides_DeleteProcessedFiles(), 
		   source, 
		   new String[] {
			 "name", "delete-processed-files",
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getFtpProvides_Filename(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getFtpProvides_Folder(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getFtpProvides_Overwrite(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getFtpProvides_Password(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getFtpProvides_Port(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getFtpProvides_Server(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getFtpProvides_User(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getFtpProvides_TransferType(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "transfer-type",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getFtpProvides_Encoding(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getFtpProvides_MaxIdleTime(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0",
			 "name", "max-idle-time"
		   });		
		addAnnotation
		  (getFtpProvides_MaxConnection(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0",
			 "name", "max-connection"
		   });
	}

} //Ftp3PackageImpl
