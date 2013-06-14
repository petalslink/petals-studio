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
package com.ebmwebsourcing.petals.services.wizards;

import java.io.File;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.InvalidJbiXmlException;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.sa.nature.SaNature;
import com.ebmwebsourcing.petals.services.su.extensions.ExtensionManager;
import com.ebmwebsourcing.petals.services.su.jbiproperties.PetalsSPPropertiesManager;
import com.ebmwebsourcing.petals.services.su.nature.SuNature;
import com.sun.java.xml.ns.jbi.Jbi;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class FileStructureImportWizardPage extends WizardPage {

	private boolean jbiXmlFileExists = false, jbiXmlValid;
	private boolean addJavaNature = false, addOtherNature = false, copyProject = false;

	private String otherNature, projectName, projectLocation;
	private String componentName, componentFunction, componentVersion;

	private Image suImg, saImg, componentImg, slImg;


	/**
	 * Constructor.
	 */
	public FileStructureImportWizardPage () {
		super( "FileStructureImportWizardPage" );
		setTitle( "Petals File Structure Import" );
		setDescription( "Create a Petals project from a Maven file structure for Petals." );

		try {
			ImageDescriptor desc = PetalsServicesPlugin.getImageDescriptor( "icons/obj16/service_unit.png" );
			if( desc != null )
				this.suImg = desc.createImage();

			desc = PetalsServicesPlugin.getImageDescriptor( "icons/obj16/service_assembly.png" );
			if( desc != null )
				this.saImg = desc.createImage();

			desc = AbstractUIPlugin.imageDescriptorFromPlugin( "com.ebmwebsourcing.petals.component", "icons/component.png" );
			if( desc != null )
				this.componentImg = desc.createImage();

			desc = AbstractUIPlugin.imageDescriptorFromPlugin( "com.ebmwebsourcing.petals.component", "icons/shared_library_16x16.png" );
			if( desc != null )
				this.slImg = desc.createImage();

		} catch( Exception e ) {
			PetalsServicesPlugin.log( e, IStatus.WARNING );
		}
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.dialogs.DialogPage
	 * #dispose()
	 */
	@Override
	public void dispose() {

		if( this.suImg != null && ! this.suImg.isDisposed())
			this.suImg.dispose();

		if( this.saImg != null && ! this.saImg.isDisposed())
			this.saImg.dispose();

		if( this.componentImg != null && ! this.componentImg.isDisposed())
			this.componentImg.dispose();

		if( this.slImg != null && ! this.slImg.isDisposed())
			this.slImg.dispose();

		super.dispose();
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl( Composite parent ) {

		Composite container = new Composite( parent, SWT.NONE );
		container.setLayout( new GridLayout ());
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));
		setControl( container );

		// Project location
		new Label( container, SWT.NONE ).setText( "Select the root of the directory to import as a project:" );

		Composite subContainer = new Composite( container, SWT.NONE );
		GridLayout layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		subContainer.setLayout( layout );
		subContainer.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final Text directoryLocationText = new Text( subContainer, SWT.BORDER | SWT.SINGLE | SWT.READ_ONLY );
		directoryLocationText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		Button browseButton = new Button( subContainer, SWT.PUSH );
		browseButton.setText( "Browse..." );


		// Project properties
		subContainer = new Composite( container, SWT.NONE );
		layout = new GridLayout( 3, false );
		layout.marginHeight = 18;
		layout.marginWidth = 0;
		subContainer.setLayout( layout );
		subContainer.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		new Label( subContainer, SWT.NONE ).setText( "Project type:" );
		final Label projectImgLabel = new Label( subContainer, SWT.NONE );
		final Label projectTypeLabel = new Label( subContainer, SWT.NONE );
		projectTypeLabel.setText( "Unknown" );

		new Label( subContainer, SWT.NONE ).setText( "Project name:" );
		final Text projectNameText = new Text( subContainer, SWT.BORDER | SWT.SINGLE );
		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 2;
		projectNameText.setLayoutData( layoutData );
		projectNameText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				FileStructureImportWizardPage.this.projectName = projectNameText.getText();
				validate();
			}
		});


		// Options
		final Button copyDirectoryButton = new Button( container, SWT.CHECK );
		copyDirectoryButton.setText( "Copy the directory in the workspace as a new project" );
		copyDirectoryButton.addSelectionListener( new SelectionListener() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {
				FileStructureImportWizardPage.this.copyProject = copyDirectoryButton.getSelection();
				validate();
			}
		});

		final Button addJavaNatureButton = new Button( container, SWT.CHECK );
		addJavaNatureButton.setText( "Add the Java nature to the project" );
		addJavaNatureButton.addSelectionListener( new SelectionListener() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {
				FileStructureImportWizardPage.this.addJavaNature = addJavaNatureButton.getSelection();
			}
		});

		final Button addOtherNatureButton = new Button( container, SWT.CHECK );
		addOtherNatureButton.setText( "" );


		// SU properties
		subContainer = new Composite( container, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginHeight = 18;
		layout.marginWidth = 0;
		subContainer.setLayout( layout );
		subContainer.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		Label l = new Label( subContainer, SWT.NONE );
		l.setText( "Service Unit projects need the following information to be complete:" );
		layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		l.setLayoutData( layoutData );

		new Label( subContainer, SWT.NONE ).setText( "Component name:" );
		final Combo nameCombo = new Combo( subContainer, SWT.BORDER | SWT.DROP_DOWN );
		nameCombo.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		nameCombo.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				FileStructureImportWizardPage.this.componentName = nameCombo.getText();
				validate();
			}
		});

		Set<String> componentNames = ExtensionManager.INSTANCE.findAllComponentNames();
		String[] _componentNames = new String[ componentNames.size()];
		nameCombo.setItems( componentNames.toArray( _componentNames ));
		nameCombo.setVisibleItemCount( componentNames.size());

		new Label( subContainer, SWT.NONE ).setText( "Component function:" );
		final Combo functionCombo = new Combo( subContainer, SWT.BORDER | SWT.DROP_DOWN );
		functionCombo.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		functionCombo.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				FileStructureImportWizardPage.this.componentFunction = functionCombo.getText();
				validate();
			}
		});

		new Label( subContainer, SWT.NONE ).setText( "Component version:" );
		final Combo versionCombo = new Combo( subContainer, SWT.BORDER | SWT.DROP_DOWN );
		versionCombo.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		versionCombo.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				FileStructureImportWizardPage.this.componentVersion = versionCombo.getText();
				validate();
			}
		});

		nameCombo.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {
				widgetSelected( e );
			}

			@Override
			public void widgetSelected( SelectionEvent e ) {

				String componentName = nameCombo.getText();
				functionCombo.add( ExtensionManager.INSTANCE.findComponentAlias( componentName ));
				functionCombo.setVisibleItemCount( 1 );
				if( functionCombo.getItemCount() > 0 )
					functionCombo.select( 0 );

				SortedSet<String> versions = ExtensionManager.INSTANCE.findComponentVersions( componentName );
				String[] _versions = new String[ versions.size()];
				versionCombo.setItems( versions.toArray( _versions ));
				versionCombo.setVisibleItemCount( versions.size() > 1 ? versions.size() : 1 );
				if( versionCombo.getItemCount() > 0 )
					versionCombo.select( 0 );

				validate();
			}
		});


		// Widget visibility and the most important listener
		addOtherNatureButton.setVisible( false );
		subContainer.setVisible( false );
		addOtherNatureButton.addSelectionListener( new SelectionListener() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {
				FileStructureImportWizardPage.this.addOtherNature = addOtherNatureButton.getSelection();
				nameCombo.getParent().setVisible( FileStructureImportWizardPage.this.addOtherNature
						&& SuNature.NATURE_ID.equals( FileStructureImportWizardPage.this.otherNature ));

				validate();
			}
		});

		browseButton.addSelectionListener( new SelectionListener() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {
				DirectoryDialog dlg = new DirectoryDialog( getShell());
				dlg.setText( "Directory Selection" );
				dlg.setMessage( "Select the directory to import as a project." );
				if( directoryLocationText.getText().length() > 0 )
					dlg.setFilterPath( directoryLocationText.getText());

				String loc = dlg.open();
				if( loc != null ) {

					// Update the UI
					directoryLocationText.setText( loc );
					FileStructureImportWizardPage.this.projectLocation = loc;
					File projectDirectory = new File( loc );
					File jbiXmlFile = new File( loc, PetalsConstants.LOC_JBI_FILE );
					FileStructureImportWizardPage.this.jbiXmlFileExists = jbiXmlFile.exists();

					// Parse the jbi.xml
					String projectType = "Not a Petals project";
					String projectName = "";
					FileStructureImportWizardPage.this.jbiXmlValid = true;
					FileStructureImportWizardPage.this.otherNature = null;
					Image img = null;
					if( FileStructureImportWizardPage.this.jbiXmlFileExists ) {

						projectName = projectDirectory.getName();
						try {
							Jbi jbi = JbiXmlUtils.getJbiXmlModel( jbiXmlFile );
							if( jbi.getServices() != null ) {

								// Same properties than the other possibilities
								projectType = "Service Unit";
								img = FileStructureImportWizardPage.this.suImg;
								addOtherNatureButton.setText( "Add the SU nature to the project" );
								addOtherNatureButton.setVisible( true );
								FileStructureImportWizardPage.this.otherNature = SuNature.NATURE_ID;

								// A specific treatment for SU properties
								File suPropertiesFile = new File( loc, PetalsSPPropertiesManager.PROPERTIES_FILENAME );
								if( suPropertiesFile.exists()) {
									Properties prop = PetalsSPPropertiesManager.getProperties( suPropertiesFile );
									nameCombo.setText( prop.getProperty( PetalsSPPropertiesManager.COMPONENT_NAME, "" ));
									versionCombo.setText( prop.getProperty( PetalsSPPropertiesManager.COMPONENT_VERSION, "" ));
									functionCombo.setText( prop.getProperty( PetalsSPPropertiesManager.COMPONENT_FUNCTION, "" ));

								} else {
									nameCombo.setText( "" );
									versionCombo.setText( "" );
									functionCombo.setText( "" );
								}
							}
							else if( jbi.getServiceAssembly() != null ) {
								projectType = "Service Assembly";
								img = FileStructureImportWizardPage.this.saImg;
								addOtherNatureButton.setText( "Add the SA nature to the project" );
								addOtherNatureButton.setVisible( true );
								FileStructureImportWizardPage.this.otherNature = SaNature.NATURE_ID;
							}
							else if( jbi.getComponent() != null ) {
								projectType = "JBI Component";
								img = FileStructureImportWizardPage.this.componentImg;
								addOtherNatureButton.setVisible( false );
							}
							else if( jbi.getSharedLibrary() != null ) {
								projectType = "Shared Library";
								img = FileStructureImportWizardPage.this.slImg;
								addOtherNatureButton.setVisible( false );
							}
							else {
								FileStructureImportWizardPage.this.jbiXmlValid = false;
							}

							addJavaNatureButton.getParent().layout();

						} catch( InvalidJbiXmlException e1 ) {
							FileStructureImportWizardPage.this.jbiXmlValid = false;
						}
					}

					// Update the rest of the UI
					projectImgLabel.setImage( img );
					projectTypeLabel.setText( projectType );
					projectNameText.setText( projectName );
					projectImgLabel.getParent().layout();

					// Properties should be visible if and only if the SU nature is added
					// Otherwise, the component information will not be used
					nameCombo.getParent().setVisible( FileStructureImportWizardPage.this.addOtherNature
							&& SuNature.NATURE_ID.equals( FileStructureImportWizardPage.this.otherNature ));

					validate();
				}
			}
		});


		// Force the shell size
		setErrorMessage( null );
		Point size = getShell().computeSize( 600, 560 );
		getShell().setSize( size );

		Rectangle rect = Display.getCurrent().getBounds();
		getShell().setLocation((rect.width - size.x) / 2, (rect.height - size.y) / 2);
	}


	/**
	 * Validates the page data.
	 */
	private void validate() {

		String msg = null;
		if( ! this.jbiXmlFileExists )
			msg = "This directory does not contain a src/main/jbi/jbi.xml file.";
		else if( ! this.jbiXmlValid )
			msg = "The jbi.xml is not valid. The project type could not be determined.";
		else if( StringUtils.isEmpty( this.projectName ))
			msg = "You have to specify the project name.";
		else if( new File( this.projectLocation, ".project" ).exists())
			msg = "This directory points to an Eclipse project and thus should be imported with the usual wizard.";
		else {
			IWorkspaceRoot wr = ResourcesPlugin.getWorkspace().getRoot();
			if( wr.getProject( this.projectName ).exists())
				msg = "There is already a project called " + this.projectName + " in the workspace.";
			else if( this.copyProject
					&& wr.getLocation().append( this.projectName ).toFile().exists())
				msg = "The directory cannot be copied in the workspace. A directory with this name already exists.";
			else if( SuNature.NATURE_ID.equals( this.otherNature )) {
				if( StringUtils.isEmpty( this.componentName ))
					msg = "You have to specify the name of the target component.";
				else if( StringUtils.isEmpty( this.componentFunction ))
					msg = "You have to specify the function of the target component (e.g. SOAP).";
				else if( StringUtils.isEmpty( this.componentVersion ))
					msg = "You have to specify the version of the target component.";
			}
		}

		setErrorMessage( msg );
		setPageComplete( msg == null );
	}


	/**
	 * @return the addJavaNature
	 */
	public boolean isAddJavaNature() {
		return this.addJavaNature;
	}


	/**
	 * @return the addOtherNature
	 */
	public boolean isAddOtherNature() {
		return this.addOtherNature;
	}


	/**
	 * @return the copyProject
	 */
	public boolean isCopyProject() {
		return this.copyProject;
	}


	/**
	 * @return the otherNature
	 */
	public String getOtherNature() {
		return this.otherNature;
	}


	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return this.projectName;
	}


	/**
	 * @return the componentName
	 */
	public String getComponentName() {
		return this.componentName;
	}


	/**
	 * @return the componentFunction
	 */
	public String getComponentFunction() {
		return this.componentFunction;
	}


	/**
	 * @return the componentVersion
	 */
	public String getComponentVersion() {
		return this.componentVersion;
	}


	/**
	 * @return the projectLocation
	 */
	public String getProjectLocation() {
		return this.projectLocation;
	}
}
