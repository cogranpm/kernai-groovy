package com.parinherm.ui.view

import org.eclipse.jface.layout.GridDataFactory
import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.Text

class DataBindingTest extends Composite {
	
	DataBindingTest(parent){
		super(parent, SWT.NONE)
		setLayout(new GridLayout(2, false))
		def lblString = new Label(this, SWT.NONE)
		lblString.setText("String")
		
		def txtString = new Text(this, SWT.NONE)
		GridDataFactory.fillDefaults().grab(true, false).applyTo(txtString)
	}
	
	
}
