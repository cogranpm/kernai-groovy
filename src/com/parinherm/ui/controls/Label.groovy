/* class to wrap a swt label and provider sensible constructor and so on */
package com.parinherm.ui.controls

import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Label as SwtLabel

import groovy.transform.Canonical

@Canonical
class Label {
	
	SwtLabel peer
	
	Label (Composite parent, String text) {
		peer = new SwtLabel(parent, SWT.NONE)
		peer.text = text
	}
}
