/******************************************************************************
 * Copyright (c) 2010-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.xslt.wizard;

import java.io.File;

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
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.preferences.PreferencesManager;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.DefaultSelectionListener;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;

/**
 * Replace the default COMPONENT page.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class XsltProvideSpecificPage extends AbstractSuWizardPage {

	private String attachmentName;
	private String xslUrl;
	private boolean createXsltFile = true;
	private boolean createWsdlFile = false;


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {

		// Create the composite container and define its layout.
		setDescription( "Specify how to get the XSL style sheet." );
		final Composite container = new Composite( parent, SWT.NONE );
		GridLayoutFactory.swtDefaults().extendedMargins( 15, 15, 20, 0 ).applyTo( container );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));


		// The XSL style sheet
		final Button createXslStyleSheetButton = new Button( container, SWT.RADIO );
		createXslStyleSheetButton.setText( "Create a new XSL style sheet." );

		final Button importXslStyleSheetButton = new Button( container, SWT.RADIO );
		importXslStyleSheetButton.setText( "Import an existing XSL style sheet." );

		Composite comp = new Composite( container, SWT.NONE );
		GridLayout layout = new GridLayout( 3, false );
		layout.marginLeft = 15;
		comp.setLayout( layout );
		comp.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final Label xslLabel = new Label( comp, SWT.NONE );
		xslLabel.setText( "XSL Sheet location:" );

		final Text xslText = new Text( comp, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY );
		xslText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		xslText.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				XsltProvideSpecificPage.this.xslUrl = xslText.getText().trim();
				validate();
			}
		});

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
		createWsdlButton.setSelection( this.createWsdlFile );

		GridData layoutData = new GridData();
		layoutData.verticalIndent = 10;
		createWsdlButton.setLayoutData( layoutData );

		createWsdlButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				XsltProvideSpecificPage.this.createWsdlFile = ! XsltProvideSpecificPage.this.createWsdlFile;
				validate();
			}
		});


		// The attachment name
		Label attachLabel = new Label( container, SWT.NONE );
		attachLabel.setText( "Attachment-name (if the transformation result is sent as an attachment):" );

		final Text attachText = new Text( container, SWT.SINGLE | SWT.BORDER );
		attachText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		attachText.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				XsltProvideSpecificPage.this.attachmentName = attachText.getText().trim();
				validate();
			}
		});


		// Listeners
		SelectionListener commonListener = new DefaultSelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				XsltProvideSpecificPage.this.createXsltFile = createXslStyleSheetButton.getSelection();

				xslLabel.setEnabled( ! XsltProvideSpecificPage.this.createXsltFile );
				xslText.setEnabled( ! XsltProvideSpecificPage.this.createXsltFile );
				xslBrowserButton.setEnabled( ! XsltProvideSpecificPage.this.createXsltFile );

				validate();
			}
		};

		createXslStyleSheetButton.addSelectionListener( commonListener );
		importXslStyleSheetButton.addSelectionListener( commonListener );


		// Initialize the page
		createXslStyleSheetButton.setSelection( this.createXsltFile );
		createXslStyleSheetButton.notifyListeners( SWT.Selection, new Event());
		if( getErrorMessage() != null ) {
			updateStatus( null );
			setPageComplete( false );
		}

		setControl( container );
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage#validate()
	 */
	@Override
	public boolean validate() {

		boolean valid = true;
		if( ! this.createXsltFile ) {
			if( StringUtils.isEmpty( this.xslUrl )) {
				updateStatus( "You must select the XSL style sheet to import." );
				valid = false;
			}
			else {
				try {
					UriAndUrlHelper.urlToUri( this.xslUrl );

				} catch( Exception e ) {
					updateStatus( "The URL for the XSL style sheet is not valid." );
					valid = false;
				}
			}
		}

		if( valid )
			updateStatus( null );

		if( this.createWsdlFile && ! this.createXsltFile )
			setMessage( "The generated WSDL might not reflect the content of the imported XSL style sheet.", IMessageProvider.WARNING );
		else
			setMessage( null, IMessageProvider.WARNING );

		return valid;
	}


	/**
	 * @return the attachmentName
	 */
	public String getAttachmentName() {
		return this.attachmentName;
	}


	/**
	 * @return the xslUrl
	 */
	public String getXslUrl() {
		return this.xslUrl;
	}


	/**
	 * @return the createXsltFile
	 */
	public boolean isCreateXsltFile() {
		return this.createXsltFile;
	}


	/**
	 * @return the createWsdlFile
	 */
	public boolean isCreateWsdlFile() {
		return this.createWsdlFile;
	}
}
