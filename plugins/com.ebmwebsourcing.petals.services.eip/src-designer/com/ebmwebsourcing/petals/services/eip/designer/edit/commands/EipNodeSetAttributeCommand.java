/****************************************************************************
 * 
 * Copyright (c) 2011, EBM WebSourcing
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

import com.ebmwebsourcing.petals.services.eip.designer.model.AbstractNode;
import com.ebmwebsourcing.petals.services.eip.designer.model.EIPtype;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipNode;
import com.ebmwebsourcing.petals.services.eip.designer.model.EipProperty;

/**
 * A command to update an attribute of an EIP node.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipNodeSetAttributeCommand extends Command {

	private final String eipProperty;
	private Object oldValue, newValue;
	private EipNode eipNode;



	/**
	 * Constructor.
	 * @param eipProperty
	 */
	public EipNodeSetAttributeCommand( String eipProperty ) {
		this.eipProperty = eipProperty;
	}


	/**
	 * @param newValue the newValue to set
	 */
	public void setNewValue( Object newValue ) {
		this.newValue = newValue;
	}


	/**
	 * @param eipNode the eipNode to set
	 */
	public void setEipNode( EipNode eipNode ) {
		this.eipNode = eipNode;

		if( AbstractNode.PROPERTY_ENDPOINT_NAME.equals( this.eipProperty ))
			this.oldValue = eipNode.getEndpointName();
		else if( AbstractNode.PROPERTY_INTERFACE_NAME.equals( this.eipProperty ))
			this.oldValue = eipNode.getInterfaceName();
		else if( AbstractNode.PROPERTY_INTERFACE_NAMESPACE.equals( this.eipProperty ))
			this.oldValue = eipNode.getInterfaceNamespace();
		else if( AbstractNode.PROPERTY_SERVICE_NAME.equals( this.eipProperty ))
			this.oldValue = eipNode.getServiceName();
		else if( AbstractNode.PROPERTY_SERVICE_NAMESPACE.equals( this.eipProperty ))
			this.oldValue = eipNode.getServiceNamespace();
		else if( EipNode.PROPERTY_WSDL_URI.equals( this.eipProperty ))
			this.oldValue = eipNode.getWsdlUri();
		else if( EipNode.PROPERTY_EIP_TYPE.equals( this.eipProperty ))
			this.oldValue = eipNode.getEipType();
		else {
			EipProperty property = EipProperty.valueOf( this.eipProperty );
			if( property != null )
				this.oldValue = eipNode.getProperties().get( property );
			else
				this.oldValue = null;
		}
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

		if( AbstractNode.PROPERTY_ENDPOINT_NAME.equals( this.eipProperty ))
			this.eipNode.setEndpointName((String) this.newValue );
		else if( AbstractNode.PROPERTY_INTERFACE_NAME.equals( this.eipProperty ))
			this.eipNode.setInterfaceName((String) this.newValue );
		else if( AbstractNode.PROPERTY_INTERFACE_NAMESPACE.equals( this.eipProperty ))
			this.eipNode.setInterfaceNamespace((String) this.newValue );
		else if( AbstractNode.PROPERTY_SERVICE_NAME.equals( this.eipProperty ))
			this.eipNode.setServiceName((String) this.newValue );
		else if( AbstractNode.PROPERTY_SERVICE_NAMESPACE.equals( this.eipProperty ))
			this.eipNode.setServiceNamespace((String) this.newValue );
		else if( EipNode.PROPERTY_WSDL_URI.equals( this.eipProperty ))
			this.eipNode.setWsdlUri((String) this.newValue );
		else if( EipNode.PROPERTY_EIP_TYPE.equals( this.eipProperty ))
			this.eipNode.setEipType((EIPtype) this.newValue );
		else {
			EipProperty property = EipProperty.valueOf( this.eipProperty );
			if( property != null )
				this.eipNode.setEipProperty( property, (String) this.newValue );
		}
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {

		if( AbstractNode.PROPERTY_ENDPOINT_NAME.equals( this.eipProperty ))
			this.eipNode.setEndpointName((String) this.oldValue );
		else if( AbstractNode.PROPERTY_INTERFACE_NAME.equals( this.eipProperty ))
			this.eipNode.setInterfaceName((String) this.oldValue );
		else if( AbstractNode.PROPERTY_INTERFACE_NAMESPACE.equals( this.eipProperty ))
			this.eipNode.setInterfaceNamespace((String) this.oldValue );
		else if( AbstractNode.PROPERTY_SERVICE_NAME.equals( this.eipProperty ))
			this.eipNode.setServiceName((String) this.oldValue );
		else if( AbstractNode.PROPERTY_SERVICE_NAMESPACE.equals( this.eipProperty ))
			this.eipNode.setServiceNamespace((String) this.oldValue );
		else if( EipNode.PROPERTY_WSDL_URI.equals( this.eipProperty ))
			this.eipNode.setWsdlUri((String) this.oldValue );
		else if( EipNode.PROPERTY_EIP_TYPE.equals( this.eipProperty ))
			this.eipNode.setEipType((EIPtype) this.oldValue );
		else {
			EipProperty property = EipProperty.valueOf( this.eipProperty );
			if( property != null )
				this.eipNode.setEipProperty( property, (String) this.oldValue );
		}
	}
}
