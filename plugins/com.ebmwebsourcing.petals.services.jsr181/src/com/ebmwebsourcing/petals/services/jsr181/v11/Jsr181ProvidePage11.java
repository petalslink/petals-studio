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

package com.ebmwebsourcing.petals.services.jsr181.v11;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;

import org.eclipse.bpel.common.wsdl.helpers.UriAndUrlHelper;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.swt.DefaultSelectionListener;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.TextWithButtonComposite;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;

/**
 * Replace the default COMPONENT page.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class Jsr181ProvidePage11 extends AbstractSuWizardPage {

	private static final int LABEL_WIDTH = 65;

	private StyledText styledText;
	private boolean wsdlFirst = false;
	private String classToGenerate;
	private String wsdlUriAsString;


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.XsdBasedAbstractSuPage
	 * #dialogChanged()
	 */
	@Override
	public boolean validate() {

		// Case: WSDL first
		String errorMsg = null;
		if( this.wsdlFirst ) {
			if( StringUtils.isEmpty( this.wsdlUriAsString )) {
				errorMsg = "You must specify a WSDL URI.";

			} else {
				try {
					URI uri = UriAndUrlHelper.urlToUri( this.wsdlUriAsString );

					InputStream stream = uri.toURL().openStream();
					ByteArrayOutputStream os = new ByteArrayOutputStream();
					IoUtils.copyStream( stream, os );
					this.styledText.setText( os.toString());

				} catch( Exception e ) {
					errorMsg = "The WSDL location is not a valid URI.";
				}
			}
		}


		// Case: new Java class
		else if( StringUtils.isEmpty( this.classToGenerate )) {
			errorMsg = "You must specify the name of the class to generate.";

		} else {
			IStatus status = JavaConventions.validateJavaTypeName(
						this.classToGenerate,
						JavaCore.getOption( JavaCore.COMPILER_SOURCE ),
						JavaCore.getOption( JavaCore.COMPILER_COMPLIANCE ));

			if( status.getSeverity() != IStatus.ERROR ) {
				if( this.classToGenerate.indexOf( '.' ) < 0 ) {
					errorMsg = "The use of the default package is now allowed.";

				} else if( ! status.isOK()) {
					errorMsg = status.getMessage() + ".";
				}

			} else {
				errorMsg = status.getMessage() + ".";
			}
		}

		updateStatus( errorMsg );
		return errorMsg == null;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl( Composite parent ) {

		// Set help link for documentation page.
		setDescription( "Select the creation mode of the JAX Web Service." );

		// Create the composite container and define its layout.
		final Composite container = new Composite( parent, SWT.NONE );
		GridLayoutFactory.swtDefaults().extendedMargins( 15, 15, 20, 0 ).applyTo( container );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));
		setControl( container );


		// Start with a JAX-WS implementation
		final Button implemFirstButton = new Button( container, SWT.RADIO );
		implemFirstButton.setText( "Start with a JAX-WS implementation." );

		Composite comp = new Composite( container, SWT.NONE );
		GridLayoutFactory.swtDefaults().numColumns( 2 ).margins( 0, 5 ).applyTo( comp );
		comp.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final Label classLabel = SwtFactory.createLabel( comp, "Class Name:", "This class skeleton will be generated when this wizard completes" );
		classLabel.setLayoutData( new GridData( LABEL_WIDTH, SWT.DEFAULT ));
		final Text classText = SwtFactory.createSimpleTextField( comp, true );
		classText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				Jsr181ProvidePage11.this.classToGenerate = classText.getText().trim();
				validate();
			}
		});


		// Start with a WSDL
		final Button wsdlFirstButton = new Button( container, SWT.RADIO );
		wsdlFirstButton.setText( "Start with a WSDL definition." );
		GridDataFactory.swtDefaults().indent( 0, 15 ).applyTo( wsdlFirstButton );

		// Add controls.
		comp = new Composite( container, SWT.NONE );
		GridLayoutFactory.swtDefaults().numColumns( 3 ).margins( 0, 5 ).applyTo( comp );
		comp.setLayoutData( new GridData( GridData.FILL_BOTH ));

		final Label wsdlLabel = SwtFactory.createLabel( comp, "WSDL URL:", "The WSDL from which a JAX-WS implementation will be generated" );
		wsdlLabel.setLayoutData( new GridData( LABEL_WIDTH, SWT.DEFAULT ));
		final TextWithButtonComposite twbc = SwtFactory.createFileBrowser( comp, false, true, "WSDL" );
		twbc.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		twbc.getText().addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				Jsr181ProvidePage11.this.wsdlUriAsString = ((Text) e.widget).getText().trim();
				validate();
			}
		});

		this.styledText = SwtFactory.createXmlTextViewer( comp );
		this.styledText.getParent().setLayoutData( new GridData( SWT.FILL, SWT.FILL, true, true, 3, 1 ));
		this.styledText.setEditable( false );


		// Listeners
		SelectionListener commonListener = new DefaultSelectionListener() {

			@Override
			public void widgetSelected( SelectionEvent e ) {
				Jsr181ProvidePage11.this.wsdlFirst = wsdlFirstButton.getSelection();

				classLabel.setEnabled( ! Jsr181ProvidePage11.this.wsdlFirst );
				classText.setEnabled( ! Jsr181ProvidePage11.this.wsdlFirst );

				wsdlLabel.setEnabled( Jsr181ProvidePage11.this.wsdlFirst );
				twbc.getText().setEnabled( Jsr181ProvidePage11.this.wsdlFirst );
				twbc.getButton().setEnabled( Jsr181ProvidePage11.this.wsdlFirst );
				Jsr181ProvidePage11.this.styledText.setEnabled( Jsr181ProvidePage11.this.wsdlFirst );

				validate();
			}
		};

		implemFirstButton.addSelectionListener( commonListener );
		wsdlFirstButton.addSelectionListener( commonListener );


		// Initialize the page
		implemFirstButton.setSelection( true );
		implemFirstButton.notifyListeners( SWT.Selection, new Event());
		if( getErrorMessage() != null ) {
			updateStatus( null );
			setPageComplete( false );
		}
	}


	/**
	 * @return the wsdlFirst
	 */
	public boolean isWsdlFirst() {
		return this.wsdlFirst;
	}


	/**
	 * @return the wsdlUriAsString
	 */
	public String getWsdlUriAsString() {
		return this.wsdlUriAsString;
	}


	/**
	 * @return the classToGenerate
	 */
	public String getClassToGenerate() {
		return this.classToGenerate;
	}
}
