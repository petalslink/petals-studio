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
 
package com.ebmwebsourcing.petals.services.eip.designer.edit.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.internal.ui.palette.editparts.ToolEntryEditPart;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteEntry;

import com.ebmwebsourcing.petals.services.eip.designer.figures.EipPaletteTooltipFigure;
import com.ebmwebsourcing.petals.services.eip.designer.model.EIPtype;

/**
 * A tool entry with a custom tool tip for EIP nodes.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipToolEntryEditPart extends ToolEntryEditPart {

	/**
	 * Constructor.
	 * @param paletteEntry
	 */
	public EipToolEntryEditPart( PaletteEntry paletteEntry ) {
		super( paletteEntry );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.ui.palette.editparts.PaletteEditPart
	 * #createToolTip()
	 */
	@Override
	protected IFigure createToolTip() {

		IFigure result = null;
		if( getModel() instanceof CombinedTemplateCreationEntry ) {

			Object tpl = ((CombinedTemplateCreationEntry) getModel()).getTemplate();
			if( tpl instanceof EIPtype )
				result = new EipPaletteTooltipFigure((EIPtype) tpl);
			else
				result = super.createToolTip();
		}
		else
			result = super.createToolTip();

		return result;
	}
}
