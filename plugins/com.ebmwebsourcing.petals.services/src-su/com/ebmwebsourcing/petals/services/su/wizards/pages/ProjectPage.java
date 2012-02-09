/****************************************************************************
 *
 * Copyright (c) 2008-2012, EBM WebSourcing
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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.dialogs.WorkingSetGroup;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.NameUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.services.su.Messages;
import com.ebmwebsourcing.petals.services.su.wizards.PetalsMode;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ProjectPage extends AbstractSuWizardPage {

	public static final String PAGE_NAME = "ProjectPage";

	private WorkingSetGroup workingSetGroup;
	private String projectName, projectContainer, oldBaseName;
	private Text projectNameText, projectLocationText;


	/**
	 * Constructor.
	 */
	public ProjectPage() {
		super( PAGE_NAME );
		setDescription( "Define the project properties." );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #validate()
	 */
	@Override
	public boolean validate() {

		// Project name
		if( StringUtils.isEmpty( this.projectName )) {
			updateStatus( "The project name cannot be empty." );
			return false;
		}

		// Name syntax
		String warningMsg = null;

		// Remove white spaces ("File Transfer") - cause of problems with systems
		String newSuType = getWizard().getComponentVersionDescription().getComponentAlias().replaceAll( "\\s", "" ).trim();
		if( getWizard().getPetalsMode() == PetalsMode.provides) {
			String regex = "su-" + newSuType + "-" + "[a-zA-Z_]+[.\\w\\-]*" + "-provide.*";
			if( ! this.projectName.matches( regex ))
				warningMsg = "The project name does not respect the convention su-" + newSuType + "-...-provide.";
		} else {
			String regex = "su-" + newSuType + "-" + "[a-zA-Z_]+[.\\w\\-]*" + "-consume.*";
			if( ! this.projectName.matches( regex ))
				warningMsg = "The project name does not respect the convention su-" + newSuType + "-...-consume.";
		}


		// Name already used?
		for( IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
			if( project.getName().equalsIgnoreCase( this.projectNameText.getText())) {
				updateStatus( NLS.bind( Messages.ConsumeJbiPage_12, this.projectNameText.getText()));
				return false;
			}
		}


		// Project location
		File targetfile = computeProjectLocation();
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject( this.projectName );
		if( ! isAtDefaultlocation()) {
			IStatus status = ResourcesPlugin.getWorkspace().validateProjectLocationURI( project, targetfile.toURI());
			if( status.getCode() != IStatus.OK ) {
				updateStatus( status.getMessage());
				return false;
			}
		}

		if( targetfile.exists()) {
			if( targetfile.isDirectory())
				warningMsg = "This project will override an existing directory.";
			else
				warningMsg = "This project will override an existing file.";
		}


		// Display warning or clear the messages
		setMessage( warningMsg, IMessageProvider.WARNING );
		updateStatus( null );
		return true;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl( Composite parent ) {

		// Create the composite container and define its layout
		final Composite container = SwtFactory.createComposite( parent );
		setControl( container );
		SwtFactory.applyNewGridLayout( container, 1, false, 15, 15, 0, 15 );
		SwtFactory.applyHorizontalGridData( container );


		// Project name
		Composite locContainer = SwtFactory.createComposite( container );
		SwtFactory.applyNewGridLayout( locContainer, 2, false, 0, 0, 5, 0 );
		SwtFactory.applyHorizontalGridData( locContainer );

		SwtFactory.createLabel( locContainer, "Project name:", "Petals project names should respect naming conventions." );
		this.projectNameText = SwtFactory.createSimpleTextField( locContainer, true );
		this.projectNameText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				ProjectPage.this.projectName = ProjectPage.this.projectNameText.getText();
				updateProjectLocation();
				validate();
			}
		});


		// Location
		final Button useDefaultLocButton = SwtFactory.createCheckBoxButton( container, "Use default location", null, true );
		SwtFactory.applyGridData( useDefaultLocButton, 2, 10 );

		locContainer = SwtFactory.createComposite( container );
		SwtFactory.applyNewGridLayout( locContainer, 3, false, 0, 0, 11, 0 );
		SwtFactory.applyHorizontalGridData( locContainer, 2 );

		final Label locLabel = SwtFactory.createLabel( locContainer, "Location:", null );
		this.projectLocationText = SwtFactory.createSimpleTextField( locContainer, false );

		// Set the width hint: otherwise, long project locations will make the wizard page too wide!
		GridDataFactory.swtDefaults().align( SWT.FILL, SWT.CENTER ).grab( true, false ).hint( 200, SWT.DEFAULT ).applyTo( this.projectLocationText );

		this.projectLocationText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				ProjectPage.this.projectName = ProjectPage.this.projectNameText.getText();
				validate();
			}
		});

		final Button browseButton = SwtFactory.createPushButton( locContainer, "Browse...", null );
		browseButton.addSelectionListener( new SelectionAdapter () {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				String s = new DirectoryDialog( getShell()).open();
				if( s != null ) {
					ProjectPage.this.projectContainer = s;
					updateProjectLocation();
				}
			}
		});

		// Working sets
		this.workingSetGroup = new WorkingSetGroup(
				container, new StructuredSelection(),
				new String[] { "org.eclipse.ui.resourceWorkingSetPage" });


		// Other listeners
		useDefaultLocButton.addSelectionListener( new SelectionAdapter () {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				boolean enabled = ! useDefaultLocButton.getSelection();
				if( ! enabled )
					ProjectPage.this.projectContainer = null;

				locLabel.setEnabled( enabled );
				ProjectPage.this.projectLocationText.setEnabled( enabled );
				browseButton.setEnabled( enabled );
				updateProjectLocation();
			}
		});


		// Complete the page
		useDefaultLocButton.notifyListeners( SWT.Selection, new Event());
	}


	/**
	 * Updates the project location, using the project name and the target folder.
	 */
	private void updateProjectLocation() {
		String s = computeProjectLocation().getAbsolutePath();
		this.projectLocationText.setText( s );
		this.projectLocationText.setSelection( s.length());
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage
	 * #setVisible(boolean)
	 */
	@Override
	public void setVisible( boolean visible ) {

		// Update the UI
		int start = -1, end = -1;
		if( visible ) {

			// Update the project name from the "service-name"
			boolean defaultServiceName = false;
			AbstractEndpoint ae = getNewlyCreatedEndpoint();
			String serviceName = ae.getServiceName() != null ? ae.getServiceName().getLocalPart() : null;

			// If there is no service name, use a default one
			if( StringUtils.isEmpty( serviceName )) {
				serviceName = ae.getInterfaceName() != null ? ae.getInterfaceName().getLocalPart() : null;
				if( StringUtils.isEmpty( serviceName )) {
					serviceName = "YourServiceName";
					defaultServiceName = true;
				}
			}

			// Store it, so that we know when we can reset the project name...
			// and when we must let it unchanged
			boolean refresh = false;
			if( this.oldBaseName == null || ! this.oldBaseName.equals( serviceName )) {
				this.oldBaseName = serviceName;
				refresh = true;
			}

			// Create a SU name
			String newSuType = getWizard().getComponentVersionDescription().getComponentAlias().replaceAll( "\\s", "" );
			if( refresh ) {
				String formattedName = NameUtils.createSuName( newSuType, serviceName, getWizard().getPetalsMode() != PetalsMode.provides);
				this.projectNameText.setText( formattedName );
			}

			if( defaultServiceName ) {
				start = 4 + newSuType.length();
				end = start + serviceName.length();
			}

			String error = getErrorMessage();
			if( error != null ) {
				setErrorMessage( null );
				setMessage( error, IMessageProvider.INFORMATION );
			}
		}

		// Super
		super.setVisible( visible );

		// Force the focus
		if( visible ) {
			this.projectNameText.forceFocus();
			if( start != -1 && end != -1 )
				this.projectNameText.setSelection( start, end );
			else
				this.projectLocationText.setSelection( 5 );
		}
	}


	/**
	 * @return the selected working sets
	 */
	public IWorkingSet[] getSelectedWorkingSets() {
		return this.workingSetGroup == null ? new IWorkingSet[ 0 ] : this.workingSetGroup.getSelectedWorkingSets();
	}


	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return this.projectName;
	}


	/**
	 * @return the projectLocationURI
	 */
	public File computeProjectLocation() {
		Object parent = this.projectContainer == null ? ResourcesPlugin.getWorkspace().getRoot().getLocation() : this.projectContainer;
		return StringUtils.isEmpty( this.projectName ) ? new File( parent.toString()) : new File( parent.toString(), this.projectName );
	}


	/**
	 * @return true if the project is at the default location, false otherwise
	 */
	public boolean isAtDefaultlocation() {
		return this.projectContainer == null;
	}
}
