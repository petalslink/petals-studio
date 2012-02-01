/****************************************************************************
 * 
 * Copyright (c) 2009-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.explorer;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import com.ebmwebsourcing.petals.services.explorer.model.EndpointBean;
import com.ebmwebsourcing.petals.services.explorer.model.ServiceUnitBean;
import com.ebmwebsourcing.petals.services.explorer.sources.CurrentWorkspaceSource;
import com.ebmwebsourcing.petals.services.explorer.sources.EndpointSource;
import com.ebmwebsourcing.petals.services.explorer.sources.ExternalWorkspaceSource;
import com.ebmwebsourcing.petals.services.explorer.sources.SaDirectorySource;

/**
 * Sort elements in the Petals service explorer.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class CommonSorter extends ViewerSorter {

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ViewerComparator
	 * #compare(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare( Viewer viewer, Object e1, Object e2 ) {

		if( e1 instanceof EndpointSource && e2 instanceof EndpointSource ) {
			SourceType st1 = getSourceType( e1 );
			SourceType st2 = getSourceType( e2 );
			return st1.compareTo( st2 );
		}

		if( e1 instanceof ServiceUnitBean && e2 instanceof EndpointBean )
			return -1;

		if( e2 instanceof ServiceUnitBean && e1 instanceof EndpointBean )
			return 1;

		return super.compare( viewer, e1, e2 );
	}


	/**
	 * @param o
	 * @return
	 */
	private SourceType getSourceType( Object o ) {

		if( o.getClass().equals( CurrentWorkspaceSource.class ))
			return SourceType.currentWorkspace;
		if( o.getClass().equals( ExternalWorkspaceSource.class ))
			return SourceType.externalWorkspace;
		if( o.getClass().equals( SaDirectorySource.class ))
			return SourceType.saDirectory;

		// FIXME: add server

		return SourceType.none;
	}


	private enum SourceType {
		currentWorkspace, externalWorkspace, saDirectory, server, none
	}
}
