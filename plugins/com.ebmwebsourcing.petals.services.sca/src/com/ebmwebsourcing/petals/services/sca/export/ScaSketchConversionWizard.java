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
package com.ebmwebsourcing.petals.services.sca.export;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import com.ebmwebsourcing.petals.common.internal.provisional.maven.MavenBean;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StatusUtils;
import com.ebmwebsourcing.petals.services.sca.PetalsScaPlugin;
import com.ebmwebsourcing.petals.services.wizards.AbstractPetalsServiceCreationWizardPage;
import com.ebmwebsourcing.petals.services.wizards.beans.SaImportBean;
import com.ebmwebsourcing.petals.services.wizards.beans.SuImportBean;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ScaSketchConversionWizard extends Wizard {

	private AbstractPetalsServiceCreationWizardPage page;
	private final IProject scaProject;
	private IFile compositeFile;

	private final List<IResource> resourcesToSelect = new ArrayList<IResource> ();
	private final List<SaImportBean> importBeans = new ArrayList<SaImportBean> ();
	private final List<IStatus> errors = new ArrayList<IStatus> ();


	/**
	 * Constructor.
	 * @param scaProject the selected SCA project
	 */
	public ScaSketchConversionWizard( IProject scaProject ) {
		super();
		this.scaProject = scaProject;
		setNeedsProgressMonitor( true );
		setWindowTitle( "SCA Sketch Conversion" );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #addPages()
	 */
	@Override
	public void addPages() {
		this.page = new ScaSketchExportWizardPage( this.compositeFile );
		this.page.updateImportBeans( this.importBeans, true );
		addPage( this.page );
	}





	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #performFinish()
	 */
	@Override
	public boolean performFinish() {

		// Define the wizard completion process.
		WorkspaceModifyOperation op = new WorkspaceModifyOperation() {

			@Override
			protected void execute( IProgressMonitor monitor )
			throws CoreException, InvocationTargetException, InterruptedException {

				try {
					doFinish( monitor );

				} catch( Exception e ) {
					throw new InvocationTargetException( e );
				} finally {
					monitor.done();
				}
			}
		};

		try {
			// Run the operation.
			getContainer().run( true, false, op );

			// Reveal the file in the explorer
			IResource[] res = new IResource[ this.resourcesToSelect.size()];
			ResourceUtils.selectResourceInPetalsExplorer(
						true, this.resourcesToSelect.toArray( res ));

		} catch( Exception e ) {
			PetalsScaPlugin.log( e, IStatus.ERROR );
			return false;

		} finally {

			// Clean resources
			this.importBeans.clear();
			this.resourcesToSelect.clear();
			this.compositeFile = null;
		}

		// Display errors
		if( ! this.errors.isEmpty()) {
			IStatus[] children = new IStatus[ this.errors.size()];
			MultiStatus status = new MultiStatus(
						PetalsScaPlugin.PLUGIN_ID, 0,
						this.errors.toArray( children ),
						"The croquis conversion encountered errors.", null );
			ErrorDialog.openError( new Shell(), "Conversion Error", "", status );
		}

		return true;
	}


	/**
	 * Create the selected projects.
	 * @param monitor
	 * @throws CoreException
	 */
	protected void doFinish( IProgressMonitor monitor ) throws CoreException {

		monitor.beginTask( "Creating the concrete projects...", IProgressMonitor.UNKNOWN );
		List<SaImportBean> importBeans = this.page.getImportsBeans();

		// Create the SU
		List<MavenBean> suMavenBeans = new ArrayList<MavenBean>();
		Map<String,String> suNameToComponent = new HashMap<String,String> ();
		for( SaImportBean saBean : importBeans ) {
			for( SuImportBean suBean : saBean.getSuBeans()) {

				if( ! suBean.isToCreate())
					continue;

				URI pLocUri = null;
				if( ! this.page.isAtDefaultLocation())
					pLocUri = new File( this.page.getProjectLocation(), suBean.getProjectName()).toURI();

				try {
					// Delete the project?
					if( suBean.isToOverwrite()) {
						IProject p = ResourcesPlugin.getWorkspace().getRoot().getProject( suBean.getProjectName());
						if( p.exists())
							p.delete( true, monitor );
					}

					//					// Create the project
					//					if( "petals-bc-soap".equalsIgnoreCase( suBean.getComponentName())) {
					//						createSuSoapProject( suBean, pLocUri, suBean.isConsume(), monitor );
					//						suNameToComponent.put( suBean.getProjectName(), "petals-bc-soap" );
					//					}
					//					else {
					//						createSuBpelProject( suBean, pLocUri, monitor );
					//						suNameToComponent.put( suBean.getProjectName(), "petals-se-bpel" );
					//					}
					//
					//					// Create the POM artifacts for the SA
					//					// Elements match what is set in PetalsServicesProjectUtils#createPetalsSuProjectStructure().
					//					MavenBean suMavenBean = new MavenBean();
					//					suMavenBean.setArtifactId( suBean.getProjectName());
					//					suMavenBeans.add( suMavenBean );

				}  catch( Exception e ) {
					PetalsScaPlugin.log( e, IStatus.ERROR );
					IStatus status = StatusUtils.createStatus( e, "" );
					// BpelSketchExportHandler.this.errors.add( status );
				}
			}

			// Create the SA?
			if( saBean.isToCreate()) {
				URI pLocUri = null;
				if( ! this.page.isAtDefaultLocation())
					pLocUri = new File( this.page.getProjectLocation(), saBean.getProjectName()).toURI();

				try {
					// Delete the project?
					if( saBean.isToOverwrite()) {
						IProject p = ResourcesPlugin.getWorkspace().getRoot().getProject( saBean.getProjectName());
						if( p.exists())
							p.delete( true, monitor );
					}

					// Create the project
					// createSaProject( saBean, pLocUri, monitor, suMavenBeans, suNameToComponent );

				} catch( Exception e ) {
					PetalsScaPlugin.log( e, IStatus.ERROR );
					IStatus status = StatusUtils.createStatus( e, "" );
					// BpelSketchExportHandler.this.errors.add( status );
				}
			}
		}
	}
}
