package com.r2dsolution.comein.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2dsolution.comein.dao.PayablePeriodRepository;
import com.r2dsolution.comein.dao.PayableTourViewRepository;
import com.r2dsolution.comein.dto.PayableCompanyDto;
import com.r2dsolution.comein.entity.PayablePeriod;
import com.r2dsolution.comein.entity.PayableTourView;
import com.r2dsolution.comein.exception.ServiceException;

@Service
public class PayableCompanyService {

	private static Logger log = LoggerFactory.getLogger(PayableCompanyService.class);
	
	@Autowired
	private PayableTourViewRepository payableTourViewRepository;

	@Autowired
	private PayablePeriodRepository payablePeriodRepository;

	public List<PayableCompanyDto> getPayableTourByCompanyId(Long companyId){
		log.info("getPayableTourByCompanyId companyId : {}", companyId);
		List<PayableCompanyDto> response = new ArrayList<>();;
		
		List<PayableTourView> entities = payableTourViewRepository.findByCompanyIdAndStatus(companyId, "Open");
		if(!entities.isEmpty()) {
			PayableCompanyDto dto = null;
			for(PayableTourView entity : entities) {
				dto = new PayableCompanyDto();
			    dto.setPeriodId(entity.getPeriodId());
				dto.setDateFrom(entity.getDateFrom());
				dto.setDateTo(entity.getDateTo());
				dto.setBookingCode(entity.getBookingCode());
				dto.setTourName(entity.getTourName());
				dto.setTourDate(entity.getTourDate());
				dto.setStatus(entity.getStatus());
				dto.setNetValue(entity.getNetValue());
//				dto.setTotal(BigDecimal.valueOf(43450));
				
			    response.add(dto);
			}
		} else {
			throw new ServiceException("Data not found.");
		}

		return response;
	}
	
	public void savePayableCompanyById(Long companyId, Map<String, Object> req){
		log.info("savePayableCompanyById companyId : {}", companyId);
		
		Long periodId = Long.valueOf((Integer)req.get("periodId"));
		String note = (String)req.get("note");
		Optional<PayablePeriod> opt = payablePeriodRepository.findById(periodId);
		if(opt.isPresent()) {
			PayablePeriod entity = opt.get();
			entity.setNote(note);
			entity.setStatus("Close");
			
			payablePeriodRepository.save(entity);
		} else {
			throw new ServiceException("Data not found.");
		}

		
	}
	
	
}
