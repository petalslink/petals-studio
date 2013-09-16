/******************************************************************************
 * Copyright (c) 2008-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
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
