/****************************************************************************
 * 
 * Copyright (c) 2009-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.server.server;

/**
 * A Petals server.
 * TODO: add IURLProvider as a super interface once exploded deployment works.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public interface IPetalsServer {

	void setHost( String host );
	String getHost();

	void setWsPrefix( String wsPrefix );
	String getWsPrefix();

	void setPort( int port );
	int getPort();

	void setContainerName( String containerName );
	String getContainerName();

	String validateTopologyInformation();
}
