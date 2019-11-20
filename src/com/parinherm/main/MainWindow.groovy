package com.parinherm.main

import org.eclipse.core.databinding.observable.Realm
import org.eclipse.jface.action.*
import org.eclipse.jface.databinding.swt.DisplayRealm
import org.eclipse.jface.resource.ImageDescriptor
import org.eclipse.jface.resource.ImageRegistry
import org.eclipse.jface.window.ApplicationWindow
import org.eclipse.swt.SWT
import org.eclipse.swt.graphics.Image
import org.eclipse.swt.graphics.Point
import org.eclipse.swt.graphics.Rectangle
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.widgets.*

import com.parinherm.view.DataBindingView
import com.parinherm.view.JSyntaxPaneView
import com.parinherm.view.ScriptView

import groovy.transform.CompileStatic

@CompileStatic
class MainWindow extends ApplicationWindow {
	
	private ImageRegistry imageRegistry
	private final static String IMAGE_ACTVITY_SMALL = "activitysmall"
	private final static String IMAGE_ACTIVITY_LARGE = "activitylarge"
	private final static String IMAGES_PATH = "/images/"
	
	Composite container
	
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
		container = new Composite(parent, SWT.NONE)
		container.setLayout(new FillLayout())
		//def dbTest = new DataBindingTest(container)
		setStatus("what in the hell?")
		getShell().text = "Kernai"
		Image activitySmall = imageRegistry.get(IMAGE_ACTVITY_SMALL)
		Image activityLarge = imageRegistry.get(IMAGE_ACTIVITY_LARGE)
		def images = [activitySmall, activityLarge] as Image[]
		getShell().setImages(images)
		
		DataBindingView view = new DataBindingView(container)
		container
	}
	
	MenuManager createMenuManager() {
		
		try {
			
			def win = this
			
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
			
			IAction actionViewConsole  = new Action("Console") {
				@Override
				public void run() {
					for(Control control : win.container.getChildren())
					{
						control.dispose();
					}
				
					
					JSyntaxPaneView view = new JSyntaxPaneView(container)
					win.container.layout
				}
			}
			
			IAction snippets  = new Action("Snippets") {
				@Override
				public void run() {
					for(Control control : win.container.getChildren())
					{
						control.dispose();
					}
				
					
					ScriptView view = new ScriptView(container)
					win.container.layout
				}
			}
			
			
			
			IAction actionQuit = new Action("&Quit") {
				@Override
				public void run() {
					win.close()
				}
			}
			
			//this is how to do accelerator in groovy 
			char q = 'Q'
			actionQuit.setAccelerator(SWT.MOD1 | q as int)
				
			actionOpenFile.description = "blah"
			actionOpenFile.actionDefinitionId = "crap"
			MenuManager mm = new MenuManager("menu")

			
			MenuManager fileMenu = new MenuManager("&File")
			fileMenu.add(new Separator())
			fileMenu.add(actionOpenFile)
			fileMenu.add(actionQuit)
			mm.add(fileMenu)

			MenuManager viewMenu = new MenuManager("&View")
			viewMenu.add(actionViewConsole)
			viewMenu.add(snippets)
			mm.add(viewMenu)

						
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
	
	
	
	
	
	
	@Override
	public boolean close() {
		// TODO Auto-generated method stub
		return super.close();
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
					display.dispose()
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
