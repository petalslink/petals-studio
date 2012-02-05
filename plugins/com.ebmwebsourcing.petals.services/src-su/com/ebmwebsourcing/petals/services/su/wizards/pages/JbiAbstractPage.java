/****************************************************************************
 *
 * Copyright (c) 2008-2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.services.su.wizards.pages;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.swt.QNameText;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.services.PetalsServicesPlugin;
import com.ebmwebsourcing.petals.services.su.extensions.SuWizardSettings;
import com.sun.java.xml.ns.jbi.AbstractEndpoint;

/**
 * The abstract wizard page showing JBI standard fields.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class JbiAbstractPage extends AbstractSuWizardPage {

	public static final String PAGE_NAME = "JbiPage";

	protected QNameText itfQText, srvQText;
	protected Text edptText;
	protected Image tipImage;


	/**
	 * Constructor.
	 * @param suType the component type used by the SU (e.g. FTP, XSLT...).
	 * @param suTypeVersion the version of the Petals component used by the SU.
	 */
	public JbiAbstractPage() {
		super( PAGE_NAME );
		this.tipImage = PetalsServicesPlugin.loadImage( "icons/obj16/smartmode_co.gif" );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage
	 * #dispose()
	 */
	@Override
	public void dispose() {

		if( this.tipImage != null && ! this.tipImage.isDisposed())
			this.tipImage.dispose();

		super.dispose();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public final void createControl( Composite parent ) {

		// Container
		final Composite container = SwtFactory.createComposite( parent );
		setControl( container );
		SwtFactory.applyNewGridLayout( container, 2, false, 20, 15, 0, 15 );
		SwtFactory.applyHorizontalGridData( container );

		// Set the page description
		setDescription( getDescription());

		// Create controls in the form.
		createCustomControls( container );
	}


	/**
	 * This method defines the controls to add in the page.
	 * <p>
	 * It should use {@link JbiAbstractPage#createCommonControls(Composite)}
	 * </p>
	 *
	 * @param container the parent container with a two-column layout
	 */
	protected abstract void createCustomControls( Composite container );


	/**
	 * Create JBI common controls (service, interface and end-point names).
	 * @param parent the parent
	 * @param marginTop the margin above the first widget.
	 */
	protected void createCommonControls( Composite container, int marginTop ) {

		// Interface name
		SuWizardSettings settings = getWizard().getSettings();
		Label l = SwtFactory.createLabel( container, "Interface Name *:", "The qualified name of the service contract" );
		SwtFactory.applyGridData( l, 1, marginTop );

		this.itfQText = SwtFactory.createQNameTextField( container, false, "Interface", "http://Your.Interface.Namespace/" );
		SwtFactory.applyHorizontalGridData( this.itfQText, 1, marginTop );
		if( ! settings.activateInterfaceName )
			this.itfQText.setEditable( false );

		this.itfQText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				getNewlyCreatedEndpoint().setInterfaceName( JbiAbstractPage.this.itfQText.getValue());
				validate();
			}
		});


		// Service name
		SwtFactory.createLabel( container, "Service Name:", "The qualified name of the service implementation" );
		this.srvQText = SwtFactory.createQNameTextField( container, true, "Service  ", "http://Your.Service.Namespace/" );
		if( settings.activateServiceNameOnly )
			this.srvQText.setNamespacePartEditable( false );
		else if( ! settings.activateServiceName )
			this.srvQText.setEditable( false );

		this.srvQText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				getNewlyCreatedEndpoint().setServiceName( JbiAbstractPage.this.srvQText.getValue());
				validate();
			}
		});


		// End-point name
		SwtFactory.createLabel( container, "End-point Name:", "The name of the service deployment point" );
		this.edptText = SwtFactory.createSimpleTextField( container, true );
		if( ! settings.activateEndpointName )
			this.edptText.setEditable( false );

		this.edptText.addModifyListener( new ModifyListener() {
			@Override
			public void modifyText( ModifyEvent e ) {
				getNewlyCreatedEndpoint().setEndpointName( JbiAbstractPage.this.edptText.getText());
				validate();
			}
		});
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage
	 * #setVisible(boolean)
	 */
	@Override
	public void setVisible( boolean visible ) {

		// Update the UI
		if( visible ) {
			AbstractEndpoint ae = getNewlyCreatedEndpoint();
			this.itfQText.setValue( ae.getInterfaceName());
			this.srvQText.setValue( ae.getServiceName());
			String edpt = ae.getEndpointName();

			if( ! getWizard().getComponentVersionDescription().getAutoGeneratedEndpointValue().equals( edpt ))
				this.edptText.setText( edpt == null ? "" : edpt );

			String errorMsg = getErrorMessage();
			if( errorMsg != null ) {
				setMessage( errorMsg, IMessageProvider.INFORMATION );
				setErrorMessage( null );
			}

			setPageComplete( errorMsg == null );
		}

		// Call to super
		super.setVisible( visible );
	}
}
