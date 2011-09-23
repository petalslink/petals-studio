package com.ebmwebsourcing.petals.jbi.editor.form.sa;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.sun.java.xml.ns.jbi.ServiceAssembly;

final class SATreeContentProvider implements ITreeContentProvider {
	/**
	 * 
	 */
	private final SaMasterPage saMasterPage;

	/**
	 * @param saMasterPage
	 */
	SATreeContentProvider(SaMasterPage saMasterPage) {
		this.saMasterPage = saMasterPage;
	}

	public void inputChanged( Viewer viewer, Object oldInput, Object newInput ) {
		// nothing
	}

	public void dispose() {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider
	 * #getElements(java.lang.Object)
	 */
	public Object[] getElements( Object inputElement ) {
		return new Object[] { saMasterPage.saFormPage.getJbi().getServiceAssembly() };
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider
	 * #hasChildren(java.lang.Object)
	 */
	public boolean hasChildren( Object element ) {
		return getChildren( element ).length > 0;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider
	 * #getParent(java.lang.Object)
	 */
	public Object getParent( Object element ) {
		if (element instanceof EObject) {
			return ((EObject)element).eContainer();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider
	 * #getChildren(java.lang.Object)
	 */
	public Object[] getChildren( Object parentElement ) {
		return ((ServiceAssembly)parentElement).getServiceUnit().toArray();
	}
}