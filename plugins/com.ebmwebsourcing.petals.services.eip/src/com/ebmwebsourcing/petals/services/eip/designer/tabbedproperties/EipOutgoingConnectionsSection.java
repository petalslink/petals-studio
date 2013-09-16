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
 
package com.ebmwebsourcing.petals.services.eip.designer.tabbedproperties;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.ebmwebsourcing.petals.services.eip.designer.edit.commands.NodeSwitchCommand;
import com.ebmwebsourcing.petals.services.eip.designer.edit.parts.AbstractNodeEditPart;
import com.ebmwebsourcing.petals.services.eip.designer.edit.parts.EipNodeEditPart;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipConnection;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipNode;
import com.ebmwebsourcing.petals.services.eip.designer.model.Endpoint;

/**
 * The section for EIP's outgoing connections.
 * <p>
 * This section helps to sort connections and define the invocation order.
 * </p>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipOutgoingConnectionsSection extends AbstractPropertySection implements PropertyChangeListener {

	private EipNode eip;
	private TableViewer outgoingConnectionsViewer;


	/**
	 * Constructor.
	 */
	public EipOutgoingConnectionsSection() {
		// nothing
	}


	/*
	 * (non-Jsdoc)
	 * @see java.beans.PropertyChangeListener
	 * #propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange( PropertyChangeEvent evt ) {

		if( EipNode.PROPERTY_OUTGOING_CONNECTION.equals( evt.getPropertyName()))
			refresh();
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection
	 * #shouldUseExtraSpace()
	 */
	@Override
	public boolean shouldUseExtraSpace() {
		return true;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection
	 * #createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	@Override
	public void createControls( Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage ) {

		// Create the container
		super.createControls( parent, aTabbedPropertySheetPage );
		Composite container = getWidgetFactory().createComposite( parent, SWT.NONE );
		GridLayout layout = new GridLayout( 2, false );
		layout.marginTop = 10;
		layout.marginBottom = 8;
		container.setLayout( layout );


		// Add the table
		String[] PROPERTIES = new String[] { "Index", "Connection Target" };

		Table table = getWidgetFactory().createTable( container, SWT.SINGLE | SWT.FULL_SELECTION | SWT.HIDE_SELECTION );
		table.setHeaderVisible( true );
		GridData layoutData = new GridData( GridData.FILL_BOTH );
		layoutData.horizontalIndent = 7;
		table.setLayoutData( layoutData );

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData( new ColumnWeightData( 10, 70, true ));
		tlayout.addColumnData( new ColumnWeightData( 90, 300, true ));
		table.setLayout( tlayout );

		TableColumn column = new TableColumn( table, SWT.CENTER );
		column.setText( PROPERTIES[ 0 ]);
		column = new TableColumn( table, SWT.LEFT );
		column.setText( PROPERTIES[ 1 ]);

		this.outgoingConnectionsViewer = new TableViewer( table );
		this.outgoingConnectionsViewer.setColumnProperties( PROPERTIES );
		this.outgoingConnectionsViewer.setContentProvider( new ArrayContentProvider());
		this.outgoingConnectionsViewer.setLabelProvider( new ITableLabelProvider() {
			public void addListener( ILabelProviderListener listener ) {
				// nothing
			}

			public void dispose() {
				// nothing
			}

			public boolean isLabelProperty( Object element, String property ) {
				return false;
			}

			public void removeListener( ILabelProviderListener listener ) {
				// nothing
			}

			public Image getColumnImage( Object element, int columnIndex ) {
				return null;
			}

			public String getColumnText( Object element, int columnIndex ) {

				StringBuilder sb = new StringBuilder();
				EipConnection conn = (EipConnection) element;

				if( columnIndex == 0 ) {
					int index = -1;
					if( conn.getSource() != null )
						index = conn.getSource().getOutgoingConnections().indexOf( conn );

					if( index != -1 ) {
						sb.append( ++ index );
						sb.append( ". " );
					}

				} else {
					if( conn.getTarget() instanceof EipNode )
						sb.append( conn.getTarget().getServiceName() + " - EIP" );
					else if( conn.getTarget() instanceof Endpoint )
						sb.append( conn.getTarget().getServiceName() + " - Petals Service" );
				}

				return sb.toString();
			}
		});


		// Add the buttons
		Composite buttonsComposite = getWidgetFactory().createComposite( container );
		layout = new GridLayout();
		layout.marginHeight = 0;
		buttonsComposite.setLayout( layout );
		buttonsComposite.setLayoutData( new GridData( GridData.FILL_VERTICAL ));

		final Button upButton = getWidgetFactory().createButton( buttonsComposite, "Up", SWT.PUSH );
		upButton.setToolTipText( "Push up this target node and its connection" );
		layoutData =  new GridData();
		layoutData.widthHint = 100;
		upButton.setLayoutData( layoutData );
		upButton.setEnabled( false );

		final Button downButton = getWidgetFactory().createButton( buttonsComposite, "Down", SWT.PUSH );
		downButton.setToolTipText( "Pull down this target node and its connection" );
		layoutData =  new GridData();
		layoutData.widthHint = 100;
		downButton.setLayoutData( layoutData );
		downButton.setEnabled( false );


		// Add the listeners
		this.outgoingConnectionsViewer.addSelectionChangedListener( new ISelectionChangedListener() {

			public void selectionChanged( SelectionChangedEvent event ) {

				EipConnection conn = (EipConnection) ((IStructuredSelection) event.getSelection()).getFirstElement();
				int size = EipOutgoingConnectionsSection.this.outgoingConnectionsViewer.getTable().getItemCount();
				int pos = conn.getSource().getOutgoingConnections().indexOf( conn );

				upButton.setEnabled( pos > 0 );
				downButton.setEnabled( pos < size-1 );
			}
		});

		upButton.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				moveSelectedConnection( true );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				moveSelectedConnection( true );
			}
		});

		downButton.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				moveSelectedConnection( false );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				moveSelectedConnection( false );
			}
		});
	}


	/**
	 * Moves the connection target in the invocation order.
	 * @param conn
	 * @param up
	 */
	private void moveSelectedConnection( boolean up ) {

		// Get the connections
		EipConnection conn = (EipConnection) ((IStructuredSelection) this.outgoingConnectionsViewer.getSelection()).getFirstElement();
		int index1 = conn.getSource().getOutgoingConnections().indexOf( conn );
		EipConnection conn2;
		if( up )
			conn2 = conn.getSource().getOutgoingConnections().get( index1 - 1 );
		else
			conn2 = conn.getSource().getOutgoingConnections().get( index1 + 1 );

		// Get the target edit parts
		IEditorPart part = getPart().getSite().getPage().getActiveEditor();
		Map<?,?> map = ((GraphicalViewer) part.getAdapter( GraphicalViewer.class )).getEditPartRegistry();
		List<AbstractNodeEditPart> parts = new ArrayList<AbstractNodeEditPart> ();
		for( Map.Entry<?,?> entry : map.entrySet()) {
			if( entry.getKey().equals( conn.getTarget())
						|| entry.getKey().equals( conn2.getTarget())) {

				if( entry.getValue() instanceof AbstractNodeEditPart )
					parts.add((AbstractNodeEditPart) entry.getValue());
			}
		}

		// Create and execute the command
		if( parts.size() == 2 ) {
			NodeSwitchCommand cmd = new NodeSwitchCommand( parts.get( 0 ), parts.get( 1 ));
			CommandStack commandStack = (CommandStack) part.getAdapter( CommandStack.class );
			if( commandStack != null )
				commandStack.execute( cmd );
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection
	 * #setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setInput( IWorkbenchPart part, ISelection selection ) {

		super.setInput( part, selection );

		// Do not listen to model changes from the previous input
		if( this.eip != null )
			this.eip.removePropertyChangeListener( this );

		if( selection instanceof IStructuredSelection ) {
			Object input = ((IStructuredSelection) selection).getFirstElement();
			if( input instanceof EipNodeEditPart )
				this.eip = (EipNode) ((EipNodeEditPart) input).getModel();
		}

		// Listen to changes in the new model
		if( this.eip != null )
			this.eip.addPropertyChangeListener( this );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection
	 * #refresh()
	 */
	@Override
	public void refresh() {

		if( this.outgoingConnectionsViewer != null
					&& ! this.outgoingConnectionsViewer.getTable().isDisposed()
					&& this.eip != null ) {

			this.outgoingConnectionsViewer.setInput( this.eip.getOutgoingConnections());
			this.outgoingConnectionsViewer.refresh();

			// Invoke pack to force the use of the ColumnWeightData
			this.outgoingConnectionsViewer.getTable().pack();
		}
	}
}
