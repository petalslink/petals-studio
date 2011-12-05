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
package com.ebmwebsourcing.petals.services.eip.designer.outline;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.parts.ScrollableThumbnail;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.ebmwebsourcing.petals.services.eip.designer.EipChainDiagramEditor;

/**
 * The outline page for the EIP diagram editor.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipDiagramOutlinePage extends ContentOutlinePage {

	private SashForm sash;
	private ScrollableThumbnail thumbnail;
	private DisposeListener disposeListener;

	private final EipChainDiagramEditor editor;


	/**
	 * Constructor.
	 * @param editor
	 */
	public EipDiagramOutlinePage( EipChainDiagramEditor editor ) {
		super( new TreeViewer());
		this.editor = editor;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.ui.parts.ContentOutlinePage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl( Composite parent ) {

		this.sash = new SashForm( parent, SWT.VERTICAL );
		Canvas canvas = new Canvas( this.sash, SWT.BORDER );
		LightweightSystem lws = new LightweightSystem( canvas );

		ScalableFreeformRootEditPart rootEP =
			(ScalableFreeformRootEditPart) this.editor.getGraphicalViewer().getRootEditPart();

		this.thumbnail = new ScrollableThumbnail((Viewport) rootEP.getFigure());
		this.thumbnail.setSource( rootEP.getLayer(LayerConstants.PRINTABLE_LAYERS ));
		lws.setContents( this.thumbnail );

		this.disposeListener = new DisposeListener() {
			public void widgetDisposed( DisposeEvent e ) {
				if (EipDiagramOutlinePage.this.thumbnail != null) {
					EipDiagramOutlinePage.this.thumbnail.deactivate();
					EipDiagramOutlinePage.this.thumbnail = null;
				}
			}
		};

		this.editor.getGraphicalViewer().getControl().addDisposeListener( this.disposeListener );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.ui.parts.ContentOutlinePage
	 * #getControl()
	 */
	@Override
	public Control getControl() {
		return this.sash;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.ui.part.Page
	 * #dispose()
	 */
	@Override
	public void dispose() {

		this.editor.getSelectionSynchronizer().removeViewer(getViewer());
		if( this.editor.getGraphicalViewer().getControl() != null
					&& ! this.editor.getGraphicalViewer().getControl().isDisposed())
			this.editor.getGraphicalViewer().getControl().removeDisposeListener( this.disposeListener );

		super.dispose();
	}
}
