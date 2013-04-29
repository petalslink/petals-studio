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
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;

/**
 * A wrapper for a {@link Text} with a phantom hint for the user.
 * <p>
 * Notice that {@link Text} cannot be sub-classed. This is why it is wrapped in a composite.
 * </p>
 * <p>
 * We use {@link StyledText} because some OS draw borders around native widgets like Texts.
 * Styled texts are not native widgets.
 * </p>
 * <p>
 * Be careful, some methods were not overridden and point to the composite and not the text.
 * This class will probably be completed depending on the developer needs (e.g. support a new kind of listener).
 * </p>
 *
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PhantomText extends Composite {

	private final StyledText text;
	private boolean updateEnabled = false;
	private String value;
	private String defaultValue = "";


	/**
	 * Constructor.
	 * @param parent
	 * @param style
	 */
	public PhantomText( Composite parent, int style ) {

		super( parent, SWT.NONE );
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		setLayout( layout );

		this.text = new StyledText( this, style );
		this.text.setLayoutData( new GridData( GridData.FILL_BOTH ));

		// Focus listener
		this.text.addFocusListener( new FocusListener() {

			@Override
			public void focusLost( FocusEvent e ) {
				PhantomText.this.updateEnabled = false;
				PhantomText.this.text.setText( StringUtils.isEmpty( PhantomText.this.value ) ? PhantomText.this.defaultValue : PhantomText.this.value );
				PhantomText.this.text.setForeground( PhantomText.this.text.getDisplay().getSystemColor( SWT.COLOR_DARK_GRAY ));
			}

			@Override
			public void focusGained( FocusEvent e ) {
				PhantomText.this.text.setText( StringUtils.isEmpty( PhantomText.this.value ) ? "" : PhantomText.this.value );
				PhantomText.this.text.setForeground( PhantomText.this.text.getDisplay().getSystemColor( SWT.COLOR_BLACK ));
				PhantomText.this.updateEnabled = true;
			}
		});

		// Modify listener
		this.text.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				if( PhantomText.this.updateEnabled )
					PhantomText.this.value = ((StyledText) e.widget).getText().trim();
			}
		});
	}


	/**
	 * Sets the given text in the widget.
	 * @param string the string to set (can be null)
	 */
	public void setTextValue( String string ) {
		this.value = string;
		this.text.setText( string == null ? this.defaultValue : string );
		this.text.notifyListeners( SWT.FocusOut, new Event());
	}


	/**
	 * @return the real text value (never null)
	 */
	public String getTextValue() {
		return this.value == null ? "" : this.value;
	}


	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return this.defaultValue;
	}


	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue( String defaultValue ) {
		this.defaultValue = defaultValue;
		this.text.notifyListeners( SWT.FocusOut, new Event());
	}


	/**
	 * @return the text widget
	 */
	public StyledText getTextWidget() {
		return this.text;
	}


	/**
	 * @param listener
	 * @see org.eclipse.swt.widgets.Text
	 * #addModifyListener(org.eclipse.swt.events.ModifyListener)
	 */
	public void addModifyListener( ModifyListener listener ) {
		this.text.addModifyListener( listener );
	}


	/**
	 * @param listener
	 * @see org.eclipse.swt.widgets.Text
	 * #setEditable(boolean)
	 */
	public void setEditable( boolean editable ) {
		this.text.setEditable( editable );
	}


	/**
	 * @param eventType
	 * @param event
	 * @see org.eclipse.swt.widgets.Widget
	 * #notifyListeners(int, org.eclipse.swt.widgets.Event)
	 */
	@Override
	public void notifyListeners( int eventType, Event event ) {
		this.text.notifyListeners( eventType, event );
		super.notifyListeners( eventType, event );
	}


	/**
	 * @param enabled
	 * @see org.eclipse.swt.widgets.Control
	 * #setEnabled(boolean)
	 */
	@Override
	public void setEnabled( boolean enabled ) {
		this.text.setEnabled( enabled );
		super.setEnabled( enabled );
	}


	/**
	 * @param string
	 * @see org.eclipse.swt.widgets.Control
	 * #setToolTipText(java.lang.String)
	 */
	@Override
	public void setToolTipText( String string ) {
		this.text.setToolTipText( string );
		super.setToolTipText( string );
	}


	/**
	 * @param color
	 * @see org.eclipse.swt.widgets.Control#setBackground(org.eclipse.swt.graphics.Color)
	 */
	@Override
	public void setBackground( Color color ) {
		this.text.setBackground( color );
		super.setBackground( color );
	}
}
