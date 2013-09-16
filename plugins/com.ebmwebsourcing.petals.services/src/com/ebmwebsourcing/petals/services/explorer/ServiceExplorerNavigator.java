/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.explorer;

import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * An extension of the CommonNavigator to register tabbed properties.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ServiceExplorerNavigator extends CommonNavigator implements ITabbedPropertySheetPageContributor {

	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor
	 * #getContributorId()
	 */
	public String getContributorId() {
		return getSite().getId();
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.ui.navigator.CommonNavigator
	 * #getAdapter(java.lang.Class)
	 */
	@Override
	public Object getAdapter( Class adapter ) {

		if( adapter == IPropertySheetPage.class )
			return new TabbedPropertySheetPage( this );

		return super.getAdapter( adapter );
	}
}
