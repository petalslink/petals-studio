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
 
package com.ebmwebsourcing.petals.common.internal.compare;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.contentmergeviewer.TextMergeViewer;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.ui.internal.StructuredTextViewer;
import org.eclipse.wst.xml.core.internal.provisional.contenttype.ContentTypeIdForXML;
import org.eclipse.wst.xml.ui.StructuredTextViewerConfigurationXML;

/**
 * A viewer for XML files.
 * @author Vincent Zurczak - EBM WebSourcing
 */
@SuppressWarnings( "restriction" )
public class XmlViewer extends TextMergeViewer {

	/**
	 * Constructor.
	 * @param parent
	 * @param configuration
	 */
	public XmlViewer( Composite parent, CompareConfiguration configuration ) {
		super( parent, configuration );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.compare.contentmergeviewer.TextMergeViewer
	 * #configureTextViewer(org.eclipse.jface.text.TextViewer)
	 */
	@Override
	protected void configureTextViewer( TextViewer textViewer ) {

		if( textViewer instanceof StructuredTextViewer ) {
			((SourceViewer) textViewer).configure( new StructuredTextViewerConfigurationXML());
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.compare.contentmergeviewer.TextMergeViewer
	 * #createSourceViewer(org.eclipse.swt.widgets.Composite, int)
	 */
	@Override
	protected SourceViewer createSourceViewer( Composite parent, int textOrientation ) {

		return new StructuredTextViewer( parent, null, null, false, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL ) {
			@Override
			public void setDocument( IDocument document ) {

				if( document instanceof IStructuredDocument ) {
					super.setDocument( document );

				} else if( document != null ) {
					String contentTypeID = ContentTypeIdForXML.ContentTypeID_XML;
					IStructuredModel scratchModel = StructuredModelManager.getModelManager().createUnManagedStructuredModelFor( contentTypeID );
					IDocument newDocument = scratchModel.getStructuredDocument();

					String s = document.get();
					newDocument.set( s );
					super.setDocument( newDocument );

				} else {
					super.setDocument( null );
				}
			}
		};
	}
}
