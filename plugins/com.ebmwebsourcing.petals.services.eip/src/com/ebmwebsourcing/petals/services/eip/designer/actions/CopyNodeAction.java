/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.eip.designer.actions;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

import com.ebmwebsourcing.petals.services.eip.designer.edit.commands.NodeCopyCommand;
import com.ebmwebsourcing.petals.services.eip.designer.edit.parts.AbstractNodeEditPart;
import com.ebmwebsourcing.petals.services.eip.designer.model.AbstractNode;

/**
 * An action to copy nodes.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class CopyNodeAction extends SelectionAction {

	/**
	 * Constructor.
	 * @param part
	 */
	public CopyNodeAction( IWorkbenchPart part ) {
		super( part );
		setLazyEnablementCalculation( true );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.ui.actions.WorkbenchPartAction
	 * #init()
	 */
	@Override
	protected void init() {

		super.init();

		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setText( "Copy" );
		setId( ActionFactory.COPY.getId());
		setHoverImageDescriptor( sharedImages.getImageDescriptor( ISharedImages.IMG_TOOL_COPY ));
		setImageDescriptor( sharedImages.getImageDescriptor( ISharedImages.IMG_TOOL_COPY ));
		setDisabledImageDescriptor( sharedImages.getImageDescriptor( ISharedImages.IMG_TOOL_COPY_DISABLED ));

		setEnabled( false );
	}


	/**
	 * Creates a copy command from the selection.
	 * @param selectedObjects
	 * @return
	 */
	private Command createCopyCommand( List<?> selectedObjects ) {

		if( selectedObjects == null || selectedObjects.isEmpty()) {
			return null;
		}

		NodeCopyCommand cmd = new NodeCopyCommand();
		for( Object o : selectedObjects ) {
			if( o instanceof AbstractNodeEditPart ) {
				AbstractNodeEditPart ep = (AbstractNodeEditPart) o;
				cmd.addElement((AbstractNode) ep.getModel());
			}
		}

		return cmd;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.ui.actions.WorkbenchPartAction
	 * #calculateEnabled()
	 */
	@Override
	protected boolean calculateEnabled() {
		Command cmd = createCopyCommand( getSelectedObjects());
		return cmd != null ? cmd.canExecute() : false;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.action.Action
	 * #run()
	 */
	@Override
	public void run() {
		Command cmd = createCopyCommand( getSelectedObjects());
		if( cmd != null && cmd.canExecute()) {
			cmd.execute();
		}
	}
}
