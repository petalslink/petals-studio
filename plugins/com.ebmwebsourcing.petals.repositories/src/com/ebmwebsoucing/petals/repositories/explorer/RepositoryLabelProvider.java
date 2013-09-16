/******************************************************************************
 * Copyright (c) 2010-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsoucing.petals.repositories.explorer;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

import com.ebmwebsoucing.petals.repositories.explorer.model.Repository;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class RepositoryLabelProvider extends LabelProvider
implements ICommonLabelProvider {

	/**
	 * Constructor.
	 */
	public RepositoryLabelProvider() {

		//		ImageDescriptor desc;
		//		try {
		//			desc = PetalsServicesPlugin.getImageDescriptor( "icons/obj16/Endpoint_3.gif" );
		//			if( desc != null )
		//				this.edptImg = desc.createImage();
		//
		//		} catch (Exception e) {
		//			e.printStackTrace();
		//		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.navigator.IDescriptionProvider
	 * #getDescription(java.lang.Object)
	 */
	public String getDescription( Object element ) {

		String desc = null;
		if( element instanceof Repository )
			desc = ((Repository) element).getDescription();
		else if( element instanceof String )
			desc = (String) element;

		return desc;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider
	 * #getText(java.lang.Object)
	 */
	@Override
	public String getText( Object element ) {

		String label = "";
		if( element instanceof Repository )
			label = ((Repository) element).getName();
		else if( element instanceof String )
			label = (String) element;

		return label;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.BaseLabelProvider#dispose()
	 */
	@Override
	public void dispose() {

		try {
			//			if( this.serverEdptImg != null )
			//				this.serverEdptImg.dispose();

		} catch (Exception e) {
			e.printStackTrace();
		}

		super.dispose();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider
	 * #getImage(java.lang.Object)
	 */
	@Override
	public Image getImage( Object element ) {

		Image img = null;
		if( element instanceof Repository )
			img = ((Repository) element).getImage();

		return img;
	}


	public void init( ICommonContentExtensionSite aConfig ) {
		// TODO Auto-generated method stub

	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.navigator.IMementoAware
	 * #restoreState(org.eclipse.ui.IMemento)
	 */
	public void restoreState( IMemento aMemento ) {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.navigator.IMementoAware
	 * #saveState(org.eclipse.ui.IMemento)
	 */
	public void saveState( IMemento aMemento ) {
		// nothing
	}
}
