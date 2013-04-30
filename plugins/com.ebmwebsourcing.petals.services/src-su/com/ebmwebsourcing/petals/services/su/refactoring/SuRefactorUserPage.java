/****************************************************************************
 * 
 * Copyright (c) 2010-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.su.refactoring;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.generation.JbiUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.refactoring.AbstractRefactoringWizardPage;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SuRefactorUserPage extends AbstractRefactoringWizardPage {

	private Text text;
	private Button derivedButton, serviceNameButton;


	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {

		Composite container = new Composite( parent, SWT.NONE );
		GridLayout gridLayout = new GridLayout( 2, false );
		gridLayout.marginWidth = 10;
		gridLayout.marginHeight = 10;
		container.setLayout( gridLayout );

		container.setLayoutData( new GridData( GridData.FILL_BOTH ));
		initializeDialogUnits( container );
		Dialog.applyDialogFont( container );

		// Name
		Label l = new Label( container, SWT.NONE );
		l.setText( "New name:" );

		this.text = new Text( container, SWT.SINGLE | SWT.BORDER );
		this.text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.text.setText( getInfo().getNewName());
		this.text.selectAll();

		this.text.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				getInfo().setNewName( SuRefactorUserPage.this.text.getText());
				validate();
			}
		});

		// Update the service name
		this.serviceNameButton = new Button( container, SWT.CHECK );
		this.serviceNameButton.setText( "Extract and update the service name" );
		GridData layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		layoutData.verticalIndent = 10;
		this.serviceNameButton.setLayoutData( layoutData );

		this.serviceNameButton.setSelection( true );
		getInfo().getOptions().put( RefactoringExtensionForSu.UPDATE_SERVICE_NAME, true );
		getInfo().getOptions().put( RefactoringExtensionForSu.UPDATE_DERIVED_NAMES, false );

		this.serviceNameButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				Boolean update = getInfo().getOptions().get( RefactoringExtensionForSu.UPDATE_SERVICE_NAME );
				if( update == null )
					update = true;
				else
					update = ! update;

				getInfo().getOptions().put( RefactoringExtensionForSu.UPDATE_SERVICE_NAME, update );
				validate();
			}
		});

		// Update the derived names
		this.derivedButton = new Button( container, SWT.CHECK );
		this.derivedButton.setText( "Update the service name's derivations" );
		layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		this.derivedButton.setLayoutData( layoutData );

		this.derivedButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				Boolean update = getInfo().getOptions().get( RefactoringExtensionForSu.UPDATE_DERIVED_NAMES );
				if( update == null )
					update = true;
				else
					update = ! update;

				getInfo().getOptions().put( RefactoringExtensionForSu.UPDATE_DERIVED_NAMES, update );
				validate();
			}
		});

		setControl( container );
		validate();
	}


	/**
	 * Validates the input.
	 */
	private void validate() {

		this.derivedButton.setEnabled( this.serviceNameButton.getSelection());
		String msg = null;
		if( this.serviceNameButton.getSelection()
					&& ! JbiUtils.isSuName( getInfo().getProject().getName()))
			msg = "The original project name does respect the SU naming convention.\nService name extraction cannot be performed.";

		boolean complete = true;
		if( getInfo().getProject().getName().equalsIgnoreCase( getInfo().getNewName()))
			complete = false;
		else if( StringUtils.isEmpty( getInfo().getNewName()))
			complete = false;

		setErrorMessage( msg );
		setPageComplete( complete && msg == null );

		if( msg == null ) {
			if( ! JbiUtils.isSuName( getInfo().getNewName()))
				msg = "The new project name does respect the SU naming convention.";
			setMessage( msg, IMessageProvider.WARNING );
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ltk.ui.refactoring.UserInputWizardPage
	 * #setVisible(boolean)
	 */
	@Override
	public void setVisible( boolean visible ) {
		super.setVisible( visible );

		Rectangle rect = getShell().getDisplay().getBounds();
		if( visible ) {
			this.text.forceFocus();
		} else {
			getShell().setSize( rect.width * 4 / 5, rect.height * 4 / 5 );
		}

		Point size = getShell().getSize();
		getShell().setLocation((rect.width - size.x) / 2, (rect.height - size.y) / 2);
	}
}
