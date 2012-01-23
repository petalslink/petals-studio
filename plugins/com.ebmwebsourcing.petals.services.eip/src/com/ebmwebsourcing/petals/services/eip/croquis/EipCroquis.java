/****************************************************************************
 *
 * Copyright (c) 2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.eip.croquis;

import java.io.ByteArrayInputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.ebmwebsourcing.petals.common.croquis.internal.provisional.CroquisNewWizardPage;
import com.ebmwebsourcing.petals.common.croquis.internal.provisional.ICroquisExtension;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ResourceUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StatusUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;
import com.ebmwebsourcing.petals.services.eip.PetalsEipPlugin;
import com.ebmwebsourcing.petals.services.eip.designer.EipDesignerSerializer;

/**
 * The croquis extension for EIP diagrams.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class EipCroquis implements ICroquisExtension {

	private final IFolder croquisFolder;
	private String fileName, chainName;



	/**
	 * Constructor.
	 */
	public EipCroquis() {
		IProject p = ResourcesPlugin.getWorkspace().getRoot().getProject( CROQUIS_PROJECT_NAME );
		this.croquisFolder = p.getFolder( getSubDirectoryName());
	}


	/* (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.croquis.internal.ICroquisExtension
	 * #getSubDirectoryName()
	 */
	public String getSubDirectoryName() {
		return "EIP-Chains";
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.croquis.internal.provisional.ICroquisExtension
	 * #getLabel()
	 */
	public String getLabel() {
		return "EIP Chains";
	}


	/* (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.croquis.internal.ICroquisExtension
	 * #getScreenshot()
	 */
	public ImageDescriptor getScreenshot() {
		return PetalsEipPlugin.getImageDescriptor( "icons/screenshots/overview.png" );
	}


	/* (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.croquis.internal.ICroquisExtension
	 * #performFinish(org.eclipse.core.resources.IFolder)
	 */
	public IStatus performFinish( IProgressMonitor monitor ) {

		IStatus result = Status.OK_STATUS;
		IFile createdFile = getFile();
		if( ! createdFile.exists()) {
			try {
				StringBuilder sb = new StringBuilder();
				sb.append( EipDesignerSerializer.CHAIN_TITLE + " = " + this.chainName + "\n" );
				sb.append( EipDesignerSerializer.CURRENT_SERIAL_ID + " = " + EipDesignerSerializer.CURRENT_SERIAL_ID + "\n" );
				createdFile.create( new ByteArrayInputStream( sb.toString().getBytes()), true, monitor );

			} catch( CoreException e ) {
				result = StatusUtils.createStatus( e, "" );
			}
		}

		return result;
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.croquis.internal.provisional.ICroquisExtension
	 * #getTitle()
	 */
	public String getTitle() {
		return "New EIP Chain";
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.croquis.internal.provisional.ICroquisExtension
	 * #getDescription()
	 */
	public String getDescription() {
		return "Create graphically a chain of Enterprise Integration Patterns.";
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.croquis.internal.provisional.ICroquisExtension
	 * #performAfterFinish()
	 */
	public void performAfterFinish() {

		IFile createdFile = getFile();
		try {
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IDE.openEditor( page, createdFile );

		} catch( PartInitException e ) {
			PetalsEipPlugin.log( e, IStatus.ERROR );
		}

		ResourceUtils.selectResourceInPetalsExplorer( true, createdFile );
	}


	/*
	 * (non-Jsdoc)
	 * @see com.ebmwebsourcing.petals.common.croquis.internal.provisional.ICroquisExtension
	 * #createControl(org.eclipse.swt.widgets.Composite, com.ebmwebsourcing.petals.common.croquis.internal.provisional.CroquisNewWizardPage)
	 */
	public void createControl( Composite parent, final CroquisNewWizardPage page ) {

		SwtFactory.createLabel( parent, "Chain Title:", "The name of the EIP chain to create" );
		Text chainText = SwtFactory.createSimpleTextField( parent, true );

		SwtFactory.createLabel( parent, "File Name:", "The name of the file to create" );
		final Text fileText = SwtFactory.createSimpleTextField( parent, true );
		fileText.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				EipCroquis.this.fileName = ((Text) e.widget).getText().trim();
				validate( page );
			}
		});

		chainText.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				EipCroquis.this.chainName = ((Text) e.widget).getText().trim();
				String s = EipCroquis.this.chainName.replace( " ", "" ).replaceAll( "\\W", "_" );
				fileText.setText( s );
			}
		});
	}


	/**
	 * Validates the private fields.
	 * @param page the page to update in case of error
	 */
	private void validate( CroquisNewWizardPage page ) {

		String msg = null;
		if( StringUtils.isEmpty( this.fileName ))
			msg = "You must set the name of the file to create.";
		else if( ! this.fileName.matches( "\\w+" ))
			msg = "The file name can only contain alphanumeric characters (and underscores).";
		else if( this.fileName.contains( "." ) && ! this.fileName.endsWith( ".peip" ))
			msg = "The file must have a .peip extension.";
		else if( getFile().exists())
			msg = "There is already an EIP chain file with this name.";
		if( StringUtils.isEmpty( this.chainName ))
			msg = "You must set the name of the EIP chain.";

		page.updateStatus( msg );
	}


	/**
	 * @return the file to create
	 */
	private IFile getFile() {

		String s = this.fileName;
		if( ! s.endsWith( ".peip" ))
			s+= ".peip";

		return this.croquisFolder.getFile( s );
	}
}
