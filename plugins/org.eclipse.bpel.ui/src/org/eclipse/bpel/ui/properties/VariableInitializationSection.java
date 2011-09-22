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

import org.eclipse.bpel.common.ui.details.IDetailsAreaConstants;
import org.eclipse.bpel.common.ui.flatui.FlatFormAttachment;
import org.eclipse.bpel.common.ui.flatui.FlatFormData;
import org.eclipse.bpel.model.BPELFactory;
import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.ui.Messages;
import org.eclipse.bpel.ui.commands.SetCommand;
import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.bpel.ui.util.MultiObjectAdapter;
import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;


/**
 * Variable from-spec section. This is allowed in BPEL 2.0.
 * 
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date June, 2007
 */


public class VariableInitializationSection extends BPELPropertySection {

	/** The from section */
	CategorySection fFromSection = null;

	/** The current copy rule being edited. */
	Variable fVariable;


	/**
	 * Variable initialization section. This is the LHS of the copy rule.
	 * 
	 */

	public VariableInitializationSection()  {

		super();
		this.fFromSection = new CategorySection(this);

		this.fFromSection.fAllowed = new IAssignCategory[] {
					new VariablePartAssignCategory( this ),
					new ExpressionAssignCategory( this ),
					new LiteralAssignCategory( this ),
					new VariablePropertyAssignCategory( this ),
					new PartnerRoleAssignCategory( this, true ),
					new EndpointReferenceAssignCategory( this ),
					new OpaqueAssignCategory( this ),
					new NullAssignCategory (this  )
		};

	}

	protected boolean isFromAffected ( Notification n ) {
		return n.getFeature() == BPELPackage.eINSTANCE.getVariable_From();
	}


	@Override
	protected MultiObjectAdapter[] createAdapters() {
		return new MultiObjectAdapter[] {
					/* model object */
					new MultiObjectAdapter() {

						@Override
						public void notify (Notification n) {
							if ( isFromAffected (n) ) {
								selectCategoriesForInput( null );
							}
						}
					},
		};
	}

	protected void createCategorySectionWidgets (Composite composite, final CategorySection section ) {

		FlatFormData data;

		section.fLabel = this.fWidgetFactory.createLabel(composite, Messages.AssignImplDetails_From__1 );

		section.fCombo = new Combo(composite,SWT.FLAT | SWT.BORDER | SWT.READ_ONLY );
		data = new FlatFormData();

		data.left = new FlatFormAttachment(0, BPELUtil.calculateLabelWidth(section.fLabel, STANDARD_LABEL_WIDTH_SM));
		data.right = new FlatFormAttachment(100, 0);

		data.top = new FlatFormAttachment(0,0);
		section.fCombo.setLayoutData(data);

		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, 0);
		data.right = new FlatFormAttachment(section.fCombo, -IDetailsAreaConstants.CENTER_SPACE);
		data.top = new FlatFormAttachment(section.fCombo, 0, SWT.CENTER);
		section.fLabel.setLayoutData(data);

		for (IAssignCategory category : section.fAllowed ) {
			section.fCombo.add( category.getName() );
		}

		section.fCombo.addSelectionListener(new SelectionListener() {

			public void widgetSelected (SelectionEvent e) {
				int index = section.fCombo.getSelectionIndex();

				updateCategorySelection ( section , index , true );

			}
			// TODO: is this correct?
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		section.fOuterComposite = createFlatFormComposite(composite);
		data = new FlatFormData();
		data.left = new FlatFormAttachment(section.fLabel, 0, SWT.LEFT);
		data.right = new FlatFormAttachment(section.fCombo, 0, SWT.RIGHT);
		data.top = new FlatFormAttachment(section.fCombo, IDetailsAreaConstants.VSPACE);
		data.bottom = new FlatFormAttachment(100,0);
		section.fOuterComposite.setLayoutData(data);
	}


	/**
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#shouldUseExtraSpace()
	 */
	@Override
	public boolean shouldUseExtraSpace() {
		return true;
	}


	@Override
	protected void createClient(Composite parent) {

		Composite composite = createFlatFormComposite(parent);
		Composite mainComposite = createFlatFormComposite(composite);
		FlatFormData data = new FlatFormData();
		data.left = new FlatFormAttachment ( null, IDetailsAreaConstants.HSPACE );
		data.top = new FlatFormAttachment(0,0);
		data.right = new FlatFormAttachment(100,0);
		data.bottom = new FlatFormAttachment(100,0);
		mainComposite.setLayoutData(data);

		createCategorySectionWidgets(mainComposite,this.fFromSection );

	}


	// Total Hack until we have Copy objects in graphical editor
	@Override
	protected void basicSetInput (EObject newInput) {
		super.basicSetInput(newInput);

		this.fVariable = getModel();
		selectCategoriesForInput (this.fVariable);
	}




	/**
	 * Called when the copy rule changes or is created.
	 *
	 */
	protected void selectCategoriesForInput (Variable variable) {

		if (variable != null) {
			this.fVariable = variable;
		}

		for (IAssignCategory category : this.fFromSection.fAllowed) {
			if (category.isCategoryForModel( this.fVariable.getFrom() )) {
				updateCategorySelection(this.fFromSection,category,false);
				return;
			}
		}

		/** In case we can't find the appropriate one, just display the first one */
		if ( this.fFromSection.fCurrent == null)  {
			updateCategorySelection(this.fFromSection,0,false);
		}
	}


	/**
	 * @see org.eclipse.bpel.ui.properties.BPELPropertySection#refresh()
	 */
	@Override
	public void refresh() {
		super.refresh();
	}

	/**
	 * @see org.eclipse.bpel.ui.properties.BPELPropertySection#aboutToBeHidden()
	 */
	@Override
	public void aboutToBeHidden() {
		super.aboutToBeHidden();

		if (this.fFromSection.fCurrent != null) {
			this.fFromSection.fCurrent.aboutToBeHidden();
		}

	}

	/**
	 * @see org.eclipse.bpel.ui.properties.BPELPropertySection#aboutToBeShown()
	 */
	@Override
	public void aboutToBeShown() {
		super.aboutToBeShown();
		if (this.fFromSection.fCurrent != null) {
			this.fFromSection.fCurrent.aboutToBeShown();
		}
	}


	void updateCategorySelection ( CategorySection section, int index , boolean bVisual ) {
		updateCategorySelection(section, section.fAllowed[index], bVisual);
	}

	void updateCategorySelection ( CategorySection section, IAssignCategory newCurrent, boolean bVisual) {

		if (section.fCurrent != newCurrent) {
			/** Hide current */
			section.hideCurrent();

			/** Update current to the one that picked from */
			section.fCurrent = newCurrent;
			section.ensureCategoryCompositeCreated();
		}

		/** Visual selection */

		if (bVisual || this.fVariable.getFrom() == null) {

			Command cmd ;

			if ( section.fCurrent.isCategoryForModel (null) == false ) {
				cmd = new SetCommand( getInput(), BPELFactory.eINSTANCE.createFrom(), BPELPackage.eINSTANCE.getVariable_From() );
			} else {
				cmd = new SetCommand( getInput(), null , BPELPackage.eINSTANCE.getVariable_From() );

			}
			// Execute this right away.
			getBPELEditor().getCommandFramework().execute( cmd );
		}

		if (!bVisual) {
			section.updateCombo();
		}

		// Set the input of the category after we insert the to or from into the model.
		section.fCurrent.setInput( this.fVariable.getFrom() );
		section.showCurrent();
		section.fCurrent.refresh();

		// TODO: should the categories only store when a widget change is committed?
		// Cons of that idea:
		//   - Changing the category in the combo, but *not* changing anything else,
		//     then clicking elsewhere and back, would cause the combo to revert.
		//   - The OpaqueAssignCategory doesn't have any widgets..
	}

	/**
	 * @see org.eclipse.bpel.ui.properties.BPELPropertySection#getUserContext()
	 */
	@Override
	public Object getUserContext() {
		return null;
	}


	/**
	 * @see org.eclipse.bpel.ui.properties.BPELPropertySection#restoreUserContext(java.lang.Object)
	 */
	@SuppressWarnings("boxing")
	@Override
	public void restoreUserContext(Object userContext) {

	}

	/**
	 * @see org.eclipse.bpel.ui.properties.BPELPropertySection#gotoMarker(org.eclipse.core.resources.IMarker)
	 */
	@Override
	public void gotoMarker(IMarker marker) {
		refresh();
	}
}
