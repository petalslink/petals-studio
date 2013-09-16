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
 
package com.ebmwebsourcing.petals.services.bpel.designer.validation;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.bpel.validator.helpers.DOMNodeAdapter;
import org.eclipse.bpel.validator.model.ARule;
import org.eclipse.bpel.validator.model.Validator;
import org.eclipse.core.runtime.IStatus;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ebmwebsourcing.easybpel.model.bpel.tools.validator.BPELValidator;
import com.ebmwebsourcing.easybpel.model.bpel.tools.validator.ValidatorResult;
import com.ebmwebsourcing.easybpel.model.bpel.tools.validator.XPathError;
import com.ebmwebsourcing.easybpel.model.bpel.tools.validator.XPathInfo;
import com.ebmwebsourcing.easybpel.model.bpel.tools.validator.XPathWarning;
import com.ebmwebsourcing.petals.services.bpel.PetalsBpelPlugin;

/**
 * @author Mickael Istria - EBM WebSourcing
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EasyBPELValidator extends Validator {

	// Singleton

	private static Map<String, Long> lastValidations = new HashMap<String, Long>();
	private static Map<String, ValidatorResult> results = new HashMap<String, ValidatorResult>();

	// logic

	@Override
	public void start() {
		super.start();
		Document doc = getCurrentDocument();
		String docURI = doc.getBaseURI();
		if (docURI == null) {
			return;
		}

		try {
			File docFile = new File(new URI(docURI).getPath());
			if (!lastValidations.containsKey(docURI) ||
				lastValidations.get(docURI) < docFile.lastModified()) {
				validate(doc);
				lastValidations.put(docURI, System.currentTimeMillis());
			}

		} catch (Exception ex) {
			PetalsBpelPlugin.log( ex, IStatus.ERROR, "Could not find URI for BPEL Document" );
		}

	}

	private void validate(Document doc) {
		try {
			results.put(doc.getBaseURI(), new BPELValidator().validate(doc));
		} catch (Exception ex) {
			PetalsBpelPlugin.log( ex, IStatus.ERROR, "Could not instantiate BPELValidator" );
		}
	}

	private Document getCurrentDocument() {
		if (this.mNode.rootNode() instanceof DOMNodeAdapter) {
			Object item = ((DOMNodeAdapter)this.mNode.rootNode()).nodeValue();
			if (item instanceof Element) {
				return ((Element)item).getOwnerDocument();
			}
		}
		return null;
	}

	@ARule (
		errors="EASY_BPEL_ERRORS",
		infos="EASY_BPEL_INFOS",
		warnings="EASY_BPEL_WARNINGS"
	)
	public void rule__easyBPEL() {
		String docURI = getCurrentDocument().getBaseURI();
		if( docURI == null )
			return;

		for( XPathError error : results.get( docURI ).errors ) {
			if( error.getElement().getTag().equals( this.mNode.nodeName()))
				createError( this.mNode ).fill( error.getError().getMessage());
		}

		for( XPathWarning warning : results.get( docURI ).warnings ) {
			if( warning.getElement().getTag().equals(this.mNode.nodeName()))
				createWarning( this.mNode ).fill( warning.getWarning());
		}

		for( XPathInfo info : results.get( docURI ).infos ) {
			if( info.getElement().getTag().equals( this.mNode.nodeName()))
				createInfo( this.mNode ).fill( info.getInfo());
		}
	}
}
