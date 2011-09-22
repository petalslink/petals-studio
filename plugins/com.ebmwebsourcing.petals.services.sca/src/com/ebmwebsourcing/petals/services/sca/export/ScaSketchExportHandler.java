/****************************************************************************
 * 
 * Copyright (c) 2010-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.sca.export;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;


/**
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ScaSketchExportHandler extends AbstractHandler {

	public Object execute( ExecutionEvent event ) throws ExecutionException {

		// Filter the target
		// Most of the job is made by the platform, as defined in the plugin.xml
		final ISelection selection = HandlerUtil.getActiveWorkbenchWindow( event ).getActivePage().getSelection();
		if( selection == null
					|| selection.isEmpty()
					|| !( selection instanceof IStructuredSelection ))
			return null;

		Object o = ((IStructuredSelection) selection).getFirstElement();
		if( !( o instanceof IProject ))
			return null;

		// Launch the wizard
		IWorkbench workbench = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getWorkbench();
		ScaSketchConversionWizard wizard = new ScaSketchConversionWizard((IProject) o);

		final Display display = PlatformUI.getWorkbench().getDisplay();
		WizardDialog dlg = new WizardDialog( new Shell( display ), wizard ) {
			@Override
			protected Control createContents( Composite parent ) {

				Control c = super.createContents( parent );

				Rectangle rect = display.getBounds();
				Point shellPoint = getShell().getSize();
				int x = (rect.width - shellPoint.x) / 2 + rect.x;
				int y = (rect.height - shellPoint.y) / 2 + rect.y;
				getShell().setLocation( x, y );

				return c;
			}
		};

		workbench.saveAllEditors( true );
		dlg.setPageSize( 400, 300 );
		dlg.open();

		return null;
	}
}
