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

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjecttUIHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.ejb.Messages;
import com.ebmwebsourcing.petals.services.ejb.ejb.EjbPackage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;

/**
 * @author istria
 *
 */
public class EJBDetailsPage extends AbstractSuPage {

	private DataBindingContext dbc;
	private EStructuralFeature[] fields;

	public EJBDetailsPage() {
		super();
		fields = new EStructuralFeature[] {
				EjbPackage.Literals.EJB_PROVIDES__EJB_JNDI_NAME,
				EjbPackage.Literals.EJB_PROVIDES__JAVA_NAMING_FACTORY_INITIAL,
				EjbPackage.Literals.EJB_PROVIDES__JAVA_NAMING_FACTORY_URL_PKGS,
				EjbPackage.Literals.EJB_PROVIDES__JAVA_NAMING_PROVIDER_URL,
				EjbPackage.Literals.EJB_PROVIDES__EJB_VERSION,
				EjbPackage.Literals.EJB_PROVIDES__EJB_HOME_INTERFACE,
				EjbPackage.Literals.EJB_PROVIDES__SECURITY_NAME,
				EjbPackage.Literals.EJB_PROVIDES__SECURITY_PRINCIPAL,
				EjbPackage.Literals.EJB_PROVIDES__SECURITY_CREDENCIALS,
				EjbPackage.Literals.EJB_PROVIDES__MARSHALLING_ENGINE
			};		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		dbc = new DataBindingContext();
		Composite res = new Composite(parent, SWT.NONE);
		res.setLayout(new GridLayout(2, false));
		
		EObjecttUIHelper.generateWidgets(getNewlyCreatedEndpoint(), new FormToolkit(getShell().getDisplay()), res, null, dbc, fields);
		
		setControl(res);
	}

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage#validate()
	 */
	@Override
	public boolean validate() {
		setErrorMessage(null);
		boolean containsError = false;
		StringBuilder error = new StringBuilder();
		for (EStructuralFeature field : fields) {
			if (field.getLowerBound() > 0) {
				if (!getNewlyCreatedEndpoint().eIsSet(field)) {
					error.append("Field not set");
					error.append('\n');
					containsError = false;
				}
			}
		}
		if (containsError) {
			setErrorMessage(error.toString());
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public void dispose() {
		super.dispose();
		dbc.dispose();
	}

}
