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

package com.ebmwebsourcing.petals.services.xslt;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler;
import com.ebmwebsourcing.petals.services.xslt.generated.XsltStyleSheet;
import com.ebmwebsourcing.petals.services.xslt.wizard.XsltProvideSpecificPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class XsltWizard24 extends ComponentWizardHandler {

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new XsltDescription24();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.core.runtime.IProgressMonitor, java.util.List)
	 */
	@Override
	public IStatus performLastActions(
			IFolder resourceFolder, AbstractEndpoint abstractEndpoint,
			IProgressMonitor monitor, List<Object> resourcesToSelect ) {

		// Generate a default XSL style sheet?
		// FIXME
		boolean createXsl = true; // (Boolean) eclipseSuBean.customObjects.get( XsltProvideSpecificPage.CREATE_XSL );
		if( createXsl ) {
			String content = new XsltStyleSheet().generate( null );
			IFile destination = resourceFolder.getFile( XsltProvideSpecificPage.DEFAULT_XSL_NAME );
			createFile( destination, content, monitor );
		}

		// If no WSDL file has been set, set the default SendMail WSDL
		// FIXME
		Object o = true; // eclipseSuBean.customObjects.get( XsltProvideSpecificPage.CREATE_WSDL );
//		if( StringUtils.isEmpty( eclipseSuBean.getWsdlUrl())
//				&& o instanceof Boolean
//				&& (Boolean) o) {
//			IFile wsdlFile = resourceFolder.getFile( "XsltService.wsdl" );
//			createFile( wsdlFile, new XsltService24().generate( abstractEndpoint ), monitor );
//		}

		return Status.OK_STATUS;
	}
}
