package com.r2dsolution.comein.service;

import java.time.LocalDate;
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
import com.r2dsolution.comein.dao.PayablePeriodRepository;
import com.r2dsolution.comein.dao.TourBookingViewRepository;
import com.r2dsolution.comein.dto.DashboardReq;
import com.r2dsolution.comein.entity.DashboardTourBooking;
import com.r2dsolution.comein.entity.PayablePeriod;
import com.r2dsolution.comein.entity.TourBookingView;
import com.r2dsolution.comein.exception.ServiceException;
import com.r2dsolution.comein.util.Constant;
import com.r2dsolution.comein.util.DateUtils;


@Service
public class TourDashboardService {

	private static Logger log = LoggerFactory.getLogger(TourDashboardService.class);
	
	@Autowired
	private DashboardTourBookingRepository dashboardRepository;

	@Autowired
	private TourBookingViewRepository tourBookingViewRepository;
	
	@Autowired
	private PayablePeriodRepository payablePeriodRepository;
	
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
	
	public List<Map<String, Object>> getTourBooking(Long tourId, DashboardReq req){
		log.info("getTourBooking tourId : {}",tourId);
		List<Map<String, Object>> response = new LinkedList<>();
		
		List<TourBookingView> entities = tourBookingViewRepository.findByTourIdAndTourDateAndStatus(tourId, req.getTour_date(), Constant.STATUS_BOOKING_BOOKED);
		if(!entities.isEmpty()) {
			Map<String, Object> map = null;
			for(TourBookingView entity : entities) {
				map = new HashMap<>();
				map.put("booking_code", entity.getBookingCode());
				map.put("reference_name", entity.getReferenceName());
				map.put("total_child", entity.getTotalChild());
				map.put("total_adult", entity.getTotalAdult());
				map.put("status", entity.getStatus());
				map.put("remark", entity.getRemark());
				
				response.add(map);
			}
		} else {
			throw new ServiceException("Data not found.");
		}

		return response;
	}
	
	public List<Map<String, Object>> getPaymentPeriods(Long companyId){
		log.info("getPaymentPeriods companyId : {}",companyId);
		List<Map<String, Object>> response = new LinkedList<>();
		
		List<PayablePeriod> entities = payablePeriodRepository.findByCompanyIdAndPeriodTypeAndStatus(companyId, "Tour", "Close");
		if(!entities.isEmpty()) {
			Map<String, Object> map = null;
			for(PayablePeriod entity : entities) {
				map = new HashMap<>();
				map.put("period_id", entity.getId());
				map.put("period_desc", entity.getPeriodDesc());
				
				response.add(map);
			}
		} else {
			throw new ServiceException("Data not found.");
		}

		return response;
	}
}
