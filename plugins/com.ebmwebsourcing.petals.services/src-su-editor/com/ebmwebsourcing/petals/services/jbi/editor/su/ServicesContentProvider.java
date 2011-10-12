/****************************************************************************
 * 
 * Copyright (c) 2011, PetalsLink
 * 
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 * 
 * You should have received a copy of the agreement along with this program.
 * If not, write to PetalsLink - EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 * 
 *****************************************************************************/
package com.ebmwebsourcing.petals.services.jbi.editor.su;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.sun.java.xml.ns.jbi.Consumes;
import com.sun.java.xml.ns.jbi.Jbi;
import com.sun.java.xml.ns.jbi.Provides;

public class ServicesContentProvider implements ITreeContentProvider {

	private Jbi jbi;
	public static Object PROVIDE = new Object();
	public static Object CONSUME = new Object();

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.jbi = (Jbi)newInput;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return new Object[] { PROVIDE, CONSUME };
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement == PROVIDE) {
			return jbi.getServices().getProvides().toArray(); 
		} else if (parentElement == CONSUME) {
			return jbi.getServices().getConsumes().toArray();
		} else {
			return null;
		}
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof Provides) {
			return PROVIDE;
		} else if (element instanceof Consumes) {
			return CONSUME;
		} else {
			return null;
		}
	}

	@Override
	public boolean hasChildren(Object element) {
		return element == PROVIDE || element == CONSUME;
	}

}
