/**
 *  Copyright (c) 2011, EBM WebSourcing
 *  
 *  This source code is available under agreement available at
 *  http://www.petalslink.com/legal/licenses/petals-studio
 *  
 *  You should have received a copy of the agreement along with this program.
 *  If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 */
package com.ebmwebsourcing.petals.services.jbi.editor.extensibility.defaultpages;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.services.jbi.editor.JbiFormEditor;
import com.ebmwebsourcing.petals.services.jbi.editor.extensibility.JbiEditorDetailsContribution;
import com.ebmwebsourcing.petals.services.jbi.editor.su.JBIEndpointUIHelpers;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * This class generate default contributions.
 * It is parameterized by the {@link EClass}es to introspect to generate widgets.
 * The main tab is made of default JBI stuff, advanced tab is made of generated widgets 
 * @author istria
 *
 */
public class DefaultJbiEditorContribution implements JbiEditorDetailsContribution {

	private EClass[] extensionClasses;
	
	public DefaultJbiEditorContribution(EClass... extensionEClasses) {
		this.extensionClasses = extensionEClasses;
	}
	
	@Override
	public void addMainSUContent(AbstractEndpoint endpoint, FormToolkit toolkit, Composite generalDetails, JbiFormEditor editor) {
		JBIEndpointUIHelpers.createCommonEndpointUI(endpoint, toolkit, generalDetails, editor);
	}

	@Override
	public void addAdvancedSUContent(AbstractEndpoint endpoint, FormToolkit toolkit, Composite advancedDetails, JbiFormEditor editor) {
		JBIEndpointUIHelpers.createDefaultWidgetsByEIntrospection(endpoint, toolkit, advancedDetails, editor, extensionClasses);
	}

}
