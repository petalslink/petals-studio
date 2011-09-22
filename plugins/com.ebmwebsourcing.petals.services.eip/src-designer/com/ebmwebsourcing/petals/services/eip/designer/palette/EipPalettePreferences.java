/****************************************************************************
 * 
 * Copyright (c) 2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.eip.designer.palette;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;

import com.ebmwebsourcing.petals.services.eip.PetalsEipPlugin;

/**
 * Preferences for the palette customization.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipPalettePreferences implements FlyoutPreferences {

	private static final String DOCK_LOCATION = "eip.dock.location";
	private static final String PALETTE_STATE = "eip.palette.state";
	private static final String PALETTE_WIDTH = "eip.palette.width";

	private final IEclipsePreferences prefs;


	/**
	 * Constructor.
	 */
	public EipPalettePreferences() {
		this.prefs = new InstanceScope().getNode( PetalsEipPlugin.PLUGIN_ID );
	}


	/* (non-Jsdoc)
	 * @see org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences
	 * #getDockLocation()
	 */
	public int getDockLocation() {
		return this.prefs.getInt( DOCK_LOCATION, PositionConstants.EAST );
	}


	/* (non-Jsdoc)
	 * @see org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences
	 * #getPaletteState()
	 */
	public int getPaletteState() {
		return this.prefs.getInt( PALETTE_STATE, FlyoutPaletteComposite.STATE_PINNED_OPEN );
	}


	/* (non-Jsdoc)
	 * @see org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences
	 * #getPaletteWidth()
	 */
	public int getPaletteWidth() {
		return this.prefs.getInt( PALETTE_WIDTH, 300 );
	}


	/* (non-Jsdoc)
	 * @see org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences
	 * #setDockLocation(int)
	 */
	public void setDockLocation( int location ) {
		this.prefs.putInt( DOCK_LOCATION, location );
	}


	/* (non-Jsdoc)
	 * @see org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences
	 * #setPaletteState(int)
	 */
	public void setPaletteState( int state ) {
		this.prefs.putInt( PALETTE_STATE, state );
	}


	/* (non-Jsdoc)
	 * @see org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences
	 * #setPaletteWidth(int)
	 */
	public void setPaletteWidth( int width ) {
		this.prefs.putInt( PALETTE_WIDTH, width );
	}
}
