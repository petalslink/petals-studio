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

package com.ebmwebsourcing.petals.services.xslt.wizard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.extensions.SuWizardSettings;
import com.ebmwebsourcing.petals.services.su.wizards.ComponentCreationWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;
import com.ebmwebsourcing.petals.services.xslt.PetalsXsltPlugin;
import com.ebmwebsourcing.petals.services.xslt.XsltDescription23_24;
import com.ebmwebsourcing.petals.services.xslt.generated.XsltService24;
import com.ebmwebsourcing.petals.services.xslt.generated.XsltStyleSheet;
import com.ebmwebsourcing.petals.studio.services.xslt.xslt.XsltPackage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class XsltWizard24 extends ComponentCreationWizard {

	private boolean createXsl = true;
	private boolean createWSDL;
	private String xslUrl;
	public final static String DEFAULT_XSL_NAME = "transformation.xsl";


	public XsltWizard24() {
		super();
		getDialogSettings().put( SuWizardSettings.WSDL_SHOW, "false" );
		getDialogSettings().put( SuWizardSettings.ITF_NAME_ACTIVATE, "false" );
	}
	
	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new XsltDescription23_24();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.core.runtime.IProgressMonitor, java.util.List)
	 */
	@Override
	public IStatus performLastActions(IFolder resourceFolder, AbstractEndpoint abstractEndpoint, IProgressMonitor monitor) {

		// Generate a default XSL style sheet?
		if( createXsl ) {
			String content = new XsltStyleSheet().generate( null );
			IFile destination = resourceFolder.getFile( XsltWizard24.DEFAULT_XSL_NAME );
			createFile( destination, content, monitor );
			getNewlyCreatedEndpoint().eSet(XsltPackage.Literals.XSLT_PROVIDES__STYLESHEET, XsltWizard24.DEFAULT_XSL_NAME);
		} else /* import */ {
			try {
				importXSL(resourceFolder, monitor);
			} catch (Exception ex) {
				return new Status(IStatus.ERROR, PetalsXsltPlugin.PLUGIN_ID, ex.getMessage());
			}
		}

		// If no WSDL file has been set, set the default SendMail WSDL
		// FIXME
		if (createWSDL) {
			IFile wsdlFile = resourceFolder.getFile( "XsltService.wsdl" );
			createFile( wsdlFile, new XsltService24().generate( abstractEndpoint ), monitor );
		}

		return Status.OK_STATUS;
	}
	
	public void importXSL(IFolder resourceFolder, IProgressMonitor monitor)	throws MalformedURLException, CoreException, FileNotFoundException {
		File input;
		if (xslUrl.startsWith("file:")) {
			input = new File(new URL(xslUrl).getFile());
		} else {
			input = new File(xslUrl);
		}
		if (input.exists() && input.length() > 0) {
			IFile targetFile = resourceFolder.getFile(input.getName());
			if (targetFile.exists()) {
				targetFile = resourceFolder.getFile(input.getName().substring(0, (int) input.length() - ".xsd".length()) + System.currentTimeMillis() + ".xsd");
			}
			targetFile.create(new FileInputStream(input), false, monitor);
			getNewlyCreatedEndpoint().eSet(XsltPackage.Literals.XSLT_PROVIDES__STYLESHEET, targetFile.getName());
		} else {
			PetalsXsltPlugin.log("Provided file not existing or empty", IStatus.ERROR);
		}
	}


	@Override
	protected void presetServiceValues(AbstractEndpoint endpoint) {
	}


	@Override
	protected AbstractSuPage[] getCustomWizardPagesAfterJbi() {
		return null;
	}


	@Override
	protected AbstractSuPage[] getCustomWizardPagesAfterProject() {
		return new AbstractSuPage[] {
				new XsltProvideSpecificPage()
		};
	}


	@Override
	protected AbstractSuPage[] getCustomWizardPagesBeforeProject() {
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


	public boolean isCreateXsl() {
		return createXsl;
	}


	public void setCreateXsl(boolean createXsl) {
		this.createXsl = createXsl;
	}


	public boolean isCreateWSDL() {
		return createWSDL;
	}


	public void setCreateWSDL(boolean createWSDL) {
		this.createWSDL = createWSDL;
	}


	public String getXslUrl() {
		return xslUrl;
	}


	public void setXslUrl(String xslUrl) {
		this.xslUrl = xslUrl;
	}
}
