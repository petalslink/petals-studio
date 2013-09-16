/******************************************************************************
 * Copyright (c) 2009-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.explorer.sources;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;

import com.ebmwebsourcing.petals.common.internal.provisional.emf.InvalidJbiXmlException;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.explorer.model.ServiceUnitBean;
import com.ebmwebsourcing.petals.services.su.jbiproperties.PetalsSPPropertiesManager;
import com.sun.java.xml.ns.jbi.Jbi;

/**
 * A source looking for end-points in an external workspace directory.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ExternalWorkspaceSource extends EndpointSource {

	private final File directory;


	/**
	 * Constructor.
	 * @param directory
	 */
	public ExternalWorkspaceSource( File directory ) {
		super( directory.getName(), directory.getAbsolutePath());
		this.directory = directory;
		this.description = "The Petals services defined an the external workspace (" + directory.getAbsolutePath() + ").";
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.explorer.sources.EndpointSource
	 * #dispose()
	 */
	@Override
	public void dispose() {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.explorer.sources.EndpointSource
	 * #refreshServiceUnits(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected Collection<ServiceUnitBean> refreshServiceUnits( IProgressMonitor monitor ) {

		if( monitor == null )
			monitor = new NullProgressMonitor();

		List<ServiceUnitBean> suBeans = new ArrayList<ServiceUnitBean>();
		for( File projectFile : this.directory.listFiles ()) {

			if( ! projectFile.isDirectory())
				continue;

			// Get the end-points
			for( File jbiXmlFile : getAllJbiXmlFile( projectFile )) {
				try {
					monitor.worked( 1 );
					Jbi jbi = JbiXmlUtils.getJbiXmlModel( jbiXmlFile );
					if( jbi.getServices() != null ) {

						ServiceUnitBean suBean = new ServiceUnitBean();
						suBean.setSource( this );
						suBean.setJbiXmlLocation( jbi.eResource().getURI().toFileString());
						suBean.setServiceUnitName( projectFile.getName());
						suBean.setComponentName( getComponentId( jbiXmlFile ));

						getEndpointBeans( jbi, suBean );
						if( suBean.getEndpoints().size() > 0 )
							suBeans.add( suBean );
					}

				} catch( InvalidJbiXmlException e1 ) {
					PetalsServicesPlugin.log( e1, IStatus.WARNING, "Invalid jbi.xml located at " + jbiXmlFile.getAbsolutePath());
				}
			}
		}

		return suBeans;
	}


	/**
	 * Gets (recursively) all the jbi.xml files under a given file.
	 * @param file the file to introspect (not included in the result)
	 * @return the list of found jbi.xml files
	 */
	private List<File> getAllJbiXmlFile( File file ) {

		List<File> result = new ArrayList<File> ();
		for( File f : file.listFiles()) {
			if( f.isFile() && f.getName().equals( "jbi.xml" ))
				result.add( f );
			else if( f.isDirectory() &&
					( ! f.isHidden() || f.getName().startsWith( "." )))
				result.addAll( getAllJbiXmlFile( f ));
		}

		return result;
	}


	/**
	 * Gets the target component's name for this jbi.xml file.
	 * @param jbiXmlFile a jbi.xml file
	 * @return the component name, or null if it could be found
	 */
	private String getComponentId( File jbiXmlFile ) {

		String result = null;
		if( "jbi".equals( jbiXmlFile.getParentFile().getName())) {
			File propertiesFile = new File(
					jbiXmlFile.getParentFile().getParentFile().getParentFile().getParentFile(),
					PetalsSPPropertiesManager.PROPERTIES_FILENAME );

			Properties prop = PetalsSPPropertiesManager.getProperties( propertiesFile );
			result = prop.getProperty( PetalsSPPropertiesManager.COMPONENT_DEPLOYMENT_ID );
		}

		return result;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.explorer.sources.EndpointSource
	 * #getWsdlContainerLocation(com.ebmwebsourcing.petals.services.explorer.model.ServiceUnitBean)
	 */
	@Override
	public String getWsdlContainerLocation( ServiceUnitBean suBean ) {

		String jbiXmlLocation = suBean.getJbiXmlLocation();
		if( jbiXmlLocation == null )
			return "";

		// Case simple project: the WSDL is in the resources folder
		File f = new File( jbiXmlLocation ).getParentFile();
		if( "META-INF".equals( f.getName()))
			return new File( f.getParentFile(), "resources" ).getAbsolutePath();

		// Case Maven project: the WSDL is in the same folder
		return f.getAbsolutePath();
	}


	/**
	 * @return the directory
	 */
	public File getDirectory() {
		return this.directory;
	}
}
