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

package com.ebmwebsourcing.petals.services.su.wizards.legacy.swt;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;

import com.ebmwebsourcing.petals.common.internal.provisional.preferences.PreferencesManager;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.UriUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class FileBrowser extends BrowserWidget {

	private final int style;
	protected String[] filterNames = new String[] { "Any File (*.*)" };
	protected String[] filterExtensions = new String[] { "*.*" };

	/**
	 * True to get the file URL, false to get the file path (i.e. without the URL scheme).
	 */
	protected boolean getUrl = false;



	/**
	 * Constructor.
	 * 
	 * @param initialValue
	 * @param parent
	 * @param baseLabel
	 * @param labelSuffix
	 * @param labelTooltip
	 * @param isOptional
	 * @param singleSelection
	 * @param alignAll
	 */
	public FileBrowser(
			String initialValue, Composite parent,
			String baseLabel, String labelSuffix,
			String labelTooltip, boolean isOptional,
			boolean singleSelection, boolean alignAll ) {

		super( initialValue, parent, baseLabel, labelSuffix, labelTooltip, isOptional, alignAll );
		this.style = singleSelection ? SWT.SINGLE : SWT.MULTI;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.AbstractWizardWidget#validate()
	 */
	@Override
	public String validate() {

		for( String s : getFilePaths( false )) {
			String validation = super.validate( s );
			if( validation != null )
				return validation;
		}

		return null;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.BrowserWidget
	 * #activateButtonListener()
	 */
	@Override
	protected void activateButtonListener() {

		FileDialog dlg = new FileDialog( this.text.getShell(), this.style );
		dlg.setText( "Select a file" );
		dlg.setFilterNames( this.filterNames );
		dlg.setFilterExtensions( this.filterExtensions );

		String path = PreferencesManager.getSavedLocation();
		if( path.trim().length() > 0 )
			dlg.setFilterPath( path );

		String fn = dlg.open();
		if( fn == null )
			return;


		// Process the file paths
		StringBuilder filePaths = new StringBuilder();
		try {
			path = dlg.getFilterPath();
			PreferencesManager.setSavedLocation( path );

			File parent = new File( path );
			for( Iterator<String> it = Arrays.asList( dlg.getFileNames()).iterator(); it.hasNext(); ) {

				File file = new File( parent, it.next());
				if( this.getUrl )
					filePaths.append( UriUtils.convertFilePathToUrl( file.getAbsolutePath()));
				else
					filePaths.append( file.getAbsolutePath());

				if( it.hasNext())
					filePaths.append( " ;; " );
			}
		} catch( Exception e1 ) {
			// e1.printStackTrace();
		}

		this.text.setText( filePaths.toString());
		this.text.setSelection( filePaths.length());
	}


	/**
	 * @param filterNames the filterNames to set
	 */
	public void setFilterNames( String[] filterNames ) {
		this.filterNames = filterNames;
	}


	/**
	 * @param filterExtensions the filterExtensions to set
	 */
	public void setFilterExtensions( String[] filterExtensions ) {
		this.filterExtensions = filterExtensions;
	}


	/**
	 * Parse the text value and build an array of string corresponding to the selected file paths.
	 * @param asUrl
	 * @return the selected file paths
	 */
	public String[] getFilePaths( boolean asUrl ) {

		if( StringUtils.isEmpty( getValue()))
			return new String[ 0 ];

		String[] parts = this.text.getText().split( ";;" );
		String[] result = new String[ parts.length ];
		for( int i=0; i<parts.length; i++ ) {
			result[ i ] = parts[ i ] == null ? "" : parts[ i ].trim();
			if( asUrl )
				result[ i ] = UriUtils.convertFilePathToUrl( result[ i ]);
		}

		return result;
	}
}
