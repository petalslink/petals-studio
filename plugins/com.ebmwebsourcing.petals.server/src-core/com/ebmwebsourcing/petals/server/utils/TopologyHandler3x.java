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

package com.ebmwebsourcing.petals.server.utils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.NamespaceUtils;

/**
 * A handler to parse Petals (3.x) topology files.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class TopologyHandler3x extends DefaultHandler {

	private final String containerName;
	private String host, wsPrefix;
	private int port;

	private Boolean inContainer = null;
	private boolean inWs = false;
	private StringBuffer buffer;


	/**
	 * Constructor.
	 * @param containerName the container name to search in the topology
	 */
	public TopologyHandler3x( String containerName ) {
		this.containerName = containerName;
	}


	/*
	 * (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler
	 * #startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement( String uri, String localName, String qName, Attributes attributes )
	throws SAXException {

		// Never been in the right node: check the container name
		if( this.inContainer == null ) {
			String cleanName = NamespaceUtils.removeNamespaceElements( qName );
			if( cleanName.equalsIgnoreCase( "container" )) {
				String name = attributes.getValue( "name" );
				if( name != null && name.equals( this.containerName ))
					this.inContainer = true;
			}
		}

		// We are in the right container: check sub-elements
		else if( this.inContainer ) {
			String cleanName = NamespaceUtils.removeNamespaceElements( qName );
			if( cleanName.equalsIgnoreCase( "webservice-service" ))
				this.inWs = true;

			else if( cleanName.equalsIgnoreCase( "port" )
						|| cleanName.equalsIgnoreCase( "host" )
						|| cleanName.equalsIgnoreCase( "prefix" ))
				this.buffer = new StringBuffer();
		}

		// "inContainer == false"
		// <=>
		// "We were in the right container but we are not anymore."
		else {
			this.buffer = null;	// No need to store characters
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler
	 * #endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement( String uri, String localName, String qName )
	throws SAXException {

		if( this.inContainer != null && this.inContainer ) {
			String cleanName = NamespaceUtils.removeNamespaceElements( qName );
			if( cleanName.equalsIgnoreCase( "container" )) {
				this.inContainer = false;

			} else if( cleanName.equalsIgnoreCase( "host" )) {
				this.host = this.buffer.toString();

			} else if( cleanName.equalsIgnoreCase( "webservice-service" )) {
				this.inWs = false;

			} else if( cleanName.equalsIgnoreCase( "port" ) && this.inWs ) {
				this.port = Integer.parseInt( this.buffer.toString());

			} else if( cleanName.equalsIgnoreCase( "prefix" ) && this.inWs ) {
				this.wsPrefix = this.buffer.toString();
			}
		}

		this.buffer = null;
	}


	/*
	 * (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters( char[] ch, int start, int length )
	throws SAXException {

		if( this.buffer != null ) {
			String string = new String( ch, start, length );
			this.buffer.append( string );
		}
	}


	/**
	 * @return the containerName
	 */
	public String getContainerName() {
		return this.containerName;
	}


	/**
	 * @return the host
	 */
	public String getHost() {
		return this.host;
	}


	/**
	 * @return the wsPrefix
	 */
	public String getWsPrefix() {
		return this.wsPrefix;
	}


	/**
	 * @return the port
	 */
	public int getPort() {
		return this.port;
	}
}
