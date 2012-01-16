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

import java.util.concurrent.ConcurrentLinkedQueue;

import javax.xml.namespace.QName;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * A widget for QNames, made up of two {@link Text}s separated by a {@link Label}.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class QNameText extends Composite {

	private final static String DEFAULT_LOCAL_PART = "local part";
	private final static String DEFAULT_NAMESPACE = "http://your.namespace.uri";

	private final PhantomText namespacePhantomText;
	private final Text localPartPhantomText;
	private final Label separatorLabel;
	private final ConcurrentLinkedQueue<ModifyListener> modifyListeners = new ConcurrentLinkedQueue<ModifyListener> ();


	/**
	 * Constructor.
	 * @param parent
	 */
	public QNameText( Composite parent ) {
		super( parent, SWT.BORDER );
		setBackground( getDisplay().getSystemColor( SWT.COLOR_WHITE ));

		GridLayout layout = new GridLayout( 3, false );
		layout.marginHeight = 1;
		layout.marginWidth = 1;
		setLayout( layout );

		// The local part
		this.localPartPhantomText = new Text( this, SWT.SINGLE );
		this.localPartPhantomText.setText( DEFAULT_LOCAL_PART );
		this.localPartPhantomText.setLayoutData( new GridData());
		this.localPartPhantomText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {

				int cpt = ((Text) e.widget).getText().length() + 1;
				GC gc = new GC( QNameText.this );
				gc.setFont( getFont());
				int width = Dialog.convertWidthInCharsToPixels( gc.getFontMetrics(), cpt );
				gc.dispose();

				((GridData) ((Text) e.widget).getLayoutData()).widthHint = width;
				layout();

				for( ModifyListener listener : QNameText.this.modifyListeners )
					listener.modifyText( e );
			}
		});

		// The separator
		this.separatorLabel = new Label( this, SWT.NONE );
		this.separatorLabel.setText( "-" );
		this.separatorLabel.setBackground( getDisplay().getSystemColor( SWT.COLOR_WHITE ));

		// The name space
		this.namespacePhantomText = new PhantomText( this, SWT.SINGLE );
		this.namespacePhantomText.setDefaultValue( DEFAULT_NAMESPACE );
		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.minimumWidth = 60;
		this.namespacePhantomText.setLayoutData( layoutData );

		this.namespacePhantomText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				for( ModifyListener listener : QNameText.this.modifyListeners )
					listener.modifyText( e );
			}
		});

		// Display default values
		setValue( null );
	}


	/**
	 * Sets the local part.
	 * @param localPart the local part (can be null)
	 */
	public void setLocalPart( String localPart ) {
		this.localPartPhantomText.setText( localPart != null ? localPart : "" );
	}


	/**
	 * Sets the name space.
	 * @param namespace the name space (can be null)
	 */
	public void setNamespace( String namespace ) {
		this.namespacePhantomText.setTextValue( namespace );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Control
	 * #setEnabled(boolean)
	 */
	@Override
	public void setEnabled( boolean enabled ) {

		this.localPartPhantomText.setEnabled( enabled );
		this.namespacePhantomText.setEnabled( enabled );
		this.separatorLabel.setEnabled( enabled );

		int colorId = enabled ? SWT.COLOR_WHITE : SWT.COLOR_WIDGET_BACKGROUND;
		Color color = getDisplay().getSystemColor( colorId );
		this.separatorLabel.setBackground( color );
		setBackground( color );

		super.setEnabled( enabled );
	}


	/**
	 * Defines whether the text fields can be edited or not.
	 * @param editable true if they are, false otherwise
	 */
	public void setEditable( boolean editable ) {
		this.localPartPhantomText.setEditable( editable );
		this.namespacePhantomText.setEditable( editable );
	}


	/**
	 * @param string
	 * @see org.eclipse.swt.widgets.Control
	 * #setToolTipText(java.lang.String)
	 */
	@Override
	public void setToolTipText( String string ) {
		this.localPartPhantomText.setToolTipText( string );
		this.separatorLabel.setToolTipText( string );
		this.namespacePhantomText.setToolTipText( string );
	}


	/**
	 * @return the QName described by this widget
	 */
	public QName getValue() {

		QName result;
		String ns = this.namespacePhantomText.getTextValue();
		String name = this.localPartPhantomText.getText();
		if( ns == null || ns.length() == 0 )
			result = new QName( name );
		else
			result = new QName( ns, name );

		return result;
	}


	/**
	 * @param value the QName to display in this widget
	 */
	public void setValue( QName value ) {

		if( value != null ) {
			setLocalPart( value.getLocalPart());
			setNamespace( value.getNamespaceURI());
		} else {
			setLocalPart( null );
			setNamespace( null );
		}
	}


	/**
	 * @return the text widget for the local part
	 */
	public Text getLocalPartText() {
		return this.localPartPhantomText;
	}


	/**
	 * @return the text widget for the name space
	 */
	public Text getNamespaceText() {
		return this.namespacePhantomText.getTextWidget();
	}


	/**
	 * @param modifyListener
	 */
	public void addModifyListener( ModifyListener modifyListener ) {
		this.modifyListeners.add( modifyListener );
	}


	/**
	 * @param modifyListener
	 */
	public void removeModifyListener( ModifyListener modifyListener ) {
		this.modifyListeners.remove( modifyListener );
	}


	/**
	 * @param defaultLocalPart the defaultLocalPart to set
	 */
	public void setDefaultLocalPart( String defaultLocalPart ) {
		this.localPartPhantomText.setText( defaultLocalPart == null ? "" : defaultLocalPart );
	}


	/**
	 * @param defaultNamespace the defaultNamespace to set
	 */
	public void setDefaultNamespace( String defaultNamespace ) {
		this.namespacePhantomText.setDefaultValue( defaultNamespace == null ? "" : defaultNamespace );
	}
}
