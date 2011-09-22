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
package org.eclipse.bpel.ui;

import java.util.List;

import org.eclipse.bpel.common.ui.tray.AddChildInTrayAction;
import org.eclipse.bpel.ui.actions.AutoArrangeFlowsAction;
import org.eclipse.bpel.ui.actions.BPELCopyAction;
import org.eclipse.bpel.ui.actions.BPELCutAction;
import org.eclipse.bpel.ui.actions.BPELDeleteAction;
import org.eclipse.bpel.ui.actions.BPELDuplicateAction;
import org.eclipse.bpel.ui.actions.BPELPasteAction;
import org.eclipse.bpel.ui.actions.EditPartContextAction;
import org.eclipse.bpel.ui.actions.MakePartner2WayAction;
import org.eclipse.bpel.ui.actions.ShowPaletteInPaletteViewAction;
import org.eclipse.bpel.ui.actions.ShowPropertiesViewAction;
import org.eclipse.bpel.ui.actions.ToggleAutoFlowLayout;
import org.eclipse.bpel.ui.actions.ToggleLayoutOrientationAction;
import org.eclipse.bpel.ui.actions.ToggleShowCompensationHandler;
import org.eclipse.bpel.ui.actions.ToggleShowEventHandler;
import org.eclipse.bpel.ui.actions.ToggleShowFaultHandler;
import org.eclipse.bpel.ui.actions.ToggleShowTerminationHandler;
import org.eclipse.bpel.ui.actions.editpart.IEditPartAction;
import org.eclipse.bpel.ui.adapters.IEditPartActionContributor;
import org.eclipse.bpel.ui.editparts.BPELEditPart;
import org.eclipse.bpel.ui.editparts.CorrelationSetsEditPart;
import org.eclipse.bpel.ui.editparts.MessageExchangesEditPart;
import org.eclipse.bpel.ui.editparts.PartnerLinksEditPart;
import org.eclipse.bpel.ui.editparts.ReferencePartnerLinksEditPart;
import org.eclipse.bpel.ui.editparts.VariablesEditPart;
import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;


/**
 * Process Context Menu Provider.
 * 
 * @author IBM
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 *
 */
public class ProcessContextMenuProvider extends ContextMenuProvider {
	private final ActionRegistry actionRegistry;
	private final ITextEditor textEditor;

	/**
	 * @param editor
	 * @param registry
	 */
	public ProcessContextMenuProvider(BPELEditor editor, ActionRegistry registry) {
		super(editor.getGraphicalViewer());
		this.actionRegistry = registry;
		this.textEditor = editor.getMultipageEditor().getTextEditor();
	}

	protected static final String EDITPART_ACTIONS = "org.eclipse.bpel.ui.EditPartActions"; //$NON-NLS-1$
	protected static final String FREQUENT_ACTIONS = "org.eclipse.bpel.ui.FrequentActions"; //$NON-NLS-1$

	protected static final String ADVANCED_ACTIONS = IWorkbenchActionConstants.MB_ADDITIONS;

	protected static final String LAYOUT_ACTIONS = "org.eclipse.bpel.ui.LayoutActions"; //$NON-NLS-1$
	protected static final String DEBUG_ACTIONS = "org.eclipse.bpel.ui.DebugActions"; //$NON-NLS-1$


	/**
	 * @see org.eclipse.gef.ContextMenuProvider#buildContextMenu(org.eclipse.jface.action.IMenuManager)
	 */

	@Override
	public void buildContextMenu(IMenuManager menu) {

		IAction action, action2;

		menu.add(new Separator(GEFActionConstants.GROUP_UNDO));
		menu.add(new Separator(EDITPART_ACTIONS));
		menu.add(new Separator(FREQUENT_ACTIONS));
		menu.add(new Separator(GEFActionConstants.GROUP_COPY));
		menu.add(new Separator(GEFActionConstants.GROUP_EDIT));
		menu.add(new Separator(GEFActionConstants.GROUP_REST));
		menu.add(new Separator(ADVANCED_ACTIONS));
		menu.add(new Separator(GEFActionConstants.GROUP_VIEW));
		menu.add(new Separator(LAYOUT_ACTIONS));
		menu.add(new Separator(DEBUG_ACTIONS));
		menu.add(new Separator(IWorkbenchActionConstants.GROUP_SHOW_IN));

		// Undo, Redo (always shown) and Revert (if appropriate)
		//		menu.appendToGroup(GEFActionConstants.GROUP_UNDO, actionRegistry.getAction(ActionFactory.UNDO.getId()));
		//		menu.appendToGroup(GEFActionConstants.GROUP_UNDO, actionRegistry.getAction(ActionFactory.REDO.getId()));
		menu.appendToGroup(GEFActionConstants.GROUP_UNDO, this.textEditor.getAction(ITextEditorActionConstants.UNDO));
		menu.appendToGroup(GEFActionConstants.GROUP_UNDO, this.textEditor.getAction(ITextEditorActionConstants.REDO));
		action = this.actionRegistry.getAction(ActionFactory.REVERT.getId());
		if (action.isEnabled()) menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

		// Cut, Copy, Paste (always shown)
		menu.appendToGroup(GEFActionConstants.GROUP_COPY, this.actionRegistry.getAction(BPELCutAction.ID));
		menu.appendToGroup(GEFActionConstants.GROUP_COPY, this.actionRegistry.getAction(BPELCopyAction.ID));
		menu.appendToGroup(GEFActionConstants.GROUP_COPY, this.actionRegistry.getAction(BPELPasteAction.ID));
		menu.appendToGroup(GEFActionConstants.GROUP_COPY, this.actionRegistry.getAction(BPELDuplicateAction.ACTION_ID));

		// Frequently-used actions
		List<?> selected = getViewer().getSelectedEditParts();
		if (selected.size() == 1) {// any more than 1 is ambiguous
			if (selected.get(0) instanceof BPELEditPart) {
				BPELEditPart p = (BPELEditPart)selected.get(0);
				Object model = p.getModel();
				IEditPartActionContributor contributor = BPELUtil.adapt(model, IEditPartActionContributor.class);
				if (contributor != null) {
					for (IEditPartAction epAction : contributor.getEditPartActions(p)) {
						String s = epAction.getToolTip();
						// TODO: change IEditPartAction to provide lifecycle management
						// for the images themselves (i.e. an action could either create/destroy
						// an image from the decriptor, or it could return a shared image and do
						// nothing on destroy).

						ImageDescriptor imageDes = epAction.getIcon();
						//Image image = epAction.getIconImg();
						if (s != null && imageDes != null) {
							EditPartContextAction conAction = new EditPartContextAction(null, p, epAction);
							menu.appendToGroup(EDITPART_ACTIONS, conAction);
						}
					}
				}
			}
		}

		// Add and Insert actions
		MenuManager addMenu = new MenuManager(Messages.ProcessContextMenuProvider_Add_1);
		MenuManager insertMenu = new MenuManager(Messages.ProcessContextMenuProvider_Insert_Before_2);

		// TODO: There should be a better way to do this
		BPELEditor bpelEditor = ((BPELMultipageEditorPart)(((DefaultEditDomain)getViewer().getEditDomain()).getEditorPart())).getDesignEditor();

		// add all the possible actions
		// TODO: need to be more selective here!  Only add actions that make sense for this
		// context..
		for (IAction anAction : bpelEditor.getAppendNewActions()) {
			if (anAction != null && anAction.isEnabled()) {
				addMenu.add(anAction);
			}
		}
		for (IAction anAction : bpelEditor.getInsertNewActions()) {
			if (anAction != null && anAction.isEnabled()) {
				insertMenu.add(anAction);
			}
		}

		if (!addMenu.isEmpty()) {
			menu.appendToGroup(FREQUENT_ACTIONS, addMenu);
		}
		if (!insertMenu.isEmpty()) {
			menu.appendToGroup(FREQUENT_ACTIONS, insertMenu);
		}

		// Change Type Actions
		MenuManager changeTypeMenu = new MenuManager(Messages.ProcessContextMenuProvider_Change_Type_3);

		for(IAction anAction : bpelEditor.getChangeTypeActions()) {
			if (anAction != null && anAction.isEnabled()) {
				changeTypeMenu.add( anAction );
			}
		}

		menu.appendToGroup(FREQUENT_ACTIONS, changeTypeMenu);

		action = this.actionRegistry.getAction(AddChildInTrayAction.calculateId(PartnerLinksEditPart.class));
		if (action != null && action.isEnabled()) {
			menu.appendToGroup(FREQUENT_ACTIONS, action);
		}

		action = this.actionRegistry.getAction(AddChildInTrayAction.calculateId(ReferencePartnerLinksEditPart.class));
		if (action != null && action.isEnabled()) {
			menu.appendToGroup(FREQUENT_ACTIONS, action);
		}

		action = this.actionRegistry.getAction(AddChildInTrayAction.calculateId(VariablesEditPart.class));
		if (action != null && action.isEnabled()) {
			menu.appendToGroup(FREQUENT_ACTIONS, action);
		}

		action = this.actionRegistry.getAction(AddChildInTrayAction.calculateId(CorrelationSetsEditPart.class));
		if (action != null && action.isEnabled()) {
			menu.appendToGroup(FREQUENT_ACTIONS, action);
		}

		action = this.actionRegistry.getAction(AddChildInTrayAction.calculateId(MessageExchangesEditPart.class));
		if (action != null && action.isEnabled()) {
			menu.appendToGroup(FREQUENT_ACTIONS, action);
		}

		action = this.actionRegistry.getAction(MakePartner2WayAction.ID);
		if (action != null && action.isEnabled()) {
			menu.appendToGroup(FREQUENT_ACTIONS, action);
		}

		// Delete (always shown) and Rename (if appropriate)
		menu.appendToGroup(GEFActionConstants.GROUP_EDIT, this.actionRegistry.getAction(BPELDeleteAction.ID));
		action = this.actionRegistry.getAction(ActionFactory.RENAME.getId());

		if (action != null && action.isEnabled()) {
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}

		action = this.actionRegistry.getAction(GEFActionConstants.ZOOM_IN);
		action2 = this.actionRegistry.getAction(GEFActionConstants.ZOOM_OUT);
		if (action.isEnabled() || action2.isEnabled()) {
			menu.appendToGroup(GEFActionConstants.GROUP_VIEW, action);
			menu.appendToGroup(GEFActionConstants.GROUP_VIEW, action2);
		}

		action = this.actionRegistry.getAction(ToggleAutoFlowLayout.ACTION_ID);
		if (action != null && action.isEnabled()) {
			menu.appendToGroup(LAYOUT_ACTIONS, action);
			action.setChecked(true);
		}

		action = this.actionRegistry.getAction(AutoArrangeFlowsAction.ACTION_ID);
		if (action != null && action.isEnabled()) {
			menu.appendToGroup(LAYOUT_ACTIONS, action);
		}

		action = this.actionRegistry.getAction(ToggleLayoutOrientationAction.ACTION_ID);
		if (action != null && action.isEnabled()) {
			menu.appendToGroup(LAYOUT_ACTIONS, action);
		}

		action = this.actionRegistry.getAction(ToggleShowFaultHandler.ACTION_ID);
		if (action != null && action.isEnabled()) {
			menu.appendToGroup(LAYOUT_ACTIONS, action);
			action.setChecked(true);
		}

		action = this.actionRegistry.getAction(ToggleShowCompensationHandler.ACTION_ID);
		if (action != null && action.isEnabled()) {
			menu.appendToGroup(LAYOUT_ACTIONS, action);
			action.setChecked(true);
		}

		action = this.actionRegistry.getAction(ToggleShowTerminationHandler.ACTION_ID);
		if (action != null && action.isEnabled()) {
			menu.appendToGroup(LAYOUT_ACTIONS, action);
			action.setChecked(true);
		}

		action = this.actionRegistry.getAction(ToggleShowEventHandler.ACTION_ID);
		if (action != null && action.isEnabled()) {
			menu.appendToGroup(LAYOUT_ACTIONS, action);
			action.setChecked(true);
		}

		action = this.actionRegistry.getAction(ShowPropertiesViewAction.ACTION_ID);
		if (action != null && action.isEnabled()) {
			menu.appendToGroup(IWorkbenchActionConstants.GROUP_SHOW_IN, action);
			action.setChecked(false);
		}

		action = this.actionRegistry.getAction(ShowPaletteInPaletteViewAction.ACTION_ID);
		if (action != null && action.isEnabled()) {
			menu.appendToGroup(IWorkbenchActionConstants.GROUP_SHOW_IN, action);
			action.setChecked(false);
		}
	}
}