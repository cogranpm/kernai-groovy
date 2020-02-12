package com.parinherm.persistence

import java.util.logging.*

import groovy.json.JsonGenerator
import groovy.sql.Sql

class H2Database implements IDatabase {
	
	Logger logger = Logger.getLogger('groovy.sql')
	Sql db
	JsonGenerator jsonOutputter = new JsonGenerator.Options().excludeFieldsByName('propertyChangeListeners').build()

	H2Database(String dir){
		logger.level = Level.FINE
		def url = "jdbc:h2:$dir"
		def user = 'sa'
		def password = ''
		def driver = 'org.h2.Driver'
		db = Sql.newInstance(url, user, password, driver)
		createTables()
	}

	def close() {
		db.close()
	}	
	
	private def createTables() {
		def tableddl = """\
					CREATE TABLE IF NOT EXISTS ENTITYDATA(ID IDENTITY NOT NULL PRIMARY KEY,
					ENTITYCLASS VARCHAR NOT NULL,
					DATA JSON NOT NULL);""".stripIndent()
		db.execute(tableddl)
	}
	
	def persist(def model) {
		def clazz = model.getClass().getName()
		def json = jsonOutputter.toJson(model)
	
		def insert = """\
			INSERT INTO ENTITYDATA (ENTITYCLASS, DATA) VALUES (?, ? FORMAT JSON);
		""".stripIndent()
		db.execute insert, [clazz, json]

	}


	List getAll(String className) {
		def list = []
		
		Closure rowParser = {row ->
			def id = row[0]
			def clazzName = row[1]
			def jsonRaw = new String(row[2])
			def map = [json: jsonRaw, id: id]
			list << map
		}
	
		db.eachRow('SELECT ID, ENTITYCLASS, DATA FORMAT JSON FROM ENTITYDATA WHERE ENTITYCLASS = :classname', [classname: className], rowParser)
		list
	}


}
