package com.r2dsolution.comein.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.r2dsolution.comein.dto.ProvinceDto;
import com.r2dsolution.comein.service.ProvinceService;

@Controller
public class ProvinceController {
	private static Logger log = LoggerFactory.getLogger(ProvinceController.class);
	
	@Autowired
	private ProvinceService provinceService;
	
	@GetMapping("/provinces/{country}")
	public ResponseEntity<List<ProvinceDto>> getProvince(@PathVariable String country) {
		log.info("Start.....getProvince with country : {}", country);
		
		List<ProvinceDto> res = this.provinceService.getAllProvince(country);
		
        return ResponseEntity.ok(res);
	}
	
}
