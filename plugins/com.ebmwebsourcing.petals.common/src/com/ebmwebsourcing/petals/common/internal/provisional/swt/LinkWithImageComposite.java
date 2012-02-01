/******************************************************************************
 * Copyright (c) 2011-2012, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;

/**
 * A composite with a single link and a label to display an image.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class LinkWithImageComposite extends Composite {

	private final Label label;
	private final Link link;


	/**
	 * Constructor.
	 * <p>
	 * The label and the link are created, but no property or layout data is set.
	 * </p>
	 *
	 * @param parent the parent
	 */
	public LinkWithImageComposite( Composite parent ) {
		super( parent, SWT.NONE );

		GridLayout layout = new GridLayout( 2, false );
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		setLayout( layout );

		this.label = new Label( this, SWT.NONE );
		this.link = new Link( this, SWT.NONE );
	}


	/**
	 * @return the label
	 */
	public Label getLabel() {
		return this.label;
	}


	/**
	 * @return the link
	 */
	public Link getLink() {
		return this.link;
	}
}
