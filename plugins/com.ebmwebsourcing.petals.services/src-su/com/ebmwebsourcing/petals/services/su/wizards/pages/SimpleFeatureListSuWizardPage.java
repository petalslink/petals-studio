/****************************************************************************
 *
 * Copyright (c) 2011-2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.wizards.pages;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjecttUIHelper;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class SimpleFeatureListSuWizardPage extends AbstractSuWizardPage {

	protected DataBindingContext dbc;
	protected final EStructuralFeature[] features;


	/**
	 * Constructor.
	 * @param features
	 */
	public SimpleFeatureListSuWizardPage(EStructuralFeature... features) {
		this.features = features;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		this.dbc = new DataBindingContext();
		WizardPageSupport.create( this, this.dbc );

		Composite container = new Composite(parent, SWT.NONE);
		GridLayoutFactory.swtDefaults().numColumns( 2 ).extendedMargins( 15, 15, 20, 0 ).applyTo( container );
		setControl( container );

		EObjecttUIHelper.generateWidgets(
				getNewlyCreatedEndpoint(),
				new FormToolkit(getShell().getDisplay()),
				container, null, this.dbc, false, this.features);
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage
	 * #validate()
	 */
	@Override
	public boolean validate() {
		return true;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
	 */
	@Override
	public void setVisible( boolean visible ) {
		super.setVisible( visible );

		// Do not display an error when it becomes visible
		if( visible && getErrorMessage() != null ) {
			setErrorMessage( null );
			setPageComplete( false );
		}
	}
}
