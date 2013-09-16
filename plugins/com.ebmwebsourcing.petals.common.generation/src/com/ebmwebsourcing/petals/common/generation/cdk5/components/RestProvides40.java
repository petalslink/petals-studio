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
 
package com.ebmwebsourcing.petals.common.generation.cdk5.components;

import com.ebmwebsourcing.petals.common.generation.cdk5.CdkProvides5;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class RestProvides40 extends CdkProvides5 {

	private String serviceAddress;
	private RestMode restMode;


	public enum RestMode {
		put, get, post;
	}


	/**
	 * Constructor.
	 */
	public RestProvides40() {
		super();
		addNamespace( "soap", "http://petals.ow2.org/components/soap/version-4" );
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean
	 * #getComponentSection()
	 */
	@Override
	public String getComponentSection() {

		String result = "";
		result += "\t\t\t<soap:address>" + this.serviceAddress + "</soap:address>\n";
		result += "\t\t\t<soap:soap-version>1.1</soap:soap-version>\n";
		result += "\t\t\t<soap:add-root>false</soap:add-root>\n";
		result += "\t\t\t<soap:chunked-mode>false</soap:chunked-mode>\n";
		result += "\t\t\t<soap:cleanup-transport>true</soap:cleanup-transport>\n";
		result += "\t\t\t<soap:mode>REST</soap:mode>\n";
		result += "\t\t\t<soap:rest-http-method>" + this.restMode + "</soap:rest-http-method>\n";

		return result;
	}


	/**
	 * @param serviceAddress the serviceAddress to set
	 */
	public void setServiceAddress( String serviceAddress ) {
		this.serviceAddress = serviceAddress;
	}


	/**
	 * @param restMode the restMode to set
	 */
	public void setRestMode( RestMode restMode ) {
		this.restMode = restMode;
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean
	 * #getSuType()
	 */
	@Override
	public String getSuType() {
		return "REST";
	}
}
