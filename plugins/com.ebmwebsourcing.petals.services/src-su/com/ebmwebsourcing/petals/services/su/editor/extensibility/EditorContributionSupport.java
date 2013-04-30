/****************************************************************************
 *
 * Copyright (c) 2011-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

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
