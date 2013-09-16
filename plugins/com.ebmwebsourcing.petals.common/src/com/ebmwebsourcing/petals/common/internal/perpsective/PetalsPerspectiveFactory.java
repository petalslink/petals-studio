/******************************************************************************
 * Copyright (c) 2009-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
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
	@Override
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
		layout.addNewWizardShortcut( "com.ebmwebsourcing.petals.services.provider.project" );
		layout.addNewWizardShortcut( "com.ebmwebsourcing.petals.services.consumer.project" );
		layout.addNewWizardShortcut( "com.ebmwebsourcing.petals.services.sa.project" );
		layout.addNewWizardShortcut( "com.ebmwebsourcing.petals.common.croquisWizard" );
		layout.addNewWizardShortcut( "org.eclipse.wst.wsdl.ui" );
		layout.addNewWizardShortcut( "org.eclipse.ui.wizards.new.folder" );
		layout.addNewWizardShortcut( "org.eclipse.ui.wizards.new.file" );

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
