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
public class ValidationProvideSpecificPage extends AbstractSuPage {

	public final static String DEFAULT_XSD_NAME = "validation.xsd";
	public final static String CREATE_XSD = "create.xsd";
	public final static String CREATE_WSDL = "create.wsdl";
	public final static String XSD_TNS = "xsd.tns";

	private String xsdUrl;
	private String targetNamespace = "http://petals.ow2.org";
	private boolean createXsdFile = true;
	private boolean createWsdlFile = false;


	/**
	 * Empty constructor. Required empty to be instantiated by the main plug-in.
	 */
	public ValidationProvideSpecificPage() {
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
		registerNamespace( "validation", "http://petals.ow2.org/components/validation/version-1" );
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

		// Import the XSD?
		XmlElement xsdElement = new XmlElement();
		xsdElement.setName( "validation:schema" );
		xsdElement.setNillable( false );
		xsdElement.setOptional( false );

		if( ! this.createXsdFile )
			getFileImportManager().registerXmlFileElement( xsdElement, this.xsdUrl, "" );
		else
			xsdElement.setValue( DEFAULT_XSD_NAME );

		suBean.specificElements.add( xsdElement );

		// Create the XSD?
		suBean.customObjects.put( CREATE_XSD, this.createXsdFile );
		suBean.customObjects.put( XSD_TNS, this.targetNamespace );
		suBean.customObjects.put( CREATE_WSDL, this.createWsdlFile );
		if( this.createWsdlFile ) {
			suBean.setCreatedWsdlMarkupValue( "ValidationService.wsdl" );
		}
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.XsdBasedAbstractSuPage
	 * #dialogChanged()
	 */
	@Override
	public boolean validate() {

		boolean valid = true;
		if( ! this.createXsdFile ) {
			if( StringUtils.isEmpty( this.xsdUrl )) {
				updateStatus( "You must select the XML schema to import." );
				valid = false;
			}
			else {
				try {
					UriUtils.convertFilePathToUrl( this.xsdUrl );

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

		if( this.createWsdlFile && ! this.createXsdFile )
			setMessage( "The generated WSDL might not reflect the content of the imported XML schema.", IMessageProvider.WARNING );
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

		final Text xsdText = new Text( comp, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY );
		xsdText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		xsdText.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				ValidationProvideSpecificPage.this.xsdUrl = xsdText.getText().trim();
				validate();
			}
		});

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
		createWsdlButton.setSelection( this.createWsdlFile );

		GridData layoutData = new GridData();
		layoutData.verticalIndent = 5;
		createWsdlButton.setLayoutData( layoutData );

		createWsdlButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				ValidationProvideSpecificPage.this.createWsdlFile = ! ValidationProvideSpecificPage.this.createWsdlFile;
				validate();
			}
		});


		// Listeners
		SelectionListener commonListener = new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				ValidationProvideSpecificPage.this.createXsdFile = createXsdButton.getSelection();

				xsdLabel.setEnabled( ! ValidationProvideSpecificPage.this.createXsdFile );
				xsdText.setEnabled( ! ValidationProvideSpecificPage.this.createXsdFile );
				xsdBrowserButton.setEnabled( ! ValidationProvideSpecificPage.this.createXsdFile );

				tnsLabel.setEnabled( ValidationProvideSpecificPage.this.createXsdFile );
				tnsText.setEnabled( ValidationProvideSpecificPage.this.createXsdFile );

				validate();
			}
		};

		createXsdButton.addSelectionListener( commonListener );
		importXsdButton.addSelectionListener( commonListener );


		// Initialize the page
		createXsdButton.setSelection( this.createXsdFile );
		createXsdButton.notifyListeners( SWT.Selection, new Event());
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
