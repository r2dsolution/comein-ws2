package com.r2dsolution.comein.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
			Map<LocalDate, List<DashboardTourBooking>> grpDate = entities.stream().collect(Collectors.groupingBy(e -> e.getId().getTourDate(), Collectors.toList()));
			

			
			Map<String, Object> data = null;
			Map<String, Object> mapDate = null;

			Iterator<String> iterator = grpTour.keySet().iterator();
			while (iterator.hasNext()) {
				data = new LinkedHashMap<>();
		        String key = iterator.next();
		        
				Map<LocalDate, Map<String, Object>> defaultDate = new LinkedHashMap<>();
		        //set default
		        Iterator<LocalDate> itDate = grpDate.keySet().iterator();
				while (itDate.hasNext()) {
					mapDate = new HashMap<>();
					LocalDate date = itDate.next();
					mapDate.put("tour_date", DateUtils.toStr(date, DateUtils.YYYYMMDD));
		        	mapDate.put("visitor", 0);
		        	mapDate.put("booking", 0);
		        	mapDate.put("confirm", 0);
		        	mapDate.put("cancel", 0);
		        	defaultDate.put(date, mapDate);
				}
				
		        for(DashboardTourBooking entity : grpTour.get(key)){
		        	if(defaultDate.containsKey(entity.getId().getTourDate())) {
		        		mapDate = defaultDate.get(entity.getId().getTourDate());
			        	mapDate.put("visitor", entity.getTotalVisitor());
			        	mapDate.put("booking", entity.getTotalBooking());
			        	mapDate.put("confirm", entity.getTotalConfirm());
			        	mapDate.put("cancel", entity.getTotalCancel());
		        	}
		        }					

		        data.put("tour_name", key);
		        data.put("dates", defaultDate.values());
		        response.add(data);
		    }
		}

		return response;
	}
	
}
