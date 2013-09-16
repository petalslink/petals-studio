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
 
package com.ebmwebsoucing.petals.repositories.explorer.model;

import java.net.URI;

/**
 * A class that associates a query URL with a query API.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class QueryApiBean {

	private URI queryUri;
	private RepositoryQueryApi queryApi;



	/**
	 * Constructor.
	 */
	public QueryApiBean() {
		// nothing
	}


	/**
	 * Constructor.
	 * @param queryUri
	 * @param queryApi
	 */
	public QueryApiBean( URI queryUri, RepositoryQueryApi queryApi ) {
		this.queryUri = queryUri;
		this.queryApi = queryApi;
	}


	/**
	 * @return the queryUri
	 */
	public URI getQueryUri() {
		return this.queryUri;
	}


	/**
	 * @param queryUri the queryUri to set
	 */
	public void setQueryUri( URI queryUri ) {
		this.queryUri = queryUri;
	}


	/**
	 * @return the queryApi
	 */
	public RepositoryQueryApi getQueryApi() {
		return this.queryApi;
	}


	/**
	 * @param queryApi the queryApi to set
	 */
	public void setQueryApi( RepositoryQueryApi queryApi ) {
		this.queryApi = queryApi;
	}
}
