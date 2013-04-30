/****************************************************************************
 * 
 * Copyright (c) 2010-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.common.internal.compare;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.IViewerCreator;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class XmlViewerCreator implements IViewerCreator {

	/**
	 * Constructor.
	 */
	public XmlViewerCreator() {
		// nothing
	}


	/* (non-Javadoc)
	 * @see org.eclipse.compare.IViewerCreator
	 * #createViewer(org.eclipse.swt.widgets.Composite, org.eclipse.compare.CompareConfiguration)
	 */
	public Viewer createViewer( Composite parent, CompareConfiguration config ) {
		return new XmlViewer( parent, config );
	}
}
