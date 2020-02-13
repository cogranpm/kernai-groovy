package com.parinherm.persistence

//todo remove this
import com.parinherm.domain.IEntity



interface IDatabase {
	def persist(IEntity model)
	List getAll(String className)
	def close()
}
