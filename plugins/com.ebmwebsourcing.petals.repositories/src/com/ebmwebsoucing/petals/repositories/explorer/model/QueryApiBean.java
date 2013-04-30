/****************************************************************************
 * 
 * Copyright (c) 2010-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
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
