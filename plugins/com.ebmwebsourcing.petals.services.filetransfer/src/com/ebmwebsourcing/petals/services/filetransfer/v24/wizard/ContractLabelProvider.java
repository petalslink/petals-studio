package com.ebmwebsourcing.petals.services.filetransfer.v24.wizard;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;

import com.ebmwebsourcing.petals.services.filetransfer.Messages;

public class ContractLabelProvider extends LabelProvider implements IBaseLabelProvider {

	@Override
	public String getText(Object item) {
		if (item == Contract.READ_FILES) {
			return Messages.readFileContract;
		} else if (item == Contract.WRITE_FILES) {
			return Messages.writeFileContract;
		} else {
			return super.getText(item);
		}
	}
}
