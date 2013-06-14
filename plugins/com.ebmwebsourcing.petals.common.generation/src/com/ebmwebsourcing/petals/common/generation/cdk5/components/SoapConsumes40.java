/****************************************************************************
 * 
 * Copyright (c) 2009-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.generation.cdk5.components;

import com.ebmwebsourcing.petals.common.generation.cdk5.CdkConsumes5;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SoapConsumes40 extends CdkConsumes5 {

	private String address;


	/**
	 * Constructor.
	 */
	public SoapConsumes40() {
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
		result += "\t\t\t<soap:mode>SOAP</soap:mode>\n";
		result += "\t\t\t<soap:rest-add-namespace-prefix>soapbc</soap:rest-add-namespace-prefix>\n";

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
		return "SOAP";
	}
}
