/****************************************************************************
 * 
 * Copyright (c) 2010-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.sa.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ILabelProvider;

import com.ebmwebsourcing.petals.services.editor.AbstractServicesFormPage;
import com.ebmwebsourcing.petals.services.editor.IJbiEditorPersonality;
import com.ebmwebsourcing.petals.services.editor.JbiFormEditor;
import com.ebmwebsourcing.petals.services.editor.ServicesLabelProvider;
import com.ebmwebsourcing.petals.services.utils.ServiceProjectRelationUtils;

/**
 * The SA personality for the JBI editor.
 */
public class SaPersonality implements IJbiEditorPersonality {

	private ILabelProvider statusLineLabelProvider;


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.editor.IServicesFormEditorPersonality
	 * #getStatusLineLabelProvider()
	 */
	public ILabelProvider getStatusLineLabelProvider() {

		if( this.statusLineLabelProvider == null )
			this.statusLineLabelProvider = new ServicesLabelProvider();

		return this.statusLineLabelProvider;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.editor.IServicesFormEditorPersonality
	 * #dispose()
	 */
	public void dispose() {

		if( this.statusLineLabelProvider != null ) {
			this.statusLineLabelProvider.dispose();
			this.statusLineLabelProvider = null;
		}
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.editor.IJbiEditorPersonality
	 * #getGeneralMasterPage(com.ebmwebsourcing.petals.services.editor.AbstractServicesFormEditor)
	 */
	public AbstractServicesFormPage getGeneralMasterPage( JbiFormEditor editor ) {

		SaPage saPage = new SaPage( editor, "petals-sa-general-page", "General" );
		return saPage;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.editor.IServicesFormEditorPersonality
	 * #matchesPersonality(org.eclipse.core.resources.IFile)
	 */
	public boolean matchesPersonality( IFile jbiXmlFile ) {
		IProject p = jbiXmlFile.getProject();
		return p.isOpen() && ServiceProjectRelationUtils.isSaProject( p );
	}
}
