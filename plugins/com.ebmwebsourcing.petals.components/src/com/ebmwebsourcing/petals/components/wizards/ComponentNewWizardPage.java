/******************************************************************************
 * Copyright (c) 2009-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
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
import org.eclipse.jface.layout.GridLayoutFactory;
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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.swt.DefaultSelectionListener;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ComponentNewWizardPage extends WizardPage {

	private static final int SELECTION_OFFSET = 10;

	private String name, groupId, location, rootPackage;
	private PetalsContainerVersion petalsContainerVersion = PetalsContainerVersion.petals3_1;
	private boolean bc = true, isAtDefaultLocation = true;
	private URI projectLocationURI;


	/**
	 * Constructor.
	 */
	public ComponentNewWizardPage() {

		super( "componentPage" );
		setTitle( "Petals Component" );
		setDescription( "Define the component properties." );

		this.name = "petals-bc-your_component_name";
		this.groupId = "org.ow2.petals.your_component_name";
		this.rootPackage = "org.ow2.petals.components";
	}


	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	@Override
	public void createControl(Composite parent) {

		Composite container = new Composite( parent, SWT.NONE );
		GridLayoutFactory.swtDefaults().numColumns( 2 ).extendedMargins( 15, 15, 20, 0 ).spacing( 15, 9 ).applyTo( container );
		container.setLayoutData( new GridData( SWT.CENTER, SWT.DEFAULT, true, false ));

		// Component Fields
		new Label( container, SWT.NONE ).setText( "Component &type:");
		final Combo componentTypeCombo = new Combo( container, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY );
		componentTypeCombo.add( "Binding Component" );
		componentTypeCombo.add( "Service Engine" );
		componentTypeCombo.select( this.bc ? 0 : 1 );
		componentTypeCombo.addSelectionListener( new DefaultSelectionListener() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				int index = ((Combo) e.widget).getSelectionIndex();
				ComponentNewWizardPage.this.bc = index == 0;
				// Validation is implicit, called by the name text
			}
		});

		new Label( container, SWT.NONE ).setText( "Component &name:");
		final Text componentNameText = new Text( container, SWT.BORDER | SWT.SINGLE );
		componentNameText.setText( this.name );
		componentNameText.setSelection( ComponentNewWizardPage.SELECTION_OFFSET, this.name.length());
		componentNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		new Label( container, SWT.NONE ).setText( "&Group ID:");
		final Text componentGroupIdText = new Text( container, SWT.BORDER | SWT.SINGLE );
		componentGroupIdText.setText( this.groupId );
		componentGroupIdText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		componentGroupIdText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				ComponentNewWizardPage.this.groupId = ((Text) e.widget).getText();
				validate();
			}
		});

		new Label( container, SWT.NONE ).setText( "Java Root Package:");
		final Text rootPckgText = new Text( container, SWT.BORDER | SWT.SINGLE );
		rootPckgText.setText( this.rootPackage );
		rootPckgText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		rootPckgText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				ComponentNewWizardPage.this.rootPackage = ((Text) e.widget).getText();
				validate();
			}
		});

		new Label( container, SWT.NONE ).setText( "&Petals Version:");
		ComboViewer containerViewer = new ComboViewer( container, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY );
		containerViewer.setLabelProvider( new LabelProvider());
		containerViewer.setContentProvider( new ArrayContentProvider());
		containerViewer.setInput( PetalsContainerVersion.values());
		containerViewer.setSelection( new StructuredSelection( this.petalsContainerVersion ));
		containerViewer.addSelectionChangedListener( new ISelectionChangedListener() {
			@Override
			public void selectionChanged( SelectionChangedEvent event ) {
				ComponentNewWizardPage.this.petalsContainerVersion =
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
		GridLayoutFactory.swtDefaults().numColumns( 3 ).margins( 0, 0 ).applyTo( locContainer );
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
				ComponentNewWizardPage.this.location = ((Text) e.widget).getText().trim();
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

				ComponentNewWizardPage.this.isAtDefaultLocation = useDefaultLocButton.getSelection();
				boolean use = ! ComponentNewWizardPage.this.isAtDefaultLocation;

				locLabel.setEnabled( use );
				projectLocationText.setEnabled( use );
				browseButton.setEnabled( use );

				projectLocationText.setFocus();
				validate();
			}
		});



		// Listeners
		componentTypeCombo.addSelectionListener( new DefaultSelectionListener() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				int selection = ((Combo) e.widget).getSelectionIndex();
				String componentName = componentNameText.getText().substring( 10 );
				String newName = "petals-" + (selection == 0 ? "bc-" : "se-") + componentName;
				componentNameText.setText( newName );
				componentNameText.setSelection( newName.length());
			}
		});


		componentNameText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {

				int selection = componentTypeCombo.getSelectionIndex();
				String componentName = componentNameText.getText().substring( 10 );
				String newGroupId = componentGroupIdText.getText();
				if( newGroupId.startsWith( "org.ow2.petals." ))
					newGroupId = "org.ow2.petals." + (selection == 0 ? "bc" : "se") + "." + componentName;

				ComponentNewWizardPage.this.name = ((Text) e.widget).getText().trim();
				componentGroupIdText.setText( newGroupId );
				// Validation is implicit, called by the group ID text
			}
		});

		componentNameText.addFocusListener( new FocusAdapter() {
			@Override
			public void focusGained( FocusEvent e ) {
				componentNameText.setSelection( ComponentNewWizardPage.SELECTION_OFFSET, ComponentNewWizardPage.this.name.length());
			}
		});


		// Last steps in the UI definition
		useDefaultLocButton.notifyListeners( SWT.Selection, new Event());
		componentTypeCombo.setFocus();
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
			updateStatus( "You have to provide the component name." );
			return;
		}

		if( ! this.name.toLowerCase().equals( this.name )) {
			updateStatus( "The component name should be completely in lower case." );
			return;
		}

		if( this.isAtDefaultLocation ) {
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject( this.name );
			if( project.exists()) {
				updateStatus( "A component project with a similar name already exists in the workspace." );
				return;
			}
		}

		if( StringUtils.isEmpty( this.groupId )) {
			updateStatus( "You have to provide the component group ID." );
			return;
		}

		if( ! JavaConventions.validatePackageName( this.groupId, JavaCore.VERSION_1_5, JavaCore.VERSION_1_5 ).isOK()) {
			updateStatus( "The component group ID does not respect the Java package syntax." );
			return;
		}

		if( StringUtils.isEmpty( this.rootPackage )) {
			updateStatus( "You have to provide the Java root package." );
			return;
		}

		if( ! JavaConventions.validatePackageName( this.rootPackage, JavaCore.VERSION_1_5, JavaCore.VERSION_1_5 ).isOK()) {
			updateStatus( "The Java root package name does not respect the Java package syntax." );
			return;
		}


		// Project location
		File projectFile;
		if( ! this.isAtDefaultLocation ) {

			if( StringUtils.isEmpty( this.location )) {
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
			projectFile = new File(
						ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile(),
						this.name );
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


	/**
	 * @return true if it is a BC, false for a SE
	 */
	public boolean isBc() {
		return this.bc;
	}


	/**
	 * @return the rootPackage
	 */
	public String getRootPackage() {
		return this.rootPackage;
	}
}
