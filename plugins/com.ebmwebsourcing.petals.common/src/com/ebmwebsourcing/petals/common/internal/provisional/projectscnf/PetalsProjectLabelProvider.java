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

package com.ebmwebsourcing.petals.common.internal.provisional.projectscnf;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.model.IWorkbenchAdapter;

import com.ebmwebsourcing.petals.common.croquis.internal.projectscnf.PetalsCroquisLabelProvider;
import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.projectscnf.PetalsProjectManager;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.ImageRegistry;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.PlatformUtils;

/**
 * A styled label provider for the Petals project explorer.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsProjectLabelProvider extends LabelProvider implements IStyledLabelProvider {

	protected final ImageRegistry imageRegistry;


	/**
	 * Constructor.
	 */
	public PetalsProjectLabelProvider() {
		this.imageRegistry = new ImageRegistry();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.BaseLabelProvider#dispose()
	 */
	@Override
	public void dispose() {

		this.imageRegistry.dispose();
		super.dispose();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider
	 * #getStyledText(java.lang.Object)
	 */
	public StyledString getStyledText( Object element ) {
		String label = getText( element );
		return label != null ? new StyledString( label ) : null;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider
	 * #getText(java.lang.Object)
	 */
	@Override
	public String getText( Object element ) {

		if( element instanceof PetalsProjectCategory )
			return ((PetalsProjectCategory) element).getLabel();

		// Hack for croquis
		if( PetalsCroquisLabelProvider.getCroquisExtension( element ) != null )
			return null;
		// End of hack

		if( element instanceof IResource )
			return ((IResource) element).getName();

		if( element instanceof IStorage )
			return ((IStorage) element).getName();

		String result = "";
		IWorkbenchAdapter adapter = (IWorkbenchAdapter) PlatformUtils.getAdapter( element, IWorkbenchAdapter.class );
		if( adapter != null )
			result = adapter.getLabel( element );


		// Hack for packages with the hierarchical presentation
		if( element instanceof IPackageFragment
					&& ! PetalsProjectManager.isJavaLayoutFlat()) {

			// Find the "parent" package
			IPackageFragmentRoot root = (IPackageFragmentRoot) ((IPackageFragment) element).getParent();
			String currentParent = "";
			try {
				for( IJavaElement jelt : root.getChildren()) {

					if( jelt instanceof IPackageFragment ) {
						String name = adapter.getLabel( jelt );
						boolean hasChildren = ((IPackageFragment) jelt).getNonJavaResources().length > 0
						|| ((IPackageFragment) jelt).getChildren().length > 0;

						if( hasChildren
									&& result.startsWith( name )
									&& ! result.equals( name )
									&& name.length() > currentParent.length())
							currentParent = name;
					}
				}

			} catch( JavaModelException e ) {
				PetalsCommonPlugin.log( e, IStatus.ERROR );
			}

			// Update the name
			result = result.substring( currentParent.length());
			if( result.startsWith( "." ))
				result = result.substring( 1 );
		}
		// End of hack

		return result;
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider
	 * #getImage(java.lang.Object)
	 */
	@Override
	public Image getImage( Object element ) {

		// Find the image descriptor
		ImageDescriptor descriptor = null;
		IWorkbenchAdapter adapter = (IWorkbenchAdapter) PlatformUtils.getAdapter( element, IWorkbenchAdapter.class );
		if( adapter != null )
			descriptor = adapter.getImageDescriptor( element );
		else if( element instanceof PetalsProjectCategory )
			descriptor = ((PetalsProjectCategory) element).getImageDescriptor();

		// Get the image
		Image img = null;
		if( descriptor != null ) {

			IResource res = (IResource) PlatformUtils.getAdapter( element, IResource.class );
			if( res != null ) {
				if( res.isAccessible()) {
					try {
						int severity = res.findMaxProblemSeverity( null, false, IResource.DEPTH_INFINITE );
						if( severity == IMarker.SEVERITY_ERROR )
							img = this.imageRegistry.getErrorImage( descriptor );
						else if( severity == IMarker.SEVERITY_WARNING )
							img = this.imageRegistry.getWarningImage( descriptor );
						else
							img = this.imageRegistry.getBaseImage( descriptor );

					} catch( CoreException e ) {
						PetalsCommonPlugin.log( e, IStatus.WARNING );
					}
				}

				// Not accessible
				else {
					img = this.imageRegistry.getBaseImage( descriptor );
				}
			}
			else {
				img = this.imageRegistry.getBaseImage( descriptor );
			}
		}

		return img;
	}
}
