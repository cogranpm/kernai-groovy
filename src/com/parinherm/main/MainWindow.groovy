package com.parinherm.main

import org.eclipse.core.databinding.observable.Realm
import org.eclipse.jface.action.MenuManager
import org.eclipse.jface.action.Separator
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
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;


import org.eclipse.jface.layout.GridDataFactory
import org.eclipse.swt.custom.CTabFolder
import org.eclipse.swt.custom.CTabItem
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.events.SelectionListener
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Event
import org.eclipse.swt.widgets.Listener
import org.eclipse.swt.widgets.ToolBar

import com.parinherm.ui.view.DataBindingTest

class MainWindow extends ApplicationWindow {
	
	private ImageRegistry imageRegistry
	private final static String IMAGE_ACTVITY_SMALL = "activitysmall"
	private final static String IMAGE_ACTIVITY_LARGE = "activitylarge"
	private final static String IMAGES_PATH = "/images/"
	
	private IAction exitAction = new Action("E&xit\tCtrl+X") {
		void run() {
			close()
		}
	}
	
	MainWindow() {
		super(null)
		this.setupImages()
		this.addMenuBar()
		this.addToolBar(SWT.FLAT | SWT.WRAP)
		this.addStatusLine()
	}
	
	private def setupImages() {
		imageRegistry = new ImageRegistry()
		this.imageRegistry.put(IMAGE_ACTVITY_SMALL, ImageDescriptor.createFromFile(MainWindow.class, String.format("%s%s", IMAGES_PATH, "Activity_16xSM.png")));
		this.imageRegistry.put(IMAGE_ACTIVITY_LARGE, ImageDescriptor.createFromFile(MainWindow.class, String.format("%s%s", IMAGES_PATH, "Activity_32x.png")));
		
	}
	
	
	/* overrides */
	
	protected Control createContents(Composite parent) {
		

		
		parent.setLayout(new FillLayout())
		def container = new Composite(parent, SWT.NONE)
		container.setLayout(new FillLayout())
		def dbTest = new DataBindingTest(container)
		setStatus("what in the hell?")
		container
	}
	
	MenuManager createMenuManager() {
		
		try {
			println "create the goddamned"
			MenuManager mm = new MenuManager("menu")

			
			MenuManager fileMenu = new MenuManager("&File")
			fileMenu.add(new Separator())
			fileMenu.add(exitAction)
			mm.add(fileMenu)
			
			MenuManager helpMenu = new MenuManager("&Help")
			//helpMenu.add(ApplicationData.instance().getAction(ApplicationData.ABOUT_ACTION_KEY));
			mm.add(helpMenu);
			
			mm.update(true)
			mm
		}
		catch (e) {
			println e
		}
	}
	
	protected ToolBarManager createToolBarManager(int style) {
		def tbm = new ToolBarManager(SWT.NONE)
		
		ActionContributionItem item = new ActionContributionItem(exitAction)
		item.setMode(ActionContributionItem.MODE_FORCE_TEXT)
		tbm.add(item);
		
		tbm.update(true)
		tbm
	}
	
	protected StatusLineManager createStatusLineManager() {
		def sl = new StatusLineManager()
		sl
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
		//close an resources here
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
