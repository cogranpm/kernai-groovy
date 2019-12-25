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

import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.SWT


class QuizView extends Composite{
	def props = [id:0, questionText:'', answerText:'']
	
	QuizView(Composite parent){
		super(parent, SWT.NONE)
	}
}
