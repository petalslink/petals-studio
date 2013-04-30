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
package com.ebmwebsourcing.petals.services.eip.designer.zoom;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.Tool;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

import com.ebmwebsourcing.petals.services.eip.PetalsEipPlugin;

/**
 * A tool entry to add "zoom in" in a GEF palette.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ZoomInToolEntry extends ToolEntry {

	/**
	 * Constructor.
	 */
	public ZoomInToolEntry() {
		super( "Zoom In", "Zoom in the diagram", getImageDescriptor( false ), getImageDescriptor( true ));
		setUserModificationPermission( PERMISSION_NO_MODIFICATION );
	}


	/**
	 * @return the image descriptor for this tool entry.
	 */
	private static ImageDescriptor getImageDescriptor( boolean big ) {

		ImageDescriptor result = null;
		try {
			if( big )
				result = PetalsEipPlugin.getImageDescriptor( "icons/obj24/zoomplus_24x24.gif" );
			else
				result = PetalsEipPlugin.getImageDescriptor( "icons/obj16/zoomplus.gif" );

		} catch( Exception e ) {
			PetalsEipPlugin.log( e, IStatus.WARNING );
		}

		return result;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.palette.ToolEntry
	 * #createTool()
	 */
	@Override
	public Tool createTool() {
		return new ZoomInTool();
	}


	/**
	 * The "zoom in" tool (refactored from the BPEL Designer).
	 */
	private static class ZoomInTool extends AbstractTool {

		private Cursor cursor;


		/*
		 * (non-Jsdoc)
		 * @see org.eclipse.gef.tools.AbstractTool
		 * #getCommandName()
		 */
		@Override
		protected String getCommandName() {
			return null;
		}

		@Override
		public void activate() {
			setDefaultCursor( createCursor());
			super.activate();
		}


		/*
		 * (non-Jsdoc)
		 * @see org.eclipse.gef.tools.AbstractTool
		 * #deactivate()
		 */
		@Override
		public void deactivate() {

			super.deactivate();
			if( this.cursor != null && ! this.cursor.isDisposed())
				this.cursor.dispose();
		}


		/*
		 * (non-Jsdoc)
		 * @see org.eclipse.gef.tools.AbstractTool
		 * #handleButtonDown(int)
		 */
		@Override
		protected boolean handleButtonDown( int button ) {

			// See MouseEvent#button
			if( button == 1 ) {
				EditPartViewer v = getCurrentViewer();
				if( v instanceof GraphicalViewer ) {
					ScalableFreeformRootEditPart root = (ScalableFreeformRootEditPart) v.getRootEditPart();
					root.getZoomManager().zoomIn();
				}
			}

			return true;
		}


		/**
		 * @return
		 */
		private Cursor createCursor() {

			ImageData source = PetalsEipPlugin.getImageDescriptor( "icons/cursor/zoomin_source.bmp" ).getImageData();
			ImageData mask = PetalsEipPlugin.getImageDescriptor( "icons/cursor/zoom_mask.bmp" ).getImageData();
			this.cursor = new Cursor( Display.getDefault(), source, mask, 0, 0 );

			return this.cursor;
		}
	}
}
