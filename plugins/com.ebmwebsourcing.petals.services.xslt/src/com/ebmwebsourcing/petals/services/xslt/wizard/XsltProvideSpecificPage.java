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

import org.eclipse.jface.dialogs.IMessageProvider;
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

import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.XmlElement;
import com.ebmwebsourcing.petals.common.internal.provisional.preferences.PreferencesManager;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.UriUtils;
import com.ebmwebsourcing.petals.services.su.wizards.SuMainConstants;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;

/**
 * Replace the default COMPONENT page.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class XsltProvideSpecificPage extends AbstractSuPage {

	public final static String DEFAULT_XSL_NAME = "transformation.xsl";
	public final static String CREATE_XSL = "create.xsl";
	public final static String CREATE_WSDL = "create.wsdl";

	private String attachmentName;
	private String xslUrl;
	private boolean createXsltFile = true;
	private boolean createWsdlFile = false;



	/**
	 * Empty constructor. Required empty to be instantiated by the main plug-in.
	 */
	public XsltProvideSpecificPage() {
		// Custom component page - follow the rule about page naming.
		super( SuMainConstants.PAGE_SPECIFIC_JBI_DATA );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #setBasicFields(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void setBasicFields( String suType, String suTypeVersion, String pluginId ) {

		super.setBasicFields( suType, suTypeVersion, pluginId );
		registerNamespace( "xslt", "http://petals.ow2.org/components/xslt/version-2" );
		registerNamespace( "petalsCDK", "http://petals.ow2.org/components/extensions/version-5" );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.pages.AbstractSuPage#
	 * setHelpContextId(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void setHelpContextId( Composite container ) {
		// Nothing.
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #fillInData(com.ebmwebsourcing.petals.tools.eclipse.su.main.wizards.generation.EclipseSuBean)
	 */
	@Override
	public void fillInData( EclipseSuBean suBean ) {

		// Import the XSL style sheet?
		XmlElement xslElement = new XmlElement();
		xslElement.setName( "xslt:stylesheet" );
		xslElement.setNillable( false );
		xslElement.setOptional( false );

		if( ! this.createXsltFile )
			getFileImportManager().registerXmlFileElement( xslElement, this.xslUrl, "" );
		else
			xslElement.setValue( DEFAULT_XSL_NAME );

		suBean.specificElements.add( xslElement );

		// Create the XSL / WSDL?
		suBean.customObjects.put( CREATE_XSL, this.createXsltFile );
		suBean.customObjects.put( CREATE_WSDL, this.createWsdlFile );
		if( this.createWsdlFile ) {
			suBean.setCreatedWsdlMarkupValue( "XsltService.wsdl" );
		}

		// Register the attachment name
		XmlElement attNameElement = new XmlElement();
		attNameElement.setName( "xslt:output-attachment-name" );
		attNameElement.setValue( this.attachmentName );
		attNameElement.setNillable( false );
		attNameElement.setOptional( true );
		suBean.specificElements.add( attNameElement );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.XsdBasedAbstractSuPage
	 * #dialogChanged()
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
					UriUtils.convertFilePathToUrl( this.xslUrl );

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


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {

		// Create the composite container and define its layout.
		final Composite container = new Composite( parent, SWT.NONE );

		// Set help link for documentation page.
		setHelpContextId( container );
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
		SelectionListener commonListener = new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
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


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #reloadDataFromConfiguration()
	 */
	@Override
	public void reloadDataFromConfiguration() {
		// nothing
	}
}
