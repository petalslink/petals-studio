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
 
package com.ebmwebsourcing.petals.common.generation.cdk4;

import com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class CdkProvides4 extends AbstractJbiXmlBean {

	private String timeout, wsdl;
	private Boolean downloadWsdlImport;


	/**
	 * Constructor.
	 * <p>
	 * Must be called by sub-classes.
	 * </p>
	 */
	public CdkProvides4() {
		super();
		addNamespace( "petalsCDK", "http://petals.ow2.org/components/extensions/version-4.0" );
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.jbi.core.jbixml.JbiBeanDelegate#getCdkSection()
	 */
	@Override
	public String getCdkSection() {

		String result = "";

		if( this.timeout != null && ! "0".equals( this.timeout.trim()))
			result += "\t\t\t<petalsCDK:timeout>" + this.timeout + "</petalsCDK:timeout>\n";

		if( this.downloadWsdlImport != null )
			result += "\t\t\t<petalsCDK:wsdl-imports-download>" + this.downloadWsdlImport + "</petalsCDK:wsdl-imports-download>\n";

		if( this.wsdl != null && this.wsdl.trim().length() > 0 )
			result += "\t\t\t<petalsCDK:wsdl>" + this.wsdl + "</petalsCDK:wsdl>\n";
		else
			result += "\t\t\t<petalsCDK:wsdl xsi:nil=\"true\" />\n";

		return result;
	}


	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout( String timeout ) {
		this.timeout = timeout;
	}


	/**
	 * @param wsdl the wsdl to set
	 */
	public void setWsdl( String wsdl ) {
		this.wsdl = wsdl;
	}


	/**
	 * @param downloadWsdlImport the downloadWsdlImport to set
	 */
	public void setDownloadWsdlImport( Boolean downloadWsdlImport ) {
		this.downloadWsdlImport = downloadWsdlImport;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.jbi.core.jbixml.JbiBeanDelegate#isProvides()
	 */
	@Override
	public boolean isProvides() {
		return true;
	}
}
