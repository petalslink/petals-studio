/****************************************************************************
 *
 * Copyright (c) 2011-2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/
package com.ebmwebsourcing.petals.components.drivers;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class DuoInputDialog extends TitleAreaDialog {

	private String name, version;


	/**
	 * Constructor.
	 * @param parentShell
	 */
	public DuoInputDialog( Shell parentShell ) {
		super( parentShell );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog
	 * #createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea( Composite parent ) {

		Composite container = new Composite((Composite) super.createDialogArea( parent ), SWT.None );
		container.setLayout( new GridLayout( 2, false ));
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));

		setTitle( "Shared Library Description" );
		setMessage( "Define the name and the version of a shared library to use." );
		getShell().setText( "Shared Library Description" );

		Label l = new Label( container, SWT.NONE );
		l.setText( "Name:" );
		l.setToolTipText( "The name of the shared library" );

		Text t = new Text( container, SWT.SINGLE | SWT.BORDER );
		t.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		if( this.name != null )
			t.setText( this.name );

		t.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				DuoInputDialog.this.name = ((Text) e.widget).getText().trim();
				validate();
			}
		});

		l = new Label( container, SWT.NONE );
		l.setText( "Version:" );
		l.setToolTipText( "The version of the shared library" );

		t = new Text( container, SWT.SINGLE | SWT.BORDER );
		t.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		if( this.version != null )
			t.setText( this.version );

		t.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				DuoInputDialog.this.version = ((Text) e.widget).getText().trim();
				validate();
			}
		});

		return container;
	}


	/**
	 * Validates the input.
	 */
	private void validate() {

		String msg = null;
		if( StringUtils.isEmpty( this.name ))
			msg = "You have to provide the shared library's name.";

		// FIXME: Version is mandatory in Petals 3.1.1, but will be optional in Petals 4
		else if( StringUtils.isEmpty( this.version ))
			msg = "You have to provide the shared library's version.";

		setErrorMessage( msg );
		Button okButton = getButton( IDialogConstants.OK_ID );
		if( okButton != null )
			okButton.setEnabled( msg == null );
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}


	/**
	 * @return the version
	 */
	public String getVersion() {
		return this.version;
	}


	/**
	 * @param name the name to set
	 */
	public void setName( String name ) {
		this.name = name;
	}


	/**
	 * @param version the version to set
	 */
	public void setVersion( String version ) {
		this.version = version;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.dialogs.Dialog
	 * #createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createButtonsForButtonBar( Composite parent ) {
		super.createButtonsForButtonBar( parent );
		validate();
		setErrorMessage( null );
	}
}
