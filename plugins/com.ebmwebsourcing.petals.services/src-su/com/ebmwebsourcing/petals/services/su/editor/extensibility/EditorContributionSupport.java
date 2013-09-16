/******************************************************************************
 * Copyright (c) 2011-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.su.editor.extensibility;

import com.sun.java.xml.ns.jbi.AbstractExtensibleElement;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Micka�l Istria - EBM WebSourcing
 */
public abstract class EditorContributionSupport {

	protected abstract JbiEditorDetailsContribution getProvidesContribution();
	protected abstract JbiEditorDetailsContribution getConsumesContribution();


	/**
	 * @param initialElement
	 * @return
	 */
	public JbiEditorDetailsContribution createJbiEditorContribution(AbstractExtensibleElement initialElement) {

		if( initialElement instanceof Provides )
			return getProvidesContribution();
		else if( initialElement instanceof Consumes )
			return getConsumesContribution();
		else
			return null;
	}
}
