/**
 * Copyright (c) 2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.impl;

import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;

import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.Filetransfer2xPackage;

import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl.Filetransfer2xPackageImpl;

import com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Consumes;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.FileTransfer3Provides;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.Filetransfer3Factory;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.Filetransfer3Package;

import com.sun.java.xml.ns.jbi.JbiPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Filetransfer3PackageImpl extends EPackageImpl implements Filetransfer3Package {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fileTransfer3ProvidesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fileTransfer3ConsumesEClass = null;

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
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer3.Filetransfer3Package#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Filetransfer3PackageImpl() {
		super(eNS_URI, Filetransfer3Factory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link Filetransfer3Package#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Filetransfer3Package init() {
		if (isInited) return (Filetransfer3Package)EPackage.Registry.INSTANCE.getEPackage(Filetransfer3Package.eNS_URI);

		// Obtain or create and register package
		Filetransfer3PackageImpl theFiletransfer3Package = (Filetransfer3PackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Filetransfer3PackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Filetransfer3PackageImpl());

		isInited = true;

		// Initialize simple dependencies
		Cdk5Package.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		Filetransfer2xPackageImpl theFiletransfer2xPackage = (Filetransfer2xPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Filetransfer2xPackage.eNS_URI) instanceof Filetransfer2xPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Filetransfer2xPackage.eNS_URI) : Filetransfer2xPackage.eINSTANCE);

		// Create package meta-data objects
		theFiletransfer3Package.createPackageContents();
		theFiletransfer2xPackage.createPackageContents();

		// Initialize created meta-data
		theFiletransfer3Package.initializePackageContents();
		theFiletransfer2xPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theFiletransfer3Package.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Filetransfer3Package.eNS_URI, theFiletransfer3Package);
		return theFiletransfer3Package;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFileTransfer3Provides() {
		return fileTransfer3ProvidesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileTransfer3Provides_Filename() {
		return (EAttribute)fileTransfer3ProvidesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileTransfer3Provides_BackupDirectory() {
		return (EAttribute)fileTransfer3ProvidesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileTransfer3Provides_Folder() {
		return (EAttribute)fileTransfer3ProvidesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFileTransfer3Consumes() {
		return fileTransfer3ConsumesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileTransfer3Consumes_Folder() {
		return (EAttribute)fileTransfer3ConsumesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileTransfer3Consumes_BackupDirectory() {
		return (EAttribute)fileTransfer3ConsumesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileTransfer3Consumes_TransferMode() {
		return (EAttribute)fileTransfer3ConsumesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileTransfer3Consumes_Filename() {
		return (EAttribute)fileTransfer3ConsumesEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileTransfer3Consumes_PollingPeriod() {
		return (EAttribute)fileTransfer3ConsumesEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileTransfer3Consumes_BaseMessage() {
		return (EAttribute)fileTransfer3ConsumesEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileTransfer3Consumes_ProcessorPoolSize() {
		return (EAttribute)fileTransfer3ConsumesEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileTransfer3Consumes_ProcessorPoolTimeout() {
		return (EAttribute)fileTransfer3ConsumesEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Filetransfer3Factory getFiletransfer3Factory() {
		return (Filetransfer3Factory)getEFactoryInstance();
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
		fileTransfer3ProvidesEClass = createEClass(FILE_TRANSFER3_PROVIDES);
		createEAttribute(fileTransfer3ProvidesEClass, FILE_TRANSFER3_PROVIDES__FILENAME);
		createEAttribute(fileTransfer3ProvidesEClass, FILE_TRANSFER3_PROVIDES__BACKUP_DIRECTORY);
		createEAttribute(fileTransfer3ProvidesEClass, FILE_TRANSFER3_PROVIDES__FOLDER);

		fileTransfer3ConsumesEClass = createEClass(FILE_TRANSFER3_CONSUMES);
		createEAttribute(fileTransfer3ConsumesEClass, FILE_TRANSFER3_CONSUMES__FOLDER);
		createEAttribute(fileTransfer3ConsumesEClass, FILE_TRANSFER3_CONSUMES__BACKUP_DIRECTORY);
		createEAttribute(fileTransfer3ConsumesEClass, FILE_TRANSFER3_CONSUMES__TRANSFER_MODE);
		createEAttribute(fileTransfer3ConsumesEClass, FILE_TRANSFER3_CONSUMES__FILENAME);
		createEAttribute(fileTransfer3ConsumesEClass, FILE_TRANSFER3_CONSUMES__POLLING_PERIOD);
		createEAttribute(fileTransfer3ConsumesEClass, FILE_TRANSFER3_CONSUMES__BASE_MESSAGE);
		createEAttribute(fileTransfer3ConsumesEClass, FILE_TRANSFER3_CONSUMES__PROCESSOR_POOL_SIZE);
		createEAttribute(fileTransfer3ConsumesEClass, FILE_TRANSFER3_CONSUMES__PROCESSOR_POOL_TIMEOUT);
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
		Cdk5Package theCdk5Package = (Cdk5Package)EPackage.Registry.INSTANCE.getEPackage(Cdk5Package.eNS_URI);
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);
		JbiPackage theJbiPackage = (JbiPackage)EPackage.Registry.INSTANCE.getEPackage(JbiPackage.eNS_URI);
		Filetransfer2xPackage theFiletransfer2xPackage = (Filetransfer2xPackage)EPackage.Registry.INSTANCE.getEPackage(Filetransfer2xPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		fileTransfer3ProvidesEClass.getESuperTypes().add(theCdk5Package.getCDK5Provides());
		fileTransfer3ConsumesEClass.getESuperTypes().add(theCdk5Package.getCDK5Consumes());

		// Initialize classes and features; add operations and parameters
		initEClass(fileTransfer3ProvidesEClass, FileTransfer3Provides.class, "FileTransfer3Provides", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFileTransfer3Provides_Filename(), theXMLTypePackage.getString(), "filename", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileTransfer3Provides_BackupDirectory(), theXMLTypePackage.getString(), "backupDirectory", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileTransfer3Provides_Folder(), theXMLTypePackage.getString(), "folder", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fileTransfer3ConsumesEClass, FileTransfer3Consumes.class, "FileTransfer3Consumes", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFileTransfer3Consumes_Folder(), theXMLTypePackage.getString(), "folder", null, 1, 1, FileTransfer3Consumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileTransfer3Consumes_BackupDirectory(), theXMLTypePackage.getString(), "backupDirectory", null, 0, 1, FileTransfer3Consumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileTransfer3Consumes_TransferMode(), theFiletransfer2xPackage.getTransferMode(), "transferMode", "TransferMode.CONTENT", 0, 1, FileTransfer3Consumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileTransfer3Consumes_Filename(), theXMLTypePackage.getString(), "filename", null, 0, 1, FileTransfer3Consumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileTransfer3Consumes_PollingPeriod(), theXMLTypePackage.getInt(), "pollingPeriod", null, 0, 1, FileTransfer3Consumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileTransfer3Consumes_BaseMessage(), theXMLTypePackage.getString(), "baseMessage", null, 0, 1, FileTransfer3Consumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileTransfer3Consumes_ProcessorPoolSize(), theXMLTypePackage.getInt(), "processorPoolSize", null, 0, 1, FileTransfer3Consumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileTransfer3Consumes_ProcessorPoolTimeout(), theXMLTypePackage.getInt(), "processorPoolTimeout", null, 0, 1, FileTransfer3Consumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		  (fileTransfer3ProvidesEClass, 
		   source, 
		   new String[] {
			 "name", ""
		   });		
		addAnnotation
		  (getFileTransfer3Provides_Filename(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0",
			 "name", "filename"
		   });		
		addAnnotation
		  (getFileTransfer3Provides_BackupDirectory(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0",
			 "name", "backup-directory"
		   });		
		addAnnotation
		  (getFileTransfer3Provides_Folder(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0",
			 "name", "folder"
		   });		
		addAnnotation
		  (fileTransfer3ConsumesEClass, 
		   source, 
		   new String[] {
			 "name", ""
		   });		
		addAnnotation
		  (getFileTransfer3Consumes_Folder(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0",
			 "name", "folder"
		   });		
		addAnnotation
		  (getFileTransfer3Consumes_BackupDirectory(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0",
			 "name", "backup-directory"
		   });		
		addAnnotation
		  (getFileTransfer3Consumes_TransferMode(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0",
			 "name", "transfer-mode"
		   });		
		addAnnotation
		  (getFileTransfer3Consumes_Filename(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0",
			 "name", "filename"
		   });		
		addAnnotation
		  (getFileTransfer3Consumes_PollingPeriod(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0",
			 "name", "polling-period"
		   });		
		addAnnotation
		  (getFileTransfer3Consumes_BaseMessage(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0",
			 "name", "base-message"
		   });		
		addAnnotation
		  (getFileTransfer3Consumes_ProcessorPoolSize(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0",
			 "name", "processor-pool-size"
		   });		
		addAnnotation
		  (getFileTransfer3Consumes_ProcessorPoolTimeout(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "group", "#group:0",
			 "name", "processor-pool-timeout"
		   });
	}

} //Filetransfer3PackageImpl
