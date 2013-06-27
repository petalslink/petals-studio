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

import java.io.InputStream;

import org.eclipse.core.runtime.IStatus;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.PetalsMode;
import com.ebmwebsourcing.petals.studio.dev.properties.AbstractModel;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.JbiFactory;

/**
 * @author Vincent Zurczak - Linagora
 */
public class SuWizardModel {

	private final AbstractEndpoint endpoint;
	private final AbstractModel cdkModel, componentModel;


	/**
	 * Constructor.
	 * @param petalsMode
	 * @param desc
	 */
	public SuWizardModel( PetalsMode petalsMode, ComponentVersionDescription desc ) {
		this.endpoint = petalsMode == PetalsMode.provides ? JbiFactory.eINSTANCE.createProvides() : JbiFactory.eINSTANCE.createConsumes();
		InputStream is = desc.loadCdkModel( petalsMode );
		this.cdkModel = is != null ? AbstractModel.create( is ) : null;
		IoUtils.closeQuietly( is );

		is = desc.loadComponentModel( petalsMode );
		this.componentModel = is != null ? AbstractModel.create( is ) : null;
		IoUtils.closeQuietly( is );

		if( this.cdkModel == null )
			PetalsServicesPlugin.log( "The CDK model should not be null.", IStatus.ERROR );
		else if( this.componentModel == null )
			PetalsServicesPlugin.log( "The component model should not be null.", IStatus.ERROR );
	}

	/**
	 * @return the endpoint
	 */
	public AbstractEndpoint getEndpoint() {
		return this.endpoint;
	}

	/**
	 * @return the cdkModel
	 */
	public AbstractModel getCdkModel() {
		return this.cdkModel;
	}

	/**
	 * @return the componentModel
	 */
	public AbstractModel getComponentModel() {
		return this.componentModel;
	}
}
