
package org.eclipse.bpel.ui.details.providers;

import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.ui.details.tree.ITreeNode;
import org.eclipse.bpel.ui.util.ModelHelper;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.wst.wsdl.Message;
import org.eclipse.wst.wsdl.Part;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDTypeDefinition;


/**
 * Filter which excludes from a set of Variables those variables whose
 * types do not match the appropriate settings in the filter.
 */

public class VariableFilter extends ViewerFilter implements IFilter {

	Message fMessage;
	XSDElementDeclaration fElementDeclaration;
	XSDTypeDefinition fTypeDefinition;


	/**
	 * Clear the filter
	 */

	public void clear () {
		this.fMessage = null;
		this.fElementDeclaration = null;
		this.fTypeDefinition = null;
	}

	/**
	 * Set type of filter to be the message msg
	 * @param msg the message
	 */
	public void setType( Message msg ) {
		this.fMessage = msg;
	}

	/**
	 * Set the type of filter to be this element declaration.
	 * @param decl
	 */
	public void setType ( XSDElementDeclaration decl ) {
		this.fElementDeclaration = decl;
	}

	/**
	 * Set this type of filter to be type definition.
	 * @param typeDef
	 */
	public void setType ( XSDTypeDefinition typeDef) {
		this.fTypeDefinition = typeDef;
	}

	/*
	 * @see org.eclipse.jface.viewers.ViewerFilter
	 * #select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean select (Viewer viewer, Object parentElement, Object element) {

		if (element instanceof ITreeNode)
			return select(((ITreeNode)element).getModelObject());

		return select( element );
	}

	/*
	 * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
	 */
	public boolean select (Object toTest) {

		// VZ: reorganized the code and explained the filtering.
		// Plus, I added fixes to comparison, as equals was always returning false.
		// Types, element and message equality is now based on QNames.

		// By default, we reject everything.
		boolean accepted = false;
		if( toTest != null ) {

			// For variables...
			if ( toTest instanceof Variable ) {

				// No filtering criteria: we accept the element.
				Variable v = (Variable) toTest;
				if (this.fMessage == null
							&& this.fElementDeclaration == null
							&& this.fTypeDefinition == null)
					accepted = true;

				else {
					// Invoke message = variable's message type: we accept.
					if (this.fMessage != null) {
						if( ModelHelper.areEqual( this.fMessage, v.getMessageType()))
							accepted = true;

						else if( this.fMessage.getEParts().size() == 1) {
							Part part = (Part) this.fMessage.getEParts().get(0);
							XSDElementDeclaration decl = part.getElementDeclaration();
							if( decl != null
										&& ModelHelper.areEqual( decl, v.getXSDElement() ))
								accepted = true;
						}
					}

					// Invoke's element = variable's element: we accept.
					if( this.fElementDeclaration != null
								&& ModelHelper.areEqual( this.fElementDeclaration, v.getXSDElement()))
						accepted = true;

					// Invole's type = variable's type: we accept.
					if( this.fTypeDefinition != null
								&& ModelHelper.areEqual( this.fTypeDefinition, v.getType()))
						accepted = true;
				}
			}
		}

		return accepted;
	}
}
