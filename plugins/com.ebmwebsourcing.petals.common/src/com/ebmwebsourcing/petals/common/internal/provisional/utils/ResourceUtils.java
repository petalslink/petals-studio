/****************************************************************************
 *
 * Copyright (c) 2008-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.utils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.part.FileEditorInput;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

/**
 * Utility methods to manage and look for resources in the workspace.
 *
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ResourceUtils {

	/**
	 * Gets all the files whose extension is <b>extension</b> and present in <b>containers</b>.
	 * @param extension the file extension. Use "*" for any extension. Not null.
	 * @param containers the containers to explore
	 * @return all the IFile contained into this container (with no limit in the level).
	 */
	public static List<IFile> getFiles( String extension, Collection<? extends IContainer> containers ) {

		List<IFile> result = new ArrayList<IFile> ();
		if( containers == null )
			return result;

		for( IContainer container : containers ) {
			try {
				IResource[] resources = container.members();
				for (IResource resource : resources) {
					switch (resource.getType()) {

					case IResource.FILE:
						if( "*".equals( extension )
									|| extension.equalsIgnoreCase( resource.getFileExtension()))
							result.add((IFile) resource);
						break;

					case IResource.FOLDER:
						IFolder subFolder = (IFolder) resource;
						result.addAll( getFiles( extension, Arrays.asList( subFolder )));
						break;

					case IResource.PROJECT:
						IProject project = (IProject) resource;
						if( project.isAccessible())
							result.addAll( getFiles( extension, Arrays.asList( project )));
						break;

					default:
						System.out.println( "unkown type" );
						break;
					}
				}

			} catch( CoreException e ) {
				PetalsCommonPlugin.log( e, IStatus.ERROR );
			}
		}

		return result;
	}


	/**
	 * Gets all the folders present in <b>container</b>.
	 *
	 * @param container
	 *            the container to explore
	 * @return all the IFolder contained into this container (with no limit in
	 *         the level).
	 */
	public static List<IFolder> getFolders(IContainer container) {

		List<IFolder> result = new ArrayList<IFolder>();
		try {
			IResource[] resources = container.members();
			for (IResource resource : resources) {
				switch (resource.getType()) {
				case IResource.FOLDER:
					IFolder subFolder = (IFolder) resource;
					result.add(subFolder);
					result.addAll(getFolders(subFolder));
				default:
					break;
				}
			}

		} catch( CoreException e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );
		}

		return result;
	}


	/**
	 * Gets sub-containers of container having at least one resource of the
	 * given extensions.
	 * <p>
	 * The algorithm checks in the container children if it contains any valid
	 * element. It searches the entire sub-folders until it finds something or
	 * until it checked all the files in this container.
	 * </p>
	 *
	 * @param container
	 *            the container to search into
	 * @param extensions
	 *            the file extensions to search for
	 * @param resourcesToSkip
	 *            resources to skip. Can't be null.
	 * @return the children that contains at least one composite file
	 */
	public static IResource[] getDirectValidChildren(
				IContainer container,
				List<String> extensions,
				List<IResource> resourcesToSkip) {

		if (container instanceof IProject && !((IProject) container).isOpen())
			return new IResource[0];

		IResource[] res;
		try {
			res = container.members();
		} catch (CoreException e) {
			e.printStackTrace();
			res = new IResource[0];
		}

		ArrayList<IResource> resources = new ArrayList<IResource>();
		for (IResource r : res) {
			if (resourcesToSkip.contains(r))
				continue;

			if (r instanceof IContainer) {
				IResource[] subRes = getDirectValidChildren((IContainer) r,
							extensions, resourcesToSkip);
				if (subRes.length > 0)
					resources.add(r);
			} else if (r instanceof IFile) {
				String extension = ((IFile) r).getFileExtension();
				if (extensions.contains(extension))
					resources.add(r);
			}
		}

		res = new IResource[resources.size()];
		return resources.toArray(res);
	}


	/**
	 * @param container
	 * @param extensions
	 * @param resourcesToSkip
	 * @return
	 */
	public static IResource[] getDirectValidChildren(
				IContainer container,
				String[] extensions,
				List<IResource> resourcesToSkip) {

		return getDirectValidChildren(container, Arrays.asList(extensions),
					resourcesToSkip);
	}


	/**
	 * Gets the plug-in binary path.
	 * <p>
	 * The plug-in can be either in a jar file or as a plug-in project. In case
	 * of a plug-in project, this method assumes the class files are in a "bin"
	 * folder.
	 * </p>
	 *
	 * @param pluginId
	 *            the plug-in ID
	 * @param binaryFolderName
	 *            the name of the binary folder in the plug-in.
	 *            <p>
	 *            If null, the name will be set by default to "bin".
	 *            </p>
	 *
	 * @return the file containing the binary resources of a plug-in.
	 */
	public static File getPluginBinaryPath( final String pluginId, String binaryFolderName ) {

		if (binaryFolderName == null || binaryFolderName.trim().length() == 0)
			binaryFolderName = "bin"; //$NON-NLS-1$

		File bundleFile;
		try {
			bundleFile = FileLocator.getBundleFile( Platform.getBundle( pluginId ));

			if( bundleFile.isFile())
				return bundleFile;

			else if( bundleFile.isDirectory()) {
				File binaryFolder = new File( bundleFile, binaryFolderName );
				if( binaryFolder.exists() && binaryFolder.isDirectory())
					return binaryFolder;
			}

		} catch( Exception e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );
		}

		return null;
	}


	/**
	 * Returns the IFile corresponding to a File object.
	 * <p>
	 * This is a convenience method for:
	 * </p>
	 * <code>
	 * ResourcesPlugin.getWorkspace().getRoot().getFileForLocation( new Path( file.getAbsolutePath()));
	 * </code>
	 *
	 * @param file
	 *            the file
	 * @return the associated IFile, or null if the file is not in the current
	 *         workspace
	 * @see IWorkspaceRoot#getFileForLocation(org.eclipse.core.runtime.IPath)
	 */
	public static IFile getIFile( File file ) {
		Path path = new Path( file.getAbsolutePath());
		return ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(path);
	}


	/**
	 * Returns an IResources corresponding to the File object.
	 * @param file the file
	 * @return an instance of IFile, or IContainer, or null if the file is not in the workspace
	 */
	public static IResource getResource( File file ) {

		Path path = new Path( file.getAbsolutePath());
		if( file.isFile())
			return ResourcesPlugin.getWorkspace().getRoot().getFileForLocation( path );

		return ResourcesPlugin.getWorkspace().getRoot().getContainerForLocation( path );
	}


	/**
	 * Check if this container or one of its children contains at least one file whose name matches the given regular expression.
	 * <p>Hidden folders and folders whose name starts with a dot are skipped.</p>
	 *
	 * @param container the IContainer (it must exist)
	 * @param regex the pattern the file name must verify
	 * @return true if one file at least match, false otherwise
	 */
	public static List<IFile> getFilesByRegexp( IContainer container, String regex ) {

		List<IFile> result = new ArrayList<IFile> ();
		try {
			IResource[] resources = container.members();
			for( IResource resource : resources ) {
				switch( resource.getType()) {

				case IResource.FILE:
					String fileName = resource.getName();
					if( fileName.matches( regex ))
						result.add((IFile) resource);
					break;

				case IResource.FOLDER:
					IFolder subFolder = (IFolder) resource;
					if( subFolder.getName().startsWith( "." )
								|| subFolder.isHidden())
						break;

					List<IFile> subResult = getFilesByRegexp( subFolder, regex );
					result.addAll( subResult );
				}
			}

		} catch (CoreException e) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );
		}

		return result;
	}


	/**
	 * Selects and reveals a resource in the Petals explorer.
	 * <p>
	 * This method can be called from a non-UI thread.
	 * </p>
	 *
	 * @param expand true to expand the node in the viewer
	 * @param resources
	 */
	public static void selectResourceInPetalsExplorer( final boolean expand, final Collection<?> resources ) {

		List<Object> elements = new ArrayList<Object> ();
		for( Object o : resources ) {
			elements.add( o );
			if( o instanceof IResource ) {
				IJavaElement elt = JavaCore.create((IResource) o);
				if( elt != null )
					elements.add( elt );
			}
		}

		selectResourceInView( expand, PetalsConstants.PETALS_PROJECT_EXPLORER_VIEW_ID, elements );
	}


	/**
	 * Selects and reveals a resource in the Petals explorer.
	 * <p>
	 * This method can be called from a non-UI thread.
	 * </p>
	 *
	 * @param expand true to expand the node in the viewer
	 * @param resources
	 */
	public static void selectResourceInPetalsExplorer( final boolean expand, final IResource... resources ) {
		if( resources != null )
			selectResourceInPetalsExplorer( expand, Arrays.asList( resources ));
	}

	/**
	 * Selects and reveals a resource in the Java packages view.
	 * <p>
	 * This method can be called from a non-UI thread.
	 * </p>
	 *
	 * @param expand true to expand the node in the viewer
	 * @param resources
	 */
	public static void selectResourceInJavaView( final boolean expand, final List<? extends IResource> resources ) {
		IResource[] res = new IResource[ resources.size()];
		selectResourceInJavaView( expand, resources.toArray( res ));
	}


	/**
	 * Selects and reveals a resource in the Java packages view.
	 * <p>
	 * This method can be called from a non-UI thread.
	 * </p>
	 *
	 * @param expand true to expand the node in the viewer
	 * @param resources
	 */
	public static void selectResourceInJavaView( final boolean expand, final IResource... resources ) {

		List<Object> elements = new ArrayList<Object> ();
		for( IResource res : resources ) {
			IJavaElement elt = JavaCore.create( res );
			if( elt != null )
				elements.add( elt );
			else
				elements.add( res );
		}

		selectResourceInView( expand, "org.eclipse.jdt.ui.PackageExplorer", elements );
	}


	/**
	 * Selects a resource in a view.
	 * <p>
	 * This method can be called from a non-UI thread.
	 * </p>
	 *
	 * @param expand true to expand the elements
	 * @param viewId the view ID (not null)
	 * @param resources the resources to select
	 */
	public static void selectResourceInView( final boolean expand, final String viewId, final List<?> resources ) {

		Display display = Display.getCurrent();
		if( display == null )
			display = Display.getDefault();

		if( display != null && resources != null ) {
			display.syncExec( new Runnable() {
				@Override
				public void run() {

					try {
						IViewPart viewPart =
							PlatformUI.getWorkbench().getActiveWorkbenchWindow().
							getActivePage().showView( viewId );

						Method getTreeViewerMethod = null;
						if( viewPart instanceof CommonNavigator ) {
							if( expand ) {
								for( Object res : resources )
									((CommonNavigator) viewPart).getCommonViewer().reveal( res );
							}

							((CommonNavigator) viewPart).getCommonViewer().setSelection( new StructuredSelection( resources ), true );

							// Alternative, but less options
							// IStructuredSelection s = new StructuredSelection( resources );
							// viewPart.getSite().getSelectionProvider().setSelection( s );

						} else if(( getTreeViewerMethod = viewPart.getClass().getMethod( "getTreeViewer" )) != null ) {
							TreeViewer viewer = (TreeViewer) getTreeViewerMethod.invoke( viewPart );
							if( viewer != null )
								viewer.setSelection( new StructuredSelection( resources ), expand );

						} else {
							viewPart.getViewSite().getSelectionProvider().setSelection( new StructuredSelection( resources ));
							// PetalsCommonPlugin.log( "Only the common navigator sub-classes are supported (PetalsCommons.ResourceUtils).", IStatus.ERROR );
						}

					} catch( PartInitException e ) {
						PetalsCommonPlugin.log( e, IStatus.WARNING );

					} catch( Exception e ) {
						PetalsCommonPlugin.log( e, IStatus.WARNING );
					}
				}
			});
		}
	}


	/**
	 * Gets the file edited in the active editor.
	 * @return the file edited in the active editor or null if it could not be retrieved.
	 */
	public static IFile getIFileFromEditor() {

		IFile result = null;
		IWorkbench workbench = PlatformUI.getWorkbench();
		if( workbench != null ) {
			IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
			if( window != null ) {
				IWorkbenchPage page = window.getActivePage();
				if( page != null && page.getActiveEditor() != null ) {
					IEditorInput editorInput = page.getActiveEditor().getEditorInput();
					if( editorInput instanceof FileEditorInput )
						result = ((FileEditorInput) editorInput).getFile();
				}
			}
		}

		return result;
	}
}
