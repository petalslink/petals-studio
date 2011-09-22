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

package com.ebmwebsourcing.petals.services.quartz;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.ui.StructuredTextViewerConfiguration;
import org.eclipse.wst.sse.ui.internal.StructuredTextViewer;
import org.eclipse.wst.sse.ui.internal.provisional.style.LineStyleProvider;
import org.eclipse.wst.xml.core.internal.provisional.contenttype.ContentTypeIdForXML;
import org.eclipse.wst.xml.ui.StructuredTextViewerConfigurationXML;

import com.ebmwebsourcing.commons.jbi.internal.provisional.beans.XmlElement;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;
import com.ebmwebsourcing.petals.services.su.wizards.pages.consume.se.SeConsumeSpecificPage;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
@SuppressWarnings( "restriction" )
public class QuartzComponentSpecificPage10 extends SeConsumeSpecificPage {

	private String content;
	private StyledText contentTextField;


	/**
	 * Empty constructor so that it can be instantiated by the main plug-in.
	 */
	public QuartzComponentSpecificPage10 () {
		this( "1.0" );
	}


	/**
	 * Constructor with an argument, for sub-classes.
	 * @param version
	 */
	public QuartzComponentSpecificPage10 ( String version ) {
		super( "Quartz", version, "com.ebmwebsourcing.petals.services.quartz" );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.consume.se.SeConsumeSpecificPage
	 * #fillInData(com.ebmwebsourcing.petals.tools.eclipse.su.main.wizards.generation.EclipseSuBean)
	 */
	@Override
	public void fillInData( EclipseSuBean suBean ) {

		super.fillInData( suBean );

		XmlElement contentElement = new XmlElement();
		contentElement.setName( "quartz:content" );
		contentElement.setValue( this.content );
		suBean.specificElements.add( contentElement );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.XsdBasedAbstractSuPage
	 * #addControlsAfterXsd(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void addControlsAfterXsd( Composite parent ) {

		Label l = new Label( parent, SWT.NONE );
		l.setText( "Content:" );
		l.setToolTipText( "The XML message to send to the scheduled service (will be wrapped in CDATA)" );

		GridData layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		layoutData.verticalIndent = 5;
		l.setLayoutData( layoutData );

		Composite editor = new Composite( parent, SWT.BORDER );
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
		document.set( "<!-- Put the XML message to send here -->" );
		viewer.configure( sourceViewerConfiguration );
		viewer.setDocument( document );

		this.contentTextField = viewer.getTextWidget();
		this.contentTextField.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				validate();
			}
		});
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.tools.eclipse.su.main.pages.XsdBasedAbstractSuPage
	 * #validate()
	 */
	@Override
	public boolean validate() {

		if( this.contentTextField.getText().trim().length() == 0 ) {
			this.updateStatus( "You have to provide a valid XML message to send to the service." );
			return false;
		}

		this.content = "<![CDATA[" + this.contentTextField.getText().trim() + "]]>";
		return super.validate();
	}
}
