/****************************************************************************
 * 
 * Copyright (c) 2010-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.sa.export;

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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.sa.export.SaExportWizard.SaExportMode;
import com.ebmwebsourcing.petals.services.utils.ServiceProjectRelationUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SaExportWizardPage extends WizardPage {

	private static final String PREF_DIRECTORY_LOC = "petals.services.export.sa.directory";

	private final Map<IProject,Boolean> saProjects = new LinkedHashMap<IProject,Boolean> ();
	private SaExportMode exportMode;
	private File outputFile;

	private boolean complete = false;


	/**
	 * Constructor.
	 * @param selection
	 */
	public SaExportWizardPage( IStructuredSelection selection ) {

		super( "SA Export Page" );
		setTitle( "Service Assembly Export" ); //NON-NLS-1
		setDescription( "Export one or several Service Assembly projects." ); //NON-NLS-1

		for( IProject p : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
			if( projectMatches( p ))
				this.saProjects.put( p, false );
		}

		for( Iterator<?> it=selection.iterator(); it.hasNext(); ) {
			Object o = it.next();
			if( o instanceof IProject ) {
				IProject p = (IProject) o;
				if( this.saProjects.containsKey( p ))
					this.saProjects.put( p, true );
			}
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {

		Composite container = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout();
		layout.marginHeight = 10;
		layout.marginWidth = 10;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));


		new Label( container, SWT.NONE ).setText( "Select the Service Assembly project(s) to export:" );


		// Project viewer
		TableViewer viewer = new TableViewer( container, SWT.BORDER | SWT.CHECK );
		GridData layoutData = new GridData( GridData.FILL_BOTH );
		layoutData.heightHint = 180;
		viewer.getTable().setLayoutData( layoutData );
		viewer.setLabelProvider( new WorkbenchLabelProvider());
		viewer.setContentProvider( new ArrayContentProvider());
		viewer.setInput( this.saProjects.keySet());

		for( TableItem item : viewer.getTable().getItems()) {
			IProject p = (IProject) item.getData();
			if( this.saProjects.get( p ))
				item.setChecked( true );
		}

		viewer.getTable().addListener( SWT.Selection, new Listener() {
			public void handleEvent( Event event ) {

				boolean checked = ((TableItem) event.item).getChecked();
				IProject p = (IProject) ((TableItem) event.item).getData();
				SaExportWizardPage.this.saProjects.put( p, checked );
				validate();
			}
		});


		// Export mode
		Label label = new Label( container, SWT.NONE );
		label.setText( "Select the export mode:" );
		layoutData = new GridData();
		layoutData.verticalIndent = 17;
		label.setLayoutData( layoutData );


		// Export mode - one per project
		Button projectModeButton = new Button( container, SWT.RADIO );
		projectModeButton.setText( "Export every selected Service Assembly in its project." );
		projectModeButton.setToolTipText( "The created archives will be placed at the root of the associated Service Assembly projects." );
		layoutData = new GridData();
		layoutData.verticalIndent = 3;
		projectModeButton.setLayoutData( layoutData );


		// Export mode - one per project, in a same directory
		Button separateModeButton = new Button( container, SWT.RADIO );
		separateModeButton.setText( "Export the Service Assemblies separately, but in a same directory." );
		separateModeButton.setToolTipText( "The created archive will be placed in the selected directory." );

		Composite subContainer = new Composite( container, SWT.NONE );
		layout = new GridLayout( 3, false );
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.marginLeft = 16;
		subContainer.setLayout( layout );
		subContainer.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final Label dirLabel = new Label( subContainer, SWT.NONE );
		dirLabel.setText( "Output directory:" );
		layoutData = new GridData();
		layoutData.widthHint = 90;
		dirLabel.setLayoutData( layoutData );

		final Text dirText = new Text( subContainer, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY );
		dirText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		String loc = PetalsServicesPlugin.getDefault().getPreferenceStore().getString( PREF_DIRECTORY_LOC );
		if( loc.length() != 0 ) {
			dirText.setText( loc );
			this.outputFile = new File( loc );
		}

		dirText.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				SaExportWizardPage.this.outputFile = new File( dirText.getText());
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
				if( SaExportWizardPage.this.outputFile != null
							&& SaExportWizardPage.this.outputFile.exists())
					dlg.setFilterPath( SaExportWizardPage.this.outputFile.getAbsolutePath());

				String loc = dlg.open();
				if( loc != null ) {
					dirText.setText( loc );
					PetalsServicesPlugin.getDefault().getPreferenceStore().setValue( PREF_DIRECTORY_LOC, loc );
				}
			}
		});


		// Selection listeners
		projectModeButton.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {

				dirLabel.setEnabled( false );
				dirText.setEnabled( false );
				browseDirButton.setEnabled( false );
				dirText.setBackground( dirText.getDisplay().getSystemColor( SWT.COLOR_WIDGET_BACKGROUND ));

				SaExportWizardPage.this.outputFile = null;
				SaExportWizardPage.this.exportMode = SaExportMode.IN_PROJECT;
				validate();
			}
		});

		separateModeButton.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {

				dirLabel.setEnabled( true );
				dirText.setEnabled( true );
				browseDirButton.setEnabled( true );
				dirText.setBackground( dirText.getDisplay().getSystemColor( SWT.COLOR_WHITE ));

				SaExportWizardPage.this.outputFile = new File( dirText.getText());
				SaExportWizardPage.this.exportMode = SaExportMode.IN_SAME_LOCATION;
				validate();
			}
		});


		// Last UI details
		setControl( container );
		viewer.getTable().setFocus();
		projectModeButton.setSelection( true );
		projectModeButton.notifyListeners( SWT.Selection, new Event());

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
		if( getSaProjectsToExport().isEmpty())
			updateStatus( "You must select at least one Service Assembly project to export." );
		else if( this.exportMode == null )
			updateStatus( "You have to select an export mode." );
		else if( this.exportMode == SaExportMode.IN_SAME_LOCATION &&
					( this.outputFile == null || ! this.outputFile.exists() || ! this.outputFile.isDirectory()))
			updateStatus( "You have to select a valid output directory." );
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
	 * Checks if a project can be a base project.
	 * @param project
	 * @return
	 */
	private boolean projectMatches( IProject project ) {
		boolean result = project.isAccessible() &&ServiceProjectRelationUtils.isSaProject( project );
		return result;
	}


	/**
	 * Gets the SA projects to export.
	 * @return the list of SA projects to export
	 */
	public Collection<IProject> getSaProjectsToExport() {

		List<IProject> result = new ArrayList<IProject>();
		for( Map.Entry<IProject,Boolean> entry : this.saProjects.entrySet()) {
			if( entry.getValue())
				result.add( entry.getKey());
		}

		return result;
	}


	/**
	 * @return the exportMode
	 */
	public SaExportMode getExportMode() {
		return this.exportMode;
	}


	/**
	 * @return the outputFile
	 */
	public File getOutputFile() {
		return this.outputFile;
	}
}
