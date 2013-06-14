/****************************************************************************
 * 
 * Copyright (c) 2011-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.eip.designer.actions;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.ebmwebsourcing.petals.services.eip.PetalsEipPlugin;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ShowPropertiesAction extends Action {

	/**
	 * Constructor.
	 */
	public ShowPropertiesAction() {

		setId( "com.ebmwebsourcing.petals.services.eip.contextmenu.showproperties" );
		setText( "Show properties" );
		setToolTipText( "Show the properties" );
		setDescription( "Show the properties." );

		ImageDescriptor desc = PetalsEipPlugin.getImageDescriptor( "icons/obj16/properties.gif" );
		setImageDescriptor( desc );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.action.Action
	 * #run()
	 */
	@Override
	public void run() {

		try {
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			page.showView( IPageLayout.ID_PROP_SHEET );

		} catch( PartInitException e ) {
			PetalsEipPlugin.log( e, IStatus.WARNING );
		}
	}
}
