/****************************************************************************
 *
 * Copyright (c) 2008-2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.ejb.wizards;

import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjecttUIHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjecttUIHelper.EntryDescription;
import com.ebmwebsourcing.petals.services.ejb.Messages;
import com.ebmwebsourcing.petals.services.ejb.ejb.EjbPackage;
import com.ebmwebsourcing.petals.services.ejb.ejb.EjbVersion;
import com.ebmwebsourcing.petals.services.ejb.test.EJBConnectionTest;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;

/**
 * @author Mickael Istria (EBM Websourcing)
 *
 */
public class EJBDetailsPage extends AbstractSuWizardPage {

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	private DataBindingContext dbc;

	public void createControl(Composite parent) {
		this.dbc = new DataBindingContext();

		Composite res = new Composite(parent, SWT.NONE);
		res.setLayout(new GridLayout(2, false));
		FormToolkit toolkit = new FormToolkit(getShell().getDisplay());
		List<EntryDescription> entries = EObjecttUIHelper.generateWidgets(getNewlyCreatedEndpoint(), toolkit, res, null, this.dbc,
			EjbPackage.Literals.EJB_PROVIDES__EJB_JNDI_NAME,
			EjbPackage.Literals.EJB_PROVIDES__JAVA_NAMING_FACTORY_INITIAL,
			EjbPackage.Literals.EJB_PROVIDES__JAVA_NAMING_FACTORY_URL_PKGS,
			EjbPackage.Literals.EJB_PROVIDES__JAVA_NAMING_PROVIDER_URL,
			EjbPackage.Literals.EJB_PROVIDES__EJB_VERSION,
			EjbPackage.Literals.EJB_PROVIDES__EJB_HOME_INTERFACE,
			EjbPackage.Literals.EJB_PROVIDES__SECURITY_NAME,
			EjbPackage.Literals.EJB_PROVIDES__SECURITY_PRINCIPAL,
			EjbPackage.Literals.EJB_PROVIDES__SECURITY_CREDENCIALS);


		final ISelectionProvider ejbCombo = (ISelectionProvider)entries.get(4).widget;
		final Text ejbHome = (Text)entries.get(5).widget;
		ejbCombo.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection sel = (IStructuredSelection)event.getSelection();
				Object element = sel.getFirstElement();
				ejbHome.setEnabled(element == EjbVersion.V20 || element == EjbVersion.V21);
			}
		});
		ejbCombo.setSelection(new StructuredSelection(EjbVersion.V30));

		//http://netbeans.org/competition/win-with-netbeans/debug-ejb.html
		Button testButton = new Button(res, SWT.PUSH);
		testButton.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, true, 2, 1));
		testButton.setText(Messages.testConnection);
		testButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				EJBConnectionTest.testConnection(getNewlyCreatedEndpoint(), getShell());
			}
		});

		setControl(res);
	}

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage#validate()
	 */
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void dispose() {
		super.dispose();
		this.dbc.dispose();
	}
}
