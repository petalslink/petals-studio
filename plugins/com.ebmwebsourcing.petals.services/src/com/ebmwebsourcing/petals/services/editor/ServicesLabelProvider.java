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
 
package com.ebmwebsourcing.petals.services.editor;

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.ImageRegistry;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.MarkerUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.sun.java.xml.ns.jbi.ServiceAssembly;
import com.sun.java.xml.ns.jbi.ServiceUnit;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ServicesLabelProvider extends LabelProvider {

	private final ImageDescriptor saImgDesc, suImgDesc;
	private Map<EObject,List<IMarker>> elementToMarkers;
	private final ImageRegistry imageRegistry;


	/**
	 * Constructor.
	 */
	public ServicesLabelProvider() {
		this.imageRegistry = new ImageRegistry();
		this.saImgDesc = PetalsServicesPlugin.getImageDescriptor( "icons/obj16/service_assembly.png" );
		this.suImgDesc = PetalsServicesPlugin.getImageDescriptor( "icons/obj16/service_unit.png" );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.BaseLabelProvider#dispose()
	 */
	@Override
	public void dispose() {

		if( this.imageRegistry != null )
			this.imageRegistry.dispose();

		super.dispose();
	}


	/**
	 * @param elementToMarkers the elementToMarkers to set
	 */
	public void setElementToMarkers( Map<EObject,List<IMarker>> elementToMarkers ) {
		this.elementToMarkers = elementToMarkers;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider
	 * #getText(java.lang.Object)
	 */
	@Override
	public String getText( Object element ) {

		String result = "";
		if( element instanceof ServiceUnit )
			result = ((ServiceUnit) element).getIdentification().getName();
		else if( element instanceof ServiceAssembly )
			result = ((ServiceAssembly) element).getIdentification().getName();

		return result;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider
	 * #getImage(java.lang.Object)
	 */
	@Override
	public Image getImage( Object element ) {

		Image result = null;
		ImageDescriptor desc = null;
		if( element instanceof ServiceUnit )
			desc = this.suImgDesc;
		else if( element instanceof ServiceAssembly )
			desc = this.saImgDesc;

		if( desc != null ) {

			// Get markers attached to this element
			int level = IStatus.OK;
			if( this.elementToMarkers != null ) {
				List<IMarker> markers = this.elementToMarkers.get( element );
				if( markers != null )
					level = MarkerUtils.getMaximumSeverity( markers );
			}

			// Need to create a composite image?
			if( level == IStatus.ERROR )
				result = this.imageRegistry.getErrorImage( desc );
			else if( level == IStatus.WARNING )
				result = this.imageRegistry.getWarningImage( desc );
			else
				result = this.imageRegistry.getBaseImage( desc );
		}

		return result;
	}
}
