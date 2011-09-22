/****************************************************************************
 * 
 * Copyright (c) 2009-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.editor;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.ide.IDEActionFactory;
import org.eclipse.ui.part.MultiPageEditorActionBarContributor;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;

/**
 * Manages the installation/deinstallation of global actions for multi-page editors.
 * Responsible for the redirection of global actions to the active editor.
 * Multi-page contributor replaces the contributors for the individual editors in the multi-page editor.
 * 
 * TODO: review it.
 */
public class ActionBarContributor extends MultiPageEditorActionBarContributor {

	/** */
	private IEditorPart activeEditorPart;
	/** */
	private Action sampleAction;


	/**
	 * Creates a multi-page contributor.
	 */
	public ActionBarContributor() {
		super();
		createActions();
	}

	/**
	 * Returns the action registed with the given text editor.
	 * @return IAction or null if editor is null.
	 */
	protected IAction getAction(ITextEditor editor, String actionID) {
		return (editor == null ? null : editor.getAction(actionID));
	}

	/* (non-JavaDoc)
	 * Method declared in AbstractMultiPageEditorActionBarContributor.
	 */
	@Override
	public void setActivePage(IEditorPart part) {
		if (this.activeEditorPart == part)
			return;

		this.activeEditorPart = part;

		IActionBars actionBars = getActionBars();
		if (actionBars != null) {

			ITextEditor editor = (part instanceof ITextEditor) ? (ITextEditor) part : null;

			actionBars.setGlobalActionHandler(
						ActionFactory.DELETE.getId(),
						getAction(editor, ITextEditorActionConstants.DELETE));
			actionBars.setGlobalActionHandler(
						ActionFactory.UNDO.getId(),
						getAction(editor, ITextEditorActionConstants.UNDO));
			actionBars.setGlobalActionHandler(
						ActionFactory.REDO.getId(),
						getAction(editor, ITextEditorActionConstants.REDO));
			actionBars.setGlobalActionHandler(
						ActionFactory.CUT.getId(),
						getAction(editor, ITextEditorActionConstants.CUT));
			actionBars.setGlobalActionHandler(
						ActionFactory.COPY.getId(),
						getAction(editor, ITextEditorActionConstants.COPY));
			actionBars.setGlobalActionHandler(
						ActionFactory.PASTE.getId(),
						getAction(editor, ITextEditorActionConstants.PASTE));
			actionBars.setGlobalActionHandler(
						ActionFactory.SELECT_ALL.getId(),
						getAction(editor, ITextEditorActionConstants.SELECT_ALL));
			actionBars.setGlobalActionHandler(
						ActionFactory.FIND.getId(),
						getAction(editor, ITextEditorActionConstants.FIND));
			actionBars.setGlobalActionHandler(
						IDEActionFactory.BOOKMARK.getId(),
						getAction(editor, IDEActionFactory.BOOKMARK.getId()));
			actionBars.updateActionBars();
		}
	}

	/**
	 *
	 */
	private void createActions() {
		this.sampleAction = new Action() {
			@Override
			public void run() {
				MessageDialog.openInformation(null, "Wizard Plug-in", "Sample Action Executed"); //$NON-NLS-1$ //$NON-NLS-2$
			}
		};
		this.sampleAction.setText("Sample Action"); //$NON-NLS-1$
		this.sampleAction.setToolTipText("Sample Action tool tip"); //$NON-NLS-1$
		this.sampleAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
					getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorActionBarContributor#contributeToMenu(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	public void contributeToMenu(IMenuManager manager) {
		IMenuManager menu = new MenuManager("Editor &Menu"); //$NON-NLS-1$
		manager.prependToGroup(IWorkbenchActionConstants.MB_ADDITIONS, menu);
		menu.add(this.sampleAction);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorActionBarContributor#contributeToToolBar(org.eclipse.jface.action.IToolBarManager)
	 */
	@Override
	public void contributeToToolBar(IToolBarManager manager) {
		manager.add(new Separator());
		manager.add(this.sampleAction);
	}
}
