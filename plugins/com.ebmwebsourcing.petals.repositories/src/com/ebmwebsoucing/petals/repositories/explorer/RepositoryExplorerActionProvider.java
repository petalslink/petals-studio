/****************************************************************************
 * 
 * Copyright (c) 2010-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsoucing.petals.repositories.explorer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

import com.ebmwebsoucing.petals.repositories.PetalsRepositoriesPlugin;
import com.ebmwebsoucing.petals.repositories.explorer.model.Repository;
import com.ebmwebsoucing.petals.repositories.explorer.wizards.RepositoryNewWizard;

/**
 * Adds actions on elements from the repository explorer.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class RepositoryExplorerActionProvider extends CommonActionProvider {

	private Action deleteRepositoryAction;
	private Action addRepositoryAction;
	private Action propertiesAction;


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
		// Remove action
		this.deleteRepositoryAction = new Action() {
			@Override
			public void run() {
				Repository rep = getSelectedRepository( provider );
				if( rep != null )
					RepositoryManager.getInstance().removeRepository( rep );
			}

			@Override
			public boolean isEnabled() {
				return getSelectedRepository( provider ) != null;
			}
		};

		this.deleteRepositoryAction.setText( "Delete" );
		this.deleteRepositoryAction.setToolTipText( "Delete this service repository" );
		this.deleteRepositoryAction.setDescription( "Delete this service repository." );
		ImageDescriptor desc = PetalsRepositoriesPlugin.getImageDescriptor( "icons/obj16/delete.gif" );
		this.deleteRepositoryAction.setImageDescriptor( desc );


		//
		// New repository action
		this.addRepositoryAction = new Action() {
			@Override
			public void run() {

				IWorkbench workbench = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getWorkbench();
				RepositoryNewWizard wizard = new RepositoryNewWizard();

				final Display display = PlatformUI.getWorkbench().getDisplay();
				WizardDialog dlg = new WizardDialog( new Shell( display ), wizard ) {
					@Override
					protected Control createContents( Composite parent ) {

						Control c = super.createContents( parent );

						Rectangle rect = display.getBounds();
						Point shellPoint = getShell().getSize();
						int x = (rect.width - shellPoint.x) / 2 + rect.x;
						int y = (rect.height - shellPoint.y) / 2 + rect.y;
						getShell().setLocation( x, y );

						return c;
					}
				};

				workbench.saveAllEditors( true );
				dlg.setPageSize( 400, 300 );
				dlg.open();
			}

			@Override
			public boolean isEnabled() {
				return true;
			}
		};

		this.addRepositoryAction.setText( "New Repository" );
		this.addRepositoryAction.setToolTipText( "Add a new service repository" );
		this.addRepositoryAction.setDescription( "Add a new service repository." );
		desc = PetalsRepositoriesPlugin.getImageDescriptor( "icons/obj16/petals_service.png" );
		this.addRepositoryAction.setImageDescriptor( desc );


		//
		// Properties action
		this.propertiesAction = new Action() {
			@Override
			public boolean isEnabled() {
				return getSelectedRepository( provider ) != null;
			}

			@Override
			public void run() {

				Repository rep = getSelectedRepository( provider );
				try {
					// Show properties view
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					page.showView( "org.eclipse.ui.views.PropertySheet" ); //$NON-NLS-1$

					// Show the Petals service explorer
					page.showView( "com.ebmwebsourcing.petals.repositories.view" ); //$NON-NLS-1$

					// Select the previous selection in the outline.
					provider.setSelection( new StructuredSelection( rep ));
				}
				catch( Exception e ) {
					PetalsRepositoriesPlugin.log( e, IStatus.ERROR );
				}
			}
		};

		this.propertiesAction.setText( "Properties" );
		this.propertiesAction.setToolTipText( "Show properties" );
		this.propertiesAction.setDescription( "Show the properties of the selected element." );
		desc = PetalsRepositoriesPlugin.getImageDescriptor( "icons/obj16/properties.gif" );
		this.propertiesAction.setImageDescriptor( desc );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.actions.ActionGroup
	 * #fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	public void fillContextMenu( IMenuManager menu ) {

		menu.add( this.addRepositoryAction );
		if( this.deleteRepositoryAction.isEnabled())
			menu.add( this.deleteRepositoryAction );

		menu.add( new Separator());
		if( this.propertiesAction.isEnabled()) {
			menu.add( new Separator());
			menu.add( this.propertiesAction );
		}
	}


	/**
	 * @param provider the selection provider
	 * @return a repository, or null if the the selection is empty or is not a repository
	 */
	private Repository getSelectedRepository( ISelectionProvider provider ) {

		Repository result = null;
		if( provider.getSelection() != null && provider.getSelection() instanceof IStructuredSelection ) {
			Object o = ((IStructuredSelection) provider.getSelection()).getFirstElement();
			if( o instanceof Repository )
				result = (Repository) o;
		}

		return result;
	}
}
