/****************************************************************************
 * 
 * Copyright (c) 2008-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.jsr181.wizards;

import java.io.File;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.preferences.PreferencesManager;
import com.ebmwebsourcing.petals.services.jsr181.jsr181.Jsr181Package;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;

/**
 * Replace the default COMPONENT page.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class Jsr181ProvidePage extends AbstractSuWizardPage {

	private static final int LABEL_WIDTH = 65;

	private DataBindingContext dbc;

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.XsdBasedAbstractSuPage
	 * #dialogChanged()
	 */
	@Override
	public boolean validate() {
		return true;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {
		dbc = new DataBindingContext();
		
		// Create the composite container and define its layout.
		final Composite container = new Composite( parent, SWT.NONE );

		// Set help link for documentation page.
		setDescription( "Select creation mode of the JAX Web Service." );

		GridLayout layout = new GridLayout ();
		layout.marginLeft = layout.marginRight = 15;
		layout.marginTop = 20;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));


		// Start with a JAX-WS implementation
		final Button implemFirstButton = new Button( container, SWT.RADIO );
		implemFirstButton.setText( "Start with a JAX-WS implementation." );

		Composite comp = new Composite( container, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginWidth = 0;
		comp.setLayout( layout );
		comp.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final Label classLabel = new Label( comp, SWT.NONE );
		classLabel.setText( "Class Name:" );
		classLabel.setToolTipText( "This class skeleton will be generated when this wizard completes" );
		classLabel.setLayoutData( new GridData( LABEL_WIDTH, SWT.DEFAULT ));

		final Text classText = new Text( comp, SWT.SINGLE | SWT.BORDER );
		classText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));


		// Start with a WSDL
		final Button wsdlFirstButton = new Button( container, SWT.RADIO );
		wsdlFirstButton.setText( "Start with a WSDL definition." );

		GridData layoutData = new GridData();
		layoutData.verticalIndent = 15;
		wsdlFirstButton.setLayoutData( layoutData );

		// Add controls.
		comp = new Composite( container, SWT.NONE );
		layout = new GridLayout( 3, false );
		layout.marginWidth = 0;
		comp.setLayout( layout );
		comp.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final Label wsdlLabel = new Label( comp, SWT.NONE );
		wsdlLabel.setText( "WSDL URL:" );
		wsdlLabel.setToolTipText( "This WSDL from which a JAX-WS implementation will be generated" );
		wsdlLabel.setLayoutData( new GridData( LABEL_WIDTH, SWT.DEFAULT ));

		final Text wsdlText = new Text( comp, SWT.SINGLE | SWT.BORDER );
		wsdlText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final Button wsdlBrowserButton = new Button( comp, SWT.PUSH );
		wsdlBrowserButton.setText( "Browse..." );
		wsdlBrowserButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				FileDialog dlg = new FileDialog( wsdlLabel.getShell(), SWT.SINGLE );
				dlg.setText( "WSDL Selection" );
				dlg.setFilterNames( new String[] { "WSDL Files (*.wsdl)" });
				dlg.setFilterExtensions( new String[] { "*.wsdl" }); //$NON-NLS-1$

				String path = PreferencesManager.getSavedLocation();
				if( path.trim().length() > 0 )
					dlg.setFilterPath( path );

				String fn = dlg.open();
				if( fn != null ) {
					path = dlg.getFilterPath();
					PreferencesManager.setSavedLocation( path );

					String filePath = new File( fn ).toURI().toString();
					wsdlText.setText( filePath );
					wsdlText.setSelection( wsdlText.getText().length());
				}
			}
		});


		// Listeners
		ISWTObservableValue wsdlFirstObservable = SWTObservables.observeSelection(wsdlFirstButton);
		dbc.bindValue(wsdlFirstObservable,
				PojoObservables.observeValue(getWizard(), "wsdlFirst"));
		dbc.bindValue(wsdlFirstObservable,
				SWTObservables.observeEnabled(wsdlLabel));
		dbc.bindValue(wsdlFirstObservable,
				SWTObservables.observeEnabled(wsdlText));
		dbc.bindValue(wsdlFirstObservable,
				SWTObservables.observeEnabled(wsdlBrowserButton));
		

		UpdateValueStrategy notRule = new UpdateValueStrategy();
		notRule.setConverter(new IConverter() {
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
		dbc.bindValue(SWTObservables.observeEnabled(classLabel),
				wsdlFirstObservable, notRule, notRule);
		dbc.bindValue(SWTObservables.observeEnabled(classText),
				wsdlFirstObservable, notRule, notRule);
		

		dbc.bindValue(SWTObservables.observeText(classText, SWT.Modify),
				EMFObservables.observeValue(getNewlyCreatedEndpoint(), Jsr181Package.Literals.JSR181_PROVIDES__CLAZZ));
		dbc.bindValue(SWTObservables.observeText(wsdlText, SWT.Modify),
				PojoObservables.observeValue(getWizard(), "wsdlUrl"));

		// Initialize the page
		wsdlFirstButton.setSelection(true);
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
}
