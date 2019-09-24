package com.revature.gamesgalore.util;

public class DetailsUtil {

	private DetailsUtil() {}
	
	public static String toFieldName(String columnName) {
		String [] nameArray = columnName.split("_");
		for(int i = 1; i< nameArray.length; i++) {
			nameArray[i] = nameArray[i].substring(0,1).toUpperCase() + nameArray[i].substring(1);
		}
		return String.join("", nameArray);
	}
}
