package org.eclipse.bpel.ui.editparts;

import org.eclipse.bpel.common.ui.tray.TrayCategoryEditPart;
import org.eclipse.bpel.model.Scope;
import org.eclipse.bpel.ui.BPELEditor;
import org.eclipse.bpel.ui.editparts.policies.TrayContainerEditPolicy;
import org.eclipse.bpel.ui.util.ModelHelper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.viewers.StructuredSelection;

public abstract class BPELTrayCategoryEditPart extends TrayCategoryEditPart{

	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		// handles creations
		installEditPolicy(EditPolicy.CONTAINER_ROLE, new TrayContainerEditPolicy());
	}

	@Override
	protected AccessibleEditPart createAccessible() {
		return new BPELTrayAccessibleEditPart(this);
	}

	/**
	 * WARNING: only call this method if you know what you're doing!
	 * 
	 * HACK: The following hack avoids the Process from being selected when a variable,
	 * correlationSet or partnerLink is deleted. If the Process is selected, this edit part	 *
	 * has its parent set to null and when its time for the variable, correlationSet or partnerLink
	 * being deleted to "unregister visuals" it will try to get the viewer and that will cause a
	 * NPR because the parent is null.
	 */
	protected void selectAnotherEntry() {
		int size = getModelChildren().size();
		if (size > 0) {
			selectEditPart(getModelChildren().get(0));
		} else {
			// if we are executing this method we are dealing with scoped variables
			Scope scope = (Scope)((EObject)getModel()).eContainer();
			BPELEditor editor = ModelHelper.getBPELEditor(scope);
			EditPart editPart = (EditPart)editor.getGraphicalViewer().getEditPartRegistry().get(scope);
			if (editPart != null) {
				getViewer().setSelection(new StructuredSelection(editPart));
			}
		}
	}
}
