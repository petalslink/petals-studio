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
package com.ebmwebsourcing.petals.services.ejb.test;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.services.PetalsImages;

/**
 * @author Mickael Istria (EBM Websourcing)
 *
 */
public class EJBTestDialog extends Dialog {

	private Composite jndiImage;
	private Composite lookupImage;
	private Composite ejbImage;
	private Text errorDescLabel;

	/**
	 * @param parentShell
	 */
	protected EJBTestDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.RESIZE);
	}
	
	@Override
	public Composite createDialogArea(Composite parent) {
		Composite res = new Composite(parent, SWT.NONE);
		res.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		res.setLayout(new GridLayout(2, false));
		
		Label jndiLabel = new Label(res, SWT.NONE);
		jndiLabel.setText("1. Creating JNDI Context");
		jndiImage = new Composite(res, SWT.NONE);
		jndiImage.setBackground(parent.getBackground());
		jndiImage.setLayoutData(new GridData(16, 16));
		
		Label javacompLabel = new Label(res, SWT.NONE);
		javacompLabel.setText("2. Testing basic lookup");
		lookupImage = new Composite(res, SWT.NONE);
		lookupImage.setLayoutData(new GridData(16, 16));
		lookupImage.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		Label ejbLabel = new Label(res, SWT.NONE);
		ejbLabel.setText("3. Trying to get EJB");
		ejbImage = new Composite(res, SWT.NONE);
		ejbImage.setLayoutData(new GridData(16, 16));
		ejbImage.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		new Composite(res, SWT.NONE).setLayoutData(new GridData(-1, 50));
		
		Label errorLabel = new Label(res, SWT.NONE);
		errorLabel.setLayoutData(new GridData(SWT.LEFT, SWT.DEFAULT, true, false, 2, 1));
		errorLabel.setText("Error:");
		errorDescLabel = new Text(res, SWT.MULTI | SWT.BORDER | SWT.WRAP);
		errorDescLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		errorDescLabel.setText("None");
		return res;
	}

	/**
	 * @param ex
	 */
	public void showError(Exception ex) {
		if (jndiImage.getBackgroundImage() != PetalsImages.getOk()) {
			jndiImage.setBackgroundImage(PetalsImages.getKO());
		} else if (lookupImage.getBackgroundImage() != PetalsImages.getOk()) {
			lookupImage.setBackgroundImage(PetalsImages.getKO());
		} else if (ejbImage.getBackgroundImage() != PetalsImages.getOk()) {
			ejbImage.setBackgroundImage(PetalsImages.getKO());
		}
		errorDescLabel.setText(ex.toString());
		errorDescLabel.pack(true);
		errorDescLabel.getParent().pack(true);
		errorDescLabel.getParent().layout(true);
	}

	/**
	 * 
	 */
	public void jndiOk() {
		jndiImage.setBackgroundImage(PetalsImages.getOk());
	}

	/**
	 * 
	 */
	public void lookupOk() {
		lookupImage.setBackgroundImage(PetalsImages.getOk());
	}

	/**
	 * 
	 */
	public void ejbOk() {
		ejbImage.setBackgroundImage(PetalsImages.getOk());
	}

}
