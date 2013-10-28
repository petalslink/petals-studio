/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.xslt.wizard;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import javax.xml.namespace.QName;

import org.eclipse.bpel.common.wsdl.helpers.UriAndUrlHelper;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.services.cdk.Cdk5Utils;
import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.ebmwebsourcing.petals.services.xslt.PetalsXsltPlugin;
import com.ebmwebsourcing.petals.services.xslt.XsltDescription23;
import com.ebmwebsourcing.petals.services.xslt.generated.XsltService23;
import com.ebmwebsourcing.petals.services.xslt.generated.XsltStyleSheet;
import com.ebmwebsourcing.petals.studio.services.xslt.xslt.XsltPackage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class XsltWizard23 extends AbstractServiceUnitWizard {

	private static final String XSL_EXT = ".xsl";
	private static final String DEFAULT_XSL_NAME = "transformation.xsl";
	protected final XsltProvideSpecificPage page;


	/**
	 * Constructor.
	 */
	public XsltWizard23() {
		super();
		this.settings.showWsdl = false;
		this.settings.activateInterfaceName = false;
		this.settings.activateServiceNameOnly = true;
		this.page = new XsltProvideSpecificPage();
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new XsltDescription23();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.core.runtime.IProgressMonitor, java.util.List)
	 */
	@Override
	public IStatus performLastActions(IFolder resourceFolder, AbstractEndpoint abstractEndpoint, IProgressMonitor monitor) {

		// Generate a default XSL style sheet?
		if( this.page.isCreateXsltFile()) {
			String content = new XsltStyleSheet().generate( null );
			IFile destination = resourceFolder.getFile( DEFAULT_XSL_NAME );
			createFile( destination, content, monitor );
			getNewlyCreatedEndpoint().eSet(XsltPackage.Literals.XSLT_PROVIDES__STYLESHEET, DEFAULT_XSL_NAME );

		} else {
			try {
				importXSL(resourceFolder, monitor);
			} catch (Exception ex) {
				return new Status(IStatus.ERROR, PetalsXsltPlugin.PLUGIN_ID, ex.getMessage());
			}
		}

		// Create a WSDL?
		if( this.page.isCreateWsdlFile()) {
			abstractEndpoint.eSet( Cdk5Package.Literals.CDK5_PROVIDES__WSDL, "XsltService.wsdl" );
			IFile wsdlFile = resourceFolder.getFile( "XsltService.wsdl" );
			String wsdlContent = getWsdlContent( abstractEndpoint );
			createFile( wsdlFile, wsdlContent, monitor );
		}

		return Status.OK_STATUS;
	}


	/**
	 * @param abstractEndpoint
	 * @return the content for the WSDL to generate
	 */
	protected String getWsdlContent( AbstractEndpoint abstractEndpoint ) {
		return new XsltService23().generate( abstractEndpoint );
	}


	/**
	 * @param resourceFolder
	 * @param monitor
	 * @throws MalformedURLException
	 * @throws CoreException
	 * @throws FileNotFoundException
	 */
	public void importXSL(IFolder resourceFolder, IProgressMonitor monitor)
	throws MalformedURLException, CoreException, FileNotFoundException {

		String name = UriAndUrlHelper.extractOrGenerateFileName( this.page.getXslUrl());
		if( ! name.endsWith( XSL_EXT ))
			name += XSL_EXT;

		InputStream is = null;
		try {
			is = UriAndUrlHelper.urlToUri( this.page.getXslUrl()).toURL().openStream();
			IFile targetFile = resourceFolder.getFile( name );

			targetFile.create( is, true, monitor );
			getNewlyCreatedEndpoint().eSet(XsltPackage.Literals.XSLT_PROVIDES__STYLESHEET, name );

		} catch( IOException e ) {
			PetalsXsltPlugin.log( e, IStatus.ERROR );

		} finally {
			if( is != null ) {
				try {
					is.close();
				} catch( IOException e ) {
					PetalsXsltPlugin.log( e, IStatus.WARNING );
				}
			}
		}
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #presetServiceValues(com.sun.java.xml.ns.jbi.AbstractEndpoint)
	 */
	@Override
	protected void presetServiceValues(AbstractEndpoint endpoint) {
		endpoint.setInterfaceName( new QName( "http://petals.ow2.org/components/xslt/version-2", "XsltService" ));
		endpoint.setServiceName( new QName( "http://petals.ow2.org/components/xslt/version-2", "change-it" ));
		Cdk5Utils.setInitialProvidesValues((Provides)endpoint);
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getLastCustomWizardPages()
	 */
	@Override
	protected AbstractSuWizardPage[] getLastCustomWizardPages() {
		return new AbstractSuWizardPage[] { this.page };
	}
}
