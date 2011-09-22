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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.ebmwebsourcing.commons.jbi.internal.provisional.utils.JbiNameFormatter;
import com.ebmwebsourcing.petals.common.internal.provisional.preferences.PreferencesManager;
import com.ebmwebsourcing.petals.services.cdk.CdkXsdManager;
import com.ebmwebsourcing.petals.services.su.extensions.WizardConfiguration;
import com.ebmwebsourcing.petals.services.su.utils.HciSerialization;
import com.ebmwebsourcing.petals.services.su.utils.XsdUtils;
import com.ebmwebsourcing.petals.services.su.wizards.SuMainConstants;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.XsdNamespaceStore;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.CacheItem;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.HciItem;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.HciType;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.SerializationItem;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step1.XsdGlobalRegistry;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step1.XsdParser;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step2.XsdDependencyGraphBuilder;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step4.SwtWidgetGenerator;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.step4.WidgetGeneratorLink;

/**
 * The abstract wizard page whose HCI can be partially generated from XSD files.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class XsdBasedAbstractSuPage extends AbstractSuPage implements WidgetGeneratorLink {

	/**
	 * The object which generates HCI from XSDs.
	 */
	protected SwtWidgetGenerator widgetGenerator;

	/**
	 * The list of widgets generated from XSDs.
	 */
	protected List<HciItem> xsdBasedWidgets;

	/**
	 * True if the page should use the cache, false otherwise (overrides preferences).
	 */
	protected boolean useCache;



	/**
	 * Empty constructor.
	 * <p>
	 * Sub-classes using this constructor should initialize the following fields:
	 * suType, suTypeVersion, pluginId, xsdBasedWidgets.
	 * </p>
	 * <p>
	 * When created from an extension, sub-class fields are partially initialized.
	 * The only thing the constructor should do is defining the widgets.
	 * Besides, these sub-classes have to define an empty constructor.
	 * </p>
	 * <pre>
	 * public mySubClass() {
	 * 		super( "pageName" );
	 * 		// Convention / Rule: use "SuMainConstants.PAGE_CDK_SPECIFIC_DATA" as the page name for a custom CDK page,
	 * 		// SuMainConstants.PAGE_SPECIFIC_JBI_DATA for a custom component page.
	 * 		// Break this rule at your own risks...
	 *
	 * 		// If you want to use XSD- based generation
	 * 		// ... (code to create widgets from XSD files)
	 * 		this.xsdBasedWidgets = ...;
	 * }
	 * </pre>
	 *
	 * @param pageName the page name
	 */
	public XsdBasedAbstractSuPage( String pageName ) {
		super();
	}


	/**
	 * Constructor that provides the widgets to generate.
	 * Used in case where we parsed the XSD before the creation of the page
	 * (e.g. in the case where you need to know whether the page will have controls from XSD's).
	 * For ambiguous constructor when providing a null parameter, use a cast.
	 * e.g.
	 * 		super((List<HciSimpleStructure>) null );
	 *
	 * @param pageName
	 * @param suType
	 * @param suTypeVersion
	 */
	public XsdBasedAbstractSuPage( String pageName, String suType, String suTypeVersion ) {
		super( pageName, suType, suTypeVersion );
	}


	/**
	 * Get the values from generated widgets.
	 * @return the values
	 */
	public LinkedHashMap<SerializationItem, String> getValues() {
		if( this.widgetGenerator == null )
			return new LinkedHashMap<SerializationItem, String> ();
		return this.widgetGenerator.getValues();
	}


	/**
	 * @param xsdBasedWidgets the widgets to check
	 * @return false if the argument is null, or is empty, or is only made up of non-visible elements.
	 */
	protected boolean areValidItems( List<HciItem> xsdBasedWidgets ) {
		if( xsdBasedWidgets == null
					|| xsdBasedWidgets.isEmpty()
					|| XsdUtils.allFieldAreNotVisible( xsdBasedWidgets ))
			return false;

		return true;
	}


	/**
	 * Create the controls displayed into the page.
	 * This part is processed in three times.
	 * Add controls ("before XSDs"), add XSD-based controls and add other controls ("after XSDs").
	 * @param parent the parent composite.
	 */
	public final void createControl( Composite parent ) {

		// Create the composite container and define its layout.
		final Composite container = new Composite( parent, SWT.NONE );

		// Set help link for documentation page.
		setHelpContextId( container );

		GridLayout layout = new GridLayout ();
		layout.marginLeft = layout.marginRight = 15;
		layout.marginTop = 20;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));

		// Define a form.
		Composite table = new Composite( container, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.horizontalSpacing = 10;
		layout.marginWidth = layout.marginHeight = 0;
		table.setLayout( layout );
		table.setLayoutData( new GridData( GridData.FILL_BOTH ));

		// Create sub-controls.
		addControlsBeforeXsd( table );
		if( this.xsdBasedWidgets == null )
			this.xsdBasedWidgets = new ArrayList<HciItem>();

		this.widgetGenerator = new SwtWidgetGenerator( this );
		this.widgetGenerator.generateWidgets( this.xsdBasedWidgets, table, 0 );
		addControlsAfterXsd( table );
		setControl( container );

		// Enable or disable the "finish" button.
		boolean isComplete = false;
		isComplete = XsdUtils.allFieldsAreOptionalOrNillable( this.xsdBasedWidgets );
		setPageComplete( isComplete );
		doAfterControlsAreCreated();
		reloadDataFromConfiguration();
	}


	/**
	 * Used by sub-classes to execute actions after the controls are created but before any interaction with the user.
	 * Example:
	 * 	Update the "next" button using additional elements than the default ones,
	 * e.g. when adding controls before / after the XSD-based ones.
	 */
	protected void doAfterControlsAreCreated() {
		// Nothing by default.
	}


	/**
	 * Validate with respect to the XSD files.
	 * <p>Sub-classes that override this method should proceed in the following way.</p>
	 * <pre>
	 * 		public void dialogChanged() {
	 * 			// Validate fields before the XSD widgets.
	 * 			if( errorFound ) {
	 * 				updateStatus( "Your error message" );
	 * 				return;
	 * 			}
	 *
	 * 			// Validate XSD fields.
	 * 			super.dialogChanged();
	 *
	 * 			// Validate fields after the XSD widgets.
	 * 			if( errorFound ) {
	 * 				updateStatus( "Your error message" );
	 * 				return;
	 * 			}
	 *
	 * 			// Save your custom fields in non-UI-dependent variables and remove error messages.
	 * 			...
	 * 			updateStatus( null );
	 * 		}
	 * </pre>
	 *
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage#validate()
	 */
	@Override
	public boolean validate() {

		if( this.widgetGenerator != null ) {
			String xsdChecking = this.widgetGenerator.dialogChanged();
			updateStatus( xsdChecking );
			return xsdChecking == null;
		}

		updateStatus( null );
		return true;
	}


	/**
	 * Add controls into the page, right below the generated controls.
	 * @param parent the parent composite, a composite with two columns.
	 */
	protected void addControlsAfterXsd( Composite parent ) {}


	/**
	 * Add controls into the page, right above the generated controls.
	 * @param parent the parent composite, a composite with two columns.
	 */
	protected void addControlsBeforeXsd( Composite parent ) {}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.xsd.swt.WidgetGeneratorLink#widgetChanged()
	 */
	public final void widgetChanged() {
		validate();
	}


	/**
	 * Builds a list of HCI items for a specific page, from a given plug-in (pluginId, suType, suType version).
	 * Specific pages are built from XSD files located at a specific place in the component plug-ins.
	 * Take a look at the developer's guide for more details.
	 *
	 * @param suType
	 * @param suTypeVersion
	 * @param pluginId
	 * @param isConsume
	 * @param useCache
	 * @return
	 */
	public static List<HciItem> initializeSpecificWidgets(
				String suType,
				String suTypeVersion,
				String pluginId,
				boolean isConsume,
				boolean useCache ) {

		List<HciItem> xsdBasedWidgets = null;
		CacheItem cacheItem = null;
		XsdNamespaceStore namespaceStore = XsdNamespaceStore.getNamespaceStore();

		String mode = isConsume ? "Consumes" : "Provides";
		final String serialName = suType + "_v" + suTypeVersion + "_" + mode + ".cache.ser"; //$NON-NLS-1$ //$NON-NLS-2$

		// Get cache preference?
		boolean isCacheActivated = useCache;
		if( isCacheActivated ) {
			isCacheActivated = PreferencesManager.isSuCacheEnabled();
			if( isCacheActivated )
				cacheItem = HciSerialization.getInstance().retrieveCache( serialName );
		}

		// Otherwise, check the XSD files.
		if( cacheItem == null ) {

			// Generate the elements.
			String cdkXsdFolder = SuMainConstants.COMPONENT_XSD_LOCATION + suTypeVersion;
			XsdGlobalRegistry r = new XsdParser().parse( cdkXsdFolder, pluginId, namespaceStore );
			XsdDependencyGraphBuilder extractor = new XsdDependencyGraphBuilder ();
			xsdBasedWidgets = extractor.buildTree( mode, r );

			// Save the widgets.
			if( isCacheActivated ) {
				cacheItem = new CacheItem ();
				cacheItem.setItems( xsdBasedWidgets );
				cacheItem.setNamespaces( namespaceStore.getNamespaces());
				HciSerialization.getInstance().saveToCache( serialName, cacheItem );
			}
		}
		else {
			xsdBasedWidgets = cacheItem.getItems();
			namespaceStore.addAll( cacheItem.getNamespaces());
		}

		return xsdBasedWidgets;
	}


	/**
	 * Builds a list of HCI items for a CDK page.
	 * CDK pages are built from XSD files located in the CDK plug-in.
	 * Take a look at the developer's guide for more details.
	 *
	 * @param suType
	 * @param suTypeVersion
	 * @param isConsume
	 * @param useCache
	 * @return
	 */
	public static List<HciItem> initializeCdkWidgets( String suType, String suTypeVersion, boolean isConsume, boolean useCache ) {

		List<HciItem> xsdBasedWidgets = null;
		CacheItem cacheItem = null;
		XsdNamespaceStore namespaceStore = XsdNamespaceStore.getNamespaceStore();

		String mode = isConsume ? "Consumes" : "Provides";
		final String serialName = suType + "_v" + suTypeVersion + "_" + mode + "_CDK.cache.ser"; //$NON-NLS-1$ //$NON-NLS-2$

		// Get cache preference?
		boolean isCacheActivated = useCache;
		if( isCacheActivated ) {
			isCacheActivated = PreferencesManager.isSuCacheEnabled();
			if( isCacheActivated )
				cacheItem = HciSerialization.getInstance().retrieveCache( serialName );
		}

		// Otherwise, check the XSD files.
		if( cacheItem == null ) {
			// Resolve the folder containing the right XSDs.
			Map<String, String> registeredNs = namespaceStore.getNamespaces();
			String cdkXsdFolder = CdkXsdManager.getInstance().resolveCdkNamespace( registeredNs.values());
			if( cdkXsdFolder == null )
				return null;

			// Generate the elements.
			XsdGlobalRegistry r = new XsdParser().parse( cdkXsdFolder, SuMainConstants.CDK_PLUGIN_ID, namespaceStore );
			XsdDependencyGraphBuilder extractor = new XsdDependencyGraphBuilder ();
			xsdBasedWidgets = extractor.buildTree( mode, r );

			// Save the widgets.
			if( isCacheActivated ) {
				cacheItem = new CacheItem ();
				cacheItem.setItems( xsdBasedWidgets );
				cacheItem.setNamespaces( namespaceStore.getNamespaces());
				HciSerialization.getInstance().saveToCache( serialName, cacheItem );
			}
		}
		else {
			xsdBasedWidgets = cacheItem.getItems();
			namespaceStore.addAll( cacheItem.getNamespaces());
		}

		return xsdBasedWidgets;
	}


	/**
	 * Modify the list of {@link HciItem}s using a wizard configuration found from a SU type and version.
	 * @param wizardConfiguration the wizard configuration where information must be picked-up
	 * @param items a list of items which might be redefined if the wizard configuration says so
	 */
	public void redefineItems( WizardConfiguration wizardConfiguration, List<HciItem> items ) {

		for( HciItem item : wizardConfiguration.getRedefinedItems()) {
			int index = items.indexOf( item );
			if( index == -1 )
				continue;

			HciItem genItem = items.get( index );
			if( item.getLabelName() != null )
				genItem.setLabelName( item.getLabelName());
			if( item.getTooltip() != null )
				genItem.setTooltip( item.getTooltip());
			if( item.getDefaultValue() != null )
				genItem.setDefaultValue( item.getDefaultValue());
			if( item.getValues() != null )
				genItem.setValues( item.getValues());
			genItem.setVisible( item.isVisible());
		}
	}


	/**
	 * Perform structure and content transformation on HciItems.
	 * <p>
	 * This methods should be called by sub-classes at the beginning of
	 * {@link #fillInData(com.ebmwebsourcing.petals.tools.eclipse.su.main.wizards.generation.EclipseSuBean)}.
	 * </p>
	 *
	 * <p>
	 * One typical example of what it does is for QNames.
	 * The namespace URIs of the QNames are registered in {@link XsdNamespaceStore} and the
	 * values of the items are updated to use the generated prefixes instead of the URIs.
	 * </p>
	 *
	 * @param items
	 */
	protected void transformStructureForGeneration( LinkedHashMap<SerializationItem, String> items ) {

		for( Map.Entry<SerializationItem, String> entry : items.entrySet()) {
			if( entry.getKey().getType() != HciType.QNAME )
				continue;

			String value = entry.getValue();
			String nsUri = JbiNameFormatter.extractNamespaceUri( value );
			String prefix = XsdNamespaceStore.getNamespaceStore().store( nsUri );

			if( prefix == null )	// Fix for the SOAP component - operation set to ""
				items.put( entry.getKey(), "" );
			else {
				value = JbiNameFormatter.removeNamespaceElements( value );
				value = prefix + ":" + value;
				items.put( entry.getKey(), value );
			}
		}
	}


	/**
	 * Update fields using information retrieved from the contributor plug-in.
	 * <p>
	 * In some cases, some fields can't be given in the constructor (or are not valid ones).<br />
	 * That's the case of the plug-ins ID, the SU type or the component version (e.g. when we
	 * extend this class in a contributor plug-in, the constructor can't have any argument).
	 * </p>
	 * <p>
	 * When this method is called, it should update the page description and the widgets created from
	 * the XSD files.
	 * </p>
	 *
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #reloadDataFromConfiguration()
	 *
	 * TODO: really useful?
	 */
	@Override
	public abstract void reloadDataFromConfiguration();


	/**
	 * @param useCache the useCache to set
	 */
	public void setUseCache( boolean useCache ) {
		this.useCache = useCache;
	}
}
