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
package com.ebmwebsourcing.petals.services.sca.configuration;

import org.eclipse.jface.resource.ImageDescriptor;

import com.ebmwebsourcing.petals.services.sca.PetalsScaPlugin;

/**
 * An enumeration of creation patterns for the SCA wizard.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public enum ScaPattern {

	EMPTY, NATIVE, COMPOSITION;


	/**
	 * @return the title of this creation pattern
	 */
	public String getTitle() {

		String title = null;
		switch( this ) {
		case COMPOSITION:
			title = "Build a composition of Petals services";
			break;
		case EMPTY:
			title = "Create an empty SCA application";
			break;
		case NATIVE:
			title = "Implement a native Petals service with SCA";
			break;
		}

		return title;
	}


	/**
	 * @return the description of this creation pattern
	 */
	public String getDescription() {

		StringBuilder sb = new StringBuilder( "<form>" );
		switch( this ) {
		case COMPOSITION:
			sb.append( "<p><b>Build a composition of Petals services</b></p>" );
			sb.append( "<li vspace=\"false\">It contains a component with a default Java implementation.</li>" );
			sb.append( "<li vspace=\"false\">It has one service with a Java interface.</li>" );
			sb.append( "<li vspace=\"false\">It has one or several references that point to Petals services.</li>" );
			sb.append( "<p><br />After creation, you will have to:</p>" );
			sb.append( "<li vspace=\"false\">Complete the Java skeletons.</li>" );
			sb.append( "<li vspace=\"false\">Generate the service's WSDL and the jbi.xml.</li>" );
			break;

		case EMPTY:
			sb.append( "<p><b>Create an empty SCA composite</b><br /></p>" );
			sb.append( "<li vspace=\"false\">It does not contain anything.</li>" );
			sb.append( "<p><br />It is up to you to populate it entirely.</p>" );
			break;

		case NATIVE:
			sb.append( "<p><b>Implement a native Petals service with SCA</b></p>" );
			sb.append( "<li vspace=\"false\">It contains a component with a default Java implementation.</li>" );
			sb.append( "<li vspace=\"false\">It has one service with a Java interface.</li>" );
			sb.append( "<p><br />After creation, you will have to:</p>" );
			sb.append( "<li vspace=\"false\">Complete the Java skeletons.</li>" );
			sb.append( "<li vspace=\"false\">Generate the service's WSDL and the jbi.xml.</li>" );
			break;
		}

		sb.append( "</form>" );
		return sb.toString();
	}


	/**
	 * @return the image descriptor for this creation pattern
	 */
	public ImageDescriptor getImageDescriptor() {

		ImageDescriptor desc = null;
		switch( this ) {
		case COMPOSITION:
			desc = PetalsScaPlugin.getImageDescriptor( "icons/screenshots/ServiceComposition.png" );
			break;
		case EMPTY:
			desc = PetalsScaPlugin.getImageDescriptor( "icons/screenshots/EmptyComposite.png" );
			break;
		case NATIVE:
			desc = PetalsScaPlugin.getImageDescriptor( "icons/screenshots/NativePetalsService.png" );
			break;
		}

		return desc;
	}
}
