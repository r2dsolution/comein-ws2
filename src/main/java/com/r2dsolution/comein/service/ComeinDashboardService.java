package com.r2dsolution.comein.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2dsolution.comein.dao.TourBookingViewRepository;
import com.r2dsolution.comein.dto.DashboardReq;
import com.r2dsolution.comein.entity.TourBookingView;
import com.r2dsolution.comein.exception.ServiceException;
import com.r2dsolution.comein.util.Constant;


@Service
public class ComeinDashboardService {

	private static Logger log = LoggerFactory.getLogger(ComeinDashboardService.class);
	
	@Autowired
	private TourBookingViewRepository tourBookingViewRepository;
	
	public List<Map<String, Object>> getTourBooking(Long companyId, DashboardReq req){
		log.info("getTourBooking companyId : {}",companyId);
		List<Map<String, Object>> response = new LinkedList<>();
		
		List<TourBookingView> entities = tourBookingViewRepository.findByCompanyIdAndTourDateGreaterThanEqualAndTourDateLessThanEqualAndStatus(companyId, req.getDate_from(), req.getDate_to(), Constant.STATUS_BOOKING_BOOKED);
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
}
