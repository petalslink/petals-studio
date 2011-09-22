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
package com.ebmwebsourcing.petals.services.jsr181;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.JaxWsUtils;
import com.ebmwebsourcing.petals.services.su.extensions.PetalsUseCase;
import com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription;
import com.ebmwebsourcing.petals.services.su.extensions.ValidationRule;
import com.ebmwebsourcing.petals.services.su.extensions.ValidationRule.ValidationRuleType;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class Jsr181ContributorDescription extends SuContributorDescription {

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #getComponentName()
	 */
	@Override
	public String getComponentName() {
		return "petals-se-jsr181";
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
		return "Hard-code a Petals service with JAX-WS.";
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

		String errorMsg = null;
		try {
			JaxWsUtils.getJavaExecutable( true );
			JaxWsUtils.getJavaExecutable( false );

		} catch( IOException e ) {
			errorMsg = e.getMessage();
		}

		return errorMsg;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #getValidationRules()
	 */
	@Override
	public Set<ValidationRule> getValidationRules() {

		Set<ValidationRule> rules = new HashSet<ValidationRule> ();
		ValidationRule rule = new ValidationRule( ValidationRule.ANY_NAMESPACE, "class", ValidationRuleType.JAVA, false, false );
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
		return new PetalsUseCase[] { PetalsUseCase.soa, PetalsUseCase.code, PetalsUseCase.composition };
	}
}
