/******************************************************************************
 * Copyright (c) 2011, EBM WebSourcing
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
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;

/**
 * A wrapper for a {@link Text} with a phantom hint for the user.
 * <p>
 * Notice that {@link Text} cannot be sub-classed. This is why it is wrapped in a composite.
 * </p>
 * <p>
 * Be careful, some methods were not overridden and point to the composite and not the text.
 * This class will probably be completed depending on the developer needs (e.g. support a new kind of listener).
 * </p>
 *
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PhantomText extends Composite {

	private final Text text;
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

		this.text = new Text( this, style );
		this.text.setLayoutData( new GridData( GridData.FILL_BOTH ));

		// Focus listener
		this.text.addFocusListener( new FocusListener() {

			public void focusLost( FocusEvent e ) {
				PhantomText.this.updateEnabled = false;
				if( PhantomText.this.value == null || PhantomText.this.value.length() == 0 ) {
					PhantomText.this.text.setText( PhantomText.this.defaultValue );
					PhantomText.this.text.setForeground( PhantomText.this.text.getDisplay().getSystemColor( SWT.COLOR_TITLE_INACTIVE_FOREGROUND ));
				} else {
					PhantomText.this.text.setForeground( PhantomText.this.text.getDisplay().getSystemColor( SWT.COLOR_TITLE_FOREGROUND ));
				}
			}

			public void focusGained( FocusEvent e ) {
				PhantomText.this.text.setText( PhantomText.this.value == null ? "" : PhantomText.this.value );
				PhantomText.this.text.setForeground( PhantomText.this.text.getDisplay().getSystemColor( SWT.COLOR_TITLE_FOREGROUND ));
				PhantomText.this.updateEnabled = true;
			}
		});

		// Modify listener
		this.text.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				if( PhantomText.this.updateEnabled )
					PhantomText.this.value = ((Text) e.widget).getText().trim();
			}
		});
	}


	/**
	 * Sets the given text in the widget.
	 * @param string the string to set (can be null)
	 */
	public void setTextValue( String string ) {
		this.value = string;
		this.text.setText( string == null ? "" : string );
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
	public Text getTextWidget() {
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
}
