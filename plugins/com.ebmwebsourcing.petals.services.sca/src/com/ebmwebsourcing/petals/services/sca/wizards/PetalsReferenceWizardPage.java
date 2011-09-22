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

package com.ebmwebsourcing.petals.services.sca.wizards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.soa.sca.core.common.internal.provisional.utils.ScaCoreConstants;
import org.eclipse.soa.sca.core.common.utils.ScaResourcesFilter;
import org.eclipse.soa.sca.sca1_0.common.utils.ScaModelUtils;
import org.eclipse.soa.sca.sca1_0.common.utils.ScaModelUtils.InvalidScaModelException;
import org.eclipse.soa.sca.sca1_0.model.sca.Component;
import org.eclipse.soa.sca.sca1_0.model.sca.ComponentReference;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.JavaUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.services.explorer.model.EndpointBean;
import com.ebmwebsourcing.petals.services.sca.PetalsScaPlugin;
import com.ebmwebsourcing.petals.services.utils.ConsumeUtils;
import com.ebmwebsourcing.petals.services.utils.PCStyledLabelProvider;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsReferenceWizardPage extends WizardPage {

	/**
	 * The target project.
	 */
	private IJavaProject targetProject;

	/**
	 * Can be an {@link IClasspathEntry} or a String.
	 */
	private Object outputFolder;

	/**
	 * Can be a Composite or a Component.
	 */
	private Component referenceOwner;

	/**
	 * Can be a Reference, a ComponentReference or a String.
	 */
	private Object reference;

	/**
	 * The Petals service to reference.
	 */
	private EndpointBean edptBean;

	/**
	 * The properties to copy in the JBI binding.
	 */
	private boolean invokeBySrv = true, invokeByEdpt = true;

	/**
	 * Promote the created reference?
	 */
	private boolean promoteRef = false;

	/**
	 * The composite file to modify.
	 */
	private IFile compositeFile;

	/**
	 * The model utils used to load the composite to update.
	 */
	private ScaModelUtils scaModelUtils;


	/**
	 * Constructor.
	 * @param selection
	 */
	public PetalsReferenceWizardPage( IStructuredSelection selection ) {

		super( "ScaPage" );
		setTitle( "Petals SCA reference" );
		setDescription( "Create a new SCA reference that targets a Petals service." );

		IFile editedFile = ResourceUtils.getIFileFromEditor();
		try {
			if( editedFile != null
						&& "composite".equals( editedFile.getFileExtension())
						&& editedFile.getProject().hasNature( ScaCoreConstants.SCA_NATURE )
						&& editedFile.getProject().hasNature( JavaCore.NATURE_ID )) {
				this.targetProject = JavaCore.create( editedFile.getProject());
				this.compositeFile = editedFile;

			} else if( selection != null && ! selection.isEmpty()) {
				Object o = selection.getFirstElement();
				if( o instanceof IProject ) {
					IProject project = (IProject) o;
					if( project.isAccessible()
								&& project.hasNature( ScaCoreConstants.SCA_NATURE )
								&& project.hasNature( JavaCore.NATURE_ID )) {
						this.targetProject = JavaCore.create( project );
					}
				}
			}

		} catch( CoreException e ) {
			PetalsScaPlugin.log( e, IStatus.ERROR );
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {

		final Composite superContainer = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout();
		layout.verticalSpacing = 16;
		superContainer.setLayout( layout );
		superContainer.setLayoutData( new GridData( GridData.FILL_BOTH ));

		Group group = new Group( superContainer, SWT.SHADOW_ETCHED_OUT );
		group.setText( "Impacted SCA artifacts" );
		group.setLayout( new GridLayout());
		group.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		Composite container = new Composite( group, SWT.NONE );
		container.setLayout( new GridLayout( 3, false ));
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));


		// Projects
		new Label( container, SWT.NONE ).setText( "&SCA Project:" );
		final ComboViewer projectViewer = new ComboViewer( new Combo( container, SWT.READ_ONLY | SWT.DROP_DOWN | SWT.BORDER ));
		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 2;
		projectViewer.getCombo().setLayoutData( layoutData );

		projectViewer.setContentProvider( new ArrayContentProvider ());
		projectViewer.setLabelProvider( new WorkbenchLabelProvider ());


		// Composite file
		new Label( container, SWT.NONE ).setText( "Composite file:" );
		final ComboViewer compositeViewer = new ComboViewer( new Combo( container, SWT.READ_ONLY | SWT.DROP_DOWN | SWT.BORDER ));
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 2;
		compositeViewer.getCombo().setLayoutData( layoutData );

		compositeViewer.setContentProvider( new ArrayContentProvider ());
		compositeViewer.setLabelProvider( new WorkbenchLabelProvider ());


		// Reference owner
		new Label(container, SWT.NONE).setText( "&Reference owner:" );
		final ComboViewer referenceOwnerViewer = new ComboViewer( new Combo( container, SWT.READ_ONLY | SWT.DROP_DOWN | SWT.BORDER ));
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		layoutData.horizontalSpan = 2;
		referenceOwnerViewer.getCombo().setLayoutData( layoutData );

		referenceOwnerViewer.setContentProvider( new ArrayContentProvider());
		referenceOwnerViewer.setLabelProvider( new LabelProvider() {
			@Override
			public String getText(Object element) {

				if( element instanceof Component ) {
					Component comp = (Component) element;
					String name = ((org.eclipse.soa.sca.sca1_0.model.sca.Composite) comp.eContainer()).getName();
					return name + "/" + comp.getName();
				}

				return element == null ? "" : element.toString();
			}
		});


		// Reference name
		new Label( container, SWT.NONE ).setText( "&Reference name:" );
		final ComboViewer referenceNameViewer = new ComboViewer( new Combo( container, SWT.READ_ONLY | SWT.DROP_DOWN | SWT.BORDER ));
		referenceNameViewer.getCombo().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final List<Object> referenceNameInput = new ArrayList<Object> ();
		referenceNameViewer.setContentProvider( new ArrayContentProvider());
		referenceNameViewer.setLabelProvider( new LabelProvider() {
			@Override
			public String getText( Object element ) {
				if( element instanceof ComponentReference )
					return ((ComponentReference) element).getName();

				return element == null ? "" : element.toString();
			}
		});

		Button referenceNameButton = new Button( container, SWT.PUSH );
		referenceNameButton.setText( "New..." );
		referenceNameButton.setLayoutData( new GridData( SWT.FILL, SWT.DEFAULT, false, false ));

		new Label( container, SWT.NONE ).setText( "" );
		final Button promoteReferenceButton = new Button( container, SWT.CHECK );
		promoteReferenceButton.setText( "Promote the reference and set the binding on the promotion" );
		promoteReferenceButton.setSelection( this.promoteRef );
		promoteReferenceButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				PetalsReferenceWizardPage.this.promoteRef = promoteReferenceButton.getSelection();
				promoteReferenceButton.setEnabled( PetalsReferenceWizardPage.this.reference instanceof String );
			}
		});

		referenceNameButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				InputDialog dlg = new InputDialog(
							getShell(), "New Reference",
							"Enter the name of the Petals reference.",
							"", null );

				if( dlg.open() == Window.OK ) {
					String newRefName = dlg.getValue();
					if( ! referenceNameInput.contains( newRefName ))
						referenceNameInput.add( newRefName );

					referenceNameViewer.setInput( referenceNameInput );
					referenceNameViewer.refresh();
					referenceNameViewer.setSelection( new StructuredSelection( newRefName ));
					promoteReferenceButton.notifyListeners( SWT.Selection, new Event());
				}
			}
		});


		// Output source folder
		group = new Group( superContainer, SWT.SHADOW_ETCHED_OUT );
		group.setText( "Data-binding directory" );
		group.setLayout( new GridLayout());
		group.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		container = new Composite( group, SWT.NONE );
		container.setLayout( new GridLayout( 3, false ));
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));

		new Label( container, SWT.NONE ).setText( "Output &Folder:" );
		final ComboViewer outputFolderViewer = new ComboViewer( new Combo( container, SWT.READ_ONLY | SWT.DROP_DOWN | SWT.BORDER ));
		outputFolderViewer.getCombo().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		outputFolderViewer.setContentProvider( new ArrayContentProvider ());
		outputFolderViewer.setLabelProvider( new LabelProvider () {
			@Override
			public String getText( Object element ) {
				if( element instanceof IClasspathEntry ) {
					IPath path = ((IClasspathEntry) element).getPath();
					return " " + path.lastSegment() + " - " + path.toString(); //$NON-NLS-1$ //$NON-NLS-2$
				}

				return element == null ? "" : element.toString() + " - ";
			}
		});

		final List<Object> outputFolderInput = new ArrayList<Object> ();
		Button outputFolderButton = new Button( container, SWT.PUSH );
		outputFolderButton.setText( "New..." );
		outputFolderButton.setLayoutData( new GridData( SWT.FILL, SWT.DEFAULT, false, false ));
		outputFolderButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e) {

				IInputValidator validator = new IInputValidator() {
					public String isValid( String newText ) {
						IPath path = PetalsReferenceWizardPage.this.targetProject.getProject().getLocation();
						IClasspathEntry srcEntry = JavaCore.newSourceEntry( path.append( newText ));
						try {
							if( Arrays.asList( PetalsReferenceWizardPage.this.targetProject.getRawClasspath()).contains( srcEntry ))
								return "A source folder with this name already exists.";

						} catch( JavaModelException e ) {
							PetalsScaPlugin.log( e, IStatus.WARNING );
						}

						return null;
					}
				};

				InputDialog dlg = new InputDialog(
							getShell(),
							"Output Folder Name",
							"Enter the name of the output source folder.", "",
							validator );

				if( dlg.open() == Window.OK ) {
					String folder = dlg.getValue();
					outputFolderInput.add( folder );

					outputFolderViewer.setInput( outputFolderInput );
					outputFolderViewer.refresh();
					PetalsReferenceWizardPage.this.outputFolder = folder;
					outputFolderViewer.setSelection( new StructuredSelection( folder ));
				}
			}
		});


		// Target service
		group = new Group( superContainer, SWT.SHADOW_ETCHED_OUT );
		group.setText( "Petals invocation" );
		group.setLayout( new GridLayout());
		group.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		container = new Composite( group, SWT.NONE );
		container.setLayout( new GridLayout( 3, false ));
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));

		new Label( container, SWT.NONE ).setText( "Target Service:" );
		final Text serviceText = new Text( container, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY );
		serviceText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final Button serviceSelectionButton = new Button( container, SWT.PUSH );
		serviceSelectionButton.setText( "Browse..." );
		serviceSelectionButton.setLayoutData( new GridData( SWT.FILL, SWT.DEFAULT, false, false ));
		serviceSelectionButton.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {
				widgetSelected( e );
			}

			@Override
			public void widgetSelected( SelectionEvent e ) {

				EndpointBean bean = ConsumeUtils.selectEndpointToConsume( serviceSelectionButton );
				if( bean != null ) {
					PetalsReferenceWizardPage.this.edptBean = bean;

					// FIXME: do we really need a styled label provider here?
					PCStyledLabelProvider labelProvider = new PCStyledLabelProvider( serviceSelectionButton );
					StyledString ss = labelProvider.getStyledText( bean );
					serviceText.setText(ss.getString());
					labelProvider.dispose();

					validate();
				}
			}
		});


		// JBI binding properties
		new Label( container, SWT.NONE ).setText( "" );
		Button button = new Button( container, SWT.CHECK );
		layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		button.setLayoutData( layoutData );
		button.setText( "Invoke by service name" );
		button.setSelection( this.invokeBySrv );
		button.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				PetalsReferenceWizardPage.this.invokeBySrv = ! PetalsReferenceWizardPage.this.invokeBySrv;
				validate();
			}
		});

		new Label( container, SWT.NONE ).setText( "" );
		button = new Button( container, SWT.CHECK );
		layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		button.setLayoutData( layoutData );
		button.setText( "Invoke by end-point name" );
		button.setSelection( this.invokeByEdpt );
		button.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				PetalsReferenceWizardPage.this.invokeByEdpt = ! PetalsReferenceWizardPage.this.invokeByEdpt;
				validate();
			}
		});


		// Listeners for the viewers
		referenceNameViewer.addSelectionChangedListener( new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				PetalsReferenceWizardPage.this.reference =
					((IStructuredSelection) event.getSelection()).getFirstElement();
				promoteReferenceButton.notifyListeners( SWT.Selection, new Event());
				validate();
			}
		});

		outputFolderViewer.addSelectionChangedListener( new ISelectionChangedListener() {
			public void selectionChanged( SelectionChangedEvent event ) {
				Object o = ((IStructuredSelection) event.getSelection()).getFirstElement();
				PetalsReferenceWizardPage.this.outputFolder = o;
				validate();
			}
		});

		projectViewer.getCombo().addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Object o = ((IStructuredSelection) projectViewer.getSelection()).getFirstElement();
				IJavaProject jp = (IJavaProject) o;

				// Refresh SRC folder
				outputFolderInput.clear();
				List<IClasspathEntry> entries = JavaUtils.getSourceFolders( jp );
				outputFolderInput.addAll( entries );
				outputFolderViewer.setInput( outputFolderInput );
				outputFolderViewer.refresh();
				if( entries.size() > 0 ) {
					outputFolderViewer.getCombo().select( 0 );
					outputFolderViewer.getCombo().notifyListeners( SWT.Selection, new Event());
				}

				// Refresh the composite owner
				Set<IContainer> srcFolders = ScaResourcesFilter.getSourceContainers( jp.getProject());
				List<IFile> compositeFiles = ResourceUtils.getFiles( "composite", srcFolders );
				compositeViewer.setInput( compositeFiles );
				compositeViewer.refresh();
				if( compositeFiles.size() > 0 ) {
					compositeViewer.getCombo().select( 0 );
					compositeViewer.getCombo().notifyListeners( SWT.Selection, new Event());
				}
			}
		});

		compositeViewer.getCombo().addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Object o = ((IStructuredSelection) compositeViewer.getSelection()).getFirstElement();
				PetalsReferenceWizardPage.this.compositeFile = (IFile) o;

				List<Component> input = new ArrayList<Component> ();
				try {
					// Free EMF objects
					PetalsReferenceWizardPage.this.referenceOwner = null;

					// Load the newly selected composite
					PetalsReferenceWizardPage.this.scaModelUtils = new ScaModelUtils();
					org.eclipse.soa.sca.sca1_0.model.sca.Composite composite =
						PetalsReferenceWizardPage.this.scaModelUtils.getCompositeFile( PetalsReferenceWizardPage.this.compositeFile );

					if( composite.getComponent() != null) {
						EList<Component> components = composite.getComponent();
						for( int i=0; i<components.size(); i++ )
							input.add( components.get( i ));
					}

					referenceOwnerViewer.setInput( input );
					referenceOwnerViewer.refresh();
					if( input.size() > 0 ) {
						referenceOwnerViewer.getCombo().select( 0 );
						referenceOwnerViewer.getCombo().notifyListeners( SWT.Selection, new Event());
					}

				} catch( InvalidScaModelException e1 ) {
					PetalsScaPlugin.log( e1, IStatus.ERROR );
				}
			}
		});

		referenceOwnerViewer.addSelectionChangedListener( new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				Object o = ((IStructuredSelection) event.getSelection()).getFirstElement();
				PetalsReferenceWizardPage.this.referenceOwner = (Component) o;

				referenceNameInput.clear();
				EList<?> references = ((Component) o).getReference();
				if( references != null ) {
					for( int i=0; i<references.size(); i++ )
						referenceNameInput.add( references.get( i ));
				}

				referenceNameViewer.setInput( referenceNameInput );
				referenceNameViewer.refresh();
				if( references != null && references.size() > 0 ) {
					PetalsReferenceWizardPage.this.reference = referenceNameInput.get( 0 );
					referenceNameViewer.setSelection( new StructuredSelection( PetalsReferenceWizardPage.this.reference ));
					promoteReferenceButton.notifyListeners( SWT.Selection, new Event());
				}
			}
		});


		// Initialize the project combo.
		// The other widgets should be initialized at the same time by the listeners.
		List<IJavaProject> scaProjects = new ArrayList<IJavaProject>();
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		for ( IProject project : projects ) {
			try {
				if( project.isAccessible()
							&& project.hasNature( ScaCoreConstants.SCA_NATURE )
							&& project.hasNature( JavaCore.NATURE_ID )) {
					IJavaProject jp = JavaCore.create( project );
					scaProjects.add( jp);
				}

			} catch (CoreException e) {
				// nothing
			}
		}
		projectViewer.setInput( scaProjects );


		// Initial selection - may have been initialized in constructor
		if( this.targetProject == null
					&& scaProjects.size() > 0 ) {
			this.targetProject = scaProjects.get( 0 );
		}

		// The target project may have been initialized from the selection
		if( this.targetProject != null ) {
			projectViewer.setSelection( new StructuredSelection( this.targetProject ));
			projectViewer.getCombo().notifyListeners( SWT.Selection, new Event());
			if( this.compositeFile != null ) {
				compositeViewer.setSelection( new StructuredSelection( this.compositeFile ));
				compositeViewer.getCombo().notifyListeners( SWT.Selection, new Event());
			}
		}

		setControl( superContainer );
		setErrorMessage( null );
	}


	/**
	 * @return the target project
	 */
	public IJavaProject getTargetProject() {
		return this.targetProject;
	}


	/**
	 * @return the output folder
	 */
	public Object getOutputFolder() {
		return this.outputFolder;
	}


	/**
	 * @return the referenceOwner
	 */
	public Component getReferenceOwner() {
		return this.referenceOwner;
	}


	/**
	 * @return the reference
	 */
	public Object getReference() {
		return this.reference;
	}


	/**
	 * @return the edptBean
	 */
	public EndpointBean getEdptBean() {
		return this.edptBean;
	}


	/**
	 * @return the scaModelUtils
	 */
	public ScaModelUtils getScaModelUtils() {
		return this.scaModelUtils;
	}


	/**
	 * @return the compositeFile
	 */
	public IFile getCompositeFile() {
		return this.compositeFile;
	}


	/**
	 * @return the invokeBySrv
	 */
	public boolean isInvokeBySrv() {
		return this.invokeBySrv;
	}


	/**
	 * @return the invokeByEdpt
	 */
	public boolean isInvokeByEdpt() {
		return this.invokeByEdpt;
	}


	/**
	 * @return the promoteRef
	 */
	public boolean isPromoteRef() {
		return this.promoteRef;
	}


	/**
	 * Validates the wizard information.
	 */
	private void validate() {

		String msg = null;

		// Most of the fields are automatically validated
		// Just make sure they exist (case empty lists)
		if( this.targetProject == null )
			msg = "You have to select a Java SCA project to update.";
		else if( ! this.targetProject.getProject().exists())
			msg = "The selected SCA project does not exist on the file system.";
		else if( this.compositeFile == null )
			msg = "You have to select a composite file (located in a source folder).";
		else if( this.referenceOwner == null )
			msg = "No reference owner was found. The composite is probably invalid.";

		// Validate the reference name
		else if( this.reference == null )
			msg = "You have to choose or define a SCA reference.";

		// Validate the target service
		else if( this.edptBean == null )
			msg = "You have to select a Petals service to reference.";
		else if( this.edptBean.getWsdlUri() == null )
			msg = "The selected Petals service does not have a WSDL description.";

		setErrorMessage( msg );
		setPageComplete( msg == null );
	}
}
