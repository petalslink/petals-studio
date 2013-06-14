/****************************************************************************
 *
 * Copyright (c) 2011-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/
package com.ebmwebsourcing.petals.common.generation;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author Vincent Zurczak - EBM WebSourcing
 */
public enum Mep {

	UNKNOWN, IN_ONLY, IN_OUT, ROBUST_IN_ONLY;


	/*
	 * (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {

		String result;
		switch( this ) {
		case UNKNOWN:
			result = "Unknown / Null";
			break;

		case IN_ONLY:
			result = "InOnly";
			break;

		case IN_OUT:
			result = "InOut";
			break;

		case ROBUST_IN_ONLY:
			result = "RobustInOnly";
			break;

		default:
			result = "";
		}

		return result;
	}


	/**
	 * A more flexible method than #valueOf(String).
	 * @param s a string that is supposed to match a MEP (can be null)
	 * @return the exact MEP if the string matches one, UNKNOWN otherwise
	 */
	public static Mep whichMep( String s ) {

		Mep result = UNKNOWN;
		for( Mep mep : values()) {
			if( mep.toString().equals( s )) {
				result = mep;
				break;
			}
		}

		return result;
	}


	/**
	 * Creates a string representation of a list of Meps.
	 * @param meps the meps (not null)
	 * @return a string (never null)
	 */
	public static String listMep( Collection<Mep> meps ) {

		StringBuilder sb = new StringBuilder();
		for( Iterator<Mep> it = meps.iterator(); it.hasNext(); ) {
			sb.append( it.next());
			if( it.hasNext())
				sb.append( ", " );
		}

		return sb.toString();
	}
}
