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
package com.ebmwebsourcing.petals.services.eip.designer;

import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.gef.ui.actions.ZoomInRetargetAction;
import org.eclipse.gef.ui.actions.ZoomOutRetargetAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.RetargetAction;

/**
 * The action bar contributor for the diagram editor part.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipChainDiagramContributor extends ActionBarContributor {

	/* (non-Jsdoc)
	 * @see org.eclipse.gef.ui.actions.ActionBarContributor
	 * #buildActions()
	 */
	@Override
	protected void buildActions() {

		addRetargetAction( new UndoRetargetAction());
		addRetargetAction( new RedoRetargetAction());
		addRetargetAction( new DeleteRetargetAction());

		addRetargetAction( new ZoomInRetargetAction());
		addRetargetAction( new ZoomOutRetargetAction());

		IWorkbenchWindow iww = getPage().getWorkbenchWindow();
		addRetargetAction((RetargetAction) ActionFactory.COPY.create( iww ));
		addRetargetAction((RetargetAction) ActionFactory.PASTE.create( iww ));
	}


	/* (non-Jsdoc)
	 * @see org.eclipse.gef.ui.actions.ActionBarContributor
	 * #declareGlobalActionKeys()
	 */
	@Override
	protected void declareGlobalActionKeys() {
		// nothing
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.ui.part.EditorActionBarContributor
	 * #contributeToToolBar(org.eclipse.jface.action.IToolBarManager)
	 */
	@Override
	public void contributeToToolBar( IToolBarManager toolBarManager ) {

		toolBarManager.add( getAction( ActionFactory.UNDO.getId()));
		toolBarManager.add( getAction( ActionFactory.REDO.getId()));
		toolBarManager.add( getAction( ActionFactory.DELETE.getId()));

		toolBarManager.add( getAction( GEFActionConstants.ZOOM_IN ));
		toolBarManager.add( getAction( GEFActionConstants.ZOOM_OUT ));
		toolBarManager.add( new ZoomComboContributionItem( getPage()));

		toolBarManager.add( getAction( ActionFactory.COPY.getId()));
		toolBarManager.add( getAction( ActionFactory.PASTE.getId()));
	}
}
