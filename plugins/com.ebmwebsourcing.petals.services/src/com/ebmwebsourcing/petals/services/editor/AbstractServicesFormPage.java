/****************************************************************************
 * 
 * Copyright (c) 2009-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.editor;

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class AbstractServicesFormPage extends FormPage {

	protected IServiceMasterPage masterPage;


	/**
	 * Cached markers (cached because the GUI might not be ready to display them).
	 */
	private Map<IMarker,Node> cachedMarkerToNode;
	protected IManagedForm managedForm;
	protected Document document;



	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public AbstractServicesFormPage( JbiFormEditor editor, String id, String title ) {
		super( editor, id, title );
	}


	/**
	 * Updates the page when the model changed.
	 */
	public abstract void updatePage();


	/**
	 * Updates the markers on the page.
	 * @param markerToNode
	 */
	public void updateMarkers( Map<IMarker,Node> markerToNode ) {

		this.cachedMarkerToNode = markerToNode;
		if( this.managedForm != null )
			updateCachedMarkers();
	}


	/**
	 * Update the GUI to display the cached markers.
	 * <p>
	 * This method can only be called after {@link AbstractServicesFormPage
	 * #onPageContentCreation(IManagedForm)} has been called.
	 * </p>
	 */
	private void updateCachedMarkers() {

		if( this.cachedMarkerToNode != null ) {
			this.managedForm.getMessageManager().removeAllMessages();
			this.masterPage.updateMarkers( this.cachedMarkerToNode );
		}
	}


	/**
	 * @param managedForm
	 */
	protected void onPageContentCreation( IManagedForm managedForm ) {

		this.managedForm = managedForm;
		managedForm.getMessageManager().setAutoUpdate( false );
		managedForm.getForm().getForm().addMessageHyperlinkListener( new HyperlinkAdapter() {

			@Override
			public void linkEntered( HyperlinkEvent e ) {
				// nothing
			}
		});

		updateCachedMarkers();
	}


	/**
	 * Shows and selects the part which is wrong and associated with this marker.
	 * @param node (not null)
	 * @param xPathLocation
	 * @return true if a matching widget could be found, false otherwise
	 */
	public boolean gotoMarker( Node node, String xPathLocation ) {
		return this.masterPage.gotoMarker( node, xPathLocation );
	}


	/**
	 * Gets the markers for the details page associated with this element.
	 * @param elt
	 * @return a list of markers (possibly null)
	 */
	public List<IMarker> getMarkersForDetails( Element elt ) {
		return this.masterPage.getMarkersForDetails( elt );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormPage#dispose()
	 */
	@Override
	public void dispose() {
		this.masterPage.dispose();
		super.dispose();
	}


	/**
	 * @return the document
	 */
	public Document getDocument() {
		return this.document;
	}


	/**
	 * @param document the document to set
	 */
	public void setDocument( Document document ) {
		this.document = document;
	}


	/**
	 * Start a set of modifications on the document model.
	 * <p>A call is made to {@link JbiFormEditor#aboutToChangeModel()}.</p>
	 * <p>Any transaction should be ended by a call to {@link #stopTransaction()}.</p>
	 */
	public void startTransaction () {
		((JbiFormEditor) getEditor()).aboutToChangeModel();
	}


	/**
	 * Stop a set of modifications on the document model.
	 * <p>A call is made to {@link JbiFormEditor#changedModel()}</p>
	 * <p>Any transaction should start with a call to {@link #startTransaction()}.</p>
	 */
	public void stopTransaction () {
		((JbiFormEditor) getEditor()).changedModel();
	}
}
