package com.parinherm.view.graphics


import org.eclipse.draw2d.ColorConstants
import org.eclipse.draw2d.Figure
import org.eclipse.draw2d.FlowLayout
import org.eclipse.draw2d.FreeformLayout
import org.eclipse.draw2d.Graphics
import org.eclipse.draw2d.GridLayout
import org.eclipse.draw2d.Label
import org.eclipse.draw2d.PositionConstants
import org.eclipse.draw2d.geometry.*


class TrunkFigure  extends Figure{
	
	//private static final int FOLDED_CORNER_LENGTH = 12
	//private Label label
	
	TrunkFigure() {
		super()
		
		
		
		//example draws box around a label
//		label = new Label();
//		label.setTextAlignment(PositionConstants.LEFT);
//		label.setText("I'm dyin ova here")
		//add(label);
		//setLayoutManager(new FlowLayout())
		//setLayoutManager(new FreeformLayout());
		//setLayoutManager(new GridLayout())
		//setSize(150, 40)
		repaint()
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		//Rectangle r = getClientArea()
		Rectangle r = getClientArea()
		println r
		
		//draw a line halfway across top to bottom
		Integer trunkWidth = 20
		Integer middle = r.width.intdiv(2)
		Integer trunkLeft = middle - trunkWidth
		Integer trunkRight = trunkLeft + 10
		//Integer trunkRight = middle + trunkWidth
		
		
		
		//g.drawLine(trunkLeft , r.y,  trunkLeft, r.y + r.height)
		//g.drawLine(trunkRight, r.y,  trunkRight, r.y + r.height)

		g.drawRectangle(new Rectangle(
			trunkLeft, 50,
			trunkWidth, r.height - 50
			))
		
		/*
		// draw the rectangle without the top left corner
		g.drawLine(r.x, r.y,
		r.x + r.width - FOLDED_CORNER_LENGTH - 1, r.y); // top
		g.drawLine(r.x, r.y,
		r.x, r.y + r.height - 1); // left
		g.drawLine(r.x, r.y + r.height - 1,
		r.x + r.width - 1, r.y + r.height - 1); // bottom
		g.drawLine(r.x + r.width - 1, r.y + FOLDED_CORNER_LENGTH - 1,
		r.x + r.width - 1, r.y + r.height - 1); // right
		// draw the label
		setConstraint(label, new Rectangle(r.x + 10, r.y + 10,
		r.width - 21, r.height - 21));
		// draw the folded corner
		Point topLeftCorner, bottomLeftCorner, bottomRightCorner;
		PointList trianglePolygon;
		topLeftCorner =
		new Point(r.x + r.width - FOLDED_CORNER_LENGTH - 1, r.y);
		bottomLeftCorner =
		new Point(r.x + r.width - FOLDED_CORNER_LENGTH - 1, r.y
		+ FOLDED_CORNER_LENGTH);
		bottomRightCorner =
		new Point(r.x + r.width - 1, r.y + FOLDED_CORNER_LENGTH);
		trianglePolygon = new PointList(3);
		trianglePolygon.addPoint(topLeftCorner);
		trianglePolygon.addPoint(bottomLeftCorner);
		trianglePolygon.addPoint(bottomRightCorner);
		g.setBackgroundColor(ColorConstants.lightGray);
		g.fillPolygon(trianglePolygon);
		g.drawLine(topLeftCorner, bottomLeftCorner);
		g.drawLine(bottomLeftCorner, bottomRightCorner);
		g.setLineDash([1] as int[]);
		g.drawLine(bottomRightCorner, topLeftCorner);
		*/
		
		
	}
	
		
}
