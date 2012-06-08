/******************************************************************************
 * Copyright (c) 2012, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.bpel.designer.dnd;

import org.eclipse.bpel.model.Import;
import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.ui.IBPELUIConstants;
import org.eclipse.bpel.ui.commands.util.AutoUndoCommand;
import org.eclipse.bpel.ui.util.ModelHelper;

/**
 * An EMF command to add an import in a WSDL model.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SimpleAddBpelImportCommand extends AutoUndoCommand {

	private final Process bpelProcess;
	private final Import newImport;


	/**
	 * Constructor.
	 * @param domain
	 * @param wsdlDefinition
	 * @param newImport
	 */
	public SimpleAddBpelImportCommand( Process bpelProcess, Import newImport ) {
		super( IBPELUIConstants.CMD_ADD_IMPORT, bpelProcess );
		this.bpelProcess = bpelProcess;
		this.newImport = newImport;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command
	 * #canExecute()
	 */
	@Override
	public boolean canExecute() {
		return super.canExecute() && ! ModelHelper.containsImport( this.bpelProcess, this.newImport );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.edit.command.AbstractOverrideableCommand
	 * #doExecute()
	 */
	@Override
	public void doExecute() {
		this.bpelProcess.getImports().add( this.newImport );
	}
}
