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

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.wizard.IWizardNode;
import org.eclipse.jface.wizard.WizardSelectionPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.ebmwebsourcing.petals.common.internal.provisional.preferences.PreferencesManager;
import com.ebmwebsourcing.petals.common.internal.provisional.ui.FixedShellTooltip;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PlatformUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.extensions.ExtensionManager;
import com.ebmwebsourcing.petals.services.su.extensions.IComponentDescription;
import com.ebmwebsourcing.petals.services.su.extensions.PetalsKeyWords;
import com.ebmwebsourcing.petals.services.su.wizards.ComponentCreationWizard;
import com.ebmwebsourcing.petals.services.su.wizards.FinishServiceCreationStrategy;
import com.ebmwebsourcing.petals.services.su.wizards.PetalsMode;

/**
 * Choose the kind of SU to create.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ChoicePage extends WizardSelectionPage {

	public static final String PAGE_NAME = "ChoicePage";

	private final Image busImg, descImg, serviceImg, consumeImg, wheelsImg, arrowImg;
	private Image helpImg;
	private Font boldFont;
	private FixedShellTooltip helpTooltip;
	private final PetalsMode petalsMode;

	private FinishServiceCreationStrategy strategy;

	/**
	 * Constructor.
	 * @param strategy 
	 */
	public ChoicePage( PetalsMode petalsMode, FinishServiceCreationStrategy strategy ) {
		super( PAGE_NAME );
		this.petalsMode = petalsMode;
		this.strategy = strategy;
		setDescription( "Select the Petals component to configure and its version." );

		this.descImg = PetalsServicesPlugin.loadImage( "icons/others/descriptor_3.png" );
		this.wheelsImg = PetalsServicesPlugin.loadImage( "icons/others/rouage_3.0.png" );
		this.serviceImg = PetalsServicesPlugin.loadImage( "icons/others/wsdl.png" );
		this.consumeImg = PetalsServicesPlugin.loadImage( "icons/others/connect_3.0.png" );
		this.busImg = PetalsServicesPlugin.loadImage( "icons/others/petals_esb.png" );
		this.arrowImg = PetalsServicesPlugin.loadImage( "icons/others/fleche_2.0.png" );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl( Composite parent ) {

		// Create the composite container and define its layout
		final Composite container = SwtFactory.createComposite( parent );
		setControl( container );
		SwtFactory.applyNewGridLayout( container, 2, false, 15, 0, 0, 15 );
		SwtFactory.applyHorizontalGridData( container );


		// Add a tool tip to display in case of problem
		this.helpTooltip = new FixedShellTooltip( getShell(), true, 90 ) {
			@Override
			public void populateTooltip( Composite parent ) {

				GridLayout layout = new GridLayout();
				layout.verticalSpacing = 2;
				parent.setLayout( layout );
				parent.setLayoutData( new GridData( GridData.FILL_BOTH ));
				parent.setBackground( getShell().getDisplay().getSystemColor( SWT.COLOR_LIST_BACKGROUND ));

				try {
					ImageDescriptor desc = AbstractUIPlugin.imageDescriptorFromPlugin(
								PetalsConstants.PETALS_COMMON_PLUGIN_ID, "icons/petals/thinking_hard.png" );

					if( desc != null )
						ChoicePage.this.helpImg = desc.createImage();

					parent.setBackgroundMode( SWT.INHERIT_DEFAULT );
					Label imgLabel = new Label( parent, SWT.NONE );
					imgLabel.setImage( ChoicePage.this.helpImg );
					imgLabel.setLayoutData( new GridData( SWT.CENTER, SWT.DEFAULT, true, true ));

				} catch( Exception e ) {
					PetalsServicesPlugin.log( e, IStatus.WARNING );
				}

				FontData[] fd = PlatformUtils.getModifiedFontData( getFont().getFontData(), SWT.BOLD );
				ChoicePage.this.boldFont = new Font( getShell().getDisplay(), fd );
				Label titleLabel = new Label( parent, SWT.NONE );
				titleLabel.setFont( ChoicePage.this.boldFont );
				GridData layoutData = new GridData( SWT.CENTER, SWT.DEFAULT, true, true );
				layoutData.verticalIndent = 5;
				titleLabel.setLayoutData( layoutData );
				titleLabel.setText( "What does this error mean?" );

				Label l = new Label( parent, SWT.WRAP );
				l.setText( "This wizard will generate, among other things, Maven artifacts." );
				layoutData = new GridData();
				layoutData.verticalIndent = 8;
				l.setLayoutData( layoutData );

				RowLayout rowLayout = new RowLayout( SWT.HORIZONTAL );
				rowLayout.marginLeft = 0;
				rowLayout.marginTop = 0;
				rowLayout.marginRight = 0;
				rowLayout.marginBottom = 0;
				rowLayout.spacing = 0;

				Composite rowComposite = new Composite( parent, SWT.NONE );
				rowComposite.setLayout( rowLayout );
				rowComposite.setLayoutData( new GridData( SWT.CENTER, SWT.DEFAULT, true, true ));

				new Label( rowComposite, SWT.WRAP ).setText( "Unfortunately, there is a problem with the " );
				Link link = new Link( rowComposite, SWT.WRAP | SWT.NO_FOCUS );
				link.setText( "<A>the Petals Maven preferences</A>" );
				new Label( rowComposite, SWT.WRAP ).setText( "." );
				new Label( parent, SWT.WRAP ).setText( "Please, make them correct." );

				link.addSelectionListener( new SelectionListener() {

					@Override
					public void widgetSelected( SelectionEvent e ) {
						widgetDefaultSelected( e );
					}

					@Override
					public void widgetDefaultSelected( SelectionEvent e ) {
						try {
							Dialog dlg = PreferencesUtil.createPreferenceDialogOn(
										new Shell(),
										"com.ebmwebsourcing.petals.services.prefs.maven",
										null, null );

						} catch( Exception e1 ) {
							PetalsServicesPlugin.log( e1, IStatus.ERROR );
						}
					}
				});
			}
		};


		// Show the key words
		new Label( container, SWT.NONE ).setText( "Key Words:" );
		final ComboViewer keyWordCombo = new ComboViewer( container, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY );
		keyWordCombo.getCombo().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		keyWordCombo.setContentProvider( new ArrayContentProvider());
		keyWordCombo.setLabelProvider( new LabelProvider());
		keyWordCombo.setSorter( new ViewerSorter() {
			@Override
			public int compare( Viewer viewer, Object e1, Object e2 ) {
				String s1 = ((PetalsKeyWords) e1).toString();
				String s2 = ((PetalsKeyWords) e2).toString();
				return s1.compareTo( s2 );
			}
		});


		// List the associated Petals components
		new Label( container, SWT.NONE ).setText( "Petals Component:" );
		final ComboViewer componentCombo = new ComboViewer( container, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY );
		componentCombo.getCombo().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		componentCombo.setContentProvider( new ArrayContentProvider());


		// Display the available versions
		new Label( container, SWT.NONE ).setText( "Component Version:" );
		final ComboViewer versionCombo = new ComboViewer( container, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY );
		GridData layoutData = new GridData();
		layoutData.widthHint = 130;
		versionCombo.getCombo().setLayoutData( layoutData );
		versionCombo.setContentProvider( new ArrayContentProvider());
		versionCombo.setLabelProvider( new LabelProvider() {
			@Override
			public String getText( Object element ) {
				return ((ComponentCreationWizard) element).getComponentVersionDescription().getComponentVersion();
			}
		});


		// Explanation labels
		Composite labelContainer = new Composite( container, SWT.BORDER );
		labelContainer.setBackground( Display.getDefault().getSystemColor( SWT.COLOR_WHITE ));
		GridLayout layout = new GridLayout( 5, false );
		layout.verticalSpacing = 20;
		layout.marginRight = 15;
		labelContainer.setLayout( layout );

		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 2;
		layoutData.verticalIndent = 30;
		layoutData.heightHint = 150;
		labelContainer.setLayoutData( layoutData );

		final Text middleText = new Text( labelContainer, SWT.MULTI | SWT.READ_ONLY );
		middleText.setBackground( Display.getDefault().getSystemColor( SWT.COLOR_WHITE ));
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 5;
		middleText.setLayoutData( layoutData );

		Label descLabel = new Label( labelContainer, SWT.NONE );
		descLabel.setBackground( Display.getDefault().getSystemColor( SWT.COLOR_WHITE ));
		descLabel.setImage( this.descImg );

		Label firstArrowLabel = new Label( labelContainer, SWT.NONE );
		firstArrowLabel.setBackground( Display.getDefault().getSystemColor( SWT.COLOR_WHITE ));
		firstArrowLabel.setImage( this.arrowImg );

		Label busLabel = new Label( labelContainer, SWT.NONE );
		busLabel.setBackground( Display.getDefault().getSystemColor( SWT.COLOR_WHITE ));
		busLabel.setImage( this.busImg );

		Label secondArrowLabel = new Label( labelContainer, SWT.NONE );
		secondArrowLabel.setBackground( Display.getDefault().getSystemColor( SWT.COLOR_WHITE ));
		secondArrowLabel.setImage( this.arrowImg );

		final Label rightLabel = new Label( labelContainer, SWT.NONE );
		rightLabel.setBackground( Display.getDefault().getSystemColor( SWT.COLOR_WHITE ));


		// Prepare the input
		Comparator<ComponentCreationWizard> comparator = new Comparator<ComponentCreationWizard>() {
			@Override
			public int compare( ComponentCreationWizard o1, ComponentCreationWizard o2 ) {
				String v1 = o1.getComponentVersionDescription().getComponentVersion();
				String v2 = o2.getComponentVersionDescription().getComponentVersion();
				return - v1.compareTo( v2 ); // negative so that must recent is first
			}
		};

		final Map<String,Collection<ComponentCreationWizard>> componentNameToHandler = new TreeMap<String,Collection<ComponentCreationWizard>> ();
		final Map<PetalsKeyWords,Set<String>> keywordToComponentName = new HashMap<PetalsKeyWords,Set<String>> ();
		for( ComponentCreationWizard handler : ExtensionManager.INSTANCE.findComponentWizards(petalsMode)) {
			for( PetalsKeyWords keyword : handler.getComponentVersionDescription().getKeyWords()) {
				Set<String> list = keywordToComponentName.get( keyword );
				if( list == null )
					list = new TreeSet<String> ();

				String componentName = handler.getComponentVersionDescription().getComponentName();
				list.add( componentName );
				keywordToComponentName.put( keyword, list );

				Collection<ComponentCreationWizard> handlers = componentNameToHandler.get( componentName );
				if( handlers == null )
					handlers = new TreeSet<ComponentCreationWizard>( comparator );

				handlers.add( handler );
				componentNameToHandler.put( componentName, handlers );
			}
		}

		keyWordCombo.setInput( keywordToComponentName.keySet());
		keyWordCombo.getCombo().setVisibleItemCount( keywordToComponentName.size());


		// Update some label providers
		componentCombo.setLabelProvider( new LabelProvider() {
			@Override
			public String getText( Object element ) {

				IComponentDescription desc = componentNameToHandler.get( element ).iterator().next().getComponentVersionDescription();
				String componentName = desc.getComponentName();
				String componentAlias = desc.getComponentAlias();
				String annotation = desc.getComponentAnnotation();

				StringBuilder sb = new StringBuilder();
				if( StringUtils.isEmpty( componentName ))
					sb.append( componentAlias );	// Generic component
				else
					sb.append( componentAlias + "    //  " + componentName );

				if( ! StringUtils.isEmpty( annotation ))
					sb.append( "    ( " + annotation + " )" );

				return sb.toString();
			}
		});


		// Listener: key word
		keyWordCombo.addSelectionChangedListener( new ISelectionChangedListener() {
			@Override
			public void selectionChanged( SelectionChangedEvent event ) {

				// Get the selection
				String key = keyWordCombo.getCombo().getText();
				PetalsKeyWords keyword = PetalsKeyWords.resolveString( key );
				Set<String> componentNames = keywordToComponentName.get( keyword );
				componentCombo.setInput( componentNames );

				// Default selection - there is always one, guaranteed by the preliminary filtering
				componentCombo.getCombo().setVisibleItemCount( componentNames.size());
				componentCombo.setSelection( new StructuredSelection( componentNames.iterator().next()));
				componentCombo.getCombo().notifyListeners( SWT.Selection, new Event());
			}
		});


		// Listener: component
		componentCombo.addSelectionChangedListener( new ISelectionChangedListener() {
			@Override
			public void selectionChanged( SelectionChangedEvent event ) {

				// Get the selection
				String componentName = (String) ((IStructuredSelection) event.getSelection()).getFirstElement();
				Collection<ComponentCreationWizard> handlers = componentNameToHandler.get( componentName );
				versionCombo.setInput( handlers );

				// Default selection - there is always one
				versionCombo.getCombo().setVisibleItemCount( handlers.size());
				versionCombo.setSelection( new StructuredSelection( handlers.iterator().next()));
				versionCombo.getCombo().notifyListeners( SWT.Selection, new Event());
			}
		});


		// Listener: version
		versionCombo.addSelectionChangedListener( new ISelectionChangedListener() {
			@Override
			public void selectionChanged( SelectionChangedEvent event ) {

				// Get the selection
				ComponentCreationWizard selectedWizard = (ComponentCreationWizard) ((IStructuredSelection) event.getSelection()).getFirstElement();
				setSelectedNode(getWizardNode(selectedWizard));
				

				// Update the right image
				Image newImg;
				if( selectedWizard.getComponentVersionDescription().isBc())
					newImg = ChoicePage.this.petalsMode == PetalsMode.provides ? ChoicePage.this.serviceImg : ChoicePage.this.consumeImg;
				else
					newImg = ChoicePage.this.wheelsImg;

				// Update the description label
				String txt;
				if( ChoicePage.this.petalsMode == PetalsMode.provides ) {
					txt = selectedWizard.getComponentVersionDescription().getProvideDescription();
				} else {
					txt = selectedWizard.getComponentVersionDescription().getConsumeDescription();
				}

				// Refresh the UI
				rightLabel.setImage( newImg );
				middleText.setText( txt );
				middleText.getParent().layout();
			}
		});


		// Initialize
		this.helpTooltip.hide();
		keyWordCombo.getCombo().select( 0 );
		keyWordCombo.getCombo().notifyListeners( SWT.Selection, new Event());
		setPageComplete( PreferencesManager.isMavenTemplateConfigurationValid());
	}


	protected IWizardNode getWizardNode(ComponentCreationWizard wizard) {
		wizard.setStrategy(strategy);
		return new ComponentWizardDescriptionWizardNode(wizard);
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage
	 * #dispose()
	 */
	@Override
	public void dispose() {

		this.helpTooltip.dispose();
		if( this.busImg != null )
			this.busImg.dispose();

		if( this.serviceImg != null )
			this.serviceImg.dispose();

		if( this.wheelsImg != null )
			this.wheelsImg.dispose();

		if( this.arrowImg != null )
			this.arrowImg.dispose();

		if( this.descImg != null )
			this.descImg.dispose();

		if( this.consumeImg != null )
			this.consumeImg.dispose();

		if( this.helpImg != null )
			this.helpImg.dispose();

		if( this.boldFont != null )
			this.boldFont.dispose();

		super.dispose();
	}
}
