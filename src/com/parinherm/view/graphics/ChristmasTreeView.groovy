package com.parinherm.view.graphics

import org.eclipse.draw2d.BorderLayout
import org.eclipse.draw2d.CompoundBorder
import org.eclipse.draw2d.Figure
import org.eclipse.draw2d.GridData
import org.eclipse.draw2d.GridLayout
import org.eclipse.draw2d.GroupBoxBorder
import org.eclipse.draw2d.LabeledContainer
import org.eclipse.draw2d.LightweightSystem
import org.eclipse.draw2d.ScrollPane
import org.eclipse.draw2d.StackLayout
import org.eclipse.draw2d.geometry.Rectangle
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Canvas
import org.eclipse.swt.widgets.Composite

import com.parinherm.main.AppCache

class ChristmasTreeView {
	
	private AppCache cache = AppCache.getInstance()
	private Figure contents = new Figure() 
	private ChristmasTreeFigure xmas = new ChristmasTreeFigure()
	
	ChristmasTreeView(Composite parent){

		//new Figure
		def canvas = new Canvas(parent, SWT.NONE)

		canvas.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true,
				true))

		def lws = new LightweightSystem(canvas)
		//contents.setLayoutManager(new FlowLayout())
		contents.setLayoutManager(new GridLayout(1, true))
		contents.removeAll()
		
		ScrollPane scrollpane = new ScrollPane()
		scrollpane.setBorder(new GroupBoxBorder("scrollpane"))
		scrollpane.getViewport().setContentsTracksWidth(true)

		final BasicFigure figure = new BasicFigure()
		figure.setPreferredSize(100, 100)
		figure.setLayoutManager(new BorderLayout())

		scrollpane.setContents(figure)
		
		Figure mainPanel = new Figure()
		LabeledContainer container = new LabeledContainer(new CompoundBorder())
		container.setLayoutManager(new StackLayout())
		container.setOpaque(true)
		container.setRequestFocusEnabled(true)
		//container.setLabel("Testing")
		container.add(scrollpane)
		container.setBounds(new Rectangle(1*20, 1*20, 800,800))
		mainPanel.add(container)
		lws.setContents(mainPanel)
		
		//lws.setContents(figure)



		/*
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true)
		contents.layoutManager.setConstraint(xmas, gd)
		contents.add(xmas)
		lws.setContents(contents)

		 */
	}
}
