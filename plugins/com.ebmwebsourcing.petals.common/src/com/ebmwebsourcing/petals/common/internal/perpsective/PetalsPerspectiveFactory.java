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

package com.ebmwebsourcing.petals.common.internal.perpsective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsPerspectiveFactory implements IPerspectiveFactory {

	public static final String PERSPECTIVE_ID = "com.ebmwebsourcing.petals.common.mainPerspective";


	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
	 */
	public void createInitialLayout( IPageLayout layout ) {

		// Left top
		IFolderLayout leftTopFolder = layout.createFolder(
					"leftTopFolder",
					IPageLayout.LEFT,
					0.25f,
					layout.getEditorArea());

		leftTopFolder.addView( "com.ebmwebsourcing.petals.common.projects" );
		leftTopFolder.addView( "org.eclipse.jdt.ui.PackageExplorer" );


		// Left bottom
		IFolderLayout leftBottomFolder = layout.createFolder(
					"leftBottomFolder",
					IPageLayout.BOTTOM,
					0.50f, "leftTopFolder" );

		leftBottomFolder.addView( IPageLayout.ID_OUTLINE );
		leftBottomFolder.addView( "com.ebmwebsourcing.petals.services.explorer" );


		// Bottom
		IFolderLayout bottomFolder = layout.createFolder(
					"bottomFolder",
					IPageLayout.BOTTOM,
					0.75f,
					layout.getEditorArea());

		bottomFolder.addView( IPageLayout.ID_PROBLEM_VIEW );
		bottomFolder.addView( "org.eclipse.pde.runtime.LogView" );
		bottomFolder.addView( IPageLayout.ID_PROP_SHEET );
		bottomFolder.addView( "org.eclipse.ui.console.ConsoleView" );
		bottomFolder.addView( "org.eclipse.ant.ui.views.AntView" );
		// bottomFolder.addView( "com.ebmwebsourcing.petals.repositories.view" );

		// Add wizard shortcuts
		layout.addNewWizardShortcut( "com.ebmwebsourcing.petals.services.sa.project" );
		layout.addNewWizardShortcut( "com.ebmwebsourcing.petals.services.su.project" );
		layout.addNewWizardShortcut( "com.ebmwebsourcing.petals.common.croquisWizard" );
		layout.addNewWizardShortcut( "org.eclipse.ui.wizards.new.folder" );
		layout.addNewWizardShortcut( "org.eclipse.ui.wizards.new.file" );
		layout.addNewWizardShortcut( "org.eclipse.wst.wsdl.ui" );

		// Add perspective shortcuts
		layout.addPerspectiveShortcut( "org.eclipse.jdt.ui.JavaPerspective" );
		layout.addPerspectiveShortcut( "org.eclipse.debug.ui.DebugPerspective" );
		layout.addPerspectiveShortcut( "org.eclipse.team.ui.TeamSynchronizingPerspective" );
		layout.addPerspectiveShortcut( "org.tigris.subversion.subclipse.ui.svnPerspective" );

		// Add view shortcuts
		layout.addShowViewShortcut( "com.ebmwebsourcing.petals.common.projects" );
		layout.addShowViewShortcut( "com.ebmwebsourcing.petals.services.explorer" );
		layout.addShowViewShortcut( "com.ebmwebsourcing.petals.repositories.view" );
		layout.addShowViewShortcut( "org.eclipse.jdt.ui.PackageExplorer" );
		layout.addShowViewShortcut( "org.eclipse.ui.views.ResourceNavigator" );
		layout.addShowViewShortcut( "org.eclipse.ui.console.ConsoleView" );
		layout.addShowViewShortcut( "org.eclipse.ui.views.ContentOutline" );
		layout.addShowViewShortcut( "org.eclipse.ui.views.PropertySheet" );
		layout.addShowViewShortcut( "org.eclipse.ui.views.ProblemView" );
		layout.addShowViewShortcut( "de.tobject.findbugs.view.bugtreeview" );

		// Add action sets
		// layout.addActionSet( "com.ebmwebsoucing.petals.repositories.SearchActionSet" );
	}
}