package com.parinherm.view.graphics

import org.eclipse.draw2d.BorderLayout
import org.eclipse.draw2d.Figure
import org.eclipse.draw2d.FigureCanvas
import org.eclipse.draw2d.FlowLayout
import org.eclipse.draw2d.FreeformLayer
import org.eclipse.draw2d.FreeformLayout
import org.eclipse.draw2d.FreeformViewport
import org.eclipse.draw2d.GridData
import org.eclipse.draw2d.GridLayout
import org.eclipse.draw2d.LightweightSystem
import org.eclipse.draw2d.Polyline
import org.eclipse.draw2d.ScrollPane
import org.eclipse.draw2d.ToolbarLayout
import org.eclipse.draw2d.geometry.Point
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

		final BasicFigure figure = new BasicFigure()
		figure.setLayoutManager(new BorderLayout())


		Polyline polyline = new Polyline();
		float w = 70;
		float k = 50;
		polyline.addPoint(new Point(0 + k, 0 + k));
		polyline.addPoint(new Point(w + k, 0 + k));
		polyline.addPoint(new Point(w + k, w + k));
		polyline.addPoint(new Point(0 + k, w + k));
		polyline.setLineWidth(3);
		//polyline.setBackgroundColor(ColorConstants.red);
		//polyline.setClosed(true);

		Point c = polyline.getBounds().getCenter();
		//polyline.setRotation(c.x(), c.y(), 45);
		//figure.add(polyline, BorderLayout.CENTER);
		lws.setContents(figure)
	
		/*//scrollpane?
		def scrollPane = new ScrollPane()
		scrollPane.setViewport(new FreeformViewport())
		def pane = new FreeformLayer();
		pane.setLayoutManager(new FreeformLayout());
		pane.add(xmas)
		scrollPane.setContents(pane)
		contents.add(scrollPane)
		*/


		/*
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true)
		contents.layoutManager.setConstraint(xmas, gd)
		contents.add(xmas)
		lws.setContents(contents)

		 */
	}
}
