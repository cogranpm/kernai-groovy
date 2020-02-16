package com.parinherm.domain

import groovy.beans.Bindable
import groovy.transform.Canonical

@Canonical
@Bindable
class Question extends BaseEntity {
	String question
	String category
	String answer
}
