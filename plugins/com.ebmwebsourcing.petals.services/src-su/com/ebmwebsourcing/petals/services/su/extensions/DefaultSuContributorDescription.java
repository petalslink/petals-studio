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

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class DefaultSuContributorDescription extends SuContributorDescription {

	private boolean bc = false, provide, consume;
	private String componentName, provideDescription, consumeDescription;

	/**
	 * False by default.
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #isBc()
	 */
	@Override
	public boolean isBc() {
		return this.bc;
	}

	/**
	 * @param bc the bc to set
	 */
	public void setBc( boolean bc ) {
		this.bc = bc;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #isProvide()
	 */
	@Override
	public boolean isProvide() {
		return this.provide;
	}

	/**
	 * @param provide the provide to set
	 */
	public void setProvide( boolean provide ) {
		this.provide = provide;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #isConsume()
	 */
	@Override
	public boolean isConsume() {
		return this.consume;
	}

	/**
	 * @param consume the consume to set
	 */
	public void setConsume( boolean consume ) {
		this.consume = consume;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #getComponentName()
	 */
	@Override
	public String getComponentName() {
		return this.componentName;
	}

	/**
	 * @param componentName the componentName to set
	 */
	public void setComponentName( String componentName ) {
		this.componentName = componentName;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #getProvideDescription()
	 */
	@Override
	public String getProvideDescription() {
		return this.provideDescription;
	}

	/**
	 * @param provideDescription the provideDescription to set
	 */
	public void setProvideDescription( String provideDescription ) {
		this.provideDescription = provideDescription;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.SuContributorDescription
	 * #getConsumeDescription()
	 */
	@Override
	public String getConsumeDescription() {
		return this.consumeDescription;
	}

	/**
	 * @param consumeDescription the consumeDescription to set
	 */
	public void setConsumeDescription( String consumeDescription ) {
		this.consumeDescription = consumeDescription;
	}

	/*
	 * (non-Javadoc)
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
		return new PetalsUseCase[ 0 ];
	}
}
