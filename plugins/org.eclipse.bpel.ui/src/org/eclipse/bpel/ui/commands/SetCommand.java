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
package org.eclipse.bpel.ui.commands;

import org.eclipse.bpel.ui.Messages;
import org.eclipse.bpel.ui.commands.util.AutoUndoCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;


/**
 * Generic "model-setting" command.  Subclasses only need to implement get() and set()
 * in terms of the particular model property they set.
 */
public class SetCommand extends AutoUndoCommand {

	/** Target */
	protected EObject fTarget;

	/** The structural feature we are setting */
	protected EStructuralFeature fFeature = null;

	/** New and old value for the structural feature */
	protected Object  fNewValue, fOldValue;


	/**
	 * Brand new shiny SetStructuralFeatureCommand command.
	 * 
	 * Typically, you would override getDefaultLabel()
	 * 
	 * @param aTarget the target EMF object
	 * @param aValue the value to set
	 */

	public SetCommand (EObject aTarget, Object aValue )  {
		super(aTarget);
		this.fTarget = aTarget;
		this.fNewValue = aValue;
	}


	/**
	 * Brand new shiny SetStructuralFeatureCommand command. For example
	 * <pre>
	 *   new SetCommand ( copyBpelObject, newTo, BPELPackage.eINSTANCE.getCopy_To() );
	 * </pre>
	 * 
	 * Typically, you would override getDefaultLabel()
	 * 
	 * @param aTarget the target EMF object
	 * @param aValue the value to set
	 * @param aFeature the feature id
	 */

	public SetCommand (EObject aTarget, Object aValue, EStructuralFeature aFeature )  {
		this(aTarget,aValue);
		this.fNewValue = aValue;
		this.fFeature = aFeature;
	}

	/**
	 * Get the value of the structural feature from the current target.
	 * @return the value of the structural feature.
	 */

	public Object get() {
		return this.fTarget.eGet(this.fFeature);
	}

	/**
	 * Set the value of the structural feature.
	 * 
	 * @param o
	 */
	public void set (Object o) {
		this.fTarget.eSet(this.fFeature, o);
	}



	// TODO: THIS SHOULDN'T EXIST.  FIX.
	public void setNewValue (EObject newValue) {
		this.fNewValue = newValue;
	}

	/**
	 * Return the default command label.
	 * @return  the default label
	 */

	@Override
	public String getLabel() {
		if (this.fFeature == null) {
			return Messages.SetCommand_Change_1;
		}
		return NLS.bind(Messages.SetCommand_Change_2, this.fFeature.getName(), null );
	}


	protected boolean hasNoEffect()  {
		if (this.fOldValue == null) return (this.fNewValue == null);
		if (this.fNewValue == null) return false;
		return this.fNewValue.equals(this.fOldValue);
	}

	/**
	 * @see org.eclipse.bpel.ui.commands.util.AutoUndoCommand#canDoExecute()
	 */
	@Override
	public boolean canDoExecute() {
		return true;
	}

	/**
	 * @see org.eclipse.bpel.ui.commands.util.AutoUndoCommand#doExecute()
	 */
	@Override
	public void doExecute() {
		this.fOldValue = get();
		if( ! hasNoEffect())
			set(this.fNewValue);
	}
}
