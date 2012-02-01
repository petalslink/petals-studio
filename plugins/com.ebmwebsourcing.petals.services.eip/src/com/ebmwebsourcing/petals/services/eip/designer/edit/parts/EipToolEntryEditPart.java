/****************************************************************************
 * 
 * Copyright (c) 2011-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
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
