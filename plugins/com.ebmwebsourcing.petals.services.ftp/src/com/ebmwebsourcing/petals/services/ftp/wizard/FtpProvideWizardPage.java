package com.ebmwebsourcing.petals.services.ftp.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjecttUIHelper;
import com.ebmwebsourcing.petals.services.ftp.FtpDescription32;
import com.ebmwebsourcing.petals.services.ftp.FtpDescription33;
import com.ebmwebsourcing.petals.services.ftp.ftp3.Ftp3Package;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;

public class FtpProvideWizardPage extends AbstractSuPage {

	private DataBindingContext dbc;

	public void createControl(Composite parent) {
		dbc = new DataBindingContext();
		initializeProvides();

		Composite page = new Composite(parent, SWT.NONE);
		page.setLayout(new GridLayout(2, false));
		
		/*
		page.setLayout(new FillLayout());
		org.eclipse.swt.widgets.Group destGroup = new Group(page, SWT.NONE);
		destGroup.setText(Messages.destination);
		destGroup.setLayout(new GridLayout(4, false));
		Label hostLabel = new Label(destGroup, SWT.NONE);
		hostLabel.setText(Messages.host);
		Text hostText = new Text(destGroup, SWT.BORDER);
		hostText.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
		*/
		List<EStructuralFeature> features = new ArrayList<EStructuralFeature>();
		features.add(Ftp3Package.Literals.FTP_PROVIDES__SERVER);
		features.add(Ftp3Package.Literals.FTP_PROVIDES__PORT);
		features.add(Ftp3Package.Literals.FTP_PROVIDES__USER);
		features.add(Ftp3Package.Literals.FTP_PROVIDES__PASSWORD);
		features.add(Ftp3Package.Literals.FTP_PROVIDES__FOLDER);
		features.add(Ftp3Package.Literals.FTP_PROVIDES__FILENAME);
		
		
		EObjecttUIHelper.generateWidgets(getNewlyCreatedEndpoint(), new FormToolkit(getShell().getDisplay()), page, null, dbc, features.toArray(new EStructuralFeature[0]));
		setControl(page);
	}

	private void initializeProvides() {
		EObject endpoint = getNewlyCreatedEndpoint();
		endpoint.eSet(Ftp3Package.Literals.FTP_PROVIDES__PORT, 21);
		if (getWizard().getWizardHandler().getComponentVersionDescription() instanceof FtpDescription32) {
			endpoint.eSet(Ftp3Package.Literals.FTP_PROVIDES__DELETE_PROCESSED_FILES, false);
		}
		if (getWizard().getWizardHandler().getComponentVersionDescription() instanceof FtpDescription33) {
			endpoint.eSet(Ftp3Package.Literals.FTP_PROVIDES__OVERWRITE, true);
		}
		
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void dispose() {
		super.dispose();
		if (dbc != null) {
			dbc.dispose();
		}
	}

}
