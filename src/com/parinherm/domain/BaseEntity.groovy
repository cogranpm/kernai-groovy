package com.parinherm.domain

import groovy.beans.Bindable

@Bindable
abstract class BaseEntity {
	BigInteger id
	Boolean dirtyFlag
	Boolean newFlag
}
