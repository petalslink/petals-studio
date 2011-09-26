/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.impl;

import com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.CopyMode;
import com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.FileTransferConsumes;
import com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.FileTransferExtension;
import com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.FileTransferFactory;
import com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.FileTransferPackage;
import com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.FileTransferProvides;
import com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.TransferMode;

import com.sun.java.xml.ns.jbi.JbiPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FileTransferPackageImpl extends EPackageImpl implements FileTransferPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fileTransferExtensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fileTransferProvidesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fileTransferConsumesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum copyModeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum transferModeEEnum = null;

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
	 * @see com.ebmwebsourcing.petals.jbi.filetransfer.model.filetransfer.FileTransferPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private FileTransferPackageImpl() {
		super(eNS_URI, FileTransferFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link FileTransferPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static FileTransferPackage init() {
		if (isInited) return (FileTransferPackage)EPackage.Registry.INSTANCE.getEPackage(FileTransferPackage.eNS_URI);

		// Obtain or create and register package
		FileTransferPackageImpl theFileTransferPackage = (FileTransferPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof FileTransferPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new FileTransferPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		JbiPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theFileTransferPackage.createPackageContents();

		// Initialize created meta-data
		theFileTransferPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theFileTransferPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(FileTransferPackage.eNS_URI, theFileTransferPackage);
		return theFileTransferPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFileTransferExtension() {
		return fileTransferExtensionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileTransferExtension_FileTransferExtContainer() {
		return (EAttribute)fileTransferExtensionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileTransferExtension_FilePattern() {
		return (EAttribute)fileTransferExtensionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileTransferExtension_ReadDirectory() {
		return (EAttribute)fileTransferExtensionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileTransferExtension_BackupDirectory() {
		return (EAttribute)fileTransferExtensionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFileTransferProvides() {
		return fileTransferProvidesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileTransferProvides_WriteDirectory() {
		return (EAttribute)fileTransferProvidesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileTransferProvides_CopyMode() {
		return (EAttribute)fileTransferProvidesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFileTransferConsumes() {
		return fileTransferConsumesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileTransferConsumes_TransferMode() {
		return (EAttribute)fileTransferConsumesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileTransferConsumes_PollingPeriod() {
		return (EAttribute)fileTransferConsumesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getCopyMode() {
		return copyModeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTransferMode() {
		return transferModeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FileTransferFactory getFileTransferFactory() {
		return (FileTransferFactory)getEFactoryInstance();
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
		fileTransferExtensionEClass = createEClass(FILE_TRANSFER_EXTENSION);
		createEAttribute(fileTransferExtensionEClass, FILE_TRANSFER_EXTENSION__FILE_TRANSFER_EXT_CONTAINER);
		createEAttribute(fileTransferExtensionEClass, FILE_TRANSFER_EXTENSION__FILE_PATTERN);
		createEAttribute(fileTransferExtensionEClass, FILE_TRANSFER_EXTENSION__READ_DIRECTORY);
		createEAttribute(fileTransferExtensionEClass, FILE_TRANSFER_EXTENSION__BACKUP_DIRECTORY);

		fileTransferProvidesEClass = createEClass(FILE_TRANSFER_PROVIDES);
		createEAttribute(fileTransferProvidesEClass, FILE_TRANSFER_PROVIDES__WRITE_DIRECTORY);
		createEAttribute(fileTransferProvidesEClass, FILE_TRANSFER_PROVIDES__COPY_MODE);

		fileTransferConsumesEClass = createEClass(FILE_TRANSFER_CONSUMES);
		createEAttribute(fileTransferConsumesEClass, FILE_TRANSFER_CONSUMES__TRANSFER_MODE);
		createEAttribute(fileTransferConsumesEClass, FILE_TRANSFER_CONSUMES__POLLING_PERIOD);

		// Create enums
		copyModeEEnum = createEEnum(COPY_MODE);
		transferModeEEnum = createEEnum(TRANSFER_MODE);
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
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		fileTransferExtensionEClass.getESuperTypes().add(theJbiPackage.getAbstractExtensibleElement());
		fileTransferProvidesEClass.getESuperTypes().add(theJbiPackage.getProvides());
		fileTransferConsumesEClass.getESuperTypes().add(theJbiPackage.getConsumes());

		// Initialize classes and features; add operations and parameters
		initEClass(fileTransferExtensionEClass, FileTransferExtension.class, "FileTransferExtension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFileTransferExtension_FileTransferExtContainer(), theEcorePackage.getEFeatureMapEntry(), "fileTransferExtContainer", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileTransferExtension_FilePattern(), ecorePackage.getEString(), "filePattern", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileTransferExtension_ReadDirectory(), ecorePackage.getEString(), "readDirectory", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileTransferExtension_BackupDirectory(), ecorePackage.getEString(), "backupDirectory", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(fileTransferProvidesEClass, FileTransferProvides.class, "FileTransferProvides", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFileTransferProvides_WriteDirectory(), ecorePackage.getEString(), "writeDirectory", null, 0, 1, FileTransferProvides.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileTransferProvides_CopyMode(), this.getCopyMode(), "copyMode", "CopyMode.CONTENT_ONLY", 0, 1, FileTransferProvides.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(fileTransferConsumesEClass, FileTransferConsumes.class, "FileTransferConsumes", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFileTransferConsumes_TransferMode(), this.getTransferMode(), "transferMode", "TransferMode.CONTENT", 0, 1, FileTransferConsumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileTransferConsumes_PollingPeriod(), ecorePackage.getEInt(), "pollingPeriod", null, 0, 1, FileTransferConsumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(copyModeEEnum, CopyMode.class, "CopyMode");
		addEEnumLiteral(copyModeEEnum, CopyMode.CONTENT_ONLY);
		addEEnumLiteral(copyModeEEnum, CopyMode.ATTACHMENTS_ONLY);
		addEEnumLiteral(copyModeEEnum, CopyMode.CONTENT_AND_ATTACHMENTS);

		initEEnum(transferModeEEnum, TransferMode.class, "TransferMode");
		addEEnumLiteral(transferModeEEnum, TransferMode.CONTENT);
		addEEnumLiteral(transferModeEEnum, TransferMode.ATTACHMENTS);

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
		  (fileTransferExtensionEClass, 
		   source, 
		   new String[] {
			 "name", ""
		   });		
		addAnnotation
		  (getFileTransferExtension_FileTransferExtContainer(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "wildcards", "http://petals.ow2.org/components/filetransfer/version-2",
			 "processing", "skip",
			 "name", "fileTransferExtContainer",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getFileTransferExtension_FilePattern(), 
		   source, 
		   new String[] {
			 "name", "file-pattern",
			 "kind", "element",
			 "namespace", "http://petals.ow2.org/components/filetransfer/version-2",
			 "group", "#fileTransferExtContainer"
		   });		
		addAnnotation
		  (getFileTransferExtension_ReadDirectory(), 
		   source, 
		   new String[] {
			 "name", "read-directory",
			 "kind", "element",
			 "namespace", "http://petals.ow2.org/components/filetransfer/version-2",
			 "group", "#fileTransferExtContainer"
		   });		
		addAnnotation
		  (getFileTransferExtension_BackupDirectory(), 
		   source, 
		   new String[] {
			 "name", "backup-directory",
			 "kind", "element",
			 "namespace", "http://petals.ow2.org/components/filetransfer/version-2",
			 "group", "#fileTransferExtContainer"
		   });		
		addAnnotation
		  (fileTransferProvidesEClass, 
		   source, 
		   new String[] {
			 "name", ""
		   });		
		addAnnotation
		  (getFileTransferProvides_WriteDirectory(), 
		   source, 
		   new String[] {
			 "name", "write-directory",
			 "kind", "element",
			 "namespace", "http://petals.ow2.org/components/filetransfer/version-2",
			 "group", "#fileTransferExtContainer"
		   });		
		addAnnotation
		  (getFileTransferProvides_CopyMode(), 
		   source, 
		   new String[] {
			 "name", "copy-mode",
			 "kind", "element",
			 "namespace", "http://petals.ow2.org/components/filetransfer/version-2",
			 "group", "#fileTransferExtContainer"
		   });		
		addAnnotation
		  (fileTransferConsumesEClass, 
		   source, 
		   new String[] {
			 "name", ""
		   });		
		addAnnotation
		  (getFileTransferConsumes_TransferMode(), 
		   source, 
		   new String[] {
			 "name", "transfer-mode",
			 "kind", "element",
			 "namespace", "http://petals.ow2.org/components/filetransfer/version-2"
		   });		
		addAnnotation
		  (getFileTransferConsumes_PollingPeriod(), 
		   source, 
		   new String[] {
			 "name", "polling-period",
			 "kind", "element",
			 "namespace", "http://petals.ow2.org/components/filetransfer/version-2",
			 "group", "#fileTransferExtContainer"
		   });
	}

} //FileTransferPackageImpl
