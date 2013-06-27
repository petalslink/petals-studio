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

package com.ebmwebsourcing.petals.services.cdk.api;

import java.io.InputStream;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.PlatformUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.cdk.generated.ConsumesCdk10;
import com.ebmwebsourcing.petals.services.cdk.generated.ProvidesCdk10;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.PetalsMode;

/**
 * @author Vincent Zurczak - Linagora
 */
public abstract class Cdk5ComponentVersionDescription extends ComponentVersionDescription {

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #loadCdkModel(com.ebmwebsourcing.petals.services.su.wizards.PetalsMode)
	 */
	@Override
	public InputStream loadCdkModel( PetalsMode petalsMode ) {
		String loc = petalsMode == PetalsMode.provides ? "provides" : "consumes";
		return PlatformUtils.loadPluginResource( PetalsServicesPlugin.PLUGIN_ID, "/model/" + loc + "-CDK-1.0.properties" );
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getCdkNamespace()
	 */
	@Override
	public final String getCdkNamespace() {
		return "http://petals.ow2.org/components/extensions/version-5";
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getSortedCdkProperties(com.ebmwebsourcing.petals.services.su.wizards.PetalsMode)
	 */
	@Override
	public final String[] getSortedCdkProperties( PetalsMode petalsMode ) {

		String[] result;
		if( petalsMode == PetalsMode.provides ) {
			result = new String[] {
				ProvidesCdk10.TIMEOUT,
				ProvidesCdk10.VALIDATE_WSDL,
				ProvidesCdk10.FORWARD_SECURITY_SUBJECT,
				ProvidesCdk10.FORWARD_MESSAGE_PROPERTIES,
				ProvidesCdk10.FORWARD_ATTACHMENTS,
				ProvidesCdk10.WSDL
			};

		} else {
			result = new String[] {
				ConsumesCdk10.TIMEOUT,
				ConsumesCdk10.OPERATION,
				ConsumesCdk10.MEP
			};
		}

		return result;
	}
}
