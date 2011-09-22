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

package com.ebmwebsourcing.petals.services.su.wizards.pages;

import java.io.File;
import java.net.URI;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.IMessageProvider;
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
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.ebmwebsourcing.commons.jbi.internal.provisional.utils.JbiNameFormatter;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.Messages;
import com.ebmwebsourcing.petals.services.su.extensions.WizardConfiguration;
import com.ebmwebsourcing.petals.services.su.wizards.PetalsSuNewWizard;
import com.ebmwebsourcing.petals.services.su.wizards.SettingConstants;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.XsdNamespaceStore;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ProjectPage extends AbstractSuPage {

	/**
	 * The suffix to provide for the SU name creation.
	 * {@link SpecificUtils#createSuName(String, String, String)}
	 */
	protected boolean isConsume = false;

	/** */
	protected String projectName, folderLocation = "";

	/** */
	protected URI projectLocationURI;

	/** */
	protected boolean isAtDefaultLocation = true;

	/** */
	protected Text projectNameText, projectLocationText;

	/** */
	protected Button useDefaultLocButton;


	/**
	 * @param suType
	 * @param suTypeVersion
	 * @param isConsume
	 */
	public ProjectPage( String suType, String suTypeVersion, boolean isConsume ) {
		super( "ProjectPage", suType, suTypeVersion );
		this.isConsume = isConsume;
		setDescription( "Define the project name and location." );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #fillInData(com.ebmwebsourcing.petals.tools.eclipse.su.main.wizards.generation.EclipseSuBean)
	 */
	@Override
	public void fillInData( EclipseSuBean suBean ) {

		suBean.setProjectName( this.projectName );
		if( this.isAtDefaultLocation )
			suBean.setProjectLocation( null );
		else
			suBean.setProjectLocation( this.projectLocationURI );

		suBean.setComponentVersion( this.suTypeVersion );
		WizardConfiguration wizardConfiguration =
			((PetalsSuNewWizard) getWizard()).getWizardConfiguration();

		XsdNamespaceStore.getNamespaceStore().addAll( wizardConfiguration.getAdditionalNamespaces ());
		suBean.specificElements.addAll( wizardConfiguration.getAdditionalSpecificElements ());
		suBean.cdkElements.addAll( wizardConfiguration.getAdditionalCdkElements ());

		boolean createJavaProject = getWizard().getDialogSettings().getBoolean(
					SettingConstants.CREATE_JAVA_PROJECT );
		suBean.setCreateJavaProject( createJavaProject );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #setHelpContextId(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void setHelpContextId( Composite container ) {
		PlatformUI.getWorkbench().getHelpSystem().setHelp(
					container,
					PetalsServicesPlugin.PLUGIN_ID + ".TODO" ); //$NON-NLS-1$
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #validate()
	 */
	@Override
	public boolean validate() {

		setMessage( null );
		String warningMsg = null;

		// Remove white spaces ("File Transfer")
		String newSuType = this.suType.replaceAll( "\\s", "" );

		// Project
		if( this.isConsume ) {
			String regex = "su-" + newSuType + "-" + "[a-zA-Z_]+[.\\w\\-]*" + "-consume.*";
			if( ! this.projectNameText.getText().matches( regex ))
				warningMsg = "The project name does not respect the convention su-" + newSuType + "-...-consume.";
		}
		else {
			String regex = "su-" + newSuType + "-" + "[a-zA-Z_]+[.\\w\\-]*" + "-provide.*";
			if( ! this.projectNameText.getText().matches( regex ))
				warningMsg = "The project name does not respect the convention su-" + newSuType + "-...-provide.";
		}

		for( IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
			if( project.getName().equalsIgnoreCase( this.projectNameText.getText())) {
				updateStatus( NLS.bind( Messages.ConsumeJbiPage_12, this.projectNameText.getText()));
				return false;
			}
		}
		this.projectName = this.projectNameText.getText();


		// Project location
		this.projectLocationURI = null;
		File projectFile = new File(
					this.projectLocationText.getText(),
					this.projectNameText.getText());

		if( ! this.isAtDefaultLocation ) {

			if( this.projectLocationText.getText().trim().length() == 0 ) {
				updateStatus( "You have to specify the project location." );
				return false;
			}

			try {
				this.projectLocationURI = projectFile.toURI();

			} catch( Exception e ) {
				updateStatus( "The specified location is not a valid file location." );
				return false;
			}

			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject( this.projectName );
			IStatus status = ResourcesPlugin.getWorkspace().validateProjectLocationURI( project, this.projectLocationURI );
			if( status.getCode() != IStatus.OK ) {
				updateStatus( status.getMessage());
				return false;
			}
		}

		if( projectFile.exists()) {
			if( projectFile.isDirectory())
				warningMsg = "This project will override an existing directory.";
			else
				warningMsg = "This project will override an existing file.";
		}

		setMessage( warningMsg, IMessageProvider.WARNING );
		updateStatus( null );
		return true;
	}


	/**
	 * Adds widgets in the page, located above the project parts.
	 * <p>
	 * This method does nothing by default.
	 * </p>
	 *
	 * @param container the container
	 * @return the margin to set between these controls and the controls below
	 */
	protected int addControlsBeforeProject( Composite container ) {
		return 0;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {

		// Create the composite container and define its layout
		final Composite container = new Composite( parent, SWT.NONE );

		GridLayout layout = new GridLayout( 2, false );
		layout.marginLeft = layout.marginRight = 15;
		layout.marginTop = 15;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		// Add previous widgets
		int marginTop = addControlsBeforeProject( container );

		// Project name
		Label projectNameLabel = new Label( container, SWT.NONE );
		projectNameLabel.setText( "Project name:" );
		projectNameLabel.setToolTipText( "Petals project names should respect naming conventions." );
		GridData layoutData = new GridData();
		layoutData.verticalIndent = marginTop;
		projectNameLabel.setLayoutData( layoutData );

		this.projectNameText = new Text( container, SWT.SINGLE | SWT.BORDER );
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.verticalIndent = marginTop;
		this.projectNameText.setLayoutData( layoutData );
		this.projectNameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateProjectLocation();
			}
		});


		// Location
		this.useDefaultLocButton = new Button( container, SWT.CHECK );
		this.useDefaultLocButton.setText( "Use default location" );
		layoutData = new GridData ();
		layoutData.horizontalSpan = 2;
		layoutData.verticalIndent = 10;
		this.useDefaultLocButton.setLayoutData( layoutData );

		Composite locContainer = new Composite( container, SWT.NONE );
		layout = new GridLayout( 3, false );
		layout.marginHeight = layout.marginWidth = 0;
		locContainer.setLayout( layout );
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 2;
		locContainer.setLayoutData( layoutData );

		final Label locLabel = new Label( locContainer, SWT.NONE );
		locLabel.setText( "Location:" );

		this.projectLocationText = new Text( locContainer, SWT.SINGLE | SWT.BORDER );
		this.projectLocationText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
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
				String s = new DirectoryDialog( getShell()).open();
				if( s != null ) {
					ProjectPage.this.folderLocation = s;
					updateProjectLocation();
				}
			}
		});

		this.useDefaultLocButton.setSelection( this.isAtDefaultLocation );
		this.useDefaultLocButton.addSelectionListener( new SelectionAdapter () {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				ProjectPage.this.isAtDefaultLocation = ProjectPage.this.useDefaultLocButton.getSelection();
				boolean use = !ProjectPage.this.isAtDefaultLocation;

				locLabel.setEnabled( use );
				ProjectPage.this.projectLocationText.setEnabled( use );
				browseButton.setEnabled( use );
				updateProjectLocation();
				ProjectPage.this.projectLocationText.setFocus();
			}
		});


		//
		this.useDefaultLocButton.notifyListeners( SWT.Selection, new Event());
		reloadDataFromConfiguration();

		setHelpContextId( container );
		setControl( container );
		this.projectNameText.setFocus();
	}


	/**
	 * Updates the project location, using the project name and the target folder.
	 */
	private void updateProjectLocation() {

		if( this.isAtDefaultLocation ) {
			IPath path = ResourcesPlugin.getWorkspace().getRoot().getLocation();
			this.projectLocationText.setText( path.toString());
		}
		else {
			File parent = new File( this.folderLocation );
			String path = "";
			if( this.folderLocation.trim().length() > 0 && parent.exists())
				path = new File( parent, this.projectNameText.getText()).getAbsolutePath();
			this.projectLocationText.setText( path );
		}

		this.projectLocationText.setSelection( this.projectLocationText.getText().length());
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #reloadDataFromConfiguration()
	 */
	@Override
	public void reloadDataFromConfiguration() {

		if( this.projectNameText == null )
			return;

		// Update the project name from the "service-name"
		boolean defaultServiceName = false;
		String serviceName = getWizard().getDialogSettings().get( SettingConstants.SRV_NAME_VALUE );

		// If there is no service name, use a default one
		if( StringUtils.isEmpty( serviceName )) {
			serviceName = getWizard().getDialogSettings().get( SettingConstants.ITF_NAME_VALUE );
			if( StringUtils.isEmpty( serviceName )) {
				serviceName = "YourServiceName";
				defaultServiceName = true;
			}
		}

		// Create a SU name
		String newSuType = this.suType.replaceAll( "\\s", "" );
		String formattedName = JbiNameFormatter.createSuName( newSuType, serviceName, this.isConsume );

		this.projectNameText.setText( formattedName );
		if( defaultServiceName ) {
			int start = 4 + newSuType.length();
			this.projectNameText.setSelection( start, start + serviceName.length());

		} else {
			this.projectNameText.setSelection( this.projectNameText.getText().length());
		}

		String error = getErrorMessage();
		if( error != null ) {
			setErrorMessage( null );
			setMessage( error, IMessageProvider.INFORMATION );
		}
	}
}
