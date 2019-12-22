/* can probably only use this from an eclipse plugin */

package com.parinherm.view

import org.eclipse.ui.editors.text.TextEditor


class CommonTextEditor extends TextEditor {
	
	CommonTextEditor(){
		super()
		this.setSourceViewerConfiguration(new CommonTextConfiguration())
		this.setDocumentProvider(new CommonDocumentProvider())
		
	}
	
}
