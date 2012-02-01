/****************************************************************************
 *
 * Copyright (c) 2008-2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.ejb.wizards;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.xml.namespace.QName;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import com.ebmwebsourcing.petals.common.internal.provisional.swt.ListWithButtonComposite;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.TextWithButtonComposite;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.jbi.editor.form.cdk5.model.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.ejb.Messages;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.sun.java.xml.ns.jbi.JbiPackage;

/**
 * Located after the CHOICE page.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EJBCustomSpecificationPage12 extends AbstractSuWizardPage {

	private ListWithButtonComposite ejbFilesViewer, jeeFilesViewer;
	private final Set<File> ejbFiles = new HashSet<File> ();
	private String className;
	private String wsdlName;


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.XsdBasedAbstractSuPage
	 * #dialogChanged()
	 */
	@Override
	public boolean validate() {

		// Validate the class
		String error = null;
		if( StringUtils.isEmpty( this.className )) {
			error = "You have to define the name of the EJB's remote interface.";
		} else if( StringUtils.isEmpty( this.wsdlName )) {
			error = "You have to give a name to the WSDL that will be generated.";
		} else if( ! this.wsdlName.endsWith( ".wsdl" )) {
			error = "The WSDL name must end with the '.wsdl' extension.";
		}

		// Fill-in the next page...
		if( error == null ) {
			String simpleName = getSimpleClassName( this.className );

			// CXF will take whatever value we put
			// These values are the ones generated by Java to EasyWSDL
			String[] parts = this.className.split( "\\." );
			StringBuilder ns = new StringBuilder( "http://" );
			if( parts.length > 1 ) {
				for( int i=parts.length-2; i>0; i-- )
					ns.append( parts[ i ] + "." );
				ns.append( parts[ 0 ]);
			}
			// CXF 2.2.x also adds a "/" at the end of the name space
			// We assume it is the general behavior of CXF
			ns.append( "/" );

			String namespace = ns.toString();
			getNewlyCreatedEndpoint().eSet( JbiPackage.Literals.ABSTRACT_ENDPOINT__ENDPOINT_NAME, simpleName + "Port");
			getNewlyCreatedEndpoint().eSet( JbiPackage.Literals.ABSTRACT_ENDPOINT__SERVICE_NAME, new QName( namespace, simpleName ));
			getNewlyCreatedEndpoint().eSet( JbiPackage.Literals.ABSTRACT_ENDPOINT__INTERFACE_NAME, new QName( namespace, simpleName + "PortType" ));
			getNewlyCreatedEndpoint().eSet( Cdk5Package.Literals.CDK5_PROVIDES__WSDL, this.wsdlName );
		}

		setPageComplete( error == null );
		return error == null;
	}


	/**
	 * @param qualifiedName
	 * @return
	 */
	private String getSimpleClassName( String qualifiedClassName ) {
		String[] parts = qualifiedClassName.split( "\\." );
		return parts[ parts.length - 1 ];
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl( Composite parent ) {

		// Create the composite container and define its layout.
		final Composite container = new Composite( parent, SWT.NONE );

		GridLayout layout = new GridLayout();
		layout.marginLeft = layout.marginRight = 5;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));


		// Business Libraries
		new Label( container, SWT.NONE ).setText( Messages.ejbLibsText );
		this.ejbFilesViewer = SwtFactory.createFileListViewer( container, "Jar", this.ejbFiles );
		this.ejbFilesViewer.addModificationListener( new Listener() {
			@Override
			public void handleEvent( Event event ) {
				getEjbWizard().setEJBFiles( EJBCustomSpecificationPage12.this.ejbFiles );
				validate();
			}
		});

		// Server Libraries
		final Set<File> jeeFiles = new HashSet<File> ();
		Label l = new Label( container, SWT.NONE );
		l.setText( Messages.serverLibsText );
		GridData layoutData = new GridData();
		layoutData.verticalIndent = 9;
		l.setLayoutData( layoutData );

		this.jeeFilesViewer = SwtFactory.createFileListViewer( container, "Jar", jeeFiles );
		this.jeeFilesViewer.addModificationListener( new Listener() {
			@Override
			public void handleEvent( Event event ) {
				getEjbWizard().setJEEFiles( jeeFiles );
				validate();
			}
		});


		// Class browser
		Composite subC = SwtFactory.createComposite( container );
		SwtFactory.applyNewGridLayout( subC, 2, false, 9, 0, 0, 0 );
		subC.setLayoutData( new GridData( GridData.FILL_BOTH ));

		new Label( subC, SWT.NONE ).setText( "EJB's Remote Interface:" );
		final TextWithButtonComposite classBrowser = new TextWithButtonComposite( subC );
		classBrowser.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		classBrowser.getText().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		classBrowser.getButton().setText( "Browse..." );
		classBrowser.getButton().addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				String cName = openClassSelectionDialog();
				if( cName != null ) {
					classBrowser.getText().setText( cName );
					classBrowser.getText().setSelection( cName.length());
				}
			}
		});


		// The WSDL name
		new Label( subC, SWT.NONE ).setText( "WSDL File Name:" );
		final Text wsdlText = new Text( subC, SWT.SINGLE | SWT.BORDER );
		wsdlText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		wsdlText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				EJBCustomSpecificationPage12.this.wsdlName = wsdlText.getText().trim();
				getEjbWizard().setWsdlName( wsdlText.getText().trim());
				validate();
			}
		});

		classBrowser.getText().addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				EJBCustomSpecificationPage12.this.className = classBrowser.getText().getText().trim();
				getEjbWizard().setEjbClassName( EJBCustomSpecificationPage12.this.className );
				String newWsdlName = getSimpleClassName( EJBCustomSpecificationPage12.this.className ) + ".wsdl";
				wsdlText.setText( newWsdlName );
				wsdlText.setSelection( newWsdlName.length());
			}
		});


		// Complete the page
		validate();
		String msg = getErrorMessage();
		if( msg != null ) {
			setErrorMessage( null );
			setMessage( msg, IMessageProvider.INFORMATION );
		}

		setControl( container );
	}

	/**
	 * Opens a dialog to select a class contained in the JAR files.
	 * @return the selected class name, or null if CANCEL was clicked
	 */
	private String openClassSelectionDialog() {

		// Get all the classes in the JAR
		List<String> classNames = new ArrayList<String> ();
		for( File f : this.ejbFiles ) {
			JarFile jarFile;
			try {
				jarFile = new JarFile( f );

			} catch( IOException e ) {
				continue;
			}

			Enumeration<JarEntry> entries = jarFile.entries();
			while( entries.hasMoreElements()) {
				String entryName = entries.nextElement().getName();
				if( entryName.endsWith( ".class" )
							&& ! entryName.contains( "$" )) {
					entryName = entryName.substring( 0, entryName.length() - 6 );
					entryName = entryName.replaceAll( "/", "." );
					classNames.add( entryName );
				}
			}
		}

		// Display them in a dialog
		ElementListSelectionDialog dlg =
					new ElementListSelectionDialog( getShell(), new LabelProvider());

		dlg.setTitle( "Class Selection" );
		dlg.setElements( classNames.toArray());
		dlg.setMessage( "Select the EJB's remote interface" );
		dlg.setFilter( this.className != null ? this.className : "*" );
		dlg.setAllowDuplicates( false );
		dlg.setIgnoreCase( false );
		dlg.setMultipleSelection( false );

		String result = null;
		if( dlg.open() == Window.OK )
			result = (String) dlg.getFirstResult();

		return result;
	}


	/**
	 * @return the EJB wizard
	 */
	public EjbWizard13 getEjbWizard() {
		return (EjbWizard13) getWizard();
	}
}
