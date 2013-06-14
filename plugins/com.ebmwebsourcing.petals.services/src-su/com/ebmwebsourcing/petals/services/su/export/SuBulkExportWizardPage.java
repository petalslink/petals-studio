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

package com.ebmwebsourcing.petals.services.su.export;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.PlatformUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.utils.ServiceProjectRelationUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SuBulkExportWizardPage extends WizardPage {

	private File outputDirectory;
	private IProject suProject;
	private int startId = 0, endId = 10;
	private String suffix = "";
	private boolean keepSrv = false, override = false;

	private boolean complete = false;
	private Image folderImg, projectImg, propertiesImg;


	/**
	 * Constructor.
	 * @param selection
	 */
	public SuBulkExportWizardPage( IStructuredSelection selection ) {

		super( "SU Bulk Export Page" );
		setTitle( "Bulk Services Export" ); //NON-NLS-1
		setDescription( "Create n Petals services from one Service Unit project." ); //NON-NLS-1

		if( ! selection.isEmpty()) {
			IProject project = PlatformUtils.getAdaptedProject( selection.getFirstElement());
			if( project != null
					&& ServiceProjectRelationUtils.isSuProject( project ))
				this.suProject = project;
		}

		try {
			ImageDescriptor desc = PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor( ISharedImages.IMG_OBJ_FOLDER );
			if( desc != null )
				this.folderImg = desc.createImage();

		} catch( Exception e ) {
			PetalsServicesPlugin.log( e, IStatus.WARNING );
		}

		try {
			ImageDescriptor desc = PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor( org.eclipse.ui.ide.IDE.SharedImages.IMG_OBJ_PROJECT );
			if( desc != null )
				this.projectImg = desc.createImage();

		} catch( Exception e ) {
			PetalsServicesPlugin.log( e, IStatus.WARNING );
		}

		try {
			ImageDescriptor desc = PetalsServicesPlugin.getImageDescriptor( "icons/obj16/properties.gif" );
			if( desc != null )
				this.propertiesImg = desc.createImage();

		} catch( Exception e ) {
			PetalsServicesPlugin.log( e, IStatus.WARNING );
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage#dispose()
	 */
	@Override
	public void dispose() {

		if( this.projectImg != null ) {
			this.projectImg.dispose();
			this.projectImg = null;
		}

		if( this.propertiesImg != null ) {
			this.propertiesImg.dispose();
			this.propertiesImg = null;
		}

		if( this.folderImg != null ) {
			this.folderImg.dispose();
			this.folderImg = null;
		}

		super.dispose();
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


		// SU selection
		Composite subContainer = new Composite( container, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		subContainer.setLayout( layout );
		subContainer.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		new Label( subContainer, SWT.NONE ).setImage( this.projectImg );
		new Label( subContainer, SWT.NONE ).setText( "Select the Service Unit project to derivate:" );


		subContainer = new Composite( container, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		subContainer.setLayout( layout );
		subContainer.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final Text projectText = new Text( subContainer, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY );
		projectText.setBackground( Display.getDefault().getSystemColor( SWT.COLOR_WHITE ));
		projectText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		if( this.suProject != null )
			projectText.setText( this.suProject.getName());

		Button browseButton = new Button( subContainer, SWT.PUSH );
		browseButton.setText( "Browse..." );
		browseButton.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {

				// List the projects
				List<IProject> suProjects = new ArrayList<IProject> ();
				IWorkspaceRoot iwr = ResourcesPlugin.getWorkspace().getRoot();
				for( IProject p : iwr.getProjects()) {
					if( ServiceProjectRelationUtils.isSuProject( p ))
						suProjects.add( p );
				}

				// Let the user choose
				ElementListSelectionDialog dlg =
					new ElementListSelectionDialog( new Shell(), new WorkbenchLabelProvider());
				dlg.setAllowDuplicates( false );
				dlg.setElements( suProjects.toArray());
				dlg.setEmptyListMessage( "The workspace does not contain any valid Service Unit project." );
				dlg.setMatchEmptyString( true );
				dlg.setMultipleSelection( false );
				dlg.setTitle( "Service Unit Project Selection" );
				dlg.setMessage( "Select a Service Unit project." );
				if( SuBulkExportWizardPage.this.suProject == null )
					dlg.setFilter( "*" );
				else
					dlg.setFilter( SuBulkExportWizardPage.this.suProject.getName());

				if( dlg.open() == Window.OK ) {
					SuBulkExportWizardPage.this.suProject = (IProject) dlg.getFirstResult();
					projectText.setText( SuBulkExportWizardPage.this.suProject.getName());
					validate();
				}
			}
		});


		// Output selection
		subContainer = new Composite( container, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		subContainer.setLayout( layout );

		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.verticalIndent = 14;
		subContainer.setLayoutData( layoutData );

		new Label( subContainer, SWT.NONE ).setImage( this.folderImg );
		new Label( subContainer, SWT.NONE ).setText( "Select the output directory:" );


		subContainer = new Composite( container, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		subContainer.setLayout( layout );
		subContainer.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final Text outputText = new Text( subContainer, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY );
		outputText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		outputText.setBackground( Display.getDefault().getSystemColor( SWT.COLOR_WHITE ));

		browseButton = new Button( subContainer, SWT.PUSH );
		browseButton.setText( "Browse..." );
		browseButton.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {

				DirectoryDialog dlg = new DirectoryDialog( new Shell());
				dlg.setText( "Output Directory" );
				dlg.setMessage( "Select the directory in which the derviated services will be saved." );
				if( SuBulkExportWizardPage.this.outputDirectory != null )
					dlg.setFilterPath( SuBulkExportWizardPage.this.outputDirectory.getAbsolutePath());

				String path = dlg.open();
				if( path != null ) {
					SuBulkExportWizardPage.this.outputDirectory = new File( path );
					outputText.setText( path );
					outputText.setSelection( path.length());
					validate();
				}
			}
		});


		// Other elements
		subContainer = new Composite( container, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		subContainer.setLayout( layout );

		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.verticalIndent = 14;
		subContainer.setLayoutData( layoutData );

		new Label( subContainer, SWT.NONE ).setImage( this.propertiesImg );
		new Label( subContainer, SWT.NONE ).setText( "Edit the generation properties:" );


		// Cheating: align the right-border of the Text with the browsing ones
		subContainer = new Composite( container, SWT.NONE );
		layout = new GridLayout( 3, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		subContainer.setLayout( layout );

		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.verticalIndent = 3;
		subContainer.setLayoutData( layoutData );


		new Label( subContainer, SWT.NONE ).setText( "Suffix:" );
		final Text suffixText = new Text( subContainer, SWT.SINGLE | SWT.BORDER );
		suffixText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		suffixText.setToolTipText( "The generated service will be named <baseName>-<suffix>-<id> with 'startId <= id <= endId'" );
		suffixText.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				SuBulkExportWizardPage.this.suffix = suffixText.getText();
				validate();
			}
		});

		Button cheatingButton = new Button( subContainer, SWT.PUSH );
		cheatingButton.setText( "Browse..." );
		cheatingButton.setVisible( false );


		new Label( subContainer, SWT.NONE ).setText( "Start ID:" );
		final Spinner startSpinner = new Spinner( subContainer, SWT.BORDER | SWT.READ_ONLY );
		startSpinner.setValues( this.startId, 0, Integer.MAX_VALUE, 0, 1, 100 );
		startSpinner.setBackground( Display.getDefault().getSystemColor( SWT.COLOR_WHITE ));
		startSpinner.setToolTipText( "The generated service will be named <baseName>-<suffix>-<id> with 'startId <= id <= endId'" );
		startSpinner.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				SuBulkExportWizardPage.this.startId = startSpinner.getSelection();
				validate();
			}
		});

		layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		layoutData.widthHint = 120;
		startSpinner.setLayoutData( layoutData );


		new Label( subContainer, SWT.NONE ).setText( "End ID:" );
		final Spinner endSpinner = new Spinner( subContainer, SWT.BORDER | SWT.READ_ONLY );
		endSpinner.setValues( this.endId, 1, Integer.MAX_VALUE, 0, 1, 100 );
		endSpinner.setBackground( Display.getDefault().getSystemColor( SWT.COLOR_WHITE ));
		endSpinner.setToolTipText( "The generated service will be named <baseName>-<suffix>-<id> with 'startId <= id <= endId'" );
		endSpinner.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				SuBulkExportWizardPage.this.endId = endSpinner.getSelection();
				validate();
			}
		});

		layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		layoutData.widthHint = 120;
		endSpinner.setLayoutData( layoutData );


		// Last options
		final Button keepSrvButton = new Button( container, SWT.CHECK );
		keepSrvButton.setText( "Keep the same service name" );
		keepSrvButton.setToolTipText( "Keep the same service name for all the derivated services" );
		keepSrvButton.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				SuBulkExportWizardPage.this.keepSrv = keepSrvButton.getSelection();
				validate();
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				SuBulkExportWizardPage.this.keepSrv = keepSrvButton.getSelection();
				validate();
			}
		});

		final Button overrideButton = new Button( container, SWT.CHECK );
		overrideButton.setText( "Override (skip by default)" );
		overrideButton.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				SuBulkExportWizardPage.this.override = overrideButton.getSelection();
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				SuBulkExportWizardPage.this.override = overrideButton.getSelection();
			}
		});


		// Last UI details
		projectText.setFocus();
		setControl( container );
	}


	/**
	 * Validates the page entries.
	 */
	private void validate() {

		if( this.suProject == null )
			updateStatus( "You have to select a Service Unit project." );
		else if( this.outputDirectory == null )
			updateStatus( "You have to select the output directory." );
		else if( ! this.suffix.matches( "(\\w)*" ))
			updateStatus( "The suffix contains illegal characters." );
		else if( this.startId > this.endId )
			updateStatus( "The end ID must be greater or equals to the start ID." );
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
	 * @return the outputDirectory
	 */
	public File getOutputDirectory() {
		return this.outputDirectory;
	}


	/**
	 * @return the suProject
	 */
	public IProject getSuProject() {
		return this.suProject;
	}


	/**
	 * @return the startId
	 */
	public int getStartId() {
		return this.startId;
	}


	/**
	 * @return the endId
	 */
	public int getEndId() {
		return this.endId;
	}


	/**
	 * @return the suffix
	 */
	public String getSuffix() {
		return this.suffix;
	}


	/**
	 * @return the keepSrv
	 */
	public boolean isKeepSrv() {
		return this.keepSrv;
	}


	/**
	 * @return the override
	 */
	public boolean isOverride() {
		return this.override;
	}
}
