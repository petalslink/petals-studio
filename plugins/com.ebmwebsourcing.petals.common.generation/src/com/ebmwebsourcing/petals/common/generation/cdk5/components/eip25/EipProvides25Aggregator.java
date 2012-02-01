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

package com.ebmwebsourcing.petals.common.generation.cdk5.components.eip25;

import com.ebmwebsourcing.petals.common.generation.cdk5.CdkProvides5;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipProvides25Aggregator extends CdkProvides5 {

	private String test, aggregatorCorrelation;


	/**
	 * Constructor.
	 */
	public EipProvides25Aggregator() {
		super();
		addNamespace( "eip", "http://petals.ow2.org/components/eip/version-2" );
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean
	 * #getComponentSection()
	 */
	@Override
	public String getComponentSection() {

		StringBuffer buf = new StringBuffer();
		buf.append( "\t\t\t<eip:eip>aggregator</eip:eip>\n" );
		buf.append( "\t\t\t<eip:test>" + this.test + "</eip:test>\n" );
		buf.append( "\t\t\t<eip:aggregator-correlation>" + this.aggregatorCorrelation + "</eip:aggregator-correlation>" );

		return buf.toString();
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean
	 * #getSuType()
	 */
	@Override
	public final String getSuType() {
		return "EIP";
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean
	 * #isBc()
	 */
	@Override
	public boolean isBc() {
		return false;
	}


	/**
	 * @param test the test to set
	 */
	public void setTest( String test ) {
		this.test = test;
	}


	/**
	 * @param aggregatorCorrelation the aggregatorCorrelation to set
	 */
	public void setAggregatorCorrelation( String aggregatorCorrelation ) {
		this.aggregatorCorrelation = aggregatorCorrelation;
	}
}
