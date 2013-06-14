/****************************************************************************
 *
 * Copyright (c) 2010-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.wizards;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wst.xml.ui.internal.properties.StringComboBoxCellEditor;

import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.wizards.beans.SaImportBean;
import com.ebmwebsourcing.petals.services.wizards.beans.ServiceImportBean;
import com.ebmwebsourcing.petals.services.wizards.beans.SuImportBean;

/**
 * A page that shows a tree and widgets to create SU and SA projects.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class AbstractPetalsServiceCreationWizardPage extends WizardPage {

	private static final String COMPONENT_NAME = "Component";
	private static final String PROJECT_NAME = "Project Name";
	private static final String COMPONENT_VERSION = "Version";
	private static final String CREATE = "Create";
	private final static String OVERWRITE = "Overwrite";

	/**
	 * The default array of versions, to use to not make the combo editor crash.
	 */
	private final static String[] DEFAULT_VERSIONS = new String[] { "" };

	private Image suImg, saImg;
	private Image checked, unchecked;

	private String projectLocation;
	private boolean complete = false;
	private boolean isAtDefaultLocation = true;

	private TreeViewer viewer;
	private final List<SaImportBean> importsBeans = new ArrayList<SaImportBean> ();


	/**
	 * Constructor.
	 * @param title the page title
	 * @param description the page description
	 */
	public AbstractPetalsServiceCreationWizardPage( String title, String description ) {

		super( title );
		setTitle( title ); //NON-NLS-1
		setDescription( description ); //NON-NLS-1

		try {
			ImageDescriptor desc = PetalsServicesPlugin.getImageDescriptor( "icons/obj16/service_unit.png" );
			if( desc != null )
				this.suImg = desc.createImage();

			desc = PetalsServicesPlugin.getImageDescriptor( "icons/obj16/service_assembly.png" );
			if( desc != null )
				this.saImg = desc.createImage();

			desc = PetalsServicesPlugin.getImageDescriptor( "icons/obj16/checked.gif" );
			if( desc != null )
				this.checked = desc.createImage();

			desc = PetalsServicesPlugin.getImageDescriptor( "icons/obj16/unchecked.gif" );
			if( desc != null )
				this.unchecked = desc.createImage();

		} catch( Exception e ) {
			PetalsServicesPlugin.log( e, IStatus.WARNING );
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	@SuppressWarnings( "restriction" )
	public void createControl( Composite parent ) {

		Composite container = new Composite( parent, SWT.NONE );
		container.setLayout( new GridLayout ());
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));


		// Before the workspace location
		createWidgetsBeforeProjectLocation( container );


		// Workspace location
		final Button useDefaultLocButton = new Button( container, SWT.CHECK );
		useDefaultLocButton.setText( "Create the project(s) in the default location" );
		GridData layoutData = new GridData ();
		layoutData.verticalIndent = 17;
		useDefaultLocButton.setLayoutData( layoutData );

		Composite locContainer = new Composite( container, SWT.NONE );
		GridLayout layout = new GridLayout( 3, false );
		layout.marginHeight = layout.marginWidth = 0;
		locContainer.setLayout( layout );
		locContainer.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final Label locLabel = new Label( locContainer, SWT.NONE );
		locLabel.setText( "Project(s) location:" );

		final Text projectLocationText = new Text( locContainer, SWT.SINGLE | SWT.BORDER );
		projectLocationText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		projectLocationText.setText( ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString());
		projectLocationText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				AbstractPetalsServiceCreationWizardPage.this.projectLocation = projectLocationText.getText();
				validate();
			}
		});

		final Button browseButton = new Button( locContainer, SWT.PUSH );
		browseButton.setText( "Browse..." );
		browseButton.addSelectionListener( new SelectionAdapter () {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				String location = new DirectoryDialog( getShell()).open();
				if( location != null )
					projectLocationText.setText( location );
			}
		});

		useDefaultLocButton.setSelection( this.isAtDefaultLocation );
		useDefaultLocButton.addSelectionListener( new SelectionAdapter () {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				AbstractPetalsServiceCreationWizardPage.this.isAtDefaultLocation =
					useDefaultLocButton.getSelection();

				boolean use = ! AbstractPetalsServiceCreationWizardPage.this.isAtDefaultLocation;
				locLabel.setEnabled( use );
				projectLocationText.setEnabled( use );
				browseButton.setEnabled( use );
				projectLocationText.setFocus();
			}
		});


		// List of projects to create
		Label l = new Label( container, SWT.NONE );
		l.setText( "Specify the properties of the target project(s)." );

		layoutData = new GridData();
		layoutData.verticalIndent = 17;
		l.setLayoutData( layoutData );

		Tree tree = new Tree( container, SWT.FULL_SELECTION | SWT.BORDER | SWT.SINGLE );
		layoutData = new GridData( GridData.FILL_BOTH );
		layoutData.heightHint = 90;
		tree.setLayoutData( layoutData );
		tree.setHeaderVisible( true );
		tree.setLinesVisible( true );

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData( new ColumnWeightData( 40, 150, false ));
		tlayout.addColumnData( new ColumnWeightData( 10, 10, false ));
		tlayout.addColumnData( new ColumnWeightData( 10, 15, false ));
		tlayout.addColumnData( new ColumnWeightData( 20, 80, true ));
		tlayout.addColumnData( new ColumnWeightData( 20, 60, true ));
		tree.setLayout( tlayout );

		TreeColumn column = new TreeColumn( tree, SWT.LEFT );
		column.setText( PROJECT_NAME );
		column = new TreeColumn( tree, SWT.CENTER );
		column.setText( CREATE );
		column = new TreeColumn( tree, SWT.CENTER );
		column.setText( OVERWRITE );
		column = new TreeColumn( tree, SWT.LEFT );
		column.setText( COMPONENT_NAME );
		column = new TreeColumn( tree, SWT.CENTER );
		column.setText( COMPONENT_VERSION );


		// Create the viewer
		this.viewer = new TreeViewer( tree );


		// Define its content provider
		this.viewer.setContentProvider( new ITreeContentProvider() {

			@Override
			public Object[] getChildren( Object parentElement ) {
				Object[] children = new Object[ 0 ];
				if( parentElement instanceof SaImportBean ) {
					children = new Object[((SaImportBean) parentElement).countSuBeans()];
					children = ((SaImportBean) parentElement).getSuBeans().toArray( children );
				}
				return children;
			}

			@Override
			public Object getParent( Object element ) {
				return null;
			}

			@Override
			public boolean hasChildren( Object element ) {
				boolean hasChildren = false;
				if( element instanceof SaImportBean ) {
					hasChildren = ((SaImportBean) element).countSuBeans() > 0;
				}
				return hasChildren;
			}

			@Override
			public Object[] getElements( Object inputElement ) {
				Object[] result = new Object[ ((Collection<?>) inputElement).size()];
				return ((Collection<?>) inputElement).toArray( result );
			}

			@Override
			public void dispose() {
				// nothing
			}

			@Override
			public void inputChanged( Viewer _viewer, Object oldInput, Object newInput ) {
				// nothing
			}
		});


		// Define its label provider
		this.viewer.setLabelProvider( new ITableLabelProvider() {
			@Override
			public Image getColumnImage( Object element, int columnIndex ) {
				Image result = null;

				switch( columnIndex ) {
				case 0:
					if( element instanceof SaImportBean )
						result = AbstractPetalsServiceCreationWizardPage.this.saImg;
					else if( element instanceof SuImportBean )
						result = AbstractPetalsServiceCreationWizardPage.this.suImg;
					break;

				case 1:
					if( element instanceof ServiceImportBean ) {
						result = ((ServiceImportBean) element).isToCreate() ?
									AbstractPetalsServiceCreationWizardPage.this.checked
									: AbstractPetalsServiceCreationWizardPage.this.unchecked;
					}
					break;

				case 2:
					if( element instanceof ServiceImportBean ) {
						result = ((ServiceImportBean) element).isToOverwrite() ?
									AbstractPetalsServiceCreationWizardPage.this.checked
									: AbstractPetalsServiceCreationWizardPage.this.unchecked;
					}
					break;
				}

				return result;
			}


			@Override
			public String getColumnText( Object element, int columnIndex ) {
				String result = "";

				switch( columnIndex ) {
				case 0:
					if( element instanceof ServiceImportBean )
						result = ((ServiceImportBean) element).getProjectName();
					break;

				case 1:
				case 2:
					break;

				case 3:
					if( element instanceof SuImportBean )
						result = ((SuImportBean) element).getComponentName();
					break;

				case 4:
					if( element instanceof SuImportBean ) {
						result = ((SuImportBean) element).getComponentVersion();
						if( result == null )
							result = "";
					}
					break;
				}

				return result;
			}

			@Override
			public void addListener( ILabelProviderListener listener ) {
				// nothing
			}

			@Override
			public void dispose() {
				// nothing
			}

			@Override
			public boolean isLabelProperty( Object element, String property ) {
				return false;
			}

			@Override
			public void removeListener( ILabelProviderListener listener ) {
				// nothing
			}
		});


		// Define its sorter
		this.viewer.setSorter( new ViewerSorter() {
			@Override
			public int compare( Viewer viewer, Object e1, Object e2 ) {

				if( e1 instanceof ServiceImportBean && e2 instanceof ServiceImportBean ) {
					String n1 = ((ServiceImportBean) e1).getProjectName();
					if( n1 == null )
						n1 = "";

					String n2 = ((ServiceImportBean) e2).getProjectName();
					if( n2 == null )
						n2 = "";

					return n1.compareTo( n2 );
				}

				return super.compare( viewer, e1, e2 );
			}
		});


		// Create the editor for the versions (it must be done before creating the cell modifiers).
		// This is done this way to allow the editor to store and retrieve custom values (i.e. versions
		// that are not registered but manually entered by the user).
		final StringComboBoxCellEditor comboEditor = new StringComboBoxCellEditor( tree, DEFAULT_VERSIONS, SWT.DROP_DOWN );

		this.viewer.addSelectionChangedListener( new ISelectionChangedListener() {
			@Override
			public void selectionChanged( SelectionChangedEvent event ) {

				if( ! event.getSelection().isEmpty()) {
					Object o = ((IStructuredSelection) event.getSelection()).getFirstElement();
					if( o instanceof SuImportBean ) {

						// If there is a custom value, add it in the list
						SuImportBean suBean = (SuImportBean) o;
						List<String> versions = Arrays.asList( suBean.getSupportedVersions());
						versions = new ArrayList<String>( versions );
						if( ! versions.contains( suBean.getComponentVersion()))
							versions.add( suBean.getComponentVersion());

						String[] items = new String[ versions.size()];
						comboEditor.setItems( versions.toArray( items ));
					}
					else if( o instanceof SaImportBean )
						comboEditor.setItems( DEFAULT_VERSIONS );
				}
			}
		});


		// Define its cell modifier
		this.viewer.setCellModifier( new ICellModifier() {
			@Override
			public void modify( Object element, String property, Object value ) {

				TreeItem tableItem = (TreeItem) element;
				if( PROJECT_NAME.equals( property )) {
					ServiceImportBean bean = (ServiceImportBean) tableItem.getData();
					bean.setProjectName((String) value);
					AbstractPetalsServiceCreationWizardPage.this.viewer.refresh( bean );
					validate();

				} else if( CREATE.equals( property )) {
					ServiceImportBean bean = (ServiceImportBean) tableItem.getData();
					bean.setToCreate((Boolean) value);
					AbstractPetalsServiceCreationWizardPage.this.viewer.refresh( bean );
					validate();

				} else if( OVERWRITE.equals( property )) {
					ServiceImportBean bean = (ServiceImportBean) tableItem.getData();
					bean.setOverwrite((Boolean) value);
					AbstractPetalsServiceCreationWizardPage.this.viewer.refresh( bean );
					validate();

				}  else if( tableItem.getData() instanceof SuImportBean ) {
					if( COMPONENT_NAME.equals( property )) {
						SuImportBean bean = (SuImportBean) tableItem.getData();
						bean.setComponentName((String) value);
						AbstractPetalsServiceCreationWizardPage.this.viewer.refresh( bean );
						validate();

					} else if( COMPONENT_VERSION.equals( property )) {
						SuImportBean bean = (SuImportBean) tableItem.getData();
						bean.setComponentVersion((String) value);
						AbstractPetalsServiceCreationWizardPage.this.viewer.refresh( bean );
						validate();
					}
				}
			}

			@Override
			public Object getValue( Object element, String property ) {

				Object value = null;
				if( PROJECT_NAME.equals( property ))
					value = ((ServiceImportBean) element).getProjectName();

				else if( CREATE.equals( property ))
					value = Boolean.valueOf(((ServiceImportBean) element).isToCreate());

				else if( OVERWRITE.equals( property ))
					value = ((ServiceImportBean) element).isToOverwrite();

				else if( element instanceof SuImportBean ) {
					SuImportBean bean = (SuImportBean) element;
					if( COMPONENT_NAME.equals( property ))
						value = bean.getComponentName();
					else if( COMPONENT_VERSION.equals( property ))
						value = bean.getComponentVersion();
				}

				return value;
			}

			@Override
			public boolean canModify( Object element, String property ) {

				boolean canModify = true;
				if( element instanceof SaImportBean ) {
					canModify = PROJECT_NAME.equals( property ) ||
					CREATE.equals( property ) ||
					OVERWRITE.equals( property );
				}

				return canModify;
			}
		});


		// End up with the viewer properties
		this.viewer.setColumnProperties( new String[] {
					PROJECT_NAME, CREATE, OVERWRITE, COMPONENT_NAME, COMPONENT_VERSION });

		this.viewer.setCellEditors( new CellEditor[] {
					new TextCellEditor( tree ),
					new CheckboxCellEditor( tree ),
					new CheckboxCellEditor( tree ),
					new TextCellEditor( tree, SWT.READ_ONLY ),
					comboEditor });


		// Last steps
		if( ! this.importsBeans.isEmpty()) {
			this.viewer.setInput( this.importsBeans );
			this.viewer.expandAll();
		}

		useDefaultLocButton.notifyListeners( SWT.Selection, new Event());
		tree.notifyListeners( SWT.Selection, new Event());
		validate();
		setErrorMessage( null );
		setControl( container );

		// Force the shell size
		Point size = getShell().computeSize( 700, 600 );
		getShell().setSize( size );

		Rectangle rect = Display.getCurrent().getBounds();
		getShell().setLocation((rect.width - size.x) / 2, (rect.height - size.y) / 2);
	}


	/**
	 * Validates the page entries.
	 * @return true if the validation succeeded, false otherwise
	 */
	protected boolean validate() {

		// Project location
		if( ! this.isAtDefaultLocation ) {

			if( this.projectLocation.trim().length() == 0 ) {
				setErrorMessage( "The project(s) location is not specified." );
				setPageComplete( false );
				return false;
			}

			if( ! new File( this.projectLocation ).exists()) {
				setErrorMessage( "The project(s) location is not a valid directory." );
				setPageComplete( false );
				return false;
			}
		}

		// Validate the projects
		Set<String> names = new HashSet<String> ();
		for( SaImportBean saBean : this.importsBeans ) {
			if( ! validateServiceProject( saBean ))
				return false;

			for( SuImportBean suBean : saBean.getSuBeans()) {
				if( ! validateServiceProject( suBean ))
					return false;

				if( suBean.isToCreate()
						&& ! isNameUnique( suBean.getProjectName(), names ))
					return false;
			}

			if( saBean.isToCreate()
					&& ! isNameUnique( saBean.getProjectName(), names ))
				return false;
		}

		setErrorMessage( null );
		setPageComplete( true );
		return true;
	}


	/**
	 * Tests the uniqueness of the project names.
	 * @param name the name to check
	 * @param names the stores names
	 * @return true if this name is unique, false otherwise
	 */
	private boolean isNameUnique( String name, Set<String> names ) {

		boolean result = true;
		if( names.contains( name )) {
			setErrorMessage( "There is a conflict in the project names. " + name + " is already in the list." );
			result = false;

		} else {
			names.add( name );
		}

		setPageComplete( result );
		return result;
	}


	/**
	 * Validates the creation of a Petals service project.
	 * @param serviceImportBean a service import bean
	 * @return true if a project can be created from this bean, false otherwise
	 */
	private boolean validateServiceProject( ServiceImportBean serviceImportBean ) {

		boolean valid = true;
		if( serviceImportBean.isToCreate()) {
			IWorkspaceRoot wr = ResourcesPlugin.getWorkspace().getRoot();

			// Existing project
			IProject p = wr.getProject( serviceImportBean.getProjectName());
			if( p.exists() && serviceImportBean.isToCreate() && ! serviceImportBean.isToOverwrite()) {
				setErrorMessage( "The project " + serviceImportBean.getProjectName() + " already exists." );
				setPageComplete( false );
				valid = false;
			}

			// Existing target location ?
			if( ! this.isAtDefaultLocation ) {
				File f = new File( this.projectLocation, serviceImportBean.getProjectName());
				if( f.exists()) {
					setErrorMessage( "A file already exists at the target location for " + serviceImportBean.getProjectName() + "." );
					setPageComplete( false );
					valid = false;
				}
			}
		}

		return valid;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.WizardPage
	 * #isPageComplete()
	 */
	@Override
	public boolean isPageComplete() {
		return this.complete;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.WizardPage
	 * #setPageComplete(boolean)
	 */
	@Override
	public void setPageComplete( boolean complete ) {
		this.complete = complete;
		super.setPageComplete( complete );
	}


	/**
	 * @return the importsBeans
	 */
	public List<SaImportBean> getImportsBeans() {
		return this.importsBeans;
	}


	/**
	 * Adds import beans in the list managed by this class.
	 * <p>
	 * After insertion, the viewer is refreshed and expanded.
	 * </p>
	 *
	 * @param importBeans the import beans to set (may be null)
	 * @param clearBeforeInsertion true to empty the current list before insertion
	 */
	public void updateImportBeans( List<SaImportBean> importBeans, boolean clearBeforeInsertion ) {

		if( clearBeforeInsertion )
			this.importsBeans.clear();

		if( importBeans != null )
			this.importsBeans.addAll( importBeans );

		if( this.viewer != null ) {
			this.viewer.setInput( importBeans );
			this.viewer.refresh();
			this.viewer.expandAll();
		}
	}


	/**
	 * @return the isAtDefaultLocation
	 */
	public boolean isAtDefaultLocation() {
		return this.isAtDefaultLocation;
	}


	/**
	 * @return the projectLocation
	 */
	public String getProjectLocation() {
		return this.projectLocation;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage#dispose()
	 */
	@Override
	public void dispose() {

		if( this.viewer.getLabelProvider() != null )
			this.viewer.getLabelProvider().dispose();

		if( this.suImg != null )
			this.suImg.dispose();

		if( this.saImg != null )
			this.saImg.dispose();

		if( this.checked != null )
			this.checked.dispose();

		if( this.unchecked != null )
			this.unchecked.dispose();

		releaseResources();
		super.dispose();
	}


	/**
	 * Releases additional resources.
	 */
	protected abstract void releaseResources();


	/**
	 * Creates the widgets to show before the project location UI part.
	 * @param container
	 */
	protected abstract void createWidgetsBeforeProjectLocation( Composite container );

}
