package com.r2dsolution.comein.util;

import java.math.BigDecimal;

public class NumberUtils {

	public static BigDecimal toBigDecimal(BigDecimal data) {
		if(data == null) 
			return BigDecimal.ZERO;
		else 
			return data;
	}
	
}
