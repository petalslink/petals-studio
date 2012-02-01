/****************************************************************************
 * 
 * Copyright (c) 2011-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.common.croquis.internal.provisional;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;

import com.ebmwebsourcing.petals.common.croquis.internal.CroquisContributionManager;
import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;

/**
 * The page for the croquis wizard.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class CroquisNewWizardPage extends WizardPage implements IWizardPage {

	private ICroquisExtension selectedExtension;
	private Image currentImage, defaultImage;


	/**
	 * Constructor.
	 */
	public CroquisNewWizardPage() {
		super( "Main Page" );

		try {
			ImageDescriptor desc = PetalsCommonPlugin.getImageDescriptor( "icons/screenshots/question-mark.png" );
			this.defaultImage = desc.createImage();

		} catch( Exception e ) {
			PetalsCommonPlugin.log( e, IStatus.WARNING );
		}
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.dialogs.DialogPage
	 * #dispose()
	 */
	@Override
	public void dispose() {

		// Super...
		super.dispose();

		// Destroy the images
		if( this.defaultImage != null && ! this.defaultImage.isDisposed())
			this.defaultImage.dispose();

		if( this.currentImage != null && ! this.currentImage.isDisposed())
			this.currentImage.dispose();
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {

		// The top composite
		Composite container = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout( 2, true );
		layout.horizontalSpacing = 8;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));
		setControl( container );


		// Update the page settings
		setTitle( "New Petals Croquis" );
		getShell().setText( "New Petals Croquis" );
		setDescription( "Create a new croquis for Petals ESB." );


		// The left part
		final Composite leftContainer = new Composite( container, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginTop = 2;
		leftContainer.setLayout( layout );
		leftContainer.setLayoutData( new GridData( GridData.FILL_BOTH ));

		final Label l = new Label( leftContainer, SWT.NONE );
		l.setText( "Croquis Type:" );
		l.setToolTipText( "Select the kind of croquis to create" );

		final ComboViewer croquisViewer = new ComboViewer( leftContainer, SWT.BORDER | SWT.READ_ONLY | SWT.DROP_DOWN );
		croquisViewer.getCombo().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		croquisViewer.setContentProvider( new ArrayContentProvider());
		croquisViewer.setLabelProvider( new LabelProvider() {
			@Override
			public String getText( Object element ) {
				return element instanceof String ? (String) element : ((ICroquisExtension) element).getTitle();
			}
		});


		// The right part
		Composite rightContainer = new Composite( container, SWT.BORDER );
		rightContainer.setBackground( getShell().getDisplay().getSystemColor( SWT.COLOR_WHITE ));
		layout = new GridLayout();
		layout.marginWidth = 12;
		layout.marginHeight = 12;
		rightContainer.setLayout( layout );
		rightContainer.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final Label screenshotLabel = new Label( rightContainer, SWT.NONE );
		screenshotLabel.setBackground( getShell().getDisplay().getSystemColor( SWT.COLOR_WHITE ));

		final Label descriptionLabel = new Label( rightContainer, SWT.NONE );
		GridData layoutData = new GridData( SWT.CENTER, SWT.DEFAULT, true, false );
		layoutData.verticalIndent = 7;
		descriptionLabel.setLayoutData( layoutData );
		descriptionLabel.setBackground( getShell().getDisplay().getSystemColor( SWT.COLOR_WHITE ));


		// The selection change listener
		croquisViewer.addSelectionChangedListener( new ISelectionChangedListener() {
			public void selectionChanged( SelectionChangedEvent event ) {

				Object o = ((IStructuredSelection) event.getSelection()).getFirstElement();
				if( !( o instanceof  ICroquisExtension ))
					return;

				if( o.equals( CroquisNewWizardPage.this.selectedExtension ))
					return;

				CroquisNewWizardPage.this.selectedExtension = (ICroquisExtension) o;
				String text = ((ICroquisExtension) o).getDescription();
				descriptionLabel.setText( text );

				ImageDescriptor desc = ((ICroquisExtension) o).getScreenshot();
				if( CroquisNewWizardPage.this.currentImage != null
							&& CroquisNewWizardPage.this.currentImage != CroquisNewWizardPage.this.defaultImage ) {
					CroquisNewWizardPage.this.currentImage.dispose();
					CroquisNewWizardPage.this.currentImage = null;
				}

				if( desc != null ) {
					try {
						CroquisNewWizardPage.this.currentImage = desc.createImage();
					} catch( Exception e ) {
						PetalsCommonPlugin.log( e, IStatus.ERROR );
					}
				} else {
					CroquisNewWizardPage.this.currentImage = CroquisNewWizardPage.this.defaultImage;
				}

				screenshotLabel.setImage( CroquisNewWizardPage.this.currentImage );
				screenshotLabel.getParent().layout();

				for( Control c : leftContainer.getChildren()) {
					if( c != l && c != croquisViewer.getCombo())
						c.dispose();
				}

				((ICroquisExtension) o).createControl( leftContainer, CroquisNewWizardPage.this );
				leftContainer.layout();
			}
		});


		// Initialize the rest
		List<ICroquisExtension> ext = CroquisContributionManager.INSTANCE.getCroquisData();
		if( ext.size() > 0 ) {
			croquisViewer.setInput( ext );
			croquisViewer.getCombo().select( 0 );
			croquisViewer.getCombo().notifyListeners( SWT.Selection, new Event());

		} else {
			croquisViewer.setInput( "-- No Croquis Available --" );
		}
	}


	/**
	 * Updates the page status.
	 * @param message an error message or null to make the page complete
	 */
	public void updateStatus( String message ) {
		setErrorMessage( message );
		setPageComplete( message == null );
	}


	/**
	 * @return the selectedExtension
	 */
	public ICroquisExtension getSelectedExtension() {
		return this.selectedExtension;
	}
}
