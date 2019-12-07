package com.parinherm.view.graphics

import org.eclipse.draw2d.*
import org.eclipse.draw2d.geometry.PointList
import org.eclipse.draw2d.geometry.Rectangle

class MarriageFigure extends PolygonShape{
	
	private static final PointList ARROWHEAD = new PointList ([0, 0, -2, 2, -2, 0, -2, -2, 0, 0] as int[])
	
	MarriageFigure(int year){
		Rectangle r = new Rectangle(0, 0, 50, 50)
		setStart(r.getTop())
		addPoint(r.getTop())
		addPoint(r.getLeft())
		addPoint(r.getBottom())
		addPoint(r.getRight())
		addPoint(r.getTop())
		setEnd(r.getTop())
		setFill(true)
		setBackgroundColor(ColorConstants.lightGray)
		setPreferredSize(r.getSize().expand(1, 1))
		
		setLayoutManager(new StackLayout())
		add(new Label("$year"))
		
		new FigureMover(this)
	}
	
	PolylineConnection addParent(IFigure figure) {
		def connection = new PolylineConnection()
		connection.setSourceAnchor(new ChopboxAnchor(figure))
		//connection.setTargetAnchor(anchor)
	}
	
	
}

