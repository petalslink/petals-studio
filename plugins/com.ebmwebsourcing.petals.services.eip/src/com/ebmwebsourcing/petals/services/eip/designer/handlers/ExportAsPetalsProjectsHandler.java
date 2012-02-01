/****************************************************************************
 * 
 * Copyright (c) 2010-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.eip.designer.handlers;

import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
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

import com.ebmwebsourcing.petals.services.eip.PetalsEipPlugin;
import com.ebmwebsourcing.petals.services.eip.designer.EipDesignerSerializer;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipChain;
import com.ebmwebsourcing.petals.services.eip.designer.wizards.MavenProjectExportWizard;

/**
 * The default handler for the command "Validate jbi.xml".
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ExportAsPetalsProjectsHandler extends AbstractHandler {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler
	 * #execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute( ExecutionEvent event ) throws ExecutionException {

		// Get the selection's content
		IFile eipChainFile = null;
		ISelection s = null;
		try {
			s = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
			Object o = ((IStructuredSelection) s).getFirstElement();
			if( o instanceof IFile
						&& ((IFile) o).getName().endsWith( ".peip" ))
				eipChainFile = (IFile) o;
			else
				eipChainFile = null;

		} catch( Exception e1 ) {
			// nothing
		}

		// Load the model
		EipChain eipChain = null;
		if( eipChainFile != null ) {
			try {
				eipChain = EipDesignerSerializer.INSTANCE.read( eipChainFile.getLocation().toFile());

			} catch( IOException e ) {
				PetalsEipPlugin.log( e, IStatus.ERROR );
				MessageDialog.openError( new Shell(), "Export Error", "An error occurred while reading the file. Check the logs for more details." );
			}
		}

		// Open the export wizard
		if( eipChain != null ) {
			IWorkbench workbench = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getWorkbench();
			MavenProjectExportWizard wizard = new MavenProjectExportWizard( eipChain );

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
		}

		return null;
	}
}
