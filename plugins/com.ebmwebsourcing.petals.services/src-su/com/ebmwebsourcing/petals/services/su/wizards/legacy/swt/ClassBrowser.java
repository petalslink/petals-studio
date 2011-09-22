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
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ClassBrowser extends BrowserWidget {

	/**
	 * The list of selected jar files.
	 */
	private List<File> jarFiles = Collections.emptyList();

	/**
	 * The names of the classes embedded into the selected jar.
	 */
	private final Collection<String> classNames = new HashSet<String> ();

	/**
	 * The minimal length from which the "browse" button is enabled.
	 */
	private int minimalLength = 0;


	/**
	 * Constructor.
	 *
	 * @param initialValue
	 * @param parent
	 * @param baseLabel
	 * @param labelSuffix
	 * @param labelTooltip
	 * @param isOptional
	 * @param alignAll
	 */
	public ClassBrowser(
				String initialValue, Composite parent,
				String baseLabel, String labelSuffix,
				String labelTooltip, boolean isOptional, boolean alignAll ) {

		super( initialValue, parent, baseLabel, labelSuffix, labelTooltip, isOptional, alignAll );
		addListener( new AbstractWizardListener () {
			public void valueHasChanged() {
				if( getValue().trim().length() > ClassBrowser.this.minimalLength )
					ClassBrowser.this.browse.setEnabled( true );
				else
					ClassBrowser.this.browse.setEnabled( false );
			}
		});
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.BrowserWidget
	 * #activateButtonListener()
	 */
	@Override
	protected void activateButtonListener() {

		ElementListSelectionDialog dlg =
			new ElementListSelectionDialog( this.text.getShell(), new LabelProvider());

		dlg.setTitle( "Class Selection" );
		dlg.setElements( this.classNames.toArray());
		dlg.setMessage( "Select the service class" );
		dlg.setFilter( this.text.getText());
		dlg.setAllowDuplicates( false );
		dlg.setIgnoreCase( false );
		dlg.setMultipleSelection( false );

		if( dlg.open() == Window.OK ) {
			String selection = (String) dlg.getFirstResult();
			this.text.setText( selection );
			this.text.setSelection( selection.length());
		}
	}


	/**
	 * @param jarFiles the jarFiles to set
	 */
	public void setJarFiles( List<File> jarFiles ) {

		if( jarFiles == null )
			this.jarFiles = Collections.emptyList();
		else
			this.jarFiles = jarFiles;

		updateClassList();
	}


	/**
	 * Update the class list from the selected jar files.
	 * This list should be updated each time the jar selection changes.
	 * 
	 * If the jar was in the workspace, we could use {@link JavaCore#createJarPackageFragmentRootFrom(IFile)}
	 */
	private void updateClassList() {

		this.classNames.clear();
		for( File jarF : this.jarFiles ) {
			JarFile jarFile;

			try {
				jarFile = new JarFile( jarF );
			} catch( IOException e ) {
				e.printStackTrace();
				continue;
			}

			String filter = this.text.getText();
			if( filter.length() > this.minimalLength )
				filter = filter.substring( 0, this.minimalLength );

			Enumeration<JarEntry> entries = jarFile.entries();
			while( entries.hasMoreElements()) {
				String entryName = entries.nextElement().getName();
				if( entryName.endsWith( ".class" ) && !entryName.contains( "$" )) {
					entryName = entryName.substring( 0, entryName.length() - 6 );
					entryName = entryName.replaceAll( "/", "." );

					if( entryName.startsWith( filter ))
						this.classNames.add( entryName );
				}
			}
		}
	}


	/**
	 * @param minimalLength the minimalLength to set
	 */
	public void setMinimalLength( int minimalLength ) {

		this.minimalLength = minimalLength;
		if( minimalLength > 0 ) {

			this.browse.setEnabled( false );
			String tooltip =
				"Enabled when more than "
				+ minimalLength + " characters have been typed in the text field";
			this.text.setToolTipText( tooltip );
			this.browse.setToolTipText( tooltip );
		}
	}
}
