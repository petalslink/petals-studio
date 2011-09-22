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

package com.ebmwebsourcing.petals.services.bpel.designer.builder;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.ebmwebsourcing.easybpel.model.bpel.tools.validator.BPELValidator;
import com.ebmwebsourcing.easybpel.model.bpel.tools.validator.ValidatorResult;
import com.ebmwebsourcing.easybpel.model.bpel.tools.validator.XPathError;
import com.ebmwebsourcing.easybpel.model.bpel.tools.validator.XPathInfo;
import com.ebmwebsourcing.easybpel.model.bpel.tools.validator.XPathWarning;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.MarkerUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.services.bpel.PetalsBpelPlugin;
import com.ebmwebsourcing.petals.services.bpel.designer.provisional.PetalsBpelModules;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsBpelBuilder extends IncrementalProjectBuilder {

	public static final String BUILDER_ID = "com.ebmwebsourcing.petals.extension.bpeldesigner.petalsBpelBuilder";
	public static final String MARKER_TYPE = "com.ebmwebsourcing.petals.extension.bpeldesigner.petalsBpelProblem";
	public static final String MARKER_XPATH_LINE = "com.ebmwebsourcing.petals.extension.bpeldesigner.petalsBpelXpathLine";
	private final List<IFile> bpelFiles = new ArrayList<IFile>( 3 ); // Set an initial capacity


	/**
	 * True to indicate a full build must be performed, false otherwise.
	 * <p>
	 * Used in incremental builds.
	 * </p>
	 */
	private boolean fullBuild = false;


	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.IncrementalProjectBuilder
	 * #build(int, java.util.Map, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@SuppressWarnings( "rawtypes" )
	@Override
	protected IProject[] build( int kind, Map args, IProgressMonitor monitor )
	throws CoreException {

		if( kind == FULL_BUILD ) {
			fullBuild( monitor );
		} else {
			IResourceDelta delta = getDelta( getProject());
			if( delta == null )
				fullBuild( monitor );
			else
				incrementalBuild( delta, monitor );
		}
		return null;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.resources.IncrementalProjectBuilder
	 * #clean(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected void clean( IProgressMonitor monitor ) throws CoreException {

		this.bpelFiles.clear();

		// Remove markers from the file.
		if( getProject() != null ) {
			getProject().deleteMarkers( MARKER_TYPE, true, IResource.DEPTH_INFINITE );

			// Remove markers from the milestones
			// TODO: delete this after the release
			getProject().deleteMarkers( PetalsBpelPlugin.PLUGIN_ID
						+ ".petalsMaestroProblem", true, IResource.DEPTH_INFINITE );
		}

		super.clean( monitor );
	}


	/**
	 * Checks if the user selected the "cancel" button in the progress monitor.
	 * @param monitor
	 */
	protected void checkCancel( IProgressMonitor monitor ) {
		if( monitor.isCanceled()) {
			forgetLastBuiltState();	// do a full build next time
			throw new OperationCanceledException();
		}
	}


	/**
	 * Runs a full build.
	 * @param monitor
	 * @throws CoreException
	 */
	protected void fullBuild( final IProgressMonitor monitor ) throws CoreException {

		this.fullBuild = false;
		try {
			// Get BPEL files
			getProject().accept( new IResourceVisitor() {
				public boolean visit( IResource resource ) throws CoreException {

					checkCancel( monitor );

					if( resource instanceof IFile ) {
						if( resource.getFileExtension().toLowerCase().equals( "bpel" ))
							PetalsBpelBuilder.this.bpelFiles.add((IFile) resource);
						return false;
					}

					return true;
				}
			});

			// Validate files and add markers
			validateBpelFiles();

		} catch( CoreException e ) {
			PetalsBpelPlugin.log( e, IStatus.WARNING );
		}
	}

	/**
	 * Runs an incremental build.
	 * @param delta
	 * @param monitor
	 * @throws CoreException
	 */
	protected void incrementalBuild( IResourceDelta delta, final IProgressMonitor monitor )
	throws CoreException {


		this.bpelFiles.clear();
		delta.accept( new IResourceDeltaVisitor() {
			public boolean visit( IResourceDelta delta ) throws CoreException {

				checkCancel( monitor );

				// Full build required? Do not go further...
				if( PetalsBpelBuilder.this.fullBuild )
					return false;

				// Check the modified resource
				IResource resource = delta.getResource();
				if( resource instanceof IFile ) {
					if( ! resource.exists())
						return false;

					if( resource.getFileExtension().toLowerCase().equals( "bpel" )) {
						PetalsBpelBuilder.this.bpelFiles.remove( resource );
						if( delta.getFlags() != IResourceDelta.REMOVED
									&& delta.getFlags() != IResourceDelta.REMOVED_PHANTOM )
							PetalsBpelBuilder.this.bpelFiles.add((IFile) resource);
					}
					else if( resource.getFileExtension().toLowerCase().equals( "wsdl" )) {
						PetalsBpelBuilder.this.fullBuild = true;
						return false;
					}

					return false;
				}

				return true;
			}
		});

		// Full build required?
		if( this.fullBuild )
			fullBuild( monitor );

		// Validate files and add markers
		else
			validateBpelFiles();
	}


	/**
	 * Validates the BPEL files with Maestro.
	 */
	private void validateBpelFiles() {

		// If the validator could not be created, an error
		// should have already been logged
		BPELValidator validator = PetalsBpelModules.getBpelEngineValidator();
		if( validator == null )
			return;

		// Validate - and delete markers only if the validation ended correctly
		boolean errorOccurredDuringValidation = false;
		for( IFile file : this.bpelFiles ) {
			try {
				URL bpelUrl = file.getLocation().toFile().toURI().toURL();
				File resFolder = file.getProject().getFolder( PetalsConstants.LOC_RES_FOLDER ).getLocation().toFile();

				// Validate
				ValidatorResult result;
				try {
					result = validator.validate( bpelUrl );
					file.deleteMarkers( MARKER_TYPE, true, IResource.DEPTH_INFINITE );

				} catch( Exception unexpectedException ) {
					errorOccurredDuringValidation = true;
					PetalsBpelPlugin.log( unexpectedException, IStatus.ERROR, "A problem occurred while validating a BPEL process." );
					continue;
				}

				// Handle errors
				for( XPathError error : result.errors ) {
					try {
						IMarker marker = file.createMarker( MARKER_TYPE );
						marker.setAttribute( IMarker.SEVERITY, IMarker.SEVERITY_ERROR );
						marker.setAttribute( IMarker.MESSAGE, error.getError().getMessage());

						String loc = error.getXpathExpression().getXpathExpression();
						marker.setAttribute( IMarker.LOCATION, loc );

						String location = updateXPathLocation( loc );
						marker.setAttribute( MARKER_XPATH_LINE, location );

					} catch( CoreException e ) {
						PetalsBpelPlugin.log( e, IStatus.WARNING );
					}
				}

				// Handle warnings
				for( XPathWarning warning : result.warnings ) {
					try {
						IMarker marker = file.createMarker( MARKER_TYPE );
						marker.setAttribute( IMarker.SEVERITY, IMarker.SEVERITY_WARNING );
						marker.setAttribute( IMarker.MESSAGE, warning.getWarning());

						String loc = warning.getXpathExpression().getXpathExpression();
						marker.setAttribute( IMarker.LOCATION, loc );

						String location = updateXPathLocation( loc );
						marker.setAttribute( MARKER_XPATH_LINE, location );

					} catch( CoreException e ) {
						PetalsBpelPlugin.log( e, IStatus.WARNING );
					}
				}

				// Handle info
				for( XPathInfo info : result.infos ) {
					try {
						IMarker marker = file.createMarker( MARKER_TYPE );
						marker.setAttribute( IMarker.SEVERITY, IMarker.SEVERITY_INFO );
						marker.setAttribute( IMarker.MESSAGE, info.getInfo());

						String loc = info.getXpathExpression().getXpathExpression();
						marker.setAttribute( IMarker.LOCATION, loc );

						String location = updateXPathLocation( loc );
						marker.setAttribute( MARKER_XPATH_LINE, location );

					} catch( CoreException e ) {
						PetalsBpelPlugin.log( e, IStatus.WARNING );
					}
				}

				// Check relative imports
				// They should be located under the "jbi" directory
				if( resFolder.exists()) {

					for( Map.Entry<String,URI> entry : PetalsBpelModules.getWsdlImportUris( bpelUrl, true, true ).entrySet()) {
						File resolvedImportFile = new File( entry.getValue());
						if( ! resolvedImportFile.exists())
							continue;

						if( ! resolvedImportFile.getAbsolutePath().startsWith( resFolder.getAbsolutePath())) {
							try {
								IMarker marker = file.createMarker( MARKER_TYPE );
								marker.setAttribute( IMarker.SEVERITY, IMarker.SEVERITY_WARNING );
								String msg = "A WSDL definition is imported but might not be exported correctly."
									+ " Resources to package should be located under the src/main/jbi directory.";
								marker.setAttribute( IMarker.MESSAGE, msg );

								String loc = "//*[local-name()='import'][@location='" + entry.getKey() + "']";
								marker.setAttribute( IMarker.LOCATION, loc );
								marker.setAttribute( MARKER_XPATH_LINE, loc );

							} catch( CoreException e ) {
								PetalsBpelPlugin.log( e, IStatus.WARNING );
							}
						}
					}
				}

				// Resolve line numbers
				MarkerUtils.resolveLineNumbers( file, MARKER_TYPE, MARKER_XPATH_LINE );

			} catch( Exception e ) {
				PetalsBpelPlugin.log( e, IStatus.ERROR, "An error occurred while validating a BPEL process." );
			}
		}

		// Display an error message in case where something went wrong
		if( errorOccurredDuringValidation ) {
			Display.getDefault().asyncExec( new Runnable() {
				@Override
				public void run() {
					MessageDialog.openError(
								new Shell(),
								"Validation exception",
					"One or several errors occurred while validating BPEL files. Check the log for more details." );
				}
			});
		}
	}


	/**
	 * Updates the XPath location so that the builder can resolve the XML element.
	 * @param xpathLocation an XPath sent by the Petals BPEL validator
	 * @return the updated XPath location
	 */
	public static String updateXPathLocation( String xpathLocation ) {

		StringBuilder sb = new StringBuilder();
		String[] parts = xpathLocation.split( "/" );
		for( String part : parts ) {

			if( part.trim().length() == 0 )
				continue;

			int index = part.indexOf( '[' );
			String name, cond;
			if( index > 0 ) {
				name = part.substring( 0, index );
				cond = part.substring( index );
			}
			else {
				name = part;
				cond = "";
			}

			boolean attr = cond.contains( "[@" );
			if( attr )
				cond = cond.replaceFirst( "\\[@", "@" ).replaceFirst( "\\]$", "" );

			index = name.indexOf( ':' );
			if( index >= 0 )
				name = name.substring( index + 1 );

			// Bug fix
			if( "partnerLink".equals( name )) {
				cond = cond.replaceFirst( "@(.*)\\]", "" ) + "]";
				attr = false;
			}
			//

			sb.append( "/*[local-name()='" + name + "'" );
			if( attr )
				sb.append( " and " + cond );

			sb.append( "]" );
			if( ! attr )
				sb.append( cond );
		}

		return sb.toString();
	}
}
