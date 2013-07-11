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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.BufferedRuleBasedScanner;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;

import com.ebmwebsourcing.petals.common.xpath.internal.configuration.rules.AxisRule;
import com.ebmwebsourcing.petals.common.xpath.internal.configuration.rules.FloatRule;
import com.ebmwebsourcing.petals.common.xpath.internal.configuration.rules.FunctionRule;
import com.ebmwebsourcing.petals.common.xpath.internal.configuration.rules.ITokenContext;
import com.ebmwebsourcing.petals.common.xpath.internal.configuration.rules.SingleCharRule;
import com.ebmwebsourcing.petals.common.xpath.internal.configuration.rules.SingleOperatorRule;
import com.ebmwebsourcing.petals.common.xpath.internal.configuration.rules.StringRule;
import com.ebmwebsourcing.petals.common.xpath.internal.configuration.rules.WordRule;
import com.ebmwebsourcing.petals.common.xpath.internal.provisional.configuration.ColorManager;

/**
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date Oct 25, 2006
 */
public class XPathSourceScanner extends BufferedRuleBasedScanner {

	IWordDetector fNCNameDetector = new XPathWordDetector.NCNameWordDetector ();
	IWordDetector fWordDetector = new XPathWordDetector();
	IWordDetector fVariableNameDetector = new XPathWordDetector.VariableDetector();
	IWordDetector fQNameDetector = new XPathWordDetector.QNameDetector ();
	LinkedList<IToken> tokenWindow = new LinkedList<IToken>();


	/**
	 * The scanner for the XPath source editor, which provides
	 * syntax coloring based on the default damager and repairer.
	 *
	 * @param manager
	 */
	public XPathSourceScanner( ColorManager manager ) {

		IToken defToken = new Token( new TextAttribute ( manager.getColor( new RGB( 0, 0, 0 ))));
		final IToken operatorToken = new Token ( new TextAttribute (
					manager.getColor( new RGB( 155, 68, 208 )),
					null,
					SWT.BOLD
		));

		IToken number = new Token( new TextAttribute (
					manager.getColor( new RGB( 100, 100, 100 )),
					null,
					SWT.BOLD
		));

		IToken string = new Token( new TextAttribute ( manager.getColor( new RGB( 0, 136, 0 )))) ;
		IToken brackets = new Token( new TextAttribute (
					manager.getColor( new RGB( 0, 0, 0 )),
					null,
					SWT.BOLD
		));

		IToken axis = new Token( new TextAttribute (
					manager.getColor( new RGB( 0, 136, 0 )),
					null,
					SWT.ITALIC
		));

		IToken pathSep = new Token ( new TextAttribute (
					manager.getColor( new RGB( 0, 0, 255 )),
					null,
					SWT.BOLD
		));

		IToken functionsDefault = new Token( new TextAttribute (
					manager.getColor( new RGB( 0,0,200 )),
					null,
					SWT.ITALIC
		));

		IToken functions = new Token( new TextAttribute (
					manager.getColor( new RGB( 0,0,200 )),
					null,
					SWT.BOLD
		));

		final IToken variableToken = new Token( new TextAttribute(
					manager.getColor( new RGB( 10,10,10 )),
					null,
					SWT.BOLD
		));

		IToken partToken = new Token( new TextAttribute(
					manager.getColor( new RGB( 0xe0,80,0 )),
					null,
					SWT.BOLD
		));


		// The list of rules for this scanner.
		List<IRule> rules = new ArrayList<IRule>(24);


		// Add rule for double quotes string
		rules.add( new SingleLineRule("\"", "\"", string , '\\') ); //$NON-NLS-1$ //$NON-NLS-2$
		// Add a rule for single quotes string
		rules.add( new SingleLineRule("'", "'", string , '\\') ); //$NON-NLS-1$ //$NON-NLS-2$

		// Add function calls ...

		// Add generic whitespace rule.
		rules.add( new WhitespaceRule(new XPathWhitespaceDetector()) );

		// numbers
		rules.add ( new FloatRule ( number )) ;

		// variable rule
		WordRule wordRule = new WordRule ( this.fVariableNameDetector );
		wordRule.addWord ( WordRule.ANY , variableToken );
		rules.add (wordRule);

		// Variable part rule
		wordRule = new WordRule ( new XPathWordDetector.MessagePartDetector() );
		wordRule.addWord ( WordRule.ANY , partToken );
		wordRule.setTokenContextCheck( new TokenContext () {
			@Override
			public boolean checkSeenTokens(XPathSourceScanner scanner) {
				return (scanner.lastToken(0) == variableToken);
			}
		});

		rules.add (wordRule);


		// Some operators.
		rules.add ( new SingleOperatorRule ( operatorToken, "+-*=|/<>" ) );

		// Operators of sorts ...
		rules.add ( new StringRule ( operatorToken, "!=") );
		rules.add ( new StringRule ( operatorToken, ">=") );
		rules.add ( new StringRule ( operatorToken, "<=") );
		rules.add ( new StringRule ( operatorToken, ">=") );

		rules.add ( new SingleCharRule ( brackets, "[]().@," ) );
		rules.add ( new StringRule ( operatorToken, "//") );
		rules.add ( new StringRule ( pathSep, "::") );


		// rule for operators ...
		wordRule = new WordRule( new XPathWordDetector () );

		wordRule.addWord ("mod",operatorToken);
		wordRule.addWord ("div",operatorToken);
		wordRule.addWord ("and",operatorToken);
		wordRule.addWord ("or",operatorToken);

		wordRule.setTokenContextCheck( new TokenContext() {
			@Override
			public boolean checkSeenTokens(XPathSourceScanner scanner) {
				int idx = (scanner.lastToken(0) == Token.WHITESPACE ? 1 : 0);
				return scanner.lastToken(idx) != operatorToken;
			}
		});


		rules.add( wordRule );
		AxisRule axisRule = new AxisRule ( this.fNCNameDetector );
		axisRule.addWords(AXIS, axis);
		rules.add(axisRule);


		// The basic XPath functions
		FunctionRule functionRule = new FunctionRule ( this.fQNameDetector );
		functionRule.addWords(XPATH_FUNCTIONS,functionsDefault);
		rules.add(functionRule);

		// All other functions
		functionRule = new FunctionRule ( this.fQNameDetector );
		functionRule.addWord( WordRule.ANY,functions);
		rules.add(functionRule);

		wordRule = new WordRule( this.fWordDetector );
		wordRule.addWord ( WordRule.ANY, defToken );
		rules.add( wordRule );

		setDefaultReturnToken( defToken ) ;
		setRules ( rules.toArray(new IRule[]{} ));
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.rules.RuleBasedScanner#nextToken()
	 */
	@Override
	public IToken nextToken() {

		IToken next = super.nextToken();
		this.tokenWindow.addFirst(next);
		if (this.tokenWindow.size() > 4)
			this.tokenWindow.removeLast();

		return next;
	}


	/**
	 * Returns the last token with the index of offset. Index 0 means the last token seen,
	 * 1 means the one before the last token seen.
	 *
	 * @param offset
	 * @return the token requested or undefined.
	 */
	public IToken lastToken ( int offset ) {

		try {
			return this.tokenWindow.get(offset);

		} catch( Exception e ) {
			return Token.UNDEFINED;
		}
	}


	static private final String[] XPATH_FUNCTIONS = {

		"last","position","count","id","local-name","namespace-uri","name",
		"string","concat","starts-with","contains","substring-before","substring-after",
		"substring","string-length","normalize-space","translate",
		"boolean","not","true","false","lang",
		"number","sum","floor","ceiling","round"
	};


	static private final String[] AXIS = {
		"ancestor",
		"ancestor-or-self",
		"attribute",
		"child",
		"descendant",
		"descendant-or-self",
		"following",
		"following-sibling",
		"namespace",
		"parent",
		"preceding",
		"preceding-sibling",
		"self"
	};


	/**
	 * The TokenContext class allows us to see what tokens we have seen
	 * so far. In some syntax coloring constructs we need to have a memory
	 * (albeit a simple one) of where we have been.
	 *
	 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
	 * @date Nov 27, 2006
	 */
	abstract class TokenContext implements ITokenContext {

		/*
		 * (non-Javadoc)
		 * @see com.ebmwebsourcing.petals.common.xpath.internal.configuration.rules.ITokenContext
		 * #check(org.eclipse.jface.text.rules.ICharacterScanner)
		 */
		@Override
		public boolean check (ICharacterScanner scanner) {
			if (scanner instanceof XPathSourceScanner) {
				return checkSeenTokens ( (XPathSourceScanner) scanner);
			}
			return false;
		}

		/**
		 * @param scanner
		 * @return true if the right tokens have been seen so far, false otherwise.
		 */
		public abstract boolean checkSeenTokens ( XPathSourceScanner scanner ) ;
	}
}
