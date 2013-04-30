/****************************************************************************
 * 
 * Copyright (c) 2011-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.eip.designer.tabbedproperties;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.NamespaceUtils;
import com.ebmwebsourcing.petals.services.eip.designer.edit.commands.EipConnectionSetAttributeCommand;
import com.ebmwebsourcing.petals.services.eip.designer.edit.parts.EipConnectionEditPart;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipConnection;

/**
 * The section to define the condition associated with an EIP connection.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ConnectionOperationConditionSection extends AbstractPropertySection implements PropertyChangeListener {

	private EipConnection connection;
	private boolean enableListener;
	private Text opNameText, opNsText;


	/**
	 * Constructor.
	 */
	public ConnectionOperationConditionSection() {
		// nothing
	}


	/*
	 * (non-Jsdoc)
	 * @see java.beans.PropertyChangeListener
	 * #propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange( PropertyChangeEvent evt ) {

		if( EipConnection.PROPERTY_CONDITION_EXPRESSION.equals( evt.getPropertyName()))
			refresh();
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
		Composite container = getWidgetFactory().createPlainComposite( parent, SWT.NONE );
		container.setLayout( new GridLayout( 4, false ));

		// Add the text sections
		CLabel label = getWidgetFactory().createCLabel( container, "Operation Name:" );
		label.setToolTipText( "Define the local part of the expected operation" );
		this.opNameText = getWidgetFactory().createText( container, "" );
		this.opNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		label = getWidgetFactory().createCLabel( container, "Operation Namespace:" );
		label.setToolTipText( "Define the name space URI of the expected operation" );
		this.opNsText = getWidgetFactory().createText( container, "" );
		this.opNsText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		ModifyListener opModifyListener = new ModifyListener() {

			public void modifyText( ModifyEvent e ) {
				if( ConnectionOperationConditionSection.this.enableListener ) {
					EipConnectionSetAttributeCommand cmd =
						new EipConnectionSetAttributeCommand( EipConnection.PROPERTY_CONDITION_EXPRESSION );

					cmd.setEipConnection( ConnectionOperationConditionSection.this.connection );
					cmd.setNewValue( "{"
								+ ConnectionOperationConditionSection.this.opNsText.getText().trim()
								+ "}"
								+ ConnectionOperationConditionSection.this.opNameText.getText().trim());

					executeCommand( cmd );
				}
			}
		};

		this.opNsText.addModifyListener( opModifyListener );
		this.opNameText.addModifyListener( opModifyListener );
	}


	/**
	 * Executes a command on top of the EIP editor's command stack.
	 * @param command
	 */
	private void executeCommand( Command command ) {

		IEditorPart part = getPart().getSite().getPage().getActiveEditor();
		CommandStack commandStack = (CommandStack) part.getAdapter( CommandStack.class );
		if( commandStack != null )
			commandStack.execute( command );
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
		if( this.connection != null )
			this.connection.removePropertyChangeListener( this );

		if( selection instanceof IStructuredSelection ) {
			Object input = ((IStructuredSelection) selection).getFirstElement();
			if( input instanceof EipConnectionEditPart )
				this.connection = (EipConnection) ((EipConnectionEditPart) input).getModel();
		}

		// Listen to changes in the new model
		if( this.connection != null )
			this.connection.addPropertyChangeListener( this );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection
	 * #refresh()
	 */
	@Override
	public void refresh() {

		this.enableListener = false;
		if( this.opNsText != null
					&& ! this.opNsText.isDisposed()
					&& this.connection != null ) {

			// Save the caret position
			Text focusText = null;
			if( this.opNameText.isFocusControl())
				focusText = this.opNameText;
			else if( this.opNsText.isFocusControl())
				focusText = this.opNsText;

			int caret = focusText != null ? focusText.getCaretPosition() : -1;

			// Update values
			String local = null, uri = null;
			if( NamespaceUtils.isShortenNamespace( this.connection.getConditionExpression())) {
				local = NamespaceUtils.removeNamespaceElements( this.connection.getConditionExpression());
				uri = NamespaceUtils.extractNamespaceUri( this.connection.getConditionExpression());

			} else if( this.connection.getConditionExpression().startsWith( "{}" )) {
				local = this.connection.getConditionExpression().substring( 2 );
				uri = "";

			} else if( this.connection.getConditionExpression().startsWith( "{" )
						&& this.connection.getConditionExpression().endsWith( "}" )) {
				local = "";
				uri = this.connection.getConditionExpression().substring( 1 );
				uri = uri.substring( 0, uri.length() - 1 );
			}

			this.opNameText.setText( local != null ? local : "" );
			this.opNsText.setText( uri != null ? uri : "" );

			// Restore the caret
			if( focusText != null )
				focusText.setSelection( caret );
		}

		this.enableListener = true;
	}
}
