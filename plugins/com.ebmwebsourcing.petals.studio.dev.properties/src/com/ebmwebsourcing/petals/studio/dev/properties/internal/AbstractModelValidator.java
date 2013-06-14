/******************************************************************************
 * Copyright (c) 2012, Linagora
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.studio.dev.properties.internal;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;

import com.ebmwebsourcing.petals.studio.dev.properties.AbstractModel;

/**
 * @author Vincent Zurczak - Linagora
 */
public class AbstractModelValidator {

	private static final String MARKER_ID = "com.ebmwebsourcing.petals.studio.dev.properties.markers";


	/**
	 * Validates a properties model file.
	 * @param propertiesFile
	 */
	public static void validateAndMark( IFile propertiesFile ) {

		// Clear the markers
		clearMarkers( propertiesFile );

		// Load the current file content and get the property lines
		Map<String, Integer> propertyToLine = new HashMap<String, Integer>();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		File file = propertiesFile.getLocation().toFile();
		try {
			Utils.copyStream( file, os );
			int currentLine = 0;
			char lastChar = 'c';

			for( String line : os.toString( "UTF-8" ).split( "\n" ) ) {
				currentLine++;
				line = line.trim();

				// Eliminate some cases
				if( line.startsWith( "#" ) )
					continue;

				if( line.isEmpty() )
					continue;

				if( lastChar == '\\' ) {
					lastChar = 'c';
					continue;
				}

				int index = line.indexOf( '=' );
				if( index < 0 )
					continue;

				// Now, we have a good candidate
				String property = line.substring( 0, index ).trim();
				propertyToLine.put( property, currentLine );

				// Prepare next iteration
				lastChar = line.charAt( line.length() - 1 );
			}

		} catch( IOException e ) {
			PetalsStudioDevPlugin.log( e, IStatus.ERROR );
			return;
		}

		// Load and validate the model
		try {
			AbstractModel model = new AbstractModel( file );
			for( String key : model.getProperties().getPropertyMap().keySet()) {
				Integer line = propertyToLine.get( key );
				if( line == null ) {
					PetalsStudioDevPlugin.log( key + " property was not indexed correctly.", IStatus.ERROR );
					line = 1;
				}

				if( model.findTypeDeclaration( key ) == null )
					createMarker( propertiesFile, "No type defined for '" + key + "'. Assumption: string.", line, false );
			}

			// Otherwise, check it is correctly defined
			for( Map.Entry<String,List<String>> entry : model.validateAbstractModel().entrySet()) {
				Integer line = entry.getKey().isEmpty() ? 1 : propertyToLine.get( entry.getKey());
				if( line == null ) {
					PetalsStudioDevPlugin.log( entry.getKey() + " property was not indexed correctly.", IStatus.ERROR );
					line = 1;
				}

				for( String error :entry.getValue())
					createMarker( propertiesFile, error, line, true );
			}

		} catch( IOException e ) {
			createMarker( propertiesFile, "The file could not be loaded.", 1, true );
		}
	}


	/**
	 * Validates a properties model file.
	 * @param propertiesFile
	 */
	public static boolean validate( IFile propertiesFile ) {

		boolean valid;
		try {
			AbstractModel model = new AbstractModel( propertiesFile.getLocation().toFile());
			valid = validate( model );

		} catch( IOException e ) {
			PetalsStudioDevPlugin.log( e, IStatus.ERROR );
			valid = false;
		}

		return valid;
	}


	/**
	 * Validates a properties model file.
	 * @param model
	 */
	public static boolean validate( AbstractModel model ) {
		return model.validateAbstractModel().isEmpty();
	}


	/**
	 * Clears all the markers.
	 * @param propertiesFile
	 */
	public static void clearMarkers( IFile propertiesFile ) {
		try {
			propertiesFile.deleteMarkers( MARKER_ID, true, IResource.DEPTH_ONE );

		} catch( CoreException e ) {
			PetalsStudioDevPlugin.log( e, IStatus.ERROR );
		}
	}


	/**
	 * Creates a marker.
	 * @param propertiesFile
	 * @param message a message (not null)
	 * @param line a line number
	 * @param error true if it is an error, false for a warning
	 */
	private static void createMarker( IFile propertiesFile, String message, int line, boolean error ) {
		try {
			IMarker marker = propertiesFile.createMarker( MARKER_ID );
			marker.setAttribute( IMarker.SEVERITY, error ? IMarker.SEVERITY_ERROR : IMarker.SEVERITY_WARNING );
			marker.setAttribute( IMarker.LINE_NUMBER, line );
			marker.setAttribute( IMarker.MESSAGE, message );

		} catch( CoreException e ) {
			PetalsStudioDevPlugin.log( e, IStatus.ERROR );
		}
	}
}
