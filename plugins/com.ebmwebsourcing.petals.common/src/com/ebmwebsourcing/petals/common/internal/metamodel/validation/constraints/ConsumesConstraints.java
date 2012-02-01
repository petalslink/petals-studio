/****************************************************************************
 * 
 * Copyright (c) 2008-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.metamodel.validation.constraints;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import com.ebmwebsourcing.petals.common.internal.Messages;
import com.sun.java.xml.ns.jbi.Consumes;

/**
 * Checks that an instance of {@link Consumes} is correct.
 * <p>The checked constraints are:</p>
 * <ul>
 * 	<li>The end-point name may be not null only if the service-name is defined.</li>
 * 	<!-- li>The link-type can only be defined when the <i>service end-point</i> is not null.</li -->
 * </ul>
 * 
 * <p>
 * Note: when a string value is empty or made of white spaces, EMF considers this value as null.
 * </p>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ConsumesConstraints extends AbstractModelConstraint {

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

			if( objectToCheck instanceof Consumes ) {
				String errorMsg = check((Consumes) objectToCheck);
				if( errorMsg != null )
					return ctx.createFailureStatus( new Object[] { errorMsg });
			}
		}
		return ctx.createSuccessStatus();
	}


	/**
	 * Checks whether the attributes of a {@link Consumes} object are correct.
	 * @param consumes the consumes to check
	 * @return null if everything is fine, an error message otherwise
	 */
	private String check( Consumes consumes ) {

		String endpointName = consumes.getEndpointName();
		String serviceName =
			consumes.getServiceName() == null ? null : consumes.getServiceName().toString();

		if( endpointName != null && serviceName == null )
			return Messages.ConsumesConstraints_0;

		//		if( endpointName == null
		//				&& consumes.isSetLinkType()
		//				&& consumes.getLinkType().toString() != null )
		//			return "The link-type can only be provided when the endpoint-name is defined.";

		return null;
	}
}
