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
package com.ebmwebsourcing.petals.services.ejb.jndi;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.ebmwebsourcing.petals.common.internal.provisional.utils.PetalsImages;

/**
 * @author Mickael Istria - EBM WebSourcing
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


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog
	 * #createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public Composite createDialogArea(Composite parent) {
		Composite res = new Composite(parent, SWT.NONE);
		res.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		res.setLayout(new GridLayout(2, false));

		Label jndiLabel = new Label(res, SWT.NONE);
		jndiLabel.setText("1. Creating JNDI Context");
		this.jndiImage = new Composite(res, SWT.NONE);
		this.jndiImage.setBackground(parent.getBackground());
		this.jndiImage.setLayoutData(new GridData(16, 16));

		Label javacompLabel = new Label(res, SWT.NONE);
		javacompLabel.setText("2. Testing basic lookup");
		this.lookupImage = new Composite(res, SWT.NONE);
		this.lookupImage.setLayoutData(new GridData(16, 16));
		this.lookupImage.setBackgroundMode(SWT.INHERIT_DEFAULT);

		Label ejbLabel = new Label(res, SWT.NONE);
		ejbLabel.setText("3. Trying to get EJB");
		this.ejbImage = new Composite(res, SWT.NONE);
		this.ejbImage.setLayoutData(new GridData(16, 16));
		this.ejbImage.setBackgroundMode(SWT.INHERIT_DEFAULT);

		new Composite(res, SWT.NONE).setLayoutData(new GridData(-1, 50));

		Label errorLabel = new Label(res, SWT.NONE);
		errorLabel.setLayoutData(new GridData(SWT.LEFT, SWT.DEFAULT, true, false, 2, 1));
		errorLabel.setText("Error:");
		this.errorDescLabel = new Text(res, SWT.MULTI | SWT.BORDER | SWT.WRAP);
		this.errorDescLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		this.errorDescLabel.setText("None");
		return res;
	}


	/**
	 * @param ex
	 */
	public void showError(Exception ex) {
		if (this.jndiImage.getBackgroundImage() != PetalsImages.getOk()) {
			this.jndiImage.setBackgroundImage(PetalsImages.getKO());
		} else if (this.lookupImage.getBackgroundImage() != PetalsImages.getOk()) {
			this.lookupImage.setBackgroundImage(PetalsImages.getKO());
		} else if (this.ejbImage.getBackgroundImage() != PetalsImages.getOk()) {
			this.ejbImage.setBackgroundImage(PetalsImages.getKO());
		}

		this.errorDescLabel.setText(ex.toString());
		this.errorDescLabel.pack(true);
		this.errorDescLabel.getParent().pack(true);
		this.errorDescLabel.getParent().layout(true);
	}


	/**
	 *
	 */
	public void jndiOk() {
		this.jndiImage.setBackgroundImage(PetalsImages.getOk());
	}


	/**
	 *
	 */
	public void lookupOk() {
		this.lookupImage.setBackgroundImage(PetalsImages.getOk());
	}


	/**
	 *
	 */
	public void ejbOk() {
		this.ejbImage.setBackgroundImage(PetalsImages.getOk());
	}
}
