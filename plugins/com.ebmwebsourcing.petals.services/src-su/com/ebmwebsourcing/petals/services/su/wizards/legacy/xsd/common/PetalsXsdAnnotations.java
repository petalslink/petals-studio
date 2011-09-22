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

package com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common;

import java.util.Collection;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsXsdAnnotations {

	public static final String PETALS_ANNOTATION_NS = "http://petals.ow2.org/eclipse/su-plug-ins";
	public static final String NO_HCI = "no-hci";
	public static final String EDITABLE_COMBO = "editable-combo";
	public static final String SURROUND_WITH_CDATA = "surround-with-cdata";



	/**
	 * @param annotations
	 * @return
	 */
	public static boolean containsNoHciAnnotation( Collection<DependencyAnnotation> annotations ) {
		return containsAnnotation( PetalsXsdAnnotations.NO_HCI, annotations );
	}


	/**
	 * @param annotationName
	 * @param annotations
	 * @return
	 */
	public static boolean containsAnnotation( String annotationName, Collection<DependencyAnnotation> annotations ) {
		return annotations.contains( new DependencyAnnotation( annotationName, "" ));
	}
}
