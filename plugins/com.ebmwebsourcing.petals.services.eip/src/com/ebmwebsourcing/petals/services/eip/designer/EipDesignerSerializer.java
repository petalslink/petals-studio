/******************************************************************************
 * Copyright (c) 2010-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.eip.designer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.geometry.Point;

import com.ebmwebsourcing.petals.services.eip.PetalsEipPlugin;
import com.ebmwebsourcing.petals.services.eip.designer.model.AbstractNode;
import com.ebmwebsourcing.petals.services.eip.designer.model.EIPtype;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipChain;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipConnection;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipNode;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipProperty;
import com.ebmwebsourcing.petals.services.eip.designer.model.Endpoint;

/**
 * A class in charge of serializing and de-serializing EIP chains.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipDesignerSerializer {

	/**
	 * The unique instance of this class.
	 */
	public static final EipDesignerSerializer INSTANCE = new EipDesignerSerializer();

	public static final String SERIAL_ID = "serial.id";
	public static final String CURRENT_SERIAL_ID = "1.0";
	public static final String CHAIN_TITLE = "chain.title";

	private static final String CHAIN_DESC = "chain.description";
	private static final String CHAIN_VERSION = "chain.version";
	private static final String CONNECTIONS = "chain.connections";
	private static final String EIPS = "chain.eips";
	private static final String ENDPOINTS = "chain.endpoints";

	private static final String CONN_PREFIX = "c.";
	private static final String CONN_TARGET = ".target";
	private static final String CONN_CONDITION_NAME = ".condition.name";
	private static final String CONN_CONDITION_VALUE = ".condition.value";
	private static final String CONN_CONSUMED_OPERATION = ".consume.operation";
	private static final String CONN_CONSUMED_MEP = ".consume.mep";
	private static final String CONN_CONSUME_ITF = ".consume.itf";
	private static final String CONN_CONSUME_SRV = ".consume.srv";
	private static final String CONN_CONSUME_EDPT = ".consume.edpt";

	private static final String SRV_NAME = "service.name";
	private static final String SRV_NS = "service.namespace";
	private static final String ITF_NAME = "interface.name";
	private static final String ITF_NS = "interface.namespace";
	private static final String EDPT_NAME = "endpoint.name";
	private static final String WSDL_URI = "wsdl.location";
	private static final String EIP_TYPE = "pattern";
	private static final String OUTGOING_CONNECTIONS = "outgoing.connections";
	private static final String LOCATION_X = "location.x";
	private static final String LOCATION_Y = "location.y";


	/**
	 * Constructor.
	 */
	private EipDesignerSerializer() {
		// nothing
	}


	/**
	 * Writes an EIP chain in an output stream.
	 * @param stream an output stream to write in (closed after having been written)
	 * @param eipChain the EIP chain to save (not null)
	 * @throws IOException if something went wrong
	 */
	public void write( OutputStream stream, EipChain eipChain ) throws IOException {

		// 1. Build a properties object
		Properties properties = new Properties();
		properties.setProperty( SERIAL_ID, CURRENT_SERIAL_ID );

		// General properties
		if( eipChain.getTitle() != null )
			properties.setProperty( CHAIN_TITLE, eipChain.getTitle());

		if( eipChain.getDescription() != null )
			properties.setProperty( CHAIN_DESC, eipChain.getDescription());

		if( eipChain.getVersion() != null )
			properties.setProperty( CHAIN_VERSION, eipChain.getVersion());


		// Connections
		StringBuilder sb = new StringBuilder();
		for( Iterator<EipConnection> it = eipChain.getConnections().iterator(); it.hasNext(); ) {
			EipConnection conn = it.next();

			// ID
			sb.append( conn.getId());
			if( it.hasNext())
				sb.append( ", " );

			// Properties of the connection
			// The only required is the target (helps to set the incoming connection).
			// Outgoing connections of EIPs will be used to define sources.
			properties.setProperty(
						CONN_PREFIX + conn.getId() + CONN_TARGET,
						String.valueOf( conn.getTarget().getId()));

			properties.setProperty(
						CONN_PREFIX + conn.getId() + CONN_CONSUME_EDPT,
						String.valueOf( conn.isConsumeEdpt()));

			properties.setProperty(
						CONN_PREFIX + conn.getId() + CONN_CONSUME_ITF,
						String.valueOf( conn.isConsumeItf()));

			properties.setProperty(
						CONN_PREFIX + conn.getId() + CONN_CONSUME_SRV,
						String.valueOf( conn.isConsumeSrv()));

			properties.setProperty(
						CONN_PREFIX + conn.getId() + CONN_CONSUMED_MEP,
						String.valueOf( conn.getConsumeMep()));

			properties.setProperty(
						CONN_PREFIX + conn.getId() + CONN_CONSUMED_OPERATION,
						String.valueOf( conn.getConsumeOperation()));

			if( conn.shouldHaveCondition()) {
				if( conn.getConditionExpression() != null ) {
					properties.setProperty(
								CONN_PREFIX + conn.getId() + CONN_CONDITION_VALUE,
								conn.getConditionExpression());
				}

				if( conn.getConditionName() != null ) {
					properties.setProperty(
								CONN_PREFIX + conn.getId() + CONN_CONDITION_NAME,
								conn.getConditionName());
				}
			}
		}

		properties.setProperty( CONNECTIONS, sb.toString());


		// End-points
		sb = new StringBuilder();
		for( Iterator<Endpoint> it = eipChain.getEndpoints().iterator(); it.hasNext(); ) {

			// List all the end-points
			Endpoint edpt = it.next();
			sb.append( edpt.getId());
			if( it.hasNext())
				sb.append( ", " );

			// Store the properties of every end-point
			String key = edpt.getId() + ".";
			if( edpt.getServiceName() != null )
				properties.setProperty( key + SRV_NAME, edpt.getServiceName());

			if( edpt.getServiceNamespace() != null )
				properties.setProperty( key + SRV_NS, edpt.getServiceNamespace());

			if( edpt.getInterfaceName() != null )
				properties.setProperty( key + ITF_NAME, edpt.getInterfaceName());

			if( edpt.getInterfaceNamespace() != null )
				properties.setProperty( key + ITF_NS, edpt.getInterfaceNamespace());

			if( edpt.getEndpointName() != null )
				properties.setProperty( key + EDPT_NAME, edpt.getEndpointName());

			// Store the location
			properties.setProperty( key + LOCATION_X, String.valueOf( edpt.getLocation().x ));
			properties.setProperty( key + LOCATION_Y, String.valueOf( edpt.getLocation().y ));
		}

		properties.setProperty( ENDPOINTS, sb.toString());


		// EIPs
		sb = new StringBuilder();
		for( Iterator<EipNode> it = eipChain.getEipNodes().iterator(); it.hasNext(); ) {

			// List all the end-points
			EipNode eip = it.next();
			sb.append( eip.getId());
			if( it.hasNext())
				sb.append( ", " );

			// Store the properties of every EIP
			String key = eip.getId() + ".";
			if( eip.getServiceName() != null )
				properties.setProperty( key + SRV_NAME, eip.getServiceName());

			if( eip.getServiceNamespace() != null )
				properties.setProperty( key + SRV_NS, eip.getServiceNamespace());

			if( eip.getInterfaceName() != null )
				properties.setProperty( key + ITF_NAME, eip.getInterfaceName());

			if( eip.getInterfaceNamespace() != null )
				properties.setProperty( key + ITF_NS, eip.getInterfaceNamespace());

			if( eip.getEndpointName() != null )
				properties.setProperty( key + EDPT_NAME, eip.getEndpointName());

			if( eip.getWsdlUri() != null )
				properties.setProperty( key + WSDL_URI, eip.getWsdlUri().toString());

			// Store the specific properties
			properties.setProperty( key + EIP_TYPE, eip.getEipType().toString());
			for( EipProperty property : eip.getSupportedProperties()) {
				String value = eip.getProperties().get( property );
				if( value != null )
					properties.setProperty( key + property, value );
			}

			// Store the outgoing connections
			StringBuilder sbConn = new StringBuilder();
			for( Iterator<EipConnection> itConn = eip.getOutgoingConnections().iterator(); itConn.hasNext(); ) {
				sbConn.append( itConn.next().getId());
				if( itConn.hasNext())
					sbConn.append( ", " );
			}

			properties.setProperty( key + OUTGOING_CONNECTIONS, sbConn.toString());

			// Store the location
			properties.setProperty( key + LOCATION_X, String.valueOf( eip.getLocation().x ));
			properties.setProperty( key + LOCATION_Y, String.valueOf( eip.getLocation().y ));
		}

		properties.setProperty( EIPS, sb.toString());


		// 2. Serialize the properties
		try {
			properties.store( stream, "EIP Diagram - Created by Petals Studio" );

		} finally {
			if( stream != null )
				stream.close();
		}
	}


	/**
	 * Writes an EIP chain in a file.
	 * @param outputFile the output file (may not exist, overwritten otherwise)
	 * @param eipChain the EIP chain to save (not null)
	 * @throws IOException if something went wrong
	 */
	public void write( File outputFile, EipChain eipChain ) throws IOException {

		if( ! outputFile.exists() && ! outputFile.createNewFile())
			throw new IOException( outputFile.getAbsolutePath() + " could not be created." );

		FileOutputStream fos = new FileOutputStream( outputFile );
		write( fos, eipChain );
	}


	/**
	 * Reads an EIP file and builds an EIP chain from its content.
	 * @param stream the input stream to read (closed after having been read)
	 * @param inputName the name of the stream to display if an error must be logged
	 * @return an EIP chain
	 * @throws IOException if something went wrong
	 */
	public EipChain read( InputStream stream, String inputName ) throws IOException {

		// 1. Load the properties file
		Properties properties = new Properties();
		try {
			properties.load( stream );

		} finally {
			if( stream != null )
				stream.close();
		}


		// 2. Create a new EIP chain from these properties
		String title = properties.getProperty( CHAIN_TITLE );
		String description = properties.getProperty( CHAIN_DESC );
		String version = properties.getProperty( CHAIN_VERSION );

		EipChain chain = new EipChain();
		chain.setTitle( title );
		chain.setDescription( description );
		chain.setVersion( version );

		Map<Integer,AbstractNode> idToNode = new HashMap<Integer,AbstractNode> ();
		List<IStatus> readingWarnings = new ArrayList<IStatus> ();
		int maxNodeId = 0, maxConnId = 0;

		// Read end-points
		String value = properties.getProperty( ENDPOINTS );
		if( value != null ) {
			for( String part : value.split( "," )) {
				part = part.trim();
				if( part.length() == 0 )
					continue;

				// Create the end-point
				int id = Integer.valueOf( part );
				if( id > maxNodeId )
					maxNodeId = id;

				Endpoint edpt = new Endpoint( id );
				chain.simplyAddEndpoint( edpt );
				edpt.setEipChain( chain );
				idToNode.put( id, edpt );

				// Read and set its properties
				edpt.setServiceName( properties.getProperty( id + "." + SRV_NAME ));
				edpt.setServiceNamespace( properties.getProperty( id + "." + SRV_NS ));
				edpt.setInterfaceName( properties.getProperty( id + "." + ITF_NAME ));
				edpt.setInterfaceNamespace( properties.getProperty( id + "." + ITF_NS ));
				edpt.setEndpointName( properties.getProperty( id + "." + EDPT_NAME ));

				// Get the location
				String loc = properties.getProperty( id + "." + LOCATION_X, null );
				if( loc != null ) {
					int x = Integer.valueOf( loc );
					loc = properties.getProperty( id + "." + LOCATION_Y, null );
					if( loc != null ) {
						int y = Integer.valueOf( loc );
						edpt.setLocation( new Point( x, y ));
					}
				}
			}
		}


		// Read EIPs
		value = properties.getProperty( EIPS );
		Map<EipNode,String> eipToOutgoingConnections = new HashMap<EipNode,String> ();
		if( value != null ) {
			for( String part : value.split( "," )) {
				part = part.trim();
				if( part.length() == 0 )
					continue;

				// Create the end-point
				int id = Integer.valueOf( part );
				if( id > maxNodeId )
					maxNodeId = id;

				String eipType = properties.getProperty( id + "." + EIP_TYPE );
				if( eipType == null ) {
					String msg = "The pattern could not be found for the EIP with ID " + id + ".";
					readingWarnings.add( new Status( IStatus.WARNING, PetalsEipPlugin.PLUGIN_ID, msg ));
					continue;
				}

				EipNode eip = new EipNode( id, EIPtype.valueOf( eipType ));
				chain.simplyAddEipNode( eip );
				eip.setEipChain( chain );
				idToNode.put( id, eip );

				String outgoingConnections = properties.getProperty( id + "." + OUTGOING_CONNECTIONS );
				if( outgoingConnections == null )
					outgoingConnections = "";
				eipToOutgoingConnections.put( eip, outgoingConnections );

				// Read and set its properties
				eip.setServiceName( properties.getProperty( id + "." + SRV_NAME ));
				eip.setServiceNamespace( properties.getProperty( id + "." + SRV_NS ));
				eip.setInterfaceName( properties.getProperty( id + "." + ITF_NAME ));
				eip.setInterfaceNamespace( properties.getProperty( id + "." + ITF_NS ));
				eip.setEndpointName( properties.getProperty( id + "." + EDPT_NAME ));
				eip.setWsdlUri( properties.getProperty( id + "." + WSDL_URI ));

				// Get the specific properties
				for( EipProperty property : eip.getSupportedProperties()) {
					value = properties.getProperty( id + "." + property.toString());
					if( value != null )
						eip.setEipProperty( property, value );
				}

				// Get the location
				String loc = properties.getProperty( id + "." + LOCATION_X, null );
				if( loc != null ) {
					int x = Integer.valueOf( loc );
					loc = properties.getProperty( id + "." + LOCATION_Y, null );
					if( loc != null ) {
						int y = Integer.valueOf( loc );
						eip.setLocation( new Point( x, y ));
					}
				}
			}
		}


		// Read connections
		Map<Integer,EipConnection> idToConnection = new HashMap<Integer,EipConnection> ();
		value = properties.getProperty( CONNECTIONS );
		if( value != null ) {
			for( String part : value.split( "," )) {
				part = part.trim();
				if( part.length() == 0 )
					continue;

				int id = Integer.valueOf( part );
				if( id > maxConnId )
					maxConnId = id;

				String targetIdAsString = properties.getProperty( CONN_PREFIX + id + CONN_TARGET );
				int targetId = targetIdAsString == null ? -1 : Integer.parseInt( targetIdAsString );

				AbstractNode target = idToNode.get( targetId );
				if( target == null ) {
					String msg = "No node could be resolved for the connection's target " + targetId + ".";
					readingWarnings.add( new Status( IStatus.WARNING, PetalsEipPlugin.PLUGIN_ID, msg ));
					continue;
				}

				EipConnection conn = new EipConnection( id, null, target );
				conn.connect();
				idToConnection.put( id, conn );

				conn.setConditionExpression( properties.getProperty( CONN_PREFIX + id + CONN_CONDITION_VALUE ));
				conn.setConditionName( properties.getProperty( CONN_PREFIX + id + CONN_CONDITION_NAME ));

				String val = properties.getProperty( CONN_PREFIX + id + CONN_CONSUMED_MEP );
				conn.setConsumeMep( "null".equals( val ) ? null : val );
				val = properties.getProperty( CONN_PREFIX + id + CONN_CONSUMED_OPERATION );
				conn.setConsumeOperation( "null".equals( val ) ? null : val );

				Boolean b = Boolean.valueOf( properties.getProperty( CONN_PREFIX + id + CONN_CONSUME_EDPT ));
				conn.setConsumeEdpt( b );
				b = Boolean.valueOf( properties.getProperty( CONN_PREFIX + id + CONN_CONSUME_ITF ));
				conn.setConsumeItf( b );
				b = Boolean.valueOf( properties.getProperty( CONN_PREFIX + id + CONN_CONSUME_SRV ));
				conn.setConsumeSrv( b );
			}
		}


		// Handle the outgoing connections of the EIPs
		for( Map.Entry<EipNode,String> entry : eipToOutgoingConnections.entrySet()) {
			for( String part : entry.getValue().split( "," )) {
				part = part.trim();
				if( part.length() == 0 )
					continue;

				int id = Integer.parseInt( part );
				EipConnection conn = idToConnection.get( id );
				if( conn == null ) {
					String msg = "No outgoing connection with the ID " + id + " could be resolved for the EIP " + entry.getKey().getId() + ".";
					readingWarnings.add( new Status( IStatus.WARNING, PetalsEipPlugin.PLUGIN_ID, msg ));
					continue;
				}

				conn.setSource( entry.getKey());
				entry.getKey().addOutgoingConnection( conn );
			}
		}


		// Update counters for new object creations
		chain.setNextNodeId( maxNodeId + 1 );
		chain.setNextConnectionId( maxConnId + 1 );


		// Log errors
		if( ! readingWarnings.isEmpty()) {
			IStatus[] children = new IStatus[ readingWarnings.size()];
			MultiStatus status = new MultiStatus(
						PetalsEipPlugin.PLUGIN_ID, 0,
						readingWarnings.toArray( children ),
						"Errors where found while parsing " + inputName + ".",
						null );

			PetalsEipPlugin.getDefault().getLog().log( status );
		}

		return chain;
	}


	/**
	 * Reads an EIP file and builds an EIP chain from its content.
	 * @param inputFile the input file (must exist)
	 * @return an EIP chain
	 * @throws IOException if something went wrong
	 */
	public EipChain read( File inputFile ) throws IOException {

		if( ! inputFile.exists())
			throw new IOException( inputFile.getAbsolutePath() + " could not be read (does not exist)." );

		FileInputStream fis = new FileInputStream( inputFile );
		return read( fis, inputFile.getAbsolutePath());
	}
}
