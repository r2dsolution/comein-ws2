package com.r2dsolution.comein.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2dsolution.comein.dao.PayableBookingViewRepository;
import com.r2dsolution.comein.dao.TopUpRateCompanyRepository;
import com.r2dsolution.comein.dao.TopUpRateDefaultRepository;
import com.r2dsolution.comein.dto.HotelPayableNoteDto;
import com.r2dsolution.comein.dto.PayableBookingDetailDto;
import com.r2dsolution.comein.dto.PayableBookingDto;
import com.r2dsolution.comein.dto.ReceivableNoteDto;
import com.r2dsolution.comein.dto.TourPayableNoteDto;
import com.r2dsolution.comein.entity.PayableBookingView;
import com.r2dsolution.comein.entity.TopupRateCompany;
import com.r2dsolution.comein.entity.TopupRateDefault;
import com.r2dsolution.comein.exception.ServiceException;

@Service
public class PayableBookingService {

	private static Logger log = LoggerFactory.getLogger(PayableBookingService.class);
	
	@Autowired
	private PayableBookingViewRepository payableBookingViewRepository;

	@Autowired
	private TopUpRateDefaultRepository topUpRateDefaultRepository;
	
	@Autowired
	private TopUpRateCompanyRepository topUpRateCompanyRepository;
	
	public List<PayableBookingDto> getPayableTourBooking(){
		log.info("getPayableTourBooking ...");
		List<PayableBookingDto> response = new ArrayList<>();;
		
		List<PayableBookingView> entities = payableBookingViewRepository.findAll();
		if(!entities.isEmpty()) {
			PayableBookingDto dto = null;
			for(PayableBookingView entity : entities) {
				dto = new PayableBookingDto();
				dto.setCompanyId(entity.getCompanyId());
			    dto.setBookingCode(entity.getBookingCode());
			    dto.setTourDate(entity.getTourDate());
			    dto.setStatus(entity.getStatus());
			    dto.setPaymentMethod(entity.getPaymentMethod());
			    dto.setPaymentDate(entity.getPaymentDate());
			    dto.setSellValue(entity.getTotalSellValue());
			    
			    response.add(dto);
			}
		} else {
			throw new ServiceException("Data not found.");
		}

		return response;
	}
	
	public PayableBookingDetailDto getPayableTourBookingByBookingCode(String bookingCode){
		log.info("getPayableTourBookingByBookingCode bookingCode : {}", bookingCode);
		PayableBookingDetailDto response = new PayableBookingDetailDto();
		
		PayableBookingView entity = payableBookingViewRepository.findFirstByBookingCode(bookingCode);
		if(entity != null) {
			
			LocalDate currentDate = LocalDate.now();
			BigDecimal receive = entity.getTotalSellValue();
			
			ReceivableNoteDto recv = new ReceivableNoteDto();
			recv.setTransactionDate(currentDate);
			recv.setPaymentMethod(entity.getPaymentMethod());
			recv.setReceive(receive);
			recv.setTotal(receive);
			response.setReceivableNote(recv);
			
			
			Long companyId = entity.getCompanyId();
			List<TopupRateCompany> compRates = topUpRateCompanyRepository.findByCompanyId(companyId);
			if(compRates.isEmpty())
				throw new ServiceException("Data not found.");
			
			BigDecimal comeinRate = BigDecimal.ZERO;
			BigDecimal hotelRate = BigDecimal.ZERO;
			BigDecimal totalRateTour = BigDecimal.ZERO;
			TopupRateCompany firstCompRate = compRates.get(0);
			boolean useDefault = firstCompRate.getUseDefault();
			if(useDefault) {
				List<TopupRateDefault> defaultRates = topUpRateDefaultRepository.findAll();
				for(TopupRateDefault rate : defaultRates) {
					if(receive.compareTo(rate.getMinPeriod()) < 0 && receive.compareTo(rate.getMaxPeriod()) > 0) {
						comeinRate = rate.getComeinRate();
						hotelRate = rate.getHotelRate();
						totalRateTour = receive.subtract(comeinRate).subtract(hotelRate);
					}
				}
			} else {
				for(TopupRateCompany rate : compRates) {
					if(receive.compareTo(rate.getMinPeriod()) < 0 && receive.compareTo(rate.getMaxPeriod()) > 0) {
						comeinRate = rate.getComeinRate();
						hotelRate = rate.getHotelRate();
						totalRateTour = receive.subtract(comeinRate).subtract(hotelRate);
					}					
				}
			}

			TourPayableNoteDto tour = new TourPayableNoteDto();
			tour.setTransactionDate(currentDate);
			tour.setReceive(receive);
			tour.setComeinRate(comeinRate.negate());
			tour.setHotelRate(hotelRate.negate());
			tour.setTotal(totalRateTour);
			response.setTourPayableNote(tour);
			
			HotelPayableNoteDto hotel = new HotelPayableNoteDto();
			hotel.setTransactionDate(currentDate);
			hotel.setHotelRate(hotelRate);
			hotel.setTotal(hotelRate);
			response.setHotelPayableNote(hotel);
			
		} else {
			throw new ServiceException("Data not found.");
		}

		return response;
	}

	public void savePayableTourBookingByBookingCode(String bookingCode, PayableBookingDetailDto req){
		log.info("savePayableTourBookingByBookingCode bookingCode : {}", bookingCode);
		
		//TODO savePayable
		
		
	}
	
}
