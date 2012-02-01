/****************************************************************************
 * 
 * Copyright (c) 2009-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
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
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsComponentNewWizardPage extends AbstractMavenArtifactPage {

	private static final int SELECTION_OFFSET = 10;

	private Combo componentTypeCombo, cdkCombo;
	private Text componentNameText, componentVersionText, componentGroupIdText, projectLocationText;

	private String name;
	private String version;

	private String groupId;
	private int type = 0;	// 0 for BC, 1 for SE
	private String archetypeVersion;

	private URI projectLocationURI;
	private boolean isAtDefaultLocation = true;


	/**
	 * Constructor.
	 */
	public PetalsComponentNewWizardPage() {

		super( "componentPage" );
		setTitle( "Petals Component" );
		setDescription( "Define the component properties." );

		this.name = "petals-bc-your_component_name";
		this.version = "1.0-SNAPSHOT";
		this.groupId = "org.ow2.petals.your_component_name";
	}


	/**
	 * @see IDialogPage#createControl(Composite)
	 */
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

		// Component type
		new Label( container, SWT.NONE ).setText( "Component &type:");
		this.componentTypeCombo = new Combo( container, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY );
		this.componentTypeCombo.add( "Binding Component" );
		this.componentTypeCombo.add( "Service Engine" );
		this.componentTypeCombo.select( this.type );
		this.componentTypeCombo.addSelectionListener( new SelectionListener() {
			public void widgetDefaultSelected( SelectionEvent e ) {
				widgetSelected( e );
			}

			public void widgetSelected( SelectionEvent e ) {

				int selection = PetalsComponentNewWizardPage.this.componentTypeCombo.getSelectionIndex();
				String componentName = PetalsComponentNewWizardPage.this.componentNameText.getText().substring( 10 );
				String newName = "petals-" + (selection == 0 ? "bc-" : "se-") + componentName;
				PetalsComponentNewWizardPage.this.componentNameText.setText( newName );
				PetalsComponentNewWizardPage.this.componentNameText.setSelection( newName.length());
				// Validation is implicit, called by the name text
			}
		});

		// Component name
		new Label( container, SWT.NONE ).setText( "Component &name:");
		this.componentNameText = new Text( container, SWT.BORDER | SWT.SINGLE );
		this.componentNameText.setText( this.name );
		this.componentNameText.setSelection( PetalsComponentNewWizardPage.SELECTION_OFFSET, this.name.length());
		this.componentNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.componentNameText.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {

				int selection = PetalsComponentNewWizardPage.this.componentTypeCombo.getSelectionIndex();
				String componentName = PetalsComponentNewWizardPage.this.componentNameText.getText().substring( 10 );
				String newGroupId = PetalsComponentNewWizardPage.this.componentGroupIdText.getText();
				if( newGroupId.startsWith( "org.ow2.petals." ))
					newGroupId = "org.ow2.petals." + (selection == 0 ? "bc" : "se") + "." + componentName;

				PetalsComponentNewWizardPage.this.componentGroupIdText.setText( newGroupId );
				// Validation is implicit, called by the group ID text
			}
		});

		this.componentNameText.addFocusListener( new FocusAdapter() {
			@Override
			public void focusGained( FocusEvent e ) {
				String componentName = PetalsComponentNewWizardPage.this.componentNameText.getText();
				PetalsComponentNewWizardPage.this.componentNameText.setSelection(
							PetalsComponentNewWizardPage.SELECTION_OFFSET,
							componentName.length());
			}
		});

		// Component version
		new Label( container, SWT.NONE ).setText( "Component &version:");
		this.componentVersionText = new Text( container, SWT.BORDER | SWT.SINGLE );
		this.componentVersionText.setText( this.version );
		this.componentVersionText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.componentVersionText.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				validate();
			}
		});

		// Group ID
		new Label( container, SWT.NONE ).setText( "&Group ID:");
		this.componentGroupIdText = new Text( container, SWT.BORDER | SWT.SINGLE );
		this.componentGroupIdText.setText( this.groupId );
		this.componentGroupIdText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.componentGroupIdText.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				validate();
			}
		});


		// CDK version
		new Label( container, SWT.NONE ).setText( "&CDK version:");
		this.cdkCombo = new Combo( container, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY );
		this.cdkCombo.add( "CDK 5" );
		this.cdkCombo.select( 0 );
		this.cdkCombo.addSelectionListener( new SelectionListener() {
			public void widgetDefaultSelected( SelectionEvent e ) {
				validate();
			}

			public void widgetSelected( SelectionEvent e ) {
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

		this.projectLocationText = new Text( locContainer, SWT.SINGLE | SWT.BORDER );
		this.projectLocationText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.projectLocationText.setText( ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString());
		this.projectLocationText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
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
					PetalsComponentNewWizardPage.this.projectLocationText.setText( location );
			}
		});

		useDefaultLocButton.setSelection( this.isAtDefaultLocation );
		useDefaultLocButton.addSelectionListener( new SelectionAdapter () {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				PetalsComponentNewWizardPage.this.isAtDefaultLocation =
					useDefaultLocButton.getSelection();

				boolean use = ! PetalsComponentNewWizardPage.this.isAtDefaultLocation;
				locLabel.setEnabled( use );
				PetalsComponentNewWizardPage.this.projectLocationText.setEnabled( use );
				browseButton.setEnabled( use );
				PetalsComponentNewWizardPage.this.projectLocationText.setFocus();
				validate();
			}
		});


		// Last steps in the UI definition
		initializeMavenTooltip();
		useDefaultLocButton.notifyListeners( SWT.Selection, new Event());
		this.componentTypeCombo.setFocus();
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
		if( message != null )
			this.helpTooltip.hide();
	}


	/**
	 * Validates the page fields.
	 */
	private void validate() {

		// Validate fields
		String name = this.componentNameText.getText();
		if( name == null || name.trim().length() == 0 ) {
			updateStatus( "You have to provide the component name." );
			return;
		}

		if( this.isAtDefaultLocation ) {
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject( name );
			if( project.exists()) {
				updateStatus( "A component project with a similar name already exists in the workspace." );
				return;
			}
		}

		String version = this.componentVersionText.getText();
		if( version == null || version.trim().length() == 0 ) {
			updateStatus( "You have to provide the component version." );
			return;
		}

		if( ! version.matches( "\\d+(\\.\\d+)*(-SNAPSHOT)?" )) {
			updateStatus( "The component version is invalid." );
			return;
		}

		String groupId = this.componentGroupIdText.getText();
		if( groupId == null || groupId.trim().length() == 0 ) {
			updateStatus( "You have to provide the component group ID." );
			return;
		}

		if( ! JavaConventions.validatePackageName( groupId, JavaCore.VERSION_1_5, JavaCore.VERSION_1_5 ).isOK()) {
			updateStatus( "The component group ID does not respect the Java package syntax." );
			return;
		}

		boolean otherMessageShown = false;
		if( ! name.toLowerCase().equals( name )) {
			updateStatus( "The component name should be completely in lower case.", IMessageProvider.WARNING );
			otherMessageShown = true;
		}

		// Project location
		File projectFile;
		if( ! this.isAtDefaultLocation ) {

			if( this.projectLocationText.getText().trim().length() == 0 ) {
				updateStatus( "You have to specify the project location." );
				return;
			}

			try {
				projectFile = new File(
							this.projectLocationText.getText(),
							this.componentNameText.getText());

				this.projectLocationURI = projectFile.toURI();

			} catch( Exception e ) {
				updateStatus( "The specified location is not a valid file location." );
				return;
			}

			IProject p = ResourcesPlugin.getWorkspace().getRoot().getProject( this.componentNameText.getText());
			IStatus status = ResourcesPlugin.getWorkspace().validateProjectLocationURI( p, this.projectLocationURI );
			if( status.getCode() != IStatus.OK ) {
				updateStatus( status.getMessage());
				return;
			}

		} else {
			this.projectLocationURI = null;
			projectFile = new File(
						ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile(),
						this.componentNameText.getText());
		}

		// Does the file exists?
		if( projectFile.exists()) {
			updateStatus( "There is already an existing file at this location." );
			return;
		}

		// Store valid values
		this.name = name;
		this.version = version;
		this.groupId = groupId;
		this.type = this.componentTypeCombo.getSelectionIndex();

		switch( this.cdkCombo.getSelectionIndex()) {
		case 0:
		default:
			this.archetypeVersion = "1.3";
		}

		// Is Maven installed?
		if( ! otherMessageShown ) {
			if( ! isMavenInstalled()) {
				updateStatus( "Maven seems to not be installed.", IMessageProvider.WARNING );
				this.helpTooltip.show();
				return;

			} else {
				updateStatus( null );
			}
		}
	}


	/**
	 * @return the name
	 */
	@Override
	public String getArtifactName() {
		return this.name;
	}


	/**
	 * @return the version
	 */
	@Override
	public String getArtifactVersion() {
		return this.version;
	}


	/**
	 * @return the groupId
	 */
	@Override
	public String getGroupId() {
		return this.groupId;
	}


	/**
	 * @return the archetype ID
	 */
	@Override
	public String getArchetypeId() {

		String archetypeId = "";

		// CDK 5
		if( "1.3".equals( this.archetypeVersion )) {
			switch( this.type ) {
			case 0:
				archetypeId = "maven-archetype-petals-jbi-binding-component";
				break;
			case 1:
				archetypeId = "maven-archetype-petals-jbi-service-engine";
				break;
			}
		}

		return archetypeId;
	}


	/**
	 * @return the archetypeVersion
	 */
	@Override
	public String getArchetypeVersion() {
		return this.archetypeVersion;
	}


	/**
	 * @return the projectLocationURI
	 */
	@Override
	public URI getProjectLocationURI() {
		return this.projectLocationURI;
	}
}
