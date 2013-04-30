/****************************************************************************
 * 
 * Copyright (c) 2010-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.common.internal.provisional.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.ltk.core.refactoring.TextFileChange;
import org.eclipse.text.edits.MultiTextEdit;
import org.eclipse.text.edits.ReplaceEdit;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.provisional.refactoring.PetalsRefactoringBean;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsRefactoringUtils {

	/**
	 * Builds a TextFileChange for a refactoring wizard.
	 * @param file the file whose content must be refactored
	 * @param beans a list of {@link PetalsRefactoringBean}.
	 * @return a text file change (can be null)
	 */
	public static TextFileChange buildTextFileChange( IFile file, List<PetalsRefactoringBean> beans ) {

		List<ReplaceEdit> replaceEdits = new ArrayList<ReplaceEdit> ();
		try {
			String s = IoUtils.readFileContent( file );
			for( PetalsRefactoringBean bean : beans ) {
				replaceEdits.addAll( PetalsRefactoringUtils.buildReplaceEdits(
							s,
							bean.getRegex(),
							bean.getLeftRegex(),
							bean.getRightRegex(),
							bean.getNewValue()));
			}

		} catch( IOException e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );
		}

		TextFileChange textFileChange = null;
		if( replaceEdits.size() > 0 ) {
			MultiTextEdit textEdit = new MultiTextEdit();
			for( ReplaceEdit replaceEdit : replaceEdits )
				textEdit.addChild( replaceEdit );

			textFileChange = new TextFileChange( "Update " + file.getName(), file );
			textFileChange.setEdit( textEdit );
			textFileChange.setTextType( file.getFileExtension());
		}

		return textFileChange;
	}


	/**
	 * Builds a list of {@link ReplaceEdit} for a refactoring wizard.
	 * @param file the file whose content must be refactored
	 * @param regex the regular expression to replace
	 * @param leftRegex a regular expression that defines a left delimiter (can be null for none)
	 * @param rightRegex a regular expression that defines a right delimiter (can be null for none)
	 * @param newValue the new text
	 * @return a text file change (can be null)
	 */
	public static TextFileChange buildTextFileChange(
				IFile file,
				String regex,
				String leftRegex,
				String rightRegex,
				String newValue ) {

		PetalsRefactoringBean bean = new PetalsRefactoringBean();
		bean.setRegex( rightRegex );
		bean.setLeftRegex( leftRegex );
		bean.setRegex( regex );
		bean.setNewValue( newValue );

		return buildTextFileChange( file, Arrays.asList( bean ));
	}


	/**
	 * Builds a list of {@link ReplaceEdit} for a refactoring wizard.
	 * @param text the original text
	 * @param regex the regular expression to replace
	 * @param newValue the new text
	 * @param leftRegex a regular expression that defines a left delimiter (can be null for none)
	 * @param rightRegex a regular expression that defines a right delimiter (can be null for none)
	 * @return a non-null list of Replace edits
	 */
	public static List<ReplaceEdit> buildReplaceEdits(
				String text,
				String regex,
				String leftRegex,
				String rightRegex,
				String newValue ) {

		StringBuilder completeRegex = new StringBuilder();
		if( leftRegex != null )
			completeRegex.append( leftRegex );
		completeRegex.append( regex );
		if( rightRegex != null )
			completeRegex.append( rightRegex );

		List<ReplaceEdit> edits = new ArrayList<ReplaceEdit> ();
		Pattern completePattern = Pattern.compile( completeRegex.toString(), Pattern.MULTILINE | Pattern.DOTALL );
		Pattern pattern = Pattern.compile( regex, Pattern.MULTILINE | Pattern.DOTALL );
		Matcher m = completePattern.matcher( text );
		while( m.find()) {

			String sequence =  m.group();
			Matcher subMatcher = pattern.matcher( sequence );
			if( subMatcher.find()) {
				int start = m.start() + subMatcher.start();
				int length = subMatcher.group().length();

				ReplaceEdit replaceEdit = new ReplaceEdit( start, length, newValue );
				edits.add( replaceEdit );
			}
		}

		return edits;
	}
}
