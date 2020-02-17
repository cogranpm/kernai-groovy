package com.parinherm.domain

import groovy.beans.Bindable
import groovy.json.JsonSlurper
import groovy.transform.Canonical
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Canonical
@Bindable
class Question extends BaseEntity {
	String question
	String category
	String answer
	
	static Closure<Question> mapFromData = { String jsonData, BigInteger id ->
		def questionMap = new JsonSlurper().parseText(jsonData)
		println "questionmap: $questionMap"
		def updatedOn = questionMap.updatedOn
		println "UpdatedOn: $updatedOn"
		if (updatedOn != null) {
			LocalDateTime updatedOnDate = LocalDateTime.parse(updatedOn, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
			questionMap.updatedOn = updatedOnDate
		}
		
		println "question map: $questionMap"
		println "----------------------------------"
		Question entity = new Question(questionMap)
		entity.id = id
		entity
	}
}
