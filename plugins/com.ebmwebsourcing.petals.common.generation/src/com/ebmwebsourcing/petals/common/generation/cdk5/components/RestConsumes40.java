/****************************************************************************
 * 
 * Copyright (c) 2009-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.generation.cdk5.components;

import com.ebmwebsourcing.petals.common.generation.cdk5.CdkConsumes5;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class RestConsumes40 extends CdkConsumes5 {

	private String address;


	/**
	 * Constructor.
	 */
	public RestConsumes40() {
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
		result += "\t\t\t<soap:address>" + this.address + "</soap:address>\n";
		result += "\t\t\t<soap:remove-root>false</soap:remove-root>\n";
		result += "\t\t\t<soap:mode>REST</soap:mode>\n";
		result += "\t\t\t<soap:rest-add-namespace-prefix>soapbc</soap:rest-add-namespace-prefix>\n";
		result += "\t\t\t<soap:rest-add-namespace-uri>false</soap:rest-add-namespace-uri>\n";
		result += "\t\t\t<soap:rest-add-namespace-prefix>false</soap:rest-add-namespace-prefix>\n";
		result += "\t\t\t<soap:rest-remove-prefix-on-response>true</soap:rest-remove-prefix-on-response>\n";
		return result;
	}


	/**
	 * @param address the address to set
	 */
	public void setAddress( String address ) {
		this.address = address;
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
