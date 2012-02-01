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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * A composite with a single text field and a button.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class TextWithButtonComposite extends Composite {

	private final Button button;
	private final Text text;

	/**
	 * Constructor.
	 * <p>
	 * The text and the button are created, but no property or layout data is set.
	 * </p>
	 *
	 * @param parent the parent
	 */
	public TextWithButtonComposite( Composite parent ) {

		super( parent, SWT.NONE );
		GridLayout layout = new GridLayout( 2, false );
		layout.marginWidth = layout.marginHeight = 0;
		setLayout( layout );

		this.text = new Text( this, SWT.SINGLE | SWT.BORDER );
		this.button = new Button( this, SWT.PUSH );
	}


	/**
	 * @return the button
	 */
	public Button getButton() {
		return this.button;
	}


	/**
	 * @return the text
	 */
	public Text getText() {
		return this.text;
	}
}
