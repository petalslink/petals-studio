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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;

/**
 * A composite with a single text field and a button.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ListWithButtonComposite extends Composite {

	private final Button addButton, removeButton;
	private final TableViewer viewer;
	private final List<Listener> modificationListeners = new ArrayList<Listener> ();

	/**
	 * Constructor.
	 * <p>
	 * The list and the button are created, but no property or layout data is set.
	 * </p>
	 *
	 * @param parent the parent
	 */
	public ListWithButtonComposite( Composite parent ) {

		super( parent, SWT.NONE );
		GridLayout layout = new GridLayout( 2, false );
		layout.marginWidth = layout.marginHeight = 0;
		setLayout( layout );

		Table table = new Table( this, SWT.MULTI | SWT.BORDER );
		table.setLayoutData( new GridData( GridData.FILL_BOTH ));

		this.viewer = new TableViewer( table );
		this.viewer.setContentProvider( new ArrayContentProvider ());
		this.viewer.setLabelProvider( new LabelProvider () {
			@Override
			public String getText( Object element ) {
				if( element instanceof File )
					return ((File) element).getAbsolutePath();
				return super.getText( element );
			}
		});

		Composite buttons = new Composite( this, SWT.NONE );
		layout = new GridLayout ();
		layout.marginWidth = layout.marginHeight = 0;
		buttons.setLayout( layout );
		buttons.setLayoutData( new GridData( GridData.VERTICAL_ALIGN_BEGINNING ));


		// ADD button
		this.addButton = new Button( buttons, SWT.PUSH );
		this.addButton.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));


		// REMOVE button
		this.removeButton = new Button( buttons, SWT.PUSH );
		this.removeButton.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
	}


	/**
	 * @return the addButton
	 */
	public Button getAddButton() {
		return this.addButton;
	}


	/**
	 * @return the removeButton
	 */
	public Button getRemoveButton() {
		return this.removeButton;
	}


	/**
	 * @return the viewer
	 */
	public TableViewer getViewer() {
		return this.viewer;
	}


	/**
	 * Adds a modification listener.
	 * @param listener a listener
	 */
	public void addModificationListener( Listener listener ) {
		this.modificationListeners.add( listener );
	}


	/**
	 * Removes a modification listener.
	 * @param listener a listener
	 */
	public void removeModificationListener( Listener listener ) {
		this.modificationListeners.remove( listener );
	}


	/**
	 * Notifies the listeners that an object was added or removed from the table.
	 */
	public void notifyListeners() {

		Event event = new Event();
		event.widget = this.viewer.getTable();

		for( Listener l : this.modificationListeners )
			l.handleEvent( event );
	}
}
