package com.parinherm.view.graphics

import org.eclipse.draw2d.Figure
import org.eclipse.draw2d.FlowLayout
import org.eclipse.draw2d.FreeformLayer
import org.eclipse.draw2d.FreeformLayout
import org.eclipse.draw2d.FreeformViewport
import org.eclipse.draw2d.LightweightSystem
import org.eclipse.draw2d.ScrollPane
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Canvas
import org.eclipse.swt.widgets.Composite

import com.parinherm.main.AppCache

class ChristmasTreeView {
	
	private AppCache cache = AppCache.getInstance()
	private Figure contents = new Figure() 
	private ChristmasTreeFigure xmas = new ChristmasTreeFigure()
	
	ChristmasTreeView(Composite parent){

		def canvas = new Canvas(parent, SWT.NONE)
		def lws = new LightweightSystem(canvas)
		contents.setLayoutManager(new FlowLayout())
		contents.removeAll()
	
		/*//scrollpane?
		def scrollPane = new ScrollPane()
		scrollPane.setViewport(new FreeformViewport())
		def pane = new FreeformLayer();
		pane.setLayoutManager(new FreeformLayout());
		pane.add(xmas)
		scrollPane.setContents(pane)
		contents.add(scrollPane)
		*/
		
		contents.add(xmas)
		lws.setContents(contents)
	}
}
