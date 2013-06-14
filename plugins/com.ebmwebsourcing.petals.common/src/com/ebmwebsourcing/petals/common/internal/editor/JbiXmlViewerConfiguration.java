/****************************************************************************
 * 
 * Copyright (c) 2007-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.editor;

import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.hyperlink.IHyperlinkDetector;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.wst.sse.core.text.IStructuredPartitions;
import org.eclipse.wst.xml.core.text.IXMLPartitions;
import org.eclipse.wst.xml.ui.StructuredTextViewerConfigurationXML;
import org.eclipse.wst.xml.ui.internal.contentassist.NoRegionContentAssistProcessor;

import com.ebmwebsourcing.petals.common.internal.provisional.sse.PetalsContentAssistProcessor;

/**
 * Extends the XML Configuration for jbi.xml files.
 * @author Vincent Zurczak - EBM WebSourcing
 */
@SuppressWarnings( "restriction" )
public class JbiXmlViewerConfiguration extends StructuredTextViewerConfigurationXML  {

	/**
	 * The label provider for the status line.
	 */
	// private StatusLineLabelProvider statusLineLabelProvider;



	/**
	 * Empty constructor, which just calls the super constructor.
	 */
	public JbiXmlViewerConfiguration() {
		super();
	}


	/**
	 * Bla-bla...
	 * 
	 * @see org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration
	 * #getContentAssistant(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	protected IContentAssistProcessor[] getContentAssistProcessors(
				ISourceViewer sourceViewer,
				String partitionType) {

		IContentAssistProcessor[] processors = null;

		if( IStructuredPartitions.DEFAULT_PARTITION.equals( partitionType )
					|| IXMLPartitions.XML_DEFAULT.equals( partitionType ))
			processors = new IContentAssistProcessor[]{ new PetalsContentAssistProcessor()};

		else if( IStructuredPartitions.UNKNOWN_PARTITION.equals( partitionType ))
			processors = new IContentAssistProcessor[]{ new NoRegionContentAssistProcessor()};

		return processors;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.wst.xml.ui.StructuredTextViewerConfigurationXML
	 * #getStatusLineLabelProvider(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public ILabelProvider getStatusLineLabelProvider(ISourceViewer sourceViewer) {

		// TODO:

		// if( this.statusLineLabelProvider == null )
		//	this.statusLineLabelProvider = new StatusLineLabelProvider();
		// return this.statusLineLabelProvider;
		return super.getStatusLineLabelProvider( sourceViewer );
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.editors.text.TextSourceViewerConfiguration
	 * #getHyperlinkDetectors(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public IHyperlinkDetector[] getHyperlinkDetectors( ISourceViewer sourceViewer ) {

		IHyperlinkDetector[] hyperlinks = super.getHyperlinkDetectors( sourceViewer );
		int size = hyperlinks == null ? 1 : hyperlinks.length + 1;
		IHyperlinkDetector[] newHyperlinks = new IHyperlinkDetector[ size ];

		if( hyperlinks != null )
			System.arraycopy( hyperlinks, 0, newHyperlinks, 0, size-1 );

		newHyperlinks[ size - 1 ] = new LocalFileHyperlinkDetector();
		return newHyperlinks;
	}
}
