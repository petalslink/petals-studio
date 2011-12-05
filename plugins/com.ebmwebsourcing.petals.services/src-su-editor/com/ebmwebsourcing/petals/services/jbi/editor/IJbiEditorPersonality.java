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
package com.ebmwebsourcing.petals.services.jbi.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.ILabelProvider;

import com.sun.java.xml.ns.jbi.Jbi;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public interface IJbiEditorPersonality {

	/**
	 * @param editor the JBI editor
	 * @return the "General" page of the editor
	 */
	public AbstractJBIFormPage getGeneralMasterPage( JbiFormEditor editor );


	/**
	 * @return the label provider for the status line
	 */
	public ILabelProvider getStatusLineLabelProvider();


	/**
	 * Disposes the resources.
	 */
	public void dispose();

	/**
	 * @param the {@link Jbi} model
	 * @param jbiXmlFile the edited JBI descriptor
	 * @return true if this jbi.xml file should be edited with this personality
	 */
	public boolean matchesPersonality(Jbi model, IFile editedFile);
}
