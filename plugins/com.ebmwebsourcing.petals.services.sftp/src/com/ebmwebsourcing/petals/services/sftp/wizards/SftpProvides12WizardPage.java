package com.ebmwebsourcing.petals.services.sftp.wizards;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjecttUIHelper;
import com.ebmwebsourcing.petals.services.sftp.sftp.SftpPackage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;

public class SftpProvides12WizardPage extends AbstractSuPage {

	private DataBindingContext dbc;

	public void createControl(Composite parent) {
		dbc = new DataBindingContext();
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		
		EObjecttUIHelper.generateWidgets(getNewlyCreatedEndpoint(), new FormToolkit(getShell().getDisplay()), container, null, dbc, 
				SftpPackage.Literals.SFTP_PROVIDES__HOST,
				SftpPackage.Literals.SFTP_PROVIDES__PORT,
				SftpPackage.Literals.SFTP_PROVIDES__USER,
				SftpPackage.Literals.SFTP_PROVIDES__PASSWORD,
				SftpPackage.Literals.SFTP_PROVIDES__PRIVATEKEY,
				SftpPackage.Literals.SFTP_PROVIDES__PASSPHRASE,
				SftpPackage.Literals.SFTP_PROVIDES__FOLDER,
				SftpPackage.Literals.SFTP_PROVIDES__MAX_IDLE_TIME,
				SftpPackage.Literals.SFTP_PROVIDES__OVERWRITE);
		
		setControl(container);
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void dispose() {
		super.dispose();
		dbc.dispose();
	}

}
