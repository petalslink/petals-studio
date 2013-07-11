/****************************************************************************
 *
 * Copyright (c) 2008-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.pojo.wizards;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import com.ebmwebsourcing.petals.common.internal.provisional.swt.DefaultSelectionListener;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.ListWithButtonComposite;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.TextWithButtonComposite;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.services.pojo.Messages;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;

/**
 * Replace the default COMPONENT page.
 * @author Vincent Zurczak - EBM WebSourcing
 * @author Micka�l Istria - EBM WebSourcing
 */
public class PojoProvideSpecificPage22 extends AbstractSuWizardPage {

	private boolean useExistingImplementation = false;
	private final Set<File> jarFiles = new HashSet<File> ();
	private String className;


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.XsdBasedAbstractSuPage
	 * #dialogChanged()
	 */
	@Override
	public boolean validate() {

		String msg = null;
		if( this.useExistingImplementation ) {
			if( this.jarFiles.size() == 0 )
				msg = "You have to provide at least one *.jar file.";
			else if( StringUtils.isEmpty( this.className ))
				msg = "You have to provide the name of the POJO class.";
		}

		updateStatus( msg );
		return msg == null;
	}


	/**
	 * @return the jarFiles
	 */
	public Set<File> getJarFiles() {
		return this.jarFiles;
	}


	/**
	 * @return the useExistingImplementation
	 */
	public boolean isUseExistingImplementation() {
		return this.useExistingImplementation;
	}


	/**
	 * @return the className
	 */
	public String getClassName() {
		return this.className;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl( Composite parent ) {

		// Create the composite container and define its layout.
		setDescription( "Select the Java resources of the POJO." );
		final Composite container = new Composite( parent, SWT.NONE );
		GridLayoutFactory.swtDefaults().numColumns( 2 ).extendedMargins( 15, 15, 20, 0 ).applyTo( container );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));
		setControl( container );


		// Case 1: create a sample POJO
		Button createJavaProjectButton = new Button( container, SWT.RADIO );
		createJavaProjectButton.setText( "Create a Java project and a sample POJO class." );
		createJavaProjectButton.setLayoutData(new GridData(SWT.DEFAULT, SWT.DEFAULT, false, false, 2, 1));
		createJavaProjectButton.addSelectionListener( new DefaultSelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				validate();
			}
		});


		// Case 2: use an existing implementation, already packaged in a JAR
		final Button useExistingImplemButton = new Button( container, SWT.RADIO );
		useExistingImplemButton.setText( "Use an existing POJO implementation." );
		useExistingImplemButton.setSelection( this.useExistingImplementation );
		useExistingImplemButton.setLayoutData(new GridData(SWT.DEFAULT, SWT.DEFAULT, false, false, 3, 1));

		// The list of JARs
		Label l = new Label(container, SWT.NONE);
		l.setText( Messages.classpath );
		l.setLayoutData( new GridData( SWT.DEFAULT, SWT.TOP, false, true ));
		final ListWithButtonComposite lwb = SwtFactory.createFileListViewer( container, "Jar", this.jarFiles );
		lwb.addModificationListener( new Listener() {
			@Override
			public void handleEvent( Event event ) {
				validate();
			}
		});


		// The class selection
		new Label( container, SWT.NONE ).setText( Messages.className);
		final TextWithButtonComposite classBrowser = new TextWithButtonComposite( container );
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


		// Hack for a nicer UI
		int width1 = lwb.getRemoveButton().computeSize( SWT.DEFAULT, SWT.DEFAULT ).x;
		int width2 = lwb.getAddButton().computeSize( SWT.DEFAULT, SWT.DEFAULT ).x;
		int width = Math.max( width1, width2 );
		GridDataFactory.swtDefaults().hint( width, SWT.DEFAULT ).applyTo( classBrowser.getButton());


		// Listeners
		classBrowser.getText().addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				PojoProvideSpecificPage22.this.className = ((Text) e.widget).getText();
				validate();
			}
		});

		Listener activationListener = new Listener() {
			@Override
			public void handleEvent( Event event ) {
				PojoProvideSpecificPage22.this.useExistingImplementation = useExistingImplemButton.getSelection();

				classBrowser.getText().setEnabled( PojoProvideSpecificPage22.this.useExistingImplementation );
				classBrowser.getButton().setEnabled( PojoProvideSpecificPage22.this.useExistingImplementation );
				lwb.setEnabled( PojoProvideSpecificPage22.this.useExistingImplementation );
				validate();
			}
		};

		createJavaProjectButton.addListener( SWT.Selection, activationListener );
		useExistingImplemButton.addListener( SWT.Selection, activationListener );


		// Initialize
		createJavaProjectButton.setSelection(true);
		useExistingImplemButton.setSelection(false);
		createJavaProjectButton.notifyListeners( SWT.Selection, new Event());

		validate();
		setErrorMessage( null );
	}


	/**
	 * Opens a dialog to select a class contained in the JAR files.
	 * @return the selected class name, or null if CANCEL was clicked
	 */
	private String openClassSelectionDialog() {

		// Get all the classes in the JAR
		List<String> classNames = new ArrayList<String> ();
		for( File f : this.jarFiles ) {
			JarFile jarFile = null;
			try {
				jarFile = new JarFile( f );
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

			} catch( IOException e ) {
				continue;

			} finally {
				if( jarFile != null ) {
					try {
						jarFile.close();
					} catch( IOException e1 ) {
						// nothing
					}
				}
			}
		}

		// Display them in a dialog
		ElementListSelectionDialog dlg =
					new ElementListSelectionDialog( getShell(), new LabelProvider());

		dlg.setTitle( "Class Selection" );
		dlg.setElements( classNames.toArray());
		dlg.setMessage( "Select the POJO class." );
		dlg.setFilter( this.className != null ? this.className : "*" );
		dlg.setAllowDuplicates( false );
		dlg.setIgnoreCase( false );
		dlg.setMultipleSelection( false );

		String result = null;
		if( dlg.open() == Window.OK )
			result = (String) dlg.getFirstResult();

		return result;
	}
}
