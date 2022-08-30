package com.r2dsolution.comein.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.util.ObjectUtils;

public class DateUtils {

	public static final String YYYYMMDD = "yyyy-MM-dd";
	public static final String YYYYMMDD_HHMM = "yyyy-MM-dd HH:mm";
	
	public static String toStr(LocalDateTime dateTime, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return dateTime.format(formatter);
	}
	
	public static LocalDate toLocalDate(String dateStr, String pattern) {
		if(ObjectUtils.isEmpty(dateStr)) return null;
		if(ObjectUtils.isEmpty(pattern)) pattern = YYYYMMDD;
		return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
	}
	
}
