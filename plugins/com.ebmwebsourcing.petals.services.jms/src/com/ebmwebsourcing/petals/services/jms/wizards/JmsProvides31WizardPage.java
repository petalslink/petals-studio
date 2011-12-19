package com.ebmwebsourcing.petals.services.jms.wizards;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjecttUIHelper;
import com.ebmwebsourcing.petals.services.jms.jms.JmsPackage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;

public class JmsProvides31WizardPage extends AbstractSuPage {

	private DataBindingContext dbc;

	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		
		dbc = new DataBindingContext();
		
		EObjecttUIHelper.generateWidgets(getNewlyCreatedEndpoint(), new FormToolkit(getShell().getDisplay()), container, null, dbc,
				JmsPackage.Literals.JMS_EXTENSION__JNDI_PROVIDER_URL,
				JmsPackage.Literals.JMS_EXTENSION__JNDI_INITIAL_CONTEXT_FACTORY,
				JmsPackage.Literals.JMS_EXTENSION__JNDI_DESTINATION_NAME,
				JmsPackage.Literals.JMS_EXTENSION__JNDI_CONNECTION_FACTORY,
				JmsPackage.Literals.JMS_EXTENSION__USER,
				JmsPackage.Literals.JMS_EXTENSION__PASSWORD,
				JmsPackage.Literals.JMS_EXTENSION__TRANSACTED,
				JmsPackage.Literals.JMS_PROVIDES__MAX_ACTIVE,
				JmsPackage.Literals.JMS_PROVIDES__MAX_IDLE,
				JmsPackage.Literals.JMS_PROVIDES__TIME_BETWEEN_EVICTION_RUNS_MILLES,
				JmsPackage.Literals.JMS_PROVIDES__MIN_EVICTABLE_IDLE_TIME_MILLIS,
				JmsPackage.Literals.JMS_PROVIDES__TITLE_WHILE_IDLE);
		
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
