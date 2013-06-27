/******************************************************************************
 * Copyright (c) 2013, Linagora
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Linagora - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.su.model;

import java.util.ArrayList;

import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.studio.dev.properties.AbstractModel;

/**
 * @author Vincent Zurczak - Linagora
 */
public class SuEditorModel {

	private final ComponentVersionDescription desc;
	private final ArrayList<AbstractModel> providesComponentModels, providesCdkModels, consumesComponentModels, consumesCdkModels;


	/**
	 * Constructor.
	 * @param desc
	 */
	public SuEditorModel( ComponentVersionDescription desc ) {
		this.desc = desc;
		this.providesComponentModels = new ArrayList<AbstractModel> ();
		this.providesCdkModels = new ArrayList<AbstractModel> ();
		this.consumesComponentModels = new ArrayList<AbstractModel> ();
		this.consumesCdkModels = new ArrayList<AbstractModel> ();
	}


	/**
	 * @return the desc
	 */
	public ComponentVersionDescription getDesc() {
		return this.desc;
	}


	/**
	 * @return the providesComponentModels
	 */
	public ArrayList<AbstractModel> getProvidesComponentModels() {
		return this.providesComponentModels;
	}


	/**
	 * @return the providesCdkModels
	 */
	public ArrayList<AbstractModel> getProvidesCdkModels() {
		return this.providesCdkModels;
	}


	/**
	 * @return the consumesComponentModels
	 */
	public ArrayList<AbstractModel> getConsumesComponentModels() {
		return this.consumesComponentModels;
	}


	/**
	 * @return the consumesCdkModels
	 */
	public ArrayList<AbstractModel> getConsumesCdkModels() {
		return this.consumesCdkModels;
	}
}
