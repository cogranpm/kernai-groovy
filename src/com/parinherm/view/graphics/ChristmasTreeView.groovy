package com.parinherm.view.graphics

import org.eclipse.draw2d.Figure
import org.eclipse.draw2d.LightweightSystem
import org.eclipse.draw2d.XYLayout
import org.eclipse.draw2d.geometry.Rectangle
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Canvas
import org.eclipse.swt.widgets.Composite

import com.parinherm.main.AppCache

class ChristmasTreeView {
	
	private AppCache cache = AppCache.getInstance()
	private Figure contents = new Figure() 
	private XYLayout xyLayout = new XYLayout()
	private ChristmasTreeFigure xmas = new ChristmasTreeFigure()
	
	ChristmasTreeView(Composite parent){
		//def scrolled = new ScrolledComposite(parent, SWT.NONE)
		//scrolled.setLayout(new FillLayout())
		def canvas = new Canvas(parent, SWT.NONE)
		def lws = new LightweightSystem(canvas)
		contents.setLayoutManager(xyLayout)
		contents.removeAll()
		contents.add(xmas)
		//put figure as position 20, 20 with it's preferred size
		xyLayout.setConstraint(xmas, new Rectangle(20, 20, -1, -1))
		lws.setContents(contents)
		//scrolled.layout()
	}
}
