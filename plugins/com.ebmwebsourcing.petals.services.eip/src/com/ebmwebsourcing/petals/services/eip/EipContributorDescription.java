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
package com.ebmwebsourcing.petals.services.eip;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.services.su.extensions.PetalsUseCase;
import com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription;
import com.ebmwebsourcing.petals.services.su.extensions.ValidationRule;
import com.ebmwebsourcing.petals.services.su.extensions.ValidationRule.ValidationRuleType;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipContributorDescription extends SuContributorDescription {

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #getComponentName()
	 */
	@Override
	public String getComponentName() {
		return "petals-se-eip";
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #getConsumeDescription()
	 */
	@Override
	public String getConsumeDescription() {
		return null;
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #getProvideDescription()
	 */
	@Override
	public String getProvideDescription() {
		return "Chain invocations to other Petals services according to pre-defined patterns.";
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #isBc()
	 */
	@Override
	public boolean isBc() {
		return false;
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #isConsume()
	 */
	@Override
	public boolean isConsume() {
		return false;
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #isProvide()
	 */
	@Override
	public boolean isProvide() {
		return true;
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #isWizardEnabled()
	 */
	@Override
	public String isWizardEnabled() {
		return null;
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #getAnnotation()
	 */
	@Override
	public String getAnnotation() {
		return "Use Croquis Instead";
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #getValidationRules()
	 */
	@Override
	public Set<ValidationRule> getValidationRules() {

		Set<ValidationRule> rules = new HashSet<ValidationRule> ();
		ValidationRule rule = new ValidationRule( ValidationRule.ANY_NAMESPACE, "aggregator-correlation", ValidationRuleType.XPATH, true, false );
		rules.add( rule );

		rule = new ValidationRule( ValidationRule.ANY_NAMESPACE, "test", ValidationRuleType.XPATH, true, false );
		rules.add( rule );

		return rules;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #getUsesCases()
	 */
	@Override
	public PetalsUseCase[] getUsesCases() {
		return new PetalsUseCase[] { PetalsUseCase.integration, PetalsUseCase.composition };
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #getDefaultOperations()
	 */
	@Override
	public Map<QName,Mep> getDefaultOperations() {

		HashMap<QName,Mep> result = new HashMap<QName,Mep> ();
		result.put( new QName( "http://petals.ow2.org/components/eip/version-2", "anyOperation" ), Mep.IN_OUT );
		return result;
	}
}
