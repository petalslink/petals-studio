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

package com.ebmwebsourcing.petals.services.validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler;
import com.ebmwebsourcing.petals.services.su.extensions.SuWizardSettings;
import com.ebmwebsourcing.petals.services.validation.wizard.ValidationProvideSpecificPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ValidationWizard11 extends ComponentWizardHandler {

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
	public void predefineJbiValues( AbstractEndpoint ae ) {

		if( isServiceProvider()) {
			ae.setInterfaceName( new QName( "http://petals.ow2.org/components/validation/version-1", "ValidationInterface" ));
			ae.setServiceName( new QName( "http://petals.ow2.org/components/validation/version-1", "change-it" ));
		}
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #getOverridenWizardSettings()
	 */
	@Override
	public Map<String, String> getOverridenWizardSettings() {

		Map<String,String> result = new HashMap<String,String> ();
		result.put( SuWizardSettings.WSDL_SHOW, "false" );
		result.put( SuWizardSettings.ITF_NAME_ACTIVATE, "false" );

		return result;
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
		generateXmlSchema( resourceFolder, monitor );

		// If no WSDL file has been set, set the default SendMail WSDL
//		Object o = eclipseSuBean.customObjects.get( ValidationProvideSpecificPage.CREATE_WSDL );
//		if( StringUtils.isEmpty( eclipseSuBean.getWsdlUrl())
//				&& o instanceof Boolean
//				&& (Boolean) o) {
//			IFile wsdlFile = resourceFolder.getFile( "ValidationService.wsdl" );
//			createFile( wsdlFile, new ValidationService().generate( eclipseSuBean ), monitor );
//		}

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
	protected void generateXmlSchema(
			IFolder resourceFolder,
			IProgressMonitor monitor ) {

		// FIXME
		boolean createXsd = true; // (Boolean) eclipseSuBean.customObjects.get( ValidationProvideSpecificPage.CREATE_XSD );
		if( createXsd ) {

			String tns = "lol"; // (String) eclipseSuBean.customObjects.get( ValidationProvideSpecificPage.XSD_TNS );
			StringBuilder content = new StringBuilder();
			content.append( "<xs:schema\n" );
			content.append( "\ttargetNamespace=\"" + tns + "\"\n" );
			content.append( "\txmlns:ns=\"" + tns + "\"\n" );
			content.append( "\txmlns:xs=\"http://www.w3.org/2001/XMLSchema\"\n" );
			content.append( "\telementFormDefault=\"qualified\">\n\n" );
			content.append( "</xs:schema>\n" );

			IFile destination = resourceFolder.getFile( ValidationProvideSpecificPage.DEFAULT_XSD_NAME );
			createFile( destination, content.toString(), monitor );
		}
	}
}
