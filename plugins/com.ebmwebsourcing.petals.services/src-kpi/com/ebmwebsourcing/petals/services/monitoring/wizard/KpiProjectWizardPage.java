/****************************************************************************
 * 
 * Copyright (c) 2010-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.monitoring.wizard;

import java.io.File;
import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormText;

import com.ebmwebsourcing.petals.common.internal.provisional.preferences.PreferencesManager;
import com.ebmwebsourcing.petals.common.internal.provisional.ui.FixedShellTooltip;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.Messages;
import com.ebmwebsourcing.petals.services.su.wizards.pages.ProjectPage;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class KpiProjectWizardPage extends ProjectPage {

	private final static int COMBO_WIDTH = 190;

	private String projectLocation;
	private String processName;
	private Combo processCombo;
	private boolean consumeConfiguration = false;
	private boolean appendToProcess = false;
	private FixedShellTooltip helpTooltip;

	private KpiConsumeWizardPage consumePage;
	private KpiXPathWizardPage xpathPage;


	/**
	 * Constructor.
	 */
	public KpiProjectWizardPage() {
		super( "KPI", "1.0", true );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.ProjectPage
	 * #addControlsBeforeProject(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected int addControlsBeforeProject( Composite container ) {

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

		// Process name
		Label label = new Label( container, SWT.NONE );
		label.setText( "Process Name:" );
		label.setToolTipText( "The name of the process to watch" );

		this.processCombo = new Combo( container, SWT.SINGLE | SWT.BORDER | SWT.DROP_DOWN );
		this.processCombo.setVisibleItemCount( 5 );
		this.processCombo.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		// Restore previous process names
		String mergedNames = PetalsServicesPlugin.getDefault().getPreferenceStore().getString( KpiConstants.PREF_PROCESS_NAMES );
		if( ! StringUtils.isEmpty( mergedNames ))
			this.processCombo.setItems( mergedNames.split( KpiConstants.PREF_PROCESS_SEPARATOR ));

		// Generate the project name from the process name
		this.processCombo.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				KpiProjectWizardPage.this.processName = KpiProjectWizardPage.this.processCombo.getText();
				KpiProjectWizardPage.this.projectNameText.setText( "sa-KPI-" + KpiProjectWizardPage.this.processName + "-consume" );
			}
		});

		// Append to an existing process?
		label = new Label( container, SWT.NONE );
		label.setText( "Creation mode:" );
		label.setToolTipText( "Create a new process or append to an existing one?" );

		final Combo creationModeCombo = new Combo( container, SWT.SINGLE | SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY );
		creationModeCombo.setVisibleItemCount( 2 );
		creationModeCombo.setLayoutData( new GridData( COMBO_WIDTH, SWT.DEFAULT ));
		creationModeCombo.setItems( new String[] {
					"Create a new process", "Append to an existing process"
		});

		creationModeCombo.select( 0 );
		creationModeCombo.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				KpiProjectWizardPage.this.appendToProcess = creationModeCombo.getSelectionIndex() == 1;
				KpiProjectWizardPage.this.useDefaultLocButton.setEnabled( ! KpiProjectWizardPage.this.appendToProcess );
				validate();
			}
		});

		this.processCombo.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				creationModeCombo.select( 1 );
				creationModeCombo.notifyListeners( SWT.Selection, new Event());
			}
		});

		// Dialect
		label = new Label( container, SWT.NONE );
		label.setText( "Watching Filter:" );
		label.setToolTipText( "The kind of rules used to select the watched contents" );

		final Combo confCombo = new Combo( container, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY );
		confCombo.setVisibleItemCount( 2 );
		confCombo.setItems( new String[] {
					"Message Content (XPath dialect)",
					"Service Identifier (SOA dialect)"
		});

		confCombo.select( 0 );
		confCombo.setLayoutData( new GridData( COMBO_WIDTH, SWT.DEFAULT ));
		confCombo.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				int index = confCombo.getSelectionIndex();
				KpiProjectWizardPage.this.consumeConfiguration = index == 1;
			}
		});

		// Margin below
		return 28;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.ProjectPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl( Composite parent ) {
		super.createControl( parent );

		// Make the project text read only
		this.projectNameText.setEditable( false );
		this.processCombo.setFocus();
		updateStatus( null );

		// Force the wizard size
		Point size = getShell().computeSize( 510, 480 );
		getShell().setSize( size );

		Rectangle rect = Display.getCurrent().getBounds();
		getShell().setLocation((rect.width - size.x) / 2, (rect.height - size.y) / 2);
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #reloadDataFromConfiguration()
	 */
	@Override
	public void reloadDataFromConfiguration() {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.ProjectPage
	 * #validate()
	 */
	@Override
	public boolean validate() {

		// Handle problems with the Maven configuration at the beginning of the wizard
		if( ! PreferencesManager.isMavenTemplateConfigurationValid()) {
			updateStatus( "There is an error in your preferences about custom POM templates." );
			this.helpTooltip.show();
			return false;
		}
		else {
			this.helpTooltip.hide();
		}

		// Process
		if( StringUtils.isEmpty( this.processName )) {
			updateStatus( "You must define the process name." );
			return false;
		}

		// Project
		IWorkspaceRoot wr = ResourcesPlugin.getWorkspace().getRoot();
		for( IProject project : wr.getProjects()) {
			if( project.getName().equalsIgnoreCase( this.projectNameText.getText())) {
				updateStatus( NLS.bind( Messages.ConsumeJbiPage_12, this.projectNameText.getText()));
				if( ! this.appendToProcess )
					return false;
			}
		}

		this.projectName = this.projectNameText.getText();
		if( this.appendToProcess
					&& ! wr.getProject( this.projectName ).exists()) {
			updateStatus( "This process does not exist. Append mode is not allowed." );
			return false;
		}


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

		if( ! this.appendToProcess && projectFile.exists()) {
			if( projectFile.isDirectory())
				setMessage( "This project will override an existing directory.", IMessageProvider.WARNING );
			else
				setMessage( "This project will override an existing file.", IMessageProvider.WARNING );
		}
		else {
			setMessage( null, IMessageProvider.WARNING );
		}

		this.projectLocation = this.projectLocationText.getText();
		updateStatus( null );
		return true;
	}


	/**
	 * Returns the project location.
	 * @return null for the default location, something else otherwise
	 */
	public String getProjectLocation() {
		return this.isAtDefaultLocation ? null : this.projectLocation;
	}


	/**
	 * @return the project name
	 */
	public String getProjectName() {
		return this.projectName;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.WizardPage
	 * #canFlipToNextPage()
	 */
	@Override
	public boolean canFlipToNextPage() {
		return ! StringUtils.isEmpty( this.processName );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #getNextPage()
	 */
	@Override
	public IWizardPage getNextPage() {

		IWizardPage nextPage = null;
		if( this.consumeConfiguration ) {

			if( this.consumePage == null ) {
				this.consumePage = new KpiConsumeWizardPage();
				this.consumePage.setTitle( "Watched Service(s)" );
				this.consumePage.setDescription( "Give the identifiers of the service(s) to watch." );
				((Wizard) getWizard()).addPage( this.consumePage );
			}

			nextPage = this.consumePage;
		}
		else {

			if( this.xpathPage == null ) {
				this.xpathPage = new KpiXPathWizardPage();
				this.xpathPage.setTitle( "Filter Expressions" );
				this.xpathPage.setDescription( "Give the XPath expression used to filter messages." );
				((Wizard) getWizard()).addPage( this.xpathPage );
			}

			this.xpathPage.setProcessName( this.processName );
			nextPage = this.xpathPage;
		}

		return nextPage;
	}


	/**
	 * @return the processType
	 */
	public int getProcessType() {

		int processType;
		if( this.consumePage != null )
			processType = this.consumePage.getConfigurationPage().getProcessType();
		else
			processType = this.xpathPage.getConfigurationPage().getProcessType();

		return processType;
	}


	/**
	 * Returns a collection of beans defining projects to create.
	 * @return the projects to create
	 */
	public Collection<KpiProjectBean> getProjectsToCreate() {

		Collection<KpiProjectBean> result;
		if( this.consumePage != null )
			result = this.consumePage.getConfigurationPage().getProjectsToCreate();
		else
			result = this.xpathPage.getConfigurationPage().getProjectsToCreate();

		return result;
	}


	/**
	 * @return the consumeConfiguration
	 */
	protected final boolean isConsumeConfiguration() {
		return this.consumeConfiguration;
	}


	/**
	 * @return the appendToProcess
	 */
	protected final boolean isAppendToProcess() {
		return this.appendToProcess;
	}


	/**
	 * @return the processName
	 */
	protected final String getProcessName() {
		return this.processName;
	}
}
