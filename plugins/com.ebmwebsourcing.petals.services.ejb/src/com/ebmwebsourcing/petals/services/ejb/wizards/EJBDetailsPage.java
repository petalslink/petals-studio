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
package com.ebmwebsourcing.petals.services.ejb.wizards;

import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjecttUIHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjecttUIHelper.EntryDescription;
import com.ebmwebsourcing.petals.services.ejb.ejb.EjbPackage;
import com.ebmwebsourcing.petals.services.ejb.ejb.EjbVersion;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class EJBDetailsPage extends AbstractSuWizardPage {

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	private DataBindingContext dbc;


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {

		// Create the composite container and define its layout.
		final Composite container = new Composite( parent, SWT.NONE );

		GridLayout layout = new GridLayout( 2, false );
		layout.marginLeft = layout.marginRight = 5;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));
		setControl( container );


		// The data-binding context
		this.dbc = new DataBindingContext();
		WizardPageSupport.create( this, this.dbc );

		FormToolkit toolkit = new FormToolkit( getShell().getDisplay());
		List<EntryDescription> entries = EObjecttUIHelper.generateWidgets(
				getNewlyCreatedEndpoint(), toolkit, container, null, this.dbc, false,
				EjbPackage.Literals.EJB_PROVIDES__EJB_JNDI_NAME,
				EjbPackage.Literals.EJB_PROVIDES__JAVA_NAMING_FACTORY_INITIAL,
				EjbPackage.Literals.EJB_PROVIDES__JAVA_NAMING_FACTORY_URL_PKGS,
				EjbPackage.Literals.EJB_PROVIDES__JAVA_NAMING_PROVIDER_URL,
				EjbPackage.Literals.EJB_PROVIDES__EJB_VERSION,
				EjbPackage.Literals.EJB_PROVIDES__EJB_HOME_INTERFACE,
				EjbPackage.Literals.EJB_PROVIDES__SECURITY_NAME,
				EjbPackage.Literals.EJB_PROVIDES__SECURITY_PRINCIPAL,
				EjbPackage.Literals.EJB_PROVIDES__SECURITY_CREDENCIALS );


		// The "Home Interface" only makes sense for EJB 2.x
		final ISelectionProvider ejbCombo = (ISelectionProvider) entries.get( 4 ).widget;
		final Text ejbHome = (Text) entries.get( 5 ).widget;
		ejbCombo.addSelectionChangedListener( new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {

				IStructuredSelection sel = (IStructuredSelection) event.getSelection();
				if( ! sel.isEmpty()) {
					Object element = sel.getFirstElement();
					ejbHome.setEnabled( element == EjbVersion.V20 || element == EjbVersion.V21 );
				}
			}
		});

		ejbCombo.setSelection( new StructuredSelection( EjbVersion.V30 ));

		// TODO: Test the JNDI connection
//		Button testButton = new Button( container , SWT.PUSH);
//		testButton.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, true, 2, 1));
//		testButton.setText(Messages.testConnection);
//		testButton.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				EJBConnectionTest.testConnection(getNewlyCreatedEndpoint(), getShell());
//			}
//		});
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage
	 * #validate()
	 */
	@Override
	public boolean validate() {
		return false;
	}
}
