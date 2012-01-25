/****************************************************************************
 * 
 * Copyright (c) 2010-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.common.internal.provisional.tabbedproperties;

import org.eclipse.bpel.common.wsdl.helpers.UriAndUrlHelper;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * The section to display the card ID of a service.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ServiceCardIdSection extends AbstractPropertySection {

	private IServiceCardId service;
	private Text srvText, itfText, edptText, wsdlText, implText;


	/**
	 * Constructor.
	 */
	public ServiceCardIdSection() {
		// nothing
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

		// Containing section
		Composite subParent = getWidgetFactory().createComposite( parent );
		GridLayout layout = new GridLayout();
		layout.marginWidth = 12;
		layout.marginHeight = 12;
		layout.verticalSpacing = 25;
		subParent.setLayout( layout );


		// First fields
		Section section = getWidgetFactory().createSection( subParent,
					ExpandableComposite.TITLE_BAR
					| ExpandableComposite.EXPANDED );

		section.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		section.setText( "Service ID" );

		Composite container = getWidgetFactory().createComposite( section, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 10;
		container.setLayout( layout );
		container.setLayoutData( new TableWrapData( TableWrapData.FILL_GRAB ));
		section.setClient( container );

		CLabel label = getWidgetFactory().createCLabel( container, "Interface Name:" );
		label.setToolTipText( "The interface name" );
		this.itfText = getWidgetFactory().createText( container, "", SWT.READ_ONLY );
		this.itfText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		label = getWidgetFactory().createCLabel( container, "Service Name:" );
		label.setToolTipText( "The service name" );
		this.srvText = getWidgetFactory().createText( container, "", SWT.READ_ONLY );
		this.srvText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		label = getWidgetFactory().createCLabel( container, "End-point Name:" );
		label.setToolTipText( "The end-point name" );
		this.edptText = getWidgetFactory().createText( container, "" );
		this.edptText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));


		// Other fields
		section = getWidgetFactory().createSection( subParent,
					ExpandableComposite.TITLE_BAR
					| ExpandableComposite.EXPANDED );

		section.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		section.setText( "Other Information" );

		container = getWidgetFactory().createComposite( section, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 10;
		container.setLayout( layout );
		container.setLayoutData( new TableWrapData( TableWrapData.FILL_GRAB ));
		section.setClient( container );

		label = getWidgetFactory().createCLabel( container, "WSDL Location:" );
		label.setToolTipText( "The WSDL location" );
		this.wsdlText = getWidgetFactory().createText( container, "", SWT.READ_ONLY );
		this.wsdlText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		label = getWidgetFactory().createCLabel( container, "Implementation Type:" );
		label.setToolTipText( "The kind of implementation used by this service" );
		this.implText = getWidgetFactory().createText( container, "", SWT.READ_ONLY );
		this.implText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection
	 * #setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setInput( IWorkbenchPart part, ISelection selection ) {

		super.setInput( part, selection );
		if( selection instanceof IStructuredSelection ) {
			Object input = ((IStructuredSelection) selection).getFirstElement();
			if( input instanceof IServiceCardId )
				this.service = (IServiceCardId) input;
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection
	 * #refresh()
	 */
	@Override
	public void refresh() {

		if( this.itfText != null
					&& ! this.itfText.isDisposed()
					&& this.service != null ) {

			// ITF
			StringBuilder sb = new StringBuilder();
			if( this.service.getInterfaceName() != null ) {
				if( this.service.getInterfaceName().getLocalPart() != null )
					sb.append( this.service.getInterfaceName().getLocalPart());

				sb.append( " - " );

				if( this.service.getInterfaceName().getNamespaceURI() != null )
					sb.append( this.service.getInterfaceName().getNamespaceURI());
			}

			this.itfText.setText( sb.toString());
			this.itfText.setToolTipText( sb.toString());


			// SRV
			sb = new StringBuilder();
			if( this.service.getServiceName() != null ) {
				if( this.service.getServiceName().getLocalPart() != null )
					sb.append( this.service.getServiceName().getLocalPart());

				sb.append( " - " );

				if( this.service.getServiceName().getNamespaceURI() != null )
					sb.append( this.service.getServiceName().getNamespaceURI());
			}

			this.srvText.setText( sb.toString());
			this.srvText.setToolTipText( sb.toString());


			// EDPT
			this.edptText.setToolTipText( this.service.getEndpointName());
			if( this.service.getEndpointName() != null )
				this.edptText.setText( this.service.getEndpointName());
			else
				this.edptText.setText( "" );


			// WSDL
			if( this.service.getWsdlLocation() == null ) {
				this.wsdlText.setToolTipText( "This service does not have a WSDL or it could not be found" );
				this.wsdlText.setText( " - " );

			} else {
				boolean abs = UriAndUrlHelper.isAbsoluteUri( this.service.getWsdlLocation());
				sb = new StringBuilder( this.service.getWsdlLocation());
				sb.append( " (" + (abs ? "absolute" : "relative") + ")" );
				this.wsdlText.setText( sb.toString());
				this.wsdlText.setToolTipText( this.service.getWsdlLocation());
			}


			// Implementation type
			if( this.service.getImplementationType() != null ) {
				this.implText.setText( this.service.getImplementationType());
				this.implText.setToolTipText( this.service.getImplementationType());

			} else {
				this.implText.setText( "Undetermined" );
				this.implText.setToolTipText( "The type of implementation could not be determined for this service." );
			}
		}
	}
}
