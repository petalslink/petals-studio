/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM Corporation - initial API and implementation
 *   Sebastian Davids <sdavids@gmx.de> - Fix for bug 19346 - Dialog font should be activated and used by other components.
 *   Vincent Zurczak <vincent.zurczak@petalslink.com> - Fork to support styled cell label providers.
 *   Vincent Zurczak <vincent.zurczak@petalslink.com> - Added type parameters.
 *******************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.dialogs.FilteredList;
import org.eclipse.ui.progress.WorkbenchJob;

/**
 * An alternative to {@link FilteredList} supporting styled cell label providers.
 * @author Vincent Zurczak - EBM WebSourcing
 */
class StyledFilteredList extends Composite {

	private final DelegatingStyledCellLabelProvider labelProvider;
	private final TableViewer viewer;

	private Object[] elements = new Object[ 0 ];
	private final List<Object> filteredElements = new ArrayList<Object> ();
	private final List<Object> selectedElements = new ArrayList<Object> ();

	private boolean ignoreCase = true;
	private String filter = ""; //$NON-NLS-1$

	private Pattern filterPattern;
	private TableUpdateJob fUpdateJob;


	/**
	 * Constructs a new filtered list.
	 * @param parent
	 * @param style
	 * @param labelProvider
	 */
	public StyledFilteredList( Composite parent, int style, IStyledLabelProvider labelProvider ) {

		super( parent, SWT.NONE );
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		setLayout( layout );

		Table table = new Table( this, style );
		table.setLayoutData(new GridData( GridData.FILL_BOTH ));
		table.addDisposeListener( new DisposeListener() {
			public void widgetDisposed( DisposeEvent e ) {
				StyledFilteredList.this.labelProvider.dispose();
				if( StyledFilteredList.this.fUpdateJob != null )
					StyledFilteredList.this.fUpdateJob.cancel();
			}
		});

		this.viewer = new TableViewer( table );
		this.viewer.setContentProvider( new ArrayContentProvider());
		this.labelProvider = new DelegatingStyledCellLabelProvider( labelProvider );
		this.viewer.setLabelProvider( this.labelProvider );

		this.viewer.addSelectionChangedListener( new ISelectionChangedListener() {
			public void selectionChanged( SelectionChangedEvent event ) {
				StyledFilteredList.this.selectedElements.clear();
				Object[] sel = ((IStructuredSelection) event.getSelection()).toArray();
				StyledFilteredList.this.selectedElements.addAll( Arrays.asList( sel ));
			}
		});
	}


	/**
	 * @param elements
	 */
	public void setElements( Object[] elements ) {

		if( elements == null )
			this.elements = new Object[ 0 ];
		else
			this.elements = elements;

		updateList();
	}


	/**
	 * Updates the filtering pattern.
	 */
	private void updateMatchingPattern() {

		String _filter = this.filter != null ? this.filter : "";
		String regex = _filter.replaceAll( "\\*", "(\\.)*" );
		if( this.ignoreCase )
			this.filterPattern = Pattern.compile( regex, Pattern.CASE_INSENSITIVE );
		else
			this.filterPattern = Pattern.compile( regex );

		updateList();
	}


	/**
	 * @return true if the list of filtered elements is empty
	 */
	public boolean isEmpty() {
		return this.filteredElements.isEmpty();
	}


	/**
	 * Updates the list of elements to display.
	 */
	private void updateList() {

		// FIXME: should we use a viewer filter for this?
		// Performances might be better.
		this.filteredElements.clear();
		IStyledLabelProvider p = this.labelProvider.getStyledStringProvider();
		for( Object o : this.elements ) {
			if( this.filterPattern.matcher( p.getStyledText( o ).getString()).find())
				this.filteredElements.add( o );
		}

		if( this.fUpdateJob != null )
			this.fUpdateJob.cancel();

		this.fUpdateJob = new TableUpdateJob();
		this.fUpdateJob.schedule();
	}


	/**
	 * @param selection
	 */
	public void setSelection( Object[] selection ) {

		this.selectedElements.clear();
		if( selection != null )
			this.selectedElements.addAll( Arrays.asList( selection ));
	}


	/**
	 * @return
	 */
	public Object[] getSelection() {
		return this.selectedElements.toArray();
	}


	/**
	 * @param selectionListener
	 */
	public void addSelectionListener( SelectionListener selectionListener ) {
		this.viewer.getTable().addSelectionListener( selectionListener );
	}


	/**
	 * @param selectionListener
	 */
	public void removeSelectionListener( SelectionListener selectionListener ) {
		this.viewer.getTable().removeSelectionListener( selectionListener );
	}


	/**
	 * A job to update the viewer content.
	 */
	private class TableUpdateJob extends WorkbenchJob {

		/**
		 * Create a new instance of a job used to update the table viewer.
		 */
		public TableUpdateJob() {
			super( "Updating the displayed elements..." );
			setSystem( true );
		}


		/*
		 * (non-Javadoc)
		 * @see org.eclipse.ui.progress.UIJob
		 * #runInUIThread(org.eclipse.core.runtime.IProgressMonitor)
		 */
		@Override
		public IStatus runInUIThread( IProgressMonitor monitor ) {

			if( StyledFilteredList.this.viewer.getTable().isDisposed())
				return Status.CANCEL_STATUS;

			// Refresh the viewer
			StyledFilteredList.this.viewer.setInput( StyledFilteredList.this.filteredElements );
			StyledFilteredList.this.viewer.refresh();

			// Restore the selected elements
			StyledFilteredList.this.viewer.setSelection( new StructuredSelection(
						StyledFilteredList.this.selectedElements ));

			return Status.OK_STATUS;
		}
	}


	/*
	 * SETTERS & GETTERS
	 */

	/**
	 * @return the ignoreCase
	 */
	public boolean isIgnoreCase() {
		return this.ignoreCase;
	}


	/**
	 * @param ignoreCase the ignoreCase to set
	 */
	public void setIgnoreCase( boolean ignoreCase ) {
		this.ignoreCase = ignoreCase;
		updateMatchingPattern();
	}


	/**
	 * @return the filter
	 */
	public String getFilter() {
		return this.filter;
	}


	/**
	 * @param filter the filter to set
	 */
	public void setFilter( String filter ) {
		this.filter = filter;
		updateMatchingPattern();
	}
}
