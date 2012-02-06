/****************************************************************************
 *
 * Copyright (c) 2011-2012, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.common.internal.provisional.emf;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.databinding.edit.EditingDomainEObjectObservableValue;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AbstractOverrideableCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ebmwebsourcing.petals.common.internal.Messages;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.StringUtils;
import com.ebmwebsourcing.petals.common.internal.provisional.utils.SwtFactory;

/**
 * @author Mickaël Istria - EBM WebSourcing
 */
public class EObjecttUIHelper {

	/**
	 * A validator for mandatory fields.
	 */
	private static final class MandatoryFieldValidator implements IValidator {
		private final EStructuralFeature feature;

		/**
		 * Constructor.
		 * @param feature
		 */
		public MandatoryFieldValidator(EStructuralFeature feature) {
			this.feature = feature;
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.core.databinding.validation.IValidator
		 * #validate(java.lang.Object)
		 */
		@Override
		public IStatus validate( Object value ) {

			IStatus result = ValidationStatus.ok();
			if( value instanceof String && StringUtils.isEmpty((String) value)) {
				String label = StringUtils.camelCaseToHuman( this.feature.getName());
				label = StringUtils.capitalize( label );
				result = ValidationStatus.error( NLS.bind( Messages.fieldNotSet, label ));
			}

			return result;
		}
	}


	/**
	 * An entry description.
	 */
	public static class EntryDescription {
		public Object widget;
		public EAttribute attribute;

		/**
		 * Constructor.
		 * @param widget
		 * @param att
		 */
		public EntryDescription(Object widget, EAttribute att) {
			this.widget = widget;
			this.attribute = att;
		}
	}


	/**
	 * Generates a 2 column list with left column containing description of widgets and right column containing widget.
	 * @param eObject the eObject to edit
	 * @param toolkit a {@link FormToolkit} to create widgets
	 * @param parent
	 * @param domain the {@link EditingDomain} in case of transactional edition. Can be null, then no transaction is used.
	 * @param dbc
	 * @param toProcessFeatures list of features to edit.
	 * @return
	 */
	public static List<EntryDescription> generateWidgets(
			EObject eObject,
			FormToolkit toolkit,
			Composite parent,
			EditingDomain domain,
			DataBindingContext dbc,
			boolean showDecorator,
			EStructuralFeature... toProcessFeatures ) {

		List<EntryDescription> entries = produceWidgets(toolkit, parent, toProcessFeatures);
		setUpDatabinding(eObject, domain, dbc, showDecorator, entries);
		return entries;
	}


	/**
	 * Sets up the data binding.
	 * @param eObject
	 * @param domain
	 * @param dbc
	 * @param entries
	 */
	private static void setUpDatabinding(
			EObject eObject,
			EditingDomain domain,
			DataBindingContext dbc,
			boolean showDecorator,
			List<EntryDescription> entries ) {

		for (EntryDescription entry : entries) {
			IObservableValue widgetObservable = null;
			if (entry.widget instanceof Text) {
				widgetObservable = SWTObservables.observeDelayedValue( 300, SWTObservables.observeText((Text) entry.widget, SWT.Modify ));

			} else if (entry.widget instanceof Spinner) {
				widgetObservable = SWTObservables.observeSelection((Spinner) entry.widget);

			} else if (entry.widget instanceof ISelectionProvider) {
				widgetObservable = ViewersObservables.observeSingleSelection((ISelectionProvider) entry.widget);

			} else if (entry.widget instanceof Button) {
				widgetObservable = SWTObservables.observeSelection((Button) entry.widget);
			}

			if( widgetObservable != null ) {
				UpdateValueStrategy targetToModel = new UpdateValueStrategy();
				if( entry.attribute.getLowerBound() > 0 )
					targetToModel.setBeforeSetValidator( new MandatoryFieldValidator( entry.attribute ));

				IObservableValue iov = domain == null ?
						EMFObservables.observeValue( eObject, entry.attribute )
						// : EMFEditObservables.observeValue( domain, eObject, entry.attribute );
						: createCustomEmfEditObservable( domain, eObject, entry.attribute );

				Binding binding;
				if( domain == null )
					binding = dbc.bindValue( widgetObservable, iov, targetToModel, null );
				else
					binding = dbc.bindValue( widgetObservable, iov );

				if( showDecorator && entry.attribute.getLowerBound() > 0 )
					ControlDecorationSupport.create( binding, SWT.TOP | SWT.LEFT );
			}
		}
	}



	public static IObservableValue createCustomEmfEditObservable( EditingDomain domain, final EObject eo, final EAttribute ea ) {

		return new EditingDomainEObjectObservableValue( domain, eo, ea ) {
			 @Override
			  protected void doSetValue( final Object value ) {

			    Command command = new AbstractOverrideableCommand( this.domain, "MySetCommand" ) {
			    	private Object oldValue;

					@Override
					public void doExecute() {
						this.oldValue = eo.eGet( ea );
						eo.eSet( ea, value );
					}

					@Override
					public void doUndo() {
						eo.eSet( ea, this.oldValue );
					}

					@Override
					public void doRedo() {
						execute();
					}

					@Override
					public boolean doCanExecute() {
						return true;
					}
			    };

			    this.domain.getCommandStack().execute( command );
			  }
		};
	}


	/**
	 * Produces the widgets.
	 * @param toolkit
	 * @param parent
	 * @param toProcessFeatures
	 * @return
	 */
	private static List<EntryDescription> produceWidgets(
			FormToolkit toolkit,
			Composite parent,
			EStructuralFeature... toProcessFeatures ) {

		List<EntryDescription> entries = new ArrayList<EntryDescription>();
		for (EStructuralFeature feature : toProcessFeatures) {
			Object widget = null;
			EAttribute attr = (EAttribute) feature;

			String label = StringUtils.camelCaseToHuman( attr.getName());
			label = StringUtils.capitalize( label );
			if( attr.getLowerBound() > 0)
				label += " *";

			label += ":";
			toolkit.createLabel(parent, label).setBackground(parent.getBackground());

			// TODO leverage ExtendedMetaData.INSTANCE for tooltip and label
			Class<?> instanceClass = attr.getEType().getInstanceClass();
			if (instanceClass.equals( String.class )) {

				String lowered = label.toLowerCase();
				if( lowered.contains( "password" ) || lowered.contains( "passphrase" ))
					widget = SwtFactory.createPasswordField( parent, false ).getText();

				else if( lowered.contains( "folder" ) || lowered.contains( "directory" ))
					widget = SwtFactory.createDirectoryBrowser( parent ).getText();

				else
					widget = toolkit.createText(parent, "", SWT.BORDER);

				((Text)widget).setLayoutData(new GridData( GridData.FILL_HORIZONTAL ));

			} else if (instanceClass.equals(Integer.class) || instanceClass.equals(int.class)) {
				widget = new Spinner(parent, SWT.BORDER);
				GridDataFactory.swtDefaults().hint( 100, SWT.DEFAULT ).minSize( 100, SWT.DEFAULT ).applyTo((Spinner) widget);
				((Spinner) widget).setMaximum( Integer.MAX_VALUE );

			} else if (instanceClass.isEnum()) {
				widget = new ComboViewer(parent, SWT.READ_ONLY | SWT.FLAT);
				ComboViewer viewer = (ComboViewer) widget;
				viewer.setContentProvider(new EEnumLiteralsProvider());
				viewer.setLabelProvider(new EEnumNameLabelProvider());
				viewer.setInput(attr.getEType());
				viewer.getCombo().setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

			} else if (instanceClass.equals(Boolean.class) || instanceClass.equals(boolean.class)) {
				widget = new ComboViewer(parent, SWT.READ_ONLY | SWT.FLAT);
				ComboViewer viewer = (ComboViewer) widget;
				viewer.setContentProvider( new ArrayContentProvider());
				viewer.setLabelProvider(new LabelProvider());
				viewer.setInput( new Boolean[] { Boolean.TRUE, Boolean.FALSE });

				Combo combo = ((ComboViewer) widget).getCombo();
				GridDataFactory.swtDefaults().hint( 100, SWT.DEFAULT ).minSize( 100, SWT.DEFAULT ).applyTo( combo );
			}

			if( widget != null )
				entries.add( new EntryDescription(widget, attr));
		}

		return entries;
	}
}
