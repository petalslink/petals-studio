/******************************************************************************
 * Copyright (c) 2011, EBM WebSourcing
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     EBM WebSourcing - initial API and implementation
 *******************************************************************************/

package com.ebmwebsourcing.petals.common.internal.formeditor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.ide.IDE;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.IJbiEditorPersonality;
import com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition;
import com.ebmwebsourcing.petals.common.internal.provisional.swt.PetalsHyperlinkListener;
import com.sun.java.xml.ns.jbi.Jbi;

/**
 * A default personality for the JBI form editor.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class JbiDefaultPersonality implements IJbiEditorPersonality {

	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.formeditor.IJbiEditorPersonality
	 * #getStatusLineLabelProvider()
	 */
	@Override
	public ILabelProvider getStatusLineLabelProvider() {
		return new LabelProvider();
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.formeditor.IJbiEditorPersonality
	 * #dispose()
	 */
	@Override
	public void dispose() {
		// nothing
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.formeditor.IJbiEditorPersonality
	 * #matchesPersonality(com.sun.java.xml.ns.jbi.Jbi, org.eclipse.core.resources.IFile)
	 */
	@Override
	public boolean matchesPersonality( Jbi model, IFile editedFile ) {
		return true;
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.formeditor.IJbiEditorPersonality
	 * #createControl(org.eclipse.swt.widgets.Composite, com.ebmwebsourcing.petals.common.internal.provisional.formeditor.ISharedEdition)
	 */
	@Override
	public void createControl( final Composite parent, final ISharedEdition ise ) {

		Composite c = ise.getFormToolkit().createComposite( parent );
		c.setLayout( new GridLayout());
		c.setLayoutData( new GridData( GridData.FILL_BOTH ));

		StringBuilder sb = new StringBuilder();
		sb.append( "<form>" );
		sb.append( "<p>This <b>jbi.xml</b> file cannot be read by this editor.</p>" );
		sb.append( "</form>" );

		FormText formText = ise.getFormToolkit().createFormText( c, false );
		formText.setLayoutData( new GridData( SWT.CENTER, SWT.BOTTOM, true, true ));
		formText.setText( sb.toString(), true, false );

		sb = new StringBuilder();
		sb.append( "<form>" );
		sb.append( "<p>Please, use the <a>Source Editor</a> instead.</p>" );
		sb.append( "</form>" );

		formText = ise.getFormToolkit().createFormText( c, false );
		formText.setLayoutData( new GridData( SWT.CENTER, SWT.TOP, true, true ));
		formText.setText( sb.toString(), true, false );

		formText.addHyperlinkListener( new PetalsHyperlinkListener() {
			@Override
			public void linkActivated( HyperlinkEvent e ) {

				try {
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					page.closeEditor( page.getActiveEditor(), false );
					IDE.openEditor( page, ise.getEditedFile(), "com.ebmwebsourcing.petals.common.sourceeditor" );

				} catch( Exception e1 ) {
					PetalsCommonPlugin.log( e1, IStatus.ERROR );
					MessageDialog.openError( parent.getShell(), "Error", "An error occurred while opening the file in the source editor." );
				}
			}
		});
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.formeditor.IJbiEditorPersonality
	 * #getTitle()
	 */
	@Override
	public String getTitle() {
		return "Not Supported";
	}


	/* (non-Javadoc)
	 * @see com.ebmwebsourcing.petals.common.internal.provisional.formeditor.IJbiEditorPersonality
	 * #getTitleImage()
	 */
	@Override
	public Image getTitleImage() {
		return null;
	}
}
