/******************************************************************************
 * Copyright (c) 2009-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
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
