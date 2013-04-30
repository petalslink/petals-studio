/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.pojo;

import java.util.Arrays;
import java.util.List;

import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.extensions.PetalsKeyWords;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class PojoDescription extends ComponentVersionDescription {

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #isBc()
	 */
	@Override
	public boolean isBc() {
		return false;
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getComponentName()
	 */
	@Override
	public String getComponentName() {
		return "petals-se-pojo";
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getComponentAlias()
	 */
	@Override
	public String getComponentAlias() {
		return "POJO";
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getComponentAnnotation()
	 */
	@Override
	public String getComponentAnnotation() {
		return null;
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getProvideDescription()
	 */
	@Override
	public String getProvideDescription() {
		return "Hard-code a Petals service with Java and Petals API.";
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getConsumeDescription()
	 */
	@Override
	public String getConsumeDescription() {
		return null;
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getKeyWords()
	 */
	@Override
	public List<PetalsKeyWords> getKeyWords() {
		return Arrays.asList( new PetalsKeyWords[] { PetalsKeyWords.integration, PetalsKeyWords.code, PetalsKeyWords.composition });
	}
}
