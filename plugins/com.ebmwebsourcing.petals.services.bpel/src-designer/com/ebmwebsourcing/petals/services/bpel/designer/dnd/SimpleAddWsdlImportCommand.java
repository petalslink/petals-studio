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

import java.util.List;

import org.eclipse.bpel.ui.commands.util.AutoUndoCommand;
import org.eclipse.core.runtime.Assert;
import org.eclipse.wst.wsdl.Definition;
import org.eclipse.wst.wsdl.Import;

/**
 * An EMF command to add an import in a WSDL model.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SimpleAddWsdlImportCommand extends AutoUndoCommand {

	private final Definition wsdlDefinition;
	private final Import newImport;


	/**
	 * Constructor.
	 * @param domain
	 * @param wsdlDefinition
	 * @param newImport
	 */
	public SimpleAddWsdlImportCommand( Definition wsdlDefinition, Import newImport ) {
		super( wsdlDefinition );
		this.wsdlDefinition = wsdlDefinition;
		this.newImport = newImport;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.edit.command.AbstractOverrideableCommand
	 * #doCanExecute()
	 */
	@Override
	public boolean canExecute() {

		if( ! super.canExecute())
			return false;

		for( Object o : this.wsdlDefinition.getImports().values()) {
			if( o instanceof List<?> ) {
				for( Object oo : ((List<?>) o)) {
					Definition d = ((Import) oo).getEDefinition();
					if( haveSameNamespace( d, this.newImport ))
						return false;
				}
			}

			// Case "org.eclipse.wst.Definition"
			else if( o instanceof Definition ) {
				Definition d = (Definition) o;;
				if( haveSameNamespace( d, this.newImport ))
					return false;
			}
		}

		return true;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.edit.command.AbstractOverrideableCommand
	 * #doExecute()
	 */
	@Override
	public void doExecute() {

		// Hack for the role: we need to define manually the name space prefix for the TNS of the business WSDL
		int cpt = 0;
		String prefix = "wsdl_0";
		while( this.wsdlDefinition.getNamespace( prefix ) != null ) {
			prefix = "wsdl_" + (++cpt);
		}

		this.wsdlDefinition.addNamespace( prefix, this.newImport.getNamespaceURI());
		this.wsdlDefinition.getNamespaces().put( prefix, this.newImport.getNamespaceURI());
		Assert.isNotNull( this.wsdlDefinition.getNamespace( prefix ), "Pas là" );

		// Add the import
		this.newImport.setEnclosingDefinition( this.wsdlDefinition );
		this.wsdlDefinition.addImport( this.newImport );
	}


	/**
	 * @param i1
	 * @param e2
	 * @return
	 */
	private boolean haveSameNamespace( Definition def, Import e2 ) {
		return def.getTargetNamespace() != null && def.getTargetNamespace().equals( e2.getNamespaceURI());
	}
}
