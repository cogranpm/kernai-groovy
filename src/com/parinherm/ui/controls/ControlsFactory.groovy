package com.parinherm.ui.controls

import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter

import org.eclipse.jface.databinding.viewers.ObservableListContentProvider
import org.eclipse.jface.layout.GridDataFactory
import org.eclipse.jface.layout.TableColumnLayout
import org.eclipse.jface.viewers.TableViewer
import org.eclipse.swt.SWT
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.layout.RowLayout
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.Text

class ControlsFactory {
	
	static Button button(Composite parent, String text, Integer style = SWT.PUSH, Closure handler) {
		def button = new Button(parent, style)
		button.text = text
		button.addSelectionListener(widgetSelectedAdapter(handler))
		button
	}
	
	static Composite toolbar(Composite parent) {
		Composite composite = new Composite(parent, SWT.BORDER)
		composite.setLayout(new RowLayout())
		composite
	}
	
	static Composite listContainer(Composite parent) {
		Composite composite = new Composite(parent, SWT.BORDER)
		composite.setLayout(new FillLayout(SWT.VERTICAL))
		composite
	}
	
	static Composite editContainer(Composite parent) {
		Composite composite = new Composite(parent, SWT.BORDER)
		composite.setLayout(new FillLayout(SWT.VERTICAL))
		composite
	}
	
	static Composite editForm(Composite parent) {
		Composite composite = new Composite(parent, SWT.BORDER)
		composite.setLayout(new GridLayout(2, false))
		composite
	}
	
	
	static TableViewer listView(Composite parent, ObservableListContentProvider contentProvider, Closure selectionHandler, String... columns ) {
		def listView = new TableViewer(parent, SWT.NONE)
		def listTable = listView.getTable()
		listTable.setHeaderVisible(true)
		listTable.setLinesVisible(true)
		TableColumnLayout tableLayout = new TableColumnLayout()
		parent.setLayout(tableLayout)
		columns.each { TableViewerColumnHelper.getColumn(it, listView, tableLayout) }
		listView.setContentProvider(contentProvider)
		listView.addSelectionChangedListener(selectionHandler)
		listView
	}
	
	static Label label(Composite parent, String text) {
		Label label = new Label(parent, SWT.NONE)
		label.text = text
		GridDataFactory.fillDefaults().applyTo(label)
		label
		
	}
	
	static Label errorLabel(Composite parent) {
		Label label = new Label(parent, SWT.NONE)
		GridDataFactory.fillDefaults().span(2, 1).grab(true, false).applyTo(label)
		label
	}
	
	static Text text(Composite parent, Boolean fill = true) {
		Text text = new Text(parent, SWT.NONE)
		GridDataFactory.fillDefaults().grab(fill, false).applyTo(text)
		text
	}

}
