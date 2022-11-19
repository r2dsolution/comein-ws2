package com.r2dsolution.comein.util;

public class ObjectUtils {

	public static boolean isEmpty(Object data) {
		if(data == null || org.springframework.util.ObjectUtils.isEmpty(data)) 
			return true;
		else 
			return false;
	}
	
}
