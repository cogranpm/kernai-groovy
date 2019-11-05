package com.parinherm.view

import com.parinherm.domain.DomainTest

import groovy.beans.Bindable

class DatabindingViewModel {
	
	def items = [new DomainTest("apple", 1), new DomainTest("orange", 2), new DomainTest("lemon", 3)]
	
	@Bindable Boolean dirty = false
}
