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

package com.ebmwebsourcing.petals.services.eip;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.XmlElement;
import com.ebmwebsourcing.petals.services.su.utils.XsdUtils;
import com.ebmwebsourcing.petals.services.su.wizards.SuMainConstants;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.SerializationItem;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.XsdBasedAbstractSuPage;

/**
 * The EIP plug-in specific page (added just before the CDK page).
 * TODO: reorganize this class and its sub-classes to have a clean solution.
 * 
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipConsumesPage extends AbstractSuPage {

	/** The viewer which contains the consumed projects. */
	protected ListViewer viewer;

	/** */
	protected List<EipConsumeDataBean> consumes = new ArrayList<EipConsumeDataBean> ();

	/** The Add button. */
	protected Button addButton;

	/** The label showing the selected pattern. */
	private Label chosenPatternLabel;

	/** Keep track of the selected pattern (it may change in the case where the user goes back in the wizard). */
	protected String selectedPattern;



	/**
	 * Empty constructor (for instantiation from the plug-in registry).
	 */
	public EipConsumesPage() {
		super( "CustomEipPage", "", "" );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #validate()
	 */
	@Override
	public boolean validate() {

		updateConditions();
		this.addButton.setEnabled( canAddNewItem());

		// Page is valid if...
		// --> the number of elements respects the chosen pattern.
		if( this.selectedPattern == null ) {
			updateStatus( "No pattern was selected." );
			return false;
		}
		int size = this.consumes.size();

		// Aggregator: only 1 consume.
		if( this.selectedPattern.equals( "aggregator" ) && size != 1 ) {
			updateStatus( "Agregator pattern requires one and only one consume." );
			return false;
		}

		// Scatter-gather: no restriction.
		if( this.selectedPattern.equals( "scatter-gather" ) && size < 1 ) {
			updateStatus( "Scatter-gather pattern requires at least one consume." );
			return false;
		}

		// Router: no restriction.
		if( this.selectedPattern.equals( "router" ) && size < 1 ) {
			updateStatus( "Router pattern requires at least one consume." );
			return false;
		}

		// Dynamic-router: no restriction.
		if( this.selectedPattern.equals( "dynamic-router" ) && size < 2 ) {
			updateStatus( "Dynamic-router pattern requires at least two consumes." );
			return false;
		}

		// Dispatcher: no restriction.
		if( this.selectedPattern.equals( "dispatcher" ) && size < 1 ) {
			updateStatus( "Dispatcher pattern requires at least one consume." );
			return false;
		}

		// Routing-slip: no restriction.
		if( this.selectedPattern.equals( "routing-slip" ) && size < 1 ) {
			updateStatus( "Routing-slip pattern requires at least one consume." );
			return false;
		}

		// Wire-tap: exactly 2 consumes.
		if( this.selectedPattern.equals( "wire-tap" ) && size != 2 ) {
			updateStatus( "Wire-tap pattern requires exactly 2 consumes." );
			return false;
		}

		// Bridge: only 1 consume.
		if( this.selectedPattern.equals( "bridge" ) && size != 1 ) {
			updateStatus( "Bridge pattern requires one and only one consume." );
			return false;
		}

		// Splitter: only 1 consume.
		if( this.selectedPattern.equals( "splitter" ) && size != 1 ) {
			updateStatus( "Bridge pattern requires one and only one consume." );
			return false;
		}

		// --> each element in the table has defined a correct condition (depends on the chosen pattern).
		for( int i=0; i<size; i++ ) {
			EipConsumeDataBean bean = this.consumes.get( i );
			if( bean == null
						|| bean.getInterfaceName() == null
						|| "".equals(  bean.getInterfaceName())) {

				updateStatus( "The item at line " + (i+1) + " did not define a project to consume." );
				return false;
			}

			if( bean.mustHaveCondition() && ! bean.hasCondition()) {
				updateStatus( "The item at line " + (i+1) + " has an empty condition." );
				return false;
			}
		}

		updateStatus( null );
		return true;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #fillInData(com.ebmwebsourcing.petals.tools.eclipse.su.main.wizards.generation.EclipseSuBean)
	 */
	@Override
	public void fillInData( EclipseSuBean eclipseSuBean ) {

		StringBuilder sb = new StringBuilder();
		int position = 0;
		for( EipConsumeDataBean bean : this.consumes ) {
			if( bean == null )
				continue;

			if( bean.mustHaveCondition ())
				sb.append( bean.getCondition() + ";;" );

			// Change comment of the first consume in case of "dynamic-router".
			if( position == 0 && "dynamic-router".equals( this.selectedPattern ))
				bean.setComment( "( Service to Test )" );

			// Change comment of the last consume in case of "(dynamic-)router".
			if( position + 1 == this.consumes.size() &&
						( "dynamic-router".equals( this.selectedPattern ) || "router".equals( this.selectedPattern )))
				bean.setComment( "( Default )" );

			position++;
		}

		// Consumes.
		eclipseSuBean.customObjects.put( "EipVersion22", this.consumes );

		// Conditions.
		// Find "condition" SerializationItem. - There should be only one.
		List<XmlElement> conditionElements =
			XsdUtils.findXmlElement( "eip:test", eclipseSuBean.specificElements );

		if( conditionElements.size() == 1 ) {
			XmlElement xmlElement = conditionElements.get( 0 );
			List<XmlElement> replacingElements =
				XsdUtils.createMultipleXmlElements( xmlElement, sb.toString(), getFileImportManager());

			int index = eclipseSuBean.specificElements.indexOf( xmlElement );
			eclipseSuBean.specificElements.remove( xmlElement );
			eclipseSuBean.specificElements.addAll( index, replacingElements );
		}
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.AbstractSuPage
	 * #setHelpContextId(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void setHelpContextId(Composite container) {
		// Nothing.
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {
		Composite container = new Composite( parent, SWT.NONE );

		// Set help link for documentation page.
		setHelpContextId( container );

		GridLayout layout = new GridLayout( 2, false );
		layout.marginLeft = layout.marginRight = 15;
		layout.marginTop = 10;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		// Remind the chosen pattern.
		this.chosenPatternLabel = new Label( container, SWT.NONE );
		GridData layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		this.chosenPatternLabel.setLayoutData( layoutData );
		this.chosenPatternLabel.setText( "Selected Pattern:" );

		// Create the table viewer.
		createViewer( container );

		// Buttons on the right.
		Composite buttonsContainer = new Composite( container, SWT.NONE );
		layout = new GridLayout();
		layout.marginLeft = layout.marginTop = 5;
		layout.marginHeight = 0;
		buttonsContainer.setLayout( layout );
		buttonsContainer.setLayoutData( new GridData( SWT.RIGHT, SWT.TOP, false, true ));

		// "add", "remove", "up", "down"
		this.addButton = new Button( buttonsContainer, SWT.PUSH );
		this.addButton.setText( "&Add" );
		this.addButton.setLayoutData( new GridData( SWT.FILL, SWT.TOP, true, false ));
		this.addButton.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				EipConsumeDataBean bean = new EipConsumeDataBean( EipConsumesPage.this.suType );
				EipConsumesPage.this.consumes.add( bean );

				EipConsumesPage.this.viewer.refresh( true );
				EipConsumesPage.this.viewer.setSelection( new StructuredSelection( bean ));
				validate();
			}
		});
		this.addButton.addPaintListener( new PaintListener() {	// Bad performances !
			public void paintControl(PaintEvent arg0) {
				updateSelectedPattern();
			}
		});

		Button b = new Button( buttonsContainer, SWT.PUSH );
		b.setText( "&Remove" );
		b.setLayoutData( new GridData( SWT.FILL, SWT.TOP, true, false ));
		b.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if( EipConsumesPage.this.viewer.getSelection().isEmpty())
					return;

				Object o = ((IStructuredSelection) EipConsumesPage.this.viewer.getSelection()).getFirstElement();
				EipConsumeDataBean bean = (EipConsumeDataBean) o;
				int index = EipConsumesPage.this.consumes.indexOf( bean );
				EipConsumesPage.this.consumes.remove( bean );

				EipConsumesPage.this.viewer.refresh( true );
				if( index > 0 ) {
					EipConsumeDataBean beanBefore = EipConsumesPage.this.consumes.get( index - 1 );
					EipConsumesPage.this.viewer.setSelection( new StructuredSelection( beanBefore ));
				}
				else if( EipConsumesPage.this.consumes.size() > 0 ) {
					EipConsumeDataBean beanAfter = EipConsumesPage.this.consumes.get( 0 );
					EipConsumesPage.this.viewer.setSelection( new StructuredSelection( beanAfter ));
				}
				validate();
			}
		});

		b = new Button( buttonsContainer, SWT.PUSH );
		b.setText( "&Edit" );
		b.setLayoutData( new GridData( SWT.FILL, SWT.TOP, true, false ));
		b.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				openDialog();
			}
		});

		b = new Button( buttonsContainer, SWT.PUSH );
		b.setText( "Move &Up" );
		b.setLayoutData( new GridData( SWT.FILL, SWT.TOP, true, false ));
		b.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if( EipConsumesPage.this.viewer.getSelection().isEmpty())
					return;

				Object o = ((IStructuredSelection) EipConsumesPage.this.viewer.getSelection()).getFirstElement();
				EipConsumeDataBean bean = (EipConsumeDataBean) o;
				int index = EipConsumesPage.this.consumes.indexOf( bean );

				if( index > 0 ) {
					EipConsumesPage.this.consumes.remove( bean );
					EipConsumesPage.this.consumes.add( index - 1, bean );
					EipConsumesPage.this.viewer.refresh( true );
				}

				EipConsumesPage.this.viewer.setSelection( new StructuredSelection( bean ));
				validate();
			}
		});

		b = new Button( buttonsContainer, SWT.PUSH );
		b.setText( "Move &Down" );
		b.setLayoutData( new GridData( SWT.FILL, SWT.TOP, true, false ));
		b.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if( EipConsumesPage.this.viewer.getSelection().isEmpty())
					return;

				Object o = ((IStructuredSelection) EipConsumesPage.this.viewer.getSelection()).getFirstElement();
				EipConsumeDataBean bean = (EipConsumeDataBean) o;
				int index = EipConsumesPage.this.consumes.indexOf( bean );

				if( index < EipConsumesPage.this.consumes.size() - 1 ) {
					EipConsumesPage.this.consumes.remove( bean );
					EipConsumesPage.this.consumes.add( index + 1, bean );
					EipConsumesPage.this.viewer.refresh( true );
				}

				EipConsumesPage.this.viewer.setSelection( new StructuredSelection( bean ));
				validate();
			}
		});

		// Set the control.
		setControl( container );
		setPageComplete( false );
	}


	/**
	 * The two parameters can be used to evaluate the position of the selected item in the list.
	 * This helps us to determine whether the "condition" widget should be enabled or not.
	 * 
	 * @param index the index of the selected element (the one which has to be edited).
	 * @return
	 */
	protected boolean enableCondition( int index ) {

		if( this.selectedPattern == null )
			return false;

		// Router: last item has no condition.
		if( this.selectedPattern.equals( "router" ))
			return index < this.consumes.size() - 1;

		// Dynamic-router: first and last items have no condition.
		if( this.selectedPattern.equals( "dynamic-router" ))
			return index > 0 && index < this.consumes.size() - 1;

			// Aggregator: one consume and it needs a test
			if( "aggregator".equals( this.selectedPattern ))
				return true;

			// Splitter: one consume and it needs a test
			if( "splitter".equals( this.selectedPattern ))
				return true;

			return false;
	}


	/**
	 * Opens the edit dialog to fill in the fields of a consumed project.
	 * Called when double-clicking on the list or when pushing <b>Edit</b> button.
	 */
	protected void openDialog() {

		if( this.viewer.getSelection().isEmpty())
			return;

		Object o = ((IStructuredSelection) this.viewer.getSelection()).getFirstElement();
		EipConsumeDataBean bean = (EipConsumeDataBean) o;
		int index = this.consumes.indexOf( bean );

		// Enable the condition ?
		updateConditions();

		// Open dialog.
		EipConsumeDialog dialog = new EipConsumeDialog(	index, bean, this );
		if( dialog.open() == Window.OK ) {
			EipConsumeDataBean consumeBean = dialog.getData();
			this.consumes.remove( index );
			this.consumes.add( index, consumeBean );

			this.viewer.refresh( true );
			this.viewer.setSelection( new StructuredSelection( consumeBean ));
			validate();
		}
	}


	/**
	 * Compute whether a new item can be added in the list of consumes.
	 * This method is used to determine whether the Add button should be enabled or not.
	 * @return true if it can be added, false otherwise.
	 */
	protected boolean canAddNewItem() {

		int size = this.consumes.size();
		if( this.selectedPattern == null )
			return true;

		// Aggregator: only 1 consume.
		if( this.selectedPattern.equals( "aggregator" ))
			return size < 1;

		// Wire-tap: exactly 2 consumes.
		if( this.selectedPattern.equals( "wire-tap" ))
			return size < 2;

		// Bridge: only 1 consume.
		if( this.selectedPattern.equals( "bridge" ))
			return size < 1;

		// Bridge: only 1 consume.
		if( this.selectedPattern.equals( "splitter" ))
			return size < 1;

		// True otherwise.
		return true;
	}


	/**
	 * Update the selected EIP pattern on the previous page.
	 */
	private void updateSelectedPattern() {

		IWizardPage previousPage =
			getWizard().getPage( SuMainConstants.PAGE_SPECIFIC_JBI_DATA );

		// Version 2.4
		SerializationItem key = new SerializationItem();
		key.setXsdName( "eip:eip" );

		if( previousPage instanceof XsdBasedAbstractSuPage ) {
			try {
				String eipPattern = ((XsdBasedAbstractSuPage) previousPage).getValues().get( key );
				eipPattern = eipPattern != null ? eipPattern : "";

				// Not initialized => initialize it.
				if( this.selectedPattern == null ) {
					this.selectedPattern = eipPattern;
					this.chosenPatternLabel.setText( "Selected Pattern: " + eipPattern );
					this.chosenPatternLabel.getParent().layout();
				}

				// Already initialized but value changed.
				else if( !this.selectedPattern.equals( eipPattern )) {
					this.selectedPattern = eipPattern;
					this.chosenPatternLabel.setText( "Selected Pattern: " + this.selectedPattern );
					this.chosenPatternLabel.getParent().layout();
					validate();
				}
			} catch( Exception e ) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * Update the conditions.
	 * This is based on the selected pattern on the previous page and on the position
	 * of the items in the list of consumes.
	 * 
	 * This method does not modify the EIpConsumeDataBean associated with every item.
	 */
	protected void updateConditions() {

		for( int i=0; i<this.consumes.size(); i++ ) {
			EipConsumeDataBean bean = this.consumes.get( i );
			boolean conditionIsEnabled = enableCondition( i );

			if( bean != null )
				bean.setMustHaveCondition( conditionIsEnabled );
		}
	}


	/**
	 * Create the viewer containing the consumed projects.
	 * @param parent
	 */
	private void createViewer( Composite parent ) {
		this.viewer = new ListViewer( parent, SWT.SINGLE | SWT.BORDER );
		this.viewer.setContentProvider( new IStructuredContentProvider() {
			public void dispose() {}
			public void inputChanged( Viewer arg0, Object arg1, Object arg2 ) {}

			public Object[] getElements( Object input ) {
				return ((List<?>) input).toArray();
			}
		});

		this.viewer.setLabelProvider( new LabelProvider() {
			@Override
			public Image getImage(Object element) {
				return null;
			}

			@Override
			public String getText( Object element ) {
				EipConsumeDataBean bean = (EipConsumeDataBean) element;

				int position = -1;
				int currentPos = 0;
				loop: for( EipConsumeDataBean consumed : EipConsumesPage.this.consumes ) {
					if( consumed != bean )
						currentPos++;
					else {
						position = currentPos;
						break loop;
					}
				}
				String lineNumber = position != -1 ? "" + (position + 1) : "";
				String serviceName = bean.getServiceName() != null ? bean.getServiceName() : "SERVICE NAME";
				String interfaceName = bean.getInterfaceName() != null ? bean.getInterfaceName() : "Interface Name";

				return lineNumber + ". < " + interfaceName + "> : " + serviceName;
			}
		});
		this.viewer.setInput( this.consumes );

		GridData data = new GridData( SWT.FILL, SWT.FILL, true, true );
		data.heightHint = 50;
		data.verticalIndent = 5;
		this.viewer.getList().setLayoutData( data );

		// Double-click listener.
		this.viewer.getList().addMouseListener( new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				openDialog();
			}
		});
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
}
