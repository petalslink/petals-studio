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

package com.ebmwebsourcing.petals.services.explorer;

import java.io.File;
import java.net.URI;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.explorer.model.EndpointBean;
import com.ebmwebsourcing.petals.services.explorer.sources.CurrentWorkspaceSource;
import com.ebmwebsourcing.petals.services.explorer.sources.EndpointSource;
import com.ebmwebsourcing.petals.services.explorer.sources.ExternalWorkspaceSource;
import com.ebmwebsourcing.petals.services.explorer.sources.SaDirectorySource;

/**
 * Adds actions on elements from the End-point explorer.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ServiceExplorerActionProvider extends CommonActionProvider {

	private Action refreshAction;
	private Action deleteAction;
	private Action addWorkspaceFolderSourceAction;
	private Action addSaFolderAction;
	private Action propertiesAction;
	private Action openWsdlAction;
	//	private Action addServerSourceAction;


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.navigator.CommonActionProvider
	 * #init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
	 */
	@Override
	public void init( final ICommonActionExtensionSite aSite ) {

		ICommonViewerSite site = aSite.getViewSite();
		if( !( site instanceof ICommonViewerWorkbenchSite ))
			return;

		final ISelectionProvider provider = ((ICommonViewerWorkbenchSite) site).getSelectionProvider();

		//
		// Workspace Folder Source
		this.addWorkspaceFolderSourceAction = new Action() {
			@Override
			public void run() {

				DirectoryDialog dlg = new DirectoryDialog( aSite.getViewSite().getShell());
				dlg.setText( "External workspace" );
				dlg.setMessage( "Find Petals services in an external workspace." );
				String result = dlg.open();

				if( result != null ) {
					EndpointSource source = new ExternalWorkspaceSource( new File( result ));
					SourceManager.getInstance().addSource( source );
				}
			}
		};

		this.addWorkspaceFolderSourceAction.setText( "Workspace Folder" );
		this.addWorkspaceFolderSourceAction.setToolTipText( "New workspace folder source" );
		this.addWorkspaceFolderSourceAction.setDescription( "Find the Petals services in contained in another workspace folder." );
		ImageDescriptor desc = PetalsServicesPlugin.getImageDescriptor( "icons/obj16/EndpointProjectClosed.gif" );
		this.addWorkspaceFolderSourceAction.setImageDescriptor( desc );


		//
		// SA Folder Source
		this.addSaFolderAction = new Action() {
			@Override
			public void run() {
				DirectoryDialog dlg = new DirectoryDialog( aSite.getViewSite().getShell());
				dlg.setText( "Service Assembly folder" );
				dlg.setMessage( "Find Petals services in a directory of service assemblies." );
				String result = dlg.open();

				if( result != null ) {
					EndpointSource source = new SaDirectorySource( new File( result ), false, false );
					SourceManager.getInstance().addSource( source );
				}
			}
		};

		this.addSaFolderAction.setText( "SA Folder" );
		this.addSaFolderAction.setToolTipText( "New SA folder source" );
		this.addSaFolderAction.setDescription( "Find the Petals services in contained in a folder of service assemblies." );
		desc = PetalsServicesPlugin.getImageDescriptor( "icons/obj16/EndpointFolderClosed.gif" );
		this.addSaFolderAction.setImageDescriptor( desc );


		// 	//
		//	// Workspace Folder Source
		//	this.addServerSourceAction = new Action() {
		//		@Override
		//		public void run() {
		//			// TODO
		//		}
		//	};
		//
		//	this.addServerSourceAction.setText( "Petals Server" );
		//	this.addServerSourceAction.setToolTipText( "New server source" );
		//	this.addServerSourceAction.setDescription( "Browse the end-points contained in a Petals server." );
		//	desc = PetalsServicesPlugin.getImageDescriptor( "icons/obj16/EndpointServer.gif" );
		//	this.addServerSourceAction.setImageDescriptor( desc );


		//
		// Refresh action
		this.refreshAction = new Action() {
			@Override
			public void run() {
				final EndpointSource source = getEndpointSource( provider );
				if( source != null )
					SourceManager.getInstance().updateSource( source );
			}

			@Override
			public boolean isEnabled() {
				return getEndpointSource( provider ) != null;
			}
		};

		this.refreshAction.setText( "Refresh" );
		this.refreshAction.setAccelerator( SWT.F5 );
		this.refreshAction.setToolTipText( "Refresh the list of Petals services" );
		this.refreshAction.setDescription( "Refresh the list of Petals services." );
		desc = PetalsServicesPlugin.getImageDescriptor( "icons/obj16/refresh.gif" );
		this.refreshAction.setImageDescriptor( desc );


		//
		// Remove action
		this.deleteAction = new Action() {
			@Override
			public void run() {
				EndpointSource source = getEndpointSource( provider );
				if( source != null )
					SourceManager.getInstance().removeSource( source );
			}

			@Override
			public boolean isEnabled() {
				EndpointSource edptSource = getEndpointSource( provider );
				return edptSource != null && ! ( edptSource instanceof CurrentWorkspaceSource );
			}
		};

		this.deleteAction.setText( "Delete" );
		this.deleteAction.setToolTipText( "Delete this source of Petals services" );
		this.deleteAction.setDescription( "Delete this source of Petals services." );
		desc = PetalsServicesPlugin.getImageDescriptor( "icons/obj16/delete.gif" );
		this.deleteAction.setImageDescriptor( desc );


		//
		// Properties action
		this.propertiesAction = new Action() {
			@Override
			public boolean isEnabled() {
				return getEndpointSource( provider ) != null
				|| getEndpointBean( provider ) != null;
			}

			@Override
			public void run() {
				Object object = getEndpointSource( provider );
				if( object == null )
					object = getEndpointBean( provider );

				try {
					// Show properties view
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					page.showView( "org.eclipse.ui.views.PropertySheet" ); //$NON-NLS-1$

					// Show the Petals service explorer
					page.showView( "com.ebmwebsourcing.petals.services.explorer" ); //$NON-NLS-1$

					// Select the previous selection in the outline.
					provider.setSelection( new StructuredSelection( object ));
				}
				catch( Exception e ) {
					PetalsServicesPlugin.log( e, IStatus.ERROR );
				}
			}
		};

		this.propertiesAction.setText( "Properties" );
		this.propertiesAction.setToolTipText( "Show properties" );
		this.propertiesAction.setDescription( "Show the properties of the selected element." );
		desc = PetalsServicesPlugin.getImageDescriptor( "icons/obj16/properties.gif" );
		this.propertiesAction.setImageDescriptor( desc );


		//
		// Open WSDL action
		this.openWsdlAction = new Action() {

			@Override
			public boolean isEnabled() {
				EndpointBean edptBean = getEndpointBean( provider );

				if( edptBean != null ) {
					if( edptBean.getServiceUnit().isWsdlContainerLocationComputed()) {
						URI uri = edptBean.getWsdlUri();
						return uri != null && new File( uri ).exists();
					}

					return edptBean.getWsdlLocation() != null;
				}

				return false;
			}

			@Override
			public boolean isHandled() {
				EndpointBean edptBean = getEndpointBean( provider );
				return edptBean != null;
			}

			@Override
			public void run() {
				final EndpointBean edptBean = getEndpointBean( provider );
				try {
					URI wsdlUri = edptBean.getWsdlUri();

					// WSDL not null: open it
					File f = null;
					if( wsdlUri != null && (f = new File( wsdlUri )).exists()) {
						IFileStore fileStore = EFS.getLocalFileSystem().getStore( new Path( f.getAbsolutePath()));
						if( ! fileStore.fetchInfo().isDirectory() && fileStore.fetchInfo().exists()) {
							IWorkbenchPage page =
								PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
							IDE.openEditorOnFileStore( page, fileStore );
						}
					}

					// WSDL URI is null or points to a nonexistent file
					/*
					 * Can only happen when this URI is computed for the first time.
					 * Then, isEnabled() prevents this from happening.
					 * 
					 * Refreshing the source of the end-point will have for consequence
					 * a new computation of the WSDL URI.
					 */
					else if( f != null ) {
						MessageDialog.openInformation(
									aSite.getViewSite().getShell(),
									"Nonexistent WSDL", "The WSDL file " + f.getAbsolutePath() + " does not exist." );
					}
					else {
						MessageDialog.openInformation(
									aSite.getViewSite().getShell(),
									"No WSDL to open", "No WSDL could be found for this service." );
					}

				} catch( Exception e ) {
					MessageDialog.openError(
								aSite.getViewSite().getShell(),
								"Failure in opening the WSDL",
					"The service WSDL could not be opened. See the log for more details." );
					PetalsServicesPlugin.log( e, IStatus.ERROR );
				}
			}
		};

		this.openWsdlAction.setText( "Open WSDL" );
		this.openWsdlAction.setToolTipText( "Show the service interface in the WSDL editor" );
		this.openWsdlAction.setDescription( "Show the service interface in the WSDL editor." );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.actions.ActionGroup
	 * #fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	public void fillContextMenu( IMenuManager menu ) {

		MenuManager newManager = new MenuManager( "&New", "New" );
		newManager.add( this.addSaFolderAction );
		newManager.add( this.addWorkspaceFolderSourceAction );
		//	newManager.add( this.addServerSourceAction );
		menu.add( newManager );

		if( this.refreshAction.isEnabled())
			menu.add( this.refreshAction );

		if( this.deleteAction.isEnabled()) {
			menu.add( new Separator());
			menu.add( this.deleteAction );
		}

		boolean propertiesEnabled = this.propertiesAction.isEnabled();
		boolean openWsdlHandled = this.openWsdlAction.isHandled();
		if( propertiesEnabled || openWsdlHandled ) {

			menu.add( new Separator());

			// The WSDL action must appear on every end-point...
			// but can be activated only if there is a WSDL
			if( openWsdlHandled ) {
				menu.add( this.openWsdlAction );
				this.openWsdlAction.setEnabled( this.openWsdlAction.isEnabled());
			}

			if( propertiesEnabled )
				menu.add( this.propertiesAction );
		}
	}


	/**
	 * @param provider
	 * @return a EndpointSource, or null if the the selection is empty or is not an EndpointSource
	 */
	private EndpointSource getEndpointSource( ISelectionProvider provider ) {

		if( provider.getSelection() != null && provider.getSelection() instanceof IStructuredSelection ) {
			Object o = ((IStructuredSelection) provider.getSelection()).getFirstElement();
			if( o instanceof EndpointSource )
				return (EndpointSource) o;
		}

		return null;
	}


	/**
	 * @param provider
	 * @return a EndpointBean, or null if the the selection is empty or is not an EndpointBean
	 */
	private EndpointBean getEndpointBean( ISelectionProvider provider ) {

		if( provider.getSelection() != null && provider.getSelection() instanceof IStructuredSelection ) {
			Object o = ((IStructuredSelection) provider.getSelection()).getFirstElement();
			if( o instanceof EndpointBean )
				return (EndpointBean) o;
		}

		return null;
	}
}
