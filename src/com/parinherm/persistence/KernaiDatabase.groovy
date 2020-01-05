/* trial of using berkely db database instead of something like
 * h2 or derby or sqlite
 */

package com.parinherm.persistence



import com.parinherm.main.AppCache
import com.sleepycat.je.Environment
import com.sleepycat.je.EnvironmentConfig
import com.sleepycat.bind.serial.StoredClassCatalog
import com.sleepycat.je.Database.*
import com.sleepycat.je.DatabaseConfig


class KernaiDatabase {

	private Environment env
	private static final String CLASS_CATALOG = 'java_class_catalog'
	private StoredClassCatalog javaCatalog

	KernaiDatabase(String homeDirectory) {
		try {

			DatabaseConfig dbConfig = new DatabaseConfig()
			EnvironmentConfig config = new EnvironmentConfig()
			config.setTransactional(true)
			config.setAllowCreate(true)
			env = new Environment(new File(homeDirectory), config)

			
			dbConfig.setTransactional(true)
			dbConfig.setAllowCreate(true)
			def catalogDb = env.openDatabase(null, CLASS_CATALOG, dbConfig)
			javaCatalog = new StoredClassCatalog(catalogDb)
			
		} catch (Exception e) {
			println e
		}
	}

	void close() {
		try {
			javaCatalog?.close()
			env.close()
		} catch (Exception e) {
			println e
		}
	}

	final Environment getEnvironment() {
		env
	}

	final StoredClassCatalog getClassCatalog() {
		javaCatalog
	}
}
