/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.eip.designer.palette;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.tools.ConnectionCreationTool;

import com.ebmwebsourcing.petals.services.eip.designer.edit.ConnectionCreationFactory;
import com.ebmwebsourcing.petals.services.eip.designer.edit.parts.EipNodeEditPart;

/**
 * A tool entry to create EIP connections.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipConnectionCreationTool extends ConnectionCreationTool {

	/**
	 * Constructor.
	 */
	public EipConnectionCreationTool() {
		super( new ConnectionCreationFactory ());
		setUnloadWhenFinished( true );
	}


	/**
	 * Initializes a new connection and sets the source.
	 * @param sourcePart the source's edit part
	 */
	public void performConnectionStartWith( EditPart sourcePart ) {

		setConnectionSource( sourcePart );
		updateTargetRequest();
		Command cmd = ((EipNodeEditPart) sourcePart).getCommand( getTargetRequest());
		setCurrentCommand( cmd );
		setState( STATE_CONNECTION_STARTED );
	}
}
