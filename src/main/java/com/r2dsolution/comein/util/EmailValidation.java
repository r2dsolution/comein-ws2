package com.r2dsolution.comein.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailValidation {
	

	private static Logger log = LoggerFactory.getLogger(EmailValidation.class);
	
	//Using OWASP validation regex
	private static final String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

	public static boolean patternMatches(String email) {
		// initialize the Pattern object
		Pattern pattern = Pattern.compile(regex);

		// searching for occurrences of regex
		Matcher matcher = pattern.matcher(email);
		
		boolean isMatch = matcher.matches();

		log.debug("Email : {} is valid : {}", email, isMatch);
		
		return isMatch;
	}
	   
}
