package com.r2dsolution.comein.controller;



import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.r2dsolution.comein.dto.DashboardReq;
import com.r2dsolution.comein.service.ComeinDashboardService;


@RestController
public class ComeinDashboardController {

	private static Logger log = LoggerFactory.getLogger(ComeinDashboardController.class);
	
	@Autowired
	private ComeinDashboardService comeinDashboardService;
	
	
	@PostMapping("/comein-dashboards/{companyId}")
	public ResponseEntity<List<Map<String, Object>>> getComeinDashboardBookingInfo(@PathVariable Long companyId, @RequestBody DashboardReq req) {
		log.info("getComeinDashboardBookingInfo.....companyId : {}", companyId);
		
		List<Map<String, Object>> res = this.comeinDashboardService.getTourBooking(companyId, req);
		
        return ResponseEntity.ok(res);
	}
	
}
