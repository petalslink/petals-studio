/****************************************************************************
 *
 * Copyright (c) 2008-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.sa.wizards;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ListDialog;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import com.ebmwebsourcing.petals.common.generation.JbiUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.preferences.PreferencesManager;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.DefaultSelectionListener;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.TextWithButtonComposite;
import com.ebmwebsourcing.petals.common.internal.provisional.ui.FixedShellTooltip;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.Messages;
import com.ebmwebsourcing.petals.services.utils.ServiceProjectRelationUtils;

/**
 * The wizard page asking for generic information about a Maven project.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsSaNewWizardPage extends WizardPage {

	private Text nameText, versionText, descriptionText, artifactIdText, groupIdText, projectLocationText;
	private String name, version, description, artifactId, groupId;

	private URI projectLocationURI;
	private boolean isAtDefaultLocation = true;
	private FixedShellTooltip helpTooltip;


	/**
	 * The constructor.
	 * @param pageName
	 */
	public PetalsSaNewWizardPage( String pageName ) {
		super( pageName );

		this.version = PetalsConstants.DEFAULT_ARTIFACT_VERSION;
		this.groupId = PreferencesManager.getMavenGroupId();
		if( StringUtils.isEmpty( this.groupId ))
			this.groupId = PetalsConstants.DEFAULT_GROUP_ID;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl( Composite parent ) {

		// Add a tool tip to display in case of problem
		this.helpTooltip = new FixedShellTooltip( getShell(), true, 90 ) {
			@Override
			public void populateTooltip( Composite parent ) {

				parent.setLayout( new GridLayout());
				parent.setLayoutData( new GridData( GridData.FILL_BOTH ));
				parent.setBackground( getShell().getDisplay().getSystemColor( SWT.COLOR_LIST_BACKGROUND ));

				StringBuilder sb = new StringBuilder();
				sb.append( "<form><p><b>What does this error mean?</b></p><p>");
				sb.append( "This wizard will generate, among other things, Maven artifacts.<br />" );
				sb.append( "Unfortunately, there is a problem with the <A>the Petals Maven preferences</A>.</p>" );
				sb.append( "<p>Please, make them correct.</p></form>");

				FormText formText = new FormText( parent, SWT.WRAP | SWT.NO_FOCUS );
				formText.setBackground( getShell().getDisplay().getSystemColor( SWT.COLOR_LIST_BACKGROUND ));
				formText.setText( sb.toString(), true, false );
				formText.addHyperlinkListener( new HyperlinkAdapter() {

					@Override
					public void linkActivated( HyperlinkEvent e ) {
						try {
							Dialog dlg = PreferencesUtil.createPreferenceDialogOn(
										new Shell(),
										"com.ebmwebsourcing.petals.services.prefs.maven",
										null, null );

							if( dlg.open() == Window.OK )
								validate();

						} catch( Exception e1 ) {
							PetalsServicesPlugin.log( e1, IStatus.ERROR );
						}
					}
				});
			}
		};

		this.helpTooltip.hide();


		// Basic fields
		Composite container = new Composite( parent, SWT.NONE );
		GridLayoutFactory.swtDefaults().numColumns( 2 ).extendedMargins( 15, 15, 20, 0 ).spacing( 15, 5 ).applyTo( container );
		container.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		SwtFactory.createLabel( container, "Name:", "The service assembly name to put into the jbi.xml and pom.xml files" );
		TextWithButtonComposite twc = new TextWithButtonComposite( container );
		twc.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		twc.getText().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final List<IProject> suProjects = new ArrayList<IProject> ();
		for( IProject p : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
			if( ServiceProjectRelationUtils.isSuProject( p ))
				suProjects.add( p );
		}

		twc.getButton().setText( "Name Helper..." );
		if( suProjects.isEmpty()) {
			twc.getButton().setEnabled( false );
			twc.getButton().setToolTipText( "No Service Unit project was found in the workspace" );
		} else {
			twc.getButton().setToolTipText( "Generate the SA name from a SU name" );
		}

		twc.getButton().addSelectionListener( new DefaultSelectionListener() {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				ListDialog dlg = new ListDialog( getShell());
				dlg.setContentProvider( new ArrayContentProvider());
				dlg.setLabelProvider( new WorkbenchLabelProvider());
				dlg.setInput( suProjects );
				if( dlg.open() != Window.OK )
					return;

				String suName = ((IProject) dlg.getResult()[ 0 ]).getName();
				String saName = JbiUtils.createSaName( suName );
				PetalsSaNewWizardPage.this.nameText.setText( saName );
			}
		});

		this.nameText = twc.getText();
		this.nameText.addModifyListener( new ModifyListener () {
			@Override
			public void modifyText( ModifyEvent e ) {

				String aid = PetalsSaNewWizardPage.this.artifactIdText.getText();
				String n = PetalsSaNewWizardPage.this.nameText.getText();

				if( aid.trim().length() != 0 && ! n.startsWith( aid ))
					validate();
				else
					PetalsSaNewWizardPage.this.artifactIdText.setText( n );
			}
		});


		ModifyListener validationListener = new ModifyListener () {
			@Override
			public void modifyText( ModifyEvent e ) {
				validate();
			}
		};

		SwtFactory.createLabel( container, "Artifact ID:", "The service assembly description to put into the jbi.xml and pom.xml files" );
		this.artifactIdText = SwtFactory.createSimpleTextField( container, true );
		this.artifactIdText.addModifyListener( validationListener );

		new Label( container, SWT.NONE ).setText( "Group ID:" );
		this.groupIdText = SwtFactory.createSimpleTextField( container, true );
		this.groupIdText.setText( this.groupId );
		this.groupIdText.addModifyListener( validationListener );

		new Label( container, SWT.NONE ).setText( "Version:" );
		this.versionText = SwtFactory.createSimpleTextField( container, true );
		this.versionText.setText( this.version );
		this.versionText.addModifyListener( validationListener );

		new Label( container, SWT.NONE ).setText( "Description:" );
		this.descriptionText = SwtFactory.createSimpleTextField( container, true );
		this.descriptionText.addModifyListener( validationListener );


		// Project location
		final Button useDefaultLocButton = new Button( container, SWT.CHECK );
		useDefaultLocButton.setText( "Create the project in the default location" );
		GridDataFactory.swtDefaults().indent( 0, 17 ).span( 2, 1 ).applyTo( useDefaultLocButton );

		Composite locContainer = new Composite( container, SWT.NONE );
		GridLayoutFactory.swtDefaults().numColumns( 2 ).margins( 0, 0 ).applyTo( locContainer );
		GridDataFactory.swtDefaults().span( 2, 1 ).applyTo( locContainer );

		final Label locLabel = new Label( locContainer, SWT.NONE );
		locLabel.setText( "Project location:" );

		final TextWithButtonComposite twbc = SwtFactory.createDirectoryBrowser( locContainer );
		this.projectLocationText = twbc.getText();
		this.projectLocationText.setText( ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString());
		this.projectLocationText.addModifyListener( validationListener );

		useDefaultLocButton.setSelection( this.isAtDefaultLocation );
		useDefaultLocButton.addSelectionListener( new SelectionAdapter () {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				PetalsSaNewWizardPage.this.isAtDefaultLocation =
					useDefaultLocButton.getSelection();

				boolean use = ! PetalsSaNewWizardPage.this.isAtDefaultLocation;
				locLabel.setEnabled( use );
				PetalsSaNewWizardPage.this.projectLocationText.setEnabled( use );
				twbc.getButton().setEnabled( use );
				PetalsSaNewWizardPage.this.projectLocationText.setFocus();
			}
		});


		// Last steps in the UI definition
		useDefaultLocButton.notifyListeners( SWT.Selection, new Event());
		setControl( container );
		validate();

		String msg = getErrorMessage();
		setErrorMessage( null );
		setMessage( msg, INFORMATION );

		this.nameText.setFocus();
	}


	/**
	 * Display the error message provided as parameter and prevent the user from going further in the wizard.
	 * @param message the error message to display, or null to display nothing.
	 */
	protected final void updateStatus( String message ) {
		setErrorMessage( message );
		setPageComplete( message == null );
	}


	/**
	 * Validate the page fields.
	 * @return true if all the elements are valid, false otherwise.
	 */
	protected boolean validate() {

		setMessage( null, INFORMATION );

		// Handle problems with the Maven configuration at the beginning of the wizard
		if( ! PreferencesManager.isMavenTemplateConfigurationValid()) {
			updateStatus( "There is an error in your preferences about custom POM templates." );
			this.helpTooltip.show();
			return false;
		}
		else {
			this.helpTooltip.hide();
		}

		try {
			for( IResource resource : ResourcesPlugin.getWorkspace().getRoot().members()) {
				if( resource.getName().equalsIgnoreCase( this.nameText.getText())) {
					updateStatus( NLS.bind( Messages.ProvideJbiPage_40, this.nameText.getText()));
					return false;
				}
			}
		} catch( CoreException e ) {
			PetalsServicesPlugin.log( e, IStatus.ERROR );
		}

		// Other fields
		if( this.nameText.getText().length() == 0 ) {
			updateStatus( "The name cannot be empty." );
			return false;
		}

		if( this.groupIdText.getText().length() == 0 ) {
			updateStatus( "The group ID cannot be empty." );
			return false;
		}

		// Project location
		if( ! this.isAtDefaultLocation ) {

			if( this.projectLocationText.getText().trim().length() == 0 ) {
				updateStatus( "You have to specify the project location." );
				return false;
			}

			try {
				this.projectLocationURI = new File(
							this.projectLocationText.getText(),
							this.nameText.getText()).toURI();

			} catch( Exception e ) {
				updateStatus( "The specified location is not a valid file location." );
				return false;
			}

			IProject p = ResourcesPlugin.getWorkspace().getRoot().getProject( this.nameText.getText());
			IStatus status = ResourcesPlugin.getWorkspace().validateProjectLocationURI( p, this.projectLocationURI );
			if( status.getCode() != IStatus.OK ) {
				updateStatus( status.getMessage());
				return false;
			}
		}

		// Store the result
		updateStatus( null );
		this.name = this.nameText.getText();
		this.description = this.descriptionText.getText();
		this.artifactId = this.artifactIdText.getText();
		this.groupId = this.groupIdText.getText();
		this.version = this.versionText.getText();

		return true;
	}


	/**
	 * @return the SA name
	 */
	public String getSaName () {
		return this.name;
	}


	/**
	 * @return the SA description
	 */
	public String  getSaDescription () {
		return this.description;
	}


	/**
	 * @return the artifact Id
	 */
	public String  getArtifactId () {
		return this.artifactId;
	}


	/**
	 * @return the group Id
	 */
	public String  getGroupId () {
		return this.groupId;
	}


	/**
	 * @return the model version
	 */
	public String  getVersion () {
		return this.version;
	}


	/**
	 * @return the projectLocationURI
	 */
	public URI getProjectLocationURI() {
		return this.projectLocationURI;
	}
}
