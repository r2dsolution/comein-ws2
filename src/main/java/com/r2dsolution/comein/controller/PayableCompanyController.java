package com.r2dsolution.comein.controller;



import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.r2dsolution.comein.dto.PayableCompanyDto;
import com.r2dsolution.comein.service.PayableCompanyService;

@Controller
public class PayableCompanyController {

	private static Logger log = LoggerFactory.getLogger(PayableCompanyController.class);
	
	private static final String ATTR_USER_TOKEN = "userToken";
	
	@Autowired
	private PayableCompanyService payableCompanyService;
	
	@GetMapping("/payable-companies/{companyId}")
	public ResponseEntity<List<PayableCompanyDto>> getPayableCompanyById(@PathVariable Long companyId) {
		log.info("Start getPayableCompanyById.....code : {}", companyId);
		
		List<PayableCompanyDto> res = this.payableCompanyService.getPayableTourByCompanyId(companyId);
		
        return ResponseEntity.ok(res);
	}
	
	@PostMapping("/payable-companies/{companyId}")
	public ResponseEntity<Void> savePayableCompanyById(@PathVariable Long companyId, @RequestBody Map<String, Object> req) {
		log.info("Start savePayableCompanyById.....companyId : {}", companyId);
		
		this.payableCompanyService.savePayableCompanyById(companyId, req);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
