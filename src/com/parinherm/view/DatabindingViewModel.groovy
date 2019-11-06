package com.parinherm.view

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

import com.parinherm.domain.DataTypesList
import com.parinherm.domain.DomainTest
import com.parinherm.main.AppCache

import groovy.beans.Bindable

//@CompileStatic
class DatabindingViewModel {
	
	List<DomainTest> items = [buildNewDomainTest("apples", 0), 
		buildNewDomainTest("orange", 365), 
		buildNewDomainTest("lemon", 900)]
	
	/* this is best place for the dirty field
	 * as it really applies to the view
	 */
	@Bindable Boolean dirty = false
	
	private def buildNewDomainTest(String stringTest, int dateOffSet) {
		int intTest = AppCache.instance.getRandomInt()
		String comboTest = DataTypesList.items[AppCache.instance.getRandomInt(DataTypesList.items.size())].code
		LocalDate createdDate = LocalDateTime.now().toLocalDate().minusDays(dateOffSet)
		LocalTime createdTime = LocalDateTime.now().minusDays(dateOffSet).toLocalTime()
		LocalDateTime createdDateTime = LocalDateTime.now().minusDays(dateOffSet)
		new DomainTest(stringTest, intTest, comboTest, createdDate, createdTime, createdDateTime)
	}
	

}
