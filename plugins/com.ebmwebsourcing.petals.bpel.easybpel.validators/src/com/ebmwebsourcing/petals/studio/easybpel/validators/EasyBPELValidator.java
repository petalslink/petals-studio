package com.ebmwebsourcing.petals.studio.easybpel.validators;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.bpel.validator.helpers.DOMNodeAdapter;
import org.eclipse.bpel.validator.model.ARule;
import org.eclipse.bpel.validator.model.Validator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ebmwebsourcing.easybpel.model.bpel.tools.validator.BPELValidator;
import com.ebmwebsourcing.easybpel.model.bpel.tools.validator.ValidatorResult;
import com.ebmwebsourcing.easybpel.model.bpel.tools.validator.XPathError;
import com.ebmwebsourcing.easybpel.model.bpel.tools.validator.XPathInfo;
import com.ebmwebsourcing.easybpel.model.bpel.tools.validator.XPathWarning;

public class EasyBPELValidator extends Validator {
	
	// Singleton
	
	private static Map<String, Long> lastValidations = new HashMap<String, Long>();
	private static Map<String, ValidatorResult> results = new HashMap<String, ValidatorResult>();
	
	// logic
	

	
	public EasyBPELValidator() {
	}
	
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
			EasyBPELValidatorPlugin.getDefault().getLog().log(new Status(
					IStatus.ERROR,
					EasyBPELValidatorPlugin.PLUGIN_ID,
					"Could not find URI for BPEL Document",
					ex));
		}
		
	}

	private void validate(Document doc) {
		try {
			results.put(doc.getBaseURI(), new BPELValidator().validate(doc));
		} catch (Exception ex) {
			EasyBPELValidatorPlugin.getDefault().getLog().log(new Status(
					IStatus.ERROR,
					EasyBPELValidatorPlugin.PLUGIN_ID,
					"Could not instantiate BPELValidator",
					ex));
		}
	}

	private Document getCurrentDocument() {
		if (mNode.rootNode() instanceof DOMNodeAdapter) {
			Object item = ((DOMNodeAdapter)mNode.rootNode()).nodeValue();
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
		if (docURI == null) {
			return;
		}
		for (XPathError error : results.get(docURI).errors) {
			if (error.getElement().getTag().equals(mNode.nodeName())) {
				createError(mNode).fill(error.getError().getMessage());
			}
		}
		for (XPathWarning warning : results.get(docURI).warnings) {
			if (warning.getElement().getTag().equals(mNode.nodeName())) {
				createError(mNode).fill(warning.getWarning());
			}
		}
		for (XPathInfo info : results.get(docURI).infos) {
			if (info.getElement().getTag().equals(mNode.nodeName())) {
				createError(mNode).fill(info.getInfo());
			}
		}
	}
}
