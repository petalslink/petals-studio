/****************************************************************************
 * 
 * Copyright (c) 2009-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.components.wizards;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.Arrays;
import java.util.UUID;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.eclipse.ui.ide.IDE;

import com.ebmwebsourcing.petals.common.internal.provisional.maven.MavenUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.IoUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.components.PetalsComponentsPlugin;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsArtifactNewWizard extends Wizard implements INewWizard, IExecutableExtension {

	private static final String LOCK = "lock";

	// Reporting (console)
	private MessageConsoleStream prefixStream, mavenStream;
	private StringBuffer buffer;

	// Others
	private boolean isComponentWizard;
	private IProject project;
	private AbstractMavenArtifactPage page;
	private final File shellDirectory = new File(
				System.getProperty( "java.io.tmpdir" ),
				UUID.randomUUID().toString());



	/**
	 * Determines whether it is a component or shared library wizard.
	 */
	public void setInitializationData( IConfigurationElement config, String propertyName, Object data )
	throws CoreException {

		if( data != null && data instanceof String )
			this.isComponentWizard = "component".equalsIgnoreCase((String) data);

		if( this.isComponentWizard ) {
			setWindowTitle( "New JBI Component" );
			setDefaultPageImageDescriptor( PetalsComponentsPlugin.getImageDescriptor( "icons/wizban/wiz_component.png" ));
			this.page = new PetalsComponentNewWizardPage ();

		} else {
			setWindowTitle( "New Shared Library" );
			setDefaultPageImageDescriptor( PetalsComponentsPlugin.getImageDescriptor( "icons/wizban/wiz_shared_library.png" ));
			this.page = new PetalsSharedLibraryNewWizardPage ();
		}
	}


	/**
	 * Constructor.
	 */
	public PetalsArtifactNewWizard() {
		super();
		setNeedsProgressMonitor( true );
		if( ! this.shellDirectory.mkdir())
			PetalsComponentsPlugin.log( "Could not create the temporary folder for the script.", IStatus.ERROR );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		addPage( this.page );
	}


	/**
	 * This method is called when 'Finish' button is pressed in
	 * the wizard. We will create an operation and run it
	 * using wizard as execution context.
	 */
	@Override
	public boolean performFinish() {

		// Create the M2_REPO variable if it was not defined
		if( JavaCore.getClasspathVariable( "M2_REPO" ) == null ) {
			String mavenHome = MavenUtils.findLocalMavenRepository().toOSString();
			try {
				JavaCore.setClasspathVariable( "M2_REPO",  new Path( mavenHome ), null );

			} catch( JavaModelException e ) {
				PetalsComponentsPlugin.log( e, IStatus.ERROR, "Could not create the M2_REPO variable" );
			}
		}

		// Display the Maven output message in an Eclipse console
		MessageConsole console = new MessageConsole( "Petals console", null ); //$NON-NLS-1$
		console.activate();
		ConsolePlugin.getDefault().getConsoleManager().addConsoles( new IConsole[]{ console });

		this.prefixStream = console.newMessageStream();
		this.prefixStream.setColor( getShell().getDisplay().getSystemColor( SWT.COLOR_GRAY ));
		this.prefixStream.setActivateOnWrite( true );

		this.mavenStream = console.newMessageStream();
		this.mavenStream.setColor( getShell().getDisplay().getSystemColor( SWT.COLOR_BLUE ));
		this.mavenStream.setActivateOnWrite( true );

		// Generate the creation script
		WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
			@Override
			protected void execute( IProgressMonitor monitor )
			throws CoreException, InvocationTargetException, InterruptedException {

				try {
					doFinish( monitor );
					IoUtils.deleteFilesRecursively( PetalsArtifactNewWizard.this.shellDirectory );

				} catch( Exception e ) {
					throw new InvocationTargetException( e );

				} finally {
					monitor.done();
				}
			}
		};

		try {
			// Create the project
			getContainer().run( true, false, op );

			// Select the jbi.xml in the explorer
			final IFile jbiXmlFile = this.project.getFile( PetalsConstants.LOC_JBI_FILE );
			IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			try {
				IDE.openEditor( activePage, jbiXmlFile, true );
				ResourceUtils.selectResourceInPetalsExplorer( true, jbiXmlFile );

			} catch( PartInitException e ) {
				// nothing
			}

		} catch( InterruptedException e ) {
			return false;

		} catch( InvocationTargetException e ) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError( getShell(), "Error", realException.getMessage());
			return false;
		}

		return true;
	}


	/**
	 * Creates the Petals component by calling Maven through system commands.
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws MavenEmbedderException
	 */
	private void doFinish( IProgressMonitor monitor ) throws CoreException, IOException, InterruptedException {

		monitor.beginTask( "Creating " + this.page.getArtifactName() + "...", IProgressMonitor.UNKNOWN );

		// Create the component
		StringBuilder mavenCommand = new StringBuilder();
		mavenCommand.append( "mvn archetype:generate " );
		mavenCommand.append( "-B " );	// Not interactive
		mavenCommand.append( "-DarchetypeGroupId=org.ow2.petals " );
		mavenCommand.append( "-DarchetypeArtifactId=" + this.page.getArchetypeId() + " " );
		mavenCommand.append( "-DarchetypeVersion=" + this.page.getArchetypeVersion() + " " );
		mavenCommand.append( "-DgroupId=" + this.page.getGroupId() + " " );
		mavenCommand.append( "-DartifactId=" + this.page.getArtifactName() + " " );
		mavenCommand.append( "-Dversion=" + this.page.getArtifactVersion());

		URI projectLocationURI = this.page.getProjectLocationURI();
		File dir;
		if( projectLocationURI == null )
			dir = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile();
		else
			dir = new File( projectLocationURI ).getParentFile();

		// Launching the script execution
		String[] cmd = getLaunchCommandLine( getScriptContent( mavenCommand.toString(), dir ));
		ProcessBuilder pb = new ProcessBuilder( Arrays.asList( cmd ));
		pb = pb.directory( dir );
		pb = pb.redirectErrorStream( true );

		String javaHome = System.getProperty( "java.home" );
		pb.environment().put( "JAVA_HOME", javaHome );
		final Process creationProcess = pb.start();


		// Get a printer thread that can run within the UI thread
		final Runnable printerThread = new Runnable() {
			public void run() {
				String text = PetalsArtifactNewWizard.this.buffer.toString().trim();
				if( text.length() > 0 && ! text.matches( "(\\d)+ KB" )) {
					PetalsArtifactNewWizard.this.prefixStream.print( "[ Maven output ] " );
					PetalsArtifactNewWizard.this.mavenStream.println( text );
				}
			}
		};

		Thread reportThread = new Thread() {
			@Override
			public void run() {
				try {
					BufferedReader reader = new BufferedReader( new InputStreamReader( creationProcess.getInputStream()));
					String line = "";
					try {
						while((line = reader.readLine()) != null) {
							PetalsArtifactNewWizard.this.buffer = new StringBuffer( line );
							getShell().getDisplay().syncExec( printerThread );
						}
					} finally {
						reader.close();
					}

				} catch( IOException ioe ) {
					ioe.printStackTrace();
				}
			}
		};
		reportThread.start();


		// Wait for the script to terminate - polling the poll file (will be deleted by the script)
		Thread pollingThread = new Thread() {
			@Override
			public void run() {
				File lockFile = new File( PetalsArtifactNewWizard.this.shellDirectory, LOCK );
				while( lockFile.exists()) {
					try {
						Thread.sleep( 1500 );
					} catch( InterruptedException e ) {
						e.printStackTrace();
					}
				}
			}
		};
		pollingThread.start();
		pollingThread.join();
		creationProcess.destroy();


		// Import the project into Eclipse
		monitor.worked( 33 );
		monitor.subTask( "Importing the project in the workspace..." );

		this.project = ResourcesPlugin.getWorkspace().getRoot().getProject( this.page.getArtifactName());
		if( projectLocationURI != null ) {
			IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription( this.page.getArtifactName());
			projectDescription.setLocationURI( projectLocationURI );
			this.project.create( projectDescription, monitor );
		}
		else {
			// ProjectLocation = null => create it in the workspace
			this.project.create( monitor );
		}

		this.project.open( monitor );
		monitor.worked( 20 );
	}


	/**
	 * Creates the script to execute and returns the command to execute it.
	 * @param scriptContent the script content
	 * @return the command to execute the script, based on the Operating System name
	 * @throws IOException
	 */
	private String[] getLaunchCommandLine( String scriptContent ) throws IOException {

		String[] args = new String[ 3 ];
		String extension = ".bat";

		// Get the command arguments
		String osName = System.getProperty( "os.name" ).toLowerCase();
		if( osName.indexOf( "windows 9" ) > -1 ) {
			args[ 0 ] = "command.com";
			args[ 1 ] = "/c";
		}
		else if ( osName.indexOf( "nt" ) > -1
					|| osName.indexOf( "win" ) > -1 ) {
			args[ 0 ] = "cmd.exe";
			args[ 1 ] = "/k";
		}
		else {
			args[ 0 ] = "/bin/bash";
			args[ 1 ] = "-c";
			extension = ".sh";
		}

		// Create the file
		File scriptFile = new File( this.shellDirectory, "generatedScriptForMaven_" + extension );
		if( ! scriptFile.exists() && ! scriptFile.createNewFile())
			throw new IOException( "Could not create " + scriptFile.getAbsolutePath() + "." );

		File lockFile = new File( this.shellDirectory, LOCK );
		if( ! lockFile.createNewFile())
			throw new IOException( "Could not create " + lockFile.getAbsolutePath() + "." );

		IoUtils.copyStream(
					new ByteArrayInputStream( scriptContent.getBytes()),
					scriptFile );

		scriptFile.setExecutable( true, false );
		scriptFile.deleteOnExit();
		args[ 2 ] = scriptFile.getAbsolutePath();

		return args;
	}


	/**
	 * Creates the script content.
	 * @param mavenCommand
	 * @param directory
	 * @return the script content
	 * @throws IOException
	 */
	private String getScriptContent( String mavenCommand, File directory ) throws IOException {

		// Get the command arguments
		boolean win = false;
		String osName = System.getProperty( "os.name" ).toLowerCase();
		if( osName.indexOf( "windows 9" ) > -1 ) {
			win = true;
		}
		else if ( osName.indexOf( "nt" ) > -1
					|| osName.indexOf( "win" ) > -1 ) {
			win = true;
		}

		StringBuilder scriptBuilder = new StringBuilder();
		if( win ) {
			String driveLetter = null;
			File aux = directory;
			while(( aux = aux.getParentFile()) != null ) {
				driveLetter = aux.getPath();
			}

			scriptBuilder.append( driveLetter.replace( '\\', '\n' )) ;
			scriptBuilder.append( "cd " + directory.getAbsolutePath() + "\n" );
			scriptBuilder.append( "call " + mavenCommand + "\n" );
			scriptBuilder.append( "cd " + new File( directory, this.page.getArtifactName()).getAbsolutePath() + "\n" );
			scriptBuilder.append( "call mvn eclipse:eclipse\n" );

			driveLetter = null;
			aux = this.shellDirectory;
			while(( aux = aux.getParentFile()) != null ) {
				driveLetter = aux.getPath();
			}

			scriptBuilder.append( driveLetter.replace( '\\', '\n' )) ;
			scriptBuilder.append( "cd " + this.shellDirectory.getAbsolutePath() + "\n" );
			scriptBuilder.append( "erase " + LOCK  );
		}

		else {
			scriptBuilder.append( "cd " + directory.getAbsolutePath() + "\n" );
			scriptBuilder.append( mavenCommand + "\n" );
			scriptBuilder.append( "cd " + new File( directory, this.page.getArtifactName()).getAbsolutePath() + "\n" );
			scriptBuilder.append( "mvn eclipse:eclipse\n" );
			scriptBuilder.append( "cd " + this.shellDirectory.getAbsolutePath() + "\n" );
			scriptBuilder.append( "rm " + LOCK  );
		}

		return scriptBuilder.toString();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard
	 * #init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init( IWorkbench workbench, IStructuredSelection selection ) {
		// nothing
	}
}
