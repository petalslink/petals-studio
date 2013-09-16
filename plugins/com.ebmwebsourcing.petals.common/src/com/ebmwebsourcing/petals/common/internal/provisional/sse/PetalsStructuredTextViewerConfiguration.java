/******************************************************************************
 * Copyright (c) 2010-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.common.internal.provisional.sse;

import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IAutoIndentStrategy;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.IUndoManager;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.hyperlink.IHyperlinkDetector;
import org.eclipse.jface.text.hyperlink.IHyperlinkPresenter;
import org.eclipse.jface.text.information.IInformationPresenter;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.quickassist.IQuickAssistAssistant;
import org.eclipse.jface.text.reconciler.IReconciler;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration;
import org.eclipse.wst.sse.ui.internal.provisional.style.LineStyleProvider;
import org.eclipse.wst.sse.ui.internal.provisional.style.ReconcilerHighlighter;
import org.eclipse.wst.xml.ui.StructuredTextViewerConfigurationXML;

/**
 * A source viewer configuration that delegates most of the job to a Structured Text Viewer configuration.
 * @author Vincent Zurczak - EBM WebSourcing
 */
@SuppressWarnings( "restriction" )
public class PetalsStructuredTextViewerConfiguration extends SourceViewerConfiguration {

	private final StructuredTextViewerConfiguration config;


	/**
	 * Constructor.
	 */
	public PetalsStructuredTextViewerConfiguration() {
		this.config = new StructuredTextViewerConfigurationXML();
	}


	/**
	 * @param obj
	 * @return
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {

		boolean equals = false;
		if( obj instanceof PetalsStructuredTextViewerConfiguration ) {
			if( this.config == null )
				equals = ((PetalsStructuredTextViewerConfiguration) obj).config == null;
			else
				equals = this.config.equals( ((PetalsStructuredTextViewerConfiguration) obj).config );
		}

		return equals;
	}


	/**
	 * @param sourceViewer
	 * @return
	 * @see org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration
	 * #getAnnotationHover(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public final IAnnotationHover getAnnotationHover( ISourceViewer sourceViewer ) {
		return this.config.getAnnotationHover( sourceViewer );
	}


	/**
	 * @param sourceViewer
	 * @param contentType
	 * @return
	 * @see org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration
	 * #getAutoEditStrategies(org.eclipse.jface.text.source.ISourceViewer, java.lang.String)
	 */
	@Override
	public IAutoEditStrategy[] getAutoEditStrategies( ISourceViewer sourceViewer, String contentType ) {
		return this.config.getAutoEditStrategies( sourceViewer, contentType );
	}


	/**
	 * @param sourceViewer
	 * @param contentType
	 * @return
	 * @deprecated
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration
	 * #getAutoIndentStrategy(org.eclipse.jface.text.source.ISourceViewer, java.lang.String)
	 */
	@Deprecated
	@Override
	public IAutoIndentStrategy getAutoIndentStrategy( ISourceViewer sourceViewer, String contentType ) {
		return this.config.getAutoIndentStrategy( sourceViewer, contentType );
	}


	/**
	 * @param sourceViewer
	 * @return
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration
	 * #getConfiguredContentTypes(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public String[] getConfiguredContentTypes( ISourceViewer sourceViewer ) {
		return this.config.getConfiguredContentTypes( sourceViewer );
	}


	/**
	 * @param sourceViewer
	 * @return
	 * @see org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration
	 * #getConfiguredDocumentPartitioning(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public final String getConfiguredDocumentPartitioning( ISourceViewer sourceViewer ) {
		return this.config.getConfiguredDocumentPartitioning( sourceViewer );
	}


	/**
	 * @param sourceViewer
	 * @param contentType
	 * @return
	 * @see org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration
	 * #getConfiguredTextHoverStateMasks(org.eclipse.jface.text.source.ISourceViewer, java.lang.String)
	 */
	@Override
	public int[] getConfiguredTextHoverStateMasks( ISourceViewer sourceViewer, String contentType ) {
		return this.config.getConfiguredTextHoverStateMasks( sourceViewer, contentType );
	}


	/**
	 * @param sourceViewer
	 * @return
	 * @see org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration
	 * #getContentAssistant(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public IContentAssistant getContentAssistant( ISourceViewer sourceViewer ) {
		return this.config.getContentAssistant( sourceViewer );
	}


	/**
	 * @param sourceViewer
	 * @return
	 * @see org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration
	 * #getContentFormatter(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public IContentFormatter getContentFormatter( ISourceViewer sourceViewer ) {
		return this.config.getContentFormatter( sourceViewer );
	}


	/**
	 * @param sourceViewer
	 * @param contentType
	 * @return
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration
	 * #getDefaultPrefixes(org.eclipse.jface.text.source.ISourceViewer, java.lang.String)
	 */
	@Override
	public String[] getDefaultPrefixes( ISourceViewer sourceViewer, String contentType ) {
		return this.config.getDefaultPrefixes( sourceViewer, contentType );
	}


	/**
	 * @param sourceViewer
	 * @param contentType
	 * @return
	 * @see org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration
	 * #getDoubleClickStrategy(org.eclipse.jface.text.source.ISourceViewer, java.lang.String)
	 */
	@Override
	public ITextDoubleClickStrategy getDoubleClickStrategy( ISourceViewer sourceViewer, String contentType ) {
		return this.config.getDoubleClickStrategy( sourceViewer, contentType );
	}


	/**
	 * @param sourceViewer
	 * @return
	 * @see org.eclipse.ui.editors.text.TextSourceViewerConfiguration
	 * #getHyperlinkDetectors(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public IHyperlinkDetector[] getHyperlinkDetectors( ISourceViewer sourceViewer ) {
		return this.config.getHyperlinkDetectors( sourceViewer );
	}


	/**
	 * @param sourceViewer
	 * @return
	 * @see org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration
	 * #getHyperlinkPresenter(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public final IHyperlinkPresenter getHyperlinkPresenter( ISourceViewer sourceViewer ) {
		return this.config.getHyperlinkPresenter( sourceViewer );
	}


	/**
	 * @param sourceViewer
	 * @return
	 * @see org.eclipse.ui.editors.text.TextSourceViewerConfiguration
	 * #getHyperlinkStateMask(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public int getHyperlinkStateMask( ISourceViewer sourceViewer ) {
		return this.config.getHyperlinkStateMask( sourceViewer );
	}


	/**
	 * @param sourceViewer
	 * @param contentType
	 * @return
	 * @see org.eclipse.ui.editors.text.TextSourceViewerConfiguration
	 * #getIndentPrefixes(org.eclipse.jface.text.source.ISourceViewer, java.lang.String)
	 */
	@Override
	public String[] getIndentPrefixes( ISourceViewer sourceViewer, String contentType ) {
		return this.config.getIndentPrefixes( sourceViewer, contentType );
	}


	/**
	 * @param sourceViewer
	 * @return
	 * @see org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration
	 * #getInformationControlCreator(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public final IInformationControlCreator getInformationControlCreator( ISourceViewer sourceViewer ) {
		return this.config.getInformationControlCreator( sourceViewer );
	}


	/**
	 * @param sourceViewer
	 * @return
	 * @see org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration
	 * #getInformationPresenter(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public final IInformationPresenter getInformationPresenter( ISourceViewer sourceViewer ) {
		return this.config.getInformationPresenter( sourceViewer );
	}


	/**
	 * @param sourceViewer
	 * @param partitionType
	 * @return
	 * @see org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration
	 * #getLineStyleProviders(org.eclipse.jface.text.source.ISourceViewer, java.lang.String)
	 */
	public LineStyleProvider[] getLineStyleProviders( ISourceViewer sourceViewer, String partitionType ) {
		return this.config.getLineStyleProviders( sourceViewer, partitionType );
	}


	/**
	 * @param sourceViewer
	 * @return
	 * @see org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration
	 * #getOverviewRulerAnnotationHover(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public IAnnotationHover getOverviewRulerAnnotationHover( ISourceViewer sourceViewer ) {
		return this.config.getOverviewRulerAnnotationHover( sourceViewer );
	}


	/**
	 * @param sourceViewer
	 * @return
	 * @see org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration
	 * #getPresentationReconciler(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public IPresentationReconciler getPresentationReconciler( ISourceViewer sourceViewer ) {
		return this.config.getPresentationReconciler( sourceViewer );
	}


	/**
	 * @param sourceViewer
	 * @return
	 * @see org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration
	 * #getQuickAssistAssistant(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public IQuickAssistAssistant getQuickAssistAssistant( ISourceViewer sourceViewer ) {
		return this.config.getQuickAssistAssistant( sourceViewer );
	}


	/**
	 * @param sourceViewer
	 * @return
	 * @see org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration
	 * #getReconciler(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public final IReconciler getReconciler( ISourceViewer sourceViewer ) {
		return this.config.getReconciler( sourceViewer );
	}


	/**
	 * @param sourceViewer
	 * @return
	 * @see org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration
	 * #getStatusLineLabelProvider(org.eclipse.jface.text.source.ISourceViewer)
	 */
	public ILabelProvider getStatusLineLabelProvider( ISourceViewer sourceViewer ) {
		return this.config.getStatusLineLabelProvider( sourceViewer );
	}


	/**
	 * @param sourceViewer
	 * @return
	 * @see org.eclipse.ui.editors.text.TextSourceViewerConfiguration
	 * #getTabWidth(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public int getTabWidth( ISourceViewer sourceViewer ) {
		return this.config.getTabWidth( sourceViewer );
	}


	/**
	 * @param sourceViewer
	 * @param contentType
	 * @param stateMask
	 * @return
	 * @see org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration
	 * #getTextHover(org.eclipse.jface.text.source.ISourceViewer, java.lang.String, int)
	 */
	@Override
	public ITextHover getTextHover( ISourceViewer sourceViewer, String contentType, int stateMask ) {
		return this.config.getTextHover( sourceViewer, contentType, stateMask );
	}


	/**
	 * @param sourceViewer
	 * @param contentType
	 * @return
	 * @see org.eclipse.ui.editors.text.TextSourceViewerConfiguration
	 * #getTextHover(org.eclipse.jface.text.source.ISourceViewer, java.lang.String)
	 */
	@Override
	public ITextHover getTextHover( ISourceViewer sourceViewer, String contentType ) {
		return this.config.getTextHover( sourceViewer, contentType );
	}


	/**
	 * @param sourceViewer
	 * @return
	 * @see org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration
	 * #getUndoManager(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public final IUndoManager getUndoManager( ISourceViewer sourceViewer ) {
		return null;
	}


	/**
	 * @return
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.config.hashCode();
	}


	/**
	 * @param highlighter
	 * @see org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration
	 * #setHighlighter(org.eclipse.wst.sse.ui.internal.provisional.style.ReconcilerHighlighter)
	 */
	public void setHighlighter( ReconcilerHighlighter highlighter ) {
		this.config.setHighlighter( highlighter );
	}


	/**
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.config.toString();
	}
}
