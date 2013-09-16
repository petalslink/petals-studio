/******************************************************************************
 * Copyright (c) 2008-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.common.internal.metamodel.validation.constraints;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import com.ebmwebsourcing.petals.common.internal.Messages;
import com.sun.java.xml.ns.jbi.Consumer;

/**
 * Checks that an instance of {@link Consumer} is correct.
 * <p>The checked constraints are:</p>
 * <ul>
 * 	<li>If the interface-name is not null, then the service and end-point name must be null.</li>
 * 	<li>If the interface name is null, then the service and end-point names cannot be null.</li>
 * </ul>
 * 
 * <p>
 * Note: when a string value is empty or made of white spaces, EMF considers this value as null.
 * </p>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ConsumerConstraints extends AbstractModelConstraint {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint
	 * #validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate( IValidationContext ctx ) {

		EObject objectToCheck = ctx.getTarget();
		EMFEventType typeEvenement = ctx.getEventType();
		if( typeEvenement == EMFEventType.NULL ) {

			if( objectToCheck instanceof Consumer ) {
				String errorMsg = check((Consumer) objectToCheck);
				if( errorMsg != null )
					return ctx.createFailureStatus( new Object[] { errorMsg });
			}
		}
		return ctx.createSuccessStatus();
	}


	/**
	 * Checks whether the attributes of a {@link Consumer} object are correct.
	 * @param consumer the consumer to check
	 * @return null if everything is fine, an error message otherwise
	 */
	private String check( Consumer consumer ) {

		String endpointName = consumer.getEndpointName();
		String serviceName =
			consumer.getServiceName() == null ? null : consumer.getServiceName().toString();

		if( consumer.getInterfaceName() != null ) {
			if( endpointName != null
						|| serviceName == null )
				return Messages.ConsumerConstraints_0;
		}
		else {
			if( serviceName == null
						|| endpointName == null )
				return Messages.ConsumerConstraints_4;
		}

		return null;
	}
}
