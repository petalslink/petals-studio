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
 
package com.ebmwebsourcing.petals.services.eip.designer.edit.commands;

import org.eclipse.gef.commands.Command;

import com.ebmwebsourcing.petals.services.eip.designer.model.EipChain;

/**
 * A command to update an attribute of an EIP chain.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipChainSetAttributeCommand extends Command {

	private final String property;
	private String oldValue, newValue;
	private EipChain eipChain;



	/**
	 * Constructor.
	 * @param property
	 */
	public EipChainSetAttributeCommand( String property ) {
		this.property = property;
	}


	/**
	 * @param newValue the newValue to set
	 */
	public void setNewValue( String newValue ) {
		this.newValue = newValue;
	}


	/**
	 * @param eipChain the eipChain to set
	 */
	public void setEipChain( EipChain eipChain ) {
		this.eipChain = eipChain;

		if( EipChain.PROPERTY_CHAIN_TITLE.equals( this.property ))
			this.oldValue = eipChain.getTitle();
		else if( EipChain.PROPERTY_CHAIN_DESCRIPTION.equals( this.property ))
			this.oldValue = eipChain.getDescription();
		else if( EipChain.PROPERTY_CHAIN_VERSION.equals( this.property ))
			this.oldValue = eipChain.getVersion();
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	@Override
	public boolean canExecute() {
		return true;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		return true;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {

		if( EipChain.PROPERTY_CHAIN_TITLE.equals( this.property ))
			this.eipChain.setTitle( this.newValue );
		else if( EipChain.PROPERTY_CHAIN_DESCRIPTION.equals( this.property ))
			this.eipChain.setDescription( this.newValue );
		else if( EipChain.PROPERTY_CHAIN_VERSION.equals( this.property ))
			this.eipChain.setVersion( this.newValue );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {

		if( EipChain.PROPERTY_CHAIN_TITLE.equals( this.property ))
			this.eipChain.setTitle( this.oldValue );
		else if( EipChain.PROPERTY_CHAIN_DESCRIPTION.equals( this.property ))
			this.eipChain.setDescription( this.oldValue );
		else if( EipChain.PROPERTY_CHAIN_VERSION.equals( this.property ))
			this.eipChain.setVersion( this.oldValue );
	}
}
