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

package com.ebmwebsourcing.petals.services.xslt.wizard;

import java.io.File;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.preferences.PreferencesManager;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.ebmwebsourcing.petals.studio.services.xslt.xslt.XsltPackage;

/**
 * Replace the default COMPONENT page.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class XsltProvideSpecificPage extends AbstractSuWizardPage {

	private DataBindingContext dbc;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {

		// Create the composite container and define its layout.
		final Composite container = new Composite( parent, SWT.NONE );

		// Set help link for documentation page.
		setDescription( "Specify how to get the XSL style sheet." );

		GridLayout layout = new GridLayout ();
		layout.marginLeft = 15;
		layout.marginRight = 15;
		layout.marginTop = 20;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));


		// The XSL style sheet
		final Button createXslStyleSheetButton = new Button( container, SWT.RADIO );
		createXslStyleSheetButton.setText( "Create a new XSL style sheet." );

		final Button importXslStyleSheetButton = new Button( container, SWT.RADIO );
		importXslStyleSheetButton.setText( "Import an existing XSL style sheet." );

		Composite comp = new Composite( container, SWT.NONE );
		layout = new GridLayout( 3, false );
		layout.marginLeft = 15;
		comp.setLayout( layout );
		comp.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final Label xslLabel = new Label( comp, SWT.NONE );
		xslLabel.setText( "XSL Sheet location:" );

		final Text xslText = new Text( comp, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY );
		xslText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final Button xslBrowserButton = new Button( comp, SWT.PUSH );
		xslBrowserButton.setText( "Browse..." );
		xslBrowserButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				FileDialog dlg = new FileDialog( xslLabel.getShell(), SWT.SINGLE );
				dlg.setText( "XSL Selection" );
				dlg.setFilterNames( new String[] { "XSL Files (*.xsl)" });
				dlg.setFilterExtensions( new String[] { "*.xsl" }); //$NON-NLS-1$

				String path = PreferencesManager.getSavedLocation();
				if( path.trim().length() > 0 )
					dlg.setFilterPath( path );

				String fn = dlg.open();
				if( fn != null ) {
					path = dlg.getFilterPath();
					PreferencesManager.setSavedLocation( path );

					String filePath = new File( fn ).toURI().toString();
					xslText.setText( filePath );
					xslText.setSelection( xslText.getText().length());
				}
			}
		});


		// Create a WSDL
		Button createWsdlButton = new Button( container, SWT.CHECK );
		createWsdlButton.setText( "Create a default WSDL (might need to be updated)" );

		GridData layoutData = new GridData();
		layoutData.verticalIndent = 10;
		createWsdlButton.setLayoutData( layoutData );


		// The attachment name
		Label attachLabel = new Label( container, SWT.NONE );
		attachLabel.setText( "Attachment-name (if the transformation result is sent as an attachment):" );

		final Text attachText = new Text( container, SWT.SINGLE | SWT.BORDER );
		attachText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		
		dbc = new DataBindingContext();
		IObservableValue createXslButtonObservable = SWTObservables.observeSelection(createXslStyleSheetButton);
		dbc.bindValue(createXslButtonObservable, PojoObservables.observeValue(getWizard(), "createXsl"));
		
		UpdateValueStrategy notRule = new UpdateValueStrategy().setConverter(new IConverter() {
			public Object getToType() {
				return boolean.class;
			}
			
			public Object getFromType() {
				return boolean.class;
			}
			
			public Object convert(Object fromObject) {
				return ! ((Boolean)fromObject);
			}
		});
		dbc.bindValue(SWTObservables.observeEnabled(xslLabel), createXslButtonObservable, notRule, notRule);
		dbc.bindValue(SWTObservables.observeEnabled(xslText), createXslButtonObservable, notRule, notRule);
		
		dbc.bindValue(SWTObservables.observeSelection(createWsdlButton), PojoObservables.observeValue(getWizard(), "createWSDL"));
		dbc.bindValue(SWTObservables.observeText(attachText, SWT.Modify), EMFObservables.observeValue(getNewlyCreatedEndpoint(), XsltPackage.Literals.XSLT_PROVIDES__OUTPUT_ATTACHMENT_NAME));

		// Initialize the page
		createXslStyleSheetButton.setSelection( getWizard().isCreateXsl() );
		createXslStyleSheetButton.notifyListeners( SWT.Selection, new Event());
		if( getErrorMessage() != null ) {
			updateStatus( null );
			setPageComplete( false );
		}

		setControl( container );
	}
	
	@Override
	public XsltWizard23 getWizard() {
		return (XsltWizard23)super.getWizard();
	}

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage#validate()
	 */
	@Override
	public boolean validate() {
		return true;
	}
	
	@Override
	public void dispose() {
		super.dispose();
		dbc.dispose();
	}
}
