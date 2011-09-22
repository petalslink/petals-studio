/****************************************************************************
 *
 * Copyright (c) 2008-2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.wizards.pages;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

import com.ebmwebsourcing.petals.services.su.utils.FileImportManager;
import com.ebmwebsourcing.petals.services.su.wizards.PageManager;
import com.ebmwebsourcing.petals.services.su.wizards.PetalsSuNewWizard;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.XsdNamespaceStore;

/**
 * The abstract page used in the wizards of this plug-in.
 * <p>
 * Every sub-class instantiated from the extension-point should extend this class
 * or one of its subclasses, and define an empty constructor.
 * </p>
 *
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class AbstractSuPage extends WizardPage {

	/**
	 * The contributor plug-in ID.
	 */
	protected String pluginId;

	/**
	 * The version of the Petals component.
	 */
	protected String suTypeVersion = ""; //$NON-NLS-1$

	/**
	 * The component type used by the SU (e.g. FTP, XSLT...).
	 */
	protected String suType = ""; //$NON-NLS-1$

	/**
	 * True if the page has some content to display, false otherwise.
	 * <p>
	 * If the page has nothing to display, it should not be added to a wizard.
	 * <br />Introduced for pages using generated content.
	 * </p>
	 */
	protected boolean hasSomethingToDisplay = true;

	/**
	 * Count the number of these pages and ensure they never have the same name.
	 */
	private static int cpt = 0;



	/**
	 * Constructor defining the page name.
	 */
	public AbstractSuPage () {
		super( "AbstractSuPage_" + cpt++ );
	}


	/**
	 * Constructor defining the page name.
	 * @param pageName
	 */
	public AbstractSuPage( String pageName ) {
		super( pageName );
	}


	/**
	 * @param pageName the page name.
	 * @param suType the SU type.
	 * @param suTypeVersion the version of the SU type (of its component).
	 */
	public AbstractSuPage( String pageName, String suType, String suTypeVersion ) {
		super( pageName );
		this.suType = suType;
		this.suTypeVersion = suTypeVersion;
	}


	/**
	 * Use:
	 * 	PlatformUI.getWorkbench().getHelpSystem().setHelp(
	 * 		container,
	 * 		"com.ebmwebsourcing.petals.tools.eclipse.su.main.contextId" );
	 *
	 * <b>This</b> plug-in ID followed by ".<helpContextId>".
	 *
	 * When using a sub-class in the extension-point provided by this plug-in,
	 * make sure to call this method in your createControls( composite) method.
	 * Besides, the documentation you reference should be provided by your plug-in.
	 *
	 * @param container
	 */
	protected abstract void setHelpContextId( Composite container );


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.WizardPage#getNextPage()
	 */
	@Override
	public IWizardPage getNextPage() {
		IWizardPage p = getWizard().getNextPage( this );
		return p;
	}


	/**
	 * Called each time we need to validate user inputs.
	 * This call must be explicit, it is not automatic.
	 * @return true if the validation succeeded, false otherwise.
	 */
	public abstract boolean validate();


	/**
	 * Display the error message provided as parameter and prevent the user from going further in the wizard.
	 * @param message the error message to display, or null to display nothing.
	 */
	protected final void updateStatus( String message ) {
		setErrorMessage( message );
		setPageComplete( message == null );
	}


	/**
	 * @param suTypeVersion the suTypeVersion to set
	 */
	public void setSuTypeVersion(String suTypeVersion) {
		this.suTypeVersion = suTypeVersion;
	}


	/**
	 * @param suType the suType to set
	 */
	public void setSuType(String suType) {
		this.suType = suType;
	}


	/**
	 * @param pluginId the contributor plug-in ID.
	 */
	public void setContributorPlugin( String pluginId ) {
		this.pluginId = pluginId;
	}


	/**
	 * Defined for sub-classes instantiated from extension-point.
	 *
	 * For XsdBasedAbnstractPage:
	 * <pre>
	 * public void setBasicFieldsForXsdGeneration () {
	 *		super.setBasicFieldsForXsdGeneration();
	 *		// Create widgets from XSDs.
	 * }
	 * </pre>
	 *
	 * @param suType
	 * @param suTypeVersion
	 * @param pluginId
	 */
	public void setBasicFields( String suType, String suTypeVersion, String pluginId ) {
		setSuType( suType );
		setSuTypeVersion( suTypeVersion );
		setContributorPlugin( pluginId );
	}


	/**
	 * @return the namespace store related to this wizard.
	 */
	protected XsdNamespaceStore getNamespaceStore() {
		return XsdNamespaceStore.getNamespaceStore();
	}


	/**
	 * Registers a new name space.
	 * @param prefix the prefix (not null)
	 * @param uri the name space URI (not null)
	 * @see XsdNamespaceStore#store(String, String)
	 */
	protected void registerNamespace( String prefix, String uri ) {
		XsdNamespaceStore.getNamespaceStore().store( prefix, uri );
	}


	/**
	 * @return the file import manager related to this wizard.
	 */
	protected FileImportManager getFileImportManager() {
		return FileImportManager.getFileImportManager();
	}


	/**
	 * @return true if the page has something to display, false otherwise.
	 * If such a page has nothing to display, it should not be added to a wizard.
	 */
	public boolean hasSomethingToDisplay() {
		return this.hasSomethingToDisplay;
	}


	/**
	 * A method called by the wizard, asking the page to save its information in the argument.
	 * @param suBean the bean whose fields must be filled in by the page.
	 */
	abstract public void fillInData( EclipseSuBean suBean );


	/**
	 * Each time {@link #getNextPage()} is called, the {@link PageManager} calls this method on the next page.
	 * <p>
	 * Every sub-class which needs to update its content from other pages should
	 * override this method and use the wizard settings to refresh its fields.
	 * </p>
	 * <p>
	 * The first thing a sub-class should do in this method is to check whether
	 * the UI elements have been created.
	 * <br /><br />
	 * <code>if( myWidgetText == null ) { return; }</code>
	 * </p>
	 * <p>
	 * This implementation does nothing.
	 * </p>
	 */
	public void reloadDataFromConfiguration () {
		// nothing
	}


	/**
	 * @return the suTypeVersion
	 */
	public String getSuTypeVersion() {
		return this.suTypeVersion;
	}


	/**
	 * @return the suType
	 */
	public String getSuType() {
		return this.suType;
	}


	/**
	 * @return true if this page should be displayed, false otherwise
	 * <p>
	 * Sub-classes could use other parameters to determine whether a page should be displayed or not.
	 * </p>
	 */
	public boolean displayPage() {
		return true;
	}


	/**
	 * @return true if this page is used in a provider wizard, false otherwise
	 */
	public boolean isProvides() {
		return ((PetalsSuNewWizard) getWizard()).isProvides();
	}
}
