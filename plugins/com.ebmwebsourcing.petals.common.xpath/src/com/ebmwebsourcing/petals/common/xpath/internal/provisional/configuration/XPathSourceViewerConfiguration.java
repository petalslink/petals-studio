/****************************************************************************
 * 
 * Copyright (c) 2010-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.xpath.internal.provisional.configuration;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

import com.ebmwebsourcing.petals.common.xpath.internal.configuration.XPathSourceDoubleClickStrategy;
import com.ebmwebsourcing.petals.common.xpath.internal.configuration.XPathSourceScanner;

/**
 * Forked from the Eclipse BPEL Designer.
 */
public class XPathSourceViewerConfiguration extends SourceViewerConfiguration {

	private XPathSourceDoubleClickStrategy doubleClickStrategy;
	private final XPathSourceScanner scanner;
	private final ColorManager colorManager;


	/**
	 * Constructor.
	 * @param manager a color manager
	 */
	public XPathSourceViewerConfiguration( ColorManager manager ) {
		this.colorManager = manager;
		this.scanner = new XPathSourceScanner( this.colorManager );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration
	 * #getConfiguredContentTypes(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] { IDocument.DEFAULT_CONTENT_TYPE };
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration
	 * #getDoubleClickStrategy(org.eclipse.jface.text.source.ISourceViewer, java.lang.String)
	 */
	@Override
	public ITextDoubleClickStrategy getDoubleClickStrategy( ISourceViewer sourceViewer, String contentType) {

		if( this.doubleClickStrategy == null )
			this.doubleClickStrategy = new XPathSourceDoubleClickStrategy();

		return this.doubleClickStrategy;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration
	 * #getPresentationReconciler(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public IPresentationReconciler getPresentationReconciler( ISourceViewer sourceViewer ) {

		PresentationReconciler reconciler = new PresentationReconciler();
		DefaultDamagerRepairer dr = new DefaultDamagerRepairer( this.scanner );
		reconciler.setDamager( dr, IDocument.DEFAULT_CONTENT_TYPE );
		reconciler.setRepairer( dr, IDocument.DEFAULT_CONTENT_TYPE );

		return reconciler;
	}
}
