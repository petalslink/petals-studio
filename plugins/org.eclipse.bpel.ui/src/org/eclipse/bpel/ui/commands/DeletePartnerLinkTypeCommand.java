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

import org.eclipse.bpel.model.partnerlinktype.PartnerLinkType;
import org.eclipse.bpel.ui.IBPELUIConstants;


/**
 * Deletes a PartnerLinkType from its enclosing WSDL file.
 */
public class DeletePartnerLinkTypeCommand extends DeleteWSDLExtensibilityElementCommand {

	// TODO: wrong label?
	@Override
	public String getDefaultLabel() { return IBPELUIConstants.CMD_DELETE_ROLE; }

	public DeletePartnerLinkTypeCommand(PartnerLinkType plt) {
		super(plt);
	}
}
