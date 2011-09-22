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

import org.eclipse.bpel.common.ui.details.ChangeHelper;
import org.eclipse.bpel.common.ui.details.IDetailsAreaConstants;
import org.eclipse.bpel.common.ui.details.viewers.CComboViewer;
import org.eclipse.bpel.common.ui.flatui.FlatFormAttachment;
import org.eclipse.bpel.common.ui.flatui.FlatFormData;
import org.eclipse.bpel.model.EndpointReferenceRole;
import org.eclipse.bpel.model.PartnerLink;
import org.eclipse.bpel.model.partnerlinktype.Role;
import org.eclipse.bpel.ui.Messages;
import org.eclipse.bpel.ui.adapters.IVirtualCopyRuleSide;
import org.eclipse.bpel.ui.details.providers.ModelLabelProvider;
import org.eclipse.bpel.ui.details.providers.PartnerLinkContentProvider;
import org.eclipse.bpel.ui.details.providers.RoleContentProvider;
import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

/**
 * An AssignCategory allowing the selection of a Partner. If the category is
 * used for a From (not for a To), it also provides a combo for choosing which
 * Role of the Partner is referenced (myRole or partnerRole).
 */
public class PartnerRoleAssignCategory extends AssignCategoryBase {

	protected static final int PARTNER_CONTEXT = 0;
	protected static final int ROLE_CONTEXT = 1;

	protected int lastChangeContext = -1;

	Table partnerTable;
	TableViewer partnerViewer;
	ChangeHelper partnerChangeHelper, roleChangeHelper;

	CCombo roleCombo;
	CComboViewer roleViewer;

	protected boolean fIsFrom = false;

	protected PartnerRoleAssignCategory(BPELPropertySection ownerSection,
				boolean isFrom) {
		super(ownerSection);
		this.fIsFrom = isFrom;
	}

	/**
	 * @see org.eclipse.bpel.ui.properties.IAssignCategory#getName()
	 */
	@Override
	public String getName() {
		return Messages.PartnerRoleAssignCategory_Partner_Reference_1;
	}

	@Override
	protected void createClient2(Composite parent) {
		FlatFormData data;

		if (this.fIsFrom) {
			this.roleChangeHelper = new ChangeHelper(getCommandFramework()) {
				public String getLabel() {
					return Messages.PartnerRoleAssignCategory_Role_Change_2;
				}

				public Command createApplyCommand() {
					PartnerRoleAssignCategory.this.lastChangeContext = ROLE_CONTEXT;
					return wrapInShowContextCommand(newStoreModelCommand());
				}

				public void restoreOldState() {
					updateCategoryWidgets();
				}
			};

			Label roleLabel = this.fWidgetFactory.createLabel(parent,
						Messages.PartnerRoleAssignCategory_Role__3);
			this.roleCombo = this.fWidgetFactory.createCCombo(parent);

			data = new FlatFormData();
			data.left = new FlatFormAttachment(0, BPELUtil.calculateLabelWidth(
						roleLabel, STANDARD_LABEL_WIDTH_SM));
			data.right = new FlatFormAttachment(100, 0);
			data.bottom = new FlatFormAttachment(100, 0);
			this.roleCombo.setLayoutData(data);

			data = new FlatFormData();
			data.left = new FlatFormAttachment(0, 0);
			data.right = new FlatFormAttachment(this.roleCombo,
						-IDetailsAreaConstants.HSPACE);
			data.top = new FlatFormAttachment(this.roleCombo, 0, SWT.CENTER);
			roleLabel.setLayoutData(data);

			this.roleViewer = new CComboViewer(this.roleCombo);
			this.roleViewer.setContentProvider(new RoleContentProvider());
			this.roleViewer.setLabelProvider(new ModelLabelProvider());
			// TODO: does this need a sorter?

			this.roleChangeHelper.startListeningTo(this.roleCombo);
		}

		this.partnerChangeHelper = new ChangeHelper(getCommandFramework()) {
			public String getLabel() {
				return Messages.PartnerRoleAssignCategory_Partner_Change_4;
			}

			public Command createApplyCommand() {
				PartnerRoleAssignCategory.this.lastChangeContext = PARTNER_CONTEXT;
				return wrapInShowContextCommand(newStoreModelCommand());
			}

			public void restoreOldState() {
				updateCategoryWidgets();
			}
		};

		this.partnerTable = this.fWidgetFactory.createTable(parent, SWT.NONE);
		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, 0);
		data.top = new FlatFormAttachment(0, 0);
		data.right = new FlatFormAttachment(100, 0);
		if (this.fIsFrom) {
			data.bottom = new FlatFormAttachment(this.roleCombo,
						-IDetailsAreaConstants.VSPACE);
		} else {
			data.bottom = new FlatFormAttachment(100, 0);
		}

		// data.borderType = IBorderConstants.BORDER_1P1_BLACK;
		this.partnerTable.setLayoutData(data);

		this.partnerViewer = new TableViewer(this.partnerTable);
		this.partnerViewer.setContentProvider(new PartnerLinkContentProvider());
		this.partnerViewer.setLabelProvider(new ModelLabelProvider());
		// partnerViewer.setSorter(ModelViewerSorter.getInstance());
		this.partnerViewer.setInput(getProcess());

		this.partnerChangeHelper.startListeningTo(this.partnerTable);

		this.partnerTable.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection sel = (IStructuredSelection) PartnerRoleAssignCategory.this.partnerViewer
				.getSelection();
				PartnerLink partnerLink = (PartnerLink) sel.getFirstElement();
				if (PartnerRoleAssignCategory.this.roleViewer != null)
					PartnerRoleAssignCategory.this.roleViewer.setInput(partnerLink);
				if (!PartnerRoleAssignCategory.this.partnerChangeHelper.isNonUserChange()) {
					if (PartnerRoleAssignCategory.this.roleCombo != null) {
						PartnerRoleAssignCategory.this.roleChangeHelper.startNonUserChange();
						try {
							PartnerRoleAssignCategory.this.roleCombo.select(0);
						} finally {
							PartnerRoleAssignCategory.this.roleChangeHelper.finishNonUserChange();
						}
					}
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}

	/**
	 * @see org.eclipse.bpel.ui.properties.IAssignCategory#isCategoryForModel(org.eclipse.emf.ecore.EObject)
	 */

	@Override
	public boolean isCategoryForModel(EObject aModel) {
		IVirtualCopyRuleSide side = BPELUtil.adapt(aModel,
					IVirtualCopyRuleSide.class);
		return side != null && side.getPartnerLink() != null;
	}

	@Override
	protected void load(IVirtualCopyRuleSide side) {

		PartnerLink selPartnerLink = side.getPartnerLink();
		boolean isMyRole = false;
		EndpointReferenceRole reference = side.getEndpointReference();

		if (reference != null) {
			int roleMarker = reference.getValue();
			if (roleMarker == EndpointReferenceRole.MY_ROLE) {
				isMyRole = true;
			}
		}

		this.partnerChangeHelper.startNonUserChange();
		try {
			if (selPartnerLink == null) {
				this.partnerViewer.setSelection(StructuredSelection.EMPTY, false);
				if (this.roleViewer != null) {
					this.roleChangeHelper.startNonUserChange();
					try {
						this.roleViewer.setInput(null);
					} finally {
						this.roleChangeHelper.finishNonUserChange();
					}
				}
			} else {
				this.partnerViewer.setSelection(new StructuredSelection(
							selPartnerLink), true);
				if (this.roleViewer != null) {
					this.roleChangeHelper.startNonUserChange();
					try {
						this.roleViewer.setInput(selPartnerLink);
						Role role = isMyRole ? selPartnerLink.getMyRole()
									: selPartnerLink.getPartnerRole();
						if (role != null) {
							this.roleViewer.setSelection(new StructuredSelection(
										role), true);
						} else {
							this.roleViewer.setSelection(StructuredSelection.EMPTY);
						}
					} finally {
						this.roleChangeHelper.finishNonUserChange();
					}
				}
			}
		} finally {
			this.partnerChangeHelper.finishNonUserChange();
		}
	}

	@Override
	protected void store(IVirtualCopyRuleSide side) {
		IStructuredSelection sel = (IStructuredSelection) this.partnerViewer
		.getSelection();
		PartnerLink partnerLink = (PartnerLink) sel.getFirstElement();

		side.setPartnerLink(partnerLink);

		// To: doesn't have roleViewer, so check roleViewer for null
		if (this.roleViewer == null) {
			return;
		}

		Object role = ((IStructuredSelection) this.roleViewer.getSelection())
		.getFirstElement();
		if (role == null) {
			return;
		}
		if (role.equals(partnerLink.getMyRole())) {
			side.setEndpointReference(EndpointReferenceRole.MY_ROLE_LITERAL);
		} else if (role.equals(partnerLink.getPartnerRole())) {
			side
			.setEndpointReference(EndpointReferenceRole.PARTNER_ROLE_LITERAL);
		}

	}

	/**
	 * @see org.eclipse.bpel.ui.properties.BPELPropertySection#getUserContext()
	 */
	@Override
	public Object getUserContext() {
		return Integer.valueOf( this.lastChangeContext );
	}

	/**
	 * @see org.eclipse.bpel.ui.properties.BPELPropertySection#restoreUserContext(java.lang.Object)
	 */
	@Override
	public void restoreUserContext(Object userContext) {
		int i = ((Integer) userContext).intValue();
		switch (i) {
		case PARTNER_CONTEXT:
			this.partnerTable.setFocus();
			return;
		case ROLE_CONTEXT:
			this.roleCombo.setFocus();
			return;
		}
		throw new IllegalStateException();
	}
}
