/******************************************************************************
 * Copyright (c) 2010-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.wizards.beans;

import com.ebmwebsourcing.petals.common.generation.JbiUtils;

/**
 * A factory that simplifies the creation of {@link SuImportBean}s.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SuImportBeanBuilder {

	/**
	 * The unique instance of this class.
	 */
	public static final SuImportBeanBuilder INSTANCE = new SuImportBeanBuilder();

	/**
	 * An enumeration of component IDs.
	 */
	public static enum ComponentId {
		SOAP, SCA, BPEL;
	}

	/**
	 * An exception which is raised if this factory does not support some parameter values.
	 */
	public static class UnsupportedComponentException extends Exception {

		/**
		 * The serial ID.
		 */
		private static final long serialVersionUID = 5102945354554848109L;

		/**
		 * Constructor.
		 * @param msg
		 */
		public UnsupportedComponentException( String msg ) {
			super( msg );
		}
	}


	/**
	 * Private constructor.
	 */
	private SuImportBeanBuilder() {
		// nothing
	}


	/**
	 * Creates and initializes a news instance of {@link SuImportBean}.
	 * @param componentId the target component
	 * @param isConsume true if the SU is a consumes one, false for a provides one
	 * @param serviceName the service name, to generate the project name
	 * @return a configured instance of {@link SuImportBean}
	 * @throws UnsupportedComponentException if the parameters are invalid or not supported
	 */
	public SuImportBean buildSuImportBean( ComponentId componentId, boolean isConsume, String serviceName )
	throws UnsupportedComponentException {

		SuImportBean result = new SuImportBean();
		result.setConsume( isConsume );
		result.setToCreate( true );

		String projectName;
		switch( componentId ) {
		case SOAP:
			result.setComponentName( "petals-bc-soap" );
			result.setSupportedVersions( new String[] { "4.0" });
			result.setSuType( "SOAP" );
			projectName = JbiUtils.createSuName( "SOAP", serviceName, isConsume );
			break;

		case BPEL:
			if( isConsume )
				throw new UnsupportedComponentException( "BPEL does not support the 'consume' mode." );

			result.setComponentName( "petals-se-bpel" );
			result.setSupportedVersions( new String[] { "1.0" });
			result.setSuType( "BPEL" );
			projectName = JbiUtils.createSuName( "BPEL", serviceName, isConsume );
			break;

		case SCA:
			if( isConsume )
				throw new UnsupportedComponentException( "SCA does not support the 'consume' mode." );

			result.setComponentName( "petals-se-sca" );
			result.setSupportedVersions( new String[] { "1.1" });
			result.setSuType( "SCA" );
			projectName = JbiUtils.createSuName( "SCA", serviceName, isConsume );
			break;

		default:
			throw new UnsupportedComponentException( "The component " + componentId + " is not supported by this factory." );
		}

		result.setProjectName( projectName );
		return result;
	}
}
