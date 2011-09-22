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

package com.ebmwebsourcing.petals.services.editor;

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.w3c.dom.Element;

import com.ebmwebsourcing.petals.common.internal.provisional.sse.StructuredModelHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.DomUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ImageRegistry;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.MarkerUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ServicesLabelProvider extends LabelProvider {

	private final ImageDescriptor saImgDesc, suImgDesc;
	private Map<Element,List<IMarker>> elementToMarkers;
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
	public void setElementToMarkers( Map<Element, List<IMarker>> elementToMarkers ) {
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
		if( element instanceof Element ) {
			Element child = DomUtils.getChildElement((Element) element, "identification" );
			if( child != null ) {
				child = DomUtils.getChildElement( child, "name" );
				if( child != null )
					result = StructuredModelHelper.getElementSimpleValue( child );
			}
		}

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
		if( element instanceof Element ) {
			String name = DomUtils.getNodeName((Element) element);
			ImageDescriptor desc = null;
			if( "service-assembly".equalsIgnoreCase( name ))
				desc = this.saImgDesc;
			else if( "service-unit".equalsIgnoreCase( name ))
				desc = this.suImgDesc;

			// Get markers attached to this element
			int level = IStatus.OK;
			if( this.elementToMarkers != null ) {
				List<IMarker> markers = this.elementToMarkers.get( element );
				if( markers != null )
					level = MarkerUtils.getMaximumSeverity( markers );
			}

			if( desc != null ) {
				if( level == IStatus.ERROR )
					result = this.imageRegistry.getErrorImage( desc );
				else if( level == IStatus.WARNING )
					result = this.imageRegistry.getWarningImage( desc );
				else
					result = this.imageRegistry.getBaseImage( desc );
			}
		}

		return result;
	}
}
