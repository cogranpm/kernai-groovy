package com.parinherm.ui.view

import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Label

class DataBindingTest extends Composite {
	
	DataBindingTest(parent){
		super(parent, SWT.NONE)
		setLayout(new GridLayout(2))
		def lblString = new Label(this, SWT.NONE)
		lblString.setText("String")
	}
	
	
}
