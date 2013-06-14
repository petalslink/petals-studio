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

package com.ebmwebsourcing.petals.common.generation.cdk5.components.eip25;

import java.util.ArrayList;
import java.util.List;

import com.ebmwebsourcing.petals.common.generation.cdk5.CdkProvides5;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipProvides25DynamicRouter extends CdkProvides5 {

	private boolean faultToException;
	private final List<String> routingConditions;


	/**
	 * Constructor.
	 */
	public EipProvides25DynamicRouter() {
		super();
		this.routingConditions = new ArrayList<String> ();
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
		buf.append( "\t\t\t<eip:eip>dynamic-router</eip:eip>\n" );
		for( String cond : this.routingConditions )
			buf.append( "\t\t\t<eip:test>" + cond + "</eip:test>\n" );

		buf.append( "\t\t\t<eip:fault-to-exception>" + this.faultToException + "</eip:fault-to-exception>" );
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


	/**
	 * @param e
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean addRoutingCondition( String e ) {
		return this.routingConditions.add( e );
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
	 * @param faultToException the faultToException to set
	 */
	public void setFaultToException( boolean faultToException ) {
		this.faultToException = faultToException;
	}
}
