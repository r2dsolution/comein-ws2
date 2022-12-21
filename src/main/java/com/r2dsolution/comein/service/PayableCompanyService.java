package com.r2dsolution.comein.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2dsolution.comein.dao.AccountingTransactionRepository;
import com.r2dsolution.comein.dao.PayTransactionRepository;
import com.r2dsolution.comein.dao.PayablePeriodRepository;
import com.r2dsolution.comein.dao.PayableTourViewRepository;
import com.r2dsolution.comein.dao.TourBookingRepository;
import com.r2dsolution.comein.dto.PayableCompanyDto;
import com.r2dsolution.comein.dto.PayableCompanySummaryDto;
import com.r2dsolution.comein.entity.AccountingTransaction;
import com.r2dsolution.comein.entity.PayTransaction;
import com.r2dsolution.comein.entity.PayablePeriod;
import com.r2dsolution.comein.entity.PayableTourView;
import com.r2dsolution.comein.entity.TourBooking;
import com.r2dsolution.comein.exception.ServiceException;
import com.r2dsolution.comein.util.Constant;

@Service
public class PayableCompanyService {

	private static Logger log = LoggerFactory.getLogger(PayableCompanyService.class);
	
	@Autowired
	private PayableTourViewRepository payableTourViewRepository;

	@Autowired
	private PayablePeriodRepository payablePeriodRepository;

	@Autowired
	private TourBookingRepository tourBookingRepository;
	
	@Autowired
	private PayTransactionRepository payTransactionRepository;
	
	@Autowired
	private AccountingTransactionRepository accountingTransactionRepository;

	public List<PayableCompanySummaryDto> getPayableTourByCompanyId(Long companyId){
		log.info("getPayableTourByCompanyId companyId : {}", companyId);
		Map<Long, PayableCompanySummaryDto> map = new LinkedHashMap<>();
		PayableCompanySummaryDto sumDto = null;
		List<PayableCompanyDto> details = new ArrayList<>();;
		
		List<PayableTourView> entities = payableTourViewRepository.findByCompanyIdAndStatus(companyId, "Open");
		if(!entities.isEmpty()) {
			PayableCompanyDto dto = null;
			Long periodId = null;
			BigDecimal netValue = BigDecimal.ZERO;
			BigDecimal total = BigDecimal.ZERO;
			for(PayableTourView entity : entities) {
				
				periodId = entity.getPeriodId();
				netValue = entity.getNetValue();
				if(map.containsKey(periodId)) {
					sumDto = map.get(periodId);
					details = sumDto.getDetails();
					total = sumDto.getTotal().add(netValue);
					
					sumDto.setTotal(total);
				} else {
					sumDto = new PayableCompanySummaryDto();
					dto = new PayableCompanyDto();	
					details = new ArrayList<>();
					total = netValue;
					
					sumDto.setPeriodId(entity.getPeriodId());
					sumDto.setPeriodDesc(entity.getPeriodDesc());
					sumDto.setStatus(entity.getStatus());
					sumDto.setTotal(total);
					sumDto.setDetails(details);
					
					map.put(periodId, sumDto);
				}
				
			    dto.setPeriodId(periodId);
				dto.setDateFrom(entity.getDateFrom());
				dto.setDateTo(entity.getDateTo());
				dto.setBookingCode(entity.getBookingCode());
				dto.setTourName(entity.getTourName());
				dto.setTourDate(entity.getTourDate());
				dto.setStatus(entity.getStatus());
				dto.setNetValue(entity.getNetValue());
				
				details.add(dto);
			}
		} else {
			throw new ServiceException("Data not found.");
		}

		return new ArrayList<>(map.values());
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
			
			Optional<PayTransaction> payTrxOpt = this.payTransactionRepository.findById(entity.getPayId());
			if(payTrxOpt.isPresent()) {
				
				Optional<AccountingTransaction> acctTrxOpt = this.accountingTransactionRepository.findById(payTrxOpt.get().getAccountingId());
				if(acctTrxOpt.isPresent()) {
					TourBooking tourBooking = this.tourBookingRepository.findFirstByBookingCodeAndStatus(acctTrxOpt.get().getBookingCode(), Constant.STATUS_BOOKING_BOOKED);
					if(tourBooking != null) {
						tourBooking.setPayStatus(Constant.STATUS_PAY_PAID);
						
						this.tourBookingRepository.save(tourBooking);
					}
				}
			}

		} else {
			throw new ServiceException("Data not found.");
		}

		
	}
	
	
}
