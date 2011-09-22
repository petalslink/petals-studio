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

package com.ebmwebsourcing.petals.services.sca;

/**
 * Manage the versions of the SCA component.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public enum ScaComponentVersion {

	v1_1;


	/*
	 * (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		String sup = super.toString();
		sup = sup.substring( 1 ).replaceAll( "_", "." );
		return sup;
	}


	/**
	 * Resolve a version to the matching enum.
	 * <p>
	 * <u>Example:</u> "1.0" must return "v1_0".
	 * </p>
	 * 
	 * @param version
	 * @return the matching enum
	 */
	public static ScaComponentVersion resolveVersion( String version ) {
		version = "v" + version.replaceAll( "\\.", "_" );
		return valueOf( version );
	}


	/**
	 * @param version
	 * @return the SCA component name (should not change with time, but we never know)
	 */
	public static String getComponentName( String version ) {
		switch( resolveVersion( version )) {
		case v1_1: return "petals-se-sca";
		}

		return "";
	}


	/**
	 * @return all the versions as an array of string
	 */
	public static String[] getAllVersions() {

		ScaComponentVersion[] versions = ScaComponentVersion.values();
		String[] versionsAS = new String[ versions.length ];
		for( int i=0; i<versions.length; i++ )
			versionsAS[ i ] = versions[ i ].toString();

		return versionsAS;
	}
}
