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

package com.ebmwebsourcing.petals.common.internal.provisional.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.ebmwebsourcing.petals.common.internal.provisional.preferences.PreferencesManager;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.UriUtils;

/**
 * Displays a list of files, with an ADD and a REMOVE button on the right.
 * The value can never be null in this class.
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class FileList extends AbstractWizardWidget<Collection<File>> {

	protected String[] filterNames = new String[] { "Any File (*,*)" };
	protected String[] filterExtensions = new String[] { "*.*" };
	private TableViewer viewer;
	private Button addButton, removeButton;
	private Label label;



	/**
	 * Constructor.
	 * 
	 * @param initialValue
	 * @param parent
	 * @param label
	 */
	public FileList( Collection<File> initialValue, Composite parent, String label ) {

		super( initialValue, parent, null, null, null, false );
		this.labelText = label;
		if( getValue() == null )
			setValue( new ArrayList<File> ());

		createControls( parent );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.AbstractWizardWidget
	 * #setValue(java.lang.Object)
	 */
	@Override
	public void setValue( Collection<File> value ) {
		if( value == null )
			setValue( new ArrayList<File> ());
		super.setValue( value );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.AbstractWizardWidget
	 * #createControls(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createControls( Composite parent ) {

		this.label = new Label( parent, SWT.NONE );
		this.label.setText( this.labelText );
		this.label.setToolTipText( this.labelTooltip );

		Composite browser = new Composite( parent, SWT.NONE );
		browser.setLayoutData( new GridData( GridData.FILL_BOTH ));
		GridLayout layout = new GridLayout( 2, false );
		layout.marginWidth = layout.marginHeight = 0;
		browser.setLayout( layout );

		Table table = new Table( browser, SWT.MULTI | SWT.BORDER );
		table.setLayoutData( new GridData( GridData.FILL_BOTH ));
		this.viewer = new TableViewer( table );
		this.viewer.setContentProvider( new ArrayContentProvider ());
		this.viewer.setLabelProvider( new LabelProvider () {
			@Override
			public String getText( Object element ) {
				if( element instanceof File )
					return ((File) element).getAbsolutePath();
				return super.getText( element );
			}
		});

		Composite buttons = new Composite( browser, SWT.NONE );
		layout = new GridLayout ();
		layout.marginWidth = layout.marginHeight = 0;
		buttons.setLayout( layout );
		buttons.setLayoutData( new GridData( GridData.VERTICAL_ALIGN_BEGINNING ));


		// ADD button
		this.addButton = new Button( buttons, SWT.PUSH );
		this.addButton.setText( "Add" );
		this.addButton.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		this.addButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				FileDialog dlg = new FileDialog( FileList.this.viewer.getTable().getShell(), SWT.MULTI );
				dlg.setText( "Select one or several files" );
				dlg.setFilterNames( FileList.this.filterNames );
				dlg.setFilterExtensions( FileList.this.filterExtensions );

				String path = PreferencesManager.getSavedLocation();
				if( path.trim().length() > 0 )
					dlg.setFilterPath( path );

				String fn = dlg.open();
				if( fn == null )
					return;

				// Process the files
				try {
					path = dlg.getFilterPath();
					PreferencesManager.setSavedLocation( path );

					List<File> files = new ArrayList<File>();
					File parent = new File( path );
					for( String filename : dlg.getFileNames()) {
						File chosenFile = new File( parent, filename );
						files.add(chosenFile);
					}
					
					addFiles(files);
				} catch( Exception e1 ) {
					// e1.printStackTrace();
				}

			}
		});


		// REMOVE button
		this.removeButton = new Button( buttons, SWT.PUSH );
		this.removeButton.setText( "Remove" );
		this.removeButton.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		this.removeButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				Iterator<?> it = ((IStructuredSelection) FileList.this.viewer.getSelection()).iterator();
				while( it.hasNext()) {
					File f = (File) it.next();
					getValue().remove( f );
				}

				setValue( getValue());
				refreshAll();
			}
		});
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.AbstractWizardWidget
	 * #getValueAsString()
	 */
	@Override
	protected String getValueAsString() {

		StringBuilder result = new StringBuilder();
		for( TableItem ti : this.viewer.getTable().getItems())
			result.append( ti.getText() + " ;; " );

		return result.toString();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.AbstractWizardWidget
	 * #isEmptyValue()
	 */
	@Override
	protected boolean isEmptyValue() {
		return getValue().isEmpty();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.AbstractWizardWidget
	 * #refreshAll()
	 */
	@Override
	protected void refreshAll() {
		this.viewer.setInput( getValue());
		this.viewer.refresh();
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
	 * @param asUrl true if the paths should be returned as URLs
	 * @return the selected file paths
	 */
	public String[] getFilePaths( boolean asUrl ) {

		Collection<File> files = getValue();
		Collection<String> result = new ArrayList<String> ();

		if( asUrl ) {
			for( File f : files ) {
				String url = UriUtils.convertFilePathToUrl( f.getAbsolutePath());
				result.add( url );
			}
		}
		else {
			for( File f : files )
				result.add( f.getAbsolutePath());
		}

		return result.toArray( new String[ result.size()]);
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.swt.AbstractWizardWidget
	 * #setEnabled(boolean)
	 */
	@Override
	public void setEnabled( boolean enabled ) {
		this.label.setEnabled( enabled );
		this.addButton.setEnabled( enabled );
		this.removeButton.setEnabled( enabled );
		this.viewer.getTable().setEnabled( enabled );
	}

	/**
	 * Adds the files to the fileList
	 * @param files
	 */
	public void addFiles(Collection<File> files) {
		getValue().addAll( files );
		setValue( getValue());
		refreshAll();
	}
}
