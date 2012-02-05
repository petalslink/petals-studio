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
package com.ebmwebsourcing.petals.services.su.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.DefaultTreeContentProvider;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.explorer.SourceManager;
import com.ebmwebsourcing.petals.services.explorer.model.EndpointBean;
import com.ebmwebsourcing.petals.services.explorer.model.ServiceUnitBean;
import com.ebmwebsourcing.petals.services.explorer.sources.EndpointSource;
import com.ebmwebsourcing.petals.services.utils.ConsumeUtils;

/**
 * A dialog to select a service to consume and an operation to invoke.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EnhancedConsumeDialog extends TitleAreaDialog {

	public final static QName NO_OPERATION = new QName( "http://petals.ow2.org/studio/", "no-operation" );
	private final static String WILDCARD = "*";
	private final static String DEFAULT_MSG = "Select a Petals service and one of its operations to invoke.";

	private final FormToolkit toolkit;
	private final Image edptImage, opImage, itfImage, srvImage;

	private QName itfToInvoke, srvToInvoke;
	private String edptToInvoke;
	private QName operationToInvoke;
	private Mep invocationMep;
	private boolean needOperation = true;

	private String filterItfName, filterItfNs;
	private String filterSrvName, filterSrvNs, filterEdpt, filterComp;
	private List<Mep> constrainedMep;


	/**
	 * Constructor.
	 * @param parentShell
	 */
	public EnhancedConsumeDialog( Shell parentShell ) {
		this( parentShell, null );
	}


	/**
	 * Constructor.
	 * @param parentShell
	 * @param toolkit
	 */
	public EnhancedConsumeDialog( Shell parentShell, FormToolkit toolkit ) {
		super( parentShell );
		if( toolkit != null )
			this.toolkit = toolkit;
		else
			this.toolkit = new FormToolkit( parentShell.getDisplay());

		setShellStyle( SWT.PRIMARY_MODAL | SWT.DIALOG_TRIM | SWT.RESIZE | SWT.MAX );
		this.itfImage = PetalsServicesPlugin.loadImage( "icons/obj16/contract.gif" );
		this.srvImage = PetalsServicesPlugin.loadImage( "icons/obj16/service.gif" );
		this.edptImage = PetalsServicesPlugin.loadImage( "icons/obj16/Endpoint_3.gif" );
		this.opImage = PetalsServicesPlugin.loadImage( "icons/obj16/operation_2.gif" );
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.dialogs.TrayDialog
	 * #close()
	 */
	@Override
	public boolean close() {

		boolean result = super.close();
		if( this.edptImage != null && ! this.edptImage.isDisposed())
			this.edptImage.dispose();

		if( this.srvImage != null && ! this.srvImage.isDisposed())
			this.srvImage.dispose();

		if( this.itfImage != null && ! this.itfImage.isDisposed())
			this.itfImage.dispose();

		if( this.opImage != null && ! this.opImage.isDisposed())
			this.opImage.dispose();

		return result;
	}



	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog
	 * #createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea( final Composite parent ) {

		// General properties
		getShell().setText( "Consume a Petals Service" );
		setTitle( "Consume a Petals Service" );
		setMessage( DEFAULT_MSG );

		Composite outterComposite = new Composite( parent, SWT.BORDER );
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		outterComposite.setLayout( layout );
		outterComposite.setLayoutData( new GridData( GridData.FILL_BOTH ));

		ScrolledForm form = this.toolkit.createScrolledForm( outterComposite );
		form.setLayoutData( new GridData( GridData.FILL_BOTH ));

		Composite container = form.getBody();
		TableWrapLayout tableWrapLayout = new TableWrapLayout();
		tableWrapLayout.topMargin = 12;
		layout = new GridLayout();
		layout.verticalSpacing = 9;
		layout.marginTop = 7;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));


		// Create the search filter
		Section filterSection = this.toolkit.createSection( container,
					ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE | Section.DESCRIPTION );
		filterSection.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		filterSection.clientVerticalSpacing = 10;
		filterSection.setText( "Search Filters" );
		filterSection.setDescription( "Filter the displayed services." );

		Composite subContainer = this.toolkit.createComposite( filterSection );
		layout = new GridLayout( 4, false );
		layout.marginWidth = 0;
		layout.marginBottom = 10;
		layout.horizontalSpacing = 10;
		subContainer.setLayout( layout );
		subContainer.setLayoutData( new TableWrapData( TableWrapData.FILL_GRAB ));
		filterSection.setClient( subContainer );

		this.toolkit.createLabel( subContainer, "Interface Name:" );
		final Text itfNameText = this.toolkit.createText( subContainer, "", SWT.BORDER | SWT.SINGLE );
		itfNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		itfNameText.setText( this.filterItfName == null ? WILDCARD : this.filterItfName );

		this.toolkit.createLabel( subContainer, "Interface Namespace:" );
		final Text itfNsText = this.toolkit.createText( subContainer, "", SWT.BORDER | SWT.SINGLE );
		itfNsText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		itfNsText.setText( this.filterItfNs == null ? WILDCARD : this.filterItfNs );

		this.toolkit.createLabel( subContainer, "Service Name:" );
		final Text srvNameText = this.toolkit.createText( subContainer, "", SWT.BORDER | SWT.SINGLE );
		srvNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		srvNameText.setText( this.filterSrvName == null ? WILDCARD : this.filterSrvName );

		this.toolkit.createLabel( subContainer, "Service Namespace:" );
		final Text srvNsText = this.toolkit.createText( subContainer, "", SWT.BORDER | SWT.SINGLE );
		srvNsText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		srvNsText.setText( this.filterSrvNs == null ? WILDCARD : this.filterSrvNs );

		this.toolkit.createLabel( subContainer, "End-point Name:" );
		final Text edptNameText = this.toolkit.createText( subContainer, "", SWT.BORDER | SWT.SINGLE );
		edptNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		edptNameText.setText( this.filterEdpt == null ? WILDCARD : this.filterEdpt );

		this.toolkit.createLabel( subContainer, "Target Component:" );
		final Text compText = this.toolkit.createText( subContainer, "", SWT.BORDER | SWT.SINGLE );
		compText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		compText.setText( this.filterComp == null ? WILDCARD : this.filterComp );


		// The tree to list all the services
		Composite bottomComposite = this.toolkit.createComposite( container );
		layout = new GridLayout( 2, true );
		layout.marginWidth = 0;
		bottomComposite.setLayout( layout );
		bottomComposite.setLayoutData( new GridData( GridData.FILL_BOTH ));

		Section section = this.toolkit.createSection( bottomComposite,
					ExpandableComposite.TITLE_BAR | ExpandableComposite.EXPANDED | Section.DESCRIPTION );
		section.setLayoutData( new GridData( GridData.FILL_BOTH ));
		section.clientVerticalSpacing = 10;
		section.setText( "Available Services" );
		section.setDescription( "A list of all the known Petals services." );

		subContainer = this.toolkit.createComposite( section );
		layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		subContainer.setLayout( layout );
		subContainer.setLayoutData( new TableWrapData( TableWrapData.FILL ));
		section.setClient( subContainer );

		Tree tree = this.toolkit.createTree( subContainer, SWT.BORDER | SWT.HIDE_SELECTION | SWT.FULL_SELECTION );
		GridData layoutData = new GridData( GridData.FILL_BOTH );
		layoutData.widthHint = 400;
		layoutData.heightHint = 400;
		tree.setLayoutData( layoutData );

		final TreeViewer treeViewer = new TreeViewer( tree );
		treeViewer.setContentProvider( new ServiceContentProvider());
		treeViewer.setLabelProvider( new ServiceLabelProvider());
		treeViewer.addFilter( new ServiceViewerFilter());
		ColumnViewerToolTipSupport.enableFor( treeViewer, ToolTip.NO_RECREATE );


		// Prepare the input...
		Map<QName,ItfBean> itfNameToInterface = new HashMap<QName,ItfBean> ();
		for( EndpointSource src : SourceManager.getInstance().getSources()) {
			for( ServiceUnitBean su : src.getServiceUnits()) {
				for( EndpointBean bean : su.getEndpoints()) {

					// Handle the interface name
					ItfBean itfBean = itfNameToInterface.get( bean.getInterfaceName());
					if( itfBean == null ) {
						itfBean = new ItfBean();
						itfBean.itfName = bean.getInterfaceName();
						itfNameToInterface.put( itfBean.itfName, itfBean );
					}

					// Handle the service name
					SrvBean srvBean = itfBean.srvNameToService.get( bean.getServiceName());
					if( srvBean == null ) {
						srvBean = new SrvBean();
						srvBean.itf = itfBean;
						srvBean.srvName = bean.getServiceName();
						itfBean.srvNameToService.put( srvBean.srvName, srvBean );
					}

					// Handle the end-point name
					EdptBean edptBean = new EdptBean();
					edptBean.edptBean = bean;
					srvBean.endpoints.add( edptBean );
				}
			}
		}

		// ... and set it!
		treeViewer.setInput( itfNameToInterface );


		// The properties of the selection
		final Composite leftComposite = this.toolkit.createComposite( bottomComposite );
		leftComposite.setLayoutData( new GridData( GridData.FILL_BOTH ));
		layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		leftComposite.setLayout( layout );


		// Show a default widget on the left (waiting for a new selection)
		// It will be deleted as soon as a selection is made in the tree
		section = this.toolkit.createSection( leftComposite, ExpandableComposite.TITLE_BAR | ExpandableComposite.EXPANDED );
		section.setLayoutData( new GridData( GridData.FILL_BOTH ));
		section.clientVerticalSpacing = 10;
		section.setText( "Properties" );

		final Composite propertiesComposite = this.toolkit.createComposite( section );
		layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 10;
		layout.verticalSpacing = 2;
		propertiesComposite.setLayout( layout );
		propertiesComposite.setLayoutData( new TableWrapData( TableWrapData.FILL_GRAB ));
		section.setClient( propertiesComposite );

		this.toolkit.createLabel( propertiesComposite, "Select a service identifier in the tree on the left." );
		this.toolkit.createLabel( propertiesComposite, "Its properties will be displayed here." );

		// Listeners
		ModifyListener modifyListener = new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {

				String value = ((Text) e.widget).getText().trim();
				if( e.widget == itfNameText )
					EnhancedConsumeDialog.this.filterItfName = value;
				else if( e.widget == itfNsText )
					EnhancedConsumeDialog.this.filterItfNs = value;
				else if( e.widget == srvNameText )
					EnhancedConsumeDialog.this.filterSrvName = value;
				else if( e.widget == srvNsText )
					EnhancedConsumeDialog.this.filterSrvNs = value;
				else if( e.widget == edptNameText )
					EnhancedConsumeDialog.this.filterEdpt = value;
				else if( e.widget == compText )
					EnhancedConsumeDialog.this.filterComp = value;

				treeViewer.refresh();
			}
		};

		itfNameText.addModifyListener( modifyListener );
		itfNsText.addModifyListener( modifyListener );
		srvNameText.addModifyListener( modifyListener );
		srvNsText.addModifyListener( modifyListener );
		edptNameText.addModifyListener( modifyListener );
		compText.addModifyListener( modifyListener );

		treeViewer.addSelectionChangedListener( new ISelectionChangedListener() {
			@Override
			public void selectionChanged( SelectionChangedEvent event ) {

				EnhancedConsumeDialog.this.operationToInvoke = null;
				EnhancedConsumeDialog.this.invocationMep = null;
				EnhancedConsumeDialog.this.itfToInvoke = null;
				EnhancedConsumeDialog.this.srvToInvoke = null;
				EnhancedConsumeDialog.this.edptToInvoke = null;

				handleSelection( event, leftComposite );
				validate();
				parent.layout();
			}
		});

		if( ! StringUtils.isEmpty( this.filterItfName )
					|| ! StringUtils.isEmpty( this.filterItfNs )
					|| ! StringUtils.isEmpty( this.filterSrvName )
					|| ! StringUtils.isEmpty( this.filterSrvNs )
					|| ! StringUtils.isEmpty( this.filterEdpt )
					|| ! StringUtils.isEmpty( this.filterComp ))
			filterSection.setExpanded( true );

		return container;
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.dialogs.TrayDialog
	 * #createButtonBar(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createButtonBar( Composite parent ) {

		Composite comp = (Composite) super.createButtonBar( parent );
		comp.setBackground( parent.getDisplay().getSystemColor( SWT.COLOR_WHITE ));

		Button okButton = getButton( IDialogConstants.OK_ID );
		if( okButton != null )
			okButton.getParent().setBackground( parent.getDisplay().getSystemColor( SWT.COLOR_WHITE ));

		return comp;
	}


	/**
	 * Handles the selection in the service hierarchy.
	 * @param event the selection event
	 * @param parent the composite where new widgets should be added
	 */
	private void handleSelection( SelectionChangedEvent event, Composite parent ) {

		Object o = ((IStructuredSelection) event.getSelection()).getFirstElement();
		for( Control c : parent.getChildren())
			c.dispose();

		Section section = this.toolkit.createSection( parent,
					ExpandableComposite.TITLE_BAR | ExpandableComposite.EXPANDED | Section.DESCRIPTION );
		section.setLayoutData( new GridData( GridData.FILL_BOTH ));
		section.clientVerticalSpacing = 10;
		section.setText( "Properties" );
		section.setDescription( "The properties of the selection." );

		final Composite propertiesComposite = this.toolkit.createComposite( section );
		GridLayout layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 10;
		propertiesComposite.setLayout( layout );
		propertiesComposite.setLayoutData( new TableWrapData( TableWrapData.FILL_GRAB ));
		section.setClient( propertiesComposite );


		// What do we have?
		if( o instanceof ItfBean )
			this.itfToInvoke = ((ItfBean) o).itfName;

		if( o instanceof SrvBean ) {
			this.srvToInvoke = ((SrvBean) o).srvName;
			this.itfToInvoke = ((SrvBean) o).itf.itfName;
		}

		EndpointBean bean = null;
		if( o instanceof EdptBean ) {
			bean = ((EdptBean) o).edptBean;
			this.edptToInvoke = bean.getEndpointName();
			this.srvToInvoke = bean.getServiceName();
			this.itfToInvoke = bean.getInterfaceName();
		}


		// Show what we can
		if( this.itfToInvoke != null ) {
			this.toolkit.createLabel( propertiesComposite, "Interface Name:" );
			Text text = this.toolkit.createText( propertiesComposite, this.itfToInvoke.getLocalPart(), SWT.READ_ONLY );
			text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
			this.toolkit.createLabel( propertiesComposite, "Interface Namespace:" );
			text = this.toolkit.createText( propertiesComposite, this.itfToInvoke.getNamespaceURI(), SWT.READ_ONLY );
			text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		}

		if( this.srvToInvoke != null ) {
			this.toolkit.createLabel( propertiesComposite, "Service Name:" );
			Text text = this.toolkit.createText( propertiesComposite, this.srvToInvoke.getLocalPart(), SWT.READ_ONLY );
			text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
			this.toolkit.createLabel( propertiesComposite, "Service Namespace:" );
			text = this.toolkit.createText( propertiesComposite, this.srvToInvoke.getNamespaceURI(), SWT.READ_ONLY );
			text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		}

		if( bean != null ) {
			this.toolkit.createLabel( propertiesComposite, "End-point Name:" );
			Text text = this.toolkit.createText( propertiesComposite, bean.getEndpointName(), SWT.READ_ONLY );
			text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

			this.toolkit.createLabel( propertiesComposite, "Target Component:" );
			String value = bean.getComponentName() != null ? bean.getComponentName() : "";
			text = this.toolkit.createText( propertiesComposite, value, SWT.READ_ONLY );
			text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		}


		// Show operations
		section = this.toolkit.createSection(
					parent,
					ExpandableComposite.TITLE_BAR | ExpandableComposite.EXPANDED | Section.DESCRIPTION );
		section.setLayoutData( new GridData( GridData.FILL_BOTH ));
		section.clientVerticalSpacing = 10;
		section.setText( "Operations" );
		section.setDescription( "The operations for the selected service's end-point." );

		final Composite subContainer = this.toolkit.createComposite( section );
		layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 10;
		subContainer.setLayout( layout );
		subContainer.setLayoutData( new TableWrapData( TableWrapData.FILL_GRAB ));
		section.setClient( subContainer );

		final Table table = this.toolkit.createTable( subContainer, SWT.BORDER | SWT.SINGLE );
		table.setLayoutData( new GridData( GridData.FILL_BOTH ));
		Map<QName,Mep> ops = ConsumeUtils.getValidOperationsForConsume( this.itfToInvoke, this.srvToInvoke, this.edptToInvoke );
		for( Map.Entry<QName,Mep> entry : ops.entrySet()) {
			TableItem item = new TableItem( table, SWT.NONE );
			item.setText( entry.getKey().getLocalPart() + " - " + entry.getValue());
			item.setImage( this.opImage );
			item.setData( entry );
		}

		table.addSelectionListener( new SelectionListener() {
			@Override
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {
				TableItem[] items = table.getSelection();
				if( items != null && items.length > 0 ) {
					Map.Entry<?,?> entry = (Map.Entry<?,?>) items[ 0 ].getData();
					EnhancedConsumeDialog.this.operationToInvoke = (QName) entry.getKey();
					EnhancedConsumeDialog.this.invocationMep = (Mep) entry.getValue();
					validate();
				}
			}
		});

		final Button b = this.toolkit.createButton( subContainer, "Let the component choose the operation to invoke.", SWT.CHECK );
		b.addSelectionListener( new SelectionListener() {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				EnhancedConsumeDialog.this.needOperation = ! b.getSelection();
				table.setEnabled( EnhancedConsumeDialog.this.needOperation );
				if( ! EnhancedConsumeDialog.this.needOperation ) {
					EnhancedConsumeDialog.this.operationToInvoke = null;
					EnhancedConsumeDialog.this.invocationMep = null;
				}

				validate();
			}

			@Override
			public void widgetDefaultSelected( SelectionEvent e ) {
				widgetSelected( e );
			}
		});

		b.setSelection( ! this.needOperation );
		table.setEnabled( this.needOperation );
		parent.layout();
	}


	/**
	 * @param filterItfName the filterItfName to set
	 */
	public void setFilterItfName( String filterItfName ) {
		this.filterItfName = filterItfName;
	}


	/**
	 * @return the constrainedMep
	 */
	public List<Mep> getConstrainedMep() {
		return this.constrainedMep;
	}


	/**
	 * @param constrainedMep the constrainedMep to set
	 */
	public void setConstrainedMep( List<Mep> constrainedMep ) {
		this.constrainedMep = constrainedMep;
	}


	/**
	 * @param filterItfNs the filterItfNs to set
	 */
	public void setFilterItfNs( String filterItfNs ) {
		this.filterItfNs = filterItfNs;
	}


	/**
	 * @param filterSrvName the filterSrvName to set
	 */
	public void setFilterSrvName( String filterSrvName ) {
		this.filterSrvName = filterSrvName;
	}


	/**
	 * @param filterSrvNs the filterSrvNs to set
	 */
	public void setFilterSrvNs( String filterSrvNs ) {
		this.filterSrvNs = filterSrvNs;
	}


	/**
	 * @param filterEdpt the filterEdpt to set
	 */
	public void setFilterEdpt( String filterEdpt ) {
		this.filterEdpt = filterEdpt;
	}


	/**
	 * @return the operationToInvoke
	 */
	public QName getOperationToInvoke() {
		return this.operationToInvoke == null ? NO_OPERATION : this.operationToInvoke;
	}


	/**
	 * @return the invocationMep
	 */
	public Mep getInvocationMep() {
		return this.invocationMep == null ? Mep.UNKNOWN : this.invocationMep;
	}


	/**
	 * @return the itfToInvoke
	 */
	public QName getItfToInvoke() {
		return this.itfToInvoke;
	}


	/**
	 * @return the srvToInvoke
	 */
	public QName getSrvToInvoke() {
		return this.srvToInvoke;
	}


	/**
	 * @return the edptToInvoke
	 */
	public String getEdptToInvoke() {
		return this.edptToInvoke;
	}


	/**
	 * Validates the selection.
	 */
	private void validate() {

		String msg = null, warning = null;
		if( this.itfToInvoke == null
				&& this.srvToInvoke == null
				&& this.itfToInvoke == null )
			msg = "You must select a service to consume (invoke).";

		else if( this.operationToInvoke == null && this.needOperation )
			msg = "You must select an operation to invoke.";

		else if( this.constrainedMep != null
				&& ! this.constrainedMep.isEmpty()
				&& ! this.constrainedMep.contains( this.invocationMep ))
			warning = "This operation is associated with a MEP which is not supported by the current component.";

		setMessage( warning, IMessageProvider.WARNING );
		setErrorMessage( msg );
		if( warning == null && msg == null )
			setMessage( DEFAULT_MSG );

		Button okButton = getButton( IDialogConstants.OK_ID );
		if( okButton != null )
			okButton.setEnabled( msg == null );
	}


	/**
	 * A content provider for the viewer.
	 */
	private static class ServiceContentProvider extends DefaultTreeContentProvider {
		@Override
		public boolean hasChildren( Object element ) {
			return element instanceof SrvBean || element instanceof ItfBean;
		}

		@Override
		public Object[] getElements( Object inputElement ) {
			return ((Map<?,?>) inputElement).values().toArray();
		}

		@Override
		public Object[] getChildren( Object parentElement ) {

			Object[] result = null;
			if( parentElement instanceof ItfBean )
				result = ((ItfBean) parentElement).srvNameToService.values().toArray();
			else if( parentElement instanceof SrvBean )
				result = ((SrvBean) parentElement).endpoints.toArray();

			if( result == null )
				result = new Object[ 0 ];

			return result;
		}
	}


	/**
	 * The filter for the viewer.
	 */
	private class ServiceViewerFilter extends ViewerFilter {

		@Override
		public boolean select( Viewer viewer, Object parentElement, Object element ) {

			boolean result = false;
			if( element instanceof ItfBean ) {
				ItfBean q = (ItfBean) element;
				boolean loc = StringUtils.isEmpty( EnhancedConsumeDialog.this.filterItfName )
				|| WILDCARD.equals( EnhancedConsumeDialog.this.filterItfName )
				|| q.itfName.getLocalPart().startsWith( EnhancedConsumeDialog.this.filterItfName );

				boolean ns = StringUtils.isEmpty( EnhancedConsumeDialog.this.filterItfNs )
				|| WILDCARD.equals( EnhancedConsumeDialog.this.filterItfNs )
				|| q.itfName.getNamespaceURI().startsWith( EnhancedConsumeDialog.this.filterItfNs );

				if( loc && ns ) {
					List<SrvBean> srvBeans = filterServices(((ItfBean) element).srvNameToService.values());
					for( SrvBean srvBean : srvBeans ) {
						List<EdptBean> beans = filterEndpoints( srvBean.endpoints);
						if( beans.size() > 0 ) {
							result = true;
							break;
						}
					}
				}

			} else if( element instanceof SrvBean ) {
				List<SrvBean> srvBeans = filterServices( Arrays.asList((SrvBean) element));
				if( srvBeans.size() == 1 ) {
					List<EdptBean> beans = filterEndpoints(((SrvBean) element).endpoints);
					result = beans.size() > 0;
				}

			} else if( element instanceof EdptBean ) {
				List<EdptBean> beans = filterEndpoints( Arrays.asList((EdptBean) element));
				result = beans.size() == 1;
			}

			return result;
		}


		/**
		 * Filters a list of end-points using the filtering criteria.
		 * @param endpoints
		 * @return a non-null list
		 */
		private List<EdptBean> filterEndpoints( Collection<EdptBean> endpoints ) {

			List<EdptBean> filteredList = new ArrayList<EdptBean> ();
			for( EdptBean edptBean : endpoints ) {
				EndpointBean bean = edptBean.edptBean;
				boolean edpt = StringUtils.isEmpty( EnhancedConsumeDialog.this.filterEdpt )
				|| WILDCARD.equals( EnhancedConsumeDialog.this.filterEdpt )
				|| bean.getEndpointName().startsWith( EnhancedConsumeDialog.this.filterEdpt );

				boolean comp = StringUtils.isEmpty( EnhancedConsumeDialog.this.filterComp )
				|| WILDCARD.equals( EnhancedConsumeDialog.this.filterComp )
				|| bean.getComponentName() == null
				|| bean.getComponentName().startsWith( EnhancedConsumeDialog.this.filterComp );

				if( comp && edpt )
					filteredList.add( edptBean );
			}

			return filteredList;
		}


		/**
		 * Filters a list of services using the filtering criteria.
		 * @param endpoints
		 * @return a non-null list
		 */
		private List<SrvBean> filterServices( Collection<SrvBean> services ) {

			List<SrvBean> filteredList = new ArrayList<SrvBean> ();
			for( SrvBean srvBean : services ) {
				boolean loc = StringUtils.isEmpty( EnhancedConsumeDialog.this.filterSrvName )
				|| WILDCARD.equals( EnhancedConsumeDialog.this.filterSrvName )
				|| srvBean.srvName.getLocalPart().startsWith( EnhancedConsumeDialog.this.filterSrvName );

				boolean ns = StringUtils.isEmpty( EnhancedConsumeDialog.this.filterSrvNs )
				|| WILDCARD.equals( EnhancedConsumeDialog.this.filterSrvNs )
				|| srvBean.srvName.getNamespaceURI().startsWith( EnhancedConsumeDialog.this.filterSrvNs );

				if( loc && ns )
					filteredList.add( srvBean );
			}

			return filteredList;
		}
	}


	/**
	 * The label provider for the viewer.
	 */
	private class ServiceLabelProvider extends ColumnLabelProvider {

		@Override
		public String getText( Object element ) {

			QName qname = null;
			if( element instanceof ItfBean )
				qname = ((ItfBean) element).itfName;
			else if( element instanceof SrvBean )
				qname = ((SrvBean) element).srvName;

			String text = "";
			if( qname != null )
				text = qname.getLocalPart();
			else if( element instanceof EdptBean )
				text = ((EdptBean) element).edptBean.getEndpointName();

			return text;
		}


		@Override
		public Image getImage( Object element ) {

			Image result = null;
			if( element instanceof ItfBean )
				result = EnhancedConsumeDialog.this.itfImage;
			else if( element instanceof SrvBean )
				result = EnhancedConsumeDialog.this.srvImage;
			else if( element instanceof EdptBean )
				result = EnhancedConsumeDialog.this.edptImage;

			return result;
		}

		@Override
		public String getToolTipText( Object element ) {

			QName qname = null;
			if( element instanceof ItfBean )
				qname = ((ItfBean) element).itfName;
			else if( element instanceof SrvBean )
				qname = ((SrvBean) element).srvName;

			String text = "";
			if( qname != null )
				text = qname.getLocalPart() + " - " + qname.getNamespaceURI();
			else if( element instanceof EdptBean )
				text = ((EdptBean) element).edptBean.getEndpointName();

			return text;
		}
	}


	/**
	 * A bean that describes an interface.
	 */
	private class ItfBean {
		QName itfName;
		Map<QName,SrvBean> srvNameToService = new HashMap<QName,SrvBean> ();
	}


	/**
	 * A bean that describes a service.
	 */
	private class SrvBean {
		QName srvName;
		ItfBean itf;
		List<EdptBean> endpoints = new ArrayList<EdptBean> ();
	}


	/**
	 * A bean that describes an end-point.
	 */
	private class EdptBean {
		EndpointBean edptBean;
	}
}
