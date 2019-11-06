package com.parinherm.view

import com.parinherm.domain.DomainTest

import groovy.beans.Bindable

class DatabindingViewModel {
	
	List<DomainTest> items = [new DomainTest("apple", 1, "string"), new DomainTest("orange", 2, "int"), new DomainTest("lemon", 3, "string")]
	
	/* this is best place for the dirty field
	 * as it really applies to the view
	 */
	@Bindable Boolean dirty = false
}
