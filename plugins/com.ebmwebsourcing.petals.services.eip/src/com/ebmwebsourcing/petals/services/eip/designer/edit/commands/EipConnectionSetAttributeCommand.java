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

import com.ebmwebsourcing.petals.services.eip.designer.model.EipConnection;

/**
 * A command to update an attribute of an end-point.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipConnectionSetAttributeCommand extends Command {

	private final String connectionProperty;
	private Object oldValue, newValue;
	private EipConnection conn;



	/**
	 * Constructor.
	 * @param connectionProperty
	 */
	public EipConnectionSetAttributeCommand( String connectionProperty ) {
		this.connectionProperty = connectionProperty;
	}


	/**
	 * @param newValue the newValue to set
	 */
	public void setNewValue( Object newValue ) {
		this.newValue = newValue;
	}


	/**
	 * @param conn the connection to set
	 */
	public void setEipConnection( EipConnection conn ) {
		this.conn = conn;

		if( EipConnection.PROPERTY_CONSUME_BY_EDPT.equals( this.connectionProperty ))
			this.oldValue = conn.isConsumeEdpt();
		else if( EipConnection.PROPERTY_CONSUME_BY_SRV.equals( this.connectionProperty ))
			this.oldValue = conn.isConsumeSrv();
		else if( EipConnection.PROPERTY_CONSUME_BY_ITF.equals( this.connectionProperty ))
			this.oldValue = conn.isConsumeItf();
		else if( EipConnection.PROPERTY_CONSUMED_MEP.equals( this.connectionProperty ))
			this.oldValue = conn.getConsumeMep();
		else if( EipConnection.PROPERTY_CONSUMED_OPERATION.equals( this.connectionProperty ))
			this.oldValue = conn.getConsumeOperation();
		else if( EipConnection.PROPERTY_CONDITION_NAME.equals( this.connectionProperty ))
			this.oldValue = conn.getConditionName();
		else if( EipConnection.PROPERTY_CONDITION_EXPRESSION.equals( this.connectionProperty ))
			this.oldValue = conn.getConditionExpression();
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

		if( EipConnection.PROPERTY_CONSUME_BY_EDPT.equals( this.connectionProperty ))
			this.conn.setConsumeEdpt((Boolean) this.newValue);
		else if( EipConnection.PROPERTY_CONSUME_BY_SRV.equals( this.connectionProperty ))
			this.conn.setConsumeSrv((Boolean) this.newValue);
		else if( EipConnection.PROPERTY_CONSUME_BY_ITF.equals( this.connectionProperty ))
			this.conn.setConsumeItf((Boolean) this.newValue);
		else if( EipConnection.PROPERTY_CONSUMED_MEP.equals( this.connectionProperty ))
			this.conn.setConsumeMep((String) this.newValue);
		else if( EipConnection.PROPERTY_CONSUMED_OPERATION.equals( this.connectionProperty ))
			this.conn.setConsumeOperation((String) this.newValue);
		else if( EipConnection.PROPERTY_CONDITION_NAME.equals( this.connectionProperty ))
			this.conn.setConditionName((String) this.newValue);
		else if( EipConnection.PROPERTY_CONDITION_EXPRESSION.equals( this.connectionProperty ))
			this.conn.setConditionExpression((String) this.newValue);
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {

		if( EipConnection.PROPERTY_CONSUME_BY_EDPT.equals( this.connectionProperty ))
			this.conn.setConsumeEdpt((Boolean) this.oldValue);
		else if( EipConnection.PROPERTY_CONSUME_BY_SRV.equals( this.connectionProperty ))
			this.conn.setConsumeSrv((Boolean) this.oldValue);
		else if( EipConnection.PROPERTY_CONSUME_BY_ITF.equals( this.connectionProperty ))
			this.conn.setConsumeItf((Boolean) this.oldValue);
		else if( EipConnection.PROPERTY_CONSUMED_MEP.equals( this.connectionProperty ))
			this.conn.setConsumeMep((String) this.oldValue);
		else if( EipConnection.PROPERTY_CONSUMED_OPERATION.equals( this.connectionProperty ))
			this.conn.setConsumeOperation((String) this.oldValue);
		else if( EipConnection.PROPERTY_CONDITION_NAME.equals( this.connectionProperty ))
			this.conn.setConditionName((String) this.oldValue);
		else if( EipConnection.PROPERTY_CONDITION_EXPRESSION.equals( this.connectionProperty ))
			this.conn.setConditionExpression((String) this.oldValue);
	}
}
