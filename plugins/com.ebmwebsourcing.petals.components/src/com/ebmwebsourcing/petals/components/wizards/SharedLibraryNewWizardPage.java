/****************************************************************************
 *
 * Copyright (c) 2009-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.components.wizards;

import java.io.File;
import java.net.URI;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SharedLibraryNewWizardPage extends WizardPage {

	private static final int SELECTION_OFFSET = 10;

	private PetalsContainerVersion petalsContainerVersion = PetalsContainerVersion.petals3_1;
	private String name, groupId, location;
	private URI projectLocationURI;
	private boolean isAtDefaultLocation = true;


	/**
	 * Constructor.
	 */
	public SharedLibraryNewWizardPage() {

		super( "sharedLibraryPage" );
		setTitle( "Petals Shared-Library" );
		setDescription( "Define the properties of the shared-library to create." );

		this.name = "petals-sl-your_shared_library_name";
		this.groupId = "org.ow2.petals.your_shared_library_name";
	}


	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	@Override
	public void createControl(Composite parent) {

		Composite container = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout( 2, false );
		layout.marginLeft = 15;
		layout.marginRight = 15;
		layout.marginTop = 20;
		layout.horizontalSpacing = 15;
		layout.verticalSpacing = 9;
		container.setLayout( layout );
		container.setLayoutData( new GridData( SWT.CENTER, SWT.DEFAULT, true, false ));

		// Name
		new Label( container, SWT.NONE ).setText( "Shared-Library &name:");
		final Text slNameText = new Text( container, SWT.BORDER | SWT.SINGLE );
		slNameText.setText( this.name );
		slNameText.setSelection( SharedLibraryNewWizardPage.SELECTION_OFFSET, this.name.length());
		slNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		slNameText.addFocusListener( new FocusAdapter() {
			@Override
			public void focusGained( FocusEvent e ) {
				String componentName = slNameText.getText();
				slNameText.setSelection( SharedLibraryNewWizardPage.SELECTION_OFFSET, componentName.length());
			}
		});

		// Group ID
		new Label( container, SWT.NONE ).setText( "&Group ID:");
		final Text slGroupIdText = new Text( container, SWT.BORDER | SWT.SINGLE );
		slGroupIdText.setText( this.groupId );
		slGroupIdText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		slGroupIdText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				SharedLibraryNewWizardPage.this.groupId = slGroupIdText.getText().trim();
				validate();
			}
		});

		// Petals version
		new Label( container, SWT.NONE ).setText( "&Petals Version:");
		ComboViewer containerViewer = new ComboViewer( container, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY );
		containerViewer.setLabelProvider( new LabelProvider());
		containerViewer.setContentProvider( new ArrayContentProvider());
		containerViewer.setInput( PetalsContainerVersion.values());
		containerViewer.setSelection( new StructuredSelection( this.petalsContainerVersion ));
		containerViewer.addSelectionChangedListener( new ISelectionChangedListener() {
			@Override
			public void selectionChanged( SelectionChangedEvent event ) {
				SharedLibraryNewWizardPage.this.petalsContainerVersion =
						(PetalsContainerVersion) ((IStructuredSelection) event.getSelection()).getFirstElement();
				validate();
			}
		});


		// Project location
		final Button useDefaultLocButton = new Button( container, SWT.CHECK );
		useDefaultLocButton.setText( "Create the project in the default location" );
		GridData layoutData = new GridData ();
		layoutData.horizontalSpan = 2;
		layoutData.verticalIndent = 17;
		useDefaultLocButton.setLayoutData( layoutData );

		Composite locContainer = new Composite( container, SWT.NONE );
		layout = new GridLayout( 3, false );
		layout.marginHeight = layout.marginWidth = 0;
		locContainer.setLayout( layout );
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 2;
		locContainer.setLayoutData( layoutData );

		final Label locLabel = new Label( locContainer, SWT.NONE );
		locLabel.setText( "Project location:" );

		final Text projectLocationText = new Text( locContainer, SWT.SINGLE | SWT.BORDER );
		projectLocationText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		projectLocationText.setText( ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString());
		projectLocationText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				SharedLibraryNewWizardPage.this.location = ((Text) e.widget).getText().trim();
				validate();
			}
		});

		final Button browseButton = new Button( locContainer, SWT.PUSH );
		browseButton.setText( "Browse..." );
		browseButton.addSelectionListener( new SelectionAdapter () {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				String location = new DirectoryDialog( getShell()).open();
				if( location != null )
					projectLocationText.setText( location );
			}
		});

		useDefaultLocButton.setSelection( this.isAtDefaultLocation );
		useDefaultLocButton.addSelectionListener( new SelectionAdapter () {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				SharedLibraryNewWizardPage.this.isAtDefaultLocation =
					useDefaultLocButton.getSelection();

				boolean use = ! SharedLibraryNewWizardPage.this.isAtDefaultLocation;
				locLabel.setEnabled( use );
				projectLocationText.setEnabled( use );
				browseButton.setEnabled( use );
				projectLocationText.setFocus();
				validate();
			}
		});

		slNameText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {

				SharedLibraryNewWizardPage.this.name = slNameText.getText().trim();
				String slName = slNameText.getText().substring( 10 );
				String newGroupId = slGroupIdText.getText();
				if( newGroupId.startsWith( "org.ow2.petals." ))
					newGroupId = "org.ow2.petals." + slName;

				slGroupIdText.setText( newGroupId );
				// Validation is implicit, called by the group ID text
			}
		});


		// Last steps in the UI definition
		useDefaultLocButton.notifyListeners( SWT.Selection, new Event());
		setControl( container );
	}


	/**
	 * Updates the page status.
	 * @param message the message to show, or null to show nothing
	 * @param status a {@link IMessageProvider} constant
	 */
	private void updateStatus( String message, int status ) {

		setMessage( null, IMessageProvider.ERROR );
		setMessage( null, IMessageProvider.INFORMATION );
		setMessage( null, IMessageProvider.WARNING );
		setMessage( null, IMessageProvider.NONE );

		setMessage( message, status );
		setPageComplete( status != IMessageProvider.ERROR || message == null );
	}


	/**
	 * Updates the page status.
	 * @param message the error message, or null if everything is all right
	 */
	private void updateStatus( String message ) {
		updateStatus( message, IMessageProvider.ERROR );
	}


	/**
	 * Validates the page fields.
	 */
	private void validate() {

		// Validate fields
		if( StringUtils.isEmpty( this.name )) {
			updateStatus( "You have to provide the shared-library's name." );
			return;
		}

		if( ! this.name.toLowerCase().equals( this.name )) {
			updateStatus( "The shared library's name should be completely in lower case." );
			return;
		}

		if( this.isAtDefaultLocation ) {
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject( this.name );
			if( project.exists()) {
				updateStatus( "A project with a similar name already exists in the workspace." );
				return;
			}
		}

		if( StringUtils.isEmpty( this.groupId )) {
			updateStatus( "You have to provide the group ID." );
			return;
		}

		if( ! JavaConventions.validatePackageName( this.groupId, JavaCore.VERSION_1_5, JavaCore.VERSION_1_5 ).isOK()) {
			updateStatus( "The group ID does not respect the Java package syntax." );
			return;
		}

		// Project location
		File projectFile;
		if( ! this.isAtDefaultLocation ) {

			if( this.location == null || this.location.length() == 0 ) {
				updateStatus( "You have to specify the project location." );
				return;
			}

			try {
				projectFile = new File( this.location, this.name );
				this.projectLocationURI = projectFile.toURI();

			} catch( Exception e ) {
				updateStatus( "The specified location is not a valid file location." );
				return;
			}

			IProject p = ResourcesPlugin.getWorkspace().getRoot().getProject( this.name );
			IStatus status = ResourcesPlugin.getWorkspace().validateProjectLocationURI( p, this.projectLocationURI );
			if( status.getCode() != IStatus.OK ) {
				updateStatus( status.getMessage());
				return;
			}

		} else {
			this.projectLocationURI = null;
			projectFile = new File( ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile(), this.name );
		}

		// Does the file exists?
		if( projectFile.exists()) {
			updateStatus( "There is already an existing file at this location." );
			return;
		}

		updateStatus( null );
	}


	/**
	 * @return the name
	 */
	public String getArtifactName() {
		return this.name;
	}


	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return this.groupId;
	}


	/**
	 * @return the projectLocationURI
	 */
	public URI getProjectLocationURI() {
		return this.projectLocationURI;
	}


	/**
	 * @return the petalsContainerVersion
	 */
	public PetalsContainerVersion getPetalsContainer() {
		return this.petalsContainerVersion;
	}
}
