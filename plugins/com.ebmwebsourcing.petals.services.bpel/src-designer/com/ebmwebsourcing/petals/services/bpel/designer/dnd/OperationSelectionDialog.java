/******************************************************************************
 * Copyright (c) 2009-2011, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/
package com.ebmwebsourcing.petals.services.bpel.designer.dnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.eclipse.bpel.ui.details.providers.ModelTreeContentProvider;
import org.eclipse.bpel.ui.details.providers.ModelTreeLabelProvider;
import org.eclipse.bpel.ui.details.tree.MessageTypeTreeNode;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.wsdl.Input;
import org.eclipse.wst.wsdl.Operation;
import org.eclipse.wst.wsdl.Output;
import org.eclipse.wst.wsdl.PortType;

/**
 * A dialog to select the operation to invoke on the DnD of a Petals service.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class OperationSelectionDialog extends TitleAreaDialog {

	private QName selectedOperation;
	private final PortType portType;


	/**
	 * Constructor.
	 * @param parentShell
	 * @param portType
	 */
	public OperationSelectionDialog( Shell parentShell, PortType portType ) {
		super( parentShell );
		this.portType = portType;
		setShellStyle( SWT.PRIMARY_MODAL | SWT.DIALOG_TRIM | SWT.RESIZE );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog
	 * #createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea( Composite parent ) {

		Composite container = (Composite) super.createDialogArea( parent );

		setTitle( "Operation Selection" );
		getShell().setText( "Operation Selection" );
		setMessage( "Select the operation to invoke." );

		Composite comp = new Composite( container, SWT.NONE );
		GridLayout layout = new GridLayout( 2, true );
		layout.marginHeight = 15;
		layout.marginWidth = 15;
		comp.setLayout( layout );
		comp.setLayoutData( new GridData( GridData.FILL_BOTH ));


		// The list to select the operation
		Composite subComp = new Composite( comp, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		subComp.setLayout( layout );
		subComp.setLayoutData( new GridData( GridData.FILL_BOTH ));

		Label l = new Label( subComp, SWT.NONE );
		l.setText( "Operation:" );
		GridData layoutData =  new GridData( SWT.DEFAULT, SWT.TOP, false, true );
		layoutData.verticalIndent = 4;
		l.setLayoutData( layoutData );

		final Combo opCombo = new Combo( subComp, SWT.BORDER | SWT.READ_ONLY | SWT.DROP_DOWN );
		opCombo.setLayoutData( new GridData( SWT.FILL, SWT.TOP, true, true ));
		final Map<String,Object> opNameToOp = new HashMap<String,Object> ();
		for( Object o : this.portType.getOperations()) {
			String name = ((Operation) o).getName();
			opCombo.add( name );
			opNameToOp.put( name, o );
		}


		// The viewer for a quick overview of the operation
		final TreeViewer viewer = new TreeViewer( comp, SWT.BORDER );
		viewer.getTree().setLayoutData( new GridData( GridData.FILL_BOTH ));
		viewer.setLabelProvider( new ModelTreeLabelProvider ());

		viewer.setContentProvider( new ModelTreeContentProvider( true ) {
			@Override
			public Object[] primGetElements( Object inputElement ) {

				Operation op = (Operation) inputElement;
				ArrayList<Object> list = new ArrayList<Object> ();
				if( op.getInput() != null )
					list.add( new MessageTypeTreeNode((Input) op.getInput(), false, false ));

				if( op.getOutput() != null )
					list.add( new MessageTypeTreeNode((Output) op.getOutput(), false, false ));

				return list.toArray();
			}
		});


		// Add a listener
		opCombo.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {

				Operation op = (Operation) opNameToOp.get( opCombo.getText());
				OperationSelectionDialog.this.selectedOperation = new QName(
							OperationSelectionDialog.this.portType.getQName().getNamespaceURI(),
							op.getName());

				viewer.setInput( op );
				viewer.refresh();
				viewer.expandAll();
			}
		});

		if( opCombo.getItemCount() > 0 ) {
			opCombo.select( 0 );
			opCombo.notifyListeners( SWT.Selection, new Event());
		}

		return container;
	}


	/**
	 * @return the selected operation
	 */
	public QName getSelectedOperation() {
		return this.selectedOperation;
	}
}