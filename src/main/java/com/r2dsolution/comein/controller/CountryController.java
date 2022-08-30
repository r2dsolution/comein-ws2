package com.r2dsolution.comein.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.r2dsolution.comein.dto.CountryDto;
import com.r2dsolution.comein.service.CountryService;

@Controller
public class CountryController {
	private static Logger log = LoggerFactory.getLogger(CountryController.class);
	
	@Autowired
	private CountryService countryService;
	
	@GetMapping("/countries")
	public ResponseEntity<List<CountryDto>> getCountry() {
		log.info("Start.....getCountry.");
		
		List<CountryDto> res = this.countryService.getAllCountry();
		
        return ResponseEntity.ok(res);
	}
	
}
