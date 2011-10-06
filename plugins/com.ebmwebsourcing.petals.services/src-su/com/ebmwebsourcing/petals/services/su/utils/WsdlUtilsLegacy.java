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

package com.ebmwebsourcing.petals.services.su.utils;

import java.util.List;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;

import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.XmlElement;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils;
import com.ebmwebsourcing.petals.services.su.wizards.ErrorReporter;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class WsdlUtilsLegacy {

	/**
	 * Update the value of the XSD-generated WSDL item with the URL retrieved in the JBI page.
	 * @param eclipseSuBean
	 * @param fileImportManager
	 */
	public static void addHookForWsdlUrl( EclipseSuBean eclipseSuBean, FileImportManager fileImportManager ) {

		if( eclipseSuBean.getWsdlUrl() == null || eclipseSuBean.getWsdlUrl().length() == 0 )
			return;

		List<XmlElement> wsdlElements =
			XsdUtils.findXmlElement( "petalsCDK:wsdl", eclipseSuBean.cdkElements );

		// One (provide) or zero (consume) element expected in the list.
		for( XmlElement xmlElement : wsdlElements ) {
			fileImportManager.registerXmlFileElement( xmlElement, eclipseSuBean.getWsdlUrl(), "" );
			xmlElement.setValue( eclipseSuBean.getWsdlUrl());
		}
	}


	/**
	 * Update the value WSDL item that will be written in the jbi.xml file.
	 * @param eclipseSuBean
	 */
	public static void addHookForWsdlMarkUp( EclipseSuBean eclipseSuBean ) {

		if( eclipseSuBean.getCreatedWsdlMarkupValue() == null
					|| eclipseSuBean.getCreatedWsdlMarkupValue().length() == 0 )
			return;

		List<XmlElement> wsdlElements =
			XsdUtils.findXmlElement( "petalsCDK:wsdl", eclipseSuBean.cdkElements );

		// One (provide) or zero (consume) element expected in the list.
		for( XmlElement xmlElement : wsdlElements )
			xmlElement.setValue( eclipseSuBean.getCreatedWsdlMarkupValue());
	}


	/**
	 * @param eclipseSuBean
	 * @param createdProject
	 */
	public static void updateWsdlEndpoint( EclipseSuBean eclipseSuBean, IProject createdProject ) {

		if( StringUtils.isEmpty( eclipseSuBean.getWsdlUrl()))
			return;

		// Get WSDL file name.
		List<XmlElement> wsdlElements =
			XsdUtils.findXmlElement( "petalsCDK:wsdl", eclipseSuBean.cdkElements );

		// One (provide) or zero (consume) element expected in the list.
		boolean updateWorked = true;
		if( wsdlElements.size() == 1 ) {

			// Get WSDL file.
			IPath wsdlFilePath = createdProject.getLocation();
			wsdlFilePath = wsdlFilePath.append( PetalsConstants.LOC_RES_FOLDER );
			wsdlFilePath = wsdlFilePath.append( wsdlElements.get( 0 ).getValue());

			// Update the end-point
			QName serviceName = new QName(
						eclipseSuBean.getServiceNamespaceUri(),
						eclipseSuBean.getServiceName());

			updateWorked = WsdlUtils.INSTANCE.updateEndpointNameInWsdl(
						wsdlFilePath.toFile(),
						serviceName,
						eclipseSuBean.getEndpointName());
		}

		if( ! updateWorked ) {
			String msg = "The endpoint name could not be updated in the WSDL file.";
			ErrorReporter.INSTANCE.registerError( "WSDL-update", msg, IStatus.ERROR, null );
		}
	}
}
