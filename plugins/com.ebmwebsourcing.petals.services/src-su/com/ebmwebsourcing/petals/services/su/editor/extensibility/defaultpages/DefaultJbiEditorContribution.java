/**
 * Copyright (c) 2011-2012, EBM WebSourcing
 *
 *  This source code is available under agreement available at
 *  http://www.petalslink.com/legal/licenses/petals-studio
 *
 *  You should have received a copy of the agreement along with this program.
 *  If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.su.editor.extensibility.defaultpages;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.su.editor.su.JBIEndpointUIHelpers;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * This class generates default contributions.
 * <p>
 * It is parameterized by the {@link EClass}es to introspect to generate widgets.
 * The main tab is made of default JBI stuff, advanced tab is made of generated widgets
 * </p>
 * @author Mickael Istria - EBM WebSourcing
 */
public class DefaultJbiEditorContribution extends JbiEditorDetailsContribution {

	private final EClass[] extensionClasses;


	/**
	 * Constructor.
	 * @param extensionEClasses
	 */
	public DefaultJbiEditorContribution(EClass... extensionEClasses) {
		this.extensionClasses = extensionEClasses;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution
	 * #addMainSUContent(com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.ui.forms.widgets.FormToolkit,
	 * org.eclipse.swt.widgets.Composite, com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition)
	 */
	@Override
	public void addMainSUContent(AbstractEndpoint endpoint, FormToolkit toolkit, Composite generalDetails, ISharedEdition ise) {
		JBIEndpointUIHelpers.createCommonEndpointUI(endpoint, toolkit, generalDetails, ise);
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.editor.extensibility.JbiEditorDetailsContribution
	 * #addAdvancedSUContent(com.sun.java.xml.ns.jbi.AbstractEndpoint, org.eclipse.ui.forms.widgets.FormToolkit,
	 * org.eclipse.swt.widgets.Composite, com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition)
	 */
	@Override
	public void addAdvancedSUContent(AbstractEndpoint endpoint, FormToolkit toolkit, Composite advancedDetails, ISharedEdition ise) {
		JBIEndpointUIHelpers.createDefaultWidgetsByEIntrospection(endpoint, toolkit, advancedDetails, ise, this.extensionClasses);
	}
}
