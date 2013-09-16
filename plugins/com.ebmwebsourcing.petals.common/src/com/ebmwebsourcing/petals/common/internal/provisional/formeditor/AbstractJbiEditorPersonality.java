/******************************************************************************
 * Copyright (c) 2010-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.common.internal.provisional.formeditor;

import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.sun.java.xml.ns.jbi.Jbi;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class AbstractJbiEditorPersonality {

	/**
	 * @return the label provider for the status line
	 */
	public abstract ILabelProvider getStatusLineLabelProvider();

	/**
	 * Disposes the resources.
	 */
	public abstract void dispose();

	/**
	 * @param the {@link Jbi} model
	 * @param jbiXmlFile the edited JBI descriptor
	 * @return true if this jbi.xml file should be edited with this personality
	 */
	public abstract boolean matchesPersonality( Jbi model, IFile editedFile );

	/**
	 * Creates the control for the editor.
	 * @param parent the parent
	 * @param ise an instance of {@link ISharedEdition}
	 */
	public abstract void createControl( Composite parent, ISharedEdition ise );

	/**
	 * @return a title to display at the top of the editor
	 */
	public abstract String getTitle();

	/**
	 * @return an image to show in the editor's header
	 */
	public abstract Image getTitleImage();


	/**
	 * Saves the EMF model.
	 * @param model the JBI model instance
	 * @param editedFile the edited file
	 * @param domain the editing domain
	 */
	public void saveModel( Jbi model, IFile editedFile, EditingDomain domain ) {

		final Map<Object,Object> saveOptions = JbiXmlUtils.getJbiXmlSaveOptions();
		saveOptions.put( Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER );
		for( Resource resource : domain.getResourceSet().getResources()) {
			try {
				resource.save( saveOptions );

			} catch( Exception exception ) {
				PetalsCommonPlugin.log( exception, IStatus.ERROR );
			}
		}
	}
}
