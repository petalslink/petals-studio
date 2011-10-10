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

package com.ebmwebsourcing.petals.services.jbi.editor.sa;

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

import com.ebmwebsourcing.petals.common.internal.provisional.sse.StructuredModelHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.DomUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.MarkerUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.services.jbi.editor.AbstractServicesFormPage;
import com.ebmwebsourcing.petals.services.jbi.editor.JbiFormEditor;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class SaDetailsPage implements IDetailsPage {

	private final List<IMarker> pageMarkers = new ArrayList<IMarker> ();
	protected Map<String,Control> reducedXpathToControl = new HashMap<String,Control> ();

	protected IManagedForm managedForm;
	protected final AbstractServicesFormPage page;
	protected Element selectedElement;
	protected IFile editedFile;

	protected Text nameText, descText;
	protected boolean reportChanges = true;


	/**
	 * Constructor.
	 * @param page
	 */
	public SaDetailsPage( AbstractServicesFormPage page ) {
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
		parent.setLayout( layout );


		// Containing section
		FormToolkit toolkit = this.managedForm.getToolkit();
		Section section = toolkit.createSection( parent,
					Section.DESCRIPTION
					| ExpandableComposite.TITLE_BAR
					| ExpandableComposite.EXPANDED );

		section.setLayoutData( new GridData( GridData.FILL_BOTH ));
		section.clientVerticalSpacing = 10;
		section.setText( "Properties" );
		section.setDescription( "Edit the properties of the selected element." );

		Composite container = toolkit.createComposite( section, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 10;
		container.setLayout( layout );
		container.setLayoutData( new TableWrapData( TableWrapData.FILL_GRAB ));
		section.setClient( container );


		// Base UI
		Label label = toolkit.createLabel( container, "Name:" );
		label.setToolTipText( "The element name" );
		label.setForeground( blueFont );

		this.nameText = toolkit.createText( container, "", SWT.SINGLE | SWT.BORDER );
		this.nameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		this.reducedXpathToControl.put( "/*[local-name()='identification']", this.nameText );

		label = toolkit.createLabel( container, "Description:" );
		label.setToolTipText( "The element description" );
		label.setForeground( blueFont );

		GridData layoutData = new GridData( SWT.DEFAULT, SWT.BEGINNING, false, false );
		label.setLayoutData( layoutData );

		this.descText = toolkit.createText( container, "", SWT.MULTI | SWT.BORDER );
		layoutData = new GridData( SWT.FILL, SWT.BEGINNING, true, false );
		layoutData.heightHint = 60;
		this.descText.setLayoutData( layoutData );
		this.reducedXpathToControl.put( "/*[local-name()='identification']/*[local-name()='description']", this.descText );

		// Listeners
		Listener elementModifyListener = new Listener() {
			public void handleEvent( Event event ) {
				if( SaDetailsPage.this.reportChanges )
					editElement((Text) event.widget );
			}
		};

		this.nameText.addListener( SWT.Modify, elementModifyListener );
		this.descText.addListener( SWT.Modify, elementModifyListener );
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
	public void refresh() {

		if( this.selectedElement == null
					|| this.nameText == null 	// page already loaded?
					|| this.nameText.isDisposed())
			return;

		try {
			// Do not propagate indefinitely model changes
			this.reportChanges = false;

			// Name and description
			Element identificationElement = DomUtils.getChildElement( this.selectedElement, "identification" );
			if( identificationElement != null ) {
				Element editedElement = DomUtils.getChildElement( identificationElement, "name" );
				if( editedElement != null ) {
					String value = StructuredModelHelper.getElementSimpleValue( editedElement );
					this.nameText.setText( value != null ? value : "" );
				}

				editedElement = DomUtils.getChildElement( identificationElement, "description" );
				if( editedElement != null ) {
					String value = StructuredModelHelper.getElementSimpleValue( editedElement );
					this.descText.setText( value != null ? value : "" );
				}
			}

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
	protected void prepareMarkerRefreshing() {

		WorkbenchJob refreshMarkerJob = new WorkbenchJob( "Refreshing markers" ) {
			@Override
			public IStatus runInUIThread( IProgressMonitor monitor ) {

				if( SaDetailsPage.this.nameText == null
							|| SaDetailsPage.this.nameText.isDisposed())
					return Status.CANCEL_STATUS;

				try {
					for( IMarker marker : SaDetailsPage.this.pageMarkers ) {

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

						SaDetailsPage.this.managedForm.getMessageManager().removeMessage( xpath );
						Control c = SaDetailsPage.this.reducedXpathToControl.get( sb.toString());
						if( c != null )
							SaDetailsPage.this.managedForm.getMessageManager().addMessage( xpath, msg, marker, type, c );
						else
							SaDetailsPage.this.managedForm.getMessageManager().addMessage( xpath, msg, marker, type );
					}

					// Update the form messages
					SaDetailsPage.this.managedForm.getMessageManager().update();

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
		this.nameText.setFocus();
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
	protected void editElement( Text text ) {

		// Remember the caret position
		int offset = text.getCaretPosition();
		//

		String value = text.getText().trim();

		// Identification element
		if( text == this.nameText || text == this.descText ) {
			Element identificationElement = DomUtils.getChildElement( this.selectedElement, "identification" );

			// TODO: create the element if it does not exist
			if( identificationElement != null ) {
				if( text == this.nameText ) {
					Element editedElement = DomUtils.getChildElement( identificationElement, "name" );
					if( editedElement != null )
						StructuredModelHelper.setElementSimpleValue( editedElement, value );
				}
				else {
					Element editedElement = DomUtils.getChildElement( identificationElement, "description" );
					if( editedElement != null )
						StructuredModelHelper.setElementSimpleValue( editedElement, value );
				}
			}
		}

		// Restore the caret position
		text.setSelection( offset );
		//
	}
}
