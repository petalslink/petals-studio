/******************************************************************************
 * Copyright (c) 2008-2013, Linagora
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Linagora - initial API and implementation
 *******************************************************************************/
 
package com.ebmwebsourcing.petals.services.su.wizards.pages;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.IWizardNode;
import org.eclipse.jface.wizard.WizardSelectionPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.ebmwebsourcing.petals.common.internal.provisional.preferences.PreferencesManager;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.DefaultSelectionListener;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.DefaultTreeContentProvider;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.PhantomText;
import com.ebmwebsourcing.petals.common.internal.provisional.ui.FixedShellTooltip;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PlatformUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.extensions.ExtensionManager;
import com.ebmwebsourcing.petals.services.su.extensions.IComponentDescription;
import com.ebmwebsourcing.petals.services.su.extensions.PetalsKeyWords;
import com.ebmwebsourcing.petals.services.su.wizards.AbstractServiceUnitWizard;
import com.ebmwebsourcing.petals.services.su.wizards.FinishServiceCreationStrategy;
import com.ebmwebsourcing.petals.services.su.wizards.PetalsMode;

/**
 * Choose the kind of SU to create.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ChoicePage extends WizardSelectionPage {

	public static final String PAGE_NAME = "ChoicePage";

	private final Map<PetalsKeyWords,Image> keywordToImage;
	private Image helpImg;
	private final Image bcImg;
	private final Image seImg;
	private Font boldFont;
	private FixedShellTooltip helpTooltip;

	private final PetalsMode petalsMode;
	private final FinishServiceCreationStrategy strategy;


	/**
	 * Constructor.
	 * @param strategy
	 */
	public ChoicePage( PetalsMode petalsMode, FinishServiceCreationStrategy strategy ) {
		super( PAGE_NAME );
		this.petalsMode = petalsMode;
		this.strategy = strategy;

		setTitle( petalsMode == PetalsMode.provides ? "Petals Service Provider" : "Petals Service Consumer" );
		setDescription( "Select the Petals component to configure and its version." );

		this.bcImg = PetalsServicesPlugin.loadImage( "icons/obj16/choice_bc_16x16.png" );
		this.seImg = PetalsServicesPlugin.loadImage( "icons/obj16/choice_se_16x16.png" );

		this.keywordToImage = new HashMap<PetalsKeyWords,Image> ();
		for( PetalsKeyWords kw : PetalsKeyWords.values()) {
			try {
				Image img = kw.getImageDescriptor().createImage();
				this.keywordToImage.put( kw, img );

			} catch( Exception e ) {
				PetalsServicesPlugin.log( e, IStatus.ERROR );
			}
		}
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

				link.addSelectionListener( new DefaultSelectionListener() {
					@Override
					public void widgetSelected( SelectionEvent e ) {
						try {
							PreferencesUtil.createPreferenceDialogOn(
										new Shell(),
										"com.ebmwebsourcing.petals.services.prefs.maven",
										null, null ).open();

						} catch( Exception e1 ) {
							PetalsServicesPlugin.log( e1, IStatus.ERROR );
						}
					}
				});
			}
		};


		// Prepare the input
		Comparator<AbstractServiceUnitWizard> comparator = new Comparator<AbstractServiceUnitWizard>() {
			@Override
			public int compare( AbstractServiceUnitWizard o1, AbstractServiceUnitWizard o2 ) {
				String v1 = o1.getComponentVersionDescription().getComponentVersion();
				String v2 = o2.getComponentVersionDescription().getComponentVersion();
				return - v1.compareTo( v2 ); // negative so that the most recent is first
			}
		};

		final Map<String,Collection<AbstractServiceUnitWizard>> componentNameToHandler = new TreeMap<String,Collection<AbstractServiceUnitWizard>> ();
		final Map<PetalsKeyWords,Set<String>> keywordToComponentName = new TreeMap<PetalsKeyWords,Set<String>> ();
		for( AbstractServiceUnitWizard handler : ExtensionManager.INSTANCE.findComponentWizards( this.petalsMode )) {
			for( PetalsKeyWords keyword : handler.getComponentVersionDescription().getKeyWords()) {
				Set<String> list = keywordToComponentName.get( keyword );
				if( list == null )
					list = new TreeSet<String> ();

				String componentName = handler.getComponentVersionDescription().getComponentName();
				list.add( componentName );
				keywordToComponentName.put( keyword, list );

				Collection<AbstractServiceUnitWizard> handlers = componentNameToHandler.get( componentName );
				if( handlers == null )
					handlers = new TreeSet<AbstractServiceUnitWizard>( comparator );

				handlers.add( handler );
				componentNameToHandler.put( componentName, handlers );
			}
		}


		// Add the selection area
		final PhantomText searchText = new PhantomText( container, SWT.SINGLE | SWT.BORDER );
		searchText.setDefaultValue( "Search..." );
		GridDataFactory.swtDefaults().grab( true, false ).align( SWT.FILL, SWT.TOP ).span( 2, 1 ).applyTo( searchText );

		final TreeViewer componentsViewer = new TreeViewer( container, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION );
		GridDataFactory.fillDefaults().span( 2, 1 ).hint( 380, 300 ).applyTo( componentsViewer.getTree());
		componentsViewer.setLabelProvider( new LabelProvider() {
			@Override
			public String getText( Object element ) {

				String result;
				if( element instanceof String ) {
					IComponentDescription desc = componentNameToHandler.get( element ).iterator().next().getComponentVersionDescription();
					String componentName = desc.getComponentName();
					String componentAlias = desc.getComponentAlias();
					String annotation = desc.getComponentAnnotation();

					StringBuilder sb = new StringBuilder();
					if( StringUtils.isEmpty( componentName ))
						sb.append( componentAlias );	// Generic component
					else
						sb.append( componentAlias + "    -  " + componentName );

					if( ! StringUtils.isEmpty( annotation ))
						sb.append( "    ( " + annotation + " )" );

					result = sb.toString();

				} else {
					result = super.getText( element );
				}

				return result;
			}

			@Override
			public Image getImage( Object element ) {

				Image result = null;
				if( element instanceof PetalsKeyWords ) {
					result = ChoicePage.this.keywordToImage.get( element );
				} else {
					IComponentDescription desc = componentNameToHandler.get( element ).iterator().next().getComponentVersionDescription();
					result = desc.isBc() ? ChoicePage.this.bcImg : ChoicePage.this.seImg;
				}

				return result;
			}
		});

		componentsViewer.setContentProvider( new DefaultTreeContentProvider() {
			@Override
			public Object[] getElements( Object inputElement ) {
				return keywordToComponentName.keySet().toArray();
			}

			@Override
			public Object[] getChildren( Object parentElement ) {

				Object[] result;
				if( parentElement instanceof PetalsKeyWords ) {
					Collection<String> componentNames = keywordToComponentName.get( parentElement );
					result = componentNames == null ? new Object[ 0 ] : componentNames.toArray();

				} else {
					result = new Object[ 0 ];
				}

				return result;
			}

			@Override
			public boolean hasChildren( Object element ) {
				return element instanceof PetalsKeyWords;
			}
		});

		componentsViewer.addFilter( new ViewerFilter() {
			@Override
			public boolean select( Viewer viewer, Object parentElement, Object element ) {

				boolean result = false;
				String filter = searchText.getTextValue().trim().toLowerCase();
				if( filter.length() == 0 )
					result = true;

				else if( element instanceof PetalsKeyWords ) {
					Set<String> names = keywordToComponentName.get( element );
					if( names != null ) {
						for( String s : names ) {
							if( select( viewer, null, s )) {
								result = true;
								break;
							}
						}
					}
				}

				else if( element instanceof String )
					result = ((String) element).toLowerCase().contains( filter );

				return result;
			}
		});

		componentsViewer.setInput( new Object());
		if( keywordToComponentName.size() > 0 )
			componentsViewer.expandToLevel( keywordToComponentName.keySet().iterator().next(), 1 );


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
				return ((AbstractServiceUnitWizard) element).getComponentVersionDescription().getComponentVersion();
			}
		});


		final Label descriptionLabel = new Label( container, SWT.NONE );
		GridDataFactory.swtDefaults().span( 2, 1 ).indent( 0, 10 ).applyTo( descriptionLabel );


		// Selection listeners
		searchText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				componentsViewer.refresh();
				if( searchText.getTextValue().trim().length() == 0 )
					componentsViewer.collapseAll();
				else
					componentsViewer.expandAll();
			}
		});


		componentsViewer.addSelectionChangedListener( new ISelectionChangedListener() {
			@Override
			public void selectionChanged( SelectionChangedEvent event ) {

				// Get the selection
				Object o = ((IStructuredSelection) event.getSelection()).getFirstElement();
				Collection<?> input;
				if( o == null || o instanceof PetalsKeyWords )
					input = Collections.emptyList();
				else
					input = componentNameToHandler.get( o );

				// Default selection - there is always one
				versionCombo.setInput( input );
				versionCombo.getCombo().setVisibleItemCount( input.size() > 0 ? input.size() : 1 );
				if( ! input.isEmpty()) {
					versionCombo.setSelection( new StructuredSelection( input.iterator().next()));
					versionCombo.getCombo().notifyListeners( SWT.Selection, new Event());
				} else {
					setPageComplete( false );
					setSelectedNode( null );
					descriptionLabel.setText( "" );
					descriptionLabel.getParent().layout();
				}
			}
		});


		versionCombo.addSelectionChangedListener( new ISelectionChangedListener() {
			@Override
			public void selectionChanged( SelectionChangedEvent event ) {
				AbstractServiceUnitWizard suWizard = (AbstractServiceUnitWizard) ((IStructuredSelection) event.getSelection()).getFirstElement();
				if( suWizard == null )
					return;

				setPageComplete( true );
				setSelectedNode( getWizardNode( suWizard ));

				String desc = ChoicePage.this.petalsMode == PetalsMode.provides ?
						suWizard.getComponentVersionDescription().getProvideDescription()
						: suWizard.getComponentVersionDescription().getConsumeDescription();
				descriptionLabel.setText( desc );
				descriptionLabel.getParent().layout();
			}
		});


		// Initialize
		if( PreferencesManager.isMavenTemplateConfigurationValid())
			this.helpTooltip.hide();

		componentsViewer.getTree().setFocus();
	}


	/**
	 * @param wizard
	 * @return
	 */
	protected IWizardNode getWizardNode(AbstractServiceUnitWizard wizard) {
		wizard.setStrategy(this.strategy);
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
		if( this.helpImg != null && ! this.helpImg.isDisposed())
			this.helpImg.dispose();

		if( this.seImg != null && ! this.seImg.isDisposed())
			this.seImg.dispose();

		if( this.bcImg != null && ! this.bcImg.isDisposed())
			this.bcImg.dispose();

		if( this.boldFont != null && ! this.boldFont.isDisposed())
			this.boldFont.dispose();

		for( Image img : this.keywordToImage.values()) {
			if( img != null && ! img.isDisposed())
				img.dispose();
		}

		super.dispose();
	}
}
