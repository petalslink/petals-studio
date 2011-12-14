package com.ebmwebsourcing.petals.services.mail.wizards;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjecttUIHelper;
import com.ebmwebsourcing.petals.services.mail.mail.MailPackage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;

public class MailConsumesWizardPage extends AbstractSuPage {

	private DataBindingContext dbc;

	public void createControl(Composite parent) {
		dbc = new DataBindingContext();
		
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		FormToolkit toolkit = new FormToolkit(getShell().getDisplay());
		EObjecttUIHelper.generateWidgets(getNewlyCreatedEndpoint(), toolkit, container, null, dbc,
				MailPackage.Literals.MAIL_SERVICE_COMMON__SCHEME,
				MailPackage.Literals.MAIL_SERVICE_COMMON__HOST,
				MailPackage.Literals.MAIL_SERVICE_COMMON__PORT,
				MailPackage.Literals.MAIL_SERVICE_COMMON__USER,
				MailPackage.Literals.MAIL_SERVICE_COMMON__PASSWORD,
				MailPackage.Literals.MAIL_CONSUMES__FOLDER,
				MailPackage.Literals.MAIL_CONSUMES__DELETE,
				MailPackage.Literals.MAIL_CONSUMES__PERIOD,
				MailPackage.Literals.MAIL_CONSUMES__ISXMLCONTENT);
		
		setControl(container);
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

}
