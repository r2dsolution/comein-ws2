package com.r2dsolution.comein.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.r2dsolution.comein.util.ObjectUtils;

import com.r2dsolution.comein.dao.BookingRepository;
import com.r2dsolution.comein.dao.OtaBookingRepository;
import com.r2dsolution.comein.dto.BookingDto;
import com.r2dsolution.comein.dto.OtaBookingDto;
import com.r2dsolution.comein.dto.PaggingDto;
import com.r2dsolution.comein.dto.ResponseListDto;
import com.r2dsolution.comein.entity.BookingInfo;
import com.r2dsolution.comein.entity.OtaBooking;
import com.r2dsolution.comein.exception.ServiceException;
import com.r2dsolution.comein.spec.OtaBookingSpecification;
import com.r2dsolution.comein.util.Constant;
import com.r2dsolution.comein.util.StringUtils;

@Service
public class OtaBookingService {

	private static Logger log = LoggerFactory.getLogger(OtaBookingService.class);
	
	@Autowired
	private OtaBookingRepository otaBookingRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	public ResponseListDto<OtaBookingDto> searchOtaBooking(LocalDate startDate, LocalDate endDate, Pageable pageable){
		log.info("searchOtaBooking startDate : {}, endDate : {}", startDate, endDate);
		ResponseListDto<OtaBookingDto> response = new ResponseListDto<>();
		
		List<OtaBookingDto> results = new ArrayList<>();
		OtaBookingSpecification spec = new OtaBookingSpecification(null, startDate, endDate);
		
		Sort sort = Sort.by(Direction.ASC, "checkinDate");
		Pageable pageable1 = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
		Page<OtaBooking> entities = otaBookingRepository.findAll(spec, pageable1);
		
		for(OtaBooking entity : entities) {
			results.add(convertOtaBookingToDto(entity));
		}
		
		response.setDatas(results);
		
		Pageable page = entities.getPageable();
		PaggingDto pagging = new PaggingDto(page.getPageNumber(), page.getPageSize(), entities.getTotalElements());
		response.setPagging(pagging);
		
		
		return response;
	}
	
	public OtaBookingDto getOtaBooking(Long id){
		log.info("getOtaBooking id : {}", id);
		OtaBookingDto response = null;
		
		Optional<OtaBooking> opt = this.otaBookingRepository.findById(id);
		if(opt.isPresent()) {
			OtaBooking entity = opt.get();
			response = convertOtaBookingToDto(entity);
		}
		
		return response;
	}
	
	private OtaBookingDto convertOtaBookingToDto(OtaBooking entity) {
		OtaBookingDto dto = new OtaBookingDto();
	    
		dto.setId(entity.getId());
	    dto.setIsBooking(entity.getIsBooking());
	    dto.setIsCancel(entity.getIsCancel());
	    dto.setTemplateLogic(entity.getTemplateLogic());
	    dto.setFirstName(entity.getFirstName());
	    dto.setLastName(entity.getLastName());
	    dto.setCheckinDate(entity.getCheckinDate());
	    dto.setCheckoutDate(entity.getCheckoutDate());
	    dto.setEmail(entity.getEmail());
	    dto.setContactNo(entity.getContactNo());
	    dto.setNationality(entity.getNationality());
	    dto.setRoomNight(entity.getRoomNight());
	    dto.setRoomType(entity.getRoomType());
	    dto.setBookingNumber(entity.getBookingNumber());
	    dto.setPrice(entity.getPrice());
	    dto.setAdult(entity.getAdult());
	    dto.setChild(entity.getChild());
	    dto.setHotelName(entity.getHotelName());
	    dto.setDateReceive(entity.getDateReceive());
	    dto.setFeedDate(entity.getFeedDate());
	    dto.setCreatedDate(entity.getCreatedDate());
	    dto.setStatus(entity.getStatus());
	    dto.setRemark(entity.getRemark());
	    
	    return dto;
	}

	public void manualMatch(OtaBookingDto req, String userToken){
		log.info("Start manualMatch id : {}, hotelId : {}", req.getId(), req.getHotelId());
		
		if(req.getId() == null) {
			throw new ServiceException("Id is require.");	
		}		if(req.getHotelId() == null) {
			throw new ServiceException("Hotel is require.");	
		}
		
		Optional<OtaBooking> opt = this.otaBookingRepository.findById(req.getId());
		if(opt.isPresent()) {

			LocalDateTime currentTimestamp = LocalDateTime.now();
			
			OtaBooking entity = opt.get();
			entity.setStatus(Constant.STATUS_OTA_MANUAL_MATCH);
//			entity.setUpdatedDate(currentTimestamp);
//			entity.setUpdatedBy(userToken);
			
			this.otaBookingRepository.save(entity);
			
			BookingInfo bookingInfo = new BookingInfo();
			
			long visitorAdult = entity.getAdult();
			LocalDate checkin = req.getCheckinDate()==null?LocalDate.now():req.getCheckinDate();
			LocalDate checkout = req.getCheckoutDate()==null?LocalDate.now():req.getCheckoutDate();
			String roomName = StringUtils.getValueOrDefault(req.getRoomType(), "-");
			LocalDate bookingDate = entity.getDateReceive()==null?null:entity.getDateReceive().toLocalDate();
			String referenceName = StringUtils.trimToEmpty(entity.getFirstName()) + " " + StringUtils.trimToEmpty(entity.getLastName());
			
			bookingInfo.setBookingNo(entity.getBookingNumber());
			bookingInfo.setHotelId(req.getHotelId());
			bookingInfo.setBookingDate(bookingDate);
			bookingInfo.setRoomName(roomName);
			bookingInfo.setRoomDesc(null);
			bookingInfo.setRefName(referenceName);
			bookingInfo.setCheckin(checkin);
			bookingInfo.setCheckout(checkout);
			bookingInfo.setVisitorAdult(visitorAdult);
			bookingInfo.setVisitorChild(entity.getChild());
			bookingInfo.setStatus(Constant.STATUS_ACTIVE);
			bookingInfo.setCreatedDate(currentTimestamp);
			bookingInfo.setCreatedBy(userToken);
			bookingInfo.setUpdatedDate(currentTimestamp);
			bookingInfo.setUpdatedBy(userToken);
			
			bookingInfo.setOtaRefEmail(entity.getEmail());
			bookingInfo.setOtaRefContact(entity.getContactNo());
			bookingInfo.setOtaBookingId(entity.getId());
			bookingInfo.setOtaCancelId(null);
			bookingInfo.setPrice(entity.getPrice());
			
			this.bookingRepository.save(bookingInfo);
			
		}
		log.info("End manualMatch success.");
	}
	
	public void saveBooking(BookingDto req, String userToken){
		log.info("Start saveBooking bookingNo : {}", req.getBookingNo());
		
		if(req != null) {
			if(ObjectUtils.isEmpty(req.getBookingNo())){
				throw new ServiceException("Booking No is require.");
			}
		}
		
		LocalDateTime currentTimestamp = LocalDateTime.now();
		
		try {
			
			BookingInfo entity = new BookingInfo();
			
			log.info("saveBooking bookingNo : {}", req.getBookingNo());
			
			long visitorAdult = req.getVisitorAdult()==null?0:req.getVisitorAdult();
			LocalDate checkin = req.getCheckin()==null?LocalDate.now():req.getCheckin();
			LocalDate checkout = req.getCheckout()==null?LocalDate.now():req.getCheckout();
			String roomName = StringUtils.getValueOrDefault(req.getRoomName(), "-");
			String roomDesc = StringUtils.getValueOrDefault(req.getRoomDesc(), "-");
			
			entity.setBookingNo(req.getBookingNo());
			entity.setHotelId(req.getHotelId());
			entity.setBookingDate(req.getBookingDate());
			entity.setRoomName(roomName);
			entity.setRoomDesc(roomDesc);
			entity.setRefName(req.getReferenceName());
			entity.setCheckin(checkin);
			entity.setCheckout(checkout);
			entity.setVisitorAdult(visitorAdult);
			entity.setVisitorChild(req.getVisitorChild());
			entity.setStatus(Constant.STATUS_INVITED);
			entity.setCreatedDate(currentTimestamp);
			entity.setCreatedBy(userToken);
			entity.setUpdatedDate(currentTimestamp);
			entity.setUpdatedBy(userToken);
			
			this.bookingRepository.save(entity);
			
		}catch(Exception e) {
			log.error("Exception, ", e);
			throw new ServiceException(e);
		}
		
		log.info("End saveBooking bookingNo : {}", req.getBookingNo());
	}

	public void saveOtaBooking(OtaBookingDto req, String userToken){
		log.info("Start saveOtaBooking hotelName : {}, checin", req.getHotelName());
			
		LocalDateTime currentTimestamp = LocalDateTime.now();
		LocalDate currentDate = LocalDate.now();
		
		OtaBooking entity = new OtaBooking();
			
	    entity.setIsBooking(req.getIsBooking());
	    entity.setIsCancel(req.getIsCancel());
	    entity.setTemplateLogic(req.getTemplateLogic());
	    entity.setFirstName(req.getFirstName());
	    entity.setLastName(req.getLastName());
	    entity.setCheckinDate(req.getCheckinDate());
	    entity.setCheckoutDate(req.getCheckoutDate());
	    entity.setEmail(req.getEmail());
	    entity.setContactNo(req.getContactNo());
	    entity.setNationality(req.getNationality());
	    entity.setRoomNight(req.getRoomNight());
	    entity.setRoomType(req.getRoomType());
	    entity.setBookingNumber(req.getBookingNumber());
	    entity.setPrice(req.getPrice());
	    entity.setAdult(req.getAdult());
	    entity.setChild(req.getChild());
	    entity.setHotelName(req.getHotelName());
	    entity.setDateReceive(req.getDateReceive());
	    entity.setCreatedDate(currentTimestamp);
	    entity.setFeedDate(currentDate);
	    entity.setFeedDate(null);
	    entity.setStatus(Constant.STATUS_OTA_UNMATCH);
	    entity.setRemark(req.getRemark());
	    
		this.otaBookingRepository.save(entity);
		
		log.info("End saveOtaBooking success.");
	}
}
