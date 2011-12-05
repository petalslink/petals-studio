/****************************************************************************
 *
 * Copyright (c) 2010-2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.rest;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;

import com.ebmwebsourcing.petals.services.rest.generated.GenericRestWsdl;
import com.ebmwebsourcing.petals.services.su.extensions.LastActionsPerformer;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;

/**
 * Perform actions after the project has been created.
 * @author Adrien Louis - EBM WebSourcing
 */
public class RestLastActionsPerformer40 extends LastActionsPerformer {

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
			EclipseSuBean eclipseSuBean,
			List<Object> resourcesToSelect,
			IProgressMonitor monitor ) {

		// No WSDL file => generate one
		if( ! eclipseSuBean.isConsume() &&
					( eclipseSuBean.getWsdlUrl() == null || eclipseSuBean.getWsdlUrl().length() == 0 )) {

			IFile wsdlFile = resourceFolder.getFile( "RestService.wsdl" );
			createFile( wsdlFile, new GenericRestWsdl().generate( eclipseSuBean ), monitor );
		}
	}
}
