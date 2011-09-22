/****************************************************************************
 *
 * Copyright (c) 2009-2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.editor.blocks;

import javax.xml.namespace.QName;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.w3c.dom.Element;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.common.internal.provisional.sse.StructuredModelHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.DomUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.NamespaceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.editor.AbstractServicesFormPage;
import com.ebmwebsourcing.petals.services.su.ui.EnhancedConsumeDialog;
import com.ebmwebsourcing.petals.services.su.ui.PetalsHyperlinkListener;
import com.ebmwebsourcing.petals.services.su.utils.DomEditorHelper;


/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class GeneralConsumesDetails extends GeneralAbstractDetails {

	private Text opNsText, opNameText;
	private ComboViewer mepViewer;


	/**
	 * Constructor.
	 * @param page
	 */
	public GeneralConsumesDetails( AbstractServicesFormPage page ) {
		super( page );
		this.sectionTitle = "Consumes properties";
		this.sectionDescription = "Define the identifiers of the service to consume.";
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.blocks.GeneralAbstractDetails
	 * #addContentAfter(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void addContentAfter( final Composite parent ) {
		// nothing
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.blocks.GeneralAbstractDetails
	 * #addContentBefore(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void addContentBefore( Composite parent ) {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.blocks.GeneralAbstractDetails
	 * #createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createContents( final Composite parent ) {

		// Create the inherited widgets
		super.createContents( parent );


		// Add a section below for the operation to invoke
		Color blueFont = parent.getDisplay().getSystemColor( SWT.COLOR_DARK_BLUE );
		final FormToolkit toolkit = this.managedForm.getToolkit();
		Section section = toolkit.createSection( parent,
					Section.DESCRIPTION
					| ExpandableComposite.TITLE_BAR
					| ExpandableComposite.EXPANDED );

		section.setLayoutData( new GridData( GridData.FILL_BOTH ));
		section.clientVerticalSpacing = 10;
		section.setText( "Invocation properties" );
		section.setDescription( "Edit the invoked operation and the message exchange pattern." );

		Composite container = toolkit.createComposite( section );
		GridLayout layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 10;
		container.setLayout( layout );
		container.setLayoutData( new TableWrapData( TableWrapData.FILL_GRAB ));
		section.setClient( container );

		// The edition fields
		Label label = toolkit.createLabel( container, "Operation namespace:" );
		label.setToolTipText( "The namespace of the operation (should match an operation declared in a WSDL)" );
		label.setForeground( blueFont );

		this.opNsText = toolkit.createText( container, "", SWT.SINGLE | SWT.BORDER );
		this.opNsText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		label = toolkit.createLabel( container, "Operation name:" );
		label.setToolTipText( "The name of the operation (should match an operation declared in a WSDL)" );
		label.setForeground( blueFont );

		this.opNameText = toolkit.createText( container, "", SWT.SINGLE | SWT.BORDER );
		this.opNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.reducedXpathToControl.put( "/*[local-name()='operation']", this.opNameText );

		label = toolkit.createLabel( container, "Invocation MEP:" );
		label.setToolTipText( "The Message Exchange Pattern to use for the invocation" );
		label.setForeground( blueFont );

		CCombo mepCombo = new CCombo( container, SWT.BORDER | SWT.READ_ONLY );
		mepCombo.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.reducedXpathToControl.put( "/*[local-name()='mep']", mepCombo );
		toolkit.adapt( mepCombo );

		this.mepViewer = new ComboViewer( mepCombo );
		this.mepViewer.setContentProvider( new ArrayContentProvider());
		this.mepViewer.setLabelProvider( new LabelProvider());
		this.mepViewer.setInput( Mep.values());


		// Add a set of helpers
		toolkit.createLabel( container, "" );

		section = toolkit.createSection( container, ExpandableComposite.TWISTIE );
		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.verticalIndent = 5;
		section.setLayoutData( layoutData );
		section.clientVerticalSpacing = 10;
		section.setText( "Helpers" );

		Composite subContainer = toolkit.createComposite( section );
		layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		subContainer.setLayout( layout );
		subContainer.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		section.setClient( subContainer );

		Hyperlink selectLink = toolkit.createHyperlink(
					subContainer, "Select a Petals service and an operation to invoke", SWT.NONE );
		selectLink.setToolTipText( "Select the service and the operation to invoke from the known Petals services" );
		selectLink.addHyperlinkListener( new PetalsHyperlinkListener() {
			@Override
			public void linkActivated( HyperlinkEvent e ) {

				EnhancedConsumeDialog dlg = new EnhancedConsumeDialog( parent.getShell(), toolkit );
				if( dlg.open() == Window.OK ) {

					QName q = dlg.getItfToInvoke();
					GeneralConsumesDetails.this.itfNameText.setText( q.getLocalPart());
					GeneralConsumesDetails.this.itfNsText.setText( q.getNamespaceURI());

					q = dlg.getSrvToInvoke();
					GeneralConsumesDetails.this.srvNameText.setText( q != null ? q.getLocalPart() : "" );
					GeneralConsumesDetails.this.srvNsText.setText( q != null ? q.getNamespaceURI() : "" );

					String edpt = dlg.getEdptToInvoke();
					GeneralConsumesDetails.this.edptNameText.setText( edpt != null ? edpt : "" );

					QName op = dlg.getOperationToInvoke();
					GeneralConsumesDetails.this.mepViewer.setSelection( new StructuredSelection( dlg.getInvocationMep()));
					if( op == EnhancedConsumeDialog.NO_OPERATION ) {
						GeneralConsumesDetails.this.opNsText.setText( "" );
						GeneralConsumesDetails.this.opNameText.setText( "" );

					} else {
						GeneralConsumesDetails.this.opNsText.setText( op.getNamespaceURI());
						GeneralConsumesDetails.this.opNameText.setText(  op.getLocalPart());
					}
				}
			}
		});


		// Listeners
		ModifyListener opListener = new ModifyListener() {
			public void modifyText( ModifyEvent e ) {

				if( ! GeneralConsumesDetails.this.reportChanges )
					return;

				// Remember the caret position
				Text focusText = null;
				if( GeneralConsumesDetails.this.opNameText.isFocusControl())
					focusText = GeneralConsumesDetails.this.opNameText;
				else if( GeneralConsumesDetails.this.opNsText.isFocusControl())
					focusText = GeneralConsumesDetails.this.opNsText;

				int offset = focusText == null ? -1 : focusText.getCaretPosition();
				//

				String local = GeneralConsumesDetails.this.opNameText.getText().trim();
				String ns = GeneralConsumesDetails.this.opNsText.getText().trim();
				if( local.length() == 0 && ns.length() == 0 ) {
					Element newElt = DomUtils.getChildElement( GeneralConsumesDetails.this.selectedElement, "operation" );
					if( newElt != null ) {
						GeneralConsumesDetails.this.page.startTransaction();
						DomUtils.removeElement( newElt );
						GeneralConsumesDetails.this.page.stopTransaction();
					}

				} else {
					GeneralConsumesDetails.this.page.startTransaction();
					DomEditorHelper.setOperation( GeneralConsumesDetails.this.selectedElement, ns, local );
					GeneralConsumesDetails.this.page.stopTransaction();
				}

				// Restore the caret position
				if( focusText != null )
					focusText.setSelection( offset );
				//
			}
		};

		this.opNameText.addModifyListener( opListener );
		this.opNsText.addModifyListener( opListener );
		this.mepViewer.addSelectionChangedListener( new ISelectionChangedListener() {
			public void selectionChanged( SelectionChangedEvent event ) {

				if( ! GeneralConsumesDetails.this.reportChanges )
					return;

				Object o = ((IStructuredSelection) event.getSelection()).getFirstElement();
				if( o instanceof Mep ) {
					GeneralConsumesDetails.this.page.startTransaction();
					DomEditorHelper.setMep( GeneralConsumesDetails.this.selectedElement, (Mep) o );
					GeneralConsumesDetails.this.page.stopTransaction();
				}
			}
		});
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.blocks.GeneralAbstractDetails
	 * #refreshCustomWidgets()
	 */
	@Override
	public void refreshCustomWidgets() {

		if( this.selectedElement == null
					|| this.itfNsText == null 	// page already loaded?
					|| this.itfNsText.isDisposed())
			return;


		// End-point
		String name = this.selectedElement.getAttribute( "endpoint-name" );
		this.edptNameText.setText( name != null ? name : "" );

		// Operation
		Element opElt = DomUtils.getChildElement( this.selectedElement, "operation" );
		QName qname = opElt != null ? NamespaceUtils.getQNameElement( opElt ) : null;
		this.opNameText.setText( qname != null ? qname.getLocalPart() : "" );
		this.opNsText.setText( qname != null ? qname.getNamespaceURI() : "" );
		if( opElt != null && StringUtils.isEmpty( this.opNsText.getText())) {
			String ns = opElt.getAttribute( "xmlns:op" );
			if( ! StringUtils.isEmpty( ns ))
				this.opNsText.setText( ns );
		}

		// MEP
		Element mepElt = DomUtils.getChildElement( this.selectedElement, "mep" );
		String mepAsString = mepElt != null ? StructuredModelHelper.getElementSimpleValue( mepElt ) : null;
		Mep mep = Mep.whichMep( mepAsString );
		this.mepViewer.setSelection( new StructuredSelection( mep ));
	}


	/**
	 * Edits an attribute of {@link #selectedElement}.
	 * @param widget the edition widget, that determines the attribute to edit
	 */
	@Override
	protected void editAttribute( Text widget ) {

		// Remember the caret position
		int offset = widget.getCaretPosition();
		//

		String value = widget.getText().trim();
		String prefix = "";
		String attributeName = null;
		boolean deleteAttribute = false;

		if( widget == this.itfNameText ) {
			attributeName = "interface-name";
			prefix = "itfNs:";

			if( this.selectedElement.getAttribute( ITF_NS_DECL ) == null ) {
				// Propagate the name space
				String itfValue = this.selectedElement.getAttribute( "interface-name" );
				String ns = NamespaceUtils.getNamespaceUri( itfValue, this.selectedElement );
				if( ns == null || ns.trim().length() == 0 )
					ns = "http://";

				DomUtils.addOrSetAttribute( this.selectedElement, ITF_NS_DECL, ns );
			}

		} else if( widget == this.srvNameText ) {
			attributeName = "service-name";
			prefix = "srvNs:";

			if( value.length() == 0 )
				deleteAttribute = true;

			else if( this.selectedElement.getAttribute( SRV_NS_DECL ) == null ) {
				// Propagate the name space
				String srvValue = this.selectedElement.getAttribute( "service-name" );
				String ns = NamespaceUtils.getNamespaceUri( srvValue, this.selectedElement );
				if( ns == null || ns.trim().length() == 0 )
					ns = "http://";

				DomUtils.addOrSetAttribute( this.selectedElement, SRV_NS_DECL, ns );
			}

		} else if( widget == this.itfNsText ) {
			attributeName = ITF_NS_DECL;
			if( value.length() == 0 )
				deleteAttribute = true;

			else {
				// Force the element to use this name space prefix
				String itfValue = this.selectedElement.getAttribute( "interface-name" );
				String lp = NamespaceUtils.removeNamespaceElements( itfValue );
				if( lp == null )
					lp = "";
				DomUtils.addOrSetAttribute( this.selectedElement, "interface-name", "itfNs:" + lp );
			}

		} else if( widget == this.srvNsText ) {
			attributeName = SRV_NS_DECL;
			if( value.length() == 0 )
				deleteAttribute = true;

			else {
				// Force the element to use this name space prefix
				String srvValue = this.selectedElement.getAttribute( "service-name" );
				String lp = NamespaceUtils.removeNamespaceElements( srvValue );
				if( lp == null )
					lp = "";
				DomUtils.addOrSetAttribute( this.selectedElement, "service-name", "srvNs:" + lp );
			}

		} else if( widget == this.edptNameText ) {
			attributeName = "endpoint-name";
			deleteAttribute = value.length() == 0 || PetalsConstants.AUTO_GENERATE.equals( value );
		}

		// Do not define empty name spaces
		if( deleteAttribute ) {
			this.selectedElement.removeAttribute( attributeName );

		} else {
			String attributeValue = prefix + value;
			DomUtils.addOrSetAttribute( this.selectedElement, attributeName, attributeValue );
		}

		this.page.updatePage();

		// Restore the caret position
		widget.setSelection( offset );
		//
	}
}
