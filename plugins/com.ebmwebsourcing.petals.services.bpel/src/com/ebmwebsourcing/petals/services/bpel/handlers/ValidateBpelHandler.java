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

package com.ebmwebsourcing.petals.services.bpel.handlers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.progress.IProgressService;

import com.ebmwebsourcing.easybpel.model.bpel.tools.validator.BPELValidator;
import com.ebmwebsourcing.easybpel.model.bpel.tools.validator.ValidatorResult;
import com.ebmwebsourcing.easybpel.model.bpel.tools.validator.XPathError;
import com.ebmwebsourcing.easybpel.model.bpel.tools.validator.XPathInfo;
import com.ebmwebsourcing.easybpel.model.bpel.tools.validator.XPathWarning;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.MarkerUtils;
import com.ebmwebsourcing.petals.services.bpel.PetalsBpelPlugin;
import com.ebmwebsourcing.petals.services.bpel.designer.builder.PetalsBpelBuilder;
import com.ebmwebsourcing.petals.services.bpel.designer.provisional.PetalsBpelModules;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ValidateBpelHandler extends AbstractHandler {

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler
	 * #execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute( ExecutionEvent event ) throws ExecutionException {

		final ISelection selection = HandlerUtil.getActiveWorkbenchWindow( event ).getActivePage().getSelection();
		if( selection == null
					|| selection.isEmpty()
					|| !( selection instanceof IStructuredSelection ))
			return null;

		IRunnableWithProgress validationOperation = new IRunnableWithProgress() {
			/*
			 * (non-Javadoc)
			 * @see org.eclipse.jface.operation.IRunnableWithProgress
			 * #run(org.eclipse.core.runtime.IProgressMonitor)
			 */
			public void run( IProgressMonitor monitor ) throws InvocationTargetException, InterruptedException {
				try {
					List<IFile> bpelFiles = new ArrayList<IFile> ();
					for( Iterator<?> it = ((IStructuredSelection) selection).iterator(); it.hasNext(); ) {
						Object o = it.next();
						if( o instanceof IFile )
							bpelFiles.add((IFile) o);
					}

					monitor.beginTask( "Validating BPEL process(es) with Petals BPEL Validator...", IProgressMonitor.UNKNOWN );
					BPELValidator validator = PetalsBpelModules.getBpelEngineValidator();
					if( validator == null )
						throw new NullPointerException( "The BPEL validator could not be instantiated." );

					for( int i=0; i<bpelFiles.size(); i++ ) {
						IFile bpelFile = bpelFiles.get( i );
						monitor.subTask( "Validating " + bpelFile.getName() + "... (File " + (i+1) + "/" + bpelFiles.size() + ")" );
						ValidatorResult result = validator.validate( bpelFile.getLocation().toFile().toURI().toURL());
						bpelFile.deleteMarkers( PetalsBpelBuilder.MARKER_TYPE, true, IResource.DEPTH_ZERO );

						// Handle errors
						for( XPathError error : result.errors ) {
							try {
								IMarker marker = bpelFile.createMarker( PetalsBpelBuilder.MARKER_TYPE );
								marker.setAttribute( IMarker.SEVERITY, IMarker.SEVERITY_ERROR );
								marker.setAttribute( IMarker.MESSAGE, error.getError().getMessage());

								String loc = error.getXpathExpression().getXpathExpression();
								marker.setAttribute( IMarker.LOCATION, loc );

								String location = PetalsBpelBuilder.updateXPathLocation( loc );
								marker.setAttribute( PetalsBpelBuilder.MARKER_XPATH_LINE, location );

							} catch( CoreException e ) {
								e.printStackTrace();
							}
						}

						// Handle warnings
						for( XPathWarning warning : result.warnings ) {
							try {
								IMarker marker = bpelFile.createMarker( PetalsBpelBuilder.MARKER_TYPE );
								marker.setAttribute( IMarker.SEVERITY, IMarker.SEVERITY_WARNING );
								marker.setAttribute( IMarker.MESSAGE, warning.getWarning());

								String loc = warning.getXpathExpression().getXpathExpression();
								marker.setAttribute( IMarker.LOCATION, loc );

								String location = PetalsBpelBuilder.updateXPathLocation( loc );
								marker.setAttribute( PetalsBpelBuilder.MARKER_XPATH_LINE, location );

							} catch( CoreException e ) {
								e.printStackTrace();
							}
						}

						// Handle info
						for( XPathInfo info : result.infos ) {
							try {
								IMarker marker = bpelFile.createMarker( PetalsBpelBuilder.MARKER_TYPE );
								marker.setAttribute( IMarker.SEVERITY, IMarker.SEVERITY_INFO );
								marker.setAttribute( IMarker.MESSAGE, info.getInfo());

								String loc = info.getXpathExpression().getXpathExpression();
								marker.setAttribute( IMarker.LOCATION, loc );

								String location = PetalsBpelBuilder.updateXPathLocation( loc );
								marker.setAttribute( PetalsBpelBuilder.MARKER_XPATH_LINE, location );

							} catch( CoreException e ) {
								e.printStackTrace();
							}
						}

						// Resolve line numbers
						MarkerUtils.resolveLineNumbers(
									bpelFile,
									PetalsBpelBuilder.MARKER_TYPE,
									PetalsBpelBuilder.MARKER_XPATH_LINE );
					}

				} catch( Exception e ) {
					throw new InvocationTargetException( e );

				} finally {
					monitor.done();
				}
			}
		};

		// Run it
		try {
			IProgressService ps = PlatformUI.getWorkbench().getProgressService();
			ps.busyCursorWhile( validationOperation );

		} catch( InterruptedException e ) {
			// nothing

		} catch( InvocationTargetException e ) {
			PetalsBpelPlugin.log( e, IStatus.ERROR, "A problem occurred while validating a BPEL process." );
			MessageDialog.openError( new Shell(),
						"Validation Error",
			"An error occured while validating BPEL process(es). Check the log for more details." );
		}

		return null;
	}
}
