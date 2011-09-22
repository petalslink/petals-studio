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

package com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class CacheItem implements Serializable {
	private static final long serialVersionUID = -1590304852558566479L;
	List<HciItem> items;
	Map<String, String> namespaces;


	/**
	 * @return the items
	 */
	public List<HciItem> getItems() {
		return this.items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(List<HciItem> items) {
		this.items = items;
	}
	/**
	 * @return the namespaces
	 */
	public Map<String, String> getNamespaces() {
		return this.namespaces;
	}
	/**
	 * @param namespaces the namespaces to set
	 */
	public void setNamespaces(Map<String, String> namespaces) {
		this.namespaces = namespaces;
	}
}
