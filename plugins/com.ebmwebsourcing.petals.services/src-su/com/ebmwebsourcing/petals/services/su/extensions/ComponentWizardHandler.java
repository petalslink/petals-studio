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
package com.ebmwebsourcing.petals.services.su.extensions;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Jbi;

/**
 * A bean that holds information about a Petals component's version.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class ComponentWizardHandler {

	private boolean serviceProvider;
	public enum CustomPagePosition {
		beforeJbiPage, beforeProjectPage, afterProjectPage;
	}



	/**
	 * @return the component version's description
	 */
	public abstract ComponentVersionDescription getComponentVersionDescription();


	/**
	 * @return true if the wizard for this version should result in a Java project, false otherwise
	 */
	public boolean isJavaProject() {
		return false;
	}


	/**
	 * @return null if the wizard is enabled, false otherwise
	 */
	public String validatePrerequisites() {
		return null;
	}


	/**
	 * Registers custom pages to insert in the wizard at the given position.
	 * @param position a page position
	 * @param pagesToInsert a non-null, ordered list, to insert pages to register
	 */
	public void registerCustomWizardPages( CustomPagePosition position, List<AbstractSuPage> pagesToInsert ) {
		// nothing
	}


	/**
	 * Performs actions before the jbi.xml file is written.
	 * <p>
	 * A typical action is to import a remote file in the created project and to update the
	 * jbi.xml file in consequence to reference the imported file.
	 * </p>
	 *
	 * @param resourceDirectory the resource directory of the created project
	 * @param jbiInstance the model describing the content of the jbi.xml
	 * @param monitor a progress monitor
	 * @return a status indicating the result of the update
	 */
	public IStatus performActionsBeforeWrittingJbiXml( IFolder resourceDirectory, Jbi jbiInstance, IProgressMonitor monitor ) {
		return Status.OK_STATUS;
	}


	/**
	 * Perform additional actions after wizard completion.
	 * <p>
	 * That can be create or import new files in the created project.
	 * </p>
	 *
	 * @param resourceFolder the resource folder of the created project
	 * @param abstractEndpoint the main provides or consumes block
	 * @param resourcesToSelect a non-null list in which resources can be added for selection
	 * @param monitor the progress monitor
	 * @return a status indicating the result of the update
	 */
	public IStatus performLastActions( IFolder resourceFolder, AbstractEndpoint abstractEndpoint, IProgressMonitor monitor, List<Object> resourcesToSelect ) {
		return Status.OK_STATUS;
	}


	/**
	 * Returns wizard settings to override.
	 * <p>
	 * Wizard settings are defined in the class {@link SuWizardSettings}.
	 * </p>
	 */
	public Map<String,String> getOverridenWizardSettings() {
		return Collections.emptyMap();
	}


	/**
	 * Predefines values in the edited model object.
	 * @param abstractEndpoint the edited provides or consumes block
	 * @see #isServiceProvider()
	 */
	public void predefineJbiValues( AbstractEndpoint abstractEndpoint ) {
		// nothing
	}


	/**
	 * Creates the file and write its content.
	 * @param targetFile the target file
	 * @param content the content to write in the file
	 * @param monitor a progress monitor
	 */
	protected void createFile( IFile targetFile, String content, IProgressMonitor monitor ) {
		try {
			if( content == null )
				content = "Result was null. Make your code correct.";

			ByteArrayInputStream inputStream = new ByteArrayInputStream( content.getBytes());
			if( ! targetFile.exists())
				targetFile.create( inputStream, true, monitor );
			else
				targetFile.setContents( inputStream, true, true, monitor );

		} catch( Exception e ) {
			PetalsServicesPlugin.log( e, IStatus.ERROR );
		}
	}


	/**
	 * Helps to define whether this handler is used to generate a service provider or a service consumer.
	 * <p>
	 * <i>Proxies</i> (e.g. EIP) must be considered as service providers in this class.
	 * </p>
	 * @return the serviceProvider
	 */
	public boolean isServiceProvider() {
		return this.serviceProvider;
	}


	/**
	 * Defines whether this handler is used to generate a service provider or a service consumer.
	 * @param serviceProvider the serviceProvider to set
	 */
	public void setServiceProvider( boolean serviceProvider ) {
		this.serviceProvider = serviceProvider;
	}
}
