package com.r2dsolution.comein.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.r2dsolution.comein.util.DateUtils;


@ControllerAdvice
@RestController
public class CustomResponseExceptionHandler extends ResponseEntityExceptionHandler {

	private static Logger log = LoggerFactory.getLogger(CustomResponseExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Map<String, String>> handleAllExceptions(Exception ex, WebRequest request){
		
		log.info("handleAllExceptions token : {}, msg : {}", "token", ex.getMessage(), ex);
		
		Map<String, String> errors = new HashMap<>();
		
//		ExceptionResponse res = new ExceptionResponse(null, ex.getMessage(), LocalDateTime.now());
//		errors.put("cause", res);
		errors.put("token", null);
		errors.put("message", ex.getMessage());
		errors.put("time", DateUtils.toStr(LocalDateTime.now(), DateUtils.YYYYMMDD_HHMM));
		
		
		return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ServiceException.class)
	public final ResponseEntity<Map<String, String>> handleServiceExceptions(ServiceException ex, WebRequest request){
		
		log.info("handleServiceExceptions token : {}, msg : {}", "token", ex.getMessage());
		
		Map<String, String> errors = new HashMap<>();
		
//		ExceptionResponse res = new ExceptionResponse(null, ex.getMessage(), LocalDateTime.now());
//		errors.put("cause", res);
		
		errors.put("token", null);
		errors.put("message", ex.getMessage());
		errors.put("time", DateUtils.toStr(LocalDateTime.now(), DateUtils.YYYYMMDD_HHMM));
		
		return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
