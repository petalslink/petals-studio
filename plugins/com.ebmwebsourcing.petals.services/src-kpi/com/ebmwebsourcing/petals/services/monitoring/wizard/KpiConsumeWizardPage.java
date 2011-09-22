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

package com.ebmwebsourcing.petals.services.monitoring.wizard;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

import com.ebmwebsourcing.petals.common.generation.JbiUtils;
import com.ebmwebsourcing.petals.services.explorer.model.EndpointBean;
import com.ebmwebsourcing.petals.services.monitoring.wizard.KpiProjectBean.KpiFlowBean;
import com.ebmwebsourcing.petals.services.su.wizards.pages.SeveralConsumeWizardPage;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class KpiConsumeWizardPage extends SeveralConsumeWizardPage {

	private KpiConfigurationWizardPage configurationPage;


	/**
	 * Constructor.
	 */
	public KpiConsumeWizardPage() {
		super( "SeveralConsumePages", "KPI", "1.0" );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #getNextPage()
	 */
	@Override
	public IWizardPage getNextPage() {

		if( this.configurationPage == null ) {
			this.configurationPage = new KpiConfigurationWizardPage();
			this.configurationPage.setTitle( "Watcher Configuration" );
			this.configurationPage.setDescription( "Define the watcher parameters." );
			((Wizard) getWizard()).addPage( this.configurationPage );
		}

		Collection<KpiProjectBean> kpiProjectBeans = new ArrayList<KpiProjectBean>( 1 );
		for( EndpointBean edptBean : this.edptBeans ) {
			KpiProjectBean kpb = new KpiProjectBean();
			kpiProjectBeans.add( kpb );
			kpb.setProjectName( JbiUtils.createSuName( "KPI", edptBean.getServiceName().getLocalPart(), true ));

			for( String flowName : KpiConstants.FLOW_NAMES ) {
				KpiFlowBean flowBean = new KpiFlowBean();
				flowBean.setFlowName( flowName );
				flowBean.setBean( edptBean );
				kpb.addKpiFlowBean( flowBean );
			}
		}

		this.configurationPage.setKpiProjectBeans( kpiProjectBeans );
		return this.configurationPage;
	}


	/**
	 * @return the configurationPage (never null)
	 */
	protected final KpiConfigurationWizardPage getConfigurationPage() {
		return this.configurationPage;
	}
}
