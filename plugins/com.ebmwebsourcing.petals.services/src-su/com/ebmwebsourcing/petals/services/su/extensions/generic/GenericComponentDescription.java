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

import java.util.Arrays;
import java.util.List;

import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.extensions.PetalsKeyWords;

/**
 * A generic component, whose main properties are set in the creation wizard.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class GenericComponentDescription extends ComponentVersionDescription {

	private boolean bc;
	private String componentName = "Petals-any-component", componentVersion = "To set later", componentAlias = "Generic";


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.IComponentDescription
	 * #isBc()
	 */
	@Override
	public boolean isBc() {
		return this.bc;
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.IComponentDescription
	 * #getComponentName()
	 */
	@Override
	public String getComponentName() {
		return this.componentName;
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.IComponentDescription
	 * #getComponentAlias()
	 */
	@Override
	public String getComponentAlias() {
		return this.componentAlias;
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.IComponentDescription
	 * #getComponentAnnotation()
	 */
	@Override
	public String getComponentAnnotation() {
		return null;
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.IComponentDescription
	 * #getProvideDescription()
	 */
	@Override
	public String getProvideDescription() {
		return "Registers a service in Petals ESB.";
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.IComponentDescription#getConsumeDescription()
	 */
	@Override
	public String getConsumeDescription() {
		return "Consumes a Petals service.";
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.IComponentDescription
	 * #getKeyWords()
	 */
	@Override
	public List<PetalsKeyWords> getKeyWords() {
		return Arrays.asList( new PetalsKeyWords[] { PetalsKeyWords.miscellaneous });
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #getComponentVersion()
	 */
	@Override
	public String getComponentVersion() {
		return this.componentVersion;
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #isProvide()
	 */
	@Override
	public boolean isProvide() {
		return true;
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #isConsume()
	 */
	@Override
	public boolean isConsume() {
		return true;
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription
	 * #isProxy()
	 */
	@Override
	public boolean isProxy() {
		return true;
	}


	/**
	 * @param bc the bc to set
	 */
	public void setBc( boolean bc ) {
		this.bc = bc;
	}


	/**
	 * @param componentName the componentName to set
	 */
	public void setComponentName( String componentName ) {
		this.componentName = componentName;
	}


	/**
	 * @param componentVersion the componentVersion to set
	 */
	public void setComponentVersion( String componentVersion ) {
		this.componentVersion = componentVersion;
	}


	/**
	 * @param componentAlias the componentAlias to set
	 */
	public void setComponentAlias( String componentAlias ) {
		this.componentAlias = componentAlias;
	}
}
