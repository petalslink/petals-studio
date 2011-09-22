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
import java.util.List;

import com.ebmwebsourcing.petals.services.explorer.model.EndpointBean;

/**
 * A bean to store information about KPI configurations to create.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class KpiProjectBean {

	private String projectName;
	private final List<KpiFlowBean> flowBeans = new ArrayList<KpiFlowBean> ();



	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return this.projectName;
	}


	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName( String projectName ) {
		this.projectName = projectName;
	}


	/**
	 * @return the flowBeans
	 */
	public List<KpiFlowBean> getFlowBeans() {
		return this.flowBeans;
	}


	/**
	 * @param o
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean addKpiFlowBean( KpiFlowBean o ) {
		return this.flowBeans.add( o );
	}


	/**
	 * @return true only if at least one flow has to be monitored
	 */
	public boolean isToCreate() {

		boolean toCreate = false;
		for( KpiFlowBean flowBean : this.flowBeans )
			toCreate = toCreate || flowBean.isToCreate();

		return toCreate;
	}


	/**
	 * 
	 */
	public static class KpiFlowBean {

		private String flowName;
		private EndpointBean bean;
		private String xpathExpression;
		private boolean toCreate = true;


		/**
		 * @return the flowName
		 */
		public String getFlowName() {
			return this.flowName;
		}

		/**
		 * @param flowName the flowName to set
		 */
		public void setFlowName( String flowName ) {
			this.flowName = flowName;
		}

		/**
		 * @return the bean
		 */
		public EndpointBean getBean() {
			return this.bean;
		}

		/**
		 * @param bean the bean to set
		 */
		public void setBean( EndpointBean bean ) {
			this.bean = bean;
		}

		/**
		 * @return the xpathExpression
		 */
		public String getXpathExpression() {
			return this.xpathExpression;
		}

		/**
		 * @param xpathExpression the xpathExpression to set
		 */
		public void setXpathExpression( String xpathExpression ) {
			this.xpathExpression = xpathExpression;
		}

		/**
		 * @return the toCreate
		 */
		public boolean isToCreate() {
			return this.toCreate;
		}

		/**
		 * @param toCreate the toCreate to set
		 */
		public void setToCreate( boolean toCreate ) {
			this.toCreate = toCreate;
		}
	}
}
