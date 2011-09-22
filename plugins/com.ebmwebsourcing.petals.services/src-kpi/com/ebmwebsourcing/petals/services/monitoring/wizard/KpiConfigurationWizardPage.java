/****************************************************************************
 * 
 * Copyright (c) 2010-2011, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.monitoring.wizard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Random;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TreeItem;

import com.ebmwebsourcing.petals.common.internal.provisional.ui.CheckboxTreeAndListGroup;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.monitoring.wizard.KpiProjectBean.KpiFlowBean;
import com.ebmwebsourcing.petals.services.su.Messages;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class KpiConfigurationWizardPage extends AbstractSuPage {

	private Image suImg;
	private CheckboxTreeAndListGroup viewer;

	private Collection<KpiProjectBean> kpiProjectBeans = new ArrayList<KpiProjectBean> ();
	private int processType = 1;


	/**
	 * Constructor.
	 */
	public KpiConfigurationWizardPage() {
		super( "SeveralConsumePages", "KPI", "1.0" );

		// Initialize the images
		try {
			ImageDescriptor desc = PetalsServicesPlugin.getImageDescriptor( "icons/obj16/service_unit.png" );
			if( desc != null )
				this.suImg = desc.createImage();

		} catch( Exception e ) {
			e.printStackTrace();
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage
	 * #dispose()
	 */
	@Override
	public void dispose() {

		if( this.viewer != null ) {
			this.viewer.dispose();

			if( this.suImg != null )
				this.suImg.dispose();
		}

		super.dispose();
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #fillInData(com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean)
	 */
	@Override
	public void fillInData( EclipseSuBean suBean ) {
		// nothing
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #setHelpContextId(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void setHelpContextId( Composite container ) {
		// nothing
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #validate()
	 */
	@Override
	public boolean validate() {

		// Export at least one configuration
		boolean found = false;
		IWorkspaceRoot iwr = ResourcesPlugin.getWorkspace().getRoot();
		for( KpiProjectBean kpb : this.kpiProjectBeans ) {
			for( KpiFlowBean kfb : kpb.getFlowBeans()) {

				String projectName = kpb.getProjectName();
				boolean toCreate = kfb.isToCreate();
				String flow = kfb.getFlowName();

				if( toCreate )
					found = true;

				if( projectName.trim().length() == 0 && toCreate ) {
					updateStatus( "The project name for a " + flow + " flow is invalid." );
					return false;
				}

				IProject p = iwr.getProject( projectName );
				if( toCreate && p.exists()) {
					updateStatus( NLS.bind( Messages.ConsumeJbiPage_12, projectName ));
					return false;
				}
			}
		}

		if( ! found ) {
			updateStatus( "At least one flow configuration must be created." );
			return false;
		}

		updateStatus( null );
		return true;
	}


	/**
	 * @return the list of projects to create
	 */
	public Collection<KpiProjectBean> getProjectsToCreate() {
		return this.kpiProjectBeans;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {

		// Create the composite container and define its layout
		final Composite container = new Composite( parent, SWT.NONE );

		// Set help link for documentation page.
		setHelpContextId( container );

		GridLayout layout = new GridLayout();
		layout.marginLeft = 15;
		layout.marginTop = 15;
		container.setLayout( layout );

		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.widthHint = 500;
		layoutData.heightHint = 400;
		container.setLayoutData( layoutData );

		// Add the other widgets
		Label l = new Label( container, SWT.NONE );
		l.setText( "Select the message flows to watch." );


		// The label provider
		LabelProvider labelProvider = new LabelProvider() {
			@Override
			public Image getImage( Object element ) {
				Image result = null;
				if( element instanceof KpiProjectBean )
					result = KpiConfigurationWizardPage.this.suImg;
				return result;
			}

			@Override
			public String getText( Object element ) {
				String result = "";
				if( element instanceof KpiProjectBean )
					result = ((KpiProjectBean) element).getProjectName();
				else if( element instanceof KpiFlowBean )
					result = ((KpiFlowBean) element).getFlowName();
				return result;
			}
		};


		// The content providers
		ITreeContentProvider treeContentProvider = new ITreeContentProvider() {
			public Object[] getChildren( Object parentElement ) {
				return new Object[ 0 ];
			}

			public Object getParent( Object element ) {
				return null;
			}

			public boolean hasChildren( Object element ) {
				return false;
			}

			public Object[] getElements( Object inputElement ) {
				return KpiConfigurationWizardPage.this.kpiProjectBeans.toArray();
			}

			public void dispose() {
				// nothing
			}

			public void inputChanged( Viewer viewer, Object oldInput, Object newInput ) {
				// nothing
			}
		};

		ArrayContentProvider listContentProvider = new ArrayContentProvider() {
			@Override
			public Object[] getElements( Object inputElement ) {
				Object[] result;
				if( inputElement instanceof KpiProjectBean )
					result = ((KpiProjectBean) inputElement).getFlowBeans().toArray();
				else
					result = new Object[ 0 ];
				return result;
			}
		};


		// The viewer
		this.viewer = new CheckboxTreeAndListGroup(
					container, new Object(), treeContentProvider, labelProvider,
					listContentProvider, labelProvider, SWT.NONE, 460, 120 );

		this.viewer.addCheckStateListener( new ICheckStateListener () {
			public void checkStateChanged( CheckStateChangedEvent event ) {
				if( event.getElement() instanceof KpiFlowBean ) {
					((KpiFlowBean) event.getElement()).setToCreate( event.getChecked());
				} else if( event.getElement() instanceof KpiProjectBean ) {
					for( KpiFlowBean fb : ((KpiProjectBean) event.getElement()).getFlowBeans())
						fb.setToCreate( event.getChecked());
				}
				validate();
			}
		});


		// The process type
		Composite subContainer = new Composite( container, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginTop = 18;
		layout.marginWidth = 0;
		subContainer.setLayout( layout );
		subContainer.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		l = new Label( subContainer, SWT.NONE );
		l.setText( "Define the Process Type value:" );
		l.setToolTipText( "Define a unique identifier for this monitoring configuration" );

		final Spinner spinner = new Spinner( subContainer, SWT.BORDER );
		spinner.setLayoutData( new GridData( 140, SWT.DEFAULT ));
		spinner.setValues( this.processType, 1, Integer.MAX_VALUE, 0, 1, 100 );
		spinner.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				KpiConfigurationWizardPage.this.processType = spinner.getSelection();
				validate();
			}
		});

		// Baaaad....
		new Label( subContainer, SWT.NONE );
		Link generatorLink = new Link( subContainer, SWT.NONE );
		generatorLink.setText( "<A>Generate a random process type value</A>" );
		generatorLink.setToolTipText( "The process type should be unique" );
		generatorLink.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				Random random = new Random( new GregorianCalendar().getTimeInMillis());
				int randomProcessType = random.nextInt( Integer.MAX_VALUE );
				spinner.setSelection( randomProcessType );
			}
		});


		// End-up correctly
		setControl( container );
		reloadDataFromConfiguration();
	}


	/**
	 * @return the processType
	 */
	public int getProcessType() {
		return this.processType;
	}


	/**
	 * @param kpiProjectBeans the KPI project beans to set
	 */
	public void setKpiProjectBeans( Collection<KpiProjectBean> kpiProjectBeans ) {
		this.kpiProjectBeans = kpiProjectBeans;

		IWorkspaceRoot iwr = ResourcesPlugin.getWorkspace().getRoot();
		for( KpiProjectBean kpb : this.kpiProjectBeans ) {
			IProject p = iwr.getProject( kpb.getProjectName());
			if( p.exists()) {
				for( KpiFlowBean fb : kpb.getFlowBeans())
					fb.setToCreate( false );
			}
		}

		reloadDataFromConfiguration();
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #getNextPage()
	 */
	@Override
	public IWizardPage getNextPage() {
		return null;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #reloadDataFromConfiguration()
	 */
	@Override
	public void reloadDataFromConfiguration() {

		if( this.viewer == null )
			return;

		this.viewer.refresh();
		this.viewer.expandAll();
		validate();
		setErrorMessage( null );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage
	 * #setVisible(boolean)
	 */
	@Override
	public void setVisible( boolean visible ) {
		super.setVisible( visible );
		if( visible && this.viewer != null ) {
			for( KpiProjectBean kpb : this.kpiProjectBeans ) {
				if( kpb.isToCreate())
					this.viewer.initialCheckTreeItem( kpb );
			}

			TreeItem topItem = this.viewer.getTree().getTopItem();
			if( topItem != null ) {
				this.viewer.getTree().select( topItem );
				this.viewer.getTree().notifyListeners( SWT.Selection, new Event());
			}
		}
	}
}
