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

package com.ebmwebsourcing.petals.services.su.wizards.legacy.validation;

import java.io.File;
import java.net.URI;
import java.net.URL;

/**
 * Validate a file path and check if the file exists or does not exist.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class FileValidator extends AbstractWizardValidator<String> {

	private boolean validateDoesNotExist = false;


	@Override
	public String validate( String value ) {

		if( value == null )
			return "The value is null and can not be a valid file path.";

		File file;
		try {
			try {
				URI uri = new URL( value ).toURI();
				if( uri.getScheme().equalsIgnoreCase( "file" ))
					file = new File( uri );
				else
					return null; 	// Cannot validate a remote file with a valid URI

			} catch( Exception e ) {
				file = new File( value );
			}
		} catch( Exception e ) {
			return value + " is not a valid file path.";
		}

		if( this.validateDoesNotExist && file.exists())
			return "File " + file.getAbsolutePath() + " already exists.";
		else if( !this.validateDoesNotExist && !file.exists())
			return "File " + file.getAbsolutePath() + " does not exist.";

		return null;
	}


	/**
	 * @param check true to validate that the file does not exist, false otherwise.
	 * False by default.
	 */
	public void checkFileDoesNotExist( boolean check ) {
		this.validateDoesNotExist = check;
	}
}
