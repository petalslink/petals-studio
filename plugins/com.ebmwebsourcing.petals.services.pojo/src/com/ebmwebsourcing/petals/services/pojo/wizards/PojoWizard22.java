/******************************************************************************
 * Copyright (c) 2011-2012, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.services.pojo.wizards;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.CollectionUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JavaUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.cdk.Cdk5Utils;
import com.ebmwebsourcing.petals.services.pojo.PetalsPojoPlugin;
import com.ebmwebsourcing.petals.services.pojo.PojoDescription22;
import com.ebmwebsourcing.petals.services.pojo.pojo.PojoPackage;
import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuWizardPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 * @author Mickaël Istria - EBM WebSourcing
 */
public class PojoWizard22 extends AbstractServiceUnitWizard {

	private PojoProvideSpecificPage22 page;


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #getComponentVersionDescription()
	 */
	@Override
	public ComponentVersionDescription getComponentVersionDescription() {
		return new PojoDescription22();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.extensions.ComponentWizardHandler
	 * #performLastActions(org.eclipse.core.resources.IFolder, com.sun.java.xml.ns.jbi.AbstractEndpoint,
	 * org.eclipse.core.runtime.IProgressMonitor, java.util.List)
	 */
	@Override
	public IStatus performLastActions(IFolder resourceFolder, AbstractEndpoint abstractEndpoint, IProgressMonitor monitor) {

		abstractEndpoint.eSet(Cdk5Package.Literals.CDK5_PROVIDES__WSDL, this.finalWsdlFileLocation );
		IStatus result = Status.OK_STATUS;

		// Create a sample POJO
		if( ! this.page.isUseExistingImplementation()) {

			try {

				// Generate the sample class
				IJavaProject javaProject = JavaUtils.createJavaProject( resourceFolder.getProject());
				IFolder srcFolder = resourceFolder.getProject().getFolder(PetalsConstants.LOC_SRC_FOLDER);
				IFolder targetFolder = srcFolder.getFolder("com/ebmwebsourcing/formation");
				if( ! targetFolder.exists()
						&& ! targetFolder.getLocation().toFile().mkdirs())
					throw new IOException( "Could not create packages for a POJO project." );

				srcFolder.refreshLocal(IResource.DEPTH_INFINITE, monitor);
				IFile file = targetFolder.getFile("MyFirstPojo.java");
				if( ! file.exists())
					file.create( getClass().getResourceAsStream("MyFirstPojo.java_"), true, monitor);

				abstractEndpoint.eSet(PojoPackage.Literals.POJO_PROVIDES__CLASS_NAME, "com.ebmwebsourcing.formation.MyFirstPojo");


				// Find the libraries to add in the project class path
				File pojoLibPath = ResourceUtils.getPluginBinaryPath( "com.ebmwebsourcing.petals.libs.pojo22", "lib" ); //$NON-NLS-1$
				if( pojoLibPath == null ) {
					PetalsPojoPlugin.log( "Could not find the POJO libraries in the distribution.", IStatus.ERROR );
					return new Status( IStatus.ERROR, PetalsPojoPlugin.PLUGIN_ID, "The POJO libraries could not be located." );
				}

				File[] jarFiles = pojoLibPath.listFiles( new FilenameFilter() {
					@Override
					public boolean accept( File dir, String name ) {
						return name.endsWith( ".jar" ) || name.endsWith( ".zip" );
					}
				});


				// Add the libraries in the project class path
				ArrayList<IClasspathEntry> entries = new ArrayList<IClasspathEntry> ();
				entries.addAll( Arrays.asList( javaProject.getRawClasspath()));
				if( jarFiles != null ) {
					for( File jarFile : jarFiles ) {
						IPath path = new Path( jarFile.getAbsolutePath());
						IClasspathEntry entry = JavaCore.newLibraryEntry( path, null, null );
						entries.add( entry );
					}
				}

				IClasspathEntry[] newEntries = CollectionUtils.convertToArray( entries, IClasspathEntry.class );
				if( ! javaProject.hasClasspathCycle( newEntries ))
					javaProject.setRawClasspath( newEntries, monitor );

			} catch( CoreException e ) {
				PetalsPojoPlugin.log( e, IStatus.ERROR );

			} catch( IOException e ) {
				PetalsPojoPlugin.log( e, IStatus.ERROR );
			}
		}


		// Start with an existing implementation
		else {
			abstractEndpoint.eSet(PojoPackage.Literals.POJO_PROVIDES__CLASS_NAME, this.page.getClassName());
			for( File jarFile : this.page.getJarFiles()) {
				try {
					IoUtils.copyFile(jarFile, new java.io.File(resourceFolder.getLocation().toFile(), jarFile.getName()), false);

				} catch (Exception ex) {
					PetalsPojoPlugin.log(ex, IStatus.ERROR);
				}
			}

		}

		return result;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #presetServiceValues(com.sun.java.xml.ns.jbi.AbstractEndpoint)
	 */
	@Override
	protected void presetServiceValues(AbstractEndpoint endpoint) {
		Cdk5Utils.setInitialProvidesValues((Provides)endpoint);
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #getCustomWizardPagesAfterJbi()
	 */
	@Override
	protected AbstractSuWizardPage[] getLastCustomWizardPages() {
		this.page = new PojoProvideSpecificPage22();
		return new AbstractSuWizardPage[] { this.page };
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard
	 * #isJavaProject()
	 */
	@Override
	protected boolean isJavaProject() {
		return ! this.page.isUseExistingImplementation();
	}
}
