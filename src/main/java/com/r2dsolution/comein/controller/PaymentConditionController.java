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

import com.r2dsolution.comein.dto.PaymentConditionDto;
import com.r2dsolution.comein.service.TourInfoService;

@Controller
public class PaymentConditionController {

	private static Logger log = LoggerFactory.getLogger(PaymentConditionController.class);
	
	private static final String ATTR_USER_TOKEN = "userToken";
	
	@Autowired
	private TourInfoService tourInfoService;
	
	@GetMapping("/payment-conditions")
	public ResponseEntity<PaymentConditionDto> getDefaultPaymentCondition() {
		log.info("getDefaultPaymentCondition.....");
		
//		TourInfoDto res = this.tourInfoService.getTourInfo(id);
		
		PaymentConditionDto res = new PaymentConditionDto();
		res.setPayableTourDate(3);
		res.setPayableDate(0);
		
        return ResponseEntity.ok(res);
	}
	
	@GetMapping("/payment-conditions/{id}")
	public ResponseEntity<PaymentConditionDto> getPaymentCondition(@PathVariable Long id) {
		log.info("getPaymentCondition.....id : {}", id);
		
		PaymentConditionDto res = new PaymentConditionDto();
		res.setPayableTourDate(3);
		res.setPayableDate(0);
		if(id != null) {
			res.setUseDefault(false);
			res.setCompanyId(1L);
		}
		
        return ResponseEntity.ok(res);
	}
	
        
	@PostMapping("/payment-conditions")
	public ResponseEntity<Void> saveDefaultPaymentCondition(@RequestHeader(ATTR_USER_TOKEN) String userToken, @RequestBody PaymentConditionDto req) {
		log.info("saveDefaultPaymentCondition.....");
			
//		this.tourInfoService.saveTourInfo(req, userToken);
		
        return new ResponseEntity<Void>(HttpStatus.OK);
	}	
	
	@PostMapping("/payment-conditions/{id}")
	public ResponseEntity<Void> savePaymentCondition(@RequestHeader(ATTR_USER_TOKEN) String userToken, @PathVariable Long id, @RequestBody PaymentConditionDto req) {
		log.info("savePaymentCondition.....companyId : {}", id);
			
//		this.tourInfoService.saveTourInfo(req, userToken);
		
        return new ResponseEntity<Void>(HttpStatus.OK);
	}	
	
	
}
