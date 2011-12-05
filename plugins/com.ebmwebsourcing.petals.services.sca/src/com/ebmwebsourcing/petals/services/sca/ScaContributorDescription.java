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
package com.ebmwebsourcing.petals.services.sca;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.JaxWsUtils;
import com.ebmwebsourcing.petals.services.su.extensions.PetalsKeyWords;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler;
import com.ebmwebsourcing.petals.services.su.extensions.ValidationRule;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ScaContributorDescription extends ComponentWizardHandler {

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #getComponentName()
	 */
	@Override
	public String getComponentName() {
		return "petals-se-sca";
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #getConsumeDescription()
	 */
	@Override
	public String getConsumeDescription() {
		return null;
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #getAnnotation()
	 */
	@Override
	public String getAnnotation() {
		return "Experimental";
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #getProvideDescription()
	 */
	@Override
	public String getProvideDescription() {
		return "Create a SCA application to run into Petals.";
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

		//  Comment the rule for SCA: the composite is in the class path, not under "src/main/jbi".
		//	ValidationRule rule = new ValidationRule( ValidationRule.ANY_NAMESPACE, "composite-file", ValidationRuleType.FILE, false, false );
		//	rule.setFileExtension( "composite" );
		//	rules.add( rule );

		return rules;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #getUsesCases()
	 */
	@Override
	public PetalsKeyWords[] getKeyWords() {
		return new PetalsKeyWords[] { PetalsKeyWords.soa, PetalsKeyWords.code, PetalsKeyWords.composition };
	}
}
