package com.parinherm.view.graphics

import static com.parinherm.view.graphics.MarriageFigure.RADIUS

import org.eclipse.draw2d.AbstractConnectionAnchor
import org.eclipse.draw2d.IFigure
import org.eclipse.draw2d.geometry.Dimension
import org.eclipse.draw2d.geometry.Point
import org.eclipse.draw2d.geometry.PrecisionPoint

class MarriageAnchor extends AbstractConnectionAnchor {
	
	MarriageAnchor(IFigure owner){
		super(owner)
	}

	@Override
	public PrecisionPoint getLocation(Point ref) {
		assert getOwner() != null
		def reference = new PrecisionPoint(ref)
		def origin = new PrecisionPoint(getOwner().getBounds().getCenter())
		def radius = new Dimension(RADIUS, RADIUS)
		
		getOwner().translateToAbsolute(origin)
		getOwner().translateToAbsolute(radius)
		
		double Ax = Math.abs(reference.x - origin.preciseX())
		double Ay = Math.abs(reference.y - origin.preciseY())
		
		double divisor = Ax + Ay
		if(divisor == 0.0d) return origin 
		
		double x = (radius.preciseWidth() * Ax) / divisor
		double y = (radius.preciseHeight() * Ay) / divisor
		
		if (reference.preciseX() < origin.preciseX()) x = -x
		if(reference.preciseY() < origin.preciseY()) y = -y
		
		new PrecisionPoint(origin.preciseX() + x, origin.preciseY() + y) 
		
	}
}
