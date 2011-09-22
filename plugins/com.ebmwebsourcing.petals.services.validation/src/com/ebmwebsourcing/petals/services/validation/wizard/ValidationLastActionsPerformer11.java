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

package com.ebmwebsourcing.petals.services.validation.wizard;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;
import com.ebmwebsourcing.petals.services.su.wizards.generation.LastActionsPerformer;
import com.ebmwebsourcing.petals.services.validation.generated.ValidationService;

/**
 * Perform actions after the project has been created.
 * @author Adrien Louis - EBM WebSourcing
 */
public class ValidationLastActionsPerformer11 extends LastActionsPerformer {

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
		generateXmlSchema( resourceFolder, eclipseSuBean, monitor );

		// If no WSDL file has been set, set the default SendMail WSDL
		Object o = eclipseSuBean.customObjects.get( ValidationProvideSpecificPage.CREATE_WSDL );
		if( StringUtils.isEmpty( eclipseSuBean.getWsdlUrl())
					&& o instanceof Boolean
					&& (Boolean) o) {
			IFile wsdlFile = resourceFolder.getFile( "ValidationService.wsdl" );
			createFile( wsdlFile, new ValidationService().generate( eclipseSuBean ), monitor );
		}
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
				EclipseSuBean eclipseSuBean,
				IProgressMonitor monitor ) {

		boolean createXsd = (Boolean) eclipseSuBean.customObjects.get( ValidationProvideSpecificPage.CREATE_XSD );
		if( createXsd ) {

			String tns = (String) eclipseSuBean.customObjects.get( ValidationProvideSpecificPage.XSD_TNS );
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
