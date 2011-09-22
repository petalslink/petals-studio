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

package com.ebmwebsourcing.petals.services.filetransfer.v24;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;

import com.ebmwebsourcing.petals.services.filetransfer.generated.GetFilesWsdl24;
import com.ebmwebsourcing.petals.services.filetransfer.generated.WriteWsdl24;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;
import com.ebmwebsourcing.petals.services.su.wizards.generation.LastActionsPerformer;

/**
 * Perform actions after the project has been created.
 * @author Adrien Louis - EBM WebSourcing
 */
public class FiletransferLastActionsPerformer24 extends LastActionsPerformer {

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

		// Create the default WSDL
		if( ! eclipseSuBean.isConsume()) {

			String name = eclipseSuBean.getCreatedWsdlMarkupValue();
			eclipseSuBean.setWsdlUrl( name );
			IFile wsdlFile = resourceFolder.getFile( name );

			boolean writeContract = (Boolean) eclipseSuBean.customObjects.get( "write-contract" );
			if( writeContract )
				createFile( wsdlFile, new WriteWsdl24().generate( eclipseSuBean ), monitor );
			else
				createFile( wsdlFile, new GetFilesWsdl24().generate( eclipseSuBean ), monitor );
		}
	}
}
