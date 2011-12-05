/****************************************************************************
 *
 * Copyright (c) 2008-2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.wizards;

import java.util.LinkedHashMap;
import java.util.Map;

import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;

/**
 * The class in charge of managing wizard pages order.
 * It does not create any page. Wizards and pages should register any insertion here.
 *
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PageManager {

	/**
	 * The wizard pages.
	 * Insertion order matters and is kept.
	 * The key is the page name and the value is the AbstractSuPage page.
	 */
	private final LinkedHashMap<String, AbstractSuPage> pages = new LinkedHashMap<String, AbstractSuPage> ();


	/**
	 * Constructor.
	 */
	public PageManager() {
		// nothing
	}

	/**
	 * Adds a page.
	 * @param page
	 */
	public void addPage( AbstractSuPage page ) {
		this.pages.put( page.getName(), page );
	}

	/**
	 * Inserts a page with the given name.
	 * @param pageName
	 * @param page
	 */
	public void put( String pageName, AbstractSuPage page ) {
		this.pages.put( pageName, page );
	}

	/**
	 * Returns the page whose name is given in argument.
	 * @param pageName
	 * @return the associated page
	 */
	public AbstractSuPage getPage( String pageName ) {

		AbstractSuPage result = null;
		if( pageName != null )
			result = this.pages.get( pageName );

		return result;
	}

	/**
	 * Returns the first non-null page registered after the argument.
	 * We look at all the page names. When we find the wanted page name,
	 * we get the next one with a non-null value and we return it.
	 *
	 * @param suPage the page we want the page after.
	 * @return the page after if it exists, or null otherwise.
	 */
	public AbstractSuPage getNextPage( AbstractSuPage suPage ) {

		AbstractSuPage nextPage = null;
		boolean found = false;
		for( Map.Entry<String,AbstractSuPage> entry : this.pages.entrySet()) {

			String pageName = entry.getKey();
			if( found ) {
				nextPage = entry.getValue();
				break;
			}

			if( pageName.equals( suPage.getName()))
				found = true;
		}

		return nextPage;
	}

	/**
	 * Removes all the registered pages except the version page whose name is given in argument.
	 * @param versionPageName
	 */
	public void clearAllPagesExcept( String versionPageName ) {
		AbstractSuPage versionPage = this.pages.get( versionPageName );
		this.pages.clear();

		if( versionPage != null )
			this.pages.put( versionPageName, versionPage );
	}
}
