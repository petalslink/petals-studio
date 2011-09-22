/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpel.ui.properties;

import org.eclipse.bpel.common.ui.details.DelegateIValue;
import org.eclipse.bpel.common.ui.details.FocusContext;
import org.eclipse.bpel.common.ui.details.IDetailsAreaConstants;
import org.eclipse.bpel.common.ui.details.IValue;
import org.eclipse.bpel.common.ui.details.ViewerIValue;
import org.eclipse.bpel.common.ui.details.viewers.ComboViewer;
import org.eclipse.bpel.common.ui.flatui.FlatFormAttachment;
import org.eclipse.bpel.common.ui.flatui.FlatFormData;
import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.ui.IHelpContextIds;
import org.eclipse.bpel.ui.Messages;
import org.eclipse.bpel.ui.details.providers.ExpressionEditorDescriptorContentProvider;
import org.eclipse.bpel.ui.details.providers.ExpressionEditorDescriptorLabelProvider;
import org.eclipse.bpel.ui.details.providers.ModelViewerSorter;
import org.eclipse.bpel.ui.extensions.BPELUIRegistry;
import org.eclipse.bpel.ui.extensions.ExpressionEditorDescriptor;
import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;


/**
 * Details section for attributes of a Process
 */
public class AttributesSection extends BPELPropertySection {

	protected Combo fExpressionLanguageCombo;
	protected Combo fQueryLanguageCombo;

	protected ComboViewer fExpressionLanguageViewer;
	protected ComboViewer fQueryLanguageViewer;

	protected EditController fExpressionLanguageController;
	protected EditController fQueryLanguageController;

	protected IValue fContext;


	@Override
	protected void basicSetInput(EObject input) {
		saveUserContextToInput() ;
		super.basicSetInput(input);
		restoreUserContextFromInput() ;

		/** We assume it is Process */
		this.fQueryLanguageController.setInput(input);
		this.fExpressionLanguageController.setInput(input);
	}

	static class ExpressionEditorDescriptorIValue extends DelegateIValue {
		ExpressionEditorDescriptorIValue ( IValue arg ) {
			super (arg);
		}
		/**
		 * @see org.eclipse.bpel.common.ui.details.DelegateIValue#get()
		 */
		@Override
		public Object get() {
			String result = (String) this.fDelegate.get();
			return result != null ? BPELUIRegistry.getInstance().getExpressionEditorDescriptor( result ) : null;
		}

		/**
		 * @see org.eclipse.bpel.common.ui.details.DelegateIValue#set(java.lang.Object)
		 */
		@Override
		public void set (Object object) {
			ExpressionEditorDescriptor eed = (ExpressionEditorDescriptor) object;
			this.fDelegate.set( eed != null ? eed.getExpressionLanguage() : null );
		}
	}
	protected void createChangeTrackers() {
		this.fExpressionLanguageController = createEditController();
		this.fExpressionLanguageController.setFeature(BPELPackage.eINSTANCE.getProcess_ExpressionLanguage());
		this.fExpressionLanguageController.setViewIValue(new ViewerIValue ( this.fExpressionLanguageViewer ) );
		this.fExpressionLanguageController.setModeIValue(new ExpressionEditorDescriptorIValue (
					this.fExpressionLanguageController.getModelIValue() ));

		this.fExpressionLanguageController.startListeningTo(this.fExpressionLanguageCombo) ;

		this.fQueryLanguageController = createEditController();
		this.fQueryLanguageController.setFeature(BPELPackage.eINSTANCE.getProcess_QueryLanguage() );
		this.fQueryLanguageController.setViewIValue(new ViewerIValue ( this.fQueryLanguageViewer ));
		this.fQueryLanguageController.setModeIValue(new ExpressionEditorDescriptorIValue (
					this.fQueryLanguageController.getModelIValue() ));
		this.fQueryLanguageController.startListeningTo(this.fQueryLanguageCombo) ;
	}

	@SuppressWarnings("nls")
	protected void createAttributesWidgets(Composite composite) {
		FlatFormData data;

		Label expressionLanguageLabel = this.fWidgetFactory.createLabel(composite, Messages.AttributesDetails_Expression_Language__2);
		this.fExpressionLanguageCombo = new Combo(composite,SWT.FLAT | SWT.READ_ONLY );
		this.fWidgetFactory.adapt(this.fExpressionLanguageCombo);
		this.fExpressionLanguageCombo.setData(FocusContext.NAME,"expressionLanguage");

		// Expression language combo layout
		this.fExpressionLanguageViewer = new ComboViewer(this.fExpressionLanguageCombo);
		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, BPELUtil.calculateLabelWidth(expressionLanguageLabel, STANDARD_LABEL_WIDTH_LRG));
		data.right = new FlatFormAttachment(100, (-2) * IDetailsAreaConstants.HSPACE );
		data.top = new FlatFormAttachment(0, IDetailsAreaConstants.VSPACE);
		this.fExpressionLanguageCombo.setLayoutData(data);

		this.fExpressionLanguageViewer.setContentProvider(new ExpressionEditorDescriptorContentProvider());
		this.fExpressionLanguageViewer.setLabelProvider(new ExpressionEditorDescriptorLabelProvider());
		this.fExpressionLanguageViewer.setSorter(ModelViewerSorter.getInstance());

		// Expression language label layout
		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, 0);
		data.right = new FlatFormAttachment(this.fExpressionLanguageCombo, -IDetailsAreaConstants.HSPACE);
		data.top = new FlatFormAttachment(this.fExpressionLanguageCombo, 0, SWT.CENTER);
		expressionLanguageLabel.setLayoutData(data);

		this.fExpressionLanguageViewer.setInput(new Object());

		Label queryLanguageLabel = this.fWidgetFactory.createLabel(composite, Messages.AttributesDetails_Query_Language__2);
		this.fQueryLanguageCombo = new Combo(composite,SWT.FLAT | SWT.READ_ONLY );
		this.fWidgetFactory.adapt( this.fQueryLanguageCombo );
		this.fQueryLanguageCombo.setData(FocusContext.NAME,"queryLanguage");

		// Query language combo layout
		this.fQueryLanguageViewer = new ComboViewer(this.fQueryLanguageCombo);
		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, BPELUtil.calculateLabelWidth(queryLanguageLabel, STANDARD_LABEL_WIDTH_LRG));
		data.right = new FlatFormAttachment(100, (-2) * IDetailsAreaConstants.HSPACE );
		data.top = new FlatFormAttachment(this.fExpressionLanguageCombo, IDetailsAreaConstants.VSPACE);
		this.fQueryLanguageCombo.setLayoutData(data);

		this.fQueryLanguageViewer.setLabelProvider(new ExpressionEditorDescriptorLabelProvider());
		this.fQueryLanguageViewer.setContentProvider(new ExpressionEditorDescriptorContentProvider());
		this.fQueryLanguageViewer.setSorter(ModelViewerSorter.getInstance());
		//		queryLanguageViewer.addFilter(new ExpressionLanguageFilter(new String[0]));

		// Query language label layout
		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, 0);
		data.right = new FlatFormAttachment(this.fQueryLanguageCombo, -IDetailsAreaConstants.HSPACE);
		data.top = new FlatFormAttachment(this.fQueryLanguageCombo, 0, SWT.CENTER);
		queryLanguageLabel.setLayoutData(data);

		this.fQueryLanguageViewer.setInput(new Object());


		this.fContext = new FocusContext( this.fExpressionLanguageCombo, this.fQueryLanguageCombo );
	}

	@Override
	protected void createClient(Composite parent) {
		Composite composite = createFlatFormComposite(parent);
		createAttributesWidgets(composite);
		createChangeTrackers();

		PlatformUI.getWorkbench().getHelpSystem().setHelp(
					composite, IHelpContextIds.PROPERTY_PAGE_PROCESS_DETAILS);
	}

	/**
	 * @see org.eclipse.bpel.ui.properties.BPELPropertySection#getUserContext()
	 */
	@Override
	public Object getUserContext() {
		return this.fContext.get();
	}

	/**
	 * @see org.eclipse.bpel.ui.properties.BPELPropertySection#restoreUserContext(java.lang.Object)
	 */
	@Override
	public void restoreUserContext(Object userContext) {
		this.fContext.set(userContext);
	}
}
