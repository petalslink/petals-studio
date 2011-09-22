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

import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.VerticalRuler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.XPathUtils;
import com.ebmwebsourcing.petals.common.xpath.internal.provisional.configuration.ColorManager;
import com.ebmwebsourcing.petals.common.xpath.internal.provisional.configuration.XPathSourceViewerConfiguration;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;
import com.ebmwebsourcing.petals.services.su.wizards.legacy.xsd.common.SerializationItem;
import com.ebmwebsourcing.petals.services.su.wizards.pages.provide.se.SeProvideSpecificPage;

/**
 * Replace the default COMPONENT page.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipSpecificPage extends SeProvideSpecificPage {

	private String aggregatorCorrelation;


	/**
	 * Empty constructor.
	 * <p>
	 * Required empty to be instantiated by the main plug-in.
	 * </p>
	 */
	public EipSpecificPage() {
		super( "EIP", "", PetalsEipPlugin.PLUGIN_ID );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.provide.se.SeProvideSpecificPage
	 * #fillInData(com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean)
	 */
	@Override
	public void fillInData( EclipseSuBean suBean ) {

		// Register the correlation
		SerializationItem key = new SerializationItem();
		key.setXsdName( "eip:aggregator-correlation" );
		key.setNillable( false );
		key.setOptional( true );
		getValues().put( key, this.aggregatorCorrelation );

		// Call to super()
		super.fillInData( suBean );
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.XsdBasedAbstractSuPage
	 * #validate()
	 */
	@Override
	public boolean validate() {

		boolean valid = true;
		if( super.validate()) {
			String msg = null;
			if( ! StringUtils.isEmpty( this.aggregatorCorrelation )) {
				if( ! isAggregatorPattern())
					msg = "The field 'aggregator-correlation' must only be used with the Aggregator pattern.";
				else
					msg = XPathUtils.validateXPathExpression( this.aggregatorCorrelation );

			} else if( isAggregatorPattern()) {
				msg = "You must set the value of the 'aggregator-correlation'.";

			} else if( isWireTapPattern()) {
				SerializationItem key = new SerializationItem();
				key.setXsdName( "eip:wiretap-way" );;
				if( StringUtils.isEmpty( getValues().get( key )))
					msg = "You must define the Wire-Tap way.";
			}

			updateStatus( msg );
			valid = msg == null;

		} else {
			valid = false;
		}

		return valid;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.XsdBasedAbstractSuPage
	 * #addControlsAfterXsd(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void addControlsAfterXsd( Composite parent ) {

		// Add a label
		Label label = new Label( parent, SWT.NONE );
		label.setText( "Aggregator Correlation: " );
		label.setToolTipText( "XPath condition that is applied on the incoming message to correlate them (Aggregator pattern)" );
		label.setLayoutData( new GridData( SWT.BEGINNING, SWT.DEFAULT, false, false ));

		// Add the styled editor
		Composite editor = new Composite( parent, SWT.BORDER );
		editor.setLayout( new FillLayout ());
		editor.setLayoutData( new GridData( GridData.FILL_BOTH ));

		int style = SWT.V_SCROLL | SWT.MULTI | SWT.BORDER;
		final ISourceViewer viewer = new SourceViewer( editor, new VerticalRuler( 0 ), style );
		ColorManager cManager = new ColorManager ();
		viewer.configure( new XPathSourceViewerConfiguration( cManager ));

		viewer.getTextWidget().setLayoutData( new GridData( GridData.FILL_BOTH ));
		IDocument document = new Document( "" );
		viewer.setDocument( document );
		viewer.getTextWidget().addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				EipSpecificPage.this.aggregatorCorrelation = viewer.getTextWidget().getText();
				validate();
			}
		});
	}


	/**
	 * @return true if the EIP is the "aggregator", false otherwise
	 */
	private boolean isAggregatorPattern() {

		SerializationItem key = new SerializationItem();
		key.setXsdName( "eip:eip" );

		String eipPattern = getValues().get( key );
		return "aggregator".equalsIgnoreCase( eipPattern );
	}


	/**
	 * @return true if the EIP is the "wire-tap", false otherwise
	 */
	private boolean isWireTapPattern() {

		SerializationItem key = new SerializationItem();
		key.setXsdName( "eip:eip" );

		String eipPattern = getValues().get( key );
		return "wire-tap".equalsIgnoreCase( eipPattern );
	}
}
