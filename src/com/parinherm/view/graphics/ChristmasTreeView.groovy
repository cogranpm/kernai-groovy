package com.parinherm.view.graphics


import org.eclipse.draw2d.ChopboxAnchor
import org.eclipse.draw2d.ColorConstants
import org.eclipse.draw2d.Connection
import org.eclipse.draw2d.ConnectionLayer
import org.eclipse.draw2d.FigureCanvas
import org.eclipse.draw2d.FreeformLayer
import org.eclipse.draw2d.FreeformLayeredPane
import org.eclipse.draw2d.FreeformLayout
import org.eclipse.draw2d.FreeformViewport
import org.eclipse.draw2d.IFigure
import org.eclipse.draw2d.PolylineConnection
import org.eclipse.draw2d.ScalableFreeformLayeredPane
import org.eclipse.draw2d.ShortestPathConnectionRouter
import org.eclipse.draw2d.geometry.PrecisionPoint
import org.eclipse.draw2d.geometry.Rectangle
import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Composite

import com.parinherm.main.AppCache
import com.parinherm.main.MainWindow
import com.parinherm.view.ViewMessage
import groovy.transform.TypeChecked

@TypeChecked
class ChristmasTreeView implements ViewMessage {
	
	private AppCache cache = MainWindow.cache
	private ScalableFreeformLayeredPane root = null
	private FreeformLayer primary = null
	private ConnectionLayer connections = null
	//private Figure contents = new Figure() 
	//private ChristmasTreeFigure xmas = new ChristmasTreeFigure()
	
	ChristmasTreeView(Composite parent){

		//new Figure
		//def canvas = new Canvas(parent, SWT.NONE)
		parent.setLayout(new GridLayout())
		FigureCanvas canvas = createDiagram(parent)
		canvas.setLayoutData(new GridData(GridData.FILL_BOTH))

	}
	
	private FigureCanvas createDiagram(Composite parent) {
		root = new ScalableFreeformLayeredPane()
		root.setFont(parent.getFont())
		
		primary = new FreeformLayer()
		primary.setLayoutManager(new FreeformLayout())
		root.add(primary, "Primary")
		
		connections = new ConnectionLayer()
		connections.setConnectionRouter(new ShortestPathConnectionRouter(primary))
		root.add(connections, "Connections")
		
		def andy = new PersonFigure("Andy", MainWindow.cache.getImage(MainWindow.cache.IMAGE_STOCK_EXIT), 1922, 2002)
		andy.add(new NoteFigure("Andy was a \ngood man"))
		primary.add(andy, new Rectangle(new PrecisionPoint(10.0d, 10.0d), andy.getPreferredSize()))
		def betty = new PersonFigure("Betty", MainWindow.cache.getImage(MainWindow.cache.IMAGE_STOCK_INFO), 1924, 2006)
		betty.add(new NoteFigure("Betty was a \ngood womman"))
		primary.add(betty, new Rectangle(new PrecisionPoint(230.0d, 10.0d), betty.getPreferredSize()))
		def carl = new PersonFigure("Carl",MainWindow.cache.getImage(MainWindow.cache.IMAGE_STOCK_EXIT), 1947, -1)
		carl.add(new NoteFigure("Carl is a \ndoofus man"))
		carl.add(new NoteFigure("He lives in\nBoston, MA."))
		primary.add(carl, new Rectangle(new PrecisionPoint(120.0d, 120.0d), carl.getPreferredSize()))
		
		def marriage = new MarriageFigure(1942)
		primary.add(marriage, new Rectangle(new PrecisionPoint(145.9d, 35.0d), marriage.getPreferredSize()))
		
		connections.add(marriage.addParent(andy))
		connections.add(marriage.addParent(betty))
		connections.add(marriage.addChild(carl))
		
		
		//add a loose note
		def note = new NoteFigure("Smith Family")
		note.setFont(parent.getFont())
		def noteSize = note.getPreferredSize()
		
		primary.add(note, new Rectangle( new PrecisionPoint(10, 220 - noteSize.height), noteSize))
		
		FigureCanvas canvas = new FigureCanvas(parent, SWT.DOUBLE_BUFFERED)
		canvas.setBackground(ColorConstants.white)
		canvas.setViewport(new FreeformViewport())
		//LightweightSystem lws = new LightweightSystem(canvas)
		//lws.setContents(root)
		canvas.setContents(root)
		canvas
	}
	


	
	
	private Connection connect(IFigure figure1, IFigure figure2) {
		def connection = new PolylineConnection()
		connection.setSourceAnchor(new ChopboxAnchor(figure1))
		connection.setTargetAnchor(new ChopboxAnchor(figure2))
		connection
	}

	@Override
	public void messagePosted(String messageId, List args) {
		switch(messageId) {
			case "zoom":
				doZoom (args[0] as double)
				break
			default:
				break
		}
	}
	
	private void doZoom(double scale) {
		if (scale == 0.0) {
			scaleToFit()	
		}
		else {
			root.setScale(scale)
		}
	}
	
	private void scaleToFit() {
		def viewport = root.getParent() as FreeformViewport
		assert viewport != null
		def viewArea = viewport.getClientArea()
		root.setScale(1.0d)
		def extent = root.getFreeformExtent().union(0, 0)
		
		def wScale = (viewArea.width as double) / extent.width
		def hScale = (viewArea.height as double) / extent.height
		def newScale = Math.min(wScale, hScale)
		root.setScale(newScale)
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
