/****************************************************************************
 *
 * Copyright (c) 2011-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.commands;

import java.util.Collections;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.ui.preferences.BuildPathsPropertyPage;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PreferencesUtil;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

/**
 * @author Mickaël Istria - EBM WebSourcing
 */
public class ConfigureBuildPathCommandHandler extends AbstractHandler {

	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.core.commands.AbstractHandler
	 * #execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {

		try {
			IStructuredSelection selection = (IStructuredSelection)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
			if( ! selection.isEmpty()) {
				IJavaProject project = (IJavaProject) ((IProject) selection.getFirstElement()).getNature(JavaCore.NATURE_ID);

				// Copy-pasted from ConfigureBuildPathAction
				PreferencesUtil.createPropertyDialogOn(
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
						project,
						BuildPathsPropertyPage.PROP_ID,
						null,
						Collections.EMPTY_MAP).open();
			}

		} catch (Exception ex) {
			throw new ExecutionException("Could not cast to Java Project", ex);
		}

		return null;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler
	 * #setEnabled(java.lang.Object)
	 */
	@Override
	public void setEnabled( Object evaluationContext ) {

		boolean enabled = false;
		try {
			if( PlatformUI.getWorkbench() != null
					&& PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
					&& PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService() != null
					&& PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection() instanceof IStructuredSelection ) {

				IStructuredSelection selection = (IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
				enabled = selection.size() == 1
						&& selection.getFirstElement() instanceof IProject
						&& ((IProject)selection.getFirstElement()).isAccessible()
						&& ((IProject)selection.getFirstElement()).hasNature( JavaCore.NATURE_ID );
			}

		} catch (Exception ex) {
			PetalsCommonPlugin.log(ex, IStatus.ERROR);
		}

		super.setBaseEnabled( enabled );
	}
}
