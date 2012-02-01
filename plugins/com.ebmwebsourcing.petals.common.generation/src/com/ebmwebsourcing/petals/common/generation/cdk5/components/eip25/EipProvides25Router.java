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

import java.util.ArrayList;
import java.util.List;

import com.ebmwebsourcing.petals.common.generation.cdk5.CdkProvides5;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipProvides25Router extends CdkProvides5 {

	private boolean isXpathCondition;
	private boolean faultToException;
	private final List<String> routingConditions;


	/**
	 * Constructor.
	 */
	public EipProvides25Router() {
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
		buf.append( "\t\t\t<eip:eip>router</eip:eip>\n" );

		if( this.isXpathCondition ) {
			for( String cond : this.routingConditions )
				buf.append( "\t\t\t<eip:test>" + cond + "</eip:test>\n" );
		} else {
			for( String cond : this.routingConditions )
				buf.append( "\t\t\t<eip:test-operation>" + cond + "</eip:test-operation>\n" );
		}

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
	 * @param isXpathCondition the isXpathCondition to set
	 */
	public void setXpathCondition( boolean isXpathCondition ) {
		this.isXpathCondition = isXpathCondition;
	}


	/**
	 * @param faultToException the faultToException to set
	 */
	public void setFaultToException( boolean faultToException ) {
		this.faultToException = faultToException;
	}
}
