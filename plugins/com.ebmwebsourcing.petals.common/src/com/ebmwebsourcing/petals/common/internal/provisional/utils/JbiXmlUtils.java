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

package com.ebmwebsourcing.petals.common.internal.provisional.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.InvalidJbiXmlException;
import com.ebmwebsourcing.petals.common.internal.provisional.emf.JbiCustomDiagnostician;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.DocumentRoot;
import com.sun.java.xml.ns.jbi.Jbi;
import com.sun.java.xml.ns.jbi.Provides;
import com.sun.java.xml.ns.jbi.provider.JbiItemProviderAdapterFactory;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class JbiXmlUtils {

	/**
	 * Loads the JBI model instance from a jbi.xml file.
	 * @param emfUri the EMF URI of the jbi.xml file
	 * @return the JBI model
	 * @throws InvalidJbiXmlException if the jbi.xml file is badly-formed
	 */
	public static Jbi getJbiXmlModel( org.eclipse.emf.common.util.URI emfUri ) throws InvalidJbiXmlException {

		try {
			ComposedAdapterFactory adapterFactory =
					new ComposedAdapterFactory( ComposedAdapterFactory.Descriptor.Registry.INSTANCE );

			adapterFactory.addAdapterFactory( new ResourceItemProviderAdapterFactory());
			adapterFactory.addAdapterFactory( new JbiItemProviderAdapterFactory());
			adapterFactory.addAdapterFactory( new ReflectiveItemProviderAdapterFactory());

			BasicCommandStack commandStack = new BasicCommandStack();
			AdapterFactoryEditingDomain editingDomain = new AdapterFactoryEditingDomain(
					adapterFactory, commandStack, new HashMap<Resource, Boolean>());

			Resource resource = editingDomain.getResourceSet().getResource( emfUri, true );
			DocumentRoot root = (DocumentRoot) resource.getContents().get( 0 );

			return root.getJbi();

		} catch( Exception e ) {
			throw new InvalidJbiXmlException( e );
		}
	}


	/**
	 * Loads the JBI model instance from a jbi.xml file.
	 * @param jbiXmlFile the jbi.xml file
	 * @return the JBI model
	 * @throws InvalidJbiXmlException if the jbi.xml file is badly-formed
	 */
	public static Jbi getJbiXmlModel( File jbiXmlFile ) throws InvalidJbiXmlException {
		org.eclipse.emf.common.util.URI uri = org.eclipse.emf.common.util.URI.createFileURI( jbiXmlFile.getAbsolutePath());
		return getJbiXmlModel( uri );
	}


	/**
	 * Validates an {@link EObject} with respect to the JBI meta-model.
	 * @param jbiXmlElement the element to validate (generally the document root or the jbi element)
	 * @return the validation diagnostic.
	 * <p>
	 * Its status should be {@link Diagnostic#OK} if no error was found.
	 * </p>
	 */
	public static Diagnostic validateJbiXmlModel( EObject jbiXmlElement ) {
		return new JbiCustomDiagnostician().validate( jbiXmlElement );
	}


	/**
	 * Loads the jbi.xml file directly from the URL of a Petals archive.
	 *  @param petalsArchiveUri the URI of the Petals archive
	 * @return the JBI instance
	 * @throws InvalidJbiXmlException
	 */
	public static Jbi getJbiXmlModel( URI petalsArchiveUri )  throws InvalidJbiXmlException {
		org.eclipse.emf.common.util.URI emfUri = org.eclipse.emf.common.util.URI.createURI( "archive:" + petalsArchiveUri + "!/META-INF/jbi.xml" );
		return JbiXmlUtils.getJbiXmlModel( emfUri );
	}


	/**
	 * Gets the WSDL element value.
	 * @param provides the section whose WSDL must be retrieved
	 * @return the WSDL element value (may be null)
	 */
	public static String getWsdlValue( Provides provides ) {

		for( int i=0; i<provides.getGroup().size(); i++ ) {
			FeatureMap.Entry f = provides.getGroup().get( i );
			Object o = f.getValue();
			if( !( o instanceof AnyType ))
				continue;

			if( ! "wsdl".equalsIgnoreCase( f.getEStructuralFeature().getName()))
				continue;

			FeatureMap featureMap = ((AnyType) o).getMixed();
			for( int j=0; j<featureMap.size(); j++ ) {
				EStructuralFeature esf = featureMap.getEStructuralFeature( j );
				if( FeatureMapUtil.isText( esf )) {
					String wsdlValue = featureMap.getValue( j ).toString().trim();
					return wsdlValue;
				}
			}
		}

		return null;
	}


	/**
	 * Gets the MEP element value.
	 * @param consumes the section whose MEP must be retrieved
	 * @return the MEP element value (may be null)
	 */
	public static String getMepValue( Consumes consumes ) {

		for( int i=0; i<consumes.getGroup().size(); i++ ) {
			FeatureMap.Entry f = consumes.getGroup().get( i );
			Object o = f.getValue();
			if( o == null || !( o instanceof AnyType ))
				continue;

			if( ! "mep".equalsIgnoreCase( f.getEStructuralFeature().getName()))
				continue;

			FeatureMap featureMap = ((AnyType)o).getMixed();
			for( int j=0; j<featureMap.size(); j++ ) {
				EStructuralFeature esf = featureMap.getEStructuralFeature( j );
				if( FeatureMapUtil.isText( esf )) {
					String mepValue = featureMap.getValue( j ).toString().trim();
					return mepValue;
				}
			}
		}

		return null;
	}


	/**
	 * Gets the jbi.xml file from a project.
	 * @param project the SU project
	 * @return the jbi.xml file
	 * @throws FileNotFoundException if the jbi.xml file cannot be found
	 */
	public static IFile getJbiXmlFile( IProject project ) throws FileNotFoundException {

		IFile jbiXmlFile = project.getFile( PetalsConstants.LOC_JBI_FILE );
		if( jbiXmlFile.exists())
			return jbiXmlFile;

		throw new FileNotFoundException( "No jbi.xml file could be found in " + project.getName() + "." );
	}


	/**
	 * Gets the jbi.xml file from a project.
	 * @param projectFile the SU project directory
	 * @return the jbi.xml file
	 * @throws FileNotFoundException if the jbi.xml file cannot be found
	 */
	public static File getJbiXmlFile( File projectFile ) throws FileNotFoundException {

		File jbiXmlFile = new File( projectFile, PetalsConstants.LOC_JBI_FILE );
		if( jbiXmlFile.exists())
			return jbiXmlFile;

		throw new FileNotFoundException( "No jbi.xml file could be found in " + projectFile.getAbsolutePath() + "." );
	}


	/**
	 * Gets the JBI model instance of a SU project.
	 * @param project the project
	 * @return the JBI model
	 * @throws InvalidJbiXmlException if the jbi.xml file is badly-formed
	 * @throws FileNotFoundException if the jbi.xml file does not exist
	 */
	public static Jbi getJbiXmlModel( IProject project )
			throws InvalidJbiXmlException, FileNotFoundException {

		IFile jbiXmlFile = getJbiXmlFile( project );
		return getJbiXmlModel( jbiXmlFile );
	}


	/**
	 * Loads the JBI model instance from a jbi.xml file.
	 * @param jbiXmlFile the jbi.xml file
	 * @return the JBI model
	 * @throws InvalidJbiXmlException if the jbi.xml file is badly-formed
	 */
	public static Jbi getJbiXmlModel( IFile jbiXmlFile ) throws InvalidJbiXmlException {
		return getJbiXmlModel( jbiXmlFile.getLocation().toFile());
	}


	/**
	 * Gets the WSDL file from a provides section.
	 * @param jbiXmlFile the jbi.xml file
	 * @param provides the provides section
	 * @return the WSDL file, or null if the WSDL element value is null or empty
	 */
	public static File getWsdlFile( IFile jbiXmlFile, Provides provides ) {
		String wsdlValue = getWsdlValue( provides );
		return getWsdlFile( jbiXmlFile, wsdlValue );
	}


	/**
	 * Gets the WSDL file from a provides section.
	 * @param jbiXmlFile the jbi.xml file
	 * @param wsdlElementValue the WSDL element value
	 * @return the WSDL file, or null if the WSDL element value is null or empty
	 */
	public static File getWsdlFile( IFile jbiXmlFile, String wsdlElementValue ) {

		File file = null;
		if( wsdlElementValue != null
				&& wsdlElementValue.trim().length() != 0 ) {
			try {
				URI wsdlURI = UriUtils.urlToUri( wsdlElementValue );
				if( "file".equals( wsdlURI.getScheme()))
					file = new File( wsdlURI );

			} catch( Exception e ) {
				// e.printStackTrace();
			}

			try {
				if( file == null )
					file = getResourceFile( jbiXmlFile.getProject(), wsdlElementValue ).getLocation().toFile();

			} catch( Exception e ) {
				// e.printStackTrace();
			}
		}

		return file;
	}


	/**
	 * Gets the WSDL file from a provides section.
	 * @param projectRoot a file pointing to the root of a SU project
	 * @param wsdlElementValue the WSDL element value
	 * @return the WSDL file, or null if the WSDL element value is null or empty
	 */
	public static File getWsdlFile( File projectRoot, String wsdlElementValue ) {

		File file = null;
		if( wsdlElementValue != null
				&& wsdlElementValue.trim().length() != 0 ) {
			try {
				URI wsdlURI = UriUtils.urlToUri( wsdlElementValue );
				if( "file".equals( wsdlURI.getScheme()))
					file = new File( wsdlURI );

			} catch( Exception e ) {
				// e.printStackTrace();
			}

			try {
				if( file == null )
					file = new File( new File( projectRoot, PetalsConstants.LOC_RES_FOLDER ), wsdlElementValue );

			} catch( Exception e ) {
				// e.printStackTrace();
			}
		}

		return file;
	}


	/**
	 * It is not checked whether the returned resource file exists.
	 * @param project
	 * @param relativePath
	 * @return
	 * @throws FileNotFoundException
	 */
	public static IFile getResourceFile( IProject project, String relativePath ) throws FileNotFoundException {
		IFolder folder = project.getFolder( PetalsConstants.LOC_RES_FOLDER );
		return folder.getFile( relativePath );
	}


	/**
	 * Indicates if a project contains provided services.
	 * @param project a project (any project)
	 * @return true if the project contains at least one provide, false otherwise
	 */
	public static boolean hasProvides( IProject project ) {

		boolean result = false;
		try {
			Jbi jbi = getJbiXmlModel( project );
			if( jbi.getServices() != null ) {
				EList<Provides> provides = jbi.getServices().getProvides();
				result = provides != null && provides.size() > 0;
			}

		} catch( InvalidJbiXmlException e ) {
			// nothing

		} catch( FileNotFoundException e ) {
			// nothing
		}

		return result;
	}


	/**
	 * Checks whether a JBI descriptor describes a component.
	 * @param jbiXmlFile the JBI descriptor to check
	 * @return true if it describes a component, false otherwise
	 */
	public static boolean describesComponent( File jbiXmlFile ) {

		boolean result = false;
		try {
			Jbi jbi = getJbiXmlModel( jbiXmlFile );
			result = jbi != null && jbi.getComponent() != null;

		} catch( InvalidJbiXmlException e ) {
			// nothing
		}

		return result;
	}


	/**
	 * Checks whether a JBI descriptor describes a shared library.
	 * @param jbiXmlFile the JBI descriptor to check
	 * @return true if it describes a shared library, false otherwise
	 */
	public static boolean describesSharedLibrary( File jbiXmlFile ) {

		boolean result = false;
		try {
			Jbi jbi = getJbiXmlModel( jbiXmlFile );
			result = jbi != null && jbi.getSharedLibrary() != null;

		} catch( InvalidJbiXmlException e ) {
			// nothing
		}

		return result;
	}


	/**
	 * Checks whether a JBI descriptor describes a service unit.
	 * @param jbiXmlFile the JBI descriptor to check
	 * @return true if it describes a SU, false otherwise
	 */
	public static boolean describesServiceUnit( File jbiXmlFile ) {

		boolean result = false;
		try {
			Jbi jbi = getJbiXmlModel( jbiXmlFile );
			result = jbi != null && jbi.getServices() != null;

		} catch( InvalidJbiXmlException e ) {
			// nothing
		}

		return result;
	}


	/**
	 * Checks whether a JBI descriptor describes a service-assembly.
	 * @param jbiXmlFile the JBI descriptor to check
	 * @return true if it describes a SA, false otherwise
	 */
	public static boolean describesServiceAssembly( File jbiXmlFile ) {

		boolean result = false;
		try {
			Jbi jbi = getJbiXmlModel( jbiXmlFile );
			result = jbi != null && jbi.getServiceAssembly() != null;

		} catch( InvalidJbiXmlException e ) {
			// nothing
		}

		return result;
	}
}
