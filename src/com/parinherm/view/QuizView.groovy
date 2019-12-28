/*
 * implements view for saving a single question
 * going to test out jface databinding to a groovy map
 * 

https://stackoverflow.com/questions/31560084/jface-databinding-map-property-to-swt-text-field
Finally found it... map entries can be observed via

IObservableValue o = Observables.observeMapEntry(map, "test")
 * 
 * 
 */


package com.parinherm.view

import org.eclipse.core.databinding.DataBindingContext
import org.eclipse.core.databinding.observable.Observables
import org.eclipse.core.databinding.observable.map.WritableMap
import org.eclipse.core.databinding.observable.value.IObservableValue
import org.eclipse.core.databinding.observable.value.WritableValue
import org.eclipse.jface.databinding.swt.typed.WidgetProperties
import org.eclipse.jface.layout.GridDataFactory
import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.Text


class QuizView extends Composite{
	//def props = [id:0, questionText:'', answerText:'']
	DataBindingContext dbc = new DataBindingContext()
	Text txtId
	WritableValue value = new WritableValue()
	WritableMap wm = new WritableMap() 
	
	QuizView(Composite parent){
		super(parent, SWT.NONE)
		
		wm.put("id", 0)
		wm.put("questionText", 'hello')
		value.setValue(wm)
		
		Label lblId = new Label(this, SWT.NONE)
		lblId.text = "Question"
		txtId = new Text(this, SWT.NONE)
		
		Button btnDo = new Button(this, SWT.PUSH)
		btnDo.text = "Save"
		btnDo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				def x = value.getValue() as WritableMap
				println x.get('questionText')
			}
		})
		
		IObservableValue target =  WidgetProperties.text(SWT.Modify).observe(txtId)
		IObservableValue model = Observables.observeMapEntry(wm, "questionText")
		dbc.bindValue(target, model)
		
		GridDataFactory.fillDefaults().applyTo(lblId)
		GridDataFactory.fillDefaults().grab(true, false).applyTo(txtId)
		GridDataFactory.fillDefaults().applyTo(btnDo)
		this.setLayout(new GridLayout(2, false))
	}
}
