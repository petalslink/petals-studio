/****************************************************************************
 * 
 * Copyright (c) 2010-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.eip.designer;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import com.ebmwebsourcing.petals.services.eip.PetalsEipPlugin;
import com.ebmwebsourcing.petals.services.eip.designer.model.EIPtype;

/**
 * A utility class to store images.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipDesignerImageStore {

	/**
	 * The instance of this class.
	 */
	public static EipDesignerImageStore INSTANCE = new EipDesignerImageStore();

	/**
	 * The EIP images (enabled).
	 */
	private final Map<EIPtype,Image> enabledEipToImage = new HashMap<EIPtype,Image> ();

	/**
	 * The EIP images (disabled).
	 */
	private final Map<EIPtype,Image> disabledEipToImage = new HashMap<EIPtype,Image> ();

	/**
	 * The end-point image.
	 */
	private Image enabledEdptImage, disabledEdptImage;

	/**
	 * The 16x16 image that represents an EIP chain.
	 */
	private Image eip_16x16_image;

	/**
	 * The icon that represents an error.
	 */
	private Image errorIcon;
	private Image warningIcon;

	/**
	 * Tooling images.
	 */
	private Image docImage, exportImage;


	/**
	 * Constructor.
	 * <p>
	 * It is in charge of creating the images.
	 * </p>
	 */
	private EipDesignerImageStore() {

		// Create the EIP images
		for( EIPtype type : EIPtype.values()) {

			String path = "icons/obj64/d_" + type.toString().toLowerCase() + "_64x64.png";
			try {
				ImageDescriptor desc = PetalsEipPlugin.getImageDescriptor( path );
				this.disabledEipToImage.put( type, desc.createImage());

				path = "icons/obj64/e_" + type.toString().toLowerCase() + "_64x64.png";
				desc = PetalsEipPlugin.getImageDescriptor( path );
				this.enabledEipToImage.put( type, desc.createImage());

			} catch( Exception e ) {
				PetalsEipPlugin.log( "The image " + path + " could not be found.", IStatus.WARNING );
			}
		}

		// Create the end-point image
		String path = "icons/obj32/d_endpoint_32x32.png";
		try {
			ImageDescriptor desc = PetalsEipPlugin.getImageDescriptor( path );
			this.disabledEdptImage = desc.createImage();

			path = "icons/obj32/e_endpoint_32x32.png";
			desc = PetalsEipPlugin.getImageDescriptor( path );
			this.enabledEdptImage = desc.createImage();

		} catch( Exception e ) {
			PetalsEipPlugin.log( "The image " + path + " could not be found.", IStatus.WARNING );
		}


		// Create the 16x16 image
		path = "icons/obj16/eip_1_16x16.png";
		try {
			ImageDescriptor desc = PetalsEipPlugin.getImageDescriptor( path );
			this.eip_16x16_image = desc.createImage();

		} catch( Exception e ) {
			PetalsEipPlugin.log( "The image " + path + " could not be found.", IStatus.WARNING );
		}


		// Create the other images
		path = "icons/obj16/documentation.gif";
		try {
			ImageDescriptor desc = PetalsEipPlugin.getImageDescriptor( path );
			this.docImage = desc.createImage();

		} catch( Exception e ) {
			PetalsEipPlugin.log( "The image " + path + " could not be found.", IStatus.WARNING );
		}

		path = "icons/obj16/export.gif";
		try {
			ImageDescriptor desc = PetalsEipPlugin.getImageDescriptor( path );
			this.exportImage = desc.createImage();

		} catch( Exception e ) {
			PetalsEipPlugin.log( "The image " + path + " could not be found.", IStatus.WARNING );
		}

		path = "icons/obj16/error.gif";
		try {
			ImageDescriptor desc = PetalsEipPlugin.getImageDescriptor( path );
			this.errorIcon = desc.createImage();

		} catch( Exception e ) {
			PetalsEipPlugin.log( "The image " + path + " could not be found.", IStatus.WARNING );
		}
		
		path = "icons/obj16/warning.gif";
		try {
			ImageDescriptor desc = PetalsEipPlugin.getImageDescriptor( path );
			this.warningIcon = desc.createImage();

		} catch( Exception e ) {
			PetalsEipPlugin.log( "The image " + path + " could not be found.", IStatus.WARNING );
		}
	}


	/**
	 * Initializes the image store.
	 */
	public void initialize() {
		// nothing
	}


	/**
	 * @return the errorIcon
	 */
	public Image getErrorIcon() {
		return this.errorIcon;
	}
	
	public Image getWarningIcon() {
		return this.warningIcon;
	}


	/**
	 * @param eipType the EIP type
	 * @param enabled true to get the enabled image
	 * @return the associated image (can be null)
	 */
	public Image getEipImage( EIPtype eipType, boolean enabled ) {
		if( enabled )
			return this.enabledEipToImage.get( eipType );
		else
			return this.disabledEipToImage.get( eipType );
	}


	/**
	 * @param eipType the EIP type
	 * @return the associated image (can be null)
	 */
	public ImageDescriptor getDefaultEipImageDescriptor( EIPtype eipType ) {

		ImageDescriptor desc = null;
		String path = "icons/obj64/d_" + eipType.toString().toLowerCase() + "_64x64.png";
		try {
			desc = PetalsEipPlugin.getImageDescriptor( path );

		} catch( Exception e ) {
			PetalsEipPlugin.log( "The image " + path + " could not be found.", IStatus.WARNING );
		}

		return desc;
	}


	/**
	 * @return the eip_16x16_image (can be null)
	 */
	public Image getEip_16x16_image() {
		return this.eip_16x16_image;
	}


	/**
	 * @param enabled
	 * @return the edptImage
	 */
	public Image getEdptImage( boolean enabled ) {
		return enabled ? this.enabledEdptImage : this.disabledEdptImage;
	}


	/**
	 * @return the docImage
	 */
	public Image getDocImage() {
		return this.docImage;
	}


	/**
	 * @return the exportImage
	 */
	public Image getExportImage() {
		return this.exportImage;
	}


	/**
	 * Disposes all the images.
	 */
	public void dispose() {

		// The EIP images
		for( Image img : this.enabledEipToImage.values()) {
			if( img != null && ! img.isDisposed())
				img.dispose();
		}

		for( Image img : this.disabledEipToImage.values()) {
			if( img != null && ! img.isDisposed())
				img.dispose();
		}

		this.enabledEipToImage.clear();
		this.disabledEipToImage.clear();

		// The end-point images
		if( this.enabledEdptImage != null && ! this.enabledEdptImage.isDisposed())
			this.enabledEdptImage.dispose();
		this.enabledEdptImage = null;

		if( this.disabledEdptImage != null && ! this.disabledEdptImage.isDisposed())
			this.disabledEdptImage.dispose();
		this.disabledEdptImage = null;

		// The 16x16 image
		if( this.eip_16x16_image != null && ! this.eip_16x16_image.isDisposed())
			this.eip_16x16_image.dispose();
		this.eip_16x16_image = null;

		// Other images
		if( this.docImage != null && ! this.docImage.isDisposed())
			this.docImage.dispose();
		this.docImage = null;

		if( this.exportImage != null && ! this.exportImage.isDisposed())
			this.exportImage.dispose();
		this.exportImage = null;

		if( this.errorIcon != null && ! this.errorIcon.isDisposed())
			this.errorIcon.dispose();
		this.errorIcon = null;
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object
	 * #finalize()
	 */
	@Override
	protected void finalize() throws Throwable {

		// Dispose resources (in case of Eclipse crash)
		try {
			dispose();

		} catch( Exception e ) {
			// nothing
		}

		// Call to super
		super.finalize();
	}


}
