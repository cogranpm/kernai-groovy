package com.parinherm.main

import org.eclipse.core.databinding.observable.Realm
import org.eclipse.jface.action.*
import org.eclipse.jface.databinding.swt.DisplayRealm
import org.eclipse.jface.resource.ImageDescriptor
import org.eclipse.jface.resource.ImageRegistry
import org.eclipse.jface.window.ApplicationWindow
import org.eclipse.swt.SWT
import org.eclipse.swt.graphics.Device
import org.eclipse.swt.graphics.Image
import org.eclipse.swt.graphics.Point
import org.eclipse.swt.graphics.Rectangle
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.widgets.*

import groovy.transform.CompileStatic

@CompileStatic
class MainWindow extends ApplicationWindow {
	
	private ImageRegistry imageRegistry
	private final static String IMAGE_ACTVITY_SMALL = "activitysmall"
	private final static String IMAGE_ACTIVITY_LARGE = "activitylarge"
	private final static String IMAGES_PATH = "/images/"
	
	
	MainWindow(Shell parentShell) {
		super(parentShell)
		try {
		
			this.setupImages()
			this.addMenuBar()
			this.addToolBar(SWT.FLAT | SWT.WRAP)
			this.addStatusLine()
			
		}catch(Exception e) {
			println e.message
			throw e
		}
	}
	
	private def setupImages() {
		imageRegistry = new ImageRegistry()
		this.imageRegistry.put(IMAGE_ACTVITY_SMALL, ImageDescriptor.createFromFile(MainWindow.class, String.format("%s%s", IMAGES_PATH, "Activity_16xSM.png")));
		this.imageRegistry.put(IMAGE_ACTIVITY_LARGE, ImageDescriptor.createFromFile(MainWindow.class, String.format("%s%s", IMAGES_PATH, "Activity_32x.png")));
		
	}
	
	
	/* overrides */
	
	protected Control createContents(Composite parent) {
//		parent.setLayout(new FillLayout())
		def container = new Composite(parent, SWT.NONE)
		container.setLayout(new FillLayout())
		//def dbTest = new DataBindingTest(container)
		setStatus("what in the hell?")
		getShell().text = "Kernai"
		Image activitySmall = imageRegistry.get(IMAGE_ACTVITY_SMALL)
		Image activityLarge = imageRegistry.get(IMAGE_ACTIVITY_LARGE)
		def images = [activitySmall, activityLarge] as Image[]
		getShell().setImages(images)
		container
	}
	
	MenuManager createMenuManager() {
		
		try {
			println "create the goddamned"
			
			
			IAction actionOpenFile = new Action("Open") {
				@Override
				public void run() {
					FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
					final String file = dialog.open();
					if(file != null) {
						try {
							
							setStatus("File loaded successfully: ");
						} catch (IOException e) {
							e.printStackTrace();
							setStatus("Failed to load file: ");
						}
					}
				}
			}
				
			actionOpenFile.description = "blah"
			actionOpenFile.actionDefinitionId = "crap"
			MenuManager mm = new MenuManager("menu")

			
			MenuManager fileMenu = new MenuManager("&File")
			fileMenu.add(new Separator())
			fileMenu.add(actionOpenFile)
			mm.add(fileMenu)
			
			MenuManager helpMenu = new MenuManager("&Help")
			//helpMenu.add(ApplicationData.instance().getAction(ApplicationData.ABOUT_ACTION_KEY));
			//mm.add(helpMenu);
			
			mm
		}
		catch (Exception e) {
			println e
			throw e
		}
	}
	
	protected ToolBarManager createToolBarManager(int style) {
		def tbm = new ToolBarManager(SWT.NONE)
		
//		ActionContributionItem item = new ActionContributionItem(exitAction)
//		item.setMode(ActionContributionItem.MODE_FORCE_TEXT)
//		tbm.add(item);
//		
		tbm.update(true)
		tbm
	}
	
	protected StatusLineManager createStatusLineManager() {
		def sl = new StatusLineManager()
		sl
	}
	
	
	/* there is some kind of bug here
	 * if configure shell is called then the menus do not appear
	 * need to do this stuff in the createContents instead
	void configureShell(Shell newShell) {
		newShell.setText("Kernai")
		Image activitySmall = imageRegistry.get(IMAGE_ACTVITY_SMALL)
		Image activityLarge = imageRegistry.get(IMAGE_ACTIVITY_LARGE)
		def images = [activitySmall, activityLarge] as Image[]
		newShell.setImages(images)
	}
	*/
	
	protected Point getInitialSize() {
		def display = Display.getDefault()
		Rectangle rect = display.clientArea
		return new Point((rect.width / 2) as int, (rect.height / 2) as int)
	}
	
	public boolean close() {
		//close an resources here
		return super.close()
	}
	
	
	static void main(String... args) {
		
		try
		{
			def display = Display.getDefault()
			Runnable run = {
				try {
					def mainwin = new MainWindow(null)
					mainwin.setBlockOnOpen(true)
					mainwin.open()
					//Display.getCurrent().dispose()
				} catch (Exception e) {
					println e.message
					println e.stackTrace
				}
				
			}
			Realm.runWithDefault(DisplayRealm.getRealm(display), run)
		} catch (Exception e) {
			println e.message
		}
	}	
}
