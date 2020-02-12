package com.parinherm.persistence

//todo remove this
import groovy.sql.Sql



interface IDatabase {
	def persist(def model)
	List getAll(String className)
	def close()
}
