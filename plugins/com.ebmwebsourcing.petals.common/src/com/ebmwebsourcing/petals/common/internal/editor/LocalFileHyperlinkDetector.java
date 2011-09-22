/****************************************************************************
 * 
 * Copyright (c) 2009-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.editor;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.jface.text.hyperlink.IHyperlinkDetector;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.provisional.sse.StructuredModelHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.DomUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.UriUtils;

/**
 * Hyper links to open Java files referenced in composites and component types.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class LocalFileHyperlinkDetector implements IHyperlinkDetector {

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.hyperlink.IHyperlinkDetector
	 * #detectHyperlinks(org.eclipse.jface.text.ITextViewer, org.eclipse.jface.text.IRegion, boolean)
	 */
	public IHyperlink[] detectHyperlinks(
				ITextViewer textViewer, final IRegion region, boolean canShowMultipleHyperlinks ) {

		List<IHyperlink> hyperlinks = new ArrayList<IHyperlink> ();


		// Get the element associated with the current offset
		IDocument document = textViewer.getDocument();
		Node node = StructuredModelHelper.getNodeByOffset( document, region.getOffset());
		Element elt = null;
		if( node != null ) {
			if( node.getNodeType() == Node.ELEMENT_NODE )
				elt = (Element) node;
			else if( node.getNodeType() == Node.ATTRIBUTE_NODE )
				elt = ((Attr) node).getOwnerElement();
			else if( node.getNodeType() == Node.TEXT_NODE
						&& node.getParentNode() != null
						&& node.getParentNode().getNodeType() == Node.ELEMENT_NODE )
				elt = (Element) node.getParentNode();
		}

		// Resolved the region and the URI
		IRegion nodeRegion = region;
		String fileUri = null;
		if( elt != null ) {

			// Filter allowed mark-ups pointing to files
			String name = DomUtils.getNodeName( elt ).toLowerCase();
			if( "wsdl".equals( name )) //$NON-NLS-1$
				fileUri = StructuredModelHelper.getElementSimpleValue( elt );

			// Check that the URI is contained in the hovered line...
			// ... and compute the associated region to highlight the link
			try {
				IRegion lineRegion = document.getLineInformationOfOffset( region.getOffset());
				String lineText = document.get( lineRegion.getOffset(), lineRegion.getLength());

				if( fileUri != null && lineText.contains( fileUri )) {
					int length = fileUri.length();
					int start = lineRegion.getOffset() + lineText.indexOf( fileUri );
					nodeRegion = new Region( start, length );
				}
				else
					fileUri = null;

			} catch( BadLocationException e ) {
				e.printStackTrace();
			}
		}

		// Resolve the file URI
		if( fileUri != null ) {
			IFile file = ResourceUtils.getIFileFromEditor();
			if( UriUtils.isAbsoluteUri( fileUri )) {

				// URL => Open the URL in the browser
				URI uri = UriUtils.urlToUri( fileUri );
				try {
					PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser().openURL( uri.toURL());

				} catch( PartInitException e ) {
					PetalsCommonPlugin.log( e, IStatus.WARNING );

				} catch( MalformedURLException e ) {
					PetalsCommonPlugin.log( e, IStatus.WARNING );
				}

			} else {
				// Open the file in the default editor
				try {
					IFile fileToOpen = JbiXmlUtils.getResourceFile( file.getProject(), fileUri );
					if( fileToOpen.exists())
						hyperlinks.add( createNewHyperlink( nodeRegion, fileUri, fileToOpen, true ));

				} catch( FileNotFoundException e ) {
					// nothing
				}
			}
		}

		IHyperlink[] result = new IHyperlink[ hyperlinks.size()];
		if( hyperlinks.size() == 0 )
			result = null;
		else
			result = hyperlinks.toArray( result );

		return result;
	}


	/**
	 * Creates a new hyper link.
	 * @param nodeRegion
	 * @param fileUri
	 * @param fileToOpen
	 * @param b
	 */
	private IHyperlink createNewHyperlink(
				final IRegion nodeRegion,
				final String fileUri,
				final IFile fileToOpen,
				final boolean localFile ) {

		return new IHyperlink() {

			public IRegion getHyperlinkRegion() {
				return nodeRegion;
			}

			public String getHyperlinkText() {
				return fileUri;
			}

			public String getTypeLabel() {
				return "Petals resource"; //$NON-NLS-1$
			}

			public void open() {
				if( localFile ) {
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					try {
						IDE.openEditor( page, fileToOpen, true );
					} catch( PartInitException e ) {
						e.printStackTrace();
					}
				}
			}
		};
	}
}
