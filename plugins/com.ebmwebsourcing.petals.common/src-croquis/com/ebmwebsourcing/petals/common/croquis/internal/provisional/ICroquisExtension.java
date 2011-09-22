/****************************************************************************
 * 
 * Copyright (c) 2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.common.croquis.internal.provisional;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;

/**
 * An interface to implement to register a new croquis extension.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public interface ICroquisExtension {

	public static final String CROQUIS_PROJECT_NAME = "Petals-Croquis";


	/**
	 * @return the name of the sub-directory that will contain these croquis
	 */
	String getSubDirectoryName();

	/**
	 * @return the label to display in the projects viewer
	 */
	String getLabel();

	/**
	 * @return the title to display in the wizard's list of croquis types
	 */
	String getTitle();

	/**
	 * @return the description of the croquis wizard
	 */
	String getDescription();

	/**
	 * @return a screen shot to display in the croquis wizard
	 */
	ImageDescriptor getScreenshot();

	/**
	 * Adds widgets in the page given as parameter.
	 * @param parent the parent for the new widgets
	 * @param page the wizard page
	 */
	void createControl( Composite parent, CroquisNewWizardPage page );

	/**
	 * This method is called by the croquis wizard on completion.
	 * <p>
	 * It is intended to create the required resources.
	 * </p>
	 * 
	 * @param monitor the wizard's progress monitor
	 * @return a status indicating the result of the operation
	 */
	IStatus performFinish( IProgressMonitor monitor );

	/**
	 * Executes actions after the perform finish, in the UI thread, e.g. open an editor.
	 */
	void performAfterFinish();
}
