/****************************************************************************
 * 
 * Copyright (c) 2010-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.sse;

import org.eclipse.wst.sse.core.internal.format.IStructuredFormatPreferences;
import org.eclipse.wst.xml.core.internal.provisional.format.FormatProcessorXML;
import org.eclipse.wst.xml.core.internal.provisional.format.StructuredFormatPreferencesXML;

/**
 * A formatter for Petals jbi.xml files.
 * @author Vincent Zurczak - EBM WebSourcing
 * FIXME: is this class really useful?
 */
@SuppressWarnings( "restriction" )
public class PetalsProcessorXML extends FormatProcessorXML {

	/**
	 * Provide default preferences for formatting.
	 * @see org.eclipse.wst.xml.core.internal.provisional.format.FormatProcessorXML
	 * #getFormatPreferences()
	 */
	@Override
	public IStructuredFormatPreferences getFormatPreferences() {

		if (this.fFormatPreferences == null) {
			this.fFormatPreferences = super.getFormatPreferences();
			if( getModelPreferences() == null ) {

				// Several attributes => one per line
				((StructuredFormatPreferencesXML) this.fFormatPreferences).setSplitMultiAttrs( true );

				// Do not align the end bracket
				((StructuredFormatPreferencesXML) this.fFormatPreferences).setAlignEndBracket( false );

				// Preserve white space in CData sections
				((StructuredFormatPreferencesXML) this.fFormatPreferences).setPreservePCDATAContent( true );

				// Do not clear blank lines
				this.fFormatPreferences.setClearAllBlankLines( false );
			}
		}

		return this.fFormatPreferences;
	}
}
