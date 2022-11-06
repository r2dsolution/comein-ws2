package com.r2dsolution.comein.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2dsolution.comein.dao.DashboardTourBookingRepository;
import com.r2dsolution.comein.dto.DashboardReq;
import com.r2dsolution.comein.entity.DashboardTourBooking;
import com.r2dsolution.comein.util.DateUtils;


@Service
public class TourDashboardService {

	private static Logger log = LoggerFactory.getLogger(TourDashboardService.class);
	
	@Autowired
	private DashboardTourBookingRepository dashboardRepository;

	
	public List<Map<String, Object>> searchDashboard(String userToken, DashboardReq req){
		log.info("searchDashboard userToken : {}, tourCompanyId : {}, dateFrom : {}, dateTo : {}", userToken, req.getTour_company_id(), req.getDate_from(), req.getDate_to());
		List<Map<String, Object>> response = new ArrayList<>();
		
		List<DashboardTourBooking> entities = dashboardRepository.findByIdTourCompanyIdAndIdTourDateGreaterThanEqualAndIdTourDateLessThanEqualOrderByIdTourNameAscIdTourDateAsc(req.getTour_company_id(), req.getDate_from(), req.getDate_to());
		if(!entities.isEmpty()) {
			Map<String, List<DashboardTourBooking>> grpTour = entities.stream().collect(Collectors.groupingBy(e -> e.getId().getTourName(), Collectors.toList()));
			
			Map<String, Object> data = null;
			Map<String, Object> mapDate = null;
			List<Map<String, Object>> dates = null;
			Iterator<String> iterator = grpTour.keySet().iterator();
			while (iterator.hasNext()) {
				data = new LinkedHashMap<>();
		        String key = iterator.next();
		        
		        dates = new LinkedList<>();
		        for(DashboardTourBooking entity : grpTour.get(key)){
		        	mapDate = new HashMap<>();
		        	mapDate.put("tour_date", DateUtils.toStr(entity.getId().getTourDate(), DateUtils.YYYYMMDD));
		        	mapDate.put("visitor", entity.getTotalVisitor());
		        	mapDate.put("booking", entity.getTotalBooking());
		        	mapDate.put("confirm", entity.getTotalConfirm());
		        	mapDate.put("cancel", entity.getTotalCancel());
		        	dates.add(mapDate);
		        }
		        data.put("tour_name", key);
		        data.put("dates", dates);
		        response.add(data);
		    }
		}

		return response;
	}
	
}
