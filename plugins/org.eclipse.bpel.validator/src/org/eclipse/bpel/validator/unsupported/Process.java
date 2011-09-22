/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpel.validator.unsupported;

import org.eclipse.bpel.validator.model.ARule;
import org.eclipse.bpel.validator.model.IProblem;
import org.eclipse.bpel.validator.rules.CValidator;



/**
 * <vprop:property name="taxpayerNumber" type="txtyp:SSN" />
 * 
 *  
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 *
 */


@SuppressWarnings({"nls","boxing"})

public class Process extends CValidator {
		
	String ncName ;

	protected String fTypeName;
	
		
	@Override
	protected void start () {
		super.start();		
		ncName = mNode.getAttribute( AT_NAME );		
	}
	
	/**
	 * Rule to check the name of the link. 
	 */
	@ARule(
		sa = 0,
		desc = "Check the NCName of the property",
		author = "michal.chmielewski@oracle.com",
		date = "02/15/2007"
	)
	
	public void rule_CheckName_1 () {		
		// Must be a valid NCName ...		
		if (checkNCName(mNode, ncName, AT_NAME ) == false) {
			disableRules();
		}			
	}
	
	/**
	 * Report that the process cannot be validated.
	 * 
	 */
	
	public void rule_ReportUnsupportedProcessVariant_5 () {
		
		IProblem problem = createError();
		problem.fill("BPELC__UNSUPPORTED_VALIDATION", 
				toString(mNode.nodeName()), ncName, mNode.nodeName().getNamespaceURI() );
		
	}

	
	
	/** 
	 * End of public rule methods.
	 * 
	 * Other methods are support methods for this class to perform its
	 * validation function.
	 * 
	 */

}
