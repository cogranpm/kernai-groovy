package com.parinherm.domain

import groovy.beans.Bindable
import groovy.transform.Canonical

@Canonical
@Bindable
class Question extends BaseEntity {
	//BigInteger id
	String question
	String category
	String answer
	//Boolean dirtyFlag
	//Boolean newFlag
	

}
