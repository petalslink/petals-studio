/****************************************************************************
 *
 * Copyright (c) 2009-2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.cdk.editor;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.event.HyperlinkEvent;
import javax.xml.namespace.QName;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.ide.IDE;

import com.ebmwebsourcing.petals.common.internal.provisional.ui.StyledElementListSelectionDialog;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.UriUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlParser;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlParser.JbiBasicBean;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUpdater;
import com.ebmwebsourcing.petals.common.internal.provisional.wizards.WsdlImportWizard;
import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.jbi.editor.AbstractServicesFormPage;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.InitializeModelExtensionCommand;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.jbi.editor.su.CompounedSUDetailsPage;
import com.ebmwebsourcing.petals.services.jbi.editor.su.PetalsHyperlinkListener;
import com.ebmwebsourcing.petals.services.su.ui.WsdlParsingJobManager;
import com.ebmwebsourcing.petals.services.su.ui.WsdlParsingJobManager.WsdlParsingListener;
import com.ebmwebsourcing.petals.services.utils.PCStyledLabelProvider;
import com.sun.java.xml.ns.jbi.JbiPackage;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class CDK5ProvidesDetails implements WsdlParsingListener, JbiEditorDetailsContribution {

	private Button generateEdptButton;
	private Text wsdlText;
	private final WsdlParsingJobManager wsdlParsingJob = new WsdlParsingJobManager ();
	private String edptBackup;
	private final IFile editedFile;

	private final CompounedSUDetailsPage hostDetailsPage;
	private final AbstractServicesFormPage generalFormPage;
	private Provides selectedEndpoint;
	private ModifyListener wsdlChangedListener;


	/**
	 * Constructor.
	 * @param page
	 * @param cdkSupport
	 */
	public CDK5ProvidesDetails(CompounedSUDetailsPage hostDetailsPage) {
		this.hostDetailsPage = hostDetailsPage;
		this.generalFormPage = hostDetailsPage.getGeneralPage();
		this.selectedEndpoint = (Provides) hostDetailsPage.getSelectedEndpoint();
		hostDetailsPage.setSectionTitle("Provides properties");
		hostDetailsPage.setSectionDescription("Edit the interface, service and end-point names of the provided service.");
		this.editedFile = ((IFileEditorInput)this.generalFormPage.getEditorInput()).getFile();
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.blocks.GeneralAbstractDetails
	 * #addContentAfter(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void addSUContentAfterEndpoint( Composite parent ) {

		FormToolkit toolkit = this.hostDetailsPage.getManagedForm().getToolkit();

		// Baaaad!
		toolkit.createLabel( parent, "" );

		// Add the button and its label
		this.generateEdptButton = toolkit.createButton( parent, "", SWT.CHECK );
		this.generateEdptButton.setText( "Generate the end-point at deployment time" );
		this.generateEdptButton.setToolTipText( "The end-point name will be generated by Petals on deployment" );
		this.generateEdptButton.addSelectionListener( new SelectionListener() {

			@Override
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {
				boolean selected = CDK5ProvidesDetails.this.generateEdptButton.getSelection();
				CDK5ProvidesDetails.this.hostDetailsPage.setEndpointEnabled( ! selected );

				if( CDK5ProvidesDetails.this.hostDetailsPage.isReportChanges() ) {
					if( selected ) {
						CDK5ProvidesDetails.this.edptBackup = CDK5ProvidesDetails.this.selectedEndpoint.getEndpointName().trim();
					} else if( CDK5ProvidesDetails.this.edptBackup == null
							|| CDK5ProvidesDetails.this.edptBackup.trim().length() == 0 ) {
						CDK5ProvidesDetails.this.edptBackup = CDK5ProvidesDetails.this.selectedEndpoint.getServiceName().getLocalPart() + "Endpoint";
					}

					String edptValue = selected ? PetalsConstants.AUTO_GENERATE : CDK5ProvidesDetails.this.edptBackup;

					SetCommand command = new SetCommand(CDK5ProvidesDetails.this.generalFormPage.getEditDomain(), CDK5ProvidesDetails.this.selectedEndpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__ENDPOINT_NAME, edptValue);
					CDK5ProvidesDetails.this.generalFormPage.getEditDomain().getCommandStack().execute(command);
					CDK5ProvidesDetails.this.generalFormPage.updatePage();
				}
			}
		});
	}

	@Override
	public void addSUContentAfter(Composite container) {
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.blocks.GeneralAbstractDetails
	 * #addContentBefore(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void addSUContentBefore( Composite parent ) {

		Color blueFont = parent.getDisplay().getSystemColor( SWT.COLOR_DARK_BLUE );
		FormToolkit toolkit = this.hostDetailsPage.getManagedForm().getToolkit();

		// The label browser
		Label wsdlLabel = toolkit.createLabel( parent, "WSDL location:" );
		wsdlLabel.setToolTipText( "The relative path of the WSDL in the service unit or an URL" );
		wsdlLabel.setForeground( blueFont );

		Composite container = toolkit.createComposite( parent );
		GridLayout layout = new GridLayout( 2, false );
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		this.wsdlText = toolkit.createText( container, "", SWT.SINGLE | SWT.BORDER );
		this.wsdlText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		Button browseButton = toolkit.createButton( container, "Browse...", SWT.PUSH );
		browseButton.setToolTipText( "Select a WSDL located in the project resources" );
		browseButton.addSelectionListener( new SelectionListener() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {

				IProject project = CDK5ProvidesDetails.this.editedFile.getProject();
				IFolder resourceFolder = project.getFolder( PetalsConstants.LOC_RES_FOLDER );

				// FIXME: replace this dialog by one that only shows WSDLs?
				ElementTreeSelectionDialog dlg = ResourceUtils.createWorkspaceFileSelectionDialog(
							CDK5ProvidesDetails.this.wsdlText.getShell(), resourceFolder, "wsdl" );

				dlg.setTitle( "WSDL Selection" );
				dlg.setMessage( "Select a WSDL file located in the project's resource directory." );

				File file = JbiXmlUtils.getWsdlFile(
							CDK5ProvidesDetails.this.editedFile,
							CDK5ProvidesDetails.this.wsdlText.getText());

				if( file != null ) {
					IFile wsdlFile = ResourceUtils.getIFile( file );
					if( wsdlFile != null )
						dlg.setInitialElementSelections( Arrays.asList( wsdlFile ));
				}

				if( dlg.open() == Window.OK ) {
					IFile selectedFile = (IFile) dlg.getResult()[ 0 ];
					int rfsc = resourceFolder.getFullPath().segmentCount();
					String wsdlValue = selectedFile.getFullPath().removeFirstSegments( rfsc ).toString();
					CDK5ProvidesDetails.this.wsdlText.setText( wsdlValue );
				}
			}
		});


		// The helpers
		toolkit.createLabel( parent, "" );

		Section section = toolkit.createSection( parent, ExpandableComposite.TWISTIE );
		section.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		section.clientVerticalSpacing = 10;
		section.setText( "Helpers" );
		section.setForeground( blueFont );

		container = toolkit.createComposite( section );
		layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.marginBottom = 20;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		section.setClient( container );

		final Hyperlink importLink = toolkit.createHyperlink(
					container, "Import this WSDL in the project", SWT.NONE );
		importLink.setToolTipText( "Import this WSDL in the project" );
		importLink.setEnabled( false );
		importLink.addHyperlinkListener( new PetalsHyperlinkListener() {

			public void linkActivated( HyperlinkEvent e ) {

				URI wsdlUri = null;
				File wsdlFile = JbiXmlUtils.getWsdlFile(
							CDK5ProvidesDetails.this.editedFile,
							CDK5ProvidesDetails.this.wsdlText.getText());

				if( wsdlFile != null )
					wsdlUri = wsdlFile.toURI();
				else
					wsdlUri = UriUtils.urlToUri( CDK5ProvidesDetails.this.wsdlText.getText());

				if( wsdlUri != null ) {
					WsdlImportWizard wiz = new WsdlImportWizard();
					IFolder resFolder = CDK5ProvidesDetails.this.editedFile.getProject().getFolder(
								PetalsConstants.LOC_RES_FOLDER );

					wiz.setInitialContainer( resFolder );
					wiz.setInitialWsdlUri( wsdlUri.toString());

					IWorkbench workbench = PlatformUI.getWorkbench();
					wiz.init( workbench, null );

					WizardDialog dlg = new WizardDialog( workbench.getActiveWorkbenchWindow().getShell(), wiz );
					if( dlg.open() == Window.OK ) {
						File importedFile = wiz.getWsdlFileAfterImport();
						String value = IoUtils.getBasicRelativePath( resFolder.getLocation().toFile(), importedFile );
						CDK5ProvidesDetails.this.wsdlText.setText( value );
					}
				}
			}
		});

		final Hyperlink selectServiceLink = toolkit.createHyperlink(
					container, "Select a service in the WSDL to fill-in the properties below", SWT.NONE );

		selectServiceLink.setToolTipText( "A WSDL may describe several services instead of just one" );
		selectServiceLink.setEnabled( false );
		selectServiceLink.addHyperlinkListener( new PetalsHyperlinkListener() {

			public void linkActivated( HyperlinkEvent e ) {

				PCStyledLabelProvider lp = new PCStyledLabelProvider( CDK5ProvidesDetails.this.wsdlText );
				StyledElementListSelectionDialog dlg = new StyledElementListSelectionDialog(
							CDK5ProvidesDetails.this.wsdlText.getShell(), lp );

				dlg.setFilter( "*" );
				dlg.setSize( 120, 20 );
				dlg.setMultipleSelection( false );
				dlg.setTitle( "Service Selection" );
				dlg.setMessage( "Select the service to expose inside Petals." );

				List<JbiBasicBean> wsdlBeans = parseWsdl();
				dlg.setElements( wsdlBeans.toArray());
				if( dlg.open() == Window.OK ) {

					JbiBasicBean bean = (JbiBasicBean) dlg.getResult()[ 0 ];
					ApplyBasicBeanToService(bean);
				}

				lp.dispose();
			}
		});

		final Hyperlink openLink = toolkit.createHyperlink( container, "Open in the WSDL editor", SWT.NONE );
		openLink.setToolTipText( "Open this WSDL in the default WSDL editor" );
		openLink.setEnabled( false );
		openLink.addHyperlinkListener( new PetalsHyperlinkListener() {

			public void linkActivated( HyperlinkEvent e ) {

				// This link is enabled only if the WSDL references an existing file
				File file = JbiXmlUtils.getWsdlFile(
							CDK5ProvidesDetails.this.editedFile,
							CDK5ProvidesDetails.this.wsdlText.getText());

				IFile f = ResourceUtils.getIFile( file );
				if( f != null ) {
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					try {
						IDE.openEditor( page, f, true );

					} catch( PartInitException exception ) {
						PetalsServicesPlugin.log( exception, IStatus.ERROR );
					}
				}
			}
		});

		final Hyperlink updateLink = toolkit.createHyperlink(
					container, "Update the service end-point in the WSDL", SWT.NONE );
		updateLink.setToolTipText( "Update the WSDL so that it declared end-point for this service is the one defined below" );
		updateLink.setEnabled( false );
		updateLink.addHyperlinkListener( new PetalsHyperlinkListener() {

			public void linkActivated( HyperlinkEvent e ) {

				// Enabled only when the WSDL points to an existing file
				File f = JbiXmlUtils.getWsdlFile(
							CDK5ProvidesDetails.this.editedFile,
							CDK5ProvidesDetails.this.wsdlText.getText());

				QName serviceName = CDK5ProvidesDetails.this.selectedEndpoint.getServiceName();

				String edptName = CDK5ProvidesDetails.this.generateEdptButton.getSelection() ? PetalsConstants.AUTO_GENERATE : CDK5ProvidesDetails.this.selectedEndpoint.getEndpointName();

				if( ! WsdlUpdater.getInstance().update( f, serviceName, CDK5ProvidesDetails.this.selectedEndpoint.getInterfaceName(), edptName )) {
					MessageDialog.openError(
								CDK5ProvidesDetails.this.wsdlText.getShell(),
								"End-point Update Failure",
					"The end-point could not be updated in the WSDL." );
				}
				else {
					// Force the validation of the file
					try {
						CDK5ProvidesDetails.this.editedFile.touch( null );

					} catch( CoreException e1 ) {
						PetalsServicesPlugin.log( e1, IStatus.WARNING );
					}
				}
			}
		});


		this.wsdlChangedListener = new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {

				// Update the hyper links
				URI uri = null;
				IFile f = CDK5ProvidesDetails.this.editedFile.getParent().getFile(new Path(CDK5ProvidesDetails.this.wsdlText.getText()));

				if( f != null && f.exists()) {
					openLink.setEnabled( f != null );
					selectServiceLink.setEnabled( true );
					updateLink.setEnabled( true );

					IPath resFolderPath = CDK5ProvidesDetails.this.editedFile.getProject().getFolder(
								PetalsConstants.LOC_RES_FOLDER ).getLocation();

					boolean isInResFolder = resFolderPath.isPrefixOf(f.getLocation());
					importLink.setEnabled( ! isInResFolder );
				}
				else {
					if((uri = getWsdlUri()) != null ) {
						openLink.setEnabled( false );
						selectServiceLink.setEnabled( true );
						importLink.setEnabled( true );
						updateLink.setEnabled( false );
					}
					else {
						openLink.setEnabled( false );
						selectServiceLink.setEnabled( false );
						importLink.setEnabled( false );
						updateLink.setEnabled( false );
					}
				}

				// Parse the WSDL
				if( uri != null
							&& CDK5ProvidesDetails.this.hostDetailsPage.isReportChanges() )
					CDK5ProvidesDetails.this.wsdlParsingJob.setWsdlUri( uri );
			}
		};
		this.wsdlText.addModifyListener( this.wsdlChangedListener);


		// Register itself as a listener
		// TODO this.wsdlParsingJob.addWsdlParsingListener( this );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.blocks.GeneralAbstractDetails
	 * #refreshCustomWidgets()
	 */
	@Override
	public void refresh() {
		this.selectedEndpoint = (Provides) this.hostDetailsPage.getSelectedEndpoint();
		if( this.selectedEndpoint == null
					|| this.wsdlText == null 	// page already loaded?
					|| this.wsdlText.isDisposed())
			return;

		InitializeModelExtensionCommand fixExtensionCommand = new InitializeModelExtensionCommand(Cdk5Package.eINSTANCE, this.selectedEndpoint);
		if (fixExtensionCommand.canExecute()) {
			this.generalFormPage.getEditDomain().getCommandStack().execute(fixExtensionCommand);
		}

		// End-point
		DataBindingContext dbc = this.hostDetailsPage.getDataBindingContext();

		String name = this.selectedEndpoint.getEndpointName();
		if( PetalsConstants.AUTO_GENERATE.equalsIgnoreCase( name )) {
			this.hostDetailsPage.setEndpointEnabled(false);
			this.generateEdptButton.setSelection( true );
		}
		else {
			this.hostDetailsPage.setEndpointEnabled(true);
			this.generateEdptButton.setSelection( false );
		}


		// WSDL
		dbc.bindValue(
			SWTObservables.observeDelayedValue(300, SWTObservables.observeText(this.wsdlText, SWT.Modify)),
			EMFEditObservables.observeValue(this.generalFormPage.getEditDomain(), this.selectedEndpoint, Cdk5Package.Literals.CDK5_PROVIDES__WSDL));
	}


	/**
	 * @return the WSDL URI if the WSDL value is a valid URI, null otherwise
	 */
	private URI getWsdlUri() {

		URI uri = null;
		try {
			if( this.wsdlText.getText().trim().length() > 0 )
				uri = UriUtils.urlToUri( this.wsdlText.getText());
		} catch( Exception e ) {
			// nothing
		}

		return uri;
	}


	/**
	 * Updates the GUI when the WSDL is parsed.
	 */
	@Override
	public void notifyWsdlParsingDone() {

		List<JbiBasicBean> wsdlBeans = parseWsdl();
		refreshWidgetsFromWsdl( wsdlBeans );
	}


	/**
	 * Forces the WSDL parsing.
	 */
	private List<JbiBasicBean> parseWsdl() {

		List<JbiBasicBean> wsdlBeans = new ArrayList<JbiBasicBean> ();
		if( this.wsdlParsingJob.cancel()) {

			// Get the WSDL URI
			File f = JbiXmlUtils.getWsdlFile( this.editedFile, this.wsdlText.getText());
			URI uri;
			if( f == null )
				uri = getWsdlUri();
			else
				uri = f.toURI();

			// Parse and update...
			if( uri != null ) {
				List<JbiBasicBean> _beans = null;
				try {
					_beans = WsdlParser.getInstance().parse( uri.toString());
				} catch( IllegalArgumentException e ) {
					// nothing
				}

				if( _beans != null )
					wsdlBeans.addAll( _beans );
			}

			// ... or report an error
			else {
				MessageDialog.openError(
							this.wsdlText.getShell(),
							"WSDL Parsing Failure",
				"The WSDL parsing failed: no service description was found in the referenced WSDL file." );
			}
		}

		return wsdlBeans;
	}


	/**
	 * Refreshes the UI with the values resulting from the WSDL parsing.
	 */
	private void refreshWidgetsFromWsdl( List<JbiBasicBean> wsdlBeans ) {

		this.hostDetailsPage.setReportChanges(false);
		if( wsdlBeans.size() > 0 ) {
			JbiBasicBean firstBean = wsdlBeans.get( 0 );
			ApplyBasicBeanToService(firstBean);
		}

		this.hostDetailsPage.setReportChanges(true);
	}


	private void ApplyBasicBeanToService(JbiBasicBean bean) {
		CompoundCommand compositeCommand = new CompoundCommand();
		EditingDomain editDomain = this.generalFormPage.getEditDomain();

		String srvName = bean.getServiceName();
		String srvNs = bean.getServiceNs();
		QName q = new QName(srvNs, srvName);
		Command currentCommand = new SetCommand(editDomain, this.selectedEndpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__SERVICE_NAME, q);
		compositeCommand.append(currentCommand);

		String itfName = bean.getInterfaceName();
		String itfNs = bean.getInterfaceNs();
		q = new QName(itfNs, itfName);
		currentCommand = new SetCommand(editDomain, this.selectedEndpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__INTERFACE_NAME, q);
		compositeCommand.append(currentCommand);

		editDomain.getCommandStack().execute(compositeCommand);

		// TODO use a semantic model command
		String edptName = bean.getEndpointName() != null ? bean.getEndpointName() : "";
		if( PetalsConstants.AUTO_GENERATE.equalsIgnoreCase( edptName )) {
			this.hostDetailsPage.setEndpointEnabled(false);
			CDK5ProvidesDetails.this.generateEdptButton.setSelection( true );
		}
		else {
			this.hostDetailsPage.setEndpointEnabled(true);
			CDK5ProvidesDetails.this.generateEdptButton.setSelection( false );
		}
	}

}
