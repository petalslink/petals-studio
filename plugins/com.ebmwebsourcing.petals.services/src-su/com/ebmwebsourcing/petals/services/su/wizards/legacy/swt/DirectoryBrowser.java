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

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;

import com.ebmwebsourcing.petals.common.internal.provisional.preferences.PreferencesManager;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class DirectoryBrowser extends BrowserWidget {

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
	public DirectoryBrowser(
				String initialValue, Composite parent,
				String baseLabel, String labelSuffix, String labelTooltip,
				boolean isOptional, boolean singleSelection, boolean alignAll ) {

		super( initialValue, parent, baseLabel, labelSuffix, labelTooltip, isOptional, alignAll );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.BrowserWidget
	 * #activateButtonListener()
	 */
	@Override
	protected void activateButtonListener() {

		DirectoryDialog dlg = new DirectoryDialog( this.text.getShell(), SWT.NONE );
		dlg.setText( "Select a Directory" );

		String path = PreferencesManager.getSavedLocation();
		if( path.trim().length() > 0 )
			dlg.setFilterPath( path );

		String fn = dlg.open();
		if( fn == null )
			return;

		path = dlg.getFilterPath();
		PreferencesManager.setSavedLocation( path );

		String filePath = new File( fn ).getAbsolutePath();
		this.text.setText( filePath );
		this.text.setSelection( filePath.length());
	}
}
