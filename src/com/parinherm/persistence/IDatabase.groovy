package com.parinherm.persistence

//todo remove this
import com.parinherm.domain.BaseEntity
import com.parinherm.domain.IEntity



interface IDatabase {
	def persist(BaseEntity model)
	def delete(BaseEntity model)
	List getAll(String className, Closure mapper)
	def get(BigInteger id, Closure mapper)
	def close()
}
