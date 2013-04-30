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

package com.ebmwebsourcing.petals.services.filetransfer;

import java.util.Arrays;
import java.util.List;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.extensions.PetalsKeyWords;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class FileTransferDescription extends ComponentVersionDescription {

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
		return "petals-bc-filetransfer";
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getComponentAlias()
	 */
	public String getComponentAlias() {
		return "File Transfer";
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
		return "Create a Petals service to interact with a local file system.";
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getConsumeDescription()
	 */
	public String getConsumeDescription() {
		return "Invoke a Petals service when a file appears in a directory.";
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getKeyWords()
	 */
	public List<PetalsKeyWords> getKeyWords() {
		return Arrays.asList( new PetalsKeyWords[] { PetalsKeyWords.communication });
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #isProvide()
	 */
	@Override
	public boolean isProvide() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #isConsume()
	 */
	@Override
	public boolean isConsume() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #isProxy()
	 */
	@Override
	public boolean isProxy() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getSupportedMep()
	 */
	@Override
	public Mep[] getSupportedMep() {
		return new Mep[] { Mep.IN_ONLY };
	}
}
