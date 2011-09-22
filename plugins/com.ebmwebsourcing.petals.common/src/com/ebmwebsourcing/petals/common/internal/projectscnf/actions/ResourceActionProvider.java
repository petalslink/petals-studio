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

package com.ebmwebsourcing.petals.common.internal.projectscnf.actions;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.eclipse.ui.navigator.WizardActionGroup;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ResourceActionProvider extends CommonActionProvider {

	private WizardActionGroup newWizardActionGroup;
	private ActionFactory.IWorkbenchAction showDlgAction;


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.navigator.CommonActionProvider
	 * #init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
	 */
	@Override
	public void init( ICommonActionExtensionSite anExtensionSite ) {

		if( !( anExtensionSite.getViewSite() instanceof ICommonViewerWorkbenchSite ))
			return;

		final ICommonViewerWorkbenchSite site = (ICommonViewerWorkbenchSite) anExtensionSite.getViewSite();
		IWorkbenchWindow window = site.getWorkbenchWindow();
		this.showDlgAction = ActionFactory.NEW.create( window );
		this.newWizardActionGroup = new WizardActionGroup(
					window,
					PlatformUI.getWorkbench().getNewWizardRegistry(),
					WizardActionGroup.TYPE_NEW,
					anExtensionSite.getContentService());
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.actions.ActionGroup
	 * #fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	public void fillContextMenu( IMenuManager menu ) {

		// Override the new...
		MenuManager newManager = new MenuManager( "&New", "petals.projects.resource.new" );
		newManager.add( new Separator( "petals.wizard.new" ));

		// See NewActionProvider for more details
		this.newWizardActionGroup.setContext( getContext());
		this.newWizardActionGroup.fillContextMenu( newManager );

		newManager.add( new Separator( ICommonMenuConstants.GROUP_ADDITIONS ));
		newManager.add( new Separator());
		newManager.add( this.showDlgAction );
		menu.insertBefore( ICommonMenuConstants.GROUP_NEW, newManager );
	}
}
