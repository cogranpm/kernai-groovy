package com.parinherm.ui.controls

import org.eclipse.jface.viewers.ViewerComparator
import org.eclipse.swt.SWT

class ListComparator extends ViewerComparator {
	protected final static int DESCENDING = 1
	protected int direction = DESCENDING
	protected int propertyIndex
	
	ListComparator() {
		super()
		this.propertyIndex = 0
		this.direction = DESCENDING
	}
	
	void setColumn(int column) {
		if(column == this.propertyIndex) {
			direction = 1 - direction
		} else {
			this.propertyIndex = column
			direction = DESCENDING
		}
	}
	
	int getDirection() {
		(direction == DESCENDING)  ? SWT.DOWN : SWT.UP
	}
}
