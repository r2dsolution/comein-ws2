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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.r2dsolution.comein.dto.DashboardReq;
import com.r2dsolution.comein.service.TourDashboardService;


@RestController
public class TourDashboardController {

	private static Logger log = LoggerFactory.getLogger(TourDashboardController.class);
	
	private static final String ATTR_USER_TOKEN = "userToken";

	@Autowired
	private TourDashboardService tourDashboardService;
	
	@PostMapping("/tour-dashboards")
	public ResponseEntity<List<Map<String, Object>>> searchDashboard(@RequestHeader(ATTR_USER_TOKEN) String userToken,
			@RequestBody DashboardReq req) {
		log.info("Start searchDashboard...dashboardId : {}, tourCompanyId : {}", req.getDashboard_id(), req.getTour_company_id());
		
		List<Map<String, Object>> res = this.tourDashboardService.searchDashboard(userToken, req);
		
        return ResponseEntity.ok(res);
	}
	
	@PostMapping("/tour-dashboards/{tourId}")
	public ResponseEntity<List<Map<String, Object>>> getTourDashboardBookingInfo(@PathVariable Long tourId, @RequestBody DashboardReq req) {
		log.info("getTourDashboardBookingInfo.....tourId : {}", tourId);
		
		List<Map<String, Object>> res = this.tourDashboardService.getTourBooking(tourId, req);
		
        return ResponseEntity.ok(res);
	}
	
	@PostMapping("/tour-dashboards/{companyId}/periods")
	public ResponseEntity<List<Map<String, Object>>> getTourDashboardPaymentPeriod(@PathVariable Long companyId) {
		log.info("getTourDashboardPaymentPeriod.....companyId : {}", companyId);
		
		List<Map<String, Object>> res = this.tourDashboardService.getPaymentPeriods(companyId);
		
        return ResponseEntity.ok(res);
	}
	
	@PostMapping("/tour-pay-dashboards/{periodId}")
	public ResponseEntity<List<Map<String, Object>>> searchPayableDashboard(@RequestHeader(ATTR_USER_TOKEN) String userToken, @PathVariable Long periodId) {
		log.info("Start searchDashboard...periodId : {}", periodId);
		
		List<Map<String, Object>> res = this.tourDashboardService.searchPayableDashboard(userToken, periodId);
		
        return ResponseEntity.ok(res);
	}
	
	@PostMapping("/tour-pay-dashboards/{periodId}/details")
	public ResponseEntity<List<Map<String, Object>>> searchPayableDashboardDetail(@RequestHeader(ATTR_USER_TOKEN) String userToken, @PathVariable Long periodId) {
		log.info("Start searchDashboard...periodId : {}", periodId);
		
		List<Map<String, Object>> res = this.tourDashboardService.searchPayableDashboardDetail(userToken, periodId);
		
        return ResponseEntity.ok(res);
	}
	
}
