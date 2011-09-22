/****************************************************************************
 * 
 * Copyright (c) 2009-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.sca.configuration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormText;

import com.ebmwebsourcing.petals.services.su.wizards.SettingConstants;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;

/**
 * The composite page of the wizard for the version 1.0 of the SCA component.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class ScaWizardPage11 extends AbstractSuPage {

	public static final String PAGE_NAME = "ScaWizardPage";

	private String compositeName, compositeTns = "http://";
	private ScaPattern scaPattern;


	/**
	 * Empty constructor. Required empty to be instantiated by the main plug-in.
	 */
	public ScaWizardPage11() {
		super( PAGE_NAME );
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #fillInData(com.ebmwebsourcing.petals.tools.eclipse.su.main.wizards.generation.EclipseSuBean)
	 */
	@Override
	public void fillInData( EclipseSuBean suBean ) {

		suBean.customObjects.put( "CompositeName", this.compositeName );
		suBean.customObjects.put( "CompositeTns", this.compositeTns );
		suBean.customObjects.put( "ScaPattern", this.scaPattern );
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #setHelpContextId(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void setHelpContextId( Composite container ) {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #reloadDataFromConfiguration()
	 */
	@Override
	public void reloadDataFromConfiguration() {
		// nothing
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #validate()
	 */
	@Override
	public boolean validate() {

		// Validate the fields
		if( this.compositeName == null ) {
			updateStatus( "You have to provide the composite name." );
			return false;
		}

		for( Character c : this.compositeName.toCharArray()) {
			if( ! Character.isJavaIdentifierPart( c )) {
				updateStatus( this.compositeName + " is not a valid file name." );
				return false;
			}
		}

		if( this.compositeTns == null ) {
			updateStatus( "You have to provide the target namespace." );
			return false;
		}

		try {
			new URL( this.compositeTns );

		} catch( MalformedURLException e ) {
			updateStatus( this.compositeTns + " is not a valid URL." );
			return false;
		}


		// Update the project page
		getWizard().getDialogSettings().put( SettingConstants.SRV_NAME_VALUE, this.compositeName );

		updateStatus( null );
		return true;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {

		// Create the composite container and define its layout.
		final Composite container = new Composite( parent, SWT.NONE );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));

		GridLayout layout = new GridLayout( 2, false );
		layout.marginLeft = layout.marginRight = 15;
		layout.horizontalSpacing = 10;
		layout.verticalSpacing = 10;
		layout.marginTop = 20;
		container.setLayout( layout );


		// Set help link for documentation page.
		setHelpContextId( container );


		// Basic GUI part
		Label label = new Label( container, SWT.NONE );
		label.setText( "Composite name:" );
		label.setToolTipText( "The name of the composite file to create" );

		final Text nameText = new Text( container, SWT.SINGLE | SWT.BORDER );
		nameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		nameText.addModifyListener( new ModifyListener () {

			public void modifyText( ModifyEvent e ) {
				ScaWizardPage11.this.compositeName = nameText.getText();
				validate();
			}
		});

		label = new Label( container, SWT.NONE );
		label.setText( "Target name space:" );
		label.setToolTipText( "The target namespace of the composite to create" );

		final Text tnsText = new Text( container, SWT.SINGLE | SWT.BORDER );
		tnsText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		tnsText.setText( this.compositeTns );
		tnsText.addModifyListener( new ModifyListener () {

			public void modifyText( ModifyEvent e ) {
				ScaWizardPage11.this.compositeTns = tnsText.getText();
				validate();
			}
		});


		// Add the combo box
		label = new Label( container, SWT.NONE );
		label.setText( "Creation pattern:" );
		label.setToolTipText( "The 'shape' of the SCA application to create" );

		ComboViewer patternViewer = new ComboViewer( container, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY );
		patternViewer.setContentProvider( new ArrayContentProvider());
		patternViewer.setLabelProvider( new LabelProvider() {
			@Override
			public String getText( Object element ) {
				return ((ScaPattern) element).getTitle();
			}
		});

		patternViewer.getCombo().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		patternViewer.setInput( ScaPattern.values());


		// Add the overview panel
		Composite overviewComposite = new Composite( container, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.marginTop = 18;
		layout.horizontalSpacing = 0;
		overviewComposite.setLayout( layout );

		GridData layoutData = new GridData( GridData.FILL_BOTH );
		layoutData.horizontalSpan = 2;
		overviewComposite.setLayoutData( layoutData );

		// The description
		final Composite descComposite = new Composite( overviewComposite, SWT.BORDER );
		layout = new GridLayout();
		layout.marginHeight = 7;
		layout.marginWidth = 7;
		descComposite.setLayout( layout );

		layoutData = new GridData( GridData.FILL_BOTH );
		layoutData.widthHint = 280;
		descComposite.setLayoutData( layoutData );
		descComposite.setBackground( descComposite.getDisplay().getSystemColor( SWT.COLOR_WHITE ));

		final FormText descText = new FormText( descComposite, SWT.WRAP | SWT.NO_FOCUS );
		descText.setLayoutData( new GridData( GridData.FILL_BOTH ));
		descText.setBackground( descText.getDisplay().getSystemColor( SWT.COLOR_WHITE ));

		MouseTrackListener mouseListener = new MouseTrackAdapter() {
			@Override
			public void mouseEnter( MouseEvent e ) {
				setIn( true );
			}

			@Override
			public void mouseExit( MouseEvent e ) {
				setIn( false );
			}

			/**
			 * Updates the background color in function of the mouse location.
			 * @param inValue
			 */
			void setIn( boolean inValue ) {
				Color bgColor;
				if( inValue )
					bgColor = descText.getDisplay().getSystemColor( SWT.COLOR_INFO_BACKGROUND );
				else
					bgColor = descText.getDisplay().getSystemColor( SWT.COLOR_WHITE );

				descComposite.setBackground( bgColor );
				descText.setBackground( bgColor );
			}
		};

		// descComposite.addMouseTrackListener( mouseListener );
		descText.addMouseTrackListener( mouseListener );

		// The image
		Composite imgComposite = new Composite( overviewComposite, SWT.BORDER );
		layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		imgComposite.setLayout( layout );
		imgComposite.setLayoutData( new GridData( GridData.FILL_BOTH ));

		final Label imgLabel = new Label( imgComposite, SWT.NONE );
		imgLabel.setLayoutData( new GridData( GridData.FILL_BOTH ));
		imgLabel.setBackground( imgLabel.getDisplay().getSystemColor( SWT.COLOR_WHITE ));


		// Add the viewer listener
		patternViewer.addSelectionChangedListener( new ISelectionChangedListener() {
			Map<ScaPattern,Image> patternToImage = new HashMap<ScaPattern,Image> ();

			public void selectionChanged( SelectionChangedEvent event ) {
				ScaWizardPage11.this.scaPattern = (ScaPattern) ((IStructuredSelection) event.getSelection()).getFirstElement();
				descText.setText( ScaWizardPage11.this.scaPattern.getDescription(), true, false );

				final Image img;
				if( this.patternToImage.containsKey( ScaWizardPage11.this.scaPattern )) {
					img = this.patternToImage.get( ScaWizardPage11.this.scaPattern );
				} else {
					ImageDescriptor desc = ScaWizardPage11.this.scaPattern.getImageDescriptor();
					if( desc != null )
						img = desc.createImage();
					else
						img = null;
					this.patternToImage.put( ScaWizardPage11.this.scaPattern, img );
				}

				imgLabel.setImage( img );
			}
		});


		// Complete the page
		patternViewer.getCombo().select( 0 );
		patternViewer.getCombo().notifyListeners( SWT.Selection, new Event());

		setPageComplete( false );
		setControl( container );
		nameText.setFocus();
	}


	/**
	 * @return true if a service composition must be created, false otherwise
	 */
	public boolean mustCreateComposition() {
		return this.scaPattern == ScaPattern.COMPOSITION;
	}
}
