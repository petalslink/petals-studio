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
package org.eclipse.bpel.ui.util;

import org.eclipse.bpel.ui.dialogs.PartnerLinkTypeSelectorDialog;
import org.eclipse.bpel.ui.dialogs.TypeSelectorDialog;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.wsdl.PortType;


public class BrowseUtil {

	public static PortType browseForPortType(ResourceSet resourceSet, Shell parent) {
		// TODO!
		return null;
	}

	public static Object browseForXSDTypeOrElement(EObject eObject, Shell parent) {
		TypeSelectorDialog dialog = new TypeSelectorDialog (parent,eObject);
		if (dialog.open() != Window.OK) {
			return null;
		}
		Object obj[] = dialog.getResult();
		if (obj != null && obj.length > 0) {
			return obj[0];
		}
		return null;

	}


	public static Object browseForPartnerLinkType (EObject eObject, Shell parent) {
		PartnerLinkTypeSelectorDialog dialog = new PartnerLinkTypeSelectorDialog (parent,eObject);
		if (dialog.open() != Window.OK) {
			return null;
		}
		Object obj[] = dialog.getResult();
		if (obj != null && obj.length > 0) {
			return obj[0];
		}
		return null;

	}

}
