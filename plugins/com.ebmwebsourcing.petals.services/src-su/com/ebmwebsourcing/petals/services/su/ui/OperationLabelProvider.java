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
 
package com.ebmwebsourcing.petals.services.su.ui;

import javax.xml.namespace.QName;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Display;

/**
 * A label provider used to display service operations in a viewer.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class OperationLabelProvider extends LabelProvider implements IStyledLabelProvider {

	private final Styler nameStyler;


	/**
	 * Constructor.
	 */
	public OperationLabelProvider() {
		this.nameStyler = new Styler() {
			@Override
			public void applyStyles( TextStyle textStyle ) {
				textStyle.foreground = Display.getDefault().getSystemColor( SWT.COLOR_DARK_GREEN );
			}
		};
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider
	 * #getStyledText(java.lang.Object)
	 */
	public StyledString getStyledText( Object element ) {

		StyledString styledString = new StyledString( "" );
		if( element instanceof QName ) {
			styledString.append( ((QName) element).getLocalPart(), this.nameStyler );
			styledString.append( " - " + ((QName) element).getNamespaceURI());
		}

		return styledString;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider
	 * #getText(java.lang.Object)
	 */
	@Override
	public String getText( Object element ) {
		return getStyledText( element ).getString();
	}
}
