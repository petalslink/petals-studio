/****************************************************************************
 *
 * Copyright (c) 2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.bpel.wizards;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.progress.IProgressService;

import com.ebmwebsourcing.petals.common.generation.JbiUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.NameUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StatusUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils.JbiBasicBean;
import com.ebmwebsourcing.petals.services.bpel.PetalsBpelPlugin;
import com.ebmwebsourcing.petals.services.bpel.designer.builder.PetalsBpelBuilder;
import com.ebmwebsourcing.petals.services.bpel.designer.provisional.PetalsBpelModules;
import com.ebmwebsourcing.petals.services.wizards.AbstractPetalsServiceCreationWizardPage;
import com.ebmwebsourcing.petals.services.wizards.beans.SaImportBean;
import com.ebmwebsourcing.petals.services.wizards.beans.SuImportBean;

/**
 * A wizard page to export a BPEL croquis in a set of Petals Maven projects.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class BpelCroquisExportWizardPage extends AbstractPetalsServiceCreationWizardPage {

	public static final String WSDL_LOCATION = "wsdl.location";
	public static final String JBI_BEAN = "jbi.bean";

	private static final String[] SOAP_VERSIONS = new String[] { "4.0" };
	private static final String[] BPEL_VERSIONS = new String[] { "1.0" };
	private IFile selectedBpel;
	private Collection<String> remoteImports;


	/**
	 * Constructor.
	 */
	public BpelCroquisExportWizardPage() {
		super( "BPEL Process Projection", "Create a Petals configuration set from a BPEL process." );
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.services.wizards.AbstractPetalsServiceCreationWizardPage
	 * #releaseResources()
	 */
	@Override
	protected void releaseResources() {
		// nothing
	}


	/**
	 * @return the selectedBpel
	 */
	public IFile getSelectedBpel() {
		return this.selectedBpel;
	}


	/**
	 * @return the remoteImports
	 */
	public Collection<String> getRemoteImports() {
		return this.remoteImports;
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.services.wizards.AbstractPetalsServiceCreationWizardPage
	 * #validate()
	 */
	@Override
	protected boolean validate() {

		// Do not export BPELs that are not valid
		boolean valid = true;
		if( this.selectedBpel != null ) {

			int maxSeverity;
			try {
				maxSeverity = this.selectedBpel.findMaxProblemSeverity(
							PetalsBpelBuilder.MARKER_TYPE, true, IResource.DEPTH_ZERO );
			} catch( CoreException e ) {
				maxSeverity = IMarker.SEVERITY_INFO;
			}

			if( maxSeverity == IMarker.SEVERITY_ERROR ) {
				setErrorMessage( "The selected BPEL file contains errors " +
				"and cannot be exported until this or these errors are solved." );

				setPageComplete( false );
				valid = false;
			}
			else
				valid = super.validate();
		}

		return valid;
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.services.wizards.AbstractPetalsServiceCreationWizardPage
	 * #createWidgetsBeforeProjectLocation(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createWidgetsBeforeProjectLocation( Composite container ) {

		Label l = new Label( container, SWT.NONE );
		l.setText( "BPEL process to export:" );

		Composite subContainer = new Composite( container, SWT.NONE );
		GridLayout layout = new GridLayout( 2, false );
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		subContainer.setLayout( layout );
		subContainer.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final Text text = new Text( subContainer, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY );
		text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final Button button = new Button( subContainer, SWT.PUSH );
		button.setText( "Browse..." );
		button.addSelectionListener( new SelectionListener() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {

				ElementTreeSelectionDialog dlg = SwtFactory.createWorkspaceFileSelectionDialog(
						getShell(), ResourcesPlugin.getWorkspace().getRoot(),
						"BPEL Selection", "Select a BPEL file located in the workspace.", "bpel" );

				if( BpelCroquisExportWizardPage.this.selectedBpel != null )
					dlg.setInitialElementSelections( Arrays.asList( BpelCroquisExportWizardPage.this.selectedBpel ));

				if( dlg.open() == Window.OK ) {
					BpelCroquisExportWizardPage.this.selectedBpel = (IFile) dlg.getResult()[ 0 ];

					StringBuilder sb = new StringBuilder();
					sb.append( BpelCroquisExportWizardPage.this.selectedBpel.getName() + "  -  " );
					sb.append( BpelCroquisExportWizardPage.this.selectedBpel.getFullPath());
					text.setText( sb.toString());

					updateImportBeans( getProjectsToCreate(), true );
					validate();
				}
			}
		});
	}


	/**
	 * Analyzes the BPEL processes and create a list of projects to create.
	 */
	private List<SaImportBean> getProjectsToCreate() {

		final List<SaImportBean> result = new ArrayList<SaImportBean> ();

		// Prepare the operation to run
		IRunnableWithProgress op = new IRunnableWithProgress() {

			@Override
			public void run( IProgressMonitor monitor )
			throws InvocationTargetException, InterruptedException {

				if( BpelCroquisExportWizardPage.this.selectedBpel == null )
					return;

				monitor.beginTask( "Analyzing " + BpelCroquisExportWizardPage.this.selectedBpel.getName() + "...", 3 );
				IWorkspaceRoot iwr = ResourcesPlugin.getWorkspace().getRoot();
				try {

					List<SuImportBean> suImportBeans = new ArrayList<SuImportBean> ();
					try {
						// Load the BPEL and get the imports
						URL url = BpelCroquisExportWizardPage.this.selectedBpel.getLocation().toFile().toURI().toURL();
						BpelCroquisExportWizardPage.this.remoteImports = PetalsBpelModules.getWsdlImportUris( url, false, true ).keySet();
						Collection<URI> localImports = PetalsBpelModules.getWsdlImportUris( url, true, false ).values();

						// TODO: do not get all the imported WSDLs, but only the partner links WSDLs?
						// Get all the imports
						List<String> imports = new ArrayList<String> ();
						imports.addAll( BpelCroquisExportWizardPage.this.remoteImports );
						for( URI uri : localImports )
							imports.add( uri.toString());

						// Get the WSDLs describing the WSDL
						List<URI> tempBpelDefinitionUris = PetalsBpelModules.getProcessWsdl( url );
						List<String> bpelDefinitionUris = new ArrayList<String>( tempBpelDefinitionUris.size());
						for( URI uri : tempBpelDefinitionUris )
							bpelDefinitionUris.add( uri.toString());

						// One SOAP project per remote import
						for( String import_ : imports ) {

							List<JbiBasicBean> jbiBeans;
							try {
								jbiBeans = WsdlUtils.INSTANCE.parse( import_ );

							} catch( IllegalArgumentException e ) {
								PetalsBpelPlugin.log( e, IStatus.ERROR );
								continue;
							}

							for( JbiBasicBean jbiBean : jbiBeans ) {

								SuImportBean suBean = new SuImportBean();
								suBean.setComponentName( "petals-bc-soap" );
								suBean.setComponentVersion( SOAP_VERSIONS[ 0 ]);
								suBean.setSupportedVersions( SOAP_VERSIONS );
								suBean.getKeyToObject().put( WSDL_LOCATION, import_ );
								suBean.setSuType( "SOAP" );

								String name;
								if( bpelDefinitionUris.contains( import_ )) {
									name = NameUtils.createSuName( "SOAP", jbiBean.getServiceName().getLocalPart(), true );
									suBean.setConsume( true );
								}
								else
									name = NameUtils.createSuName( "SOAP", jbiBean.getServiceName().getLocalPart(), false );

								suBean.setProjectName( name );
								IProject p = iwr.getProject( name );
								suBean.setToCreate( ! p.exists());

								suBean.getKeyToObject().put( JBI_BEAN, jbiBean );
								suImportBeans.add( suBean );
							}

							jbiBeans = null;
						}

						monitor.worked( 1 );

					} catch( MalformedURLException e ) {
						PetalsBpelPlugin.log( e, IStatus.ERROR );
					}

					// Add the BPEL SU
					monitor.subTask( "Creating the BPEL service unit..." );
					SuImportBean suBean = new SuImportBean();
					suBean.setComponentName( "petals-se-bpel" );
					suBean.setComponentVersion( BPEL_VERSIONS[ 0 ]);
					suBean.setSupportedVersions( BPEL_VERSIONS );
					suBean.setJbiXmlLocation( null );
					suBean.setSuType( "BPEL" );

					String name = JbiUtils.createSuName( "BPEL", BpelCroquisExportWizardPage.this.selectedBpel.getName(), false );
					if( ! name.startsWith( "su-" ))
						name = JbiUtils.createSuName( "BPEL", name, false );
					suBean.setProjectName( name );

					IProject p = iwr.getProject( name );
					suBean.setToCreate( ! p.exists());

					suImportBeans.add( suBean );
					monitor.worked( 1 );

					// Add the BPEL SA
					monitor.subTask( "Creating the BPEL service assembly..." );
					SaImportBean saBean = new SaImportBean();
					saBean.setJbiXmlLocation( null );

					name = JbiUtils.createSaName( name );
					saBean.setProjectName( name );

					p = iwr.getProject( name );
					saBean.setToCreate( ! p.exists());

					saBean.addAllSuBeans( suImportBeans );
					result.add( saBean );
					monitor.worked( 1 );

				} finally {
					monitor.done();
				}
			}
		};

		// Start processing
		IWorkbench wb = PlatformUI.getWorkbench();
		IProgressService ps = wb.getProgressService();
		try {
			ps.busyCursorWhile( op );

		} catch( InvocationTargetException e ) {
			PetalsBpelPlugin.log( e, IStatus.ERROR, "An error occurred while concretizing a BPEL croquis." );
			IStatus status = StatusUtils.createStatus( e, "" );
			ErrorDialog.openError(
						new Shell(),
						"Conversion Error",
						"The conversion process encountered errors.",
						status );

		} catch( InterruptedException e ) {
			// nothing
		}

		return result;
	}
}
