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

package com.ebmwebsourcing.petals.common.xpath.internal.configuration.rules;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

/**
 * A rule for recognizing a sequence of characters, whatever that
 * might be ...
 * 
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date Nov 17, 2006
 */
public class StringRule implements IRule {

	IToken fToken;
	String fSequence;


	/**
	 * Return a brand new shiny String Rule ...
	 * 
	 * @param token
	 * @param sequence
	 */
	public StringRule(IToken token, String sequence) {
		assert token != null;
		this.fToken = token;
		assert sequence != null;
		this.fSequence = sequence;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.rules.IRule
	 * #evaluate(org.eclipse.jface.text.rules.ICharacterScanner)
	 */
	public IToken evaluate(ICharacterScanner scanner) {

		int cnt = 0;
		for (int i = 0, j = this.fSequence.length(); i < j; i++) {
			int ch = scanner.read();
			cnt += 1;

			if (ch != this.fSequence.charAt(i)) {
				for (int x = 0; x < cnt; x++)
					scanner.unread();
				return Token.UNDEFINED;
			}
		}

		return this.fToken;
	}
}
