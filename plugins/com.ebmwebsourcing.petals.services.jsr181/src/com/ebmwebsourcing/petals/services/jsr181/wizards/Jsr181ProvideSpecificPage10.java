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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;
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
import com.ebmwebsourcing.petals.common.internal.provisional.utils.UriUtils;
import com.ebmwebsourcing.petals.services.su.wizards.SuMainConstants;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;

/**
 * Replace the default COMPONENT page.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class Jsr181ProvideSpecificPage10 extends AbstractSuPage {

	public static final String CONFIG_WSDL_FIRST = "jsr181-wsdl-first";
	public static final String CONFIG_WSDL_URI = "jsr181-wsdl-uri";

	private static final int LABEL_WIDTH = 65;

	private boolean wsdlFirst = false;
	private String classToGenerate;
	private String wsdlUriAsString;



	/**
	 * Empty constructor. Required empty to be instantiated by the main plug-in.
	 */
	public Jsr181ProvideSpecificPage10() {
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
		registerNamespace( "jsr181", "http://petals.ow2.org/components/jsr181/version-1.0" );
		registerNamespace( "petalsCDK", "http://petals.ow2.org/components/extensions/version-4.0" );
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

		suBean.customObjects.put( CONFIG_WSDL_FIRST, Boolean.valueOf( this.wsdlFirst ));

		// WSDL first
		if( this.wsdlFirst ) {
			suBean.customObjects.put( CONFIG_WSDL_URI, this.wsdlUriAsString );
		}

		// Implementation first
		else {
			// Save class name.
			XmlElement classNameElement = new XmlElement();
			classNameElement.setName( "jsr181:class" );

			String className = this.classToGenerate;
			classNameElement.setValue( className );
			classNameElement.setNillable( false );
			classNameElement.setOptional( false );

			suBean.specificElements.add( classNameElement );

			// Add the service name...
			int lastDotIndex = className.lastIndexOf( '.' );
			String packageName, simpleClassName;

			// The page validation guarantees that the class name respects Java conventions
			if( lastDotIndex > 0 ) {
				simpleClassName = className.substring( lastDotIndex + 1 );
				packageName = className.substring( 0, lastDotIndex );
			}
			else {
				simpleClassName = className;
				packageName = "";
			}

			suBean.setServiceName( simpleClassName );
			suBean.setInterfaceName( simpleClassName );
			suBean.setEndpointName( simpleClassName + "Port" );
			suBean.setBc( false );

			String[] parts = packageName.trim().split( "\\." );
			StringBuffer sb = new StringBuffer( "http://" );
			for( int i=parts.length - 1; i>0; i-- )
				sb.append( parts[ i ] + "." );
			sb.append( parts[ 0 ]);

			suBean.setServiceNamespaceUri( sb.toString());
			suBean.setInterfaceNamespaceUri( sb.toString());
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
		if( this.wsdlFirst ) {
			try {
				UriUtils.urlToUri( this.wsdlUriAsString );
				updateStatus( null );

			} catch( Exception e ) {
				updateStatus( "The WSDL location is not a valid URI." );
				valid = false;
			}
		}
		else {
			IStatus status = JavaConventions.validateJavaTypeName(
						this.classToGenerate,
						JavaCore.getOption( JavaCore.COMPILER_SOURCE ),
						JavaCore.getOption( JavaCore.COMPILER_COMPLIANCE ));

			if( status.isOK()) {
				if( this.classToGenerate.indexOf( '.' ) < 0 ) {
					updateStatus( "The use of the default package is now allowed." );
					valid = false;
				}
				else
					updateStatus( null );
			}
			else if( status.getSeverity() != IStatus.ERROR )
				updateStatus( status.getMessage() + "." );
			else {
				updateStatus( status.getMessage() + "." );
				valid = false;
			}
		}

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
		setDescription( "Select creation mode of the JAX Web Service." );

		GridLayout layout = new GridLayout ();
		layout.marginLeft = layout.marginRight = 15;
		layout.marginTop = 20;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));


		// Start with a JAX-WS implementation
		final Button implemFirstButton = new Button( container, SWT.RADIO );
		implemFirstButton.setText( "Start with a JAX-WS implementation." );
		implemFirstButton.setSelection( ! this.wsdlFirst );

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
		classText.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				Jsr181ProvideSpecificPage10.this.classToGenerate = classText.getText().trim();
				validate();
			}
		});


		// Start with a WSDL
		final Button wsdlFirstButton = new Button( container, SWT.RADIO );
		wsdlFirstButton.setText( "Start with a WSDL definition." );
		wsdlFirstButton.setSelection( this.wsdlFirst );

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
		wsdlText.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				Jsr181ProvideSpecificPage10.this.wsdlUriAsString = wsdlText.getText().trim();
				validate();
			}
		});

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
		SelectionListener commonListener = new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				Jsr181ProvideSpecificPage10.this.wsdlFirst = wsdlFirstButton.getSelection();

				classLabel.setEnabled( ! Jsr181ProvideSpecificPage10.this.wsdlFirst );
				classText.setEnabled( ! Jsr181ProvideSpecificPage10.this.wsdlFirst );

				wsdlLabel.setEnabled( Jsr181ProvideSpecificPage10.this.wsdlFirst );
				wsdlText.setEnabled( Jsr181ProvideSpecificPage10.this.wsdlFirst );
				wsdlBrowserButton.setEnabled( Jsr181ProvideSpecificPage10.this.wsdlFirst );

				validate();
			}
		};

		implemFirstButton.addSelectionListener( commonListener );
		wsdlFirstButton.addSelectionListener( commonListener );


		// Initialize the page
		wsdlFirstButton.notifyListeners( SWT.Selection, new Event());
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
