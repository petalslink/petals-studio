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

package com.ebmwebsourcing.petals.services.filetransfer.v24.wizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.services.filetransfer.v24.FileTransferDescription24;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler;
import com.ebmwebsourcing.petals.services.su.extensions.SuWizardSettings;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class FileTransferWizard24 extends ComponentWizardHandler {

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new FileTransferDescription24();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #predefineJbiValues(com.sun.java.xml.ns.jbi.AbstractEndpoint)
	 */
	@Override
	public void predefineJbiValues( AbstractEndpoint ae ) {

		if( isServiceProvider()) {
			ae.setInterfaceName( new QName( "http://petals.ow2.org/components/filetransfer/version-2", "Abstract (will be in the next pages)" ));
			ae.setServiceName( new QName( "http://petals.ow2.org/components/filetransfer/version-2", "change-it" ));
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

		if( isServiceProvider()) {

//			String name = eclipseSuBean.getCreatedWsdlMarkupValue();
//			eclipseSuBean.setWsdlUrl( name );
//			IFile wsdlFile = resourceFolder.getFile( name );
//
//			boolean writeContract = (Boolean) eclipseSuBean.customObjects.get( "write-contract" );
//			if( writeContract )
//				createFile( wsdlFile, new WriteWsdl24().generate( abstractEndpoint ), monitor );
//			else
//				createFile( wsdlFile, new GetFilesWsdl24().generate( abstractEndpoint ), monitor );
		}

		return Status.OK_STATUS;
	}


	@Override
	public List<AbstractSuPage> getCustomWizardPages(CustomPagePosition position) {
		List<AbstractSuPage> pages = new ArrayList<AbstractSuPage>();
		if (position == CustomPagePosition.afterProjectPage) {
			if (isServiceProvider()) {
				pages.add(new FiletransferProvidesPage());
			} else {
				pages.add(new FiletransferConsumesPage());
			}
		}
		return pages;
	}
}
