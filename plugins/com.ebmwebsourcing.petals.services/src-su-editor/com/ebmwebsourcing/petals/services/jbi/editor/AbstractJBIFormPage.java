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
public abstract class AbstractJBIFormPage extends FormPage {

	/**
	 * Cached markers (cached because the GUI might not be ready to display them).
	 */
	protected IManagedForm managedForm;

	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public AbstractJBIFormPage( JbiFormEditor editor, String id, String title ) {
		super( editor, id, title );
	}


	/**
	 * Updates the page when the model changed.
	 */
	//public abstract void updatePage();


	/**
	 * @param managedForm
	 */
	/*protected void onPageContentCreation( IManagedForm managedForm ) {

		this.managedForm = managedForm;
		managedForm.getMessageManager().setAutoUpdate( false );
		managedForm.getForm().getForm().addMessageHyperlinkListener( new HyperlinkAdapter() {

			@Override
			public void linkEntered( HyperlinkEvent e ) {
				// nothing
			}
		});
	}*/
	
	@Override
	public JbiFormEditor getEditor() {
		return (JbiFormEditor)super.getEditor();
	}
	
}
