package com.parinherm.view.graphics


import org.eclipse.draw2d.ColorConstants
import org.eclipse.draw2d.CompoundBorder
import org.eclipse.draw2d.Figure
import org.eclipse.draw2d.Graphics
import org.eclipse.draw2d.Label
import org.eclipse.draw2d.LineBorder
import org.eclipse.draw2d.MarginBorder
import org.eclipse.draw2d.ToolbarLayout
import org.eclipse.draw2d.geometry.Insets
import org.eclipse.swt.graphics.Pattern
import org.eclipse.swt.widgets.Display

import groovy.transform.TypeChecked

@TypeChecked
class PersonFigure extends Figure {
	PersonFigure(String name, int birthYear, int deathYear, String note){
		
		setLayoutManager(new ToolbarLayout())
		setPreferredSize(100, 100)
		setBorder(new CompoundBorder(new LineBorder(1), new MarginBorder(2,2,2,2)))
		add(new Label(name))
		
		String datesText = "$birthYear - ${deathYear != -1 ? deathYear : ''}"
		//if (deathYear != -1)
			//datesText += " $deathYear"
		add(new Label(datesText))
		add(new NoteFigure(note))
		new FigureMover(this)
	}
	
	@Override
	public void paintFigure(Graphics graphics) {
		def r = getBounds()
		graphics.setBackgroundPattern(new Pattern(Display.getCurrent(), r.x, r.y, r.x + r.width, r.y + r.height, ColorConstants.white, ColorConstants.lightGray))
		graphics.fillRectangle(r)
	}
}
