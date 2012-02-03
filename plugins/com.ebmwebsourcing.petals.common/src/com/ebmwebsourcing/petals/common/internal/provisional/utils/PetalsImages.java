package com.ebmwebsourcing.petals.common.internal.provisional.utils;

import org.eclipse.swt.graphics.Image;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

public class PetalsImages {

	private static Image pencil;
	private static Image add;
	private static Image delete;
	private static Image browse;
	private static Image searchWSDL;
	private static Image ok;
	private static Image ko;

	public synchronized static Image getPencil() {
		if (pencil == null) {
			pencil = PetalsCommonPlugin.getImageDescriptor("icons/obj16/pencil.png").createImage();
		}
		return pencil;
	}

	public synchronized static Image getAdd() {
		if (add == null) {
			add = PetalsCommonPlugin.getImageDescriptor("icons/obj16/add.png").createImage();
		}
		return add;
	}

	public synchronized static Image getDelete() {
		if (delete == null) {
			delete = PetalsCommonPlugin.getImageDescriptor("icons/obj16/delete.png").createImage();
		}
		return delete;
	}

	public synchronized static Image getBrowse() {
		if (browse == null) {
			browse = PetalsCommonPlugin.getImageDescriptor("icons/obj16/folder_explore.png").createImage();
		}
		return browse;
	}

	public synchronized static Image getSearchWSDL() {
		if (searchWSDL == null) {
			searchWSDL = PetalsCommonPlugin.getImageDescriptor("icons/obj16/Interface_explore.gif").createImage();
		}
		return searchWSDL;
	}

	/**
	 * @return
	 */
	public static Image getOk() {
		if (ok == null) {
			ok = PetalsCommonPlugin.getImageDescriptor("icons/obj16/tick.png").createImage();
		}
		return ok;
	}

	/**
	 * @return
	 */
	public static Image getKO() {
		if (ko == null) {
			ko = PetalsCommonPlugin.getImageDescriptor("icons/obj16/cross.png").createImage();
		}
		return ko;
	}

}
