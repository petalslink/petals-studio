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
package com.ebmwebsourcing.petals.common.internal.provisional.formeditor;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import com.sun.java.xml.ns.jbi.Jbi;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public interface IJbiEditorPersonality {

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
	public boolean matchesPersonality( Jbi model, IFile editedFile );

	/**
	 * Creates the control for the editor.
	 * @param parent the parent
	 * @param ise an instance of {@link ISharedEdition}
	 */
	public void createControl( Composite parent, ISharedEdition ise );

	/**
	 * @return a title to display at the top of the editor
	 */
	public String getTitle();

	/**
	 * @return an image to show in the editor's header
	 */
	public Image getTitleImage();
}
