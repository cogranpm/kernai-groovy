package com.parinherm.view.graphics



import org.eclipse.draw2d.Button
import org.eclipse.draw2d.ColorConstants
import org.eclipse.draw2d.Figure
import org.eclipse.draw2d.Label
import org.eclipse.draw2d.LineBorder
import org.eclipse.draw2d.MouseEvent
import org.eclipse.draw2d.MouseListener
import org.eclipse.draw2d.MouseMotionListener
import org.eclipse.draw2d.ToolbarLayout

import com.parinherm.main.AppCache

class ChristmasTreeFigure extends Figure {
	
	private AppCache cache = AppCache.instance
	private Label label
	
	ChristmasTreeFigure() {
		setLayoutManager(new ToolbarLayout(true))
		setBorder(new LineBorder(ColorConstants.black))
		setBackgroundColor(ColorConstants.yellow)
		setOpaque(true)
		def button = new Button("Button", cache.getImage(cache.IMAGE_ACTIVITY_LARGE))
		button.addActionListener({ e -> println "I was clicked"})
		
		label = new Label("Label", cache.getImage(cache.IMAGE_ACTVITY_SMALL))
		label.addMouseListener(new MouseListener() {
			
	
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseDoubleClicked(MouseEvent arg0) {
				println "double clicked label"
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}
		})
		
		
		label.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				label.setIcon(cache.getImage(cache.IMAGE_GOUP))
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				label.setIcon(cache.getImage(cache.IMAGE_ACTVITY_SMALL))
				
			}

			@Override
			public void mouseHover(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		})
		
		add(button)
		add(label)
	}
}
