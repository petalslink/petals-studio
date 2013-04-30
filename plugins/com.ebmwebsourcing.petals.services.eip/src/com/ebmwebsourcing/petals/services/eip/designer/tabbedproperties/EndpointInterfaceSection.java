/****************************************************************************
 * 
 * Copyright (c) 2010-2013, Linagora
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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.services.eip.PetalsEipPlugin;
import com.ebmwebsourcing.petals.services.eip.designer.edit.commands.EndpointSetAttributeCommand;
import com.ebmwebsourcing.petals.services.eip.designer.edit.parts.EndpointEditPart;
import com.ebmwebsourcing.petals.services.eip.designer.model.AbstractNode;
import com.ebmwebsourcing.petals.services.eip.designer.model.Endpoint;
import com.ebmwebsourcing.petals.services.explorer.model.EndpointBean;
import com.ebmwebsourcing.petals.services.utils.ConsumeUtils;

/**
 * The main (and only one) section for the Petals tab (properties of a JBI binding).
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EndpointInterfaceSection extends AbstractPropertySection implements PropertyChangeListener {

	private Endpoint edpt;
	private boolean enableListener;
	private Text itfNsText, itfNameText, srvNsText, srvNameText, edptText;
	private Image ideaImg;


	/**
	 * Constructor.
	 */
	public EndpointInterfaceSection() {

		try {
			ImageDescriptor desc = PetalsEipPlugin.getImageDescriptor( "icons/obj16/smartmode_co.gif" );
			this.ideaImg = desc.createImage();

		} catch( Exception e ) {
			PetalsEipPlugin.log( e, IStatus.WARNING );
		}
	}


	/*
	 * (non-Jsdoc)
	 * @see java.beans.PropertyChangeListener
	 * #propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange( PropertyChangeEvent evt ) {

		if( AbstractNode.PROPERTY_SERVICE_NAME.equals( evt.getPropertyName())
					|| AbstractNode.PROPERTY_SERVICE_NAMESPACE.equals( evt.getPropertyName())
					|| AbstractNode.PROPERTY_INTERFACE_NAME.equals( evt.getPropertyName())
					|| AbstractNode.PROPERTY_INTERFACE_NAMESPACE.equals( evt.getPropertyName())
					|| AbstractNode.PROPERTY_ENDPOINT_NAME.equals( evt.getPropertyName()))
			refresh();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection
	 * #dispose()
	 */
	@Override
	public void dispose() {

		if( this.ideaImg != null ) {
			this.ideaImg.dispose();
			this.ideaImg = null;
		}

		super.dispose();
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
		final Composite container = getWidgetFactory().createPlainComposite( parent, SWT.NONE );
		container.setLayout( new GridLayout( 4, false ));

		Composite subContainer = getWidgetFactory().createComposite( container );
		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 4;
		subContainer.setLayoutData( layoutData );

		GridLayout layout = new GridLayout();
		layout.marginBottom = 5;
		layout.marginTop = 5;
		layout.marginWidth = 0;
		subContainer.setLayout( layout );
		getWidgetFactory().createLabel( subContainer, "Define the properties of the Petals service to invoke." );

		// Interface
		CLabel label = getWidgetFactory().createCLabel( container, "Interface name:" );
		label.setToolTipText( "The local part of the interface name" );

		this.itfNameText = getWidgetFactory().createText( container, "" );
		this.itfNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.itfNameText.addModifyListener( new ModifyListener() {

			public void modifyText( ModifyEvent e ) {
				if( EndpointInterfaceSection.this.enableListener ) {
					EndpointSetAttributeCommand cmd =
						new EndpointSetAttributeCommand( AbstractNode.PROPERTY_INTERFACE_NAME );

					cmd.setEndpoint( EndpointInterfaceSection.this.edpt );
					cmd.setNewValue( EndpointInterfaceSection.this.itfNameText.getText());
					executeCommand( cmd );
				}
			}
		});

		label = getWidgetFactory().createCLabel( container, "Interface namespace:" );
		label.setToolTipText( "The name space URI of the interface name" );

		this.itfNsText = getWidgetFactory().createText( container, "" );
		this.itfNsText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.itfNsText.addModifyListener( new ModifyListener() {

			public void modifyText( ModifyEvent e ) {
				if( EndpointInterfaceSection.this.enableListener ) {
					EndpointSetAttributeCommand cmd =
						new EndpointSetAttributeCommand( AbstractNode.PROPERTY_INTERFACE_NAMESPACE );

					cmd.setEndpoint( EndpointInterfaceSection.this.edpt );
					cmd.setNewValue( EndpointInterfaceSection.this.itfNsText.getText());
					executeCommand( cmd );
				}
			}
		});

		// Service
		label = getWidgetFactory().createCLabel( container, "Service name:" );
		label.setToolTipText( "The local part of the service name" );

		this.srvNameText = getWidgetFactory().createText( container, "" );
		this.srvNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.srvNameText.addModifyListener( new ModifyListener() {

			public void modifyText( ModifyEvent e ) {
				if( EndpointInterfaceSection.this.enableListener ) {
					EndpointSetAttributeCommand cmd =
						new EndpointSetAttributeCommand( AbstractNode.PROPERTY_SERVICE_NAME );

					cmd.setEndpoint( EndpointInterfaceSection.this.edpt );
					cmd.setNewValue( EndpointInterfaceSection.this.srvNameText.getText());
					executeCommand( cmd );
				}
			}
		});

		label = getWidgetFactory().createCLabel( container, "Service namespace:" );
		label.setToolTipText( "The name space URI of the service name" );

		this.srvNsText = getWidgetFactory().createText( container, "" );
		this.srvNsText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.srvNsText.addModifyListener( new ModifyListener() {

			public void modifyText( ModifyEvent e ) {
				if( EndpointInterfaceSection.this.enableListener ) {
					EndpointSetAttributeCommand cmd =
						new EndpointSetAttributeCommand( AbstractNode.PROPERTY_SERVICE_NAMESPACE );

					cmd.setEndpoint( EndpointInterfaceSection.this.edpt );
					cmd.setNewValue( EndpointInterfaceSection.this.srvNsText.getText());
					executeCommand( cmd );
				}
			}
		});

		// End-point
		label = getWidgetFactory().createCLabel( container, "End-point name:" );
		label.setToolTipText( "The end-point name (a simple string)" );

		this.edptText = getWidgetFactory().createText( container, "" );
		this.edptText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.edptText.addModifyListener( new ModifyListener() {

			public void modifyText( ModifyEvent e ) {
				if( EndpointInterfaceSection.this.enableListener ) {
					EndpointSetAttributeCommand cmd =
						new EndpointSetAttributeCommand( AbstractNode.PROPERTY_ENDPOINT_NAME );

					cmd.setEndpoint( EndpointInterfaceSection.this.edpt );
					cmd.setNewValue( EndpointInterfaceSection.this.edptText.getText());
					executeCommand( cmd );
				}
			}
		});

		// Baaaad...
		getWidgetFactory().createLabel( container, "" );
		getWidgetFactory().createLabel( container, "" );
		getWidgetFactory().createLabel( container, "" );

		// Add the helper to select another Petals service
		subContainer = getWidgetFactory().createComposite( container );
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 3;
		subContainer.setLayoutData( layoutData );

		layout = new GridLayout( 2, false );
		layout.marginWidth = 0;
		subContainer.setLayout( layout );

		label = getWidgetFactory().createCLabel( subContainer, "" );
		label.setImage( this.ideaImg );

		Hyperlink link = getWidgetFactory().createHyperlink( subContainer, "Select a service from the Petals Services view", SWT.NONE );
		link.setToolTipText( "Select an end-point to invoke among the currently referenced end-points" );
		link.addHyperlinkListener( new HyperlinkAdapter() {
			@Override
			public void linkActivated( HyperlinkEvent e ) {

				EndpointBean bean = ConsumeUtils.selectEndpointToConsume( container );
				if( bean == null )
					return;

				try {
					EndpointInterfaceSection.this.srvNameText.setText( bean.getServiceName().getLocalPart());
					EndpointInterfaceSection.this.srvNsText.setText( bean.getServiceName().getNamespaceURI());
					EndpointInterfaceSection.this.itfNameText.setText( bean.getInterfaceName().getLocalPart());
					EndpointInterfaceSection.this.itfNsText.setText( bean.getInterfaceName().getNamespaceURI());

					if( PetalsConstants.AUTO_GENERATE.equals( bean.getEndpointName()))
						EndpointInterfaceSection.this.edptText.setText( "" );
					else
						EndpointInterfaceSection.this.edptText.setText( bean.getEndpointName());

				} catch( Exception e1 ) {
					PetalsEipPlugin.log( e1, IStatus.ERROR );
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
		if( this.edpt != null )
			this.edpt.removePropertyChangeListener( this );

		if( selection instanceof IStructuredSelection ) {
			Object input = ((IStructuredSelection) selection).getFirstElement();
			if( input instanceof EndpointEditPart )
				this.edpt = (Endpoint) ((EndpointEditPart) input).getModel();
		}

		// Listen to changes in the new model
		if( this.edpt != null )
			this.edpt.addPropertyChangeListener( this );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection
	 * #refresh()
	 */
	@Override
	public void refresh() {

		this.enableListener = false;
		if( this.itfNsText != null
					&& ! this.itfNsText.isDisposed()
					&& this.edpt != null ) {

			// Save the caret position
			Text focusText = null;
			if( this.itfNsText.isFocusControl())
				focusText = this.itfNsText;
			else if( this.itfNameText.isFocusControl())
				focusText = this.itfNameText;
			else if( this.srvNameText.isFocusControl())
				focusText = this.srvNameText;
			else if( this.srvNsText.isFocusControl())
				focusText = this.srvNsText;
			else if( this.edptText.isFocusControl())
				focusText = this.edptText;

			int caret = focusText != null ? focusText.getCaretPosition() : -1;

			// Update values
			this.itfNsText.setText( this.edpt.getInterfaceNamespace() != null ? this.edpt.getInterfaceNamespace() : "" );
			this.itfNameText.setText( this.edpt.getInterfaceName() != null ? this.edpt.getInterfaceName() : "" );
			this.srvNsText.setText( this.edpt.getServiceNamespace() != null ? this.edpt.getServiceNamespace() : "" );
			this.srvNameText.setText( this.edpt.getServiceName() != null ? this.edpt.getServiceName() : "" );
			this.edptText.setText( this.edpt.getEndpointName() != null ? this.edpt.getEndpointName() : "" );

			// Restore the caret
			if( focusText != null )
				focusText.setSelection( caret );
		}

		this.enableListener = true;
	}
}
