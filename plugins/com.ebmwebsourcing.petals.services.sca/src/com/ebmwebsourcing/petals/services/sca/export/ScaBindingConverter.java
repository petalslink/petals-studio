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
package com.ebmwebsourcing.petals.services.sca.export;

import java.io.File;
import java.net.URI;
import java.util.List;

import org.eclipse.soa.sca.sca1_0.model.sca.BaseReference;
import org.eclipse.soa.sca.sca1_0.model.sca.Binding;
import org.eclipse.soa.sca.sca1_0.model.sca.Composite;
import org.eclipse.soa.sca.sca1_0.model.sca.Reference;
import org.eclipse.soa.sca.sca1_0.model.sca.ScaPackage;
import org.eclipse.soa.sca.sca1_0.model.sca.WebServiceBinding;
import org.eclipse.soa.sca.sca1_0.runtime.frascati.model.frascati.FrascatiFactory;
import org.eclipse.soa.sca.sca1_0.runtime.frascati.model.frascati.FrascatiPackage;
import org.eclipse.soa.sca.sca1_0.runtime.frascati.model.frascati.JBIBinding;

import com.ebmwebsourcing.petals.common.generation.cdk5.components.SoapProvides40;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.UriUtils;
import com.ebmwebsourcing.petals.services.wizards.beans.SuImportBean;
import com.ebmwebsourcing.petals.services.wizards.beans.SuImportBeanBuilder;
import com.ebmwebsourcing.petals.services.wizards.beans.SuImportBeanBuilder.ComponentId;
import com.ebmwebsourcing.petals.services.wizards.beans.SuImportBeanBuilder.UnsupportedComponentException;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ScaBindingConverter {

	public static ScaBindingConverter INSTANCE = new ScaBindingConverter();


	/**
	 * Constructor.
	 */
	private ScaBindingConverter() {
		// nothing
	}



	public boolean isSupported( Binding scaBinding ) {

		boolean supported = true;
		switch( scaBinding.eClass().getClassifierID()) {
		case ScaPackage.SCA_BINDING:
		case ScaPackage.WEB_SERVICE_BINDING:
		case FrascatiPackage.JBI_BINDING:
			break;
		default:
			supported = false;
		}

		return supported;
	}


	public JBIBinding buildJbiBinding( Binding scaBinding, Composite composite, String elementName ) {

		// Prepare common elements
		JBIBinding result = null;
		File compositeFile = new File( composite.eResource().getURI().toFileString());

		String tns = composite.getTargetNamespace();
		if( tns == null )
			tns = "http://petals.ow2.org/sca/" + composite.getName() + "/" + elementName;

		// Have a default (basic) JBI binding
		JBIBinding defaultBinding = FrascatiFactory.eINSTANCE.createJBIBinding();
		defaultBinding.setEndpointName( elementName + "ScaEdpt" );
		defaultBinding.setServiceName( elementName );
		defaultBinding.setServiceNamespace( tns );
		defaultBinding.setInterfaceName( elementName + "PortType" );
		defaultBinding.setInterfaceNamespace( tns );

		// Check every binding
		switch( scaBinding.eClass().getClassifierID()) {

		case FrascatiPackage.JBI_BINDING:
			result = (JBIBinding) scaBinding;
			break;

		case ScaPackage.SCA_BINDING:
			result = defaultBinding;
			break;

		case ScaPackage.WEB_SERVICE_BINDING:
			result = defaultBinding;
			WebServiceBinding wsBinding = (WebServiceBinding) scaBinding;

			java.net.URI wsdlUri = null;
			List<String> locations = wsBinding.getWsdlLocation();
			if( locations != null ) {
				for( String location : locations ) {
					wsdlUri = UriUtils.urlToUri( location );
					URI relativeUri = UriUtils.getRelativeLocationToUri( compositeFile.toURI(), wsdlUri );
					result.setWsdl( relativeUri.toString());
					break;
				}
			}

			if( result.getWsdl() == null ) {
				wsdlUri = UriUtils.urlToUri( scaBinding.getUri() + "?wsdl" );
				result.setWsdl( wsdlUri.toString());
			}

			break;

		}

		return result;
	}


	public SuImportBean createPetalsConsumer( Binding scaBinding, JBIBinding jbiBindingToConsume )
	throws UnsupportedComponentException {

		SuImportBean result = null;
		switch( scaBinding.eClass().getClassifierID()) {

		case FrascatiPackage.JBI_BINDING:
		case ScaPackage.SCA_BINDING:
			// nothing
			break;

		case ScaPackage.WEB_SERVICE_BINDING:
			result = SuImportBeanBuilder.INSTANCE.buildSuImportBean(
						ComponentId.SOAP, true,
						jbiBindingToConsume.getServiceName());
			break;
		}

		return result;
	}


	public ScaSketchExportBean createPetalsProvider( Binding scaBinding, BaseReference reference )
	throws UnsupportedComponentException {

		Composite composite = null;
		if( reference instanceof Reference )
			composite = (Composite) reference.eContainer();
		else
			composite = (Composite) reference.eContainer().eContainer();

		ScaSketchExportBean result = null;
		switch( scaBinding.eClass().getClassifierID()) {

		case FrascatiPackage.JBI_BINDING:
		case ScaPackage.SCA_BINDING:
			// nothing
			break;

		case ScaPackage.WEB_SERVICE_BINDING:
			JBIBinding jbiBinding = buildJbiBinding( scaBinding, composite, reference.getName());
			SoapProvides40 soapBean = new SoapProvides40();
			soapBean.setServiceAddress( scaBinding.getUri());
			soapBean.setWsdl( jbiBinding.getWsdl());

			soapBean.setServiceName( jbiBinding.getServiceName());
			soapBean.setServiceNamespace( jbiBinding.getServiceNamespace());
			soapBean.setInterfaceName( jbiBinding.getInterfaceName());
			soapBean.setInterfaceNamespace( jbiBinding.getInterfaceNamespace());
			soapBean.setEndpointName( jbiBinding.getEndpointName());

			SuImportBean suImportBean = SuImportBeanBuilder.INSTANCE.buildSuImportBean(
						ComponentId.SOAP, true,
						jbiBinding.getServiceName());

			// suImportBean.setWsdlLocation( jbiBinding.getWsdl());
			result = new ScaSketchExportBean( suImportBean, soapBean );
			break;
		}

		return result;
	}
}
