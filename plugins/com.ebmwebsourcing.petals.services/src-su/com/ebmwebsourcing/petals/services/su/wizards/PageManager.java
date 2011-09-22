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

import com.ebmwebsourcing.petals.services.su.extensions.RegisteredContributors;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.XsdNamespaceStore;
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
	 * Retrieves <b>every</b> information filled in by the user into the wizard.
	 * Create a WizardData object and ask to every page to register its information in it.
	 *
	 * @return a bean containing all the information filled in by the user in the wizard.
	 */
	public EclipseSuBean retrieveWizardData() {
		EclipseSuBean result = new EclipseSuBean();

		// Set SU type
		String suType = getPage( "ChoicePage" ).getSuType();
		result.setSuType( suType );

		// Component name
		String componentName = RegisteredContributors.getInstance().getComponentName( suType );
		result.setComponentName( componentName );

		// Define if it is a BC or not
		boolean isBC = RegisteredContributors.getInstance().isBc( suType );
		result.setBc( isBC );

		// Get the contributor id.
		String contributorId = RegisteredContributors.getInstance().getPluginId( suType );
		result.setContributorId( contributorId );

		// For every stored page, ask it to fill in the bean.
		// Note: be careful, pages with only non-visible elements cannot be initialized.
		// Example: BPEL. No Component page, and the 'bpel-process' mark-up cannot be set from a WizardConfiguration
		// Solution: override the fillInData method of a wizard page
		for( AbstractSuPage page : this.pages.values()) {
			if( page != null )
				page.fillInData( result );
		}

		// Get registered name spaces in last position
		// QName values can register additional name spaces when #fillInData is called
		Map<String, String> namespaces = XsdNamespaceStore.getNamespaceStore().getNamespaces();
		if( namespaces != null )
			result.addNamespaces( namespaces );

		return result;
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

		boolean found = false;
		for( Map.Entry<String,AbstractSuPage> entry : this.pages.entrySet()) {

			String pageName = entry.getKey();
			AbstractSuPage nextPage = entry.getValue();

			if( found
						&& nextPage != null
						&& nextPage.displayPage()) {
				AbstractSuPage page = this.pages.get( pageName );
				page.reloadDataFromConfiguration();
				return page;
			}

			if( pageName.equals( suPage.getName()))
				found = true;
		}

		return null;
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
