package com.parinherm.view



import org.eclipse.core.databinding.DataBindingContext
import org.eclipse.core.databinding.beans.BeanProperties
import org.eclipse.core.databinding.observable.list.WritableList
import org.eclipse.core.databinding.observable.map.IObservableMap
import org.eclipse.core.databinding.observable.set.IObservableSet
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider
import org.eclipse.jface.layout.GridDataFactory
import org.eclipse.jface.layout.TableColumnLayout
import org.eclipse.jface.viewers.ColumnWeightData
import org.eclipse.jface.viewers.ILabelProvider
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
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.Table
import org.eclipse.swt.widgets.Text

import com.parinherm.domain.DomainTest

class DataBindingView extends Composite {
	
	Label lblStringTest
	Text txtStringTest
	TableViewer listView
	Table listTable
	DataBindingContext ctx
	DatabindingViewModel model = new DatabindingViewModel()
	WritableList<DomainTest> input
	ObservableListContentProvider contentProvider = new ObservableListContentProvider()
	
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
		
		lblStringTest = new Label(editComposite, SWT.NONE)
		lblStringTest.text = "String Test:"
		txtStringTest = new Text(editComposite, SWT.NONE)
		GridDataFactory.fillDefaults().grab(true, false).applyTo(txtStringTest)
		Button btnTest = new Button(editComposite, SWT.PUSH)
		btnTest.text = "Update"
		btnTest.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				model.items[0].stringTest = "changed me"
			}
		})
		
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
		
		
	}
}
