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

import java.util.concurrent.ConcurrentLinkedQueue;

import javax.xml.namespace.QName;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;

/**
 * A widget for QNames, made up of two {@link Text}s separated by a {@link Label}.
 * <p>
 * We use {@link StyledText} because some OS draw borders around native widgets like Texts.
 * Styled texts are not native widgets.
 * </p>
 *
 * @author Vincent Zurczak - EBM WebSourcing
 * FIXME: review the model interaction, the widget and the value might be not synchronized correctly
 */
public class QNameText extends Composite {

	private final static String DEFAULT_LOCAL_PART = "local part";
	private final static String DEFAULT_NAMESPACE = "http://your.namespace.uri";

	private final String defaultLocalPart;
	private final PhantomText namespacePhantomText;
	private final StyledText localPartPhantomText;
	private final Label separatorLabel;
	private final ConcurrentLinkedQueue<ModifyListener> modifyListeners = new ConcurrentLinkedQueue<ModifyListener> ();



	/**
	 * Constructor.
	 * @param parent
	 */
	public QNameText( Composite parent ) {
		this( parent, null, null );
	}


	/**
	 * Constructor.
	 * @param parent
	 * @param defaultLocalPart
	 * @param defaultNamespace
	 */
	public QNameText( Composite parent, String defaultLocalPart, String defaultNamespace ) {
		super( parent, SWT.BORDER );
		setBackground( getDisplay().getSystemColor( SWT.COLOR_WHITE ));
		this.defaultLocalPart = defaultLocalPart == null ? DEFAULT_LOCAL_PART : defaultLocalPart;

		GridLayout layout = new GridLayout( 3, false );
		layout.marginHeight = 1;
		layout.marginWidth = 1;
		setLayout( layout );

		// The local part
		this.localPartPhantomText = new StyledText( this, SWT.SINGLE );
		this.localPartPhantomText.setText( DEFAULT_LOCAL_PART );
		this.localPartPhantomText.setLayoutData( new GridData());
		this.localPartPhantomText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {

				int cpt = ((StyledText) e.widget).getText().length() + 1;
				GC gc = new GC( QNameText.this );
				gc.setFont( getFont());
				int width = Dialog.convertWidthInCharsToPixels( gc.getFontMetrics(), cpt );
				gc.dispose();

				((GridData) ((StyledText) e.widget).getLayoutData()).widthHint = width;
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
		this.namespacePhantomText.setDefaultValue( defaultNamespace == null ? DEFAULT_NAMESPACE : defaultNamespace );

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


		// Focus listener
		Listener listener = new Listener() {
			@Override
			public void handleEvent( Event event ) {
				((StyledText) event.widget).selectAll();
			}
		};

		this.localPartPhantomText.addListener( SWT.MouseDown, listener );
		this.localPartPhantomText.addListener( SWT.FocusIn, listener );
		this.namespacePhantomText.getTextWidget().addListener( SWT.MouseDown, listener );
		this.namespacePhantomText.getTextWidget().addListener( SWT.FocusIn, listener );

		// Display default values
		setValue( null );
	}


	/**
	 * Sets the local part.
	 * @param localPart the local part (can be null)
	 */
	public void setLocalPart( String localPart ) {
		this.localPartPhantomText.setText( localPart != null ? localPart : this.defaultLocalPart );
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
		setLocalPartEditable( editable );
		setNamespacePartEditable( editable );
	}


	/**
	 * Sets the local part editable.
	 * @param editable
	 */
	public void setLocalPartEditable( boolean editable ) {
		this.localPartPhantomText.setEditable( editable );

		Color color = getDisplay().getSystemColor( SWT.COLOR_WHITE );
		String tooltip = editable ? null : "This field cannot be edited";
		this.localPartPhantomText.setBackground( color );
		this.localPartPhantomText.setToolTipText( tooltip );
	}


	/**
	 * Sets the namespace part editable.
	 * @param editable
	 */
	public void setNamespacePartEditable( boolean editable ) {
		this.namespacePhantomText.getTextWidget().setEditable( editable );

		Color color = getDisplay().getSystemColor( SWT.COLOR_WHITE );
		String tooltip = editable ? null : "This field cannot be edited";
		this.namespacePhantomText.getTextWidget().setBackground( color );
		this.namespacePhantomText.getTextWidget().setToolTipText( tooltip );
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
		name = this.defaultLocalPart.equals( name ) ? null : name;

		if( StringUtils.isEmpty( name ))
			result = null;
		else if( ns == null || ns.length() == 0 )
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
	public StyledText getLocalPartText() {
		return this.localPartPhantomText;
	}


	/**
	 * @return the text widget for the name space
	 */
	public StyledText getNamespaceText() {
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
}
