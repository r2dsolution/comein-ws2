package com.r2dsolution.comein.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.r2dsolution.comein.cognito.AWSCognitoService;
import com.r2dsolution.comein.dao.TourBookingRepository;
import com.r2dsolution.comein.dao.TourBookingTicketRepository;
import com.r2dsolution.comein.dao.TourBookingViewRepository;
import com.r2dsolution.comein.dao.TourTicketRepository;
import com.r2dsolution.comein.dto.PaggingDto;
import com.r2dsolution.comein.dto.PersonalDto;
import com.r2dsolution.comein.dto.ResponseListDto;
import com.r2dsolution.comein.dto.TourBookingDto;
import com.r2dsolution.comein.entity.TourBooking;
import com.r2dsolution.comein.entity.TourBookingTicket;
import com.r2dsolution.comein.entity.TourBookingView;
import com.r2dsolution.comein.entity.TourTicket;
import com.r2dsolution.comein.exception.ServiceException;
import com.r2dsolution.comein.spec.TourBookingViewSpecification;
import com.r2dsolution.comein.util.Constant;
import com.r2dsolution.comein.util.ObjectUtils;
import com.r2dsolution.comein.util.StringUtils;

@Service
public class TourBookingService {

	private static Logger log = LoggerFactory.getLogger(TourBookingService.class);
	
	@Autowired
	private TourBookingRepository tourBookingRepository;
	
	@Autowired
	private TourBookingViewRepository tourBookingViewRepository;
	
	@Autowired
	private TourTicketRepository tourTicketRepository;
	
	@Autowired
	private AWSCognitoService cognitoService;
	
	@Autowired
	private TourBookingTicketRepository tourBookingTicketRepository;

	public ResponseListDto<TourBookingDto> searchTourBooking(String dateType, LocalDate startDate, LocalDate endDate, String bookingCode, String referenceName, Pageable pageable){
		log.info("searchTourBooking dateType : {}, startDate : {}, endDate : {}, bookingCode : {}, referenceName : {}", dateType, startDate, endDate, bookingCode, referenceName);
		ResponseListDto<TourBookingDto> response = new ResponseListDto<>();
		
		List<TourBookingDto> results = new ArrayList<>();
		TourBookingView filter = new TourBookingView();
		filter.setBookingCode(bookingCode);
		filter.setReferenceName(referenceName);
		TourBookingViewSpecification spec = new TourBookingViewSpecification(filter, dateType, startDate, endDate);
		
		Sort sort = Sort.by(Direction.ASC, "bookingDate");
		Pageable pageable1 = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
		Page<TourBookingView> entities = tourBookingViewRepository.findAll(spec, pageable1);
		
		TourBookingDto dto = null;
		for(TourBookingView entity : entities) {
			dto = new TourBookingDto();
			dto.setId(entity.getId());
			dto.setBookingCode(entity.getBookingCode());
			dto.setReferenceName(entity.getReferenceName());
			dto.setLocationPickup(entity.getLocationPickup());
			dto.setBookingDate(entity.getBookingDate());
			dto.setTotalAdult(entity.getTotalAdult());
			dto.setTotalChild(entity.getTotalChild());
			dto.setTotalRate(entity.getTicketSellValue());
			dto.setRemark(entity.getRemark());
			dto.setPaymentMethod(entity.getPaymentMethod());
			dto.setTourName(entity.getTourName());
			dto.setTourDate(entity.getTourDate());
		    
			results.add(dto);
		}
		
		response.setDatas(results);
		
		Pageable page = entities.getPageable();
		PaggingDto pagging = new PaggingDto(page.getPageNumber(), page.getPageSize(), entities.getTotalElements());
		response.setPagging(pagging);
		
		
		return response;
	}
	
	public TourBookingDto getTourBooking(String bookingCode){
		System.out.println("getTourBooking bookingCode : "+bookingCode);
		TourBookingDto response = null;
		
		TourBookingView entity = tourBookingViewRepository.findFirstByBookingCodeAndStatus(bookingCode, Constant.STATUS_BOOKING_BOOKED);
		if(entity != null) {
			response = new TourBookingDto();
			response.setId(entity.getId());
			response.setBookingCode(entity.getBookingCode());
			response.setReferenceName(entity.getReferenceName());
			response.setLocationPickup(entity.getLocationPickup());
			response.setBookingDate(entity.getBookingDate());
			response.setTotalAdult(entity.getTotalAdult());
			response.setTotalChild(entity.getTotalChild());
			response.setTotalRate(entity.getTicketSellValue());
			response.setRemark(entity.getRemark());
			response.setPaymentMethod(entity.getPaymentMethod());
			response.setTourName(entity.getTourName());
			response.setTourDate(entity.getTourDate());
			response.setCancelable(entity.getCancelable());
			response.setCancelBefore(entity.getCancelBefore());
			
			response.setCancelFlag(Constant.CANCEL_FLAG_NOT_ALLOW);
			LocalDate current = LocalDate.now();
			LocalDate tourDate = entity.getTourDate();
			if(current.isBefore(tourDate)) {
				String cancelable = entity.getCancelable();
				int cancelBefore = entity.getCancelBefore();
				LocalDate cancelableDate = tourDate.minusDays(cancelBefore);
				if(!ObjectUtils.isEmpty(cancelable) && Constant.FLAG_Y.equals(cancelable)) {
					if(current.isBefore(cancelableDate)) {
						response.setCancelFlag(Constant.CANCEL_FLAG_ALLOW);
					} else {
						response.setCancelFlag(Constant.CANCEL_FLAG_WARNING);
					}
				}
			}
			
			String ownerId = entity.getOwnerId();
			PersonalDto personalRes = null;
			if(!StringUtils.isEmpty(ownerId)) {
				PersonalDto personalReq = new PersonalDto();
				personalReq.setOwnerId(ownerId);
				List<PersonalDto> persons = this.cognitoService.get(AWSCognitoService.GET_USER, personalReq);
				if(!persons.isEmpty()) {
					personalRes = persons.get(0);
					response.setPersonalData(personalRes);
				}
			}
			
		} else {
			throw new ServiceException("Data not found.");
		}

		return response;
	}
	
	public void cancelTourBooking(String bookingCode, String userToken){
		log.info("Start cancelTourBooking bookingCode : {}", bookingCode);
		
		if(ObjectUtils.isEmpty(bookingCode)) {
			throw new ServiceException("Booking Code is require.");	
		}
		
		LocalDateTime currentTimestamp = LocalDateTime.now();
		TourBooking booking = this.tourBookingRepository.findFirstByBookingCodeAndStatus(bookingCode, Constant.STATUS_BOOKING_BOOKED);
		if(booking != null) {
			booking.setStatus(Constant.STATUS_BOOKING_CANCEL);
			booking.setUpdatedBy(userToken);
			booking.setUpdatedDate(currentTimestamp);

			this.tourBookingRepository.save(booking);

			List<Long> tickets = this.tourBookingTicketRepository.findTicketIdByBookingId(booking.getId());

			this.tourTicketRepository.updateByTicketIdIn(Constant.STATUS_TICKET_CANCEL, tickets);
		}
//			int cntCancel = this.tourBookingRepository.cancelTourBooking(Constant.STATUS_CANCEL, userToken, bookingCode, Constant.STATUS_ENABLE);
//			log.info("cancelTourBooking bookingCode : {}, cnt : {} success.", bookingCode, cntCancel);
			
		log.info("End cancelTourBooking bookingCode : {}", bookingCode);
	}

	public void changeTourBooking(TourBookingDto req, String userToken){
		log.info("Start changeTourBooking bookingCode : {}, tourDate : {}", req.getBookingCode(), req.getTourData());
		
		if(ObjectUtils.isEmpty(req.getBookingCode())) {
			throw new ServiceException("Booking Code is require.");	
		}
		if(ObjectUtils.isEmpty(req.getTourDate())) {
			throw new ServiceException("Tour Date is require.");	
		}
		
		String bookingCode = req.getBookingCode();
		int cntBooking = this.tourBookingRepository.countByBookingCodeAndStatus(bookingCode, Constant.STATUS_BOOKING_BOOKED);
		log.info("cntBooking : {}", cntBooking);
		if(cntBooking > 0) {
			int cntUpdate = this.tourBookingRepository.updateTourBooking(req.getTourDate(), userToken, bookingCode, Constant.STATUS_BOOKING_BOOKED);
			log.info("changeTourBooking bookingCode : {} with tourDate : {}, cnt : {} success.", bookingCode, req.getTourDate(), cntUpdate);
		}
		log.info("End changeTourBooking bookingCode : {}", bookingCode);
	}

	public void saveTourBooking(TourBookingDto req, String userToken){
		log.info("Start saveTourBooking bookingCode : {}, tourDate : {}", req.getBookingCode(), req.getTourData());

		LocalDateTime currentTimestamp = LocalDateTime.now();
		
		long tourId = req.getTourData().getId();
		int totalAdult = req.getTotalAdult();
		int totalChild = req.getTotalChild();
		
		//TODO Mock Test
//		List<TourTicket> tourTickets = this.tourTicketRepository.findByTourIdAndStatus(tourId, Constant.STATUS_TICKET_ACTIVE);
//		List<TourBookingTicket> tourBookingTickets = new ArrayList<>();
//		
//		BigDecimal ticketSellValue = BigDecimal.ZERO;
//		BigDecimal hotelSellValue = BigDecimal.ZERO;
//		BigDecimal netValue = BigDecimal.ZERO;
//		BigDecimal sellValue = BigDecimal.ZERO;
//		int cntAdult = totalAdult;
//		int cntChild = totalChild;
//		TourBookingTicket tourBookingTicket = null;
//		for(TourTicket tourTicket : tourTickets) {
//			tourBookingTicket = new TourBookingTicket();
//			tourBookingTicket.setTicketId(tourTicket.getId());
//			
//			if(cntAdult > 0) {
//				sellValue = tourTicket.getAdultRate();
//				tourBookingTicket.setBookingType("Adult");
//				tourBookingTicket.setSellValue(sellValue);
//				cntAdult--;
//			} else if(cntChild > 0) {
//				sellValue = tourTicket.getChildRate();
//				tourBookingTicket.setBookingType("Child");
//				tourBookingTicket.setSellValue(sellValue);
//				cntChild--;
//			}
//			ticketSellValue.add(sellValue);
//			
//			tourBookingTickets.add(tourBookingTicket);
//		}
//		
//		TourBooking tourBooking = new TourBooking();
//		tourBooking.setBookingCode(req.getBookingCode());
//	    tourBooking.setOwnerId("XXXXX");
//	    tourBooking.setReferenceName(req.getReferenceName());
//	    tourBooking.setLocationPickup(req.getLocationPickup());
//	    tourBooking.setBookingDate(req.getBookingDate());
//	    tourBooking.setPaymentMethod(req.getPaymentMethod());
//	    tourBooking.setRemark(req.getRemark());
//	    tourBooking.setStatus(Constant.STATUS_BOOKING_BOOKED);
//	    tourBooking.setCreatedDate(currentTimestamp);
//	    tourBooking.setCreatedBy(userToken);
//	    tourBooking.setUpdatedDate(currentTimestamp);
//	    tourBooking.setUpdatedBy(userToken);
//	    tourBooking.setTourName(req.getTourName());
//	    tourBooking.setTourDate(req.getTourDate());
//	    tourBooking.setTicketSellValue(ticketSellValue);
//	    tourBooking.setHotelSellValue(hotelSellValue);
//	    tourBooking.setNetValue(netValue);
//	    tourBooking.setCompanyId(req.getCompanyId());
//	    tourBooking.setTourId(req.getTourData().getId());
//	    
//	    tourBooking = this.tourBookingRepository.save(tourBooking);
//	    long bookingId = tourBooking.getId();
//	    for(TourBookingTicket bookingTicket : tourBookingTickets) {
//	    	bookingTicket.setBookingId(bookingId);
//	    	bookingTicket.setCreatedDate(currentTimestamp);
//	    	bookingTicket.setCreatedBy(userToken);
//	    	bookingTicket.setUpdatedDate(currentTimestamp);
//	    	bookingTicket.setUpdatedBy(userToken);
//	    }
//	    this.tourBookingTicketRepository.saveAll(tourBookingTickets);
	    
		log.info("End saveTourBooking bookingCode : {}", req.getBookingCode());
	}
	
}
