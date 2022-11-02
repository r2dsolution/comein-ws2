package com.r2dsolution.comein.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.r2dsolution.comein.dto.PaymentConditionDto;
import com.r2dsolution.comein.service.TourInfoService;

@Controller
public class PaymentConditionController {

	private static Logger log = LoggerFactory.getLogger(PaymentConditionController.class);
	
	private static final String ATTR_USER_TOKEN = "userToken";
	
	@Autowired
	private TourInfoService tourInfoService;
	
	@GetMapping("/payment-conditions")
	public ResponseEntity<PaymentConditionDto> getDefaultPaymentCondition(@RequestParam(required = false) Long companyId) {
		log.info("getDefaultPaymentCondition.....");
		
//		TourInfoDto res = this.tourInfoService.getTourInfo(id);
		
		PaymentConditionDto res = new PaymentConditionDto();
		res.setPayableTourDate(3);
		res.setPayableDate(0);
		if(companyId != null) {
			res.setUseDefault(null);
			res.setCompanyId(1L);
		}
		
        return ResponseEntity.ok(res);
	}
	
        
	@PostMapping("/payment-conditions")
	public ResponseEntity<Void> savePaymentCondition(@RequestHeader(ATTR_USER_TOKEN) String userToken, @RequestBody PaymentConditionDto req) {
		log.info("savePaymentCondition.....companyId : {}", req.getCompanyId());
			
//		this.tourInfoService.saveTourInfo(req, userToken);
		
        return new ResponseEntity<Void>(HttpStatus.OK);
	}	
	
	
}
