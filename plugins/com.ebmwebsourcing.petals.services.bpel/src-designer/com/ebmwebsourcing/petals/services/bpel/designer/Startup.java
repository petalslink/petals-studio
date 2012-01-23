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

package com.ebmwebsourcing.petals.services.bpel.designer;

import org.eclipse.bpel.ui.BPELEditor;
import org.eclipse.bpel.ui.BPELMultipageEditorPart;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PlatformUI;

import com.ebmwebsourcing.petals.services.bpel.designer.dnd.PetalsDropTargetListener;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 * @author Mickaël Istria - EBM WebSourcing
 */
public class Startup implements IStartup {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IStartup#earlyStartup()
	 */
	@Override
	public void earlyStartup() {

		// Listener for DND of PetalsServices to BPEL Designer
		// Alternative implementation. See Eclipse bug 367715 for another approach
		Display.getDefault().syncExec( new Runnable() {
			@Override
			public void run() {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().addPartListener( new IPartListener2() {

					/*
					 * (non-Javadoc)
					 * @see org.eclipse.ui.IPartListener2
					 * #partVisible(org.eclipse.ui.IWorkbenchPartReference)
					 */
					@Override
					public void partVisible(IWorkbenchPartReference partRef) {
						// nothing
					}


					/*
					 * (non-Javadoc)
					 * @see org.eclipse.ui.IPartListener2
					 * #partOpened(org.eclipse.ui.IWorkbenchPartReference)
					 */
					@Override
					public void partOpened(IWorkbenchPartReference partRef) {

						if (partRef instanceof IEditorReference) {
							IEditorReference editorRef = (IEditorReference)partRef;
							if (editorRef.getEditor(false) instanceof BPELEditor) {
								BPELEditor bpelEd = (BPELEditor)editorRef.getEditor(false);
								bpelEd.getGraphicalViewer().addDropTargetListener(new PetalsDropTargetListener(bpelEd.getGraphicalViewer(), bpelEd));

							} else if (editorRef.getEditor(false) instanceof BPELMultipageEditorPart) {
								BPELMultipageEditorPart bpelEd = (BPELMultipageEditorPart) editorRef.getEditor(false);
								IEditorPart ed = bpelEd.getActiveEditor();

								if (ed instanceof BPELEditor) {
									BPELEditor editor = (BPELEditor) ed;
									((BPELEditor)ed).getGraphicalViewer().addDropTargetListener( new PetalsDropTargetListener(editor.getGraphicalViewer(), editor));
								}

							}
						}

					}


					/*
					 * (non-Javadoc)
					 * @see org.eclipse.ui.IPartListener2
					 * #partInputChanged(org.eclipse.ui.IWorkbenchPartReference)
					 */
					@Override
					public void partInputChanged(IWorkbenchPartReference partRef) {
						// nothing
					}


					/*
					 * (non-Javadoc)
					 * @see org.eclipse.ui.IPartListener2
					 * #partHidden(org.eclipse.ui.IWorkbenchPartReference)
					 */
					@Override
					public void partHidden(IWorkbenchPartReference partRef) {
						// nothing
					}


					/*
					 * (non-Javadoc)
					 * @see org.eclipse.ui.IPartListener2
					 * #partDeactivated(org.eclipse.ui.IWorkbenchPartReference)
					 */
					@Override
					public void partDeactivated(IWorkbenchPartReference partRef) {
						// nothing
					}


					/*
					 * (non-Javadoc)
					 * @see org.eclipse.ui.IPartListener2
					 * #partClosed(org.eclipse.ui.IWorkbenchPartReference)
					 */
					@Override
					public void partClosed(IWorkbenchPartReference partRef) {
						// nothing
					}


					/*
					 * (non-Javadoc)
					 * @see org.eclipse.ui.IPartListener2
					 * #partBroughtToTop(org.eclipse.ui.IWorkbenchPartReference)
					 */
					@Override
					public void partBroughtToTop(IWorkbenchPartReference partRef) {
						// nothing
					}


					/*
					 * (non-Javadoc)
					 * @see org.eclipse.ui.IPartListener2
					 * #partActivated(org.eclipse.ui.IWorkbenchPartReference)
					 */
					@Override
					public void partActivated(IWorkbenchPartReference partRef) {
						// nothing
					}
				});
			}
		});
	}
}
