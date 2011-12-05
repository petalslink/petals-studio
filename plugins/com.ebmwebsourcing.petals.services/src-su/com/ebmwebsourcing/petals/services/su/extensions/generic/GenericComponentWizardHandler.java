/******************************************************************************
 * Copyright (c) 2011, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.su.extensions.generic;

import java.util.List;

import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class GenericComponentWizardHandler extends ComponentWizardHandler {

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new GenericComponentDescription();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #registerCustomWizardPages(com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler.CustomPagePosition, java.util.List)
	 */
	@Override
	public void registerCustomWizardPages( CustomPagePosition position, List<AbstractSuPage> pagesToInsert ) {

		if( position == CustomPagePosition.beforeProjectPage )
			pagesToInsert.add( new GenericSuWizardPage());
	}
}
