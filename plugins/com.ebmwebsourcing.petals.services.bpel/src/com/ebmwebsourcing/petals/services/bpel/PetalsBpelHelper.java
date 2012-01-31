/******************************************************************************
 * Copyright (c) 2012, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.bpel;

import java.io.File;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;

import org.eclipse.bpel.common.wsdl.helpers.UriAndUrlHelper;
import org.eclipse.bpel.common.wsdl.importhelpers.WsdlImportHelper;
import org.eclipse.bpel.common.wsdl.parsers.WsdlParser;
import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.model.Import;
import org.eclipse.bpel.model.PartnerLink;
import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.model.partnerlinktype.PartnerlinktypePackage;
import org.eclipse.bpel.model.resource.BPELResourceFactoryImpl;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.wst.wsdl.Definition;
import org.eclipse.wst.wsdl.Port;
import org.eclipse.wst.wsdl.PortType;
import org.eclipse.wst.wsdl.Service;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.EmfUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.bpel.bpel.BpelPackage;
import com.sun.java.xml.ns.jbi.Jbi;
import com.sun.java.xml.ns.jbi.JbiFactory;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * A set of utility methods for the Petals BPEL extensions.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsBpelHelper {

	private final URI bpelUri;
	private final Process process;
	private Set<String> partnerImports, processInterfaceImports, otherImports;


	/**
	 * Constructor.
 	 * @param bpelUri the URI of the BPEL file
	 */
	public PetalsBpelHelper( URI bpelUri ) {
		this.bpelUri = bpelUri;

		// Load the BPEL
		ResourceSet resourceSet = WsdlParser.createBasicResourceSetForWsdl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put( "bpel", new BPELResourceFactoryImpl());
		resourceSet.getPackageRegistry().put( PartnerlinktypePackage.eNS_URI, PartnerlinktypePackage.eINSTANCE );
		resourceSet.getPackageRegistry().put( BPELPackage.eNS_URI, BPELPackage.eINSTANCE );

		org.eclipse.emf.common.util.URI emfUri = org.eclipse.emf.common.util.URI.createURI( bpelUri.toString(), true );
		Resource res = resourceSet.getResource( emfUri, true );
		this.process = (Process) res.getContents().get( 0 );
	}


	/**
	 * Constructor.
 	 * @param bpelFile the BPEL file
	 */
	public PetalsBpelHelper( File bpelFile ) {
		this( bpelFile.toURI());
	}

	/**
	 * @return the bpelUri
	 */
	public URI getBpelUri() {
		return this.bpelUri;
	}


	/**
	 * Parses the BPEL process definition and builds a set of JBI beans to create jbi.xml files.
	 * @return a non-null list of beans to generate jbi.xml files for the BPEL SE
	 */
	public List<JbiXmlBean> extractJbiXmlBean() {

		List<JbiXmlBean> beans = new ArrayList<JbiXmlBean> ();
		try {
			// Find all the interfaces implemented by the process
			// Hint: they have the "myRole" role
			List<PortType> portTypes = new ArrayList<PortType> ();
			if( this.process.getPartnerLinks() != null ) {	// Empty template => no PL
				for( PartnerLink pl : this.process.getPartnerLinks().getChildren()) {
					if( pl.getMyRole() != null )
						portTypes.add( (PortType) pl.getMyRole().getPortType());
				}
			}

			if( portTypes.isEmpty())
				PetalsBpelPlugin.log( "No interface was found while analyzing the BPEL process.", IStatus.ERROR );
			else {
				// FIXME: getEServices might not retrieve the services from the imports
				for( Object o : portTypes.get( 0 ).getEnclosingDefinition().getEServices()) {
					if( !( o instanceof Service ))
						continue;

					for( Object oo : ((Service) o).getEPorts()) {
						Port port = (Port) oo;
						PortType portType = port.getEBinding().getEPortType();
						if( ! portTypes.contains( portType ))
							continue;

						JbiXmlBean bean = new JbiXmlBean();
						bean.interfaceName = portType.getQName();
						bean.serviceName = ((Service) o).getQName();
						bean.endpointName = port.getName();
						bean.wsdlFile = EmfUtils.getUnderlyingFile( port );
						beans.add( bean );
					}
				}
			}

		} catch( Exception e ) {
			PetalsBpelPlugin.log( e, IStatus.ERROR );
		}

		return beans;
	}


	/**
	 * Gets the URIs of the WSDLs that are associated with a partner.
	 * @param refresh true to refresh the list, false to send the cached one
	 * @return a non-null set of local imports
	 */
	public Set<String> findPartnerImports( boolean refresh ) {

		if( refresh || this.partnerImports == null )
			findWsdlImports();

		return this.partnerImports;
	}


	/**
	 * Gets the URIs of the WSDLs that are associated with the process.
	 * @param refresh true to refresh the list, false to send the cached one
	 * @return a non-null set of local imports
	 */
	public Set<String> findProcessInterfaceImports( boolean refresh ) {

		if( refresh || this.processInterfaceImports == null )
			findWsdlImports();

		return this.processInterfaceImports;
	}


	/**
	 * Gets the URIs of the artifacts which are neither partner WSDL nor process WSDLs.
	 * @param refresh true to refresh the list, false to send the cached one
	 * @return a non-null set of other imports
	 */
	public Set<String> findOtherImports( boolean refresh ) {

		if( refresh || this.otherImports == null )
			findWsdlImports();

		return this.otherImports;
	}


	/**
	 * Finds the WSDL imports in the process.
	 */
	private void findWsdlImports() {

		// First, get the WSDL associated with the process
		Set<String> tns = new HashSet<String> ();
		if( this.process.getPartnerLinks() != null ) {	// Empty template => no PL
			for( PartnerLink pl : this.process.getPartnerLinks().getChildren()) {
				if( pl.getMyRole() != null ) {
					Definition def = ((PortType) pl.getMyRole().getPortType()).getEnclosingDefinition();
					tns.add( def.getTargetNamespace());
				}
			}
		}


		// Initialize the collections
		if( this.processInterfaceImports == null )
			this.processInterfaceImports = new HashSet<String> ();
		else
			this.processInterfaceImports.clear();

		if( this.partnerImports == null )
			this.partnerImports = new HashSet<String> ();
		else
			this.partnerImports.clear();

		if( this.otherImports == null )
			this.otherImports = new HashSet<String> ();
		else
			this.otherImports.clear();


		// Analyze the imports
		for( Import imp : this.process.getImports()) {
			String loc;
			try {
				loc = UriAndUrlHelper.buildNewURI( this.bpelUri, imp.getLocation()).toString();

			} catch( URISyntaxException e ) {
				PetalsBpelPlugin.log( e, IStatus.ERROR );
				continue;
			}

			if( WsdlImportHelper.WSDL11_NAMESPACE.equals( imp.getImportType())) {
				if( tns.contains( imp.getNamespace()))
					this.processInterfaceImports.add( loc );
				else
					this.partnerImports.add( loc );

			} else {
				this.otherImports.add( loc );
			}
		}
	}


	/**
	 * Creates a jbi.xml instance from the BPEL file.
	 * <p>
	 * Note that the jbi.xml file is not written by this method.
	 * Only its content is returned.
	 * </p>
	 *
	 * @param jbiFile the jbi.xml file to create
	 * @param bpelFileName the BPEL file name
	 * @return a {@link Jbi} instance
	 */
	public Jbi createJbiXml( IFile jbiFile, String bpelFileName ) {

		Jbi jbiInstance = JbiFactory.eINSTANCE.createJbi();
		jbiInstance.setVersion( new BigDecimal( "1.0" ));
		jbiInstance.setServices( JbiFactory.eINSTANCE.createServices());
		jbiInstance.getServices().setBindingComponent( false );
		for( JbiXmlBean bean : extractJbiXmlBean()) {
			Provides provides = JbiFactory.eINSTANCE.createProvides();
			provides.setInterfaceName( bean.getInterfaceName());
			provides.setServiceName( bean.getServiceName());
			provides.setEndpointName( bean.getEndpointName());

			String wsdlPath = IoUtils.getRelativeLocationToFile( jbiFile.getLocation().toFile(), bean.getWsdlFile());
			provides.eSet( Cdk5Package.Literals.CDK5_PROVIDES__WSDL, wsdlPath );
			provides.eSet( BpelPackage.Literals.BPEL_PROVIDES__BPEL, bpelFileName );
			provides.eSet( BpelPackage.Literals.BPEL_PROVIDES__POOLSIZE, 1 );

			jbiInstance.getServices().getProvides().add( provides );
		}

		return jbiInstance;
	}


	/**
	 * A simple bean that makes the creation of jbi.xml files easy.
	 */
	public static class JbiXmlBean {
		QName interfaceName, serviceName;
		String endpointName;
		File wsdlFile;

		/**
		 * @return the interfaceName
		 */
		public QName getInterfaceName() {
			return this.interfaceName;
		}

		/**
		 * @return the serviceName
		 */
		public QName getServiceName() {
			return this.serviceName;
		}

		/**
		 * @return the endpointName
		 */
		public String getEndpointName() {
			return this.endpointName;
		}

		/**
		 * @return the wsdlFile
		 */
		public File getWsdlFile() {
			return this.wsdlFile;
		}
	}
}
