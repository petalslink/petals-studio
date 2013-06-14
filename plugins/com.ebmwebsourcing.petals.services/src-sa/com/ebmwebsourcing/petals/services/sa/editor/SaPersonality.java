/****************************************************************************
 *
 * Copyright (c) 2009-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.sa.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.AbstractJbiEditorPersonality;
import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.editor.ServicesLabelProvider;
import com.sun.java.xml.ns.jbi.Jbi;

/**
 * The SA personality for the JBI editor.
 */
public class SaPersonality extends AbstractJbiEditorPersonality {

	private ILabelProvider statusLineLabelProvider;


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.editor.IServicesFormEditorPersonality
	 * #getStatusLineLabelProvider()
	 */
	@Override
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
	@Override
	public void dispose() {
		if( this.statusLineLabelProvider != null )
			this.statusLineLabelProvider.dispose();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.editor.IServicesFormEditorPersonality
	 * #matchesPersonality(org.eclipse.core.resources.IFile)
	 */
	@Override
	public boolean matchesPersonality( Jbi jbi, IFile jbiXmlFile ) {
		return jbi.getServiceAssembly() != null;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.IJbiEditorPersonality
	 * #getTitle()
	 */
	@Override
	public String getTitle() {
		return "Service Assembly";
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.IJbiEditorPersonality
	 * #getTitleImage()
	 */
	@Override
	public Image getTitleImage() {
		return PetalsServicesPlugin.loadImage( "icons/obj16/service_assembly.png" );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.IJbiEditorPersonality
	 * #createControl(org.eclipse.swt.widgets.Composite, com.ebmwebsourcing.petals.services.su.editor.ISharedEdition)
	 */
	@Override
	public void createControl( Composite parent, ISharedEdition ise ) {
		new SaEditionComposite( parent, ise );
	}
}
