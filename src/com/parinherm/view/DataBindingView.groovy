package com.parinherm.view



import org.eclipse.core.databinding.AggregateValidationStatus
import org.eclipse.core.databinding.Binding
import org.eclipse.core.databinding.DataBindingContext
import org.eclipse.core.databinding.UpdateValueStrategy
import org.eclipse.core.databinding.beans.typed.BeanProperties
import org.eclipse.core.databinding.observable.ChangeEvent
import org.eclipse.core.databinding.observable.IChangeListener
import org.eclipse.core.databinding.observable.list.IObservableList
import org.eclipse.core.databinding.observable.list.WritableList
import org.eclipse.core.databinding.observable.map.IObservableMap
import org.eclipse.core.databinding.observable.set.IObservableSet
import org.eclipse.core.databinding.observable.value.IObservableValue
import org.eclipse.core.databinding.observable.value.WritableValue
import org.eclipse.core.databinding.validation.IValidator
import org.eclipse.core.databinding.validation.ValidationStatus
import org.eclipse.core.runtime.IStatus
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport
import org.eclipse.jface.databinding.swt.typed.WidgetProperties
import org.eclipse.jface.databinding.viewers.IViewerObservableValue
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties
import org.eclipse.jface.layout.GridDataFactory
import org.eclipse.jface.layout.TableColumnLayout
import org.eclipse.jface.viewers.ColumnWeightData
import org.eclipse.jface.viewers.ComboViewer
import org.eclipse.jface.viewers.ILabelProvider
import org.eclipse.jface.viewers.ISelectionChangedListener
import org.eclipse.jface.viewers.IStructuredSelection
import org.eclipse.jface.viewers.SelectionChangedEvent
import org.eclipse.jface.viewers.TableViewer
import org.eclipse.jface.viewers.TableViewerColumn
import org.eclipse.swt.SWT
import org.eclipse.swt.custom.SashForm
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Composite

import org.eclipse.swt.widgets.Table
import org.eclipse.swt.widgets.Text

import com.parinherm.domain.DomainTest
import com.parinherm.ui.controls.Label
import groovy.transform.CompileStatic


@CompileStatic
class DataBindingView extends Composite {
	
	/* view controls for editing the current entity */
	Label lblStringTest
	Text txtStringTest
	
	Label lblComboTest
	ComboViewer cboComboTest
	
	/* view controls for selecting current entity */
	TableViewer listView
	Table listTable
	
	/* view controls for showing errors */
	Label lblError
	
	/* databinding members */
	DataBindingContext ctx
	DatabindingViewModel model = new DatabindingViewModel()
	WritableList<DomainTest> input
	WritableValue<DomainTest> value
	ObservableListContentProvider contentProvider = new ObservableListContentProvider()
	
	//toolbar buttons
	Button btnSave
	Button btnDelete
	Boolean selectionChange = false
	
	DomainTest selectedItem = null
	
	
	//handler that listens for databinding change events
	IChangeListener listener = new IChangeListener() {
		@Override
		public void handleChange(ChangeEvent event) {
			/* selectionChange flag is set on the list selection event handler, 
			 * which fires before this databinding handler fires
			 * allows view to ignore list selection changes when setting the dirty flag */
			if(!selectionChange) {
				model.dirty = true
			} else {
				//if we get here then a list change has occurred, reset the flag
				selectionChange = false
				
				//can set the currently selected entity on a list selection change
				IStructuredSelection selection = listView.getStructuredSelection()
				selectedItem = selection.firstElement as DomainTest

			}
		}
	}
	
	
	public DataBindingView(Composite parent) {
		super(parent, SWT.None)
		def mainComposite = new Composite(this, SWT.NONE)
		mainComposite.setLayout(new FillLayout())
		
		def sashForm = new SashForm(mainComposite, SWT.HORIZONTAL)
		
		def listComposite = new Composite(sashForm, SWT.NONE)
		def editComposite = new Composite(sashForm, SWT.NONE)
		
		/* list */
		listView = new TableViewer(listComposite, SWT.NONE)
		listTable = listView.getTable()
		listTable.setHeaderVisible(true)
		listTable.setLinesVisible(true)
		TableViewerColumn nameColumn = new TableViewerColumn(listView, SWT.LEFT)
		nameColumn.getColumn().setText("Name")
		nameColumn.getColumn().setResizable(false)
		nameColumn.getColumn().setMoveable(false)
		TableColumnLayout tableLayout = new TableColumnLayout()
		listComposite.setLayout(tableLayout)
		tableLayout.setColumnData(nameColumn.getColumn(), new ColumnWeightData(100))
		listView.setContentProvider(contentProvider)
		listView.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent arg0) {
				selectionChange = true
			}
		})
		
		lblStringTest = new Label(editComposite, "String Test:")
		//lblStringTest.text = "String Test:"
		txtStringTest = new Text(editComposite, SWT.NONE)
		GridDataFactory.fillDefaults().grab(true, false).applyTo(txtStringTest)
		
		lblComboTest = new Label(editComposite, "Combo Test:")
		
		
		/* error label */
		lblError = new Label(editComposite, "Errors")
		//lblError.setText("Errors")
		GridDataFactory.fillDefaults().span(2, 1).grab(true, false).applyTo(lblError.peer)
		
		Button btnTest = new Button(editComposite, SWT.PUSH)
		btnTest.text = "Update"
		btnTest.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				model.items[0].stringTest = "changed me"
			}
		})
		
		btnSave = new Button(editComposite, SWT.PUSH)
		btnSave.text = "Save"
		btnSave.enabled = false
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				model.dirty = false
			
			}
		})
		
		btnDelete = new Button(editComposite, SWT.PUSH)
		btnDelete.text = "Delete"
		btnDelete.enabled = false
		
		sashForm.setWeights([1, 3] as int[])
		
		
		editComposite.setLayout(new GridLayout(2, false))
		setLayout(new FillLayout())
		addDataBindings()
	}
	
	private def addDataBindings() {
		ctx = new DataBindingContext()
		
		IObservableSet<DomainTest> knownElements = contentProvider.getKnownElements()
		final IObservableMap names = BeanProperties.value(DomainTest.class, "stringTest").observeDetail(knownElements);
		IObservableMap[] labelMaps = [names] as IObservableMap[];
		ILabelProvider labelProvider = new ObservableMapLabelProvider(labelMaps) {
				@Override
				public String getColumnText(Object element, int columnIndex) {
					DomainTest mc = (DomainTest)element;
					return mc.getStringTest();
				}
		}
		listView.setLabelProvider(labelProvider)
		List<DomainTest> el = model.items
		input = new WritableList(el, DomainTest.class)
		listView.setInput(input)
		
		
		//master detail bindings
		//the detail field
		IObservableValue target = WidgetProperties.text(SWT.Modify).observe(txtStringTest)
		//the viewer
		IViewerObservableValue selectedEntity = ViewerProperties.singleSelection().observe(listView)
		//the property in the domain entity
		IObservableValue detailValue = BeanProperties.value("stringTest", String.class).observeDetail(selectedEntity)
	   
		/* just the validators and decorators in the name field */
		IValidator validator = new IValidator() {
			@Override
			public IStatus validate(Object value) {
				String nameValue = String.valueOf(value).replaceAll("\\s", "")
				if (nameValue.length() > 0){
				  return ValidationStatus.ok()
				}
				return ValidationStatus.error("String Test must be entered")
			}
			
		  };
		UpdateValueStrategy updateStrategy = new UpdateValueStrategy()
		updateStrategy.setAfterConvertValidator(validator)
		//Binding nameBinding = ctx.bindValue(nameTargetObservable, nameModelObservable, nameUpdateStrategy, null);
		
		def detailBinding = ctx.bindValue(target, detailValue, updateStrategy, null)
		


		/***********************************************
		 no more bindings to appear above this point
		 other than fields that edit the current entity
		 as it will mess up the dirty binding
		 ************************************************/
 
		
		//dirty binding
		IObservableList bindings = ctx.getValidationStatusProviders()
		bindings.each { element -> 
			def b = element as Binding
			b.target.addChangeListener(listener)
		}
		
		
		//control decorators
		ControlDecorationSupport.create(detailBinding, SWT.TOP | SWT.LEFT)
		
		// error label binding
		final IObservableValue errorObservable = WidgetProperties.text().observe(lblError.peer)
		def allValidationBinding = ctx.bindValue(errorObservable, new AggregateValidationStatus(ctx.getBindings(), AggregateValidationStatus.MAX_SEVERITY), null, null);


		//save button binding
		IObservableValue save = WidgetProperties.enabled().observe(btnSave)
		IObservableValue mdirty= BeanProperties.value("dirty").observe(model)
		def dirtyBinding = ctx.bindValue(save, mdirty)
		
		//this is only needed to set enabled on the action associated with toolbar button
		dirtyBinding.getTarget().addChangeListener(new IChangeListener() {
			@Override
			public void handleChange(ChangeEvent event) {
				//set the enabled of the action associated with toolbar button
			}
		})
		
		//delete button binding
		IObservableValue deleteItemTarget = WidgetProperties.enabled().observe(btnDelete)
		UpdateValueStrategy convertSelectedToBoolean = new UpdateValueStrategy(){
			@Override
			protected IStatus doSet(IObservableValue observableValue, Object value)
			{
				return super.doSet(observableValue, value == null ? Boolean.FALSE : Boolean.TRUE)
			}
		}
		
		
		//a binding that sets delete toolitem to disabled based on whether item in list is selected
		Binding deleteBinding = ctx.bindValue(deleteItemTarget, selectedEntity,  new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), convertSelectedToBoolean)
		//a listener on above binding that makes sure action enabled is set set toolitem changes, ie can't databind the enbabled of an action
		deleteBinding.getTarget().addChangeListener(new IChangeListener() {
			@Override
			public void handleChange(ChangeEvent event) {

				//deleteAction.setEnabled(deleteToolItem.getEnabled())
			}
		})
		

		
	}
}
