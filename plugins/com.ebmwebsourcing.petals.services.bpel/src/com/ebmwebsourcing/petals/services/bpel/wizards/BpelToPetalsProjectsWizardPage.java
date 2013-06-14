/****************************************************************************
 *
 * Copyright (c) 2011-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.bpel.wizards;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.bpel.common.wsdl.helpers.UriAndUrlHelper;
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
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

import com.ebmwebsourcing.petals.common.generation.JbiUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.TextWithButtonComposite;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.NameUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StatusUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils.JbiBasicBean;
import com.ebmwebsourcing.petals.services.bpel.PetalsBpelHelper;
import com.ebmwebsourcing.petals.services.bpel.PetalsBpelPlugin;
import com.ebmwebsourcing.petals.services.wizards.AbstractPetalsServiceCreationWizardPage;
import com.ebmwebsourcing.petals.services.wizards.beans.SaImportBean;
import com.ebmwebsourcing.petals.services.wizards.beans.SuImportBean;

/**
 * A wizard page to create a set of Petals Maven projects from a BPEL process.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class BpelToPetalsProjectsWizardPage extends AbstractPetalsServiceCreationWizardPage {

	public static final String WSDL_LOCATION = "wsdl.location";
	public static final String JBI_BEAN = "jbi.bean";

	private static final String[] SOAP_VERSIONS = new String[] { "4.0" };
	private static final String[] BPEL_VERSIONS = new String[] { "1.0" };
	private PetalsBpelHelper helper;
	private URI bpelUri;



	/**
	 * Constructor.
	 */
	public BpelToPetalsProjectsWizardPage() {
		super( "BPEL Process Projection", "Create a Petals configuration set from a BPEL process." );
	}


	/**
	 * @return the helper
	 */
	public PetalsBpelHelper getHelper() {
		return this.helper;
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


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.services.wizards.AbstractPetalsServiceCreationWizardPage
	 * #validate()
	 */
	@Override
	protected boolean validate() {

		// Do not export BPELs that are not valid
		boolean valid = true;
		IFile bpelFile = null;
		File f = null;
		if( this.helper != null && this.helper.getBpelUri() != null )
			f = IoUtils.convertToFile( this.helper.getBpelUri());

		if( f != null )
			bpelFile = ResourceUtils.getIFile( f );

		if( bpelFile != null ) {
			if( ! bpelFile.exists()) {
				valid = false;
				setErrorMessage( "The selected BPEL file does not exist." );

			} else {
				try {
					if( IMarker.SEVERITY_ERROR == bpelFile.findMaxProblemSeverity( null, true, IResource.DEPTH_ZERO )) {
						valid = false;
						setErrorMessage( "The selected BPEL file contains errors and cannot be exported until this or these errors are solved." );

					} else
						valid = super.validate();

				} catch( CoreException e ) {
					// nothing
				}
			}

		} else {
			valid = super.validate();
		}

		setPageComplete( valid );
		return valid;
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.services.wizards.AbstractPetalsServiceCreationWizardPage
	 * #createWidgetsBeforeProjectLocation(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createWidgetsBeforeProjectLocation( Composite container ) {

		SwtFactory.createLabel( container, "BPEL process to export:", null );
		TextWithButtonComposite twb = SwtFactory.createFileBrowser( container, false, true, "BPEL" );
		twb.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		twb.getText().addModifyListener( new ModifyListener() {

			@Override
			public void modifyText( ModifyEvent e ) {

				BpelToPetalsProjectsWizardPage.this.bpelUri = UriAndUrlHelper.urlToUri(((Text) e.widget).getText().trim());
				BpelToPetalsProjectsWizardPage.this.helper = new PetalsBpelHelper( BpelToPetalsProjectsWizardPage.this.bpelUri );

				updateImportBeans( getProjectsToCreate(), true );
				validate();
			}
		});
	}


	/**
	 * Analyzes the BPEL processes and create a list of projects to create.
	 */
	private List<SaImportBean> getProjectsToCreate() {

		final List<SaImportBean> result = new ArrayList<SaImportBean> ();
		String s = UriAndUrlHelper.extractOrGenerateFileName( this.helper.getBpelUri().toString());
		final String bpelName = StringUtils.removeFileExtension( s );

		// Prepare the operation to run
		IRunnableWithProgress op = new IRunnableWithProgress() {

			@Override
			public void run( IProgressMonitor monitor )
			throws InvocationTargetException, InterruptedException {

				if( BpelToPetalsProjectsWizardPage.this.helper == null )
					return;

				monitor.beginTask( "Analyzing " + bpelName + "...", 3 );
				IWorkspaceRoot iwr = ResourcesPlugin.getWorkspace().getRoot();
				try {
					// Get the WSDLs of the process
					Set<String> partnerImportUris = BpelToPetalsProjectsWizardPage.this.helper.findPartnerImports( true );
					Set<String> processImportUris = BpelToPetalsProjectsWizardPage.this.helper.findProcessInterfaceImports( false );

					// Create the import beans
					List<SuImportBean> suImportBeans = new ArrayList<SuImportBean> ();
					for( String import_ : partnerImportUris )
						createSoapImportBean( import_, false, suImportBeans );

					for( String import_ : processImportUris )
						createSoapImportBean( import_, true, suImportBeans );

					monitor.worked( 1 );

					// Add the BPEL SU
					monitor.subTask( "Creating the BPEL service unit..." );
					SuImportBean suBean = new SuImportBean();
					suBean.setComponentName( "petals-se-bpel" );
					suBean.setComponentVersion( BPEL_VERSIONS[ 0 ]);
					suBean.setSupportedVersions( BPEL_VERSIONS );
					suBean.setJbiXmlLocation( null );
					suBean.setSuType( "BPEL" );

					String name = JbiUtils.createSuName( "BPEL", bpelName, false );
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
			PetalsBpelPlugin.log( e, IStatus.ERROR, "An error occurred while converting a BPEL process into Petals projects." );
			IStatus status = StatusUtils.createStatus( e, "" );
			ErrorDialog.openError(
						new Shell(),
						"Conversion Error",
						"The conversion process encountered errors. The process may be invalid.",
						status );

		} catch( InterruptedException e ) {
			// nothing
		}

		return result;
	}


	/**
	 * Creates the SOAP SU from the imports URIs.
	 * @param import_ the import to process
	 * @param consumes true if a consumer must be created, false for a provider
	 * @param suImportBeans the import beans to display in the wizard page
	 * @throws InvocationTargetException if the imported WSDL could not be created
	 */
	private void createSoapImportBean( String import_, boolean consumes, List<SuImportBean> suImportBeans )
	throws InvocationTargetException {

		List<JbiBasicBean> jbiBeans;
		try {
			jbiBeans = WsdlUtils.INSTANCE.parse( import_ );

		} catch( IllegalArgumentException e ) {
			PetalsBpelPlugin.log( e, IStatus.ERROR );
			return;
		}

		for( JbiBasicBean jbiBean : jbiBeans ) {

			SuImportBean suBean = new SuImportBean();
			suBean.setComponentName( "petals-bc-soap" );
			suBean.setComponentVersion( SOAP_VERSIONS[ 0 ]);
			suBean.setSupportedVersions( SOAP_VERSIONS );
			suBean.getKeyToObject().put( WSDL_LOCATION, import_ );
			suBean.setSuType( "SOAP" );

			String name;
			if( consumes ) {
				name = NameUtils.createSuName( "SOAP", jbiBean.getServiceName().getLocalPart(), true );
				suBean.setConsume( true );
			}
			else
				name = NameUtils.createSuName( "SOAP", jbiBean.getServiceName().getLocalPart(), false );

			suBean.setProjectName( name );
			IProject p = ResourcesPlugin.getWorkspace().getRoot().getProject( name );
			suBean.setToCreate( ! p.exists());

			suBean.getKeyToObject().put( JBI_BEAN, jbiBean );
			suImportBeans.add( suBean );
		}
	}
}
