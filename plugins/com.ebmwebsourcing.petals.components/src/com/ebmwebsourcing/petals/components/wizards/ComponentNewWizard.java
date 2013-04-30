/****************************************************************************
 *
 * Copyright (c) 2009-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.components.wizards;

import java.net.URI;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;

import com.ebmwebsourcing.petals.components.PetalsComponentsPlugin;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ComponentNewWizard extends AbstractArtifactNewWizard {

	private IProject project;
	private ComponentNewWizardPage page;


	/**
	 * Constructor.
	 */
	public ComponentNewWizard() {
		super();
		setNeedsProgressMonitor( true );

		setWindowTitle( "New JBI Component" );
		setDefaultPageImageDescriptor( PetalsComponentsPlugin.getImageDescriptor( "icons/wizban/wiz_component.png" ));
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		this.page = new ComponentNewWizardPage ();
		addPage( this.page );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.components.wizards.AbstractArtifactNewWizard
	 * #doFinish(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	void doFinish( IProgressMonitor monitor ) throws Exception {

		monitor.beginTask( "Creating " + this.page.getArtifactName() + "...", IProgressMonitor.UNKNOWN );

		// Create the project
		this.project = ResourcesPlugin.getWorkspace().getRoot().getProject( this.page.getArtifactName());
		URI projectLocationURI = this.page.getProjectLocationURI();
		if( projectLocationURI != null ) {
			IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription( this.page.getArtifactName());
			projectDescription.setLocationURI( projectLocationURI );
			this.project.create( projectDescription, monitor );
		}
		else {
			// ProjectLocation = null => create it in the workspace
			this.project.create( monitor );
		}

		// Copy the template files
		this.project.open( monitor );
		String type = this.page.isBc() ? "bc" : "se";
		String pkgDir = this.page.getRootPackage().replace( '.', '/' );

		switch( this.page.getPetalsContainer()) {
		case petals3_1:
			copyTemplate( "templates/component-3.1.x/readme.txt", this.project.getFile( "readme.txt" ), monitor );
			copyTemplate( "templates/component-3.1.x/pom.xml", this.project.getFile( "pom.xml" ), monitor );
			copyTemplate( "templates/component-3.1.x/jbi_" + type + ".xml", this.project.getFile( "src/main/jbi/jbi.xml" ), monitor );
			copyTemplate( "templates/component-3.1.x/JBIListener.java", this.project.getFile( "src/main/java/" + pkgDir + "/listeners/JBIListener.java" ), monitor );
			if( this.page.isBc())
				copyTemplate( "templates/component-3.1.x/ExternalListener.java", this.project.getFile( "src/main/java/" + pkgDir + "/listeners/ExternalListener.java" ), monitor );

			break;

		case petals4_x:
			copyTemplate( "templates/component-4.x/readme.txt", this.project.getFile( "readme.txt" ), monitor );
			copyTemplate( "templates/component-4.x/pom.xml", this.project.getFile( "pom.xml" ), monitor );
			copyTemplate( "templates/component-4.x/jbi_" + type + ".xml", this.project.getFile( "src/main/resources/jbi/jbi.xml" ), monitor );
			copyTemplate( "templates/component-4.x/JBIListener.java", this.project.getFile( "src/main/java/" + pkgDir + "/listeners/JBIListener.java" ), monitor );
			if( this.page.isBc())
				copyTemplate( "templates/component-4.x/ExternalListener.java", this.project.getFile( "src/main/java/" + pkgDir + "/listeners/ExternalListener.java" ), monitor );

			break;
		}

		monitor.worked( 20 );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.components.wizards.AbstractArtifactNewWizard
	 * #getProject()
	 */
	@Override
	IProject getProject() {

		if( this.project == null && this.page != null )
			this.project = ResourcesPlugin.getWorkspace().getRoot().getProject( this.page.getArtifactName());

		return this.project;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.components.wizards.AbstractArtifactNewWizard
	 * #replaceKeywords(java.lang.String)
	 */
	@Override
	String replaceKeywords( String string ) {

		return string.replace( "{NAME}", this.page.getArtifactName())
				.replace( "{ARTIFACT_ID}", this.page.getArtifactName())
				.replace( "{GROUP_ID}", this.page.getGroupId())
				.replace( "{ROOT_PACKAGE}", this.page.getRootPackage())
				.replace( "{DESCRIPTION}", this.page.getDescription())
				.replace( "{AUTHOR}", System.getProperty( "user.name" ));
	}
}
