/******************************************************************************
 * Copyright (c) 2008-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.common.internal.provisional.ui;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class FileUrlFieldEditor extends FileFieldEditor {


	/**
	 * Constructor.
	 * @param name
	 * @param labelText
	 * @param enforceAbsolute
	 * @param parent
	 * @see FileFieldEditor
	 */
	public FileUrlFieldEditor( String name, String labelText, boolean enforceAbsolute, Composite parent ) {
		super( name, labelText, enforceAbsolute, parent );
		setErrorMessage( "The value is neither the location of an existing file, nor a valid URL." );
	}


	/**
	 * Constructor.
	 * 
	 * @param name
	 * @param labelText
	 * @param enforceAbsolute
	 * @param validationStrategy
	 * @param parent
	 * @see FileFieldEditor
	 */
	public FileUrlFieldEditor( String name, String labelText, boolean enforceAbsolute, int validationStrategy, Composite parent ) {
		super( name, labelText, enforceAbsolute, parent );
		setValidateStrategy( validationStrategy );
		setErrorMessage( "The value is neither the location of an existing file, nor a valid URL." );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.FileFieldEditor#checkState()
	 */
	@Override
	protected boolean checkState() {

		String msg = null;
		String value = getTextControl().getText();
		value = value == null ? "" : value.trim();

		try {
			if( value.length() > 0 ) {
				try {
					File f = new File( value );
					if( !f.exists() || !f.isFile())
						throw new Exception();

				} catch( Exception e ) {
					new URL( value );
				}
			}
			else if( !isEmptyStringAllowed())
				msg = getErrorMessage();

		} catch( MalformedURLException e ) {
			msg = getErrorMessage();
		}


		showErrorMessage( msg );
		return msg == null;
	}
}
