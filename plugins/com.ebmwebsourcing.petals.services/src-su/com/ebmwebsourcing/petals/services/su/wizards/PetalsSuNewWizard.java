package com.ebmwebsourcing.petals.services.su.wizards;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import com.ebmwebsourcing.petals.services.su.wizards.pages.ChoicePage;

public class PetalsSuNewWizard extends Wizard implements INewWizard, IExecutableExtension {

	private PetalsMode petalsMode;

	public PetalsSuNewWizard() {
	}
	
	public PetalsSuNewWizard(PetalsMode petalsMode) {
		this.petalsMode = petalsMode;
		init();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.IExecutableExtension
	 * #setInitializationData(org.eclipse.core.runtime.IConfigurationElement, java.lang.String, java.lang.Object)
	 */
	@Override
	public void setInitializationData( IConfigurationElement config, String propertyName, Object data ) throws CoreException {
		this.petalsMode = "provides".equalsIgnoreCase( String.valueOf( data )) ? PetalsMode.provides : PetalsMode.consumes;
	}
	
	public void init() {
		if (petalsMode == PetalsMode.provides) {
			setWindowTitle("Petals Service");
		} else {
			setWindowTitle("Petals Service Consumer" );
		}
	}
	
	/**
	 * Adds the first page of the wizard.
	 */
	@Override
	public void addPages() {
		addPage( new ChoicePage( this.petalsMode ));
	}

	
	@Override
	public boolean performFinish() {
		return false;
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}

}
