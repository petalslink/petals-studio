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
package com.ebmwebsourcing.petals.services.jms;

import com.ebmwebsourcing.petals.services.su.extensions.PetalsUseCase;
import com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class JmsContributorDescription extends SuContributorDescription {

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #getComponentName()
	 */
	@Override
	public String getComponentName() {
		return "petals-bc-jms";
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #getConsumeDescription()
	 */
	@Override
	public String getConsumeDescription() {
		return "Invoke a Petals service on a JMS event.";
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #getProvideDescription()
	 */
	@Override
	public String getProvideDescription() {
		return "Create a Petals service to send or publish messages on a JMS server.";
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #isBc()
	 */
	@Override
	public boolean isBc() {
		return true;
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #isConsume()
	 */
	@Override
	public boolean isConsume() {
		return true;
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
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #getUsesCases()
	 */
	@Override
	public PetalsUseCase[] getUsesCases() {
		return new PetalsUseCase[] { PetalsUseCase.communication };
	}
}