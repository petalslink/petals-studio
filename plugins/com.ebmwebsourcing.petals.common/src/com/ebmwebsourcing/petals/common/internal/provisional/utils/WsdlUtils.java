/****************************************************************************
 *
 * Copyright (c) 2008-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.wsdl.Definition;
import javax.wsdl.Operation;
import javax.wsdl.Port;
import javax.wsdl.PortType;
import javax.wsdl.Service;
import javax.wsdl.WSDLException;
import javax.wsdl.extensions.soap.SOAPAddress;
import javax.wsdl.extensions.soap.SOAPBinding;
import javax.wsdl.extensions.soap12.SOAP12Address;
import javax.wsdl.extensions.soap12.SOAP12Binding;
import javax.wsdl.xml.WSDLWriter;
import javax.xml.namespace.QName;

import org.eclipse.bpel.common.wsdl.helpers.UriAndUrlHelper;
import org.eclipse.core.runtime.IStatus;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

/**
 * A WSDL parser for the version 1.1 of the WSDL specification.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class WsdlUtils {

	/**
	 * The unique instance of this parser.
	 */
	public static WsdlUtils INSTANCE = new WsdlUtils();

	/**
	 * The instance of the WSDL parser for the version 1.1 of WSDL.
	 */
	private javax.wsdl.xml.WSDLReader wsdlReader11;

	/**
	 * The factory to write WSDL files.
	 */
	private javax.wsdl.factory.WSDLFactory wsdlFactory11;



	/**
	 * The constructor initializes the two parsers.
	 */
	private WsdlUtils() {

		try {
			this.wsdlFactory11 = javax.wsdl.factory.WSDLFactory.newInstance();
			this.wsdlReader11 = this.wsdlFactory11.newWSDLReader();
			this.wsdlReader11.setFeature( "javax.wsdl.verbose", false ); //$NON-NLS-1$

		} catch( javax.wsdl.WSDLException e ) {
			PetalsCommonPlugin.log( e, IStatus.WARNING );
		}
	}


	/**
	 * Parses a WSDL URL, given as a string (WSDL 1.1 only).
	 * @param wsdlUri the URI of the WSDL to parse (not null).
	 * @return a list of beans containing easily accessible data
	 * @throws IllegalArgumentException if the URI is invalid
	 * @throws InvocationTargetException if the WSDL is not a valid WSDL 1.1
	 * <p>
	 * An Eclipse log entry is also created in this case.
	 * </p>
	 */
	public List<JbiBasicBean> parse( String wsdlUrlAsString ) throws IllegalArgumentException, InvocationTargetException {
		URI uri = UriAndUrlHelper.urlToUri( wsdlUrlAsString );
		return parse( uri );
	}


	/**
	 * Parses a WSDL URL (WSDL 1.1 only).
	 * @param wsdlUri the URI of the WSDL to parse
	 * @return a list of {@link JbiBasicBean}s (never null)
	 * @throws InvocationTargetException if the WSDL is not a valid WSDL 1.1
	 * <p>
	 * An Eclipse log entry is also created in this case.
	 * </p>
	 */
	public List<JbiBasicBean> parse( URI wsdlUri ) throws InvocationTargetException {

		List<JbiBasicBean> result = new ArrayList<JbiBasicBean> ();

		// Read WSDL 1.1 definitions from the URL
		try {
			Definition def = this.wsdlReader11.readWSDL( wsdlUri.toString());
			for( Iterator<?> itService = def.getServices().keySet().iterator(); itService.hasNext(); ) {

				QName serviceName = (QName) itService.next();
				Object value = def.getServices().get( serviceName );
				if( !( value instanceof javax.wsdl.Service ))
					continue;

				Service service = (javax.wsdl.Service) value;
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

						bean.setServiceName( service.getQName());
						bean.setSoapAddress( soapAddress );
						bean.setEndpointName( portName );

						PortType p = port.getBinding().getPortType();
						bean.setInterfaceName( p.getQName());

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

		} catch( WSDLException e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );
			throw new InvocationTargetException( e );
		}

		return result;
	}


	/**
	 * Updates a WSDL file by changing the end-point (port name).
     *
	 * @param file the WSDL file to parse
	 * @param serviceName the name of the service whose end-point (port name) must be updated
	 * @param newEndpoint the new end-point
	 * @return true if the update worked, false otherwise
	 */
	public boolean updateEndpointNameInWsdl( File wsdlFile, QName serviceName, String newEndpoint ) {
		return updateEndpointAndServiceNamesInWsdl( wsdlFile, serviceName, null, newEndpoint );
	}


	/**
	 * Updates a WSDL file by changing the end-point (port name) and service name.
     *
	 * @param file the WSDL file to parse
	 * @param serviceName the name of the service whose end-point (port name) and name must be updated
	 * @param newServiceName the new service name
	 * @param newEndpoint the new end-point
	 * @return true if the update worked, false otherwise
	 */
	public boolean updateEndpointAndServiceNamesInWsdl( java.io.File wsdlFile, QName serviceName, QName newServiceName, String newEndpoint ) {

		boolean updated = false;
		try {
			// Parse the WSDL to update
			Definition def = this.wsdlReader11.readWSDL( wsdlFile.toURI().toString());
			Object value = def.getServices().get( serviceName );
			if( !( value instanceof Service ))
				throw new IOException( "Invalid service properties (not found or invalid class)." );

			Service service = (Service) value;
			Map<?,?> ports = service.getPorts();
			Set<?> keyPorts = ports.keySet();

			// Find the elements to update
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

			// Update the end-point and service
			if( rightPort != null ) {
				rightPort.setName( newEndpoint );
				if( newServiceName != null )
					service.setQName( newServiceName );

				WSDLWriter writer = this.wsdlFactory11.newWSDLWriter();
				FileOutputStream fos = new FileOutputStream( wsdlFile );
				writer.writeWSDL( def, fos );
				fos.close();
				updated = true;
			}

		} catch( WSDLException e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );

		} catch( IOException e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );
		}

		return updated;
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
				URI wsdlUri,
				String itfName,
				String itfNs,
				String srvName,
				String srvNs,
				String edptName ) {

		Map<QName,Mep> result = new HashMap<QName,Mep> ();
		try {
			for( JbiBasicBean bean : parse( wsdlUri )) {
				if( bean.haveSameIdentifiers( itfName, itfNs, srvName, srvNs, edptName )) {
					result.putAll( bean.getOperations());
					break;
				}
			}

		} catch( Exception e ) {
			// nothing
		}

		return result;
	}


	/**
	 * A class use to store WSDL parsing results.
	 */
	public static class JbiBasicBean {

		private QName itfName, srvName;
		private String endpointName;
		private String soapAddress;
		private boolean portTypeExists;
		private SoapVersion soapVersion = SoapVersion.v11;
		private final Map<QName, Mep> operations = new HashMap<QName,Mep> ();


		/**
		 * @return the itfName
		 */
		public QName getInterfaceName() {
			return this.itfName;
		}

		/**
		 * @param itfName the itfName to set
		 */
		public void setInterfaceName( QName itfName ) {
			this.itfName = itfName;
		}

		/**
		 * @return the srvName
		 */
		public QName getServiceName() {
			return this.srvName;
		}

		/**
		 * @param srvName the srvName to set
		 */
		public void setServiceName( QName srvName ) {
			this.srvName = srvName;
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

			return StringUtils.areEqual( itfName, this.itfName.getLocalPart())
			&& StringUtils.areEqual( itfNs, this.itfName.getNamespaceURI())
			&& StringUtils.areEqual( srvName, this.srvName.getLocalPart())
			&& StringUtils.areEqual( srvNs, this.srvName.getNamespaceURI())
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
