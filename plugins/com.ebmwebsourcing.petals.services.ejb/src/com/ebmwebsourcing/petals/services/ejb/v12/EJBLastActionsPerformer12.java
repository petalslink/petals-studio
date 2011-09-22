/****************************************************************************
 *
 * Copyright (c) 2008-2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.ejb.v12;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

import com.ebmwebsourcing.petals.common.extensions.internal.provisional.WsdlExtUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.services.ejb.PetalsEjbPlugin;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;
import com.ebmwebsourcing.petals.services.su.wizards.generation.LastActionsPerformer;

/**
 * Perform actions after the project has been created.
 * @author Adrien Louis - EBM WebSourcing
 */
public class EJBLastActionsPerformer12 extends LastActionsPerformer {

	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.generation.LastActionsPerformer
	 * #performLastActions(org.eclipse.core.resources.IFolder,
	 * com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean,
	 * java.util.List, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void performLastActions(
			IFolder resourceFolder,
			EclipseSuBean suBean,
			List<Object> resourcesToSelect,
			IProgressMonitor monitor ) {

		List<String> paths = new ArrayList<String> ();
		List<IFile> jars = ResourceUtils.getFilesByRegexp( resourceFolder, ".*\\.(jar|zip)" );
		for( IFile f : jars )
			paths.add( f.getLocation().toString());

		for( String f : (String[]) suBean.customObjects.get( EJBCustomSpecificationPage12.JEE_LIBS ))
			paths.add( f );

		String[] classpath = new String[ paths.size()];
		classpath = paths.toArray( classpath );
		String outputPathAS = resourceFolder.getLocation().toString();
		String className = (String) suBean.customObjects.get( "ejbClassName" );
		String wsdlName = suBean.getCreatedWsdlMarkupValue();

		File f = WsdlExtUtils.generateWsdlFile(
					wsdlName,
					outputPathAS,
					className,
					classpath,
					outputPathAS,
					suBean.getEndpointName(),
					suBean.getServiceName(),
					monitor);

		if( ! f.exists()) {
			String msg = "The generation of the WSDL from the EJB interface failed.";
			PetalsEjbPlugin.log( msg, IStatus.ERROR );
		}
	}
}
