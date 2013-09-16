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
