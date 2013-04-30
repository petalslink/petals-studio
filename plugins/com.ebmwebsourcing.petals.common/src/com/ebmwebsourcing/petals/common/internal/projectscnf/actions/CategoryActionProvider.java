/****************************************************************************
 * 
 * Copyright (c) 2010-2013, Linagora
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
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.eclipse.ui.navigator.WizardActionGroup;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class CategoryActionProvider extends CommonActionProvider {

	private WizardActionGroup importWizardActionGroup;
	private WizardActionGroup exportWizardActionGroup;


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.navigator.CommonActionProvider
	 * #init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
	 */
	@Override
	public void init( final ICommonActionExtensionSite aSite ) {

		// Import
		IWorkbenchWindow window = ((ICommonViewerWorkbenchSite) aSite.getViewSite()).getWorkbenchWindow();
		this.importWizardActionGroup = new WizardActionGroup(
					window,
					PlatformUI.getWorkbench().getImportWizardRegistry(),
					WizardActionGroup.TYPE_IMPORT,
					aSite.getContentService());

		// Export
		this.exportWizardActionGroup = new WizardActionGroup(
					window,
					PlatformUI.getWorkbench().getExportWizardRegistry(),
					WizardActionGroup.TYPE_EXPORT,
					aSite.getContentService());
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.actions.ActionGroup
	 * #fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	public void fillContextMenu( IMenuManager menu ) {

		// Anchor for "New"
		MenuManager newManager = new MenuManager( "&New", "petals.projects.new" );
		menu.insertAfter( ICommonMenuConstants.GROUP_NEW, newManager );

		// Export
		newManager = new MenuManager( "Export", "petals.projects.export" );
		this.exportWizardActionGroup.setContext( getContext());
		this.exportWizardActionGroup.fillContextMenu( newManager );
		newManager.add( new Separator( "petals.projects.export.separator" ));
		menu.insertAfter( ICommonMenuConstants.GROUP_OPEN, newManager );

		// Import
		newManager = new MenuManager( "Import", "petals.projects.import" );
		this.importWizardActionGroup.setContext( getContext());
		this.importWizardActionGroup.fillContextMenu( newManager );
		newManager.add( new Separator( "petals.projects.import.separator" ));
		menu.insertAfter( ICommonMenuConstants.GROUP_OPEN, newManager );
	}
}
