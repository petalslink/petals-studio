/****************************************************************************
 * 
 * Copyright (c) 2011-2012, EBM WebSourcing
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
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.VerticalRuler;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.ebmwebsourcing.petals.common.xpath.internal.provisional.configuration.ColorManager;
import com.ebmwebsourcing.petals.common.xpath.internal.provisional.configuration.XPathSourceViewerConfiguration;
import com.ebmwebsourcing.petals.services.eip.designer.edit.commands.EipConnectionSetAttributeCommand;
import com.ebmwebsourcing.petals.services.eip.designer.edit.parts.EipConnectionEditPart;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipConnection;

/**
 * The section to define the condition associated with an EIP connection.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ConnectionXPathConditionSection extends AbstractPropertySection implements PropertyChangeListener {

	private EipConnection connection;
	private boolean enableListener;

	private Text nameText;
	private StyledText expressionText;


	/**
	 * Constructor.
	 */
	public ConnectionXPathConditionSection() {
		// nothing
	}


	/*
	 * (non-Jsdoc)
	 * @see java.beans.PropertyChangeListener
	 * #propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange( PropertyChangeEvent evt ) {

		if( EipConnection.PROPERTY_CONDITION_EXPRESSION.equals( evt.getPropertyName())
					|| EipConnection.PROPERTY_CONDITION_NAME.equals( evt.getPropertyName()))
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
		container.setLayout( new GridLayout( 2, false ));

		// Add the text sections
		CLabel label = getWidgetFactory().createCLabel( container, "Condition alias:" );
		label.setToolTipText( "A readable name to describe the XPath condition" );

		this.nameText = getWidgetFactory().createText( container, "" );
		this.nameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.nameText.addModifyListener( new ModifyListener() {

			public void modifyText( ModifyEvent e ) {
				if( ConnectionXPathConditionSection.this.enableListener ) {
					EipConnectionSetAttributeCommand cmd =
						new EipConnectionSetAttributeCommand( EipConnection.PROPERTY_CONDITION_NAME );

					cmd.setEipConnection( ConnectionXPathConditionSection.this.connection );
					cmd.setNewValue( ConnectionXPathConditionSection.this.nameText.getText());
					executeCommand( cmd );
				}
			}
		});

		label = getWidgetFactory().createCLabel( container, "XPath condition:" );
		label.setToolTipText( "A XPath expression to test against the received messages" );
		label.setLayoutData( new GridData( SWT.DEFAULT, SWT.TOP, false, true ));

		Composite editor = getWidgetFactory().createComposite( container, SWT.BORDER );
		editor.setLayout( new FillLayout ());
		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.heightHint = 60;
		editor.setLayoutData( layoutData );

		int style = SWT.V_SCROLL | SWT.MULTI | SWT.BORDER;
		final ISourceViewer viewer = new SourceViewer( editor, new VerticalRuler( 0 ), style );
		ColorManager cManager = new ColorManager ();
		viewer.configure( new XPathSourceViewerConfiguration( cManager ));

		viewer.getTextWidget().setLayoutData( new GridData( GridData.FILL_BOTH ));
		IDocument document = new Document( "" );
		viewer.setDocument( document );

		this.expressionText = viewer.getTextWidget();
		this.expressionText.addModifyListener( new ModifyListener() {

			public void modifyText( ModifyEvent e ) {
				if( ConnectionXPathConditionSection.this.enableListener ) {
					EipConnectionSetAttributeCommand cmd =
						new EipConnectionSetAttributeCommand( EipConnection.PROPERTY_CONDITION_EXPRESSION );

					cmd.setEipConnection( ConnectionXPathConditionSection.this.connection );
					cmd.setNewValue( ConnectionXPathConditionSection.this.expressionText.getText());
					executeCommand( cmd );
				}
			}
		});
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
		if( this.nameText != null
					&& ! this.nameText.isDisposed()
					&& this.connection != null ) {

			// Save the caret position
			int caret = -1;
			boolean nameFocus = false, expFocus = false;
			if( this.nameText.isFocusControl()) {
				nameFocus = true;
				caret = this.nameText.getCaretPosition();

			} else if( this.expressionText.isFocusControl()) {
				expFocus = true;
				caret = this.expressionText.getCaretOffset();
			}

			// Update values
			this.nameText.setText( this.connection.getConditionName() != null ? this.connection.getConditionName() : "" );
			this.expressionText.setText(
						this.connection.getConditionExpression() != null
						? this.connection.getConditionExpression() : "" );

			// Restore the caret
			if( nameFocus )
				this.nameText.setSelection( caret );
			else if( expFocus )
				this.expressionText.setSelection( caret );
		}

		this.enableListener = true;
	}
}
