/****************************************************************************
 * 
 * Copyright (c) 2009-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/

package com.ebmwebsourcing.petals.server.ui.wizards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.IVMInstallType;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.IRuntimeWorkingCopy;
import org.eclipse.wst.server.core.TaskModel;
import org.eclipse.wst.server.ui.wizard.IWizardHandle;
import org.eclipse.wst.server.ui.wizard.WizardFragment;

import com.ebmwebsourcing.petals.server.PetalsServerPlugin;
import com.ebmwebsourcing.petals.server.runtime.IPetalsRuntimeWorkingCopy;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsRuntimeWizardFragment3x extends WizardFragment {

	private IRuntimeWorkingCopy runtimeWc;
	private IPetalsRuntimeWorkingCopy petalsRuntimeWc;
	private IWizardHandle wizard;

	private String installPath, runtimeName;
	private IVMInstall vmInstall = JavaRuntime.getDefaultVMInstall();

	private Text runtimeNameText, locationText;
	private ComboViewer jreViewer;



	/* (non-Javadoc)
	 * @see org.eclipse.wst.server.ui.wizard.WizardFragment#hasComposite()
	 */
	@Override
	public boolean hasComposite() {
		return true;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.wst.server.ui.wizard.WizardFragment#enter()
	 */
	@Override
	public void enter() {

		Object o = getTaskModel().getObject( TaskModel.TASK_RUNTIME );
		if( o instanceof IRuntime ) {
			o = ((IRuntime) o).createWorkingCopy();
			getTaskModel().putObject( TaskModel.TASK_RUNTIME, o );
		}

		this.runtimeWc = (IRuntimeWorkingCopy) o;
		if( this.runtimeWc.getOriginal() == null ) {
			try {
				this.runtimeWc.save( true, null );

			} catch( CoreException e ) {
				e.printStackTrace();
			}
		}

		this.petalsRuntimeWc = (IPetalsRuntimeWorkingCopy) this.runtimeWc.loadAdapter( IPetalsRuntimeWorkingCopy.class, null );

		// Update UI
		if( this.runtimeNameText != null ) {

			IVMInstall install = this.petalsRuntimeWc.getVMInstall();
			if( install != null ) {
				this.vmInstall = install;
				this.jreViewer.setSelection( new StructuredSelection( this.vmInstall ));
			}

			this.runtimeName = this.runtimeWc.getName();
			this.runtimeNameText.setText( this.runtimeName );
			if( this.runtimeWc.getOriginal().getLocation() != null )
				this.locationText.setText( this.runtimeWc.getOriginal().getLocation().toOSString());
		}
	}


	/* (non-Javadoc)
	 * @see org.eclipse.wst.server.ui.wizard.WizardFragment#exit()
	 */
	@Override
	public void exit() {
		// nothing
	}


	/**
	 * Validates the page data.
	 */
	public void validate() {

		// Validate the petalsRuntimeWc
		this.petalsRuntimeWc.setVMInstall( this.vmInstall );
		if( this.runtimeName == null || this.runtimeName.trim().length() == 0 ) {
			this.wizard.setMessage( "You must give your runtime a name.", IMessageProvider.ERROR );
			setComplete( false );
			this.wizard.update();
			return;
		}

		// Install path
		boolean complete = false;
		if( this.installPath != null )
			this.runtimeWc.setLocation( new Path( this.installPath ));
		else
			this.runtimeWc.setLocation( null );


		IStatus status = this.runtimeWc.validate( null );
		complete = status == null || status.getSeverity() != IStatus.ERROR;
		int severity = IMessageProvider.NONE;

		if( status != null ) {
			if( status.getSeverity() == IStatus.ERROR )
				severity = IMessageProvider.ERROR;
			else if( status.getSeverity() == IStatus.WARNING )
				severity = IMessageProvider.WARNING;
			else if( status.getSeverity() == IStatus.INFO )
				severity = IMessageProvider.INFORMATION;
		}

		if( status != null && severity != IMessageProvider.NONE )
			this.wizard.setMessage( status.getMessage(), severity );
		else
			this.wizard.setMessage( null, IMessageProvider.ERROR );

		getTaskModel().putObject( TaskModel.TASK_RUNTIME, this.runtimeWc );
		setComplete( complete );
		this.wizard.update();
	}


	/* (non-Javadoc)
	 * @see org.eclipse.wst.server.ui.wizard.WizardFragment
	 * #createComposite(org.eclipse.swt.widgets.Composite, org.eclipse.wst.server.ui.wizard.IWizardHandle)
	 */
	@Override
	public Composite createComposite( Composite parent, IWizardHandle wizard ) {

		// Wizard
		this.wizard = wizard;
		wizard.setTitle( "Petals Runtime" );
		wizard.setDescription( "Create a new Petals runtime." );
		wizard.setImageDescriptor( PetalsServerPlugin.getImageDescriptor( "icons/wizban/pstudio_64x64.png" ));

		// Composite
		Composite container = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout( 3, false );
		layout.marginTop = 10;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));

		// Location
		final Label locationLabel = new Label( container, SWT.NONE );
		locationLabel.setText( "Location:" );

		this.locationText = new Text( container, SWT.SINGLE | SWT.BORDER );
		this.locationText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.locationText.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				PetalsRuntimeWizardFragment3x.this.installPath = PetalsRuntimeWizardFragment3x.this.locationText.getText().trim();
				validate();
			}
		});

		final Button browseButton = new Button( container, SWT.PUSH );
		browseButton.setText( "Browse..." );
		browseButton.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		browseButton.addSelectionListener( new SelectionAdapter() {

			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {
				widgetSelected( e );
			}

			@Override
			public void widgetSelected( SelectionEvent e ) {

				DirectoryDialog dlg = new DirectoryDialog( PetalsRuntimeWizardFragment3x.this.locationText.getShell());
				dlg.setMessage( "Select the install directory of the Petals server." );
				dlg.setText( "Petals server location" );
				dlg.setFilterPath( PetalsRuntimeWizardFragment3x.this.locationText.getText());

				String path = dlg.open();
				if( path != null ) {
					PetalsRuntimeWizardFragment3x.this.locationText.setText( path );
					PetalsRuntimeWizardFragment3x.this.installPath = path.trim();
					validate();
				}
			}
		});


		// JRE
		final Label jreLabel = new Label( container, SWT.NONE );
		jreLabel.setText( "JRE / JDK:" );

		this.jreViewer = new ComboViewer( container, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY );
		this.jreViewer.getCombo().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.jreViewer.setContentProvider( new ArrayContentProvider());
		this.jreViewer.setLabelProvider( new LabelProvider() {
			@Override
			public String getText( Object element ) {
				if( element != null && element instanceof IVMInstall )
					return ((IVMInstall) element).getName();
				return "";
			}
		});

		List<IVMInstall> vms = getVmInstalls();
		this.jreViewer.setInput( vms );
		this.jreViewer.setSelection( new StructuredSelection( this.vmInstall ));
		this.jreViewer.addSelectionChangedListener( new ISelectionChangedListener () {
			public void selectionChanged( SelectionChangedEvent event ) {
				Object o = ((IStructuredSelection) event.getSelection()).getFirstElement();
				PetalsRuntimeWizardFragment3x.this.vmInstall = (IVMInstall) o;
				validate();
			}
		});

		final Button installedJresButton = new Button( container, SWT.PUSH );
		installedJresButton.setText( "Installed JRE..." );
		installedJresButton.setLayoutData( new GridData( SWT.FILL, SWT.DEFAULT, false, false ));
		installedJresButton.addSelectionListener( new SelectionAdapter() {

			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {
				widgetSelected( e );
			}

			@Override
			public void widgetSelected( SelectionEvent e ) {
				String id = "org.eclipse.jdt.debug.ui.preferences.VMPreferencePage";
				PreferenceDialog dlg = PreferencesUtil.createPreferenceDialogOn( new Shell(), id, null, null );

				if( dlg.open() == Window.OK ) {
					List<IVMInstall> vms = getVmInstalls();
					if( vms == null )
						vms = Collections.emptyList();

					PetalsRuntimeWizardFragment3x.this.jreViewer.setInput( vms );
					PetalsRuntimeWizardFragment3x.this.jreViewer.refresh();

					// Show the selected VM - if not null
					PetalsRuntimeWizardFragment3x.this.jreViewer.setSelection( new StructuredSelection(
								PetalsRuntimeWizardFragment3x.this.vmInstall ));
				}
			}
		});


		// Redefine the petalsRuntimeWc name
		new Label( container, SWT.NONE ).setText( "Runtime name:" );
		this.runtimeNameText = new Text( container, SWT.BORDER | SWT.SINGLE );
		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 2;
		this.runtimeNameText.setLayoutData( layoutData );
		this.runtimeNameText.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				PetalsRuntimeWizardFragment3x.this.runtimeName = PetalsRuntimeWizardFragment3x.this.runtimeNameText.getText();
				validate();
			}
		});

		return container;
	}


	/**
	 * @return
	 */
	private List<IVMInstall> getVmInstalls() {

		List<IVMInstall> vmInstallList = new ArrayList<IVMInstall> ();
		IVMInstallType[] vmInstallTypes = JavaRuntime.getVMInstallTypes();
		for( IVMInstallType vmInstallType : vmInstallTypes ) {
			IVMInstall[] vmInstalls = vmInstallType.getVMInstalls();
			for( IVMInstall vmInstall : vmInstalls )
				vmInstallList.add( vmInstall );
		}

		return vmInstallList;
	}
}
