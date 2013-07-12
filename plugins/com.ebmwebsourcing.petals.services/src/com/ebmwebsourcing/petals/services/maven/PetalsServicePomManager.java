/****************************************************************************
 * 
 * Copyright (c) 2010-2013, Linagora
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.maven;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IStatus;

import com.ebmwebsourcing.petals.common.internal.provisional.maven.MavenBean;
import com.ebmwebsourcing.petals.common.internal.provisional.preferences.PreferencesManager;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.generated.SaPomXml;
import com.ebmwebsourcing.petals.services.generated.SuPomXml;

/**
 * A class in charge of generating pom.xml files for service projects.
 * <p>
 * This class uses the Petals preferences to determine which of
 * the customized or default POM it must use.
 * </p>
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsServicePomManager {

	public static final PetalsServicePomManager INSTANCE = new PetalsServicePomManager();
	public static final String DEFAULT_SA_POM = "sa-default-pom.xml";
	public static final String DEFAULT_SU_POM = "su-default-pom.xml";


	/**
	 * Private constructor.
	 */
	private PetalsServicePomManager() {
		// nothing
	}


	/**
	 * Gets the POM content for a SA project.
	 * @param mavenBean a Maven bean
	 * @return the POM content, as a string
	 */
	public String getSaPom( MavenBean mavenBean ) {

		// Bug PETALSSTUD-110: make sure the root directory is an existing directory
		boolean useCustomTpl = PreferencesManager.useCustomizedTemplates()
		&& PreferencesManager.isMavenTemplateConfigurationValid();

		// Get the right template then
		String pomXmlContent = null;
		if( useCustomTpl ) {
			File templateFile = getPomTemplateFile( false, null );
			pomXmlContent = readTemplateFile( templateFile );
			pomXmlContent = updateTemplate( pomXmlContent, mavenBean );
		}

		if( StringUtils.isEmpty( pomXmlContent )) {
			pomXmlContent = new SaPomXml().generate( mavenBean );
		}

		return pomXmlContent;
	}


	/**
	 * Gets the POM content for a SU project.
	 * @param mavenBean a Maven bean
	 * @param componentName the component name (can be null)
	 * @return the POM content, as a string
	 */
	public String getSuPom( MavenBean mavenBean, String componentName ) {

		// Bug PETALSSTUD-110: make sure the root directory is an existing directory
		boolean useCustomTpl = PreferencesManager.useCustomizedTemplates()
		&& PreferencesManager.isMavenTemplateConfigurationValid();

		// Get the right template then
		String pomXmlContent = null;
		if( useCustomTpl ) {
			File templateFile = getPomTemplateFile( true, componentName );
			pomXmlContent = readTemplateFile( templateFile );
			pomXmlContent = updateTemplate( pomXmlContent, mavenBean );
		}

		if( StringUtils.isEmpty( pomXmlContent )) {
			pomXmlContent = new SuPomXml().generate( mavenBean );
		}

		return pomXmlContent;
	}


	/**
	 * Gets the right custom template file.
	 * <p>
	 * The root directory that contains the templates must exist and
	 * be a directory.
	 * </p>
	 * 
	 * @param su true to get a SU template, false for a SA
	 * @param componentName the component name (only for a SU, can be null)
	 * @return the associated POM template
	 */
	private File getPomTemplateFile( boolean su, String componentName ) {

		String loc = PreferencesManager.getCustomizedTemplatesLocation();
		File result = null;
		File rootDirectory = new File( loc );

		// SU
		if( su ) {
			if( componentName == null ) {
				result = new File( rootDirectory, DEFAULT_SU_POM );

			} else {
				FileFilter filter = new FileFilter() {
					public boolean accept( File pathname ) {
						return pathname.isFile();
					}
				};

				for( File f : rootDirectory.listFiles( filter )) {
					if( f.getName().startsWith( componentName )) {
						result = f;
						break;
					}
				}

				if( result == null )
					result = new File( rootDirectory, DEFAULT_SU_POM );
			}
		}

		// SA
		else {
			result = new File( rootDirectory, DEFAULT_SA_POM );
		}

		return result;
	}


	/**
	 * Reads a template file and loads it as a string.
	 * @param templateFile the template file (can be null or not exist)
	 * @return the file content as a string, or the empty string if it could not be read
	 */
	private String readTemplateFile( File templateFile ) {

		String result;
		if( templateFile == null
					|| ! templateFile.exists()) {
			result = "";

		} else {
			try {
				result = IoUtils.readFileContent( templateFile );

			} catch( IOException e ) {
				PetalsServicesPlugin.log( e, IStatus.ERROR );
				result = "";
			}
		}


		return result;
	}


	/**
	 * Fills-in a template.
	 * @param tplContent the template content
	 * @param mavenBean a Maven bean
	 * @return the final content
	 */
	private String updateTemplate( String tplContent, MavenBean mavenBean ) {

		// Parent condition
		boolean hideParent = StringUtils.isEmpty( mavenBean.getParentArtifactId())
		|| StringUtils.isEmpty( mavenBean.getParentGroupId())
		|| StringUtils.isEmpty( mavenBean.getParentVersion());;

		// We want to determine whether we should display the text between two mark-ups
		// <!-- ${PETALS_TestParent} -->
		Pattern conditionPattern =
			Pattern.compile( "<!--.*\\$\\{PETALS_TestParent\\}.*-->", Pattern.MULTILINE | Pattern.DOTALL );

		StringBuilder sb = new StringBuilder( tplContent );
		Matcher matcher;
		int start = -1, end = -1;
		int lengthStart = 0, lengthEnd = 0;
		while(( matcher = conditionPattern.matcher( sb.toString())).find()) {
			if( start == -1 ) {
				start = matcher.start();
				lengthStart = matcher.group().length();
			} else {
				lengthEnd = matcher.group().length();
				end = matcher.start() + lengthEnd;
			}

			if( end != -1 ) {
				if( hideParent ) {
					sb.replace( start, end, "" );
				} else {
					sb.replace( start, start + lengthStart, "" );
					sb.replace( end - lengthStart - lengthEnd, end - lengthStart, "" );
				}

				start = -1;
				end = -1;
			}
		}

		tplContent = sb.toString();


		// Simple fields
		tplContent = tplContent.replaceAll( "\\$\\{PETALS_ParentArtifactId\\}", mavenBean.getParentArtifactId());
		tplContent = tplContent.replaceAll( "\\$\\{PETALS_ParentGroupId\\}", mavenBean.getParentGroupId());
		tplContent = tplContent.replaceAll( "\\$\\{PETALS_ParentVersion\\}", mavenBean.getParentVersion());

		tplContent = tplContent.replaceAll( "\\$\\{PETALS_Name\\}", mavenBean.getName());
		tplContent = tplContent.replaceAll( "\\$\\{PETALS_ArtifactId\\}", mavenBean.getArtifactId());
		tplContent = tplContent.replaceAll( "\\$\\{PETALS_GroupId\\}", mavenBean.getGroupId());
		tplContent = tplContent.replaceAll( "\\$\\{PETALS_Version\\}", mavenBean.getVersion());
		tplContent = tplContent.replaceAll( "\\$\\{PETALS_Description\\}", mavenBean.getDescription());

		tplContent = tplContent.replaceAll( "\\$\\{PETALS_PetalsMavenPluginVersion\\}", mavenBean.getPetalsMavenPluginVersion());
		tplContent = tplContent.replaceAll( "\\$\\{PETALS_ComponentName\\}", mavenBean.getComponentName());
		tplContent = tplContent.replaceAll( "\\$\\{PETALS_ComponentVersion\\}", mavenBean.getComponentVersion());


		// Dependencies
		sb = new StringBuilder();
		for( Iterator<MavenBean> it = mavenBean.dependencies.iterator(); it.hasNext(); ) {
			MavenBean depBean = it.next();

			sb.append( "\t\t<dependency>\n" );
			sb.append( "\t\t\t<artifactId>" + depBean.getArtifactId() + "</artifactId>\n" );
			sb.append( "\t\t\t<groupId>" + depBean.getGroupId() + "</groupId>\n" );
			sb.append( "\t\t\t<version>" + depBean.getVersion() + "</version>\n" );
			sb.append( "\t\t\t<type>jbi-service-unit</type>\n" );
			sb.append( "\t\t</dependency>" );

			if( it.hasNext())
				sb.append( "\n" );
		}
		tplContent = tplContent.replaceAll( "\\$\\{PETALS_SuDependencies\\}", sb.toString());

		return tplContent;
	}
}
