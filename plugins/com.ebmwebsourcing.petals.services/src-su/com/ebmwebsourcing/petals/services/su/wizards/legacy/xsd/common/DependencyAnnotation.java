/****************************************************************************
 * 
 * Copyright (c) 2008-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common;

import java.io.Serializable;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class DependencyAnnotation implements Serializable {

	/** */
	private static final long serialVersionUID = 6537033182349344583L;
	protected String name, value;



	/**
	 * @param name
	 */
	public DependencyAnnotation( String name ) {
		this.name = name;
	}


	/**
	 * @param name
	 * @param value
	 */
	public DependencyAnnotation( String name, String value ) {
		this.name = name;
		this.value = value;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}


	/**
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {

		if( obj == null || !( obj instanceof DependencyAnnotation ))
			return false;

		DependencyAnnotation ann = (DependencyAnnotation) obj;
		if( this.name == null )
			return ann.name == null;

		return this.name.equals( ann.name );
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.name;
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int n = this.name == null ? 7 : ( this.name.length()) * 19 * (this.name.length() > 1 ? ((int) this.name.charAt( 0 )) : 11 );
		int v = this.value == null ? 3 : ( this.value.length()) * 31 * (this.value.length() > 1 ? ((int) this.value.charAt( 0 )) : 7 );
		return n * v;
	}
}
