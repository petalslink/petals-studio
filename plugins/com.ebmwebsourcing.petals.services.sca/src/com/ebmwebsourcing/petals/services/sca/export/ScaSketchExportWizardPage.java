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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.soa.sca.sca1_0.common.utils.ScaModelUtils.InvalidScaModelException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.progress.IProgressService;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StatusUtils;
import com.ebmwebsourcing.petals.services.sca.PetalsScaPlugin;
import com.ebmwebsourcing.petals.services.sca.transformation.CompositeAnalyzer;
import com.ebmwebsourcing.petals.services.wizards.AbstractPetalsServiceCreationWizardPage;
import com.ebmwebsourcing.petals.services.wizards.beans.SaImportBean;

/**
 *
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ScaSketchExportWizardPage extends AbstractPetalsServiceCreationWizardPage {

	private IFile compositeFile;


	/**
	 * Constructor.
	 * @param compositeFile
	 */
	public ScaSketchExportWizardPage( IFile compositeFile ) {
		super( "SCA Croquis Conversion", "Convert a SCA croquis into a set of concrete Petals projects." );
		updateCompositeFile( compositeFile );
	}


	/**
	 * @param compositeFile
	 */
	public void updateCompositeFile( IFile compositeFile ) {
		this.compositeFile = compositeFile;
		if( preValidate()) {
			findProjectsToCreate();
			validate();
		}
	}


	/**
	 * Analyzes the composite and create a list of projects to create.
	 */
	private void findProjectsToCreate() {

		// Prepare the operation to run
		IRunnableWithProgress op = new IRunnableWithProgress() {

			public void run( IProgressMonitor monitor )
			throws InvocationTargetException, InterruptedException {

				monitor.beginTask(
							"Analyzing " + ScaSketchExportWizardPage.this.compositeFile.getName() + "...",
							IProgressMonitor.UNKNOWN );

				try {
					List<SaImportBean> importBeans = new ArrayList<SaImportBean> ();
					SaImportBean saBean = new CompositeAnalyzer().analyze( ScaSketchExportWizardPage.this.compositeFile, monitor );
					importBeans.add( saBean );
					updateImportBeans( importBeans, true );
					monitor.worked( 1 );

				}
				catch( InvalidScaModelException e ) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch( IOException e ) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch( ScaSketchExportException e ) {
					// TODO Auto-generated catch block
					e.printStackTrace();

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
			PetalsScaPlugin.log( e, IStatus.ERROR, "An error occurred while concretizing a SCA croquis." );
			IStatus status = StatusUtils.createStatus( e, "" );
			ErrorDialog.openError(
						new Shell(),
						"Conversion Error",
						"The conversion process encountered errors.",
						status );

		} catch( InterruptedException e ) {
			// nothing
		}
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.wizards.AbstractPetalsServiceCreationWizardPage
	 * #releaseResources()
	 */
	@Override
	protected void releaseResources() {
		// nothing
	}


	/**
	 * Applies a first validation before introspecting the composite.
	 * <p>
	 * The idea is to not export composites that are not valid.
	 * </p>
	 *
	 * @return
	 */
	private boolean preValidate() {

		int maxSeverity;
		try {
			maxSeverity = this.compositeFile.findMaxProblemSeverity( null, true, IResource.DEPTH_ZERO );

		} catch( CoreException e ) {
			maxSeverity = IMarker.SEVERITY_INFO;
		}

		boolean valid = true;
		if( maxSeverity == IMarker.SEVERITY_ERROR ) {
			setErrorMessage( "The selected composite file contains errors " +
			"and cannot be exported until this or these errors are solved." );

			setPageComplete( false );
			valid = false;
		}

		return valid;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.wizards.AbstractPetalsServiceCreationWizardPage
	 * #createWidgetsBeforeProjectLocation(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createWidgetsBeforeProjectLocation( Composite container ) {

		Label l = new Label( container, SWT.NONE );
		l.setText( "Exported SCA composite:" );

		ComboViewer scaViewer = new ComboViewer( container, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY );
		scaViewer.getCombo().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		scaViewer.setContentProvider( new ArrayContentProvider());
		scaViewer.setLabelProvider( new WorkbenchLabelProvider());

		if( this.compositeFile != null ) {
			IFolder resFolder = this.compositeFile.getProject().getFolder( PetalsConstants.LOC_RES_FOLDER );
			List<IFile> compositeFiles = ResourceUtils.getFiles( "composite", Arrays.asList( resFolder ));
			scaViewer.setInput( compositeFiles );
			scaViewer.setSelection( new StructuredSelection( this.compositeFile ));
		}

		scaViewer.addSelectionChangedListener( new ISelectionChangedListener() {
			public void selectionChanged( SelectionChangedEvent event ) {

				if( ! event.getSelection().isEmpty()
							&& event.getSelection() instanceof IStructuredSelection ) {
					IFile compFile = (IFile) ((IStructuredSelection) event.getSelection()).getFirstElement();
					updateCompositeFile( compFile );
				}
			}
		});
	}
}
