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

package com.ebmwebsourcing.petals.services.monitoring.wizard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.VerticalRuler;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

import com.ebmwebsourcing.petals.common.generation.JbiUtils;
import com.ebmwebsourcing.petals.common.xpath.internal.provisional.configuration.ColorManager;
import com.ebmwebsourcing.petals.common.xpath.internal.provisional.configuration.XPathSourceViewerConfiguration;
import com.ebmwebsourcing.petals.services.monitoring.wizard.KpiProjectBean.KpiFlowBean;
import com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean;
import com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class KpiXPathWizardPage extends AbstractSuPage {

	private static final String INITIAL_XPATH = "//*";

	private final Map<String,String> flowNameToXPathExpression = new HashMap<String,String>( 4 );
	private final Map<String,Boolean> flowNameToEnablement = new HashMap<String,Boolean>( 4 );
	private String processName;
	private KpiConfigurationWizardPage configurationPage;


	/**
	 * Constructor.
	 */
	public KpiXPathWizardPage() {
		super( "SeveralConsumePages", "KPI", "1.0" );

		for( String flow : KpiConstants.FLOW_NAMES ) {
			this.flowNameToXPathExpression.put( flow, INITIAL_XPATH );
			this.flowNameToEnablement.put( flow, false );
		}
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #fillInData(com.ebmwebsourcing.petals.services.su.wizards.generation.EclipseSuBean)
	 */
	@Override
	public void fillInData( EclipseSuBean suBean ) {
		// nothing
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #setHelpContextId(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void setHelpContextId( Composite container ) {
		// nothing
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #validate()
	 */
	@Override
	public boolean validate() {

		// Check the expression validity
		boolean allEmpty = true;
		for( Map.Entry<String,String> entry : this.flowNameToXPathExpression.entrySet()) {
			if( this.flowNameToEnablement.get( entry.getKey())) {

				allEmpty = false;
				XPath xpath = XPathFactory.newInstance().newXPath();
				try {
					xpath.compile( entry.getValue() );

				} catch( Exception e ) {
					String cause = e.getCause().getMessage();
					cause = cause == null ? "" : " " + cause;
					updateStatus( "Invalid XPath expression (" + entry.getValue() + ")." + cause );
					return false;
				}
			}
		}

		// All empty?
		if( allEmpty ) {
			updateStatus( "You must define at least one XPath expression that will filter messages." );
			return false;
		}

		updateStatus( null );
		return true;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage
	 * #createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl( Composite parent ) {

		/* create the composite container and define its layout */
		final Composite container = new Composite( parent, SWT.NONE );

		// Set help link for documentation page.
		setHelpContextId( container );

		GridLayout layout = new GridLayout();
		layout.marginLeft = 15;
		layout.marginTop = 15;
		container.setLayout( layout );
		container.setLayoutData( new GridData( GridData.FILL_BOTH ));

		// Add the other widgets
		for( final String flow : KpiConstants.FLOW_NAMES ) {

			final Button button = new Button( container, SWT.CHECK );
			button.setText( "Enable the monitoring for " + flow.toUpperCase() + " flows." );

			GridData layoutData = new GridData();
			layoutData.verticalIndent = 4;
			button.setLayoutData( layoutData );

			final StyledText st = createXpathEditor( container, flow );
			button.addSelectionListener( new SelectionAdapter() {
				@Override
				public void widgetSelected( SelectionEvent e ) {
					st.setEnabled( button.getSelection());
					KpiXPathWizardPage.this.flowNameToEnablement.put( flow, button.getSelection());
					if( ! st.isEnabled())
						st.setBackground( st.getDisplay().getSystemColor( SWT.COLOR_WIDGET_BACKGROUND ));
					else
						st.setBackground( st.getDisplay().getSystemColor( SWT.COLOR_WHITE ));
					validate();
				}
			});

			button.notifyListeners( SWT.Selection, new Event());
		}

		// End-up correctly
		setControl( container );
	}


	/**
	 * Creates an XPath editor.
	 * @param container the parent composite
	 * @param flowName the associated flow name
	 * @return the styled text that displays the XPath expression
	 */
	private StyledText createXpathEditor( Composite container, final String flowName ) {

		Composite editor = new Composite( container, SWT.BORDER );
		editor.setLayout( new FillLayout());
		editor.setLayoutData( new GridData( GridData.FILL_BOTH ));

		int style = SWT.V_SCROLL | SWT.MULTI | SWT.BORDER;
		ISourceViewer viewer = new SourceViewer( editor, new VerticalRuler( 0 ), style );
		ColorManager cManager = new ColorManager ();
		viewer.configure( new XPathSourceViewerConfiguration( cManager ));

		IDocument document = new Document( INITIAL_XPATH );
		viewer.setDocument( document );

		final StyledText xpathConditionText = viewer.getTextWidget();
		xpathConditionText.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				KpiXPathWizardPage.this.flowNameToXPathExpression.put( flowName, xpathConditionText.getText());
				validate();
			}
		});

		return xpathConditionText;
	}


	/**
	 * @param processName the processName to set
	 */
	protected final void setProcessName( String processName ) {
		this.processName = processName;
	}


	/**
	 * @return the configurationPage
	 */
	protected final KpiConfigurationWizardPage getConfigurationPage() {
		return this.configurationPage;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.WizardPage
	 * #canFlipToNextPage()
	 */
	@Override
	public boolean canFlipToNextPage() {
		return getErrorMessage() == null;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.services.su.wizards.pages.AbstractSuPage
	 * #getNextPage()
	 */
	@Override
	public IWizardPage getNextPage() {

		if( this.configurationPage == null ) {
			this.configurationPage = new KpiConfigurationWizardPage();
			this.configurationPage.setTitle( "Watcher Configuration" );
			this.configurationPage.setDescription( "Define the watcher parameters." );
			((Wizard) getWizard()).addPage( this.configurationPage );
		}

		KpiProjectBean kpb = new KpiProjectBean();
		kpb.setProjectName( JbiUtils.createSuName( "KPI", this.processName, true ));
		for( Map.Entry<String,Boolean> entry : this.flowNameToEnablement.entrySet()) {
			if( entry.getValue()) {
				KpiFlowBean flowBean = new KpiFlowBean();
				flowBean.setFlowName( entry.getKey());
				flowBean.setXpathExpression( this.flowNameToXPathExpression.get( entry.getKey()));
				kpb.addKpiFlowBean( flowBean );
			}
		}

		Collection<KpiProjectBean> kpiProjectBeans = new ArrayList<KpiProjectBean>( 1 );
		kpiProjectBeans.add( kpb );
		this.configurationPage.setKpiProjectBeans( kpiProjectBeans );

		return this.configurationPage;
	}
}
