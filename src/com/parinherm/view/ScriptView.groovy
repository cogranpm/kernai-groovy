package com.parinherm.view

import java.awt.EventQueue
import java.awt.Frame

import javax.swing.JApplet
import javax.swing.JEditorPane
import javax.swing.JScrollPane
import javax.swing.JTable
import javax.swing.SwingUtilities

import org.eclipse.swt.SWT
import org.eclipse.swt.awt.SWT_AWT
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.widgets.Composite

class ScriptView {
	
	public ScriptView(Composite parent) {
		
		
		Composite composite = new Composite(parent, SWT.EMBEDDED | SWT.NO_BACKGROUND)
		composite.setLayout(new FillLayout())
		
		Frame frame = SWT_AWT.new_Frame(composite)
	
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
			
				JApplet applet = new JApplet()
				applet.setFocusCycleRoot(false)
				frame.add(applet)
				
				JScrollPane scrollPane = new JScrollPane();
				JTable table = new JTable();
				scrollPane.setViewportView(table);
				

			
				JEditorPane ep = new JEditorPane()
				scrollPane.add(ep)
				
				SwingUtilities.updateComponentTreeUI(applet.getRootPane().getContentPane())
				
			}
		})
		

		
	//	ep.setEditorKit(new JavaSyntaxKit())
		//DefaultSyntaxKit.initKit()
		//ep.setContentType("text/java")
		
	
		
//		Console console = new Console()
//		console.setVariable("scriptname", "groovysqlscript")
//		//console?.inputEditor?.getTextEditor()?.setText("blash blah")
//		console.afterExecution = { println "after execute" }
//		console.run()
//		println "the console"
//		console.appendOutput("hello world", null)
	}
}
