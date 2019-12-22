package com.parinherm.view

import org.eclipse.core.runtime.CoreException
import org.eclipse.jface.text.IDocument
import org.eclipse.ui.editors.text.FileDocumentProvider

class CommonDocumentProvider extends FileDocumentProvider {
	
	CommonDocumentProvider() {
		super()
	}
	
	@Override
	protected IDocument createDocument(Object element) throws CoreException {
		IDocument document = super.createDocument(element)
		if (document != null) {
			//might want to do stuff
		}
		return document
	}
}
