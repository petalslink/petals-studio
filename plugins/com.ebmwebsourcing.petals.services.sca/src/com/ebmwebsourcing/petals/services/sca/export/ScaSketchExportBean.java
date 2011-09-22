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
package com.ebmwebsourcing.petals.services.sca.export;

import com.ebmwebsourcing.petals.common.generation.AbstractJbiXmlBean;
import com.ebmwebsourcing.petals.services.wizards.beans.SuImportBean;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ScaSketchExportBean {

	private final SuImportBean suImportBean;
	private final AbstractJbiXmlBean abstractJbiXmlBean;


	/**
	 * Constructor.
	 * @param suImportBean
	 * @param abstractJbiXmlBean
	 */
	public ScaSketchExportBean( SuImportBean suImportBean, AbstractJbiXmlBean abstractJbiXmlBean ) {
		this.suImportBean = suImportBean;
		this.abstractJbiXmlBean = abstractJbiXmlBean;
	}

	/**
	 * @return the suImportBean
	 */
	public SuImportBean getSuImportBean() {
		return this.suImportBean;
	}

	/**
	 * @return the abstractJbiXmlBean
	 */
	public AbstractJbiXmlBean getJbiBeanDelegate() {
		return this.abstractJbiXmlBean;
	}
}
