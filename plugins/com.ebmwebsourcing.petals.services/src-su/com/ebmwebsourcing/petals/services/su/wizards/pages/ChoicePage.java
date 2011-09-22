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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
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
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.extensions.PetalsUseCase;
import com.ebmwebsourcing.petals.services.su.extensions.RegisteredContributors;
import com.ebmwebsourcing.petals.services.su.wizards.PetalsSuNewWizard;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;

/**
 * Choose the kind of SU to create.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ChoicePage extends AbstractSuPage {

	private boolean isProvides;
	private Image busImg, descImg, serviceImg, consumeImg, wheelsImg, arrowImg, helpImg;
	private Font boldFont;
	private FixedShellTooltip helpTooltip;


	enum PetalsMode {
		provides, consumes;

		/*
		 * (non-Javadoc)
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {

			String result;
			switch( this ) {
			case provides:
				result = "Provide or Import a service in Petals ESB";
				break;
			case consumes:
				result = "Consume a Petals service (or Expose it outside the bus)";
				break;
			default:
				result = "";
			}

			return result;
		};


		/**
		 * Resolves a mode from its string value.
		 * @param s a string
		 * @return a Petals mode, or null if the string does not match anything
		 */
		public static PetalsMode resolveString( String s ) {

			PetalsMode result = null;
			for( PetalsMode mode : values()) {
				if( mode.toString().equals( s )) {
					result = mode;
					break;
				}
			}

			return result;
		}
	}


	/**
	 * Constructor.
	 */
	public ChoicePage() {

		super( "ChoicePage", null, null );
		setTitle( "Service Unit" );
		setDescription( "Select the kind of service unit to create." );

		try {
			ImageDescriptor desc = PetalsServicesPlugin.getImageDescriptor( "icons/others/descriptor_3.png" );
			if( desc != null )
				this.descImg = desc.createImage();

			desc = PetalsServicesPlugin.getImageDescriptor( "icons/others/rouage_3.0.png" );
			if( desc != null )
				this.wheelsImg = desc.createImage();

			desc = PetalsServicesPlugin.getImageDescriptor( "icons/others/wsdl.png" );
			if( desc != null )
				this.serviceImg = desc.createImage();

			desc = PetalsServicesPlugin.getImageDescriptor( "icons/others/connect_3.0.png" );
			if( desc != null )
				this.consumeImg = desc.createImage();

			desc = PetalsServicesPlugin.getImageDescriptor( "icons/others/petals_esb.png" );
			if( desc != null )
				this.busImg = desc.createImage();

			desc = PetalsServicesPlugin.getImageDescriptor( "icons/others/fleche_2.0.png" );
			if( desc != null )
				this.arrowImg = desc.createImage();

		} catch( Exception e ) {
			PetalsServicesPlugin.log( e, IStatus.WARNING );
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {

		/* Create the composite container and define its layout */
		final Composite container = new Composite( parent, SWT.NONE );
		setControl( container );

		// Set help link for documentation page.
		setHelpContextId( container );

		GridLayout layout = new GridLayout( 2, false );
		layout.marginLeft = 15;
		layout.marginTop = 15;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));


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

					public void widgetSelected( SelectionEvent e ) {
						widgetDefaultSelected( e );
					}

					public void widgetDefaultSelected( SelectionEvent e ) {
						try {
							Dialog dlg = PreferencesUtil.createPreferenceDialogOn(
										new Shell(),
										"com.ebmwebsourcing.petals.services.prefs.maven",
										null, null );

							if( dlg.open() == Window.OK )
								validate();

						} catch( Exception e1 ) {
							PetalsServicesPlugin.log( e1, IStatus.ERROR );
						}
					}
				});
			}
		};

		this.helpTooltip.hide();


		// Show the use cases
		new Label( container, SWT.NONE ).setText( "Use case:" );
		final ComboViewer useCaseCombo = new ComboViewer( container, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY );
		useCaseCombo.getCombo().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		useCaseCombo.setContentProvider( new ArrayContentProvider());
		useCaseCombo.setLabelProvider( new LabelProvider());

		final RegisteredContributors contributorRegistry = RegisteredContributors.getInstance();
		final Map<PetalsUseCase, List<String>> useCaseToSuTypes = contributorRegistry.getUseCaseToSuTypes();
		List<PetalsUseCase> useCases = new ArrayList<PetalsUseCase>( useCaseToSuTypes.keySet());
		Collections.sort( useCases );
		useCaseCombo.setInput( useCases );
		useCaseCombo.getCombo().setVisibleItemCount( useCases.size());

		// The combo to show the available components
		new Label( container, SWT.NONE ).setText( "Petals component:" );
		final Combo componentCombo = new Combo( container, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY );
		componentCombo.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		// The combo to select the usage (provides or consumes)
		new Label( container, SWT.NONE ).setText( "Component usage:" );
		final ComboViewer usageCombo = new ComboViewer( container, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY );
		usageCombo.getCombo().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		usageCombo.setContentProvider( new ArrayContentProvider());
		usageCombo.setLabelProvider( new LabelProvider());

		// The combo to show the available versions
		new Label( container, SWT.NONE ).setText( "Component version:" );
		final Combo versionCombo = new Combo( container, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY );
		GridData layoutData = new GridData();
		layoutData.widthHint = 130;
		versionCombo.setLayoutData( layoutData );


		// Explanation labels
		Composite labelContainer = new Composite( container, SWT.BORDER );
		labelContainer.setBackground( Display.getDefault().getSystemColor( SWT.COLOR_WHITE ));
		layout = new GridLayout( 5, false );
		layout.verticalSpacing = 25;
		layout.marginRight = 15;
		labelContainer.setLayout( layout );

		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 2;
		layoutData.verticalIndent = 70;
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


		// Listener: use case
		useCaseCombo.addSelectionChangedListener( new ISelectionChangedListener() {
			public void selectionChanged( SelectionChangedEvent event ) {

				// Get the selection
				String key = useCaseCombo.getCombo().getText();
				PetalsUseCase useCase = PetalsUseCase.resolveString( key );

				// Update the component combo
				List<String> suTypes = new ArrayList<String> ();
				for( String suType : useCaseToSuTypes.get( useCase )) {
					String componentName = contributorRegistry.getComponentName( suType );
					String annotation = contributorRegistry.getAnnotation( suType );

					StringBuilder sb = new StringBuilder();
					if( StringUtils.isEmpty( componentName ))
						sb.append( suType );	// Generic component
					else
						sb.append( suType + "  //  " + componentName );

					if( ! StringUtils.isEmpty( annotation ))
						sb.append( "    ( " + annotation + " )" );

					suTypes.add( sb.toString());
				}

				Collections.sort( suTypes );
				componentCombo.setItems( suTypes.toArray( new String[ suTypes.size()]));

				// Default selection
				if( componentCombo.getItemCount() > 0 ) {
					componentCombo.setVisibleItemCount( componentCombo.getItemCount());
					componentCombo.select( 0 );
					componentCombo.notifyListeners( SWT.Selection, new Event());
				}
				else {
					ChoicePage.this.suType = null;
				}
			}
		});


		// Listener: component
		componentCombo.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {

				// Get the selection
				int index = componentCombo.getSelectionIndex();
				String type = componentCombo.getItem( index );
				int pos = type.indexOf( "//" );
				if( pos >= 0 )
					ChoicePage.this.suType = type.substring( 0, pos ).trim();
				else
					ChoicePage.this.suType = type;

				// Update the version combo
				SortedSet<String> versions = contributorRegistry.getSupportedVersions( ChoicePage.this.suType );
				List<String> displayOrder = new ArrayList<String> ( versions );
				Collections.reverse( displayOrder );
				versionCombo.setItems( displayOrder.toArray( new String[ displayOrder.size()]));

				// Update the usage combo
				boolean supportProvides = contributorRegistry.worksInProvides( ChoicePage.this.suType, null );
				boolean supportConsumes = contributorRegistry.worksInConsumes( ChoicePage.this.suType, null );
				PetalsMode[] modes;
				if( supportProvides ) {
					if( supportConsumes )
						modes = new PetalsMode[] { PetalsMode.provides, PetalsMode.consumes };
					else
						modes = new PetalsMode[] { PetalsMode.provides };
				} else {
					modes = new PetalsMode[] { PetalsMode.consumes };
				}

				// ... but keep the current selection, when possible
				String key = usageCombo.getCombo().getText();
				PetalsMode currentMode = (PetalsMode) usageCombo.getCombo().getData( key );
				usageCombo.setInput( modes );
				usageCombo.refresh();
				if( Arrays.asList( modes ).contains( currentMode ))
					usageCombo.setSelection( new StructuredSelection( currentMode ));
				else
					usageCombo.getCombo().select( 0 );
				usageCombo.getCombo().notifyListeners( SWT.Selection, null );

				// Default selection for the version( must be made after the "mode")
				if( versionCombo.getItemCount() > 0 ) {
					versionCombo.setVisibleItemCount( versionCombo.getItemCount());
					versionCombo.select( 0 );
					versionCombo.notifyListeners( SWT.Selection, new Event());
				}
				else {
					ChoicePage.this.suTypeVersion = null;
				}
			}
		});


		// Listener: version
		versionCombo.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				int index = versionCombo.getSelectionIndex();
				ChoicePage.this.suTypeVersion = versionCombo.getItem( index );

				// Update the labels
				String desc;
				if( ChoicePage.this.isProvides)
					desc = contributorRegistry.getProvideDescription( ChoicePage.this.suType, ChoicePage.this.suTypeVersion );
				else
					desc = contributorRegistry.getConsumeDescription( ChoicePage.this.suType, ChoicePage.this.suTypeVersion );

				middleText.setText( desc == null ? "Missing description" : desc );
				middleText.getParent().layout();

				// Flush stored data and validate
				getNamespaceStore().clear();
				getFileImportManager().clear();
				validate();
			}
		});


		// Listener: usage
		usageCombo.addSelectionChangedListener( new ISelectionChangedListener() {
			public void selectionChanged( SelectionChangedEvent event ) {

				// Get the selection
				String key = usageCombo.getCombo().getText();
				PetalsMode mode = PetalsMode.resolveString( key );
				ChoicePage.this.isProvides = mode == PetalsMode.provides;

				// Update the labels
				String desc;
				if( ChoicePage.this.isProvides)
					desc = contributorRegistry.getProvideDescription( ChoicePage.this.suType, ChoicePage.this.suTypeVersion );
				else
					desc = contributorRegistry.getConsumeDescription( ChoicePage.this.suType, ChoicePage.this.suTypeVersion );

				middleText.setText( desc == null ? "Missing description" : desc );
				middleText.getParent().layout();

				// Update the UI
				if( contributorRegistry.isBc( ChoicePage.this.suType )) {
					if( mode == PetalsMode.provides )
						rightLabel.setImage( ChoicePage.this.serviceImg );
					else
						rightLabel.setImage( ChoicePage.this.consumeImg );

				} else {
					rightLabel.setImage( ChoicePage.this.wheelsImg );
				}

				rightLabel.update();
				rightLabel.getParent().layout();
				validate();
			}
		});


		// Initialize
		useCaseCombo.getCombo().select( 0 );
		useCaseCombo.getCombo().notifyListeners( SWT.Selection, new Event());
		setPageComplete( this.suTypeVersion != null && PreferencesManager.isMavenTemplateConfigurationValid());
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #dialogChanged()
	 */
	@Override
	public boolean validate() {

		boolean showTooltip = false;
		String msg = null;
		if( ! PreferencesManager.isMavenTemplateConfigurationValid()) {
			msg = "There is an error in your preferences about custom POM templates.";
			showTooltip = true;
		}
		else if( this.suTypeVersion == null )
			msg = "No component is available for this use case.";
		else if(( msg = RegisteredContributors.getInstance().getWizardEnablement( this.suType )) != null )
			((PetalsSuNewWizard) getWizard()).clearAllPagesExcept( "ChoicePage" );
		else
			((PetalsSuNewWizard) getWizard()).registerPagesAfterVersionPage(
						ChoicePage.this.suType,
						ChoicePage.this.suTypeVersion );

		if( showTooltip )
			this.helpTooltip.show();
		else
			this.helpTooltip.hide();

		updateStatus( msg );
		return msg == null;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.WizardPage#canFlipToNextPage()
	 */
	@Override
	public boolean canFlipToNextPage() {
		return this.suTypeVersion != null && super.canFlipToNextPage();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #fillInData(com.ebmwebsourcing.petals.tools.eclipse.su.main.wizards.generation.EclipseSuBean)
	 */
	@Override
	public void fillInData( EclipseSuBean suBean ) {
		// Nothing to store.
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #setHelpContextId(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void setHelpContextId( Composite container ) {
		// nothing
	}


	/**
	 * @return the isProvides
	 */
	@Override
	public boolean isProvides() {
		return this.isProvides;
	}


	/**
	 * @return the isBc
	 */
	public boolean isBc() {
		return RegisteredContributors.getInstance().isBc( ChoicePage.this.suType );
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
