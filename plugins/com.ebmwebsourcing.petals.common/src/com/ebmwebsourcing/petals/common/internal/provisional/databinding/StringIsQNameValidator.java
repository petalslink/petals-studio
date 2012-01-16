/***************************************************************************import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
ived a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.databinding;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.common.internal.PetalsCommonPlugin;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.NamespaceUtils;

/**
 * @author Mickaël Istria - EBM WebSourcing
 */
public class StringIsQNameValidator implements IValidator {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.databinding.validation.IValidator
	 * #validate(java.lang.Object)
	 */
	@Override
	public IStatus validate( Object value ) {

		String s = String.valueOf( value );
		return NamespaceUtils.isShortenNamespace( s )
				? Status.OK_STATUS
				: new Status( IStatus.ERROR, PetalsCommonPlugin.PLUGIN_ID, "QName is not valid. A QName must look like '{namespace}localName'" );
	}
}
