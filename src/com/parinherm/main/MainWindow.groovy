package com.parinherm.main

import org.eclipse.core.databinding.observable.Realm
import org.eclipse.jface.action.MenuManager
import org.eclipse.jface.action.StatusLineManager
import org.eclipse.jface.action.ToolBarManager
import org.eclipse.jface.databinding.swt.DisplayRealm
import org.eclipse.jface.window.ApplicationWindow
import org.eclipse.swt.graphics.Point
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Shell
import org.eclipse.swt.SWT

class MainWindow extends ApplicationWindow {
	
	MainWindow() {
		super(null)
		addToolBar(SWT.FLAT | SWT.WRAP)
		addMenuBar()
		addStatusLine()
	}
	
	
	/* overrides */
	
	@Override
	protected Control createContents(Composite parent) {
		def container = new Composite(parent, SWT.NONE)
		container.setLayout(new FillLayout())
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
				
			}
			
		}
		Realm.runWithDefault(DisplayRealm.getRealm(display), run)
	}	
}
