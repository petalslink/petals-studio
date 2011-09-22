/****************************************************************************
 * 
 * Copyright (c) 2008-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.commons.jbi.internal.provisional.utils;

import com.ebmwebsourcing.commons.jbi.internal.generated.SaJbiXmlTemplate;
import com.ebmwebsourcing.commons.jbi.internal.generated.SuJbiXmlTemplate;
import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.SaBean;
import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.SuBean;

/**
 * A facade class which calls a template and returns the generation result as a String.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class JbiXmlGenerator {
	/** The unique instance of this class. */
	private static JbiXmlGenerator instance = new JbiXmlGenerator();


	/** Private empty constructor (singleton pattern). */
	private JbiXmlGenerator() {}

	/**
	 * @return the unique instance of this class.
	 */
	public static JbiXmlGenerator getInstance() {
		return instance;
	}

	/**
	 * Generate the content of a service unit jbi.xml file.
	 * @param suBean a bean containing everything required for the generation.
	 * @return the result of the generation as a String.
	 */
	public String generateJbiXmlFileForSu( SuBean suBean ) {
		SuJbiXmlTemplate tpl = new SuJbiXmlTemplate();
		String result = tpl.generate( suBean );
		return result;
	}

	/**
	 * Generate the content of a service assembly jbi.xml file.
	 * @param saBean a bean containing everything required for the generation.
	 * @return the result of the generation as a String.
	 */
	public String generateJbiXmlFileForSa( SaBean saBean ) {
		SaJbiXmlTemplate tpl = new SaJbiXmlTemplate();
		String result = tpl.generate( saBean );
		return result;
	}
}
