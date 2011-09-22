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

package com.ebmwebsourcing.petals.services.bpel.designer.provisional;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.eclipse.core.runtime.IStatus;
import org.ow2.easywsdl.schema.api.abstractElmt.AbstractSchemaElementImpl;
import org.ow2.easywsdl.wsdl.api.Endpoint;
import org.ow2.easywsdl.wsdl.api.Service;
import org.ow2.easywsdl.wsdl.api.abstractItf.AbsItfDescription;
import org.ow2.easywsdl.wsdl.api.abstractItf.AbsItfInterfaceType;

import com.ebmwebsourcing.easybpel.model.bpel.api.BPELException;
import com.ebmwebsourcing.easybpel.model.bpel.tools.validator.AnalysorResult;
import com.ebmwebsourcing.easybpel.model.bpel.tools.validator.BPELAnalyser;
import com.ebmwebsourcing.easybpel.model.bpel.tools.validator.BPELValidator;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.UriUtils;
import com.ebmwebsourcing.petals.services.bpel.PetalsBpelPlugin;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsBpelModules {

	private static BPELAnalyser analyzer;
	static {
		try {
			analyzer = new BPELAnalyser();
			System.out.println( "Initializing BPEL analyzer..." );

		} catch( BPELException e ) {
			PetalsBpelPlugin.log( e, IStatus.ERROR );
		}
	}

	private static BPELValidator validator;
	static {
		try {
			validator = new BPELValidator();
			System.out.println( "Initializing BPEL validator..." );

		} catch( BPELException e ) {
			PetalsBpelPlugin.log( e, IStatus.ERROR );
		}
	}


	/**
	 * @return the unique instance of the Petals BPEL validator
	 */
	public static BPELValidator getBpelEngineValidator() {
		return validator;
	}


	/**
	 * @return the unique instance of the Petals BPEL analyzer
	 */
	public static BPELAnalyser getBpelEngineAnalyzer() {
		return analyzer;
	}


	private static Map<String,URI> localImportUris = new HashMap<String,URI> ();
	private static Map<String,URI> remoteImportUris = new HashMap<String,URI> ();


	/**
	 * Gets the resources imported by a BPEL process.
	 * @param bpelUrl the URL whose WSDL imports must be retrieved
	 * @param localImports true to get the local imports (relative URIs), false for remote ones
	 * @param refreshCachedList true to get the list cached during the last call, false to recompute the list content
	 * @return the URIs of the WSDL interfaces directly referenced by the BPEL process
	 */
	public static Map<String,URI> getWsdlImportUris( URL bpelUrl, boolean localImports, boolean refreshCachedList ) {

		if( refreshCachedList )
			getWsdlImportUris( bpelUrl );

		if( localImports )
			return localImportUris;
		return remoteImportUris;
	}


	/**
	 * Builds a map associating URIs as written in the BPEL (keys) and absolute URIs (values).
	 * <p>
	 * When absolute URIs are used in the BPEL, the key and the values are identical.
	 * </p>
	 * @param bpelUrl the URL whose WSDL imports must be retrieved
	 */
	private static void getWsdlImportUris( URL bpelUrl ) {

		localImportUris.clear();
		remoteImportUris.clear();

		try {
			AnalysorResult result = analyzer.analyze( bpelUrl );
			for( URI uri : result.getImports()) {
				try {
					URI bpelUri = UriUtils.urlToUri( bpelUrl );
					URI newUri = UriUtils.buildNewURI( bpelUri, uri.toString());
					if( uri.isAbsolute())
						remoteImportUris.put( uri.toString(), newUri );
					else
						localImportUris.put( uri.toString(), newUri );

				} catch( Exception e ) {
					PetalsBpelPlugin.log( e, IStatus.ERROR );
				}
			}

		} catch( Exception e ) {
			PetalsBpelPlugin.log( e, IStatus.ERROR );
		}
	}


	/**
	 * Gets the URI of the artifact WSDL of the process.
	 * @param bpel
	 * @return
	 */
	public static List<URI> getProcessWsdl( URL bpel ) {

		try {
			AnalysorResult result = analyzer.analyze( bpel );
			List<URI> uris = result.getProcessDescriptions();
			return uris;

		} catch( BPELException e ) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}


	/**
	 * Parses the BPEL process definition and builds a set of JBI beans to create jbi.xml files.
	 * @param bpel
	 * @return
	 */
	@SuppressWarnings( "rawtypes" )
	public static List<JbiXmlBean> getJbiXmlBean( URL bpel ) {

		List<JbiXmlBean> beans = new ArrayList<JbiXmlBean> ();
		try {
			AnalysorResult result = analyzer.analyze( bpel );
			List<AbsItfInterfaceType> interfaces = result.getProcessDefinition().getImports().getProcessInterfaces();

			for( AbsItfInterfaceType<?> itf : interfaces ) {
				QName itfName = itf.getQName();
				AbsItfDescription desc = (AbsItfDescription) ((AbstractSchemaElementImpl<?>) itf).getParent();

				for( Object o : desc.getServices()) {
					Service service = (Service) o;
					if( ! itf.equals( service.getInterface()))
						continue;

					for( Object oo : service.getEndpoints()) {
						Endpoint endpoint = (Endpoint) oo;

						JbiXmlBean bean = new JbiXmlBean();
						bean.interfaceName = itfName;
						bean.serviceName = service.getQName();
						bean.endpointName = endpoint.getName();
						bean.wsdlUri = desc.getDocumentBaseURI();

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
	 * Contains the required information to generate a basic jbi.xml from a BPEL file.
	 */
	public static class JbiXmlBean {

		QName interfaceName, serviceName;
		String endpointName;
		URI wsdlUri;

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
		 * @return the wsdlUri
		 */
		public URI getWsdlUri() {
			return this.wsdlUri;
		}
	}
}
