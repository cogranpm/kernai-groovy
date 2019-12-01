package com.parinherm.view.graphics

import org.eclipse.draw2d.Figure
import org.eclipse.draw2d.Graphics
import org.eclipse.draw2d.geometry.Point
import org.eclipse.draw2d.geometry.Rectangle

class BasicFigure  extends Figure{

    @Override
    protected void paintClientArea(Graphics graphics) {
        Rectangle rect = getClientArea()
        graphics.drawText("hello my name is fred and I am here now", new Point(rect.width().intdiv(2), rect.height().intdiv(2)))

        //draw a tree trunk
        Integer trunkWidth = 35
        Integer middlex = rect.width().intdiv(2)
        Integer middley = rect.height().intdiv(2)
        graphics.drawRectangle(middlex - trunkWidth, 0, trunkWidth, rect.height())

        //draw a branch
        Integer currentY = 0
        (0..20).each {
            graphics.drawRectangle(0, currentY, rect.width(), trunkWidth)
            currentY = currentY + (trunkWidth * 4)
        }

    }
}
