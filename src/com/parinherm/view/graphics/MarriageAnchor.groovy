package com.parinherm.view.graphics

import org.eclipse.draw2d.AbstractConnectionAnchor
import org.eclipse.draw2d.IFigure
import org.eclipse.draw2d.geometry.Point

import static com.parinherm.view.graphics.MarriageFigure.RADIUS

class MarriageAnchor extends AbstractConnectionAnchor {
	
	MarriageAnchor(IFigure owner){
		super(owner)
	}

	@Override
	public Point getLocation(Point ref) {
		def origin = getOwner()?.getBounds()?.getCenter()
		int Ax = Math.abs(ref.x - origin.x)
		int Ay = Math.abs(ref.y - origin.y)
		
		int divisor = Ax + Ay
		if(divisor == 0) return origin 
		
		int x = (RADIUS * Ax) / divisor
		int y = (RADIUS * Ay) / divisor
		
		if (ref.x < origin.x) x = -x
		if(ref.y < origin.y) y = -y
		
		new Point(origin.x + x, origin.y + y) 
		
	}
}
