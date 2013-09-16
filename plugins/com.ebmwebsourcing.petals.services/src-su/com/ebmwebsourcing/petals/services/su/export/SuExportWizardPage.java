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
 
package com.ebmwebsourcing.petals.services.su.export;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.PlatformUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.export.SuExportWizard.SuExportMode;
import com.ebmwebsourcing.petals.services.utils.ServiceProjectRelationUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SuExportWizardPage extends WizardPage {

	protected static final String PREF_DIRECTORY_LOC = "petals.services.export.su.directory";
	protected static final String PREF_FILE_LOC = "petals.services.export.su.file";

	private final Map<IProject,Boolean> suProjects = new LinkedHashMap<IProject,Boolean> ();
	private SuExportMode exportMode;
	private File outputFile;

	private boolean complete = false;
	private Label lastLabel;


	/**
	 * Constructor.
	 * @param selection
	 */
	public SuExportWizardPage( IStructuredSelection selection ) {

		super( "SU Export Page" );
		setTitle( "Service Unit Export" ); //NON-NLS-1
		setDescription( "Export one or several Service Unit projects." ); //NON-NLS-1

		for( IProject p : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
			if( ServiceProjectRelationUtils.isSuProject( p ))
				this.suProjects.put( p, false );
		}

		for( Iterator<?> it=selection.iterator(); it.hasNext(); ) {
			IProject project = PlatformUtils.getAdaptedProject( it.next());
			if( project != null
					&& this.suProjects.containsKey( project ))
				this.suProjects.put( project, true );
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {

		Composite container = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout( 2, false );
		layout.marginHeight = 10;
		layout.marginWidth = 10;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));


		// Project viewer
		Label l = new Label( container, SWT.NONE );
		l.setText( "Select the Service Unit project(s) to export:" );

		GridData layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		l.setLayoutData( layoutData );

		final TableViewer viewer = new TableViewer( container, SWT.BORDER | SWT.CHECK );
		layoutData = new GridData( GridData.FILL_BOTH );
		layoutData.heightHint = 140;
		layoutData.widthHint = 440;
		layoutData.horizontalSpan = 2;

		viewer.getTable().setLayoutData( layoutData );
		viewer.setLabelProvider( new WorkbenchLabelProvider());
		viewer.setContentProvider( new ArrayContentProvider());
		viewer.setInput( this.suProjects.keySet());

		for( TableItem item : viewer.getTable().getItems()) {
			IProject p = (IProject) item.getData();
			if( this.suProjects.get( p ))
				item.setChecked( true );
		}

		viewer.getTable().addListener( SWT.Selection, new Listener() {
			public void handleEvent( Event event ) {

				boolean checked = ((TableItem) event.item).getChecked();
				IProject p = (IProject) ((TableItem) event.item).getData();
				SuExportWizardPage.this.suProjects.put( p, checked );
				validate();
			}
		});


		// Export options
		final Composite exportComposite = new Composite( container, SWT.SHADOW_ETCHED_OUT );
		layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		exportComposite.setLayout( layout );

		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.verticalIndent = 17;
		layoutData.horizontalSpan = 2;
		exportComposite.setLayoutData( layoutData );


		l = new Label( exportComposite, SWT.NONE );
		l.setText( "Export mode:" );

		final Combo exportCombo = new Combo( exportComposite, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY );
		exportCombo.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		exportCombo.setItems( new String[] {
					"Distinct export, in a same directory",
					"Distinct export, in the project directories",
					"All-in-one export, in a same service assembly"
		});

		this.lastLabel = new Label( exportComposite, SWT.NONE );
		exportCombo.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {

				// Get the export type
				int selectionIndex = exportCombo.getSelectionIndex();

				// Delete the last two widgets
				boolean found = false;
				if( SuExportWizardPage.this.lastLabel != null ) {
					for( Control control : exportComposite.getChildren()) {
						if( found )
							control.dispose();
						else
							found = control.equals( SuExportWizardPage.this.lastLabel );
					}
				}

				// Add new widgets - separate, external directory
				if( selectionIndex == 0 ) {
					SuExportWizardPage.this.exportMode = SuExportMode.SEPARATE_IN_DIRECTORY;
					SuExportWizardPage.this.lastLabel.setVisible( true );
					SuExportWizardPage.this.lastLabel.setText( "Output directory:" );

					Composite subContainer = new Composite( exportComposite, SWT.NONE );
					GridLayout layout = new GridLayout( 2, false );
					layout.marginWidth = 0;
					layout.marginHeight = 0;
					subContainer.setLayout( layout );
					subContainer.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

					final Text dirText = new Text( subContainer, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY );
					dirText.setBackground( dirText.getDisplay().getSystemColor( SWT.COLOR_WHITE ));
					dirText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
					String loc = PetalsServicesPlugin.getDefault().getPreferenceStore().getString( PREF_DIRECTORY_LOC );
					if( loc.length() != 0 ) {
						dirText.setText( loc );
						SuExportWizardPage.this.outputFile = new File( loc );
					}

					dirText.addModifyListener( new ModifyListener() {
						public void modifyText( ModifyEvent e ) {
							SuExportWizardPage.this.outputFile = new File( dirText.getText());
							validate();
						}
					});

					final Button browseDirButton = new Button( subContainer, SWT.PUSH );
					browseDirButton.setText( "Browse..." );
					browseDirButton.addSelectionListener( new SelectionListener() {
						public void widgetSelected( SelectionEvent e ) {
							widgetDefaultSelected( e );
						}

						public void widgetDefaultSelected( SelectionEvent e ) {
							DirectoryDialog dlg = new DirectoryDialog( dirText.getShell());
							dlg.setText( "Output Directory" );
							dlg.setMessage( "Select the output directory." );
							if( SuExportWizardPage.this.outputFile != null
										&& SuExportWizardPage.this.outputFile.exists())
								dlg.setFilterPath( SuExportWizardPage.this.outputFile.getAbsolutePath());

							String loc = dlg.open();
							if( loc != null ) {
								dirText.setText( loc );
								PetalsServicesPlugin.getDefault().getPreferenceStore().setValue( PREF_DIRECTORY_LOC, loc );
							}
						}
					});
				}

				// Separate, project directories
				else if( selectionIndex == 1 ) {
					SuExportWizardPage.this.exportMode = SuExportMode.SEPARATE_IN_PROJECT;
					SuExportWizardPage.this.lastLabel.setVisible( false );
				}

				// Same SA
				else if( selectionIndex == 2 ) {
					SuExportWizardPage.this.exportMode = SuExportMode.ALL_IN_ONE;
					SuExportWizardPage.this.lastLabel.setVisible( true );
					SuExportWizardPage.this.lastLabel.setText( "Output file:" );

					Composite subContainer = new Composite( exportComposite, SWT.NONE );
					GridLayout layout = new GridLayout( 2, false );
					layout.marginWidth = 0;
					layout.marginHeight = 0;
					subContainer.setLayout( layout );
					subContainer.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

					final Text fileText = new Text( subContainer, SWT.SINGLE | SWT.BORDER );
					fileText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
					String loc = PetalsServicesPlugin.getDefault().getPreferenceStore().getString( PREF_FILE_LOC );
					if( loc.length() != 0 ) {
						fileText.setText( loc );
						SuExportWizardPage.this.outputFile = new File( loc );
					}

					fileText.addModifyListener( new ModifyListener() {
						public void modifyText( ModifyEvent e ) {
							SuExportWizardPage.this.outputFile = new File( fileText.getText());
							validate();
						}
					});

					final Button browseFileButton = new Button( subContainer, SWT.PUSH );
					browseFileButton.setText( "Browse..." );
					browseFileButton.addSelectionListener( new SelectionListener() {
						public void widgetSelected( SelectionEvent e ) {
							widgetDefaultSelected( e );
						}

						public void widgetDefaultSelected( SelectionEvent e ) {
							FileDialog dlg = new FileDialog( fileText.getShell());
							dlg.setText( "Target Service Assembly" );
							dlg.setFilterExtensions( new String[] { "*.zip" });
							dlg.setOverwrite( true );

							if( SuExportWizardPage.this.outputFile != null ) {
								dlg.setFileName( SuExportWizardPage.this.outputFile.getName());
								File parentFile = SuExportWizardPage.this.outputFile.getParentFile();
								if( parentFile != null )
									dlg.setFilterPath( parentFile.getAbsolutePath());
							}

							String loc = dlg.open();
							if( loc != null ) {
								if( ! loc.endsWith( ".zip" ))
									loc += ".zip";

								fileText.setText( loc );
								PetalsServicesPlugin.getDefault().getPreferenceStore().setValue( PREF_FILE_LOC, loc );
							}
						}
					});
				}

				// Refresh the container
				exportComposite.layout();
				validate();
			}
		});


		// Last UI details
		setControl( container );
		viewer.getTable().setFocus();
		exportCombo.select( 0 );
		exportCombo.notifyListeners( SWT.Selection, new Event());

		String msg = getErrorMessage();
		if( msg != null ) {
			setErrorMessage( null );
			setMessage( msg, IMessageProvider.INFORMATION );
		}
	}


	/**
	 * Validates the page entries.
	 */
	private void validate() {

		setMessage( null, IMessageProvider.INFORMATION );

		if( getSuProjectsToExport().isEmpty())
			updateStatus( "You must select at least one Service Unit project to export." );
		else if( this.exportMode == null )
			updateStatus( "You have to select an export mode." );
		else if( this.exportMode == SuExportMode.ALL_IN_ONE ) {
			if( this.outputFile == null || this.outputFile.getAbsolutePath().trim().length() == 0 )
				updateStatus( "You have to provide a valid file path." );
			else if( this.outputFile.exists() && ! this.outputFile.isFile())
				updateStatus( "The destination already exists and is not a file." );
			else
				updateStatus( null );
		}
		else if( this.exportMode == SuExportMode.SEPARATE_IN_DIRECTORY &&
					( this.outputFile == null || ! this.outputFile.exists() || ! this.outputFile.isDirectory()))
			updateStatus( "You have to provide a valid directory path." );
		else
			updateStatus( null );
	}


	/**
	 * Updates the page status.
	 * @param msg
	 */
	private void updateStatus( String msg ) {
		setErrorMessage( msg );
		setPageComplete( msg == null );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.WizardPage
	 * #isPageComplete()
	 */
	@Override
	public boolean isPageComplete() {
		return this.complete;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.WizardPage
	 * #setPageComplete(boolean)
	 */
	@Override
	public void setPageComplete( boolean complete ) {
		this.complete = complete;
		super.setPageComplete( complete );
	}


	/**
	 * Gets the SU projects to export.
	 * @return the list of SU projects to export
	 */
	public Collection<IProject> getSuProjectsToExport() {

		List<IProject> result = new ArrayList<IProject>();
		for( Map.Entry<IProject,Boolean> entry : this.suProjects.entrySet()) {
			if( entry.getValue())
				result.add( entry.getKey());
		}

		return result;
	}


	/**
	 * @return the exportMode
	 */
	public SuExportMode getExportMode() {
		return this.exportMode;
	}


	/**
	 * @return the outputFile
	 */
	public File getOutputFile() {
		return this.outputFile;
	}
}
