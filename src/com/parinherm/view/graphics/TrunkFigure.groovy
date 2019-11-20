package com.parinherm.view.graphics

import org.eclipse.draw2d.Figure
import org.eclipse.draw2d.Graphics
import org.eclipse.draw2d.geometry.Point

class TrunkFigure  extends Figure{
	
	@Override
	protected void paintFigure(Graphics graphics) {

		super.paintFigure(graphics)
		
		//to do: some painting of lines here
		graphics.drawText("hello", new Point(0, 0))
	}
}
