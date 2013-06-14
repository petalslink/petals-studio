/****************************************************************************
 *
 * Copyright (c) 2010-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.su.extensions;

import java.util.List;

/**
 * A bean that holds information about a Petals component.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public interface IComponentDescription {

	/**
	 * @return true if the component is a BC, false for a SE
	 */
	public abstract boolean isBc();

	/**
	 * @return the component name
	 */
	public abstract String getComponentName();

	/**
	 * @return the component alias (e.g. SOAP, FTP...)
	 */
	public abstract String getComponentAlias();

	/**
	 * @return the component annotation (e.g. experimental, deprecated...)
	 */
	public abstract String getComponentAnnotation();

	/**
	 * @return the description for the provides mode
	 */
	public abstract String getProvideDescription();

	/**
	 * @return the description for the consumes mode
	 */
	public abstract String getConsumeDescription();

	/**
	 * @return an array of key words (not null, but may be empty)
	 */
	public abstract List<PetalsKeyWords> getKeyWords();
}
