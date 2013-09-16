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
 
package com.ebmwebsourcing.petals.services.preferences;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.ebmwebsourcing.petals.common.internal.provisional.preferences.PreferencesManager;
import com.ebmwebsourcing.petals.common.internal.provisional.ui.FileUrlFieldEditor;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.maven.PetalsServicePomManager;

/**
 * The Petals preference page for Maven.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class MavenPreferencePage
extends PreferencePage
implements IWorkbenchPreferencePage {

	private BooleanFieldEditor autoPomUpdateField;
	private DirectoryFieldEditor customizedPomLocationField;
	private StringFieldEditor pluginVersionField, groupIdField;
	private FileUrlFieldEditor pomParentField;

	private Button defaultButton, customizedButton;
	private boolean useCustomizedPom;


	/**
	 * Constructor.
	 */
	public MavenPreferencePage() {
		super( "Petals Preferences for Maven" );

		setTitle( "Petals Preferences for Maven" );
		setDescription( "The preference page to configure Maven elements." );
		setPreferenceStore( PreferencesManager.getPreferenceStore());
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage
	 * #init(org.eclipse.ui.IWorkbench)
	 */
	public void init( IWorkbench workbench ) {
		this.useCustomizedPom = PreferencesManager.useCustomizedTemplates();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage
	 * #createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createContents( Composite parent ) {

		Composite container = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout();
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));


		// Petals Maven plug-in
		Group group = new Group( container, SWT.NONE );
		group.setLayout( new GridLayout( 3, false ));
		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 3;
		layoutData.verticalIndent = 4;
		group.setLayoutData( layoutData );
		group.setText( "Petals Maven plug-in" );

		Composite subContainer = new Composite( group, SWT.NONE );
		subContainer.setLayout( new GridLayout());
		subContainer.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		this.pluginVersionField = new StringFieldEditor(
					PreferencesManager.PREFS_MAVEN_PLUGIN_VERSION,
					"Plugin Version:",
					StringFieldEditor.UNLIMITED,
					subContainer );
		this.pluginVersionField.fillIntoGrid( subContainer, 3 );
		this.pluginVersionField.setPage( this );
		this.pluginVersionField.setPreferenceStore( getPreferenceStore());
		this.pluginVersionField.load();

		this.groupIdField = new StringFieldEditor(
					PreferencesManager.PREFS_MAVEN_GROUP_ID,
					"Group ID:",
					StringFieldEditor.UNLIMITED,
					subContainer );
		this.groupIdField.fillIntoGrid( subContainer, 3 );
		this.groupIdField.setPage( this );
		this.groupIdField.setPreferenceStore( getPreferenceStore());
		this.groupIdField.load();

		this.pomParentField = new FileUrlFieldEditor(
					PreferencesManager.PREFS_MAVEN_POM_PARENT,
					"POM Parent:",
					true,
					StringFieldEditor.VALIDATE_ON_KEY_STROKE,
					subContainer );
		this.pomParentField.setFileExtensions( new String[] { "*.xml" } );
		this.pomParentField.setPage( this );
		this.pomParentField.setPreferenceStore( getPreferenceStore());
		this.pomParentField.load();


		// Work with customized POM
		group = new Group( container, SWT.NONE );
		group.setLayout( new GridLayout( 4, false ));
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 3;
		layoutData.verticalIndent = 10;
		group.setLayoutData( layoutData );
		group.setText( "POM customization" );

		subContainer = new Composite( group, SWT.NONE );
		subContainer.setLayout( new GridLayout());
		subContainer.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		this.defaultButton = new Button( subContainer, SWT.RADIO );
		this.defaultButton.setText( "Use default POM" );
		layoutData = new GridData();
		layoutData.horizontalSpan = 3;
		this.defaultButton.setLayoutData( layoutData );

		this.customizedButton = new Button( subContainer, SWT.RADIO );
		this.customizedButton.setText( "Use customized POM" );
		layoutData = new GridData();
		layoutData.horizontalSpan = 3;
		this.customizedButton.setLayoutData( layoutData );


		// The next field must only validate the location if it is enabled
		this.customizedPomLocationField = new DirectoryFieldEditor(
					PreferencesManager.PREFS_CUSTOMIZED_POM_LOCATION,
					"POM templates:",
					subContainer ) {

			@Override
			protected boolean checkState() {

				boolean result = true;
				if( MavenPreferencePage.this.useCustomizedPom )
					result = super.checkState();
				else
					clearErrorMessage();

				return result;
			}

			@Override
			public void setEnabled( boolean enabled, Composite parent ) {
				super.setEnabled( enabled, parent );
				valueChanged();
			}
		};

		this.customizedPomLocationField.setErrorMessage( "The POM templates location is not a valid directory." );
		this.customizedPomLocationField.setPage( this );
		this.customizedPomLocationField.setPreferenceStore( getPreferenceStore());
		this.customizedPomLocationField.load();


		// Add a hyper link to generate the default POM
		final Link hyperlink = new Link( subContainer, SWT.NONE );
		hyperlink.setText( "<A>Generate the default POM templates</A>" );
		layoutData = new GridData( SWT.TRAIL, SWT.DEFAULT, true, false );
		layoutData.horizontalSpan = 2;
		hyperlink.setLayoutData( layoutData );


		// Add the listeners
		this.customizedPomLocationField.setPropertyChangeListener( new IPropertyChangeListener() {
			public void propertyChange( PropertyChangeEvent event ) {
				if( FieldEditor.VALUE.equals( event.getProperty())) {

					boolean valid = MavenPreferencePage.this.customizedPomLocationField.isValid();
					hyperlink.setEnabled( valid );
					setValid( valid );
				}
			}
		});

		SelectionListener selectionListener = new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				MavenPreferencePage.this.useCustomizedPom = MavenPreferencePage.this.customizedButton.getSelection();
				MavenPreferencePage.this.customizedPomLocationField.setEnabled(
							MavenPreferencePage.this.useCustomizedPom,
							MavenPreferencePage.this.customizedButton.getParent());

				if( MavenPreferencePage.this.useCustomizedPom )
					hyperlink.setEnabled( MavenPreferencePage.this.customizedPomLocationField.isValid());
				else
					hyperlink.setEnabled( false );
			}
		};

		this.defaultButton.addSelectionListener( selectionListener );
		this.customizedButton.addSelectionListener( selectionListener );
		this.defaultButton.setSelection( ! this.useCustomizedPom );
		this.customizedButton.setSelection( this.useCustomizedPom );
		this.customizedButton.notifyListeners( SWT.Selection, new Event());

		hyperlink.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				// Get the situation
				File rootDirectory = new File( MavenPreferencePage.this.customizedPomLocationField.getStringValue());
				File suPom = new File( rootDirectory, PetalsServicePomManager.DEFAULT_SU_POM );
				File saPom = new File( rootDirectory, PetalsServicePomManager.DEFAULT_SA_POM );

				boolean overwrite = false;
				if( suPom.exists() || saPom.exists()) {
					String msg = "Some of the default POM templates already exist.\nDo you want to overwrite them?";
					overwrite = MessageDialog.openQuestion( hyperlink.getShell(), "Overwrite Templates", msg );
				}

				// Create the SU template
				boolean ok = true;
				if( ! suPom.exists() || overwrite ) {
					File tpl = getBundledTemplateFile( true );
					try {
						IoUtils.copyStream( tpl, suPom );

					} catch( IOException e1 ) {
						ok = false;
						PetalsServicesPlugin.log( e1, IStatus.ERROR );
					}
				}

				// Create the SA template
				if( ! saPom.exists() || overwrite ) {
					File tpl = getBundledTemplateFile( false );
					try {
						IoUtils.copyStream( tpl, saPom );

					} catch( IOException e1 ) {
						ok = false;
						PetalsServicesPlugin.log( e1, IStatus.ERROR );
					}
				}

				// Report the result
				if( ok ) {
					MessageDialog.openInformation(
								hyperlink.getShell(),
								"Successful Creation",
					"The default POM templates were successfully created." );
				} else {
					MessageDialog.openError(
								hyperlink.getShell(),
								"Error during the Creation",
					"The default POM templates could not be created correctly.\nCheck the log for more details." );
				}
			}
		});


		// Update POM dependencies automatically
		group = new Group( container, SWT.NONE );
		group.setLayout( new GridLayout());
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 3;
		layoutData.verticalIndent = 10;
		group.setLayoutData( layoutData );
		group.setText( "POM dependencies" );

		subContainer = new Composite( group, SWT.NONE );
		subContainer.setLayout( new GridLayout());

		this.autoPomUpdateField = new BooleanFieldEditor(
					PreferencesManager.PREFS_UPDATE_MAVEN_POM,
					"Update POM dependencies automatically (SA projects)",
					subContainer );
		this.autoPomUpdateField.setPage( this );
		this.autoPomUpdateField.setPreferenceStore( getPreferenceStore());
		this.autoPomUpdateField.load();

		return container;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage
	 * #performOk()
	 */
	@Override
	public boolean performOk() {

		this.autoPomUpdateField.store();
		this.customizedPomLocationField.store();
		this.groupIdField.store();
		this.pluginVersionField.store();
		this.pomParentField.store();

		getPreferenceStore().setValue( PreferencesManager.PREFS_USE_CUSTOMIZED_POM, this.useCustomizedPom );
		return super.performOk();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage
	 * #performDefaults()
	 */
	@Override
	protected void performDefaults() {

		this.autoPomUpdateField.loadDefault();
		this.customizedPomLocationField.loadDefault();
		this.groupIdField.loadDefault();
		this.pluginVersionField.loadDefault();
		this.pomParentField.loadDefault();

		this.defaultButton.setSelection( true );
		this.customizedButton.setSelection( false );
		this.defaultButton.notifyListeners( SWT.Selection, new Event());

		super.performDefaults();
	}


	/**
	 * Gets a template file from the bundle.
	 * @param su true to get the default SU template, false for the SA's one
	 * @return the template file
	 */
	private File getBundledTemplateFile( boolean su ) {

		File result = null;
		String entry;
		if( su )
			entry = "pom/" + PetalsServicePomManager.DEFAULT_SU_POM;
		else
			entry = "pom/" + PetalsServicePomManager.DEFAULT_SA_POM;

		try {
			URL url = PetalsServicesPlugin.getDefault().getBundle().getEntry( entry );
			IPath path = new Path( FileLocator.toFileURL( url ).getPath());
			result = path.toFile();

		} catch( IOException e ) {
			PetalsServicesPlugin.log( e, IStatus.ERROR );
		}

		return result;
	}
}
