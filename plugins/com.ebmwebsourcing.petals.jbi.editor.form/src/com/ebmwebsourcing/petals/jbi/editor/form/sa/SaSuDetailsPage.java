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

package com.ebmwebsourcing.petals.jbi.editor.form.sa;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.w3c.dom.Element;

import com.ebmwebsourcing.petals.common.internal.provisional.sse.StructuredModelHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.DomUtils;
import com.ebmwebsourcing.petals.jbi.editor.form.AbstractServicesFormPage;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SaSuDetailsPage extends SaDetailsPage {

	protected Text artifactText, componentText;


	/**
	 * Constructor.
	 * @param page
	 */
	public SaSuDetailsPage( AbstractServicesFormPage page ) {
		super( page );
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage
	 * #createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createContents( Composite parent ) {

		super.createContents( parent );
		Color blueFont = parent.getDisplay().getSystemColor( SWT.COLOR_DARK_BLUE );
		FormToolkit toolkit = this.managedForm.getToolkit();
		Composite container = this.nameText.getParent();

		// Base UI
		Label label = toolkit.createLabel( container, "Zip Artifact:" );
		label.setToolTipText( "The name of the *.zip artifact" );
		label.setForeground( blueFont );

		this.artifactText = toolkit.createText( container, "", SWT.SINGLE | SWT.BORDER );
		this.artifactText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.reducedXpathToControl.put( "/*[local-name()='target']/*[local-name()='artifacts-zip']", this.artifactText );

		label = toolkit.createLabel( container, "Component name:" );
		label.setToolTipText( "The name of the target component" );
		label.setForeground( blueFont );

		this.componentText = toolkit.createText( container, "", SWT.SINGLE | SWT.BORDER );
		this.componentText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.reducedXpathToControl.put( "/*[local-name()='target']/*[local-name()='component-name']", this.componentText );

		// Listeners
		Listener elementModifyListener = new Listener() {
			public void handleEvent( Event event ) {
				if( SaSuDetailsPage.this.reportChanges )
					editElement((Text) event.widget );
			}
		};

		this.artifactText.addListener( SWT.Modify, elementModifyListener );
		this.componentText.addListener( SWT.Modify, elementModifyListener );
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.IFormPart
	 * #refresh()
	 */
	@Override
	public void refresh() {

		super.refresh();
		if( this.selectedElement == null
					|| this.nameText == null 	// page already loaded?
					|| this.nameText.isDisposed())
			return;

		try {
			// Do not propagate indefinitely model changes
			this.reportChanges = false;

			Element targetElement = DomUtils.getChildElement( this.selectedElement, "target" );
			if( targetElement != null ) {
				Element editedElement = DomUtils.getChildElement( targetElement, "artifacts-zip" );
				if( editedElement != null ) {
					String value = StructuredModelHelper.getElementSimpleValue( editedElement );
					this.artifactText.setText( value != null ? value : "" );
				}

				editedElement = DomUtils.getChildElement( targetElement, "component-name" );
				if( editedElement != null ) {
					String value = StructuredModelHelper.getElementSimpleValue( editedElement );
					this.componentText.setText( value != null ? value : "" );
				}
			}

			// Update the markers
			prepareMarkerRefreshing();

		} finally {

			// Propagate (again) model changes
			this.reportChanges = true;
		}
	}


	/**
	 * @param text
	 */
	@Override
	protected void editElement( Text text ) {

		super.editElement( text );

		// Target element?
		if( text == this.artifactText || text == this.componentText ) {

			// Remember the caret position
			int offset = text.getCaretPosition();
			//

			String value = text.getText().trim();
			Element targetElement = DomUtils.getChildElement( this.selectedElement, "target" );

			// TODO: create the element if it does not exist
			if( targetElement != null ) {
				if( text == this.nameText ) {
					Element editedElement = DomUtils.getChildElement( targetElement, "artifacts-zip" );
					if( editedElement != null )
						StructuredModelHelper.setElementSimpleValue( editedElement, value );
				}
				else {
					Element editedElement = DomUtils.getChildElement( targetElement, "component-name" );
					if( editedElement != null )
						StructuredModelHelper.setElementSimpleValue( editedElement, value );
				}
			}

			// Restore the caret position
			text.setSelection( offset );
			//
		}
	}
}
