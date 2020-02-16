package com.parinherm.domain

class QuizCategoriesList {
	
	static def itemGeneral = new ListItemDetail(code: "general", description: "General")
	static def itemGroovy = new ListItemDetail(code: "groovy", description: "Groovy")
		
	public static List<ListItemDetail> items = [itemGeneral, itemGroovy]
	
	public static ListItemDetail findByCode(String code) {
		items.find { x -> x.code == code }
	}
}
