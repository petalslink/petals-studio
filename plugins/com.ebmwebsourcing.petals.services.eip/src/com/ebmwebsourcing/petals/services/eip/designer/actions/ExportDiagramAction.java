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
package com.ebmwebsourcing.petals.services.eip.designer.actions;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.eip.PetalsEipPlugin;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipChain;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ExportDiagramAction extends Action {

	private final static int BORDER_MARGIN_SIDES = 8;
	private final static int BORDER_MARGIN_UPDOWN = 7;

	private final GraphicalViewer graphicalViewer;
	private final EipChain eipChain;


	/**
	 * Constructor.
	 * @param graphicalViewer
	 * @param eipChain
	 */
	public ExportDiagramAction( GraphicalViewer graphicalViewer, EipChain eipChain ) {

		this.graphicalViewer = graphicalViewer;
		this.eipChain = eipChain;

		setId( "com.ebmwebsourcing.petals.services.eip.contextmenu.exportdiagram" );
		setText( "Export as an image" );
		setToolTipText( "Export a diagram as an image" );
		setDescription( "Export a diagram in an image file." );

		ImageDescriptor desc = PetalsEipPlugin.getImageDescriptor( "icons/obj16/image.gif" );
		setImageDescriptor( desc );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.action.Action
	 * #run()
	 */
	@Override
	public void run() {

		// Determine the export location
		FileDialog dlg = new FileDialog( new Shell(), SWT.SAVE );
		dlg.setText( "Image Export" );
		dlg.setOverwrite( true );
		dlg.setFilterNames( new String[] { "JPEG Files (*.jpg)", "PNG Files (*.png)", "GIF Files (*.gif)" });
		String[] extensions = new String[] { "*.jpg", "*.png", "*.gif" };
		dlg.setFilterExtensions( extensions );
		String path = dlg.open();

		if( path != null ) {

			String ext = extensions[ dlg.getFilterIndex()].substring( 1 );
			if( ! path.endsWith( ext ))
				path += ext;

			// Only print printable layers
			ScalableFreeformRootEditPart rootEditPart = (ScalableFreeformRootEditPart) this.graphicalViewer.getEditPartRegistry().get( LayerManager.ID );
			IFigure rootFigure = ((LayerManager) rootEditPart).getLayer( LayerConstants.PRINTABLE_LAYERS );
			IFigure eipChainFigure = null;
			for( Object o : rootFigure.getChildren()) {
				if( o instanceof FreeformLayer )
					eipChainFigure = (FreeformLayer) o;
			}

			Rectangle bounds = eipChainFigure == null ? rootFigure.getBounds() : eipChainFigure.getBounds();
			GC figureCanvasGC = new GC( this.graphicalViewer.getControl());

			// Add 20 pixels to write the title of the diagram on the image
			Image img = new Image( null, bounds.width, bounds.height );
			GC gc = new GC( img );
			gc.setBackground( figureCanvasGC.getBackground());
			gc.setForeground( figureCanvasGC.getForeground());
			gc.setFont( figureCanvasGC.getFont());
			gc.setLineStyle( figureCanvasGC.getLineStyle());
			gc.setLineWidth( figureCanvasGC.getLineWidth());
			Graphics imgGraphics = new SWTGraphics( gc );

			// Paint it...
			rootFigure.paint( imgGraphics );

			// Prepare the next step
			Font font = new Font( Display.getCurrent(), "Arial", 14, SWT.BOLD );
			gc.setFont( font );
			int height = Dialog.convertHeightInCharsToPixels( gc.getFontMetrics(), 1 );
			gc.dispose();


			// Create a second image with the title and diagram version
			bounds.height += height + (height%2 == 0 ? 6 : 7);	// Write the title and its border
			bounds.height += 3 * BORDER_MARGIN_UPDOWN + 2;	// Add a border + some margin
			bounds.width += 2 * BORDER_MARGIN_SIDES + 2;	// Add a border

			Image finalImg = new Image( null, bounds.width, bounds.height );
			gc = new GC( finalImg );

			// Draw a surrounding border
			gc.setAntialias( SWT.ON );
			gc.setForeground( Display.getCurrent().getSystemColor( SWT.COLOR_WIDGET_DARK_SHADOW ));
			gc.drawLine( BORDER_MARGIN_SIDES, BORDER_MARGIN_UPDOWN, BORDER_MARGIN_SIDES, bounds.height - BORDER_MARGIN_UPDOWN );
			gc.drawLine( BORDER_MARGIN_SIDES, bounds.height - BORDER_MARGIN_UPDOWN, bounds.width - BORDER_MARGIN_SIDES, bounds.height - BORDER_MARGIN_UPDOWN );
			gc.drawLine( BORDER_MARGIN_SIDES, BORDER_MARGIN_UPDOWN, bounds.width - BORDER_MARGIN_SIDES, BORDER_MARGIN_UPDOWN );
			gc.drawLine( bounds.width - BORDER_MARGIN_SIDES, BORDER_MARGIN_UPDOWN, bounds.width - BORDER_MARGIN_SIDES, bounds.height - BORDER_MARGIN_UPDOWN );

			// Draw the title
			gc.setForeground( Display.getCurrent().getSystemColor( SWT.COLOR_BLACK ));
			gc.setFont( font );
			gc.setClipping((Region) null);
			gc.setForeground( Display.getCurrent().getSystemColor( SWT.COLOR_BLACK ));

			String title = this.eipChain.getTitle() != null ? this.eipChain.getTitle() : "No diagram title";
			if( ! StringUtils.isEmpty( this.eipChain.getVersion()))
				title += " - Version " + this.eipChain.getVersion().trim();

			int width = Dialog.convertWidthInCharsToPixels( gc.getFontMetrics(), title.length());
			if( width > bounds.width ) {
				title = Dialog.shortenText( title, this.graphicalViewer.getControl());
				width = Dialog.convertWidthInCharsToPixels( gc.getFontMetrics(), title.length());
			}

			gc.drawText( title, 6 + BORDER_MARGIN_SIDES, 4 + BORDER_MARGIN_UPDOWN, true );

			// Draw a surrounding shape for the title
			gc.setForeground( Display.getCurrent().getSystemColor( SWT.COLOR_WIDGET_DARK_SHADOW ));
			Rectangle titleRectangle = new Rectangle(
						BORDER_MARGIN_SIDES,
						BORDER_MARGIN_UPDOWN,
						BORDER_MARGIN_SIDES + width + (width%2 == 0 ? 10 : 11),
						BORDER_MARGIN_UPDOWN + height + (height%2 == 0 ? 6 : 7));

			final int ARROW_PADDING = (titleRectangle.height + bounds.y) / 2;
			gc.drawLine(
						titleRectangle.x,
						titleRectangle.height,
						titleRectangle.width,
						titleRectangle.height );

			gc.drawLine(
						titleRectangle.width,
						titleRectangle.y,
						titleRectangle.width + ARROW_PADDING,
						ARROW_PADDING  );

			gc.drawLine(
						titleRectangle.width,
						titleRectangle.height,
						titleRectangle.width + ARROW_PADDING,
						ARROW_PADDING );

			// Draw the image
			gc.drawImage( img, BORDER_MARGIN_SIDES + 1, BORDER_MARGIN_UPDOWN + titleRectangle.height + 1 );


			// Save the second image
			ImageData[] imgData = new ImageData[ 1 ];
			imgData[ 0 ] = finalImg.getImageData();

			int format;
			String tail = path.toLowerCase().substring( path.length() - 4 );
			if( tail.endsWith( ".jpg" ))
				format = SWT.IMAGE_JPEG;
			else if( tail.endsWith( ".png" ))
				format = SWT.IMAGE_PNG;
			else
				format = SWT.IMAGE_GIF;

			ImageLoader imgLoader = new ImageLoader();
			imgLoader.data = imgData;
			imgLoader.save( path, format );


			// Dispose everything
			figureCanvasGC.dispose();
			gc.dispose();
			img.dispose();
			finalImg.dispose();
			font.dispose();
		}
	}
}
