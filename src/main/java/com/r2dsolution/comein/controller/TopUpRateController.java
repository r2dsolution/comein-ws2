package com.r2dsolution.comein.controller;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

import com.r2dsolution.comein.dto.TopUpRateDetailDto;
import com.r2dsolution.comein.dto.TopUpRateDto;
import com.r2dsolution.comein.service.TourInfoService;

@Controller
public class TopUpRateController {

	private static Logger log = LoggerFactory.getLogger(TopUpRateController.class);
	
	private static final String ATTR_USER_TOKEN = "userToken";
	
	@Autowired
	private TourInfoService tourInfoService;
	
	@GetMapping("/topuprates")
	public ResponseEntity<TopUpRateDto> getTopUpRate(@RequestParam(required = false) Long companyId) {
		log.info("getTopUpRate.....");
		
//		TourInfoDto res = this.tourInfoService.getTourInfo(id);
		
		List<TopUpRateDetailDto> detail = new ArrayList<>();
		TopUpRateDetailDto dtl = new TopUpRateDetailDto();
		dtl.setMin(BigDecimal.ZERO);
		dtl.setMax(BigDecimal.valueOf(1000));
		dtl.setTopUpRate(BigDecimal.valueOf(10));
		dtl.setComeinRate(BigDecimal.valueOf(6));
		dtl.setHotelRate(BigDecimal.valueOf(4));
		detail.add(dtl);
		dtl = new TopUpRateDetailDto();
		dtl.setMin(BigDecimal.valueOf(1000));
		dtl.setMax(BigDecimal.valueOf(5000));
		dtl.setTopUpRate(BigDecimal.valueOf(50));
		dtl.setComeinRate(BigDecimal.valueOf(26));
		dtl.setHotelRate(BigDecimal.valueOf(24));
		detail.add(dtl);
		dtl = new TopUpRateDetailDto();
		dtl.setMin(BigDecimal.valueOf(5000));
		dtl.setMax(BigDecimal.valueOf(100000));
		dtl.setTopUpRate(BigDecimal.valueOf(2000));
		dtl.setComeinRate(BigDecimal.valueOf(1200));
		dtl.setHotelRate(BigDecimal.valueOf(800));
		detail.add(dtl);
		
		TopUpRateDto res = new TopUpRateDto();
		res.setDetail(detail);
		if(companyId != null) {
			res.setUseDefault(null);
			res.setCompanyId(1L);
		}
		
        return ResponseEntity.ok(res);
	}
	
        
	@PostMapping("/topuprates")
	public ResponseEntity<Void> saveTopUpRate(@RequestHeader(ATTR_USER_TOKEN) String userToken, @RequestBody TopUpRateDto req) {
		log.info("saveTopUpRate.....companyId : {}", req.getCompanyId());
			
//		this.tourInfoService.saveTourInfo(req, userToken);
		
        return new ResponseEntity<Void>(HttpStatus.OK);
	}	
	
	
}
