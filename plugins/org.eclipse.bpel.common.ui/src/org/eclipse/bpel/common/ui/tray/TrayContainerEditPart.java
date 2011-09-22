/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpel.common.ui.tray;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

/**
 * The container edit part to be used by the Tray editor.
 */
public abstract class TrayContainerEditPart extends TrayEditPart {

	protected class TrayContainerFigure extends Figure {

		protected IFigure titleFigure;
		protected IFigure childrenFigure;


		public TrayContainerFigure() {
			super();
			setLayoutManager( new ToolbarLayout());
			setOpaque( true );

			// VZ
			// setBackgroundColor(CommonUIPlugin.getDefault().getColorRegistry().get(IDetailsColors.COLOR_LIGHT_BACKGROUND));
			setBackgroundColor( Display.getCurrent().getSystemColor( SWT.COLOR_WHITE ));
			// VZ
		}

		public IFigure getChildrenFigure() {
			return this.childrenFigure;
		}

		public void setChildrenFigure(IFigure target) {
			if (this.childrenFigure != null)
				remove(this.childrenFigure);

			this.childrenFigure = target;
			add(target);
		}

		public IFigure getTitleFigure() {
			return this.titleFigure;
		}

		public void setTitleFigure(IFigure target) {

			if (this.titleFigure != null)
				remove(this.titleFigure);

			this.titleFigure = target;
			add(target);
		}
	}

	protected TrayContainerFigure mainFigure;

	public TrayContainerEditPart() {
		super();
	}

	@Override
	protected IFigure createFigure() {
		this.mainFigure = createMainFigure();
		this.mainFigure.setTitleFigure(createTitleFigure());
		this.mainFigure.setChildrenFigure(createChildrenFigure());
		return this.mainFigure;
	}

	protected TrayContainerFigure createMainFigure() {
		return new TrayContainerFigure();
	}

	protected IFigure createTitleFigure() {
		return new Figure();
	}

	protected IFigure createChildrenFigure() {
		Figure result = new Figure();
		result.setLayoutManager( new ToolbarLayout());
		return result;
	}

	/**
	 * Selects the EditPart that represents the given model object.
	 */
	protected EditPart selectEditPart(Object model) {
		EditPart editPart = (EditPart)getViewer().getEditPartRegistry().get(model);
		if (editPart != null)
			getViewer().setSelection( new StructuredSelection(editPart));

		return editPart;
	}

	protected CommandStack getCommandStack() {
		return getViewer().getEditDomain().getCommandStack();
	}

	public IFigure getTitleFigure() {
		return this.mainFigure.getTitleFigure();
	}

	// where children will be added
	@Override
	public IFigure getContentPane() {
		return this.mainFigure.getChildrenFigure();
	}

	// VZ: do not allow to select categories in the tray

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart
	 * #setSelected(int)
	 */
	@Override
	public final void setSelected( int value ) {

		if( canSelectEditPart())
			super.setSelected( value );
	}

	/**
	 * 
	 * @return
	 */
	protected boolean canSelectEditPart() {
		return true;
	}
	// VZ
}
