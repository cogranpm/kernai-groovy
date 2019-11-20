package com.parinherm.view.graphics

import org.eclipse.draw2d.Button
import org.eclipse.draw2d.LightweightSystem
import org.eclipse.swt.SWT
import org.eclipse.swt.custom.ScrolledComposite
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.widgets.Canvas
import org.eclipse.swt.widgets.Composite

import com.parinherm.main.AppCache

class ChristmasTreeView {
	
	private AppCache cache = AppCache.getInstance()
	
	ChristmasTreeView(Composite parent){
		//def scrolled = new ScrolledComposite(parent, SWT.NONE)
		//scrolled.setLayout(new FillLayout())
		def canvas = new Canvas(parent, SWT.NONE)
		def lws = new LightweightSystem(canvas)
		def button = new Button("Button", cache.getImage(cache.IMAGE_ACTIVITY_LARGE))
		button.addActionListener({ e -> println "I was clicked"})
		lws.setContents(button)
		//scrolled.layout()
	}
}
