/****************************************************************************
 * 
 * Copyright (c) 2010-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.sca.tabbedproperties;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.impl.NodeImpl;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.soa.sca.sca1_0.diagram.extension.edit.parts.ElementEditPart;
import org.eclipse.soa.sca.sca1_0.runtime.frascati.model.frascati.JBIBinding;

/**
 * A filter that only accepts JBI bindings.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SelectionFilter implements IFilter {

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IFilter
	 * #select(java.lang.Object)
	 */
	public boolean select( Object toTest ) {

		boolean result = false;
		if( toTest instanceof ElementEditPart ) {
			Object o = ((ElementEditPart) toTest).getModel();
			if( o instanceof NodeImpl ) {
				EObject eo =((NodeImpl) o).basicGetElement();
				if( eo instanceof JBIBinding )
					result = true;
			}
		}

		return result;
	}
}
