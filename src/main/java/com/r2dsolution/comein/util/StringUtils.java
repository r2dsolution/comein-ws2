package com.r2dsolution.comein.util;

import com.r2dsolution.comein.util.ObjectUtils;

public class StringUtils {

	public static boolean isEmpty(Object data) {
		if(data == null || ObjectUtils.isEmpty(data)) 
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
