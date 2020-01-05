/*
 * implements view for saving a single question
 * going to test out jface databinding to a groovy map
 * 
 * current best way to do this is store json column in database which can 
 * convert to a groovy map easily, which can be converted easily to 
 * jface writableMap and observed easily
 * no domain class required, no mapping, always the same sql query
 * catch is you can't query fields at database level
 *  
 * 
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

import com.parinherm.main.AppCache
import com.parinherm.persistence.KernaiDatabase
import groovy.json.JsonSlurper
import static groovy.json.JsonOutput.*


class QuizView extends Composite{
	//def props = [id:0, questionText:'', answerText:'']
	DataBindingContext dbc = new DataBindingContext()
	Text txtId
	WritableValue value = new WritableValue()
	WritableMap wm = new WritableMap()
	
	QuizView(Composite parent){
		super(parent, SWT.NONE)
		
		//lets create a map from a json string
		String jsonTest = """ {"id": 0, "questionText": "what is the color of postassium"}    """
		//jsonStuff is a map structure
		def jsonStuff = new JsonSlurper().parseText(jsonTest)
		//jsonStuff.each { key, value -> wm.put(key, value)}
		wm.putAll(jsonStuff)

		value.setValue(wm)
		
		Label lblId = new Label(this, SWT.NONE)
		lblId.text = "Question"
		txtId = new Text(this, SWT.NONE)
		
		Button btnDo = new Button(this, SWT.PUSH)
		btnDo.text = "Save"
		btnDo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// save the map to jjson in a database column
				println toJson(wm)
			//	println x.
				//println x.get('questionText')
				
				//try some db stuff
				
				
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
