package com.parinherm.view.graphics

import org.eclipse.draw2d.BorderLayout
import org.eclipse.draw2d.ColorConstants
import org.eclipse.draw2d.CompoundBorder
import org.eclipse.draw2d.Figure
import org.eclipse.draw2d.FigureUtilities
import org.eclipse.draw2d.GridData
import org.eclipse.draw2d.GroupBoxBorder
import org.eclipse.draw2d.LabeledContainer
import org.eclipse.draw2d.LightweightSystem
import org.eclipse.draw2d.LineBorder
import org.eclipse.draw2d.SchemeBorder
import org.eclipse.draw2d.ScrollPane
import org.eclipse.draw2d.StackLayout
import org.eclipse.draw2d.TitleBarBorder
import org.eclipse.draw2d.geometry.Rectangle
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Canvas
import org.eclipse.swt.widgets.Composite

import com.parinherm.main.AppCache

class ChristmasTreeView {
	
	private AppCache cache = AppCache.getInstance()
	//private Figure contents = new Figure() 
	//private ChristmasTreeFigure xmas = new ChristmasTreeFigure()
	
	ChristmasTreeView(Composite parent){

		//new Figure
		def canvas = new Canvas(parent, SWT.NONE)
		canvas.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true))


		/*		
		def figureCanvas = new FigureCanvas(parent)
		figureCanvas.getViewport().setContentsTracksHeight(true);
		figureCanvas.getViewport().setContentsTracksWidth(true);
		figureCanvas.setLayoutData(new GridData(GridData.FILL_BOTH))
		*/
		
		def lws = new LightweightSystem(canvas)
		//contents.setLayoutManager(new FlowLayout())
		//contents.setLayoutManager(new GridLayout(1, true))
		//contents.removeAll()
		
		ScrollPane scrollpane = new ScrollPane()
		scrollpane.setBorder(new GroupBoxBorder("scrollpane"))
		scrollpane.getViewport().setContentsTracksWidth(true)
		scrollpane.setScrollBarVisibility(scrollpane.ALWAYS)
		
		
		final BasicFigure figure = new BasicFigure()
		

		scrollpane.setContents(figure)
		
		Figure mainPanel = new Figure()
		CompoundBorder border = new CompoundBorder()
		border.inner =  new CompoundBorder(
			new LineBorder(FigureUtilities.mixColors(
				ColorConstants.buttonDarker, ColorConstants.button), 3),
			new SchemeBorder(SchemeBorder.SCHEMES.LOWERED))
		
		TitleBarBorder titlebar = new TitleBarBorder()
		titlebar.setTextColor(ColorConstants.white)
		titlebar.setBackgroundColor(ColorConstants.darkGray)
		border.outer = new CompoundBorder(
			new SchemeBorder(SchemeBorder.SCHEMES.RAISED),
			titlebar
		)
		
		LabeledContainer container = new LabeledContainer(border)
		container.setLayoutManager(new StackLayout())
		container.setOpaque(true)
		container.setRequestFocusEnabled(true)
		//container.setLabel("Testing")
		container.add(scrollpane)
		container.setBounds(new Rectangle(0, 0, parent.getDisplay().getBounds().width, parent.getDisplay().getBounds().height))
		mainPanel.add(container)
		
		//figureCanvas.setContents(mainPanel)
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
