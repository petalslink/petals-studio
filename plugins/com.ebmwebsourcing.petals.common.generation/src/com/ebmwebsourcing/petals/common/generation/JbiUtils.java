/****************************************************************************
 *
 * Copyright (c) 2008-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.generation;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class JbiUtils {

	/**
	 * Create a SU name from a service name.
	 * <p>
	 * This method does not add, check or modify file extensions.
	 * </p>
	 *
	 * @param suType the component used by the SU (e.g. FTP, XSLT...)
	 * @param serviceName the service name
	 * @param isConsume true if the SU is <i>consume</i> mode, false for <i>provide</i>
	 * @return the generated SU name
	 */
	public static String createSuName( String suType, String serviceName, boolean isConsume ) {

		String suMode = isConsume ? "consume" : "provide";
		return "su-" + suType + "-" + serviceName + "-" + suMode;
	}


	/**
	 * Create a SA name from a service name.
	 * <p>
	 * This method does not add, check or modify file extensions.
	 * </p>
	 *
	 * @param suType the component used by the SU (e.g. FTP, XSLT...)
	 * @param serviceName the service name
	 * @param isConsume true if the SU is <i>consume</i> mode, false for <i>provide</i>
	 * @return the generated SU name
	 */
	public static String createSaName( String suType, String serviceName, boolean isConsume ) {

		String suMode = isConsume ? "consume" : "provide";
		return "sa-" + suType + "-" + serviceName + "-" + suMode;
	}


	/**
	 * @param name a name to check
	 * @return true if the name respects the SU naming convention, false otherwise
	 */
	public static boolean isSuName( String name ) {

		String token = "[a-zA-Z_]+[.\\w\\-]*";
		return name.matches( "su-" + token + "-" + token + "-(consume|provide).*" );
	}


	/**
	 * @param name a name to check
	 * @return true if the name respects the SA naming convention, false otherwise
	 */
	public static boolean isSaName( String name ) {

		String token = "[a-zA-Z_]+[.\\w\\-]*";
		return name.matches( "sa-" + token + "-" + token + "-(consume|provide).*" );
	}


	/**
	 * @param name a name to check
	 * @return true if the name starts with "sa-", false otherwise
	 */
	public static boolean isLightSaName( String name ) {
		return name.startsWith( "sa-" );
	}


	/**
	 * Creates a SA name from a SU name.
	 * <p>
	 * This method does not add, check or modify file extensions.
	 * </p>
	 *
	 * @param suName a service unit archive name
	 * @return the generated SU name
	 */
	public static String createSaName( String suName ) {

		if( suName == null )
			return "";

		if( suName.startsWith( "su-" ))
			return suName.replaceFirst( "su-", "sa-" );

		return "sa-" + suName;
	}


	/**
	 * Creates the jbi.xml content for a Petals SA.
	 *
	 * @param componentName the target component name
	 * @param saName the SA name (not the service assembly file name).
	 * <p>
	 * Service assembly names look like <i>sa-Jsr181-&lt;serviceName&gt;-provide</i>.
	 * </p>
	 * @param suNames the SU names (not the service-unit file names).
	 * <p>
	 * Service unit names look like <i>su-Jsr181-&lt;serviceName&gt;-provide</i>.
	 * </p>
	 * @return the jbi.xml content
	 */
	public static String generateJbiXmlForSA( String componentName, String saName, String... suNames ) {

		// Content of the jbi.xml file
		StringBuilder sb = new StringBuilder();
		sb.append( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" );
		sb.append( "<jbi:jbi version=\"1.0\"\n" );
		sb.append( "\txmlns=\"http://java.sun.com/xml/ns/jbi\"\n" );
		sb.append( "\txmlns:jbi=\"http://java.sun.com/xml/ns/jbi\">\n\n" );

		sb.append( "\t<jbi:service-assembly>\n\t\t<jbi:identification>\n" );
		sb.append( "\t\t\t<jbi:name>" + saName + "</jbi:name>\n" );
		sb.append( "\t\t\t<jbi:description></jbi:description>\n" );
		sb.append( "\t\t</jbi:identification>\n" );

		for( String suName : suNames ) {

			sb.append( "\n\t\t<!-- New service-unit -->\n" );
			sb.append( "\t\t<jbi:service-unit>\n\t\t\t<jbi:identification>\n" );
			sb.append( "\t\t\t\t<jbi:name>" + suName + "</jbi:name>\n" );
			sb.append( "\t\t\t\t<jbi:description></jbi:description>\n" );
			sb.append( "\t\t\t</jbi:identification>\n\n" );

			sb.append( "\t\t\t<jbi:target>\n" );
			sb.append( "\t\t\t\t<jbi:artifacts-zip>" + suName + ".zip</jbi:artifacts-zip>\n" );
			sb.append( "\t\t\t\t<jbi:component-name>" + componentName + "</jbi:component-name>\n" );
			sb.append( "\t\t\t</jbi:target>\n\t\t</jbi:service-unit>\n" );
		}

		sb.append( "\t</jbi:service-assembly>\n" );
		sb.append( "</jbi:jbi>" );

		return sb.toString();
	}


	/**
	 * Creates the jbi.xml content for a Petals SA.
	 *
	 * @param saName the SA name (not the service assembly file name).
	 * <p>
	 * Service assembly names look like <i>sa-Jsr181-&lt;serviceName&gt;-provide</i>.
	 * </p>
	 * @param suNameToComponentName the SU names, bound to the target component name
	 * @return the jbi.xml content
	 */
	public static String generateJbiXmlForSA( String saName, Map<String,String> suNameToComponentName ) {

		// Content of the jbi.xml file
		StringBuilder sb = new StringBuilder();
		sb.append( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" );
		sb.append( "<jbi:jbi version=\"1.0\"\n" );
		sb.append( "\txmlns=\"http://java.sun.com/xml/ns/jbi\"\n" );
		sb.append( "\txmlns:jbi=\"http://java.sun.com/xml/ns/jbi\">\n\n" );

		sb.append( "\t<jbi:service-assembly>\n\t\t<jbi:identification>\n" );
		sb.append( "\t\t\t<jbi:name>" + saName + "</jbi:name>\n" );
		sb.append( "\t\t\t<jbi:description></jbi:description>\n" );
		sb.append( "\t\t</jbi:identification>\n" );

		for( Map.Entry<String,String> entry : suNameToComponentName.entrySet()) {

			sb.append( "\n\t\t<!-- New service-unit -->\n" );
			sb.append( "\t\t<jbi:service-unit>\n\t\t\t<jbi:identification>\n" );
			sb.append( "\t\t\t\t<jbi:name>" + entry.getKey() + "</jbi:name>\n" );
			sb.append( "\t\t\t\t<jbi:description></jbi:description>\n" );
			sb.append( "\t\t\t</jbi:identification>\n\n" );

			sb.append( "\t\t\t<jbi:target>\n" );
			sb.append( "\t\t\t\t<jbi:artifacts-zip>" + entry.getKey() + ".zip</jbi:artifacts-zip>\n" );
			sb.append( "\t\t\t\t<jbi:component-name>" + entry.getValue() + "</jbi:component-name>\n" );
			sb.append( "\t\t\t</jbi:target>\n\t\t</jbi:service-unit>\n" );
		}

		sb.append( "\t</jbi:service-assembly>\n" );
		sb.append( "</jbi:jbi>" );

		return sb.toString();
	}


	/**
	 * Creates a service unit or a service assembly file.
	 *
	 * @param targetZipFile the path of the target ZIP file
	 * @param jbiXmlContent the jbi.xml file content, or null to not add a jbi.xml
	 * @param rootFolder the directory whose content must be zipped (the root folder won't be in the zip)
	 * @return the created archive in case of success, null otherwise
	 * @throws IOException
	 */
	public static File createJbiArchive( String targetZipFile, String jbiXmlContent, File rootFolder )
	throws IOException {

		Map<String, File> entries = buildPackagingMap( rootFolder );
		return createJbiArchive( targetZipFile, jbiXmlContent, entries );
	}


	/**
	 * Creates a service unit or a service assembly file.
	 *
	 * @param targetZipFile the path of the target ZIP file
	 * @param rootFolder the folder containing all the resources (the first found jbi.xml will be set under META-INF/)
	 * @return the created archive in case of success, null otherwise
	 * @throws IOException
	 */
	public static File createJbiArchive( String targetZipFile, File rootFolder )
	throws IOException {

		Map<String, File> entries = buildPackagingMap( rootFolder );
		File f = entries.remove( "jbi.xml" );
		if( f != null )
			entries.put( "META-INF/jbi.xml", f );

		return createJbiArchive( targetZipFile, null, entries );
	}


	/**
	 * Creates a service unit or a service assembly file.
	 *
	 * @param targetZipFile the path of the target ZIP file
	 * @param entries key = zip entry, value = file to zip (not null)
	 * @return the created archive in case of success, null otherwise
	 * @throws IOException
	 */
	public static File createJbiArchive( String targetZipFile, Map<String, File> entries )
	throws IOException {
		return createJbiArchive( targetZipFile, null, entries );
	}


	/**
	 * Creates a service unit or a service assembly file.
	 *
	 * @param targetZipFile the path of the target ZIP file
	 * @param jbiXmlContent the jbi.xml file content, or null to not add a jbi.xml
	 * @param entries key = zip entry, value = file to zip (not null)
	 * @return the created archive in case of success, null otherwise
	 * @throws IOException
	 */
	public static File createJbiArchive( String targetZipFile, String jbiXmlContent, Map<String, File> entries )
	throws IOException {

		File zipFile = new File( targetZipFile );
		File temporaryFile = File.createTempFile( "petalsTempFile-", null, null );
		ZipOutputStream zos = new ZipOutputStream( new FileOutputStream( temporaryFile ));

		// Files
		for( Map.Entry<String, File> entry : entries.entrySet()) {
			if( entry.getValue().exists()) {
				InputStream in = new FileInputStream( entry.getValue());
				addFileToZip( zos, in, entry.getKey());
			}
		}

		// JBI descriptor
		if( jbiXmlContent != null ) {
			ByteArrayInputStream in = new ByteArrayInputStream( jbiXmlContent.getBytes());
			addFileToZip( zos, in, "META-INF/jbi.xml" );
		}

		zos.close();
		if( zipFile.exists()) {
			if( ! zipFile.delete())
				throw new IOException( "Could not delete existing target destination." );
		}

		if( ! temporaryFile.renameTo( zipFile )) {
			// Could not move the file, try to copy it
			copyFile( temporaryFile, zipFile );
			if( ! temporaryFile.delete())
				temporaryFile.deleteOnExit();
		}

		return zipFile;
	}


	/**
	 * Adds a file into a zip archive.
	 *
	 * @param out the zip file
	 * @param in the input stream to add into the zip
	 * @param entry the entry for the file to add into the zip (not null)
	 */
	private static void addFileToZip( ZipOutputStream out, InputStream in, String entry ) {

		byte[] buf = new byte[ 1024 ];	// Create a buffer for reading the files
		ZipEntry ze = new ZipEntry( entry );
		try {
			out.putNextEntry( ze );
			int len;
			while ((len = in.read( buf )) > 0) {
				out.write( buf, 0, len);
			}
			in.close ();
			out.closeEntry ();

		} catch( Exception e ) {
			e.printStackTrace();
		}
	}


	/**
	 * Creates a map used to map files and zip entries.
	 * @param root the directory whose content must be zipped
	 * @return a map (key = zip entry, value = file to zip).
	 * <p>
	 * The result of this method can be passed as an argument to
	 * {@link #createJbiArchive(String, Map)} and
	 * {@link #createJbiArchive(String, String, Map)}.
	 *  </p>
	 */
	public static Map<String,File> buildPackagingMap( File root ) {
		return addRecursiveFiles( root, "" );
	}


	/**
	 * Adds recursively entries into the zip file.
	 * <p>
	 * The root file is not added in the map, just its children files.<br />
	 * Hidden files are skipped and are not added in the result.
	 * </p>
	 *
	 * @param root the root file
	 * @param entry the zip entry (not null)
	 * @return
	 */
	private static Map<String,File> addRecursiveFiles( File root, String entry ) {

		Map<String,File> result = new HashMap<String, File> ();
		for( File file : root.listFiles()) {
			if( file.isHidden() || file.getName().startsWith( "." ))
				continue;

			if( entry.trim().length() != 0 && ! entry.endsWith( "/" ))
				entry += "/";

			if( ! file.isDirectory()) {
				result.put( entry + file.getName(), file );
			}
			else {
				Map<String,File> subResult = addRecursiveFiles( file, entry + file.getName() );
				result.putAll( subResult );
			}
		}

		return result;
	}


	/**
	 * Copies the content from inputFile into outputFile.
	 *
	 * @param inputFile
	 * @param outputFile will be created if it does not exist
	 * @throws IOException
	 */
	private static void copyFile( File inputFile, File outputFile ) throws IOException {

		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream( inputFile );
			if( ! outputFile.exists() && ! outputFile.createNewFile())
				throw new IOException( "Failed to create " + outputFile.getAbsolutePath() + "." );

			os = new FileOutputStream( outputFile );
			byte[] buf = new byte[ 1024 ];
			int len;
			while((len = is.read( buf )) > 0) {
				os.write( buf, 0, len );
			}

		} finally {
			if( os != null )
				os.close();

			if( is != null )
				is.close();
		}
	}
}
