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

package com.ebmwebsourcing.petals.services.sftp;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;

import com.ebmwebsourcing.petals.services.sftp.generated.SftpService11;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;
import com.ebmwebsourcing.petals.services.su.wizards.generation.LastActionsPerformer;

/**
 * Perform actions after the project has been created.
 * @author Adrien Louis - EBM WebSourcing
 */
public class SFTPLastActionsPerformer11 extends LastActionsPerformer {

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

		// Default WSDL
		if( !eclipseSuBean.isConsume() &&
					( eclipseSuBean.getWsdlUrl() == null || eclipseSuBean.getWsdlUrl().length() == 0 )) {

			IFile wsdlFile = resourceFolder.getFile( "SftpService.wsdl" );
			createFile( wsdlFile, new SftpService11().generate( eclipseSuBean ), monitor );
		}
	}
}
