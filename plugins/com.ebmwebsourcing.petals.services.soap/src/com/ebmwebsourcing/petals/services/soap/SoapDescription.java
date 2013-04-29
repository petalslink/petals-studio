/******************************************************************************
 * Copyright (c) 2011-2012, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.soap;

import java.util.Arrays;
import java.util.List;

import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.extensions.PetalsKeyWords;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class SoapDescription extends ComponentVersionDescription {

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #isBc()
	 */
	public boolean isBc() {
		return true;
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getComponentName()
	 */
	public String getComponentName() {
		return "petals-bc-soap";
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getComponentAlias()
	 */
	public String getComponentAlias() {
		return "SOAP";
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getComponentAnnotation()
	 */
	public String getComponentAnnotation() {
		return null;
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getProvideDescription()
	 */
	public String getProvideDescription() {
		return "Import a web service into Petals.";
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getConsumeDescription()
	 */
	public String getConsumeDescription() {
		return "Expose a Petals service as a web service.";
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getKeyWords()
	 */
	public List<PetalsKeyWords> getKeyWords() {
		return Arrays.asList( new PetalsKeyWords[] { PetalsKeyWords.communication, PetalsKeyWords.soa });
	}
}
