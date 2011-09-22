/****************************************************************************
 * 
 * Copyright (c) 2010-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.su.extensions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;

import com.ebmwebsourcing.petals.common.generation.Mep;

/**
 * A bean that holds information about a component plug-in contributor.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class SuContributorDescription {

	/**
	 * @return true if the component is a BC, false for a SE
	 */
	public abstract boolean isBc();

	/**
	 * @return true ONLY if the component supports 'provides' blocks in SU's jbi.xml
	 */
	public abstract boolean isProvide();

	/**
	 * @return true if the component ONLY supports 'consumes' blocks in SU's jbi.xml
	 */
	public abstract boolean isConsume();

	/**
	 * @return null if the wizard is enabled, false otherwise
	 */
	public abstract String isWizardEnabled();

	/**
	 * @return the component name
	 */
	public abstract String getComponentName();

	/**
	 * @return the description for the provides mode
	 */
	public abstract String getProvideDescription();

	/**
	 * @return the description for the consumes mode
	 */
	public abstract String getConsumeDescription();

	/**
	 * @return an array of use cases (not null, but may be empty)
	 */
	public abstract PetalsUseCase[] getUsesCases();

	/**
	 * @return an annotation to display in the SU wizard (e.g. experimental, deprecated)
	 */
	public String getAnnotation() {
		return null;
	}

	/**
	 * @return a list of validation rules (never null)
	 */
	public Set<ValidationRule> getValidationRules() {
		return Collections.emptySet();
	}

	/**
	 * @return a map associating an operation name and a MEP
	 * <p>
	 * It should include all the operations of the component, independently of the
	 * component version. Actually, it is not possible to determine the target component
	 * from any end-point bean. So, listing them all and letting the user choose is the best
	 * option. By the end, the list of operations of a component should become stable.
	 * </p>
	 */
	public Map<QName,Mep> getDefaultOperations() {
		return new HashMap<QName,Mep> ();
	}
}
