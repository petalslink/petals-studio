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

import java.io.IOException;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.source.ICharacterPairMatcher;

/**
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date Oct 27, 2006
 * 
 */
public class XPathCharPairMatcher implements ICharacterPairMatcher {

	protected char[] fPairs;

	protected IDocument fDocument;

	protected int fOffset;

	protected int fStartPos;

	protected int fEndPos;

	protected int fAnchor;

	protected XPathCodeReader fReader = new XPathCodeReader();

	/**
	 * 
	 * @param pairs
	 */
	public XPathCharPairMatcher(char[] pairs) {
		this.fPairs = pairs;

	}

	/**
	 *
	 * @see org.eclipse.jface.text.source.ICharacterPairMatcher#match(org.eclipse.jface.text.IDocument,
	 *      int)
	 */

	public IRegion match(IDocument document, int offset) {

		this.fOffset = offset;

		if (this.fOffset < 0)
			return null;

		this.fDocument = document;

		if (this.fDocument != null && matchPairsAt() && this.fStartPos != this.fEndPos)
			return new Region(this.fStartPos, this.fEndPos - this.fStartPos + 1);

		return null;
	}

	/**
	 * @see org.eclipse.jface.text.source.ICharacterPairMatcher#getAnchor()
	 */
	public int getAnchor() {
		return this.fAnchor;
	}

	/**
	 * @see org.eclipse.jface.text.source.ICharacterPairMatcher#dispose()
	 */
	public void dispose() {
		clear();
		this.fDocument = null;
	}

	/**
	 * @see org.eclipse.jface.text.source.ICharacterPairMatcher#clear()
	 */
	public void clear() {

	}

	protected boolean matchPairsAt() {

		int i;
		int pairIndex1 = this.fPairs.length;
		int pairIndex2 = this.fPairs.length;

		this.fStartPos = -1;
		this.fEndPos = -1;

		// get the chars preceding and following the start position

		try {
			char prevChar = this.fDocument.getChar(Math.max(this.fOffset - 1, 0));
			// modified behavior for
			// http://dev.eclipse.org/bugs/show_bug.cgi?id=16879
			// char nextChar= fDocument.getChar(fOffset);

			// search for opening peer character next to the activation
			// point
			for (i = 0; i < this.fPairs.length; i = i + 2) {
				// if (nextChar == fPairs[i]) {
				// fStartPos= fOffset;
				// pairIndex1= i;
				// } else
				if (prevChar == this.fPairs[i]) {
					this.fStartPos = this.fOffset - 1;
					pairIndex1 = i;
				}
			}

			// search for closing peer character next to the activation
			// point
			for (i = 1; i < this.fPairs.length; i = i + 2) {
				if (prevChar == this.fPairs[i]) {
					this.fEndPos = this.fOffset - 1;
					pairIndex2 = i;
				}
				// else if (nextChar == fPairs[i]) {
				// fEndPos= fOffset;
				// pairIndex2= i;
				// }
			}

			if (this.fEndPos > -1) {
				this.fAnchor = RIGHT;
				this.fStartPos = searchForOpeningPeer(this.fEndPos,
							this.fPairs[pairIndex2 - 1], this.fPairs[pairIndex2], this.fDocument);
				if (this.fStartPos > -1) {
					return true;
				}
				this.fEndPos = -1;

			} else if (this.fStartPos > -1) {
				this.fAnchor = LEFT;
				this.fEndPos = searchForClosingPeer(this.fStartPos, this.fPairs[pairIndex1],
							this.fPairs[pairIndex1 + 1], this.fDocument);

				if (this.fEndPos > -1) {
					return true;
				}
				this.fStartPos = -1;
			}
		} catch( BadLocationException e ) {
			// nothing

		} catch( IOException e ) {
			// nothing
		}

		return false;
	}

	protected int searchForClosingPeer(int offset, int openingPeer,
				int closingPeer, IDocument document) throws IOException {

		this.fReader.configureForwardReader(document, offset + 1, document
					.getLength(), true);

		int stack = 1;
		int c = this.fReader.read();
		while (c != XPathCodeReader.EOF) {
			if (c == openingPeer && c != closingPeer)
				stack++;
			else if (c == closingPeer)
				stack--;

			if (stack == 0)
				return this.fReader.getOffset();

			c = this.fReader.read();
		}

		return -1;
	}

	protected int searchForOpeningPeer(int offset, int openingPeer,
				int closingPeer, IDocument document) throws IOException {

		this.fReader.configureBackwardReader(document, offset, true);

		int stack = 1;
		int c = this.fReader.read();
		while (c != XPathCodeReader.EOF) {
			if (c == closingPeer && c != openingPeer)
				stack++;
			else if (c == openingPeer)
				stack--;

			if (stack == 0)
				return this.fReader.getOffset();

			c = this.fReader.read();
		}

		return -1;
	}
}
