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

package com.ebmwebsourcing.petals.common.internal.provisional.utils;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.wsdl.Definition;
import javax.wsdl.Port;
import javax.wsdl.Service;
import javax.wsdl.extensions.soap.SOAPBinding;
import javax.wsdl.extensions.soap12.SOAP12Binding;
import javax.wsdl.xml.WSDLWriter;
import javax.xml.namespace.QName;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.osgi.util.NLS;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.ebmwebsourcing.petals.common.internal.Messages;
import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

/**
 * A class which updates the Petals end-point in WSDLs.
 * <p>
 * It works for versions 1.1 and 2.0 of the WSDL specification.<br />
 * For WSDL 1.1, the WSDL after the update only contains the relevant information for Petals.
 * Others are deleted.
 * </p><p>
 * This class is based on the code provided by {@link WsdlParser}.
 * </p>
 *
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class WsdlUpdater {

	/** The unique instance of this parser. */
	private static WsdlUpdater instance = new WsdlUpdater();
	/** The instance of the WSDL parser for the version 1.1 of WSDL. */
	private javax.wsdl.xml.WSDLReader wsdlReader11;
	/** */
	private javax.wsdl.factory.WSDLFactory wsdlFactory11;



	/**
	 * The constructor initializes the two parsers.
	 */
	private WsdlUpdater() {
		try {
			this.wsdlFactory11 = javax.wsdl.factory.WSDLFactory.newInstance();
			this.wsdlReader11 = this.wsdlFactory11.newWSDLReader();
			this.wsdlReader11.setFeature( "javax.wsdl.verbose", false ); //$NON-NLS-1$

		} catch( javax.wsdl.WSDLException e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );
		}
	}

	/**
	 * @return the unique instance of this class.
	 */
	public static WsdlUpdater getInstance () {
		return instance;
	}

	/**
	 * Updates a WSDL file by replacing the end-point by a Petals end-point.

	 * @param file the file to parse.
	 * @param serviceName the name of the service whose end-point must be updated
	 * @param portType the port type associated with the port to update
	 * @param newEndpoint
	 * @return true if the update worked, false otherwise.
	 */
	public boolean update( java.io.File file, QName serviceName, QName portType, String newEndpoint ) {

		// Try parsing for WSDL 1.1
		try {
			// Search elements to update
			Definition def = this.wsdlReader11.readWSDL( file.toURI().toString());
			Object value = def.getServices().get( serviceName );
			if( value == null || !( value instanceof Service ))
				throw new Exception( "Invalid service properties (not found or invalid class)." );

			Service service = (Service) value;
			Map<?,?> ports = service.getPorts();
			Set<?> keyPorts = ports.keySet();

			Port rightPort = null;
			portsLoop: for( Object name : keyPorts ) {
				String portName = (String) name;
				Port port = (Port) ports.get( portName );

				// Check the port type too (case where there are several ports in a same service)
				QName portTypeName = port.getBinding().getPortType() != null ? port.getBinding().getPortType().getQName() : null;
				if( ! portType.equals( portTypeName ))
					continue;

				// Get the right binding now - must be SOAP
				for( Object o : port.getBinding().getExtensibilityElements()) {
					if( o instanceof SOAPBinding || o instanceof SOAP12Binding ) {
						rightPort = port;
						break portsLoop;
					}
				}
			}

			// Update the end-point
			if( rightPort != null ) {
				rightPort.setName( newEndpoint );
				WSDLWriter writer = this.wsdlFactory11.newWSDLWriter();
				FileOutputStream fos = new FileOutputStream( file );
				writer.writeWSDL( def, fos );
				fos.close();
				return true;
			}

			return false;

		} catch( Exception e ) {
			//e.printStackTrace();
		}

		// Try parsing for WSDL 2.0
		try {
			return updateEndpointInWsdl20( file, serviceName, newEndpoint );
		} catch( Exception e) {
			//e.printStackTrace();
		}

		return false;
	}

	/**
	 * Updates a WSDL file by replacing the end-point by a Petals end-point.
	 * <p>
	 * Temporary fix while waiting to move on EasyWSDL.
	 * </p>
	 * <p>
	 * WSDL 2.0 are not supported.
	 * </p>
	 *
	 * @param file the file to parse.
	 * @param serviceName the name of the service whose end-point must be updated
	 * @param newServiceName
	 * @param newEndpoint
	 * @return true if the update worked, false otherwise.
	 */
	public boolean updateEndpointAndService( java.io.File file, QName serviceName, QName newServiceName, String newEndpoint ) {

		// Try parsing for WSDL 1.1
		try {
			// Search elements to update
			Definition def = this.wsdlReader11.readWSDL( file.toURI().toString());
			Object value = def.getServices().get( serviceName );
			if( value == null || !( value instanceof Service ))
				throw new Exception( "Invalid service properties (not found or invalid class)." );

			Service service = (Service) value;
			Map<?,?> ports = service.getPorts();
			Set<?> keyPorts = ports.keySet();

			Port rightPort = null;
			portsLoop: for( Object name : keyPorts ) {
				String portName = (String) name;
				Port port = (Port) ports.get( portName );

				for( Object o : port.getBinding().getExtensibilityElements()) {
					if( o instanceof SOAPBinding && rightPort == null ) {
						rightPort = port;
						break portsLoop;
					}
					else if( o instanceof SOAP12Binding && rightPort == null ) {
						rightPort = port;
						break portsLoop;
					}
				}
			}

			// Update the end-point
			if( rightPort != null ) {
				rightPort.setName( newEndpoint );
				service.setQName( newServiceName );

				WSDLWriter writer = this.wsdlFactory11.newWSDLWriter();
				FileOutputStream fos = new FileOutputStream( file );
				writer.writeWSDL( def, fos );
				fos.close();
				return true;
			}

			return false;

		} catch( Exception e ) {
			//e.printStackTrace();
		}

		return false;
	}

	/**
	 * Replace the end-point by a Petals end-point in a WSDL (v2.0) file.
	 * @param file
	 * @param serviceName the name of the service whose end-point must be updated
	 * @param newEndpoint
	 * @throws Exception
	 */
	private boolean updateEndpointInWsdl20( java.io.File file, QName serviceName, String newEndpoint ) throws Exception {

		SAXBuilder sxb = new SAXBuilder();
		Document document = sxb.build( file );
		Element root = document.getRootElement();
		String wsdlNs = "http://www.w3.org/ns/wsdl";


		// Get the right "service" element in the WSDL
		// FIXME: for instance, the lookup is only based on the local part
		Element service = null;
		List<?> services = root.getChildren( "service", Namespace.getNamespace( wsdlNs )); //$NON-NLS-1$
		for( Object s : services ) {

			service = (Element) s;
			String name = service.getAttributeValue( "name" );
			if( name != null && name.equals( serviceName.getLocalPart()))
				break;

			service = null;
		}

		if( service == null )
			throw new Exception( "The specified service could not found in the WSDL document." );

		List<?> endpoints = service.getChildren( "endpoint", Namespace.getNamespace( wsdlNs )); //$NON-NLS-1$
		for( Object o2 : endpoints ) {
			if( o2 == null || !Element.class.equals( o2.getClass()))
				continue;

			Element endpoint = (Element) o2;
			String bindingAttr = endpoint.getAttributeValue( "binding" ); //$NON-NLS-1$
			String[] split = bindingAttr.split( ":" ); //$NON-NLS-1$

			String nsUri = null;
			if( split.length == 2 )
				nsUri = split[ 1 ];
			else if( split.length == 1 )
				nsUri = split[ 0 ];
			else
				throw new org.apache.woden.WSDLException(
							org.apache.woden.WSDLException.UNBOUND_PREFIX,
							NLS.bind( Messages.WsdlUpdater_9, endpoint.getName(), service.getName()));

			List<?> bindings = root.getChildren( "binding", Namespace.getNamespace( wsdlNs )); //$NON-NLS-1$
			for( Object o3 : bindings ) {
				if( o3 == null || !Element.class.equals( o3.getClass()))
					continue;

				Element binding = (Element) o3;
				if( nsUri.equals( binding.getAttributeValue( "name" )) //$NON-NLS-1$
							&& binding.getAttributeValue( "type" ) != null  //$NON-NLS-1$
							&& binding.getAttributeValue( "type" ).toLowerCase().contains( "soap" )) { //$NON-NLS-1$ //$NON-NLS-2$

					// update the address of the endpoint
					endpoint.setAttribute( "name", newEndpoint ); //$NON-NLS-1$
					XMLOutputter output = new XMLOutputter(Format.getPrettyFormat());
					output.output( document, new FileOutputStream( file ));
					return true;
				}
			}
		}
		return false;
	}
}
