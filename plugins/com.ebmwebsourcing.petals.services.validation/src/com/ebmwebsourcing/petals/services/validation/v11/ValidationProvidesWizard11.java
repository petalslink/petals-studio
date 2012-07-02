/******************************************************************************
 * Copyright (c) 2011-2012, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.validation.v11;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.bpel.common.wsdl.importhelpers.WsdlImportHelper;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.xml.sax.SAXException;

import com.ebmwebsourcing.petals.services.cdk.Cdk5Utils;
import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.ebmwebsourcing.petals.services.validation.PetalsValidationPlugin;
import com.ebmwebsourcing.petals.services.validation.generated.ValidationService;
import com.ebmwebsourcing.petals.services.validation.validation.ValidationPackage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ValidationProvidesWizard11 extends AbstractServiceUnitWizard {

	private final ValidationProvideSpecificPage page;


	/**
	 * Constructor.
	 */
	public ValidationProvidesWizard11() {
		super();
		this.settings.showWsdl = false;
		this.settings.activateInterfaceName = false;
		this.settings.activateServiceNameOnly = true;
		this.page = new ValidationProvideSpecificPage();
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new ValidationDescription11();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #predefineJbiValues(com.sun.java.xml.ns.jbi.AbstractEndpoint)
	 */
	@Override
	public void presetServiceValues( AbstractEndpoint ae) {
		ae.setInterfaceName( new QName( "http://petals.ow2.org/components/validation/version-1", "ValidationInterface" ));
		ae.setServiceName( new QName( "http://petals.ow2.org/components/validation/version-1", "change-it" ));
		Cdk5Utils.setInitialProvidesValues((Provides)ae);
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.core.runtime.IProgressMonitor, java.util.List)
	 */
	@Override
	public IStatus performLastActions(IFolder resourceFolder, AbstractEndpoint abstractEndpoint, IProgressMonitor monitor) {

		// Generate a default XSL schema?
		try {
			if( this.page.isCreateXsd())
				createDefaultXSD(resourceFolder, monitor);
			else
				importXSD(resourceFolder, monitor);

		} catch (Exception ex) {
			return new Status(IStatus.ERROR, PetalsValidationPlugin.PLUGIN_ID, ex.getMessage());
		}

		// Create a WSDL?
		if( this.page.isCreateWsdl()) {
			abstractEndpoint.eSet( Cdk5Package.Literals.CDK5_PROVIDES__WSDL, "ValidationService.wsdl" );
			IFile wsdlFile = resourceFolder.getFile( "ValidationService.wsdl" );
			createFile( wsdlFile, new ValidationService().generate(getNewlyCreatedEndpoint()), monitor );
		}

		return Status.OK_STATUS;
	}


	/**
	 * @param resourceFolder
	 * @param monitor
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	private void importXSD(IFolder resourceFolder, IProgressMonitor monitor)
	throws IllegalArgumentException, IOException, URISyntaxException, SAXException, ParserConfigurationException {

		Map<String,File> map = new WsdlImportHelper().importWsdlOrXsdAndDependencies(
				resourceFolder.getLocation().toFile(),
				this.page.getXsdURL());

		File f = map.get( this.page.getXsdURL());
		if( f == null )
			PetalsValidationPlugin.log( "The XML schema could not be imported: " + this.page.getXsdURL(), IStatus.ERROR );
		else
			getNewlyCreatedEndpoint().eSet(ValidationPackage.Literals.VALIDATION_PROVIDES__SCHEMA, f.getName());
	}


	/**
	 * @param resourceFolder
	 * @param monitor
	 */
	private void createDefaultXSD(IFolder resourceFolder, IProgressMonitor monitor) {
		StringBuilder content = new StringBuilder();
		content.append( "<xs:schema\n" );
		content.append( "\ttargetNamespace=\"" + this.page.getTargetNamespace() + "\"\n" );
		content.append( "\txmlns:ns=\"" + this.page.getTargetNamespace() + "\"\n" );
		content.append( "\txmlns:xs=\"http://www.w3.org/2001/XMLSchema\"\n" );
		content.append( "\telementFormDefault=\"qualified\">\n\n" );
		content.append( "</xs:schema>\n" );

		IFile destination = resourceFolder.getFile( "default.xsd" );
		createFile( destination, content.toString(), monitor );
		getNewlyCreatedEndpoint().eSet(ValidationPackage.Literals.VALIDATION_PROVIDES__SCHEMA, "default.xsd");
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getCustomWizardPagesAfterJbi()
	 */
	@Override
	protected AbstractSuWizardPage[] getLastCustomWizardPages() {
		return new AbstractSuWizardPage[] { this.page };
	}
}
