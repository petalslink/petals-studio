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

package com.ebmwebsourcing.petals.services.validation.wizard;

import java.io.File;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
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
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;

/**
 * Replace the default COMPONENT page.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ValidationProvideSpecificPage extends AbstractSuPage {

	private DataBindingContext dbc = new DataBindingContext();


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {
		DataBindingContext dbc = new DataBindingContext();

		// Create the composite container and define its layout.
		final Composite container = new Composite( parent, SWT.NONE );

		// Set help link for documentation page.
		setDescription( "Specify how to get the XML schema." );

		GridLayout layout = new GridLayout ();
		layout.marginLeft = 15;
		layout.marginRight = 15;
		layout.marginTop = 20;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));


		// Create the XML schema
		final Button createXsdButton = new Button( container, SWT.RADIO );
		createXsdButton.setText( "Create a new XML schema." );

		Composite comp = new Composite( container, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginLeft = 15;
		comp.setLayout( layout );
		comp.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final Label tnsLabel = new Label( comp, SWT.NONE );
		tnsLabel.setText( "Target namespace:" );

		final Text tnsText = new Text( comp, SWT.SINGLE | SWT.BORDER );
		tnsText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

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

		final Text xsdText = new Text( comp, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY );
		xsdText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final Button xsdBrowserButton = new Button( comp, SWT.PUSH );
		xsdBrowserButton.setText( "Browse..." );
		xsdBrowserButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				FileDialog dlg = new FileDialog( xsdLabel.getShell(), SWT.SINGLE );
				dlg.setText( "XSD Selection" );
				dlg.setFilterNames( new String[] { "XSD Files (*.xsd)" });
				dlg.setFilterExtensions( new String[] { "*.xsd" }); //$NON-NLS-1$

				String path = PreferencesManager.getSavedLocation();
				if( path.trim().length() > 0 )
					dlg.setFilterPath( path );

				String fn = dlg.open();
				if( fn != null ) {
					path = dlg.getFilterPath();
					PreferencesManager.setSavedLocation( path );

					String filePath = new File( fn ).toURI().toString();
					xsdText.setText( filePath );
					xsdText.setSelection( xsdText.getText().length());
				}
			}
		});


		// Create a WSDL
		Button createWsdlButton = new Button( container, SWT.CHECK );
		createWsdlButton.setText( "Create a default WSDL" );

		GridData layoutData = new GridData();
		layoutData.verticalIndent = 5;
		createWsdlButton.setLayoutData( layoutData );


		// Listeners
		IObservableValue createXsdButtonObservable = SWTObservables.observeSelection(createXsdButton);
		dbc.bindValue(createXsdButtonObservable, PojoObservables.observeValue(getWizard(), "createXSD"));
		dbc.bindValue(SWTObservables.observeEnabled(tnsText), createXsdButtonObservable);
		dbc.bindValue(SWTObservables.observeText(xsdText, SWT.Modify), PojoObservables.observeValue(getWizard(), "xsdTargetNamespace"));
		
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
		
		dbc.bindValue(createXsdButtonObservable, SWTObservables.observeSelection(importXsdButton), notRule, notRule);
		dbc.bindValue(createXsdButtonObservable, SWTObservables.observeEnabled(xsdText), notRule, notRule);
		dbc.bindValue(SWTObservables.observeText(xsdText, SWT.Modify), PojoObservables.observeValue(getWizard(), "xsdURL"));
		
		dbc.bindValue(SWTObservables.observeSelection(createWsdlButton), PojoObservables.observeValue(getWizard(), "createWSDL"));



		// Initialize the page
		createXsdButton.setSelection(true);
		createXsdButton.notifyListeners( SWT.Selection, new Event());
		if( getErrorMessage() != null ) {
			updateStatus( null );
			setPageComplete( false );
		}

		setControl( container );
	}

	@Override
	public void dispose() {
		super.dispose();
		dbc.dispose();
	}

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage#validate()
	 */
	@Override
	public boolean validate() {
		return true;
	}
}
