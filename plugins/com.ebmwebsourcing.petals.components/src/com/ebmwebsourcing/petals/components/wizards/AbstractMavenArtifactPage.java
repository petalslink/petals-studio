/****************************************************************************
 * 
 * Copyright (c) 2010-2012, EBM WebSourcing
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.components.wizards;

import java.net.URI;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.ebmwebsourcing.petals.common.internal.provisional.ui.FixedShellTooltip;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsConstants;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PlatformUtils;
import com.ebmwebsourcing.petals.components.PetalsComponentsPlugin;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public abstract class AbstractMavenArtifactPage extends WizardPage {

	private Image helpImg;
	private Font boldFont;
	protected FixedShellTooltip helpTooltip;


	/**
	 * Constructor.
	 * @param pageName
	 */
	protected AbstractMavenArtifactPage( String pageName ) {
		super( pageName );
	}


	/**
	 * @return the name
	 */
	public abstract String getArtifactName();


	/**
	 * @return the version
	 */
	public abstract String getArtifactVersion();


	/**
	 * @return the groupId
	 */
	public abstract String getGroupId();


	/**
	 * @return the archetype ID
	 */
	public abstract String getArchetypeId();


	/**
	 * @return the archetypeVersion
	 */
	public abstract String getArchetypeVersion();


	/**
	 * @return the projectLocationURI
	 */
	public abstract URI getProjectLocationURI();


	/**
	 * Creates the tool tip with an explanation in case of Maven configuration error
	 */
	protected void initializeMavenTooltip() {

		this.helpTooltip = new FixedShellTooltip( getShell(), true, 90 ) {
			@Override
			public void populateTooltip( Composite parent ) {

				GridLayout layout = new GridLayout();
				layout.verticalSpacing = 2;
				parent.setLayout( layout );
				parent.setLayoutData( new GridData( GridData.FILL_BOTH ));
				parent.setBackground( getShell().getDisplay().getSystemColor( SWT.COLOR_LIST_BACKGROUND ));

				try {
					ImageDescriptor desc = AbstractUIPlugin.imageDescriptorFromPlugin(
								PetalsConstants.PETALS_COMMON_PLUGIN_ID, "icons/petals/sad.png" );

					if( desc != null )
						AbstractMavenArtifactPage.this.helpImg = desc.createImage();

					parent.setBackgroundMode( SWT.INHERIT_DEFAULT );
					Label imgLabel = new Label( parent, SWT.NONE );
					imgLabel.setImage( AbstractMavenArtifactPage.this.helpImg );
					imgLabel.setLayoutData( new GridData( SWT.CENTER, SWT.DEFAULT, true, true ));

				} catch( Exception e ) {
					PetalsComponentsPlugin.log( e, IStatus.WARNING );
				}

				FontData[] fd = PlatformUtils.getModifiedFontData( getFont().getFontData(), SWT.BOLD );
				AbstractMavenArtifactPage.this.boldFont = new Font( getShell().getDisplay(), fd );
				Label titleLabel = new Label( parent, SWT.NONE );
				titleLabel.setFont( AbstractMavenArtifactPage.this.boldFont );
				GridData layoutData = new GridData( SWT.CENTER, SWT.DEFAULT, true, true );
				layoutData.verticalIndent = 5;
				titleLabel.setLayoutData( layoutData );
				titleLabel.setText( "What does this error mean?" );

				Label l = new Label( parent, SWT.WRAP );
				l.setText( "The creation of Petals components and shared libraries relies on Maven.\n"
							+ "Unfortunately, Maven seems to not be installed on your machine.\n"
							+ "Maven could not be found in the system class path." );

				layoutData = new GridData();
				layoutData.verticalIndent = 8;
				l.setLayoutData( layoutData );
			}
		};

		this.helpTooltip.hide();
	}


	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.jface.dialogs.DialogPage
	 * #dispose()
	 */
	@Override
	public void dispose() {

		if( this.helpTooltip != null )
			this.helpTooltip.dispose();

		if( this.boldFont != null && ! this.boldFont.isDisposed())
			this.boldFont.dispose();

		if( this.helpImg != null && ! this.helpImg.isDisposed())
			this.helpImg.dispose();

		super.dispose();
	}


	/**
	 * @return true if Maven seems to be installed, false otherwise
	 */
	protected boolean isMavenInstalled() {

		boolean result = true;
		try {
			String path = System.getenv( "PATH" );
			path = path.toLowerCase();
			if( path != null )
				result = path.contains( "maven" ) || path.contains( "m2_home" );

		} catch( Exception e ) {
			PetalsComponentsPlugin.log( e, IStatus.ERROR );
		}

		return result;
	}
}
