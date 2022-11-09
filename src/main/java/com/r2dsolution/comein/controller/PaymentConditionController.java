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
import com.r2dsolution.comein.service.PaymentConditionService;

@Controller
public class PaymentConditionController {

	private static Logger log = LoggerFactory.getLogger(PaymentConditionController.class);
	
	private static final String ATTR_USER_TOKEN = "userToken";
	
	@Autowired
	private PaymentConditionService paymentConditionService;
	
	@GetMapping("/payment-conditions")
	public ResponseEntity<PaymentConditionDto> getDefaultPaymentCondition() {
		log.info("getDefaultPaymentCondition.....");
		
		PaymentConditionDto res = this.paymentConditionService.searchDefaultPaymentCondition();
		
        return ResponseEntity.ok(res);
	}
	
	@GetMapping("/payment-conditions/{companyId}")
	public ResponseEntity<PaymentConditionDto> getCompanyPaymentCondition(@PathVariable Long companyId) {
		log.info("getPaymentCondition.....");

		PaymentConditionDto res = this.paymentConditionService.searchCompanyPaymentCondition(companyId);

        return ResponseEntity.ok(res);
	}
        
	@PostMapping("/payment-conditions")
	public ResponseEntity<Void> saveDefaultPaymentCondition(@RequestHeader(ATTR_USER_TOKEN) String userToken, @RequestBody PaymentConditionDto req) {
		log.info("saveDefaultPaymentCondition.....");
			
		this.paymentConditionService.saveDefaultPaymentCondition(req, userToken);
		
        return new ResponseEntity<Void>(HttpStatus.OK);
	}	
	
	@PostMapping("/payment-conditions/{companyId}")
	public ResponseEntity<Void> saveCompanyPaymentCondition(@RequestHeader(ATTR_USER_TOKEN) String userToken, @PathVariable Long companyId, @RequestBody PaymentConditionDto req) {
		log.info("saveCompanyPaymentCondition.....companyId : {}", req.getCompanyId());
			
		this.paymentConditionService.saveCompanyPaymentCondition(companyId, req, userToken);
		
        return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
}
