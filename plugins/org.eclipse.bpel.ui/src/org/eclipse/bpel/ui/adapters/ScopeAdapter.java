/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpel.ui.adapters;

import org.eclipse.bpel.model.BPELPackage;
import org.eclipse.bpel.model.CompensationHandler;
import org.eclipse.bpel.model.EventHandler;
import org.eclipse.bpel.model.FaultHandler;
import org.eclipse.bpel.model.Scope;
import org.eclipse.bpel.model.TerminationHandler;
import org.eclipse.bpel.ui.adapters.delegates.ActivityContainer;
import org.eclipse.bpel.ui.adapters.delegates.MultiContainer;
import org.eclipse.bpel.ui.adapters.delegates.OptionalIndirectContainer;
import org.eclipse.bpel.ui.adapters.delegates.ReferenceContainer;
import org.eclipse.bpel.ui.editparts.OutlineTreeEditPart;
import org.eclipse.bpel.ui.editparts.ScopeEditPart;
import org.eclipse.gef.EditPart;


public class ScopeAdapter extends ContainerActivityAdapter implements IFaultHandlerHolder,
	ICompensationHandlerHolder, ITerminationHandlerHolder, IEventHandlerHolder
{

	/* IContainer delegate */
	
	@Override
	public IContainer createContainerDelegate() {
		MultiContainer omc = new MultiContainer();
		omc.add(new ActivityContainer(BPELPackage.eINSTANCE.getScope_Activity()));
// TODO: Support scoped partner links, correlation sets, variables and message exchanges
		omc.add(new OptionalIndirectContainer(BPELPackage.eINSTANCE.getScope_PartnerLinks(),			
			new ReferenceContainer(BPELPackage.eINSTANCE.getPartnerLinks_Children())));
		omc.add(new OptionalIndirectContainer(BPELPackage.eINSTANCE.getScope_CorrelationSets(),			
			new ReferenceContainer(BPELPackage.eINSTANCE.getCorrelationSets_Children())));
		omc.add(new OptionalIndirectContainer(BPELPackage.eINSTANCE.getScope_Variables(),			
			new ReferenceContainer(BPELPackage.eINSTANCE.getVariables_Children())));
		omc.add(new OptionalIndirectContainer(BPELPackage.eINSTANCE.getScope_MessageExchanges(),			
				new ReferenceContainer(BPELPackage.eINSTANCE.getMessageExchanges_Children())));
		
		omc.add(new ReferenceContainer(BPELPackage.eINSTANCE.getScope_FaultHandlers()));
		omc.add(new ReferenceContainer(BPELPackage.eINSTANCE.getScope_CompensationHandler()));
		omc.add(new ReferenceContainer(BPELPackage.eINSTANCE.getScope_TerminationHandler()));
		omc.add(new ReferenceContainer(BPELPackage.eINSTANCE.getScope_EventHandlers()));
		return omc;
	}

	/* IFaultHandlerHolder */

	public FaultHandler getFaultHandler(Object object) {
		return ((Scope)object).getFaultHandlers();
	}

	public void setFaultHandler(Object object, FaultHandler faultHandler) {
		((Scope)object).setFaultHandlers(faultHandler);
	}
	
	/* IEventHandlerHolder */

	public EventHandler getEventHandler(Object object) {
		return ((Scope)object).getEventHandlers();
	}

	public void setEventHandler(Object object, EventHandler eventHandler) {
		((Scope)object).setEventHandlers(eventHandler);
	}		
		
	/* ICompensationHandlerHolder */
	
	public CompensationHandler getCompensationHandler(Object object) {
		return ((Scope)object).getCompensationHandler();
	}

	public void setCompensationHandler(Object object, CompensationHandler compensationHandler) {
		((Scope)object).setCompensationHandler(compensationHandler);
	}

	/* ITerminationHandlerHolder */

	public TerminationHandler getTerminationHandler(Object object) {
		return ((Scope)object).getTerminationHandler();
	}

	public void setTerminationHandler(Object object, TerminationHandler terminationHandler) {
		((Scope)object).setTerminationHandler(terminationHandler);
	}

	/* EditPartFactory */
	
	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart result = new ScopeEditPart();
		result.setModel(model);
		return result;
	}

	/* IOutlineEditPartFactory */
	
	@Override
	public EditPart createOutlineEditPart(EditPart context, Object model) {
		EditPart result = new OutlineTreeEditPart();
		result.setModel(model);
		return result;
	}
}