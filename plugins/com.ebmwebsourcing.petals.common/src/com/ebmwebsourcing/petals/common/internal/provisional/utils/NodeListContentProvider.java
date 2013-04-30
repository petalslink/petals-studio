/****************************************************************************
 * 
 * Copyright (c) 2008-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.w3c.dom.NodeList;

/**
 * A content provider that works with NodeList as input.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class NodeListContentProvider extends ArrayContentProvider {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ArrayContentProvider
	 * #getElements(java.lang.Object)
	 */
	@Override
	public Object[] getElements( Object inputElement ) {

		List<Object> result = new ArrayList<Object> ();
		if( inputElement instanceof NodeList ) {
			NodeList nodes = (NodeList) inputElement;
			for( int i=0; i<nodes.getLength(); i++ )
				result.add( nodes.item( i ));
		}

		Object[] array = new Object[ result.size()];
		return result.toArray( array );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ArrayContentProvider
	 * #dispose()
	 */
	@Override
	public void dispose() {
		//Nothing
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ArrayContentProvider
	 * #inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void inputChanged( Viewer viewer, Object oldInput, Object newInput ) {
		//Nothing
	}
}
