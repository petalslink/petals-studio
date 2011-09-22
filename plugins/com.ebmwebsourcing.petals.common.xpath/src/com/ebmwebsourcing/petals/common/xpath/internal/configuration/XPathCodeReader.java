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

package com.ebmwebsourcing.petals.common.xpath.internal.configuration;

import java.io.IOException;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

/**
 * Reads from a document either forwards or backwards. May be configured to skip
 * comments and strings.
 * 
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date Oct 27, 2006
 */
public class XPathCodeReader {

	/** The EOF character */
	public static final int EOF = -1;
	private boolean fSkipStrings = false;
	private boolean fForward = false;
	private IDocument fDocument;
	private int fOffset;
	private int fEnd = -1;


	/**
	 * Return an XPathCodeReader.
	 */
	public XPathCodeReader() {
		// nothing
	}


	/**
	 * Returns the offset of the last read character. Should only be called
	 * after read has been called.
	 * @return offset
	 */
	public int getOffset() {
		return this.fForward ? this.fOffset - 1 : this.fOffset;
	}


	/**
	 * @param document
	 * @param offset
	 * @param length
	 * @param skipStrings
	 */
	public void configureForwardReader (IDocument document, int offset,	int length, boolean skipStrings) {

		this.fDocument = document;
		this.fOffset = offset;
		this.fSkipStrings = skipStrings;
		this.fForward = true;
		this.fEnd = Math.min(this.fDocument.getLength(), this.fOffset + length);
	}


	/**
	 * @param document
	 * @param offset
	 * @param skipStrings
	 * @throws IOException
	 */
	public void configureBackwardReader(IDocument document, int offset, boolean skipStrings) throws IOException {

		this.fDocument = document;
		this.fOffset = offset;
		this.fSkipStrings = skipStrings;

		this.fForward = false;
		try {
			this.fDocument.getLineOfOffset(this.fOffset);

		} catch (BadLocationException x) {
			throw new IOException(x.getMessage());
		}
	}


	/**
	 * 
	 */
	public void close()  {
		this.fDocument = null;
	}


	/**
	 * @return the next character read, in either the forward or reverse direction.
	 * @throws IOException if bad location (passed start or end)
	 * 
	 */
	public int read() throws IOException {

		try {
			return this.fForward ? readForwards() : readBackwards();

		} catch (BadLocationException x) {
			throw new IOException(x.getMessage());
		}
	}


	/**
	 * @param delimiter
	 * @throws BadLocationException
	 */
	private void gotoStringEnd(char delimiter) throws BadLocationException {

		while (this.fOffset < this.fEnd) {
			char current = this.fDocument.getChar(this.fOffset++);
			if (current == '\\') {
				// ignore escaped characters
				++this.fOffset;
			} else if (current == delimiter) {
				return;
			}
		}
	}


	/**
	 * @return
	 * @throws BadLocationException
	 */
	private int readForwards() throws BadLocationException {
		while (this.fOffset < this.fEnd) {
			char current = this.fDocument.getChar(this.fOffset++);

			switch (current) {

			case '"':
			case '\'':

				if (this.fSkipStrings) {
					gotoStringEnd(current);
					continue;
				}

				return current;
			}

			return current;
		}

		return EOF;
	}


	/**
	 * @param delimiter
	 * @throws BadLocationException
	 */
	private void gotoStringStart(char delimiter) throws BadLocationException {

		while (0 < this.fOffset) {
			char current = this.fDocument.getChar(this.fOffset);
			if (current == delimiter) {
				if (!(0 <= this.fOffset && this.fDocument.getChar(this.fOffset - 1) == '\\'))
					return;
			}

			--this.fOffset;
		}
	}


	/**
	 * @return
	 * @throws BadLocationException
	 */
	private int readBackwards() throws BadLocationException {

		while (0 < this.fOffset) {

			--this.fOffset;

			char current = this.fDocument.getChar(this.fOffset);
			switch (current) {
			case '"':
			case '\'':
				if (this.fSkipStrings) {
					--this.fOffset;
					gotoStringStart(current);
					continue;
				}

				return current;
			}

			return current;
		}

		return EOF;
	}
}
