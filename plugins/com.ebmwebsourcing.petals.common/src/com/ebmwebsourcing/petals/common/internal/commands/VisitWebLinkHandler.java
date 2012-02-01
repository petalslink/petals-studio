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
package com.ebmwebsourcing.petals.common.internal.commands;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

/**
 * A handler that opens the default Web browser at a given URL.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class VisitWebLinkHandler extends AbstractHandler {

	private static final String URL_PARAMETER = "url";


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler
	 * #execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute( ExecutionEvent event ) throws ExecutionException {

		String urlAsString = event.getParameter( URL_PARAMETER );
		if( urlAsString != null ) {
			try {
				URL url = new URL( urlAsString.trim());
				PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser().openURL( url );

			} catch( PartInitException e ) {
				PetalsCommonPlugin.log( e, IStatus.ERROR );

			} catch( MalformedURLException e ) {
				PetalsCommonPlugin.log( e, IStatus.ERROR );
			}
		}

		return null;
	}
}
