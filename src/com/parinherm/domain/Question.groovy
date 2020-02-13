package com.parinherm.domain

import groovy.beans.Bindable
import groovy.transform.Canonical

@Canonical
@Bindable
class Question implements IEntity {
	BigInteger id
	String question
	String category
	String answer
}
