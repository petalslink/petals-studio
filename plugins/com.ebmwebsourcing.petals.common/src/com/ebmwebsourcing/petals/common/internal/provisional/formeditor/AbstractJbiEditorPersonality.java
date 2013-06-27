/****************************************************************************
 *
 * Copyright (c) 2010-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/
package com.ebmwebsourcing.petals.common.internal.provisional.formeditor;

import java.io.ByteArrayInputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.w3c.dom.Document;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.DomUtils;
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
	 * Loads the model and related things.
	 */
	public void loadModel( Jbi jbiInstance, IFile editedFile ) {
		// nothing by default
	}


	/**
	 * Saves the model.
	 * @param jbiInstance
	 * @param editedFile
	 * @param monitor
	 */
	public void saveModel( Jbi jbiInstance, IFile editedFile, IProgressMonitor monitor ) {

		Document doc = JbiXmlUtils.saveJbiXmlAsDocument( jbiInstance );
		String s = DomUtils.writeDocument( doc );
		if( editedFile.exists()) {
			try {
				editedFile.setContents( new ByteArrayInputStream( s.getBytes()), true, true, monitor );

			} catch( CoreException e ) {
				PetalsCommonPlugin.log( e, IStatus.ERROR );
			}
		}
	}
}
