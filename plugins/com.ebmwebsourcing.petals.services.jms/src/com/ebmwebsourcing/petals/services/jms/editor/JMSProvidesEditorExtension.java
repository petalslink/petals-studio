/******************************************************************************
 * Copyright (c) 2012-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.jms.editor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjecttUIHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.ebmwebsourcing.petals.services.cdk.editor.CDK5JBIEndpointUIHelper;
import com.ebmwebsourcing.petals.services.jms.jms.JmsPackage;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class JMSProvidesEditorExtension extends JbiEditorDetailsContribution {

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution
	 * #addMainSUContent(com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.ui.forms.widgets.FormToolkit,
	 * org.eclipse.swt.widgets.Composite, com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition)
	 */
	@Override
	public void addMainSUContent(final AbstractEndpoint endpoint, FormToolkit toolkit, final Composite mainTab, ISharedEdition ise) {
		Composite composite = createCommonProvideSection( mainTab, toolkit );
		CDK5JBIEndpointUIHelper.createProvidesUI(endpoint, toolkit, composite, ise);
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution
	 * #addAdvancedSUContent(com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.ui.forms.widgets.FormToolkit,
	 * org.eclipse.swt.widgets.Composite, com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition)
	 */
	@Override
	public void addAdvancedSUContent(AbstractEndpoint endpoint, FormToolkit toolkit, Composite advancedTab, ISharedEdition ise) {

		Composite composite = createEditorSection( advancedTab, toolkit, "JNDI Parameters" );
		EObjecttUIHelper.generateEditorWidgets(endpoint, toolkit, composite, ise.getEditingDomain(), ise.getDataBindingContext(), true,
				JmsPackage.Literals.JMS_EXTENSION__JNDI_PROVIDER_URL,
				JmsPackage.Literals.JMS_EXTENSION__JNDI_INITIAL_CONTEXT_FACTORY,
				JmsPackage.Literals.JMS_EXTENSION__JNDI_DESTINATION_NAME,
				JmsPackage.Literals.JMS_EXTENSION__JNDI_CONNECTION_FACTORY );

		composite = createEditorSection( advancedTab, toolkit, "JMS Parameters" );
		EObjecttUIHelper.generateEditorWidgets(endpoint, toolkit, composite, ise.getEditingDomain(), ise.getDataBindingContext(), true,
				JmsPackage.Literals.JMS_EXTENSION__USER,
				JmsPackage.Literals.JMS_EXTENSION__PASSWORD,
				JmsPackage.Literals.JMS_EXTENSION__TRANSACTED,
				JmsPackage.Literals.JMS_PROVIDES__MAX_ACTIVE,
				JmsPackage.Literals.JMS_PROVIDES__MAX_IDLE,
				JmsPackage.Literals.JMS_PROVIDES__TIME_BETWEEN_EVICTION_RUNS_MILLES,
				JmsPackage.Literals.JMS_PROVIDES__MIN_EVICTABLE_IDLE_TIME_MILLIS,
				JmsPackage.Literals.JMS_PROVIDES__TEST_WHILE_IDLE );

		composite = createEditorSection( advancedTab, toolkit, "CDK Parameters" );
		CDK5JBIEndpointUIHelper.generateDefaultCdkWidgetsForProvidesEditor( endpoint, toolkit, composite, ise );
	}
}
