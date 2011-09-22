/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpel.ui.editors.xpath;

import org.eclipse.bpel.ui.contentassist.ExpressionContentAssistProcessor;
import org.eclipse.bpel.ui.contentassist.ExpressionSourceViewerConfiguration;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;

/**
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date Oct 25, 2006
 *
 */
public class XPathSourceViewerConfiguration extends ExpressionSourceViewerConfiguration {

	private XPathSourceDoubleClickStrategy doubleClickStrategy;
	private XPathSourceScanner scanner;
	private final ColorManager colorManager;

	private ContentAssistant bpelAssistant = null;

	/**
	 * Create a brand new shining source viewer configuration.
	 * 
	 * @param manager
	 */

	public XPathSourceViewerConfiguration(ColorManager manager) {
		this.colorManager = manager;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.bpel.ui.contentassist.ExpressionSourceViewerConfiguration
	 * #getContentAssistant(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public IContentAssistant getContentAssistant(ISourceViewer viewer) {
		if (this.bpelAssistant == null) {
			this.bpelAssistant = new ContentAssistant();
			ExpressionContentAssistProcessor exprProcessor = new ExpressionContentAssistProcessor();
			// install custom content assist processor
			this.bpelAssistant.setContentAssistProcessor(exprProcessor,
						IDocument.DEFAULT_CONTENT_TYPE);
			this.bpelAssistant.setInformationControlCreator(this.getInformationControlCreator(viewer));
			this.bpelAssistant.enableAutoActivation(true);

			// support for multiple proposal suggestions
			this.bpelAssistant.setRepeatedInvocationMode(true);
			this.bpelAssistant.setStatusLineVisible(true);
			this.bpelAssistant.addCompletionListener(exprProcessor);
		}
		return this.bpelAssistant;
	}


	/**
	 * Get the configured content types.
	 * 
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getConfiguredContentTypes(org.eclipse.jface.text.source.ISourceViewer)
	 */

	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
					IDocument.DEFAULT_CONTENT_TYPE
		};
	}


	/**
	 * Return the double click strategy.
	 * 
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getDoubleClickStrategy(org.eclipse.jface.text.source.ISourceViewer, java.lang.String)
	 */
	@Override
	public ITextDoubleClickStrategy getDoubleClickStrategy (
				ISourceViewer sourceViewer,
				String contentType) {

		if (this.doubleClickStrategy == null) {
			this.doubleClickStrategy = new XPathSourceDoubleClickStrategy();
		}
		return this.doubleClickStrategy;
	}


	protected XPathSourceScanner getXPathSourceScanner() {

		if (this.scanner == null) {
			this.scanner = new XPathSourceScanner(this.colorManager);
		}
		return this.scanner;
	}


	//	protected BPELSourceTagScanner getBPELSourceTagScanner() {
	//		if (tagScanner == null) {
	//			tagScanner = new BPELSourceTagScanner(colorManager);
	//			tagScanner.setDefaultReturnToken(
	//				new Token(
	//					new TextAttribute(
	//						colorManager.getColor(IBPELSourceColorConstants.TAG))));
	//		}
	//		return tagScanner;
	//	}

	/**
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getPresentationReconciler(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {

		PresentationReconciler reconciler = new PresentationReconciler();

		//		DefaultDamagerRepairer dr =
		//			new DefaultDamagerRepairer(getBPELSourceTagScanner());
		//		reconciler.setDamager(dr, BPELSourcePartitionScanner.BPELSource_TAG);
		//		reconciler.setRepairer(dr, BPELSourcePartitionScanner.BPELSource_TAG);
		//

		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getXPathSourceScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		//		NonRuleBasedDamagerRepairer ndr =
		//			new NonRuleBasedDamagerRepairer(
		//				new TextAttribute(
		//					colorManager.getColor(IBPELSourceColorConstants.COMMENT)
		//                    ));
		//
		//		reconciler.setDamager(ndr, BPELSourcePartitionScanner.BPELSource_COMMENT);
		//		reconciler.setRepairer(ndr, BPELSourcePartitionScanner.BPELSource_COMMENT);

		return reconciler;
	}
}
