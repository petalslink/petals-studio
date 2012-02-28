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

package com.ebmwebsourcing.petals.common.internal.provisional.maven;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.provisional.preferences.PreferencesManager;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;

/**
 * A set of utility methods related to Maven and pom.xml files.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class MavenUtils {

	/**
	 * Gets the elements from the pom.xml file located at the work space root.
	 * @return a bean for the Maven templates (never null, even when the parent pom does not exists).
	 */
	public static MavenBean getPomParentElements() {

		String parentPath = PreferencesManager.getMavenPomParent();
		MavenBean bean = null;
		try {
			URL pomParent;
			if( parentPath != null && parentPath.trim().length() > 0 ) {
				try {
					pomParent = new URL( parentPath );

				} catch( Exception e ) {
					File f = new File( parentPath );
					if( ! f.exists() && ! f.isFile())
						throw new MalformedURLException( f.toString() + " is not a valid file location." );

					pomParent = f.toURI().toURL();
				}

				bean = getPomElements( pomParent );
			}

		} catch( MalformedURLException e ) {
			PetalsCommonPlugin.log( e, IStatus.WARNING );
		}

		MavenBean returnedBean = new MavenBean ();
		if( bean != null ) {
			returnedBean.setParentArtifactId( bean.getArtifactId());
			returnedBean.setParentGroupId( bean.getGroupId() );
			returnedBean.setParentVersion( bean.getVersion());
		}

		return returnedBean;
	}


	/**
	 * Gets the elements from a pom.xml IFile.
	 * @param file the POM file
	 * @return a bean for the Maven templates, or null in case of invalid file.
	 */
	public static MavenBean getPomElements( IFile file ) {
		try {
			URL url = file.getLocation().toFile().toURI().toURL();
			return getPomElements( url );

		} catch( MalformedURLException e ) {
			PetalsCommonPlugin.log( e, IStatus.WARNING );
		}

		return null;
	}


	/**
	 * Gets the elements from a pom.xml file.
	 * @param url
	 * @return a bean for the Maven templates, or null in case of invalid URL.
	 */
	public static MavenBean getPomElements( URL url ) {

		SAXBuilder builder = new SAXBuilder();
		try {
			InputStream stream = url.openStream();
			Document doc = builder.build( stream );
			Namespace ns = doc.getRootElement().getNamespace();

			Element artifactIdElement = ns == null ? doc.getRootElement().getChild( "artifactId" )
						: doc.getRootElement().getChild( "artifactId", ns );

			Element groupIdElement = ns == null ? doc.getRootElement().getChild( "groupId" )
						: doc.getRootElement().getChild( "groupId", ns );

			Element versionElement = ns == null ? doc.getRootElement().getChild( "version" )
						: doc.getRootElement().getChild( "version", ns );

			String artifactId = artifactIdElement != null ? artifactIdElement.getValue() : "";
			String groupId = groupIdElement != null ? groupIdElement.getValue() : "";
			String version = versionElement != null ? versionElement.getValue() : "";

			MavenBean bean = new MavenBean ();
			bean.setArtifactId( artifactId );
			bean.setGroupId( groupId );
			bean.setVersion( version );
			return bean;

		} catch( Exception e ) {
			PetalsCommonPlugin.log( e, IStatus.WARNING );
		}

		return null;
	}


	/**
	 * Gets the dependency elements from a pom.xml IFile.
	 * @param file
	 * @return a list of {@link MavenBean} matching the dependencies.
	 */
	public static List<MavenBean> getPomDependencies( IFile file ) {
		return getPomDependencies( file.getLocation().toFile());
	}


	/**
	 * Gets the dependency elements from a pom.xml file.
	 * <p>
	 * This method only gets the artifactId, the groupId and the version elements.
	 * </p>
	 * @param file
	 * @return a list of {@link MavenBean} matching the dependencies.
	 */
	public static List<MavenBean> getPomDependencies( File file ) {

		List<MavenBean> beans = new ArrayList<MavenBean> ();
		if( ! file.exists())
			return beans;

		SAXBuilder builder = new SAXBuilder();
		try {
			Document doc = builder.build( file );
			Namespace ns = doc.getRootElement().getNamespace();
			Element depsElt = ns == null ? doc.getRootElement().getChild( "dependencies" )
						: doc.getRootElement().getChild( "dependencies", ns );

			List<?> children = ns == null ? depsElt.getChildren( "dependency" ) : depsElt.getChildren( "dependency", ns );
			for( Object o : children ) {
				if( o instanceof Element ) {
					Element depElt = (Element) o;

					Element artifactIdElement = ns == null ? depElt.getChild( "artifactId" ) : depElt.getChild( "artifactId", ns );
					Element groupIdElement = ns == null ? depElt.getChild( "groupId" ) : depElt.getChild( "groupId", ns );
					Element versionElement = ns == null ? depElt.getChild( "version" ) : depElt.getChild( "version", ns );

					String artifactId = artifactIdElement != null ? artifactIdElement.getValue() : "";
					String groupId = groupIdElement != null ? groupIdElement.getValue() : "";
					String version = versionElement != null ? versionElement.getValue() : "";

					MavenBean bean = new MavenBean ();
					bean.setArtifactId( artifactId );
					bean.setGroupId( groupId );
					bean.setVersion( version );
					beans.add( bean );
				}
			}

		} catch( Exception e ) {
			PetalsCommonPlugin.log( e, IStatus.WARNING );
		}

		return beans;
	}


	/**
	 * Extracts the dependencies from a POm whose scope is 'provided'.
	 * <p>
	 * This method only gets the artifact ID.
	 * </p>
	 * @param file
	 * @return a non-null set of artifact IDs
	 */
	public static Set<String> extractDependenciesWithProvidedScope( File file ) {

		Set<String> result = new HashSet<String> ();
		if( ! file.exists())
			return result;

		SAXBuilder builder = new SAXBuilder();
		try {
			Document doc = builder.build( file );
			Namespace ns = doc.getRootElement().getNamespace();
			Element depsElt = ns == null ? doc.getRootElement().getChild( "dependencies" )
						: doc.getRootElement().getChild( "dependencies", ns );

			List<?> children = ns == null ? depsElt.getChildren( "dependency" ) : depsElt.getChildren( "dependency", ns );
			for( Object o : children ) {
				if( o instanceof Element ) {
					Element depElt = (Element) o;

					Element scopeElement = ns == null ? depElt.getChild( "scope" ) : depElt.getChild( "scope", ns );
					String scope = scopeElement != null ? scopeElement.getValue() : "";
					if( ! "provided".equalsIgnoreCase( scope ))
						continue;

					Element artifactIdElement = ns == null ? depElt.getChild( "artifactId" ) : depElt.getChild( "artifactId", ns );
					String artifactId = artifactIdElement != null ? artifactIdElement.getValue() : "";
					result.add( artifactId );
				}
			}

		} catch( Exception e ) {
			PetalsCommonPlugin.log( e, IStatus.WARNING );
		}

		return result;
	}


	/**
	 * @param targetPom
	 * @param dependencyPoms
	 */
	public static void setPomDependencies( File targetPom, Collection<File> dependencyPoms ) {

		// Get the properties of the referenced POM
		Collection<MavenBean> dependenciesMavenBeans = new ArrayList<MavenBean>( dependencyPoms.size());
		for( File f : dependencyPoms ) {
			try {
				MavenBean bean = getPomElements( f.toURI().toURL());
				dependenciesMavenBeans.add( bean );

			} catch( MalformedURLException e ) {
				PetalsCommonPlugin.log( e, IStatus.WARNING, "Could not read the Maven dependencies for " + targetPom.getAbsolutePath());
			}
		}

		// Set the dependencies in the target POM
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		try {
			doc = builder.build( targetPom );
			Namespace ns = doc.getRootElement().getNamespace();
			Element depsElt = ns == null ? doc.getRootElement().getChild( "dependencies" )
						: doc.getRootElement().getChild( "dependencies", ns );

			depsElt.removeContent();
			for( MavenBean bean : dependenciesMavenBeans ) {

				Element dependencyElement = ns == null ? new Element( "dependency" ) : new Element( "dependency", ns );
				depsElt.addContent( dependencyElement );

				Element artifactIdElement = ns == null ? new Element( "artifactId" ) : new Element( "artifactId", ns );
				artifactIdElement.setText( bean.getArtifactId());
				dependencyElement.addContent( artifactIdElement );

				Element groupIdElement = ns == null ? new Element( "groupId" ) : new Element( "groupId", ns );
				groupIdElement.setText( bean.getGroupId());
				dependencyElement.addContent( groupIdElement );

				Element versionElement = ns == null ? new Element( "version" ) : new Element( "version", ns );
				versionElement.setText( bean.getVersion());
				dependencyElement.addContent( versionElement );

				Element typeElement = ns == null ? new Element( "type" ) : new Element( "type", ns );
				typeElement.setText( "jbi-service-unit" );
				dependencyElement.addContent( typeElement );
			}

		} catch( Exception e ) {
			PetalsCommonPlugin.log( e, IStatus.WARNING, "Could not set the Maven dependencies for " + targetPom.getAbsolutePath());
		}

		// Write the new POM
		FileOutputStream fos = null;
		try {
			if( doc != null ) {
				fos = new FileOutputStream( targetPom );
				new XMLOutputter( Format.getPrettyFormat()).output( doc, fos );
			}

		} catch( Exception e ) {
			PetalsCommonPlugin.log( e, IStatus.WARNING, "Could not write the Maven dependencies for " + targetPom.getAbsolutePath());

		} finally {
			if( fos != null ) {
				try {
					fos.close();

				} catch( IOException e ) {
					// nothing
				}
			}
		}
	}


	/**
	 * Finds the location of the local Maven repository.
	 * <p>
	 * This method first searches in the <code>~/.m2/settings.xml</code> file.
	 * If the local repository was not moved, then the default one
	 * (<code>~/.m2/repository</code>) is used.
	 * </p>
	 *
	 * @return the location of the local Maven repository
	 */
	public static IPath findLocalMavenRepository() {

		IPath result = null;

		// Look at the settings.xml file
		File settingsFile = new File( System.getProperty( "user.home" ), ".m2/settings.xml" );
		SAXBuilder builder = new SAXBuilder();
		try {
			Document doc = builder.build( settingsFile );
			Namespace ns = Namespace.getNamespace( "http://maven.apache.org/SETTINGS/1.0.0" );
			Element elt = doc.getRootElement().getChild( "localRepository", ns );
			if( elt != null ) {
				String text = elt.getTextTrim();
				if( StringUtils.isEmpty( text )) {
					PetalsCommonPlugin.log( "The M2 repository is overwritten in ~/.m2/settings.xml but the location is empty.", IStatus.WARNING );
				} else {
					result = new Path( elt.getTextTrim());
				}
			}

		} catch( Exception e ) {
			PetalsCommonPlugin.log( e, IStatus.WARNING );
		}

		// By default, assume it is ~/.m2/repository
		if( result == null )
			result = new Path( System.getProperty( "user.home" )).append( ".m2/repository" );

		// Make some check
		if( ! result.toFile().exists())
			PetalsCommonPlugin.log( "The found M2 repository does not exist.", IStatus.WARNING );

		return result;
	}
}
