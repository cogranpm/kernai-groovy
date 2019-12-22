package com.parinherm.view.graphics


import org.eclipse.draw2d.Button
import org.eclipse.draw2d.ColorConstants
import org.eclipse.draw2d.RectangleFigure
import org.eclipse.draw2d.XYLayout
import org.eclipse.draw2d.geometry.Dimension
import org.eclipse.draw2d.geometry.Point
import org.eclipse.draw2d.geometry.Rectangle


class TrunkFigure  extends RectangleFigure{
	
	XYLayout layout = new XYLayout()
	Button button = new Button("fred")
	
	TrunkFigure() {
		super()
		setBackgroundColor(ColorConstants.blue)
		
		
		RectangleFigure branch = new RectangleFigure()
		branch.setSize(500, 500)
		//add(branch)
		
	
		
		Button buttonNext = new Button("Next")
		//add(buttonNext, new Rectangle(0, 60, 100, 50))
		
		this.setLayoutManager(layout)
		
		layout.setConstraint(button, new Rectangle(0, size.height / 2 as Integer, button.preferredSize.width, button.preferredSize.height))
		add(button)
		
		button.addActionListener({
			setBackgroundColor(ColorConstants.cyan)
			})
	}
	

	def relocate() {
		layout.setConstraint(button, new Rectangle(0, size.height / 2 as Integer, button.preferredSize.width, button.preferredSize.height))
	}

	
		
}
