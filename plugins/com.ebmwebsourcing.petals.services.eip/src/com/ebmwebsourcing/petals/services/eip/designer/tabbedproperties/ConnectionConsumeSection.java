/****************************************************************************
 *
 * Copyright (c) 2011, EBM WebSourcing
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
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.DefaultSelectionListener;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.NamespaceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.UriUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.WsdlUtils;
import com.ebmwebsourcing.petals.services.eip.designer.edit.commands.EipConnectionSetAttributeCommand;
import com.ebmwebsourcing.petals.services.eip.designer.edit.parts.EipConnectionEditPart;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipConnection;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipNode;
import com.ebmwebsourcing.petals.services.eip.designer.model.Endpoint;
import com.ebmwebsourcing.petals.services.su.ui.OperationLabelProvider;
import com.ebmwebsourcing.petals.services.utils.ConsumeUtils;

/**
 * The section to define the condition associated with an EIP connection.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ConnectionConsumeSection extends AbstractPropertySection implements PropertyChangeListener {

	private EipConnection connection;
	private boolean enableListener;

	private Button itfButton, srvButton, edptButton;
	private CCombo mepCCombo;
	private Text opNameText, opNsText;

	private final Map<QName,Mep> opNameToMep = new HashMap<QName,Mep> ();
	private ComboViewer operationViewer;


	/**
	 * Constructor.
	 */
	public ConnectionConsumeSection() {
		// nothing
	}


	/*
	 * (non-Jsdoc)
	 * @see java.beans.PropertyChangeListener
	 * #propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange( PropertyChangeEvent evt ) {

		if( EipConnection.PROPERTY_CONSUME_BY_ITF.equals( evt.getPropertyName())
					|| EipConnection.PROPERTY_CONSUME_BY_SRV.equals( evt.getPropertyName())
					|| EipConnection.PROPERTY_CONSUME_BY_EDPT.equals( evt.getPropertyName())
					|| EipConnection.PROPERTY_CONSUMED_MEP.equals( evt.getPropertyName())
					|| EipConnection.PROPERTY_CONSUMED_OPERATION.equals( evt.getPropertyName()))
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

		// Add the buttons
		CLabel label = getWidgetFactory().createCLabel( container, "Resolve the target service by:" );
		label.setToolTipText( "Specify the way the target service will be resolved (implicit or explicit addressing)" );
		GridData layoutData = new GridData( SWT.DEFAULT, SWT.BEGINNING, false, false );
		layoutData.verticalIndent = 2;
		label.setLayoutData( layoutData );

		Composite buttonsContainer = getWidgetFactory().createComposite( container );
		GridLayout layout = new GridLayout();
		layout.marginHeight = 5;
		buttonsContainer.setLayout( layout );

		SelectionListener buttonListener = new DefaultSelectionListener() {
			public void widgetSelected( SelectionEvent e ) {

				if( ConnectionConsumeSection.this.enableListener ) {
					EipConnectionSetAttributeCommand cmd =
						new EipConnectionSetAttributeCommand((String) e.widget.getData());

					cmd.setEipConnection( ConnectionConsumeSection.this.connection );
					cmd.setNewValue(((Button) e.widget).getSelection());
					executeCommand( cmd );
				}
			}
		};

		this.itfButton = getWidgetFactory().createButton( buttonsContainer, "Interface name", SWT.CHECK );
		this.itfButton.setToolTipText( "Find the service by interface name" );
		this.itfButton.setData( EipConnection.PROPERTY_CONSUME_BY_ITF );
		this.itfButton.addSelectionListener( buttonListener );
		this.itfButton.setEnabled( false );

		this.srvButton = getWidgetFactory().createButton( buttonsContainer, "Service name", SWT.CHECK );
		this.srvButton.setToolTipText( "Find the service by service name" );
		this.srvButton.setData( EipConnection.PROPERTY_CONSUME_BY_SRV );
		this.srvButton.addSelectionListener( buttonListener );

		this.edptButton = getWidgetFactory().createButton( buttonsContainer, "End-point name", SWT.CHECK );
		this.edptButton.setToolTipText( "Find the service by end-point name" );
		this.edptButton.setData( EipConnection.PROPERTY_CONSUME_BY_EDPT );
		this.edptButton.addSelectionListener( buttonListener );


		// Add the text sections
		label = getWidgetFactory().createCLabel( container, "Operation helper:" );
		label.setToolTipText( "A helper to select the operation to invoke" );

		CCombo opCombo = getWidgetFactory().createCCombo( container, SWT.READ_ONLY | SWT.DROP_DOWN );
		this.operationViewer = new ComboViewer( opCombo );
		this.operationViewer.getCCombo().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		this.operationViewer.setContentProvider( new ArrayContentProvider());
		this.operationViewer.setLabelProvider( new OperationLabelProvider());
		this.operationViewer.setInput( this.opNameToMep.keySet());

		label = getWidgetFactory().createCLabel( container, "Operation Name:" );
		label.setToolTipText( "Define the local part of the operation to invoke" );
		this.opNameText = getWidgetFactory().createText( container, "", SWT.BORDER );
		this.opNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		label = getWidgetFactory().createCLabel( container, "Operation Namespace:" );
		label.setToolTipText( "Define the name space URI of the operation to invoke" );
		this.opNsText = getWidgetFactory().createText( container, "", SWT.BORDER );
		this.opNsText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		label = getWidgetFactory().createCLabel( container, "Invocation MEP:" );
		label.setToolTipText( "Define the way the service provider and consumer will interact together" );

		this.mepCCombo = getWidgetFactory().createCCombo( container, SWT.READ_ONLY | SWT.DROP_DOWN );
		layoutData = new GridData();
		layoutData.widthHint = 300;
		this.mepCCombo.setLayoutData( layoutData );
		for( Mep mep : Mep.values())
			this.mepCCombo.add( mep.toString());


		// The listeners
		this.operationViewer.addSelectionChangedListener( new ISelectionChangedListener() {
			public void selectionChanged( SelectionChangedEvent event ) {

				// This listener will directly update the model
				ISelection selection = ConnectionConsumeSection.this.operationViewer.getSelection();
				if( ConnectionConsumeSection.this.enableListener
							&& ! selection.isEmpty()) {

					Object o = ((IStructuredSelection) selection).getFirstElement();
					CompoundCommand cmd = new CompoundCommand();

					EipConnectionSetAttributeCommand cmd1 =
						new EipConnectionSetAttributeCommand( EipConnection.PROPERTY_CONSUMED_OPERATION );

					cmd1.setEipConnection( ConnectionConsumeSection.this.connection );
					cmd1.setNewValue(((QName) o).toString());
					cmd.add( cmd1 );

					EipConnectionSetAttributeCommand cmd2 =
						new EipConnectionSetAttributeCommand( EipConnection.PROPERTY_CONSUMED_MEP );

					cmd2.setEipConnection( ConnectionConsumeSection.this.connection );
					Mep invocationMep = ConnectionConsumeSection.this.opNameToMep.get( o );
					cmd2.setNewValue( invocationMep.toString());
					cmd.add( cmd2 );

					executeCommand( cmd );
				}
			}
		});

		ModifyListener opModifyListener = new ModifyListener() {

			public void modifyText( ModifyEvent e ) {
				if( ConnectionConsumeSection.this.enableListener ) {
					EipConnectionSetAttributeCommand cmd =
						new EipConnectionSetAttributeCommand( EipConnection.PROPERTY_CONSUMED_OPERATION );

					cmd.setEipConnection( ConnectionConsumeSection.this.connection );
					String uri = ConnectionConsumeSection.this.opNsText.getText().trim();
					String local = ConnectionConsumeSection.this.opNameText.getText().trim();
					String value = uri.length() > 0 || local.length() > 0 ? "{" + uri + "}" + local : null;
					cmd.setNewValue( value );

					executeCommand( cmd );
				}
			}
		};

		this.opNameText.addModifyListener( opModifyListener );
		this.opNsText.addModifyListener( opModifyListener );
		this.mepCCombo.addModifyListener( new ModifyListener() {

			public void modifyText( ModifyEvent e ) {
				if( ConnectionConsumeSection.this.enableListener ) {
					EipConnectionSetAttributeCommand cmd =
						new EipConnectionSetAttributeCommand( EipConnection.PROPERTY_CONSUMED_MEP );

					cmd.setEipConnection( ConnectionConsumeSection.this.connection );
					cmd.setNewValue( ConnectionConsumeSection.this.mepCCombo.getText());
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
			String local = NamespaceUtils.removeNamespaceElements( this.connection.getConsumeOperation());
			String uri = NamespaceUtils.extractNamespaceUri( this.connection.getConsumeOperation());

			this.opNameText.setText( local != null ? local : "" );
			this.opNsText.setText( uri != null ? uri : "" );

			this.itfButton.setSelection( this.connection.isConsumeItf());
			this.srvButton.setSelection( this.connection.isConsumeSrv());
			this.edptButton.setSelection( this.connection.isConsumeEdpt());
			this.srvButton.setEnabled( this.connection.isConsumeSrvPossible());
			this.edptButton.setEnabled( this.connection.isConsumeEdptPossible());

			int index = -1;
			if( this.connection.getConsumeMep() != null )
				index = this.mepCCombo.indexOf( this.connection.getConsumeMep());

			if( index != -1 )
				this.mepCCombo.select( index );
			else
				this.mepCCombo.select( 0 );

			// Restore the caret
			if( focusText != null )
				focusText.setSelection( caret );

			// Update the content of the operation helper
			this.opNameToMep.clear();

			// End-point: search in the local repository
			if( this.connection.getTarget() instanceof Endpoint ) {
				Endpoint edpt = (Endpoint) this.connection.getTarget();

				QName itf = null;
				if( ! StringUtils.isEmpty( edpt.getInterfaceNamespace())
						&& ! StringUtils.isEmpty( edpt.getInterfaceName()))
					itf = new QName( edpt.getInterfaceNamespace(), edpt.getInterfaceName());

				QName srv = null;
				if( ! StringUtils.isEmpty( edpt.getServiceNamespace())
						&& ! StringUtils.isEmpty( edpt.getServiceName()))
					srv = new QName( edpt.getServiceNamespace(), edpt.getServiceName());

				this.opNameToMep.putAll( ConsumeUtils.getValidOperationsForConsume( itf, srv, edpt.getEndpointName()));
			}

			// EIP: check the associated WSDL, if it exists
			else if( this.connection.getTarget() != null ) {
				EipNode eip = ((EipNode) this.connection.getTarget());

				URI javaNetUri = null;
				if( eip.getWsdlUri() != null )
					javaNetUri = UriUtils.urlToUri( eip.getWsdlUri());

				if( javaNetUri != null ) {
					this.opNameToMep.putAll( WsdlUtils.INSTANCE.getOperations(
							javaNetUri, eip.getInterfaceName(), eip.getInterfaceNamespace(),
							eip.getServiceName(), eip.getServiceNamespace(), eip.getEndpointName()));
				}
			}

			// Hack for EIP nodes without a WSDL - not handled before because the EIP is not (yet) a Petals end-point
			if( this.opNameToMep.isEmpty()
						&& this.connection.getTarget() instanceof EipNode ) {
				QName op = new QName( EipNode.DEFAULT_EIP_NS, "anyOperation" );
				this.opNameToMep.put( op, Mep.UNKNOWN );
			}

			// Update the viewer
			this.operationViewer.setInput( this.opNameToMep.keySet());
			this.operationViewer.refresh();
			this.operationViewer.getCCombo().setEnabled( ! this.opNameToMep.isEmpty());
		}

		this.enableListener = true;
	}
}
