package com.parinherm.main

import org.eclipse.core.databinding.observable.Realm
import org.eclipse.jface.action.MenuManager
import org.eclipse.jface.action.StatusLineManager
import org.eclipse.jface.action.ToolBarManager
import org.eclipse.jface.databinding.swt.DisplayRealm
import org.eclipse.jface.resource.ImageDescriptor
import org.eclipse.jface.resource.ImageRegistry
import org.eclipse.jface.window.ApplicationWindow
import org.eclipse.swt.SWT
import org.eclipse.swt.graphics.Image
import org.eclipse.swt.graphics.Point
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Shell

import com.parinherm.ui.view.DataBindingTest

class MainWindow extends ApplicationWindow {
	
	private ImageRegistry imageRegistry
	private final static String IMAGE_ACTVITY_SMALL = "activitysmall"
	private final static String IMAGE_ACTIVITY_LARGE = "activitylarge"
	private final static String IMAGES_PATH = "/images/"
	
	MainWindow() {
		super(null)
		setupImages()
		addToolBar(SWT.FLAT | SWT.WRAP)
		addMenuBar()
		addStatusLine()
	}
	
	private def setupImages() {
		imageRegistry = new ImageRegistry()
		this.imageRegistry.put(IMAGE_ACTVITY_SMALL, ImageDescriptor.createFromFile(MainWindow.class, String.format("%s%s", IMAGES_PATH, "Activity_16xSM.png")));
		this.imageRegistry.put(IMAGE_ACTIVITY_LARGE, ImageDescriptor.createFromFile(MainWindow.class, String.format("%s%s", IMAGES_PATH, "Activity_32x.png")));
		
	}
	
	
	/* overrides */
	
	@Override
	protected Control createContents(Composite parent) {
		def container = new Composite(parent, SWT.NONE)
		container.setLayout(new FillLayout())
		def dbTest = new DataBindingTest(container)
		return container
	}
	
	@Override
	protected MenuManager createMenuManager() {
		def mm = new MenuManager("menu")
		return mm
	}
	
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		def tbm = new ToolBarManager(SWT.NONE)
		return tbm
	}
	
	@Override
	protected StatusLineManager createStatusLineManager() {
		return new StatusLineManager()
	}
	
	void configureShell(Shell newShell) {
		newShell.setText("Kernai")
		Image activitySmall = imageRegistry.get(IMAGE_ACTVITY_SMALL)
		Image activityLarge = imageRegistry.get(IMAGE_ACTIVITY_LARGE)
		def images = [activitySmall, activityLarge] as Image[]
		newShell.setImages(images)
	}
	
	protected Point getInitialSize() {
		return new Point(900, 800)
	}
	
	public boolean close() {
		return super.close()
	}
	
	
	static void main(String... args) {
		def display = Display.getDefault()
		Runnable run = {
			try {
				def mainwin = new MainWindow()
				mainwin.setBlockOnOpen(true)
				mainwin.open()
				Display.getCurrent().dispose()
			} catch (e) {
				println e.message
				println e.stackTrace
			}
			
		}
		Realm.runWithDefault(DisplayRealm.getRealm(display), run)
	}	
}
