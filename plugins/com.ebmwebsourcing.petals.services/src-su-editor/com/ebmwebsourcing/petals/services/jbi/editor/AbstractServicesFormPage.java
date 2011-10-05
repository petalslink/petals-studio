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

package com.ebmwebsourcing.petals.services.jbi.editor;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;

import com.sun.java.xml.ns.jbi.Jbi;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class AbstractServicesFormPage extends FormPage {

	protected IServiceMasterPage masterPage;


	/**
	 * Cached markers (cached because the GUI might not be ready to display them).
	 */
	protected IManagedForm managedForm;


	protected Jbi jbi;
	protected JbiFormEditor editor;
	protected TransactionalEditingDomain editDomain;

	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public AbstractServicesFormPage( JbiFormEditor editor, String id, String title ) {
		super( editor, id, title );
		this.editor = editor;
	}


	/**
	 * Updates the page when the model changed.
	 */
	public abstract void updatePage();


	/**
	 * @param managedForm
	 */
	protected void onPageContentCreation( IManagedForm managedForm ) {

		this.managedForm = managedForm;
		managedForm.getMessageManager().setAutoUpdate( false );
		managedForm.getForm().getForm().addMessageHyperlinkListener( new HyperlinkAdapter() {

			@Override
			public void linkEntered( HyperlinkEvent e ) {
				// nothing
			}
		});

	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormPage#dispose()
	 */
	@Override
	public void dispose() {
		this.masterPage.dispose();
		super.dispose();
	}


	public void setModel(Jbi model) {
		this.jbi = model;
	}


	public EditingDomain getEditDomain() {
		return this.editDomain;
	}


	public Jbi getJbi() {
		return this.jbi;
	}


	public void setEditDomain(TransactionalEditingDomain editDomain) {
		this.editDomain = editDomain;
	}
	
}
