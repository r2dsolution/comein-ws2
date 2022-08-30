package com.r2dsolution.comein.util;

public class StringUtils {

	public static boolean isEmpty(String data) {
		if(data == null || data.isEmpty()) 
			return true;
		else 
			return false;
	}
	
	public static String trimToEmpty(String data) {
		if(data == null) return "";
		else return data.trim();
	}
	
	public static <T> T getValueOrDefault(T value, T defaultValue) {
	    return value == null ? defaultValue : value;
	}
	
}
