/****************************************************************************
 *
 * Copyright (c) 2011-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class PetalsImages {

	public static final PetalsImages INSTANCE = new PetalsImages();
	private final List<Image> images;

	private Image pencil;
	private Image add;
	private Image delete;
	private Image browse;
	private Image searchWSDL;
	private Image ok;
	private Image ko;



	/**
	 * Constructor.
	 */
	private PetalsImages() {
		this.images = new ArrayList<Image>();
	}

	/**
	 * @return
	 */
	public Image getPencil() {
		if (this.pencil == null) {
			this.pencil = PetalsCommonPlugin.getImageDescriptor("icons/obj16/pencil.png").createImage();
			this.images.add( this.pencil );
		}
		return this.pencil;
	}

	/**
	 * @return
	 */
	public Image getAdd() {
		if (this.add == null) {
			this.add = PetalsCommonPlugin.getImageDescriptor("icons/obj16/add.png").createImage();
			this.images.add( this.add );
		}
		return this.add;
	}

	/**
	 * @return
	 */
	public Image getDelete() {
		if (this.delete == null) {
			this.delete = PetalsCommonPlugin.getImageDescriptor("icons/obj16/delete.png").createImage();
			this.images.add( this.delete );
		}
		return this.delete;
	}

	/**
	 * @return
	 */
	public Image getBrowse() {
		if (this.browse == null) {
			this.browse = PetalsCommonPlugin.getImageDescriptor("icons/obj16/folder_explore.png").createImage();
			this.images.add( this.browse );
		}
		return this.browse;
	}

	/**
	 * @return
	 */
	public Image getSearchWSDL() {
		if (this.searchWSDL == null) {
			this.searchWSDL = PetalsCommonPlugin.getImageDescriptor("icons/obj16/Interface_explore.gif").createImage();
			this.images.add( this.searchWSDL );
		}
		return this.searchWSDL;
	}

	/**
	 * @return
	 */
	public Image getOk() {
		if (this.ok == null) {
			this.ok = PetalsCommonPlugin.getImageDescriptor("icons/obj16/tick.png").createImage();
			this.images.add( this.ok );
		}
		return this.ok;
	}

	/**
	 * @return
	 */
	public Image getKO() {
		if (this.ko == null) {
			this.ko = PetalsCommonPlugin.getImageDescriptor("icons/obj16/cross.png").createImage();
			this.images.add( this.ko );
		}
		return this.ko;
	}

	/**
	 * Disposes all the images.
	 */
	public void dispose() {

		for( Image img : this.images ) {
			if( ! img.isDisposed()) {
				img.dispose();
				this.images.remove( img );
			}
		}
	}
}
