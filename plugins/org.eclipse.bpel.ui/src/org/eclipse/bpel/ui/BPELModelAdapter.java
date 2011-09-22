package org.eclipse.bpel.ui;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.wst.sse.core.internal.provisional.INodeAdapter;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.eclipse.wst.xsd.ui.internal.util.ModelReconcileAdapter;
import org.w3c.dom.Document;

public class BPELModelAdapter implements INodeAdapter
{
	protected ResourceSet resourceSet;
	protected Process process;
	private ModelReconcileAdapter modelReconcileAdapter;

	public Process getProcess()
	{
		return this.process;
	}

	public void setProcess(Process process)
	{
		this.process = process;
	}

	public boolean isAdapterForType(Object type)
	{
		return type == BPELModelAdapter.class;
	}

	public void notifyChanged(INodeNotifier notifier, int eventType, Object changedFeature, Object oldValue, Object newValue, int pos)
	{
	}


	public ModelReconcileAdapter getModelReconcileAdapter()
	{
		return this.modelReconcileAdapter;
	}

	public static BPELModelAdapter lookupOrCreateModelAdapter(Document document)
	{
		BPELModelAdapter adapter = null;
		if (document instanceof INodeNotifier)
		{
			INodeNotifier notifier = (INodeNotifier)document;
			adapter = (BPELModelAdapter)notifier.getAdapterFor(BPELModelAdapter.class);
			if (adapter == null)
			{
				adapter = new BPELModelAdapter();
				notifier.addAdapter(adapter);
			}
		}
		return adapter;
	}
}
