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

package com.ebmwebsourcing.petals.services.su.jbiproperties;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;

import com.ebmwebsourcing.petals.services.su.wizards.ErrorReporter;

/**
 * A class in charge of retrieving and updating properties stored in a comment of an IProject.
 * @author Vincent Zurczak - EBM WebSourcing
 */
@Deprecated
public class FormerProjectSPPropertiesManager {

	/**
	 * Property name for "isConsume".
	 * BEWARE ! If you change its value, we loose backward-compatibility.
	 */
	public final static String _IS_CONSUME_PROPERTY = "Project is Consume";

	/**
	 * Property name for "suType".
	 * BEWARE ! If you change its value, we loose backward-compatibility.
	 */
	public final static String _SU_TYPE_PROPERTY = "SU Type";

	/**
	 * Property name for "componentName".
	 * BEWARE ! If you change its value, we loose backward-compatibility.
	 */
	public final static String _COMPONENT_NAME_PROPERTY = "Associated Component";

	/**
	 * Property name for "componentVersion".
	 * BEWARE ! If you change its value, we loose backward-compatibility.
	 */
	public final static String _COMPONENT_VERSION_PROPERTY = "Associated Component Version";

	/**
	 * String used to separate properties.
	 * BEWARE ! If you change its value, we loose backward-compatibility.
	 */
	private final static String PROPERTY_SEPARATOR = ";";

	/**
	 * String used to separate the property name from the property value.
	 * BEWARE ! If you change its value, we loose backward-compatibility.
	 */
	private final static String PROPERTY_ELEMENT_SEPARATOR = ":";


	/**
	 * @param iProject
	 * @return the value of the property given argument, for the project argument.
	 */
	public static Map<String,String> getProperties( IProject iProject ) {

		Map<String,String> properties = new HashMap<String,String> ();
		try {
			String comment = iProject.getDescription().getComment();
			for( String property : comment.split( PROPERTY_SEPARATOR )) {

				property = property.trim();
				String propertyName;
				if( property.startsWith( _SU_TYPE_PROPERTY ))
					propertyName = _SU_TYPE_PROPERTY;
				else if( property.startsWith( _COMPONENT_VERSION_PROPERTY ))
					propertyName = _COMPONENT_VERSION_PROPERTY;
				else if( property.startsWith( _COMPONENT_NAME_PROPERTY ))
					propertyName = _COMPONENT_NAME_PROPERTY;
				else if( property.startsWith( _IS_CONSUME_PROPERTY ))
					propertyName = _IS_CONSUME_PROPERTY;
				else
					continue;

				String propertyValue = property.substring( propertyName.length());
				String[] splitted = propertyValue.split( PROPERTY_ELEMENT_SEPARATOR );
				if( splitted.length <= 1 )
					continue;

				propertyValue = splitted[ splitted.length - 1 ];
				properties.put( propertyName, propertyValue.trim());
			}

		} catch( Exception e ) {
			String msg = "An error occurred while trying to get the properties '"
				+ "' for the SU project " + iProject.getName() + ".";
			ErrorReporter.INSTANCE.registerError( "PROJECT-properties", msg, IStatus.ERROR, e );
		}

		return properties;
	}


	/**
	 * Sets the project properties.
	 * @param iProject
	 * @param properties
	 */
	public static void setProperties( IProject iProject, Map<String,String> properties ) {

		Map<String,String> currentProperties = getProperties( iProject );
		for( Map.Entry<String,String> entry : properties.entrySet())
			currentProperties.put( entry.getKey(), entry.getValue());

		String componentName = currentProperties.get( _COMPONENT_NAME_PROPERTY );
		String componentVersion = currentProperties.get( _COMPONENT_VERSION_PROPERTY );
		String isConsume = currentProperties.get( _IS_CONSUME_PROPERTY );
		String suType = currentProperties.get( _SU_TYPE_PROPERTY );

		try {
			setProjectDescription( iProject, isConsume, suType, componentName, componentVersion );

		} catch( Exception e ) {
			String msg = "Could not update project properties.";
			ErrorReporter.INSTANCE.registerError( "PROJECT-properties", msg, IStatus.ERROR, e );
		}
	}


	/**
	 * Sets the isConsumeProperty of an IProject.
	 * @param iProject
	 * @param isConsumeBooleanValue
	 */
	public static void setIsConsumeProperty( IProject iProject, boolean isConsumeBooleanValue ) {
		try {
			String componentName = getComponentNameProperty( iProject );
			String suType = getSuTypeProperty( iProject );
			String isConsume = String.valueOf( isConsumeBooleanValue );
			String componentVersion = getComponentVersionProperty( iProject );
			setProjectDescription( iProject, isConsume, suType, componentName, componentVersion );

		} catch( Exception e ) {
			String msg = "Could not update project properties (isConsume).";
			ErrorReporter.INSTANCE.registerError( "PROJECT-properties", msg, IStatus.ERROR, e );
		}
	}


	/**
	 * Sets the suTypeProperty of an IProject.
	 * @param iProject
	 * @param suType
	 */
	public static void setSuTypeProperty( IProject iProject, String suType ) {
		try {
			String componentName = getComponentNameProperty( iProject );
			String isConsume = getIsConsumeProperty( iProject );
			String componentVersion = getComponentVersionProperty( iProject );
			setProjectDescription( iProject, isConsume, suType, componentName, componentVersion );

		} catch( Exception e ) {
			String msg = "Could not update project properties (suType).";
			ErrorReporter.INSTANCE.registerError( "PROJECT-properties", msg, IStatus.ERROR, e );
		}
	}


	/**
	 * Sets the componentNameProperty of an IProject.
	 * @param iProject
	 * @param componentName
	 */
	public static void setComponentNameProperty( IProject iProject, String componentName ) {
		try {
			String suType = getSuTypeProperty( iProject );
			String isConsume = getIsConsumeProperty( iProject );
			String componentVersion = getComponentVersionProperty( iProject );
			setProjectDescription( iProject, isConsume, suType, componentName, componentVersion );

		} catch( Exception e ) {
			String msg = "Could not update project properties (componentName).";
			ErrorReporter.INSTANCE.registerError( "PROJECT-properties", msg, IStatus.ERROR, e );
		}
	}


	/**
	 * Sets the componentNameProperty of an IProject.
	 * @param iProject
	 * @param componentVersion
	 */
	public static void setComponentVersionProperty( IProject iProject, String componentVersion ) {
		try {
			String suType = getSuTypeProperty( iProject );
			String isConsume = getIsConsumeProperty( iProject );
			String componentName = getComponentNameProperty( iProject );
			setProjectDescription( iProject, isConsume, suType, componentName, componentVersion );

		} catch( Exception e ) {
			String msg = "Could not update project properties (componentVersion).";
			ErrorReporter.INSTANCE.registerError( "PROJECT-properties", msg, IStatus.ERROR, e );
		}
	}


	/**
	 * Sets the properties of an IProject.
	 * @param iProject
	 * @param isConsumeBooleanValue
	 * @param suType
	 * @param componentName
	 * @param componentVersion
	 */
	public static void setProjectProperties(
				IProject iProject, boolean isConsumeBooleanValue, String suType,
				String componentName, String componentVersion ) {

		try {
			String isConsume = String.valueOf( isConsumeBooleanValue );
			setProjectDescription( iProject, isConsume, suType, componentName, componentVersion );

		} catch( Exception e ) {
			String msg = "Could not update project properties (all).";
			ErrorReporter.INSTANCE.registerError( "PROJECT-properties", msg, IStatus.ERROR, e );
		}
	}


	/**
	 * Writes SU properties as a comment in a IProject description.
	 * 
	 * @param iProject
	 * @param isConsume
	 * @param suType
	 * @param componentName
	 * @param componentVersion
	 * @throws CoreException
	 */
	private static void setProjectDescription(
				IProject iProject, String isConsume, String suType, String componentName, String componentVersion )
	throws CoreException {

		IProjectDescription desc = iProject.getDescription();
		String comment = formatProjectProperties( isConsume, suType, componentName, componentVersion );
		desc.setComment( comment );
		iProject.setDescription( desc, null );
	}


	/**
	 * @param iProject
	 * @return the value of the isConsumeProperty for the project argument (never null).
	 */
	public static String getIsConsumeProperty( IProject iProject ) {
		return getProperty( iProject, _IS_CONSUME_PROPERTY );
	}


	/**
	 * @param iProject
	 * @return the value of the suTypeProperty for the project argument (never null).
	 */
	public static String getSuTypeProperty( IProject iProject ) {
		return getProperty( iProject, _SU_TYPE_PROPERTY );
	}


	/**
	 * @param iProject
	 * @return the value of the componentNameProperty for the project argument (never null).
	 */
	public static String getComponentNameProperty( IProject iProject ) {
		return getProperty( iProject, _COMPONENT_NAME_PROPERTY );
	}


	/**
	 * @param iProject
	 * @return the value of the componentVersionProperty for the project argument (never null).
	 */
	public static String getComponentVersionProperty( IProject iProject ) {
		return getProperty( iProject, _COMPONENT_VERSION_PROPERTY );
	}


	/**
	 * @param iProject
	 * @param propertyName
	 * @return the value of the property given argument, for the project argument.
	 */
	private static String getProperty( IProject iProject, String propertyName ) {
		try {
			String comment = iProject.getDescription().getComment();
			for( String property : comment.split( PROPERTY_SEPARATOR )) {

				property = property.trim();
				if( property.startsWith( propertyName )) {

					String propertyValue = property.substring( propertyName.length());
					String[] splitted = propertyValue.split( PROPERTY_ELEMENT_SEPARATOR );
					if( splitted.length <= 1 )
						return "";

					propertyValue = splitted[ splitted.length - 1 ];
					return propertyValue.trim();
				}
			}

		} catch( Exception e ) {
			String msg = "An error occurred while trying to get the property '" + propertyName
			+ "' for the SU project " + iProject.getName() + ".";
			ErrorReporter.INSTANCE.registerError( "PROJECT-properties", msg, IStatus.ERROR, e );
		}

		return "";
	}


	/**
	 * Formats the SU properties to write them as a comment.
	 * 
	 * @param isConsume
	 * @param suType
	 * @param componentName
	 * @param componentVersion
	 * @return the formatted properties as a single String.
	 */
	public static String formatProjectProperties( String isConsume, String suType, String componentName, String componentVersion ) {
		String result = "\n";
		result 	+= "\t\t" + _IS_CONSUME_PROPERTY + PROPERTY_ELEMENT_SEPARATOR + " "
		+ isConsume + PROPERTY_SEPARATOR + "\n";

		result 	+= "\t\t" + _SU_TYPE_PROPERTY + PROPERTY_ELEMENT_SEPARATOR + " "
		+ suType + PROPERTY_SEPARATOR + "\n";

		result 	+= "\t\t" + _COMPONENT_NAME_PROPERTY + PROPERTY_ELEMENT_SEPARATOR + " "
		+ componentName + PROPERTY_SEPARATOR + "\n";

		result 	+= "\t\t" + _COMPONENT_VERSION_PROPERTY + PROPERTY_ELEMENT_SEPARATOR + " "
		+ componentVersion + PROPERTY_SEPARATOR + "\n\t";
		return result;
	}


	/**
	 * Formats the SU properties to write them as a comment.
	 * Equivalent to
	 * formatProjectProperties( String.valueOf( isConsumeBooleanValue ), String suType, String componentName).
	 * 
	 * @param isConsumeBooleanValue
	 * @param suType
	 * @param componentName
	 * @param componentVersion
	 * @return the formatted properties as a single String.
	 */
	public static String formatProjectProperties(
				boolean isConsumeBooleanValue,
				String suType,
				String componentName,
				String componentVersion ) {

		String isConsume = String.valueOf( isConsumeBooleanValue );
		return formatProjectProperties( isConsume, suType, componentName, componentVersion );
	}
}
