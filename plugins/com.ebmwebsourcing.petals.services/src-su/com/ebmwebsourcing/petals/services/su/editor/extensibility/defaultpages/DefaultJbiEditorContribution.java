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
 * This class generate default contributions.
 * It is parameterized by the {@link EClass}es to introspect to generate widgets.
 * The main tab is made of default JBI stuff, advanced tab is made of generated widgets
 * @author istria
 *
 */
public class DefaultJbiEditorContribution implements JbiEditorDetailsContribution {

	private final EClass[] extensionClasses;

	public DefaultJbiEditorContribution(EClass... extensionEClasses) {
		this.extensionClasses = extensionEClasses;
	}

	@Override
	public void addMainSUContent(AbstractEndpoint endpoint, FormToolkit toolkit, Composite generalDetails, ISharedEdition ise) {
		JBIEndpointUIHelpers.createCommonEndpointUI(endpoint, toolkit, generalDetails, ise);
	}

	@Override
	public void addAdvancedSUContent(AbstractEndpoint endpoint, FormToolkit toolkit, Composite advancedDetails, ISharedEdition ise) {
		JBIEndpointUIHelpers.createDefaultWidgetsByEIntrospection(endpoint, toolkit, advancedDetails, ise, this.extensionClasses);
	}

}
