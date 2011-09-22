/****************************************************************************
 * 
 * Copyright (c) 2010-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
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
