/****************************************************************************
 *
 * Copyright (c) 2010-2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.validation.v11;

import org.eclipse.bpel.common.wsdl.helpers.UriAndUrlHelper;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.swt.TextWithButtonComposite;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;

/**
 * Replace the default COMPONENT page.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ValidationProvideSpecificPage extends AbstractSuWizardPage {

	private boolean createXsd=true, createWsdl=false;
	private String xsdURL, targetNamespace = "http://petals.ow2.org";


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {

		// Create the composite container and define its layout.
		setDescription( "Specify how to get the XML schema." );
		final Composite container = new Composite( parent, SWT.NONE );
		GridLayoutFactory.swtDefaults().extendedMargins( 15, 15, 20, 0 ).applyTo( container );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));


		// Create the XML schema
		final Button createXsdButton = new Button( container, SWT.RADIO );
		createXsdButton.setText( "Create a new XML schema." );

		Composite comp = new Composite( container, SWT.NONE );
		GridLayout layout = new GridLayout( 2, false );
		layout.marginLeft = 15;
		comp.setLayout( layout );
		comp.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final Label tnsLabel = new Label( comp, SWT.NONE );
		tnsLabel.setText( "Target namespace:" );

		final Text tnsText = new Text( comp, SWT.SINGLE | SWT.BORDER );
		tnsText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		tnsText.setText( this.targetNamespace );
		tnsText.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				ValidationProvideSpecificPage.this.targetNamespace = tnsText.getText().trim();
				validate();
			}
		});


		// Import an existing XML schema
		final Button importXsdButton = new Button( container, SWT.RADIO );
		importXsdButton.setText( "Import an existing XML schema." );

		comp = new Composite( container, SWT.NONE );
		layout = new GridLayout( 3, false );
		layout.marginLeft = 15;
		comp.setLayout( layout );
		comp.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final Label xsdLabel = new Label( comp, SWT.NONE );
		xsdLabel.setText( "XML schema location:" );

		final TextWithButtonComposite twb = SwtFactory.createFileBrowser( comp, false, true, "XSD" );
		twb.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		twb.getText().addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				ValidationProvideSpecificPage.this.xsdURL = ((Text) e.widget).getText().trim();
				validate();
			}
		});


		// Create a WSDL
		Button createWsdlButton = new Button( container, SWT.CHECK );
		createWsdlButton.setText( "Create a default WSDL" );
		createWsdlButton.setSelection( this.createWsdl );

		GridData layoutData = new GridData();
		layoutData.verticalIndent = 5;
		createWsdlButton.setLayoutData( layoutData );

		createWsdlButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				ValidationProvideSpecificPage.this.createWsdl = ! ValidationProvideSpecificPage.this.createWsdl;
				validate();
			}
		});


		// Listeners
		SelectionListener commonListener = new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				ValidationProvideSpecificPage.this.createXsd = createXsdButton.getSelection();

				xsdLabel.setEnabled( ! ValidationProvideSpecificPage.this.createXsd );
				twb.getText().setEnabled( ! ValidationProvideSpecificPage.this.createXsd );
				twb.getButton().setEnabled( ! ValidationProvideSpecificPage.this.createXsd );

				tnsLabel.setEnabled( ValidationProvideSpecificPage.this.createXsd );
				tnsText.setEnabled( ValidationProvideSpecificPage.this.createXsd );

				validate();
			}
		};

		createXsdButton.addSelectionListener( commonListener );
		importXsdButton.addSelectionListener( commonListener );


		// Initialize the page
		createXsdButton.setSelection( this.createXsd );
		createXsdButton.notifyListeners( SWT.Selection, new Event());
		if( getErrorMessage() != null ) {
			updateStatus( null );
			setPageComplete( false );
		}

		setControl( container );
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #validate()
	 */
	@Override
	public boolean validate() {

		boolean valid = true;
		if( ! this.createXsd ) {
			if( StringUtils.isEmpty( this.xsdURL )) {
				updateStatus( "You must select the XML schema to import." );
				valid = false;
			}
			else {
				try {
					UriAndUrlHelper.urlToUri( this.xsdURL );

				} catch( Exception e ) {
					updateStatus( "The URL for the XML schema is not valid." );
					valid = false;
				}
			}
		}
		else {
			if( StringUtils.isEmpty( this.targetNamespace )) {
				updateStatus( "You must define the target name space of the XML schema to create." );
				valid = false;
			}
		}

		if( valid )
			updateStatus( null );

		if( this.createWsdl && ! this.createXsd )
			setMessage( "The generated WSDL might not reflect the content of the imported XML schema.", IMessageProvider.WARNING );
		else
			setMessage( null, IMessageProvider.WARNING );

		return valid;
	}


	/**
	 * @return the createXsd
	 */
	public boolean isCreateXsd() {
		return this.createXsd;
	}


	/**
	 * @return the createWsdl
	 */
	public boolean isCreateWsdl() {
		return this.createWsdl;
	}


	/**
	 * @return the xsdURL
	 */
	public String getXsdURL() {
		return this.xsdURL;
	}


	/**
	 * @param xsdURL the xsdURL to set
	 */
	public void setXsdURL( String xsdURL ) {
		this.xsdURL = xsdURL;
	}


	/**
	 * @return the targetNamespace
	 */
	public String getTargetNamespace() {
		return this.targetNamespace;
	}


	/**
	 * @param targetNamespace the targetNamespace to set
	 */
	public void setTargetNamespace( String targetNamespace ) {
		this.targetNamespace = targetNamespace;
	}
}
