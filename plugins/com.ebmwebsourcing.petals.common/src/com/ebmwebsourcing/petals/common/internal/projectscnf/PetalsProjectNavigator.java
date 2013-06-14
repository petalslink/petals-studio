/****************************************************************************
 *
 * Copyright (c) 2010-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to Linagora (80, rue Roque de Fillol - 92800 Puteaux, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.projectscnf;

import java.util.Iterator;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.LinkHelperService;
import org.eclipse.ui.part.ShowInContext;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PlatformUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsProjectNavigator extends CommonNavigator {

	/*
	 * (non-Jsdoc)
	 * @see org.eclipse.ui.navigator.CommonNavigator
	 * #createCommonViewerObject(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected CommonViewer createCommonViewerObject( Composite aParent ) {

		CommonViewer viewer = super.createCommonViewerObject( aParent );
		viewer.addDoubleClickListener( new IDoubleClickListener() {

			@Override
			public void doubleClick( DoubleClickEvent event ) {

				if( event.getSelection() instanceof IStructuredSelection ) {
					Iterator<?> it = ((IStructuredSelection) event.getSelection()).iterator();
					while( it.hasNext()) {

						ICompilationUnit cu = null;
						Object o = it.next();
						if( o instanceof IJavaElement
									&& ((IJavaElement) o).getElementType() == IJavaElement.COMPILATION_UNIT )
							cu = (ICompilationUnit) o;

						if( cu != null ) {
							try {
								JavaUI.openInEditor( cu );

							} catch( PartInitException e ) {
								PetalsCommonPlugin.log( e, IStatus.ERROR );

							} catch( JavaModelException e ) {
								PetalsCommonPlugin.log( e, IStatus.ERROR );
							}
						}
					}
				}
			}
		});

		return viewer;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.IShowInTarget
	 * #show(org.eclipse.ui.part.ShowInContext)
	 */
	@Override
	public boolean show( ShowInContext context ) {

		IStructuredSelection selection = getSelection( context );
		if (selection != null && ! selection.isEmpty()) {
			Object o = selection.getFirstElement();
			if( o instanceof IPackageFragment
						|| o instanceof IPackageFragmentRoot
						|| o instanceof ICompilationUnit ) {
				selectReveal( new StructuredSelection( o ));
				return true;

			} else {
				IResource res = (IResource) PlatformUtils.getAdapter( o, IResource.class );
				if( res != null ) {
					selectReveal( new StructuredSelection( res ));
					return true;
				}
			}
		}

		return false;
	}


	/**
	 * Copies from {@link CommonNavigator}.
	 * @param context
	 * @return
	 */
	private IStructuredSelection getSelection( ShowInContext context ) {

		if (context == null)
			return StructuredSelection.EMPTY;

		ISelection selection = context.getSelection();
		if( selection != null
					&& !selection.isEmpty()
					&& selection instanceof IStructuredSelection )
			return (IStructuredSelection)selection;

		Object input = context.getInput();
		if( input instanceof IEditorInput ) {
			LinkHelperService lhs = getLinkHelperService();
			return lhs.getSelectionFor((IEditorInput) input);
		}

		if( input != null )
			return new StructuredSelection( input );

		return StructuredSelection.EMPTY;
	}


	/**
	 * Forces the content provider to change the way Java packages are displayed (flat / hierarchical).
	 * @param flatLayout
	 */
	public void setFlatLayout( boolean flatLayout ) {

		PetalsProjectManager.storeJavaLayoutPreference( flatLayout );
		CommonViewer viewer = getCommonViewer();
		if( viewer != null  ) {
			viewer.getControl().setRedraw( false );
			viewer.refresh();
			viewer.getControl().setRedraw( true );
		}
	}
}
