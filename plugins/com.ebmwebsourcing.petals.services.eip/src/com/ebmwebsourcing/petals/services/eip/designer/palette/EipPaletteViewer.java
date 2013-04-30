/****************************************************************************
 * 
 * Copyright (c) 2011-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.eip.designer.palette;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.ui.palette.DefaultPaletteViewerPreferences;
import org.eclipse.gef.ui.palette.PaletteEditPartFactory;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerPreferences;
import org.eclipse.jface.preference.IPreferenceStore;

import com.ebmwebsourcing.petals.services.eip.PetalsEipPlugin;
import com.ebmwebsourcing.petals.services.eip.designer.edit.parts.EipToolEntryEditPart;

/**
 * Our own palette provider.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipPaletteViewer extends PaletteViewer {

	/**
	 * Constructor.
	 */
	public EipPaletteViewer() {

		// Super constructor
		super();

		// Define the preferences
		IPreferenceStore store = PetalsEipPlugin.getDefault().getPreferenceStore();
		PaletteViewerPreferences prefs = new DefaultPaletteViewerPreferences( store );
		store.setDefault( PaletteViewerPreferences.PREFERENCE_LAYOUT, PaletteViewerPreferences.LAYOUT_COLUMNS );
		setPaletteViewerPreferences( prefs );

		// Change the factory for tool tips
		setEditPartFactory( new PaletteEditPartFactory() {
			@Override
			protected EditPart createEntryEditPart( EditPart parentEditPart, Object model ) {
				return new EipToolEntryEditPart((PaletteEntry) model);
			}
		});

		// DnD
		addDragSourceListener( new TemplateTransferDragSourceListener( this ));
	}
}
