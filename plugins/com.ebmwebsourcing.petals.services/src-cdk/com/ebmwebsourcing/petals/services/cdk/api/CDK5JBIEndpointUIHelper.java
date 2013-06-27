/****************************************************************************
 *
 * Copyright (c) 2011-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.cdk.api;

import java.io.File;
import java.net.URI;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.bpel.common.wsdl.helpers.UriAndUrlHelper;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.DefaultSelectionListener;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.PetalsHyperlinkListener;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PropertiesModelUIHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.services.cdk.Messages;
import com.ebmwebsourcing.petals.services.cdk.generated.ConsumesCdk10;
import com.ebmwebsourcing.petals.services.cdk.generated.ProvidesCdk10;
import com.ebmwebsourcing.petals.services.su.editor.su.JBIEndpointUIHelpers;
import com.ebmwebsourcing.petals.services.su.editor.su.JBIEndpointUIHelpers.CommonUIBean;
import com.ebmwebsourcing.petals.services.su.ui.EnhancedConsumeDialog;
import com.ebmwebsourcing.petals.services.su.ui.WsdlParsingJobManager;
import com.ebmwebsourcing.petals.studio.dev.properties.AbstractModel;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.Provides;

/**
 * @author Mickael Istria - EBM WebSourcing
 */
public class CDK5JBIEndpointUIHelper {

	public static final String CONSUME_TITLE = "Invocation Properties";
	public static final String CONSUME_DESC = "Edit the invoked operation and the message exchange pattern.";


	/**
	 * Creates the specific UI for "consume" blocks in the JBI editor (with CDK 5 fields).
	 * @param endpoint
	 * @param toolkit
	 * @param cdkComposite
	 * @param ise
	 * @param parent
	 * @param commonUiBean
	 */
	public static void createConsumesUI(
			final AbstractEndpoint endpoint,
			final AbstractModel cdkModel,
			final FormToolkit toolkit,
			final Composite parent,
			final CommonUIBean commonUiBean ) {

		Color blueFont = parent.getDisplay().getSystemColor( SWT.COLOR_DARK_BLUE );

		// The controls
		SwtFactory.createLabel( parent, "Operation Namespace:", "The QName of the operation (should match an operation declared in a WSDL)" ).setForeground( blueFont );
		final Text opNamespaceText = SwtFactory.createSimpleTextField( parent, true );

		SwtFactory.createLabel( parent, "Operation Name:", "The QName of the operation (should match an operation declared in a WSDL)" ).setForeground( blueFont );
		final Text opNameText = SwtFactory.createSimpleTextField( parent, true );

		SwtFactory.createLabel( parent, "Invocation MEP *:", "The Message Exchange Pattern to use for the invocation" ).setForeground( blueFont );
		ComboViewer mepViewer = SwtFactory.createDefaultComboViewer( parent, false, true, Mep.values());
		toolkit.adapt( mepViewer.getCombo());


		// Add the helpers
		toolkit.createLabel( parent, "" );
		Hyperlink selectLink = toolkit.createHyperlink( parent, "Select a Petals service and an operation to invoke", SWT.NONE );
		selectLink.setToolTipText( "Select the service and the operation to invoke from the known Petals services" );


		// The data-binding


		// Deal with the helper listener
		selectLink.addHyperlinkListener( new PetalsHyperlinkListener() {
			@Override
			public void linkActivated( HyperlinkEvent e ) {

				final EnhancedConsumeDialog dlg = new EnhancedConsumeDialog( parent.getShell(), toolkit );
				if( dlg.open() == Window.OK ) {
					// TODO: update the model and widgets...
				}
			}
		});
	}


	/**
	 * Creates the UI for "provide" blocks in the JBI editor (with CDK 5 fields).
	 * @param endpoint
	 * @param cdkModel
	 * @param toolkit
	 * @param parent
	 * @param editedFile
	 */
	public static void createProvidesUI(
			final AbstractEndpoint endpoint,
			final AbstractModel cdkModel,
			final FormToolkit toolkit,
			final Composite parent,
			final IFile editedFile ) {

		// First: the WSDL part
		Color blueFont = parent.getDisplay().getSystemColor( SWT.COLOR_DARK_BLUE );

		// The label browser
		Label wsdlLabel = toolkit.createLabel( parent, "WSDL location:" );
		wsdlLabel.setToolTipText( "The relative path of the WSDL in the service unit or an URL" );
		wsdlLabel.setForeground( blueFont );
		GridDataFactory.swtDefaults().align( SWT.LEFT, SWT.TOP ).indent( 0, 4 ).applyTo( wsdlLabel );

		Composite subContainer = new Composite( parent, SWT.NONE );
		GridLayoutFactory.swtDefaults().margins( 0, 0 ).numColumns( 2 ).extendedMargins( 0, 0, 0, 20 ).applyTo( subContainer );
		subContainer.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		final Text wsdlText = toolkit.createText( subContainer, "", SWT.SINGLE | SWT.BORDER );
		wsdlText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		Button browseButton = toolkit.createButton( subContainer, "Browse...", SWT.PUSH );
		browseButton.setToolTipText( "Select a WSDL located in the project resources" );
		browseButton.addSelectionListener( new DefaultSelectionListener() {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				IProject project = editedFile.getProject();
				IFolder resourceFolder = project.getFolder( PetalsConstants.LOC_RES_FOLDER );

				// FIXME: replace this dialog by one that only shows WSDLs?
				ElementTreeSelectionDialog dlg = SwtFactory.createWorkspaceFileSelectionDialog(
						wsdlText.getShell(), resourceFolder,
						"WSDL Selection", "Select a WSDL file located in the project's resource directory.", "wsdl" );

				File file = JbiXmlUtils.getWsdlFile( editedFile, wsdlText.getText());
				if( file != null ) {
					IFile wsdlFile = ResourceUtils.getIFile( file );
					if( wsdlFile != null )
						dlg.setInitialElementSelections( Arrays.asList( wsdlFile ));
				}

				if( dlg.open() == Window.OK ) {
					IFile selectedFile = (IFile) dlg.getResult()[ 0 ];
					int rfsc = resourceFolder.getFullPath().segmentCount();
					String wsdlValue = selectedFile.getFullPath().removeFirstSegments( rfsc ).toString();
					wsdlText.setText( wsdlValue );
				}
			}
		});


		// The helpers
		final WsdlParsingJobManager wsdlParsingJob = new WsdlParsingJobManager();
		wsdlText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {

				String wsdlValue = ((Text) e.widget).getText().trim();
				File f = JbiXmlUtils.getWsdlFile( editedFile, wsdlValue );
				final URI wsdlUri;
				if( f != null )
					wsdlUri = f.toURI();
				else if( wsdlValue != null )
					wsdlUri = UriAndUrlHelper.urlToUri( wsdlValue );
				else
					wsdlUri = null;

				wsdlParsingJob.setWsdlUri( wsdlUri );
			}
		});

		Link link = new Link( subContainer, SWT.NONE );
		link.setText("<A>" + Messages.wsdlTools + "</A>");
		final AtomicBoolean tooltipVisible = new AtomicBoolean( false );
		final WSDLHelperTooltip tooltip = new WSDLHelperTooltip( link, toolkit, (Provides) endpoint, null, wsdlParsingJob );
		link.addSelectionListener( new DefaultSelectionListener() {
			@Override
			public void widgetSelected( SelectionEvent e ) {

				boolean visible = tooltipVisible.get();
				if( visible )
					tooltip.hide();
				else
					tooltip.show();

				tooltipVisible.set( ! visible );
			}
		});

		link.addMouseTrackListener( new MouseTrackAdapter() {
			@Override
			public void mouseEnter( MouseEvent e ) {
				tooltip.show();
			}

			@Override
			public void mouseExit( MouseEvent e ) {
				if( ! tooltipVisible.get())
					tooltip.hide();
			}
		});


		// The data-binding
		PropertiesModelUIHelper.bind( wsdlText, cdkModel, ProvidesCdk10.WSDL );

		// Then, the common widgets
		final CommonUIBean commonBean = JBIEndpointUIHelpers.createCommonEndpointUI( endpoint, toolkit, parent );

		// Then, add the auto-generation button
		toolkit.createLabel( parent, "" );

		// Add the button and its label
		final Button generateEdptButton = toolkit.createButton( parent, "", SWT.CHECK );
		generateEdptButton.setText( "Generate the end-point at deployment time" );
		generateEdptButton.setToolTipText( "The end-point name will be generated by Petals on deployment" );
		generateEdptButton.addSelectionListener( new DefaultSelectionListener() {
			private String oldValue;

			@Override
			public void widgetSelected( SelectionEvent e ) {
				boolean selected = generateEdptButton.getSelection();
				commonBean.edptText.setEnabled( ! selected );
				commonBean.edptLabel.setEnabled( ! selected );

				if( selected )
					this.oldValue = commonBean.edptText.getText().trim();
				else if( StringUtils.isEmpty( this.oldValue ))
					this.oldValue = commonBean.srvNameText.getText() + "Endpoint";

				String edptValue = selected ? PetalsConstants.AUTO_GENERATE : this.oldValue;
				endpoint.setEndpointName( edptValue );
			}
		});
	}


	/**
	 * Generates the default contributions for the CDK section.
	 * @param ae the abstract end-point
	 * @param toolkit the form toolkit
	 * @param parent the parent
	 * @param domain the editing domain
	 * @param dbc the data-binding context
	 */
	public static void generateDefaultCdkWidgetsForProvidesEditor(
			AbstractEndpoint ae,
			AbstractModel cdkModel,
			FormToolkit toolkit,
			Composite parent ) {

		PropertiesModelUIHelper.generateWidgets( toolkit, parent, cdkModel,
				ProvidesCdk10.TIMEOUT,
				ProvidesCdk10.VALIDATE_WSDL,
				ProvidesCdk10.FORWARD_SECURITY_SUBJECT,
				ProvidesCdk10.FORWARD_MESSAGE_PROPERTIES,
				ProvidesCdk10.FORWARD_ATTACHMENTS );
	}


	/**
	 * Generates the default contributions for the CDK section.
	 * @param ae the abstract end-point
	 * @param cdkModel
	 * @param toolkit the form toolkit
	 * @param parent the parent
	 */
	public static void generateDefaultCdkWidgetsForConsumesEditor(
			AbstractEndpoint ae,
			AbstractModel cdkModel,
			FormToolkit toolkit,
			Composite parent ) {

		PropertiesModelUIHelper.generateWidgets( toolkit, parent, cdkModel, ConsumesCdk10.TIMEOUT );
	}
}
