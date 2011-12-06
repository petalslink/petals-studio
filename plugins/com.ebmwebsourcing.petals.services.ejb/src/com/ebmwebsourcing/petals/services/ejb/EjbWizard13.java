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

package com.ebmwebsourcing.petals.services.ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler;
import com.ebmwebsourcing.petals.services.su.extensions.SuWizardSettings;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EjbWizard13 extends ComponentWizardHandler {

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new EjbDescription13();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #getOverridenWizardSettings()
	 */
	@Override
	public Map<String, String> getOverridenWizardSettings() {

		Map<String,String> result = new HashMap<String,String> ();
		result.put( SuWizardSettings.SHOW_JBI_PAGE, "false" );

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

//		List<String> paths = new ArrayList<String> ();
//		List<IFile> jars = ResourceUtils.getFilesByRegexp( resourceFolder, ".*\\.(jar|zip)" );
//		for( IFile f : jars )
//			paths.add( f.getLocation().toString());
//
//		for( String f : (String[]) suBean.customObjects.get( EJBCustomSpecificationPage12.JEE_LIBS ))
//			paths.add( f );
//
//		String[] classpath = new String[ paths.size()];
//		classpath = paths.toArray( classpath );
//		String outputPathAS = resourceFolder.getLocation().toString();
//		String className = (String) suBean.customObjects.get( "ejbClassName" );
//		String wsdlName = suBean.getCreatedWsdlMarkupValue();
//
//		File f = WsdlExtUtils.generateWsdlFile(
//					wsdlName,
//					outputPathAS,
//					className,
//					classpath,
//					outputPathAS,
//					suBean.getEndpointName(),
//					suBean.getServiceName(),
//					monitor);
//
//		if( ! f.exists()) {
//			String msg = "The generation of the WSDL from the EJB interface failed.";
//			PetalsEjbPlugin.log( msg, IStatus.ERROR );
//		}

		return Status.OK_STATUS;
	}
}
