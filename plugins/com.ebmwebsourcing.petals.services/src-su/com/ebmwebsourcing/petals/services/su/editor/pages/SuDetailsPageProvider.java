/****************************************************************************
 * 
 * Copyright (c) 2010-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.editor.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IDetailsPageProvider;
import org.w3c.dom.Element;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.DomUtils;
import com.ebmwebsourcing.petals.services.editor.AbstractServicesFormPage;
import com.ebmwebsourcing.petals.services.su.editor.blocks.GeneralAbstractDetails;
import com.ebmwebsourcing.petals.services.su.editor.blocks.GeneralConsumesDetails;
import com.ebmwebsourcing.petals.services.su.editor.blocks.GeneralProvidesDetails;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SuDetailsPageProvider implements IDetailsPageProvider {

	private final AbstractServicesFormPage page;
	private final Map<Object,GeneralAbstractDetails> cachedPages;


	/**
	 * Constructor.
	 * @param page
	 */
	public SuDetailsPageProvider( AbstractServicesFormPage page ) {
		this.page = page;
		this.cachedPages = new HashMap<Object,GeneralAbstractDetails> ();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPageProvider
	 * #getPage(java.lang.Object)
	 */
	public IDetailsPage getPage( Object key ) {

		GeneralAbstractDetails detailPage = null;
		if( key != null && key instanceof Element ) {
			Element elt = (Element) key;
			String markup = DomUtils.getNodeName( elt );

			if( "provides".equalsIgnoreCase( markup ))
				detailPage = new GeneralProvidesDetails( this.page );
			else if( "consumes".equalsIgnoreCase( markup ))
				detailPage = new GeneralConsumesDetails( this.page );

			if( detailPage != null ) {
				List<IMarker> markers = this.page.getMarkersForDetails( elt );
				detailPage.setPagesMarkers( markers );
				this.cachedPages.put( key, detailPage );
			}
		};

		return detailPage;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPageProvider
	 * #getPageKey(java.lang.Object)
	 */
	public Object getPageKey( Object object ) {
		return object;
	}


	/**
	 * Gets the already created (and cached) page for this key.
	 * <p>
	 * Unlike {@link #getPage(Object)}, it prevents the creation of the
	 * page if it does not exist.
	 * </p>
	 * @param key
	 * @return
	 */
	public GeneralAbstractDetails getCreatedPage( Object key ) {
		GeneralAbstractDetails page = this.cachedPages.get( key );
		return page;
	}
}
