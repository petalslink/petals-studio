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

import org.eclipse.ui.IStartup;

import com.ebmwebsourcing.petals.services.bpel.designer.provisional.PetalsBpelModules;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class Startup implements IStartup {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IStartup#earlyStartup()
	 */
	public void earlyStartup() {

		// Initialize the Petals BPEL modules
		PetalsBpelModules.getBpelEngineValidator();
		PetalsBpelModules.getBpelEngineAnalyzer();
	}
}
