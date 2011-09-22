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

package com.ebmwebsourcing.petals.services.pojo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.XmlElement;
import com.ebmwebsourcing.petals.services.su.wizards.SuMainConstants;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.swt.AbstractWizardListener;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.swt.ClassBrowser;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.swt.FileBrowser;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;

/**
 * Replace the default COMPONENT page.
 * @author Vincent Zurczak - EBM WebSourcing
 */
@SuppressWarnings( "restriction" )
public class PojoProvideSpecificPage20 extends AbstractSuPage {

	/** The text box for the jar files. */
	private FileBrowser jarSelection;

	/** The text box for the class name. */
	private ClassBrowser classSelection;

	private boolean useExistingImplementation = false;



	/**
	 * Empty constructor. Required empty to be instantiated by the main plug-in.
	 */
	public PojoProvideSpecificPage20() {
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
		registerNamespace( "pojo", "http://petals.ow2.org/components/pojo/version-2.0" );
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

		// Add jar files to the list of files to import.
		if( this.useExistingImplementation ) {
			for( String fileUrl : this.jarSelection.getFilePaths( true ))
				getFileImportManager().registerXmlFileElement( new XmlElement(), fileUrl, "" );
		}

		// Make a Java project?
		suBean.customObjects.put( "create-java-project", ! this.useExistingImplementation );

		// Save class name.
		XmlElement classNameElement = new XmlElement();
		classNameElement.setName( "pojo:class-name" );

		String className = null;
		if( this.useExistingImplementation )
			className = this.classSelection.getValue();
		else
			className = "<!-- Set the qualified name of the POJO class. -->";

		classNameElement.setValue( className );
		classNameElement.setNillable( false );
		classNameElement.setOptional( false );

		suBean.specificElements.add( classNameElement );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.XsdBasedAbstractSuPage
	 * #dialogChanged()
	 */
	@Override
	public boolean validate() {

		boolean valid = true;
		if( this.useExistingImplementation ) {
			if( this.jarSelection.getFilePaths( false ).length == 0 ) {
				updateStatus( "You have to provide at least one *.jar file." );
				valid = false;
			}
			else {
				String error = this.classSelection.validate();
				updateStatus( error );
				valid = error == null;
			}
		}
		else {
			updateStatus( null );
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
		setDescription( "Select the Java resources of the POJO." );

		GridLayout layout = new GridLayout ();
		layout.marginLeft = layout.marginRight = 15;
		layout.marginTop = 20;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));

		// Create a Java project
		final Button createJavaProjectButton = new Button( container, SWT.RADIO );
		createJavaProjectButton.setText( "Create a Java project." );
		createJavaProjectButton.setSelection( ! this.useExistingImplementation );
		createJavaProjectButton.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				PojoProvideSpecificPage20.this.useExistingImplementation = false;
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				PojoProvideSpecificPage20.this.useExistingImplementation = false;
			}
		});

		// Add check button
		final Button useExistingImplemButton = new Button( container, SWT.RADIO );
		useExistingImplemButton.setText( "Use an existing POJO implementation." );
		useExistingImplemButton.setSelection( this.useExistingImplementation );

		// Add controls.
		this.jarSelection =
					new FileBrowser( "", container, "Jar Files: ", "", "The jar(s) to import", false, false, false );
		this.jarSelection.setFilterNames( new String[] { "Jar (*.jar)" });
		this.jarSelection.setFilterExtensions( new String[] { "*.jar" });

		this.classSelection = new ClassBrowser(
					"", container, "Service Class: ", "",
					"The class that implements the service to expose.", false, false );
		this.classSelection.addListener( new AbstractWizardListener () {
			public void valueHasChanged( ) {
				validate();
			}
		});

		// Listeners
		this.jarSelection.addListener( new AbstractWizardListener () {
			public void valueHasChanged () {
				String[] jars = PojoProvideSpecificPage20.this.jarSelection.getFilePaths( false );
				List<File> jarFiles = new ArrayList<File>( jars.length );

				for( String filePath : jars ) {
					File file = new File( filePath );
					jarFiles.add( file );
				}

				PojoProvideSpecificPage20.this.classSelection.setJarFiles( jarFiles );
				validate();
			}
		});

		useExistingImplemButton.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				PojoProvideSpecificPage20.this.useExistingImplementation = useExistingImplemButton.getSelection();
				PojoProvideSpecificPage20.this.jarSelection.setEnabled( PojoProvideSpecificPage20.this.useExistingImplementation );
				PojoProvideSpecificPage20.this.classSelection.setEnabled( PojoProvideSpecificPage20.this.useExistingImplementation );
				validate();
			}
		});


		useExistingImplemButton.notifyListeners( SWT.Selection, new Event());
		if( ! validate()) {
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
