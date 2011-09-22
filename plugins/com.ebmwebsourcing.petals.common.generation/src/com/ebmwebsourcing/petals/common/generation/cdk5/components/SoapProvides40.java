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

package com.ebmwebsourcing.petals.common.generation.cdk5.components;

import com.ebmwebsourcing.petals.common.generation.cdk5.CdkProvides5;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SoapProvides40 extends CdkProvides5 {

	private SoapVersion soapVersion = SoapVersion.v11;
	private String serviceAddress;


	/**
	 * The possible SOAP versions that can be used in the SOAP component.
	 */
	public static enum SoapVersion {
		v11, v12;

		@Override
		public String toString() {
			if( this == v12 )
				return "1.2";
			return "1.1";
		}
	}


	/**
	 * Constructor.
	 */
	public SoapProvides40() {
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
		result += "\t\t\t<soap:soap-version>" + this.soapVersion.toString() + "</soap:soap-version>\n";
		result += "\t\t\t<soap:chunked-mode>false</soap:chunked-mode>\n";
		result += "\t\t\t<soap:cleanup-transport>true</soap:cleanup-transport>\n";
		result += "\t\t\t<soap:mode>SOAP</soap:mode>\n";

		return result;
	}


	/**
	 * @param soapVersion the soapVersion to set
	 */
	public void setSoapVersion( SoapVersion soapVersion ) {
		this.soapVersion = soapVersion;
	}


	/**
	 * @param serviceAddress the serviceAddress to set
	 */
	public void setServiceAddress( String serviceAddress ) {
		this.serviceAddress = serviceAddress;
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean
	 * #getSuType()
	 */
	@Override
	public String getSuType() {
		return "SOAP";
	}
}
