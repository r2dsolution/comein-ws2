package com.r2dsolution.comein.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2dsolution.comein.dao.DashboardRepository;
import com.r2dsolution.comein.dto.DashboardReq;


@Service
public class DashboardService {

	private static Logger log = LoggerFactory.getLogger(DashboardService.class);
	
	@Autowired
	private DashboardRepository dashboardRepository;

	
	public List<Map<String, Object>> searchDashboard(String userToken, DashboardReq req){
		log.info("searchDashboard userToken : {}, tourCompanyId : {}, dateFrom : {}, dateTo : {}", userToken, req.getTour_company_id(), req.getDate_from(), req.getDate_to());
		List<Map<String, Object>> response = new ArrayList<>();
		
		try {
			long dashboardId = req.getDashboard_id();
			dashboardId = dashboardId==0?1:dashboardId;
			
			response = dashboardRepository.queryDashboard(dashboardId, req.getTour_company_id(), req.getDate_from(), req.getDate_to());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		

		return response;
	}
	
}
