/******************************************************************************
 * Copyright (c) 2011, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.validation.wizard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.ComponentCreationWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.ebmwebsourcing.petals.services.validation.PetalsValidationPlugin;
import com.ebmwebsourcing.petals.services.validation.ValidationDescription11;
import com.ebmwebsourcing.petals.services.validation.generated.ValidationService;
import com.ebmwebsourcing.petals.services.validation.validation.ValidationPackage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ValidationProvidesWizard11 extends ComponentCreationWizard {
	
	boolean createXSD = true;
	String xsdTargetNamespace = "http://petalslink.ow2.org";
	String xsdURL;
	boolean createWSDL;
	String wsdlURL;

	public ValidationProvidesWizard11() {
		super();
		settings.showWsdl = false;
		settings.activateInterfaceName = false;
	}
	
	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new ValidationDescription11();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #predefineJbiValues(com.sun.java.xml.ns.jbi.AbstractEndpoint)
	 */
	@Override
	public void presetServiceValues( AbstractEndpoint ae ) {
		ae.setInterfaceName( new QName( "http://petals.ow2.org/components/validation/version-1", "ValidationInterface" ));
		ae.setServiceName( new QName( "http://petals.ow2.org/components/validation/version-1", "change-it" ));
		ae.eSet(Cdk5Package.Literals.CDK5_PROVIDES__WSDL, "ValidationService.wsdl");
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.core.runtime.IProgressMonitor, java.util.List)
	 */
	@Override
	public IStatus performLastActions(IFolder resourceFolder, AbstractEndpoint abstractEndpoint, IProgressMonitor monitor) {
		// Generate a default XSL style sheet?
		try {
			generateXmlSchema( resourceFolder, monitor );
		} catch (Exception ex) {
			return new Status(IStatus.ERROR, PetalsValidationPlugin.PLUGIN_ID, ex.getMessage());
		}

		// If no WSDL file has been set, set the default SendMail WSDL
		if (createWSDL) {
			IFile wsdlFile = resourceFolder.getFile( "ValidationService.wsdl" );
			createFile( wsdlFile, new ValidationService().generate(getNewlyCreatedEndpoint()), monitor );
		}

		return Status.OK_STATUS;
	}


	/**
	 * Checks whether a default XML schema sheet must be created, and if so, creates it.
	 * <p>
	 * If no XML schema has to be generated, this method does nothing.
	 * </p>
	 *
	 * @param resourceFolder the directory holding the SU resources
	 * @param eclipseSuBean the Eclipse SU bean
	 * @param monitor the progress monitor
	 */
	protected void generateXmlSchema(IFolder resourceFolder, IProgressMonitor monitor ) throws Exception {

		if( createXSD ) {
			createDefaultXSD(resourceFolder, monitor);
		} else /* import */ {
			importXSD(resourceFolder, monitor);
		}
	}

	public void importXSD(IFolder resourceFolder, IProgressMonitor monitor)	throws MalformedURLException, CoreException, FileNotFoundException {
		File input;
		if (xsdURL.startsWith("file:")) {
			input = new File(new URL(xsdURL).getFile());
		} else {
			input = new File(xsdURL);
		}
		if (input.exists() && input.length() > 0) {
			IFile targetFile = resourceFolder.getFile(input.getName());
			if (targetFile.exists()) {
				targetFile = resourceFolder.getFile(input.getName().substring(0, (int) input.length() - ".xsd".length()) + System.currentTimeMillis() + ".xsd");
			}
			targetFile.create(new FileInputStream(input), false, monitor);
			getNewlyCreatedEndpoint().eSet(ValidationPackage.Literals.VALIDATION_PROVIDES__SCHEMA, targetFile.getName());
		} else {
			PetalsValidationPlugin.log("Provided file not existing or empty", IStatus.ERROR);
		}
	}

	public void createDefaultXSD(IFolder resourceFolder, IProgressMonitor monitor) {
		StringBuilder content = new StringBuilder();
		content.append( "<xs:schema\n" );
		content.append( "\ttargetNamespace=\"" + xsdTargetNamespace + "\"\n" );
		content.append( "\txmlns:ns=\"" + xsdTargetNamespace + "\"\n" );
		content.append( "\txmlns:xs=\"http://www.w3.org/2001/XMLSchema\"\n" );
		content.append( "\telementFormDefault=\"qualified\">\n\n" );
		content.append( "</xs:schema>\n" );

		IFile destination = resourceFolder.getFile( "default.xsd" );
		createFile( destination, content.toString(), monitor );
		getNewlyCreatedEndpoint().eSet(ValidationPackage.Literals.VALIDATION_PROVIDES__SCHEMA, "default.xsd");
	}

	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesAfterJbi() {
		return new AbstractSuWizardPage[] {
			new ValidationProvideSpecificPage()		
		};
	}

	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesAfterProject() {
		return null;
	}

	@Override
	protected AbstractSuWizardPage[] getCustomWizardPagesBeforeProject() {
		return null;
	}

	@Override
	protected IStatus importAdditionalFiles(IFolder resourceDirectory, IProgressMonitor monitor) {
		return Status.OK_STATUS;
	}

	@Override
	protected boolean isJavaProject() {
		return false;
	}

	public boolean isCreateXSD() {
		return createXSD;
	}

	public void setCreateXSD(boolean createXSD) {
		this.createXSD = createXSD;
	}

	public String getXsdTargetNamespace() {
		return xsdTargetNamespace;
	}

	public void setXsdTargetNamespace(String xsdTargetNamespace) {
		this.xsdTargetNamespace = xsdTargetNamespace;
	}

	public boolean isCreateWSDL() {
		return createWSDL;
	}

	public void setCreateWSDL(boolean createWSDL) {
		this.createWSDL = createWSDL;
	}

	public String getWsdlURL() {
		return wsdlURL;
	}

	public void setWsdlURL(String wsdlURL) {
		this.wsdlURL = wsdlURL;
	}

	public String getXsdURL() {
		return xsdURL;
	}

	public void setXsdURL(String xsdURL) {
		this.xsdURL = xsdURL;
	}
}
