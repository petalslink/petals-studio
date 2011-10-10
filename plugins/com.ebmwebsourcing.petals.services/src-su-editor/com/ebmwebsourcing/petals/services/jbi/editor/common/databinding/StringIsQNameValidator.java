package com.ebmwebsourcing.petals.services.jbi.editor.common.databinding;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.ebmwebsourcing.petals.services.Messages;

public class StringIsQNameValidator implements IValidator {

	@Override
	public IStatus validate(Object value) {
		String s = (String)value;
		if ((s.startsWith("{") && s.contains("}") && s.charAt(s.length() - 1) != '}')
			|| !s.contains("{")) {
			return Status.OK_STATUS;
		} else {
			return new Status(IStatus.ERROR, null, Messages.notValidQName);
		}
	}

}
