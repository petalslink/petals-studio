package com.ebmwebsourcing.petals.services.sql.wizards;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjecttUIHelper;
import com.ebmwebsourcing.petals.services.sql.sql.SqlPackage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;

public class SqlProvides11Page extends AbstractSuPage {

	private DataBindingContext dbc;

	@Override
	public void createControl(Composite parent) {
		dbc = new DataBindingContext();
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		
		EObjecttUIHelper.generateWidgets(getNewlyCreatedEndpoint(), new FormToolkit(getShell().getDisplay()), container, null, dbc,
				SqlPackage.Literals.SQL_PROVIDES__URL,
				SqlPackage.Literals.SQL_PROVIDES__USER,
				SqlPackage.Literals.SQL_PROVIDES__PASSWORD,
				SqlPackage.Literals.SQL_PROVIDES__DRIVER,
				SqlPackage.Literals.SQL_PROVIDES__MAX_ACTIVE,
				SqlPackage.Literals.SQL_PROVIDES__MAX_IDLE,
				SqlPackage.Literals.SQL_PROVIDES__MIN_IDLE,
				SqlPackage.Literals.SQL_PROVIDES__TIME_BETWEEN_EVICTION_RUNS_MILLIS,
				SqlPackage.Literals.SQL_PROVIDES__METADATA);
		
		setControl(container);
	}

	@Override
	public boolean validate() {
		return false;
	}
	
	@Override
	public void dispose() {
		super.dispose();
		dbc.dispose();
	}

}
