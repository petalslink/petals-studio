package com.ebmwebsourcing.petals.services.su.wizards.pages;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjecttUIHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.Messages;

public class SimpleFeatureListSuWizardPage extends AbstractSuWizardPage implements Adapter {

	private DataBindingContext dbc;
	private final EStructuralFeature[] features;


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
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		getNewlyCreatedEndpoint().eAdapters().add(this);
		EObjecttUIHelper.generateWidgets(getNewlyCreatedEndpoint(), new FormToolkit(getShell().getDisplay()), container, null, this.dbc, this.features);

		setControl(container);
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage
	 * #validate()
	 */
	@Override
	public boolean validate() {
		setPageComplete(isPageComplete());
		return true;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.WizardPage
	 * #isPageComplete()
	 */
	@Override
	public boolean isPageComplete() {
		for (EStructuralFeature feature : this.features) {
			if (feature.getLowerBound() > 0 &&
					(!getNewlyCreatedEndpoint().eIsSet(feature) ||
					(getNewlyCreatedEndpoint().eGet(feature) instanceof String && ((String)getNewlyCreatedEndpoint().eGet(feature)).isEmpty()))) {
				setErrorMessage(Messages.bind(Messages.featureNotSet, StringUtils.camelCaseToHuman(feature.getName())));
				return false;
			}
		}
		setErrorMessage(null);
		return true;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage
	 * #dispose()
	 */
	@Override
	public void dispose() {
		super.dispose();
		if( this.dbc != null )
			this.dbc.dispose();
	}


	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter
	 * #notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	@Override
	public void notifyChanged(Notification notification) {
		setPageComplete(isPageComplete());
	}


	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter
	 * #getTarget()
	 */
	@Override
	public Notifier getTarget() {
		// Nothing to do
		return getNewlyCreatedEndpoint();
	}


	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter
	 * #setTarget(org.eclipse.emf.common.notify.Notifier)
	 */
	@Override
	public void setTarget(Notifier newTarget) {
		// Nothing to do
	}


	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter
	 * #isAdapterForType(java.lang.Object)
	 */
	@Override
	public boolean isAdapterForType(Object type) {
		return true;
	}
}
