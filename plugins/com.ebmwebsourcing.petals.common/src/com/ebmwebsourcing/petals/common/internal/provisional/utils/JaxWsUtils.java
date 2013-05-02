/****************************************************************************
 *
 * Copyright (c) 2010-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IAnnotation;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMemberValuePair;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.core.search.SearchParticipant;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.SearchRequestor;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.IVMInstallType;
import org.eclipse.jdt.launching.JavaRuntime;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.provisional.preferences.PreferencesManager;

/**
 * Utility methods related to JAX-WS.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class JaxWsUtils {

	/**
	 * A file to store the generated classes.
	 */
	private static final File TEMP_FILE =
		new File( System.getProperty( "java.io.tmpdir" ), "Petals-JAX-WS" );

	/**
	 * The unique instance of this class.
	 */
	public static final JaxWsUtils INSTANCE = new JaxWsUtils();


	/**
	 * Constructor.
	 */
	private JaxWsUtils() {
		// nothing
	}


	/**
	 * Gets a JAX-WS executable, that comes with a JDK 6.
	 * <p>
	 * This method can be used to check that there is an available JDK 6
	 * that will be used by the tooling.
	 * </p>
	 *
	 * @param wsGen true to get WsGen, false to get WsImport
	 * @return the required executable file
	 * @throws IOException if the executable was not found
	 */
	public static File getJavaExecutable( boolean wsGen ) throws IOException {

		final String winExecName;
		final String linuxExecName;
		if( wsGen ) {
			winExecName = "bin/wsgen.exe";
			linuxExecName = "bin/wsgen";
		}
		else {
			winExecName = "bin/wsimport.exe";
			linuxExecName = "bin/wsimport";
		}

		File result = null;
		List<String> locations = new ArrayList<String> ();


		// Add all the locations where to search
		// Use the environment variables
		String javaHome = System.getProperty( "java.home" );
		if( javaHome != null )
			locations.add( javaHome );

		javaHome = System.getenv( "JAVA_HOME" );
		if( javaHome != null )
			locations.add( javaHome );

		// Use the registered VM
		IVMInstallType[] vmInstallTypes = JavaRuntime.getVMInstallTypes();
		for( IVMInstallType vmInstallType : vmInstallTypes ) {
			IVMInstall[] vmInstalls = vmInstallType.getVMInstalls();
			for( IVMInstall vmInstall : vmInstalls )
				locations.add( vmInstall.getInstallLocation().getAbsolutePath());
		}


		// Search the executables in these locations
		for( String location : locations ) {
			File executable;
			if(( executable = new File( location, winExecName )).exists())
				result = executable;
			else if(( executable = new File( location, linuxExecName )).exists())
				result = executable;

			if( result != null )
				break;
		}

		if( result == null ) {
			if( wsGen )
				throw new IOException( "Wsgen could not be found. Make sure you have a JDK 6 or higher." );
			else
				throw new IOException( "Wsimport could not be found. Make sure you have a JDK 6 or higher." );
		}

		return result;
	}


	/**
	 * Generates a WSDL from a JAX-WS annotated class.
	 * <p>
	 * If an error occurs during the execution, it will appear in the returned
	 * string builder.
	 * </p>
	 *
	 * @param className the name of the annotated class
	 * @param targetDirectory the target directory
	 * @param jp the Java project that contains this class
	 * @return a string builder containing the execution details
	 * @throws IOException if WSgen was not found, or if the process could not be started
	 * @throws InterruptedException
	 * @throws JaxWsException if the generation seems to have failed
	 */
	public StringBuilder generateWsdl( String className, File targetDirectory, IJavaProject jp )
	throws IOException, InterruptedException, JaxWsException {

		// Create the temporary file
		synchronized( TEMP_FILE ) {
			if( ! TEMP_FILE.exists()
						&& ! TEMP_FILE.mkdir())
				throw new IOException( "The class directories could not be created." );
		}

		final StringBuilder outputBuffer = new StringBuilder();
		int exitValue = 0;
		try {
			File executable = getJavaExecutable( true );

			// Build the class path
			StringBuilder classpath = new StringBuilder();
			String separator = System.getProperty( "path.separator" );
			for( String entry : JavaUtils.getClasspath( jp, true, true ))
				classpath.append( entry + separator );

			IPath binPath = null;
			try {
				binPath = jp.getOutputLocation();
			} catch( JavaModelException e ) {
				PetalsCommonPlugin.log( e, IStatus.ERROR );
			}

			if( binPath != null ) {
				binPath = ResourcesPlugin.getWorkspace().getRoot().getLocation().append( binPath );
				File binFile = binPath.toFile();
				if( ! binFile.exists() && ! binFile.mkdir())
					PetalsCommonPlugin.log( "The 'bin' directory could not be created.", IStatus.WARNING );
			}

			// Build the command line
			List<String> cmd = new ArrayList<String>();
			cmd.add( executable.getAbsolutePath());
			cmd.add( "-verbose" );
			cmd.add( "-wsdl" );
			cmd.add( "-classpath" );
			cmd.add( classpath.toString());
			cmd.add( "-r" );
			cmd.add( targetDirectory.getAbsolutePath());
			cmd.add( "-d" );
			cmd.add( TEMP_FILE.getAbsolutePath());
			cmd.add( "-s" );
			cmd.add( TEMP_FILE.getAbsolutePath());
			cmd.add( className );

			// Create the process
			ProcessBuilder pb = new ProcessBuilder( cmd );
			pb = pb.redirectErrorStream( true );
			final Process process = pb.start();

			// Monitor the execution
			Thread loggingThread = new Thread() {
				@Override
				public void run() {
					try {
						BufferedReader reader = new BufferedReader( new InputStreamReader( process.getInputStream()));
						String line = "";
						try {
							while((line = reader.readLine()) != null )
								outputBuffer.append( line + "\n" );
						} finally {
							reader.close();
						}

					} catch( IOException ioe ) {
						ioe.printStackTrace();
					}
				}
			};
			loggingThread.start();
			loggingThread.join();
			exitValue = process.waitFor();

		} finally {

			// Free the temporary directory
			synchronized( TEMP_FILE ) {
				IoUtils.deleteFilesRecursively( TEMP_FILE.listFiles());
			}

			// Keep a trace of the generation
			JaxWsException loggingException;
			if( exitValue != 0 ) {
				loggingException = new JaxWsException( "The JAX-WS process did not return 0. An error may have occurred." ) {
					private static final long serialVersionUID = -8585870927871804985L;

					@Override
					public void printStackTrace( PrintStream s ) {
						s.print( outputBuffer.toString());
					}

					@Override
					public void printStackTrace( PrintWriter s ) {
						s.print( outputBuffer.toString());
					}
				};

				throw loggingException;

			} else if( PreferencesManager.logAllJaxWsTraces()) {
				loggingException = new JaxWsException( "Logging the WSDL generation trace (" + jp.getProject().getName() + ")." ) {
					private static final long serialVersionUID = -5344307338271840633L;

					@Override
					public void printStackTrace( PrintStream s ) {
						s.print( outputBuffer.toString());
					}

					@Override
					public void printStackTrace( PrintWriter s ) {
						s.print( outputBuffer.toString());
					}
				};

				PetalsCommonPlugin.log( loggingException, IStatus.INFO );
			}
		}

		return outputBuffer;
	}


	/**
	 * Generates a JAX-WS annotated client from a WSDL file.
	 * <p>
	 * If an error occurs during the execution, it will appear in the returned
	 * string builder.
	 * </p>
	 *
	 * @param wsdlUri the WSDL's URI
	 * @param targetDirectory the target directory
	 * @return a string builder containing the execution details
	 * @throws IOException if WSgen was not found, or if the process could not be started
	 * @throws InterruptedException
	 * @throws JaxWsException if the generation seems to have failed
	 */
	public StringBuilder generateWsClient( URI wsdlUri, File targetDirectory )
	throws IOException, InterruptedException, JaxWsException {

		// Create the temporary file
		synchronized( TEMP_FILE ) {
			if( ! TEMP_FILE.exists()
						&& ! TEMP_FILE.mkdir())
				throw new IOException( "The class directories could not be created." );
		}

		final StringBuilder outputBuffer = new StringBuilder();
		int exitValue = 0;
		try {
			File executable = getJavaExecutable( false );

			// Build the command line
			List<String> cmd = new ArrayList<String>();
			cmd.add( executable.getAbsolutePath());
			cmd.add( "-verbose" );
			cmd.add( "-s" );
			cmd.add( targetDirectory.getAbsolutePath());
			cmd.add( "-d" );
			cmd.add( TEMP_FILE.getAbsolutePath());
			cmd.add( wsdlUri.toString());

			// Create the process
			ProcessBuilder pb = new ProcessBuilder( cmd );
			pb = pb.redirectErrorStream( true );
			final Process process = pb.start();

			// Monitor the execution
			Thread loggingThread = new Thread() {
				@Override
				public void run() {
					try {
						BufferedReader reader = new BufferedReader( new InputStreamReader( process.getInputStream()));
						String line = "";
						try {
							while((line = reader.readLine()) != null )
								outputBuffer.append( line + "\n" );
						} finally {
							reader.close();
						}

					} catch( IOException ioe ) {
						ioe.printStackTrace();
					}
				}
			};
			loggingThread.start();
			loggingThread.join();
			exitValue = process.waitFor();

		} finally {

			// Free the temporary directory
			synchronized( TEMP_FILE ) {
				IoUtils.deleteFilesRecursively( TEMP_FILE.listFiles());
			}

			// Keep a trace of the generation
			JaxWsException loggingException;
			if( exitValue != 0 ) {
				loggingException = new JaxWsException( "The JAX-WS process did not return 0. An error may have occurred." ) {
					private static final long serialVersionUID = -8585870927871804985L;

					@Override
					public void printStackTrace( PrintStream s ) {
						s.print( outputBuffer.toString());
					}

					@Override
					public void printStackTrace( PrintWriter s ) {
						s.print( outputBuffer.toString());
					}
				};

				throw loggingException;

			} else if( PreferencesManager.logAllJaxWsTraces()) {
				loggingException = new JaxWsException( "Logging the WSDL import trace (" + wsdlUri + ")." ) {
					private static final long serialVersionUID = -5344307338271840633L;

					@Override
					public void printStackTrace( PrintStream s ) {
						s.print( outputBuffer.toString());
					}

					@Override
					public void printStackTrace( PrintWriter s ) {
						s.print( outputBuffer.toString());
					}
				};

				PetalsCommonPlugin.log( loggingException, IStatus.INFO );
			}
		}

		return outputBuffer;
	}


	/**
	 * The @WebService annotation (javax.jws.WebService").
	 */
	private static final String WEB_WS_ANNOTATION = "javax.jws.WebService";

	/**
	 * The shorten @WebService annotation (WebService).
	 */
	private static final String SHORT_WEB_WS_ANNOTATION = "WebService";

	/**
	 * The @WebServiceClient annotation (javax.jws.WebServiceClient").
	 */
	private static final String WEB_WS_CLIENT_ANNOTATION = "javax.xml.ws.WebServiceClient";


	/**
	 * Searches all the Java types from a Java project that are annotated with @WebService.
	 *
	 * @param jp the containing Java project
	 * @param monitor the progress monitor
	 * @param includeClasses true to include classes in the search results
	 * @param includeInterfaces true to include the interfaces in the search results
	 * @return a Map
	 * <p>
	 * Key = the qualified name of the annotated type<br />
	 * Value = the associated service name (in the target WSDL)
	 * </p>
	 *
	 * @throws CoreException if the search could not be launched
	 */
	public static Map<String,String> getJaxAnnotatedJavaTypes(
				IJavaProject jp,
				IProgressMonitor monitor,
				final boolean includeClasses,
				final boolean includeInterfaces
	) throws CoreException {

		jp.getProject().refreshLocal( IResource.DEPTH_INFINITE, monitor );
		jp.getProject().build( IncrementalProjectBuilder.FULL_BUILD, monitor );

		final Map<String,String> classNameToServiceName = new HashMap<String,String> ();
		SearchPattern pattern = SearchPattern.createPattern(
					WEB_WS_ANNOTATION,
					IJavaSearchConstants.ANNOTATION_TYPE,
					IJavaSearchConstants.ANNOTATION_TYPE_REFERENCE,
					SearchPattern.R_EXACT_MATCH | SearchPattern.R_CASE_SENSITIVE );

		// This is what we do when we find a match
		SearchRequestor requestor = new SearchRequestor() {
			@Override
			public void acceptSearchMatch( SearchMatch match ) throws CoreException {

				// We get the Java type that is annotated with @WebService
				if( match.getElement() instanceof IType ) {

					// Find the annotation
					IType type = (IType) match.getElement();
					if(type.isInterface() && includeInterfaces
								|| type.isClass() && includeClasses) {

						IAnnotation ann = type.getAnnotation( WEB_WS_ANNOTATION );
						if( ! ann.exists())
							ann = type.getAnnotation( SHORT_WEB_WS_ANNOTATION );

						// Get the service name and store it
						if( ann.exists()) {
							String serviceName = null;
							for( IMemberValuePair pair : ann.getMemberValuePairs()) {
								if( "serviceName".equalsIgnoreCase( pair.getMemberName())) {
									serviceName = (String) pair.getValue();
									break;
								}
							}

							if( serviceName == null )
								serviceName = type.getElementName() + "Service";

							classNameToServiceName.put( type.getFullyQualifiedName(), serviceName );
						}
					}
				}
			}
		};

		new SearchEngine().search(
					pattern,
					new SearchParticipant[] { SearchEngine.getDefaultSearchParticipant()},
					SearchEngine.createJavaSearchScope( new IJavaElement[] { jp }, false ),
					requestor,
					monitor );

		return classNameToServiceName;
	}


	/**
	 * Deletes all the classes annotated with @WebServiceClient.
	 *
	 * @param jp the containing Java project
	 * @param monitor the progress monitor
	 * @throws CoreException if the classes annotated with @WebServiceClient could not be listed
	 */
	public static void removeWebServiceClient( IJavaProject jp, final IProgressMonitor monitor )
	throws CoreException {

		SearchPattern pattern = SearchPattern.createPattern(
					WEB_WS_CLIENT_ANNOTATION,
					IJavaSearchConstants.ANNOTATION_TYPE,
					IJavaSearchConstants.ANNOTATION_TYPE_REFERENCE,
					SearchPattern.R_EXACT_MATCH | SearchPattern.R_CASE_SENSITIVE );

		// This is what we do when we find a match
		SearchRequestor requestor = new SearchRequestor() {
			@Override
			public void acceptSearchMatch( SearchMatch match ) throws CoreException {

				// We get the Java type that is annotated with @WebService
				if( match.getElement() instanceof IType ) {
					IType type = (IType) match.getElement();
					ICompilationUnit cu = type.getCompilationUnit();
					if( cu != null
								&& cu.getCorrespondingResource() != null )
						cu.getCorrespondingResource().delete( true, monitor );
					else
						PetalsCommonPlugin.log( "A WS client could not be deleted (no compilation unit).", IStatus.ERROR );
				}
			}
		};

		new SearchEngine().search(
					pattern,
					new SearchParticipant[] { SearchEngine.getDefaultSearchParticipant()},
					SearchEngine.createJavaSearchScope( new IJavaElement[] { jp }, false ),
					requestor,
					monitor );
	}


	/**
	 * Creates JAX-WS implementations from annotated interfaces contained in a Java project.
	 * <p>
	 * For very interface annotated with @WebService, a default implementing class
	 * is created and replaces the interface.
	 * </p>
	 *
	 * @param jp the containing Java project
	 * @param monitor the progress monitor
	 * @return a map associating a WSDL service name with a JAX-WS class
	 * <p>
	 * Key = a WSDL service name<br />
	 * Value = the JAX-WS class
	 * </p>
	 *
	 * @throws CoreException if the annotated interfaces could not be listed
	 */
	public static Map<String,String> createJaxWsImplementation( IJavaProject jp, final IProgressMonitor monitor )
	throws CoreException {

		Map<String,String> serviceNameToImplName = new HashMap<String,String> ();
		Map<String,String> classNameToServiceName = getJaxAnnotatedJavaTypes( jp, monitor, false, true );
		for( Map.Entry<String,String> entry : classNameToServiceName.entrySet()) {

			// Get the class source
			String className = entry.getKey();
			IType type = jp.findType( className );
			if( type == null
						|| type.getCompilationUnit() == null )
				continue;

			// Get the interface's simple name
			int index = className.lastIndexOf( '.' );
			String simpleName;
			if( index == -1 )
				simpleName = className;
			else
				simpleName = className.substring( index + 1 );

			String implName = simpleName.replaceFirst( "PortType$", "" );
			if( implName.equals( simpleName ))
				implName = simpleName + "Impl";

			serviceNameToImplName.put( entry.getValue(), className.replaceFirst( simpleName + "$", implName ));
			StringBuffer sourceBuffer = replaceInterfaceDeclarationByImpl( type.getCompilationUnit().getSource(), simpleName, implName );
			String source = replaceInterfaceMethodsByImpl( sourceBuffer );

			// Create the class file and set its content
			ICompilationUnit cu = type.getCompilationUnit();
			if( cu != null
						&& cu.getCorrespondingResource() != null ) {
				IFile implFile = cu.getCorrespondingResource().getParent().getFile( new Path( implName + ".java" ));

				// Normally, the file should exist and be the interface's one
				if( ! implFile.exists())
					implFile.create( new ByteArrayInputStream( source.getBytes()), true, monitor );
				else
					implFile.setContents( new ByteArrayInputStream( source.getBytes()), true, true, monitor );
			}
			else
				PetalsCommonPlugin.log( "The JAX-WS implementation could not be created (no compilation unit).", IStatus.ERROR );
		}

		return serviceNameToImplName;
	}


	/**
	 * Replaces the interface declaration by the declaration of an implementation.
	 * @param interfaceDecl
	 * @param simpleName
	 * @param implName
	 * @return
	 */
	static StringBuffer replaceInterfaceDeclarationByImpl( String interfaceDecl, String simpleName, String implName ) {

		StringBuffer source = new StringBuffer( interfaceDecl );
		Pattern interfacePattern = Pattern.compile(
				" interface\\s+\\w+\\s*\\{",
				Pattern.MULTILINE | Pattern.DOTALL );

		Matcher matcher = interfacePattern.matcher( source );
		if( matcher.find()) {
			source = source.replace(
					matcher.start(),
					matcher.end(),
					" class " + implName + " implements " + simpleName + " {" );
		}

		return source;
	}


	/**
	 * Replaces interface methods by their implementations.
	 * <p>
	 * Made public for test purposes.
	 * Not really useful otherwise.
	 * </p>
	 *
	 * @param source
	 * @return the modified source
	 */
	public static String replaceInterfaceMethodsByImpl( StringBuffer source ) {

		// Get the methods' default implementations
		Pattern methodPattern = Pattern.compile(
				"public\\s+(\\w+)\\s+\\w+\\s*\\([^;]*\\)\\s*(throws\\s+\\w+)?\\s*;",
				Pattern.MULTILINE | Pattern.DOTALL );

		Matcher matcher = methodPattern.matcher( source );
		while( matcher.find()) {

			String javaType = matcher.group( 1 );
			String defaultImpl = getDefaultImplementation( javaType );
			String methodDecl = matcher.group( 0 );

			methodDecl = methodDecl.substring( 0, methodDecl.length() - 1 ).trim();	// Remove the semicolon
			String methodRepl = methodDecl + " {\n\t\t" + defaultImpl + "\n\t}";
			source = source.replace( matcher.start(), matcher.end(), methodRepl );

			// We progressively modify the searched string.
			// We have to reset the matcher to make sure it does not stop to
			// an offset that has now been pushed by our additions.
			matcher.reset();
		}

		return source.toString();
	}


	/**
	 * Gets the default implementation text for a given return type.
	 * @param javaType the Java type (void, primitive type or class name)
	 * @return the default implementation (never null)
	 */
	static String getDefaultImplementation( String javaType ) {

		String result;
		if( "void".equals( javaType ))
			result = "// nothing";
		else if( "int".equals( javaType ))
			result = "return 0;";
		else if( "boolean".equals( javaType ))
			result = "return false;";
		else if( "long".equals( javaType ))
			result = "return 0;";
		else if( "double".equals( javaType ))
			result = "return 0;";
		else if( "float".equals( javaType ))
			result = "return 0;";
		else if( "byte".equals( javaType ))
			result = "return 0;";
		else if( "short".equals( javaType ))
			result = "return 0;";
		else if( "char".equals( javaType ))
			result = "return ' ';";
		else
			result = "return null;";

		return result;
	}


	/**
	 * An exception related to this utility class.
	 */
	public static class JaxWsException extends Exception {

		/**
		 * The serial ID.
		 */
		private static final long serialVersionUID = -358390664739689755L;

		/**
		 * Constructor.
		 */
		public JaxWsException() {
			super();
		}

		/**
		 * Constructor.
		 * @param message
		 * @param cause
		 */
		public JaxWsException( String message, Throwable cause ) {
			super( message, cause );
		}

		/**
		 * Constructor.
		 * @param message
		 */
		public JaxWsException( String message ) {
			super( message );
		}

		/**
		 * Constructor.
		 * @param cause
		 */
		public JaxWsException( Throwable cause ) {
			super( cause );
		}
	}
}
