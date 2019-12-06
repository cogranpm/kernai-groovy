package com.parinherm.view.graphics


import org.eclipse.draw2d.ChopboxAnchor
import org.eclipse.draw2d.ColorConstants
import org.eclipse.draw2d.Connection
import org.eclipse.draw2d.Figure
import org.eclipse.draw2d.IFigure
import org.eclipse.draw2d.Label
import org.eclipse.draw2d.LightweightSystem
import org.eclipse.draw2d.PolygonShape
import org.eclipse.draw2d.PolylineConnection
import org.eclipse.draw2d.RectangleFigure
import org.eclipse.draw2d.ToolbarLayout
import org.eclipse.draw2d.XYLayout
import org.eclipse.draw2d.geometry.PrecisionPoint
import org.eclipse.draw2d.geometry.Rectangle
import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Canvas
import org.eclipse.swt.widgets.Composite

import com.parinherm.main.AppCache
import com.parinherm.main.MainWindow

import groovy.transform.TypeChecked

@TypeChecked
class ChristmasTreeView {
	
	private AppCache cache = MainWindow.cache
	//private Figure contents = new Figure() 
	//private ChristmasTreeFigure xmas = new ChristmasTreeFigure()
	
	ChristmasTreeView(Composite parent){

		//new Figure
		//def canvas = new Canvas(parent, SWT.NONE)
		parent.setLayout(new GridLayout())
		Canvas canvas = createDiagram(parent)
		canvas.setLayoutData(new GridData(GridData.FILL_BOTH))

	}
	
	private Canvas createDiagram(Composite parent) {
		Figure root = new Figure()
		root.setFont(parent.getFont())
		XYLayout layout = new XYLayout()
		root.setLayoutManager(layout)
		
		def andy = createPersonFigure("Andy")
		root.add(andy, new Rectangle(new PrecisionPoint(10.0d, 10.0d), andy.getPreferredSize()))
		def betty = createPersonFigure("Betty")
		root.add(betty, new Rectangle(new PrecisionPoint(230.0d, 10.0d), betty.getPreferredSize()))
		def carl = createPersonFigure("Carl")
		root.add(carl, new Rectangle(new PrecisionPoint(120.0d, 120.0d), betty.getPreferredSize()))
		
		def marriage = createMarriageFigure()
		root.add(marriage, new Rectangle(new PrecisionPoint(145.9d, 35.0d), marriage.getPreferredSize()))
		
		root.add(connect(andy, marriage))
		root.add(connect(betty, marriage))
		root.add(connect(carl, marriage))
		
		Canvas canvas = new Canvas(parent, SWT.DOUBLE_BUFFERED)
		canvas.setBackground(ColorConstants.white)
		LightweightSystem lws = new LightweightSystem(canvas)
		lws.setContents(root)
		canvas
	}
	
	private IFigure createPersonFigure(String name) {
		def rectangleFigure = new RectangleFigure()
		rectangleFigure.setBackgroundColor(ColorConstants.lightGray)
		rectangleFigure.setLayoutManager(new ToolbarLayout())
		rectangleFigure.setPreferredSize(100, 100)
		rectangleFigure.add(new Label(name))
		
		//support for moving figures around
		new FigureMover(rectangleFigure)
		
		rectangleFigure
			
	}
	
	private IFigure createMarriageFigure() {
		Rectangle r = new Rectangle(0, 0, 50, 50)
		PolygonShape ps = new PolygonShape()
		ps.setStart(r.getTop())
		ps.addPoint(r.getTop())
		ps.addPoint(r.getLeft())
		ps.addPoint(r.getBottom())
		ps.addPoint(r.getRight())
		ps.addPoint(r.getTop())
		ps.setEnd(r.getTop())
		ps.setFill(true)
		ps.setBackgroundColor(ColorConstants.lightGray)
		ps.setPreferredSize(r.getSize())
		
		new FigureMover(ps)
		ps
	}
	
	
	private Connection connect(IFigure figure1, IFigure figure2) {
		def connection = new PolylineConnection()
		connection.setSourceAnchor(new ChopboxAnchor(figure1))
		connection.setTargetAnchor(new ChopboxAnchor(figure2))
		connection
	}
	
	/* old stuff 
	 * 		canvas.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true))


		def figureCanvas = new FigureCanvas(parent)
		figureCanvas.getViewport().setContentsTracksHeight(true);
		figureCanvas.getViewport().setContentsTracksWidth(true);
		figureCanvas.setLayoutData(new GridData(GridData.FILL_BOTH))
		
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



		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true)
		contents.layoutManager.setConstraint(xmas, gd)
		contents.add(xmas)
		lws.setContents(contents)

		 
	 */
}
