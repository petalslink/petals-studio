/****************************************************************************
 *
 * Copyright (c) 2008-2012, EBM WebSourcing
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
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.bpel.common.wsdl.helpers.UriAndUrlHelper;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xml.type.AnyType;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.provisional.emf.InvalidJbiXmlException;
import com.ebmwebsourcing.petals.common.internal.provisional.emf.JbiCustomDiagnostician;
import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.DocumentRoot;
import com.sun.java.xml.ns.jbi.Jbi;
import com.sun.java.xml.ns.jbi.Provides;
import com.sun.java.xml.ns.jbi.util.JbiResourceFactoryImpl;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class JbiXmlUtils {

	/**
	 * Writes a {@link Jbi} instance into a file.
	 * @param jbiInstance the JBI instance to write
	 * @param targetFile the target file
	 * @throws IOException if the content could not be saved
	 */
	public static void writeJbiXmlModel( Jbi jbiInstance, File targetFile ) throws IOException {

		org.eclipse.emf.common.util.URI emfUri = org.eclipse.emf.common.util.URI.createFileURI( targetFile.getAbsolutePath());
		Resource resource = new JbiResourceFactoryImpl().createResource( emfUri );
		resource.getContents().add( jbiInstance );
		resource.save( getJbiXmlSaveOptions());
	}


	/**
	 * Writes a portion of a {@link Jbi} instance and returns it as a string.
	 * @param object the EObject
	 * @return the serialized content of the EObject
	 * @throws IOException
	 */
	public static String writePartialJbiXmlModel( EObject object ) throws IOException {

		Map<Object,Object> options = getJbiXmlSaveOptions();
		EList<EObject> roots = new BasicEList<EObject> ();
		roots.add( object );
		options.put( XMLResource.OPTION_ROOT_OBJECTS, roots );
		options.put( XMLResource.OPTION_DECLARE_XML, false );

		StringWriter sw = new StringWriter();
		URIConverter.WriteableOutputStream uws = new URIConverter.WriteableOutputStream( sw, "UTF-8" );
		object.eResource().save( uws, options );

		return sw.toString();
	}


	/**
	 * @return the save options for jbi.xml files
	 */
	public static Map<Object,Object> getJbiXmlSaveOptions() {

		Map<Object,Object> options = new HashMap<Object,Object> ();
		options.put( XMLResource.OPTION_ENCODING, "UTF-8" );
		options.put( XMLResource.OPTION_ESCAPE_USING_CDATA, Boolean.TRUE );

		// FIXME: are the following ones required?
		// options.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
		// options.put(XMLResource.OPTION_XML_MAP, xmlMap);

		return options;
	}


	/**
	 * Writes a {@link Jbi} instance into a file.
	 * @param jbiInstance the JBI instance to write
	 * @param targetFile the target file
	 * @throws IOException if the content could not be saved
	 */
	public static void writeJbiXmlModel( Jbi jbiInstance, IFile targetFile ) throws IOException {
		writeJbiXmlModel( jbiInstance, targetFile.getLocation().toFile());
		try {
			targetFile.refreshLocal( IResource.DEPTH_ZERO, new NullProgressMonitor());
		} catch( CoreException e ) {
			PetalsCommonPlugin.log( e, IStatus.ERROR );
		}
	}


	/**
	 * Loads the JBI model instance from a jbi.xml file.
	 * @param emfUri the EMF URI of the jbi.xml file
	 * @return the JBI model
	 * @throws InvalidJbiXmlException if the jbi.xml file is badly-formed
	 */
	public static Jbi getJbiXmlModel( org.eclipse.emf.common.util.URI emfUri ) throws InvalidJbiXmlException {

		try {
			ResourceSet resourceSet = new ResourceSetImpl();
			Resource resource = resourceSet.getResource( emfUri, true );
			resource.load( resourceSet.getLoadOptions());
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
				URI wsdlURI = UriAndUrlHelper.urlToUri( wsdlElementValue );
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
				URI wsdlURI = UriAndUrlHelper.urlToUri( wsdlElementValue );
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
