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

package com.ebmwebsourcing.petals.jbi.editor.form.sa;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IDetailsPageProvider;
import org.w3c.dom.Element;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.DomUtils;
import com.ebmwebsourcing.petals.jbi.editor.form.AbstractServicesFormPage;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SaDetailsPageProvider implements IDetailsPageProvider {

	private final AbstractServicesFormPage page;
	private final Map<Object,SaDetailsPage> cachedPages = new HashMap<Object,SaDetailsPage> ();


	/**
	 * Constructor.
	 * @param page
	 */
	public SaDetailsPageProvider( AbstractServicesFormPage page ) {
		this.page = page;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPageProvider
	 * #getPage(java.lang.Object)
	 */
	public IDetailsPage getPage( Object key ) {

		SaDetailsPage detailPage = null;
		if( key != null && key instanceof Element ) {
			Element elt = (Element) key;
			String markup = DomUtils.getNodeName( elt );

			if( "service-assembly".equalsIgnoreCase( markup ))
				detailPage = new SaDetailsPage( this.page );
			else if( "service-unit".equalsIgnoreCase( markup ))
				detailPage = new SaSuDetailsPage( this.page );

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
	public SaDetailsPage getCreatedPage( Object key ) {

		SaDetailsPage page = this.cachedPages.get( key );
		return page;
	}
}
