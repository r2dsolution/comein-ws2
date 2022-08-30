package com.r2dsolution.comein.controller;



import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.r2dsolution.comein.dto.DashboardReq;
import com.r2dsolution.comein.service.DashboardService;

@Controller
public class DashboardController {
	
	private static final Logger log = LoggerFactory.getLogger(DashboardController.class);

	private static final String ATTR_USER_TOKEN = "userToken";
	
	@Autowired
	private DashboardService dashboardService;
	
	@PostMapping("/dashboards")
	public ResponseEntity<List<Map<String, Object>>> searchDashboard(@RequestHeader(ATTR_USER_TOKEN) String userToken,
			@RequestBody DashboardReq req) {
		log.info("Start searchDashboard...dashboardId : {}, tourCompanyId : {}", req.getDashboard_id(), req.getTour_company_id());
		
		List<Map<String, Object>> res = this.dashboardService.searchDashboard(userToken, req);
		
        return ResponseEntity.ok(res);
	}
		
}
