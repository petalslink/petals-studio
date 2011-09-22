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

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.wsdl.Definition;
import javax.wsdl.Operation;
import javax.wsdl.Port;
import javax.wsdl.PortType;
import javax.wsdl.extensions.soap.SOAPAddress;
import javax.wsdl.extensions.soap12.SOAP12Address;
import javax.xml.namespace.QName;

import org.apache.woden.wsdl20.Description;
import org.apache.woden.wsdl20.Endpoint;
import org.apache.woden.wsdl20.Interface;
import org.apache.woden.wsdl20.InterfaceOperation;
import org.apache.woden.wsdl20.Service;
import org.apache.woden.wsdl20.extensions.ComponentExtensions;
import org.apache.woden.wsdl20.extensions.soap.SOAPBindingExtensions;
import org.eclipse.core.runtime.IStatus;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

/**
 * A WSDL parser for versions 1.1 and 2.0 of the WSDL specification.
 * <p>
 * This parser looks to fill in a data structure storing the name, the end-point and the interface of a service.
 * Since we could not find any WSDL parsing library managing both versions, this parser used two different
 * parsers sequentially to parse a WSDL. It first uses WSDL4j to parse WSDL 1.1.
 * If the parsing fails, it tries to parse it with Apache Woden for WSDL 2.0.
 * If it also fails, then we conclude to a parsing error.
 * </p>
 *
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class WsdlParser {

	/** The unique instance of this parser. */
	private static WsdlParser instance = new WsdlParser();

	/** The instance of the WSDL parser for the version 2.0 of WSDL. */
	private org.apache.woden.WSDLReader wsdlReader20;

	/** The instance of the WSDL parser for the version 1.1 of WSDL. */
	private javax.wsdl.xml.WSDLReader wsdlReader11;



	/**
	 * The constructor initializes the two parsers.
	 */
	private WsdlParser() {
		try {
			this.wsdlReader20 = org.apache.woden.WSDLFactory.newInstance().newWSDLReader();
			this.wsdlReader11 = javax.wsdl.factory.WSDLFactory.newInstance().newWSDLReader();
			this.wsdlReader11.setFeature( "javax.wsdl.verbose", false ); //$NON-NLS-1$

		} catch( org.apache.woden.WSDLException e ) {
			PetalsCommonPlugin.log( e, IStatus.WARNING );

		} catch( javax.wsdl.WSDLException e ) {
			PetalsCommonPlugin.log( e, IStatus.WARNING );
		}
	}


	/**
	 * @return the unique instance of this class.
	 */
	public static WsdlParser getInstance () {
		return instance;
	}


	/**
	 * Parses a WSDL URL.
	 * @param wsdlUri the URI of the WSDL to parse.
	 * @return a bean containing required data to generate a SU for Petals. Null if the parsing failed.
	 * @throws IllegalArgumentException thrown when more than one service is described by the parsed WSDL (yes, that's possible).
	 */
	public List<JbiBasicBean> parse( String wsdlUri ) throws IllegalArgumentException {

		List<JbiBasicBean> result = new ArrayList<JbiBasicBean> ();

		// Try parsing for WSDL 1.1
		try {
			Definition def = this.wsdlReader11.readWSDL( wsdlUri );
			for( Iterator<?> itService = def.getServices().keySet().iterator(); itService.hasNext(); ) {

				QName serviceName = (QName) itService.next();
				Object value = def.getServices().get( serviceName );
				if( !( value instanceof javax.wsdl.Service ))
					continue;

				javax.wsdl.Service service = (javax.wsdl.Service) value;
				Map<?,?> ports = service.getPorts();
				for( Map.Entry<?,?> entry : ports.entrySet()) {
					String portName = (String) entry.getKey();
					Port port = (Port) entry.getValue();

					List<?> l = port.getExtensibilityElements();
					for( Object o : l ) {
						if( o == null )
							continue;

						JbiBasicBean bean = new JbiBasicBean();
						String soapAddress;
						if( o instanceof SOAPAddress )
							soapAddress = ((SOAPAddress) o).getLocationURI();
						else if( o instanceof SOAP12Address ) {
							soapAddress = ((SOAP12Address) o).getLocationURI();
							bean.setSoapVersion( SoapVersion.v12 );
						}
						else
							continue;

						bean.setServiceName( service.getQName().getLocalPart());
						bean.setServiceNs( service.getQName().getNamespaceURI());
						bean.setSoapAddress( soapAddress );
						bean.setEndpointName( portName );

						PortType p = port.getBinding().getPortType();
						bean.setInterfaceName( p.getQName().getLocalPart());
						bean.setInterfaceNs( p.getQName().getNamespaceURI());

						// Bug PETALSSTUD-71: make sure the referenced port type really exists in the WSDL
						bean.setPortTypeExists( ! p.isUndefined());
						// Bug PETALSSTUD-71

						// Get the operations
						if( p.getOperations() != null ) {
							for( Object opo : p.getOperations()) {
								if( opo instanceof Operation ) {
									QName operationName = new QName(
												p.getQName().getNamespaceURI(),
												((Operation) opo).getName());

									Mep mep = Mep.IN_OUT;
									if(((Operation) opo).isUndefined())
										mep = Mep.UNKNOWN;

									// FIXME: it does not work. Tested with Talend#executeJobOnly.
									else if(((Operation) opo).getOutput() == null
												|| ((Operation) opo).getOutput().getMessage() == null
												|| ((Operation) opo).getOutput().getMessage().isUndefined())
										mep = Mep.IN_ONLY;

									bean.addOperation( operationName, mep );
								}
							}
						}

						result.add( bean );
					}
				}
			}

			return result;

		} catch( javax.wsdl.WSDLException e ) {
			// nothing
		}


		// Try parsing for WSDL 2.0
		try {
			result.clear();
			Description desc = this.wsdlReader20.readWSDL( wsdlUri );
			for( Service service : desc.getServices()) {
				for( Endpoint endpoint : service.getEndpoints()) {

					ComponentExtensions ext = endpoint.getBinding().
					getComponentExtensionsForNamespace( ComponentExtensions.URI_NS_SOAP );

					if( ext != null && ext instanceof SOAPBindingExtensions ) {

						JbiBasicBean bean = new JbiBasicBean();
						bean.setServiceName( service.getName().getLocalPart());
						bean.setServiceNs( service.getName().getNamespaceURI());
						bean.setInterfaceName( service.getInterface().getName().getLocalPart());
						bean.setInterfaceNs( service.getInterface().getName().getNamespaceURI());
						bean.setSoapAddress( endpoint.getAddress().toString());
						bean.setEndpointName( endpoint.getName().toString());

						// Bug PETALSSTUD-71: make sure the referenced port type really exists in the WSDL
						Interface itf = desc.getInterface( service.getInterface().getName());
						bean.setPortTypeExists( itf != null );
						// Bug PETALSSTUD-71

						// Get the operations
						if( itf != null
								&& itf.getInterfaceOperations() != null ) {
							for( InterfaceOperation op : itf.getInterfaceOperations()) {

								Mep mep = Mep.UNKNOWN;
								URI mepUri = op.getMessageExchangePattern();
								if( "http://www.w3.org/2004/03/wsdl/in-only".equals( mepUri.toString()))
									mep = Mep.IN_ONLY;
								else if( "http://www.w3.org/2004/03/wsdl/robust-in-only".equals( mepUri.toString()))
									mep = Mep.ROBUST_IN_ONLY;
								else if( "http://www.w3.org/2004/03/wsdl/in-out".equals( mepUri.toString()))
									mep = Mep.IN_OUT;

								bean.addOperation( op.getName(), mep );
							}
						}

						result.add( bean );
					}
				}
			}

			return result;

		} catch( Exception e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );
		}

		return result;
	}


	/**
	 * Gets the operations of the WSDL service identified by the parameters.
	 * @param wsdlUri the URI of the WSDL to parse
	 * @param itfName the interface name
	 * @param itfNs
	 * @param srvName the service name
	 * @param srvNs
	 * @param edptName the end-point name
	 * @return a map (operation name = operation MEP), never null
	 */
	public Map<QName, Mep> getOperations(
				String wsdlUri,
				String itfName,
				String itfNs,
				String srvName,
				String srvNs,
				String edptName ) {

		Map<QName,Mep> result = new HashMap<QName,Mep> ();
		try {
			List<JbiBasicBean> beans = parse( wsdlUri );
			if( beans != null ) {
				for( JbiBasicBean bean : beans ) {
					if( bean.haveSameIdentifiers( itfName, itfNs, srvName, srvNs, edptName )) {
						result.putAll( bean.getOperations());
						break;
					}
				}
			}

		} catch( IllegalArgumentException e ) {
			// nothing
		}

		return result;
	}


	/**
	 * A class use to store WSDL parsing results.
	 */
	public static class JbiBasicBean {

		private String serviceName, serviceNs;
		private String interfaceName, interfaceNs;
		private String endpointName;
		private String soapAddress;
		private boolean portTypeExists;
		private SoapVersion soapVersion = SoapVersion.v11;
		private final Map<QName, Mep> operations = new HashMap<QName,Mep> ();


		/**
		 * @return the serviceName
		 */
		public String getServiceName() {
			return this.serviceName;
		}
		/**
		 * @return the interfaceName
		 */
		public String getInterfaceName() {
			return this.interfaceName;
		}
		/**
		 * @return the serviceNs
		 */
		public String getServiceNs() {
			return this.serviceNs;
		}
		/**
		 * @return the interfaceNs
		 */
		public String getInterfaceNs() {
			return this.interfaceNs;
		}

		/**
		 * @param serviceName the serviceName to set
		 */
		public void setServiceName( String serviceName ) {
			this.serviceName = serviceName;
		}
		/**
		 * @param serviceNs the serviceNs to set
		 */
		public void setServiceNs( String serviceNs ) {
			this.serviceNs = serviceNs;
		}
		/**
		 * @param interfaceName the interfaceName to set
		 */
		public void setInterfaceName( String interfaceName ) {
			this.interfaceName = interfaceName;
		}
		/**
		 * @param interfaceNs the interfaceNs to set
		 */
		public void setInterfaceNs( String interfaceNs ) {
			this.interfaceNs = interfaceNs;
		}

		/**
		 * @return the endpointName
		 */
		public String getEndpointName() {
			return this.endpointName;
		}

		/**
		 * @param endpointName the endpointName to set
		 */
		public void setEndpointName( String endpointName ) {
			this.endpointName = endpointName;
		}

		/**
		 * @return the soapAddress
		 */
		public String getSoapAddress() {
			return this.soapAddress;
		}

		/**
		 * @param soapAddress the soapAddress to set
		 */
		public void setSoapAddress( String soapAddress ) {
			this.soapAddress = soapAddress;
		}

		/**
		 * @return the soapVersion
		 */
		public SoapVersion getSoapVersion() {
			return this.soapVersion;
		}

		/**
		 * @param soapVersion the soapVersion to set
		 */
		public void setSoapVersion( SoapVersion soapVersion ) {
			this.soapVersion = soapVersion;
		}

		/**
		 * @return the portTypeExists
		 */
		public boolean isPortTypeExists() {
			return this.portTypeExists;
		}

		/**
		 * @param portTypeExists the portTypeExists to set
		 */
		public void setPortTypeExists( boolean portTypeExists ) {
			this.portTypeExists = portTypeExists;
		}

		/**
		 * @return the operations
		 */
		public Map<QName,Mep> getOperations() {
			return this.operations;
		}

		/**
		 * @param operation an operation name
		 * @param mep the operation's MEP
		 * @see java.util.List#add(java.lang.Object)
		 */
		public void addOperation( QName operation, Mep mep ) {
			this.operations.put( operation, mep );
		}

		/**
		 * @param itfName
		 * @param itfNs
		 * @param srvName
		 * @param srvNs
		 * @param edptName
		 * @return true only if this bean has the same identifiers than those given in parameters
		 */
		public boolean haveSameIdentifiers( String itfName, String itfNs, String srvName, String srvNs, String edptName ) {

			return StringUtils.areEqual( itfName, this.interfaceName )
			&& StringUtils.areEqual( itfNs, this.interfaceNs )
			&& StringUtils.areEqual( srvName, this.serviceName )
			&& StringUtils.areEqual( srvNs, this.serviceNs )
			&& StringUtils.areEqual( edptName, this.endpointName );
		}
	}


	/**
	 * The SOAP version.
	 */
	public static enum SoapVersion {
		v11, v12;

		@Override
		public String toString() {

			switch( this ) {
			case v11: return "1.1";
			default: return "1.2";
			}
		};
	}
}
