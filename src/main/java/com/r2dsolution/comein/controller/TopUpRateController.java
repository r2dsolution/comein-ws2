package com.r2dsolution.comein.controller;



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
import org.springframework.web.bind.annotation.RequestHeader;

import com.r2dsolution.comein.dto.TopUpRateDetailDto;
import com.r2dsolution.comein.dto.TopUpRateDto;
import com.r2dsolution.comein.service.TopUpRateService;

@Controller
public class TopUpRateController {

	private static Logger log = LoggerFactory.getLogger(TopUpRateController.class);
	
	private static final String ATTR_USER_TOKEN = "userToken";
	
	@Autowired
	private TopUpRateService topUpRateService;
	
	@GetMapping("/topuprates")
	public ResponseEntity<TopUpRateDetailDto> getDefaultTopUpRate() {
		log.info("getDefaultTopUpRate.....");
		
		TopUpRateDetailDto res = this.topUpRateService.searchDefaultTopUpRate();
		
        return ResponseEntity.ok(res);
	}
	
	@GetMapping("/topuprates/{companyId}")
	public ResponseEntity<TopUpRateDto> getCompanyTopUpRate(@PathVariable Long companyId) {
		log.info("getTopUpRate.....");

		TopUpRateDto res = this.topUpRateService.searchCompanyTopUpRate(companyId);

        return ResponseEntity.ok(res);
	}
        
	@PostMapping("/topuprates")
	public ResponseEntity<Void> saveDefaultTopUpRate(@RequestHeader(ATTR_USER_TOKEN) String userToken, @RequestBody TopUpRateDetailDto req) {
		log.info("saveDefaultTopUpRate.....");
			
		this.topUpRateService.saveDefaultTopUpRate(req, userToken);
		
        return new ResponseEntity<Void>(HttpStatus.OK);
	}	
	
	@PostMapping("/topuprates/{companyId}")
	public ResponseEntity<Void> saveCompanyTopUpRate(@RequestHeader(ATTR_USER_TOKEN) String userToken, @PathVariable Long companyId, @RequestBody TopUpRateDto req) {
		log.info("saveCompanyTopUpRate.....companyId : {}", req.getCompanyId());
			
		this.topUpRateService.saveCompanyTopUpRate(companyId, req, userToken);
		
        return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
