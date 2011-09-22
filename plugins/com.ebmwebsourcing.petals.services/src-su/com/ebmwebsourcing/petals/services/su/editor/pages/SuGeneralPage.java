/****************************************************************************
 * 
 * Copyright (c) 2009-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.editor.pages;

import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import com.ebmwebsourcing.petals.services.editor.AbstractServicesFormPage;
import com.ebmwebsourcing.petals.services.editor.JbiFormEditor;
import com.ebmwebsourcing.petals.services.su.editor.blocks.ProvidesConsumesMasterPage;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SuGeneralPage extends AbstractServicesFormPage {

	/**
	 * Constructor.
	 * 
	 * @param editor
	 * @param id
	 * @param title
	 */
	public SuGeneralPage( JbiFormEditor editor, String id, String title ) {
		super( editor, id, title );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormPage
	 * #createFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void createFormContent( IManagedForm managedForm ) {

		// Prepare the page
		final ScrolledForm form = managedForm.getForm();
		form.setText( "General" );
		form.setToolTipText( "This page deals with the common properties of jbi.xml files for Petals service units" );
		managedForm.getToolkit().decorateFormHeading( form.getForm() );

		// Create the master / details
		SuDetailsPageProvider detailsPageProvider = new SuDetailsPageProvider( this );
		this.masterPage = new ProvidesConsumesMasterPage( this, true, detailsPageProvider );
		this.masterPage.createContent( managedForm );
		updatePage();
		onPageContentCreation( managedForm );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.AbstractSuFormPage
	 * #updatePage()
	 */
	@Override
	public void updatePage() {
		this.masterPage.update();
	}
}