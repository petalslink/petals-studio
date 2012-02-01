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
