/****************************************************************************
 * 
 * Copyright (c) 2009-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.explorer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.explorer.model.EndpointBean;
import com.ebmwebsourcing.petals.services.explorer.model.ServiceUnitBean;
import com.ebmwebsourcing.petals.services.explorer.sources.CurrentWorkspaceSource;
import com.ebmwebsourcing.petals.services.explorer.sources.EndpointSource;
import com.ebmwebsourcing.petals.services.explorer.sources.ExternalWorkspaceSource;
import com.ebmwebsourcing.petals.services.explorer.sources.SaDirectorySource;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ServiceExplorerLabelProvider extends LabelProvider
implements ICommonLabelProvider {

	// private Image serverImg;
	private Image edptImg, suImg;
	private Image openProjectImg, closedProjectImg;
	private Image closedFolderImg;


	/**
	 * Constructor.
	 */
	public ServiceExplorerLabelProvider() {

		ImageDescriptor desc;
		try {
			desc = PetalsServicesPlugin.getImageDescriptor( "icons/obj16/Endpoint_3.gif" );
			if( desc != null )
				this.edptImg = desc.createImage();

		} catch (Exception e) {
			PetalsServicesPlugin.log( e, IStatus.WARNING );
		}

		try {
			desc = PetalsServicesPlugin.getImageDescriptor( "icons/obj16/EndpointFolderClosed.gif" );
			if( desc != null )
				this.closedFolderImg = desc.createImage();

		} catch (Exception e) {
			PetalsServicesPlugin.log( e, IStatus.WARNING );
		}

		try {
			desc = PetalsServicesPlugin.getImageDescriptor( "icons/obj16/EndpointProjectOpen.gif" );
			if( desc != null )
				this.openProjectImg = desc.createImage();

		} catch (Exception e) {
			PetalsServicesPlugin.log( e, IStatus.WARNING );
		}

		try {
			desc = PetalsServicesPlugin.getImageDescriptor( "icons/obj16/EndpointProjectClosed.gif" );
			if( desc != null )
				this.closedProjectImg = desc.createImage();

		} catch (Exception e) {
			PetalsServicesPlugin.log( e, IStatus.WARNING );
		}

		try {
			desc = PetalsServicesPlugin.getImageDescriptor( "icons/obj16/service_unit.png" );
			if( desc != null )
				this.suImg = desc.createImage();

		} catch (Exception e) {
			PetalsServicesPlugin.log( e, IStatus.WARNING );
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.navigator.IDescriptionProvider
	 * #getDescription(java.lang.Object)
	 */
	public String getDescription( Object element ) {

		if( element instanceof EndpointSource )
			return ((EndpointSource) element).getDescription();

		if( element instanceof ServiceUnitBean )
			return "A service unit containing " + ((ServiceUnitBean) element).countEndpoints() + " end-points";

		if( element instanceof EndpointBean ) {
			EndpointBean bean = (EndpointBean) element;
			return "End-point for the service " + bean.getServiceName().getLocalPart();
		}

		if( element instanceof String )
			return (String) element;

		return null;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider
	 * #getText(java.lang.Object)
	 */
	@Override
	public String getText( Object element ) {

		if( element instanceof EndpointSource )
			return ((EndpointSource) element).getName();
		if( element instanceof ServiceUnitBean ) {
			String suName = ((ServiceUnitBean) element).getServiceUnitName();
			return  suName + " (" + ((ServiceUnitBean) element).countEndpoints() + ")";
		}

		if( element instanceof EndpointBean )
			return ((EndpointBean) element).getEndpointName();
		if( element instanceof String )
			return (String) element;

		return "";
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.BaseLabelProvider#dispose()
	 */
	@Override
	public void dispose() {

		try {
			if( this.edptImg != null )
				this.edptImg.dispose();
			if( this.openProjectImg != null )
				this.openProjectImg.dispose();
			if( this.closedFolderImg != null )
				this.closedFolderImg.dispose();
			if( this.closedProjectImg != null )
				this.closedProjectImg.dispose();
			//			if( this.serverEdptImg != null )
			//				this.serverEdptImg.dispose();
			if( this.suImg != null )
				this.suImg.dispose();

		} catch (Exception e) {
			e.printStackTrace();
		}

		super.dispose();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider
	 * #getImage(java.lang.Object)
	 */
	@Override
	public Image getImage( Object element ) {

		if( element instanceof SaDirectorySource )
			return this.closedFolderImg;
		if( element instanceof CurrentWorkspaceSource )
			return this.openProjectImg;
		if( element instanceof ExternalWorkspaceSource )
			return this.closedProjectImg;
		if( element instanceof EndpointBean )
			return this.edptImg;
		if( element instanceof ServiceUnitBean )
			return this.suImg;

		return null;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.ui.navigator.ICommonLabelProvider
	 * #init(org.eclipse.ui.navigator.ICommonContentExtensionSite)
	 */
	public void init( ICommonContentExtensionSite aConfig ) {
		// nothing
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.ui.navigator.IMementoAware
	 * #restoreState(org.eclipse.ui.IMemento)
	 */
	public void restoreState( IMemento aMemento ) {
		// nothing
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.ui.navigator.IMementoAware
	 * #saveState(org.eclipse.ui.IMemento)
	 */
	public void saveState( IMemento aMemento ) {
		// nothing
	}
}
