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
