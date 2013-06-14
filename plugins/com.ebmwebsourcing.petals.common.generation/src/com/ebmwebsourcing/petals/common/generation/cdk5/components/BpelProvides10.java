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

import com.ebmwebsourcing.petals.common.generation.cdk5.CdkProvides5;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class BpelProvides10 extends CdkProvides5 {

	private String processName;
	private int poolSize = 0;


	/**
	 * Constructor.
	 */
	public BpelProvides10() {
		super();
		addNamespace( "bpel", "http://petals.ow2.org/components/petals-bpel-engine/version-1" );
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean
	 * #getComponentSection()
	 */
	@Override
	public String getComponentSection() {

		StringBuffer buf = new StringBuffer();
		buf.append( "\t\t\t<bpel:bpel>" + this.processName + "</bpel:bpel>\n" );
		buf.append( "\t\t\t<bpel:poolsize>" + this.poolSize + "</bpel:poolsize>\n" );
		return buf.toString();
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean
	 * #getSuType()
	 */
	@Override
	public String getSuType() {
		return "BPEL";
	}


	/**
	 * @param processName the processName to set
	 */
	public void setProcessName( String processName ) {
		this.processName = processName;
	}


	/**
	 * @param poolSize the poolSize to set
	 */
	public void setPoolSize( int poolSize ) {
		this.poolSize = poolSize;
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
}
