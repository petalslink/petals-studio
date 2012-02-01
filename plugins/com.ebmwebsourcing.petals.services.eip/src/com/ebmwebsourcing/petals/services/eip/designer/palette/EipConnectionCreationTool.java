/****************************************************************************
 * 
 * Copyright (c) 2011-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
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
