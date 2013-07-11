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

package com.ebmwebsourcing.petals.services.cdk.editor;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.xml.namespace.QName;

import org.eclipse.bpel.common.wsdl.helpers.UriAndUrlHelper;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
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
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;

import com.ebmwebsourcing.petals.common.generation.Mep;
import com.ebmwebsourcing.petals.common.internal.provisional.databinding.LocalQNameToStringConverter;
import com.ebmwebsourcing.petals.common.internal.provisional.databinding.NamespaceQNameToStringConverter;
import com.ebmwebsourcing.petals.common.internal.provisional.emf.EObjecttUIHelper;
import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.DefaultSelectionListener;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.PetalsHyperlinkListener;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.JbiXmlUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.services.cdk.Messages;
import com.ebmwebsourcing.petals.services.cdk.cdk5.Cdk5Package;
import com.ebmwebsourcing.petals.services.cdk.editor.databinding.MepToStringConverter;
import com.ebmwebsourcing.petals.services.cdk.editor.databinding.StringToMepConverter;
import com.ebmwebsourcing.petals.services.su.editor.su.ActivableListener;
import com.ebmwebsourcing.petals.services.su.editor.su.JBIEndpointUIHelpers;
import com.ebmwebsourcing.petals.services.su.editor.su.JBIEndpointUIHelpers.CommonUIBean;
import com.ebmwebsourcing.petals.services.su.ui.EnhancedConsumeDialog;
import com.ebmwebsourcing.petals.services.su.ui.WsdlParsingJobManager;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;
import com.sun.java.xml.ns.jbi.JbiPackage;
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
			final FormToolkit toolkit,
			final Composite parent,
			final ISharedEdition ise,
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
		ise.getDataBindingContext().bindValue(
				SWTObservables.observeText( opNameText ),
				EObjecttUIHelper.createCustomEmfEditObservable( ise.getEditingDomain(), endpoint, Cdk5Package.Literals.CDK5_CONSUMES__OPERATION ),
				null,
				new UpdateValueStrategy().setConverter( new LocalQNameToStringConverter()));

		ise.getDataBindingContext().bindValue(
				SWTObservables.observeText( opNamespaceText ),
				EObjecttUIHelper.createCustomEmfEditObservable( ise.getEditingDomain(), endpoint, Cdk5Package.Literals.CDK5_CONSUMES__OPERATION ),
				null,
				new UpdateValueStrategy().setConverter( new NamespaceQNameToStringConverter()));

		ise.getDataBindingContext().bindValue(
				ViewersObservables.observeSingleSelection( mepViewer ),
				EObjecttUIHelper.createCustomEmfEditObservable( ise.getEditingDomain(), endpoint, Cdk5Package.Literals.CDK5_CONSUMES__MEP ),
				new UpdateValueStrategy().setConverter( new MepToStringConverter()),
				new UpdateValueStrategy().setConverter( new StringToMepConverter()));


		// The data-binding handles the "model to target (widget)" parts. But not ALL the "widget to model" parts.
		// For QNames, in fact, the data-binding cannot be applied in this sense. We have to use a modify listener for this.
		final ActivableListener activableListener = JBIEndpointUIHelpers.createModifyListenerForQname(
				ise.getEditingDomain(), endpoint,
				opNamespaceText, opNameText,
				Cdk5Package.Literals.CDK5_CONSUMES__OPERATION, true );

		// Deal with the helper listener
		selectLink.addHyperlinkListener( new PetalsHyperlinkListener() {
			@Override
			public void linkActivated( HyperlinkEvent e ) {

				final EnhancedConsumeDialog dlg = new EnhancedConsumeDialog( parent.getShell(), toolkit );
				if( dlg.open() == Window.OK ) {

					// Prepare the model update
					CompoundCommand compositeCommand = new CompoundCommand();
					EditingDomain editDomain = ise.getEditingDomain();

					QName q = dlg.getItfToInvoke();
					Command command = new SetCommand(editDomain, endpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__INTERFACE_NAME, q);
					compositeCommand.append(command);

					q = dlg.getSrvToInvoke();
					command = new SetCommand(editDomain, endpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__SERVICE_NAME, q);
					compositeCommand.append(command);

					String edpt = dlg.getEdptToInvoke();
					command = new SetCommand(editDomain, endpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__ENDPOINT_NAME, edpt);
					compositeCommand.append(command);

					command = EObjecttUIHelper.createCustomSetCommand( editDomain, endpoint, Cdk5Package.Literals.CDK5_CONSUMES__OPERATION, dlg.getOperationToInvoke());
					compositeCommand.append(command);

					String mep = dlg.getInvocationMep() == Mep.UNKNOWN ? null : dlg.getInvocationMep().toString();
					command = EObjecttUIHelper.createCustomSetCommand( editDomain, endpoint, Cdk5Package.Literals.CDK5_CONSUMES__MEP, mep );
					compositeCommand.append(command);

					// Identify the listeners to disable, so that all the fields are correctly set
					List<ActivableListener> theListeners = new ArrayList<ActivableListener> ();
					theListeners.add( activableListener );
					for( Text t : new Text[] { commonUiBean.itfNameText, commonUiBean.srvNameText }) {
						for( Listener ml : t.getListeners( SWT.Modify )) {
							if( ml instanceof ActivableListener )
								theListeners.add((ActivableListener) ml);
						}
					}

					// Perform the update carefully
					for( ActivableListener ml : theListeners )
						ml.setEnabled( false );

					editDomain.getCommandStack().execute(compositeCommand);
					for( ActivableListener ml : theListeners )
						ml.setEnabled( true );
				}
			}
		});
	}


	/**
	 * Creates the UI for "provide" blocks in the JBI editor (with CDK 5 fields).
	 * @param endpoint
	 * @param toolkit
	 * @param generalDetails
	 * @param ise
	 */
	public static void createProvidesUI(
			final AbstractEndpoint endpoint,
			final FormToolkit toolkit,
			final Composite parent,
			final ISharedEdition ise) {


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

				IProject project = ise.getEditedFile().getProject();
				IFolder resourceFolder = project.getFolder( PetalsConstants.LOC_RES_FOLDER );

				// FIXME: replace this dialog by one that only shows WSDLs?
				ElementTreeSelectionDialog dlg = SwtFactory.createWorkspaceFileSelectionDialog(
						wsdlText.getShell(), resourceFolder,
						"WSDL Selection", "Select a WSDL file located in the project's resource directory.", "wsdl" );

				File file = JbiXmlUtils.getWsdlFile(
							ise.getEditedFile(),
							wsdlText.getText());

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
				File f = JbiXmlUtils.getWsdlFile( ise.getEditedFile(), wsdlValue );
				final URI wsdlUri;
				if( f != null )
					wsdlUri = f.toURI();
				else if( ! StringUtils.isEmpty( wsdlValue ))
					wsdlUri = UriAndUrlHelper.urlToUri( wsdlValue );
				else
					wsdlUri = null;

				wsdlParsingJob.setWsdlUri( wsdlUri );
			}
		});

		Link link = new Link( subContainer, SWT.NONE );
		link.setText("<A>" + Messages.wsdlTools + "</A>");
		final AtomicBoolean tooltipVisible = new AtomicBoolean( false );
		final WSDLHelperTooltip tooltip = new WSDLHelperTooltip( link, toolkit, (Provides) endpoint, ise, wsdlParsingJob );
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
		ise.getDataBindingContext().bindValue(
				SWTObservables.observeDelayedValue(200, SWTObservables.observeText( wsdlText, SWT.Modify )),
				EObjecttUIHelper.createCustomEmfEditObservable( ise.getEditingDomain(), endpoint, Cdk5Package.Literals.CDK5_PROVIDES__WSDL ));


		// Then, the common widgets
		final CommonUIBean commonBean = JBIEndpointUIHelpers.createCommonEndpointUI( endpoint, toolkit, parent, ise );


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
				Command cmd = SetCommand.create( ise.getEditingDomain(), endpoint, JbiPackage.Literals.ABSTRACT_ENDPOINT__ENDPOINT_NAME, edptValue );
				ise.getEditingDomain().getCommandStack().execute( cmd );
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
			FormToolkit toolkit,
			Composite parent,
			ISharedEdition ise ) {

		EObjecttUIHelper.generateEditorWidgets( ae, toolkit, parent, ise.getEditingDomain(), ise.getDataBindingContext(), true,
				Cdk5Package.Literals.CDK5_PROVIDES__TIMEOUT,
				Cdk5Package.Literals.CDK5_PROVIDES__VALIDATE_WSDL,
				Cdk5Package.Literals.CDK5_PROVIDES__FORWARD_SECURITY_SUBJECT,
				Cdk5Package.Literals.CDK5_PROVIDES__FORWARD_MESSAGE_PROPERTIES,
				Cdk5Package.Literals.CDK5_PROVIDES__FORWARD_ATTACHMENTS );
	}


	/**
	 * Generates the default contributions for the CDK section.
	 * @param ae the abstract end-point
	 * @param toolkit the form toolkit
	 * @param parent the parent
	 * @param domain the editing domain
	 * @param dbc the data-binding context
	 */
	public static void generateDefaultCdkWidgetsForConsumesEditor(
			AbstractEndpoint ae,
			FormToolkit toolkit,
			Composite parent,
			ISharedEdition ise ) {

		EObjecttUIHelper.generateEditorWidgets( ae, toolkit, parent, ise.getEditingDomain(), ise.getDataBindingContext(), true,
				Cdk5Package.Literals.CDK5_CONSUMES__TIMEOUT );
	}
}
