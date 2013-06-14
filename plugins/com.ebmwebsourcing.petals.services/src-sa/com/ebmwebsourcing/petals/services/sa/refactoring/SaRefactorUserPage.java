/****************************************************************************
 * 
 * Copyright (c) 2010-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.sa.refactoring;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.refactoring.AbstractRefactoringWizardPage;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SaRefactorUserPage extends AbstractRefactoringWizardPage {

	private Text text;


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

		Label l = new Label( container, SWT.NONE );
		l.setText( "New name:" );

		this.text = new Text( container, SWT.SINGLE | SWT.BORDER );
		this.text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.text.setText( getInfo().getNewName());
		this.text.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				getInfo().setNewName( SaRefactorUserPage.this.text.getText());
				validate();
			}
		});

		setControl( container );
		validate();
		this.text.selectAll();
	}


	/**
	 * Validates the input.
	 */
	private void validate() {

		if( getInfo().getProject().getName().equalsIgnoreCase( getInfo().getNewName()))
			setPageComplete( false );
		else if( StringUtils.isEmpty( getInfo().getNewName()))
			setPageComplete( false );
		else
			setPageComplete( true );
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
