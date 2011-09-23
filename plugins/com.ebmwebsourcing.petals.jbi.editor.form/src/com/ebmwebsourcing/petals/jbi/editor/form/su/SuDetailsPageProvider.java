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

package com.ebmwebsourcing.petals.jbi.editor.form.su;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IDetailsPageProvider;

import com.ebmwebsourcing.petals.jbi.editor.form.AbstractServicesFormPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SuDetailsPageProvider implements IDetailsPageProvider {

	private final AbstractServicesFormPage page;
	private final Map<Object,CompounedSUDetailsPage> cachedPages;


	/**
	 * Constructor.
	 * @param page
	 */
	public SuDetailsPageProvider( AbstractServicesFormPage page ) {
		this.page = page;
		this.cachedPages = new HashMap<Object,CompounedSUDetailsPage> ();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPageProvider
	 * #getPage(java.lang.Object)
	 */
	public IDetailsPage getPage( Object key ) {

		CompounedSUDetailsPage detailPage = null;
		detailPage = new CompounedSUDetailsPage(page, (AbstractEndpoint)key);
		
		if( detailPage != null ) {
			this.cachedPages.put( key, detailPage );
		}

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
	public CompounedSUDetailsPage getCreatedPage( Object key ) {
		CompounedSUDetailsPage page = this.cachedPages.get( key );
		return page;
	}
}
