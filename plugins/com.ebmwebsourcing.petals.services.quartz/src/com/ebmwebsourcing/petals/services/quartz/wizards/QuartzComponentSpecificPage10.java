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

package com.ebmwebsourcing.petals.services.quartz.wizards;

import java.net.URL;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration;
import org.eclipse.wst.sse.ui.internal.StructuredTextViewer;
import org.eclipse.wst.sse.ui.internal.provisional.style.LineStyleProvider;
import org.eclipse.wst.xml.core.internal.provisional.contenttype.ContentTypeIdForXML;
import org.eclipse.wst.xml.ui.StructuredTextViewerConfigurationXML;

import com.ebmwebsourcing.petals.services.quartz.Messages;
import com.ebmwebsourcing.petals.services.quartz.quartz.QuartzPackage;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
@SuppressWarnings( "restriction" )
public class QuartzComponentSpecificPage10 extends AbstractSuPage {

	private String content;
	private StyledText contentTextField;
	private DataBindingContext dbc;

	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.XsdBasedAbstractSuPage
	 * #addControlsAfterXsd(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		dbc = new DataBindingContext();
		
		Composite res = new Composite(parent, SWT.NONE);
		res.setLayout(new GridLayout(2, false));
		
		Label cronLabel = new Label(res, SWT.NONE);
		cronLabel.setText(Messages.cronExpression);
		Text cronText = new Text(res, SWT.BORDER);
		cronText.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
		Link cronLink = new Link(res, SWT.NONE);
		cronLink.setText("<A>" + Messages.cronHelp + "</A>");
		cronLink.setLayoutData(new GridData(SWT.RIGHT, SWT.DEFAULT, true, false, 2, 1));
		cronLink.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					final IWebBrowser browser = PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser();
					browser.openURL(new URL("http://www.quartz-scheduler.org/documentation/quartz-1.x/tutorials/crontrigger"));
				} catch (Exception ex) {
					ErrorDialog dialog = new ErrorDialog(getShell(), Messages.couldNotOpenEditorTitle, Messages.couldNotOpenEditorMessage, new Status(IStatus.ERROR, "com.ebmwebsourcing.petals.services.quartz", ex.getMessage()), 0);
				}
			}
		});
		
		dbc.bindValue(SWTObservables.observeText(cronText, SWT.Modify),
				EMFObservables.observeValue(getNewlyCreatedEndpoint(), QuartzPackage.Literals.QUARTZ_CONSUMES__CRON_EXPRESSION));
		
		Label l = new Label( res, SWT.NONE );
		l.setText( Messages.content );

		GridData layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		layoutData.verticalIndent = 5;
		l.setLayoutData( layoutData );

		Composite editor = new Composite( res, SWT.BORDER );
		editor.setLayout( new FillLayout ());
		layoutData = new GridData( GridData.FILL_BOTH );
		layoutData.horizontalSpan = 2;
		editor.setLayoutData( layoutData );

		SourceViewerConfiguration sourceViewerConfiguration = new StructuredTextViewerConfiguration() {
			StructuredTextViewerConfiguration baseConfiguration = new StructuredTextViewerConfigurationXML();

			@Override
			public String[] getConfiguredContentTypes( ISourceViewer sourceViewer ) {
				return this.baseConfiguration.getConfiguredContentTypes( sourceViewer );
			}

			@Override
			public LineStyleProvider[] getLineStyleProviders( ISourceViewer sourceViewer, String partitionType ) {
				return this.baseConfiguration.getLineStyleProviders( sourceViewer, partitionType );
			}
		};

		SourceViewer viewer = null;
		String contentTypeID = ContentTypeIdForXML.ContentTypeID_XML;
		viewer = new StructuredTextViewer( editor, null, null, false, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL );
		((StructuredTextViewer) viewer).getTextWidget().setFont( JFaceResources.getFont( "org.eclipse.wst.sse.ui.textfont" )); //$NON-NLS-1$
		IStructuredModel scratchModel = StructuredModelManager.getModelManager().createUnManagedStructuredModelFor( contentTypeID );
		IDocument document = scratchModel.getStructuredDocument();
		viewer.configure( sourceViewerConfiguration );
		viewer.setDocument( document );

		this.contentTextField = viewer.getTextWidget();
		dbc.bindValue(SWTObservables.observeText(contentTextField, SWT.Modify),
				EMFObservables.observeValue(getNewlyCreatedEndpoint(), QuartzPackage.Literals.QUARTZ_CONSUMES__CONTENT));
		
		setControl(res);
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.XsdBasedAbstractSuPage
	 * #validate()
	 */
	@Override
	public boolean validate() {
		return true;
	}
	
	@Override
	public void dispose() {
		dbc.dispose();
		super.dispose();
	}
}
