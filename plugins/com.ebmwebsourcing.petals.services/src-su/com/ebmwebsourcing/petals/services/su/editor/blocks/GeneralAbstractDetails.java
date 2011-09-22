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

package com.ebmwebsourcing.petals.services.su.editor.blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.progress.WorkbenchJob;
import org.w3c.dom.Element;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.MarkerUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.NamespaceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.services.editor.AbstractServicesFormPage;
import com.ebmwebsourcing.petals.services.editor.JbiFormEditor;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class GeneralAbstractDetails implements IDetailsPage {

	private final List<IMarker> pageMarkers = new ArrayList<IMarker> ();

	protected Map<String,Control> reducedXpathToControl = new HashMap<String,Control> ();
	protected final static String ITF_NS_DECL = "xmlns:itfNs";
	protected final static String SRV_NS_DECL = "xmlns:srvNs";

	protected IManagedForm managedForm;
	protected final AbstractServicesFormPage page;
	protected Element selectedElement;
	protected IFile editedFile;

	protected Text itfNsText, itfNameText;
	protected Text srvNsText, srvNameText;
	protected Text edptNameText;
	protected Label edptNameLabel;

	protected String sectionTitle, sectionDescription;
	protected boolean reportChanges = true;


	/**
	 * Constructor.
	 * @param page
	 */
	public GeneralAbstractDetails( AbstractServicesFormPage page ) {
		this.page = page;
		this.editedFile = ((JbiFormEditor) page.getEditor()).getEditedFile();
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage
	 * #createContents(org.eclipse.swt.widgets.Composite)
	 */
	public void createContents( Composite parent ) {

		Color blueFont = parent.getDisplay().getSystemColor( SWT.COLOR_DARK_BLUE );
		GridLayout layout = new GridLayout ();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.verticalSpacing = 20;
		parent.setLayout( layout );


		// Containing section
		FormToolkit toolkit = this.managedForm.getToolkit();
		Section section = toolkit.createSection( parent,
					Section.DESCRIPTION
					| ExpandableComposite.TITLE_BAR
					| ExpandableComposite.EXPANDED );

		section.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		section.clientVerticalSpacing = 10;
		section.setText( this.sectionTitle );
		section.setDescription( this.sectionDescription );

		Composite container = toolkit.createComposite( section, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 10;
		container.setLayout( layout );
		container.setLayoutData( new TableWrapData( TableWrapData.FILL ));
		section.setClient( container );


		// Add fields before
		addContentBefore( container );


		// Add common UI
		Label label = toolkit.createLabel( container, "Interface namespace:" );
		label.setToolTipText( "The namespace of the interface (must match an interface declared in the WSDL)" );
		label.setForeground( blueFont );

		this.itfNsText = toolkit.createText( container, "", SWT.SINGLE | SWT.BORDER );
		this.itfNsText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		label = toolkit.createLabel( container, "Interface name:" );
		label.setToolTipText( "The name of the interface (must match an interface declared in the WSDL)" );
		label.setForeground( blueFont );

		this.itfNameText = toolkit.createText( container, "", SWT.SINGLE | SWT.BORDER );
		this.itfNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.reducedXpathToControl.put( "/@interface-name", this.itfNameText );

		label = toolkit.createLabel( container, "Service namespace:" );
		label.setToolTipText( "The namespace of the service (must match a service declared in the WSDL)" );
		label.setForeground( blueFont );

		this.srvNsText = toolkit.createText( container, "", SWT.SINGLE | SWT.BORDER );
		this.srvNsText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		label = toolkit.createLabel( container, "Service name:" );
		label.setToolTipText( "The name of the service (must match a service declared in the WSDL)" );
		label.setForeground( blueFont );

		this.srvNameText = toolkit.createText( container, "", SWT.SINGLE | SWT.BORDER );
		this.srvNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.reducedXpathToControl.put( "/@service-name", this.srvNameText );

		this.edptNameLabel = toolkit.createLabel( container, "End-point name:" );
		this.edptNameLabel.setToolTipText( "The end-point name, meaning the service location (must match the one declared in the WSDL)" );
		this.edptNameLabel.setForeground( blueFont );

		this.edptNameText = toolkit.createText( container, "", SWT.SINGLE | SWT.BORDER );
		this.edptNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.reducedXpathToControl.put( "/@endpoint-name", this.edptNameText );


		// Add fields after
		addContentAfter( container );


		// Listeners
		Listener attributeModifyListener = new Listener() {
			public void handleEvent( Event event ) {
				if( GeneralAbstractDetails.this.reportChanges )
					editAttribute((Text) event.widget );
			}
		};

		final ModifyListener sameNsModifyListener = new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				String serviceNs = GeneralAbstractDetails.this.srvNsText.getText();
				String interfaceNs = GeneralAbstractDetails.this.itfNsText.getText();

				Color fgColor;
				if( serviceNs.trim().length() > 0
							&& serviceNs.equals( interfaceNs )) {
					fgColor = GeneralAbstractDetails.this.srvNameText.getDisplay().getSystemColor( SWT.COLOR_DARK_GREEN );
				} else {
					fgColor = GeneralAbstractDetails.this.srvNameText.getDisplay().getSystemColor( SWT.COLOR_WIDGET_FOREGROUND );
				}

				GeneralAbstractDetails.this.srvNsText.setForeground( fgColor );
				GeneralAbstractDetails.this.itfNsText.setForeground( fgColor );
			}
		};

		FocusListener nsFocusListener = new FocusAdapter() {
			@Override
			public void focusGained( FocusEvent e ) {
				((Text) e.widget).addModifyListener( sameNsModifyListener );
				((Text) e.widget).notifyListeners( SWT.Modify, new Event());
			}

			@Override
			public void focusLost( FocusEvent e ) {
				((Text) e.widget).removeModifyListener( sameNsModifyListener );
				Color fgColor = GeneralAbstractDetails.this.srvNameText.getDisplay().getSystemColor( SWT.COLOR_WIDGET_FOREGROUND );
				GeneralAbstractDetails.this.srvNsText.setForeground( fgColor );
				GeneralAbstractDetails.this.itfNsText.setForeground( fgColor );
			}
		};

		this.itfNameText.addListener( SWT.Modify, attributeModifyListener );
		this.itfNsText.addListener( SWT.Modify, attributeModifyListener );
		this.srvNameText.addListener( SWT.Modify, attributeModifyListener );
		this.srvNsText.addListener( SWT.Modify, attributeModifyListener );
		this.edptNameText.addListener( SWT.Modify, attributeModifyListener );

		this.itfNsText.addFocusListener( nsFocusListener );
		this.srvNsText.addFocusListener( nsFocusListener );

		KeyListener defaultNsKeyListener = new KeyAdapter() {
			@Override
			public void keyPressed( KeyEvent e ) {

				if( e.stateMask == SWT.CTRL
							&& e.character == ' ' ) {
					Text text = (Text) e.widget;

					if( text.getText().trim().length() == 0
								|| "http://".equals( text.getText())) {
						text.setText( "http://petals.ow2.org/" );
						text.setSelection( 22 );
					}
				}
			}
		};

		this.srvNsText.addKeyListener( defaultNsKeyListener );
		this.itfNsText.addKeyListener( defaultNsKeyListener );
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.IFormPart
	 * #commit(boolean)
	 */
	public void commit( boolean onSave ) {
		// nothing
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.IFormPart
	 * #dispose()
	 */
	public void dispose() {
		// nothing
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.IFormPart
	 * #initialize(org.eclipse.ui.forms.IManagedForm)
	 */
	public void initialize( IManagedForm form ) {
		this.managedForm = form;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.IFormPart
	 * #isDirty()
	 */
	public boolean isDirty() {
		return false;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.IFormPart
	 * #isStale()
	 */
	public boolean isStale() {
		return false;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.IFormPart
	 * #refresh()
	 */
	public final void refresh() {

		if( this.selectedElement == null
					|| this.itfNsText == null 	// page already loaded?
					|| this.itfNsText.isDisposed())
			return;

		try {
			// Do not propagate indefinitely model changes
			this.reportChanges = false;

			// Interface
			String value = this.selectedElement.getAttribute( "interface-name" );
			String name = NamespaceUtils.removeNamespaceElements( value );
			String ns = NamespaceUtils.getNamespaceUri( value, this.selectedElement );

			this.itfNameText.setText( name != null ? name : "" );
			this.itfNsText.setText( ns != null ? ns : "" );


			// Service
			value = this.selectedElement.getAttribute( "service-name" );
			name = NamespaceUtils.removeNamespaceElements( value );
			ns = NamespaceUtils.getNamespaceUri( value, this.selectedElement );

			this.srvNameText.setText( name != null ? name : "" );
			this.srvNsText.setText( ns != null ? ns : "" );


			// End-point: deal with it in sub-classes
			refreshCustomWidgets();

			// Update the markers
			prepareMarkerRefreshing();

		} finally {

			// Propagate (again) model changes
			this.reportChanges = true;
		}
	}


	/**
	 * Updates the error markers on the page.
	 * <p>
	 * Because there is a bug that I friendly called "the extra-marker bug",
	 * we have to put a delay between the moment when we update the page and the
	 * moment when we update the markers on the controls.
	 * </p>
	 * <p>
	 * Otherwise, there is an extra-marker added at position (0,0) on the screen.
	 * In fact, I think the decorator is first set at this position because the decorated
	 * controls have not yet been placed on screen. Then, the controls are created and the
	 * decorators updated. Except that this first one does not disappear.
	 * </p>
	 * <p>
	 * Anyway, this solution works great.
	 * </p>
	 */
	private void prepareMarkerRefreshing() {

		WorkbenchJob refreshMarkerJob = new WorkbenchJob( "Refreshing markers" ) {
			@Override
			public IStatus runInUIThread( IProgressMonitor monitor ) {

				if( GeneralAbstractDetails.this.edptNameText == null
							|| GeneralAbstractDetails.this.edptNameText.isDisposed())
					return Status.CANCEL_STATUS;

				try {
					for( IMarker marker : GeneralAbstractDetails.this.pageMarkers ) {

						String msg = marker.getAttribute( IMarker.MESSAGE, "" );
						String xpath = marker.getAttribute( PetalsConstants.MARKER_XPATH_LOCATION_ATTRIBUTE, "" );
						int severity = marker.getAttribute( IMarker.SEVERITY, -1 );
						int type = MarkerUtils.getMessageSeverityFromMarkerSeverity( severity );

						String[] parts = xpath.split( "/" );
						if( parts.length < 4 )
							continue;

						StringBuilder sb = new StringBuilder();
						for( int i=parts.length - 1; i>=4; i-- )
							sb.insert( 0, "/" + parts[ i ] );

						GeneralAbstractDetails.this.managedForm.getMessageManager().removeMessage( xpath );
						Control c = GeneralAbstractDetails.this.reducedXpathToControl.get( sb.toString());
						if( c != null )
							GeneralAbstractDetails.this.managedForm.getMessageManager().addMessage( xpath, msg, marker, type, c );
						else
							GeneralAbstractDetails.this.managedForm.getMessageManager().addMessage( xpath, msg, marker, type );
					}

					// Update the form messages
					GeneralAbstractDetails.this.managedForm.getMessageManager().update();

				} catch( Exception e ) {
					e.printStackTrace();
				}

				return Status.OK_STATUS;
			}
		};

		refreshMarkerJob.schedule( 800 );
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.IFormPart
	 * #setFocus()
	 */
	public void setFocus() {
		this.itfNameText.setFocus();
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.IFormPart
	 * #setFormInput(java.lang.Object)
	 */
	public boolean setFormInput( Object input ) {
		return false;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.IPartSelectionListener
	 * #reportChanges(org.eclipse.ui.forms.IFormPart, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged( IFormPart part, ISelection selection ) {

		IStructuredSelection ssel = (IStructuredSelection) selection;
		if( ssel.size() == 1 )
			this.selectedElement = (Element) ssel.getFirstElement();
		else
			this.selectedElement = null;

		refresh();
	}


	/**
	 * Updates the page markers.
	 * @param markers
	 */
	public final void setPagesMarkers( List<IMarker> markers ) {

		this.pageMarkers.clear();
		if( markers != null )
			this.pageMarkers.addAll( markers );
	}


	/**
	 * Shows and selects the part which is wrong and associated with this marker.
	 * @param xPathLocation
	 * @return true if a matching widget could be found, false otherwise
	 */
	public boolean gotoMarker( String xPathLocation ) {

		boolean result = false;
		String[] parts = xPathLocation.split( "/" );
		if( parts.length >= 4 ) {

			// Get the reduced XPath expression
			StringBuilder sb = new StringBuilder();
			for( int i=parts.length - 1; i>=4; i-- )
				sb.insert( 0, "/" + parts[ i ] );

			Control c = this.reducedXpathToControl.get( sb.toString());
			if( c != null ) {
				result = true;
				if( c instanceof Text ) {
					if(((Text) c).forceFocus())
						((Text) c).selectAll();
				}
			}
		}
		return result;
	}


	/**
	 * @param text
	 */
	protected abstract void editAttribute( Text text );


	/**
	 * @param parent
	 */
	public abstract void addContentBefore( Composite parent );


	/**
	 * @param parent
	 */
	public abstract void addContentAfter( Composite parent );


	/**
	 * Refreshes the additional widgets added by the sub-classes.
	 */
	protected abstract void refreshCustomWidgets();
}
