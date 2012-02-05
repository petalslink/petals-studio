/******************************************************************************
 * Copyright (c) 2011-2012, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.bpel.wizards;

import javax.xml.namespace.QName;

import org.eclipse.bpel.ui.wizards.NewBpelFileFirstPage;
import org.eclipse.bpel.ui.wizards.NewBpelFileFirstPage.BpelCreationMode;
import org.eclipse.bpel.ui.wizards.NewBpelFilePortTypePage;
import org.eclipse.bpel.ui.wizards.NewBpelFileTemplatePage;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.wizard.IWizardPage;

import com.ebmwebsourcing.petals.services.bpel.BpelDescription1x;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.ProjectPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class BpelSuWizard extends AbstractServiceUnitWizard {

	protected NewBpelFileFirstPage firstPage;
	protected NewBpelFileTemplatePage wsdlPage;
	protected NewBpelFilePortTypePage portTypePage;
	private ProjectPage projectPage;


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #addPages()
	 */
	@Override
	public void addPages() {

		setDialogSettings( new DialogSettings( "to.avoid.npe" ));

		this.firstPage = new NewBpelFileFirstPage();
		this.projectPage = new ProjectPage();
		this.wsdlPage = new NewBpelFileTemplatePage() {
			@Override
			public IWizardPage getNextPage() {
				return getProjectPage( this );
			}

			@Override
			public void setVisible( boolean visible ) {
				super.setVisible( visible );

				String sName = (String) BpelSuWizard.this.firstPage.getProcessTemplateProperties().get( "processName" );
				if( sName != null )
					getNewlyCreatedEndpoint().setServiceName( new QName( sName ));
			}
		};

		this.portTypePage = new NewBpelFilePortTypePage() {
			@Override
			public IWizardPage getNextPage() {
				return getProjectPage( this );
			}
		};

		// Add all the pages
		// The pages will then decide which one follows them
		addPage( this.firstPage );
		addPage( this.portTypePage );
		addPage( this.wsdlPage );
		addPage( this.projectPage );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard
	 * #canFinish()
	 */
	@Override
	public boolean canFinish() {

		boolean complete = this.firstPage.isPageComplete() && this.projectPage.isPageComplete();
		if( complete ) {
			if( this.firstPage.getCreationMode() == BpelCreationMode.CREATE_NEW_BPEL )
				complete = this.wsdlPage.isPageComplete();
			else
				complete = this.portTypePage.isPageComplete();
		}

		return complete;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #performFinish()
	 */
	@Override
	public boolean performFinish() {
		setStrategy( new BpelStrategy());
		return super.performFinish();
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected IStatus performLastActions( IFolder resourceDirectory, AbstractEndpoint newlyCreatedEndpoint, IProgressMonitor monitor ) {
		return Status.OK_STATUS;
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new BpelDescription1x();
	}


	/**
	 * Gets the project page and updates it.
	 * @param previousPage the previous page to set
	 * @return the project page
	 */
	private IWizardPage getProjectPage( IWizardPage previousPage ) {

		// We need to do that since the page order depends on user choices
		IWizardPage nextPage = getPage( ProjectPage.PAGE_NAME );
		nextPage.setPreviousPage( previousPage );
		return nextPage;
	}
}
