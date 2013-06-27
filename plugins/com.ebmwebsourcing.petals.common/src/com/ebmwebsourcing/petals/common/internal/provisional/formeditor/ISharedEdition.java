/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.formeditor;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.sun.java.xml.ns.jbi.Jbi;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public interface ISharedEdition {

	FormToolkit getFormToolkit();
	IFile getEditedFile();
	Jbi getJbiModel();
	AbstractJbiEditorPersonality getPersonnality();
}
