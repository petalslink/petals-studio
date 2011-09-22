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

package com.ebmwebsourcing.petals.server;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.wst.server.core.IRuntimeType;
import org.eclipse.wst.server.core.IRuntimeWorkingCopy;
import org.eclipse.wst.server.core.ServerCore;
import org.eclipse.wst.server.core.model.RuntimeLocatorDelegate;

import com.ebmwebsourcing.petals.server.runtime.IPetalsRuntimeWorkingCopy;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsRuntimeLocator extends RuntimeLocatorDelegate {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.wst.server.core.model.RuntimeLocatorDelegate
	 * #searchForRuntimes(org.eclipse.core.runtime.IPath,
	 * org.eclipse.wst.server.core.model.RuntimeLocatorDelegate.IRuntimeSearchListener,
	 * org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void searchForRuntimes(
				IPath path,
				IRuntimeSearchListener listener,
				IProgressMonitor monitor ) {

		File rootFile = path == null ? null : path.toFile();
		searchDirectory( rootFile, true, monitor, 0, listener );
	}


	private static final Pattern SEARCH_PATTERN = Pattern.compile( ".*petals.*", Pattern.CASE_INSENSITIVE );


	/**
	 * Gets all the Petals runtime contained in this directory.
	 * 
	 * @param rootFile the root file to introspect (null to search from the disk roots)
	 * @param searchRecursively true to search sub-folders
	 * @param monitor a progress monitor
	 * @param depth the file depth with respect to the root file (should be zero, then used internally)
	 * @param listener the search listener (may be null)
	 * @return a list (never null) of {@link IRuntimeWorkingCopy}
	 */
	public static List<IRuntimeWorkingCopy> searchDirectory(
				File rootFile,
				boolean searchRecursively,
				IProgressMonitor monitor,
				int depth,
				IRuntimeSearchListener listener ) {

		List<IRuntimeWorkingCopy> result = new ArrayList<IRuntimeWorkingCopy> ();
		if( monitor.isCanceled())
			return result;

		// Get the directories
		File[] files = new File[ 0 ];
		if( rootFile == null ) {
			files = File.listRoots();
			depth = 0;

		} else if( rootFile.exists()) {
			files = rootFile.listFiles( new FileFilter() {
				public boolean accept( File pathname ) {
					return pathname.isDirectory();
				}
			});
		}

		// Check every directory
		for( File f : files ) {
			if( SEARCH_PATTERN.matcher( f.getName()).matches()
						&& new File( f, "conf/server.properties" ).exists()
						&& new File( f, "conf/topology.xml" ).exists()) {

				monitor.subTask( "Checking " + f.getAbsolutePath() + "..." );
				IRuntimeWorkingCopy wc = getRuntimeFromDir( f, monitor );
				if( wc != null ) {
					result.add( wc );
					if( listener != null )
						listener.runtimeFound( wc );
				}

			} else if( searchRecursively ) {

				// Limit the message display to "high" directories - so that it is readable
				if( depth < 2 )
					monitor.subTask( "Inspecting " + f.getAbsolutePath() + "..." );

				List<IRuntimeWorkingCopy> res = searchDirectory( f, true, monitor, depth + 1, listener );
				result.addAll( res );
			}
		}

		return result;
	}


	/**
	 * Creates a {@link IRuntimeWorkingCopy} from a directory.
	 * <p>
	 * The returned instance is initialized:
	 * </p>
	 * <ul>
	 * 	<li>No name (default one, or set one).</li>
	 * 	<li>Install path is set.</li>
	 * 	<li>Default VM.</li>
	 * </ul>
	 * 
	 * @param dir a directory that meets the criteria to be a Petals install directory
	 * @param monitor a progress monitor
	 * @return an instance of {@link IRuntimeWorkingCopy}, or null if none was found
	 */
	private static IRuntimeWorkingCopy getRuntimeFromDir( File dir, IProgressMonitor monitor ) {

		IRuntimeWorkingCopy runtime = null;
		String runtimeTypeId = "com.ebmwebsourcing.petals.server.runtime.";

		// Get the version from the directory name
		Pattern pattern = Pattern.compile( "\\d\\.\\d", Pattern.CASE_INSENSITIVE );
		Matcher matcher = pattern.matcher( dir.getName());
		if( matcher.find()) {

			// Keep only the first digit (3x instead of 3.0)
			String version = matcher.group().charAt( 0 ) + "x";
			runtimeTypeId += version;

		} else {
			PetalsServerPlugin.log(
						"Could not determine the Petals version from the directory name ("
						+ dir.getAbsolutePath() + ").",
						IStatus.WARNING );
		}

		// Instantiate a runtime working copy
		IRuntimeType runtimeType = ServerCore.findRuntimeType( runtimeTypeId );
		if( runtimeType != null ) {
			String absolutePath = dir.getAbsolutePath();
			String id = absolutePath.replace( File.separatorChar,'_' ).replace( ':','-' );
			try {
				runtime = runtimeType.createRuntime( id, monitor );
				runtime.setLocation( new Path( dir.getAbsolutePath()));

				IPetalsRuntimeWorkingCopy wc =
					(IPetalsRuntimeWorkingCopy) runtime.loadAdapter( IPetalsRuntimeWorkingCopy.class, null );

				if( wc != null )
					wc.setVMInstall( JavaRuntime.getDefaultVMInstall());

			} catch( CoreException e ) {
				PetalsServerPlugin.log( e, IStatus.WARNING );
			}
		}

		return runtime;
	}
}
