package com.parinherm.converters

import org.eclipse.core.databinding.conversion.IConverter

import com.parinherm.domain.DataTypesList
import com.parinherm.domain.ListItemDetail

class ListItemConverters {
	//converting from a combo lookup to a field type, say string
	public final static IConverter convertListItemDetail = IConverter.create(ListItemDetail.class, String.class,
		{
			ListItemDetail o -> o?.code
		}
	)
	
	//converting from a field type to a lookup type
	//need to create a finder method to do it
	public final static IConverter convertToListItemDetail = IConverter.create(String.class, ListItemDetail.class,
		{ 
			String o -> DataTypesList.findByCode(o)}
		)
}
