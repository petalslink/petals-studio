/****************************************************************************
 *
 * Copyright (c) 2009-2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.xslt.wizard;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;
import com.ebmwebsourcing.petals.services.su.wizards.generation.LastActionsPerformer;
import com.ebmwebsourcing.petals.services.xslt.generated.XsltService23;
import com.ebmwebsourcing.petals.services.xslt.generated.XsltStyleSheet;

/**
 * Perform actions after the project has been created.
 * @author Adrien Louis - EBM WebSourcing
 */
public class XsltLastActionsPerformer23 extends LastActionsPerformer {

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

		// Generate a default XSL style sheet?
		generateXslStyleSheet( resourceFolder, eclipseSuBean, monitor );

		// If no WSDL file has been set, set the default SendMail WSDL
		Object o = eclipseSuBean.customObjects.get( XsltProvideSpecificPage.CREATE_WSDL );
		if( StringUtils.isEmpty( eclipseSuBean.getWsdlUrl())
					&& o instanceof Boolean
					&& (Boolean) o) {
			IFile wsdlFile = resourceFolder.getFile( "XsltService.wsdl" );
			createFile( wsdlFile, new XsltService23().generate( eclipseSuBean ), monitor );
		}
	}


	/**
	 * Checks whether a default XSL style sheet must be created, and if so, creates it.
	 * <p>
	 * If no XSL style sheet has to be generated, this method does nothing.
	 * </p>
	 *
	 * @param resourceFolder the directory holding the SU resources
	 * @param eclipseSuBean the Eclipse SU bean
	 * @param monitor the progress monitor
	 */
	protected void generateXslStyleSheet(
				IFolder resourceFolder,
				EclipseSuBean eclipseSuBean,
				IProgressMonitor monitor ) {

		boolean createXsl = (Boolean) eclipseSuBean.customObjects.get( XsltProvideSpecificPage.CREATE_XSL );
		if( createXsl ) {
			String content = new XsltStyleSheet().generate( null );
			IFile destination = resourceFolder.getFile( XsltProvideSpecificPage.DEFAULT_XSL_NAME );
			createFile( destination, content, monitor );
		}
	}
}
