/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpel.ui.properties;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpel.common.ui.assist.FieldAssistAdapter;
import org.eclipse.bpel.common.ui.details.IDetailsAreaConstants;
import org.eclipse.bpel.common.ui.flatui.FlatFormAttachment;
import org.eclipse.bpel.common.ui.flatui.FlatFormData;
import org.eclipse.bpel.model.BPELFactory;
import org.eclipse.bpel.model.Invoke;
import org.eclipse.bpel.model.OnEvent;
import org.eclipse.bpel.model.OnMessage;
import org.eclipse.bpel.model.PartnerLink;
import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.model.Receive;
import org.eclipse.bpel.model.Reply;
import org.eclipse.bpel.model.Scope;
import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.model.Variables;
import org.eclipse.bpel.model.partnerlinktype.PartnerLinkType;
import org.eclipse.bpel.model.partnerlinktype.Role;
import org.eclipse.bpel.ui.BPELUIPlugin;
import org.eclipse.bpel.ui.IHelpContextIds;
import org.eclipse.bpel.ui.Messages;
import org.eclipse.bpel.ui.adapters.ILabeledElement;
import org.eclipse.bpel.ui.commands.AddPartnerLinkCommand;
import org.eclipse.bpel.ui.commands.AddVariableCommand;
import org.eclipse.bpel.ui.commands.CompoundCommand;
import org.eclipse.bpel.ui.commands.SetCommand;
import org.eclipse.bpel.ui.commands.SetOnEventVariableTypeCommand;
import org.eclipse.bpel.ui.commands.SetOperationCommand;
import org.eclipse.bpel.ui.commands.SetPartnerLinkCommand;
import org.eclipse.bpel.ui.commands.SetVariableCommand;
import org.eclipse.bpel.ui.commands.SetWSDLFaultCommand;
import org.eclipse.bpel.ui.details.providers.ModelLabelProvider;
import org.eclipse.bpel.ui.details.providers.ModelTreeLabelProvider;
import org.eclipse.bpel.ui.details.providers.OperationContentProvider;
import org.eclipse.bpel.ui.details.providers.PartnerLinkContentProvider;
import org.eclipse.bpel.ui.details.providers.PartnerLinkTreeContentProvider;
import org.eclipse.bpel.ui.details.providers.PartnerRoleFilter;
import org.eclipse.bpel.ui.details.providers.WSDLFaultContentProvider;
import org.eclipse.bpel.ui.details.tree.ITreeNode;
import org.eclipse.bpel.ui.dialogs.PartnerLinkRoleSelectorDialog;
import org.eclipse.bpel.ui.dialogs.PartnerLinkTypeSelectorDialog;
import org.eclipse.bpel.ui.proposal.providers.ModelContentProposalProvider;
import org.eclipse.bpel.ui.proposal.providers.RunnableProposal;
import org.eclipse.bpel.ui.proposal.providers.Separator;
import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.bpel.ui.util.ListMap;
import org.eclipse.bpel.ui.util.ModelHelper;
import org.eclipse.bpel.ui.util.MultiObjectAdapter;
import org.eclipse.bpel.ui.util.NameDialog;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalListener;
import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.wst.wsdl.Fault;
import org.eclipse.wst.wsdl.Input;
import org.eclipse.wst.wsdl.Message;
import org.eclipse.wst.wsdl.Operation;
import org.eclipse.wst.wsdl.Output;
import org.eclipse.wst.wsdl.PortType;
import org.eclipse.wst.wsdl.WSDLFactory;

/**
 * Details section for the Partner/PortType/Operation properties of a
 * Receive/Reply/Invoke activity and OnMessage.
 */
public class InvokeImplSection extends BPELPropertySection {

	static final String NONE = Messages.InvokeImplSection_None_1;

	protected static final int PARTNER_CONTEXT = 0;
	protected static final int OPERATION_CONTEXT = 1;
	protected static final int FAULTNAME_CONTEXT = 4;

	private static final int lastChangeContext = -1;

	private Composite parentComposite;

	private Label partnerLabel, operationLabel;
	private Text partnerName;
	private Hyperlink interfaceName;
	private Button partnerBrowseButton;
	private Text operationText;
	private Button faultButton;
	private boolean isInvoke;
	private final PartnerRoleFilter fPartnerRoleFilter = new PartnerRoleFilter();
	private Label quickPickLabel;
	private Tree quickPickTree;
	private TreeViewer quickPickTreeViewer;
	private Composite faultComposite;
	private Label faultLabel;
	private Text faultText;

	private final IControlContentAdapter fTextContentAdapter = new TextContentAdapter() {
		@Override
		public void insertControlContents(Control control, String text, int cursorPosition) {
			if (text != null) {
				super.insertControlContents(control, text, cursorPosition);
			}
		}
		@Override
		public void setControlContents(Control control, String text, int cursorPosition) {
			if (text != null) {
				super.setControlContents(control, text, cursorPosition);
			}
		}
	};

	private Button operationButton;

	private RunnableProposal fWSDLEditRunnableProposal;

	public static final int SPLIT_POINT = 55;
	public static final int SPLIT_POINT_OFFSET = 3 * IDetailsAreaConstants.HSPACE;


	private static final PartnerLink IGNORE_PARTNER_LINK = BPELFactory.eINSTANCE.createPartnerLink();

	private static final Operation IGNORE_OPERATION = WSDLFactory.eINSTANCE.createOperation();

	/**
	 * Stretch this section to maximum use of space
	 */
	@Override
	public boolean shouldUseExtraSpace () {
		return true;
	}

	private static List<String> getVariablesNamesInUse(EObject parent) {
		List<String> variablesNames = new ArrayList<String>();
		Variables variables;
		for (;parent != null; parent = parent.eContainer()) {
			if (parent instanceof Process) {
				variables = ((Process)parent).getVariables();
			} else if (parent instanceof Scope) {
				variables = ((Scope)parent).getVariables();
			} else {
				continue;
			}
			EList<Variable> variableList = variables.getChildren();
			for (Variable var : variableList) {
				variablesNames.add(var.getName());
			}
		}

		return variablesNames;
	}

	/**
	 * The same as labelWordFor(), except these strings don't contain mnemonics!
	 * @param direction
	 * @return the label
	 */
	private String plainLabelWordFor (EObject input, int direction) {
		if (this.isInvoke || !(input instanceof Reply)) {
			return (direction == ModelHelper.OUTGOING || direction == ModelHelper.NOT_SPECIFIED)? Messages.InvokeImplDetails_Request_3_Plain:Messages.InvokeImplDetails_Response_4_Plain;
		}
		return (direction == ModelHelper.OUTGOING || direction == ModelHelper.NOT_SPECIFIED)? Messages.InvokeImplDetails_Response_4_Plain:Messages.InvokeImplDetails_Request_3_Plain;
	}

	@Override
	protected MultiObjectAdapter[] createAdapters() {
		return new MultiObjectAdapter[] {
					/* model object */
					new MultiObjectAdapter() {

						@Override
						public void notify(Notification n) {
							try {
								Object input = getInput();
								boolean upp = false;
								// TODO: can the calls to updatePortTypeWidgets() be improved?
								if (ModelHelper.isPartnerAffected(input, n)) {
									upp = true;
									updatePartnerWidgets();
									updatePortTypeWidgets();
									updateOperationWidgets();
									updateFaultWidgets();
								} else if (ModelHelper.isOperationAffected(input, n)) {
									upp = true;
									updatePortTypeWidgets();
									updateOperationWidgets();
									updateFaultWidgets();
								} else {
									updateFaultWidgets();
								}
								// Update the response variable widgets, because if the operation
								// is one-way, these widgets should be disabled.
								rearrangeWidgets();

								if (replyTypeEnabled() && ModelHelper.isFaultNameAffected(input, n)) {
									updateFaultWidgets();
								}

							} catch (Exception e) {
								BPELUIPlugin.log(e);
							}
						}
					},
		};
	}

	protected boolean replyTypeEnabled () {

		if ((getInput() instanceof Reply) == false) {
			return false;
		}
		return true;
	}


	private void doChildLayout() {
		this.faultComposite.setVisible( replyTypeEnabled() );
		this.parentComposite.layout(true);
	}

	@Override
	protected void basicSetInput(EObject input) {

		super.basicSetInput(input);
		rearrangeWidgets();

		updateQuickPickWidgets();
		updatePartnerWidgets();
		updatePortTypeWidgets();
		updateOperationWidgets();
		updateFaultWidgets();

	}

	private void rearrangeWidgets() {

		Object input = getInput();
		this.isInvoke = (input instanceof Invoke);

		this.fPartnerRoleFilter.setRequireMyRole( !this.isInvoke );
		this.fPartnerRoleFilter.setRequirePartnerRole( this.isInvoke );

		doChildLayout();
	}


	private Composite createPartnerWidgets(Composite top, Composite parent) {

		FlatFormData data;

		final Composite composite = createFlatFormComposite(parent);

		data = new FlatFormData();
		if (top == null) {
			data.top = new FlatFormAttachment(0, IDetailsAreaConstants.VSPACE);
		} else {
			data.top = new FlatFormAttachment(top, IDetailsAreaConstants.VSPACE);
		}
		data.left = new FlatFormAttachment(0, IDetailsAreaConstants.HSPACE);
		data.right = new FlatFormAttachment(SPLIT_POINT, -SPLIT_POINT_OFFSET);

		composite.setLayoutData(data);

		this.partnerLabel = this.fWidgetFactory.createLabel(composite, Messages.InvokeImplDetails_Partner__10);
		this.partnerName = this.fWidgetFactory.createText(composite, EMPTY_STRING, SWT.NONE);
		this.partnerBrowseButton = this.fWidgetFactory.createButton(composite,EMPTY_STRING,SWT.ARROW | SWT.DOWN | SWT.RIGHT );

		// Content Assist for Partner Link

		RunnableProposal proposal = new RunnableProposal() {

			@Override
			public String getLabel() {
				return Messages.InvokeImplSection_0;
			}
			public void run() {
				createPartnerLink ( ModelHelper.getProcess( getInput () ) , null );
			}
		};

		RunnableProposal proposal2 = new RunnableProposal() {

			@Override
			public String getLabel() {
				return Messages.InvokeImplSection_1;
			}
			public void run() {
				createPartnerLink ( ModelHelper.getContainingScope( getInput()), null);
			}
		};

		RunnableProposal proposal3 = new RunnableProposal() {
			@Override
			public String getLabel() {
				return Messages.InvokeImplSection_2;
			}
			public void run() {
				CompoundCommand cmd = new CompoundCommand();
				cmd.getCommands().addAll( basicCommandList( getInput() , null, null )) ;
				getCommandFramework().execute( cmd );
			}
		};

		PartnerLinkContentProvider provider = new PartnerLinkContentProvider();
		ModelContentProposalProvider proposalProvider;
		proposalProvider = new ModelContentProposalProvider( new ModelContentProposalProvider.ValueProvider () {
			@Override
			public Object value() {
				return getInput();
			}
		}, provider,this.fPartnerRoleFilter );

		proposalProvider.addProposalToEnd( new Separator () );
		proposalProvider.addProposalToEnd( proposal );
		proposalProvider.addProposalToEnd( proposal2 );
		proposalProvider.addProposalToEnd( proposal3 );
		proposalProvider.addProposalToEnd(  getWSDLEdit() );

		final FieldAssistAdapter contentAssist = new FieldAssistAdapter (
					this.partnerName,
					this.fTextContentAdapter,
					proposalProvider,
					null,
					null, true );
		//
		contentAssist.setLabelProvider( new ModelLabelProvider () );
		contentAssist.setPopupSize( new Point(300,100) );
		contentAssist.setFilterStyle(ContentProposalAdapter.FILTER_CUMULATIVE);
		contentAssist.setProposalAcceptanceStyle( ContentProposalAdapter.PROPOSAL_REPLACE);
		contentAssist.addContentProposalListener( proposal );
		contentAssist.addContentProposalListener( proposal2 );
		contentAssist.addContentProposalListener( proposal3 );
		contentAssist.addContentProposalListener( getWSDLEdit() );
		contentAssist.addContentProposalListener(new IContentProposalListener () {

			public void proposalAccepted(IContentProposal chosenProposal) {
				if (chosenProposal.getContent() == null) {
					return ;
				}
				PartnerLink pl = null;
				try {
					pl = (PartnerLink) ((Adapter)chosenProposal).getTarget();
				} catch (Throwable t) {
					return ;
				}
				CompoundCommand cmd = new CompoundCommand();
				cmd.getCommands().addAll( basicCommandList(getInput(),pl,null) );
				getCommandFramework().execute(cmd);
			}
		});

		// End of Content Assist for variable
		this.partnerBrowseButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				contentAssist.openProposals();
			}
		});

		this.partnerName.addListener(SWT.KeyDown, new Listener () {
			public void handleEvent(Event event) {
				if (event.keyCode == SWT.CR) {
					findAndSetOrCreatePartnerLink( InvokeImplSection.this.partnerName.getText() );
				}
			}
		});
		// End of content assist for partner link


		data = new FlatFormData();
		data.right = new FlatFormAttachment(100, 0);
		data.top = new FlatFormAttachment(this.partnerName,+2,SWT.TOP);
		data.bottom = new FlatFormAttachment(this.partnerName,-2,SWT.BOTTOM);
		this.partnerBrowseButton.setLayoutData(data);

		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, BPELUtil.calculateLabelWidth(this.partnerLabel, STANDARD_LABEL_WIDTH_SM));
		data.right = new FlatFormAttachment(this.partnerBrowseButton, 0);
		this.partnerName.setLayoutData(data);


		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, 0);
		data.right = new FlatFormAttachment(this.partnerName, -IDetailsAreaConstants.HSPACE);
		data.top = new FlatFormAttachment(this.partnerName, 0, SWT.CENTER);
		this.partnerLabel.setLayoutData(data);

		this.partnerBrowseButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				contentAssist.openProposals();
			}
		});

		return composite;
	}


	/**
	 * @param top
	 * @param parent
	 * @return
	 */
	private Composite createPortTypeWidgets (Composite top, Composite parent) {

		return top ;

		//		FlatFormData data;
		//		final Composite composite = createFlatFormComposite(parent);
		//		data = new FlatFormData();
		//		if (top == null) {
		//			data.top = new FlatFormAttachment(0, IDetailsAreaConstants.VSPACE);
		//		} else {
		//			data.top = new FlatFormAttachment(top, IDetailsAreaConstants.VSPACE);
		//		}
		//		data.left = new FlatFormAttachment(0, IDetailsAreaConstants.HSPACE);
		//		data.right = new FlatFormAttachment(SPLIT_POINT, -SPLIT_POINT_OFFSET);
		//		composite.setLayoutData(data);
		//
		//		Label interfaceLabel = this.fWidgetFactory.createLabel(composite, Messages.InvokeImplSection_3);
		//		this.interfaceName = this.fWidgetFactory.createHyperlink(composite, EMPTY_STRING, SWT.NONE);
		//		this.interfaceName.addHyperlinkListener(new HyperlinkAdapter() {
		//			@Override
		//			public void linkActivated(HyperlinkEvent e) {
		//				PortType pt =  ModelHelper.getPortType(getInput());
		//				if (pt != null) {
		//					BPELUtil.openEditor( pt, getBPELEditor() );
		//				}
		//			}
		//		});
		//
		//		this.interfaceName.setToolTipText(Messages.InvokeImplSection_4);
		//
		//		data = new FlatFormData();
		//		data.top = new FlatFormAttachment(0, IDetailsAreaConstants.VSPACE);
		//		data.left = new FlatFormAttachment(0, BPELUtil.calculateLabelWidth( interfaceLabel, STANDARD_LABEL_WIDTH_SM));
		//		data.right = new FlatFormAttachment(100,0);
		//		this.interfaceName.setLayoutData(data);
		//
		//		data = new FlatFormData();
		//		data.left = new FlatFormAttachment(0, 0);
		//		data.right = new FlatFormAttachment(this.interfaceName, 0);
		//		data.top = new FlatFormAttachment(this.interfaceName, 0, SWT.CENTER);
		//		interfaceLabel.setLayoutData(data);
		//
		//		return composite;
	}


	private  Composite createOperationWidgets(Composite top, Composite parent) {
		FlatFormData data;

		final Composite composite = createFlatFormComposite(parent);
		data = new FlatFormData();
		if (top == null) {
			data.top = new FlatFormAttachment(0, IDetailsAreaConstants.VSPACE);
		} else {
			data.top = new FlatFormAttachment(top,  IDetailsAreaConstants.VSPACE );
		}
		data.left = new FlatFormAttachment(0, IDetailsAreaConstants.HSPACE);
		data.right = new FlatFormAttachment(SPLIT_POINT, -SPLIT_POINT_OFFSET);
		composite.setLayoutData(data);

		this.operationLabel = this.fWidgetFactory.createLabel(composite, Messages.InvokeImplDetails_Operation__19);
		this.operationText = this.fWidgetFactory.createText(composite,EMPTY_STRING,SWT.NONE);
		this.operationButton = this.fWidgetFactory.createButton(composite,EMPTY_STRING,SWT.ARROW|SWT.CENTER|SWT.DOWN);

		// Provide Content Assist for the variables
		OperationContentProvider provider = new OperationContentProvider();
		ModelContentProposalProvider proposalProvider;
		proposalProvider = new ModelContentProposalProvider( new ModelContentProposalProvider.ValueProvider () {
			@Override
			public Object value() {
				return getInput();
			}
		}, provider);

		proposalProvider.addProposalToEnd( new Separator () );
		proposalProvider.addProposalToEnd( getWSDLEdit() );

		final FieldAssistAdapter contentAssist = new FieldAssistAdapter (
					this.operationText,
					this.fTextContentAdapter,
					proposalProvider,
					null,
					null, true );
		//
		contentAssist.setLabelProvider( new ModelLabelProvider () );
		contentAssist.setPopupSize( new Point(300,100) );
		contentAssist.setFilterStyle(ContentProposalAdapter.FILTER_CUMULATIVE);
		contentAssist.setProposalAcceptanceStyle( ContentProposalAdapter.PROPOSAL_REPLACE);
		contentAssist.addContentProposalListener( getWSDLEdit() );

		contentAssist.addContentProposalListener(new IContentProposalListener () {

			public void proposalAccepted(IContentProposal chosenProposal) {
				if (chosenProposal.getContent() == null) {
					return ;
				}
				Operation oper = null;
				try {
					oper = (Operation) ((Adapter)chosenProposal).getTarget();
				} catch (Throwable t) {
					return ;
				}
				List<Command> list = basicCommandList(getInput(), IGNORE_PARTNER_LINK , oper );
				CompoundCommand cmd = new CompoundCommand();
				cmd.getCommands().addAll (list);
				getCommandFramework().execute( cmd );
			}
		});

		// End of Content Assist for variable

		this.operationButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				contentAssist.openProposals();
			}
		});

		this.operationText.addListener(SWT.KeyDown, new Listener () {
			public void handleEvent(Event event) {
				if (event.keyCode == SWT.CR) {
					findAndSetOperation ( InvokeImplSection.this.operationText.getText() );
				}
			}
		});

		// end of content assist


		data = new FlatFormData();
		data.right = new FlatFormAttachment(100,0);
		data.top = new FlatFormAttachment(this.operationText,+2,SWT.TOP);
		data.bottom = new FlatFormAttachment(this.operationText,-2,SWT.BOTTOM);
		this.operationButton.setLayoutData(data);


		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, BPELUtil.calculateLabelWidth(this.operationLabel, STANDARD_LABEL_WIDTH_SM));
		data.right = new FlatFormAttachment(this.operationButton, 0);
		this.operationText.setLayoutData(data);

		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, 0);
		data.right = new FlatFormAttachment(this.operationText, -IDetailsAreaConstants.HSPACE);
		data.top = new FlatFormAttachment(this.operationText, 0, SWT.CENTER);
		this.operationLabel.setLayoutData(data);

		return composite;
	}


	/**
	 * @param top
	 * @param parent
	 * @return
	 */
	private Composite createFaultComposite(Composite top, Composite parent) {
		FlatFormData data;

		final Composite composite = this.faultComposite = createFlatFormComposite(parent);

		data = new FlatFormData();
		if (top == null) {
			data.top = new FlatFormAttachment(0, IDetailsAreaConstants.VSPACE);
		} else {
			data.top = new FlatFormAttachment(top, IDetailsAreaConstants.VSPACE);
		}
		data.left = new FlatFormAttachment(0, IDetailsAreaConstants.HSPACE);
		data.right = new FlatFormAttachment(InvokeImplSection.SPLIT_POINT,
					-InvokeImplSection.SPLIT_POINT_OFFSET);
		composite.setLayoutData(data);

		this.faultLabel = this.fWidgetFactory.createLabel(composite,
					Messages.InvokeImplDetails_Fault_Name__25);
		this.faultText = this.fWidgetFactory.createText(composite, EMPTY_STRING);
		this.faultButton = this.fWidgetFactory.createButton(composite, EMPTY_STRING,
					SWT.ARROW | SWT.DOWN | SWT.CENTER);
		// Provide Content Assist for the operation

		WSDLFaultContentProvider provider = new WSDLFaultContentProvider();
		ModelContentProposalProvider proposalProvider;
		proposalProvider = new ModelContentProposalProvider(
					new ModelContentProposalProvider.ValueProvider() {
						@Override
						public Object value() {
							return ModelHelper.getOperation(getInput());
						}
					}, provider);

		RunnableProposal proposalClearFault = new RunnableProposal() {
			@Override
			public String getLabel() {
				return Messages.InvokeImplSection_25;
			}
			public void run() {
				CompoundCommand cmd = new CompoundCommand();
				cmd.add(new SetWSDLFaultCommand(getInput(), null));
				getCommandFramework().execute( cmd );
			}
		};

		proposalProvider.addProposalToEnd(new Separator());
		proposalProvider.addProposalToEnd(proposalClearFault);

		final FieldAssistAdapter contentAssist = new FieldAssistAdapter(
					this.faultText, this.fTextContentAdapter, proposalProvider, null, null, true);
		//
		contentAssist.setLabelProvider(new ModelLabelProvider());
		contentAssist.setPopupSize(new Point(300, 100));
		contentAssist.setFilterStyle(ContentProposalAdapter.FILTER_CUMULATIVE);
		contentAssist
		.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
		contentAssist.addContentProposalListener(new IContentProposalListener() {
			public void proposalAccepted(IContentProposal chosenProposal) {
				if (chosenProposal.getContent() == null) {
					return;
				}
				Fault fault = null;
				try {
					fault = (Fault) ((Adapter) chosenProposal)
					.getTarget();
				} catch (Throwable t) {
					return;
				}
				CompoundCommand cmd = new CompoundCommand();
				cmd.add(new SetWSDLFaultCommand(getInput(), fault));
				getCommandFramework().execute( cmd );
			}
		});
		contentAssist.addContentProposalListener( proposalClearFault );
		// End of Content Assist for fault

		this.faultButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				contentAssist.openProposals();
			}
		});

		data = new FlatFormData();
		data.right = new FlatFormAttachment(100, 0);
		data.top = new FlatFormAttachment(this.faultText, +2, SWT.TOP);
		data.bottom = new FlatFormAttachment(this.faultText, -2, SWT.BOTTOM);
		this.faultButton.setLayoutData(data);

		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, BPELUtil.calculateLabelWidth(
					this.operationLabel, STANDARD_LABEL_WIDTH_SM));
		data.right = new FlatFormAttachment(this.faultButton, 0);
		this.faultText.setLayoutData(data);

		data = new FlatFormData();
		data.left = new FlatFormAttachment(0, 0);
		data.right = new FlatFormAttachment(this.faultText,
					-IDetailsAreaConstants.HSPACE);
		data.top = new FlatFormAttachment(this.faultText, 0, SWT.CENTER);
		this.faultLabel.setLayoutData(data);

		return composite;
	}


	/**
	 * @param top
	 * @param parent
	 * @return
	 */
	private  Composite createQuickPick (Composite top, Composite parent)
	{
		FlatFormData data;

		final Composite composite = createFlatFormComposite(parent);

		data = new FlatFormData();
		if (top == null) {
			data.top = new FlatFormAttachment(0, IDetailsAreaConstants.VSPACE);
		} else {
			data.top = new FlatFormAttachment(top,IDetailsAreaConstants.VSPACE);
		}

		data.left = new FlatFormAttachment(SPLIT_POINT, SPLIT_POINT_OFFSET);
		data.right = new FlatFormAttachment(100, -IDetailsAreaConstants.HSPACE);
		data.bottom = new FlatFormAttachment(100,-IDetailsAreaConstants.VSPACE);
		composite.setLayoutData(data);

		this.quickPickLabel = this.fWidgetFactory.createLabel(composite, "Quick Pick:");  //$NON-NLS-1$

		// Tree viewer for variable structure ...
		this.quickPickTree = this.fWidgetFactory.createTree(composite, SWT.NONE);
		PartnerLinkTreeContentProvider treeContentProvider = new PartnerLinkTreeContentProvider(true);
		this.quickPickTreeViewer = new TreeViewer(this.quickPickTree);
		this.quickPickTreeViewer.setContentProvider(treeContentProvider);
		this.quickPickTreeViewer.setLabelProvider(new ModelTreeLabelProvider());
		this.quickPickTreeViewer.addFilter( this.fPartnerRoleFilter );
		this.quickPickTreeViewer.setInput ( null );
		this.quickPickTreeViewer.setAutoExpandLevel(3);
		// end tree viewer for variable structure

		data = new FlatFormData();
		data.top = new FlatFormAttachment ( 0,0);
		data.left = new FlatFormAttachment(0,0);
		this.quickPickLabel.setLayoutData(data);

		data = new FlatFormData();
		data.top = new FlatFormAttachment( this.quickPickLabel, IDetailsAreaConstants.VSPACE);
		data.left = new FlatFormAttachment(0, IDetailsAreaConstants.HSPACE);
		data.right = new FlatFormAttachment(100,-IDetailsAreaConstants.HSPACE);
		data.bottom = new FlatFormAttachment(100,-IDetailsAreaConstants.HSPACE);
		this.quickPickTree.setLayoutData(data);

		this.quickPickTreeViewer.addSelectionChangedListener( new ISelectionChangedListener () {

			public void selectionChanged(SelectionChangedEvent event) {
				quickPickSelectionChanged ( event.getSelection() );

			}

		});
		return composite;
	}

	@Override
	protected void createClient(Composite parent)  {

		Composite composite = this.parentComposite = createFlatFormComposite(parent);


		Composite ref = createPartnerWidgets(null,composite);
		ref = createPortTypeWidgets(ref, composite);
		ref = createOperationWidgets(ref,composite);

		ref = createFaultComposite ( ref, composite );


		// This creates it on the top
		ref = createQuickPick(null,composite);

		PlatformUI.getWorkbench().getHelpSystem().setHelp(
					this.parentComposite, IHelpContextIds.PROPERTY_PAGE_INVOKE_IMPLEMENTATION);
	}

	private void updatePartnerWidgets() {

		PartnerLink partnerLink = ModelHelper.getPartnerLink(getInput());
		if (partnerLink == null) {
			this.partnerName.setText(EMPTY_STRING);
		} else {
			ILabeledElement labeledElement = BPELUtil.adapt(partnerLink, ILabeledElement.class);
			this.partnerName.setText(labeledElement.getLabel(partnerLink));
		}
	}


	// TODO: move these to ModelHelper?

	private PortType getEffectivePortType (PartnerLink partnerLink) {
		if (partnerLink != null) {
			Role role = this.isInvoke? partnerLink.getPartnerRole() : partnerLink.getMyRole();
			return ModelHelper.getRolePortType(role);
		}
		return null;
	}


	/**
	 * 
	 */
	private  void updateQuickPickWidgets () {

		this.fPartnerRoleFilter.setRequireMyRole( !this.isInvoke );
		this.fPartnerRoleFilter.setRequirePartnerRole( this.isInvoke );

		Object myInput = getInput();
		if ( myInput != this.quickPickTreeViewer.getInput() ) {
			this.quickPickTreeViewer.setInput( myInput );
			if (myInput != null) {
				this.quickPickTree.getVerticalBar().setSelection(0);
			}
		}
	}


	/**
	 * 
	 */
	private  void updatePortTypeWidgets() {

		if (this.interfaceName != null) {
			PartnerLink partnerLink = ModelHelper.getPartnerLink(getInput());
			PortType portType = getEffectivePortType(partnerLink);
			if (portType == null) {
				this.interfaceName.setText(Messages.InvokeImplSection_None_1);
				this.interfaceName.setEnabled(false);
			} else {
				ILabeledElement labeledElement = BPELUtil.adapt(portType, ILabeledElement.class);
				this.interfaceName.setText(labeledElement.getLabel(portType));
				this.interfaceName.setEnabled(true);
			}
		}
	}

	private  void updateOperationWidgets() {

		Operation operation = ModelHelper.getOperation(getInput());
		if (operation != null) {
			this.operationText.setText( operation.getName() );
		} else {
			this.operationText.setText ( EMPTY_STRING );
		}
	}

	/**
	 * replyFaultEnabled() Checks if the active activity is a reply activity and
	 * has a fault name
	 * 
	 * @return - true When the active activity is a reply activity and has a
	 *         fault set.
	 *         - false When the active activity is no reply activity.
	 *         When the active activity is a reply activity and the fault is
	 *         set.
	 */
	protected boolean replyFaultEnabled() {
		if (this.isInvoke)
			return false;
		if (!(getInput() instanceof Reply))
			return false;
		boolean faultEnabled = (ModelHelper.getFaultName(getInput()) != null);
		return faultEnabled;
	}

	protected void updateFaultWidgets () {
		boolean faultEnabled = replyFaultEnabled();
		if (faultEnabled) {
			Fault fault = ModelHelper.getWSDLFault(getInput());
			if (fault != null) {
				this.faultText.setText( fault.getName() );
			} else {
				this.faultText.setText ( EMPTY_STRING );
			}
		} else {
			this.faultText.setText ( EMPTY_STRING );
		}
	}

	/**
	 * @see org.eclipse.bpel.ui.properties.BPELPropertySection#aboutToBeShown()
	 */
	@Override
	public void aboutToBeShown() {
		super.aboutToBeShown();
		doChildLayout();
	}

	/**
	 * @see org.eclipse.bpel.ui.properties.BPELPropertySection#getUserContext()
	 */
	@Override
	public Object getUserContext() {
		return Integer.valueOf( InvokeImplSection.lastChangeContext );
	}

	/**
	 * @see org.eclipse.bpel.ui.properties.BPELPropertySection#restoreUserContext(java.lang.Object)
	 */
	@Override
	public void restoreUserContext(Object userContext) {

		int i = ((Integer)userContext).intValue();
		switch (i) {
		case PARTNER_CONTEXT: this.partnerName.setFocus(); return;
		case OPERATION_CONTEXT: this.operationText.setFocus(); return;
		case FAULTNAME_CONTEXT: this.faultText.setFocus(); return;
		}
		throw new IllegalStateException();
	}



	private void findAndSetOperation (String text) {

		text = text.trim();
		EObject model = getInput();
		PortType portType = ModelHelper.getPortType( model );

		List<Command> cmdList = basicCommandList( model , IGNORE_PARTNER_LINK, null );

		if (text.length() > 0) {

			Operation op = (Operation) ModelHelper.findElementByName(
						portType ,
						text,
						Operation.class);

			if (op != null) {
				// set that operation
				SetOperationCommand cmd = (SetOperationCommand) ListMap.Find(cmdList, new ListMap.Visitor () {
					public Object visit(Object obj) {
						return (obj instanceof SetOperationCommand ? obj : ListMap.IGNORE );
					}
				});
				cmd.setNewValue(op);
			}
		}

		CompoundCommand cmd = new CompoundCommand();
		cmd.getCommands().addAll( cmdList );
		getCommandFramework().execute ( cmd );
	}


	void findAndSetOrCreatePartnerLink ( String name ) {
		name = name.trim();
		EObject model = getInput();

		PartnerLink pl = null;
		if (name.length() > 0) {

			pl = (PartnerLink) ModelHelper.findElementByName(ModelHelper.getContainingScope(model),
						name, PartnerLink.class);
			// does not exist
			if (pl == null) {
				createPartnerLink ( ModelHelper.getContainingScope(model), name );
				return ;
			}
		}

		CompoundCommand cmd = new CompoundCommand();
		cmd.getCommands().addAll( basicCommandList(model, pl, null));
		getCommandFramework().execute ( cmd );

	}

	private void createPartnerLink ( EObject ref , String name ) {
		PartnerLink pl = BPELFactory.eINSTANCE.createPartnerLink();

		if (name == null) {
			name = EMPTY_STRING;
		}

		// ask for the name, we know the type.
		NameDialog nameDialog = new NameDialog(
					this.parentComposite.getShell(),
					Messages.PartnerLinkSelectorDialog_5,
					Messages.PartnerLinkSelectorDialog_6,
					name,
					BPELUtil.getNCNameValidator());

		if (nameDialog.open() == Window.CANCEL) {
			return ;
		}

		PartnerLinkTypeSelectorDialog dialog = new PartnerLinkTypeSelectorDialog(
					this.partnerName.getShell(),
					getInput());
		if (dialog.open() == Window.CANCEL) {
			return ;
		}
		Object result = dialog.getFirstResult();
		PartnerLinkType plt = null;
		EList<Role> list = null;
		if (result != null && result instanceof PartnerLinkType) {
			plt = (PartnerLinkType) result;
			list = plt.getRole();
		}

		// Ask user about role
		if( list != null && list.size() > 1 ){
			PartnerLinkRoleSelectorDialog roleDialog = new PartnerLinkRoleSelectorDialog(this.parentComposite.getShell(), list, plt);
			if (this.isInvoke){
				roleDialog.setTitle(Messages.PartnerRoleSelectorDialog_Title_PartnerRole);
			} else {
				roleDialog.setTitle(Messages.PartnerRoleSelectorDialog_Title_MyRole);
			}
			if (roleDialog.open() == Window.CANCEL){
				return;
			}
			if (this.isInvoke){
				pl.setPartnerRole(list.get(roleDialog.getSelectedRole()));
			} else {
				pl.setMyRole(list.get(roleDialog.getSelectedRole()));
			}
		} else if( list != null ) {
			if (this.isInvoke){
				pl.setPartnerRole(list.get(0));
			} else {
				pl.setMyRole(list.get(0));
			}
		}

		// set name and type
		pl.setName ( nameDialog.getValue() );
		pl.setPartnerLinkType( plt );

		// ask for partner link type

		List<Command> cmds = basicCommandList(getInput(), pl, null);
		cmds.add(0, new AddPartnerLinkCommand( ref, pl ));
		//
		CompoundCommand cmd = new CompoundCommand();
		cmd.getCommands().addAll(cmds);
		getCommandFramework().execute(cmd);
		// TODO: Is there any way to refresh quick pick without this hack?
		this.quickPickTreeViewer.setInput ( null );
		updateQuickPickWidgets();
	}

	/**
	 * Handle the quick pick from the partner link tree.
	 * 
	 * The logic here will attempt to fill in as much of the details
	 * as possible. It may create variables necessary to for the partner
	 * activity to make sense.
	 * 
	 * @param selection the selection from the tree
	 */

	private void quickPickSelectionChanged ( ISelection selection ) {

		if (selection.isEmpty()) {
			return ;
		}
		ITreeSelection treeSelection = (ITreeSelection) selection;
		quickPickSelectionChanged ( treeSelection.getPaths() );
	}


	private void quickPickSelectionChanged ( TreePath[] paths ) {

		// Assumption is that we are single selection ...
		if (paths.length > 0) {
			quickPickSelectionChanged ( paths[0] );
		}
	}



	private void quickPickSelectionChanged ( TreePath path ) {

		// The tree view contains nodes which may have multiple parents
		// so we have to walk the selection using the visual elements

		//		org.eclipse.bpel.ui.details.tree.PartnerLinkTreeNode@3905e3
		//		org.eclipse.bpel.ui.details.tree.PortTypeTreeNode@13d765c
		//		org.eclipse.bpel.ui.details.tree.OperationTreeNode@fcc070
		//		org.eclipse.bpel.ui.details.tree.MessageTypeTreeNode@1167d36
		//		org.eclipse.bpel.ui.details.tree.PartTreeNode@33910a
		//		org.eclipse.bpel.ui.details.tree.XSDElementDeclarationTreeNode@1e96ffd
		//

		EObject input = getInput();
		List cmdList = basicCommandList( input , null, null);

		PartnerLink pl = null;
		Operation op = null;
		SetCommand setCommand = null;

		for (int i=0,j=path.getSegmentCount(); i < j; i++) {

			Object model = null;
			try {
				model = ((ITreeNode) path.getSegment(i)).getModelObject();
			} catch (Exception ex) {
				// should not happen
				BPELUIPlugin.log(ex);
				break;
			}

			if (model instanceof PartnerLink) {

				pl = (PartnerLink) model;
				setCommand = (SetCommand) ListMap.Find ( cmdList, new ListMap.Visitor() {
					public Object visit(Object obj) {
						return (obj instanceof SetPartnerLinkCommand ? obj : ListMap.IGNORE);
					}
				});
				setCommand.setNewValue( pl );

			} else if (model instanceof PortType) {

				// we don't do anything here ...

			} else if (model instanceof Operation) {

				op = (Operation) model;

				setCommand = (SetCommand) ListMap.Find ( cmdList, new ListMap.Visitor() {
					public Object visit(Object obj) {
						return (obj instanceof SetOperationCommand ? obj : ListMap.IGNORE);
					}
				});
				setCommand.setNewValue( op );

				// attempt to locate a variable matching the type
				alterCommands (cmdList,input,pl,op,(Input) op.getInput()  );
				alterCommands (cmdList,input,pl,op,(Output)op.getOutput() );

			} else {
				break;
			}
			// System.out.println( "segment[" + i + "]=" + path.getSegment( i ));
		}

		CompoundCommand cmd = new CompoundCommand ();
		cmd.getCommands().addAll (cmdList) ;
		getCommandFramework().execute( cmd );
	}



	private void alterCommands (List<Command> list, EObject input, PartnerLink pl , Operation op, Input msg ) {

		if (input instanceof Receive || input instanceof OnMessage ||
					input instanceof OnEvent)
		{
			if (pl.getMyRole() == null || msg == null) {
				return ;
			}
			alterCommands(list, input, msg.getEMessage() , pl);
		}

		if (input instanceof Invoke) {
			if (pl.getPartnerRole() == null || msg == null) {
				return ;
			}
			alterCommands(list, input,msg.getEMessage(),pl);
		}
	}


	private void alterCommands (List<Command> cmds, EObject input, PartnerLink pl, Operation op, Output msg) {
		if (input instanceof Reply) {
			if (pl.getMyRole() == null || msg == null) {
				return ;
			}
			alterCommands(cmds,input,msg.getEMessage(),pl);
		}
		if (input instanceof Invoke) {
			if (pl.getPartnerRole() == null || msg == null) {
				return;
			}
			alterCommands(cmds,input,msg.getEMessage(),pl,ModelHelper.INCOMING);
		}
	}


	private void alterCommands ( List<Command> cmds, EObject input, Message msg, PartnerLink pl) {
		alterCommands ( cmds, input,msg,pl, ModelHelper.NOT_SPECIFIED);
	}


	private void alterCommands (List<Command> cmds, EObject input, Message msg, PartnerLink pl, final int direction) {

		Variable variable = findVariable(input, msg, pl);

		if (variable == null) {
			// no such variable, create one
			variable = BPELFactory.eINSTANCE.createVariable();
			String name = pl.getName() + plainLabelWordFor(input, direction);
			List<String> variablesNamesInUse = getVariablesNamesInUse(input);
			if (variablesNamesInUse.contains(name)) {
				int index = 1;
				while (variablesNamesInUse.contains(name + index)) {
					index++;
				}
				name = name + index;
			}
			variable.setName ( name );
			variable.setMessageType( msg );
			cmds.add(0, new AddVariableCommand(input, variable));
		}

		SetVariableCommand cmd = (SetVariableCommand) ListMap.Find(cmds, new ListMap.Visitor() {
			public Object visit(Object obj) {
				if (obj instanceof SetVariableCommand) {
					SetVariableCommand svc = (SetVariableCommand) obj;
					if (svc.getDirection() == direction) {
						return svc;
					}
				}
				return ListMap.IGNORE;
			}
		});

		cmd.setNewValue( variable );
	}



	/**
	 * Find and select an appropriate variable based on the Message type passed.
	 * The partner link is passed to be used a heuristic in the name search to decided
	 * between several variables that match the requested type.
	 * 
	 * @param input the input element
	 * @param msg the message whose variable type we have to find.
	 * @param pl partner link, optional
	 * @return the chosen variable or null
	 */

	private Variable findVariable ( EObject input, Message msg , PartnerLink pl ) {

		Variable list[] = ModelHelper.getVariablesOfType( input, msg );

		if ( list.length == 1 ) {
			return list[0];
		}

		if ( list.length > 0 ) {

			if (pl == null) {
				return list[0];
			}

			// apply a simple heuristic based on having the partner link in the name.
			String plName = pl.getName();
			for( Variable element : list ) {
				if ( element.getName().indexOf( plName ) >= 0) {
					return element;
				}
			}
			// nothing matched better on name
			return list[0];
		}

		// can't find anything matching.
		return null;
	}


	/**
	 * Return the basic command list for attempting to manipulate the partner activity.
	 * The list returned must be ordered, in the sense that the commands with the most
	 * significance are done first. All the commands will generally be executed, the idea
	 * being that if only partner link is selected, then the reset of the attributes of the
	 * partner activity will be reset.
	 * 
	 * @param input
	 * @return the list of basic commands that are used to manipulate the partner activity
	 */


	private List<Command> basicCommandList (EObject input, PartnerLink pl, Operation op) {
		List<Command> list = new ArrayList<Command>(8);


		if (pl != IGNORE_PARTNER_LINK) {
			list.add ( new SetPartnerLinkCommand(input, pl) );
		}

		if (op != IGNORE_OPERATION) {
			list.add ( new SetOperationCommand(input, op) );
		}

		// These are leaf commands, that can be executed on their own
		list.add ( new SetVariableCommand(input,null) );

		if (input instanceof OnEvent) {
			list.add( new SetOnEventVariableTypeCommand((OnEvent)input) );
		} else if (input instanceof Reply) {
			list.add ( new SetWSDLFaultCommand (input, null) );
		} else if (input instanceof Invoke) {
			list.add ( new SetVariableCommand (input, null, ModelHelper.INCOMING) );
		}
		return list;
	}

	/**
	 * This shows up in a couple of places, so we share it.
	 * @return the runnable proposal for WSDL editing.
	 */

	private RunnableProposal getWSDLEdit ( ) {

		if (this.fWSDLEditRunnableProposal == null) {
			this.fWSDLEditRunnableProposal = new RunnableProposal() {
				@Override
				public String getLabel() {
					return Messages.InvokeImplSection_24;
				}
				public void run() {
					PortType pt =  ModelHelper.getPortType(getInput());
					if (pt != null) {
						BPELUtil.openEditor( pt, getBPELEditor() );
					}
				}
			};
		}
		return this.fWSDLEditRunnableProposal;
	}
}
