package com.parinherm.view.graphics

import org.eclipse.draw2d.PolygonShape
import org.eclipse.draw2d.*
import org.eclipse.draw2d.geometry.Rectangle

class MarriageFigure extends PolygonShape{
	
	MarriageFigure(){
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
		
		new FigureMover(this)
	}
	
	
}

