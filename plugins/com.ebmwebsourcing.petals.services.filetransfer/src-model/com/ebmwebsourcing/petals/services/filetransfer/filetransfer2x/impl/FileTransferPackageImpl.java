/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.CopyMode;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferConsumes;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferFactory;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferPackage;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferProvides;
import com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.TransferMode;
import com.sun.java.xml.ns.jbi.JbiPackage;

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
	 * @see com.ebmwebsourcing.petals.services.filetransfer.filetransfer2x.FileTransferPackage#eNS_URI
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
	@Override
	public EClass getFileTransferProvides() {
		return this.fileTransferProvidesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFileTransferProvides_WriteDirectory() {
		return (EAttribute)this.fileTransferProvidesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFileTransferProvides_CopyMode() {
		return (EAttribute)this.fileTransferProvidesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFileTransferProvides_FilePattern() {
		return (EAttribute)this.fileTransferProvidesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFileTransferProvides_ReadDirectory() {
		return (EAttribute)this.fileTransferProvidesEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFileTransferProvides_BackupDirectory() {
		return (EAttribute)this.fileTransferProvidesEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFileTransferConsumes() {
		return this.fileTransferConsumesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFileTransferConsumes_ReadDirectory() {
		return (EAttribute)this.fileTransferConsumesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFileTransferConsumes_BackupDirectory() {
		return (EAttribute)this.fileTransferConsumesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFileTransferConsumes_TransferMode() {
		return (EAttribute)this.fileTransferConsumesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFileTransferConsumes_FilePattern() {
		return (EAttribute)this.fileTransferConsumesEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFileTransferConsumes_PollingPeriod() {
		return (EAttribute)this.fileTransferConsumesEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getCopyMode() {
		return this.copyModeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getTransferMode() {
		return this.transferModeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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
		if (this.isCreated) return;
		this.isCreated = true;

		// Create classes and their features
		this.fileTransferProvidesEClass = createEClass(FILE_TRANSFER_PROVIDES);
		createEAttribute(this.fileTransferProvidesEClass, FILE_TRANSFER_PROVIDES__WRITE_DIRECTORY);
		createEAttribute(this.fileTransferProvidesEClass, FILE_TRANSFER_PROVIDES__COPY_MODE);
		createEAttribute(this.fileTransferProvidesEClass, FILE_TRANSFER_PROVIDES__FILE_PATTERN);
		createEAttribute(this.fileTransferProvidesEClass, FILE_TRANSFER_PROVIDES__READ_DIRECTORY);
		createEAttribute(this.fileTransferProvidesEClass, FILE_TRANSFER_PROVIDES__BACKUP_DIRECTORY);

		this.fileTransferConsumesEClass = createEClass(FILE_TRANSFER_CONSUMES);
		createEAttribute(this.fileTransferConsumesEClass, FILE_TRANSFER_CONSUMES__READ_DIRECTORY);
		createEAttribute(this.fileTransferConsumesEClass, FILE_TRANSFER_CONSUMES__BACKUP_DIRECTORY);
		createEAttribute(this.fileTransferConsumesEClass, FILE_TRANSFER_CONSUMES__TRANSFER_MODE);
		createEAttribute(this.fileTransferConsumesEClass, FILE_TRANSFER_CONSUMES__FILE_PATTERN);
		createEAttribute(this.fileTransferConsumesEClass, FILE_TRANSFER_CONSUMES__POLLING_PERIOD);

		// Create enums
		this.copyModeEEnum = createEEnum(COPY_MODE);
		this.transferModeEEnum = createEEnum(TRANSFER_MODE);
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
		if (this.isInitialized) return;
		this.isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		JbiPackage theJbiPackage = (JbiPackage)EPackage.Registry.INSTANCE.getEPackage(JbiPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		this.fileTransferProvidesEClass.getESuperTypes().add(theJbiPackage.getProvides());
		this.fileTransferConsumesEClass.getESuperTypes().add(theJbiPackage.getConsumes());

		// Initialize classes and features; add operations and parameters
		initEClass(this.fileTransferProvidesEClass, FileTransferProvides.class, "FileTransferProvides", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFileTransferProvides_WriteDirectory(), this.ecorePackage.getEString(), "writeDirectory", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileTransferProvides_CopyMode(), this.getCopyMode(), "copyMode", "CopyMode.CONTENT_ONLY", 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileTransferProvides_FilePattern(), this.ecorePackage.getEString(), "filePattern", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileTransferProvides_ReadDirectory(), this.ecorePackage.getEString(), "readDirectory", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileTransferProvides_BackupDirectory(), this.ecorePackage.getEString(), "backupDirectory", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(this.fileTransferConsumesEClass, FileTransferConsumes.class, "FileTransferConsumes", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFileTransferConsumes_ReadDirectory(), this.ecorePackage.getEString(), "readDirectory", null, 1, 1, FileTransferConsumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileTransferConsumes_BackupDirectory(), this.ecorePackage.getEString(), "backupDirectory", null, 0, 1, FileTransferConsumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileTransferConsumes_TransferMode(), this.getTransferMode(), "transferMode", "TransferMode.CONTENT", 0, 1, FileTransferConsumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileTransferConsumes_FilePattern(), this.ecorePackage.getEString(), "filePattern", null, 0, 1, FileTransferConsumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getFileTransferConsumes_PollingPeriod(), this.ecorePackage.getEInt(), "pollingPeriod", null, 0, 1, FileTransferConsumes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(this.copyModeEEnum, CopyMode.class, "CopyMode");
		addEEnumLiteral(this.copyModeEEnum, CopyMode.CONTENT_ONLY);
		addEEnumLiteral(this.copyModeEEnum, CopyMode.ATTACHMENTS_ONLY);
		addEEnumLiteral(this.copyModeEEnum, CopyMode.CONTENT_AND_ATTACHMENTS);

		initEEnum(this.transferModeEEnum, TransferMode.class, "TransferMode");
		addEEnumLiteral(this.transferModeEEnum, TransferMode.CONTENT);
		addEEnumLiteral(this.transferModeEEnum, TransferMode.ATTACHMENT);

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
		  (this.fileTransferProvidesEClass,
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
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });
		addAnnotation
		  (getFileTransferProvides_CopyMode(),
		   source,
		   new String[] {
			 "name", "copy-mode",
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });
		addAnnotation
		  (getFileTransferProvides_FilePattern(),
		   source,
		   new String[] {
			 "name", "file-pattern",
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });
		addAnnotation
		  (getFileTransferProvides_ReadDirectory(),
		   source,
		   new String[] {
			 "name", "read-directory",
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });
		addAnnotation
		  (getFileTransferProvides_BackupDirectory(),
		   source,
		   new String[] {
			 "name", "backup-directory",
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });
		addAnnotation
		  (this.fileTransferConsumesEClass,
		   source,
		   new String[] {
			 "name", ""
		   });
		addAnnotation
		  (getFileTransferConsumes_ReadDirectory(),
		   source,
		   new String[] {
			 "name", "read-directory",
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });
		addAnnotation
		  (getFileTransferConsumes_BackupDirectory(),
		   source,
		   new String[] {
			 "name", "backup-directory",
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });
		addAnnotation
		  (getFileTransferConsumes_TransferMode(),
		   source,
		   new String[] {
			 "name", "transfer-mode",
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });
		addAnnotation
		  (getFileTransferConsumes_FilePattern(),
		   source,
		   new String[] {
			 "name", "file-pattern",
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });
		addAnnotation
		  (getFileTransferConsumes_PollingPeriod(),
		   source,
		   new String[] {
			 "name", "polling-period",
			 "kind", "element",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });
	}

} //FileTransferPackageImpl
