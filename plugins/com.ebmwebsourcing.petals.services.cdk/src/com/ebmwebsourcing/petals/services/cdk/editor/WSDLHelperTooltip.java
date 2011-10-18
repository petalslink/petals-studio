package com.ebmwebsourcing.petals.services.cdk.editor;

import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;

import com.ebmwebsourcing.petals.services.PetalsColors;

public class WSDLHelperTooltip extends ToolTip {

	public WSDLHelperTooltip(Control control) {
		super(control, NO_RECREATE, false);
		
	}

	@Override
	protected Composite createToolTipContentArea(Event event, Composite parent) {
		Composite res = new Composite(parent, SWT.DEFAULT);
		res.setBackground(PetalsColors.getLightPurple());
		
		return res;
	}

}
